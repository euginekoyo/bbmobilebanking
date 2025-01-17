package com.istl.app.domain;

import static com.istl.app.domain.PINRESETHISTORYTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PINRESETHISTORYTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PINRESETHISTORY.class);
        PINRESETHISTORY pINRESETHISTORY1 = getPINRESETHISTORYSample1();
        PINRESETHISTORY pINRESETHISTORY2 = new PINRESETHISTORY();
        assertThat(pINRESETHISTORY1).isNotEqualTo(pINRESETHISTORY2);

        pINRESETHISTORY2.setId(pINRESETHISTORY1.getId());
        assertThat(pINRESETHISTORY1).isEqualTo(pINRESETHISTORY2);

        pINRESETHISTORY2 = getPINRESETHISTORYSample2();
        assertThat(pINRESETHISTORY1).isNotEqualTo(pINRESETHISTORY2);
    }
}
