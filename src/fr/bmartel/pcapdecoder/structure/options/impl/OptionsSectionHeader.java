/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
