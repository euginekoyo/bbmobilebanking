package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.ChargeRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ChargeRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargeRangeRepository extends JpaRepository<ChargeRange, Long> {}
