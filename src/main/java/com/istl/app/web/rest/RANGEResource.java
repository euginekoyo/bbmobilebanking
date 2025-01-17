package com.istl.app.web.rest;

import com.istl.app.domain.RANGE;
import com.istl.app.repository.RANGERepository;
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
 * REST controller for managing {@link com.istl.app.domain.RANGE}.
 */
@RestController
@RequestMapping("/api/ranges")
@Transactional
public class RANGEResource {

    private static final Logger LOG = LoggerFactory.getLogger(RANGEResource.class);

    private static final String ENTITY_NAME = "rANGE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RANGERepository rANGERepository;

    public RANGEResource(RANGERepository rANGERepository) {
        this.rANGERepository = rANGERepository;
    }

    /**
     * {@code POST  /ranges} : Create a new rANGE.
     *
     * @param rANGE the rANGE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rANGE, or with status {@code 400 (Bad Request)} if the rANGE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RANGE> createRANGE(@Valid @RequestBody RANGE rANGE) throws URISyntaxException {
        LOG.debug("REST request to save RANGE : {}", rANGE);
        if (rANGE.getId() != null) {
            throw new BadRequestAlertException("A new rANGE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rANGE = rANGERepository.save(rANGE);
        return ResponseEntity.created(new URI("/api/ranges/" + rANGE.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rANGE.getId().toString()))
            .body(rANGE);
    }

    /**
     * {@code PUT  /ranges/:id} : Updates an existing rANGE.
     *
     * @param id the id of the rANGE to save.
     * @param rANGE the rANGE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rANGE,
     * or with status {@code 400 (Bad Request)} if the rANGE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rANGE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RANGE> updateRANGE(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody RANGE rANGE)
        throws URISyntaxException {
        LOG.debug("REST request to update RANGE : {}, {}", id, rANGE);
        if (rANGE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rANGE.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rANGERepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rANGE = rANGERepository.save(rANGE);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rANGE.getId().toString()))
            .body(rANGE);
    }

    /**
     * {@code PATCH  /ranges/:id} : Partial updates given fields of an existing rANGE, field will ignore if it is null
     *
     * @param id the id of the rANGE to save.
     * @param rANGE the rANGE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rANGE,
     * or with status {@code 400 (Bad Request)} if the rANGE is not valid,
     * or with status {@code 404 (Not Found)} if the rANGE is not found,
     * or with status {@code 500 (Internal Server Error)} if the rANGE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RANGE> partialUpdateRANGE(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RANGE rANGE
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update RANGE partially : {}, {}", id, rANGE);
        if (rANGE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rANGE.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rANGERepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RANGE> result = rANGERepository
            .findById(rANGE.getId())
            .map(existingRANGE -> {
                if (rANGE.getrANGEFROM() != null) {
                    existingRANGE.setrANGEFROM(rANGE.getrANGEFROM());
                }
                if (rANGE.getrANGETO() != null) {
                    existingRANGE.setrANGETO(rANGE.getrANGETO());
                }
                if (rANGE.getaMOUNT() != null) {
                    existingRANGE.setaMOUNT(rANGE.getaMOUNT());
                }
                if (rANGE.gettXNTYPE() != null) {
                    existingRANGE.settXNTYPE(rANGE.gettXNTYPE());
                }
                if (rANGE.gettXNCODE() != null) {
                    existingRANGE.settXNCODE(rANGE.gettXNCODE());
                }
                if (rANGE.getcHARGEID() != null) {
                    existingRANGE.setcHARGEID(rANGE.getcHARGEID());
                }
                if (rANGE.getcHANNEL() != null) {
                    existingRANGE.setcHANNEL(rANGE.getcHANNEL());
                }

                return existingRANGE;
            })
            .map(rANGERepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rANGE.getId().toString())
        );
    }

    /**
     * {@code GET  /ranges} : get all the rANGES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rANGES in body.
     */
    @GetMapping("")
    public List<RANGE> getAllRANGES() {
        LOG.debug("REST request to get all RANGES");
        return rANGERepository.findAll();
    }

    /**
     * {@code GET  /ranges/:id} : get the "id" rANGE.
     *
     * @param id the id of the rANGE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rANGE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RANGE> getRANGE(@PathVariable("id") Long id) {
        LOG.debug("REST request to get RANGE : {}", id);
        Optional<RANGE> rANGE = rANGERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rANGE);
    }

    /**
     * {@code DELETE  /ranges/:id} : delete the "id" rANGE.
     *
     * @param id the id of the rANGE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRANGE(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete RANGE : {}", id);
        rANGERepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
