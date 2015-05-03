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
package fr.bmartel.pcapdecoder.structure.options.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.options.abstr.OptionsAbstr;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionInterfaceDescriptionObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * 
 * Implementation for Options in Interface Description Header
 * 
 * @author Bertrand Martel
 *
 */
public class OptionsInterfaceDescriptionHeader extends OptionsAbstr{

	private OptionInterfaceDescriptionObject commonObject = null;
	
	public OptionsInterfaceDescriptionHeader(int optionCode, byte[] data,boolean isBigEndian,IOptions currentOption) {
		super(optionCode, data, isBigEndian,currentOption);
		this.commonObject=(OptionInterfaceDescriptionObject) currentOption;
		
		decode();
	}

	public void decode() {
		try
		{
			switch(optionCode)
			{
				case 1:
					if (!isBigEndian)
						this.commonObject.setComment(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						this.commonObject.setComment(new String(data,"UTF-8"));
					break;
				case 2:
					//interface name
					if (!isBigEndian)
						this.commonObject.setInterfaceName(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						this.commonObject.setInterfaceName(new String(data,"UTF-8"));
					break;
				case 3:
					//interface description
					if (!isBigEndian)
						this.commonObject.setInterfaceDescription(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						this.commonObject.setInterfaceDescription(new String(data,"UTF-8"));
					break;
				case 4:
					//interface network following by netmask
					byte[] interfaceAddr = new byte[4];
					byte[] netmask = new byte[4];
					if (!isBigEndian)
					{
						interfaceAddr = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 4));
						netmask = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4, 8));
					}
					else
					{
						interfaceAddr = Arrays.copyOfRange(data, 0, 4);
						netmask = Arrays.copyOfRange(data, 4, 8);
					}
					
					this.commonObject.setInterfaceIpv4NetworkAddr(formatIpv4Addr(interfaceAddr));
					this.commonObject.setInterfaceNetmask(formatIpv4Addr(netmask));

					
					break;
				case 5:
					byte[] interfaceIpv6Addr = new byte[17];
					if (!isBigEndian)
						interfaceIpv6Addr = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 17));
					else
						interfaceIpv6Addr = Arrays.copyOfRange(data, 0, 17);
					
					this.commonObject.setInterfaceIpv6NetworkAddr(formatIpv6Addr(interfaceIpv6Addr));
					
					break;
				case 6:
					byte[] macAddr = new byte[6];
					if (!isBigEndian)
						macAddr = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 6));
					else
						macAddr = Arrays.copyOfRange(data, 0, 6);
					
					this.commonObject.setInterfaceMacAddr(formatMacAddr(macAddr));
					break;
				case 7:
					byte[] euiAddr = new byte[8];
					if (!isBigEndian)
						euiAddr = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8));
					else
						euiAddr = Arrays.copyOfRange(data, 0, 8);
					
					this.commonObject.setInterfaceEuiAddr(formatMacAddr(euiAddr));
					break;
				case 8:
					byte[] speed = new byte[8];
					if (!isBigEndian)
						speed = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8));
					else
						speed = Arrays.copyOfRange(data, 0, 8);
					
					this.commonObject.setInterfaceSpeed(UtilFunctions.convertByteArrayToInt(speed));

					break;
				case 9:
					byte timeResolution = 0;
					timeResolution=data[0];
					
					this.commonObject.setTimestampResolution(timeResolution & 0xFF);

					break;
				case 10:
					byte[] biasTime = new byte[8];
					if (!isBigEndian)
						biasTime = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 4));
					else
						biasTime = Arrays.copyOfRange(data, 0, 4);
					
					this.commonObject.setTimeBias(UtilFunctions.convertByteArrayToInt(biasTime));

					break;
				case 11:
					if (!isBigEndian)
						this.commonObject.setInterfaceFilter(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						this.commonObject.setInterfaceFilter(new String(data,"UTF-8"));
					break;
				case 12:
					if (!isBigEndian)
						this.commonObject.setInterfaceOperatingSystem(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						this.commonObject.setInterfaceOperatingSystem(new String(data,"UTF-8"));
					break;
				case 13:
					this.commonObject.setInterfaceFrameCheckSequenceLength(data[0] & 0xFF);
					break;
				case 14:
					byte[] offsetTime = new byte[8];
					if (!isBigEndian)
						offsetTime = UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 0, 8));
					else
						offsetTime = Arrays.copyOfRange(data, 0, 8);
					
					this.commonObject.setPacketOffsetTime(UtilFunctions.convertByteArrayToInt(offsetTime));
					break;
			}
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	public String formatMacAddr(byte[] macAddr)
	{
		String macAddrStr="";
		for (int i = 0;i < macAddr.length;i++)
		{
			macAddrStr+=UtilFunctions.convertFromIntToHexa(macAddr[i])+":";
		}
		
		return macAddrStr.substring(0, macAddrStr.length()-1);
	}
	
	public String formatIpv4Addr(byte[] ip)
	{
		String ipStr = "";
		
		for (int i = 0; i < ip.length;i++)
		{
			ipStr+=ip[i]+".";
		}
		return ipStr.substring(0, ipStr.length()-1);
	}
	
	public String formatIpv6Addr(byte[] ip)
	{
		String ipStr ="";
		
		for (int i = 0; i  < 16;i=i+2)
		{
			ipStr+=UtilFunctions.convertFromIntToHexa(ip[i]) + UtilFunctions.convertFromIntToHexa(ip[i+1]) + ":";
		}
		ipStr=ipStr.substring(0, ipStr.length()-1)+"/"+(ip[16] & 0xFF);
		
		return ipStr;
	}
}
