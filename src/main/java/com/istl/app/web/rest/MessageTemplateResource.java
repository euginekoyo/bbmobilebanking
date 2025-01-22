package com.istl.app.web.rest;

import com.istl.app.domain.MessageTemplate;
import com.istl.app.repository.MessageTemplateRepository;
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
 * REST controller for managing {@link com.istl.app.domain.MessageTemplate}.
 */
@RestController
@RequestMapping("/api/message-templates")
@Transactional
public class MessageTemplateResource {

    private static final Logger LOG = LoggerFactory.getLogger(MessageTemplateResource.class);

    private static final String ENTITY_NAME = "messageTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageTemplateRepository messageTemplateRepository;

    public MessageTemplateResource(MessageTemplateRepository messageTemplateRepository) {
        this.messageTemplateRepository = messageTemplateRepository;
    }

    /**
     * {@code POST  /message-templates} : Create a new messageTemplate.
     *
     * @param messageTemplate the messageTemplate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageTemplate, or with status {@code 400 (Bad Request)} if the messageTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MessageTemplate> createMessageTemplate(@Valid @RequestBody MessageTemplate messageTemplate)
        throws URISyntaxException {
        LOG.debug("REST request to save MessageTemplate : {}", messageTemplate);
        if (messageTemplate.getId() != null) {
            throw new BadRequestAlertException("A new messageTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        messageTemplate = messageTemplateRepository.save(messageTemplate);
        return ResponseEntity.created(new URI("/api/message-templates/" + messageTemplate.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, messageTemplate.getId().toString()))
            .body(messageTemplate);
    }

    /**
     * {@code PUT  /message-templates/:id} : Updates an existing messageTemplate.
     *
     * @param id the id of the messageTemplate to save.
     * @param messageTemplate the messageTemplate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageTemplate,
     * or with status {@code 400 (Bad Request)} if the messageTemplate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageTemplate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageTemplate> updateMessageTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MessageTemplate messageTemplate
    ) throws URISyntaxException {
        LOG.debug("REST request to update MessageTemplate : {}, {}", id, messageTemplate);
        if (messageTemplate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageTemplate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        messageTemplate = messageTemplateRepository.save(messageTemplate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageTemplate.getId().toString()))
            .body(messageTemplate);
    }

    /**
     * {@code PATCH  /message-templates/:id} : Partial updates given fields of an existing messageTemplate, field will ignore if it is null
     *
     * @param id the id of the messageTemplate to save.
     * @param messageTemplate the messageTemplate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageTemplate,
     * or with status {@code 400 (Bad Request)} if the messageTemplate is not valid,
     * or with status {@code 404 (Not Found)} if the messageTemplate is not found,
     * or with status {@code 500 (Internal Server Error)} if the messageTemplate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MessageTemplate> partialUpdateMessageTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MessageTemplate messageTemplate
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MessageTemplate partially : {}, {}", id, messageTemplate);
        if (messageTemplate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageTemplate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MessageTemplate> result = messageTemplateRepository
            .findById(messageTemplate.getId())
            .map(existingMessageTemplate -> {
                if (messageTemplate.getMessagetype() != null) {
                    existingMessageTemplate.setMessagetype(messageTemplate.getMessagetype());
                }
                if (messageTemplate.getDescription() != null) {
                    existingMessageTemplate.setDescription(messageTemplate.getDescription());
                }
                if (messageTemplate.getMessageenglish() != null) {
                    existingMessageTemplate.setMessageenglish(messageTemplate.getMessageenglish());
                }
                if (messageTemplate.getMessagesomali() != null) {
                    existingMessageTemplate.setMessagesomali(messageTemplate.getMessagesomali());
                }
                if (messageTemplate.getCreatedon() != null) {
                    existingMessageTemplate.setCreatedon(messageTemplate.getCreatedon());
                }

                return existingMessageTemplate;
            })
            .map(messageTemplateRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageTemplate.getId().toString())
        );
    }

    /**
     * {@code GET  /message-templates} : get all the messageTemplates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageTemplates in body.
     */
    @GetMapping("")
    public List<MessageTemplate> getAllMessageTemplates() {
        LOG.debug("REST request to get all MessageTemplates");
        return messageTemplateRepository.findAll();
    }

    /**
     * {@code GET  /message-templates/:id} : get the "id" messageTemplate.
     *
     * @param id the id of the messageTemplate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageTemplate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageTemplate> getMessageTemplate(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MessageTemplate : {}", id);
        Optional<MessageTemplate> messageTemplate = messageTemplateRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(messageTemplate);
    }

    /**
     * {@code DELETE  /message-templates/:id} : delete the "id" messageTemplate.
     *
     * @param id the id of the messageTemplate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageTemplate(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MessageTemplate : {}", id);
        messageTemplateRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
