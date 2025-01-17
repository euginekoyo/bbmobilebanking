package com.istl.app.web.rest;

import com.istl.app.domain.MESSAGETEMPLATES;
import com.istl.app.repository.MESSAGETEMPLATESRepository;
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
 * REST controller for managing {@link com.istl.app.domain.MESSAGETEMPLATES}.
 */
@RestController
@RequestMapping("/api/messagetemplates")
@Transactional
public class MESSAGETEMPLATESResource {

    private static final Logger LOG = LoggerFactory.getLogger(MESSAGETEMPLATESResource.class);

    private static final String ENTITY_NAME = "mESSAGETEMPLATES";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MESSAGETEMPLATESRepository mESSAGETEMPLATESRepository;

    public MESSAGETEMPLATESResource(MESSAGETEMPLATESRepository mESSAGETEMPLATESRepository) {
        this.mESSAGETEMPLATESRepository = mESSAGETEMPLATESRepository;
    }

    /**
     * {@code POST  /messagetemplates} : Create a new mESSAGETEMPLATES.
     *
     * @param mESSAGETEMPLATES the mESSAGETEMPLATES to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mESSAGETEMPLATES, or with status {@code 400 (Bad Request)} if the mESSAGETEMPLATES has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MESSAGETEMPLATES> createMESSAGETEMPLATES(@Valid @RequestBody MESSAGETEMPLATES mESSAGETEMPLATES)
        throws URISyntaxException {
        LOG.debug("REST request to save MESSAGETEMPLATES : {}", mESSAGETEMPLATES);
        if (mESSAGETEMPLATES.getId() != null) {
            throw new BadRequestAlertException("A new mESSAGETEMPLATES cannot already have an ID", ENTITY_NAME, "idexists");
        }
        mESSAGETEMPLATES = mESSAGETEMPLATESRepository.save(mESSAGETEMPLATES);
        return ResponseEntity.created(new URI("/api/messagetemplates/" + mESSAGETEMPLATES.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, mESSAGETEMPLATES.getId().toString()))
            .body(mESSAGETEMPLATES);
    }

    /**
     * {@code PUT  /messagetemplates/:id} : Updates an existing mESSAGETEMPLATES.
     *
     * @param id the id of the mESSAGETEMPLATES to save.
     * @param mESSAGETEMPLATES the mESSAGETEMPLATES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mESSAGETEMPLATES,
     * or with status {@code 400 (Bad Request)} if the mESSAGETEMPLATES is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mESSAGETEMPLATES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MESSAGETEMPLATES> updateMESSAGETEMPLATES(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MESSAGETEMPLATES mESSAGETEMPLATES
    ) throws URISyntaxException {
        LOG.debug("REST request to update MESSAGETEMPLATES : {}, {}", id, mESSAGETEMPLATES);
        if (mESSAGETEMPLATES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mESSAGETEMPLATES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mESSAGETEMPLATESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        mESSAGETEMPLATES = mESSAGETEMPLATESRepository.save(mESSAGETEMPLATES);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mESSAGETEMPLATES.getId().toString()))
            .body(mESSAGETEMPLATES);
    }

    /**
     * {@code PATCH  /messagetemplates/:id} : Partial updates given fields of an existing mESSAGETEMPLATES, field will ignore if it is null
     *
     * @param id the id of the mESSAGETEMPLATES to save.
     * @param mESSAGETEMPLATES the mESSAGETEMPLATES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mESSAGETEMPLATES,
     * or with status {@code 400 (Bad Request)} if the mESSAGETEMPLATES is not valid,
     * or with status {@code 404 (Not Found)} if the mESSAGETEMPLATES is not found,
     * or with status {@code 500 (Internal Server Error)} if the mESSAGETEMPLATES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MESSAGETEMPLATES> partialUpdateMESSAGETEMPLATES(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MESSAGETEMPLATES mESSAGETEMPLATES
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MESSAGETEMPLATES partially : {}, {}", id, mESSAGETEMPLATES);
        if (mESSAGETEMPLATES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mESSAGETEMPLATES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mESSAGETEMPLATESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MESSAGETEMPLATES> result = mESSAGETEMPLATESRepository
            .findById(mESSAGETEMPLATES.getId())
            .map(existingMESSAGETEMPLATES -> {
                if (mESSAGETEMPLATES.getmESSAGETYPE() != null) {
                    existingMESSAGETEMPLATES.setmESSAGETYPE(mESSAGETEMPLATES.getmESSAGETYPE());
                }
                if (mESSAGETEMPLATES.getdESCRIPTION() != null) {
                    existingMESSAGETEMPLATES.setdESCRIPTION(mESSAGETEMPLATES.getdESCRIPTION());
                }
                if (mESSAGETEMPLATES.getmESSAGEENGLISH() != null) {
                    existingMESSAGETEMPLATES.setmESSAGEENGLISH(mESSAGETEMPLATES.getmESSAGEENGLISH());
                }
                if (mESSAGETEMPLATES.getmESSAGESOMALI() != null) {
                    existingMESSAGETEMPLATES.setmESSAGESOMALI(mESSAGETEMPLATES.getmESSAGESOMALI());
                }
                if (mESSAGETEMPLATES.getcREATEDON() != null) {
                    existingMESSAGETEMPLATES.setcREATEDON(mESSAGETEMPLATES.getcREATEDON());
                }

                return existingMESSAGETEMPLATES;
            })
            .map(mESSAGETEMPLATESRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mESSAGETEMPLATES.getId().toString())
        );
    }

    /**
     * {@code GET  /messagetemplates} : get all the mESSAGETEMPLATES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mESSAGETEMPLATES in body.
     */
    @GetMapping("")
    public List<MESSAGETEMPLATES> getAllMESSAGETEMPLATES() {
        LOG.debug("REST request to get all MESSAGETEMPLATES");
        return mESSAGETEMPLATESRepository.findAll();
    }

    /**
     * {@code GET  /messagetemplates/:id} : get the "id" mESSAGETEMPLATES.
     *
     * @param id the id of the mESSAGETEMPLATES to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mESSAGETEMPLATES, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MESSAGETEMPLATES> getMESSAGETEMPLATES(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MESSAGETEMPLATES : {}", id);
        Optional<MESSAGETEMPLATES> mESSAGETEMPLATES = mESSAGETEMPLATESRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mESSAGETEMPLATES);
    }

    /**
     * {@code DELETE  /messagetemplates/:id} : delete the "id" mESSAGETEMPLATES.
     *
     * @param id the id of the mESSAGETEMPLATES to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMESSAGETEMPLATES(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MESSAGETEMPLATES : {}", id);
        mESSAGETEMPLATESRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
