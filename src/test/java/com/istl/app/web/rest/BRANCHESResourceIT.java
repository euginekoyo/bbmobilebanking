package com.istl.app.web.rest;

import static com.istl.app.domain.BranchesAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.Branches;
import com.istl.app.repository.mobileapp.BranchesRepository;
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
 * Integration tests for the {@link BranchesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchesResourceIT {

    private static final String DEFAULT_BRANCHNAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHNAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCHCODE = "AAA";
    private static final String UPDATED_BRANCHCODE = "BBB";

    private static final Long DEFAULT_APPROVED = 1L;
    private static final Long UPDATED_APPROVED = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTPERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDON = "AAAAAAA";
    private static final String UPDATED_APPROVEDON = "BBBBBBB";

    private static final String DEFAULT_CHECKERREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_CHECKERREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_DELETEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DELETEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DELETEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELETEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DELETEREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_DELETEREMARKS = "BBBBBBBBBB";

    private static final Long DEFAULT_DELETED = 1L;
    private static final Long UPDATED_DELETED = 2L;

    private static final Long DEFAULT_DECLINED = 1L;
    private static final Long UPDATED_DECLINED = 2L;

    private static final String DEFAULT_DECLINEDDON = "AAAAAAA";
    private static final String UPDATED_DECLINEDDON = "BBBBBBB";

    private static final String DEFAULT_DECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DECLINEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_SESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_SESSIONID = "BBBBBBBBBB";

    private static final Long DEFAULT_REWORKED = 1L;
    private static final Long UPDATED_REWORKED = 2L;

    private static final String DEFAULT_REWORKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_REWORKEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_REWORKEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REWORKEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONNAME = "AAAAAAAAAA";
    private static final String UPDATED_REGIONNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_REPORTING = 1L;
    private static final Long UPDATED_REPORTING = 2L;

    private static final String ENTITY_API_URL = "/api/branches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BranchesRepository branchesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchesMockMvc;

    private Branches branches;

    private Branches insertedBranches;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branches createEntity() {
        return new Branches()
            .branchname(DEFAULT_BRANCHNAME)
            .branchcode(DEFAULT_BRANCHCODE)
            .approved(DEFAULT_APPROVED)
            .email(DEFAULT_EMAIL)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .location(DEFAULT_LOCATION)
            .contactperson(DEFAULT_CONTACTPERSON)
            .remarks(DEFAULT_REMARKS)
            .createdby(DEFAULT_CREATEDBY)
            .createdon(DEFAULT_CREATEDON)
            .approvedby(DEFAULT_APPROVEDBY)
            .approvedon(DEFAULT_APPROVEDON)
            .checkerremarks(DEFAULT_CHECKERREMARKS)
            .deletedby(DEFAULT_DELETEDBY)
            .deletedon(DEFAULT_DELETEDON)
            .deleteremarks(DEFAULT_DELETEREMARKS)
            .deleted(DEFAULT_DELETED)
            .declined(DEFAULT_DECLINED)
            .declineddon(DEFAULT_DECLINEDDON)
            .declinedby(DEFAULT_DECLINEDBY)
            .sessionid(DEFAULT_SESSIONID)
            .reworked(DEFAULT_REWORKED)
            .reworkedby(DEFAULT_REWORKEDBY)
            .reworkedon(DEFAULT_REWORKEDON)
            .district(DEFAULT_DISTRICT)
            .region(DEFAULT_REGION)
            .regionname(DEFAULT_REGIONNAME)
            .reporting(DEFAULT_REPORTING);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branches createUpdatedEntity() {
        return new Branches()
            .branchname(UPDATED_BRANCHNAME)
            .branchcode(UPDATED_BRANCHCODE)
            .approved(UPDATED_APPROVED)
            .email(UPDATED_EMAIL)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .location(UPDATED_LOCATION)
            .contactperson(UPDATED_CONTACTPERSON)
            .remarks(UPDATED_REMARKS)
            .createdby(UPDATED_CREATEDBY)
            .createdon(UPDATED_CREATEDON)
            .approvedby(UPDATED_APPROVEDBY)
            .approvedon(UPDATED_APPROVEDON)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .deletedby(UPDATED_DELETEDBY)
            .deletedon(UPDATED_DELETEDON)
            .deleteremarks(UPDATED_DELETEREMARKS)
            .deleted(UPDATED_DELETED)
            .declined(UPDATED_DECLINED)
            .declineddon(UPDATED_DECLINEDDON)
            .declinedby(UPDATED_DECLINEDBY)
            .sessionid(UPDATED_SESSIONID)
            .reworked(UPDATED_REWORKED)
            .reworkedby(UPDATED_REWORKEDBY)
            .reworkedon(UPDATED_REWORKEDON)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION)
            .regionname(UPDATED_REGIONNAME)
            .reporting(UPDATED_REPORTING);
    }

    @BeforeEach
    public void initTest() {
        branches = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBranches != null) {
            branchesRepository.delete(insertedBranches);
            insertedBranches = null;
        }
    }

    @Test
    @Transactional
    void createBranches() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Branches
        var returnedBranches = om.readValue(
            restBranchesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Branches.class
        );

        // Validate the Branches in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBranchesUpdatableFieldsEquals(returnedBranches, getPersistedBranches(returnedBranches));

        insertedBranches = returnedBranches;
    }

    @Test
    @Transactional
    void createBranchesWithExistingId() throws Exception {
        // Create the Branches with an existing ID
        branches.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches)))
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBranchcodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        branches.setBranchcode(null);

        // Create the Branches, which fails.

        restBranchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLocationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        branches.setLocation(null);

        // Create the Branches, which fails.

        restBranchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBranches() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        // Get all the branchesList
        restBranchesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branches.getId().intValue())))
            .andExpect(jsonPath("$.[*].branchname").value(hasItem(DEFAULT_BRANCHNAME)))
            .andExpect(jsonPath("$.[*].branchcode").value(hasItem(DEFAULT_BRANCHCODE)))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].contactperson").value(hasItem(DEFAULT_CONTACTPERSON)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].createdon").value(hasItem(DEFAULT_CREATEDON.toString())))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].approvedon").value(hasItem(DEFAULT_APPROVEDON)))
            .andExpect(jsonPath("$.[*].checkerremarks").value(hasItem(DEFAULT_CHECKERREMARKS)))
            .andExpect(jsonPath("$.[*].deletedby").value(hasItem(DEFAULT_DELETEDBY)))
            .andExpect(jsonPath("$.[*].deletedon").value(hasItem(DEFAULT_DELETEDON.toString())))
            .andExpect(jsonPath("$.[*].deleteremarks").value(hasItem(DEFAULT_DELETEREMARKS)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.intValue())))
            .andExpect(jsonPath("$.[*].declined").value(hasItem(DEFAULT_DECLINED.intValue())))
            .andExpect(jsonPath("$.[*].declineddon").value(hasItem(DEFAULT_DECLINEDDON)))
            .andExpect(jsonPath("$.[*].declinedby").value(hasItem(DEFAULT_DECLINEDBY)))
            .andExpect(jsonPath("$.[*].sessionid").value(hasItem(DEFAULT_SESSIONID)))
            .andExpect(jsonPath("$.[*].reworked").value(hasItem(DEFAULT_REWORKED.intValue())))
            .andExpect(jsonPath("$.[*].reworkedby").value(hasItem(DEFAULT_REWORKEDBY)))
            .andExpect(jsonPath("$.[*].reworkedon").value(hasItem(DEFAULT_REWORKEDON.toString())))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].regionname").value(hasItem(DEFAULT_REGIONNAME)))
            .andExpect(jsonPath("$.[*].reporting").value(hasItem(DEFAULT_REPORTING.intValue())));
    }

    @Test
    @Transactional
    void getBranches() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        // Get the branches
        restBranchesMockMvc
            .perform(get(ENTITY_API_URL_ID, branches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branches.getId().intValue()))
            .andExpect(jsonPath("$.branchname").value(DEFAULT_BRANCHNAME))
            .andExpect(jsonPath("$.branchcode").value(DEFAULT_BRANCHCODE))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.contactperson").value(DEFAULT_CONTACTPERSON))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.createdon").value(DEFAULT_CREATEDON.toString()))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.approvedon").value(DEFAULT_APPROVEDON))
            .andExpect(jsonPath("$.checkerremarks").value(DEFAULT_CHECKERREMARKS))
            .andExpect(jsonPath("$.deletedby").value(DEFAULT_DELETEDBY))
            .andExpect(jsonPath("$.deletedon").value(DEFAULT_DELETEDON.toString()))
            .andExpect(jsonPath("$.deleteremarks").value(DEFAULT_DELETEREMARKS))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.intValue()))
            .andExpect(jsonPath("$.declined").value(DEFAULT_DECLINED.intValue()))
            .andExpect(jsonPath("$.declineddon").value(DEFAULT_DECLINEDDON))
            .andExpect(jsonPath("$.declinedby").value(DEFAULT_DECLINEDBY))
            .andExpect(jsonPath("$.sessionid").value(DEFAULT_SESSIONID))
            .andExpect(jsonPath("$.reworked").value(DEFAULT_REWORKED.intValue()))
            .andExpect(jsonPath("$.reworkedby").value(DEFAULT_REWORKEDBY))
            .andExpect(jsonPath("$.reworkedon").value(DEFAULT_REWORKEDON.toString()))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.regionname").value(DEFAULT_REGIONNAME))
            .andExpect(jsonPath("$.reporting").value(DEFAULT_REPORTING.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingBranches() throws Exception {
        // Get the branches
        restBranchesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranches() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the branches
        Branches updatedBranches = branchesRepository.findById(branches.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBranches are not directly saved in db
        em.detach(updatedBranches);
        updatedBranches
            .branchname(UPDATED_BRANCHNAME)
            .branchcode(UPDATED_BRANCHCODE)
            .approved(UPDATED_APPROVED)
            .email(UPDATED_EMAIL)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .location(UPDATED_LOCATION)
            .contactperson(UPDATED_CONTACTPERSON)
            .remarks(UPDATED_REMARKS)
            .createdby(UPDATED_CREATEDBY)
            .createdon(UPDATED_CREATEDON)
            .approvedby(UPDATED_APPROVEDBY)
            .approvedon(UPDATED_APPROVEDON)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .deletedby(UPDATED_DELETEDBY)
            .deletedon(UPDATED_DELETEDON)
            .deleteremarks(UPDATED_DELETEREMARKS)
            .deleted(UPDATED_DELETED)
            .declined(UPDATED_DECLINED)
            .declineddon(UPDATED_DECLINEDDON)
            .declinedby(UPDATED_DECLINEDBY)
            .sessionid(UPDATED_SESSIONID)
            .reworked(UPDATED_REWORKED)
            .reworkedby(UPDATED_REWORKEDBY)
            .reworkedon(UPDATED_REWORKEDON)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION)
            .regionname(UPDATED_REGIONNAME)
            .reporting(UPDATED_REPORTING);

        restBranchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBranches.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBranches))
            )
            .andExpect(status().isOk());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBranchesToMatchAllProperties(updatedBranches);
    }

    @Test
    @Transactional
    void putNonExistingBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branches.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(branches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(branches)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchesWithPatch() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the branches using partial update
        Branches partialUpdatedBranches = new Branches();
        partialUpdatedBranches.setId(branches.getId());

        partialUpdatedBranches
            .branchname(UPDATED_BRANCHNAME)
            .branchcode(UPDATED_BRANCHCODE)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .location(UPDATED_LOCATION)
            .contactperson(UPDATED_CONTACTPERSON)
            .createdby(UPDATED_CREATEDBY)
            .createdon(UPDATED_CREATEDON)
            .approvedby(UPDATED_APPROVEDBY)
            .approvedon(UPDATED_APPROVEDON)
            .deletedby(UPDATED_DELETEDBY)
            .deletedon(UPDATED_DELETEDON)
            .deleteremarks(UPDATED_DELETEREMARKS)
            .declineddon(UPDATED_DECLINEDDON)
            .sessionid(UPDATED_SESSIONID)
            .reworked(UPDATED_REWORKED)
            .reworkedby(UPDATED_REWORKEDBY)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION);

        restBranchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBranches))
            )
            .andExpect(status().isOk());

        // Validate the Branches in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBranchesUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedBranches, branches), getPersistedBranches(branches));
    }

    @Test
    @Transactional
    void fullUpdateBranchesWithPatch() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the branches using partial update
        Branches partialUpdatedBranches = new Branches();
        partialUpdatedBranches.setId(branches.getId());

        partialUpdatedBranches
            .branchname(UPDATED_BRANCHNAME)
            .branchcode(UPDATED_BRANCHCODE)
            .approved(UPDATED_APPROVED)
            .email(UPDATED_EMAIL)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .location(UPDATED_LOCATION)
            .contactperson(UPDATED_CONTACTPERSON)
            .remarks(UPDATED_REMARKS)
            .createdby(UPDATED_CREATEDBY)
            .createdon(UPDATED_CREATEDON)
            .approvedby(UPDATED_APPROVEDBY)
            .approvedon(UPDATED_APPROVEDON)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .deletedby(UPDATED_DELETEDBY)
            .deletedon(UPDATED_DELETEDON)
            .deleteremarks(UPDATED_DELETEREMARKS)
            .deleted(UPDATED_DELETED)
            .declined(UPDATED_DECLINED)
            .declineddon(UPDATED_DECLINEDDON)
            .declinedby(UPDATED_DECLINEDBY)
            .sessionid(UPDATED_SESSIONID)
            .reworked(UPDATED_REWORKED)
            .reworkedby(UPDATED_REWORKEDBY)
            .reworkedon(UPDATED_REWORKEDON)
            .district(UPDATED_DISTRICT)
            .region(UPDATED_REGION)
            .regionname(UPDATED_REGIONNAME)
            .reporting(UPDATED_REPORTING);

        restBranchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBranches))
            )
            .andExpect(status().isOk());

        // Validate the Branches in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBranchesUpdatableFieldsEquals(partialUpdatedBranches, getPersistedBranches(partialUpdatedBranches));
    }

    @Test
    @Transactional
    void patchNonExistingBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(branches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(branches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        branches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(branches)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Branches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranches() throws Exception {
        // Initialize the database
        insertedBranches = branchesRepository.saveAndFlush(branches);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the branches
        restBranchesMockMvc
            .perform(delete(ENTITY_API_URL_ID, branches.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return branchesRepository.count();
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

    protected Branches getPersistedBranches(Branches branches) {
        return branchesRepository.findById(branches.getId()).orElseThrow();
    }

    protected void assertPersistedBranchesToMatchAllProperties(Branches expectedBranches) {
        assertBranchesAllPropertiesEquals(expectedBranches, getPersistedBranches(expectedBranches));
    }

    protected void assertPersistedBranchesToMatchUpdatableProperties(Branches expectedBranches) {
        assertBranchesAllUpdatablePropertiesEquals(expectedBranches, getPersistedBranches(expectedBranches));
    }
}
