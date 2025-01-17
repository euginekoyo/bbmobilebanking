package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MESSAGETEMPLATESTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MESSAGETEMPLATES getMESSAGETEMPLATESSample1() {
        return new MESSAGETEMPLATES()
            .id(1L)
            .mESSAGETYPE("mESSAGETYPE1")
            .dESCRIPTION("dESCRIPTION1")
            .mESSAGEENGLISH("mESSAGEENGLISH1")
            .mESSAGESOMALI("mESSAGESOMALI1");
    }

    public static MESSAGETEMPLATES getMESSAGETEMPLATESSample2() {
        return new MESSAGETEMPLATES()
            .id(2L)
            .mESSAGETYPE("mESSAGETYPE2")
            .dESCRIPTION("dESCRIPTION2")
            .mESSAGEENGLISH("mESSAGEENGLISH2")
            .mESSAGESOMALI("mESSAGESOMALI2");
    }

    public static MESSAGETEMPLATES getMESSAGETEMPLATESRandomSampleGenerator() {
        return new MESSAGETEMPLATES()
            .id(longCount.incrementAndGet())
            .mESSAGETYPE(UUID.randomUUID().toString())
            .dESCRIPTION(UUID.randomUUID().toString())
            .mESSAGEENGLISH(UUID.randomUUID().toString())
            .mESSAGESOMALI(UUID.randomUUID().toString());
    }
}
