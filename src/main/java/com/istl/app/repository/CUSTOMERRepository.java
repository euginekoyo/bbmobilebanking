package com.istl.app.repository;

import com.istl.app.domain.CUSTOMER;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CUSTOMER entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CUSTOMERRepository extends JpaRepository<CUSTOMER, Long> {}
