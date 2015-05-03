package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Interface Statistics section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionsStatisticsHeader extends IOptions{

	/**
	 * capture start time (timestamp resolution is defined in Interface description header check exemple)
	 * @return
	 */
	public Long getCaptureStartTime();
	
	/**
	 * capture end time (timestamp resolution is defined in Interface description header check exemple)
	 * @return
	 */
	public Long getCaptureEndTime();
	
	/**
	 * packet received count
	 * @return
	 */
	public Long getPacketReceivedCount();

	/**
	 * packet drop count
	 * @return
	 */
	public Long getPacketDropCount();
	
	/**
	 * packet accepted by filter count
	 * @return
	 */
	public Long getPacketAcceptedByFilterCount();
	
	/**
	 * 
	 * packet dropped by Operating system count
	 * 
	 * @return
	 */
	public Long getPacketDroppedByOS();
	
	/**
	 * packet deliver to use count
	 * @return
	 */
	public Long getPacketDeliveredToUser();
	
}
