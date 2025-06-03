package com.istl.app.domain;

import com.istl.app.domain.mobileapp.ChargeRange;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ChargeRangeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ChargeRange getChargeRangeSample1() {
        return new ChargeRange()
            .id(1L)
            .billerid("billerid1")
            .processingcode("processingcode1")
            .max(1L)
            .min(1L)
            .amount(1L)
            .createdby("createdby1")
            .approvedby("approvedby1")
            .createdat("createdat1")
            .approvedon("approvedon1")
            .approved(1L)
            .declined(1L)
            .declinedby("declinedby1")
            .chargeid(1L);
    }

    public static ChargeRange getChargeRangeSample2() {
        return new ChargeRange()
            .id(2L)
            .billerid("billerid2")
            .processingcode("processingcode2")
            .max(2L)
            .min(2L)
            .amount(2L)
            .createdby("createdby2")
            .approvedby("approvedby2")
            .createdat("createdat2")
            .approvedon("approvedon2")
            .approved(2L)
            .declined(2L)
            .declinedby("declinedby2")
            .chargeid(2L);
    }

    public static ChargeRange getChargeRangeRandomSampleGenerator() {
        return new ChargeRange()
            .id(longCount.incrementAndGet())
            .billerid(UUID.randomUUID().toString())
            .processingcode(UUID.randomUUID().toString())
            .max(longCount.incrementAndGet())
            .min(longCount.incrementAndGet())
            .amount(longCount.incrementAndGet())
            .createdby(UUID.randomUUID().toString())
            .approvedby(UUID.randomUUID().toString())
            .createdat(UUID.randomUUID().toString())
            .approvedon(UUID.randomUUID().toString())
            .approved(longCount.incrementAndGet())
            .declined(longCount.incrementAndGet())
            .declinedby(UUID.randomUUID().toString())
            .chargeid(longCount.incrementAndGet());
    }
}
