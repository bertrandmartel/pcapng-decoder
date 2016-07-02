package fr.bmartel.pcapdecoder.constant;

/**
 * Link Layer error list
 * 
 * @author Bertrand Martel
 *
 */
public enum LinkLayerError {
	SYMBOL_ERRROR,
	PREAMBLE_ERRO,
	START_FRAME_DELIMITER_ERROR,
	UNALIGNED_FRAME_ERROR,
	WRONG_INTER_FRAME_GAP_ERROR,
	PACKET_TOO_SHORT_ERROR,
	PACKET_TOO_LONG_ERROR,
	CRC_ERROR,
	UNKNOWN
}
