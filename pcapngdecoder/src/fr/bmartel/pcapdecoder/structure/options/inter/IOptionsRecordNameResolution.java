package fr.bmartel.pcapdecoder.structure.options.inter;

import java.util.ArrayList;

import fr.bmartel.pcapdecoder.network.IDnsEntries;

/**
 * Interface template for records of Name resolution section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionsRecordNameResolution extends IOptions{

	public ArrayList<IDnsEntries> getIpv4DnsEntries();
	
	public ArrayList<IDnsEntries> getIpv6DnsEntries();
}
