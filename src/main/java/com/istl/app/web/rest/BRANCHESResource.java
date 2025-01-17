package com.istl.app.web.rest;

import com.istl.app.domain.BRANCHES;
import com.istl.app.repository.BRANCHESRepository;
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
 * REST controller for managing {@link com.istl.app.domain.BRANCHES}.
 */
@RestController
@RequestMapping("/api/branches")
@Transactional
public class BRANCHESResource {

    private static final Logger LOG = LoggerFactory.getLogger(BRANCHESResource.class);

    private static final String ENTITY_NAME = "bRANCHES";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BRANCHESRepository bRANCHESRepository;

    public BRANCHESResource(BRANCHESRepository bRANCHESRepository) {
        this.bRANCHESRepository = bRANCHESRepository;
    }

    /**
     * {@code POST  /branches} : Create a new bRANCHES.
     *
     * @param bRANCHES the bRANCHES to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bRANCHES, or with status {@code 400 (Bad Request)} if the bRANCHES has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BRANCHES> createBRANCHES(@Valid @RequestBody BRANCHES bRANCHES) throws URISyntaxException {
        LOG.debug("REST request to save BRANCHES : {}", bRANCHES);
        if (bRANCHES.getId() != null) {
            throw new BadRequestAlertException("A new bRANCHES cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bRANCHES = bRANCHESRepository.save(bRANCHES);
        return ResponseEntity.created(new URI("/api/branches/" + bRANCHES.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, bRANCHES.getId().toString()))
            .body(bRANCHES);
    }

    /**
     * {@code PUT  /branches/:id} : Updates an existing bRANCHES.
     *
     * @param id the id of the bRANCHES to save.
     * @param bRANCHES the bRANCHES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bRANCHES,
     * or with status {@code 400 (Bad Request)} if the bRANCHES is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bRANCHES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BRANCHES> updateBRANCHES(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BRANCHES bRANCHES
    ) throws URISyntaxException {
        LOG.debug("REST request to update BRANCHES : {}, {}", id, bRANCHES);
        if (bRANCHES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bRANCHES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bRANCHESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bRANCHES = bRANCHESRepository.save(bRANCHES);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bRANCHES.getId().toString()))
            .body(bRANCHES);
    }

    /**
     * {@code PATCH  /branches/:id} : Partial updates given fields of an existing bRANCHES, field will ignore if it is null
     *
     * @param id the id of the bRANCHES to save.
     * @param bRANCHES the bRANCHES to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bRANCHES,
     * or with status {@code 400 (Bad Request)} if the bRANCHES is not valid,
     * or with status {@code 404 (Not Found)} if the bRANCHES is not found,
     * or with status {@code 500 (Internal Server Error)} if the bRANCHES couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BRANCHES> partialUpdateBRANCHES(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BRANCHES bRANCHES
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update BRANCHES partially : {}, {}", id, bRANCHES);
        if (bRANCHES.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bRANCHES.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bRANCHESRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BRANCHES> result = bRANCHESRepository
            .findById(bRANCHES.getId())
            .map(existingBRANCHES -> {
                if (bRANCHES.getbRANCHNAME() != null) {
                    existingBRANCHES.setbRANCHNAME(bRANCHES.getbRANCHNAME());
                }
                if (bRANCHES.getbRANCHCODE() != null) {
                    existingBRANCHES.setbRANCHCODE(bRANCHES.getbRANCHCODE());
                }
                if (bRANCHES.getaPPROVED() != null) {
                    existingBRANCHES.setaPPROVED(bRANCHES.getaPPROVED());
                }
                if (bRANCHES.geteMAIL() != null) {
                    existingBRANCHES.seteMAIL(bRANCHES.geteMAIL());
                }
                if (bRANCHES.getaDDRESS() != null) {
                    existingBRANCHES.setaDDRESS(bRANCHES.getaDDRESS());
                }
                if (bRANCHES.getpHONE() != null) {
                    existingBRANCHES.setpHONE(bRANCHES.getpHONE());
                }
                if (bRANCHES.getlOCATION() != null) {
                    existingBRANCHES.setlOCATION(bRANCHES.getlOCATION());
                }
                if (bRANCHES.getcONTACTPERSON() != null) {
                    existingBRANCHES.setcONTACTPERSON(bRANCHES.getcONTACTPERSON());
                }
                if (bRANCHES.getrEMARKS() != null) {
                    existingBRANCHES.setrEMARKS(bRANCHES.getrEMARKS());
                }
                if (bRANCHES.getcREATEDBY() != null) {
                    existingBRANCHES.setcREATEDBY(bRANCHES.getcREATEDBY());
                }
                if (bRANCHES.getcREATEDON() != null) {
                    existingBRANCHES.setcREATEDON(bRANCHES.getcREATEDON());
                }
                if (bRANCHES.getaPPROVEDBY() != null) {
                    existingBRANCHES.setaPPROVEDBY(bRANCHES.getaPPROVEDBY());
                }
                if (bRANCHES.getaPPROVEDON() != null) {
                    existingBRANCHES.setaPPROVEDON(bRANCHES.getaPPROVEDON());
                }
                if (bRANCHES.getcHECKERREMARKS() != null) {
                    existingBRANCHES.setcHECKERREMARKS(bRANCHES.getcHECKERREMARKS());
                }
                if (bRANCHES.getdELETEDBY() != null) {
                    existingBRANCHES.setdELETEDBY(bRANCHES.getdELETEDBY());
                }
                if (bRANCHES.getdELETEDON() != null) {
                    existingBRANCHES.setdELETEDON(bRANCHES.getdELETEDON());
                }
                if (bRANCHES.getdELETEREMARKS() != null) {
                    existingBRANCHES.setdELETEREMARKS(bRANCHES.getdELETEREMARKS());
                }
                if (bRANCHES.getdELETED() != null) {
                    existingBRANCHES.setdELETED(bRANCHES.getdELETED());
                }
                if (bRANCHES.getdECLINED() != null) {
                    existingBRANCHES.setdECLINED(bRANCHES.getdECLINED());
                }
                if (bRANCHES.getdECLINEDDON() != null) {
                    existingBRANCHES.setdECLINEDDON(bRANCHES.getdECLINEDDON());
                }
                if (bRANCHES.getdECLINEDBY() != null) {
                    existingBRANCHES.setdECLINEDBY(bRANCHES.getdECLINEDBY());
                }
                if (bRANCHES.getsESSIONID() != null) {
                    existingBRANCHES.setsESSIONID(bRANCHES.getsESSIONID());
                }
                if (bRANCHES.getrEWORKED() != null) {
                    existingBRANCHES.setrEWORKED(bRANCHES.getrEWORKED());
                }
                if (bRANCHES.getrEWORKEDBY() != null) {
                    existingBRANCHES.setrEWORKEDBY(bRANCHES.getrEWORKEDBY());
                }
                if (bRANCHES.getrEWORKEDON() != null) {
                    existingBRANCHES.setrEWORKEDON(bRANCHES.getrEWORKEDON());
                }
                if (bRANCHES.getdISTRICT() != null) {
                    existingBRANCHES.setdISTRICT(bRANCHES.getdISTRICT());
                }
                if (bRANCHES.getrEGION() != null) {
                    existingBRANCHES.setrEGION(bRANCHES.getrEGION());
                }
                if (bRANCHES.getrEGIONNAME() != null) {
                    existingBRANCHES.setrEGIONNAME(bRANCHES.getrEGIONNAME());
                }
                if (bRANCHES.getrEPORTING() != null) {
                    existingBRANCHES.setrEPORTING(bRANCHES.getrEPORTING());
                }

                return existingBRANCHES;
            })
            .map(bRANCHESRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bRANCHES.getId().toString())
        );
    }

    /**
     * {@code GET  /branches} : get all the bRANCHES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bRANCHES in body.
     */
    @GetMapping("")
    public List<BRANCHES> getAllBRANCHES() {
        LOG.debug("REST request to get all BRANCHES");
        return bRANCHESRepository.findAll();
    }

    /**
     * {@code GET  /branches/:id} : get the "id" bRANCHES.
     *
     * @param id the id of the bRANCHES to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bRANCHES, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BRANCHES> getBRANCHES(@PathVariable("id") Long id) {
        LOG.debug("REST request to get BRANCHES : {}", id);
        Optional<BRANCHES> bRANCHES = bRANCHESRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bRANCHES);
    }

    /**
     * {@code DELETE  /branches/:id} : delete the "id" bRANCHES.
     *
     * @param id the id of the bRANCHES to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBRANCHES(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete BRANCHES : {}", id);
        bRANCHESRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
