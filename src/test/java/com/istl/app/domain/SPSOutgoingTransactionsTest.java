package com.istl.app.domain;

import static com.istl.app.domain.SPSOutgoingTransactionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.middleware.SPSOutgoingTransactions;
import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SPSOutgoingTransactionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SPSOutgoingTransactions.class);
        SPSOutgoingTransactions sPSOutgoingTransactions1 = getSPSOutgoingTransactionsSample1();
        SPSOutgoingTransactions sPSOutgoingTransactions2 = new SPSOutgoingTransactions();
        assertThat(sPSOutgoingTransactions1).isNotEqualTo(sPSOutgoingTransactions2);

        sPSOutgoingTransactions2.setId(sPSOutgoingTransactions1.getId());
        assertThat(sPSOutgoingTransactions1).isEqualTo(sPSOutgoingTransactions2);

        sPSOutgoingTransactions2 = getSPSOutgoingTransactionsSample2();
        assertThat(sPSOutgoingTransactions1).isNotEqualTo(sPSOutgoingTransactions2);
    }
}
