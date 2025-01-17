package com.istl.app.repository;

import com.istl.app.domain.MESSAGETEMPLATES;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MESSAGETEMPLATES entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MESSAGETEMPLATESRepository extends JpaRepository<MESSAGETEMPLATES, Long> {}
