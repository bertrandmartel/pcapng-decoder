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
package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsNameResolutionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.INameResolutionBlock;

/**
 * Implementation for Name Resolution Block parsing in Pcap ng decoder
 * <p/>
 * NAME RESOLUTION BLOCK
 * <p/>
 * 0                   1                   2                   3
 * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +---------------------------------------------------------------+
 * 0 |                    Block Type = 0x00000004                    |
 * +---------------------------------------------------------------+
 * 4 |                      Block Total Length                       |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 8 |      Record Type              |         Record Length         |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * 12 /                       Record Value                            /
 * /             variable length, aligned to 32 bits               /
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * .                                                               .
 * .                  . . . other records . . .                    .
 * .                                                               .
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |  Record Type == end_of_recs   |  Record Length == 00          |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * /                                                               /
 * /                      Options (variable)                       /
 * /                                                               /
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                      Block Total Length                       |
 * +---------------------------------------------------------------+
 */
public class NameResolutionHeader implements INameResolutionBlock, IPcapngType {

    private IOptionsRecordNameResolution records = null;

    private IOptionsNameResolutionHeader options = null;

    public NameResolutionHeader(byte[] data, boolean isBigEndian, BlockTypes type) {

        if (data.length > 0) {

            //parse set of options
            OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data, 0, data.length), isBigEndian, type,
                    true);
            int initIndex = optionParser.decode();
            this.records = (IOptionsRecordNameResolution) optionParser.getOption();

            if ((data.length - initIndex) > 0) {
                //parse set of options
                OptionParser optionParser2 = new OptionParser(Arrays.copyOfRange(data, initIndex, data.length),
						isBigEndian, type, false);
                optionParser2.decode();
                this.options = (IOptionsNameResolutionHeader) optionParser2.getOption();
            }
        }
    }

    @Override
    public IOptionsNameResolutionHeader getOptions() {
        return options;
    }

    @Override
    public IOptionsRecordNameResolution getRecords() {
        return records;
    }

}
