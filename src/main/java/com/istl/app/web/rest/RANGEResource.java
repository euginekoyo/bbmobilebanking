package com.istl.app.web.rest;

import com.istl.app.domain.Range;
import com.istl.app.repository.RangeRepository;
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
 * REST controller for managing {@link com.istl.app.domain.Range}.
 */
@RestController
@RequestMapping("/api/ranges")
@Transactional
public class RangeResource {

    private static final Logger LOG = LoggerFactory.getLogger(RangeResource.class);

    private static final String ENTITY_NAME = "range";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RangeRepository rangeRepository;

    public RangeResource(RangeRepository rangeRepository) {
        this.rangeRepository = rangeRepository;
    }

    /**
     * {@code POST  /ranges} : Create a new range.
     *
     * @param range the range to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new range, or with status {@code 400 (Bad Request)} if the range has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Range> createRange(@Valid @RequestBody Range range) throws URISyntaxException {
        LOG.debug("REST request to save Range : {}", range);
        if (range.getId() != null) {
            throw new BadRequestAlertException("A new range cannot already have an ID", ENTITY_NAME, "idexists");
        }
        range = rangeRepository.save(range);
        return ResponseEntity.created(new URI("/api/ranges/" + range.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, range.getId().toString()))
            .body(range);
    }

    /**
     * {@code PUT  /ranges/:id} : Updates an existing range.
     *
     * @param id the id of the range to save.
     * @param range the range to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated range,
     * or with status {@code 400 (Bad Request)} if the range is not valid,
     * or with status {@code 500 (Internal Server Error)} if the range couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Range> updateRange(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Range range)
        throws URISyntaxException {
        LOG.debug("REST request to update Range : {}, {}", id, range);
        if (range.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, range.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        range = rangeRepository.save(range);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, range.getId().toString()))
            .body(range);
    }

    /**
     * {@code PATCH  /ranges/:id} : Partial updates given fields of an existing range, field will ignore if it is null
     *
     * @param id the id of the range to save.
     * @param range the range to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated range,
     * or with status {@code 400 (Bad Request)} if the range is not valid,
     * or with status {@code 404 (Not Found)} if the range is not found,
     * or with status {@code 500 (Internal Server Error)} if the range couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Range> partialUpdateRange(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Range range
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Range partially : {}, {}", id, range);
        if (range.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, range.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rangeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Range> result = rangeRepository
            .findById(range.getId())
            .map(existingRange -> {
                if (range.getRangefrom() != null) {
                    existingRange.setRangefrom(range.getRangefrom());
                }
                if (range.getRangeto() != null) {
                    existingRange.setRangeto(range.getRangeto());
                }
                if (range.getAmount() != null) {
                    existingRange.setAmount(range.getAmount());
                }
                if (range.getTxntype() != null) {
                    existingRange.setTxntype(range.getTxntype());
                }
                if (range.getTxncode() != null) {
                    existingRange.setTxncode(range.getTxncode());
                }
                if (range.getChargeid() != null) {
                    existingRange.setChargeid(range.getChargeid());
                }
                if (range.getChannel() != null) {
                    existingRange.setChannel(range.getChannel());
                }

                return existingRange;
            })
            .map(rangeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, range.getId().toString())
        );
    }

    /**
     * {@code GET  /ranges} : get all the ranges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ranges in body.
     */
    @GetMapping("")
    public List<Range> getAllRanges() {
        LOG.debug("REST request to get all Ranges");
        return rangeRepository.findAll();
    }

    /**
     * {@code GET  /ranges/:id} : get the "id" range.
     *
     * @param id the id of the range to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the range, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Range> getRange(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Range : {}", id);
        Optional<Range> range = rangeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(range);
    }

    /**
     * {@code DELETE  /ranges/:id} : delete the "id" range.
     *
     * @param id the id of the range to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRange(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Range : {}", id);
        rangeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
