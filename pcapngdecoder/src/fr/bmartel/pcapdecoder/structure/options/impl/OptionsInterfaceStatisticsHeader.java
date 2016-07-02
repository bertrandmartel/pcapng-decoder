package fr.bmartel.pcapdecoder.structure.options.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.options.abstr.OptionsAbstr;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceStatisticsObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * 
 *  Implementation for Options in Interface Statistics Header
 *  
 * @author Bertrand Martel
 *
 */
public class OptionsInterfaceStatisticsHeader extends OptionsAbstr{

	private OptionInterfaceStatisticsObject commonObject = null;
	
	public OptionsInterfaceStatisticsHeader(int optionCode, byte[] data,boolean isBigEndian, IOptions currentOption) {
		super(optionCode, data, isBigEndian, currentOption);
		this.commonObject=(OptionInterfaceStatisticsObject) currentOption;
		
		decode();
	}

	public void decode() {
		try
		{
			switch(optionCode)
			{
				case 1:
					if (!isBigEndian)
						commonObject.setComment(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setComment(new String(data,"UTF-8"));
					break;
				case 2:

					if (!isBigEndian)
					{
						commonObject.setCaptureStartTime(parseTimeStamp(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					}
					else
					{
						commonObject.setCaptureStartTime(parseTimeStamp(Arrays.copyOfRange(data, 0, 8)));
					}
					break;
				case 3:
					if (!isBigEndian)
					{
						commonObject.setCaptureEndTime(parseTimeStamp(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					}
					else
					{
						commonObject.setCaptureEndTime(parseTimeStamp(Arrays.copyOfRange(data, 0, 8)));
					}
					break;
				case 4:
					// this appear to be in Big endian even in Little Endian ! or something is missing in doc
					/*
					if (!isBigEndian)
						this.commonObject.setPacketReceivedCount(ConvertFunctions.convertByteArrayToLong(ConvertFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					else*/
						this.commonObject.setPacketReceivedCount(UtilFunctions.convertByteArrayToLong(Arrays.copyOfRange(data, 0, 8)));
					break;
				case 5:
					if (!isBigEndian)
						this.commonObject.setPacketDropCount(UtilFunctions.convertByteArrayToLong(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					else
						this.commonObject.setPacketDropCount(UtilFunctions.convertByteArrayToLong(Arrays.copyOfRange(data, 0, 8)));
					break;
				case 6:
					if (!isBigEndian)
						this.commonObject.setPacketAcceptedByFilterCount(UtilFunctions.convertByteArrayToLong(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					else
						this.commonObject.setPacketAcceptedByFilterCount(UtilFunctions.convertByteArrayToLong(Arrays.copyOfRange(data, 0, 8)));
					break;
				case 7:
					if (!isBigEndian)
						this.commonObject.setPacketDroppedByOS(UtilFunctions.convertByteArrayToLong(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					else
						this.commonObject.setPacketDroppedByOS(UtilFunctions.convertByteArrayToLong(Arrays.copyOfRange(data, 0, 8)));
					break;
				case 8:
					if (!isBigEndian)
						this.commonObject.setPacketDeliveredToUser(UtilFunctions.convertByteArrayToLong(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8))));
					else
						this.commonObject.setPacketDeliveredToUser(UtilFunctions.convertByteArrayToLong(Arrays.copyOfRange(data, 0, 8)));
					break;
			}
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	private Long parseTimeStamp(byte[] data)
	{
		byte[] high_timestamp= UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0,4));
		byte[] low_timestamp = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4,8));
		
		byte[] finalTimestamp = new byte[8];
		for (int i = 0; i  <4;i++)
		{
			finalTimestamp[i]=high_timestamp[i];
		}
		for (int i = 0;i<4;i++)
		{
			finalTimestamp[i+4]=low_timestamp[i];
		}

		Long timestamp=(long) UtilFunctions.convertByteArrayToLong(finalTimestamp);
		return timestamp;
	}
}
