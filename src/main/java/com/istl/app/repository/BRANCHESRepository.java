package com.istl.app.repository;

import com.istl.app.domain.Branches;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Branches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long> {}
