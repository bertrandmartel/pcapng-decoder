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
package fr.bmartel.pcapdecoder.constant;

import java.util.HashMap;

import fr.bmartel.pcapdecoder.structure.BlockTypes;

/**
 * Block header for Pcap Ng files
 * 
 * @author Bertrand Martel
 *
 */
public class HeaderBlocks {

	public final static HashMap<String, byte[]> SECTION_TYPE_LIST = new HashMap<String, byte[]>();

	public final static byte[] SECTION_HEADER_BLOCK = new byte[] { 0x0a, 0x0d,
			0x0d, 0x0a };

	public final static byte[] INTERFACE_DESCRIPTION_BLOCK = new byte[] { 0x00,
			0x00, 0x00, 0x01 };

	public final static byte[] ENHANCED_PACKET_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x06 };

	public final static byte[] SIMPLE_PACKET_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x03 };

	public final static byte[] PACKET_BLOCK = new byte[] { 0x00, 0x00, 0x00,
			0x02 };

	public final static byte[] NAME_RESOLUTION_BLOCK = new byte[] { 0x00, 0x00,
			0x00, 0x04 };

	public final static byte[] INTERFACE_STATISTICS_BLOCK = new byte[] { 0x00,
			0x00, 0x00, 0x05 };

	static {
		SECTION_TYPE_LIST.put(BlockTypes.SECTION_HEADER_BLOCK.toString(),
				SECTION_HEADER_BLOCK);
		SECTION_TYPE_LIST.put(
				BlockTypes.INTERFACE_DESCRIPTION_BLOCK.toString(),
				INTERFACE_DESCRIPTION_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.ENHANCES_PACKET_BLOCK.toString(),
				ENHANCED_PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.SIMPLE_PACKET_BLOCK.toString(),
				SIMPLE_PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.PACKET_BLOCK.toString(), PACKET_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.NAME_RESOLUTION_BLOCK.toString(),
				NAME_RESOLUTION_BLOCK);
		SECTION_TYPE_LIST.put(BlockTypes.INTERFACE_STATISTICS_BLOCK.toString(),
				INTERFACE_STATISTICS_BLOCK);
	}

}
