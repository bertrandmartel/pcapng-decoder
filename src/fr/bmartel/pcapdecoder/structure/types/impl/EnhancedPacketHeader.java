/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.IEnhancedPacketBLock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Implementation for ENHANCED PACKET HEADER SECTION
 * 
 *    0                   1                   2                   3
   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +---------------------------------------------------------------+
 0 |                    Block Type = 0x00000006                    |
   +---------------------------------------------------------------+
 4 |                      Block Total Length                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 8 |                         Interface ID                          |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
12 |                        Timestamp (High)                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
16 |                        Timestamp (Low)                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
20 |                         Captured Len                          |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
24 |                          Packet Len                           |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
28 /                                                               /
   /                          Packet Data                          /
   /             variable length, aligned to 32 bits               /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   /                                                               /
   /                      Options (variable)                       /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Block Total Length                       |
   +---------------------------------------------------------------+
   
 * @author Bertrand Martel
 *
 */
public class EnhancedPacketHeader implements IEnhancedPacketBLock,IPcapngType{

	private int interfaceId = -1;
	
	/**
	 * length of packetData
	 */
	private int capturedLength = -1;
	
	/**
	 * 
	 */
	private int packetLength=-1;
	
	private Long timestamp = -1l;
	
	private byte[] packetData = null;
	
	private IOptionsEnhancedPacketHeader options  = null;
	
	public EnhancedPacketHeader(byte[] data,boolean isBigEndian,BlockTypes type) {
		
		if (isBigEndian)
		{
			interfaceId= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 0,4));
			byte[] high_timestamp = Arrays.copyOfRange(data, 4,8);
			byte[] low_timestamp  = Arrays.copyOfRange(data, 8,12);
			
			byte[] finalTimestamp = new byte[8];
			for (int i = 0; i  <4;i++)
			{
				finalTimestamp[i]=high_timestamp[i];
			}
			for (int i = 0;i<4;i++)
			{
				finalTimestamp[i+4]=low_timestamp[i];
			}

			timestamp=(long) UtilFunctions.convertByteArrayToLong(finalTimestamp);
			
			
			capturedLength=UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 12,16));
			packetLength=UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 16,20));
			if (capturedLength>0)
			{
				packetData=Arrays.copyOfRange(data, 20,20+capturedLength);
			}
			else
			{
				packetData=new byte[]{};
			}
		}
		else
		{
			interfaceId= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0,4)));
			byte[] high_timestamp= UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4,8));
			byte[] low_timestamp = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 8,12));
			
			byte[] finalTimestamp = new byte[8];
			for (int i = 0; i  <4;i++)
			{
				finalTimestamp[i]=high_timestamp[i];
			}
			for (int i = 0;i<4;i++)
			{
				finalTimestamp[i+4]=low_timestamp[i];
			}

			timestamp=(long) UtilFunctions.convertByteArrayToLong(finalTimestamp);
			
			capturedLength=UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 12,16)));
			packetLength=UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 16,20)));
			
			if (capturedLength>0)
			{
				packetData=Arrays.copyOfRange(data, 20,20+capturedLength);
			}
			else
			{
				packetData=new byte[]{};
			}
			
		}
		
		if (data.length>20+capturedLength)
		{
			//parse set of options
			OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 20+capturedLength + (capturedLength%2),data.length), isBigEndian,type);
			optionParser.decode();
			this.options=(IOptionsEnhancedPacketHeader) optionParser.getOption();
		}
	}
	
	@Override
	public int getInterfaceId() {
		return interfaceId;
	}

	@Override
	public Long getTimeStamp() {
		return timestamp;
	}

	@Override
	public int getCapturedLength() {
		return capturedLength;
	}

	@Override
	public int getPacketLength() {
		return packetLength;
	}

	@Override
	public byte[] getPacketData() {
		return packetData;
	}

	@Override
	public IOptionsEnhancedPacketHeader getOptions() {
		return options;
	}
}
