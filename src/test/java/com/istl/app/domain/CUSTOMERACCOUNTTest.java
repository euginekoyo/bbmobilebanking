package com.istl.app.domain;

import static com.istl.app.domain.CustomerAccountTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomerAccountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAccount.class);
        CustomerAccount customerAccount1 = getCustomerAccountSample1();
        CustomerAccount customerAccount2 = new CustomerAccount();
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);

        customerAccount2.setId(customerAccount1.getId());
        assertThat(customerAccount1).isEqualTo(customerAccount2);

        customerAccount2 = getCustomerAccountSample2();
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);
    }
}
