package fr.bmartel.pcapdecoder.structure.options.object;

import java.util.ArrayList;

import fr.bmartel.pcapdecoder.network.IDnsEntries;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;

/**
 * Specific options named "record" for Name resolution section header
 * 
 * @author Bertrand Martel
 *
 */
public class OptionsRecordNameResolutionObject implements IOptionsRecordNameResolution{

	private String comment = "";
	
	private ArrayList<IDnsEntries> ipv4DnsEntries = new ArrayList<IDnsEntries>();
	
	private ArrayList<IDnsEntries> ipv6DnsEntries = new ArrayList<IDnsEntries>();
	
	@Override
	public String getComment() {
		return comment;
	}
	@Override
	public ArrayList<IDnsEntries> getIpv4DnsEntries() {
		return ipv4DnsEntries;
	}

	public void addIpv4DnsEntry(IDnsEntries ipv4DnsEntries) {
		this.ipv4DnsEntries.add(ipv4DnsEntries);
	}
	@Override
	public ArrayList<IDnsEntries> getIpv6DnsEntries() {
		return ipv6DnsEntries;
	}

	public void addIpv6DnsEntry(IDnsEntries ipv6DnsEntries) {
		this.ipv6DnsEntries.add(ipv6DnsEntries);
	}
}
