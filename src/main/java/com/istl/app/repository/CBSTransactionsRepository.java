package com.istl.app.repository;

import com.istl.app.domain.CBSTransactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CBSTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CBSTransactionsRepository extends JpaRepository<CBSTransactions, Long> {}
