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
package fr.bmartel.pcapdecoder;

import java.util.ArrayList;
import java.util.Arrays;

import fr.bmartel.pcapdecoder.constant.HeaderBlocks;
import fr.bmartel.pcapdecoder.constant.MagicNumber;
import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.PcapNgStructureParser;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;
import fr.bmartel.pcapdecoder.utils.DecodeException;
import fr.bmartel.pcapdecoder.utils.DecoderStatus;
import fr.bmartel.pcapdecoder.utils.Endianess;

/**
 * PCAP NG decoder 
 * 
 * @author Bertrand Martel
 *
 */
public class PcapDecoder {

	/**
	 * data to parse
	 */
	private byte[] data=null;
	
	private boolean isBigEndian = true;
	
	private ArrayList<IPcapngType> pcapSectionList = new ArrayList<IPcapngType>();
	
	/**
	 * instantiate Pcap Decoder with a new data to parse (from Pcap Ng file)
	 * @param data
	 */
	public PcapDecoder(byte[] data)
	{
		this.data=data;
	}
	
	/**
	 * Detect endianess with magic number in section header block : will be  0x1A2B3C4D in big endian and  0x4D3C2B1A for little endian
	 * 
	 * @param magicNumber
	 * @return
	 */
	private byte detectEndianness(byte[] magicNumber)
	{
		if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_BIG_ENDIAN, magicNumber,isBigEndian))
		{
			return Endianess.BIG_ENDIAN;
		}
		else if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_LITTLE_ENDIAN, magicNumber,isBigEndian))
		{
			return Endianess.LITTLE_ENDIAN;
		}
		else
		{
			return Endianess.NO_ENDIANESS;
		}
	}
	
	private int parseBlockLength(byte[] length,boolean isBigEndian)
	{
		if (isBigEndian)
		{
			int blockLength = (((data[0]<<32) &0xFF) + ((data[1]<<16) & 0xFF) + ((data[2]<<8) & 0xFF) + ((data[3]<<0) & 0xFF));
			return blockLength;
		}
		else
		{
			int blockLength = (((length[0]<<0) &0xFF) + ((length[1]<<8) & 0xFF00) + ((length[2]<<16) & 0xFF0000) + ((length[3]<<32) & 0xFF000000));
			return blockLength;
		}
	}
	
	/**
	 * Parse data block of all type of section and return current index to be read next 
	 * 
	 * @param data
	 * @return
	 */
	private int parseDataBlock(BlockTypes type,byte[] data,int initIndex)
	{
		try
		{
			int blockLength = parseBlockLength(Arrays.copyOfRange(data, initIndex+4, initIndex+8), isBigEndian);

			// substract 4 for header and 4 for size (x2 at the end)
			byte[] dataBlock = Arrays.copyOfRange(data, initIndex+8, initIndex+ (blockLength-4));
			
			byte[] dataTemp = dataBlock;
			
			if (type==BlockTypes.SECTION_HEADER_BLOCK)
			{
				dataTemp=Arrays.copyOfRange(dataBlock, 4, dataBlock.length);	
			}
			PcapNgStructureParser structure = new PcapNgStructureParser(type, dataTemp,isBigEndian);
			structure.decode();
			pcapSectionList.add(structure.getPcapStruct());
			
			initIndex+=(blockLength-1)+1;
			return initIndex;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return DecoderStatus.FAILED_STATUS;
		}
	}
	
	/**
	 * Decode a specific section type from HeaderBLocks class
	 * 
	 * @param sectionType
	 */
	public int processSectionType(BlockTypes type,int initIndex) throws DecodeException
	{
		if (UtilFunctions.compare32Bytes(HeaderBlocks.SECTION_TYPE_LIST.get(type.toString()), Arrays.copyOfRange(data, initIndex, initIndex+4),isBigEndian))
		{
			if (type==BlockTypes.SECTION_HEADER_BLOCK)
			{
				byte endianess = detectEndianness(Arrays.copyOfRange(Arrays.copyOfRange(data, initIndex+8, initIndex+12), 0, 4));
				
				if (endianess==Endianess.BIG_ENDIAN)
				{
					isBigEndian=true;
				}
				else if (endianess==Endianess.LITTLE_ENDIAN)
				{
					isBigEndian=false;
				}
			}
			
			initIndex=parseDataBlock(type,data, initIndex);

			if (initIndex==-1)
			{
				throw new DecodeException();
			}
			return initIndex;
		}
		return initIndex;
	}
	
	/**
	 * Decode 
	 * @param data
	 */
	public byte decode()
	{
		if (data==null || data.length<4)
		{
			System.err.println("Error input data format error");
			return DecoderStatus.FAILED_STATUS;
		}
		
		int initIndex = 0;
		
		try
		{
			int formerIndex = 0;
			
			while (initIndex!=data.length)
			{
				initIndex = processSectionType(BlockTypes.SECTION_HEADER_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.INTERFACE_DESCRIPTION_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.ENHANCES_PACKET_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.SIMPLE_PACKET_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.NAME_RESOLUTION_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.INTERFACE_STATISTICS_BLOCK, initIndex);
				initIndex = processSectionType(BlockTypes.PACKET_BLOCK, initIndex);
				
				if (formerIndex==initIndex && formerIndex!=0)
				{
					throw new DecodeException("File parsing error | format not recognized");
				}
				formerIndex=initIndex;
			}
 		}
		catch (DecodeException e)
		{
			e.printStackTrace();
			return DecoderStatus.FAILED_STATUS;
		}
		return DecoderStatus.SUCCESS_STATUS;
	}
	
	public ArrayList<IPcapngType> getSectionList()
	{
		return pcapSectionList;
	}
}
