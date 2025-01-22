package com.istl.app.repository;

import com.istl.app.domain.Range;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Range entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RangeRepository extends JpaRepository<Range, Long> {}
