package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Limits;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Limits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LimitsRepository extends JpaRepository<Limits, Long> {}
