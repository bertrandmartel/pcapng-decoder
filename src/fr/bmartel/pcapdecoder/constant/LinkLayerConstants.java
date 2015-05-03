package fr.bmartel.pcapdecoder.constant;

import java.util.HashMap;

/**
 * 
 * Constants link layer enum
 * 
 * @author Bertrand Martel
 *
 */
public class LinkLayerConstants {
	
	public final static HashMap<Integer, String> LINK_LAYER_LIST = new HashMap<Integer, String>();
	
	static
	{
		LINK_LAYER_LIST.put(0  ,"LINKTYPE_NULL"                  );
		LINK_LAYER_LIST.put(1  ,"LINKTYPE_ETHERNET"              );
		LINK_LAYER_LIST.put(2  ,"LINKTYPE_EXP_ETHERNET"          );
		LINK_LAYER_LIST.put(3  ,"LINKTYPE_AX25"                  );
		LINK_LAYER_LIST.put(4  ,"LINKTYPE_PRONET"                );
		LINK_LAYER_LIST.put(5  ,"LINKTYPE_CHAOS"                 );
		LINK_LAYER_LIST.put(6  ,"LINKTYPE_TOKEN_RING"            );
		LINK_LAYER_LIST.put(7  ,"LINKTYPE_ARCNET"                );
		LINK_LAYER_LIST.put(8  ,"LINKTYPE_SLIP"                  );
		LINK_LAYER_LIST.put(9  ,"LINKTYPE_PPP"                   );
		LINK_LAYER_LIST.put(10 ,"LINKTYPE_FDDI"                  );
		LINK_LAYER_LIST.put(50 ,"LINKTYPE_PPP_HDLC"              );
		LINK_LAYER_LIST.put(51 ,"LINKTYPE_PPP_ETHER"             );
		LINK_LAYER_LIST.put(99 ,"LINKTYPE_SYMANTEC_FIREWALL"     );
		LINK_LAYER_LIST.put(100,"LINKTYPE_ATM_RFC1483"           );
		LINK_LAYER_LIST.put(101,"LINKTYPE_RAW"                   );
		LINK_LAYER_LIST.put(102,"LINKTYPE_SLIP_BSDOS"            );
		LINK_LAYER_LIST.put(103,"LINKTYPE_PPP_BSDOS"             );
		LINK_LAYER_LIST.put(104,"LINKTYPE_C_HDLC"                );
		LINK_LAYER_LIST.put(105,"LINKTYPE_IEEE802_11"            );
		LINK_LAYER_LIST.put(106,"LINKTYPE_ATM_CLIP"              );
		LINK_LAYER_LIST.put(107,"LINKTYPE_FRELAY"                );
		LINK_LAYER_LIST.put(108,"LINKTYPE_LOOP"                  );
		LINK_LAYER_LIST.put(109,"LINKTYPE_ENC"                   );
		LINK_LAYER_LIST.put(110,"LINKTYPE_LANE8023"              );
		LINK_LAYER_LIST.put(111,"LINKTYPE_HIPPI"                 );
		LINK_LAYER_LIST.put(112,"LINKTYPE_HDLC"                  );
		LINK_LAYER_LIST.put(113,"LINKTYPE_LINUX_SLL"             );
		LINK_LAYER_LIST.put(114,"LINKTYPE_LTALK"                 );
		LINK_LAYER_LIST.put(115,"LINKTYPE_ECONET"                );
		LINK_LAYER_LIST.put(116,"LINKTYPE_IPFILTER"              );
		LINK_LAYER_LIST.put(117,"LINKTYPE_PFLOG"                 );
		LINK_LAYER_LIST.put(118,"LINKTYPE_CISCO_IOS"             );
		LINK_LAYER_LIST.put(119,"LINKTYPE_PRISM_HEADER"          );
		LINK_LAYER_LIST.put(120,"LINKTYPE_AIRONET_HEADER"        );
		LINK_LAYER_LIST.put(121,"LINKTYPE_HHDLC"                 );
		LINK_LAYER_LIST.put(122,"LINKTYPE_IP_OVER_FC"            );
		LINK_LAYER_LIST.put(123,"LINKTYPE_SUNATM"                );
		LINK_LAYER_LIST.put(124,"LINKTYPE_RIO"                   );
		LINK_LAYER_LIST.put(125,"LINKTYPE_PCI_EXP"               );
		LINK_LAYER_LIST.put(126,"LINKTYPE_AURORA"                );
		LINK_LAYER_LIST.put(127,"LINKTYPE_IEEE802_11_RADIO"      );
		LINK_LAYER_LIST.put(128,"LINKTYPE_TZSP"                  );
		LINK_LAYER_LIST.put(129,"LINKTYPE_ARCNET_LINUX"          );
		LINK_LAYER_LIST.put(130,"LINKTYPE_JUNIPER_MLPPP"         );
		LINK_LAYER_LIST.put(131,"LINKTYPE_JUNIPER_MLFR"          );
		LINK_LAYER_LIST.put(132,"LINKTYPE_JUNIPER_ES"            );
		LINK_LAYER_LIST.put(133,"LINKTYPE_JUNIPER_GGSN"          );
		LINK_LAYER_LIST.put(134,"LINKTYPE_JUNIPER_MFR"           );
		LINK_LAYER_LIST.put(135,"LINKTYPE_JUNIPER_ATM2"          );
		LINK_LAYER_LIST.put(136,"LINKTYPE_JUNIPER_SERVICES"      );
		LINK_LAYER_LIST.put(137,"LINKTYPE_JUNIPER_ATM1"          );
		LINK_LAYER_LIST.put(138,"LINKTYPE_APPLE_IP_OVER_IEEE1394");
		LINK_LAYER_LIST.put(139,"LINKTYPE_MTP2_WITH_PHDR"        );
		LINK_LAYER_LIST.put(140,"LINKTYPE_MTP2"                  );
		LINK_LAYER_LIST.put(141,"LINKTYPE_MTP3"                  );
		LINK_LAYER_LIST.put(142,"LINKTYPE_SCCP"                  );
		LINK_LAYER_LIST.put(143,"LINKTYPE_DOCSIS"                );
		LINK_LAYER_LIST.put(144,"LINKTYPE_LINUX_IRDA"            );
		LINK_LAYER_LIST.put(145,"LINKTYPE_IBM_SP"                );
		LINK_LAYER_LIST.put(146,"LINKTYPE_IBM_SN"                );
	}
}
