package fr.bmartel.pcapdecoder.structure.options.object;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionSectionHeader;

/**
 * Template used for setting values of Section Header
 * 
 * @author Bertrand Martel
 *
 */
public class OptionSectionHeaderObject implements IOptionSectionHeader{

	private String hardware = "";
	private String os ="";
	private String userAppl="";
	private String comment = "";
	
	@Override
	public String getHardware() {
		return hardware;
	}

	@Override
	public String getOS() {
		return os;
	}

	@Override
	public String getUserAppl() {
		return userAppl;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setUserAppl(String userAppl) {
		this.userAppl = userAppl;
	}

	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
