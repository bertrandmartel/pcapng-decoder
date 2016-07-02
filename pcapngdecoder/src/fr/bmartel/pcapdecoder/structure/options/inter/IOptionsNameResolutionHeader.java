package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Name Resolution Header Section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionsNameResolutionHeader extends IOptions {

	/**
	 * Retrieve DNS server name
	 *  
	 * @return
	 */
	public String getDnsName();
	
	/**
	 * Retrieve DNS IPV4 server address
	 * @return
	 */
	public String getDnsIpv4Addr();
	
	/**
	 * Retrieve DNS IPV6 server address
	 * @return
	 */
	public String getDnsIpv6Addr();
}
