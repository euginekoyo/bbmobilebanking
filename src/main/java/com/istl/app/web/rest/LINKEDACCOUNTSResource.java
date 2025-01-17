package com.istl.app.web.rest;

import com.istl.app.domain.LINKEDACCOUNTS;
import com.istl.app.repository.LINKEDACCOUNTSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.LINKEDACCOUNTS}.
 */
@RestController
@RequestMapping("/api/linkedaccounts")
@Transactional
public class LINKEDACCOUNTSResource {

    private static final Logger LOG = LoggerFactory.getLogger(LINKEDACCOUNTSResource.class);

    private static final String ENTITY_NAME = "lINKEDACCOUNTS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LINKEDACCOUNTSRepository lINKEDACCOUNTSRepository;

    public LINKEDACCOUNTSResource(LINKEDACCOUNTSRepository lINKEDACCOUNTSRepository) {
        this.lINKEDACCOUNTSRepository = lINKEDACCOUNTSRepository;
    }

    /**
     * {@code POST  /linkedaccounts} : Create a new lINKEDACCOUNTS.
     *
     * @param lINKEDACCOUNTS the lINKEDACCOUNTS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lINKEDACCOUNTS, or with status {@code 400 (Bad Request)} if the lINKEDACCOUNTS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LINKEDACCOUNTS> createLINKEDACCOUNTS(@Valid @RequestBody LINKEDACCOUNTS lINKEDACCOUNTS)
        throws URISyntaxException {
        LOG.debug("REST request to save LINKEDACCOUNTS : {}", lINKEDACCOUNTS);
        if (lINKEDACCOUNTS.getId() != null) {
            throw new BadRequestAlertException("A new lINKEDACCOUNTS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        lINKEDACCOUNTS = lINKEDACCOUNTSRepository.save(lINKEDACCOUNTS);
        return ResponseEntity.created(new URI("/api/linkedaccounts/" + lINKEDACCOUNTS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, lINKEDACCOUNTS.getId().toString()))
            .body(lINKEDACCOUNTS);
    }

    /**
     * {@code PUT  /linkedaccounts/:id} : Updates an existing lINKEDACCOUNTS.
     *
     * @param id the id of the lINKEDACCOUNTS to save.
     * @param lINKEDACCOUNTS the lINKEDACCOUNTS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lINKEDACCOUNTS,
     * or with status {@code 400 (Bad Request)} if the lINKEDACCOUNTS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lINKEDACCOUNTS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LINKEDACCOUNTS> updateLINKEDACCOUNTS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LINKEDACCOUNTS lINKEDACCOUNTS
    ) throws URISyntaxException {
        LOG.debug("REST request to update LINKEDACCOUNTS : {}, {}", id, lINKEDACCOUNTS);
        if (lINKEDACCOUNTS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lINKEDACCOUNTS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lINKEDACCOUNTSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        lINKEDACCOUNTS = lINKEDACCOUNTSRepository.save(lINKEDACCOUNTS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lINKEDACCOUNTS.getId().toString()))
            .body(lINKEDACCOUNTS);
    }

    /**
     * {@code PATCH  /linkedaccounts/:id} : Partial updates given fields of an existing lINKEDACCOUNTS, field will ignore if it is null
     *
     * @param id the id of the lINKEDACCOUNTS to save.
     * @param lINKEDACCOUNTS the lINKEDACCOUNTS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lINKEDACCOUNTS,
     * or with status {@code 400 (Bad Request)} if the lINKEDACCOUNTS is not valid,
     * or with status {@code 404 (Not Found)} if the lINKEDACCOUNTS is not found,
     * or with status {@code 500 (Internal Server Error)} if the lINKEDACCOUNTS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LINKEDACCOUNTS> partialUpdateLINKEDACCOUNTS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LINKEDACCOUNTS lINKEDACCOUNTS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LINKEDACCOUNTS partially : {}, {}", id, lINKEDACCOUNTS);
        if (lINKEDACCOUNTS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lINKEDACCOUNTS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lINKEDACCOUNTSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LINKEDACCOUNTS> result = lINKEDACCOUNTSRepository
            .findById(lINKEDACCOUNTS.getId())
            .map(existingLINKEDACCOUNTS -> {
                if (lINKEDACCOUNTS.getaCOUNTNAME() != null) {
                    existingLINKEDACCOUNTS.setaCOUNTNAME(lINKEDACCOUNTS.getaCOUNTNAME());
                }
                if (lINKEDACCOUNTS.getaCCOUNTCLASS() != null) {
                    existingLINKEDACCOUNTS.setaCCOUNTCLASS(lINKEDACCOUNTS.getaCCOUNTCLASS());
                }
                if (lINKEDACCOUNTS.getaCCOUNTCURRENCY() != null) {
                    existingLINKEDACCOUNTS.setaCCOUNTCURRENCY(lINKEDACCOUNTS.getaCCOUNTCURRENCY());
                }
                if (lINKEDACCOUNTS.getaCCOUNTNUMBER() != null) {
                    existingLINKEDACCOUNTS.setaCCOUNTNUMBER(lINKEDACCOUNTS.getaCCOUNTNUMBER());
                }
                if (lINKEDACCOUNTS.getcIF() != null) {
                    existingLINKEDACCOUNTS.setcIF(lINKEDACCOUNTS.getcIF());
                }
                if (lINKEDACCOUNTS.gettIMELINKED() != null) {
                    existingLINKEDACCOUNTS.settIMELINKED(lINKEDACCOUNTS.gettIMELINKED());
                }
                if (lINKEDACCOUNTS.getpHONENUMBER() != null) {
                    existingLINKEDACCOUNTS.setpHONENUMBER(lINKEDACCOUNTS.getpHONENUMBER());
                }
                if (lINKEDACCOUNTS.getaPPROVEDON() != null) {
                    existingLINKEDACCOUNTS.setaPPROVEDON(lINKEDACCOUNTS.getaPPROVEDON());
                }
                if (lINKEDACCOUNTS.getaPPROVED() != null) {
                    existingLINKEDACCOUNTS.setaPPROVED(lINKEDACCOUNTS.getaPPROVED());
                }
                if (lINKEDACCOUNTS.getdECLINED() != null) {
                    existingLINKEDACCOUNTS.setdECLINED(lINKEDACCOUNTS.getdECLINED());
                }
                if (lINKEDACCOUNTS.getdECLINEDON() != null) {
                    existingLINKEDACCOUNTS.setdECLINEDON(lINKEDACCOUNTS.getdECLINEDON());
                }
                if (lINKEDACCOUNTS.getrEMARKS() != null) {
                    existingLINKEDACCOUNTS.setrEMARKS(lINKEDACCOUNTS.getrEMARKS());
                }
                if (lINKEDACCOUNTS.getlINKEDBY() != null) {
                    existingLINKEDACCOUNTS.setlINKEDBY(lINKEDACCOUNTS.getlINKEDBY());
                }
                if (lINKEDACCOUNTS.getaPPROVEDBY() != null) {
                    existingLINKEDACCOUNTS.setaPPROVEDBY(lINKEDACCOUNTS.getaPPROVEDBY());
                }
                if (lINKEDACCOUNTS.getlINKED() != null) {
                    existingLINKEDACCOUNTS.setlINKED(lINKEDACCOUNTS.getlINKED());
                }
                if (lINKEDACCOUNTS.getaCTIVE() != null) {
                    existingLINKEDACCOUNTS.setaCTIVE(lINKEDACCOUNTS.getaCTIVE());
                }
                if (lINKEDACCOUNTS.getdELINKEDBY() != null) {
                    existingLINKEDACCOUNTS.setdELINKEDBY(lINKEDACCOUNTS.getdELINKEDBY());
                }
                if (lINKEDACCOUNTS.getdELINKEDON() != null) {
                    existingLINKEDACCOUNTS.setdELINKEDON(lINKEDACCOUNTS.getdELINKEDON());
                }
                if (lINKEDACCOUNTS.getdELINKED() != null) {
                    existingLINKEDACCOUNTS.setdELINKED(lINKEDACCOUNTS.getdELINKED());
                }
                if (lINKEDACCOUNTS.getaCCOUNTALIAS() != null) {
                    existingLINKEDACCOUNTS.setaCCOUNTALIAS(lINKEDACCOUNTS.getaCCOUNTALIAS());
                }
                if (lINKEDACCOUNTS.getsHORTCUTS() != null) {
                    existingLINKEDACCOUNTS.setsHORTCUTS(lINKEDACCOUNTS.getsHORTCUTS());
                }

                return existingLINKEDACCOUNTS;
            })
            .map(lINKEDACCOUNTSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lINKEDACCOUNTS.getId().toString())
        );
    }

    /**
     * {@code GET  /linkedaccounts} : get all the lINKEDACCOUNTS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lINKEDACCOUNTS in body.
     */
    @GetMapping("")
    public List<LINKEDACCOUNTS> getAllLINKEDACCOUNTS() {
        LOG.debug("REST request to get all LINKEDACCOUNTS");
        return lINKEDACCOUNTSRepository.findAll();
    }

    /**
     * {@code GET  /linkedaccounts/:id} : get the "id" lINKEDACCOUNTS.
     *
     * @param id the id of the lINKEDACCOUNTS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lINKEDACCOUNTS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LINKEDACCOUNTS> getLINKEDACCOUNTS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LINKEDACCOUNTS : {}", id);
        Optional<LINKEDACCOUNTS> lINKEDACCOUNTS = lINKEDACCOUNTSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lINKEDACCOUNTS);
    }

    /**
     * {@code DELETE  /linkedaccounts/:id} : delete the "id" lINKEDACCOUNTS.
     *
     * @param id the id of the lINKEDACCOUNTS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLINKEDACCOUNTS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LINKEDACCOUNTS : {}", id);
        lINKEDACCOUNTSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
