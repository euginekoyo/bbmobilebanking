package com.istl.app.web.rest;

import com.istl.app.domain.MessagesSms;
import com.istl.app.repository.MessagesSmsRepository;
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
 * REST controller for managing {@link com.istl.app.domain.MessagesSms}.
 */
@RestController
@RequestMapping("/api/messages-sms")
@Transactional
public class MessagesSmsResource {

    private static final Logger LOG = LoggerFactory.getLogger(MessagesSmsResource.class);

    private static final String ENTITY_NAME = "messagesSms";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessagesSmsRepository messagesSmsRepository;

    public MessagesSmsResource(MessagesSmsRepository messagesSmsRepository) {
        this.messagesSmsRepository = messagesSmsRepository;
    }

    /**
     * {@code POST  /messages-sms} : Create a new messagesSms.
     *
     * @param messagesSms the messagesSms to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messagesSms, or with status {@code 400 (Bad Request)} if the messagesSms has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MessagesSms> createMessagesSms(@Valid @RequestBody MessagesSms messagesSms) throws URISyntaxException {
        LOG.debug("REST request to save MessagesSms : {}", messagesSms);
        if (messagesSms.getId() != null) {
            throw new BadRequestAlertException("A new messagesSms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        messagesSms = messagesSmsRepository.save(messagesSms);
        return ResponseEntity.created(new URI("/api/messages-sms/" + messagesSms.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, messagesSms.getId().toString()))
            .body(messagesSms);
    }

    /**
     * {@code PUT  /messages-sms/:id} : Updates an existing messagesSms.
     *
     * @param id the id of the messagesSms to save.
     * @param messagesSms the messagesSms to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messagesSms,
     * or with status {@code 400 (Bad Request)} if the messagesSms is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messagesSms couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessagesSms> updateMessagesSms(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MessagesSms messagesSms
    ) throws URISyntaxException {
        LOG.debug("REST request to update MessagesSms : {}, {}", id, messagesSms);
        if (messagesSms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messagesSms.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messagesSmsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        messagesSms = messagesSmsRepository.save(messagesSms);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messagesSms.getId().toString()))
            .body(messagesSms);
    }

    /**
     * {@code PATCH  /messages-sms/:id} : Partial updates given fields of an existing messagesSms, field will ignore if it is null
     *
     * @param id the id of the messagesSms to save.
     * @param messagesSms the messagesSms to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messagesSms,
     * or with status {@code 400 (Bad Request)} if the messagesSms is not valid,
     * or with status {@code 404 (Not Found)} if the messagesSms is not found,
     * or with status {@code 500 (Internal Server Error)} if the messagesSms couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MessagesSms> partialUpdateMessagesSms(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MessagesSms messagesSms
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MessagesSms partially : {}, {}", id, messagesSms);
        if (messagesSms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messagesSms.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messagesSmsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MessagesSms> result = messagesSmsRepository
            .findById(messagesSms.getId())
            .map(existingMessagesSms -> {
                if (messagesSms.getTrndatetime() != null) {
                    existingMessagesSms.setTrndatetime(messagesSms.getTrndatetime());
                }
                if (messagesSms.getPhonenumber() != null) {
                    existingMessagesSms.setPhonenumber(messagesSms.getPhonenumber());
                }
                if (messagesSms.getTransactionno() != null) {
                    existingMessagesSms.setTransactionno(messagesSms.getTransactionno());
                }
                if (messagesSms.getAccountnumber() != null) {
                    existingMessagesSms.setAccountnumber(messagesSms.getAccountnumber());
                }
                if (messagesSms.getMessage() != null) {
                    existingMessagesSms.setMessage(messagesSms.getMessage());
                }
                if (messagesSms.getChannel() != null) {
                    existingMessagesSms.setChannel(messagesSms.getChannel());
                }
                if (messagesSms.getTrials() != null) {
                    existingMessagesSms.setTrials(messagesSms.getTrials());
                }
                if (messagesSms.getPriority() != null) {
                    existingMessagesSms.setPriority(messagesSms.getPriority());
                }
                if (messagesSms.getResponsecode() != null) {
                    existingMessagesSms.setResponsecode(messagesSms.getResponsecode());
                }
                if (messagesSms.getResponsemsg() != null) {
                    existingMessagesSms.setResponsemsg(messagesSms.getResponsemsg());
                }
                if (messagesSms.getSent() != null) {
                    existingMessagesSms.setSent(messagesSms.getSent());
                }
                if (messagesSms.getDelivered() != null) {
                    existingMessagesSms.setDelivered(messagesSms.getDelivered());
                }
                if (messagesSms.getTxntype() != null) {
                    existingMessagesSms.setTxntype(messagesSms.getTxntype());
                }
                if (messagesSms.getErrorexception() != null) {
                    existingMessagesSms.setErrorexception(messagesSms.getErrorexception());
                }
                if (messagesSms.getDatecreated() != null) {
                    existingMessagesSms.setDatecreated(messagesSms.getDatecreated());
                }
                if (messagesSms.getDatesent() != null) {
                    existingMessagesSms.setDatesent(messagesSms.getDatesent());
                }
                if (messagesSms.getRtpsreqtime() != null) {
                    existingMessagesSms.setRtpsreqtime(messagesSms.getRtpsreqtime());
                }
                if (messagesSms.getFxgenerated() != null) {
                    existingMessagesSms.setFxgenerated(messagesSms.getFxgenerated());
                }
                if (messagesSms.getTaxprocessed() != null) {
                    existingMessagesSms.setTaxprocessed(messagesSms.getTaxprocessed());
                }
                if (messagesSms.getBatchnumber() != null) {
                    existingMessagesSms.setBatchnumber(messagesSms.getBatchnumber());
                }
                if (messagesSms.getBatchnumbertax() != null) {
                    existingMessagesSms.setBatchnumbertax(messagesSms.getBatchnumbertax());
                }
                if (messagesSms.getResponsetime() != null) {
                    existingMessagesSms.setResponsetime(messagesSms.getResponsetime());
                }
                if (messagesSms.getPduseqid() != null) {
                    existingMessagesSms.setPduseqid(messagesSms.getPduseqid());
                }
                if (messagesSms.getRemarks() != null) {
                    existingMessagesSms.setRemarks(messagesSms.getRemarks());
                }
                if (messagesSms.getResendby() != null) {
                    existingMessagesSms.setResendby(messagesSms.getResendby());
                }

                return existingMessagesSms;
            })
            .map(messagesSmsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messagesSms.getId().toString())
        );
    }

    /**
     * {@code GET  /messages-sms} : get all the messagesSms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messagesSms in body.
     */
    @GetMapping("")
    public List<MessagesSms> getAllMessagesSms() {
        LOG.debug("REST request to get all MessagesSms");
        return messagesSmsRepository.findAll();
    }

    /**
     * {@code GET  /messages-sms/:id} : get the "id" messagesSms.
     *
     * @param id the id of the messagesSms to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messagesSms, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessagesSms> getMessagesSms(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MessagesSms : {}", id);
        Optional<MessagesSms> messagesSms = messagesSmsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(messagesSms);
    }

    /**
     * {@code DELETE  /messages-sms/:id} : delete the "id" messagesSms.
     *
     * @param id the id of the messagesSms to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessagesSms(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MessagesSms : {}", id);
        messagesSmsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
