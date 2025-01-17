package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RANGETestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static RANGE getRANGESample1() {
        return new RANGE().id(1L).rANGEFROM(1L).rANGETO(1L).tXNTYPE("tXNTYPE1").tXNCODE("tXNCODE1").cHARGEID(1L).cHANNEL("cHANNEL1");
    }

    public static RANGE getRANGESample2() {
        return new RANGE().id(2L).rANGEFROM(2L).rANGETO(2L).tXNTYPE("tXNTYPE2").tXNCODE("tXNCODE2").cHARGEID(2L).cHANNEL("cHANNEL2");
    }

    public static RANGE getRANGERandomSampleGenerator() {
        return new RANGE()
            .id(longCount.incrementAndGet())
            .rANGEFROM(longCount.incrementAndGet())
            .rANGETO(longCount.incrementAndGet())
            .tXNTYPE(UUID.randomUUID().toString())
            .tXNCODE(UUID.randomUUID().toString())
            .cHARGEID(longCount.incrementAndGet())
            .cHANNEL(UUID.randomUUID().toString());
    }
}
