package fr.bmartel.pcapdecoder.main;

import java.util.Date;

import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.constant.PacketBoundState;
import fr.bmartel.pcapdecoder.constant.PacketHashType;
import fr.bmartel.pcapdecoder.constant.PacketReceptionType;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;
import fr.bmartel.pcapdecoder.structure.types.inter.IDescriptionBlock;
import fr.bmartel.pcapdecoder.structure.types.inter.IEnhancedPacketBLock;
import fr.bmartel.pcapdecoder.structure.types.inter.ISectionHeaderBlock;
import fr.bmartel.pcapdecoder.structure.types.inter.IStatisticsBlock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

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
public class DisplayAllPacket {

	/**
	 * Display all information on packets (highly consuming when used on big file)
	 * 
	 * @param decoder
	 */
	public static void displayResult(PcapDecoder decoder)
	{
		System.out.println("##########################################################");
		
		int timestampResolution = 3;
		
		for (int i = 0; i < decoder.getSectionList().size();i++)
		{
			if (decoder.getSectionList().get(i) instanceof ISectionHeaderBlock)
			{
				ISectionHeaderBlock temp = (ISectionHeaderBlock) decoder.getSectionList().get(i);
				
				System.out.println("SECTION HEADER BLOCK");
				if (temp.getMajorVersion()!=-1)
					System.out.println("Major version      : " + temp.getMajorVersion());
				
				if (temp.getMinorVersion()!=-1)
					System.out.println("Minor version      : " + temp.getMinorVersion());
				
				IOptionSectionHeader optionsList = temp.getOptions();

				if (optionsList!=null)
				{
					if (!optionsList.getHardware().equals(""))
						System.out.println("hardware interface : " +  optionsList.getHardware());
					if (!optionsList.getOS().equals(""))
						System.out.println("OS                 : " +  optionsList.getOS());
					if (!optionsList.getUserAppl().equals(""))
						System.out.println("user application   : " +  optionsList.getUserAppl());
					if (!optionsList.getComment().equals(""))
						System.out.println("comment            : " +  optionsList.getComment());
				}
				System.out.println("##########################################################");
			}
			else if (decoder.getSectionList().get(i) instanceof IDescriptionBlock)
			{
				IDescriptionBlock temp = (IDescriptionBlock) decoder.getSectionList().get(i);
				
				System.out.println("SECTION INTERFACE DESCRIPTION BLOCK");
				
				if (!temp.getLinkType().equals(""))
					System.out.println("Link type             : " + temp.getLinkType());
				if (temp.getSnapLen()!=-1)
					System.out.println("Snap len              : " + temp.getSnapLen());
				
				IOptionsDescriptionHeader optionsList = temp.getOptions();
				
				if (optionsList!=null)
				{
					if (!optionsList.getInterfaceName().equals(""))
						System.out.println("interface name        : " +  optionsList.getInterfaceName());
					if (!optionsList.getInterfaceDescription().equals(""))
						System.out.println("interface description : " +  optionsList.getInterfaceDescription());
					if (!optionsList.getInterfaceIpv4NetworkAddr().equals(""))
						System.out.println("interface ipv4 addr   : " +  optionsList.getInterfaceIpv4NetworkAddr());
					if (!optionsList.getInterfaceNetmask().equals(""))
						System.out.println("interface netmask     : " +  optionsList.getInterfaceNetmask());
					if (!optionsList.getIpv6NetworkAddr().equals(""))
						System.out.println("interface ipv6 addr   : " +  optionsList.getIpv6NetworkAddr());
					if (!optionsList.getInterfaceMacAddr().equals(""))
						System.out.println("interface mac addr    : " +  optionsList.getInterfaceMacAddr());
					if (!optionsList.getInterfaceEuiAddr().equals(""))
						System.out.println("interface EUI addr    : " +  optionsList.getInterfaceEuiAddr());
					if (optionsList.getInterfaceSpeed()!=-1)
						System.out.println("interface speed       : " +  optionsList.getInterfaceSpeed() + "bps");
					if (optionsList.getTimeStampResolution()!=-1)
					{
						System.out.println("timestamp resolution  : " +  optionsList.getTimeStampResolution());
						timestampResolution=optionsList.getTimeStampResolution();
					}
					if (optionsList.getTimeBias()!=-1)
						System.out.println("time offset from UTC  : " +  optionsList.getTimeBias());
					if (!optionsList.getInterfaceFilter().equals(""))
						System.out.println("interface filter      : " +  optionsList.getInterfaceFilter());
					if (!optionsList.getInterfaceOperatingSystem().equals(""))
						System.out.println("interface OS name     : " +  optionsList.getInterfaceOperatingSystem());
					if (optionsList.getInterfaceFrameCheckSequenceLength()!=-1)
						System.out.println("interface FCS length  : " +  optionsList.getInterfaceFrameCheckSequenceLength());
					if (optionsList.getTimeStampOffset()!=-1)
						System.out.println("timestamp offset      : " +  optionsList.getTimeStampOffset());
					if (!optionsList.getComment().equals(""))
						System.out.println("comment               : " +  optionsList.getComment());
				}
				System.out.println("##########################################################");
			}
			else if (decoder.getSectionList().get(i) instanceof IEnhancedPacketBLock)
			{
				IEnhancedPacketBLock temp = (IEnhancedPacketBLock) decoder.getSectionList().get(i);
				
				System.out.println("SECTION ENHANCED PACKET BLOCK");
				if (temp.getInterfaceId()!=-1)
					System.out.println("interface id             : " + temp.getInterfaceId());
				
				if (temp.getTimeStamp()!=-1)
				{
					if (timestampResolution==3)
					{
						System.out.println("timestamp in millis      : " + new Date(temp.getTimeStamp()));
					}
					else if (timestampResolution==6)
					{
						System.out.println("timestamp in millis      : " + new Date(temp.getTimeStamp()/1000));
					}
				}
				if (temp.getCapturedLength()!=-1)
					System.out.println("captured length          : " + temp.getCapturedLength());
				if (temp.getPacketLength()!=-1)
					System.out.println("packet length            : " + temp.getPacketLength());
				if (temp.getPacketData()!=null)
					System.out.println(UtilFunctions.byteArrayToStringMessage("packet data             ", temp.getPacketData(), '|'));
				
				IOptionsEnhancedPacketHeader optionsList = temp.getOptions();
				
				if (optionsList!=null)
				{
					if (optionsList.getPacketBound()!=PacketBoundState.UNKNOWN)
						System.out.println("packet bound state     : " + optionsList.getPacketBound());
					if (optionsList.getPacketReceptionType()!=PacketReceptionType.UNKNOWN)
						System.out.println("packet reception type  : " + optionsList.getPacketReceptionType());
					if (optionsList.getFrameCheckSumLength()!=-1)
						System.out.println("packet FCS length      : " + optionsList.getFrameCheckSumLength());
					if (optionsList.getDropPacketCount()!=-1)
						System.out.println("packet drop count      : " + optionsList.getDropPacketCount());
					if (optionsList.getPacketHashType()!=PacketHashType.UNKNOWN)
						System.out.println("packet hash type       : " + optionsList.getPacketHashType());
					if (optionsList.getPacketHashBigEndian()!=null)
						System.out.println(UtilFunctions.byteArrayToStringMessage("packet hash type", optionsList.getPacketHashBigEndian(), '|'));
					
					for (int j = 0; j  < optionsList.getLinkLayerErrorList().size();j++)
					{
						System.out.println(optionsList.getLinkLayerErrorList().get(i) + " detected");
					}
				}
				System.out.println("##########################################################");
			}
			else if (decoder.getSectionList().get(i) instanceof IStatisticsBlock)
			{
				IStatisticsBlock temp = (IStatisticsBlock) decoder.getSectionList().get(i);
				
				System.out.println("SECTION INTERFACE STATISTICS BLOCK");
				
				if (temp.getInterfaceId()!=-1)
					System.out.println("interface id             : " + temp.getInterfaceId());
				
				if (temp.getTimeStamp()!=-1)
				{
					if (timestampResolution==3)
					{
						System.out.println("timestamp in millis      : " + new Date(temp.getTimeStamp()));
					}
					else if (timestampResolution==6)
					{
						System.out.println("timestamp in millis      : " + new Date(temp.getTimeStamp()/1000));
					}
				}
				
				IOptionsStatisticsHeader optionsList = temp.getOptions();
				
				if (optionsList!=null)
				{
					if (optionsList.getCaptureStartTime()!=-1)
					{
						if (timestampResolution==3)
						{
							System.out.println("capture start time       : " + new Date(optionsList.getCaptureStartTime()));
						}
						else if (timestampResolution==6)
						{
							System.out.println("capture start time       : " + new Date(optionsList.getCaptureStartTime()/1000));
						}
					}
					if (optionsList.getCaptureEndTime()!=-1)
					{
						if (timestampResolution==3)
						{
							System.out.println("capture end time         : " + new Date(optionsList.getCaptureEndTime()));
						}
						else if (timestampResolution==6)
						{
							System.out.println("capture end time         : " + new Date(optionsList.getCaptureEndTime()/1000));
						}
					}
					if (optionsList.getPacketReceivedCount()!=-1)
						System.out.println("packet received count    : " + optionsList.getPacketReceivedCount());
					if (optionsList.getPacketDropCount()!=-1)
						System.out.println("packet drop count        : " + optionsList.getPacketDropCount());
					if (optionsList.getPacketAcceptedByFilterCount()!=-1)
						System.out.println("packet accepted by filter   : " + optionsList.getPacketAcceptedByFilterCount());
					if (optionsList.getPacketDroppedByOS()!=-1)
						System.out.println("packet dropped by OS        : " + optionsList.getPacketDroppedByOS());
					if (optionsList.getPacketDeliveredToUser()!=-1)
						System.out.println("packet delivered to user    : " + optionsList.getPacketDeliveredToUser());
				}
				
				System.out.println("##########################################################");
			}
		}
	}
}
