package com.istl.app.web.rest;

import com.istl.app.domain.CUSTOMERACCOUNT;
import com.istl.app.repository.CUSTOMERACCOUNTRepository;
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
 * REST controller for managing {@link com.istl.app.domain.CUSTOMERACCOUNT}.
 */
@RestController
@RequestMapping("/api/customeraccounts")
@Transactional
public class CUSTOMERACCOUNTResource {

    private static final Logger LOG = LoggerFactory.getLogger(CUSTOMERACCOUNTResource.class);

    private static final String ENTITY_NAME = "cUSTOMERACCOUNT";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CUSTOMERACCOUNTRepository cUSTOMERACCOUNTRepository;

    public CUSTOMERACCOUNTResource(CUSTOMERACCOUNTRepository cUSTOMERACCOUNTRepository) {
        this.cUSTOMERACCOUNTRepository = cUSTOMERACCOUNTRepository;
    }

    /**
     * {@code POST  /customeraccounts} : Create a new cUSTOMERACCOUNT.
     *
     * @param cUSTOMERACCOUNT the cUSTOMERACCOUNT to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cUSTOMERACCOUNT, or with status {@code 400 (Bad Request)} if the cUSTOMERACCOUNT has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CUSTOMERACCOUNT> createCUSTOMERACCOUNT(@Valid @RequestBody CUSTOMERACCOUNT cUSTOMERACCOUNT)
        throws URISyntaxException {
        LOG.debug("REST request to save CUSTOMERACCOUNT : {}", cUSTOMERACCOUNT);
        if (cUSTOMERACCOUNT.getId() != null) {
            throw new BadRequestAlertException("A new cUSTOMERACCOUNT cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.save(cUSTOMERACCOUNT);
        return ResponseEntity.created(new URI("/api/customeraccounts/" + cUSTOMERACCOUNT.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cUSTOMERACCOUNT.getId().toString()))
            .body(cUSTOMERACCOUNT);
    }

    /**
     * {@code PUT  /customeraccounts/:id} : Updates an existing cUSTOMERACCOUNT.
     *
     * @param id the id of the cUSTOMERACCOUNT to save.
     * @param cUSTOMERACCOUNT the cUSTOMERACCOUNT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cUSTOMERACCOUNT,
     * or with status {@code 400 (Bad Request)} if the cUSTOMERACCOUNT is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cUSTOMERACCOUNT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMERACCOUNT> updateCUSTOMERACCOUNT(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CUSTOMERACCOUNT cUSTOMERACCOUNT
    ) throws URISyntaxException {
        LOG.debug("REST request to update CUSTOMERACCOUNT : {}, {}", id, cUSTOMERACCOUNT);
        if (cUSTOMERACCOUNT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cUSTOMERACCOUNT.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cUSTOMERACCOUNTRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.save(cUSTOMERACCOUNT);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cUSTOMERACCOUNT.getId().toString()))
            .body(cUSTOMERACCOUNT);
    }

    /**
     * {@code PATCH  /customeraccounts/:id} : Partial updates given fields of an existing cUSTOMERACCOUNT, field will ignore if it is null
     *
     * @param id the id of the cUSTOMERACCOUNT to save.
     * @param cUSTOMERACCOUNT the cUSTOMERACCOUNT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cUSTOMERACCOUNT,
     * or with status {@code 400 (Bad Request)} if the cUSTOMERACCOUNT is not valid,
     * or with status {@code 404 (Not Found)} if the cUSTOMERACCOUNT is not found,
     * or with status {@code 500 (Internal Server Error)} if the cUSTOMERACCOUNT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CUSTOMERACCOUNT> partialUpdateCUSTOMERACCOUNT(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CUSTOMERACCOUNT cUSTOMERACCOUNT
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CUSTOMERACCOUNT partially : {}, {}", id, cUSTOMERACCOUNT);
        if (cUSTOMERACCOUNT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cUSTOMERACCOUNT.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cUSTOMERACCOUNTRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CUSTOMERACCOUNT> result = cUSTOMERACCOUNTRepository
            .findById(cUSTOMERACCOUNT.getId())
            .map(existingCUSTOMERACCOUNT -> {
                if (cUSTOMERACCOUNT.getcUSTOMERID() != null) {
                    existingCUSTOMERACCOUNT.setcUSTOMERID(cUSTOMERACCOUNT.getcUSTOMERID());
                }
                if (cUSTOMERACCOUNT.getaCCOUNTNUMBER() != null) {
                    existingCUSTOMERACCOUNT.setaCCOUNTNUMBER(cUSTOMERACCOUNT.getaCCOUNTNUMBER());
                }
                if (cUSTOMERACCOUNT.getaCCOUNTCLASS() != null) {
                    existingCUSTOMERACCOUNT.setaCCOUNTCLASS(cUSTOMERACCOUNT.getaCCOUNTCLASS());
                }
                if (cUSTOMERACCOUNT.getcUSTOMERNUMBER() != null) {
                    existingCUSTOMERACCOUNT.setcUSTOMERNUMBER(cUSTOMERACCOUNT.getcUSTOMERNUMBER());
                }
                if (cUSTOMERACCOUNT.getcIF() != null) {
                    existingCUSTOMERACCOUNT.setcIF(cUSTOMERACCOUNT.getcIF());
                }
                if (cUSTOMERACCOUNT.gettIMELINKED() != null) {
                    existingCUSTOMERACCOUNT.settIMELINKED(cUSTOMERACCOUNT.gettIMELINKED());
                }
                if (cUSTOMERACCOUNT.getbLOCKED() != null) {
                    existingCUSTOMERACCOUNT.setbLOCKED(cUSTOMERACCOUNT.getbLOCKED());
                }
                if (cUSTOMERACCOUNT.getsTOPPED() != null) {
                    existingCUSTOMERACCOUNT.setsTOPPED(cUSTOMERACCOUNT.getsTOPPED());
                }
                if (cUSTOMERACCOUNT.getdORMANT() != null) {
                    existingCUSTOMERACCOUNT.setdORMANT(cUSTOMERACCOUNT.getdORMANT());
                }

                return existingCUSTOMERACCOUNT;
            })
            .map(cUSTOMERACCOUNTRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cUSTOMERACCOUNT.getId().toString())
        );
    }

    /**
     * {@code GET  /customeraccounts} : get all the cUSTOMERACCOUNTS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cUSTOMERACCOUNTS in body.
     */
    @GetMapping("")
    public List<CUSTOMERACCOUNT> getAllCUSTOMERACCOUNTS() {
        LOG.debug("REST request to get all CUSTOMERACCOUNTS");
        return cUSTOMERACCOUNTRepository.findAll();
    }

    /**
     * {@code GET  /customeraccounts/:id} : get the "id" cUSTOMERACCOUNT.
     *
     * @param id the id of the cUSTOMERACCOUNT to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cUSTOMERACCOUNT, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMERACCOUNT> getCUSTOMERACCOUNT(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CUSTOMERACCOUNT : {}", id);
        Optional<CUSTOMERACCOUNT> cUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cUSTOMERACCOUNT);
    }

    /**
     * {@code DELETE  /customeraccounts/:id} : delete the "id" cUSTOMERACCOUNT.
     *
     * @param id the id of the cUSTOMERACCOUNT to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCUSTOMERACCOUNT(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CUSTOMERACCOUNT : {}", id);
        cUSTOMERACCOUNTRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
