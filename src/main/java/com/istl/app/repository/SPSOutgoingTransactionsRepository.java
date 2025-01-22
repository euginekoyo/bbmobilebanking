package com.istl.app.repository;

import com.istl.app.domain.SPSOutgoingTransactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SPSOutgoingTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SPSOutgoingTransactionsRepository extends JpaRepository<SPSOutgoingTransactions, Long> {}
