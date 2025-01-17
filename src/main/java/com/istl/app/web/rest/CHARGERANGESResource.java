package com.istl.app.web.rest;

import com.istl.app.domain.CHARGERANGES;
import com.istl.app.repository.CHARGERANGESRepository;
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
 * REST controller for managing {@link com.istl.app.domain.CHARGERANGES}.
 */
@RestController
@RequestMapping("/api/chargeranges")
@Transactional
public class CHARGERANGESResource {

    private static final Logger LOG = LoggerFactory.getLogger(CHARGERANGESResource.class);

    private static final String ENTITY_NAME = "cHARGERANGES";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CHARGERANGESRepository cHARGERANGESRepository;

    public CHARGERANGESResource(CHARGERANGESRepository cHARGERANGESRepository) {
        this.cHARGERANGESRepository = cHARGERANGESRepository;
    }

    /**
     * {@code POST  /chargeranges} : Create a new cHARGERANGES.
     *
     * @param cHARGERANGES the cHARGERANGES to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cHARGERANGES, or with status {@code 400 (Bad Request)} if the cHARGERANGES has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CHARGERANGES> createCHARGERANGES(@Valid @RequestBody CHARGERANGES cHARGERANGES) throws URISyntaxException {
        LOG.debug("REST request to save CHARGERANGES : {}", cHARGERANGES);
        if (cHARGERANGES.getId() != null) {
            throw new BadRequestAlertException("A new cHARGERANGES cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cHARGERANGES = cHARGERANGESRepository.save(cHARGERANGES);
        return ResponseEntity.created(new URI("/api/chargeranges/" + cHARGERANGES.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cHARGERANGES.getId().toString()))
            .body(cHARGERANGES);
    }

    /**
     * {@code PUT  /chargeranges/:id} : Updates an existing cHARGERANGES.
     *
     * @param id the id of the cHARGERANGES to save.
     * @param cHARGERANGES the cHARGERANGES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHARGERANGES,
     * or with status {@code 400 (Bad Request)} if the cHARGERANGES is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cHARGERANGES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CHARGERANGES> updateCHARGERANGES(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CHARGERANGES cHARGERANGES
    ) throws URISyntaxException {
        LOG.debug("REST request to update CHARGERANGES : {}, {}", id, cHARGERANGES);
        if (cHARGERANGES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHARGERANGES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHARGERANGESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cHARGERANGES = cHARGERANGESRepository.save(cHARGERANGES);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHARGERANGES.getId().toString()))
            .body(cHARGERANGES);
    }

    /**
     * {@code PATCH  /chargeranges/:id} : Partial updates given fields of an existing cHARGERANGES, field will ignore if it is null
     *
     * @param id the id of the cHARGERANGES to save.
     * @param cHARGERANGES the cHARGERANGES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cHARGERANGES,
     * or with status {@code 400 (Bad Request)} if the cHARGERANGES is not valid,
     * or with status {@code 404 (Not Found)} if the cHARGERANGES is not found,
     * or with status {@code 500 (Internal Server Error)} if the cHARGERANGES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CHARGERANGES> partialUpdateCHARGERANGES(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CHARGERANGES cHARGERANGES
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CHARGERANGES partially : {}, {}", id, cHARGERANGES);
        if (cHARGERANGES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cHARGERANGES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cHARGERANGESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CHARGERANGES> result = cHARGERANGESRepository
            .findById(cHARGERANGES.getId())
            .map(existingCHARGERANGES -> {
                if (cHARGERANGES.getbILLERID() != null) {
                    existingCHARGERANGES.setbILLERID(cHARGERANGES.getbILLERID());
                }
                if (cHARGERANGES.getpROCESSINGCODE() != null) {
                    existingCHARGERANGES.setpROCESSINGCODE(cHARGERANGES.getpROCESSINGCODE());
                }
                if (cHARGERANGES.getmAX() != null) {
                    existingCHARGERANGES.setmAX(cHARGERANGES.getmAX());
                }
                if (cHARGERANGES.getmIN() != null) {
                    existingCHARGERANGES.setmIN(cHARGERANGES.getmIN());
                }
                if (cHARGERANGES.getaMOUNT() != null) {
                    existingCHARGERANGES.setaMOUNT(cHARGERANGES.getaMOUNT());
                }
                if (cHARGERANGES.getcREATEDBY() != null) {
                    existingCHARGERANGES.setcREATEDBY(cHARGERANGES.getcREATEDBY());
                }
                if (cHARGERANGES.getaPPROVEDBY() != null) {
                    existingCHARGERANGES.setaPPROVEDBY(cHARGERANGES.getaPPROVEDBY());
                }
                if (cHARGERANGES.getcREATEDAT() != null) {
                    existingCHARGERANGES.setcREATEDAT(cHARGERANGES.getcREATEDAT());
                }
                if (cHARGERANGES.getaPPROVEDON() != null) {
                    existingCHARGERANGES.setaPPROVEDON(cHARGERANGES.getaPPROVEDON());
                }
                if (cHARGERANGES.getaPPROVED() != null) {
                    existingCHARGERANGES.setaPPROVED(cHARGERANGES.getaPPROVED());
                }
                if (cHARGERANGES.getdECLINED() != null) {
                    existingCHARGERANGES.setdECLINED(cHARGERANGES.getdECLINED());
                }
                if (cHARGERANGES.getdECLINEDBY() != null) {
                    existingCHARGERANGES.setdECLINEDBY(cHARGERANGES.getdECLINEDBY());
                }
                if (cHARGERANGES.getcHARGEID() != null) {
                    existingCHARGERANGES.setcHARGEID(cHARGERANGES.getcHARGEID());
                }

                return existingCHARGERANGES;
            })
            .map(cHARGERANGESRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cHARGERANGES.getId().toString())
        );
    }

    /**
     * {@code GET  /chargeranges} : get all the cHARGERANGES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cHARGERANGES in body.
     */
    @GetMapping("")
    public List<CHARGERANGES> getAllCHARGERANGES() {
        LOG.debug("REST request to get all CHARGERANGES");
        return cHARGERANGESRepository.findAll();
    }

    /**
     * {@code GET  /chargeranges/:id} : get the "id" cHARGERANGES.
     *
     * @param id the id of the cHARGERANGES to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cHARGERANGES, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CHARGERANGES> getCHARGERANGES(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CHARGERANGES : {}", id);
        Optional<CHARGERANGES> cHARGERANGES = cHARGERANGESRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cHARGERANGES);
    }

    /**
     * {@code DELETE  /chargeranges/:id} : delete the "id" cHARGERANGES.
     *
     * @param id the id of the cHARGERANGES to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCHARGERANGES(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CHARGERANGES : {}", id);
        cHARGERANGESRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
