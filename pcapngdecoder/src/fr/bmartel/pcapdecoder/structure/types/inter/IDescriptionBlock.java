package fr.bmartel.pcapdecoder.structure.types.inter;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;

/**
 * 
 * INTERFACE DESCRIPTION BLOCK
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
public interface IDescriptionBlock {

	/**
	 * Link layer string name (from fr.bmartel.pcapdecoder.constant.LinkLayerConstants)
	 * 
	 * @return
	 */
	public String getLinkType();
	
	/**
	 * maximum number of bytes dumped from each packet in this pcap ng file
	 * 
	 * @return
	 */
	public int getSnapLen();
	
	public  IOptionsDescriptionHeader getOptions();
}
