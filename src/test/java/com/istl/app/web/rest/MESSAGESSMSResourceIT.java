package com.istl.app.web.rest;

import static com.istl.app.domain.MessagesSmsAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.MessagesSms;
import com.istl.app.repository.MessagesSmsRepository;
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
 * Integration tests for the {@link MessagesSmsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MessagesSmsResourceIT {

    private static final Instant DEFAULT_TRNDATETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRNDATETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTIONNO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTIONNO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_TRIALS = 1L;
    private static final Long UPDATED_TRIALS = 2L;

    private static final Long DEFAULT_PRIORITY = 1L;
    private static final Long UPDATED_PRIORITY = 2L;

    private static final String DEFAULT_RESPONSECODE = "AAAA";
    private static final String UPDATED_RESPONSECODE = "BBBB";

    private static final String DEFAULT_RESPONSEMSG = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSEMSG = "BBBBBBBBBB";

    private static final Long DEFAULT_SENT = 1L;
    private static final Long UPDATED_SENT = 2L;

    private static final Long DEFAULT_DELIVERED = 1L;
    private static final Long UPDATED_DELIVERED = 2L;

    private static final String DEFAULT_TXNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_TXNTYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_ERROREXCEPTION = 1L;
    private static final Long UPDATED_ERROREXCEPTION = 2L;

    private static final Instant DEFAULT_DATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DATESENT = "AAAAAAA";
    private static final String UPDATED_DATESENT = "BBBBBBB";

    private static final String DEFAULT_RTPSREQTIME = "AAAAAAAAAA";
    private static final String UPDATED_RTPSREQTIME = "BBBBBBBBBB";

    private static final String DEFAULT_FXGENERATED = "AAAAAAAAAA";
    private static final String UPDATED_FXGENERATED = "BBBBBBBBBB";

    private static final Long DEFAULT_TAXPROCESSED = 1L;
    private static final Long UPDATED_TAXPROCESSED = 2L;

    private static final String DEFAULT_BATCHNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BATCHNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BATCHNUMBERTAX = "AAAAAAAAAA";
    private static final String UPDATED_BATCHNUMBERTAX = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSETIME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSETIME = "BBBBBBBBBB";

    private static final String DEFAULT_PDUSEQID = "AAAAAAAAAA";
    private static final String UPDATED_PDUSEQID = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_RESENDBY = "AAAAAAAAAA";
    private static final String UPDATED_RESENDBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/messages-sms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MessagesSmsRepository messagesSmsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMessagesSmsMockMvc;

    private MessagesSms messagesSms;

    private MessagesSms insertedMessagesSms;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessagesSms createEntity() {
        return new MessagesSms()
            .trndatetime(DEFAULT_TRNDATETIME)
            .phonenumber(DEFAULT_PHONENUMBER)
            .transactionno(DEFAULT_TRANSACTIONNO)
            .accountnumber(DEFAULT_ACCOUNTNUMBER)
            .message(DEFAULT_MESSAGE)
            .channel(DEFAULT_CHANNEL)
            .trials(DEFAULT_TRIALS)
            .priority(DEFAULT_PRIORITY)
            .responsecode(DEFAULT_RESPONSECODE)
            .responsemsg(DEFAULT_RESPONSEMSG)
            .sent(DEFAULT_SENT)
            .delivered(DEFAULT_DELIVERED)
            .txntype(DEFAULT_TXNTYPE)
            .errorexception(DEFAULT_ERROREXCEPTION)
            .datecreated(DEFAULT_DATECREATED)
            .datesent(DEFAULT_DATESENT)
            .rtpsreqtime(DEFAULT_RTPSREQTIME)
            .fxgenerated(DEFAULT_FXGENERATED)
            .taxprocessed(DEFAULT_TAXPROCESSED)
            .batchnumber(DEFAULT_BATCHNUMBER)
            .batchnumbertax(DEFAULT_BATCHNUMBERTAX)
            .responsetime(DEFAULT_RESPONSETIME)
            .pduseqid(DEFAULT_PDUSEQID)
            .remarks(DEFAULT_REMARKS)
            .resendby(DEFAULT_RESENDBY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessagesSms createUpdatedEntity() {
        return new MessagesSms()
            .trndatetime(UPDATED_TRNDATETIME)
            .phonenumber(UPDATED_PHONENUMBER)
            .transactionno(UPDATED_TRANSACTIONNO)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .message(UPDATED_MESSAGE)
            .channel(UPDATED_CHANNEL)
            .trials(UPDATED_TRIALS)
            .priority(UPDATED_PRIORITY)
            .responsecode(UPDATED_RESPONSECODE)
            .responsemsg(UPDATED_RESPONSEMSG)
            .sent(UPDATED_SENT)
            .delivered(UPDATED_DELIVERED)
            .txntype(UPDATED_TXNTYPE)
            .errorexception(UPDATED_ERROREXCEPTION)
            .datecreated(UPDATED_DATECREATED)
            .datesent(UPDATED_DATESENT)
            .rtpsreqtime(UPDATED_RTPSREQTIME)
            .fxgenerated(UPDATED_FXGENERATED)
            .taxprocessed(UPDATED_TAXPROCESSED)
            .batchnumber(UPDATED_BATCHNUMBER)
            .batchnumbertax(UPDATED_BATCHNUMBERTAX)
            .responsetime(UPDATED_RESPONSETIME)
            .pduseqid(UPDATED_PDUSEQID)
            .remarks(UPDATED_REMARKS)
            .resendby(UPDATED_RESENDBY);
    }

    @BeforeEach
    public void initTest() {
        messagesSms = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMessagesSms != null) {
            messagesSmsRepository.delete(insertedMessagesSms);
            insertedMessagesSms = null;
        }
    }

    @Test
    @Transactional
    void createMessagesSms() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MessagesSms
        var returnedMessagesSms = om.readValue(
            restMessagesSmsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messagesSms)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MessagesSms.class
        );

        // Validate the MessagesSms in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMessagesSmsUpdatableFieldsEquals(returnedMessagesSms, getPersistedMessagesSms(returnedMessagesSms));

        insertedMessagesSms = returnedMessagesSms;
    }

    @Test
    @Transactional
    void createMessagesSmsWithExistingId() throws Exception {
        // Create the MessagesSms with an existing ID
        messagesSms.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessagesSmsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messagesSms)))
            .andExpect(status().isBadRequest());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMessagesSms() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        // Get all the messagesSmsList
        restMessagesSmsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messagesSms.getId().intValue())))
            .andExpect(jsonPath("$.[*].trndatetime").value(hasItem(DEFAULT_TRNDATETIME.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].transactionno").value(hasItem(DEFAULT_TRANSACTIONNO)))
            .andExpect(jsonPath("$.[*].accountnumber").value(hasItem(DEFAULT_ACCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].trials").value(hasItem(DEFAULT_TRIALS.intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.intValue())))
            .andExpect(jsonPath("$.[*].responsecode").value(hasItem(DEFAULT_RESPONSECODE)))
            .andExpect(jsonPath("$.[*].responsemsg").value(hasItem(DEFAULT_RESPONSEMSG)))
            .andExpect(jsonPath("$.[*].sent").value(hasItem(DEFAULT_SENT.intValue())))
            .andExpect(jsonPath("$.[*].delivered").value(hasItem(DEFAULT_DELIVERED.intValue())))
            .andExpect(jsonPath("$.[*].txntype").value(hasItem(DEFAULT_TXNTYPE)))
            .andExpect(jsonPath("$.[*].errorexception").value(hasItem(DEFAULT_ERROREXCEPTION.intValue())))
            .andExpect(jsonPath("$.[*].datecreated").value(hasItem(DEFAULT_DATECREATED.toString())))
            .andExpect(jsonPath("$.[*].datesent").value(hasItem(DEFAULT_DATESENT)))
            .andExpect(jsonPath("$.[*].rtpsreqtime").value(hasItem(DEFAULT_RTPSREQTIME)))
            .andExpect(jsonPath("$.[*].fxgenerated").value(hasItem(DEFAULT_FXGENERATED)))
            .andExpect(jsonPath("$.[*].taxprocessed").value(hasItem(DEFAULT_TAXPROCESSED.intValue())))
            .andExpect(jsonPath("$.[*].batchnumber").value(hasItem(DEFAULT_BATCHNUMBER)))
            .andExpect(jsonPath("$.[*].batchnumbertax").value(hasItem(DEFAULT_BATCHNUMBERTAX)))
            .andExpect(jsonPath("$.[*].responsetime").value(hasItem(DEFAULT_RESPONSETIME)))
            .andExpect(jsonPath("$.[*].pduseqid").value(hasItem(DEFAULT_PDUSEQID)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].resendby").value(hasItem(DEFAULT_RESENDBY)));
    }

    @Test
    @Transactional
    void getMessagesSms() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        // Get the messagesSms
        restMessagesSmsMockMvc
            .perform(get(ENTITY_API_URL_ID, messagesSms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messagesSms.getId().intValue()))
            .andExpect(jsonPath("$.trndatetime").value(DEFAULT_TRNDATETIME.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER))
            .andExpect(jsonPath("$.transactionno").value(DEFAULT_TRANSACTIONNO))
            .andExpect(jsonPath("$.accountnumber").value(DEFAULT_ACCOUNTNUMBER))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.trials").value(DEFAULT_TRIALS.intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.intValue()))
            .andExpect(jsonPath("$.responsecode").value(DEFAULT_RESPONSECODE))
            .andExpect(jsonPath("$.responsemsg").value(DEFAULT_RESPONSEMSG))
            .andExpect(jsonPath("$.sent").value(DEFAULT_SENT.intValue()))
            .andExpect(jsonPath("$.delivered").value(DEFAULT_DELIVERED.intValue()))
            .andExpect(jsonPath("$.txntype").value(DEFAULT_TXNTYPE))
            .andExpect(jsonPath("$.errorexception").value(DEFAULT_ERROREXCEPTION.intValue()))
            .andExpect(jsonPath("$.datecreated").value(DEFAULT_DATECREATED.toString()))
            .andExpect(jsonPath("$.datesent").value(DEFAULT_DATESENT))
            .andExpect(jsonPath("$.rtpsreqtime").value(DEFAULT_RTPSREQTIME))
            .andExpect(jsonPath("$.fxgenerated").value(DEFAULT_FXGENERATED))
            .andExpect(jsonPath("$.taxprocessed").value(DEFAULT_TAXPROCESSED.intValue()))
            .andExpect(jsonPath("$.batchnumber").value(DEFAULT_BATCHNUMBER))
            .andExpect(jsonPath("$.batchnumbertax").value(DEFAULT_BATCHNUMBERTAX))
            .andExpect(jsonPath("$.responsetime").value(DEFAULT_RESPONSETIME))
            .andExpect(jsonPath("$.pduseqid").value(DEFAULT_PDUSEQID))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.resendby").value(DEFAULT_RESENDBY));
    }

    @Test
    @Transactional
    void getNonExistingMessagesSms() throws Exception {
        // Get the messagesSms
        restMessagesSmsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMessagesSms() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messagesSms
        MessagesSms updatedMessagesSms = messagesSmsRepository.findById(messagesSms.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMessagesSms are not directly saved in db
        em.detach(updatedMessagesSms);
        updatedMessagesSms
            .trndatetime(UPDATED_TRNDATETIME)
            .phonenumber(UPDATED_PHONENUMBER)
            .transactionno(UPDATED_TRANSACTIONNO)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .message(UPDATED_MESSAGE)
            .channel(UPDATED_CHANNEL)
            .trials(UPDATED_TRIALS)
            .priority(UPDATED_PRIORITY)
            .responsecode(UPDATED_RESPONSECODE)
            .responsemsg(UPDATED_RESPONSEMSG)
            .sent(UPDATED_SENT)
            .delivered(UPDATED_DELIVERED)
            .txntype(UPDATED_TXNTYPE)
            .errorexception(UPDATED_ERROREXCEPTION)
            .datecreated(UPDATED_DATECREATED)
            .datesent(UPDATED_DATESENT)
            .rtpsreqtime(UPDATED_RTPSREQTIME)
            .fxgenerated(UPDATED_FXGENERATED)
            .taxprocessed(UPDATED_TAXPROCESSED)
            .batchnumber(UPDATED_BATCHNUMBER)
            .batchnumbertax(UPDATED_BATCHNUMBERTAX)
            .responsetime(UPDATED_RESPONSETIME)
            .pduseqid(UPDATED_PDUSEQID)
            .remarks(UPDATED_REMARKS)
            .resendby(UPDATED_RESENDBY);

        restMessagesSmsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMessagesSms.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMessagesSms))
            )
            .andExpect(status().isOk());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMessagesSmsToMatchAllProperties(updatedMessagesSms);
    }

    @Test
    @Transactional
    void putNonExistingMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, messagesSms.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(messagesSms))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(messagesSms))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(messagesSms)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMessagesSmsWithPatch() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messagesSms using partial update
        MessagesSms partialUpdatedMessagesSms = new MessagesSms();
        partialUpdatedMessagesSms.setId(messagesSms.getId());

        partialUpdatedMessagesSms
            .transactionno(UPDATED_TRANSACTIONNO)
            .message(UPDATED_MESSAGE)
            .trials(UPDATED_TRIALS)
            .priority(UPDATED_PRIORITY)
            .sent(UPDATED_SENT)
            .delivered(UPDATED_DELIVERED)
            .txntype(UPDATED_TXNTYPE)
            .datecreated(UPDATED_DATECREATED)
            .rtpsreqtime(UPDATED_RTPSREQTIME)
            .taxprocessed(UPDATED_TAXPROCESSED)
            .resendby(UPDATED_RESENDBY);

        restMessagesSmsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessagesSms.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMessagesSms))
            )
            .andExpect(status().isOk());

        // Validate the MessagesSms in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMessagesSmsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMessagesSms, messagesSms),
            getPersistedMessagesSms(messagesSms)
        );
    }

    @Test
    @Transactional
    void fullUpdateMessagesSmsWithPatch() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the messagesSms using partial update
        MessagesSms partialUpdatedMessagesSms = new MessagesSms();
        partialUpdatedMessagesSms.setId(messagesSms.getId());

        partialUpdatedMessagesSms
            .trndatetime(UPDATED_TRNDATETIME)
            .phonenumber(UPDATED_PHONENUMBER)
            .transactionno(UPDATED_TRANSACTIONNO)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .message(UPDATED_MESSAGE)
            .channel(UPDATED_CHANNEL)
            .trials(UPDATED_TRIALS)
            .priority(UPDATED_PRIORITY)
            .responsecode(UPDATED_RESPONSECODE)
            .responsemsg(UPDATED_RESPONSEMSG)
            .sent(UPDATED_SENT)
            .delivered(UPDATED_DELIVERED)
            .txntype(UPDATED_TXNTYPE)
            .errorexception(UPDATED_ERROREXCEPTION)
            .datecreated(UPDATED_DATECREATED)
            .datesent(UPDATED_DATESENT)
            .rtpsreqtime(UPDATED_RTPSREQTIME)
            .fxgenerated(UPDATED_FXGENERATED)
            .taxprocessed(UPDATED_TAXPROCESSED)
            .batchnumber(UPDATED_BATCHNUMBER)
            .batchnumbertax(UPDATED_BATCHNUMBERTAX)
            .responsetime(UPDATED_RESPONSETIME)
            .pduseqid(UPDATED_PDUSEQID)
            .remarks(UPDATED_REMARKS)
            .resendby(UPDATED_RESENDBY);

        restMessagesSmsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessagesSms.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMessagesSms))
            )
            .andExpect(status().isOk());

        // Validate the MessagesSms in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMessagesSmsUpdatableFieldsEquals(partialUpdatedMessagesSms, getPersistedMessagesSms(partialUpdatedMessagesSms));
    }

    @Test
    @Transactional
    void patchNonExistingMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, messagesSms.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(messagesSms))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(messagesSms))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMessagesSms() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        messagesSms.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessagesSmsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(messagesSms)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessagesSms in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMessagesSms() throws Exception {
        // Initialize the database
        insertedMessagesSms = messagesSmsRepository.saveAndFlush(messagesSms);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the messagesSms
        restMessagesSmsMockMvc
            .perform(delete(ENTITY_API_URL_ID, messagesSms.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return messagesSmsRepository.count();
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

    protected MessagesSms getPersistedMessagesSms(MessagesSms messagesSms) {
        return messagesSmsRepository.findById(messagesSms.getId()).orElseThrow();
    }

    protected void assertPersistedMessagesSmsToMatchAllProperties(MessagesSms expectedMessagesSms) {
        assertMessagesSmsAllPropertiesEquals(expectedMessagesSms, getPersistedMessagesSms(expectedMessagesSms));
    }

    protected void assertPersistedMessagesSmsToMatchUpdatableProperties(MessagesSms expectedMessagesSms) {
        assertMessagesSmsAllUpdatablePropertiesEquals(expectedMessagesSms, getPersistedMessagesSms(expectedMessagesSms));
    }
}
