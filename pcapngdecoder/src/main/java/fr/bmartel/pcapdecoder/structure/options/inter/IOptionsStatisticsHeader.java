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
package fr.bmartel.pcapdecoder.structure.options.inter;

/**
 * Interface template for Interface Statistics section
 *
 * @author Bertrand Martel
 */
public interface IOptionsStatisticsHeader extends IOptions {

    /**
     * capture start time (timestamp resolution is defined in Interface description header check exemple)
     *
     * @return
     */
    Long getCaptureStartTime();

    /**
     * capture end time (timestamp resolution is defined in Interface description header check exemple)
     *
     * @return
     */
    Long getCaptureEndTime();

    /**
     * packet received count
     *
     * @return
     */
    Long getPacketReceivedCount();

    /**
     * packet drop count
     *
     * @return
     */
    Long getPacketDropCount();

    /**
     * packet accepted by filter count
     *
     * @return
     */
    Long getPacketAcceptedByFilterCount();

    /**
     * packet dropped by Operating system count
     *
     * @return
     */
    Long getPacketDroppedByOS();

    /**
     * packet deliver to use count
     *
     * @return
     */
    Long getPacketDeliveredToUser();

}
