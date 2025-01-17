package com.istl.app.domain;

import static com.istl.app.domain.LINKEDACCOUNTSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LINKEDACCOUNTSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LINKEDACCOUNTS.class);
        LINKEDACCOUNTS lINKEDACCOUNTS1 = getLINKEDACCOUNTSSample1();
        LINKEDACCOUNTS lINKEDACCOUNTS2 = new LINKEDACCOUNTS();
        assertThat(lINKEDACCOUNTS1).isNotEqualTo(lINKEDACCOUNTS2);

        lINKEDACCOUNTS2.setId(lINKEDACCOUNTS1.getId());
        assertThat(lINKEDACCOUNTS1).isEqualTo(lINKEDACCOUNTS2);

        lINKEDACCOUNTS2 = getLINKEDACCOUNTSSample2();
        assertThat(lINKEDACCOUNTS1).isNotEqualTo(lINKEDACCOUNTS2);
    }
}
