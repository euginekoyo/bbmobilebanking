package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.ServiceManagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceManagementRepository extends JpaRepository<ServiceManagement, Long> {}
