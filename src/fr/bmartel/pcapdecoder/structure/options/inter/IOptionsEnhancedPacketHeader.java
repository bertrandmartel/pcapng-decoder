package fr.bmartel.pcapdecoder.structure.options.inter;

import java.util.ArrayList;

import fr.bmartel.pcapdecoder.constant.LinkLayerError;
import fr.bmartel.pcapdecoder.constant.PacketBoundState;
import fr.bmartel.pcapdecoder.constant.PacketHashType;
import fr.bmartel.pcapdecoder.constant.PacketReceptionType;

/**
 * Interface template for Enhanced Packet Section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionsEnhancedPacketHeader extends IOptions{

	/**
	 * Retrieve bound state (inbound / outbound)
	 * @return
	 */
	public PacketBoundState getPacketBound();
	
	/**
	 * Retriev reception state (broadcast / unicast / multicast / promiscuous)
	 * @return
	 */
	public PacketReceptionType getPacketReceptionType();
	
	/**
	 * get FCS length
	 * @return
	 */
	public int getFrameCheckSumLength();
	
	/**
	 * get drop packet count
	 * 
	 * @return
	 */
	public int getDropPacketCount() ;

	/**
	 * get packet hash type (2SCOMP / MD5 / CRC32/ SHA1  ...)
	 * 
	 * @return
	 */
	public PacketHashType getPacketHashType();
	
	/**
	 * get packet hash
	 * @return
	 */
	public byte[] getPacketHashBigEndian();
	
	/**
	 * get a list of error (link layer dependant)
	 * @return
	 */
	public ArrayList<LinkLayerError> getLinkLayerErrorList();
	
}
