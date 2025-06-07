package com.istl.app.web.rest;

import com.istl.app.domain.mobileapp.Customer;
import com.istl.app.repository.mobileapp.CustomerRepository;
import com.istl.app.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
 * REST controller for managing {@link Customer}.
 */
@RestController
@RequestMapping("/api/customers")
@Transactional
public class CustomerResource {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerRepository customerRepository;

    public CustomerResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        LOG.debug("REST request to save Customer : {}", customer);
        if (customer.getId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customer = customerRepository.save(customer);
        return ResponseEntity.created(new URI("/api/customers/" + customer.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customer.getId().toString()))
            .body(customer);
    }

    /**
     * {@code PUT  /customers/:id} : Updates an existing customer.
     *
     * @param id the id of the customer to save.
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     * or with status {@code 400 (Bad Request)} if the customer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Customer customer
    ) throws URISyntaxException {
        LOG.debug("REST request to update Customer : {}, {}", id, customer);
        if (customer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customer = customerRepository.save(customer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customer.getId().toString()))
            .body(customer);
    }

    /**
     * {@code PATCH  /customers/:id} : Partial updates given fields of an existing customer, field will ignore if it is null
     *
     * @param id the id of the customer to save.
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     * or with status {@code 400 (Bad Request)} if the customer is not valid,
     * or with status {@code 404 (Not Found)} if the customer is not found,
     * or with status {@code 500 (Internal Server Error)} if the customer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Customer> partialUpdateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Customer customer
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Customer partially : {}, {}", id, customer);
        if (customer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Customer> result = customerRepository
            .findById(customer.getId())
            .map(existingCustomer -> {
                // Existing fields update logic
                if (customer.getCustomername() != null) {
                    existingCustomer.setCustomername(customer.getCustomername());
                }
                if (customer.getPhonenumber() != null) {
                    existingCustomer.setPhonenumber(customer.getPhonenumber());
                }
                if (customer.getCardnumber() != null) {
                    existingCustomer.setCardnumber(customer.getCardnumber());
                }
                if (customer.getAccountnumber() != null) {
                    existingCustomer.setAccountnumber(customer.getAccountnumber());
                }
                if (customer.getLang() != null) {
                    existingCustomer.setLang(customer.getLang());
                }
                if (customer.getPin() != null) {
                    existingCustomer.setPin(customer.getPin());
                }
                if (customer.getFirstlogin() != null) {
                    existingCustomer.setFirstlogin(customer.getFirstlogin());
                }
                if (customer.getActive() != null) {
                    existingCustomer.setActive(customer.getActive());
                }
                if (customer.getRegistered() != null) {
                    existingCustomer.setRegistered(customer.getRegistered());
                }
                if (customer.getCstdelete() != null) {
                    existingCustomer.setCstdelete(customer.getCstdelete());
                }
                if (customer.getRegdate() != null) {
                    existingCustomer.setRegdate(customer.getRegdate());
                }
                if (customer.getAlertenabled() != null) {
                    existingCustomer.setAlertenabled(customer.getAlertenabled());
                }
                if (customer.getRemark() != null) {
                    existingCustomer.setRemark(customer.getRemark());
                }
                if (customer.getImsi() != null) {
                    existingCustomer.setImsi(customer.getImsi());
                }
                if (customer.getPartiallyregistered() != null) {
                    existingCustomer.setPartiallyregistered(customer.getPartiallyregistered());
                }
                if (customer.getPartialdate() != null) {
                    existingCustomer.setPartialdate(customer.getPartialdate());
                }
                if (customer.getRegisterdate() != null) {
                    existingCustomer.setRegisterdate(customer.getRegisterdate());
                }
                if (customer.getApproved() != null) {
                    existingCustomer.setApproved(customer.getApproved());
                }
                if (customer.getApprovedby() != null) {
                    existingCustomer.setApprovedby(customer.getApprovedby());
                }
                if (customer.getApproveddate() != null) {
                    existingCustomer.setApproveddate(customer.getApproveddate());
                }
                if (customer.getDeclined() != null) {
                    existingCustomer.setDeclined(customer.getDeclined());
                }
                if (customer.getDeclinedby() != null) {
                    existingCustomer.setDeclinedby(customer.getDeclinedby());
                }
                if (customer.getDeclineddate() != null) {
                    existingCustomer.setDeclineddate(customer.getDeclineddate());
                }
                if (customer.getCheckerremarks() != null) {
                    existingCustomer.setCheckerremarks(customer.getCheckerremarks());
                }
                if (customer.getPostaladdress() != null) {
                    existingCustomer.setPostaladdress(customer.getPostaladdress());
                }
                if (customer.getResidence() != null) {
                    existingCustomer.setResidence(customer.getResidence());
                }
                if (customer.getDob() != null) {
                    existingCustomer.setDob(customer.getDob());
                }
                if (customer.getCreatedby() != null) {
                    existingCustomer.setCreatedby(customer.getCreatedby());
                }
                if (customer.getEmailaddress() != null) {
                    existingCustomer.setEmailaddress(customer.getEmailaddress());
                }
                if (customer.getIdentificationid() != null) {
                    existingCustomer.setIdentificationid(customer.getIdentificationid());
                }
                if (customer.getAddaccount() != null) {
                    existingCustomer.setAddaccount(customer.getAddaccount());
                }
                if (customer.getAclinkinginstitution() != null) {
                    existingCustomer.setAclinkinginstitution(customer.getAclinkinginstitution());
                }
                if (customer.getDeactivated() != null) {
                    existingCustomer.setDeactivated(customer.getDeactivated());
                }
                if (customer.getDeactivatedby() != null) {
                    existingCustomer.setDeactivatedby(customer.getDeactivatedby());
                }
                if (customer.getDeactivatedon() != null) {
                    existingCustomer.setDeactivatedon(customer.getDeactivatedon());
                }
                if (customer.getPhonenochanged() != null) {
                    existingCustomer.setPhonenochanged(customer.getPhonenochanged());
                }
                if (customer.getPhonenochangedby() != null) {
                    existingCustomer.setPhonenochangedby(customer.getPhonenochangedby());
                }
                if (customer.getPhonenochangedon() != null) {
                    existingCustomer.setPhonenochangedon(customer.getPhonenochangedon());
                }
                if (customer.getOriginalphoneno() != null) {
                    existingCustomer.setOriginalphoneno(customer.getOriginalphoneno());
                }
                if (customer.getNewphoneno() != null) {
                    existingCustomer.setNewphoneno(customer.getNewphoneno());
                }
                if (customer.getReset() != null) {
                    existingCustomer.setReset(customer.getReset());
                }
                if (customer.getResetinginstitution() != null) {
                    existingCustomer.setResetinginstitution(customer.getResetinginstitution());
                }
                if (customer.getPinresetremark() != null) {
                    existingCustomer.setPinresetremark(customer.getPinresetremark());
                }
                if (customer.getResetby() != null) {
                    existingCustomer.setResetby(customer.getResetby());
                }
                if (customer.getReseton() != null) {
                    existingCustomer.setReseton(customer.getReseton());
                }
                if (customer.getUnblockinginstitution() != null) {
                    existingCustomer.setUnblockinginstitution(customer.getUnblockinginstitution());
                }
                if (customer.getPinblock() != null) {
                    existingCustomer.setPinblock(customer.getPinblock());
                }
                if (customer.getPinblockby() != null) {
                    existingCustomer.setPinblockby(customer.getPinblockby());
                }
                if (customer.getPinblockremarks() != null) {
                    existingCustomer.setPinblockremarks(customer.getPinblockremarks());
                }
                if (customer.getBlockinginstitution() != null) {
                    existingCustomer.setBlockinginstitution(customer.getBlockinginstitution());
                }
                if (customer.getPinblockon() != null) {
                    existingCustomer.setPinblockon(customer.getPinblockon());
                }
                if (customer.getApprovedon() != null) {
                    existingCustomer.setApprovedon(customer.getApprovedon());
                }
                if (customer.getPinunblockby() != null) {
                    existingCustomer.setPinunblockby(customer.getPinunblockby());
                }
                if (customer.getLoggedin() != null) {
                    existingCustomer.setLoggedin(customer.getLoggedin());
                }
                if (customer.getTrials() != null) {
                    existingCustomer.setTrials(customer.getTrials());
                }
                if (customer.getIdtype() != null) {
                    existingCustomer.setIdtype(customer.getIdtype());
                }
                if (customer.getIdnumber() != null) {
                    existingCustomer.setIdnumber(customer.getIdnumber());
                }
                if (customer.getGender() != null) {
                    existingCustomer.setGender(customer.getGender());
                }
                if (customer.getCif() != null) {
                    existingCustomer.setCif(customer.getCif());
                }
                if (customer.getDateofbirth() != null) {
                    existingCustomer.setDateofbirth(customer.getDateofbirth());
                }
                if (customer.getRemarks() != null) {
                    existingCustomer.setRemarks(customer.getRemarks());
                }
                if (customer.getResetimsi() != null) {
                    existingCustomer.setResetimsi(customer.getResetimsi());
                }
                if (customer.getImsiresetby() != null) {
                    existingCustomer.setImsiresetby(customer.getImsiresetby());
                }
                if (customer.getFirstname() != null) {
                    existingCustomer.setFirstname(customer.getFirstname());
                }
                if (customer.getSecondname() != null) {
                    existingCustomer.setSecondname(customer.getSecondname());
                }
                if (customer.getLastname() != null) {
                    existingCustomer.setLastname(customer.getLastname());
                }
                if (customer.getPinblocktime() != null) {
                    existingCustomer.setPinblocktime(customer.getPinblocktime());
                }
                if (customer.getCustomerstatus() != null) {
                    existingCustomer.setCustomerstatus(customer.getCustomerstatus());
                }
                if (customer.getUsername() != null) {
                    existingCustomer.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null) {
                    existingCustomer.setPassword(customer.getPassword());
                }
                if (customer.getDeviceid() != null) {
                    existingCustomer.setDeviceid(customer.getDeviceid());
                }
                if (customer.getChannel() != null) {
                    existingCustomer.setChannel(customer.getChannel());
                }
                if (customer.getPassreset() != null) {
                    existingCustomer.setPassreset(customer.getPassreset());
                }
                if (customer.getPassresetby() != null) {
                    existingCustomer.setPassresetby(customer.getPassresetby());
                }
                if (customer.getPassreseton() != null) {
                    existingCustomer.setPassreseton(customer.getPassreseton());
                }
                if (customer.getPassblock() != null) {
                    existingCustomer.setPassblock(customer.getPassblock());
                }
                if (customer.getPassblockby() != null) {
                    existingCustomer.setPassblockby(customer.getPassblockby());
                }
                if (customer.getPassblockon() != null) {
                    existingCustomer.setPassblockon(customer.getPassblockon());
                }
                if (customer.getPinmarkblock() != null) {
                    existingCustomer.setPinmarkblock(customer.getPinmarkblock());
                }
                if (customer.getPassmarkblock() != null) {
                    existingCustomer.setPassmarkblock(customer.getPassmarkblock());
                }
                if (customer.getPassresetremarks() != null) {
                    existingCustomer.setPassresetremarks(customer.getPassresetremarks());
                }
                if (customer.getPassblockremarks() != null) {
                    existingCustomer.setPassblockremarks(customer.getPassblockremarks());
                }
                if (customer.getPassunblockby() != null) {
                    existingCustomer.setPassunblockby(customer.getPassunblockby());
                }
                if (customer.getPasstrials() != null) {
                    existingCustomer.setPasstrials(customer.getPasstrials());
                }
                if (customer.getAppactive() != null) {
                    existingCustomer.setAppactive(customer.getAppactive());
                }
                if (customer.getLastlogin() != null) {
                    existingCustomer.setLastlogin(customer.getLastlogin());
                }
                if (customer.getAppmarkeddisable() != null) {
                    existingCustomer.setAppmarkeddisable(customer.getAppmarkeddisable());
                }
                if (customer.getDisableby() != null) {
                    existingCustomer.setDisableby(customer.getDisableby());
                }
                if (customer.getApprovedisableby() != null) {
                    existingCustomer.setApprovedisableby(customer.getApprovedisableby());
                }
                if (customer.getAppmarkedenable() != null) {
                    existingCustomer.setAppmarkedenable(customer.getAppmarkedenable());
                }
                if (customer.getEnableby() != null) {
                    existingCustomer.setEnableby(customer.getEnableby());
                }
                if (customer.getApprovedenableby() != null) {
                    existingCustomer.setApprovedenableby(customer.getApprovedenableby());
                }
                if (customer.getMarkeddeactivate() != null) {
                    existingCustomer.setMarkeddeactivate(customer.getMarkeddeactivate());
                }
                if (customer.getAppfirstlogin() != null) {
                    existingCustomer.setAppfirstlogin(customer.getAppfirstlogin());
                }
                if (customer.getAtmtrials() != null) {
                    existingCustomer.setAtmtrials(customer.getAtmtrials());
                }
                if (customer.getShorcuts() != null) {
                    existingCustomer.setShorcuts(customer.getShorcuts());
                }
                if (customer.getMarkedactivate() != null) {
                    existingCustomer.setMarkedactivate(customer.getMarkedactivate());
                }
                if (customer.getTown() != null) {
                    existingCustomer.setTown(customer.getTown());
                }
                if (customer.getApproveddisableon() != null) {
                    existingCustomer.setApproveddisableon(customer.getApproveddisableon());
                }
                if (customer.getDisabledon() != null) {
                    existingCustomer.setDisabledon(customer.getDisabledon());
                }
                if (customer.getResetapproveon() != null) {
                    existingCustomer.setResetapproveon(customer.getResetapproveon());
                }
                if (customer.getDeletedby() != null) {
                    existingCustomer.setDeletedby(customer.getDeletedby());
                }
                if (customer.getQuestionsasked() != null) {
                    existingCustomer.setQuestionsasked(customer.getQuestionsasked());
                }
                if (customer.getQuestionstrials() != null) {
                    existingCustomer.setQuestionstrials(customer.getQuestionstrials());
                }
                if (customer.getQuestionsanswered() != null) {
                    existingCustomer.setQuestionsanswered(customer.getQuestionsanswered());
                }
                if (customer.getValidotp() != null) {
                    existingCustomer.setValidotp(customer.getValidotp());
                }
                if (customer.getActivatedby() != null) {
                    existingCustomer.setActivatedby(customer.getActivatedby());
                }
                if (customer.getActivatedon() != null) {
                    existingCustomer.setActivatedon(customer.getActivatedon());
                }
                if (customer.getBranchcode() != null) {
                    existingCustomer.setBranchcode(customer.getBranchcode());
                }

                // Custom logic for PIN reset
                if (Boolean.TRUE.equals(customer.getPinresetremark())) { // Check if reset flag is true
                    existingCustomer.setPinresetremark(null); // Reset PIN by setting it to null (or generate a new PIN)
                    existingCustomer.setResetby(customer.getResetby() != null ? customer.getResetby() : "system"); // Set reset initiator
                    if (customer.getPinresetremark() != null) {
                        existingCustomer.setPinresetremark(customer.getPinresetremark()); // Update remark for PIN reset
                    }
                }

                // Custom logic for approving PIN reset
                if (customer.getApproveReset() != null && customer.getApproveReset() == 1.0) { // Assuming approveReset is Double
                    existingCustomer.setApproved(1.0); // Mark as approved (assuming approved is Double)
                    existingCustomer.setApprovedby(customer.getApprovedby() != null ? customer.getApprovedby() : "system");
                    // Simplify the time conversion
                    existingCustomer.setApprovedon(
                        LocalDateTime.now(ZoneId.of("Africa/Nairobi")).atZone(ZoneId.of("Africa/Nairobi")).toInstant()
                    );
                    existingCustomer.setResetapproveon(
                        LocalDateTime.now(ZoneId.of("Africa/Nairobi")).atZone(ZoneId.of("Africa/Nairobi")).toInstant()
                    );
                    if (customer.getPinresetremark() != null) {
                        // Assuming pinresetpin is intended to be a remark or needs conversion
                        existingCustomer.setPinresetremark(customer.getPinresetremark().toString()); // Convert to String if needed
                    }
                }

                return existingCustomer;
            })
            .map(customerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customer.getId().toString())
        );
    }

    /**
     * {@code GET  /customers} : get all the customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("")
    public List<Customer> getAllCustomers() {
        LOG.debug("REST request to get all Customers");
        return customerRepository.findAll();
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Customer : {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customer);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Customer : {}", id);
        customerRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
