package fr.bmartel.pcapdecoder.structure.options;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionEnhancedPacketHeaderObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceDescriptionObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceStatisticsObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionSectionHeaderObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionsNameResolutionObject;
import fr.bmartel.pcapdecoder.structure.options.object.OptionsRecordNameResolutionObject;
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
	
	private boolean isRecord = false;
	
	/**
	 * Build parser with data
	 * 
	 * @param data
	 * @param isBigEndian
	 * @BlockTypes type
	 */
	public OptionParser(byte[] data,boolean isBigEndian,BlockTypes type,boolean isRecord)
	{
		this.data=data;
		this.isBigEndian=isBigEndian;
		this.type=type;
		this.isRecord=isRecord;
	}
	
	/**
	 * decode data option
	 */
	public int decode()
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
		else if (type==BlockTypes.NAME_RESOLUTION_BLOCK)
		{
			if (isRecord)
				option=new OptionsRecordNameResolutionObject();
			else 
			{
				option=new OptionsNameResolutionObject();
			}
		}
		
		int optionLength = -1;
		
		while (optionLength!=0)
		{
			int optionCode=0;
			optionLength=0;
			byte[] optionValue=null;
			
			if (isBigEndian)
			{
				optionCode= UtilFunctions.convertByteArrayToInt(Arrays.copyOfRange(data, initIndex+0,initIndex+2));
				if (optionCode==0)
				{
					initIndex+=2;
					return initIndex;
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
					initIndex+=2;
					return initIndex;
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
		return initIndex;
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
