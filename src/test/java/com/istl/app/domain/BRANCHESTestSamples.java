package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BRANCHESTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BRANCHES getBRANCHESSample1() {
        return new BRANCHES()
            .id(1L)
            .bRANCHNAME("bRANCHNAME1")
            .bRANCHCODE("bRANCHCODE1")
            .aPPROVED(1L)
            .eMAIL("eMAIL1")
            .aDDRESS("aDDRESS1")
            .pHONE("pHONE1")
            .lOCATION("lOCATION1")
            .cONTACTPERSON("cONTACTPERSON1")
            .rEMARKS("rEMARKS1")
            .cREATEDBY("cREATEDBY1")
            .aPPROVEDBY("aPPROVEDBY1")
            .aPPROVEDON("aPPROVEDON1")
            .cHECKERREMARKS("cHECKERREMARKS1")
            .dELETEDBY("dELETEDBY1")
            .dELETEREMARKS("dELETEREMARKS1")
            .dELETED(1L)
            .dECLINED(1L)
            .dECLINEDDON("dECLINEDDON1")
            .dECLINEDBY("dECLINEDBY1")
            .sESSIONID("sESSIONID1")
            .rEWORKED(1L)
            .rEWORKEDBY("rEWORKEDBY1")
            .dISTRICT("dISTRICT1")
            .rEGION("rEGION1")
            .rEGIONNAME("rEGIONNAME1")
            .rEPORTING(1L);
    }

    public static BRANCHES getBRANCHESSample2() {
        return new BRANCHES()
            .id(2L)
            .bRANCHNAME("bRANCHNAME2")
            .bRANCHCODE("bRANCHCODE2")
            .aPPROVED(2L)
            .eMAIL("eMAIL2")
            .aDDRESS("aDDRESS2")
            .pHONE("pHONE2")
            .lOCATION("lOCATION2")
            .cONTACTPERSON("cONTACTPERSON2")
            .rEMARKS("rEMARKS2")
            .cREATEDBY("cREATEDBY2")
            .aPPROVEDBY("aPPROVEDBY2")
            .aPPROVEDON("aPPROVEDON2")
            .cHECKERREMARKS("cHECKERREMARKS2")
            .dELETEDBY("dELETEDBY2")
            .dELETEREMARKS("dELETEREMARKS2")
            .dELETED(2L)
            .dECLINED(2L)
            .dECLINEDDON("dECLINEDDON2")
            .dECLINEDBY("dECLINEDBY2")
            .sESSIONID("sESSIONID2")
            .rEWORKED(2L)
            .rEWORKEDBY("rEWORKEDBY2")
            .dISTRICT("dISTRICT2")
            .rEGION("rEGION2")
            .rEGIONNAME("rEGIONNAME2")
            .rEPORTING(2L);
    }

    public static BRANCHES getBRANCHESRandomSampleGenerator() {
        return new BRANCHES()
            .id(longCount.incrementAndGet())
            .bRANCHNAME(UUID.randomUUID().toString())
            .bRANCHCODE(UUID.randomUUID().toString())
            .aPPROVED(longCount.incrementAndGet())
            .eMAIL(UUID.randomUUID().toString())
            .aDDRESS(UUID.randomUUID().toString())
            .pHONE(UUID.randomUUID().toString())
            .lOCATION(UUID.randomUUID().toString())
            .cONTACTPERSON(UUID.randomUUID().toString())
            .rEMARKS(UUID.randomUUID().toString())
            .cREATEDBY(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .aPPROVEDON(UUID.randomUUID().toString())
            .cHECKERREMARKS(UUID.randomUUID().toString())
            .dELETEDBY(UUID.randomUUID().toString())
            .dELETEREMARKS(UUID.randomUUID().toString())
            .dELETED(longCount.incrementAndGet())
            .dECLINED(longCount.incrementAndGet())
            .dECLINEDDON(UUID.randomUUID().toString())
            .dECLINEDBY(UUID.randomUUID().toString())
            .sESSIONID(UUID.randomUUID().toString())
            .rEWORKED(longCount.incrementAndGet())
            .rEWORKEDBY(UUID.randomUUID().toString())
            .dISTRICT(UUID.randomUUID().toString())
            .rEGION(UUID.randomUUID().toString())
            .rEGIONNAME(UUID.randomUUID().toString())
            .rEPORTING(longCount.incrementAndGet());
    }
}
