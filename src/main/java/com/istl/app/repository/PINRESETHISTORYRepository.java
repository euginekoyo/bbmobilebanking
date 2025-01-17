package com.istl.app.repository;

import com.istl.app.domain.PINRESETHISTORY;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PINRESETHISTORY entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PINRESETHISTORYRepository extends JpaRepository<PINRESETHISTORY, Long> {}
