package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.IStatisticsBlock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Implementation for INTERFACE STATISTICS SECTION
 * 
 *     0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +---------------------------------------------------------------+
 0 |                   Block Type = 0x00000005                     |
   +---------------------------------------------------------------+
 4 |                      Block Total Length                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 8 |                         Interface ID                          |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
12 |                        Timestamp (High)                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
16 |                        Timestamp (Low)                        |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
20 /                                                               /
   /                      Options (variable)                       /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Block Total Length                       |
   +---------------------------------------------------------------+
 * 
 * 
 * @author Bertrand Martel
 *
 */
public class InterfaceStatisticsHeader implements IStatisticsBlock,IPcapngType{
	
	private int interfaceId = -1;
	
	private Long timestamp = -1l;
	
	private IOptionsStatisticsHeader options  = null;
	
	public InterfaceStatisticsHeader(byte[] data,boolean isBigEndian,BlockTypes type) {
		
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
		}
		
		if (data.length>12)
		{
			OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 12,data.length), isBigEndian,type,false);
			optionParser.decode();
			this.options=(IOptionsStatisticsHeader) optionParser.getOption();
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
	public IOptionsStatisticsHeader getOptions() {
		return options;
	}

	
}
