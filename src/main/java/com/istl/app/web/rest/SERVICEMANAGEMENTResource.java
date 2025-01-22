package com.istl.app.web.rest;

import com.istl.app.domain.ServiceManagement;
import com.istl.app.repository.ServiceManagementRepository;
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
 * REST controller for managing {@link com.istl.app.domain.ServiceManagement}.
 */
@RestController
@RequestMapping("/api/service-managements")
@Transactional
public class ServiceManagementResource {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceManagementResource.class);

    private static final String ENTITY_NAME = "serviceManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceManagementRepository serviceManagementRepository;

    public ServiceManagementResource(ServiceManagementRepository serviceManagementRepository) {
        this.serviceManagementRepository = serviceManagementRepository;
    }

    /**
     * {@code POST  /service-managements} : Create a new serviceManagement.
     *
     * @param serviceManagement the serviceManagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceManagement, or with status {@code 400 (Bad Request)} if the serviceManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ServiceManagement> createServiceManagement(@Valid @RequestBody ServiceManagement serviceManagement)
        throws URISyntaxException {
        LOG.debug("REST request to save ServiceManagement : {}", serviceManagement);
        if (serviceManagement.getId() != null) {
            throw new BadRequestAlertException("A new serviceManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        serviceManagement = serviceManagementRepository.save(serviceManagement);
        return ResponseEntity.created(new URI("/api/service-managements/" + serviceManagement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, serviceManagement.getId().toString()))
            .body(serviceManagement);
    }

    /**
     * {@code PUT  /service-managements/:id} : Updates an existing serviceManagement.
     *
     * @param id the id of the serviceManagement to save.
     * @param serviceManagement the serviceManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceManagement,
     * or with status {@code 400 (Bad Request)} if the serviceManagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceManagement> updateServiceManagement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ServiceManagement serviceManagement
    ) throws URISyntaxException {
        LOG.debug("REST request to update ServiceManagement : {}, {}", id, serviceManagement);
        if (serviceManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        serviceManagement = serviceManagementRepository.save(serviceManagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceManagement.getId().toString()))
            .body(serviceManagement);
    }

    /**
     * {@code PATCH  /service-managements/:id} : Partial updates given fields of an existing serviceManagement, field will ignore if it is null
     *
     * @param id the id of the serviceManagement to save.
     * @param serviceManagement the serviceManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceManagement,
     * or with status {@code 400 (Bad Request)} if the serviceManagement is not valid,
     * or with status {@code 404 (Not Found)} if the serviceManagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServiceManagement> partialUpdateServiceManagement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ServiceManagement serviceManagement
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ServiceManagement partially : {}, {}", id, serviceManagement);
        if (serviceManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceManagement> result = serviceManagementRepository
            .findById(serviceManagement.getId())
            .map(existingServiceManagement -> {
                if (serviceManagement.getProcessingcode() != null) {
                    existingServiceManagement.setProcessingcode(serviceManagement.getProcessingcode());
                }
                if (serviceManagement.getActive() != null) {
                    existingServiceManagement.setActive(serviceManagement.getActive());
                }
                if (serviceManagement.getCreatedby() != null) {
                    existingServiceManagement.setCreatedby(serviceManagement.getCreatedby());
                }
                if (serviceManagement.getDatecreated() != null) {
                    existingServiceManagement.setDatecreated(serviceManagement.getDatecreated());
                }
                if (serviceManagement.getApproved() != null) {
                    existingServiceManagement.setApproved(serviceManagement.getApproved());
                }
                if (serviceManagement.getApprovedby() != null) {
                    existingServiceManagement.setApprovedby(serviceManagement.getApprovedby());
                }
                if (serviceManagement.getApproveddate() != null) {
                    existingServiceManagement.setApproveddate(serviceManagement.getApproveddate());
                }
                if (serviceManagement.getAdaptortype() != null) {
                    existingServiceManagement.setAdaptortype(serviceManagement.getAdaptortype());
                }
                if (serviceManagement.getDestination() != null) {
                    existingServiceManagement.setDestination(serviceManagement.getDestination());
                }
                if (serviceManagement.getThirdpartyresponse() != null) {
                    existingServiceManagement.setThirdpartyresponse(serviceManagement.getThirdpartyresponse());
                }
                if (serviceManagement.getTelco() != null) {
                    existingServiceManagement.setTelco(serviceManagement.getTelco());
                }
                if (serviceManagement.getDescription() != null) {
                    existingServiceManagement.setDescription(serviceManagement.getDescription());
                }
                if (serviceManagement.getRemarks() != null) {
                    existingServiceManagement.setRemarks(serviceManagement.getRemarks());
                }
                if (serviceManagement.getSessionid() != null) {
                    existingServiceManagement.setSessionid(serviceManagement.getSessionid());
                }
                if (serviceManagement.getReworkby() != null) {
                    existingServiceManagement.setReworkby(serviceManagement.getReworkby());
                }

                return existingServiceManagement;
            })
            .map(serviceManagementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceManagement.getId().toString())
        );
    }

    /**
     * {@code GET  /service-managements} : get all the serviceManagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceManagements in body.
     */
    @GetMapping("")
    public List<ServiceManagement> getAllServiceManagements() {
        LOG.debug("REST request to get all ServiceManagements");
        return serviceManagementRepository.findAll();
    }

    /**
     * {@code GET  /service-managements/:id} : get the "id" serviceManagement.
     *
     * @param id the id of the serviceManagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceManagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceManagement> getServiceManagement(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ServiceManagement : {}", id);
        Optional<ServiceManagement> serviceManagement = serviceManagementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(serviceManagement);
    }

    /**
     * {@code DELETE  /service-managements/:id} : delete the "id" serviceManagement.
     *
     * @param id the id of the serviceManagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceManagement(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ServiceManagement : {}", id);
        serviceManagementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
