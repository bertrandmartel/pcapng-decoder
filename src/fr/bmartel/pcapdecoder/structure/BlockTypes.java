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
package fr.bmartel.pcapdecoder.structure;

/**
 * Block Types description 
 * 
 * @author Bertrand Martel
 *
 */
public enum BlockTypes {
	//MANDATORY : it defines the most important characteristics of the capture file
	SECTION_HEADER_BLOCK,
	//MANDATORY : it defines the most important characteristics of the interface(s) used for capturing traffic
	INTERFACE_DESCRIPTION_BLOCK,
	//OPTIONAL  : it contains a single captured packet, or a portion of it. It represents an evolution of the original Packet Block
	ENHANCES_PACKET_BLOCK,
	//OPTIONAL  : it contains a single captured packet, or a portion of it, with only a minimal set of information about it
	SIMPLE_PACKET_BLOCK,
	//OPTIONAL  : it defines the mapping from numeric addresses present in the packet dump and the canonical name counterpart
	NAME_RESOLUTION_BLOCK,
	//OPTIONAL  : it defines how to store some statistical data (e.g. packet dropped, etc) which can be useful to undestand the conditions in which the capture has been made
	INTERFACE_STATISTICS_BLOCK,
	//OBSOLETE  : it contains a single captured packet, or a portion of it. It should be considered OBSOLETE, and superseded by the Enhanced Packet Block
	PACKET_BLOCK,
	
	UNKNOWN
}
