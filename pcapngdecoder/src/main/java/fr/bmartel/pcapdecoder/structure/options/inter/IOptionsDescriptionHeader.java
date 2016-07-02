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
package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Interface Description Header section
 *
 * @author Bertrand Martel
 */
public interface IOptionsDescriptionHeader extends IOptions {

    /**
     * name of the device used to capture
     *
     * @return
     */
    String getInterfaceName();

    /**
     * Description of the device used to capture the data
     *
     * @return
     */
    String getInterfaceDescription();

    /**
     * IPV4 address
     *
     * @return
     */
    String getInterfaceIpv4NetworkAddr();

    /**
     * IP netmask
     *
     * @return
     */
    String getInterfaceNetmask();

    /**
     * IPV6 address
     *
     * @return
     */
    String getIpv6NetworkAddr();

    /**
     * MAC address
     *
     * @return
     */
    String getInterfaceMacAddr();

    /**
     * EUI address
     *
     * @return
     */
    String getInterfaceEuiAddr();

    /**
     * interface speed in bps
     *
     * @return
     */
    int getInterfaceSpeed();

    /**
     * Resolution of timestamp (6 means microsecond resolution for instance)
     *
     * @return
     */
    int getTimeStampResolution();

    /**
     * indicate Time zone => offset from UTC time => indicating time zone
     * TODO : make sure above is true
     *
     * @return
     */
    int getTimeBias();


    /**
     * This may not be a String (waiting for more concrete example with option code 11)
     *
     * @return string filter
     */
    String getInterfaceFilter();

    /**
     * Name of the operating system
     *
     * @return
     */
    String getInterfaceOperatingSystem();


    int getInterfaceFrameCheckSequenceLength();

    /**
     * Timestamp offset for each packet / if not present timestamp are absolute
     *
     * @return
     */
    int getTimeStampOffset();
}