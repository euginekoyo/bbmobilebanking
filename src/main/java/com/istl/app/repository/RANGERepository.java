package com.istl.app.repository;

import com.istl.app.domain.RANGE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RANGE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RANGERepository extends JpaRepository<RANGE, Long> {}
