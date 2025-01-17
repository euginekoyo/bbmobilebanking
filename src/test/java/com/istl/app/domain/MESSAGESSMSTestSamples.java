package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MESSAGESSMSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MESSAGESSMS getMESSAGESSMSSample1() {
        return new MESSAGESSMS()
            .id(1L)
            .pHONENUMBER("pHONENUMBER1")
            .tRANSACTIONNO("tRANSACTIONNO1")
            .aCCOUNTNUMBER("aCCOUNTNUMBER1")
            .mESSAGE("mESSAGE1")
            .cHANNEL("cHANNEL1")
            .tRIALS(1L)
            .pRIORITY(1L)
            .rESPONSECODE("rESPONSECODE1")
            .rESPONSEMSG("rESPONSEMSG1")
            .sENT(1L)
            .dELIVERED(1L)
            .tXNTYPE("tXNTYPE1")
            .eRROREXCEPTION(1L)
            .dATESENT("dATESENT1")
            .rTPSREQTIME("rTPSREQTIME1")
            .fXGENERATED("fXGENERATED1")
            .tAXPROCESSED(1L)
            .bATCHNUMBER("bATCHNUMBER1")
            .bATCHNUMBERTAX("bATCHNUMBERTAX1")
            .rESPONSETIME("rESPONSETIME1")
            .pDUSEQID("pDUSEQID1")
            .rEMARKS("rEMARKS1")
            .rESENDBY("rESENDBY1");
    }

    public static MESSAGESSMS getMESSAGESSMSSample2() {
        return new MESSAGESSMS()
            .id(2L)
            .pHONENUMBER("pHONENUMBER2")
            .tRANSACTIONNO("tRANSACTIONNO2")
            .aCCOUNTNUMBER("aCCOUNTNUMBER2")
            .mESSAGE("mESSAGE2")
            .cHANNEL("cHANNEL2")
            .tRIALS(2L)
            .pRIORITY(2L)
            .rESPONSECODE("rESPONSECODE2")
            .rESPONSEMSG("rESPONSEMSG2")
            .sENT(2L)
            .dELIVERED(2L)
            .tXNTYPE("tXNTYPE2")
            .eRROREXCEPTION(2L)
            .dATESENT("dATESENT2")
            .rTPSREQTIME("rTPSREQTIME2")
            .fXGENERATED("fXGENERATED2")
            .tAXPROCESSED(2L)
            .bATCHNUMBER("bATCHNUMBER2")
            .bATCHNUMBERTAX("bATCHNUMBERTAX2")
            .rESPONSETIME("rESPONSETIME2")
            .pDUSEQID("pDUSEQID2")
            .rEMARKS("rEMARKS2")
            .rESENDBY("rESENDBY2");
    }

    public static MESSAGESSMS getMESSAGESSMSRandomSampleGenerator() {
        return new MESSAGESSMS()
            .id(longCount.incrementAndGet())
            .pHONENUMBER(UUID.randomUUID().toString())
            .tRANSACTIONNO(UUID.randomUUID().toString())
            .aCCOUNTNUMBER(UUID.randomUUID().toString())
            .mESSAGE(UUID.randomUUID().toString())
            .cHANNEL(UUID.randomUUID().toString())
            .tRIALS(longCount.incrementAndGet())
            .pRIORITY(longCount.incrementAndGet())
            .rESPONSECODE(UUID.randomUUID().toString())
            .rESPONSEMSG(UUID.randomUUID().toString())
            .sENT(longCount.incrementAndGet())
            .dELIVERED(longCount.incrementAndGet())
            .tXNTYPE(UUID.randomUUID().toString())
            .eRROREXCEPTION(longCount.incrementAndGet())
            .dATESENT(UUID.randomUUID().toString())
            .rTPSREQTIME(UUID.randomUUID().toString())
            .fXGENERATED(UUID.randomUUID().toString())
            .tAXPROCESSED(longCount.incrementAndGet())
            .bATCHNUMBER(UUID.randomUUID().toString())
            .bATCHNUMBERTAX(UUID.randomUUID().toString())
            .rESPONSETIME(UUID.randomUUID().toString())
            .pDUSEQID(UUID.randomUUID().toString())
            .rEMARKS(UUID.randomUUID().toString())
            .rESENDBY(UUID.randomUUID().toString());
    }
}
