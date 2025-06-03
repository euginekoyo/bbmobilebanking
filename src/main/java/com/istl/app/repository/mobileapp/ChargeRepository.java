package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Charge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Charge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {}
