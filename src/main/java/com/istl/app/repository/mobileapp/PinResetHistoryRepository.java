package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.PinResetHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PinResetHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PinResetHistoryRepository extends JpaRepository<PinResetHistory, Long> {}
