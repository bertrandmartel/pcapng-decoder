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
