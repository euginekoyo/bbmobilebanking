package com.istl.app.domain;

import static com.istl.app.domain.BillersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BillersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Billers.class);
        Billers billers1 = getBillersSample1();
        Billers billers2 = new Billers();
        assertThat(billers1).isNotEqualTo(billers2);

        billers2.setId(billers1.getId());
        assertThat(billers1).isEqualTo(billers2);

        billers2 = getBillersSample2();
        assertThat(billers1).isNotEqualTo(billers2);
    }
}
