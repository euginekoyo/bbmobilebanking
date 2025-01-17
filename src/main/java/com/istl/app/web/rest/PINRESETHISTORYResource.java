package com.istl.app.web.rest;

import com.istl.app.domain.PINRESETHISTORY;
import com.istl.app.repository.PINRESETHISTORYRepository;
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
 * REST controller for managing {@link com.istl.app.domain.PINRESETHISTORY}.
 */
@RestController
@RequestMapping("/api/pinresethistories")
@Transactional
public class PINRESETHISTORYResource {

    private static final Logger LOG = LoggerFactory.getLogger(PINRESETHISTORYResource.class);

    private static final String ENTITY_NAME = "pINRESETHISTORY";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PINRESETHISTORYRepository pINRESETHISTORYRepository;

    public PINRESETHISTORYResource(PINRESETHISTORYRepository pINRESETHISTORYRepository) {
        this.pINRESETHISTORYRepository = pINRESETHISTORYRepository;
    }

    /**
     * {@code POST  /pinresethistories} : Create a new pINRESETHISTORY.
     *
     * @param pINRESETHISTORY the pINRESETHISTORY to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pINRESETHISTORY, or with status {@code 400 (Bad Request)} if the pINRESETHISTORY has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PINRESETHISTORY> createPINRESETHISTORY(@Valid @RequestBody PINRESETHISTORY pINRESETHISTORY)
        throws URISyntaxException {
        LOG.debug("REST request to save PINRESETHISTORY : {}", pINRESETHISTORY);
        if (pINRESETHISTORY.getId() != null) {
            throw new BadRequestAlertException("A new pINRESETHISTORY cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pINRESETHISTORY = pINRESETHISTORYRepository.save(pINRESETHISTORY);
        return ResponseEntity.created(new URI("/api/pinresethistories/" + pINRESETHISTORY.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, pINRESETHISTORY.getId().toString()))
            .body(pINRESETHISTORY);
    }

    /**
     * {@code PUT  /pinresethistories/:id} : Updates an existing pINRESETHISTORY.
     *
     * @param id the id of the pINRESETHISTORY to save.
     * @param pINRESETHISTORY the pINRESETHISTORY to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pINRESETHISTORY,
     * or with status {@code 400 (Bad Request)} if the pINRESETHISTORY is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pINRESETHISTORY couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PINRESETHISTORY> updatePINRESETHISTORY(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PINRESETHISTORY pINRESETHISTORY
    ) throws URISyntaxException {
        LOG.debug("REST request to update PINRESETHISTORY : {}, {}", id, pINRESETHISTORY);
        if (pINRESETHISTORY.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pINRESETHISTORY.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pINRESETHISTORYRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        pINRESETHISTORY = pINRESETHISTORYRepository.save(pINRESETHISTORY);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pINRESETHISTORY.getId().toString()))
            .body(pINRESETHISTORY);
    }

    /**
     * {@code PATCH  /pinresethistories/:id} : Partial updates given fields of an existing pINRESETHISTORY, field will ignore if it is null
     *
     * @param id the id of the pINRESETHISTORY to save.
     * @param pINRESETHISTORY the pINRESETHISTORY to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pINRESETHISTORY,
     * or with status {@code 400 (Bad Request)} if the pINRESETHISTORY is not valid,
     * or with status {@code 404 (Not Found)} if the pINRESETHISTORY is not found,
     * or with status {@code 500 (Internal Server Error)} if the pINRESETHISTORY couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PINRESETHISTORY> partialUpdatePINRESETHISTORY(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PINRESETHISTORY pINRESETHISTORY
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update PINRESETHISTORY partially : {}, {}", id, pINRESETHISTORY);
        if (pINRESETHISTORY.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pINRESETHISTORY.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pINRESETHISTORYRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PINRESETHISTORY> result = pINRESETHISTORYRepository
            .findById(pINRESETHISTORY.getId())
            .map(existingPINRESETHISTORY -> {
                if (pINRESETHISTORY.getpHONENUMBER() != null) {
                    existingPINRESETHISTORY.setpHONENUMBER(pINRESETHISTORY.getpHONENUMBER());
                }
                if (pINRESETHISTORY.getcUSTOMERNAME() != null) {
                    existingPINRESETHISTORY.setcUSTOMERNAME(pINRESETHISTORY.getcUSTOMERNAME());
                }
                if (pINRESETHISTORY.getpINBLOCKEDON() != null) {
                    existingPINRESETHISTORY.setpINBLOCKEDON(pINRESETHISTORY.getpINBLOCKEDON());
                }
                if (pINRESETHISTORY.getpINBLOCKREMARKS() != null) {
                    existingPINRESETHISTORY.setpINBLOCKREMARKS(pINRESETHISTORY.getpINBLOCKREMARKS());
                }
                if (pINRESETHISTORY.getpINRESETBY() != null) {
                    existingPINRESETHISTORY.setpINRESETBY(pINRESETHISTORY.getpINRESETBY());
                }
                if (pINRESETHISTORY.getpINRESETON() != null) {
                    existingPINRESETHISTORY.setpINRESETON(pINRESETHISTORY.getpINRESETON());
                }
                if (pINRESETHISTORY.getpINRESETAPPROVEDBY() != null) {
                    existingPINRESETHISTORY.setpINRESETAPPROVEDBY(pINRESETHISTORY.getpINRESETAPPROVEDBY());
                }
                if (pINRESETHISTORY.getpINRESETAPPROVEDON() != null) {
                    existingPINRESETHISTORY.setpINRESETAPPROVEDON(pINRESETHISTORY.getpINRESETAPPROVEDON());
                }
                if (pINRESETHISTORY.getpINRESETREMARKS() != null) {
                    existingPINRESETHISTORY.setpINRESETREMARKS(pINRESETHISTORY.getpINRESETREMARKS());
                }
                if (pINRESETHISTORY.getbRANCHCODE() != null) {
                    existingPINRESETHISTORY.setbRANCHCODE(pINRESETHISTORY.getbRANCHCODE());
                }

                return existingPINRESETHISTORY;
            })
            .map(pINRESETHISTORYRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pINRESETHISTORY.getId().toString())
        );
    }

    /**
     * {@code GET  /pinresethistories} : get all the pINRESETHISTORIES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pINRESETHISTORIES in body.
     */
    @GetMapping("")
    public List<PINRESETHISTORY> getAllPINRESETHISTORIES() {
        LOG.debug("REST request to get all PINRESETHISTORIES");
        return pINRESETHISTORYRepository.findAll();
    }

    /**
     * {@code GET  /pinresethistories/:id} : get the "id" pINRESETHISTORY.
     *
     * @param id the id of the pINRESETHISTORY to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pINRESETHISTORY, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PINRESETHISTORY> getPINRESETHISTORY(@PathVariable("id") Long id) {
        LOG.debug("REST request to get PINRESETHISTORY : {}", id);
        Optional<PINRESETHISTORY> pINRESETHISTORY = pINRESETHISTORYRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pINRESETHISTORY);
    }

    /**
     * {@code DELETE  /pinresethistories/:id} : delete the "id" pINRESETHISTORY.
     *
     * @param id the id of the pINRESETHISTORY to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePINRESETHISTORY(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete PINRESETHISTORY : {}", id);
        pINRESETHISTORYRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
