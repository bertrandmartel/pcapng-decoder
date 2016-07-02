package fr.bmartel.pcapdecoder.structure.options;

/**
 * Pcap ng option object used to be stored dynamically by decoder
 * 
 * @author Bertrand Martel
 *
 */
public interface IPcapngOptions {

	public int getOptionCode();
	
	public int getOptionLength();
	
	public byte[] getOptionValue();
	
}
