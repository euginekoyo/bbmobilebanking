package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Transactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Transactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {}
