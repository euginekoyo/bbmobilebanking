package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LINKEDACCOUNTSTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LINKEDACCOUNTS getLINKEDACCOUNTSSample1() {
        return new LINKEDACCOUNTS()
            .id(1L)
            .aCOUNTNAME("aCOUNTNAME1")
            .aCCOUNTCLASS("aCCOUNTCLASS1")
            .aCCOUNTCURRENCY("aCCOUNTCURRENCY1")
            .aCCOUNTNUMBER("aCCOUNTNUMBER1")
            .cIF("cIF1")
            .pHONENUMBER("pHONENUMBER1")
            .aPPROVED("aPPROVED1")
            .dECLINED("dECLINED1")
            .rEMARKS("rEMARKS1")
            .lINKEDBY("lINKEDBY1")
            .aPPROVEDBY("aPPROVEDBY1")
            .lINKED("lINKED1")
            .aCTIVE("aCTIVE1")
            .dELINKEDBY("dELINKEDBY1")
            .dELINKED("dELINKED1")
            .aCCOUNTALIAS("aCCOUNTALIAS1")
            .sHORTCUTS("sHORTCUTS1");
    }

    public static LINKEDACCOUNTS getLINKEDACCOUNTSSample2() {
        return new LINKEDACCOUNTS()
            .id(2L)
            .aCOUNTNAME("aCOUNTNAME2")
            .aCCOUNTCLASS("aCCOUNTCLASS2")
            .aCCOUNTCURRENCY("aCCOUNTCURRENCY2")
            .aCCOUNTNUMBER("aCCOUNTNUMBER2")
            .cIF("cIF2")
            .pHONENUMBER("pHONENUMBER2")
            .aPPROVED("aPPROVED2")
            .dECLINED("dECLINED2")
            .rEMARKS("rEMARKS2")
            .lINKEDBY("lINKEDBY2")
            .aPPROVEDBY("aPPROVEDBY2")
            .lINKED("lINKED2")
            .aCTIVE("aCTIVE2")
            .dELINKEDBY("dELINKEDBY2")
            .dELINKED("dELINKED2")
            .aCCOUNTALIAS("aCCOUNTALIAS2")
            .sHORTCUTS("sHORTCUTS2");
    }

    public static LINKEDACCOUNTS getLINKEDACCOUNTSRandomSampleGenerator() {
        return new LINKEDACCOUNTS()
            .id(longCount.incrementAndGet())
            .aCOUNTNAME(UUID.randomUUID().toString())
            .aCCOUNTCLASS(UUID.randomUUID().toString())
            .aCCOUNTCURRENCY(UUID.randomUUID().toString())
            .aCCOUNTNUMBER(UUID.randomUUID().toString())
            .cIF(UUID.randomUUID().toString())
            .pHONENUMBER(UUID.randomUUID().toString())
            .aPPROVED(UUID.randomUUID().toString())
            .dECLINED(UUID.randomUUID().toString())
            .rEMARKS(UUID.randomUUID().toString())
            .lINKEDBY(UUID.randomUUID().toString())
            .aPPROVEDBY(UUID.randomUUID().toString())
            .lINKED(UUID.randomUUID().toString())
            .aCTIVE(UUID.randomUUID().toString())
            .dELINKEDBY(UUID.randomUUID().toString())
            .dELINKED(UUID.randomUUID().toString())
            .aCCOUNTALIAS(UUID.randomUUID().toString())
            .sHORTCUTS(UUID.randomUUID().toString());
    }
}
