package com.istl.app.repository;

import com.istl.app.domain.SERVICEMANAGEMENT;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SERVICEMANAGEMENT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SERVICEMANAGEMENTRepository extends JpaRepository<SERVICEMANAGEMENT, Long> {}
