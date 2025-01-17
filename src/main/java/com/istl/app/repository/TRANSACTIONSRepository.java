package com.istl.app.repository;

import com.istl.app.domain.TRANSACTIONS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TRANSACTIONS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TRANSACTIONSRepository extends JpaRepository<TRANSACTIONS, Long> {}
