package fr.bmartel.pcapdecoder.structure.options.abstr;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;

/**
 * Abstract layer for option management. All implementations of PCAP NG options (section header / interface statistics / interface description..)
 * must extends this class. IOptions is necessary to be implemented here because it contains getComment() only option common to all kind of sections
 * in PCAP NG file
 * 
 * @author Bertrand Martel
 *
 */
public abstract class OptionsAbstr implements IOptions {
	
	/**
	 * option code value
	 */
	protected int optionCode = -1;
	
	/**
	 * option data field
	 */
	protected byte[] data = null;
	
	/**
	 * define endianness
	 */
	protected boolean isBigEndian = false;
	
	/**
	 * option common object
	 */
	protected IOptions currentOption = null;
	
	/**
	 * Build option to be implemented on all sections' options
	 * 
	 * @param optionCode
	 * @param data
	 * @param isBigEndian
	 * @param currentOption
	 */
	public OptionsAbstr(int optionCode,byte[] data,boolean isBigEndian,IOptions currentOption)
	{
		this.optionCode=optionCode;
		this.data=data;
		this.isBigEndian=isBigEndian;
		this.currentOption=currentOption;
	}
	
	@Override
	public String getComment() {
		return "";
	}
}
