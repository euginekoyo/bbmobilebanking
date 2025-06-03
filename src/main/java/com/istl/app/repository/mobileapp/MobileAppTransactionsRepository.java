package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.MobileAppTransactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MobileAppTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileAppTransactionsRepository extends JpaRepository<MobileAppTransactions, Long> {}
