package com.istl.app.web.rest;

import static com.istl.app.domain.MessageTemplateAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.MessageTemplate;
import com.istl.app.repository.mobileapp.MessageTemplateRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MessageTemplateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MessageTemplateResourceIT {

    private static final String DEFAULT_MESSAGETYPE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGEENGLISH = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGEENGLISH = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGESOMALI = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGESOMALI = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/message-templates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMessageTemplateMockMvc;

    private MessageTemplate messageTemplate;

    private MessageTemplate insertedMessageTemplate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageTemplate createEntity() {
        return new MessageTemplate()
            .messagetype(DEFAULT_MESSAGETYPE)
            .description(DEFAULT_DESCRIPTION)
            .messageenglish(DEFAULT_MESSAGEENGLISH)
            .messagesomali(DEFAULT_MESSAGESOMALI)
            .createdon(DEFAULT_CREATEDON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageTemplate createUpdatedEntity() {
        return new MessageTemplate()
            .messagetype(UPDATED_MESSAGETYPE)
            .description(UPDATED_DESCRIPTION)
            .messageenglish(UPDATED_MESSAGEENGLISH)
            .messagesomali(UPDATED_MESSAGESOMALI)
            .createdon(UPDATED_CREATEDON);
    }

    @BeforeEach
    public void initTest() {
        messageTemplate = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMessageTemplate != null) {
            messageTemplateRepository.delete(insertedMessageTemplate);
            insertedMessageTemplate = null;
        }
    }

    @Test
    @Transactional
    void createMessageTemplate() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MessageTemplate
        var returnedMessageTemplate = om.readValue(
            restMessageTemplateMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messageTemplate)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MessageTemplate.class
        );

        // Validate the MessageTemplate in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMessageTemplateUpdatableFieldsEquals(returnedMessageTemplate, getPersistedMessageTemplate(returnedMessageTemplate));

        insertedMessageTemplate = returnedMessageTemplate;
    }

    @Test
    @Transactional
    void createMessageTemplateWithExistingId() throws Exception {
        // Create the MessageTemplate with an existing ID
        messageTemplate.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageTemplateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messageTemplate)))
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMessageTemplates() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get all the messageTemplateList
        restMessageTemplateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].messagetype").value(hasItem(DEFAULT_MESSAGETYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].messageenglish").value(hasItem(DEFAULT_MESSAGEENGLISH)))
            .andExpect(jsonPath("$.[*].messagesomali").value(hasItem(DEFAULT_MESSAGESOMALI)))
            .andExpect(jsonPath("$.[*].createdon").value(hasItem(DEFAULT_CREATEDON.toString())));
    }

    @Test
    @Transactional
    void getMessageTemplate() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get the messageTemplate
        restMessageTemplateMockMvc
            .perform(get(ENTITY_API_URL_ID, messageTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messageTemplate.getId().intValue()))
            .andExpect(jsonPath("$.messagetype").value(DEFAULT_MESSAGETYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.messageenglish").value(DEFAULT_MESSAGEENGLISH))
            .andExpect(jsonPath("$.messagesomali").value(DEFAULT_MESSAGESOMALI))
            .andExpect(jsonPath("$.createdon").value(DEFAULT_CREATEDON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMessageTemplate() throws Exception {
        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMessageTemplate() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messageTemplate
        MessageTemplate updatedMessageTemplate = messageTemplateRepository.findById(messageTemplate.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMessageTemplate are not directly saved in db
        em.detach(updatedMessageTemplate);
        updatedMessageTemplate
            .messagetype(UPDATED_MESSAGETYPE)
            .description(UPDATED_DESCRIPTION)
            .messageenglish(UPDATED_MESSAGEENGLISH)
            .messagesomali(UPDATED_MESSAGESOMALI)
            .createdon(UPDATED_CREATEDON);

        restMessageTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMessageTemplate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMessageTemplate))
            )
            .andExpect(status().isOk());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMessageTemplateToMatchAllProperties(updatedMessageTemplate);
    }

    @Test
    @Transactional
    void putNonExistingMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, messageTemplate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(messageTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(messageTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messageTemplate)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMessageTemplateWithPatch() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messageTemplate using partial update
        MessageTemplate partialUpdatedMessageTemplate = new MessageTemplate();
        partialUpdatedMessageTemplate.setId(messageTemplate.getId());

        partialUpdatedMessageTemplate
            .messagetype(UPDATED_MESSAGETYPE)
            .messageenglish(UPDATED_MESSAGEENGLISH)
            .messagesomali(UPDATED_MESSAGESOMALI)
            .createdon(UPDATED_CREATEDON);

        restMessageTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessageTemplate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMessageTemplate))
            )
            .andExpect(status().isOk());

        // Validate the MessageTemplate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMessageTemplateUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMessageTemplate, messageTemplate),
            getPersistedMessageTemplate(messageTemplate)
        );
    }

    @Test
    @Transactional
    void fullUpdateMessageTemplateWithPatch() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messageTemplate using partial update
        MessageTemplate partialUpdatedMessageTemplate = new MessageTemplate();
        partialUpdatedMessageTemplate.setId(messageTemplate.getId());

        partialUpdatedMessageTemplate
            .messagetype(UPDATED_MESSAGETYPE)
            .description(UPDATED_DESCRIPTION)
            .messageenglish(UPDATED_MESSAGEENGLISH)
            .messagesomali(UPDATED_MESSAGESOMALI)
            .createdon(UPDATED_CREATEDON);

        restMessageTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessageTemplate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMessageTemplate))
            )
            .andExpect(status().isOk());

        // Validate the MessageTemplate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMessageTemplateUpdatableFieldsEquals(
            partialUpdatedMessageTemplate,
            getPersistedMessageTemplate(partialUpdatedMessageTemplate)
        );
    }

    @Test
    @Transactional
    void patchNonExistingMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, messageTemplate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(messageTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(messageTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMessageTemplate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messageTemplate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(messageTemplate)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessageTemplate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMessageTemplate() throws Exception {
        // Initialize the database
        insertedMessageTemplate = messageTemplateRepository.saveAndFlush(messageTemplate);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the messageTemplate
        restMessageTemplateMockMvc
            .perform(delete(ENTITY_API_URL_ID, messageTemplate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return messageTemplateRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected MessageTemplate getPersistedMessageTemplate(MessageTemplate messageTemplate) {
        return messageTemplateRepository.findById(messageTemplate.getId()).orElseThrow();
    }

    protected void assertPersistedMessageTemplateToMatchAllProperties(MessageTemplate expectedMessageTemplate) {
        assertMessageTemplateAllPropertiesEquals(expectedMessageTemplate, getPersistedMessageTemplate(expectedMessageTemplate));
    }

    protected void assertPersistedMessageTemplateToMatchUpdatableProperties(MessageTemplate expectedMessageTemplate) {
        assertMessageTemplateAllUpdatablePropertiesEquals(expectedMessageTemplate, getPersistedMessageTemplate(expectedMessageTemplate));
    }
}
