package com.istl.app.web.rest;

import com.istl.app.domain.BILLERS;
import com.istl.app.repository.BILLERSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.BILLERS}.
 */
@RestController
@RequestMapping("/api/billers")
@Transactional
public class BILLERSResource {

    private static final Logger LOG = LoggerFactory.getLogger(BILLERSResource.class);

    private static final String ENTITY_NAME = "bILLERS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BILLERSRepository bILLERSRepository;

    public BILLERSResource(BILLERSRepository bILLERSRepository) {
        this.bILLERSRepository = bILLERSRepository;
    }

    /**
     * {@code POST  /billers} : Create a new bILLERS.
     *
     * @param bILLERS the bILLERS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bILLERS, or with status {@code 400 (Bad Request)} if the bILLERS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BILLERS> createBILLERS(@Valid @RequestBody BILLERS bILLERS) throws URISyntaxException {
        LOG.debug("REST request to save BILLERS : {}", bILLERS);
        if (bILLERS.getId() != null) {
            throw new BadRequestAlertException("A new bILLERS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bILLERS = bILLERSRepository.save(bILLERS);
        return ResponseEntity.created(new URI("/api/billers/" + bILLERS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, bILLERS.getId().toString()))
            .body(bILLERS);
    }

    /**
     * {@code PUT  /billers/:id} : Updates an existing bILLERS.
     *
     * @param id the id of the bILLERS to save.
     * @param bILLERS the bILLERS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bILLERS,
     * or with status {@code 400 (Bad Request)} if the bILLERS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bILLERS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BILLERS> updateBILLERS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BILLERS bILLERS
    ) throws URISyntaxException {
        LOG.debug("REST request to update BILLERS : {}, {}", id, bILLERS);
        if (bILLERS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bILLERS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bILLERSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bILLERS = bILLERSRepository.save(bILLERS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bILLERS.getId().toString()))
            .body(bILLERS);
    }

    /**
     * {@code PATCH  /billers/:id} : Partial updates given fields of an existing bILLERS, field will ignore if it is null
     *
     * @param id the id of the bILLERS to save.
     * @param bILLERS the bILLERS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bILLERS,
     * or with status {@code 400 (Bad Request)} if the bILLERS is not valid,
     * or with status {@code 404 (Not Found)} if the bILLERS is not found,
     * or with status {@code 500 (Internal Server Error)} if the bILLERS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BILLERS> partialUpdateBILLERS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BILLERS bILLERS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update BILLERS partially : {}, {}", id, bILLERS);
        if (bILLERS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bILLERS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bILLERSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BILLERS> result = bILLERSRepository
            .findById(bILLERS.getId())
            .map(existingBILLERS -> {
                if (bILLERS.getbILLERID() != null) {
                    existingBILLERS.setbILLERID(bILLERS.getbILLERID());
                }
                if (bILLERS.getdESCRIPTION() != null) {
                    existingBILLERS.setdESCRIPTION(bILLERS.getdESCRIPTION());
                }
                if (bILLERS.getbILLERCOLLECTIONACCOUNT() != null) {
                    existingBILLERS.setbILLERCOLLECTIONACCOUNT(bILLERS.getbILLERCOLLECTIONACCOUNT());
                }
                if (bILLERS.getdATECREATED() != null) {
                    existingBILLERS.setdATECREATED(bILLERS.getdATECREATED());
                }
                if (bILLERS.getcREATEDBY() != null) {
                    existingBILLERS.setcREATEDBY(bILLERS.getcREATEDBY());
                }
                if (bILLERS.getaPPROVED() != null) {
                    existingBILLERS.setaPPROVED(bILLERS.getaPPROVED());
                }
                if (bILLERS.getaPPROVEDBY() != null) {
                    existingBILLERS.setaPPROVEDBY(bILLERS.getaPPROVEDBY());
                }
                if (bILLERS.getaPPROVEDDATE() != null) {
                    existingBILLERS.setaPPROVEDDATE(bILLERS.getaPPROVEDDATE());
                }
                if (bILLERS.getcHARGABLEPRODUCTID() != null) {
                    existingBILLERS.setcHARGABLEPRODUCTID(bILLERS.getcHARGABLEPRODUCTID());
                }
                if (bILLERS.getnONCHARGABLEPRODUCTID() != null) {
                    existingBILLERS.setnONCHARGABLEPRODUCTID(bILLERS.getnONCHARGABLEPRODUCTID());
                }
                if (bILLERS.getuSDBILLERCOLLECTIONACCOUNT() != null) {
                    existingBILLERS.setuSDBILLERCOLLECTIONACCOUNT(bILLERS.getuSDBILLERCOLLECTIONACCOUNT());
                }
                if (bILLERS.geteNABLEDUPLICATECHECK() != null) {
                    existingBILLERS.seteNABLEDUPLICATECHECK(bILLERS.geteNABLEDUPLICATECHECK());
                }
                if (bILLERS.getrEMARKS() != null) {
                    existingBILLERS.setrEMARKS(bILLERS.getrEMARKS());
                }
                if (bILLERS.getsESSIONID() != null) {
                    existingBILLERS.setsESSIONID(bILLERS.getsESSIONID());
                }
                if (bILLERS.getrEWORKBY() != null) {
                    existingBILLERS.setrEWORKBY(bILLERS.getrEWORKBY());
                }
                if (bILLERS.getsTATUS() != null) {
                    existingBILLERS.setsTATUS(bILLERS.getsTATUS());
                }
                if (bILLERS.getaCTIVE() != null) {
                    existingBILLERS.setaCTIVE(bILLERS.getaCTIVE());
                }
                if (bILLERS.getrEWORK() != null) {
                    existingBILLERS.setrEWORK(bILLERS.getrEWORK());
                }

                return existingBILLERS;
            })
            .map(bILLERSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bILLERS.getId().toString())
        );
    }

    /**
     * {@code GET  /billers} : get all the bILLERS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bILLERS in body.
     */
    @GetMapping("")
    public List<BILLERS> getAllBILLERS() {
        LOG.debug("REST request to get all BILLERS");
        return bILLERSRepository.findAll();
    }

    /**
     * {@code GET  /billers/:id} : get the "id" bILLERS.
     *
     * @param id the id of the bILLERS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bILLERS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BILLERS> getBILLERS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get BILLERS : {}", id);
        Optional<BILLERS> bILLERS = bILLERSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bILLERS);
    }

    /**
     * {@code DELETE  /billers/:id} : delete the "id" bILLERS.
     *
     * @param id the id of the bILLERS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBILLERS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete BILLERS : {}", id);
        bILLERSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
