package fr.bmartel.pcapdecoder.structure.options.object;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsNameResolutionHeader;

public class OptionsNameResolutionObject implements IOptionsNameResolutionHeader  {

	private String comment = "";
	
	private String dnsName ="";
	
	private String dnsIpv4Addr = "";
	
	private String dnsIpv6Addr ="";
	
	@Override
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment)
	{
		this.comment=comment;
	}
	@Override
	public String getDnsName() {
		return dnsName;
	}

	public void setDnsName(String dnsName) {
		this.dnsName = dnsName;
	}
	@Override
	public String getDnsIpv4Addr() {
		return dnsIpv4Addr;
	}

	public void setDnsIpv4Addr(String dnsIpv4) {
		this.dnsIpv4Addr = dnsIpv4;
	}
	@Override
	public String getDnsIpv6Addr() {
		return dnsIpv6Addr;
	}

	public void setDnsIpv6Addr(String dnsIpv6) {
		this.dnsIpv6Addr = dnsIpv6;
	}

}
