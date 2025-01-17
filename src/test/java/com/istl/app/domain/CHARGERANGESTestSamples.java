package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CHARGERANGESTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CHARGERANGES getCHARGERANGESSample1() {
        return new CHARGERANGES()
            .id(1L)
            .bILLERID("bILLERID1")
            .pROCESSINGCODE("pROCESSINGCODE1")
            .mAX(1L)
            .mIN(1L)
            .aMOUNT(1L)
            .cREATEDBY("cREATEDBY1")
            .aPPROVEDBY("aPPROVEDBY1")
            .cREATEDAT("cREATEDAT1")
            .aPPROVEDON("aPPROVEDON1")
            .aPPROVED(1L)
            .dECLINED(1L)
            .dECLINEDBY("dECLINEDBY1")
            .cHARGEID(1L);
    }

    public static CHARGERANGES getCHARGERANGESSample2() {
        return new CHARGERANGES()
            .id(2L)
            .bILLERID("bILLERID2")
            .pROCESSINGCODE("pROCESSINGCODE2")
            .mAX(2L)
            .mIN(2L)
            .aMOUNT(2L)
            .cREATEDBY("cREATEDBY2")
            .aPPROVEDBY("aPPROVEDBY2")
            .cREATEDAT("cREATEDAT2")
            .aPPROVEDON("aPPROVEDON2")
            .aPPROVED(2L)
            .dECLINED(2L)
            .dECLINEDBY("dECLINEDBY2")
            .cHARGEID(2L);
    }

    public static CHARGERANGES getCHARGERANGESRandomSampleGenerator() {
        return new CHARGERANGES()
            .id(longCount.incrementAndGet())
            .bILLERID(UUID.randomUUID().toString())
            .pROCESSINGCODE(UUID.randomUUID().toString())
            .mAX(longCount.incrementAndGet())
            .mIN(longCount.incrementAndGet())
            .aMOUNT(longCount.incrementAndGet())
            .cREATEDBY(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .cREATEDAT(UUID.randomUUID().toString())
            .aPPROVEDON(UUID.randomUUID().toString())
            .aPPROVED(longCount.incrementAndGet())
            .dECLINED(longCount.incrementAndGet())
            .dECLINEDBY(UUID.randomUUID().toString())
            .cHARGEID(longCount.incrementAndGet());
    }
}
