package fr.bmartel.pcapdecoder.constant;

/**
 * Define hash type for packet 
 * 
 * @author Bertrand Martel
 *
 */
public enum PacketHashType {

	TWOS_COMPLEMENT,
	XOR,
	CRC32,
	MD5,
	SHA1,
	UNKNOWN
}
