package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CUSTOMERACCOUNTTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CUSTOMERACCOUNT getCUSTOMERACCOUNTSample1() {
        return new CUSTOMERACCOUNT()
            .id(1L)
            .cUSTOMERID(1L)
            .aCCOUNTNUMBER("aCCOUNTNUMBER1")
            .aCCOUNTCLASS("aCCOUNTCLASS1")
            .cUSTOMERNUMBER("cUSTOMERNUMBER1")
            .cIF("cIF1")
            .bLOCKED(1L)
            .sTOPPED(1L)
            .dORMANT(1L);
    }

    public static CUSTOMERACCOUNT getCUSTOMERACCOUNTSample2() {
        return new CUSTOMERACCOUNT()
            .id(2L)
            .cUSTOMERID(2L)
            .aCCOUNTNUMBER("aCCOUNTNUMBER2")
            .aCCOUNTCLASS("aCCOUNTCLASS2")
            .cUSTOMERNUMBER("cUSTOMERNUMBER2")
            .cIF("cIF2")
            .bLOCKED(2L)
            .sTOPPED(2L)
            .dORMANT(2L);
    }

    public static CUSTOMERACCOUNT getCUSTOMERACCOUNTRandomSampleGenerator() {
        return new CUSTOMERACCOUNT()
            .id(longCount.incrementAndGet())
            .cUSTOMERID(longCount.incrementAndGet())
            .aCCOUNTNUMBER(UUID.randomUUID().toString())
            .aCCOUNTCLASS(UUID.randomUUID().toString())
            .cUSTOMERNUMBER(UUID.randomUUID().toString())
            .cIF(UUID.randomUUID().toString())
            .bLOCKED(longCount.incrementAndGet())
            .sTOPPED(longCount.incrementAndGet())
            .dORMANT(longCount.incrementAndGet());
    }
}
