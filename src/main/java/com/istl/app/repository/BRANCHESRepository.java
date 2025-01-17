package com.istl.app.repository;

import com.istl.app.domain.BRANCHES;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BRANCHES entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BRANCHESRepository extends JpaRepository<BRANCHES, Long> {}
