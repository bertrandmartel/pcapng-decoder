package fr.bmartel.pcapdecoder.structure.options.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import fr.bmartel.pcapdecoder.network.DnsEntryObject;
import fr.bmartel.pcapdecoder.network.NetworkUtils;
import fr.bmartel.pcapdecoder.structure.options.abstr.OptionsAbstr;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionsRecordNameResolutionObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Implementation for Record type options in Name resolution Section Header
 * 
 * @author Bertrand Martel
 *
 */
public class OptionsRecordNameResolutionHeader extends OptionsAbstr{
	
	/**
	 * common option object used to retrieve all options information
	 */
	private OptionsRecordNameResolutionObject commonObject = null;
	
	public OptionsRecordNameResolutionHeader(int optionCode, byte[] data,boolean isBigEndian, IOptions currentOption) {
		super(optionCode, data, isBigEndian, currentOption);
		
		this.commonObject=(OptionsRecordNameResolutionObject) currentOption;
		
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
					byte[] tempIpv4 = new byte[4];
					
					if(!isBigEndian)
						tempIpv4=UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, data.length-4, data.length));
					else 
						tempIpv4=Arrays.copyOfRange(data, 0, 4);
					
					String ipv4Addr = NetworkUtils.formatIpv4Addr(tempIpv4);
					
					ArrayList<String> entries = new ArrayList<String>();
					
					if(!isBigEndian)
						entries.add(new String(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 1, data.length-4)),"UTF-8"));
					else
						entries.add(new String(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 4, data.length-1)),"UTF-8"));
					
					commonObject.addIpv4DnsEntry(new DnsEntryObject(entries, ipv4Addr));
					
					break;
				case 2:
					byte[] tempIpv6 = new byte[16];
					
					if(!isBigEndian)
						tempIpv6=UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, data.length-16, data.length));
					else 
						tempIpv6=Arrays.copyOfRange(data, 0, 16);
					
					String ipv6Addr = NetworkUtils.formatIpv6Addr(tempIpv6);
					
					ArrayList<String> entriesIpv6 = new ArrayList<String>();
					
					if(!isBigEndian)
						entriesIpv6.add(new String(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 1, data.length-16)),"UTF-8"));
					else
						entriesIpv6.add(new String(UtilFunctions.convertLeToBe(Arrays.copyOfRange(data, 16, data.length-1)),"UTF-8"));
					
					commonObject.addIpv6DnsEntry(new DnsEntryObject(entriesIpv6, ipv6Addr));
					
					break;
			}
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
