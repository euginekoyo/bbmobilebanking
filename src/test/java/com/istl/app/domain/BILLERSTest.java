package com.istl.app.domain;

import static com.istl.app.domain.BILLERSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BILLERSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BILLERS.class);
        BILLERS bILLERS1 = getBILLERSSample1();
        BILLERS bILLERS2 = new BILLERS();
        assertThat(bILLERS1).isNotEqualTo(bILLERS2);

        bILLERS2.setId(bILLERS1.getId());
        assertThat(bILLERS1).isEqualTo(bILLERS2);

        bILLERS2 = getBILLERSSample2();
        assertThat(bILLERS1).isNotEqualTo(bILLERS2);
    }
}
