package com.istl.app.repository;

import com.istl.app.domain.BILLERS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BILLERS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BILLERSRepository extends JpaRepository<BILLERS, Long> {}
