package com.istl.app.repository;

import com.istl.app.domain.MESSAGESSMS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MESSAGESSMS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MESSAGESSMSRepository extends JpaRepository<MESSAGESSMS, Long> {}
