package fr.bmartel.pcapdecoder.constant;

import java.util.HashMap;

import fr.bmartel.pcapdecoder.structure.BlockTypes;

/**
 * Block header for Pcap Ng files
 * 
 * @author Bertrand Martel
 *
 */
public class HeaderBlocks {

	public final static HashMap<String, byte[]> SECTION_TYPE_LIST = new HashMap<String, byte[]>();

	public final static byte[] SECTION_HEADER_BLOCK = new byte[] { 0x0a, 0x0d,
			0x0d, 0x0a };

	public final static byte[] INTERFACE_DESCRIPTION_BLOCK = new byte[] { 0x00,
			0x00, 0x00, 0x01 };

	public final static byte[] ENHANCED_PACKET_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x06 };

	public final static byte[] SIMPLE_PACKET_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x03 };

	public final static byte[] PACKET_BLOCK = new byte[] { 0x00, 0x00, 0x00,
			0x02 };

	public final static byte[] NAME_RESOLUTION_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x04 };

	public final static byte[] INTERFACE_STATISTICS_BLOCK = new byte[] { 0x00,
			0x00, 0x00, 0x05 };

	static {
		SECTION_TYPE_LIST.put(BlockTypes.SECTION_HEADER_BLOCK.toString(),
				SECTION_HEADER_BLOCK);
		SECTION_TYPE_LIST.put(
				BlockTypes.INTERFACE_DESCRIPTION_BLOCK.toString(),
				INTERFACE_DESCRIPTION_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.ENHANCES_PACKET_BLOCK.toString(),
				ENHANCED_PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.SIMPLE_PACKET_BLOCK.toString(),
				SIMPLE_PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.PACKET_BLOCK.toString(), PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.NAME_RESOLUTION_BLOCK.toString(),
				NAME_RESOLUTION_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.INTERFACE_STATISTICS_BLOCK.toString(),
				INTERFACE_STATISTICS_BLOCK);
	}

}
