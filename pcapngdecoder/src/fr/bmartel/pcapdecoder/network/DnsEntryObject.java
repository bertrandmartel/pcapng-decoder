package fr.bmartel.pcapdecoder.network;

import java.util.ArrayList;

public class DnsEntryObject implements IDnsEntries {

	private ArrayList<String> dnsEntries = null;
	
	private String ipAddr = "";
	
	public DnsEntryObject(ArrayList<String> dnsEntries,String ipAddr) {
		this.dnsEntries=dnsEntries;
		this.ipAddr=ipAddr;
	}
		
	@Override
	public ArrayList<String> getDnsEntries() {
		return dnsEntries;
	}

	@Override
	public String getIpAddr() {
		return ipAddr;
	}
}
