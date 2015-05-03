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
package fr.bmartel.pcapdecoder.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.utils.DecoderStatus;

/**
 * @mainpage  PCAP NG JAVA File parser
 *
COMMAND LINE SYNTAX : 

java -jar pcapngdecoder-1.0.jar -f test.pcapng -v

<ul>
<li>-f <file.pcapng> : input file</li>
<li>-v               : verbose, will show all section parsing content</li>
</ul>

<p>
For now following sections are available to be parsed :
<ul>
<li>Section Header</li>
<li>Interface Description</li>
<li>Interface Statistics</li>
<li>Enhanced Packet</li>
<li>Name Resolution</li>
</ul>
</p>

Specification from <a>https://www.winpcap.org/ntar/draft/PCAP-DumpFileFormat.html</a>
 */

/**
 * Start PCAP NG file decoder
 * 
 * @author Bertrand Martel <bertrandmartel92@gmail.com>
 * 
 */
public class MainParser {

	/**
	 * Start PCAP NG decoder
	 * 
	 * @param args
	 * 		
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		boolean verbose = false;
		
		if (args.length > 1) {
			
			byte[] dataFromFile= new byte[]{};
			
			if (args[0].equals("-f"))
			{
				dataFromFile = readFile(args[1]);
				if (args.length>2 && args[2].equals("-v"))
				{
					verbose=true;
				}
			}
			else if (args[0].equals("-v"))
			{
				verbose=true;
				
				if (args.length>2)
				{
					if (args[1].equals("-f"))
					{
						dataFromFile = readFile(args[2]);
					}
					else
					{
						System.err.println("Insufficient argument");
						return;
					}
				}
				else
				{
					System.err.println("Insufficient argument");
					return;
				}
			}
			else
			{
				System.err.println("Invalid argument");
				return;
			}

			if (dataFromFile.length > 0) {
				PcapDecoder pcapNgDecoder = new PcapDecoder(dataFromFile);
				int status = pcapNgDecoder.decode();
				
				if (status==DecoderStatus.SUCCESS_STATUS)
				{
					long endTime   = System.currentTimeMillis();
					long totalTime = endTime - startTime;
					System.out.println("Decoding time : " + totalTime + " millis");
					if (verbose)
					{
						DisplayAllPacket.displayResult(pcapNgDecoder);
					}
				}
				else
					System.err.println("Decoder failure");
			}
			else
			{
				System.err.println("File is empty");
			}
		}
		else
		{
			System.err.println("Insufficient argument");
		}
	}

	/**
	 * Read all bytes from file
	 * 
	 * @param path
	 *            file path
	 */
	static byte[] readFile(String path) {
		Path path2 = Paths.get(path);

		byte[] data = new byte[] {};

		try {
			data = Files.readAllBytes(path2);
		} catch (IOException e) {
			System.err.println("Error file path is incorrect");
		}

		return data;
	}

}
