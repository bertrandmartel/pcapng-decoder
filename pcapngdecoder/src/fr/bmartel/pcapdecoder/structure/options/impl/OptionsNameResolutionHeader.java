package fr.bmartel.pcapdecoder.structure.options.impl;

import java.io.UnsupportedEncodingException;

import fr.bmartel.pcapdecoder.structure.options.abstr.OptionsAbstr;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionsNameResolutionObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

public class OptionsNameResolutionHeader extends OptionsAbstr{

	/**
	 * common option object used to retrieve all options information
	 */
	private OptionsNameResolutionObject commonObject = null;
	
	public OptionsNameResolutionHeader(int optionCode, byte[] data,boolean isBigEndian, IOptions currentOption) {
		super(optionCode, data, isBigEndian, currentOption);
		
		this.commonObject=(OptionsNameResolutionObject) currentOption;
		
		// decode directly (no need to be called each time)
		decode();
	}

	/**
	 * decode options
	 * TODO : put comment parsing in abstract
	 */
	public void decode() {
		try
		{
			switch(optionCode)
			{
				case 1:
					if (!isBigEndian)
						commonObject.setComment(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setComment(new String(data,"UTF-8"));
					break;
				case 2:
					if (!isBigEndian)
						commonObject.setDnsName(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setDnsName(new String(data,"UTF-8"));
					break;
				case 3:
					byte[] tempIpv4 = new byte[4];
					if(!isBigEndian)
						tempIpv4=UtilFunctions.convertLeToBe(data);
					else 
						tempIpv4=data;
					commonObject.setDnsIpv4Addr(formatIpv4Addr(tempIpv4));
					break;
				case 4:
					byte[] tempIpv6 = new byte[4];
					if(!isBigEndian)
						tempIpv6=UtilFunctions.convertLeToBe(data);
					else 
						tempIpv6=data;
					commonObject.setDnsIpv6Addr(formatIpv4Addr(tempIpv6));
					break;
			}
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
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

		return ipStr.substring(0, ipStr.length()-1);
	}
}
