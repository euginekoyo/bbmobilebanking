package com.istl.app.domain;

import static com.istl.app.domain.PinResetHistoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PinResetHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PinResetHistory.class);
        PinResetHistory pinResetHistory1 = getPinResetHistorySample1();
        PinResetHistory pinResetHistory2 = new PinResetHistory();
        assertThat(pinResetHistory1).isNotEqualTo(pinResetHistory2);

        pinResetHistory2.setId(pinResetHistory1.getId());
        assertThat(pinResetHistory1).isEqualTo(pinResetHistory2);

        pinResetHistory2 = getPinResetHistorySample2();
        assertThat(pinResetHistory1).isNotEqualTo(pinResetHistory2);
    }
}
