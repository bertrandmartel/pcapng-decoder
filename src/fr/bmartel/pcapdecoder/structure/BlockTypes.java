package fr.bmartel.pcapdecoder.structure;

/**
 * Block Types description 
 * 
 * @author Bertrand Martel
 *
 */
public enum BlockTypes {
	//MANDATORY : it defines the most important characteristics of the capture file
	SECTION_HEADER_BLOCK,
	//MANDATORY : it defines the most important characteristics of the interface(s) used for capturing traffic
	INTERFACE_DESCRIPTION_BLOCK,
	//OPTIONAL  : it contains a single captured packet, or a portion of it. It represents an evolution of the original Packet Block
	ENHANCES_PACKET_BLOCK,
	//OPTIONAL  : it contains a single captured packet, or a portion of it, with only a minimal set of information about it
	SIMPLE_PACKET_BLOCK,
	//OPTIONAL  : it defines the mapping from numeric addresses present in the packet dump and the canonical name counterpart
	NAME_RESOLUTION_BLOCK,
	//OPTIONAL  : it defines how to store some statistical data (e.g. packet dropped, etc) which can be useful to undestand the conditions in which the capture has been made
	INTERFACE_STATISTICS_BLOCK,
	//OBSOLETE  : it contains a single captured packet, or a portion of it. It should be considered OBSOLETE, and superseded by the Enhanced Packet Block
	PACKET_BLOCK,
	
	UNKNOWN
}
