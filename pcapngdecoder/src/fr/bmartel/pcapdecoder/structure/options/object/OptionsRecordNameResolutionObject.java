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
package fr.bmartel.pcapdecoder.structure.options.object;

import java.util.ArrayList;

import fr.bmartel.pcapdecoder.network.IDnsEntries;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;

/**
 * Specific options named "record" for Name resolution section header
 *
 * @author Bertrand Martel
 */
public class OptionsRecordNameResolutionObject implements IOptionsRecordNameResolution {

    private String comment = "";

    private ArrayList<IDnsEntries> ipv4DnsEntries = new ArrayList<IDnsEntries>();

    private ArrayList<IDnsEntries> ipv6DnsEntries = new ArrayList<IDnsEntries>();

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public ArrayList<IDnsEntries> getIpv4DnsEntries() {
        return ipv4DnsEntries;
    }

    public void addIpv4DnsEntry(IDnsEntries ipv4DnsEntries) {
        this.ipv4DnsEntries.add(ipv4DnsEntries);
    }

    @Override
    public ArrayList<IDnsEntries> getIpv6DnsEntries() {
        return ipv6DnsEntries;
    }

    public void addIpv6DnsEntry(IDnsEntries ipv6DnsEntries) {
        this.ipv6DnsEntries.add(ipv6DnsEntries);
    }
}
