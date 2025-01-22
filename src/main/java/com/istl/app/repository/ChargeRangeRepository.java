package com.istl.app.repository;

import com.istl.app.domain.ChargeRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChargeRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargeRangeRepository extends JpaRepository<ChargeRange, Long> {}
