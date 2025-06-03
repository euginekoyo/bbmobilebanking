package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.MessagesSms;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MessagesSms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessagesSmsRepository extends JpaRepository<MessagesSms, Long> {}
