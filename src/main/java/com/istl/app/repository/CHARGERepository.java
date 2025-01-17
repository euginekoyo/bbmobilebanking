package com.istl.app.repository;

import com.istl.app.domain.CHARGE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CHARGE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CHARGERepository extends JpaRepository<CHARGE, Long> {}
