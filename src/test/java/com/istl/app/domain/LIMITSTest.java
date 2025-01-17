package com.istl.app.domain;

import static com.istl.app.domain.LIMITSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LIMITSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LIMITS.class);
        LIMITS lIMITS1 = getLIMITSSample1();
        LIMITS lIMITS2 = new LIMITS();
        assertThat(lIMITS1).isNotEqualTo(lIMITS2);

        lIMITS2.setId(lIMITS1.getId());
        assertThat(lIMITS1).isEqualTo(lIMITS2);

        lIMITS2 = getLIMITSSample2();
        assertThat(lIMITS1).isNotEqualTo(lIMITS2);
    }
}
