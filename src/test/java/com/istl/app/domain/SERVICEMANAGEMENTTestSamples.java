package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SERVICEMANAGEMENTTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SERVICEMANAGEMENT getSERVICEMANAGEMENTSample1() {
        return new SERVICEMANAGEMENT()
            .id(1L)
            .pROCESSINGCODE("pROCESSINGCODE1")
            .aCTIVE("aCTIVE1")
            .cREATEDBY("cREATEDBY1")
            .aPPROVED(1L)
            .aPPROVEDBY("aPPROVEDBY1")
            .aDAPTORTYPE("aDAPTORTYPE1")
            .dESTINATION("dESTINATION1")
            .tELCO("tELCO1")
            .dESCRIPTION("dESCRIPTION1")
            .rEMARKS("rEMARKS1")
            .sESSIONID("sESSIONID1")
            .rEWORKBY("rEWORKBY1");
    }

    public static SERVICEMANAGEMENT getSERVICEMANAGEMENTSample2() {
        return new SERVICEMANAGEMENT()
            .id(2L)
            .pROCESSINGCODE("pROCESSINGCODE2")
            .aCTIVE("aCTIVE2")
            .cREATEDBY("cREATEDBY2")
            .aPPROVED(2L)
            .aPPROVEDBY("aPPROVEDBY2")
            .aDAPTORTYPE("aDAPTORTYPE2")
            .dESTINATION("dESTINATION2")
            .tELCO("tELCO2")
            .dESCRIPTION("dESCRIPTION2")
            .rEMARKS("rEMARKS2")
            .sESSIONID("sESSIONID2")
            .rEWORKBY("rEWORKBY2");
    }

    public static SERVICEMANAGEMENT getSERVICEMANAGEMENTRandomSampleGenerator() {
        return new SERVICEMANAGEMENT()
            .id(longCount.incrementAndGet())
            .pROCESSINGCODE(UUID.randomUUID().toString())
            .aCTIVE(UUID.randomUUID().toString())
            .cREATEDBY(UUID.randomUUID().toString())
            .aPPROVED(longCount.incrementAndGet())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .aDAPTORTYPE(UUID.randomUUID().toString())
            .dESTINATION(UUID.randomUUID().toString())
            .tELCO(UUID.randomUUID().toString())
            .dESCRIPTION(UUID.randomUUID().toString())
            .rEMARKS(UUID.randomUUID().toString())
            .sESSIONID(UUID.randomUUID().toString())
            .rEWORKBY(UUID.randomUUID().toString());
    }
}
