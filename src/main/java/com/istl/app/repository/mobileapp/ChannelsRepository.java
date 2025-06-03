package com.istl.app.repository.mobileapp;

import com.istl.app.domain.mobileapp.Channels;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Channels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChannelsRepository extends JpaRepository<Channels, Long> {}
