package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CHANNELSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CHANNELS getCHANNELSSample1() {
        return new CHANNELS().id(1L).cHANNEL("cHANNEL1").dESCRIPTION("dESCRIPTION1").bIN("bIN1");
    }

    public static CHANNELS getCHANNELSSample2() {
        return new CHANNELS().id(2L).cHANNEL("cHANNEL2").dESCRIPTION("dESCRIPTION2").bIN("bIN2");
    }

    public static CHANNELS getCHANNELSRandomSampleGenerator() {
        return new CHANNELS()
            .id(longCount.incrementAndGet())
            .cHANNEL(UUID.randomUUID().toString())
            .dESCRIPTION(UUID.randomUUID().toString())
            .bIN(UUID.randomUUID().toString());
    }
}
