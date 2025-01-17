package com.istl.app.repository;

import com.istl.app.domain.CUSTOMERACCOUNT;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CUSTOMERACCOUNT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CUSTOMERACCOUNTRepository extends JpaRepository<CUSTOMERACCOUNT, Long> {}
