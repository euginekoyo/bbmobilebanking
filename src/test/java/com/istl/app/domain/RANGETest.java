package com.istl.app.domain;

import static com.istl.app.domain.CHARGETestSamples.*;
import static com.istl.app.domain.RANGETestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RANGETest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RANGE.class);
        RANGE rANGE1 = getRANGESample1();
        RANGE rANGE2 = new RANGE();
        assertThat(rANGE1).isNotEqualTo(rANGE2);

        rANGE2.setId(rANGE1.getId());
        assertThat(rANGE1).isEqualTo(rANGE2);

        rANGE2 = getRANGESample2();
        assertThat(rANGE1).isNotEqualTo(rANGE2);
    }

    @Test
    void cHARGEIDTest() {
        RANGE rANGE = getRANGERandomSampleGenerator();
        CHARGE cHARGEBack = getCHARGERandomSampleGenerator();

        rANGE.addCHARGEID(cHARGEBack);
        assertThat(rANGE.getCHARGEIDS()).containsOnly(cHARGEBack);
        assertThat(cHARGEBack.getRANGE()).isEqualTo(rANGE);

        rANGE.removeCHARGEID(cHARGEBack);
        assertThat(rANGE.getCHARGEIDS()).doesNotContain(cHARGEBack);
        assertThat(cHARGEBack.getRANGE()).isNull();

        rANGE.cHARGEIDS(new HashSet<>(Set.of(cHARGEBack)));
        assertThat(rANGE.getCHARGEIDS()).containsOnly(cHARGEBack);
        assertThat(cHARGEBack.getRANGE()).isEqualTo(rANGE);

        rANGE.setCHARGEIDS(new HashSet<>());
        assertThat(rANGE.getCHARGEIDS()).doesNotContain(cHARGEBack);
        assertThat(cHARGEBack.getRANGE()).isNull();
    }
}
