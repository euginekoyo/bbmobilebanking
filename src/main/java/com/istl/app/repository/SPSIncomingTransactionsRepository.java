package com.istl.app.repository;

import com.istl.app.domain.SPSIncomingTransactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SPSIncomingTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SPSIncomingTransactionsRepository extends JpaRepository<SPSIncomingTransactions, Long> {}
