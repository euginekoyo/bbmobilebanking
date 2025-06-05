package com.istl.app.web.rest;

import com.istl.app.domain.mobileapp.Limits;
import com.istl.app.repository.mobileapp.LimitsRepository;
import com.istl.app.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.istl.app.domain.mobileapp.Limits}.
 */
@RestController
@RequestMapping("/api/limits")
@Transactional
public class LimitsResource {

    private static final Logger LOG = LoggerFactory.getLogger(LimitsResource.class);

    private static final String ENTITY_NAME = "limits";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LimitsRepository limitsRepository;

    public LimitsResource(LimitsRepository limitsRepository) {
        this.limitsRepository = limitsRepository;
    }

    /**
     * {@code POST  /limits} : Create a new limits.
     *
     * @param limits the limits to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new limits, or with status {@code 400 (Bad Request)} if the limits has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Limits> createLimits(@Valid @RequestBody Limits limits) throws URISyntaxException {
        LOG.debug("REST request to save Limits : {}", limits);
        if (limits.getId() != null) {
            throw new BadRequestAlertException("A new limits cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        limits.setRegisteredby(currentPrincipalName);
        limits.setRegistereddate(Instant.now().toString());
        limits.setApproved("0");

        limits = limitsRepository.save(limits);
        return ResponseEntity.created(new URI("/api/limits/" + limits.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, limits.getId().toString()))
            .body(limits);
    }

    /**
     * {@code PUT  /limits/:id} : Updates an existing limits.
     *
     * @param id the id of the limits to save.
     * @param limits the limits to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated limits,
     * or with status {@code 400 (Bad Request)} if the limits is not valid,
     * or with status {@code 500 (Internal Server Error)} if the limits couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Limits> updateLimits(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Limits limits
    ) throws URISyntaxException {
        LOG.debug("REST request to update Limits : {}, {}", id, limits);
        if (limits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, limits.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!limitsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        limits = limitsRepository.save(limits);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limits.getId().toString()))
            .body(limits);
    }

    /**
     * {@code PATCH  /limits/:id} : Partial updates given fields of an existing limits, field will ignore if it is null
     *
     * @param id the id of the limits to save.
     * @param limits the limits to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated limits,
     * or with status {@code 400 (Bad Request)} if the limits is not valid,
     * or with status {@code 404 (Not Found)} if the limits is not found,
     * or with status {@code 500 (Internal Server Error)} if the limits couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Limits> partialUpdateLimits(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Limits limits
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Limits partially : {}, {}", id, limits);
        if (limits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, limits.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!limitsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Limits> result = limitsRepository
            .findById(limits.getId())
            .map(existingLimits -> {
                if (limits.getTransactiontype() != null) {
                    existingLimits.setTransactiontype(limits.getTransactiontype());
                }
                if (limits.getProcode() != null) {
                    existingLimits.setProcode(limits.getProcode());
                }
                if (limits.getChannel() != null) {
                    existingLimits.setChannel(limits.getChannel());
                }
                if (limits.getTransactionlimit() != null) {
                    existingLimits.setTransactionlimit(limits.getTransactionlimit());
                }
                if (limits.getDailylimit() != null) {
                    existingLimits.setDailylimit(limits.getDailylimit());
                }
                if (limits.getRegisteredby() != null) {
                    existingLimits.setRegisteredby(limits.getRegisteredby());
                }
                if (limits.getRegistereddate() != null) {
                    existingLimits.setRegistereddate(limits.getRegistereddate());
                }
                if (limits.getApproved() != null) {
                    existingLimits.setApproved(limits.getApproved());
                }
                if (limits.getApprovedby() != null) {
                    existingLimits.setApprovedby(limits.getApprovedby());
                }
                if (limits.getApproveddate() != null) {
                    existingLimits.setApproveddate(limits.getApproveddate());
                }
                if (limits.getUpdatedby() != null) {
                    existingLimits.setUpdatedby(limits.getUpdatedby());
                }
                if (limits.getUpdateddate() != null) {
                    existingLimits.setUpdateddate(limits.getUpdateddate());
                }
                if (limits.getRework() != null) {
                    existingLimits.setRework(limits.getRework());
                }
                if (limits.getReworkby() != null) {
                    existingLimits.setReworkby(limits.getReworkby());
                }
                if (limits.getSessionid() != null) {
                    existingLimits.setSessionid(limits.getSessionid());
                }

                return existingLimits;
            })
            .map(limitsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limits.getId().toString())
        );
    }

    @PatchMapping(value = "/approve/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Limits> approveUpdateLimits(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Limits limits
    ) throws URISyntaxException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LOG.debug("REST request to partial update Limits partially : {}, {}", id, limits);
        if (limits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, limits.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!limitsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Limits> result = limitsRepository
            .findById(limits.getId())
            .map(existingLimits -> {
                existingLimits.setApproved("1");

                existingLimits.setApprovedby(currentPrincipalName);

                existingLimits.setApproveddate(Instant.now().toString());

                existingLimits.setUpdatedby(currentPrincipalName);

                existingLimits.setUpdateddate(Instant.now().toString());

                return existingLimits;
            })
            .map(limitsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limits.getId().toString())
        );
    }

    @PatchMapping(value = "/reject/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Limits> rejectUpdateLimits(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Limits limits
    ) throws URISyntaxException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LOG.debug("REST request to partial update Limits partially : {}, {}", id, limits);
        if (limits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, limits.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!limitsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Limits> result = limitsRepository
            .findById(limits.getId())
            .map(existingLimits -> {
                existingLimits.setApproved("2");

                existingLimits.setApprovedby(currentPrincipalName);

                existingLimits.setApproveddate(Instant.now().toString());

                existingLimits.setUpdatedby(currentPrincipalName);

                existingLimits.setUpdateddate(Instant.now().toString());

                return existingLimits;
            })
            .map(limitsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limits.getId().toString())
        );
    }

    /**
     * {@code GET  /limits} : get all the limits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of limits in body.
     */
    @GetMapping("")
    public List<Limits> getAllLimits() {
        LOG.debug("REST request to get all Limits");
        return limitsRepository.findAll();
    }

    /**
     * {@code GET  /limits/:id} : get the "id" limits.
     *
     * @param id the id of the limits to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the limits, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Limits> getLimits(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Limits : {}", id);
        Optional<Limits> limits = limitsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(limits);
    }

    /**
     * {@code DELETE  /limits/:id} : delete the "id" limits.
     *
     * @param id the id of the limits to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimits(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Limits : {}", id);
        limitsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
