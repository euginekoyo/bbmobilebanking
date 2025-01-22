package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BillersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Billers getBillersSample1() {
        return new Billers()
            .id(1L)
            .billerid("billerid1")
            .description("description1")
            .billercollectionaccount("billercollectionaccount1")
            .createdby("createdby1")
            .approved(1L)
            .approvedby("approvedby1")
            .chargableproductid("chargableproductid1")
            .nonchargableproductid("nonchargableproductid1")
            .usdbillercollectionaccount("usdbillercollectionaccount1")
            .enableduplicatecheck(1L)
            .remarks("remarks1")
            .sessionid("sessionid1")
            .reworkby("reworkby1")
            .status(1L)
            .active(1L)
            .rework(1L);
    }

    public static Billers getBillersSample2() {
        return new Billers()
            .id(2L)
            .billerid("billerid2")
            .description("description2")
            .billercollectionaccount("billercollectionaccount2")
            .createdby("createdby2")
            .approved(2L)
            .approvedby("approvedby2")
            .chargableproductid("chargableproductid2")
            .nonchargableproductid("nonchargableproductid2")
            .usdbillercollectionaccount("usdbillercollectionaccount2")
            .enableduplicatecheck(2L)
            .remarks("remarks2")
            .sessionid("sessionid2")
            .reworkby("reworkby2")
            .status(2L)
            .active(2L)
            .rework(2L);
    }

    public static Billers getBillersRandomSampleGenerator() {
        return new Billers()
            .id(longCount.incrementAndGet())
            .billerid(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .billercollectionaccount(UUID.randomUUID().toString())
            .createdby(UUID.randomUUID().toString())
            .approved(longCount.incrementAndGet())
            .approvedby(UUID.randomUUID().toString())
            .chargableproductid(UUID.randomUUID().toString())
            .nonchargableproductid(UUID.randomUUID().toString())
            .usdbillercollectionaccount(UUID.randomUUID().toString())
            .enableduplicatecheck(longCount.incrementAndGet())
            .remarks(UUID.randomUUID().toString())
            .sessionid(UUID.randomUUID().toString())
            .reworkby(UUID.randomUUID().toString())
            .status(longCount.incrementAndGet())
            .active(longCount.incrementAndGet())
            .rework(longCount.incrementAndGet());
    }
}
