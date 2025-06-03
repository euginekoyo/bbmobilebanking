package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Billers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Billers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillersRepository extends JpaRepository<Billers, Long> {}
