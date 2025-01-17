package com.istl.app.domain;

import static com.istl.app.domain.MESSAGESSMSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MESSAGESSMSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MESSAGESSMS.class);
        MESSAGESSMS mESSAGESSMS1 = getMESSAGESSMSSample1();
        MESSAGESSMS mESSAGESSMS2 = new MESSAGESSMS();
        assertThat(mESSAGESSMS1).isNotEqualTo(mESSAGESSMS2);

        mESSAGESSMS2.setId(mESSAGESSMS1.getId());
        assertThat(mESSAGESSMS1).isEqualTo(mESSAGESSMS2);

        mESSAGESSMS2 = getMESSAGESSMSSample2();
        assertThat(mESSAGESSMS1).isNotEqualTo(mESSAGESSMS2);
    }
}
