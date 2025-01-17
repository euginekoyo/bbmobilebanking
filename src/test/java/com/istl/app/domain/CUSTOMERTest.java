package com.istl.app.domain;

import static com.istl.app.domain.CUSTOMERTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CUSTOMERTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CUSTOMER.class);
        CUSTOMER cUSTOMER1 = getCUSTOMERSample1();
        CUSTOMER cUSTOMER2 = new CUSTOMER();
        assertThat(cUSTOMER1).isNotEqualTo(cUSTOMER2);

        cUSTOMER2.setId(cUSTOMER1.getId());
        assertThat(cUSTOMER1).isEqualTo(cUSTOMER2);

        cUSTOMER2 = getCUSTOMERSample2();
        assertThat(cUSTOMER1).isNotEqualTo(cUSTOMER2);
    }
}
