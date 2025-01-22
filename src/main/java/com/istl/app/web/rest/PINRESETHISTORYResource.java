package com.istl.app.web.rest;

import com.istl.app.domain.PinResetHistory;
import com.istl.app.repository.PinResetHistoryRepository;
import com.istl.app.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.istl.app.domain.PinResetHistory}.
 */
@RestController
@RequestMapping("/api/pin-reset-histories")
@Transactional
public class PinResetHistoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(PinResetHistoryResource.class);

    private static final String ENTITY_NAME = "pinResetHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PinResetHistoryRepository pinResetHistoryRepository;

    public PinResetHistoryResource(PinResetHistoryRepository pinResetHistoryRepository) {
        this.pinResetHistoryRepository = pinResetHistoryRepository;
    }

    /**
     * {@code POST  /pin-reset-histories} : Create a new pinResetHistory.
     *
     * @param pinResetHistory the pinResetHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pinResetHistory, or with status {@code 400 (Bad Request)} if the pinResetHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PinResetHistory> createPinResetHistory(@Valid @RequestBody PinResetHistory pinResetHistory)
        throws URISyntaxException {
        LOG.debug("REST request to save PinResetHistory : {}", pinResetHistory);
        if (pinResetHistory.getId() != null) {
            throw new BadRequestAlertException("A new pinResetHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pinResetHistory = pinResetHistoryRepository.save(pinResetHistory);
        return ResponseEntity.created(new URI("/api/pin-reset-histories/" + pinResetHistory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pinResetHistory.getId().toString()))
            .body(pinResetHistory);
    }

    /**
     * {@code PUT  /pin-reset-histories/:id} : Updates an existing pinResetHistory.
     *
     * @param id the id of the pinResetHistory to save.
     * @param pinResetHistory the pinResetHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pinResetHistory,
     * or with status {@code 400 (Bad Request)} if the pinResetHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pinResetHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PinResetHistory> updatePinResetHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PinResetHistory pinResetHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to update PinResetHistory : {}, {}", id, pinResetHistory);
        if (pinResetHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pinResetHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pinResetHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        pinResetHistory = pinResetHistoryRepository.save(pinResetHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pinResetHistory.getId().toString()))
            .body(pinResetHistory);
    }

    /**
     * {@code PATCH  /pin-reset-histories/:id} : Partial updates given fields of an existing pinResetHistory, field will ignore if it is null
     *
     * @param id the id of the pinResetHistory to save.
     * @param pinResetHistory the pinResetHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pinResetHistory,
     * or with status {@code 400 (Bad Request)} if the pinResetHistory is not valid,
     * or with status {@code 404 (Not Found)} if the pinResetHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the pinResetHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PinResetHistory> partialUpdatePinResetHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PinResetHistory pinResetHistory
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update PinResetHistory partially : {}, {}", id, pinResetHistory);
        if (pinResetHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pinResetHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pinResetHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PinResetHistory> result = pinResetHistoryRepository
            .findById(pinResetHistory.getId())
            .map(existingPinResetHistory -> {
                if (pinResetHistory.getPhonenumber() != null) {
                    existingPinResetHistory.setPhonenumber(pinResetHistory.getPhonenumber());
                }
                if (pinResetHistory.getCustomername() != null) {
                    existingPinResetHistory.setCustomername(pinResetHistory.getCustomername());
                }
                if (pinResetHistory.getPinblockedon() != null) {
                    existingPinResetHistory.setPinblockedon(pinResetHistory.getPinblockedon());
                }
                if (pinResetHistory.getPinblockremarks() != null) {
                    existingPinResetHistory.setPinblockremarks(pinResetHistory.getPinblockremarks());
                }
                if (pinResetHistory.getPinresetby() != null) {
                    existingPinResetHistory.setPinresetby(pinResetHistory.getPinresetby());
                }
                if (pinResetHistory.getPinreseton() != null) {
                    existingPinResetHistory.setPinreseton(pinResetHistory.getPinreseton());
                }
                if (pinResetHistory.getPinresetapprovedby() != null) {
                    existingPinResetHistory.setPinresetapprovedby(pinResetHistory.getPinresetapprovedby());
                }
                if (pinResetHistory.getPinresetapprovedon() != null) {
                    existingPinResetHistory.setPinresetapprovedon(pinResetHistory.getPinresetapprovedon());
                }
                if (pinResetHistory.getPinresetremarks() != null) {
                    existingPinResetHistory.setPinresetremarks(pinResetHistory.getPinresetremarks());
                }
                if (pinResetHistory.getBranchcode() != null) {
                    existingPinResetHistory.setBranchcode(pinResetHistory.getBranchcode());
                }

                return existingPinResetHistory;
            })
            .map(pinResetHistoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pinResetHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /pin-reset-histories} : get all the pinResetHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pinResetHistories in body.
     */
    @GetMapping("")
    public List<PinResetHistory> getAllPinResetHistories() {
        LOG.debug("REST request to get all PinResetHistories");
        return pinResetHistoryRepository.findAll();
    }

    /**
     * {@code GET  /pin-reset-histories/:id} : get the "id" pinResetHistory.
     *
     * @param id the id of the pinResetHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pinResetHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PinResetHistory> getPinResetHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to get PinResetHistory : {}", id);
        Optional<PinResetHistory> pinResetHistory = pinResetHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pinResetHistory);
    }

    /**
     * {@code DELETE  /pin-reset-histories/:id} : delete the "id" pinResetHistory.
     *
     * @param id the id of the pinResetHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePinResetHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete PinResetHistory : {}", id);
        pinResetHistoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
