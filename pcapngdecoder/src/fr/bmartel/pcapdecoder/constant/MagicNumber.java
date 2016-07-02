package fr.bmartel.pcapdecoder.constant;

/**
 * Define magic number for header block
 * 
 * @author Bertrand Martel
 *
 */
public class MagicNumber {

	public final static byte[] MAGIC_NUMBER_BIG_ENDIAN=new byte[]{ 0x1A,0x2B,0x3C,0x4D};
	
	public final static byte[] MAGIC_NUMBER_LITTLE_ENDIAN=new byte[]{ 0x4D,0x3C,0x2B,0x1A};
	
}
