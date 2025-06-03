package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Range;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Range entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RangeRepository extends JpaRepository<Range, Long> {}
