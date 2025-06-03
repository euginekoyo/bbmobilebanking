package com.istl.app.domain;

import static com.istl.app.domain.SPSParticipatingCodesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.middleware.SPSParticipatingCodes;
import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SPSParticipatingCodesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SPSParticipatingCodes.class);
        SPSParticipatingCodes sPSParticipatingCodes1 = getSPSParticipatingCodesSample1();
        SPSParticipatingCodes sPSParticipatingCodes2 = new SPSParticipatingCodes();
        assertThat(sPSParticipatingCodes1).isNotEqualTo(sPSParticipatingCodes2);

        sPSParticipatingCodes2.setId(sPSParticipatingCodes1.getId());
        assertThat(sPSParticipatingCodes1).isEqualTo(sPSParticipatingCodes2);

        sPSParticipatingCodes2 = getSPSParticipatingCodesSample2();
        assertThat(sPSParticipatingCodes1).isNotEqualTo(sPSParticipatingCodes2);
    }
}
