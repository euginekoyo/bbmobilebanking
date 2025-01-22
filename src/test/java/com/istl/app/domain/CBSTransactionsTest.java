package com.istl.app.domain;

import static com.istl.app.domain.CBSTransactionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CBSTransactionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CBSTransactions.class);
        CBSTransactions cBSTransactions1 = getCBSTransactionsSample1();
        CBSTransactions cBSTransactions2 = new CBSTransactions();
        assertThat(cBSTransactions1).isNotEqualTo(cBSTransactions2);

        cBSTransactions2.setId(cBSTransactions1.getId());
        assertThat(cBSTransactions1).isEqualTo(cBSTransactions2);

        cBSTransactions2 = getCBSTransactionsSample2();
        assertThat(cBSTransactions1).isNotEqualTo(cBSTransactions2);
    }
}
