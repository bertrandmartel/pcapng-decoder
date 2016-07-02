package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.constant.LinkLayerConstants;
import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.IDescriptionBlock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;


/**
 * Implementation for INTERFACE DESCRIPTION HEADER
 * 
 *     0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +---------------------------------------------------------------+
 0 |                    Block Type = 0x00000001                    |
   +---------------------------------------------------------------+
 4 |                      Block Total Length                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 8 |           LinkType            |           Reserved            |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
12 |                            SnapLen                            |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
16 /                                                               /
   /                      Options (variable)                       /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Block Total Length                       |
   +---------------------------------------------------------------+
   
 * @author Bertrand Martel
 *
 */
public class InterfaceDescriptionHeader implements IDescriptionBlock,IPcapngType{

	private int snapLen=-1;
	
	private String linkTypeStr = "";
	
	private int linkType = -1;
	
	private IOptionsDescriptionHeader options = null;
	
	public InterfaceDescriptionHeader(byte[] data,boolean isBigEndian,BlockTypes type) {
		
		//may be used later for further specifications
		int reserved = -1;
		
		if (isBigEndian)
		{
			linkType= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 0,2));
			linkTypeStr=LinkLayerConstants.LINK_LAYER_LIST.get(linkType);
			reserved = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 2,4));
			snapLen = UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, 4,8));
		}
		else
		{
			linkType= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0,2)));
			linkTypeStr=LinkLayerConstants.LINK_LAYER_LIST.get(linkType);
			reserved= UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 2,4)));
			snapLen = UtilFunctions.convertByteArrayToInt(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4,8)));
		}
		
		if (data.length>8)
		{
			//parse set of options
			OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 8,data.length), isBigEndian,type,false);
			optionParser.decode();
			this.options=(IOptionsDescriptionHeader) optionParser.getOption();
		}
	}
	
	@Override
	public int getSnapLen() {
		return snapLen;
	}

	@Override
	public String getLinkType() {
		return linkTypeStr;
	}

	@Override
	public IOptionsDescriptionHeader getOptions() {
		return options;
	}

}
