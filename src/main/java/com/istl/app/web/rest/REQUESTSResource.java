package com.istl.app.web.rest;

import com.istl.app.domain.REQUESTS;
import com.istl.app.repository.REQUESTSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.REQUESTS}.
 */
@RestController
@RequestMapping("/api/requests")
@Transactional
public class REQUESTSResource {

    private static final Logger LOG = LoggerFactory.getLogger(REQUESTSResource.class);

    private static final String ENTITY_NAME = "rEQUESTS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final REQUESTSRepository rEQUESTSRepository;

    public REQUESTSResource(REQUESTSRepository rEQUESTSRepository) {
        this.rEQUESTSRepository = rEQUESTSRepository;
    }

    /**
     * {@code POST  /requests} : Create a new rEQUESTS.
     *
     * @param rEQUESTS the rEQUESTS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rEQUESTS, or with status {@code 400 (Bad Request)} if the rEQUESTS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<REQUESTS> createREQUESTS(@Valid @RequestBody REQUESTS rEQUESTS) throws URISyntaxException {
        LOG.debug("REST request to save REQUESTS : {}", rEQUESTS);
        if (rEQUESTS.getId() != null) {
            throw new BadRequestAlertException("A new rEQUESTS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rEQUESTS = rEQUESTSRepository.save(rEQUESTS);
        return ResponseEntity.created(new URI("/api/requests/" + rEQUESTS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rEQUESTS.getId().toString()))
            .body(rEQUESTS);
    }

    /**
     * {@code PUT  /requests/:id} : Updates an existing rEQUESTS.
     *
     * @param id the id of the rEQUESTS to save.
     * @param rEQUESTS the rEQUESTS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rEQUESTS,
     * or with status {@code 400 (Bad Request)} if the rEQUESTS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rEQUESTS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<REQUESTS> updateREQUESTS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody REQUESTS rEQUESTS
    ) throws URISyntaxException {
        LOG.debug("REST request to update REQUESTS : {}, {}", id, rEQUESTS);
        if (rEQUESTS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rEQUESTS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rEQUESTSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rEQUESTS = rEQUESTSRepository.save(rEQUESTS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rEQUESTS.getId().toString()))
            .body(rEQUESTS);
    }

    /**
     * {@code PATCH  /requests/:id} : Partial updates given fields of an existing rEQUESTS, field will ignore if it is null
     *
     * @param id the id of the rEQUESTS to save.
     * @param rEQUESTS the rEQUESTS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rEQUESTS,
     * or with status {@code 400 (Bad Request)} if the rEQUESTS is not valid,
     * or with status {@code 404 (Not Found)} if the rEQUESTS is not found,
     * or with status {@code 500 (Internal Server Error)} if the rEQUESTS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<REQUESTS> partialUpdateREQUESTS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody REQUESTS rEQUESTS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update REQUESTS partially : {}, {}", id, rEQUESTS);
        if (rEQUESTS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rEQUESTS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rEQUESTSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<REQUESTS> result = rEQUESTSRepository
            .findById(rEQUESTS.getId())
            .map(existingREQUESTS -> {
                if (rEQUESTS.getmOBILENUMBER() != null) {
                    existingREQUESTS.setmOBILENUMBER(rEQUESTS.getmOBILENUMBER());
                }
                if (rEQUESTS.getaCCOUNTNO() != null) {
                    existingREQUESTS.setaCCOUNTNO(rEQUESTS.getaCCOUNTNO());
                }
                if (rEQUESTS.getcURRENCY() != null) {
                    existingREQUESTS.setcURRENCY(rEQUESTS.getcURRENCY());
                }
                if (rEQUESTS.getcIF() != null) {
                    existingREQUESTS.setcIF(rEQUESTS.getcIF());
                }
                if (rEQUESTS.getrEQUESTTYPE() != null) {
                    existingREQUESTS.setrEQUESTTYPE(rEQUESTS.getrEQUESTTYPE());
                }
                if (rEQUESTS.getrEQUESTCHARGE() != null) {
                    existingREQUESTS.setrEQUESTCHARGE(rEQUESTS.getrEQUESTCHARGE());
                }
                if (rEQUESTS.getrEQUESTSTATUS() != null) {
                    existingREQUESTS.setrEQUESTSTATUS(rEQUESTS.getrEQUESTSTATUS());
                }
                if (rEQUESTS.getdATEREQUESTED() != null) {
                    existingREQUESTS.setdATEREQUESTED(rEQUESTS.getdATEREQUESTED());
                }
                if (rEQUESTS.gettRNREFNO() != null) {
                    existingREQUESTS.settRNREFNO(rEQUESTS.gettRNREFNO());
                }
                if (rEQUESTS.getnOOFBOOKS() != null) {
                    existingREQUESTS.setnOOFBOOKS(rEQUESTS.getnOOFBOOKS());
                }
                if (rEQUESTS.getnOOFLEAVES() != null) {
                    existingREQUESTS.setnOOFLEAVES(rEQUESTS.getnOOFLEAVES());
                }
                if (rEQUESTS.getaPPROVED() != null) {
                    existingREQUESTS.setaPPROVED(rEQUESTS.getaPPROVED());
                }
                if (rEQUESTS.getcHANNEL() != null) {
                    existingREQUESTS.setcHANNEL(rEQUESTS.getcHANNEL());
                }
                if (rEQUESTS.getaPPROVEDBY() != null) {
                    existingREQUESTS.setaPPROVEDBY(rEQUESTS.getaPPROVEDBY());
                }
                if (rEQUESTS.getaPPROVEDON() != null) {
                    existingREQUESTS.setaPPROVEDON(rEQUESTS.getaPPROVEDON());
                }
                if (rEQUESTS.getcHECKERREMARKS() != null) {
                    existingREQUESTS.setcHECKERREMARKS(rEQUESTS.getcHECKERREMARKS());
                }
                if (rEQUESTS.getrESPCODE() != null) {
                    existingREQUESTS.setrESPCODE(rEQUESTS.getrESPCODE());
                }
                if (rEQUESTS.getrESPDESCRIPTION() != null) {
                    existingREQUESTS.setrESPDESCRIPTION(rEQUESTS.getrESPDESCRIPTION());
                }
                if (rEQUESTS.getdATERESPONDED() != null) {
                    existingREQUESTS.setdATERESPONDED(rEQUESTS.getdATERESPONDED());
                }
                if (rEQUESTS.getcUSTOMERNAME() != null) {
                    existingREQUESTS.setcUSTOMERNAME(rEQUESTS.getcUSTOMERNAME());
                }
                if (rEQUESTS.getrEJECTED() != null) {
                    existingREQUESTS.setrEJECTED(rEQUESTS.getrEJECTED());
                }
                if (rEQUESTS.getrEJECTEDBY() != null) {
                    existingREQUESTS.setrEJECTEDBY(rEQUESTS.getrEJECTEDBY());
                }
                if (rEQUESTS.getrEJECTEDON() != null) {
                    existingREQUESTS.setrEJECTEDON(rEQUESTS.getrEJECTEDON());
                }

                return existingREQUESTS;
            })
            .map(rEQUESTSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rEQUESTS.getId().toString())
        );
    }

    /**
     * {@code GET  /requests} : get all the rEQUESTS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rEQUESTS in body.
     */
    @GetMapping("")
    public List<REQUESTS> getAllREQUESTS() {
        LOG.debug("REST request to get all REQUESTS");
        return rEQUESTSRepository.findAll();
    }

    /**
     * {@code GET  /requests/:id} : get the "id" rEQUESTS.
     *
     * @param id the id of the rEQUESTS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rEQUESTS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<REQUESTS> getREQUESTS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get REQUESTS : {}", id);
        Optional<REQUESTS> rEQUESTS = rEQUESTSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rEQUESTS);
    }

    /**
     * {@code DELETE  /requests/:id} : delete the "id" rEQUESTS.
     *
     * @param id the id of the rEQUESTS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteREQUESTS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete REQUESTS : {}", id);
        rEQUESTSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
