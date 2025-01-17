package com.istl.app.web.rest;

import com.istl.app.domain.LIMITS;
import com.istl.app.repository.LIMITSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.LIMITS}.
 */
@RestController
@RequestMapping("/api/limits")
@Transactional
public class LIMITSResource {

    private static final Logger LOG = LoggerFactory.getLogger(LIMITSResource.class);

    private static final String ENTITY_NAME = "lIMITS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LIMITSRepository lIMITSRepository;

    public LIMITSResource(LIMITSRepository lIMITSRepository) {
        this.lIMITSRepository = lIMITSRepository;
    }

    /**
     * {@code POST  /limits} : Create a new lIMITS.
     *
     * @param lIMITS the lIMITS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lIMITS, or with status {@code 400 (Bad Request)} if the lIMITS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LIMITS> createLIMITS(@Valid @RequestBody LIMITS lIMITS) throws URISyntaxException {
        LOG.debug("REST request to save LIMITS : {}", lIMITS);
        if (lIMITS.getId() != null) {
            throw new BadRequestAlertException("A new lIMITS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        lIMITS = lIMITSRepository.save(lIMITS);
        return ResponseEntity.created(new URI("/api/limits/" + lIMITS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, lIMITS.getId().toString()))
            .body(lIMITS);
    }

    /**
     * {@code PUT  /limits/:id} : Updates an existing lIMITS.
     *
     * @param id the id of the lIMITS to save.
     * @param lIMITS the lIMITS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lIMITS,
     * or with status {@code 400 (Bad Request)} if the lIMITS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lIMITS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LIMITS> updateLIMITS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LIMITS lIMITS
    ) throws URISyntaxException {
        LOG.debug("REST request to update LIMITS : {}, {}", id, lIMITS);
        if (lIMITS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lIMITS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lIMITSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        lIMITS = lIMITSRepository.save(lIMITS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lIMITS.getId().toString()))
            .body(lIMITS);
    }

    /**
     * {@code PATCH  /limits/:id} : Partial updates given fields of an existing lIMITS, field will ignore if it is null
     *
     * @param id the id of the lIMITS to save.
     * @param lIMITS the lIMITS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lIMITS,
     * or with status {@code 400 (Bad Request)} if the lIMITS is not valid,
     * or with status {@code 404 (Not Found)} if the lIMITS is not found,
     * or with status {@code 500 (Internal Server Error)} if the lIMITS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LIMITS> partialUpdateLIMITS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LIMITS lIMITS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LIMITS partially : {}, {}", id, lIMITS);
        if (lIMITS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lIMITS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lIMITSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LIMITS> result = lIMITSRepository
            .findById(lIMITS.getId())
            .map(existingLIMITS -> {
                if (lIMITS.gettRANSACTIONTYPE() != null) {
                    existingLIMITS.settRANSACTIONTYPE(lIMITS.gettRANSACTIONTYPE());
                }
                if (lIMITS.getpROCODE() != null) {
                    existingLIMITS.setpROCODE(lIMITS.getpROCODE());
                }
                if (lIMITS.getcHANNEL() != null) {
                    existingLIMITS.setcHANNEL(lIMITS.getcHANNEL());
                }
                if (lIMITS.gettRANSACTIONLIMIT() != null) {
                    existingLIMITS.settRANSACTIONLIMIT(lIMITS.gettRANSACTIONLIMIT());
                }
                if (lIMITS.getdAILYLIMIT() != null) {
                    existingLIMITS.setdAILYLIMIT(lIMITS.getdAILYLIMIT());
                }
                if (lIMITS.getrEGISTEREDBY() != null) {
                    existingLIMITS.setrEGISTEREDBY(lIMITS.getrEGISTEREDBY());
                }
                if (lIMITS.getrEGISTEREDDATE() != null) {
                    existingLIMITS.setrEGISTEREDDATE(lIMITS.getrEGISTEREDDATE());
                }
                if (lIMITS.getaPPROVED() != null) {
                    existingLIMITS.setaPPROVED(lIMITS.getaPPROVED());
                }
                if (lIMITS.getaPPROVEDBY() != null) {
                    existingLIMITS.setaPPROVEDBY(lIMITS.getaPPROVEDBY());
                }
                if (lIMITS.getaPPROVEDDATE() != null) {
                    existingLIMITS.setaPPROVEDDATE(lIMITS.getaPPROVEDDATE());
                }
                if (lIMITS.getuPDATEDBY() != null) {
                    existingLIMITS.setuPDATEDBY(lIMITS.getuPDATEDBY());
                }
                if (lIMITS.getuPDATEDDATE() != null) {
                    existingLIMITS.setuPDATEDDATE(lIMITS.getuPDATEDDATE());
                }
                if (lIMITS.getrEWORK() != null) {
                    existingLIMITS.setrEWORK(lIMITS.getrEWORK());
                }
                if (lIMITS.getrEWORKBY() != null) {
                    existingLIMITS.setrEWORKBY(lIMITS.getrEWORKBY());
                }
                if (lIMITS.getsESSIONID() != null) {
                    existingLIMITS.setsESSIONID(lIMITS.getsESSIONID());
                }

                return existingLIMITS;
            })
            .map(lIMITSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lIMITS.getId().toString())
        );
    }

    /**
     * {@code GET  /limits} : get all the lIMITS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lIMITS in body.
     */
    @GetMapping("")
    public List<LIMITS> getAllLIMITS() {
        LOG.debug("REST request to get all LIMITS");
        return lIMITSRepository.findAll();
    }

    /**
     * {@code GET  /limits/:id} : get the "id" lIMITS.
     *
     * @param id the id of the lIMITS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lIMITS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LIMITS> getLIMITS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LIMITS : {}", id);
        Optional<LIMITS> lIMITS = lIMITSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lIMITS);
    }

    /**
     * {@code DELETE  /limits/:id} : delete the "id" lIMITS.
     *
     * @param id the id of the lIMITS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLIMITS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LIMITS : {}", id);
        lIMITSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
