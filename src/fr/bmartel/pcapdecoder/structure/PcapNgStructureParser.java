package fr.bmartel.pcapdecoder.structure;

import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.impl.EnhancedPacketHeader;
import fr.bmartel.pcapdecoder.structure.types.impl.InterfaceDescriptionHeader;
import fr.bmartel.pcapdecoder.structure.types.impl.InterfaceStatisticsHeader;
import fr.bmartel.pcapdecoder.structure.types.impl.NameResolutionHeader;
import fr.bmartel.pcapdecoder.structure.types.impl.SectionHeader;


/**
 * 
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                          Block Type                           |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                      Block Total Length                       |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
/                          Block Body                           /
/           variable length, aligned to 32 bits                 /
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                      Block Total Length                       |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *
 * @author Bertrand Martel
 *
 */
public class PcapNgStructureParser {

	/**
	 * unique value that identifies the block. Values whose Most Significant Bit (MSB) is equal to 1 are reserved for local use. They allow to save private data to the file and to extend the file format
	 */
	private BlockTypes blockType = BlockTypes.UNKNOWN;
	
	private IPcapngType pcapStruct = null;
	
	/**
	 * total size of this block
	 */
	private byte[] blockTotalLength = new byte[32];
	
	/**
	 * content of the block
	 */
	private byte[] blockData = new byte[]{};
	
	private boolean isBigEndian =true;
	
	/**
	 * Build Pcap Ng structure 
	 * 
	 * @param type
	 * 		block type
	 * @param data
	 * 		block data
	 */
	public PcapNgStructureParser(BlockTypes type,byte[] data,boolean isBigEndian)
	{
		this.blockType=type;
		this.blockData=data;
		this.isBigEndian=isBigEndian;
	}

	public void decode() {
		if (blockType==BlockTypes.SECTION_HEADER_BLOCK)
		{
			pcapStruct = new SectionHeader(blockData,isBigEndian,blockType);
		}
		else if (blockType==BlockTypes.INTERFACE_DESCRIPTION_BLOCK)
		{
			pcapStruct = new InterfaceDescriptionHeader(blockData,isBigEndian,blockType);
		}
		else if (blockType==BlockTypes.ENHANCES_PACKET_BLOCK)
		{
			pcapStruct = new EnhancedPacketHeader(blockData,isBigEndian,blockType);
		}
		else if (blockType==BlockTypes.SIMPLE_PACKET_BLOCK)
		{
			
		}
		else if (blockType==BlockTypes.NAME_RESOLUTION_BLOCK)
		{
			pcapStruct=new NameResolutionHeader(blockData,isBigEndian,blockType);
		}
		else if (blockType==BlockTypes.INTERFACE_STATISTICS_BLOCK)
		{
			pcapStruct = new InterfaceStatisticsHeader(blockData,isBigEndian,blockType);
		}
		else if (blockType==BlockTypes.PACKET_BLOCK)
		{
			
		}
	}

	public BlockTypes getBlockType() {
		return blockType;
	}
	public IPcapngType getPcapStruct() {
		return pcapStruct;
	}

	public byte[] getBlockTotalLength() {
		return blockTotalLength;
	}

	public byte[] getBlockData() {
		return blockData;
	}

	public boolean isBigEndian() {
		return isBigEndian;
	}
}
