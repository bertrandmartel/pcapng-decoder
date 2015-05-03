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
package fr.bmartel.pcapdecoder.structure.options;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionEnhancedPacketHeaderObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceDescriptionObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceStatisticsObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionSectionHeaderObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Pcap NG option parser
 * 
 * => this is needed for several options can be present in the same section
 * 
 *  0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|      Option Code              |         Option Length         |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
/                       Option Value                            /
/            variable length, aligned to 32 bits               /
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
/                                                               /
/                 . . . other options . . .                     /
/                                                               /
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|   Option Code == opt_endofopt  |  Option Length == 0          |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

 * @author Bertrand Martel 
 *
 */
public class OptionParser {

	/**
	 * option data
	 */
	private byte[] data = null;
	
	/**
	 * type of section (option validation depends on block section type)
	 */
	private BlockTypes type = null;
	
	/**
	 * define if data is in big endian or little endian
	 */
	private boolean isBigEndian = true;
	
	private IOptions option = null;
	
	/**
	 * Build parser with data
	 * 
	 * @param data
	 * @param isBigEndian
	 * @BlockTypes type
	 */
	public OptionParser(byte[] data,boolean isBigEndian,BlockTypes type)
	{
		this.data=data;
		this.isBigEndian=isBigEndian;
		this.type=type;
	}
	
	/**
	 * decode data option
	 */
	public void decode()
	{
		int initIndex = 0;
		
		if (type==BlockTypes.SECTION_HEADER_BLOCK)
		{
			option=new OptionSectionHeaderObject();
		}
		else if (type==BlockTypes.INTERFACE_DESCRIPTION_BLOCK)
		{
			option=new OptionInterfaceDescriptionObject();
		}
		else if (type==BlockTypes.ENHANCES_PACKET_BLOCK)
		{
			option=new OptionEnhancedPacketHeaderObject();
		}
		else if (type==BlockTypes.INTERFACE_STATISTICS_BLOCK)
		{
			option=new OptionInterfaceStatisticsObject();
		}
		
		while (initIndex!=data.length)
		{
			int optionCode=0;
			int optionLength=0;
			byte[] optionValue=null;
			
			if (isBigEndian)
			{
				optionCode= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, initIndex+0,initIndex+2));
				if (optionCode==0)
				{
					return;
				}
				
				optionLength= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, initIndex+2,initIndex+4));
				if (optionLength>0)
				{
					optionValue = Arrays.copyOfRange(data, initIndex+4,initIndex+4+optionLength);
				}
			}
			else
			{
				optionCode= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, initIndex+0,initIndex+2)));
				if (optionCode==0)
				{
					return;
				}
				optionLength= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, initIndex+2,initIndex+4)));
				if (optionLength>0)
				{
					optionValue = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, initIndex+4,initIndex+4+optionLength));
				}
			}
			
			if (optionLength==0)
			{
				initIndex+=2;
			}
			else
			{
				PcapOptions optionParser = new PcapOptions(optionCode,optionLength,optionValue,isBigEndian,type,option);
				optionParser.decode();
				
				initIndex+=4 + optionLength;
			}
			
			initIndex=initIndex+(optionLength%2);
			
			int endOfOption= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, initIndex+0,initIndex+2)));
			
			
			if (endOfOption==0)
			{
				initIndex+=2;
			}
		}
		
	}

	public byte[] getData() {
		return data;
	}

	public boolean isBigEndian() {
		return isBigEndian;
	}

	public BlockTypes getType() {
		return type;
	}

	public IOptions getOption() {
		return option;
	}
}
