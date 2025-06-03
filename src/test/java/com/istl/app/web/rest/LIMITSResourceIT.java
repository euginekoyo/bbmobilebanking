package com.istl.app.web.rest;

import static com.istl.app.domain.LimitsAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.Limits;
import com.istl.app.repository.mobileapp.LimitsRepository;
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
 * Integration tests for the {@link LimitsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LimitsResourceIT {

    private static final String DEFAULT_TRANSACTIONTYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTIONTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROCODE = "AAAAAA";
    private static final String UPDATED_PROCODE = "BBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANSACTIONLIMIT = 1L;
    private static final Long UPDATED_TRANSACTIONLIMIT = 2L;

    private static final Long DEFAULT_DAILYLIMIT = 1L;
    private static final Long UPDATED_DAILYLIMIT = 2L;

    private static final String DEFAULT_REGISTEREDBY = "AAAAAAAAAA";
    private static final String UPDATED_REGISTEREDBY = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTEREDDATE = "AAAAAAA";
    private static final String UPDATED_REGISTEREDDATE = "BBBBBBB";

    private static final String DEFAULT_APPROVED = "AA";
    private static final String UPDATED_APPROVED = "BB";

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDDATE = "AAAAAAA";
    private static final String UPDATED_APPROVEDDATE = "BBBBBBB";

    private static final String DEFAULT_UPDATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATEDDATE = "AAAAAAA";
    private static final String UPDATED_UPDATEDDATE = "BBBBBBB";

    private static final Long DEFAULT_REWORK = 1L;
    private static final Long UPDATED_REWORK = 2L;

    private static final String DEFAULT_REWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_REWORKBY = "BBBBBBBBBB";

    private static final String DEFAULT_SESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_SESSIONID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/limits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LimitsRepository limitsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLimitsMockMvc;

    private Limits limits;

    private Limits insertedLimits;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Limits createEntity() {
        return new Limits()
            .transactiontype(DEFAULT_TRANSACTIONTYPE)
            .procode(DEFAULT_PROCODE)
            .channel(DEFAULT_CHANNEL)
            .transactionlimit(DEFAULT_TRANSACTIONLIMIT)
            .dailylimit(DEFAULT_DAILYLIMIT)
            .registeredby(DEFAULT_REGISTEREDBY)
            .registereddate(DEFAULT_REGISTEREDDATE)
            .approved(DEFAULT_APPROVED)
            .approvedby(DEFAULT_APPROVEDBY)
            .approveddate(DEFAULT_APPROVEDDATE)
            .updatedby(DEFAULT_UPDATEDBY)
            .updateddate(DEFAULT_UPDATEDDATE)
            .rework(DEFAULT_REWORK)
            .reworkby(DEFAULT_REWORKBY)
            .sessionid(DEFAULT_SESSIONID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Limits createUpdatedEntity() {
        return new Limits()
            .transactiontype(UPDATED_TRANSACTIONTYPE)
            .procode(UPDATED_PROCODE)
            .channel(UPDATED_CHANNEL)
            .transactionlimit(UPDATED_TRANSACTIONLIMIT)
            .dailylimit(UPDATED_DAILYLIMIT)
            .registeredby(UPDATED_REGISTEREDBY)
            .registereddate(UPDATED_REGISTEREDDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .updatedby(UPDATED_UPDATEDBY)
            .updateddate(UPDATED_UPDATEDDATE)
            .rework(UPDATED_REWORK)
            .reworkby(UPDATED_REWORKBY)
            .sessionid(UPDATED_SESSIONID);
    }

    @BeforeEach
    public void initTest() {
        limits = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLimits != null) {
            limitsRepository.delete(insertedLimits);
            insertedLimits = null;
        }
    }

    @Test
    @Transactional
    void createLimits() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Limits
        var returnedLimits = om.readValue(
            restLimitsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Limits.class
        );

        // Validate the Limits in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLimitsUpdatableFieldsEquals(returnedLimits, getPersistedLimits(returnedLimits));

        insertedLimits = returnedLimits;
    }

    @Test
    @Transactional
    void createLimitsWithExistingId() throws Exception {
        // Create the Limits with an existing ID
        limits.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLimitsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
            .andExpect(status().isBadRequest());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTransactiontypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        limits.setTransactiontype(null);

        // Create the Limits, which fails.

        restLimitsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTransactionlimitIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        limits.setTransactionlimit(null);

        // Create the Limits, which fails.

        restLimitsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLimits() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        // Get all the limitsList
        restLimitsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(limits.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactiontype").value(hasItem(DEFAULT_TRANSACTIONTYPE)))
            .andExpect(jsonPath("$.[*].procode").value(hasItem(DEFAULT_PROCODE)))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].transactionlimit").value(hasItem(DEFAULT_TRANSACTIONLIMIT.intValue())))
            .andExpect(jsonPath("$.[*].dailylimit").value(hasItem(DEFAULT_DAILYLIMIT.intValue())))
            .andExpect(jsonPath("$.[*].registeredby").value(hasItem(DEFAULT_REGISTEREDBY)))
            .andExpect(jsonPath("$.[*].registereddate").value(hasItem(DEFAULT_REGISTEREDDATE)))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED)))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].approveddate").value(hasItem(DEFAULT_APPROVEDDATE)))
            .andExpect(jsonPath("$.[*].updatedby").value(hasItem(DEFAULT_UPDATEDBY)))
            .andExpect(jsonPath("$.[*].updateddate").value(hasItem(DEFAULT_UPDATEDDATE)))
            .andExpect(jsonPath("$.[*].rework").value(hasItem(DEFAULT_REWORK.intValue())))
            .andExpect(jsonPath("$.[*].reworkby").value(hasItem(DEFAULT_REWORKBY)))
            .andExpect(jsonPath("$.[*].sessionid").value(hasItem(DEFAULT_SESSIONID)));
    }

    @Test
    @Transactional
    void getLimits() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        // Get the limits
        restLimitsMockMvc
            .perform(get(ENTITY_API_URL_ID, limits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(limits.getId().intValue()))
            .andExpect(jsonPath("$.transactiontype").value(DEFAULT_TRANSACTIONTYPE))
            .andExpect(jsonPath("$.procode").value(DEFAULT_PROCODE))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.transactionlimit").value(DEFAULT_TRANSACTIONLIMIT.intValue()))
            .andExpect(jsonPath("$.dailylimit").value(DEFAULT_DAILYLIMIT.intValue()))
            .andExpect(jsonPath("$.registeredby").value(DEFAULT_REGISTEREDBY))
            .andExpect(jsonPath("$.registereddate").value(DEFAULT_REGISTEREDDATE))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.approveddate").value(DEFAULT_APPROVEDDATE))
            .andExpect(jsonPath("$.updatedby").value(DEFAULT_UPDATEDBY))
            .andExpect(jsonPath("$.updateddate").value(DEFAULT_UPDATEDDATE))
            .andExpect(jsonPath("$.rework").value(DEFAULT_REWORK.intValue()))
            .andExpect(jsonPath("$.reworkby").value(DEFAULT_REWORKBY))
            .andExpect(jsonPath("$.sessionid").value(DEFAULT_SESSIONID));
    }

    @Test
    @Transactional
    void getNonExistingLimits() throws Exception {
        // Get the limits
        restLimitsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLimits() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the limits
        Limits updatedLimits = limitsRepository.findById(limits.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLimits are not directly saved in db
        em.detach(updatedLimits);
        updatedLimits
            .transactiontype(UPDATED_TRANSACTIONTYPE)
            .procode(UPDATED_PROCODE)
            .channel(UPDATED_CHANNEL)
            .transactionlimit(UPDATED_TRANSACTIONLIMIT)
            .dailylimit(UPDATED_DAILYLIMIT)
            .registeredby(UPDATED_REGISTEREDBY)
            .registereddate(UPDATED_REGISTEREDDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .updatedby(UPDATED_UPDATEDBY)
            .updateddate(UPDATED_UPDATEDDATE)
            .rework(UPDATED_REWORK)
            .reworkby(UPDATED_REWORKBY)
            .sessionid(UPDATED_SESSIONID);

        restLimitsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLimits.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLimits))
            )
            .andExpect(status().isOk());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLimitsToMatchAllProperties(updatedLimits);
    }

    @Test
    @Transactional
    void putNonExistingLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(put(ENTITY_API_URL_ID, limits.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
            .andExpect(status().isBadRequest());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(limits))
            )
            .andExpect(status().isBadRequest());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(limits)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLimitsWithPatch() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the limits using partial update
        Limits partialUpdatedLimits = new Limits();
        partialUpdatedLimits.setId(limits.getId());

        partialUpdatedLimits
            .procode(UPDATED_PROCODE)
            .dailylimit(UPDATED_DAILYLIMIT)
            .approved(UPDATED_APPROVED)
            .approveddate(UPDATED_APPROVEDDATE)
            .updateddate(UPDATED_UPDATEDDATE)
            .rework(UPDATED_REWORK)
            .sessionid(UPDATED_SESSIONID);

        restLimitsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLimits.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLimits))
            )
            .andExpect(status().isOk());

        // Validate the Limits in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLimitsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedLimits, limits), getPersistedLimits(limits));
    }

    @Test
    @Transactional
    void fullUpdateLimitsWithPatch() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the limits using partial update
        Limits partialUpdatedLimits = new Limits();
        partialUpdatedLimits.setId(limits.getId());

        partialUpdatedLimits
            .transactiontype(UPDATED_TRANSACTIONTYPE)
            .procode(UPDATED_PROCODE)
            .channel(UPDATED_CHANNEL)
            .transactionlimit(UPDATED_TRANSACTIONLIMIT)
            .dailylimit(UPDATED_DAILYLIMIT)
            .registeredby(UPDATED_REGISTEREDBY)
            .registereddate(UPDATED_REGISTEREDDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .updatedby(UPDATED_UPDATEDBY)
            .updateddate(UPDATED_UPDATEDDATE)
            .rework(UPDATED_REWORK)
            .reworkby(UPDATED_REWORKBY)
            .sessionid(UPDATED_SESSIONID);

        restLimitsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLimits.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLimits))
            )
            .andExpect(status().isOk());

        // Validate the Limits in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLimitsUpdatableFieldsEquals(partialUpdatedLimits, getPersistedLimits(partialUpdatedLimits));
    }

    @Test
    @Transactional
    void patchNonExistingLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, limits.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(limits))
            )
            .andExpect(status().isBadRequest());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(limits))
            )
            .andExpect(status().isBadRequest());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLimits() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        limits.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLimitsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(limits)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Limits in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLimits() throws Exception {
        // Initialize the database
        insertedLimits = limitsRepository.saveAndFlush(limits);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the limits
        restLimitsMockMvc
            .perform(delete(ENTITY_API_URL_ID, limits.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return limitsRepository.count();
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

    protected Limits getPersistedLimits(Limits limits) {
        return limitsRepository.findById(limits.getId()).orElseThrow();
    }

    protected void assertPersistedLimitsToMatchAllProperties(Limits expectedLimits) {
        assertLimitsAllPropertiesEquals(expectedLimits, getPersistedLimits(expectedLimits));
    }

    protected void assertPersistedLimitsToMatchUpdatableProperties(Limits expectedLimits) {
        assertLimitsAllUpdatablePropertiesEquals(expectedLimits, getPersistedLimits(expectedLimits));
    }
}
