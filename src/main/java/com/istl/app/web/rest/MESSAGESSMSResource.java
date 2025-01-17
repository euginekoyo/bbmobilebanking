package com.istl.app.web.rest;

import com.istl.app.domain.MESSAGESSMS;
import com.istl.app.repository.MESSAGESSMSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.MESSAGESSMS}.
 */
@RestController
@RequestMapping("/api/messagessms")
@Transactional
public class MESSAGESSMSResource {

    private static final Logger LOG = LoggerFactory.getLogger(MESSAGESSMSResource.class);

    private static final String ENTITY_NAME = "mESSAGESSMS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MESSAGESSMSRepository mESSAGESSMSRepository;

    public MESSAGESSMSResource(MESSAGESSMSRepository mESSAGESSMSRepository) {
        this.mESSAGESSMSRepository = mESSAGESSMSRepository;
    }

    /**
     * {@code POST  /messagessms} : Create a new mESSAGESSMS.
     *
     * @param mESSAGESSMS the mESSAGESSMS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mESSAGESSMS, or with status {@code 400 (Bad Request)} if the mESSAGESSMS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MESSAGESSMS> createMESSAGESSMS(@Valid @RequestBody MESSAGESSMS mESSAGESSMS) throws URISyntaxException {
        LOG.debug("REST request to save MESSAGESSMS : {}", mESSAGESSMS);
        if (mESSAGESSMS.getId() != null) {
            throw new BadRequestAlertException("A new mESSAGESSMS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        mESSAGESSMS = mESSAGESSMSRepository.save(mESSAGESSMS);
        return ResponseEntity.created(new URI("/api/messagessms/" + mESSAGESSMS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, mESSAGESSMS.getId().toString()))
            .body(mESSAGESSMS);
    }

    /**
     * {@code PUT  /messagessms/:id} : Updates an existing mESSAGESSMS.
     *
     * @param id the id of the mESSAGESSMS to save.
     * @param mESSAGESSMS the mESSAGESSMS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mESSAGESSMS,
     * or with status {@code 400 (Bad Request)} if the mESSAGESSMS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mESSAGESSMS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MESSAGESSMS> updateMESSAGESSMS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MESSAGESSMS mESSAGESSMS
    ) throws URISyntaxException {
        LOG.debug("REST request to update MESSAGESSMS : {}, {}", id, mESSAGESSMS);
        if (mESSAGESSMS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mESSAGESSMS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mESSAGESSMSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        mESSAGESSMS = mESSAGESSMSRepository.save(mESSAGESSMS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mESSAGESSMS.getId().toString()))
            .body(mESSAGESSMS);
    }

    /**
     * {@code PATCH  /messagessms/:id} : Partial updates given fields of an existing mESSAGESSMS, field will ignore if it is null
     *
     * @param id the id of the mESSAGESSMS to save.
     * @param mESSAGESSMS the mESSAGESSMS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mESSAGESSMS,
     * or with status {@code 400 (Bad Request)} if the mESSAGESSMS is not valid,
     * or with status {@code 404 (Not Found)} if the mESSAGESSMS is not found,
     * or with status {@code 500 (Internal Server Error)} if the mESSAGESSMS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MESSAGESSMS> partialUpdateMESSAGESSMS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MESSAGESSMS mESSAGESSMS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MESSAGESSMS partially : {}, {}", id, mESSAGESSMS);
        if (mESSAGESSMS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mESSAGESSMS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mESSAGESSMSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MESSAGESSMS> result = mESSAGESSMSRepository
            .findById(mESSAGESSMS.getId())
            .map(existingMESSAGESSMS -> {
                if (mESSAGESSMS.gettRNDATETIME() != null) {
                    existingMESSAGESSMS.settRNDATETIME(mESSAGESSMS.gettRNDATETIME());
                }
                if (mESSAGESSMS.getpHONENUMBER() != null) {
                    existingMESSAGESSMS.setpHONENUMBER(mESSAGESSMS.getpHONENUMBER());
                }
                if (mESSAGESSMS.gettRANSACTIONNO() != null) {
                    existingMESSAGESSMS.settRANSACTIONNO(mESSAGESSMS.gettRANSACTIONNO());
                }
                if (mESSAGESSMS.getaCCOUNTNUMBER() != null) {
                    existingMESSAGESSMS.setaCCOUNTNUMBER(mESSAGESSMS.getaCCOUNTNUMBER());
                }
                if (mESSAGESSMS.getmESSAGE() != null) {
                    existingMESSAGESSMS.setmESSAGE(mESSAGESSMS.getmESSAGE());
                }
                if (mESSAGESSMS.getcHANNEL() != null) {
                    existingMESSAGESSMS.setcHANNEL(mESSAGESSMS.getcHANNEL());
                }
                if (mESSAGESSMS.gettRIALS() != null) {
                    existingMESSAGESSMS.settRIALS(mESSAGESSMS.gettRIALS());
                }
                if (mESSAGESSMS.getpRIORITY() != null) {
                    existingMESSAGESSMS.setpRIORITY(mESSAGESSMS.getpRIORITY());
                }
                if (mESSAGESSMS.getrESPONSECODE() != null) {
                    existingMESSAGESSMS.setrESPONSECODE(mESSAGESSMS.getrESPONSECODE());
                }
                if (mESSAGESSMS.getrESPONSEMSG() != null) {
                    existingMESSAGESSMS.setrESPONSEMSG(mESSAGESSMS.getrESPONSEMSG());
                }
                if (mESSAGESSMS.getsENT() != null) {
                    existingMESSAGESSMS.setsENT(mESSAGESSMS.getsENT());
                }
                if (mESSAGESSMS.getdELIVERED() != null) {
                    existingMESSAGESSMS.setdELIVERED(mESSAGESSMS.getdELIVERED());
                }
                if (mESSAGESSMS.gettXNTYPE() != null) {
                    existingMESSAGESSMS.settXNTYPE(mESSAGESSMS.gettXNTYPE());
                }
                if (mESSAGESSMS.geteRROREXCEPTION() != null) {
                    existingMESSAGESSMS.seteRROREXCEPTION(mESSAGESSMS.geteRROREXCEPTION());
                }
                if (mESSAGESSMS.getdATECREATED() != null) {
                    existingMESSAGESSMS.setdATECREATED(mESSAGESSMS.getdATECREATED());
                }
                if (mESSAGESSMS.getdATESENT() != null) {
                    existingMESSAGESSMS.setdATESENT(mESSAGESSMS.getdATESENT());
                }
                if (mESSAGESSMS.getrTPSREQTIME() != null) {
                    existingMESSAGESSMS.setrTPSREQTIME(mESSAGESSMS.getrTPSREQTIME());
                }
                if (mESSAGESSMS.getfXGENERATED() != null) {
                    existingMESSAGESSMS.setfXGENERATED(mESSAGESSMS.getfXGENERATED());
                }
                if (mESSAGESSMS.gettAXPROCESSED() != null) {
                    existingMESSAGESSMS.settAXPROCESSED(mESSAGESSMS.gettAXPROCESSED());
                }
                if (mESSAGESSMS.getbATCHNUMBER() != null) {
                    existingMESSAGESSMS.setbATCHNUMBER(mESSAGESSMS.getbATCHNUMBER());
                }
                if (mESSAGESSMS.getbATCHNUMBERTAX() != null) {
                    existingMESSAGESSMS.setbATCHNUMBERTAX(mESSAGESSMS.getbATCHNUMBERTAX());
                }
                if (mESSAGESSMS.getrESPONSETIME() != null) {
                    existingMESSAGESSMS.setrESPONSETIME(mESSAGESSMS.getrESPONSETIME());
                }
                if (mESSAGESSMS.getpDUSEQID() != null) {
                    existingMESSAGESSMS.setpDUSEQID(mESSAGESSMS.getpDUSEQID());
                }
                if (mESSAGESSMS.getrEMARKS() != null) {
                    existingMESSAGESSMS.setrEMARKS(mESSAGESSMS.getrEMARKS());
                }
                if (mESSAGESSMS.getrESENDBY() != null) {
                    existingMESSAGESSMS.setrESENDBY(mESSAGESSMS.getrESENDBY());
                }

                return existingMESSAGESSMS;
            })
            .map(mESSAGESSMSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mESSAGESSMS.getId().toString())
        );
    }

    /**
     * {@code GET  /messagessms} : get all the mESSAGESSMS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mESSAGESSMS in body.
     */
    @GetMapping("")
    public List<MESSAGESSMS> getAllMESSAGESSMS() {
        LOG.debug("REST request to get all MESSAGESSMS");
        return mESSAGESSMSRepository.findAll();
    }

    /**
     * {@code GET  /messagessms/:id} : get the "id" mESSAGESSMS.
     *
     * @param id the id of the mESSAGESSMS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mESSAGESSMS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MESSAGESSMS> getMESSAGESSMS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MESSAGESSMS : {}", id);
        Optional<MESSAGESSMS> mESSAGESSMS = mESSAGESSMSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mESSAGESSMS);
    }

    /**
     * {@code DELETE  /messagessms/:id} : delete the "id" mESSAGESSMS.
     *
     * @param id the id of the mESSAGESSMS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMESSAGESSMS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MESSAGESSMS : {}", id);
        mESSAGESSMSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
