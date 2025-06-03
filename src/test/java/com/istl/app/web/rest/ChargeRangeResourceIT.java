package com.istl.app.web.rest;

import static com.istl.app.domain.ChargeRangeAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.ChargeRange;
import com.istl.app.repository.mobileapp.ChargeRangeRepository;
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
 * Integration tests for the {@link ChargeRangeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChargeRangeResourceIT {

    private static final String DEFAULT_BILLERID = "AAAAAAAAAA";
    private static final String UPDATED_BILLERID = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESSINGCODE = "AAAAAAAAAA";
    private static final String UPDATED_PROCESSINGCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_MAX = 1L;
    private static final Long UPDATED_MAX = 2L;

    private static final Long DEFAULT_MIN = 1L;
    private static final Long UPDATED_MIN = 2L;

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDAT = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDAT = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDON = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDON = "BBBBBBBBBB";

    private static final Long DEFAULT_APPROVED = 1L;
    private static final Long UPDATED_APPROVED = 2L;

    private static final Long DEFAULT_DECLINED = 1L;
    private static final Long UPDATED_DECLINED = 2L;

    private static final String DEFAULT_DECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DECLINEDBY = "BBBBBBBBBB";

    private static final Long DEFAULT_CHARGEID = 1L;
    private static final Long UPDATED_CHARGEID = 2L;

    private static final String ENTITY_API_URL = "/api/charge-ranges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChargeRangeRepository chargeRangeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChargeRangeMockMvc;

    private ChargeRange chargeRange;

    private ChargeRange insertedChargeRange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeRange createEntity() {
        return new ChargeRange()
            .billerid(DEFAULT_BILLERID)
            .processingcode(DEFAULT_PROCESSINGCODE)
            .max(DEFAULT_MAX)
            .min(DEFAULT_MIN)
            .amount(DEFAULT_AMOUNT)
            .createdby(DEFAULT_CREATEDBY)
            .approvedby(DEFAULT_APPROVEDBY)
            .createdat(DEFAULT_CREATEDAT)
            .approvedon(DEFAULT_APPROVEDON)
            .approved(DEFAULT_APPROVED)
            .declined(DEFAULT_DECLINED)
            .declinedby(DEFAULT_DECLINEDBY)
            .chargeid(DEFAULT_CHARGEID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeRange createUpdatedEntity() {
        return new ChargeRange()
            .billerid(UPDATED_BILLERID)
            .processingcode(UPDATED_PROCESSINGCODE)
            .max(UPDATED_MAX)
            .min(UPDATED_MIN)
            .amount(UPDATED_AMOUNT)
            .createdby(UPDATED_CREATEDBY)
            .approvedby(UPDATED_APPROVEDBY)
            .createdat(UPDATED_CREATEDAT)
            .approvedon(UPDATED_APPROVEDON)
            .approved(UPDATED_APPROVED)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .chargeid(UPDATED_CHARGEID);
    }

    @BeforeEach
    public void initTest() {
        chargeRange = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedChargeRange != null) {
            chargeRangeRepository.delete(insertedChargeRange);
            insertedChargeRange = null;
        }
    }

    @Test
    @Transactional
    void createChargeRange() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChargeRange
        var returnedChargeRange = om.readValue(
            restChargeRangeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChargeRange.class
        );

        // Validate the ChargeRange in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertChargeRangeUpdatableFieldsEquals(returnedChargeRange, getPersistedChargeRange(returnedChargeRange));

        insertedChargeRange = returnedChargeRange;
    }

    @Test
    @Transactional
    void createChargeRangeWithExistingId() throws Exception {
        // Create the ChargeRange with an existing ID
        chargeRange.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBilleridIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setBillerid(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessingcodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setProcessingcode(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setMax(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMinIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setMin(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAmountIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setAmount(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChargeidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        chargeRange.setChargeid(null);

        // Create the ChargeRange, which fails.

        restChargeRangeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllChargeRanges() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        // Get all the chargeRangeList
        restChargeRangeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chargeRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].billerid").value(hasItem(DEFAULT_BILLERID)))
            .andExpect(jsonPath("$.[*].processingcode").value(hasItem(DEFAULT_PROCESSINGCODE)))
            .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX.intValue())))
            .andExpect(jsonPath("$.[*].min").value(hasItem(DEFAULT_MIN.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT)))
            .andExpect(jsonPath("$.[*].approvedon").value(hasItem(DEFAULT_APPROVEDON)))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.intValue())))
            .andExpect(jsonPath("$.[*].declined").value(hasItem(DEFAULT_DECLINED.intValue())))
            .andExpect(jsonPath("$.[*].declinedby").value(hasItem(DEFAULT_DECLINEDBY)))
            .andExpect(jsonPath("$.[*].chargeid").value(hasItem(DEFAULT_CHARGEID.intValue())));
    }

    @Test
    @Transactional
    void getChargeRange() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        // Get the chargeRange
        restChargeRangeMockMvc
            .perform(get(ENTITY_API_URL_ID, chargeRange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chargeRange.getId().intValue()))
            .andExpect(jsonPath("$.billerid").value(DEFAULT_BILLERID))
            .andExpect(jsonPath("$.processingcode").value(DEFAULT_PROCESSINGCODE))
            .andExpect(jsonPath("$.max").value(DEFAULT_MAX.intValue()))
            .andExpect(jsonPath("$.min").value(DEFAULT_MIN.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT))
            .andExpect(jsonPath("$.approvedon").value(DEFAULT_APPROVEDON))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.intValue()))
            .andExpect(jsonPath("$.declined").value(DEFAULT_DECLINED.intValue()))
            .andExpect(jsonPath("$.declinedby").value(DEFAULT_DECLINEDBY))
            .andExpect(jsonPath("$.chargeid").value(DEFAULT_CHARGEID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingChargeRange() throws Exception {
        // Get the chargeRange
        restChargeRangeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingChargeRange() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chargeRange
        ChargeRange updatedChargeRange = chargeRangeRepository.findById(chargeRange.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedChargeRange are not directly saved in db
        em.detach(updatedChargeRange);
        updatedChargeRange
            .billerid(UPDATED_BILLERID)
            .processingcode(UPDATED_PROCESSINGCODE)
            .max(UPDATED_MAX)
            .min(UPDATED_MIN)
            .amount(UPDATED_AMOUNT)
            .createdby(UPDATED_CREATEDBY)
            .approvedby(UPDATED_APPROVEDBY)
            .createdat(UPDATED_CREATEDAT)
            .approvedon(UPDATED_APPROVEDON)
            .approved(UPDATED_APPROVED)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .chargeid(UPDATED_CHARGEID);

        restChargeRangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedChargeRange.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedChargeRange))
            )
            .andExpect(status().isOk());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChargeRangeToMatchAllProperties(updatedChargeRange);
    }

    @Test
    @Transactional
    void putNonExistingChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chargeRange.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chargeRange))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chargeRange))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChargeRangeWithPatch() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chargeRange using partial update
        ChargeRange partialUpdatedChargeRange = new ChargeRange();
        partialUpdatedChargeRange.setId(chargeRange.getId());

        partialUpdatedChargeRange
            .processingcode(UPDATED_PROCESSINGCODE)
            .max(UPDATED_MAX)
            .approvedby(UPDATED_APPROVEDBY)
            .createdat(UPDATED_CREATEDAT)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY);

        restChargeRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChargeRange.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChargeRange))
            )
            .andExpect(status().isOk());

        // Validate the ChargeRange in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChargeRangeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChargeRange, chargeRange),
            getPersistedChargeRange(chargeRange)
        );
    }

    @Test
    @Transactional
    void fullUpdateChargeRangeWithPatch() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chargeRange using partial update
        ChargeRange partialUpdatedChargeRange = new ChargeRange();
        partialUpdatedChargeRange.setId(chargeRange.getId());

        partialUpdatedChargeRange
            .billerid(UPDATED_BILLERID)
            .processingcode(UPDATED_PROCESSINGCODE)
            .max(UPDATED_MAX)
            .min(UPDATED_MIN)
            .amount(UPDATED_AMOUNT)
            .createdby(UPDATED_CREATEDBY)
            .approvedby(UPDATED_APPROVEDBY)
            .createdat(UPDATED_CREATEDAT)
            .approvedon(UPDATED_APPROVEDON)
            .approved(UPDATED_APPROVED)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .chargeid(UPDATED_CHARGEID);

        restChargeRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChargeRange.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChargeRange))
            )
            .andExpect(status().isOk());

        // Validate the ChargeRange in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChargeRangeUpdatableFieldsEquals(partialUpdatedChargeRange, getPersistedChargeRange(partialUpdatedChargeRange));
    }

    @Test
    @Transactional
    void patchNonExistingChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chargeRange.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chargeRange))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chargeRange))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChargeRange() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chargeRange.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeRangeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chargeRange)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChargeRange in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChargeRange() throws Exception {
        // Initialize the database
        insertedChargeRange = chargeRangeRepository.saveAndFlush(chargeRange);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chargeRange
        restChargeRangeMockMvc
            .perform(delete(ENTITY_API_URL_ID, chargeRange.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chargeRangeRepository.count();
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

    protected ChargeRange getPersistedChargeRange(ChargeRange chargeRange) {
        return chargeRangeRepository.findById(chargeRange.getId()).orElseThrow();
    }

    protected void assertPersistedChargeRangeToMatchAllProperties(ChargeRange expectedChargeRange) {
        assertChargeRangeAllPropertiesEquals(expectedChargeRange, getPersistedChargeRange(expectedChargeRange));
    }

    protected void assertPersistedChargeRangeToMatchUpdatableProperties(ChargeRange expectedChargeRange) {
        assertChargeRangeAllUpdatablePropertiesEquals(expectedChargeRange, getPersistedChargeRange(expectedChargeRange));
    }
}
