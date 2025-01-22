package com.istl.app.domain;

import static com.istl.app.domain.ChargeRangeTestSamples.*;
import static com.istl.app.domain.ChargeTestSamples.*;
import static com.istl.app.domain.RangeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChargeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Charge.class);
        Charge charge1 = getChargeSample1();
        Charge charge2 = new Charge();
        assertThat(charge1).isNotEqualTo(charge2);

        charge2.setId(charge1.getId());
        assertThat(charge1).isEqualTo(charge2);

        charge2 = getChargeSample2();
        assertThat(charge1).isNotEqualTo(charge2);
    }

    @Test
    void chargeRangeTest() {
        Charge charge = getChargeRandomSampleGenerator();
        ChargeRange chargeRangeBack = getChargeRangeRandomSampleGenerator();

        charge.setChargeRange(chargeRangeBack);
        assertThat(charge.getChargeRange()).isEqualTo(chargeRangeBack);

        charge.chargeRange(null);
        assertThat(charge.getChargeRange()).isNull();
    }

    @Test
    void rangeTest() {
        Charge charge = getChargeRandomSampleGenerator();
        Range rangeBack = getRangeRandomSampleGenerator();

        charge.setRange(rangeBack);
        assertThat(charge.getRange()).isEqualTo(rangeBack);

        charge.range(null);
        assertThat(charge.getRange()).isNull();
    }
}
