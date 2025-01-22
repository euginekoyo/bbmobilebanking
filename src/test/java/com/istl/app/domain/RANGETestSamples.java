package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RangeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Range getRangeSample1() {
        return new Range().id(1L).rangefrom(1L).rangeto(1L).txntype("txntype1").txncode("txncode1").chargeid(1L).channel("channel1");
    }

    public static Range getRangeSample2() {
        return new Range().id(2L).rangefrom(2L).rangeto(2L).txntype("txntype2").txncode("txncode2").chargeid(2L).channel("channel2");
    }

    public static Range getRangeRandomSampleGenerator() {
        return new Range()
            .id(longCount.incrementAndGet())
            .rangefrom(longCount.incrementAndGet())
            .rangeto(longCount.incrementAndGet())
            .txntype(UUID.randomUUID().toString())
            .txncode(UUID.randomUUID().toString())
            .chargeid(longCount.incrementAndGet())
            .channel(UUID.randomUUID().toString());
    }
}
