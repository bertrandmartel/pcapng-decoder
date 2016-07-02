/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015-2016 Bertrand Martel
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.bmartel.pcapngdecoder.main;

import java.util.Date;

import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.constant.PacketBoundState;
import fr.bmartel.pcapdecoder.constant.PacketHashType;
import fr.bmartel.pcapdecoder.constant.PacketReceptionType;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsNameResolutionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;
import fr.bmartel.pcapdecoder.structure.types.inter.IDescriptionBlock;
import fr.bmartel.pcapdecoder.structure.types.inter.IEnhancedPacketBLock;
import fr.bmartel.pcapdecoder.structure.types.inter.INameResolutionBlock;
import fr.bmartel.pcapdecoder.structure.types.inter.ISectionHeaderBlock;
import fr.bmartel.pcapdecoder.structure.types.inter.IStatisticsBlock;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;
import org.apache.logging.log4j.Logger;

public class DisplayAllPacket {

    /**
     * Display all information on packets (highly consuming when used on big file)
     *
     * @param decoder
     */
    public static void displayResult(PcapDecoder decoder, Logger logger) {

        logger.debug("##########################################################");

        int timestampResolution = 3;

        for (int i = 0; i < decoder.getSectionList().size(); i++) {
            if (decoder.getSectionList().get(i) instanceof ISectionHeaderBlock) {
                ISectionHeaderBlock temp = (ISectionHeaderBlock) decoder.getSectionList().get(i);

                logger.debug("SECTION HEADER BLOCK");
                if (temp.getMajorVersion() != -1)
                    logger.debug("Major version      : " + temp.getMajorVersion());

                if (temp.getMinorVersion() != -1)
                    logger.debug("Minor version      : " + temp.getMinorVersion());

                IOptionSectionHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (!optionsList.getHardware().equals(""))
                        logger.debug("hardware interface : " + optionsList.getHardware());
                    if (!optionsList.getOS().equals(""))
                        logger.debug("OS                 : " + optionsList.getOS());
                    if (!optionsList.getUserAppl().equals(""))
                        logger.debug("user application   : " + optionsList.getUserAppl());
                    if (!optionsList.getComment().equals(""))
                        logger.debug("comment            : " + optionsList.getComment());
                }
                logger.debug("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IDescriptionBlock) {
                IDescriptionBlock temp = (IDescriptionBlock) decoder.getSectionList().get(i);

                logger.debug("SECTION INTERFACE DESCRIPTION BLOCK");

                if (!temp.getLinkType().equals(""))
                    logger.debug("Link type             : " + temp.getLinkType());
                if (temp.getSnapLen() != -1)
                    logger.debug("Snap len              : " + temp.getSnapLen());

                IOptionsDescriptionHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (!optionsList.getInterfaceName().equals(""))
                        logger.debug("interface name        : " + optionsList.getInterfaceName());
                    if (!optionsList.getInterfaceDescription().equals(""))
                        logger.debug("interface description : " + optionsList.getInterfaceDescription());
                    if (!optionsList.getInterfaceIpv4NetworkAddr().equals(""))
                        logger.debug("interface ipv4 addr   : " + optionsList.getInterfaceIpv4NetworkAddr());
                    if (!optionsList.getInterfaceNetmask().equals(""))
                        logger.debug("interface netmask     : " + optionsList.getInterfaceNetmask());
                    if (!optionsList.getIpv6NetworkAddr().equals(""))
                        logger.debug("interface ipv6 addr   : " + optionsList.getIpv6NetworkAddr());
                    if (!optionsList.getInterfaceMacAddr().equals(""))
                        logger.debug("interface mac addr    : " + optionsList.getInterfaceMacAddr());
                    if (!optionsList.getInterfaceEuiAddr().equals(""))
                        logger.debug("interface EUI addr    : " + optionsList.getInterfaceEuiAddr());
                    if (optionsList.getInterfaceSpeed() != -1)
                        logger.debug("interface speed       : " + optionsList.getInterfaceSpeed() + "bps");
                    if (optionsList.getTimeStampResolution() != -1) {
                        logger.debug("timestamp resolution  : " + optionsList.getTimeStampResolution());
                        timestampResolution = optionsList.getTimeStampResolution();
                    }
                    if (optionsList.getTimeBias() != -1)
                        logger.debug("time offset from UTC  : " + optionsList.getTimeBias());
                    if (!optionsList.getInterfaceFilter().equals(""))
                        logger.debug("interface filter      : " + optionsList.getInterfaceFilter());
                    if (!optionsList.getInterfaceOperatingSystem().equals(""))
                        logger.debug("interface OS name     : " + optionsList.getInterfaceOperatingSystem());
                    if (optionsList.getInterfaceFrameCheckSequenceLength() != -1)
                        logger.debug("interface FCS length  : " + optionsList
                                .getInterfaceFrameCheckSequenceLength());
                    if (optionsList.getTimeStampOffset() != -1)
                        logger.debug("timestamp offset      : " + optionsList.getTimeStampOffset());
                    if (!optionsList.getComment().equals(""))
                        logger.debug("comment               : " + optionsList.getComment());
                }
                logger.debug("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IEnhancedPacketBLock) {
                IEnhancedPacketBLock temp = (IEnhancedPacketBLock) decoder.getSectionList().get(i);

                logger.debug("SECTION ENHANCED PACKET BLOCK");
                if (temp.getInterfaceId() != -1)
                    logger.debug("interface id             : " + temp.getInterfaceId());

                if (temp.getTimeStamp() != -1) {
                    if (timestampResolution == 3) {
                        logger.debug("timestamp in millis      : " + new Date(temp.getTimeStamp()));
                    } else if (timestampResolution == 6) {
                        logger.debug("timestamp in millis      : " + new Date(temp.getTimeStamp() / 1000));
                    }
                }
                if (temp.getCapturedLength() != -1)
                    logger.debug("captured length          : " + temp.getCapturedLength());
                if (temp.getPacketLength() != -1)
                    logger.debug("packet length            : " + temp.getPacketLength());
                if (temp.getPacketData() != null)
                    logger.debug(UtilFunctions.byteArrayToStringMessage("packet data             ", temp
                            .getPacketData(), '|'));

                IOptionsEnhancedPacketHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (optionsList.getPacketBound() != PacketBoundState.UNKNOWN)
                        logger.debug("packet bound state     : " + optionsList.getPacketBound());
                    if (optionsList.getPacketReceptionType() != PacketReceptionType.UNKNOWN)
                        logger.debug("packet reception type  : " + optionsList.getPacketReceptionType());
                    if (optionsList.getFrameCheckSumLength() != -1)
                        logger.debug("packet FCS length      : " + optionsList.getFrameCheckSumLength());
                    if (optionsList.getDropPacketCount() != -1)
                        logger.debug("packet drop count      : " + optionsList.getDropPacketCount());
                    if (optionsList.getPacketHashType() != PacketHashType.UNKNOWN)
                        logger.debug("packet hash type       : " + optionsList.getPacketHashType());
                    if (optionsList.getPacketHashBigEndian() != null)
                        logger.debug(UtilFunctions.byteArrayToStringMessage("packet hash type", optionsList
                                .getPacketHashBigEndian(), '|'));

                    for (int j = 0; j < optionsList.getLinkLayerErrorList().size(); j++) {
                        logger.debug(optionsList.getLinkLayerErrorList().get(i) + " detected");
                    }
                }
                logger.debug("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof IStatisticsBlock) {
                IStatisticsBlock temp = (IStatisticsBlock) decoder.getSectionList().get(i);

                logger.debug("SECTION INTERFACE STATISTICS BLOCK");

                if (temp.getInterfaceId() != -1)
                    logger.debug("interface id             : " + temp.getInterfaceId());

                if (temp.getTimeStamp() != -1) {
                    if (timestampResolution == 3) {
                        logger.debug("timestamp in millis      : " + new Date(temp.getTimeStamp()));
                    } else if (timestampResolution == 6) {
                        logger.debug("timestamp in millis      : " + new Date(temp.getTimeStamp() / 1000));
                    }
                }

                IOptionsStatisticsHeader optionsList = temp.getOptions();

                if (optionsList != null) {
                    if (optionsList.getCaptureStartTime() != -1) {
                        if (timestampResolution == 3) {
                            logger.debug("capture start time       : " + new Date(optionsList
                                    .getCaptureStartTime()));
                        } else if (timestampResolution == 6) {
                            logger.debug("capture start time       : " + new Date(optionsList
                                    .getCaptureStartTime() / 1000));
                        }
                    }
                    if (optionsList.getCaptureEndTime() != -1) {
                        if (timestampResolution == 3) {
                            logger.debug("capture end time         : " + new Date(optionsList.getCaptureEndTime
                                    ()));
                        } else if (timestampResolution == 6) {
                            logger.debug("capture end time         : " + new Date(optionsList.getCaptureEndTime
                                    () / 1000));
                        }
                    }
                    if (optionsList.getPacketReceivedCount() != -1)
                        logger.debug("packet received count    : " + optionsList.getPacketReceivedCount());
                    if (optionsList.getPacketDropCount() != -1)
                        logger.debug("packet drop count        : " + optionsList.getPacketDropCount());
                    if (optionsList.getPacketAcceptedByFilterCount() != -1)
                        logger.debug("packet accepted by filter   : " + optionsList
                                .getPacketAcceptedByFilterCount());
                    if (optionsList.getPacketDroppedByOS() != -1)
                        logger.debug("packet dropped by OS        : " + optionsList.getPacketDroppedByOS());
                    if (optionsList.getPacketDeliveredToUser() != -1)
                        logger.debug("packet delivered to user    : " + optionsList.getPacketDeliveredToUser());
                }

                logger.debug("##########################################################");
            } else if (decoder.getSectionList().get(i) instanceof INameResolutionBlock) {
                INameResolutionBlock temp = (INameResolutionBlock) decoder.getSectionList().get(i);

                logger.debug("SECTION NAME RESOLUTION BLOCK");

                if (temp.getRecords() != null) {
                    logger.debug("IPV4 ENTRIES -------------------");
                    for (int j = 0; j < temp.getRecords().getIpv4DnsEntries().size(); j++) {
                        logger.debug(temp.getRecords().getIpv4DnsEntries().get(j).getIpAddr());

                        for (int k = 0; k < temp.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().size(); k++) {
                            System.out.print("\t" + temp.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().get
                                    (k) + ";");
                        }
                        logger.debug("");
                    }

                    logger.debug("IPV6 ENTRIES -------------------");
                    for (int j = 0; j < temp.getRecords().getIpv6DnsEntries().size(); j++) {
                        logger.debug(temp.getRecords().getIpv6DnsEntries().get(j).getIpAddr());

                        for (int k = 0; k < temp.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().size(); k++) {
                            System.out.print("\t" + temp.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().get
                                    (k) + ";");
                        }
                        logger.debug("");
                    }
                }

                if (temp.getOptions() != null) {
                    if (!temp.getOptions().getDnsIpv4Addr().equals(""))
                        logger.debug("DNS IPV4 address    : " + temp.getOptions().getDnsIpv4Addr());
                    if (!temp.getOptions().getDnsIpv6Addr().equals(""))
                        logger.debug("DNS IPV6 address    : " + temp.getOptions().getDnsIpv6Addr());
                    if (!temp.getOptions().getDnsName().equals(""))
                        logger.debug("DNS SERVER NAME     : " + temp.getOptions().getDnsName());
                }
                logger.debug("##########################################################");
            }
        }
    }
}
