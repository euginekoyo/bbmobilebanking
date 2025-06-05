package com.istl.app.web.rest;

import com.istl.app.domain.mobileapp.CustomerAccount;
import com.istl.app.repository.mobileapp.CustomerAccountRepository;
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
 * REST controller for managing {@link CustomerAccount}.
 */
@RestController
@RequestMapping("/api/customer-accounts")
@Transactional
public class CustomerAccountResource {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountResource.class);

    private static final String ENTITY_NAME = "customerAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountResource(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    /**
     * {@code POST  /customer-accounts} : Create a new customerAccount.
     *
     * @param customerAccount the customerAccount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerAccount, or with status {@code 400 (Bad Request)} if the customerAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomerAccount> createCustomerAccount(@Valid @RequestBody CustomerAccount customerAccount)
        throws URISyntaxException {
        LOG.debug("REST request to save CustomerAccount : {}", customerAccount);
        if (customerAccount.getId() != null) {
            throw new BadRequestAlertException("A new customerAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customerAccount = customerAccountRepository.save(customerAccount);
        return ResponseEntity.created(new URI("/api/customer-accounts/" + customerAccount.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customerAccount.getId().toString()))
            .body(customerAccount);
    }

    /**
     * {@code PUT  /customer-accounts/:id} : Updates an existing customerAccount.
     *
     * @param id the id of the customerAccount to save.
     * @param customerAccount the customerAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerAccount,
     * or with status {@code 400 (Bad Request)} if the customerAccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAccount> updateCustomerAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CustomerAccount customerAccount
    ) throws URISyntaxException {
        LOG.debug("REST request to update CustomerAccount : {}, {}", id, customerAccount);
        if (customerAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customerAccount = customerAccountRepository.save(customerAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerAccount.getId().toString()))
            .body(customerAccount);
    }

    /**
     * {@code PATCH  /customer-accounts/:id} : Partial updates given fields of an existing customerAccount, field will ignore if it is null
     *
     * @param id the id of the customerAccount to save.
     * @param customerAccount the customerAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerAccount,
     * or with status {@code 400 (Bad Request)} if the customerAccount is not valid,
     * or with status {@code 404 (Not Found)} if the customerAccount is not found,
     * or with status {@code 500 (Internal Server Error)} if the customerAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomerAccount> partialUpdateCustomerAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CustomerAccount customerAccount
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CustomerAccount partially : {}, {}", id, customerAccount);
        if (customerAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomerAccount> result = customerAccountRepository
            .findById(customerAccount.getId())
            .map(existingCustomerAccount -> {
                if (customerAccount.getCustomerid() != null) {
                    existingCustomerAccount.setCustomerid(customerAccount.getCustomerid());
                }
                if (customerAccount.getAccountnumber() != null) {
                    existingCustomerAccount.setAccountnumber(customerAccount.getAccountnumber());
                }
                if (customerAccount.getAccountclass() != null) {
                    existingCustomerAccount.setAccountclass(customerAccount.getAccountclass());
                }
                if (customerAccount.getCustomernumber() != null) {
                    existingCustomerAccount.setCustomernumber(customerAccount.getCustomernumber());
                }
                if (customerAccount.getCif() != null) {
                    existingCustomerAccount.setCif(customerAccount.getCif());
                }
                if (customerAccount.getTimelinked() != null) {
                    existingCustomerAccount.setTimelinked(customerAccount.getTimelinked());
                }
                if (customerAccount.getBlocked() != null) {
                    existingCustomerAccount.setBlocked(customerAccount.getBlocked());
                }
                if (customerAccount.getStopped() != null) {
                    existingCustomerAccount.setStopped(customerAccount.getStopped());
                }
                if (customerAccount.getDormant() != null) {
                    existingCustomerAccount.setDormant(customerAccount.getDormant());
                }

                return existingCustomerAccount;
            })
            .map(customerAccountRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerAccount.getId().toString())
        );
    }

    /**
     * {@code GET  /customer-accounts} : get all the customerAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerAccounts in body.
     */
    @GetMapping("")
    public List<CustomerAccount> getAllCustomerAccounts() {
        LOG.debug("REST request to get all CustomerAccounts");
        return customerAccountRepository.findAll();
    }

    /**
     * {@code GET  /customer-accounts/:id} : get the "id" customerAccount.
     *
     * @param id the id of the customerAccount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerAccount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAccount> getCustomerAccount(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CustomerAccount : {}", id);
        Optional<CustomerAccount> customerAccount = customerAccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customerAccount);
    }

    /**
     * {@code DELETE  /customer-accounts/:id} : delete the "id" customerAccount.
     *
     * @param id the id of the customerAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerAccount(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CustomerAccount : {}", id);
        customerAccountRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
