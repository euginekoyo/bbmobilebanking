package com.istl.app.domain;

import static com.istl.app.domain.BranchesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.mobileapp.Branches;
import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Branches.class);
        Branches branches1 = getBranchesSample1();
        Branches branches2 = new Branches();
        assertThat(branches1).isNotEqualTo(branches2);

        branches2.setId(branches1.getId());
        assertThat(branches1).isEqualTo(branches2);

        branches2 = getBranchesSample2();
        assertThat(branches1).isNotEqualTo(branches2);
    }
}
