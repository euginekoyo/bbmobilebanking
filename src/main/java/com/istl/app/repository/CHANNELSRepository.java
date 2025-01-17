package com.istl.app.repository;

import com.istl.app.domain.CHANNELS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CHANNELS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CHANNELSRepository extends JpaRepository<CHANNELS, Long> {}
