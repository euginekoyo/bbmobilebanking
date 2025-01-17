package com.istl.app.web.rest;

import com.istl.app.domain.CUSTOMER;
import com.istl.app.repository.CUSTOMERRepository;
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
 * REST controller for managing {@link com.istl.app.domain.CUSTOMER}.
 */
@RestController
@RequestMapping("/api/customers")
@Transactional
public class CUSTOMERResource {

    private static final Logger LOG = LoggerFactory.getLogger(CUSTOMERResource.class);

    private static final String ENTITY_NAME = "cUSTOMER";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CUSTOMERRepository cUSTOMERRepository;

    public CUSTOMERResource(CUSTOMERRepository cUSTOMERRepository) {
        this.cUSTOMERRepository = cUSTOMERRepository;
    }

    /**
     * {@code POST  /customers} : Create a new cUSTOMER.
     *
     * @param cUSTOMER the cUSTOMER to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cUSTOMER, or with status {@code 400 (Bad Request)} if the cUSTOMER has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CUSTOMER> createCUSTOMER(@Valid @RequestBody CUSTOMER cUSTOMER) throws URISyntaxException {
        LOG.debug("REST request to save CUSTOMER : {}", cUSTOMER);
        if (cUSTOMER.getId() != null) {
            throw new BadRequestAlertException("A new cUSTOMER cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cUSTOMER = cUSTOMERRepository.save(cUSTOMER);
        return ResponseEntity.created(new URI("/api/customers/" + cUSTOMER.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cUSTOMER.getId().toString()))
            .body(cUSTOMER);
    }

    /**
     * {@code PUT  /customers/:id} : Updates an existing cUSTOMER.
     *
     * @param id the id of the cUSTOMER to save.
     * @param cUSTOMER the cUSTOMER to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cUSTOMER,
     * or with status {@code 400 (Bad Request)} if the cUSTOMER is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cUSTOMER couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER> updateCUSTOMER(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CUSTOMER cUSTOMER
    ) throws URISyntaxException {
        LOG.debug("REST request to update CUSTOMER : {}, {}", id, cUSTOMER);
        if (cUSTOMER.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cUSTOMER.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cUSTOMERRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cUSTOMER = cUSTOMERRepository.save(cUSTOMER);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cUSTOMER.getId().toString()))
            .body(cUSTOMER);
    }

    /**
     * {@code PATCH  /customers/:id} : Partial updates given fields of an existing cUSTOMER, field will ignore if it is null
     *
     * @param id the id of the cUSTOMER to save.
     * @param cUSTOMER the cUSTOMER to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cUSTOMER,
     * or with status {@code 400 (Bad Request)} if the cUSTOMER is not valid,
     * or with status {@code 404 (Not Found)} if the cUSTOMER is not found,
     * or with status {@code 500 (Internal Server Error)} if the cUSTOMER couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CUSTOMER> partialUpdateCUSTOMER(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CUSTOMER cUSTOMER
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CUSTOMER partially : {}, {}", id, cUSTOMER);
        if (cUSTOMER.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cUSTOMER.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cUSTOMERRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CUSTOMER> result = cUSTOMERRepository
            .findById(cUSTOMER.getId())
            .map(existingCUSTOMER -> {
                if (cUSTOMER.getcUSTOMERNAME() != null) {
                    existingCUSTOMER.setcUSTOMERNAME(cUSTOMER.getcUSTOMERNAME());
                }
                if (cUSTOMER.getpHONENUMBER() != null) {
                    existingCUSTOMER.setpHONENUMBER(cUSTOMER.getpHONENUMBER());
                }
                if (cUSTOMER.getcARDNUMBER() != null) {
                    existingCUSTOMER.setcARDNUMBER(cUSTOMER.getcARDNUMBER());
                }
                if (cUSTOMER.getaCCOUNTNUMBER() != null) {
                    existingCUSTOMER.setaCCOUNTNUMBER(cUSTOMER.getaCCOUNTNUMBER());
                }
                if (cUSTOMER.getlANG() != null) {
                    existingCUSTOMER.setlANG(cUSTOMER.getlANG());
                }
                if (cUSTOMER.getpIN() != null) {
                    existingCUSTOMER.setpIN(cUSTOMER.getpIN());
                }
                if (cUSTOMER.getfIRSTLOGIN() != null) {
                    existingCUSTOMER.setfIRSTLOGIN(cUSTOMER.getfIRSTLOGIN());
                }
                if (cUSTOMER.getaCTIVE() != null) {
                    existingCUSTOMER.setaCTIVE(cUSTOMER.getaCTIVE());
                }
                if (cUSTOMER.getrEGISTERED() != null) {
                    existingCUSTOMER.setrEGISTERED(cUSTOMER.getrEGISTERED());
                }
                if (cUSTOMER.getcSTDELETE() != null) {
                    existingCUSTOMER.setcSTDELETE(cUSTOMER.getcSTDELETE());
                }
                if (cUSTOMER.getrEGDATE() != null) {
                    existingCUSTOMER.setrEGDATE(cUSTOMER.getrEGDATE());
                }
                if (cUSTOMER.getaLERTENABLED() != null) {
                    existingCUSTOMER.setaLERTENABLED(cUSTOMER.getaLERTENABLED());
                }
                if (cUSTOMER.getrEMARK() != null) {
                    existingCUSTOMER.setrEMARK(cUSTOMER.getrEMARK());
                }
                if (cUSTOMER.getiMSI() != null) {
                    existingCUSTOMER.setiMSI(cUSTOMER.getiMSI());
                }
                if (cUSTOMER.getpARTIALLYREGISTERED() != null) {
                    existingCUSTOMER.setpARTIALLYREGISTERED(cUSTOMER.getpARTIALLYREGISTERED());
                }
                if (cUSTOMER.getpARTIALDATE() != null) {
                    existingCUSTOMER.setpARTIALDATE(cUSTOMER.getpARTIALDATE());
                }
                if (cUSTOMER.getrEGISTERDATE() != null) {
                    existingCUSTOMER.setrEGISTERDATE(cUSTOMER.getrEGISTERDATE());
                }
                if (cUSTOMER.getaPPROVED() != null) {
                    existingCUSTOMER.setaPPROVED(cUSTOMER.getaPPROVED());
                }
                if (cUSTOMER.getaPPROVEDBY() != null) {
                    existingCUSTOMER.setaPPROVEDBY(cUSTOMER.getaPPROVEDBY());
                }
                if (cUSTOMER.getaPPROVEDDATE() != null) {
                    existingCUSTOMER.setaPPROVEDDATE(cUSTOMER.getaPPROVEDDATE());
                }
                if (cUSTOMER.getdECLINED() != null) {
                    existingCUSTOMER.setdECLINED(cUSTOMER.getdECLINED());
                }
                if (cUSTOMER.getdECLINEDBY() != null) {
                    existingCUSTOMER.setdECLINEDBY(cUSTOMER.getdECLINEDBY());
                }
                if (cUSTOMER.getdECLINEDDATE() != null) {
                    existingCUSTOMER.setdECLINEDDATE(cUSTOMER.getdECLINEDDATE());
                }
                if (cUSTOMER.getcHECKERREMARKS() != null) {
                    existingCUSTOMER.setcHECKERREMARKS(cUSTOMER.getcHECKERREMARKS());
                }
                if (cUSTOMER.getpOSTALADDRESS() != null) {
                    existingCUSTOMER.setpOSTALADDRESS(cUSTOMER.getpOSTALADDRESS());
                }
                if (cUSTOMER.getrESIDENCE() != null) {
                    existingCUSTOMER.setrESIDENCE(cUSTOMER.getrESIDENCE());
                }
                if (cUSTOMER.getdOB() != null) {
                    existingCUSTOMER.setdOB(cUSTOMER.getdOB());
                }
                if (cUSTOMER.getcREATEDBY() != null) {
                    existingCUSTOMER.setcREATEDBY(cUSTOMER.getcREATEDBY());
                }
                if (cUSTOMER.geteMAILADDRESS() != null) {
                    existingCUSTOMER.seteMAILADDRESS(cUSTOMER.geteMAILADDRESS());
                }
                if (cUSTOMER.getiDENTIFICATIONID() != null) {
                    existingCUSTOMER.setiDENTIFICATIONID(cUSTOMER.getiDENTIFICATIONID());
                }
                if (cUSTOMER.getaDDACCOUNT() != null) {
                    existingCUSTOMER.setaDDACCOUNT(cUSTOMER.getaDDACCOUNT());
                }
                if (cUSTOMER.getaCLINKINGINSTITUTION() != null) {
                    existingCUSTOMER.setaCLINKINGINSTITUTION(cUSTOMER.getaCLINKINGINSTITUTION());
                }
                if (cUSTOMER.getdEACTIVATED() != null) {
                    existingCUSTOMER.setdEACTIVATED(cUSTOMER.getdEACTIVATED());
                }
                if (cUSTOMER.getdEACTIVATEDBY() != null) {
                    existingCUSTOMER.setdEACTIVATEDBY(cUSTOMER.getdEACTIVATEDBY());
                }
                if (cUSTOMER.getdEACTIVATEDON() != null) {
                    existingCUSTOMER.setdEACTIVATEDON(cUSTOMER.getdEACTIVATEDON());
                }
                if (cUSTOMER.getpHONENOCHANGED() != null) {
                    existingCUSTOMER.setpHONENOCHANGED(cUSTOMER.getpHONENOCHANGED());
                }
                if (cUSTOMER.getpHONENOCHANGEDBY() != null) {
                    existingCUSTOMER.setpHONENOCHANGEDBY(cUSTOMER.getpHONENOCHANGEDBY());
                }
                if (cUSTOMER.getpHONENOCHANGEDON() != null) {
                    existingCUSTOMER.setpHONENOCHANGEDON(cUSTOMER.getpHONENOCHANGEDON());
                }
                if (cUSTOMER.getoRIGINALPHONENO() != null) {
                    existingCUSTOMER.setoRIGINALPHONENO(cUSTOMER.getoRIGINALPHONENO());
                }
                if (cUSTOMER.getnEWPHONENO() != null) {
                    existingCUSTOMER.setnEWPHONENO(cUSTOMER.getnEWPHONENO());
                }
                if (cUSTOMER.getrESET() != null) {
                    existingCUSTOMER.setrESET(cUSTOMER.getrESET());
                }
                if (cUSTOMER.getrESETINGINSTITUTION() != null) {
                    existingCUSTOMER.setrESETINGINSTITUTION(cUSTOMER.getrESETINGINSTITUTION());
                }
                if (cUSTOMER.getpINRESETREMARK() != null) {
                    existingCUSTOMER.setpINRESETREMARK(cUSTOMER.getpINRESETREMARK());
                }
                if (cUSTOMER.getrESETBY() != null) {
                    existingCUSTOMER.setrESETBY(cUSTOMER.getrESETBY());
                }
                if (cUSTOMER.getrESETON() != null) {
                    existingCUSTOMER.setrESETON(cUSTOMER.getrESETON());
                }
                if (cUSTOMER.getuNBLOCKINGINSTITUTION() != null) {
                    existingCUSTOMER.setuNBLOCKINGINSTITUTION(cUSTOMER.getuNBLOCKINGINSTITUTION());
                }
                if (cUSTOMER.getpINBLOCK() != null) {
                    existingCUSTOMER.setpINBLOCK(cUSTOMER.getpINBLOCK());
                }
                if (cUSTOMER.getpINBLOCKBY() != null) {
                    existingCUSTOMER.setpINBLOCKBY(cUSTOMER.getpINBLOCKBY());
                }
                if (cUSTOMER.getpINBLOCKREMARKS() != null) {
                    existingCUSTOMER.setpINBLOCKREMARKS(cUSTOMER.getpINBLOCKREMARKS());
                }
                if (cUSTOMER.getbLOCKINGINSTITUTION() != null) {
                    existingCUSTOMER.setbLOCKINGINSTITUTION(cUSTOMER.getbLOCKINGINSTITUTION());
                }
                if (cUSTOMER.getpINBLOCKON() != null) {
                    existingCUSTOMER.setpINBLOCKON(cUSTOMER.getpINBLOCKON());
                }
                if (cUSTOMER.getaPPROVEDON() != null) {
                    existingCUSTOMER.setaPPROVEDON(cUSTOMER.getaPPROVEDON());
                }
                if (cUSTOMER.getpINUNBLOCKBY() != null) {
                    existingCUSTOMER.setpINUNBLOCKBY(cUSTOMER.getpINUNBLOCKBY());
                }
                if (cUSTOMER.getlOGGEDIN() != null) {
                    existingCUSTOMER.setlOGGEDIN(cUSTOMER.getlOGGEDIN());
                }
                if (cUSTOMER.gettRIALS() != null) {
                    existingCUSTOMER.settRIALS(cUSTOMER.gettRIALS());
                }
                if (cUSTOMER.getiDTYPE() != null) {
                    existingCUSTOMER.setiDTYPE(cUSTOMER.getiDTYPE());
                }
                if (cUSTOMER.getiDNUMBER() != null) {
                    existingCUSTOMER.setiDNUMBER(cUSTOMER.getiDNUMBER());
                }
                if (cUSTOMER.getgENDER() != null) {
                    existingCUSTOMER.setgENDER(cUSTOMER.getgENDER());
                }
                if (cUSTOMER.getcIF() != null) {
                    existingCUSTOMER.setcIF(cUSTOMER.getcIF());
                }
                if (cUSTOMER.getdATEOFBIRTH() != null) {
                    existingCUSTOMER.setdATEOFBIRTH(cUSTOMER.getdATEOFBIRTH());
                }
                if (cUSTOMER.getrEMARKS() != null) {
                    existingCUSTOMER.setrEMARKS(cUSTOMER.getrEMARKS());
                }
                if (cUSTOMER.getrESETIMSI() != null) {
                    existingCUSTOMER.setrESETIMSI(cUSTOMER.getrESETIMSI());
                }
                if (cUSTOMER.getiMSIRESETBY() != null) {
                    existingCUSTOMER.setiMSIRESETBY(cUSTOMER.getiMSIRESETBY());
                }
                if (cUSTOMER.getfIRSTNAME() != null) {
                    existingCUSTOMER.setfIRSTNAME(cUSTOMER.getfIRSTNAME());
                }
                if (cUSTOMER.getsECONDNAME() != null) {
                    existingCUSTOMER.setsECONDNAME(cUSTOMER.getsECONDNAME());
                }
                if (cUSTOMER.getlASTNAME() != null) {
                    existingCUSTOMER.setlASTNAME(cUSTOMER.getlASTNAME());
                }
                if (cUSTOMER.getpINBLOCKTIME() != null) {
                    existingCUSTOMER.setpINBLOCKTIME(cUSTOMER.getpINBLOCKTIME());
                }
                if (cUSTOMER.getcUSTOMERSTATUS() != null) {
                    existingCUSTOMER.setcUSTOMERSTATUS(cUSTOMER.getcUSTOMERSTATUS());
                }
                if (cUSTOMER.getuSERNAME() != null) {
                    existingCUSTOMER.setuSERNAME(cUSTOMER.getuSERNAME());
                }
                if (cUSTOMER.getpASSWORD() != null) {
                    existingCUSTOMER.setpASSWORD(cUSTOMER.getpASSWORD());
                }
                if (cUSTOMER.getdEVICEID() != null) {
                    existingCUSTOMER.setdEVICEID(cUSTOMER.getdEVICEID());
                }
                if (cUSTOMER.getcHANNEL() != null) {
                    existingCUSTOMER.setcHANNEL(cUSTOMER.getcHANNEL());
                }
                if (cUSTOMER.getpASSRESET() != null) {
                    existingCUSTOMER.setpASSRESET(cUSTOMER.getpASSRESET());
                }
                if (cUSTOMER.getpASSRESETBY() != null) {
                    existingCUSTOMER.setpASSRESETBY(cUSTOMER.getpASSRESETBY());
                }
                if (cUSTOMER.getpASSRESETON() != null) {
                    existingCUSTOMER.setpASSRESETON(cUSTOMER.getpASSRESETON());
                }
                if (cUSTOMER.getpASSBLOCK() != null) {
                    existingCUSTOMER.setpASSBLOCK(cUSTOMER.getpASSBLOCK());
                }
                if (cUSTOMER.getpASSBLOCKBY() != null) {
                    existingCUSTOMER.setpASSBLOCKBY(cUSTOMER.getpASSBLOCKBY());
                }
                if (cUSTOMER.getpASSBLOCKON() != null) {
                    existingCUSTOMER.setpASSBLOCKON(cUSTOMER.getpASSBLOCKON());
                }
                if (cUSTOMER.getpINMARKBLOCK() != null) {
                    existingCUSTOMER.setpINMARKBLOCK(cUSTOMER.getpINMARKBLOCK());
                }
                if (cUSTOMER.getpASSMARKBLOCK() != null) {
                    existingCUSTOMER.setpASSMARKBLOCK(cUSTOMER.getpASSMARKBLOCK());
                }
                if (cUSTOMER.getpASSRESETREMARKS() != null) {
                    existingCUSTOMER.setpASSRESETREMARKS(cUSTOMER.getpASSRESETREMARKS());
                }
                if (cUSTOMER.getpASSBLOCKREMARKS() != null) {
                    existingCUSTOMER.setpASSBLOCKREMARKS(cUSTOMER.getpASSBLOCKREMARKS());
                }
                if (cUSTOMER.getpASSUNBLOCKBY() != null) {
                    existingCUSTOMER.setpASSUNBLOCKBY(cUSTOMER.getpASSUNBLOCKBY());
                }
                if (cUSTOMER.getpASSTRIALS() != null) {
                    existingCUSTOMER.setpASSTRIALS(cUSTOMER.getpASSTRIALS());
                }
                if (cUSTOMER.getaPPACTIVE() != null) {
                    existingCUSTOMER.setaPPACTIVE(cUSTOMER.getaPPACTIVE());
                }
                if (cUSTOMER.getlASTLOGIN() != null) {
                    existingCUSTOMER.setlASTLOGIN(cUSTOMER.getlASTLOGIN());
                }
                if (cUSTOMER.getaPPMARKEDDISABLE() != null) {
                    existingCUSTOMER.setaPPMARKEDDISABLE(cUSTOMER.getaPPMARKEDDISABLE());
                }
                if (cUSTOMER.getdISABLEBY() != null) {
                    existingCUSTOMER.setdISABLEBY(cUSTOMER.getdISABLEBY());
                }
                if (cUSTOMER.getaPPROVEDISABLEBY() != null) {
                    existingCUSTOMER.setaPPROVEDISABLEBY(cUSTOMER.getaPPROVEDISABLEBY());
                }
                if (cUSTOMER.getaPPMARKEDENABLE() != null) {
                    existingCUSTOMER.setaPPMARKEDENABLE(cUSTOMER.getaPPMARKEDENABLE());
                }
                if (cUSTOMER.geteNABLEBY() != null) {
                    existingCUSTOMER.seteNABLEBY(cUSTOMER.geteNABLEBY());
                }
                if (cUSTOMER.getaPPROVEDENABLEBY() != null) {
                    existingCUSTOMER.setaPPROVEDENABLEBY(cUSTOMER.getaPPROVEDENABLEBY());
                }
                if (cUSTOMER.getmARKEDDEACTIVATE() != null) {
                    existingCUSTOMER.setmARKEDDEACTIVATE(cUSTOMER.getmARKEDDEACTIVATE());
                }
                if (cUSTOMER.getaPPFIRSTLOGIN() != null) {
                    existingCUSTOMER.setaPPFIRSTLOGIN(cUSTOMER.getaPPFIRSTLOGIN());
                }
                if (cUSTOMER.getaTMTRIALS() != null) {
                    existingCUSTOMER.setaTMTRIALS(cUSTOMER.getaTMTRIALS());
                }
                if (cUSTOMER.getsHORCUTS() != null) {
                    existingCUSTOMER.setsHORCUTS(cUSTOMER.getsHORCUTS());
                }
                if (cUSTOMER.getmARKEDACTIVATE() != null) {
                    existingCUSTOMER.setmARKEDACTIVATE(cUSTOMER.getmARKEDACTIVATE());
                }
                if (cUSTOMER.gettOWN() != null) {
                    existingCUSTOMER.settOWN(cUSTOMER.gettOWN());
                }
                if (cUSTOMER.getaPPROVEDDISABLEON() != null) {
                    existingCUSTOMER.setaPPROVEDDISABLEON(cUSTOMER.getaPPROVEDDISABLEON());
                }
                if (cUSTOMER.getdISABLEDON() != null) {
                    existingCUSTOMER.setdISABLEDON(cUSTOMER.getdISABLEDON());
                }
                if (cUSTOMER.getrESETAPPROVEON() != null) {
                    existingCUSTOMER.setrESETAPPROVEON(cUSTOMER.getrESETAPPROVEON());
                }
                if (cUSTOMER.getdELETEDBY() != null) {
                    existingCUSTOMER.setdELETEDBY(cUSTOMER.getdELETEDBY());
                }
                if (cUSTOMER.getqUESTIONSASKED() != null) {
                    existingCUSTOMER.setqUESTIONSASKED(cUSTOMER.getqUESTIONSASKED());
                }
                if (cUSTOMER.getqUESTIONSTRIALS() != null) {
                    existingCUSTOMER.setqUESTIONSTRIALS(cUSTOMER.getqUESTIONSTRIALS());
                }
                if (cUSTOMER.getqUESTIONSANSWERED() != null) {
                    existingCUSTOMER.setqUESTIONSANSWERED(cUSTOMER.getqUESTIONSANSWERED());
                }
                if (cUSTOMER.getvALIDOTP() != null) {
                    existingCUSTOMER.setvALIDOTP(cUSTOMER.getvALIDOTP());
                }
                if (cUSTOMER.getaCTIVATEDBY() != null) {
                    existingCUSTOMER.setaCTIVATEDBY(cUSTOMER.getaCTIVATEDBY());
                }
                if (cUSTOMER.getaCTIVATEDON() != null) {
                    existingCUSTOMER.setaCTIVATEDON(cUSTOMER.getaCTIVATEDON());
                }
                if (cUSTOMER.getbRANCHCODE() != null) {
                    existingCUSTOMER.setbRANCHCODE(cUSTOMER.getbRANCHCODE());
                }

                return existingCUSTOMER;
            })
            .map(cUSTOMERRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cUSTOMER.getId().toString())
        );
    }

    /**
     * {@code GET  /customers} : get all the cUSTOMERS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cUSTOMERS in body.
     */
    @GetMapping("")
    public List<CUSTOMER> getAllCUSTOMERS() {
        LOG.debug("REST request to get all CUSTOMERS");
        return cUSTOMERRepository.findAll();
    }

    /**
     * {@code GET  /customers/:id} : get the "id" cUSTOMER.
     *
     * @param id the id of the cUSTOMER to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cUSTOMER, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER> getCUSTOMER(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CUSTOMER : {}", id);
        Optional<CUSTOMER> cUSTOMER = cUSTOMERRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cUSTOMER);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" cUSTOMER.
     *
     * @param id the id of the cUSTOMER to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCUSTOMER(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CUSTOMER : {}", id);
        cUSTOMERRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
