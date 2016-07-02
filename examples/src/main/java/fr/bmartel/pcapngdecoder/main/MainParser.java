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
package fr.bmartel.pcapngdecoder.main;

import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.utils.DecoderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @mainpage PCAP NG JAVA File parser
 * <p/>
 * COMMAND LINE SYNTAX :
 * <p/>
 * java -jar pcapngdecoder-1.0.jar -f test.pcapng -v
 * <p/>
 * <ul>
 * <li>-f <file.pcapng> : input file</li>
 * <li>-v               : verbose, will show all section parsing content</li>
 * </ul>
 * <p/>
 * <p>
 * For now following sections are available to be parsed :
 * <ul>
 * <li>Section Header</li>
 * <li>Interface Description</li>
 * <li>Interface Statistics</li>
 * <li>Enhanced Packet</li>
 * <li>Name Resolution</li>
 * </ul>
 * </p>
 * <p/>
 * Specification from <a>https://www.winpcap.org/ntar/draft/PCAP-DumpFileFormat.html</a>
 */

/**
 * Start PCAP NG file decoder
 *
 * @author Bertrand Martel <bertrandmartel92@gmail.com>
 */
public class MainParser {

    /**
     * logger.
     */
    private final static Logger LOGGER = LogManager.getLogger(MainParser.class.getName());

    /**
     * Start PCAP NG decoder
     *
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        boolean verbose = false;

        if (args.length > 1) {

            String inputFile;

            if (args[0].equals("-f")) {
                inputFile = args[1];
                if (args.length > 2 && args[2].equals("-v")) {
                    verbose = true;
                }
            } else if (args[0].equals("-v")) {
                verbose = true;

                if (args.length > 2) {
                    if (args[1].equals("-f")) {
                        inputFile = args[2];
                    } else {
                        LOGGER.error("Insufficient argument");
                        return;
                    }
                } else {
                    LOGGER.error("Insufficient argument");
                    return;
                }
            } else {
                LOGGER.error("Invalid argument");
                return;
            }

            if (inputFile != null) {

                PcapDecoder pcapNgDecoder = new PcapDecoder(inputFile);
                int status = pcapNgDecoder.decode();

                if (status == DecoderStatus.SUCCESS_STATUS) {

                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;

                    LOGGER.debug("Decoding time : " + totalTime + " millis");

                    if (verbose) {
                        DisplayAllPacket.displayResult(pcapNgDecoder, LOGGER);
                    }
                } else
                    LOGGER.error("Decoder failure");
            } else {
                LOGGER.error("File is invalid");
            }
        } else {
            LOGGER.error("Insufficient argument");
        }
    }
}
