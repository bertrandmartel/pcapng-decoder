package fr.bmartel.pcapdecoder.structure.options.impl;

import java.io.UnsupportedEncodingException;

import fr.bmartel.pcapdecoder.structure.options.abstr.OptionsAbstr;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptions;
import fr.bmartel.pcapdecoder.structure.options.object.OptionSectionHeaderObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * Implementation for Options in Section Header
 * 
 * @author Bertrand Martel
 *
 */
public class OptionsSectionHeader extends OptionsAbstr{
	
	private OptionSectionHeaderObject commonObject = null;
	
	public OptionsSectionHeader(int optionCode,byte[] data,boolean isBigEndian,IOptions currentOption) {
		super(optionCode,data,isBigEndian,currentOption);
		this.commonObject=(OptionSectionHeaderObject) currentOption;
		decode();
	}

	public void decode() {
		try
		{
			switch(optionCode)
			{
				case 1:
					if (!isBigEndian)
						commonObject.setComment(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setComment(new String(data,"UTF-8"));
					break;
				case 2:
					if (!isBigEndian)
						commonObject.setHardware(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setHardware(new String(data,"UTF-8"));
					break;
				case 3:
					if (!isBigEndian)
						commonObject.setOs(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setOs(new String(data,"UTF-8"));
					break;
				case 4:
					if (!isBigEndian)
						commonObject.setUserAppl(new String(UtilFunctions.convertLeToBe(data),"UTF-8"));
					else
						commonObject.setUserAppl(new String(data,"UTF-8"));
					break;
			}
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
