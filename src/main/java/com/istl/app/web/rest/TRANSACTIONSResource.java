package com.istl.app.web.rest;

import com.istl.app.domain.TRANSACTIONS;
import com.istl.app.repository.TRANSACTIONSRepository;
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
 * REST controller for managing {@link com.istl.app.domain.TRANSACTIONS}.
 */
@RestController
@RequestMapping("/api/transactions")
@Transactional
public class TRANSACTIONSResource {

    private static final Logger LOG = LoggerFactory.getLogger(TRANSACTIONSResource.class);

    private static final String ENTITY_NAME = "tRANSACTIONS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TRANSACTIONSRepository tRANSACTIONSRepository;

    public TRANSACTIONSResource(TRANSACTIONSRepository tRANSACTIONSRepository) {
        this.tRANSACTIONSRepository = tRANSACTIONSRepository;
    }

    /**
     * {@code POST  /transactions} : Create a new tRANSACTIONS.
     *
     * @param tRANSACTIONS the tRANSACTIONS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tRANSACTIONS, or with status {@code 400 (Bad Request)} if the tRANSACTIONS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TRANSACTIONS> createTRANSACTIONS(@Valid @RequestBody TRANSACTIONS tRANSACTIONS) throws URISyntaxException {
        LOG.debug("REST request to save TRANSACTIONS : {}", tRANSACTIONS);
        if (tRANSACTIONS.getId() != null) {
            throw new BadRequestAlertException("A new tRANSACTIONS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tRANSACTIONS = tRANSACTIONSRepository.save(tRANSACTIONS);
        return ResponseEntity.created(new URI("/api/transactions/" + tRANSACTIONS.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tRANSACTIONS.getId().toString()))
            .body(tRANSACTIONS);
    }

    /**
     * {@code PUT  /transactions/:id} : Updates an existing tRANSACTIONS.
     *
     * @param id the id of the tRANSACTIONS to save.
     * @param tRANSACTIONS the tRANSACTIONS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tRANSACTIONS,
     * or with status {@code 400 (Bad Request)} if the tRANSACTIONS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tRANSACTIONS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TRANSACTIONS> updateTRANSACTIONS(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TRANSACTIONS tRANSACTIONS
    ) throws URISyntaxException {
        LOG.debug("REST request to update TRANSACTIONS : {}, {}", id, tRANSACTIONS);
        if (tRANSACTIONS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tRANSACTIONS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tRANSACTIONSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tRANSACTIONS = tRANSACTIONSRepository.save(tRANSACTIONS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tRANSACTIONS.getId().toString()))
            .body(tRANSACTIONS);
    }

    /**
     * {@code PATCH  /transactions/:id} : Partial updates given fields of an existing tRANSACTIONS, field will ignore if it is null
     *
     * @param id the id of the tRANSACTIONS to save.
     * @param tRANSACTIONS the tRANSACTIONS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tRANSACTIONS,
     * or with status {@code 400 (Bad Request)} if the tRANSACTIONS is not valid,
     * or with status {@code 404 (Not Found)} if the tRANSACTIONS is not found,
     * or with status {@code 500 (Internal Server Error)} if the tRANSACTIONS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TRANSACTIONS> partialUpdateTRANSACTIONS(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TRANSACTIONS tRANSACTIONS
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TRANSACTIONS partially : {}, {}", id, tRANSACTIONS);
        if (tRANSACTIONS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tRANSACTIONS.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tRANSACTIONSRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TRANSACTIONS> result = tRANSACTIONSRepository
            .findById(tRANSACTIONS.getId())
            .map(existingTRANSACTIONS -> {
                if (tRANSACTIONS.getpROCESSED() != null) {
                    existingTRANSACTIONS.setpROCESSED(tRANSACTIONS.getpROCESSED());
                }
                if (tRANSACTIONS.getiNCOMINGBITMAP() != null) {
                    existingTRANSACTIONS.setiNCOMINGBITMAP(tRANSACTIONS.getiNCOMINGBITMAP());
                }
                if (tRANSACTIONS.getoUTGOINGBITMAP() != null) {
                    existingTRANSACTIONS.setoUTGOINGBITMAP(tRANSACTIONS.getoUTGOINGBITMAP());
                }
                if (tRANSACTIONS.getiNMESSAGE() != null) {
                    existingTRANSACTIONS.setiNMESSAGE(tRANSACTIONS.getiNMESSAGE());
                }
                if (tRANSACTIONS.getmESSAGETOCBS() != null) {
                    existingTRANSACTIONS.setmESSAGETOCBS(tRANSACTIONS.getmESSAGETOCBS());
                }
                if (tRANSACTIONS.getmESSAGEFROMCBS() != null) {
                    existingTRANSACTIONS.setmESSAGEFROMCBS(tRANSACTIONS.getmESSAGEFROMCBS());
                }
                if (tRANSACTIONS.getcBSPROCESS() != null) {
                    existingTRANSACTIONS.setcBSPROCESS(tRANSACTIONS.getcBSPROCESS());
                }
                if (tRANSACTIONS.getcBSONLINE() != null) {
                    existingTRANSACTIONS.setcBSONLINE(tRANSACTIONS.getcBSONLINE());
                }
                if (tRANSACTIONS.getcBSRESPONSE() != null) {
                    existingTRANSACTIONS.setcBSRESPONSE(tRANSACTIONS.getcBSRESPONSE());
                }
                if (tRANSACTIONS.getrESPONSEMESSAGE() != null) {
                    existingTRANSACTIONS.setrESPONSEMESSAGE(tRANSACTIONS.getrESPONSEMESSAGE());
                }
                if (tRANSACTIONS.getrESPONSESENT() != null) {
                    existingTRANSACTIONS.setrESPONSESENT(tRANSACTIONS.getrESPONSESENT());
                }
                if (tRANSACTIONS.getcHANNEL() != null) {
                    existingTRANSACTIONS.setcHANNEL(tRANSACTIONS.getcHANNEL());
                }
                if (tRANSACTIONS.getoRIGINALDATA() != null) {
                    existingTRANSACTIONS.setoRIGINALDATA(tRANSACTIONS.getoRIGINALDATA());
                }
                if (tRANSACTIONS.getfIELD39RESP() != null) {
                    existingTRANSACTIONS.setfIELD39RESP(tRANSACTIONS.getfIELD39RESP());
                }
                if (tRANSACTIONS.getnARRATION() != null) {
                    existingTRANSACTIONS.setnARRATION(tRANSACTIONS.getnARRATION());
                }
                if (tRANSACTIONS.getaUTHORISED() != null) {
                    existingTRANSACTIONS.setaUTHORISED(tRANSACTIONS.getaUTHORISED());
                }
                if (tRANSACTIONS.getbRANCHCODE() != null) {
                    existingTRANSACTIONS.setbRANCHCODE(tRANSACTIONS.getbRANCHCODE());
                }
                if (tRANSACTIONS.getfIELD39ORIGINAL() != null) {
                    existingTRANSACTIONS.setfIELD39ORIGINAL(tRANSACTIONS.getfIELD39ORIGINAL());
                }
                if (tRANSACTIONS.getmESSAGECLASS() != null) {
                    existingTRANSACTIONS.setmESSAGECLASS(tRANSACTIONS.getmESSAGECLASS());
                }
                if (tRANSACTIONS.gettXNCODE() != null) {
                    existingTRANSACTIONS.settXNCODE(tRANSACTIONS.gettXNCODE());
                }
                if (tRANSACTIONS.getcURRCODE() != null) {
                    existingTRANSACTIONS.setcURRCODE(tRANSACTIONS.getcURRCODE());
                }
                if (tRANSACTIONS.getdEVICE() != null) {
                    existingTRANSACTIONS.setdEVICE(tRANSACTIONS.getdEVICE());
                }
                if (tRANSACTIONS.getbRANCH2() != null) {
                    existingTRANSACTIONS.setbRANCH2(tRANSACTIONS.getbRANCH2());
                }
                if (tRANSACTIONS.getLongERBRANCH() != null) {
                    existingTRANSACTIONS.setLongERBRANCH(tRANSACTIONS.getLongERBRANCH());
                }
                if (tRANSACTIONS.getdATEX() != null) {
                    existingTRANSACTIONS.setdATEX(tRANSACTIONS.getdATEX());
                }
                if (tRANSACTIONS.gettIMEX() != null) {
                    existingTRANSACTIONS.settIMEX(tRANSACTIONS.gettIMEX());
                }
                if (tRANSACTIONS.getpOSTED() != null) {
                    existingTRANSACTIONS.setpOSTED(tRANSACTIONS.getpOSTED());
                }
                if (tRANSACTIONS.getaTTEMPTS() != null) {
                    existingTRANSACTIONS.setaTTEMPTS(tRANSACTIONS.getaTTEMPTS());
                }
                if (tRANSACTIONS.getoRIGINALDATA2() != null) {
                    existingTRANSACTIONS.setoRIGINALDATA2(tRANSACTIONS.getoRIGINALDATA2());
                }
                if (tRANSACTIONS.getcOMMISSION() != null) {
                    existingTRANSACTIONS.setcOMMISSION(tRANSACTIONS.getcOMMISSION());
                }
                if (tRANSACTIONS.getrESPONSECREATED() != null) {
                    existingTRANSACTIONS.setrESPONSECREATED(tRANSACTIONS.getrESPONSECREATED());
                }
                if (tRANSACTIONS.getoNLINE() != null) {
                    existingTRANSACTIONS.setoNLINE(tRANSACTIONS.getoNLINE());
                }
                if (tRANSACTIONS.getoRIGINALDATA3() != null) {
                    existingTRANSACTIONS.setoRIGINALDATA3(tRANSACTIONS.getoRIGINALDATA3());
                }
                if (tRANSACTIONS.gettOSWITCH() != null) {
                    existingTRANSACTIONS.settOSWITCH(tRANSACTIONS.gettOSWITCH());
                }
                if (tRANSACTIONS.getfROMSWITCH() != null) {
                    existingTRANSACTIONS.setfROMSWITCH(tRANSACTIONS.getfROMSWITCH());
                }
                if (tRANSACTIONS.gettOCBS() != null) {
                    existingTRANSACTIONS.settOCBS(tRANSACTIONS.gettOCBS());
                }
                if (tRANSACTIONS.getfROMCBS() != null) {
                    existingTRANSACTIONS.setfROMCBS(tRANSACTIONS.getfROMCBS());
                }
                if (tRANSACTIONS.getpOSTINGLEGS() != null) {
                    existingTRANSACTIONS.setpOSTINGLEGS(tRANSACTIONS.getpOSTINGLEGS());
                }
                if (tRANSACTIONS.getcOMMISSIONTXNCODE() != null) {
                    existingTRANSACTIONS.setcOMMISSIONTXNCODE(tRANSACTIONS.getcOMMISSIONTXNCODE());
                }
                if (tRANSACTIONS.gethOSTREF() != null) {
                    existingTRANSACTIONS.sethOSTREF(tRANSACTIONS.gethOSTREF());
                }
                if (tRANSACTIONS.getrEQUESTCREATED() != null) {
                    existingTRANSACTIONS.setrEQUESTCREATED(tRANSACTIONS.getrEQUESTCREATED());
                }
                if (tRANSACTIONS.getrEQUESTMESSAGE() != null) {
                    existingTRANSACTIONS.setrEQUESTMESSAGE(tRANSACTIONS.getrEQUESTMESSAGE());
                }
                if (tRANSACTIONS.getoUTGOINGBITMAPFLEX() != null) {
                    existingTRANSACTIONS.setoUTGOINGBITMAPFLEX(tRANSACTIONS.getoUTGOINGBITMAPFLEX());
                }
                if (tRANSACTIONS.getiNCOMINGBITMAPFLEX() != null) {
                    existingTRANSACTIONS.setiNCOMINGBITMAPFLEX(tRANSACTIONS.getiNCOMINGBITMAPFLEX());
                }
                if (tRANSACTIONS.getrEQUESTSENT() != null) {
                    existingTRANSACTIONS.setrEQUESTSENT(tRANSACTIONS.getrEQUESTSENT());
                }
                if (tRANSACTIONS.getmINICBS() != null) {
                    existingTRANSACTIONS.setmINICBS(tRANSACTIONS.getmINICBS());
                }
                if (tRANSACTIONS.getrEVERSED() != null) {
                    existingTRANSACTIONS.setrEVERSED(tRANSACTIONS.getrEVERSED());
                }
                if (tRANSACTIONS.getoFFLINESENTTOHOST() != null) {
                    existingTRANSACTIONS.setoFFLINESENTTOHOST(tRANSACTIONS.getoFFLINESENTTOHOST());
                }
                if (tRANSACTIONS.getoFFLINERESPONSE() != null) {
                    existingTRANSACTIONS.setoFFLINERESPONSE(tRANSACTIONS.getoFFLINERESPONSE());
                }
                if (tRANSACTIONS.getsOURCELongERFACE() != null) {
                    existingTRANSACTIONS.setsOURCELongERFACE(tRANSACTIONS.getsOURCELongERFACE());
                }
                if (tRANSACTIONS.getmTIRRN() != null) {
                    existingTRANSACTIONS.setmTIRRN(tRANSACTIONS.getmTIRRN());
                }
                if (tRANSACTIONS.gethOSTRESPONSECODE() != null) {
                    existingTRANSACTIONS.sethOSTRESPONSECODE(tRANSACTIONS.gethOSTRESPONSECODE());
                }
                if (tRANSACTIONS.getfIELD48() != null) {
                    existingTRANSACTIONS.setfIELD48(tRANSACTIONS.getfIELD48());
                }
                if (tRANSACTIONS.getsOURCE() != null) {
                    existingTRANSACTIONS.setsOURCE(tRANSACTIONS.getsOURCE());
                }

                return existingTRANSACTIONS;
            })
            .map(tRANSACTIONSRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tRANSACTIONS.getId().toString())
        );
    }

    /**
     * {@code GET  /transactions} : get all the tRANSACTIONS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tRANSACTIONS in body.
     */
    @GetMapping("")
    public List<TRANSACTIONS> getAllTRANSACTIONS() {
        LOG.debug("REST request to get all TRANSACTIONS");
        return tRANSACTIONSRepository.findAll();
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" tRANSACTIONS.
     *
     * @param id the id of the tRANSACTIONS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tRANSACTIONS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TRANSACTIONS> getTRANSACTIONS(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TRANSACTIONS : {}", id);
        Optional<TRANSACTIONS> tRANSACTIONS = tRANSACTIONSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tRANSACTIONS);
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" tRANSACTIONS.
     *
     * @param id the id of the tRANSACTIONS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTRANSACTIONS(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TRANSACTIONS : {}", id);
        tRANSACTIONSRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
