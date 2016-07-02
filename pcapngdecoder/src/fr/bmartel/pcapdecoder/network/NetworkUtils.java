package fr.bmartel.pcapdecoder.network;

import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Useful functions for network
 * 
 * @author Bertrand Martel
 *
 */
public class NetworkUtils {
	
	public static String formatIpv4Addr(byte[] ip)
	{
		String ipStr = "";
		
		for (int i = 0; i < ip.length;i++)
		{
			ipStr+=(ip[i] & 0xFF)+".";
		}
		return ipStr.substring(0, ipStr.length()-1);
	}
	
	public static String formatIpv6Addr(byte[] ip)
	{
		String ipStr ="";
		
		for (int i = 0; i  < 16;i=i+2)
		{
			ipStr+=UtilFunctions.convertFromIntToHexa(ip[i]) + UtilFunctions.convertFromIntToHexa(ip[i+1]) + ":";
		}

		return ipStr.substring(0, ipStr.length()-1);
	}
	
	public static String formatMacAddr(byte[] macAddr)
	{
		String macAddrStr="";
		for (int i = 0;i < macAddr.length;i++)
		{
			macAddrStr+=UtilFunctions.convertFromIntToHexa(macAddr[i])+":";
		}
		
		return macAddrStr.substring(0, macAddrStr.length()-1);
	}
	
	
	public static String formatIpv6AddrWithPort(byte[] ip)
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
