package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BranchesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Branches getBranchesSample1() {
        return new Branches()
            .id(1L)
            .branchname("branchname1")
            .branchcode("branchcode1")
            .approved(1L)
            .email("email1")
            .address("address1")
            .phone("phone1")
            .location("location1")
            .contactperson("contactperson1")
            .remarks("remarks1")
            .createdby("createdby1")
            .approvedby("approvedby1")
            .approvedon("approvedon1")
            .checkerremarks("checkerremarks1")
            .deletedby("deletedby1")
            .deleteremarks("deleteremarks1")
            .deleted(1L)
            .declined(1L)
            .declineddon("declineddon1")
            .declinedby("declinedby1")
            .sessionid("sessionid1")
            .reworked(1L)
            .reworkedby("reworkedby1")
            .district("district1")
            .region("region1")
            .regionname("regionname1")
            .reporting(1L);
    }

    public static Branches getBranchesSample2() {
        return new Branches()
            .id(2L)
            .branchname("branchname2")
            .branchcode("branchcode2")
            .approved(2L)
            .email("email2")
            .address("address2")
            .phone("phone2")
            .location("location2")
            .contactperson("contactperson2")
            .remarks("remarks2")
            .createdby("createdby2")
            .approvedby("approvedby2")
            .approvedon("approvedon2")
            .checkerremarks("checkerremarks2")
            .deletedby("deletedby2")
            .deleteremarks("deleteremarks2")
            .deleted(2L)
            .declined(2L)
            .declineddon("declineddon2")
            .declinedby("declinedby2")
            .sessionid("sessionid2")
            .reworked(2L)
            .reworkedby("reworkedby2")
            .district("district2")
            .region("region2")
            .regionname("regionname2")
            .reporting(2L);
    }

    public static Branches getBranchesRandomSampleGenerator() {
        return new Branches()
            .id(longCount.incrementAndGet())
            .branchname(UUID.randomUUID().toString())
            .branchcode(UUID.randomUUID().toString())
            .approved(longCount.incrementAndGet())
            .email(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .phone(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .contactperson(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .createdby(UUID.randomUUID().toString())
            .approvedby(UUID.randomUUID().toString())
            .approvedon(UUID.randomUUID().toString())
            .checkerremarks(UUID.randomUUID().toString())
            .deletedby(UUID.randomUUID().toString())
            .deleteremarks(UUID.randomUUID().toString())
            .deleted(longCount.incrementAndGet())
            .declined(longCount.incrementAndGet())
            .declineddon(UUID.randomUUID().toString())
            .declinedby(UUID.randomUUID().toString())
            .sessionid(UUID.randomUUID().toString())
            .reworked(longCount.incrementAndGet())
            .reworkedby(UUID.randomUUID().toString())
            .district(UUID.randomUUID().toString())
            .region(UUID.randomUUID().toString())
            .regionname(UUID.randomUUID().toString())
            .reporting(longCount.incrementAndGet());
    }
}
