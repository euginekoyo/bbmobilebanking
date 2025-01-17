package com.istl.app.domain;

import static com.istl.app.domain.CUSTOMERACCOUNTTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CUSTOMERACCOUNTTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CUSTOMERACCOUNT.class);
        CUSTOMERACCOUNT cUSTOMERACCOUNT1 = getCUSTOMERACCOUNTSample1();
        CUSTOMERACCOUNT cUSTOMERACCOUNT2 = new CUSTOMERACCOUNT();
        assertThat(cUSTOMERACCOUNT1).isNotEqualTo(cUSTOMERACCOUNT2);

        cUSTOMERACCOUNT2.setId(cUSTOMERACCOUNT1.getId());
        assertThat(cUSTOMERACCOUNT1).isEqualTo(cUSTOMERACCOUNT2);

        cUSTOMERACCOUNT2 = getCUSTOMERACCOUNTSample2();
        assertThat(cUSTOMERACCOUNT1).isNotEqualTo(cUSTOMERACCOUNT2);
    }
}
