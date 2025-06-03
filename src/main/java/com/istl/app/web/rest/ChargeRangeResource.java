package com.istl.app.web.rest;

import com.istl.app.domain.mobileapp.ChargeRange;
import com.istl.app.repository.mobileapp.ChargeRangeRepository;
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
 * REST controller for managing {@link com.istl.app.domain.mobileapp.ChargeRange}.
 */
@RestController
@RequestMapping("/api/charge-ranges")
@Transactional
public class ChargeRangeResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChargeRangeResource.class);

    private static final String ENTITY_NAME = "chargeRange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChargeRangeRepository chargeRangeRepository;

    public ChargeRangeResource(ChargeRangeRepository chargeRangeRepository) {
        this.chargeRangeRepository = chargeRangeRepository;
    }

    /**
     * {@code POST  /charge-ranges} : Create a new chargeRange.
     *
     * @param chargeRange the chargeRange to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chargeRange, or with status {@code 400 (Bad Request)} if the chargeRange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChargeRange> createChargeRange(@Valid @RequestBody ChargeRange chargeRange) throws URISyntaxException {
        LOG.debug("REST request to save ChargeRange : {}", chargeRange);
        if (chargeRange.getId() != null) {
            throw new BadRequestAlertException("A new chargeRange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        chargeRange.setCreatedby(currentPrincipalName);
        chargeRange.setCreatedat(Instant.now().toString());
        chargeRange.setApproved(0L);

        chargeRange = chargeRangeRepository.save(chargeRange);
        return ResponseEntity.created(new URI("/api/charge-ranges/" + chargeRange.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chargeRange.getId().toString()))
            .body(chargeRange);
    }

    /**
     * {@code PUT  /charge-ranges/:id} : Updates an existing chargeRange.
     *
     * @param id the id of the chargeRange to save.
     * @param chargeRange the chargeRange to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeRange,
     * or with status {@code 400 (Bad Request)} if the chargeRange is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chargeRange couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChargeRange> updateChargeRange(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ChargeRange chargeRange
    ) throws URISyntaxException {
        LOG.debug("REST request to update ChargeRange : {}, {}", id, chargeRange);
        if (chargeRange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeRange.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeRangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chargeRange = chargeRangeRepository.save(chargeRange);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeRange.getId().toString()))
            .body(chargeRange);
    }

    /**
     * {@code PATCH  /charge-ranges/:id} : Partial updates given fields of an existing chargeRange, field will ignore if it is null
     *
     * @param id the id of the chargeRange to save.
     * @param chargeRange the chargeRange to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeRange,
     * or with status {@code 400 (Bad Request)} if the chargeRange is not valid,
     * or with status {@code 404 (Not Found)} if the chargeRange is not found,
     * or with status {@code 500 (Internal Server Error)} if the chargeRange couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChargeRange> partialUpdateChargeRange(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChargeRange chargeRange
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ChargeRange partially : {}, {}", id, chargeRange);
        if (chargeRange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeRange.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeRangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChargeRange> result = chargeRangeRepository
            .findById(chargeRange.getId())
            .map(existingChargeRange -> {
                if (chargeRange.getBillerid() != null) {
                    existingChargeRange.setBillerid(chargeRange.getBillerid());
                }
                if (chargeRange.getProcessingcode() != null) {
                    existingChargeRange.setProcessingcode(chargeRange.getProcessingcode());
                }
                if (chargeRange.getMax() != null) {
                    existingChargeRange.setMax(chargeRange.getMax());
                }
                if (chargeRange.getMin() != null) {
                    existingChargeRange.setMin(chargeRange.getMin());
                }
                if (chargeRange.getAmount() != null) {
                    existingChargeRange.setAmount(chargeRange.getAmount());
                }
                if (chargeRange.getCreatedby() != null) {
                    existingChargeRange.setCreatedby(chargeRange.getCreatedby());
                }
                if (chargeRange.getApprovedby() != null) {
                    existingChargeRange.setApprovedby(chargeRange.getApprovedby());
                }
                if (chargeRange.getCreatedat() != null) {
                    existingChargeRange.setCreatedat(chargeRange.getCreatedat());
                }
                if (chargeRange.getApprovedon() != null) {
                    existingChargeRange.setApprovedon(chargeRange.getApprovedon());
                }
                if (chargeRange.getApproved() != null) {
                    existingChargeRange.setApproved(chargeRange.getApproved());
                }
                if (chargeRange.getDeclined() != null) {
                    existingChargeRange.setDeclined(chargeRange.getDeclined());
                }
                if (chargeRange.getDeclinedby() != null) {
                    existingChargeRange.setDeclinedby(chargeRange.getDeclinedby());
                }
                if (chargeRange.getChargeid() != null) {
                    existingChargeRange.setChargeid(chargeRange.getChargeid());
                }

                return existingChargeRange;
            })
            .map(chargeRangeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeRange.getId().toString())
        );
    }

    @PatchMapping(value = "/approve/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChargeRange> approveUpdateChargeRange(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChargeRange chargeRange
    ) throws URISyntaxException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LOG.debug("REST request to partial update ChargeRange partially : {}, {}", id, chargeRange);
        if (chargeRange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeRange.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeRangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChargeRange> result = chargeRangeRepository
            .findById(chargeRange.getId())
            .map(existingChargeRange -> {
                existingChargeRange.setApprovedby(currentPrincipalName);

                existingChargeRange.setApprovedon(Instant.now().toString());

                existingChargeRange.setApproved(0L);

                return existingChargeRange;
            })
            .map(chargeRangeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeRange.getId().toString())
        );
    }

    @PatchMapping(value = "/reject/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChargeRange> rejectUpdateChargeRange(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ChargeRange chargeRange
    ) throws URISyntaxException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LOG.debug("REST request to partial update ChargeRange partially : {}, {}", id, chargeRange);
        if (chargeRange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chargeRange.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chargeRangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChargeRange> result = chargeRangeRepository
            .findById(chargeRange.getId())
            .map(existingChargeRange -> {
                existingChargeRange.setApproved(2L);

                existingChargeRange.setDeclined(2L);

                existingChargeRange.setDeclinedby(currentPrincipalName);

                existingChargeRange.setApprovedon(Instant.now().toString());

                return existingChargeRange;
            })
            .map(chargeRangeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeRange.getId().toString())
        );
    }

    /**
     * {@code GET  /charge-ranges} : get all the chargeRanges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chargeRanges in body.
     */
    @GetMapping("")
    public List<ChargeRange> getAllChargeRanges() {
        LOG.debug("REST request to get all ChargeRanges");
        return chargeRangeRepository.findAll();
    }

    /**
     * {@code GET  /charge-ranges/:id} : get the "id" chargeRange.
     *
     * @param id the id of the chargeRange to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chargeRange, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChargeRange> getChargeRange(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ChargeRange : {}", id);
        Optional<ChargeRange> chargeRange = chargeRangeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chargeRange);
    }

    /**
     * {@code DELETE  /charge-ranges/:id} : delete the "id" chargeRange.
     *
     * @param id the id of the chargeRange to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChargeRange(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ChargeRange : {}", id);
        chargeRangeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
