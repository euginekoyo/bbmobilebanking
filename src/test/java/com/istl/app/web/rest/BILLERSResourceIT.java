package com.istl.app.web.rest;

import static com.istl.app.domain.BillersAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.Billers;
import com.istl.app.repository.mobileapp.BillersRepository;
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
 * Integration tests for the {@link BillersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BillersResourceIT {

    private static final String DEFAULT_BILLERID = "AAAAAAAAAA";
    private static final String UPDATED_BILLERID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BILLERCOLLECTIONACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BILLERCOLLECTIONACCOUNT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final Long DEFAULT_APPROVED = 1L;
    private static final Long UPDATED_APPROVED = 2L;

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHARGABLEPRODUCTID = "AAAAAAAAAA";
    private static final String UPDATED_CHARGABLEPRODUCTID = "BBBBBBBBBB";

    private static final String DEFAULT_NONCHARGABLEPRODUCTID = "AAAAAAAAAA";
    private static final String UPDATED_NONCHARGABLEPRODUCTID = "BBBBBBBBBB";

    private static final String DEFAULT_USDBILLERCOLLECTIONACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_USDBILLERCOLLECTIONACCOUNT = "BBBBBBBBBB";

    private static final Long DEFAULT_ENABLEDUPLICATECHECK = 1L;
    private static final Long UPDATED_ENABLEDUPLICATECHECK = 2L;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_SESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_SESSIONID = "BBBBBBBBBB";

    private static final String DEFAULT_REWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_REWORKBY = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final Long DEFAULT_ACTIVE = 1L;
    private static final Long UPDATED_ACTIVE = 2L;

    private static final Long DEFAULT_REWORK = 1L;
    private static final Long UPDATED_REWORK = 2L;

    private static final String ENTITY_API_URL = "/api/billers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BillersRepository billersRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillersMockMvc;

    private Billers billers;

    private Billers insertedBillers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billers createEntity() {
        return new Billers()
            .billerid(DEFAULT_BILLERID)
            .description(DEFAULT_DESCRIPTION)
            .billercollectionaccount(DEFAULT_BILLERCOLLECTIONACCOUNT)
            .datecreated(DEFAULT_DATECREATED)
            .createdby(DEFAULT_CREATEDBY)
            .approved(DEFAULT_APPROVED)
            .approvedby(DEFAULT_APPROVEDBY)
            .approveddate(DEFAULT_APPROVEDDATE)
            .chargableproductid(DEFAULT_CHARGABLEPRODUCTID)
            .nonchargableproductid(DEFAULT_NONCHARGABLEPRODUCTID)
            .usdbillercollectionaccount(DEFAULT_USDBILLERCOLLECTIONACCOUNT)
            .enableduplicatecheck(DEFAULT_ENABLEDUPLICATECHECK)
            .remarks(DEFAULT_REMARKS)
            .sessionid(DEFAULT_SESSIONID)
            .reworkby(DEFAULT_REWORKBY)
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .rework(DEFAULT_REWORK);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billers createUpdatedEntity() {
        return new Billers()
            .billerid(UPDATED_BILLERID)
            .description(UPDATED_DESCRIPTION)
            .billercollectionaccount(UPDATED_BILLERCOLLECTIONACCOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .chargableproductid(UPDATED_CHARGABLEPRODUCTID)
            .nonchargableproductid(UPDATED_NONCHARGABLEPRODUCTID)
            .usdbillercollectionaccount(UPDATED_USDBILLERCOLLECTIONACCOUNT)
            .enableduplicatecheck(UPDATED_ENABLEDUPLICATECHECK)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY)
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .rework(UPDATED_REWORK);
    }

    @BeforeEach
    public void initTest() {
        billers = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBillers != null) {
            billersRepository.delete(insertedBillers);
            insertedBillers = null;
        }
    }

    @Test
    @Transactional
    void createBillers() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Billers
        var returnedBillers = om.readValue(
            restBillersMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Billers.class
        );

        // Validate the Billers in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBillersUpdatableFieldsEquals(returnedBillers, getPersistedBillers(returnedBillers));

        insertedBillers = returnedBillers;
    }

    @Test
    @Transactional
    void createBillersWithExistingId() throws Exception {
        // Create the Billers with an existing ID
        billers.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillersMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
            .andExpect(status().isBadRequest());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBilleridIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        billers.setBillerid(null);

        // Create the Billers, which fails.

        restBillersMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        billers.setDescription(null);

        // Create the Billers, which fails.

        restBillersMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBillers() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        // Get all the billersList
        restBillersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billers.getId().intValue())))
            .andExpect(jsonPath("$.[*].billerid").value(hasItem(DEFAULT_BILLERID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].billercollectionaccount").value(hasItem(DEFAULT_BILLERCOLLECTIONACCOUNT)))
            .andExpect(jsonPath("$.[*].datecreated").value(hasItem(DEFAULT_DATECREATED.toString())))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.intValue())))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].approveddate").value(hasItem(DEFAULT_APPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].chargableproductid").value(hasItem(DEFAULT_CHARGABLEPRODUCTID)))
            .andExpect(jsonPath("$.[*].nonchargableproductid").value(hasItem(DEFAULT_NONCHARGABLEPRODUCTID)))
            .andExpect(jsonPath("$.[*].usdbillercollectionaccount").value(hasItem(DEFAULT_USDBILLERCOLLECTIONACCOUNT)))
            .andExpect(jsonPath("$.[*].enableduplicatecheck").value(hasItem(DEFAULT_ENABLEDUPLICATECHECK.intValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].sessionid").value(hasItem(DEFAULT_SESSIONID)))
            .andExpect(jsonPath("$.[*].reworkby").value(hasItem(DEFAULT_REWORKBY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.intValue())))
            .andExpect(jsonPath("$.[*].rework").value(hasItem(DEFAULT_REWORK.intValue())));
    }

    @Test
    @Transactional
    void getBillers() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        // Get the billers
        restBillersMockMvc
            .perform(get(ENTITY_API_URL_ID, billers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billers.getId().intValue()))
            .andExpect(jsonPath("$.billerid").value(DEFAULT_BILLERID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.billercollectionaccount").value(DEFAULT_BILLERCOLLECTIONACCOUNT))
            .andExpect(jsonPath("$.datecreated").value(DEFAULT_DATECREATED.toString()))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.intValue()))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.approveddate").value(DEFAULT_APPROVEDDATE.toString()))
            .andExpect(jsonPath("$.chargableproductid").value(DEFAULT_CHARGABLEPRODUCTID))
            .andExpect(jsonPath("$.nonchargableproductid").value(DEFAULT_NONCHARGABLEPRODUCTID))
            .andExpect(jsonPath("$.usdbillercollectionaccount").value(DEFAULT_USDBILLERCOLLECTIONACCOUNT))
            .andExpect(jsonPath("$.enableduplicatecheck").value(DEFAULT_ENABLEDUPLICATECHECK.intValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.sessionid").value(DEFAULT_SESSIONID))
            .andExpect(jsonPath("$.reworkby").value(DEFAULT_REWORKBY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.intValue()))
            .andExpect(jsonPath("$.rework").value(DEFAULT_REWORK.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingBillers() throws Exception {
        // Get the billers
        restBillersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBillers() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billers
        Billers updatedBillers = billersRepository.findById(billers.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBillers are not directly saved in db
        em.detach(updatedBillers);
        updatedBillers
            .billerid(UPDATED_BILLERID)
            .description(UPDATED_DESCRIPTION)
            .billercollectionaccount(UPDATED_BILLERCOLLECTIONACCOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .chargableproductid(UPDATED_CHARGABLEPRODUCTID)
            .nonchargableproductid(UPDATED_NONCHARGABLEPRODUCTID)
            .usdbillercollectionaccount(UPDATED_USDBILLERCOLLECTIONACCOUNT)
            .enableduplicatecheck(UPDATED_ENABLEDUPLICATECHECK)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY)
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .rework(UPDATED_REWORK);

        restBillersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBillers.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBillers))
            )
            .andExpect(status().isOk());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBillersToMatchAllProperties(updatedBillers);
    }

    @Test
    @Transactional
    void putNonExistingBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(put(ENTITY_API_URL_ID, billers.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
            .andExpect(status().isBadRequest());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(billers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBillersWithPatch() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billers using partial update
        Billers partialUpdatedBillers = new Billers();
        partialUpdatedBillers.setId(billers.getId());

        partialUpdatedBillers
            .billercollectionaccount(UPDATED_BILLERCOLLECTIONACCOUNT)
            .approveddate(UPDATED_APPROVEDDATE)
            .chargableproductid(UPDATED_CHARGABLEPRODUCTID)
            .usdbillercollectionaccount(UPDATED_USDBILLERCOLLECTIONACCOUNT)
            .enableduplicatecheck(UPDATED_ENABLEDUPLICATECHECK)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .status(UPDATED_STATUS);

        restBillersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillers))
            )
            .andExpect(status().isOk());

        // Validate the Billers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillersUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedBillers, billers), getPersistedBillers(billers));
    }

    @Test
    @Transactional
    void fullUpdateBillersWithPatch() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billers using partial update
        Billers partialUpdatedBillers = new Billers();
        partialUpdatedBillers.setId(billers.getId());

        partialUpdatedBillers
            .billerid(UPDATED_BILLERID)
            .description(UPDATED_DESCRIPTION)
            .billercollectionaccount(UPDATED_BILLERCOLLECTIONACCOUNT)
            .datecreated(UPDATED_DATECREATED)
            .createdby(UPDATED_CREATEDBY)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .chargableproductid(UPDATED_CHARGABLEPRODUCTID)
            .nonchargableproductid(UPDATED_NONCHARGABLEPRODUCTID)
            .usdbillercollectionaccount(UPDATED_USDBILLERCOLLECTIONACCOUNT)
            .enableduplicatecheck(UPDATED_ENABLEDUPLICATECHECK)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY)
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .rework(UPDATED_REWORK);

        restBillersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillers))
            )
            .andExpect(status().isOk());

        // Validate the Billers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillersUpdatableFieldsEquals(partialUpdatedBillers, getPersistedBillers(partialUpdatedBillers));
    }

    @Test
    @Transactional
    void patchNonExistingBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, billers.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(billers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(billers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBillers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillersMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(billers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBillers() throws Exception {
        // Initialize the database
        insertedBillers = billersRepository.saveAndFlush(billers);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the billers
        restBillersMockMvc
            .perform(delete(ENTITY_API_URL_ID, billers.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return billersRepository.count();
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

    protected Billers getPersistedBillers(Billers billers) {
        return billersRepository.findById(billers.getId()).orElseThrow();
    }

    protected void assertPersistedBillersToMatchAllProperties(Billers expectedBillers) {
        assertBillersAllPropertiesEquals(expectedBillers, getPersistedBillers(expectedBillers));
    }

    protected void assertPersistedBillersToMatchUpdatableProperties(Billers expectedBillers) {
        assertBillersAllUpdatablePropertiesEquals(expectedBillers, getPersistedBillers(expectedBillers));
    }
}
