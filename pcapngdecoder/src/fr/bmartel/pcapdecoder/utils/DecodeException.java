package fr.bmartel.pcapdecoder.utils;

/**
 * Custom Exception for Pcap ng decoder
 * 
 * @author Bertrand Martel
 *
 */
public class DecodeException extends Exception  {

	private static final long serialVersionUID = 1L;

	public DecodeException()
	{
		super("Pcap NG file decoding exception occured");
	}
	
	public DecodeException(String customMessage)
	{
		super(customMessage);
	}
}
