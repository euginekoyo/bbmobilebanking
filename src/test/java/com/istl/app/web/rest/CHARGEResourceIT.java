package com.istl.app.web.rest;

import static com.istl.app.domain.ChargeAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.Charge;
import com.istl.app.repository.ChargeRepository;
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
 * Integration tests for the {@link ChargeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChargeResourceIT {

    private static final String DEFAULT_TXNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_TXNTYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_FEEMODE = 1L;
    private static final Long UPDATED_FEEMODE = 2L;

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final Instant DEFAULT_DATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVED = "AAAAAAAAAA";
    private static final String UPDATED_APPROVED = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_TXNCODE = 1L;
    private static final Long UPDATED_TXNCODE = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/charges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChargeMockMvc;

    private Charge charge;

    private Charge insertedCharge;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Charge createEntity() {
        return new Charge()
            .txntype(DEFAULT_TXNTYPE)
            .feemode(DEFAULT_FEEMODE)
            .amount(DEFAULT_AMOUNT)
            .datecreated(DEFAULT_DATECREATED)
            .createdby(DEFAULT_CREATEDBY)
            .approved(DEFAULT_APPROVED)
            .approvedby(DEFAULT_APPROVEDBY)
            .channel(DEFAULT_CHANNEL)
            .txncode(DEFAULT_TXNCODE)
            .description(DEFAULT_DESCRIPTION)
            .approveddate(DEFAULT_APPROVEDDATE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Charge createUpdatedEntity() {
        return new Charge()
            .txntype(UPDATED_TXNTYPE)
            .feemode(UPDATED_FEEMODE)
            .amount(UPDATED_AMOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .channel(UPDATED_CHANNEL)
            .txncode(UPDATED_TXNCODE)
            .description(UPDATED_DESCRIPTION)
            .approveddate(UPDATED_APPROVEDDATE);
    }

    @BeforeEach
    public void initTest() {
        charge = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCharge != null) {
            chargeRepository.delete(insertedCharge);
            insertedCharge = null;
        }
    }

    @Test
    @Transactional
    void createCharge() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Charge
        var returnedCharge = om.readValue(
            restChargeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(charge)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Charge.class
        );

        // Validate the Charge in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertChargeUpdatableFieldsEquals(returnedCharge, getPersistedCharge(returnedCharge));

        insertedCharge = returnedCharge;
    }

    @Test
    @Transactional
    void createChargeWithExistingId() throws Exception {
        // Create the Charge with an existing ID
        charge.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChargeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(charge)))
            .andExpect(status().isBadRequest());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTxntypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        charge.setTxntype(null);

        // Create the Charge, which fails.

        restChargeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(charge)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCharges() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        // Get all the chargeList
        restChargeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(charge.getId().intValue())))
            .andExpect(jsonPath("$.[*].txntype").value(hasItem(DEFAULT_TXNTYPE)))
            .andExpect(jsonPath("$.[*].feemode").value(hasItem(DEFAULT_FEEMODE.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].datecreated").value(hasItem(DEFAULT_DATECREATED.toString())))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED)))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].txncode").value(hasItem(DEFAULT_TXNCODE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].approveddate").value(hasItem(DEFAULT_APPROVEDDATE.toString())));
    }

    @Test
    @Transactional
    void getCharge() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        // Get the charge
        restChargeMockMvc
            .perform(get(ENTITY_API_URL_ID, charge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(charge.getId().intValue()))
            .andExpect(jsonPath("$.txntype").value(DEFAULT_TXNTYPE))
            .andExpect(jsonPath("$.feemode").value(DEFAULT_FEEMODE.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.datecreated").value(DEFAULT_DATECREATED.toString()))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.txncode").value(DEFAULT_TXNCODE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.approveddate").value(DEFAULT_APPROVEDDATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCharge() throws Exception {
        // Get the charge
        restChargeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCharge() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the charge
        Charge updatedCharge = chargeRepository.findById(charge.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCharge are not directly saved in db
        em.detach(updatedCharge);
        updatedCharge
            .txntype(UPDATED_TXNTYPE)
            .feemode(UPDATED_FEEMODE)
            .amount(UPDATED_AMOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .channel(UPDATED_CHANNEL)
            .txncode(UPDATED_TXNCODE)
            .description(UPDATED_DESCRIPTION)
            .approveddate(UPDATED_APPROVEDDATE);

        restChargeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCharge.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCharge))
            )
            .andExpect(status().isOk());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChargeToMatchAllProperties(updatedCharge);
    }

    @Test
    @Transactional
    void putNonExistingCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(put(ENTITY_API_URL_ID, charge.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(charge)))
            .andExpect(status().isBadRequest());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(charge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(charge)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChargeWithPatch() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the charge using partial update
        Charge partialUpdatedCharge = new Charge();
        partialUpdatedCharge.setId(charge.getId());

        partialUpdatedCharge
            .feemode(UPDATED_FEEMODE)
            .amount(UPDATED_AMOUNT)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .channel(UPDATED_CHANNEL)
            .txncode(UPDATED_TXNCODE)
            .description(UPDATED_DESCRIPTION);

        restChargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharge.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCharge))
            )
            .andExpect(status().isOk());

        // Validate the Charge in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChargeUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCharge, charge), getPersistedCharge(charge));
    }

    @Test
    @Transactional
    void fullUpdateChargeWithPatch() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the charge using partial update
        Charge partialUpdatedCharge = new Charge();
        partialUpdatedCharge.setId(charge.getId());

        partialUpdatedCharge
            .txntype(UPDATED_TXNTYPE)
            .feemode(UPDATED_FEEMODE)
            .amount(UPDATED_AMOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .channel(UPDATED_CHANNEL)
            .txncode(UPDATED_TXNCODE)
            .description(UPDATED_DESCRIPTION)
            .approveddate(UPDATED_APPROVEDDATE);

        restChargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharge.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCharge))
            )
            .andExpect(status().isOk());

        // Validate the Charge in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChargeUpdatableFieldsEquals(partialUpdatedCharge, getPersistedCharge(partialUpdatedCharge));
    }

    @Test
    @Transactional
    void patchNonExistingCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, charge.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(charge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(charge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        charge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChargeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(charge)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Charge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCharge() throws Exception {
        // Initialize the database
        insertedCharge = chargeRepository.saveAndFlush(charge);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the charge
        restChargeMockMvc
            .perform(delete(ENTITY_API_URL_ID, charge.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chargeRepository.count();
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

    protected Charge getPersistedCharge(Charge charge) {
        return chargeRepository.findById(charge.getId()).orElseThrow();
    }

    protected void assertPersistedChargeToMatchAllProperties(Charge expectedCharge) {
        assertChargeAllPropertiesEquals(expectedCharge, getPersistedCharge(expectedCharge));
    }

    protected void assertPersistedChargeToMatchUpdatableProperties(Charge expectedCharge) {
        assertChargeAllUpdatablePropertiesEquals(expectedCharge, getPersistedCharge(expectedCharge));
    }
}
