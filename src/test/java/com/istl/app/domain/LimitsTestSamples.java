package com.istl.app.domain;

import com.istl.app.domain.mobileapp.Limits;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LimitsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Limits getLimitsSample1() {
        return new Limits()
            .id(1L)
            .transactiontype("transactiontype1")
            .procode("procode1")
            .channel("channel1")
            .transactionlimit(1L)
            .dailylimit(1L)
            .registeredby("registeredby1")
            .registereddate("registereddate1")
            .approved("approved1")
            .approvedby("approvedby1")
            .approveddate("approveddate1")
            .updatedby("updatedby1")
            .updateddate("updateddate1")
            .rework(1L)
            .reworkby("reworkby1")
            .sessionid("sessionid1");
    }

    public static Limits getLimitsSample2() {
        return new Limits()
            .id(2L)
            .transactiontype("transactiontype2")
            .procode("procode2")
            .channel("channel2")
            .transactionlimit(2L)
            .dailylimit(2L)
            .registeredby("registeredby2")
            .registereddate("registereddate2")
            .approved("approved2")
            .approvedby("approvedby2")
            .approveddate("approveddate2")
            .updatedby("updatedby2")
            .updateddate("updateddate2")
            .rework(2L)
            .reworkby("reworkby2")
            .sessionid("sessionid2");
    }

    public static Limits getLimitsRandomSampleGenerator() {
        return new Limits()
            .id(longCount.incrementAndGet())
            .transactiontype(UUID.randomUUID().toString())
            .procode(UUID.randomUUID().toString())
            .channel(UUID.randomUUID().toString())
            .transactionlimit(longCount.incrementAndGet())
            .dailylimit(longCount.incrementAndGet())
            .registeredby(UUID.randomUUID().toString())
            .registereddate(UUID.randomUUID().toString())
            .approved(UUID.randomUUID().toString())
            .approvedby(UUID.randomUUID().toString())
            .approveddate(UUID.randomUUID().toString())
            .updatedby(UUID.randomUUID().toString())
            .updateddate(UUID.randomUUID().toString())
            .rework(longCount.incrementAndGet())
            .reworkby(UUID.randomUUID().toString())
            .sessionid(UUID.randomUUID().toString());
    }
}
