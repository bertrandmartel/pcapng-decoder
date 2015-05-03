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

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;

/**
 * Template used for setting values of Interface Description Section
 * 
 * @author Bertrand Martel
 *
 */
public class OptionInterfaceDescriptionObject implements IOptionsDescriptionHeader {

	private String interfaceName = "";
	
	private String interfaceDescription = "";
	
	private String interfaceIpv4NetworkAddr = "";
	
	private String interfaceNetmask="";
	
	private String interfaceIpv6NetworkAddr="";
	
	private String interfaceMacAddr ="";
	
	private String interfaceEuiAddr = "";
	
	private int interfaceSpeed=-1;
	
	private int timestampResolution = -1;
	
	private int timeBias = -1;
	
	private String interfaceFilter = "";
	
	private String interfaceOperatingSystem ="";
	
	private int interfaceFrameCheckSequenceLength = -1;
	
	private int packetOffsetTime = -1;
	
	private String comment = "";
	
	@Override
	public String getInterfaceName() {
		return interfaceName;
	}

	@Override
	public String getInterfaceDescription() {
		return interfaceDescription;
	}

	@Override
	public String getInterfaceIpv4NetworkAddr() {
		return interfaceIpv4NetworkAddr;
	}

	@Override
	public String getInterfaceNetmask() {
		return interfaceNetmask;
	}

	@Override
	public String getIpv6NetworkAddr() {
		return interfaceIpv6NetworkAddr;
	}

	@Override
	public String getInterfaceMacAddr() {
		return interfaceMacAddr;
	}

	@Override
	public String getInterfaceEuiAddr() {
		return interfaceEuiAddr;
	}

	@Override
	public int getInterfaceSpeed() {
		return interfaceSpeed;
	}

	@Override
	public int getTimeStampResolution() {
		return timestampResolution;
	}

	@Override
	public int getTimeBias() {
		return timeBias;
	}

	@Override
	public String getInterfaceFilter() {
		return interfaceFilter;
	}

	@Override
	public String getInterfaceOperatingSystem() {
		return interfaceOperatingSystem;
	}

	@Override
	public int getInterfaceFrameCheckSequenceLength() {
		return interfaceFrameCheckSequenceLength;
	}

	@Override
	public int getTimeStampOffset() {
		return packetOffsetTime;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	public void setInterfaceIpv4NetworkAddr(String interfaceIpv4NetworkAddr) {
		this.interfaceIpv4NetworkAddr = interfaceIpv4NetworkAddr;
	}

	public void setInterfaceNetmask(String interfaceNetmask) {
		this.interfaceNetmask = interfaceNetmask;
	}

	public void setInterfaceIpv6NetworkAddr(String interfaceIpv6NetworkAddr) {
		this.interfaceIpv6NetworkAddr = interfaceIpv6NetworkAddr;
	}

	public void setInterfaceMacAddr(String interfaceMacAddr) {
		this.interfaceMacAddr = interfaceMacAddr;
	}

	public void setInterfaceEuiAddr(String interfaceEuiAddr) {
		this.interfaceEuiAddr = interfaceEuiAddr;
	}

	public void setInterfaceSpeed(int interfaceSpeed) {
		this.interfaceSpeed = interfaceSpeed;
	}

	public void setTimestampResolution(int timestampResolution) {
		this.timestampResolution = timestampResolution;
	}

	public void setTimeBias(int timeBias) {
		this.timeBias = timeBias;
	}

	public void setInterfaceFilter(String interfaceFilter) {
		this.interfaceFilter = interfaceFilter;
	}

	public void setInterfaceOperatingSystem(String interfaceOperatingSystem) {
		this.interfaceOperatingSystem = interfaceOperatingSystem;
	}

	public void setInterfaceFrameCheckSequenceLength(
			int interfaceFrameCheckSequenceLength) {
		this.interfaceFrameCheckSequenceLength = interfaceFrameCheckSequenceLength;
	}

	public void setPacketOffsetTime(int packetOffsetTime) {
		this.packetOffsetTime = packetOffsetTime;
	}

	public void setComment(String comment)
	{
		this.comment=comment;
	}
	
	@Override
	public String getComment() {
		return comment;
	}

}
