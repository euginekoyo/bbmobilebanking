package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class REQUESTSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static REQUESTS getREQUESTSSample1() {
        return new REQUESTS()
            .id(1L)
            .mOBILENUMBER("mOBILENUMBER1")
            .aCCOUNTNO("aCCOUNTNO1")
            .cURRENCY("cURRENCY1")
            .cIF("cIF1")
            .rEQUESTTYPE("rEQUESTTYPE1")
            .rEQUESTSTATUS("rEQUESTSTATUS1")
            .tRNREFNO("tRNREFNO1")
            .nOOFBOOKS(1L)
            .nOOFLEAVES("nOOFLEAVES1")
            .aPPROVED(1L)
            .cHANNEL("cHANNEL1")
            .aPPROVEDBY("aPPROVEDBY1")
            .cHECKERREMARKS("cHECKERREMARKS1")
            .rESPCODE("rESPCODE1")
            .rESPDESCRIPTION("rESPDESCRIPTION1")
            .cUSTOMERNAME("cUSTOMERNAME1")
            .rEJECTED(1L)
            .rEJECTEDBY("rEJECTEDBY1");
    }

    public static REQUESTS getREQUESTSSample2() {
        return new REQUESTS()
            .id(2L)
            .mOBILENUMBER("mOBILENUMBER2")
            .aCCOUNTNO("aCCOUNTNO2")
            .cURRENCY("cURRENCY2")
            .cIF("cIF2")
            .rEQUESTTYPE("rEQUESTTYPE2")
            .rEQUESTSTATUS("rEQUESTSTATUS2")
            .tRNREFNO("tRNREFNO2")
            .nOOFBOOKS(2L)
            .nOOFLEAVES("nOOFLEAVES2")
            .aPPROVED(2L)
            .cHANNEL("cHANNEL2")
            .aPPROVEDBY("aPPROVEDBY2")
            .cHECKERREMARKS("cHECKERREMARKS2")
            .rESPCODE("rESPCODE2")
            .rESPDESCRIPTION("rESPDESCRIPTION2")
            .cUSTOMERNAME("cUSTOMERNAME2")
            .rEJECTED(2L)
            .rEJECTEDBY("rEJECTEDBY2");
    }

    public static REQUESTS getREQUESTSRandomSampleGenerator() {
        return new REQUESTS()
            .id(longCount.incrementAndGet())
            .mOBILENUMBER(UUID.randomUUID().toString())
            .aCCOUNTNO(UUID.randomUUID().toString())
            .cURRENCY(UUID.randomUUID().toString())
            .cIF(UUID.randomUUID().toString())
            .rEQUESTTYPE(UUID.randomUUID().toString())
            .rEQUESTSTATUS(UUID.randomUUID().toString())
            .tRNREFNO(UUID.randomUUID().toString())
            .nOOFBOOKS(longCount.incrementAndGet())
            .nOOFLEAVES(UUID.randomUUID().toString())
            .aPPROVED(longCount.incrementAndGet())
            .cHANNEL(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .cHECKERREMARKS(UUID.randomUUID().toString())
            .rESPCODE(UUID.randomUUID().toString())
            .rESPDESCRIPTION(UUID.randomUUID().toString())
            .cUSTOMERNAME(UUID.randomUUID().toString())
            .rEJECTED(longCount.incrementAndGet())
            .rEJECTEDBY(UUID.randomUUID().toString());
    }
}
