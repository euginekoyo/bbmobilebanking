package com.istl.app.web.rest;

import com.istl.app.domain.SERVICEMANAGEMENT;
import com.istl.app.repository.SERVICEMANAGEMENTRepository;
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
 * REST controller for managing {@link com.istl.app.domain.SERVICEMANAGEMENT}.
 */
@RestController
@RequestMapping("/api/servicemanagements")
@Transactional
public class SERVICEMANAGEMENTResource {

    private static final Logger LOG = LoggerFactory.getLogger(SERVICEMANAGEMENTResource.class);

    private static final String ENTITY_NAME = "sERVICEMANAGEMENT";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SERVICEMANAGEMENTRepository sERVICEMANAGEMENTRepository;

    public SERVICEMANAGEMENTResource(SERVICEMANAGEMENTRepository sERVICEMANAGEMENTRepository) {
        this.sERVICEMANAGEMENTRepository = sERVICEMANAGEMENTRepository;
    }

    /**
     * {@code POST  /servicemanagements} : Create a new sERVICEMANAGEMENT.
     *
     * @param sERVICEMANAGEMENT the sERVICEMANAGEMENT to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sERVICEMANAGEMENT, or with status {@code 400 (Bad Request)} if the sERVICEMANAGEMENT has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SERVICEMANAGEMENT> createSERVICEMANAGEMENT(@Valid @RequestBody SERVICEMANAGEMENT sERVICEMANAGEMENT)
        throws URISyntaxException {
        LOG.debug("REST request to save SERVICEMANAGEMENT : {}", sERVICEMANAGEMENT);
        if (sERVICEMANAGEMENT.getId() != null) {
            throw new BadRequestAlertException("A new sERVICEMANAGEMENT cannot already have an ID", ENTITY_NAME, "idexists");
        }
        sERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.save(sERVICEMANAGEMENT);
        return ResponseEntity.created(new URI("/api/servicemanagements/" + sERVICEMANAGEMENT.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, sERVICEMANAGEMENT.getId().toString()))
            .body(sERVICEMANAGEMENT);
    }

    /**
     * {@code PUT  /servicemanagements/:id} : Updates an existing sERVICEMANAGEMENT.
     *
     * @param id the id of the sERVICEMANAGEMENT to save.
     * @param sERVICEMANAGEMENT the sERVICEMANAGEMENT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sERVICEMANAGEMENT,
     * or with status {@code 400 (Bad Request)} if the sERVICEMANAGEMENT is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sERVICEMANAGEMENT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SERVICEMANAGEMENT> updateSERVICEMANAGEMENT(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SERVICEMANAGEMENT sERVICEMANAGEMENT
    ) throws URISyntaxException {
        LOG.debug("REST request to update SERVICEMANAGEMENT : {}, {}", id, sERVICEMANAGEMENT);
        if (sERVICEMANAGEMENT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sERVICEMANAGEMENT.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sERVICEMANAGEMENTRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        sERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.save(sERVICEMANAGEMENT);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sERVICEMANAGEMENT.getId().toString()))
            .body(sERVICEMANAGEMENT);
    }

    /**
     * {@code PATCH  /servicemanagements/:id} : Partial updates given fields of an existing sERVICEMANAGEMENT, field will ignore if it is null
     *
     * @param id the id of the sERVICEMANAGEMENT to save.
     * @param sERVICEMANAGEMENT the sERVICEMANAGEMENT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sERVICEMANAGEMENT,
     * or with status {@code 400 (Bad Request)} if the sERVICEMANAGEMENT is not valid,
     * or with status {@code 404 (Not Found)} if the sERVICEMANAGEMENT is not found,
     * or with status {@code 500 (Internal Server Error)} if the sERVICEMANAGEMENT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SERVICEMANAGEMENT> partialUpdateSERVICEMANAGEMENT(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SERVICEMANAGEMENT sERVICEMANAGEMENT
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SERVICEMANAGEMENT partially : {}, {}", id, sERVICEMANAGEMENT);
        if (sERVICEMANAGEMENT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sERVICEMANAGEMENT.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sERVICEMANAGEMENTRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SERVICEMANAGEMENT> result = sERVICEMANAGEMENTRepository
            .findById(sERVICEMANAGEMENT.getId())
            .map(existingSERVICEMANAGEMENT -> {
                if (sERVICEMANAGEMENT.getpROCESSINGCODE() != null) {
                    existingSERVICEMANAGEMENT.setpROCESSINGCODE(sERVICEMANAGEMENT.getpROCESSINGCODE());
                }
                if (sERVICEMANAGEMENT.getaCTIVE() != null) {
                    existingSERVICEMANAGEMENT.setaCTIVE(sERVICEMANAGEMENT.getaCTIVE());
                }
                if (sERVICEMANAGEMENT.getcREATEDBY() != null) {
                    existingSERVICEMANAGEMENT.setcREATEDBY(sERVICEMANAGEMENT.getcREATEDBY());
                }
                if (sERVICEMANAGEMENT.getdATECREATED() != null) {
                    existingSERVICEMANAGEMENT.setdATECREATED(sERVICEMANAGEMENT.getdATECREATED());
                }
                if (sERVICEMANAGEMENT.getaPPROVED() != null) {
                    existingSERVICEMANAGEMENT.setaPPROVED(sERVICEMANAGEMENT.getaPPROVED());
                }
                if (sERVICEMANAGEMENT.getaPPROVEDBY() != null) {
                    existingSERVICEMANAGEMENT.setaPPROVEDBY(sERVICEMANAGEMENT.getaPPROVEDBY());
                }
                if (sERVICEMANAGEMENT.getaPPROVEDDATE() != null) {
                    existingSERVICEMANAGEMENT.setaPPROVEDDATE(sERVICEMANAGEMENT.getaPPROVEDDATE());
                }
                if (sERVICEMANAGEMENT.getaDAPTORTYPE() != null) {
                    existingSERVICEMANAGEMENT.setaDAPTORTYPE(sERVICEMANAGEMENT.getaDAPTORTYPE());
                }
                if (sERVICEMANAGEMENT.getdESTINATION() != null) {
                    existingSERVICEMANAGEMENT.setdESTINATION(sERVICEMANAGEMENT.getdESTINATION());
                }
                if (sERVICEMANAGEMENT.gettHIRDPARTYRESPONSE() != null) {
                    existingSERVICEMANAGEMENT.settHIRDPARTYRESPONSE(sERVICEMANAGEMENT.gettHIRDPARTYRESPONSE());
                }
                if (sERVICEMANAGEMENT.gettELCO() != null) {
                    existingSERVICEMANAGEMENT.settELCO(sERVICEMANAGEMENT.gettELCO());
                }
                if (sERVICEMANAGEMENT.getdESCRIPTION() != null) {
                    existingSERVICEMANAGEMENT.setdESCRIPTION(sERVICEMANAGEMENT.getdESCRIPTION());
                }
                if (sERVICEMANAGEMENT.getrEMARKS() != null) {
                    existingSERVICEMANAGEMENT.setrEMARKS(sERVICEMANAGEMENT.getrEMARKS());
                }
                if (sERVICEMANAGEMENT.getsESSIONID() != null) {
                    existingSERVICEMANAGEMENT.setsESSIONID(sERVICEMANAGEMENT.getsESSIONID());
                }
                if (sERVICEMANAGEMENT.getrEWORKBY() != null) {
                    existingSERVICEMANAGEMENT.setrEWORKBY(sERVICEMANAGEMENT.getrEWORKBY());
                }

                return existingSERVICEMANAGEMENT;
            })
            .map(sERVICEMANAGEMENTRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sERVICEMANAGEMENT.getId().toString())
        );
    }

    /**
     * {@code GET  /servicemanagements} : get all the sERVICEMANAGEMENTS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sERVICEMANAGEMENTS in body.
     */
    @GetMapping("")
    public List<SERVICEMANAGEMENT> getAllSERVICEMANAGEMENTS() {
        LOG.debug("REST request to get all SERVICEMANAGEMENTS");
        return sERVICEMANAGEMENTRepository.findAll();
    }

    /**
     * {@code GET  /servicemanagements/:id} : get the "id" sERVICEMANAGEMENT.
     *
     * @param id the id of the sERVICEMANAGEMENT to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sERVICEMANAGEMENT, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SERVICEMANAGEMENT> getSERVICEMANAGEMENT(@PathVariable("id") Long id) {
        LOG.debug("REST request to get SERVICEMANAGEMENT : {}", id);
        Optional<SERVICEMANAGEMENT> sERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sERVICEMANAGEMENT);
    }

    /**
     * {@code DELETE  /servicemanagements/:id} : delete the "id" sERVICEMANAGEMENT.
     *
     * @param id the id of the sERVICEMANAGEMENT to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSERVICEMANAGEMENT(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete SERVICEMANAGEMENT : {}", id);
        sERVICEMANAGEMENTRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
