package com.istl.app.web.rest;

import com.istl.app.domain.CHARGE;
import com.istl.app.repository.CHARGERepository;
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
 * REST controller for managing {@link com.istl.app.domain.CHARGE}.
 */
@RestController
@RequestMapping("/api/charges")
@Transactional
public class CHARGEResource {

    private static final Logger LOG = LoggerFactory.getLogger(CHARGEResource.class);

    private static final String ENTITY_NAME = "cHARGE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CHARGERepository cHARGERepository;

    public CHARGEResource(CHARGERepository cHARGERepository) {
        this.cHARGERepository = cHARGERepository;
    }

    /**
     * {@code POST  /charges} : Create a new cHARGE.
     *
     * @param cHARGE the cHARGE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cHARGE, or with status {@code 400 (Bad Request)} if the cHARGE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CHARGE> createCHARGE(@Valid @RequestBody CHARGE cHARGE) throws URISyntaxException {
        LOG.debug("REST request to save CHARGE : {}", cHARGE);
        if (cHARGE.getId() != null) {
            throw new BadRequestAlertException("A new cHARGE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cHARGE = cHARGERepository.save(cHARGE);
        return ResponseEntity.created(new URI("/api/charges/" + cHARGE.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cHARGE.getId().toString()))
            .body(cHARGE);
    }

    /**
     * {@code PUT  /charges/:id} : Updates an existing cHARGE.
     *
     * @param id the id of the cHARGE to save.
     * @param cHARGE the cHARGE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHARGE,
     * or with status {@code 400 (Bad Request)} if the cHARGE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cHARGE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CHARGE> updateCHARGE(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CHARGE cHARGE
    ) throws URISyntaxException {
        LOG.debug("REST request to update CHARGE : {}, {}", id, cHARGE);
        if (cHARGE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHARGE.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHARGERepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cHARGE = cHARGERepository.save(cHARGE);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHARGE.getId().toString()))
            .body(cHARGE);
    }

    /**
     * {@code PATCH  /charges/:id} : Partial updates given fields of an existing cHARGE, field will ignore if it is null
     *
     * @param id the id of the cHARGE to save.
     * @param cHARGE the cHARGE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHARGE,
     * or with status {@code 400 (Bad Request)} if the cHARGE is not valid,
     * or with status {@code 404 (Not Found)} if the cHARGE is not found,
     * or with status {@code 500 (Internal Server Error)} if the cHARGE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CHARGE> partialUpdateCHARGE(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CHARGE cHARGE
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CHARGE partially : {}, {}", id, cHARGE);
        if (cHARGE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHARGE.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHARGERepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CHARGE> result = cHARGERepository
            .findById(cHARGE.getId())
            .map(existingCHARGE -> {
                if (cHARGE.gettXNTYPE() != null) {
                    existingCHARGE.settXNTYPE(cHARGE.gettXNTYPE());
                }
                if (cHARGE.getfEEMODE() != null) {
                    existingCHARGE.setfEEMODE(cHARGE.getfEEMODE());
                }
                if (cHARGE.getaMOUNT() != null) {
                    existingCHARGE.setaMOUNT(cHARGE.getaMOUNT());
                }
                if (cHARGE.getdATECREATED() != null) {
                    existingCHARGE.setdATECREATED(cHARGE.getdATECREATED());
                }
                if (cHARGE.getcREATEDBY() != null) {
                    existingCHARGE.setcREATEDBY(cHARGE.getcREATEDBY());
                }
                if (cHARGE.getaPPROVED() != null) {
                    existingCHARGE.setaPPROVED(cHARGE.getaPPROVED());
                }
                if (cHARGE.getaPPROVEDBY() != null) {
                    existingCHARGE.setaPPROVEDBY(cHARGE.getaPPROVEDBY());
                }
                if (cHARGE.getcHANNEL() != null) {
                    existingCHARGE.setcHANNEL(cHARGE.getcHANNEL());
                }
                if (cHARGE.gettXNCODE() != null) {
                    existingCHARGE.settXNCODE(cHARGE.gettXNCODE());
                }
                if (cHARGE.getdESCRIPTION() != null) {
                    existingCHARGE.setdESCRIPTION(cHARGE.getdESCRIPTION());
                }
                if (cHARGE.getaPPROVEDDATE() != null) {
                    existingCHARGE.setaPPROVEDDATE(cHARGE.getaPPROVEDDATE());
                }

                return existingCHARGE;
            })
            .map(cHARGERepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHARGE.getId().toString())
        );
    }

    /**
     * {@code GET  /charges} : get all the cHARGES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cHARGES in body.
     */
    @GetMapping("")
    public List<CHARGE> getAllCHARGES() {
        LOG.debug("REST request to get all CHARGES");
        return cHARGERepository.findAll();
    }

    /**
     * {@code GET  /charges/:id} : get the "id" cHARGE.
     *
     * @param id the id of the cHARGE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cHARGE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CHARGE> getCHARGE(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CHARGE : {}", id);
        Optional<CHARGE> cHARGE = cHARGERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cHARGE);
    }

    /**
     * {@code DELETE  /charges/:id} : delete the "id" cHARGE.
     *
     * @param id the id of the cHARGE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCHARGE(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CHARGE : {}", id);
        cHARGERepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
