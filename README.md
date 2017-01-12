# PCAPNG Decoder Library #

http://bertrandmartel.github.io/pcapng-decoder/

[![Build Status](https://travis-ci.org/bertrandmartel/pcapng-decoder.svg?branch=master)](https://travis-ci.org/bertrandmartel/pcapng-decoder)
[ ![Download](https://api.bintray.com/packages/bertrandmartel/maven/pcapng-parser/images/download.svg) ](https://bintray.com/bertrandmartel/maven/pcapng-parser/_latestVersion)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1188df87489749a48978a5d953662d18)](https://www.codacy.com/app/bertrandmartel/pcapng-decoder?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bertrandmartel/pcapng-decoder&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/fr.bmartel/pcapngdecoder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fr.bmartel/pcapngdecoder)
[![Javadoc](http://javadoc-badge.appspot.com/fr.bmartel/pcapngdecoder.svg?label=javadoc)](http://javadoc-badge.appspot.com/fr.bmartel/pcapngdecoder)
[![License](http://img.shields.io/:license-mit-blue.svg)](LICENSE.md)

Java PCAPNG file parser library

<a href="CHANGELOG.md">show changelog</a>

## Include in your project

* from gradle 

```
compile 'fr.bmartel:pcapngdecoder:1.2'
```

* from release archive

https://github.com/bertrandmartel/pcapng-decoder/releases

## Run example

```
./gradlew run -PappArgs="['-f', 'path_to_file/pcapfile/exemple.pcapng'  , '-v' ]"
```

|  args            | description                                        |
|------------------|----------------------------------------------------|
| -f <file.pcapng> | input file                                         |
| -v               | verbose, will show all section parsing content     |

Example source code can be found <a href="https://github.com/bertrandmartel/pcapng-decoder/tree/master/examples">here</a>

## How to use ?

### Decode

* decode from n input file :
```
PcapDecoder decoder = new PcapDecoder("path/to/file.pcapng");
decoder.decode();
```

* decode from a byte array :
```
byte[] pcapBa = getPcapBa();
PcapDecoder decoder = new PcapDecoder(pcapBa);
decoder.decode();
```

### Data access

Pcap section list can be accessed via `getSectionList()` :

```
ArrayList<IPcapngType> sectionList = decoder.getSectionList()
```

All section type inherit from `IPcapngType`, use reflection to access each type :

```
for (int i = 0; i < sectionList.size(); i++) {

    if (sectionList.get(i) instanceof ISectionHeaderBlock) {

        ISectionHeaderBlock section = (ISectionHeaderBlock) sectionList.get(i);

        //do what you want with Section Header Block frame type

    } else if (sectionList.get(i) instanceof IDescriptionBlock) {
        
        IDescriptionBlock section = (IDescriptionBlock) sectionList.get(i);

        //do what you want with Description Block frame type 

    } else if (sectionList.get(i) instanceof IEnhancedPacketBLock) {

        IEnhancedPacketBLock section = (IEnhancedPacketBLock) sectionList.get(i);

		//do what you want with Enhanced Packet Block frame type 

    } else if (sectionList.get(i) instanceof IStatisticsBlock) {

        IStatisticsBlock section = (IStatisticsBlock) sectionList.get(i);

        //do what you want with Statistics Block frame type 

    } else if (sectionList.get(i) instanceof INameResolutionBlock) {

        INameResolutionBlock section = (INameResolutionBlock) sectionList.get(i);

        //do what you want with Name Resolution Block frame type
    }
}
```

<b>Note</b> : packet data in Enhanced Packet Block is left in packet source endianness

## JavaDoc

http://javadoc-badge.appspot.com/fr.bmartel/pcapngdecoder

## Example output

```
##########################################################
SECTION HEADER BLOCK
Major version      : 0
Minor version      : 1
OS                 : Linux 3.8.0-19-generic
user application   : Dumpcap 1.10.2 (SVN Rev 51934 from /trunk-1.10)
##########################################################
SECTION INTERFACE DESCRIPTION BLOCK
Link type             : LINKTYPE_IEEE802_11_RADIO
Snap len              : 65535
interface name        : wlan0
timestamp resolution  : 6
interface OS name     : Linux 3.8.0-19-generic
##########################################################
SECTION ENHANCED PACKET BLOCK
interface id             : 0
timestamp in millis      : Sat Apr 18 12:13:41 CEST 2015
captured length          : 185
packet length            : 185
packet data              : 00 | 00 | 12 | 00 | 2E | 48 | 00 | 00 | 10 | 02 | A3 | 09 | A0 | 00 | C2 | 07 | 00 | 00 | 80 | 00 | 00 | 00 | FF | FF | FF | FF | FF | FF | 00 | 24 | D4 | 6B | 0C | 5D | 00 | 24 | D4 | 6B | 0C | 5D | 00 | E5 | 60 | 01 | 25 | DE | 32 | 03 | 00 | 00 | 60 | 00 | 01 | 04 | 00 | 08 | 46 | 72 | 65 | 65 | 57 | 69 | 66 | 69 | 01 | 08 | 82 | 84 | 8B | 96 | 2C | 0C | 12 | 18 | 03 | 01 | 0C | 05 | 04 | 00 | 02 | 00 | 00 | 2A | 01 | 04 | 32 | 05 | 24 | 30 | 48 | 60 | 6C | 2D | 1A | 6C | 00 | 03 | FF | FF | FF | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 3D | 16 | 0C | 00 | 13 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 7F | 08 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 40 | DD | 18 | 00 | 50 | F2 | 02 | 01 | 01 | 00 | 00 | 03 | A4 | 00 | 00 | 27 | A4 | 00 | 00 | 42 | 43 | 5E | 00 | 62 | 32 | 2F | 00 | A3 | 26 | 13 | 07
##########################################################
SECTION INTERFACE STATISTICS BLOCK
interface id             : 0
timestamp in millis      : Sat Apr 18 12:16:43 CEST 2015
capture start time       : Sat Apr 18 12:13:41 CEST 2015
capture end time         : Sat Apr 18 12:16:43 CEST 2015
packet received count    : 9493
packet drop count        : 0
##########################################################
```

## Compatibility

JRE 1.7 compliant

## Build

Gradle using IntelliJ IDEA or Eclipse

## Specifications 

https://www.winpcap.org/ntar/draft/PCAP-DumpFileFormat.html

## License

The MIT License (MIT) Copyright (c) 2015-2016 Bertrand Martel
