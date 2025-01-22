package com.istl.app.domain;

import static com.istl.app.domain.ServiceManagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceManagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceManagement.class);
        ServiceManagement serviceManagement1 = getServiceManagementSample1();
        ServiceManagement serviceManagement2 = new ServiceManagement();
        assertThat(serviceManagement1).isNotEqualTo(serviceManagement2);

        serviceManagement2.setId(serviceManagement1.getId());
        assertThat(serviceManagement1).isEqualTo(serviceManagement2);

        serviceManagement2 = getServiceManagementSample2();
        assertThat(serviceManagement1).isNotEqualTo(serviceManagement2);
    }
}
