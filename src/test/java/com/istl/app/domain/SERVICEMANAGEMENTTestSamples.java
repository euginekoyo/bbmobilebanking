package com.istl.app.domain;

import com.istl.app.domain.mobileapp.ServiceManagement;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceManagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ServiceManagement getServiceManagementSample1() {
        return new ServiceManagement()
            .id(1L)
            .processingcode("processingcode1")
            .active("active1")
            .createdby("createdby1")
            .approved(1L)
            .approvedby("approvedby1")
            .adaptortype("adaptortype1")
            .destination("destination1")
            .telco("telco1")
            .description("description1")
            .remarks("remarks1")
            .sessionid("sessionid1")
            .reworkby("reworkby1");
    }

    public static ServiceManagement getServiceManagementSample2() {
        return new ServiceManagement()
            .id(2L)
            .processingcode("processingcode2")
            .active("active2")
            .createdby("createdby2")
            .approved(2L)
            .approvedby("approvedby2")
            .adaptortype("adaptortype2")
            .destination("destination2")
            .telco("telco2")
            .description("description2")
            .remarks("remarks2")
            .sessionid("sessionid2")
            .reworkby("reworkby2");
    }

    public static ServiceManagement getServiceManagementRandomSampleGenerator() {
        return new ServiceManagement()
            .id(longCount.incrementAndGet())
            .processingcode(UUID.randomUUID().toString())
            .active(UUID.randomUUID().toString())
            .createdby(UUID.randomUUID().toString())
            .approved(longCount.incrementAndGet())
            .approvedby(UUID.randomUUID().toString())
            .adaptortype(UUID.randomUUID().toString())
            .destination(UUID.randomUUID().toString())
            .telco(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .sessionid(UUID.randomUUID().toString())
            .reworkby(UUID.randomUUID().toString());
    }
}
