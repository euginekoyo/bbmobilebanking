package com.istl.app.repository;

import com.istl.app.domain.CHARGERANGES;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CHARGERANGES entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CHARGERANGESRepository extends JpaRepository<CHARGERANGES, Long> {}
