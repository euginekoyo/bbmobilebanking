package com.istl.app.web.rest;

import com.istl.app.domain.Billers;
import com.istl.app.repository.BillersRepository;
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
 * REST controller for managing {@link com.istl.app.domain.Billers}.
 */
@RestController
@RequestMapping("/api/billers")
@Transactional
public class BillersResource {

    private static final Logger LOG = LoggerFactory.getLogger(BillersResource.class);

    private static final String ENTITY_NAME = "billers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillersRepository billersRepository;

    public BillersResource(BillersRepository billersRepository) {
        this.billersRepository = billersRepository;
    }

    /**
     * {@code POST  /billers} : Create a new billers.
     *
     * @param billers the billers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billers, or with status {@code 400 (Bad Request)} if the billers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Billers> createBillers(@Valid @RequestBody Billers billers) throws URISyntaxException {
        LOG.debug("REST request to save Billers : {}", billers);
        if (billers.getId() != null) {
            throw new BadRequestAlertException("A new billers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        billers = billersRepository.save(billers);
        return ResponseEntity.created(new URI("/api/billers/" + billers.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, billers.getId().toString()))
            .body(billers);
    }

    /**
     * {@code PUT  /billers/:id} : Updates an existing billers.
     *
     * @param id the id of the billers to save.
     * @param billers the billers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billers,
     * or with status {@code 400 (Bad Request)} if the billers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Billers> updateBillers(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Billers billers
    ) throws URISyntaxException {
        LOG.debug("REST request to update Billers : {}, {}", id, billers);
        if (billers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        billers = billersRepository.save(billers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billers.getId().toString()))
            .body(billers);
    }

    /**
     * {@code PATCH  /billers/:id} : Partial updates given fields of an existing billers, field will ignore if it is null
     *
     * @param id the id of the billers to save.
     * @param billers the billers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billers,
     * or with status {@code 400 (Bad Request)} if the billers is not valid,
     * or with status {@code 404 (Not Found)} if the billers is not found,
     * or with status {@code 500 (Internal Server Error)} if the billers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Billers> partialUpdateBillers(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Billers billers
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Billers partially : {}, {}", id, billers);
        if (billers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Billers> result = billersRepository
            .findById(billers.getId())
            .map(existingBillers -> {
                if (billers.getBillerid() != null) {
                    existingBillers.setBillerid(billers.getBillerid());
                }
                if (billers.getDescription() != null) {
                    existingBillers.setDescription(billers.getDescription());
                }
                if (billers.getBillercollectionaccount() != null) {
                    existingBillers.setBillercollectionaccount(billers.getBillercollectionaccount());
                }
                if (billers.getDatecreated() != null) {
                    existingBillers.setDatecreated(billers.getDatecreated());
                }
                if (billers.getCreatedby() != null) {
                    existingBillers.setCreatedby(billers.getCreatedby());
                }
                if (billers.getApproved() != null) {
                    existingBillers.setApproved(billers.getApproved());
                }
                if (billers.getApprovedby() != null) {
                    existingBillers.setApprovedby(billers.getApprovedby());
                }
                if (billers.getApproveddate() != null) {
                    existingBillers.setApproveddate(billers.getApproveddate());
                }
                if (billers.getChargableproductid() != null) {
                    existingBillers.setChargableproductid(billers.getChargableproductid());
                }
                if (billers.getNonchargableproductid() != null) {
                    existingBillers.setNonchargableproductid(billers.getNonchargableproductid());
                }
                if (billers.getUsdbillercollectionaccount() != null) {
                    existingBillers.setUsdbillercollectionaccount(billers.getUsdbillercollectionaccount());
                }
                if (billers.getEnableduplicatecheck() != null) {
                    existingBillers.setEnableduplicatecheck(billers.getEnableduplicatecheck());
                }
                if (billers.getRemarks() != null) {
                    existingBillers.setRemarks(billers.getRemarks());
                }
                if (billers.getSessionid() != null) {
                    existingBillers.setSessionid(billers.getSessionid());
                }
                if (billers.getReworkby() != null) {
                    existingBillers.setReworkby(billers.getReworkby());
                }
                if (billers.getStatus() != null) {
                    existingBillers.setStatus(billers.getStatus());
                }
                if (billers.getActive() != null) {
                    existingBillers.setActive(billers.getActive());
                }
                if (billers.getRework() != null) {
                    existingBillers.setRework(billers.getRework());
                }

                return existingBillers;
            })
            .map(billersRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billers.getId().toString())
        );
    }

    /**
     * {@code GET  /billers} : get all the billers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billers in body.
     */
    @GetMapping("")
    public List<Billers> getAllBillers() {
        LOG.debug("REST request to get all Billers");
        return billersRepository.findAll();
    }

    /**
     * {@code GET  /billers/:id} : get the "id" billers.
     *
     * @param id the id of the billers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Billers> getBillers(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Billers : {}", id);
        Optional<Billers> billers = billersRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(billers);
    }

    /**
     * {@code DELETE  /billers/:id} : delete the "id" billers.
     *
     * @param id the id of the billers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillers(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Billers : {}", id);
        billersRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
