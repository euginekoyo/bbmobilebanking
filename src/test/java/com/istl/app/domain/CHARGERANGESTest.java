package com.istl.app.domain;

import static com.istl.app.domain.CHARGERANGESTestSamples.*;
import static com.istl.app.domain.CHARGETestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CHARGERANGESTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CHARGERANGES.class);
        CHARGERANGES cHARGERANGES1 = getCHARGERANGESSample1();
        CHARGERANGES cHARGERANGES2 = new CHARGERANGES();
        assertThat(cHARGERANGES1).isNotEqualTo(cHARGERANGES2);

        cHARGERANGES2.setId(cHARGERANGES1.getId());
        assertThat(cHARGERANGES1).isEqualTo(cHARGERANGES2);

        cHARGERANGES2 = getCHARGERANGESSample2();
        assertThat(cHARGERANGES1).isNotEqualTo(cHARGERANGES2);
    }

    @Test
    void cHARGEIDTest() {
        CHARGERANGES cHARGERANGES = getCHARGERANGESRandomSampleGenerator();
        CHARGE cHARGEBack = getCHARGERandomSampleGenerator();

        cHARGERANGES.addCHARGEID(cHARGEBack);
        assertThat(cHARGERANGES.getCHARGEIDS()).containsOnly(cHARGEBack);
        assertThat(cHARGEBack.getCHARGERANGES()).isEqualTo(cHARGERANGES);

        cHARGERANGES.removeCHARGEID(cHARGEBack);
        assertThat(cHARGERANGES.getCHARGEIDS()).doesNotContain(cHARGEBack);
        assertThat(cHARGEBack.getCHARGERANGES()).isNull();

        cHARGERANGES.cHARGEIDS(new HashSet<>(Set.of(cHARGEBack)));
        assertThat(cHARGERANGES.getCHARGEIDS()).containsOnly(cHARGEBack);
        assertThat(cHARGEBack.getCHARGERANGES()).isEqualTo(cHARGERANGES);

        cHARGERANGES.setCHARGEIDS(new HashSet<>());
        assertThat(cHARGERANGES.getCHARGEIDS()).doesNotContain(cHARGEBack);
        assertThat(cHARGEBack.getCHARGERANGES()).isNull();
    }
}
