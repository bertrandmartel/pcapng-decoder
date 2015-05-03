package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Option template for all options in all sections
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptions {

	/**
	 * Retrieve comment value : this is the only option common to all section in PCAP NG file
	 * @return
	 */
	public String getComment();
}
