package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Interface Description Header section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionsDescriptionHeader extends IOptions{

	/**
	 *  name of the device used to capture
	 *  
	 * @return
	 */
	public String getInterfaceName();

	/**
	 * Description of the device used to capture the data
	 * 
	 * @return
	 */
	public String getInterfaceDescription();
	
	/**
	 * IPV4 address
	 * 
	 * @return
	 */
	public String getInterfaceIpv4NetworkAddr();
	
	/**
	 * IP netmask
	 * 
	 * @return
	 */
	public String getInterfaceNetmask();
	
	/**
	 * IPV6 address
	 * 
	 * @return
	 */
	public String getIpv6NetworkAddr();
	
	/**
	 * MAC address
	 * 
	 * @return
	 */
	public String getInterfaceMacAddr();
	
	/**
	 * EUI address
	 * 
	 * @return
	 */
	public String getInterfaceEuiAddr();
	
	/**
	 * interface speed in bps
	 * 
	 * @return
	 */
	public int getInterfaceSpeed();
	
	/**
	 * Resolution of timestamp (6 means microsecond resolution for instance)
	 * 
	 * @return
	 */
	public int getTimeStampResolution();
	
	/**
	 * indicate Time zone => offset from UTC time => indicating time zone
	 * TODO : make sure above is true 
	 * 
	 * @return
	 */
	public int getTimeBias();
	
	
	/**
	 * This may not be a String (waiting for more concrete example with option code 11)
	 * 
	 * @return
	 * 		string filter
	 */
	public String getInterfaceFilter();
	
	/**
	 * Name of the operating system 
	 * 
	 * @return
	 */
	public String getInterfaceOperatingSystem();
	
	
	public int getInterfaceFrameCheckSequenceLength();
	
	/**
	 * Timestamp offset for each packet / if not present timestamp are absolute
	 * @return
	 */
	public int getTimeStampOffset();
}