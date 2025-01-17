package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LIMITSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LIMITS getLIMITSSample1() {
        return new LIMITS()
            .id(1L)
            .tRANSACTIONTYPE("tRANSACTIONTYPE1")
            .pROCODE("pROCODE1")
            .cHANNEL("cHANNEL1")
            .tRANSACTIONLIMIT(1L)
            .dAILYLIMIT(1L)
            .rEGISTEREDBY("rEGISTEREDBY1")
            .rEGISTEREDDATE("rEGISTEREDDATE1")
            .aPPROVED("aPPROVED1")
            .aPPROVEDBY("aPPROVEDBY1")
            .aPPROVEDDATE("aPPROVEDDATE1")
            .uPDATEDBY("uPDATEDBY1")
            .uPDATEDDATE("uPDATEDDATE1")
            .rEWORK(1L)
            .rEWORKBY("rEWORKBY1")
            .sESSIONID("sESSIONID1");
    }

    public static LIMITS getLIMITSSample2() {
        return new LIMITS()
            .id(2L)
            .tRANSACTIONTYPE("tRANSACTIONTYPE2")
            .pROCODE("pROCODE2")
            .cHANNEL("cHANNEL2")
            .tRANSACTIONLIMIT(2L)
            .dAILYLIMIT(2L)
            .rEGISTEREDBY("rEGISTEREDBY2")
            .rEGISTEREDDATE("rEGISTEREDDATE2")
            .aPPROVED("aPPROVED2")
            .aPPROVEDBY("aPPROVEDBY2")
            .aPPROVEDDATE("aPPROVEDDATE2")
            .uPDATEDBY("uPDATEDBY2")
            .uPDATEDDATE("uPDATEDDATE2")
            .rEWORK(2L)
            .rEWORKBY("rEWORKBY2")
            .sESSIONID("sESSIONID2");
    }

    public static LIMITS getLIMITSRandomSampleGenerator() {
        return new LIMITS()
            .id(longCount.incrementAndGet())
            .tRANSACTIONTYPE(UUID.randomUUID().toString())
            .pROCODE(UUID.randomUUID().toString())
            .cHANNEL(UUID.randomUUID().toString())
            .tRANSACTIONLIMIT(longCount.incrementAndGet())
            .dAILYLIMIT(longCount.incrementAndGet())
            .rEGISTEREDBY(UUID.randomUUID().toString())
            .rEGISTEREDDATE(UUID.randomUUID().toString())
            .aPPROVED(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .aPPROVEDDATE(UUID.randomUUID().toString())
            .uPDATEDBY(UUID.randomUUID().toString())
            .uPDATEDDATE(UUID.randomUUID().toString())
            .rEWORK(longCount.incrementAndGet())
            .rEWORKBY(UUID.randomUUID().toString())
            .sESSIONID(UUID.randomUUID().toString());
    }
}
