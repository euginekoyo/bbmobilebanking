package com.istl.app.domain;

import static com.istl.app.domain.LimitsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LimitsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Limits.class);
        Limits limits1 = getLimitsSample1();
        Limits limits2 = new Limits();
        assertThat(limits1).isNotEqualTo(limits2);

        limits2.setId(limits1.getId());
        assertThat(limits1).isEqualTo(limits2);

        limits2 = getLimitsSample2();
        assertThat(limits1).isNotEqualTo(limits2);
    }
}
