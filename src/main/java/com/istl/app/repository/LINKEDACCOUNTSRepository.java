package com.istl.app.repository;

import com.istl.app.domain.LINKEDACCOUNTS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LINKEDACCOUNTS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LINKEDACCOUNTSRepository extends JpaRepository<LINKEDACCOUNTS, Long> {}
