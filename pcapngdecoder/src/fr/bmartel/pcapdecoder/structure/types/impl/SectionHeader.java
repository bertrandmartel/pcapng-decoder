package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.ISectionHeaderBlock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * 
 * Implementation for Section Header Block parsing in Pcap ng decoder
 * 
 *  * SECTION HEADER BLOCK
 * 
 *    0                   1                   2                   3
   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +---------------------------------------------------------------+
 0 |                   Block Type = 0x0A0D0D0A                     |
   +---------------------------------------------------------------+
 4 |                      Block Total Length                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 8 |                      Byte-Order Magic                         |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
12 |          Major Version        |         Minor Version         |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
16 |                                                               |
   |                          Section Length                       |
   |                                                               |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
24 /                                                               /
   /                      Options (variable)                       /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Block Total Length                       |
   +---------------------------------------------------------------+
 * @author Bertrand Martel
 *
 */
public class SectionHeader implements ISectionHeaderBlock,IPcapngType{

	private int majorVersion = -1;
	
	private int minorVersion = -1;
	
	private int sectionLength = -1;
	
	private IOptionSectionHeader options = null;
	
	public SectionHeader(byte[] data,boolean isBigEndian,BlockTypes type) {
		
		if (isBigEndian)
		{
			minorVersion= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 0,2));
			majorVersion= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 2,4));
			sectionLength = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 4,12));
		}
		else
		{
			minorVersion= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0,2)));
			majorVersion= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 2,4)));
			sectionLength = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4,12)));
		}
		
		if (data.length>11)
		{
			//parse set of options
			OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 12,data.length), isBigEndian,type,false);
			optionParser.decode();
			this.options=(IOptionSectionHeader) optionParser.getOption();
		}
	}
	
	@Override
	public int getMajorVersion() {
		return majorVersion;
	}

	@Override
	public int getMinorVersion() {
		return minorVersion;
	}

	@Override
	public int getSectionLength() {
		return sectionLength;
	}

	@Override
	public  IOptionSectionHeader getOptions() {
		return options;
	}

}
