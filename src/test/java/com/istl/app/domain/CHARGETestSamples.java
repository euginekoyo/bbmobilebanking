package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ChargeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Charge getChargeSample1() {
        return new Charge()
            .id(1L)
            .txntype("txntype1")
            .feemode(1L)
            .amount(1L)
            .createdby("createdby1")
            .approved("approved1")
            .approvedby("approvedby1")
            .channel("channel1")
            .txncode(1L)
            .description("description1");
    }

    public static Charge getChargeSample2() {
        return new Charge()
            .id(2L)
            .txntype("txntype2")
            .feemode(2L)
            .amount(2L)
            .createdby("createdby2")
            .approved("approved2")
            .approvedby("approvedby2")
            .channel("channel2")
            .txncode(2L)
            .description("description2");
    }

    public static Charge getChargeRandomSampleGenerator() {
        return new Charge()
            .id(longCount.incrementAndGet())
            .txntype(UUID.randomUUID().toString())
            .feemode(longCount.incrementAndGet())
            .amount(longCount.incrementAndGet())
            .createdby(UUID.randomUUID().toString())
            .approved(UUID.randomUUID().toString())
            .approvedby(UUID.randomUUID().toString())
            .channel(UUID.randomUUID().toString())
            .txncode(longCount.incrementAndGet())
            .description(UUID.randomUUID().toString());
    }
}
