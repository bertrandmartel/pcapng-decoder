package fr.bmartel.pcapdecoder.network;

import java.util.ArrayList;

/**
 * Interface template for storing DNS entries with attributed IPV4 or IPV6 address
 * 
 * @author Bertrand Martel
 *
 */
public interface IDnsEntries {

	public ArrayList<String> getDnsEntries();
	
	public String getIpAddr();
}
