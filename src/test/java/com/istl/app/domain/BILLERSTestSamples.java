package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BILLERSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BILLERS getBILLERSSample1() {
        return new BILLERS()
            .id(1L)
            .bILLERID("bILLERID1")
            .dESCRIPTION("dESCRIPTION1")
            .bILLERCOLLECTIONACCOUNT("bILLERCOLLECTIONACCOUNT1")
            .cREATEDBY("cREATEDBY1")
            .aPPROVED(1L)
            .aPPROVEDBY("aPPROVEDBY1")
            .cHARGABLEPRODUCTID("cHARGABLEPRODUCTID1")
            .nONCHARGABLEPRODUCTID("nONCHARGABLEPRODUCTID1")
            .uSDBILLERCOLLECTIONACCOUNT("uSDBILLERCOLLECTIONACCOUNT1")
            .eNABLEDUPLICATECHECK(1L)
            .rEMARKS("rEMARKS1")
            .sESSIONID("sESSIONID1")
            .rEWORKBY("rEWORKBY1")
            .sTATUS(1L)
            .aCTIVE(1L)
            .rEWORK(1L);
    }

    public static BILLERS getBILLERSSample2() {
        return new BILLERS()
            .id(2L)
            .bILLERID("bILLERID2")
            .dESCRIPTION("dESCRIPTION2")
            .bILLERCOLLECTIONACCOUNT("bILLERCOLLECTIONACCOUNT2")
            .cREATEDBY("cREATEDBY2")
            .aPPROVED(2L)
            .aPPROVEDBY("aPPROVEDBY2")
            .cHARGABLEPRODUCTID("cHARGABLEPRODUCTID2")
            .nONCHARGABLEPRODUCTID("nONCHARGABLEPRODUCTID2")
            .uSDBILLERCOLLECTIONACCOUNT("uSDBILLERCOLLECTIONACCOUNT2")
            .eNABLEDUPLICATECHECK(2L)
            .rEMARKS("rEMARKS2")
            .sESSIONID("sESSIONID2")
            .rEWORKBY("rEWORKBY2")
            .sTATUS(2L)
            .aCTIVE(2L)
            .rEWORK(2L);
    }

    public static BILLERS getBILLERSRandomSampleGenerator() {
        return new BILLERS()
            .id(longCount.incrementAndGet())
            .bILLERID(UUID.randomUUID().toString())
            .dESCRIPTION(UUID.randomUUID().toString())
            .bILLERCOLLECTIONACCOUNT(UUID.randomUUID().toString())
            .cREATEDBY(UUID.randomUUID().toString())
            .aPPROVED(longCount.incrementAndGet())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .cHARGABLEPRODUCTID(UUID.randomUUID().toString())
            .nONCHARGABLEPRODUCTID(UUID.randomUUID().toString())
            .uSDBILLERCOLLECTIONACCOUNT(UUID.randomUUID().toString())
            .eNABLEDUPLICATECHECK(longCount.incrementAndGet())
            .rEMARKS(UUID.randomUUID().toString())
            .sESSIONID(UUID.randomUUID().toString())
            .rEWORKBY(UUID.randomUUID().toString())
            .sTATUS(longCount.incrementAndGet())
            .aCTIVE(longCount.incrementAndGet())
            .rEWORK(longCount.incrementAndGet());
    }
}
