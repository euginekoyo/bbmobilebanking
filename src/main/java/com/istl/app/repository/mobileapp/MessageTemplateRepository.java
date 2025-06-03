package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.MessageTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MessageTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {}
