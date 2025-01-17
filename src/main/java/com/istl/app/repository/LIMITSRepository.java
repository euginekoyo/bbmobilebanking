package com.istl.app.repository;

import com.istl.app.domain.LIMITS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LIMITS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LIMITSRepository extends JpaRepository<LIMITS, Long> {}
