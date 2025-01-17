package com.istl.app.domain;

import static com.istl.app.domain.CHARGERANGESTestSamples.*;
import static com.istl.app.domain.CHARGETestSamples.*;
import static com.istl.app.domain.RANGETestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CHARGETest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CHARGE.class);
        CHARGE cHARGE1 = getCHARGESample1();
        CHARGE cHARGE2 = new CHARGE();
        assertThat(cHARGE1).isNotEqualTo(cHARGE2);

        cHARGE2.setId(cHARGE1.getId());
        assertThat(cHARGE1).isEqualTo(cHARGE2);

        cHARGE2 = getCHARGESample2();
        assertThat(cHARGE1).isNotEqualTo(cHARGE2);
    }

    @Test
    void cHARGERANGESTest() {
        CHARGE cHARGE = getCHARGERandomSampleGenerator();
        CHARGERANGES cHARGERANGESBack = getCHARGERANGESRandomSampleGenerator();

        cHARGE.setCHARGERANGES(cHARGERANGESBack);
        assertThat(cHARGE.getCHARGERANGES()).isEqualTo(cHARGERANGESBack);

        cHARGE.cHARGERANGES(null);
        assertThat(cHARGE.getCHARGERANGES()).isNull();
    }

    @Test
    void rANGETest() {
        CHARGE cHARGE = getCHARGERandomSampleGenerator();
        RANGE rANGEBack = getRANGERandomSampleGenerator();

        cHARGE.setRANGE(rANGEBack);
        assertThat(cHARGE.getRANGE()).isEqualTo(rANGEBack);

        cHARGE.rANGE(null);
        assertThat(cHARGE.getRANGE()).isNull();
    }
}
