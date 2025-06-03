package com.istl.app.web.rest;

import static com.istl.app.domain.PinResetHistoryAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.PinResetHistory;
import com.istl.app.repository.mobileapp.PinResetHistoryRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link PinResetHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PinResetHistoryResourceIT {

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PINBLOCKEDON = "AAAAAAAAAA";
    private static final String UPDATED_PINBLOCKEDON = "BBBBBBBBBB";

    private static final String DEFAULT_PINBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_PINBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETBY = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETON = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETON = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETAPPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETAPPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETAPPROVEDON = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETAPPROVEDON = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHCODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pin-reset-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PinResetHistoryRepository pinResetHistoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPinResetHistoryMockMvc;

    private PinResetHistory pinResetHistory;

    private PinResetHistory insertedPinResetHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PinResetHistory createEntity() {
        return new PinResetHistory()
            .phonenumber(DEFAULT_PHONENUMBER)
            .customername(DEFAULT_CUSTOMERNAME)
            .pinblockedon(DEFAULT_PINBLOCKEDON)
            .pinblockremarks(DEFAULT_PINBLOCKREMARKS)
            .pinresetby(DEFAULT_PINRESETBY)
            .pinreseton(DEFAULT_PINRESETON)
            .pinresetapprovedby(DEFAULT_PINRESETAPPROVEDBY)
            .pinresetapprovedon(DEFAULT_PINRESETAPPROVEDON)
            .pinresetremarks(DEFAULT_PINRESETREMARKS)
            .branchcode(DEFAULT_BRANCHCODE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PinResetHistory createUpdatedEntity() {
        return new PinResetHistory()
            .phonenumber(UPDATED_PHONENUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .pinblockedon(UPDATED_PINBLOCKEDON)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .pinresetby(UPDATED_PINRESETBY)
            .pinreseton(UPDATED_PINRESETON)
            .pinresetapprovedby(UPDATED_PINRESETAPPROVEDBY)
            .pinresetapprovedon(UPDATED_PINRESETAPPROVEDON)
            .pinresetremarks(UPDATED_PINRESETREMARKS)
            .branchcode(UPDATED_BRANCHCODE);
    }

    @BeforeEach
    public void initTest() {
        pinResetHistory = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPinResetHistory != null) {
            pinResetHistoryRepository.delete(insertedPinResetHistory);
            insertedPinResetHistory = null;
        }
    }

    @Test
    @Transactional
    void createPinResetHistory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PinResetHistory
        var returnedPinResetHistory = om.readValue(
            restPinResetHistoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pinResetHistory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PinResetHistory.class
        );

        // Validate the PinResetHistory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPinResetHistoryUpdatableFieldsEquals(returnedPinResetHistory, getPersistedPinResetHistory(returnedPinResetHistory));

        insertedPinResetHistory = returnedPinResetHistory;
    }

    @Test
    @Transactional
    void createPinResetHistoryWithExistingId() throws Exception {
        // Create the PinResetHistory with an existing ID
        pinResetHistory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPinResetHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pinResetHistory)))
            .andExpect(status().isBadRequest());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPinResetHistories() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        // Get all the pinResetHistoryList
        restPinResetHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pinResetHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].pinblockedon").value(hasItem(DEFAULT_PINBLOCKEDON)))
            .andExpect(jsonPath("$.[*].pinblockremarks").value(hasItem(DEFAULT_PINBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].pinresetby").value(hasItem(DEFAULT_PINRESETBY)))
            .andExpect(jsonPath("$.[*].pinreseton").value(hasItem(DEFAULT_PINRESETON)))
            .andExpect(jsonPath("$.[*].pinresetapprovedby").value(hasItem(DEFAULT_PINRESETAPPROVEDBY)))
            .andExpect(jsonPath("$.[*].pinresetapprovedon").value(hasItem(DEFAULT_PINRESETAPPROVEDON)))
            .andExpect(jsonPath("$.[*].pinresetremarks").value(hasItem(DEFAULT_PINRESETREMARKS)))
            .andExpect(jsonPath("$.[*].branchcode").value(hasItem(DEFAULT_BRANCHCODE)));
    }

    @Test
    @Transactional
    void getPinResetHistory() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        // Get the pinResetHistory
        restPinResetHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, pinResetHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pinResetHistory.getId().intValue()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.pinblockedon").value(DEFAULT_PINBLOCKEDON))
            .andExpect(jsonPath("$.pinblockremarks").value(DEFAULT_PINBLOCKREMARKS))
            .andExpect(jsonPath("$.pinresetby").value(DEFAULT_PINRESETBY))
            .andExpect(jsonPath("$.pinreseton").value(DEFAULT_PINRESETON))
            .andExpect(jsonPath("$.pinresetapprovedby").value(DEFAULT_PINRESETAPPROVEDBY))
            .andExpect(jsonPath("$.pinresetapprovedon").value(DEFAULT_PINRESETAPPROVEDON))
            .andExpect(jsonPath("$.pinresetremarks").value(DEFAULT_PINRESETREMARKS))
            .andExpect(jsonPath("$.branchcode").value(DEFAULT_BRANCHCODE));
    }

    @Test
    @Transactional
    void getNonExistingPinResetHistory() throws Exception {
        // Get the pinResetHistory
        restPinResetHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPinResetHistory() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pinResetHistory
        PinResetHistory updatedPinResetHistory = pinResetHistoryRepository.findById(pinResetHistory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPinResetHistory are not directly saved in db
        em.detach(updatedPinResetHistory);
        updatedPinResetHistory
            .phonenumber(UPDATED_PHONENUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .pinblockedon(UPDATED_PINBLOCKEDON)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .pinresetby(UPDATED_PINRESETBY)
            .pinreseton(UPDATED_PINRESETON)
            .pinresetapprovedby(UPDATED_PINRESETAPPROVEDBY)
            .pinresetapprovedon(UPDATED_PINRESETAPPROVEDON)
            .pinresetremarks(UPDATED_PINRESETREMARKS)
            .branchcode(UPDATED_BRANCHCODE);

        restPinResetHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPinResetHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPinResetHistory))
            )
            .andExpect(status().isOk());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPinResetHistoryToMatchAllProperties(updatedPinResetHistory);
    }

    @Test
    @Transactional
    void putNonExistingPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pinResetHistory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pinResetHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pinResetHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pinResetHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePinResetHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pinResetHistory using partial update
        PinResetHistory partialUpdatedPinResetHistory = new PinResetHistory();
        partialUpdatedPinResetHistory.setId(pinResetHistory.getId());

        partialUpdatedPinResetHistory
            .pinblockedon(UPDATED_PINBLOCKEDON)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .pinresetapprovedby(UPDATED_PINRESETAPPROVEDBY)
            .pinresetapprovedon(UPDATED_PINRESETAPPROVEDON)
            .pinresetremarks(UPDATED_PINRESETREMARKS);

        restPinResetHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPinResetHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPinResetHistory))
            )
            .andExpect(status().isOk());

        // Validate the PinResetHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPinResetHistoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPinResetHistory, pinResetHistory),
            getPersistedPinResetHistory(pinResetHistory)
        );
    }

    @Test
    @Transactional
    void fullUpdatePinResetHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pinResetHistory using partial update
        PinResetHistory partialUpdatedPinResetHistory = new PinResetHistory();
        partialUpdatedPinResetHistory.setId(pinResetHistory.getId());

        partialUpdatedPinResetHistory
            .phonenumber(UPDATED_PHONENUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .pinblockedon(UPDATED_PINBLOCKEDON)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .pinresetby(UPDATED_PINRESETBY)
            .pinreseton(UPDATED_PINRESETON)
            .pinresetapprovedby(UPDATED_PINRESETAPPROVEDBY)
            .pinresetapprovedon(UPDATED_PINRESETAPPROVEDON)
            .pinresetremarks(UPDATED_PINRESETREMARKS)
            .branchcode(UPDATED_BRANCHCODE);

        restPinResetHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPinResetHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPinResetHistory))
            )
            .andExpect(status().isOk());

        // Validate the PinResetHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPinResetHistoryUpdatableFieldsEquals(
            partialUpdatedPinResetHistory,
            getPersistedPinResetHistory(partialUpdatedPinResetHistory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pinResetHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pinResetHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pinResetHistory))
            )
            .andExpect(status().isBadRequest());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPinResetHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pinResetHistory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPinResetHistoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(pinResetHistory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PinResetHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePinResetHistory() throws Exception {
        // Initialize the database
        insertedPinResetHistory = pinResetHistoryRepository.saveAndFlush(pinResetHistory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the pinResetHistory
        restPinResetHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, pinResetHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return pinResetHistoryRepository.count();
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

    protected PinResetHistory getPersistedPinResetHistory(PinResetHistory pinResetHistory) {
        return pinResetHistoryRepository.findById(pinResetHistory.getId()).orElseThrow();
    }

    protected void assertPersistedPinResetHistoryToMatchAllProperties(PinResetHistory expectedPinResetHistory) {
        assertPinResetHistoryAllPropertiesEquals(expectedPinResetHistory, getPersistedPinResetHistory(expectedPinResetHistory));
    }

    protected void assertPersistedPinResetHistoryToMatchUpdatableProperties(PinResetHistory expectedPinResetHistory) {
        assertPinResetHistoryAllUpdatablePropertiesEquals(expectedPinResetHistory, getPersistedPinResetHistory(expectedPinResetHistory));
    }
}
