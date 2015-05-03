/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
