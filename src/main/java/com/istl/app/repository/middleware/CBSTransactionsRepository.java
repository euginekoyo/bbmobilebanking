package com.istl.app.repository.middleware;

import com.istl.app.domain.middleware.CBSTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CBSTransactionsRepository extends JpaRepository<CBSTransactions, Long> {}
