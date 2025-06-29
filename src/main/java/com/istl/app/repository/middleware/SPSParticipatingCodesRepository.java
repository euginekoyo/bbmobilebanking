package com.istl.app.repository.middleware;

import com.istl.app.domain.middleware.SPSParticipatingCodes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SPSParticipatingCodes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SPSParticipatingCodesRepository extends JpaRepository<SPSParticipatingCodes, Long> {}
