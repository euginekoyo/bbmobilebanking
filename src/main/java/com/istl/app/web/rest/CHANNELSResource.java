package com.istl.app.web.rest;

import com.istl.app.domain.CHANNELS;
import com.istl.app.repository.CHANNELSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.CHANNELS}.
 */
@RestController
@RequestMapping("/api/channels")
@Transactional
public class CHANNELSResource {

    private static final Logger LOG = LoggerFactory.getLogger(CHANNELSResource.class);

    private static final String ENTITY_NAME = "cHANNELS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CHANNELSRepository cHANNELSRepository;

    public CHANNELSResource(CHANNELSRepository cHANNELSRepository) {
        this.cHANNELSRepository = cHANNELSRepository;
    }

    /**
     * {@code POST  /channels} : Create a new cHANNELS.
     *
     * @param cHANNELS the cHANNELS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cHANNELS, or with status {@code 400 (Bad Request)} if the cHANNELS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CHANNELS> createCHANNELS(@Valid @RequestBody CHANNELS cHANNELS) throws URISyntaxException {
        LOG.debug("REST request to save CHANNELS : {}", cHANNELS);
        if (cHANNELS.getId() != null) {
            throw new BadRequestAlertException("A new cHANNELS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cHANNELS = cHANNELSRepository.save(cHANNELS);
        return ResponseEntity.created(new URI("/api/channels/" + cHANNELS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cHANNELS.getId().toString()))
            .body(cHANNELS);
    }

    /**
     * {@code PUT  /channels/:id} : Updates an existing cHANNELS.
     *
     * @param id the id of the cHANNELS to save.
     * @param cHANNELS the cHANNELS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHANNELS,
     * or with status {@code 400 (Bad Request)} if the cHANNELS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cHANNELS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CHANNELS> updateCHANNELS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CHANNELS cHANNELS
    ) throws URISyntaxException {
        LOG.debug("REST request to update CHANNELS : {}, {}", id, cHANNELS);
        if (cHANNELS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHANNELS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHANNELSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cHANNELS = cHANNELSRepository.save(cHANNELS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHANNELS.getId().toString()))
            .body(cHANNELS);
    }

    /**
     * {@code PATCH  /channels/:id} : Partial updates given fields of an existing cHANNELS, field will ignore if it is null
     *
     * @param id the id of the cHANNELS to save.
     * @param cHANNELS the cHANNELS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHANNELS,
     * or with status {@code 400 (Bad Request)} if the cHANNELS is not valid,
     * or with status {@code 404 (Not Found)} if the cHANNELS is not found,
     * or with status {@code 500 (Internal Server Error)} if the cHANNELS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CHANNELS> partialUpdateCHANNELS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CHANNELS cHANNELS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CHANNELS partially : {}, {}", id, cHANNELS);
        if (cHANNELS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHANNELS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHANNELSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CHANNELS> result = cHANNELSRepository
            .findById(cHANNELS.getId())
            .map(existingCHANNELS -> {
                if (cHANNELS.getcHANNEL() != null) {
                    existingCHANNELS.setcHANNEL(cHANNELS.getcHANNEL());
                }
                if (cHANNELS.getdESCRIPTION() != null) {
                    existingCHANNELS.setdESCRIPTION(cHANNELS.getdESCRIPTION());
                }
                if (cHANNELS.getbIN() != null) {
                    existingCHANNELS.setbIN(cHANNELS.getbIN());
                }

                return existingCHANNELS;
            })
            .map(cHANNELSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHANNELS.getId().toString())
        );
    }

    /**
     * {@code GET  /channels} : get all the cHANNELS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cHANNELS in body.
     */
    @GetMapping("")
    public List<CHANNELS> getAllCHANNELS() {
        LOG.debug("REST request to get all CHANNELS");
        return cHANNELSRepository.findAll();
    }

    /**
     * {@code GET  /channels/:id} : get the "id" cHANNELS.
     *
     * @param id the id of the cHANNELS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cHANNELS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CHANNELS> getCHANNELS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CHANNELS : {}", id);
        Optional<CHANNELS> cHANNELS = cHANNELSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cHANNELS);
    }

    /**
     * {@code DELETE  /channels/:id} : delete the "id" cHANNELS.
     *
     * @param id the id of the cHANNELS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCHANNELS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CHANNELS : {}", id);
        cHANNELSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
