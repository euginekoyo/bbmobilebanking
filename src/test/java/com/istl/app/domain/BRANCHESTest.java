package com.istl.app.domain;

import static com.istl.app.domain.BRANCHESTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BRANCHESTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BRANCHES.class);
        BRANCHES bRANCHES1 = getBRANCHESSample1();
        BRANCHES bRANCHES2 = new BRANCHES();
        assertThat(bRANCHES1).isNotEqualTo(bRANCHES2);

        bRANCHES2.setId(bRANCHES1.getId());
        assertThat(bRANCHES1).isEqualTo(bRANCHES2);

        bRANCHES2 = getBRANCHESSample2();
        assertThat(bRANCHES1).isNotEqualTo(bRANCHES2);
    }
}
