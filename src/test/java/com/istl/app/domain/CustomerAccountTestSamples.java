package com.istl.app.domain;

import com.istl.app.domain.mobileapp.CustomerAccount;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerAccountTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CustomerAccount getCustomerAccountSample1() {
        return new CustomerAccount()
            .id(1L)
            .customerid(1L)
            .accountnumber("accountnumber1")
            .accountclass("accountclass1")
            .customernumber("customernumber1")
            .cif("cif1")
            .blocked(1L)
            .stopped(1L)
            .dormant(1L);
    }

    public static CustomerAccount getCustomerAccountSample2() {
        return new CustomerAccount()
            .id(2L)
            .customerid(2L)
            .accountnumber("accountnumber2")
            .accountclass("accountclass2")
            .customernumber("customernumber2")
            .cif("cif2")
            .blocked(2L)
            .stopped(2L)
            .dormant(2L);
    }

    public static CustomerAccount getCustomerAccountRandomSampleGenerator() {
        return new CustomerAccount()
            .id(longCount.incrementAndGet())
            .customerid(longCount.incrementAndGet())
            .accountnumber(UUID.randomUUID().toString())
            .accountclass(UUID.randomUUID().toString())
            .customernumber(UUID.randomUUID().toString())
            .cif(UUID.randomUUID().toString())
            .blocked(longCount.incrementAndGet())
            .stopped(longCount.incrementAndGet())
            .dormant(longCount.incrementAndGet());
    }
}
