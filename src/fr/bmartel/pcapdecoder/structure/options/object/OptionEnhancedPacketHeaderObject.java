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

import java.util.ArrayList;

import fr.bmartel.pcapdecoder.constant.LinkLayerError;
import fr.bmartel.pcapdecoder.constant.PacketBoundState;
import fr.bmartel.pcapdecoder.constant.PacketHashType;
import fr.bmartel.pcapdecoder.constant.PacketReceptionType;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;

/**
 * Template used for setting values of Enhanced Packet Section
 * 
 * @author Bertrand Martel
 *
 */
public class OptionEnhancedPacketHeaderObject implements IOptionsEnhancedPacketHeader {

	private String comment = "";
	
	private PacketBoundState packetBound =PacketBoundState.UNKNOWN;
	
	private PacketReceptionType packetReceptionType = PacketReceptionType.UNKNOWN;
	
	private int frameCheckSumLength = -1;
	
	private int dropPacketCount = -1;
	
	private PacketHashType packetHashType = PacketHashType.UNKNOWN;
	
	private byte[] packetHashBigEndian = null;
	
	private ArrayList<LinkLayerError> linkLayerErrorList = new ArrayList<LinkLayerError>();
	
	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public PacketBoundState getPacketBound() {
		return packetBound;
	}

	public void setPacketBound(PacketBoundState packetBound) {
		this.packetBound = packetBound;
	}
	
	@Override
	public PacketReceptionType getPacketReceptionType() {
		return packetReceptionType;
	}

	public void setPacketReceptionType(PacketReceptionType packetReceptionType) {
		this.packetReceptionType = packetReceptionType;
	}

	@Override
	public int getFrameCheckSumLength() {
		return frameCheckSumLength;
	}

	public void setFrameCheckSumLength(int frameCheckSumLength) {
		this.frameCheckSumLength = frameCheckSumLength;
	}

	@Override
	public int getDropPacketCount() {
		return dropPacketCount;
	}

	public void setDropPacketCount(int dropPacketCount) {
		this.dropPacketCount = dropPacketCount;
	}

	@Override
	public PacketHashType getPacketHashType() {
		return packetHashType;
	}

	public void setPacketHashType(PacketHashType packetHashType) {
		this.packetHashType = packetHashType;
	}

	@Override
	public byte[] getPacketHashBigEndian() {
		return packetHashBigEndian;
	}

	public void setPacketHashBigEndian(byte[] packetHashBigEndian) {
		this.packetHashBigEndian = packetHashBigEndian;
	}

	@Override
	public ArrayList<LinkLayerError> getLinkLayerErrorList() {
		return linkLayerErrorList;
	}

	public void addLinkLayerError(LinkLayerError error) {
		this.linkLayerErrorList.add(error);
	}

}
