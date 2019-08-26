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
package fr.bmartel.pcapdecoder;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import fr.bmartel.pcapdecoder.constant.HeaderBlocks;
import fr.bmartel.pcapdecoder.constant.MagicNumber;
import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.PcapNgStructureParser;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.utils.DecoderStatus;
import fr.bmartel.pcapdecoder.utils.Endianess;
import fr.bmartel.pcapdecoder.utils.UtilFunctions;

/**
 * PCAP NG decoder
 *
 * @author Bertrand Martel
 */
public class PcapDecoder {

	/**
	 * data to parse
	 */
	private InputStream data = null;

	private boolean isBigEndian = true;

	private ArrayList<IPcapngType> pcapSectionList = new ArrayList<IPcapngType>();

	/**
	 * build Pcap Decoder with a new data to parse (from Pcap Ng file)
	 *
	 * @param data
	 */
	public PcapDecoder(InputStream data) {
		this.data = data;
	}

	/**
	 * build Pcap Decoder with a new data to parse (from Pcap Ng file)
	 *
	 * @param data
	 */
	public PcapDecoder(byte[] data) {
		this.data = new ByteArrayInputStream(data);
	}

	/**
	 * Build Pcap Decoder from an absolute file path
	 *
	 * @param inputFilePath absolute file path
	 */
	public PcapDecoder(String inputFilePath) {
		if (inputFilePath != null) {
			try {
				this.data = new FileInputStream(inputFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Detect endianess with magic number in section header block : will be
	 * 0x1A2B3C4D in big endian and 0x4D3C2B1A for little endian
	 *
	 * @param magicNumber
	 * @return
	 */
	private byte detectEndianness(byte[] magicNumber) {
		if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_BIG_ENDIAN, magicNumber, isBigEndian)) {
			return Endianess.BIG_ENDIAN;
		} else if (UtilFunctions.compare32Bytes(MagicNumber.MAGIC_NUMBER_LITTLE_ENDIAN, magicNumber, isBigEndian)) {
			return Endianess.LITTLE_ENDIAN;
		} else {
			return Endianess.NO_ENDIANESS;
		}
	}

	private int parseBlockLength(byte[] length, boolean isBigEndian) {
		if (isBigEndian) {
			int blockLength = (((length[0] << 32) & 0xFF) + ((length[1] << 16) & 0xFF) + ((length[2] << 8) & 0xFF)
					+ ((length[3] << 0) & 0xFF));
			return blockLength;
		} else {
			int blockLength = (((length[0] << 0) & 0xFF) + ((length[1] << 8) & 0xFF00) + ((length[2] << 16) & 0xFF0000)
					+ ((length[3] << 32) & 0xFF000000));
			return blockLength;
		}
	}

	/**
	 * Parse data block of all type of section and return current index to be read
	 * next
	 *
	 * @param data
	 * @return DecoderStatus
	 */
	private void parseDataBlock(BlockTypes type) throws IOException {
		if (data.available() < 4) {
			throw new IOException("Inputstream needs at least 4 bytes!");
		}

		// first four bytes for the blocktype are already read
		byte[] blockLengthBytes = new byte[4];
		data.read(blockLengthBytes);
		int blockLength = parseBlockLength(blockLengthBytes, isBigEndian);

		if (data.available() < blockLength - 8) {
			throw new IOException("Inputstream needs at least " + (blockLength - 8) + " bytes!");
		}

		byte[] dataBlock = new byte[blockLength - 8];
		data.read(dataBlock);
		if (type == BlockTypes.SECTION_HEADER_BLOCK) {
			byte[] endianessBytes = Arrays.copyOfRange(dataBlock, 0, 4);
			byte endianess = detectEndianness(endianessBytes);
			if (endianess == Endianess.BIG_ENDIAN) {
				isBigEndian = true;
			} else if (endianess == Endianess.LITTLE_ENDIAN) {
				isBigEndian = false;
			}
			dataBlock = Arrays.copyOfRange(dataBlock, 4, dataBlock.length);
		}
		PcapNgStructureParser structure = new PcapNgStructureParser(type, dataBlock, isBigEndian);
		structure.decode();
		pcapSectionList.add(structure.getPcapStruct());
	}

	/**
	 * Decode a specific section type from HeaderBLocks class
	 */
	public void processSectionType(BlockTypes type, byte[] headerBytes) throws IOException {
		if (UtilFunctions.compare32Bytes(HeaderBlocks.SECTION_TYPE_LIST.get(type.toString()), headerBytes,
				isBigEndian)) {
			parseDataBlock(type);
		}
	}
	
	/**
	 * Decode a single block
	 */
	public byte decodeNextBlock() {
		if (data == null) {
			return DecoderStatus.FAILED_STATUS;
		}
		
		try {
			if (data.available() < 4) {
				throw new IOException("Inputstream needs at least 4 bytes!");
			}

			byte[] headerBytes = new byte[4];
			data.read(headerBytes);
			
			for(BlockTypes type : BlockTypes.values()) {
				if (UtilFunctions.compare32Bytes(HeaderBlocks.SECTION_TYPE_LIST.get(type.toString()), headerBytes,
						isBigEndian)) {
					parseDataBlock(type);
					return DecoderStatus.SUCCESS_STATUS;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return DecoderStatus.FAILED_STATUS;
		}
		return DecoderStatus.FAILED_STATUS;
	}

	/**
	 * Decode
	 */
	public byte decode() {
		if (data == null) {
			return DecoderStatus.FAILED_STATUS;
		}

		try {
			while (data.available() > 0) {
				decodeNextBlock();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return DecoderStatus.FAILED_STATUS;
		}
		return DecoderStatus.SUCCESS_STATUS;
	}

	public ArrayList<IPcapngType> getSectionList() {
		return pcapSectionList;
	}
}
