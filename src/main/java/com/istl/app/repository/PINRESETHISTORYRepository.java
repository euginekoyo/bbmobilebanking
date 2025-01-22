package com.istl.app.repository;

import com.istl.app.domain.PinResetHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PinResetHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PinResetHistoryRepository extends JpaRepository<PinResetHistory, Long> {}
