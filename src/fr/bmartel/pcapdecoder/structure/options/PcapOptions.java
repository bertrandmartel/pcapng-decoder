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

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.impl.OptionsEnhancedHeader;
import fr.bmartel.pcapdecoder.structure.options.impl.OptionsInterfaceDescriptionHeader;
import fr.bmartel.pcapdecoder.structure.options.impl.OptionsInterfaceStatisticsHeader;
import fr.bmartel.pcapdecoder.structure.options.impl.OptionsSectionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;

/**
 * Pcap ng options field
 * 
 *  0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|      Option Code              |         Option Length         |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
/                       Option Value                            /
/             variable length, aligned to 32 bits               /
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
public class PcapOptions implements IPcapngOptions{
	
	private int optionCode = -1;
	
	private int optionLength= -1;
	
	private byte[] optionValue= null;
	
	private boolean isBigEndian = true;
	
	private BlockTypes type = null;
	
	private IOptions option = null;
	
	public PcapOptions(int optionCode,int optionLength,byte[] optionValue,boolean isBigEndian,BlockTypes type,IOptions option)
	{
		this.optionCode=optionCode;
		this.optionLength=optionLength;
		this.optionValue=optionValue;
		this.isBigEndian= isBigEndian;
		this.type=type;
		this.option=option;
	}
	
	/**
	 * Decode given data for optional fields
	 */
	public void decode()
	{
		if (type == BlockTypes.SECTION_HEADER_BLOCK)
		{
			OptionsSectionHeader optionImpl = new OptionsSectionHeader(optionCode,optionValue,isBigEndian,this.option);
		}
		if (type == BlockTypes.INTERFACE_DESCRIPTION_BLOCK)
		{
			OptionsInterfaceDescriptionHeader optionImpl = new OptionsInterfaceDescriptionHeader(optionCode,optionValue,isBigEndian,this.option);
		}
		if (type == BlockTypes.ENHANCES_PACKET_BLOCK)
		{
			OptionsEnhancedHeader optionImpl = new OptionsEnhancedHeader(optionCode,optionValue,isBigEndian,this.option);
		}
		if (type == BlockTypes.INTERFACE_STATISTICS_BLOCK)
		{
			OptionsInterfaceStatisticsHeader optionImpl = new OptionsInterfaceStatisticsHeader(optionCode,optionValue,isBigEndian,this.option);
		}
	}
	
	@Override
	public int getOptionCode()
	{
		return optionCode;
	}
	
	@Override
	public int getOptionLength()
	{
		return optionLength;
	}
	
	@Override
	public byte[] getOptionValue()
	{
		return optionValue;
	}

	public boolean isBigEndian() {
		return isBigEndian;
	}

	public BlockTypes getType() {
		return type;
	}
}
