package com.istl.app.repository;

import com.istl.app.domain.REQUESTS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the REQUESTS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface REQUESTSRepository extends JpaRepository<REQUESTS, Long> {}
