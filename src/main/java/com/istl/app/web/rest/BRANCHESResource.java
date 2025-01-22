package com.istl.app.web.rest;

import com.istl.app.domain.Branches;
import com.istl.app.repository.BranchesRepository;
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
 * REST controller for managing {@link com.istl.app.domain.Branches}.
 */
@RestController
@RequestMapping("/api/branches")
@Transactional
public class BranchesResource {

    private static final Logger LOG = LoggerFactory.getLogger(BranchesResource.class);

    private static final String ENTITY_NAME = "branches";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchesRepository branchesRepository;

    public BranchesResource(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    /**
     * {@code POST  /branches} : Create a new branches.
     *
     * @param branches the branches to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branches, or with status {@code 400 (Bad Request)} if the branches has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Branches> createBranches(@Valid @RequestBody Branches branches) throws URISyntaxException {
        LOG.debug("REST request to save Branches : {}", branches);
        if (branches.getId() != null) {
            throw new BadRequestAlertException("A new branches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        branches = branchesRepository.save(branches);
        return ResponseEntity.created(new URI("/api/branches/" + branches.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, branches.getId().toString()))
            .body(branches);
    }

    /**
     * {@code PUT  /branches/:id} : Updates an existing branches.
     *
     * @param id the id of the branches to save.
     * @param branches the branches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branches,
     * or with status {@code 400 (Bad Request)} if the branches is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Branches> updateBranches(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Branches branches
    ) throws URISyntaxException {
        LOG.debug("REST request to update Branches : {}, {}", id, branches);
        if (branches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branches.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        branches = branchesRepository.save(branches);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, branches.getId().toString()))
            .body(branches);
    }

    /**
     * {@code PATCH  /branches/:id} : Partial updates given fields of an existing branches, field will ignore if it is null
     *
     * @param id the id of the branches to save.
     * @param branches the branches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branches,
     * or with status {@code 400 (Bad Request)} if the branches is not valid,
     * or with status {@code 404 (Not Found)} if the branches is not found,
     * or with status {@code 500 (Internal Server Error)} if the branches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Branches> partialUpdateBranches(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Branches branches
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Branches partially : {}, {}", id, branches);
        if (branches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branches.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Branches> result = branchesRepository
            .findById(branches.getId())
            .map(existingBranches -> {
                if (branches.getBranchname() != null) {
                    existingBranches.setBranchname(branches.getBranchname());
                }
                if (branches.getBranchcode() != null) {
                    existingBranches.setBranchcode(branches.getBranchcode());
                }
                if (branches.getApproved() != null) {
                    existingBranches.setApproved(branches.getApproved());
                }
                if (branches.getEmail() != null) {
                    existingBranches.setEmail(branches.getEmail());
                }
                if (branches.getAddress() != null) {
                    existingBranches.setAddress(branches.getAddress());
                }
                if (branches.getPhone() != null) {
                    existingBranches.setPhone(branches.getPhone());
                }
                if (branches.getLocation() != null) {
                    existingBranches.setLocation(branches.getLocation());
                }
                if (branches.getContactperson() != null) {
                    existingBranches.setContactperson(branches.getContactperson());
                }
                if (branches.getRemarks() != null) {
                    existingBranches.setRemarks(branches.getRemarks());
                }
                if (branches.getCreatedby() != null) {
                    existingBranches.setCreatedby(branches.getCreatedby());
                }
                if (branches.getCreatedon() != null) {
                    existingBranches.setCreatedon(branches.getCreatedon());
                }
                if (branches.getApprovedby() != null) {
                    existingBranches.setApprovedby(branches.getApprovedby());
                }
                if (branches.getApprovedon() != null) {
                    existingBranches.setApprovedon(branches.getApprovedon());
                }
                if (branches.getCheckerremarks() != null) {
                    existingBranches.setCheckerremarks(branches.getCheckerremarks());
                }
                if (branches.getDeletedby() != null) {
                    existingBranches.setDeletedby(branches.getDeletedby());
                }
                if (branches.getDeletedon() != null) {
                    existingBranches.setDeletedon(branches.getDeletedon());
                }
                if (branches.getDeleteremarks() != null) {
                    existingBranches.setDeleteremarks(branches.getDeleteremarks());
                }
                if (branches.getDeleted() != null) {
                    existingBranches.setDeleted(branches.getDeleted());
                }
                if (branches.getDeclined() != null) {
                    existingBranches.setDeclined(branches.getDeclined());
                }
                if (branches.getDeclineddon() != null) {
                    existingBranches.setDeclineddon(branches.getDeclineddon());
                }
                if (branches.getDeclinedby() != null) {
                    existingBranches.setDeclinedby(branches.getDeclinedby());
                }
                if (branches.getSessionid() != null) {
                    existingBranches.setSessionid(branches.getSessionid());
                }
                if (branches.getReworked() != null) {
                    existingBranches.setReworked(branches.getReworked());
                }
                if (branches.getReworkedby() != null) {
                    existingBranches.setReworkedby(branches.getReworkedby());
                }
                if (branches.getReworkedon() != null) {
                    existingBranches.setReworkedon(branches.getReworkedon());
                }
                if (branches.getDistrict() != null) {
                    existingBranches.setDistrict(branches.getDistrict());
                }
                if (branches.getRegion() != null) {
                    existingBranches.setRegion(branches.getRegion());
                }
                if (branches.getRegionname() != null) {
                    existingBranches.setRegionname(branches.getRegionname());
                }
                if (branches.getReporting() != null) {
                    existingBranches.setReporting(branches.getReporting());
                }

                return existingBranches;
            })
            .map(branchesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, branches.getId().toString())
        );
    }

    /**
     * {@code GET  /branches} : get all the branches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branches in body.
     */
    @GetMapping("")
    public List<Branches> getAllBranches() {
        LOG.debug("REST request to get all Branches");
        return branchesRepository.findAll();
    }

    /**
     * {@code GET  /branches/:id} : get the "id" branches.
     *
     * @param id the id of the branches to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branches, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Branches> getBranches(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Branches : {}", id);
        Optional<Branches> branches = branchesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(branches);
    }

    /**
     * {@code DELETE  /branches/:id} : delete the "id" branches.
     *
     * @param id the id of the branches to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranches(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Branches : {}", id);
        branchesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
