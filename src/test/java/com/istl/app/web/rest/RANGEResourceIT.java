package com.istl.app.web.rest;

import static com.istl.app.domain.RangeAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.Range;
import com.istl.app.repository.RangeRepository;
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
 * Integration tests for the {@link RangeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RangeResourceIT {

    private static final Long DEFAULT_RANGEFROM = 1L;
    private static final Long UPDATED_RANGEFROM = 2L;

    private static final Long DEFAULT_RANGETO = 1L;
    private static final Long UPDATED_RANGETO = 2L;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_TXNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_TXNTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TXNCODE = "AAAAAAAAAA";
    private static final String UPDATED_TXNCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CHARGEID = 1L;
    private static final Long UPDATED_CHARGEID = 2L;

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ranges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RangeRepository rangeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRangeMockMvc;

    private Range range;

    private Range insertedRange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Range createEntity() {
        return new Range()
            .rangefrom(DEFAULT_RANGEFROM)
            .rangeto(DEFAULT_RANGETO)
            .amount(DEFAULT_AMOUNT)
            .txntype(DEFAULT_TXNTYPE)
            .txncode(DEFAULT_TXNCODE)
            .chargeid(DEFAULT_CHARGEID)
            .channel(DEFAULT_CHANNEL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Range createUpdatedEntity() {
        return new Range()
            .rangefrom(UPDATED_RANGEFROM)
            .rangeto(UPDATED_RANGETO)
            .amount(UPDATED_AMOUNT)
            .txntype(UPDATED_TXNTYPE)
            .txncode(UPDATED_TXNCODE)
            .chargeid(UPDATED_CHARGEID)
            .channel(UPDATED_CHANNEL);
    }

    @BeforeEach
    public void initTest() {
        range = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedRange != null) {
            rangeRepository.delete(insertedRange);
            insertedRange = null;
        }
    }

    @Test
    @Transactional
    void createRange() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Range
        var returnedRange = om.readValue(
            restRangeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(range)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Range.class
        );

        // Validate the Range in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRangeUpdatableFieldsEquals(returnedRange, getPersistedRange(returnedRange));

        insertedRange = returnedRange;
    }

    @Test
    @Transactional
    void createRangeWithExistingId() throws Exception {
        // Create the Range with an existing ID
        range.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(range)))
            .andExpect(status().isBadRequest());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRanges() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        // Get all the rangeList
        restRangeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(range.getId().intValue())))
            .andExpect(jsonPath("$.[*].rangefrom").value(hasItem(DEFAULT_RANGEFROM.intValue())))
            .andExpect(jsonPath("$.[*].rangeto").value(hasItem(DEFAULT_RANGETO.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].txntype").value(hasItem(DEFAULT_TXNTYPE)))
            .andExpect(jsonPath("$.[*].txncode").value(hasItem(DEFAULT_TXNCODE)))
            .andExpect(jsonPath("$.[*].chargeid").value(hasItem(DEFAULT_CHARGEID.intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)));
    }

    @Test
    @Transactional
    void getRange() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        // Get the range
        restRangeMockMvc
            .perform(get(ENTITY_API_URL_ID, range.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(range.getId().intValue()))
            .andExpect(jsonPath("$.rangefrom").value(DEFAULT_RANGEFROM.intValue()))
            .andExpect(jsonPath("$.rangeto").value(DEFAULT_RANGETO.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.txntype").value(DEFAULT_TXNTYPE))
            .andExpect(jsonPath("$.txncode").value(DEFAULT_TXNCODE))
            .andExpect(jsonPath("$.chargeid").value(DEFAULT_CHARGEID.intValue()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL));
    }

    @Test
    @Transactional
    void getNonExistingRange() throws Exception {
        // Get the range
        restRangeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRange() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the range
        Range updatedRange = rangeRepository.findById(range.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRange are not directly saved in db
        em.detach(updatedRange);
        updatedRange
            .rangefrom(UPDATED_RANGEFROM)
            .rangeto(UPDATED_RANGETO)
            .amount(UPDATED_AMOUNT)
            .txntype(UPDATED_TXNTYPE)
            .txncode(UPDATED_TXNCODE)
            .chargeid(UPDATED_CHARGEID)
            .channel(UPDATED_CHANNEL);

        restRangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRange.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRange))
            )
            .andExpect(status().isOk());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRangeToMatchAllProperties(updatedRange);
    }

    @Test
    @Transactional
    void putNonExistingRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(put(ENTITY_API_URL_ID, range.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(range)))
            .andExpect(status().isBadRequest());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(range))
            )
            .andExpect(status().isBadRequest());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(range)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRangeWithPatch() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the range using partial update
        Range partialUpdatedRange = new Range();
        partialUpdatedRange.setId(range.getId());

        partialUpdatedRange.rangefrom(UPDATED_RANGEFROM).amount(UPDATED_AMOUNT).txntype(UPDATED_TXNTYPE).channel(UPDATED_CHANNEL);

        restRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRange.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRange))
            )
            .andExpect(status().isOk());

        // Validate the Range in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRangeUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRange, range), getPersistedRange(range));
    }

    @Test
    @Transactional
    void fullUpdateRangeWithPatch() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the range using partial update
        Range partialUpdatedRange = new Range();
        partialUpdatedRange.setId(range.getId());

        partialUpdatedRange
            .rangefrom(UPDATED_RANGEFROM)
            .rangeto(UPDATED_RANGETO)
            .amount(UPDATED_AMOUNT)
            .txntype(UPDATED_TXNTYPE)
            .txncode(UPDATED_TXNCODE)
            .chargeid(UPDATED_CHARGEID)
            .channel(UPDATED_CHANNEL);

        restRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRange.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRange))
            )
            .andExpect(status().isOk());

        // Validate the Range in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRangeUpdatableFieldsEquals(partialUpdatedRange, getPersistedRange(partialUpdatedRange));
    }

    @Test
    @Transactional
    void patchNonExistingRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, range.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(range))
            )
            .andExpect(status().isBadRequest());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(range))
            )
            .andExpect(status().isBadRequest());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        range.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRangeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(range)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Range in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRange() throws Exception {
        // Initialize the database
        insertedRange = rangeRepository.saveAndFlush(range);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the range
        restRangeMockMvc
            .perform(delete(ENTITY_API_URL_ID, range.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rangeRepository.count();
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

    protected Range getPersistedRange(Range range) {
        return rangeRepository.findById(range.getId()).orElseThrow();
    }

    protected void assertPersistedRangeToMatchAllProperties(Range expectedRange) {
        assertRangeAllPropertiesEquals(expectedRange, getPersistedRange(expectedRange));
    }

    protected void assertPersistedRangeToMatchUpdatableProperties(Range expectedRange) {
        assertRangeAllUpdatablePropertiesEquals(expectedRange, getPersistedRange(expectedRange));
    }
}
