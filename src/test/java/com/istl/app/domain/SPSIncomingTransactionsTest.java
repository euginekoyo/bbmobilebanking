package com.istl.app.domain;

import static com.istl.app.domain.SPSIncomingTransactionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.middleware.SPSIncomingTransactions;
import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SPSIncomingTransactionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SPSIncomingTransactions.class);
        SPSIncomingTransactions sPSIncomingTransactions1 = getSPSIncomingTransactionsSample1();
        SPSIncomingTransactions sPSIncomingTransactions2 = new SPSIncomingTransactions();
        assertThat(sPSIncomingTransactions1).isNotEqualTo(sPSIncomingTransactions2);

        sPSIncomingTransactions2.setId(sPSIncomingTransactions1.getId());
        assertThat(sPSIncomingTransactions1).isEqualTo(sPSIncomingTransactions2);

        sPSIncomingTransactions2 = getSPSIncomingTransactionsSample2();
        assertThat(sPSIncomingTransactions1).isNotEqualTo(sPSIncomingTransactions2);
    }
}
