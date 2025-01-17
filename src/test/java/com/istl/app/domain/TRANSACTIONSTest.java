package com.istl.app.domain;

import static com.istl.app.domain.TRANSACTIONSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TRANSACTIONSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TRANSACTIONS.class);
        TRANSACTIONS tRANSACTIONS1 = getTRANSACTIONSSample1();
        TRANSACTIONS tRANSACTIONS2 = new TRANSACTIONS();
        assertThat(tRANSACTIONS1).isNotEqualTo(tRANSACTIONS2);

        tRANSACTIONS2.setId(tRANSACTIONS1.getId());
        assertThat(tRANSACTIONS1).isEqualTo(tRANSACTIONS2);

        tRANSACTIONS2 = getTRANSACTIONSSample2();
        assertThat(tRANSACTIONS1).isNotEqualTo(tRANSACTIONS2);
    }
}
