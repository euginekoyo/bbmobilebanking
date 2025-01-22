package com.istl.app.domain;

import static com.istl.app.domain.ChargeRangeTestSamples.*;
import static com.istl.app.domain.ChargeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ChargeRangeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChargeRange.class);
        ChargeRange chargeRange1 = getChargeRangeSample1();
        ChargeRange chargeRange2 = new ChargeRange();
        assertThat(chargeRange1).isNotEqualTo(chargeRange2);

        chargeRange2.setId(chargeRange1.getId());
        assertThat(chargeRange1).isEqualTo(chargeRange2);

        chargeRange2 = getChargeRangeSample2();
        assertThat(chargeRange1).isNotEqualTo(chargeRange2);
    }

    @Test
    void chargeidTest() {
        ChargeRange chargeRange = getChargeRangeRandomSampleGenerator();
        Charge chargeBack = getChargeRandomSampleGenerator();

        chargeRange.addChargeid(chargeBack);
        assertThat(chargeRange.getChargeids()).containsOnly(chargeBack);
        assertThat(chargeBack.getChargeRange()).isEqualTo(chargeRange);

        chargeRange.removeChargeid(chargeBack);
        assertThat(chargeRange.getChargeids()).doesNotContain(chargeBack);
        assertThat(chargeBack.getChargeRange()).isNull();

        chargeRange.chargeids(new HashSet<>(Set.of(chargeBack)));
        assertThat(chargeRange.getChargeids()).containsOnly(chargeBack);
        assertThat(chargeBack.getChargeRange()).isEqualTo(chargeRange);

        chargeRange.setChargeids(new HashSet<>());
        assertThat(chargeRange.getChargeids()).doesNotContain(chargeBack);
        assertThat(chargeBack.getChargeRange()).isNull();
    }
}
