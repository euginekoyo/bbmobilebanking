package com.istl.app.repository;

import com.istl.app.domain.ServiceManagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceManagementRepository extends JpaRepository<ServiceManagement, Long> {}
