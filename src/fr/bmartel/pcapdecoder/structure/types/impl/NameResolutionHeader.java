package fr.bmartel.pcapdecoder.structure.types.impl;

import java.util.Arrays;

import fr.bmartel.pcapdecoder.structure.BlockTypes;
import fr.bmartel.pcapdecoder.structure.options.OptionParser;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsNameResolutionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.INameResolutionBlock;
/**
 * 
 * Implementation for Name Resolution Block parsing in Pcap ng decoder
 * 
 * NAME RESOLUTION BLOCK
 * 
 *     0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
   +---------------------------------------------------------------+
 0 |                    Block Type = 0x00000004                    |
   +---------------------------------------------------------------+
 4 |                      Block Total Length                       |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 8 |      Record Type              |         Record Length         |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
12 /                       Record Value                            /
   /             variable length, aligned to 32 bits               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   .                                                               .
   .                  . . . other records . . .                    .
   .                                                               .
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |  Record Type == end_of_recs   |  Record Length == 00          |
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   /                                                               /
   /                      Options (variable)                       /
   /                                                               /
   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   |                      Block Total Length                       |
   +---------------------------------------------------------------+

 */ 
public class NameResolutionHeader implements INameResolutionBlock,IPcapngType {

	private IOptionsRecordNameResolution records = null;
	
	private IOptionsNameResolutionHeader options = null;
	
	public NameResolutionHeader(byte[] data,boolean isBigEndian,BlockTypes type) {
		
		if (data.length>0)
		{
			
			//parse set of options
			OptionParser optionParser = new OptionParser(Arrays.copyOfRange(data,0,data.length), isBigEndian,type,true);
			int initIndex = optionParser.decode();
			this.records=(IOptionsRecordNameResolution) optionParser.getOption();
			
			if ((data.length-initIndex)>0)
			{
				//parse set of options
				OptionParser optionParser2 = new OptionParser(Arrays.copyOfRange(data, initIndex,data.length), isBigEndian,type,false);
				optionParser2.decode();
				this.options=(IOptionsNameResolutionHeader) optionParser.getOption();
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
