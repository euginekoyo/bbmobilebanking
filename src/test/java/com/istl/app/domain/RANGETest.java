package com.istl.app.domain;

import static com.istl.app.domain.ChargeTestSamples.*;
import static com.istl.app.domain.RangeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.mobileapp.Charge;
import com.istl.app.domain.mobileapp.Range;
import com.istl.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RangeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Range.class);
        Range range1 = getRangeSample1();
        Range range2 = new Range();
        assertThat(range1).isNotEqualTo(range2);

        range2.setId(range1.getId());
        assertThat(range1).isEqualTo(range2);

        range2 = getRangeSample2();
        assertThat(range1).isNotEqualTo(range2);
    }

    @Test
    void chargeidTest() {
        Range range = getRangeRandomSampleGenerator();
        Charge chargeBack = getChargeRandomSampleGenerator();

        range.addChargeid(chargeBack);
        assertThat(range.getChargeids()).containsOnly(chargeBack);
        assertThat(chargeBack.getRange()).isEqualTo(range);

        range.removeChargeid(chargeBack);
        assertThat(range.getChargeids()).doesNotContain(chargeBack);
        assertThat(chargeBack.getRange()).isNull();

        range.chargeids(new HashSet<>(Set.of(chargeBack)));
        assertThat(range.getChargeids()).containsOnly(chargeBack);
        assertThat(chargeBack.getRange()).isEqualTo(range);

        range.setChargeids(new HashSet<>());
        assertThat(range.getChargeids()).doesNotContain(chargeBack);
        assertThat(chargeBack.getRange()).isNull();
    }
}
