package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Section Header section
 * 
 * @author Bertrand Martel
 *
 */
public interface IOptionSectionHeader extends IOptions{

	/**
	 * description of the harware
	 * 
	 * @return
	 */
	public String getHardware();
	
	/**
	 * name of the OS
	 * 
	 * @return
	 */
	public String getOS();
	
	/**
	 * name of the user application
	 * 
	 * @return
	 */
	public String getUserAppl();
}
