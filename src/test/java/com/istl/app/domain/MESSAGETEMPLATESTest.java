package com.istl.app.domain;

import static com.istl.app.domain.MESSAGETEMPLATESTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MESSAGETEMPLATESTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MESSAGETEMPLATES.class);
        MESSAGETEMPLATES mESSAGETEMPLATES1 = getMESSAGETEMPLATESSample1();
        MESSAGETEMPLATES mESSAGETEMPLATES2 = new MESSAGETEMPLATES();
        assertThat(mESSAGETEMPLATES1).isNotEqualTo(mESSAGETEMPLATES2);

        mESSAGETEMPLATES2.setId(mESSAGETEMPLATES1.getId());
        assertThat(mESSAGETEMPLATES1).isEqualTo(mESSAGETEMPLATES2);

        mESSAGETEMPLATES2 = getMESSAGETEMPLATESSample2();
        assertThat(mESSAGETEMPLATES1).isNotEqualTo(mESSAGETEMPLATES2);
    }
}
