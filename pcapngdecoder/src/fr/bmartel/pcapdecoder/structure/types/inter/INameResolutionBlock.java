package fr.bmartel.pcapdecoder.structure.types.inter;

import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsNameResolutionHeader;
import fr.bmartel.pcapdecoder.structure.options.inter.IOptionsRecordNameResolution;

/**
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
   
 * @author Bertrand Martel
 *
 */
public interface INameResolutionBlock {
	
	public IOptionsRecordNameResolution getRecords();
	
	public IOptionsNameResolutionHeader  getOptions();
}
