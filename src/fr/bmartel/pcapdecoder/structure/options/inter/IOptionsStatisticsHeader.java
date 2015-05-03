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
