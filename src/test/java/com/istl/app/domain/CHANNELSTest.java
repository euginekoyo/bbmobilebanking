package com.istl.app.domain;

import static com.istl.app.domain.CHANNELSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CHANNELSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CHANNELS.class);
        CHANNELS cHANNELS1 = getCHANNELSSample1();
        CHANNELS cHANNELS2 = new CHANNELS();
        assertThat(cHANNELS1).isNotEqualTo(cHANNELS2);

        cHANNELS2.setId(cHANNELS1.getId());
        assertThat(cHANNELS1).isEqualTo(cHANNELS2);

        cHANNELS2 = getCHANNELSSample2();
        assertThat(cHANNELS1).isNotEqualTo(cHANNELS2);
    }
}
