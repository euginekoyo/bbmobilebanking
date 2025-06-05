package com.istl.app.domain;

import com.istl.app.domain.mobileapp.PinResetHistory;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PinResetHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PinResetHistory getPinResetHistorySample1() {
        return new PinResetHistory()
            .id(1L)
            .phonenumber("phonenumber1")
            .customername("customername1")
            .pinblockedon("pinblockedon1")
            .pinblockremarks("pinblockremarks1")
            .pinresetby("pinresetby1")
            .pinreseton("pinreseton1")
            .pinresetapprovedby("pinresetapprovedby1")
            .pinresetapprovedon("pinresetapprovedon1")
            .pinresetremarks("pinresetremarks1")
            .branchcode("branchcode1");
    }

    public static PinResetHistory getPinResetHistorySample2() {
        return new PinResetHistory()
            .id(2L)
            .phonenumber("phonenumber2")
            .customername("customername2")
            .pinblockedon("pinblockedon2")
            .pinblockremarks("pinblockremarks2")
            .pinresetby("pinresetby2")
            .pinreseton("pinreseton2")
            .pinresetapprovedby("pinresetapprovedby2")
            .pinresetapprovedon("pinresetapprovedon2")
            .pinresetremarks("pinresetremarks2")
            .branchcode("branchcode2");
    }

    public static PinResetHistory getPinResetHistoryRandomSampleGenerator() {
        return new PinResetHistory()
            .id(longCount.incrementAndGet())
            .phonenumber(UUID.randomUUID().toString())
            .customername(UUID.randomUUID().toString())
            .pinblockedon(UUID.randomUUID().toString())
            .pinblockremarks(UUID.randomUUID().toString())
            .pinresetby(UUID.randomUUID().toString())
            .pinreseton(UUID.randomUUID().toString())
            .pinresetapprovedby(UUID.randomUUID().toString())
            .pinresetapprovedon(UUID.randomUUID().toString())
            .pinresetremarks(UUID.randomUUID().toString())
            .branchcode(UUID.randomUUID().toString());
    }
}
