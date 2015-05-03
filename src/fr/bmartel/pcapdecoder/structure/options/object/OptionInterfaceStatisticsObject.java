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
package fr.bmartel.pcapdecoder.structure.options.object;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;

/**
 * Template used for setting values of Interface Statistics Section
 * 
 * @author Bertrand Martel
 *
 */
public class OptionInterfaceStatisticsObject implements IOptionsStatisticsHeader{

	private Long captureStartTime= -1L;
	
	private Long captureEndTime = -1L;
	
	private Long packetReceivedCount=-1L;
	
	private Long packetDropCount = -1L;
	
	private Long packetAcceptedByFilterCount  = -1L;

	private Long packetDroppedByOS=-1L;
	
	private Long packetDeliveredToUser = -1L;

	private String comment = "";
	
	@Override
	public Long getCaptureStartTime() {
		return captureStartTime;
	}

	public void setCaptureStartTime(Long captureStartTime) {
		this.captureStartTime = captureStartTime;
	}
	@Override
	public Long getCaptureEndTime() {
		return captureEndTime;
	}

	public void setCaptureEndTime(Long captureEndTime) {
		this.captureEndTime = captureEndTime;
	}
	@Override
	public Long getPacketReceivedCount() {
		return packetReceivedCount;
	}

	public void setPacketReceivedCount(Long packetReceivedCount) {
		this.packetReceivedCount = packetReceivedCount;
	}
	@Override
	public Long getPacketDropCount() {
		return packetDropCount;
	}

	public void setPacketDropCount(Long packetDropCount) {
		this.packetDropCount = packetDropCount;
	}
	@Override
	public Long getPacketAcceptedByFilterCount() {
		return packetAcceptedByFilterCount;
	}

	public void setPacketAcceptedByFilterCount(Long packetAcceptedByFilterCount) {
		this.packetAcceptedByFilterCount = packetAcceptedByFilterCount;
	}
	@Override
	public Long getPacketDroppedByOS() {
		return packetDroppedByOS;
	}

	public void setPacketDroppedByOS(Long packetDroppedByOS) {
		this.packetDroppedByOS = packetDroppedByOS;
	}

	@Override
	public Long getPacketDeliveredToUser() {
		return packetDeliveredToUser;
	}

	public void setPacketDeliveredToUser(Long packetDeliveredToUser) {
		this.packetDeliveredToUser = packetDeliveredToUser;
	}

	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment=comment;
	}
}
