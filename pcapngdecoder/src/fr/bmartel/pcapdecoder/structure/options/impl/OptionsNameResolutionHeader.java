/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015-2016 Bertrand Martel
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
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
import fr.bmartel.pcapdecoder.structure.options.object.OptionsNameResolutionObject;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

public class OptionsNameResolutionHeader extends OptionsAbstr {

    /**
     * common option object used to retrieve all options information
     */
    private OptionsNameResolutionObject commonObject = null;

    public OptionsNameResolutionHeader(int optionCode, byte[] data, boolean isBigEndian, IOptions currentOption) {
        super(optionCode, data, isBigEndian, currentOption);

        this.commonObject = (OptionsNameResolutionObject) currentOption;

        // decode directly (no need to be called each time)
        decode();
    }

    /**
     * decode options
     * TODO : put comment parsing in abstract
     */
    public void decode() {
        try {
            switch (optionCode) {
                case 1:
                    if (!isBigEndian)
                        commonObject.setComment(new String(UtilFunctions.convertLeToBe(data), "UTF-8"));
                    else
                        commonObject.setComment(new String(data, "UTF-8"));
                    break;
                case 2:
                    if (!isBigEndian)
                        commonObject.setDnsName(new String(UtilFunctions.convertLeToBe(data), "UTF-8"));
                    else
                        commonObject.setDnsName(new String(data, "UTF-8"));
                    break;
                case 3:
                    byte[] tempIpv4 = new byte[4];
                    if (!isBigEndian)
                        tempIpv4 = UtilFunctions.convertLeToBe(data);
                    else
                        tempIpv4 = data;
                    commonObject.setDnsIpv4Addr(formatIpv4Addr(tempIpv4));
                    break;
                case 4:
                    byte[] tempIpv6 = new byte[4];
                    if (!isBigEndian)
                        tempIpv6 = UtilFunctions.convertLeToBe(data);
                    else
                        tempIpv6 = data;
                    commonObject.setDnsIpv6Addr(formatIpv4Addr(tempIpv6));
                    break;
                default:
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String formatIpv4Addr(byte[] ip) {
        String ipStr = "";

        for (int i = 0; i < ip.length; i++) {
            ipStr += ip[i] + ".";
        }
        return ipStr.substring(0, ipStr.length() - 1);
    }

    public String formatIpv6Addr(byte[] ip) {
        String ipStr = "";

        for (int i = 0; i < 16; i = i + 2) {
            ipStr += UtilFunctions.convertFromIntToHexa(ip[i]) + UtilFunctions.convertFromIntToHexa(ip[i + 1]) + ":";
        }

        return ipStr.substring(0, ipStr.length() - 1);
    }
}
