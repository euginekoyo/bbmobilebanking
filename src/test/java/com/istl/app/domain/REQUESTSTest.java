package com.istl.app.domain;

import static com.istl.app.domain.REQUESTSTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class REQUESTSTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(REQUESTS.class);
        REQUESTS rEQUESTS1 = getREQUESTSSample1();
        REQUESTS rEQUESTS2 = new REQUESTS();
        assertThat(rEQUESTS1).isNotEqualTo(rEQUESTS2);

        rEQUESTS2.setId(rEQUESTS1.getId());
        assertThat(rEQUESTS1).isEqualTo(rEQUESTS2);

        rEQUESTS2 = getREQUESTSSample2();
        assertThat(rEQUESTS1).isNotEqualTo(rEQUESTS2);
    }
}
