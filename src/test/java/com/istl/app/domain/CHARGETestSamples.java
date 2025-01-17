package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CHARGETestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CHARGE getCHARGESample1() {
        return new CHARGE()
            .id(1L)
            .tXNTYPE("tXNTYPE1")
            .fEEMODE(1L)
            .aMOUNT(1L)
            .cREATEDBY("cREATEDBY1")
            .aPPROVED("aPPROVED1")
            .aPPROVEDBY("aPPROVEDBY1")
            .cHANNEL("cHANNEL1")
            .tXNCODE(1L)
            .dESCRIPTION("dESCRIPTION1");
    }

    public static CHARGE getCHARGESample2() {
        return new CHARGE()
            .id(2L)
            .tXNTYPE("tXNTYPE2")
            .fEEMODE(2L)
            .aMOUNT(2L)
            .cREATEDBY("cREATEDBY2")
            .aPPROVED("aPPROVED2")
            .aPPROVEDBY("aPPROVEDBY2")
            .cHANNEL("cHANNEL2")
            .tXNCODE(2L)
            .dESCRIPTION("dESCRIPTION2");
    }

    public static CHARGE getCHARGERandomSampleGenerator() {
        return new CHARGE()
            .id(longCount.incrementAndGet())
            .tXNTYPE(UUID.randomUUID().toString())
            .fEEMODE(longCount.incrementAndGet())
            .aMOUNT(longCount.incrementAndGet())
            .cREATEDBY(UUID.randomUUID().toString())
            .aPPROVED(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .cHANNEL(UUID.randomUUID().toString())
            .tXNCODE(longCount.incrementAndGet())
            .dESCRIPTION(UUID.randomUUID().toString());
    }
}
