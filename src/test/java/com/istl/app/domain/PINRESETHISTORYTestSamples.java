package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PINRESETHISTORYTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PINRESETHISTORY getPINRESETHISTORYSample1() {
        return new PINRESETHISTORY()
            .id(1L)
            .pHONENUMBER("pHONENUMBER1")
            .cUSTOMERNAME("cUSTOMERNAME1")
            .pINBLOCKEDON("pINBLOCKEDON1")
            .pINBLOCKREMARKS("pINBLOCKREMARKS1")
            .pINRESETBY("pINRESETBY1")
            .pINRESETON("pINRESETON1")
            .pINRESETAPPROVEDBY("pINRESETAPPROVEDBY1")
            .pINRESETAPPROVEDON("pINRESETAPPROVEDON1")
            .pINRESETREMARKS("pINRESETREMARKS1")
            .bRANCHCODE("bRANCHCODE1");
    }

    public static PINRESETHISTORY getPINRESETHISTORYSample2() {
        return new PINRESETHISTORY()
            .id(2L)
            .pHONENUMBER("pHONENUMBER2")
            .cUSTOMERNAME("cUSTOMERNAME2")
            .pINBLOCKEDON("pINBLOCKEDON2")
            .pINBLOCKREMARKS("pINBLOCKREMARKS2")
            .pINRESETBY("pINRESETBY2")
            .pINRESETON("pINRESETON2")
            .pINRESETAPPROVEDBY("pINRESETAPPROVEDBY2")
            .pINRESETAPPROVEDON("pINRESETAPPROVEDON2")
            .pINRESETREMARKS("pINRESETREMARKS2")
            .bRANCHCODE("bRANCHCODE2");
    }

    public static PINRESETHISTORY getPINRESETHISTORYRandomSampleGenerator() {
        return new PINRESETHISTORY()
            .id(longCount.incrementAndGet())
            .pHONENUMBER(UUID.randomUUID().toString())
            .cUSTOMERNAME(UUID.randomUUID().toString())
            .pINBLOCKEDON(UUID.randomUUID().toString())
            .pINBLOCKREMARKS(UUID.randomUUID().toString())
            .pINRESETBY(UUID.randomUUID().toString())
            .pINRESETON(UUID.randomUUID().toString())
            .pINRESETAPPROVEDBY(UUID.randomUUID().toString())
            .pINRESETAPPROVEDON(UUID.randomUUID().toString())
            .pINRESETREMARKS(UUID.randomUUID().toString())
            .bRANCHCODE(UUID.randomUUID().toString());
    }
}
