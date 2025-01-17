package com.istl.app.web.rest;

import static com.istl.app.domain.BILLERSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.BILLERS;
import com.istl.app.repository.BILLERSRepository;
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
 * Integration tests for the {@link BILLERSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BILLERSResourceIT {

    private static final String DEFAULT_B_ILLERID = "AAAAAAAAAA";
    private static final String UPDATED_B_ILLERID = "BBBBBBBBBB";

    private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_B_ILLERCOLLECTIONACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_B_ILLERCOLLECTIONACCOUNT = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final Long DEFAULT_A_PPROVED = 1L;
    private static final Long UPDATED_A_PPROVED = 2L;

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_HARGABLEPRODUCTID = "AAAAAAAAAA";
    private static final String UPDATED_C_HARGABLEPRODUCTID = "BBBBBBBBBB";

    private static final String DEFAULT_N_ONCHARGABLEPRODUCTID = "AAAAAAAAAA";
    private static final String UPDATED_N_ONCHARGABLEPRODUCTID = "BBBBBBBBBB";

    private static final String DEFAULT_U_SDBILLERCOLLECTIONACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_U_SDBILLERCOLLECTIONACCOUNT = "BBBBBBBBBB";

    private static final Long DEFAULT_E_NABLEDUPLICATECHECK = 1L;
    private static final Long UPDATED_E_NABLEDUPLICATECHECK = 2L;

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_S_ESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_S_ESSIONID = "BBBBBBBBBB";

    private static final String DEFAULT_R_EWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EWORKBY = "BBBBBBBBBB";

    private static final Long DEFAULT_S_TATUS = 1L;
    private static final Long UPDATED_S_TATUS = 2L;

    private static final Long DEFAULT_A_CTIVE = 1L;
    private static final Long UPDATED_A_CTIVE = 2L;

    private static final Long DEFAULT_R_EWORK = 1L;
    private static final Long UPDATED_R_EWORK = 2L;

    private static final String ENTITY_API_URL = "/api/billers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BILLERSRepository bILLERSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBILLERSMockMvc;

    private BILLERS bILLERS;

    private BILLERS insertedBILLERS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BILLERS createEntity() {
        return new BILLERS()
            .bILLERID(DEFAULT_B_ILLERID)
            .dESCRIPTION(DEFAULT_D_ESCRIPTION)
            .bILLERCOLLECTIONACCOUNT(DEFAULT_B_ILLERCOLLECTIONACCOUNT)
            .dATECREATED(DEFAULT_D_ATECREATED)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .aPPROVED(DEFAULT_A_PPROVED)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDDATE(DEFAULT_A_PPROVEDDATE)
            .cHARGABLEPRODUCTID(DEFAULT_C_HARGABLEPRODUCTID)
            .nONCHARGABLEPRODUCTID(DEFAULT_N_ONCHARGABLEPRODUCTID)
            .uSDBILLERCOLLECTIONACCOUNT(DEFAULT_U_SDBILLERCOLLECTIONACCOUNT)
            .eNABLEDUPLICATECHECK(DEFAULT_E_NABLEDUPLICATECHECK)
            .rEMARKS(DEFAULT_R_EMARKS)
            .sESSIONID(DEFAULT_S_ESSIONID)
            .rEWORKBY(DEFAULT_R_EWORKBY)
            .sTATUS(DEFAULT_S_TATUS)
            .aCTIVE(DEFAULT_A_CTIVE)
            .rEWORK(DEFAULT_R_EWORK);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BILLERS createUpdatedEntity() {
        return new BILLERS()
            .bILLERID(UPDATED_B_ILLERID)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .bILLERCOLLECTIONACCOUNT(UPDATED_B_ILLERCOLLECTIONACCOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .cHARGABLEPRODUCTID(UPDATED_C_HARGABLEPRODUCTID)
            .nONCHARGABLEPRODUCTID(UPDATED_N_ONCHARGABLEPRODUCTID)
            .uSDBILLERCOLLECTIONACCOUNT(UPDATED_U_SDBILLERCOLLECTIONACCOUNT)
            .eNABLEDUPLICATECHECK(UPDATED_E_NABLEDUPLICATECHECK)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sTATUS(UPDATED_S_TATUS)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEWORK(UPDATED_R_EWORK);
    }

    @BeforeEach
    public void initTest() {
        bILLERS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBILLERS != null) {
            bILLERSRepository.delete(insertedBILLERS);
            insertedBILLERS = null;
        }
    }

    @Test
    @Transactional
    void createBILLERS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BILLERS
        var returnedBILLERS = om.readValue(
            restBILLERSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BILLERS.class
        );

        // Validate the BILLERS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBILLERSUpdatableFieldsEquals(returnedBILLERS, getPersistedBILLERS(returnedBILLERS));

        insertedBILLERS = returnedBILLERS;
    }

    @Test
    @Transactional
    void createBILLERSWithExistingId() throws Exception {
        // Create the BILLERS with an existing ID
        bILLERS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBILLERSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isBadRequest());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkbILLERIDIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        bILLERS.setbILLERID(null);

        // Create the BILLERS, which fails.

        restBILLERSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkdESCRIPTIONIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        bILLERS.setdESCRIPTION(null);

        // Create the BILLERS, which fails.

        restBILLERSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBILLERS() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        // Get all the bILLERSList
        restBILLERSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bILLERS.getId().intValue())))
            .andExpect(jsonPath("$.[*].bILLERID").value(hasItem(DEFAULT_B_ILLERID)))
            .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)))
            .andExpect(jsonPath("$.[*].bILLERCOLLECTIONACCOUNT").value(hasItem(DEFAULT_B_ILLERCOLLECTIONACCOUNT)))
            .andExpect(jsonPath("$.[*].dATECREATED").value(hasItem(DEFAULT_D_ATECREATED.toString())))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED.intValue())))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDDATE").value(hasItem(DEFAULT_A_PPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].cHARGABLEPRODUCTID").value(hasItem(DEFAULT_C_HARGABLEPRODUCTID)))
            .andExpect(jsonPath("$.[*].nONCHARGABLEPRODUCTID").value(hasItem(DEFAULT_N_ONCHARGABLEPRODUCTID)))
            .andExpect(jsonPath("$.[*].uSDBILLERCOLLECTIONACCOUNT").value(hasItem(DEFAULT_U_SDBILLERCOLLECTIONACCOUNT)))
            .andExpect(jsonPath("$.[*].eNABLEDUPLICATECHECK").value(hasItem(DEFAULT_E_NABLEDUPLICATECHECK.intValue())))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].sESSIONID").value(hasItem(DEFAULT_S_ESSIONID)))
            .andExpect(jsonPath("$.[*].rEWORKBY").value(hasItem(DEFAULT_R_EWORKBY)))
            .andExpect(jsonPath("$.[*].sTATUS").value(hasItem(DEFAULT_S_TATUS.intValue())))
            .andExpect(jsonPath("$.[*].aCTIVE").value(hasItem(DEFAULT_A_CTIVE.intValue())))
            .andExpect(jsonPath("$.[*].rEWORK").value(hasItem(DEFAULT_R_EWORK.intValue())));
    }

    @Test
    @Transactional
    void getBILLERS() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        // Get the bILLERS
        restBILLERSMockMvc
            .perform(get(ENTITY_API_URL_ID, bILLERS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bILLERS.getId().intValue()))
            .andExpect(jsonPath("$.bILLERID").value(DEFAULT_B_ILLERID))
            .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION))
            .andExpect(jsonPath("$.bILLERCOLLECTIONACCOUNT").value(DEFAULT_B_ILLERCOLLECTIONACCOUNT))
            .andExpect(jsonPath("$.dATECREATED").value(DEFAULT_D_ATECREATED.toString()))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED.intValue()))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDDATE").value(DEFAULT_A_PPROVEDDATE.toString()))
            .andExpect(jsonPath("$.cHARGABLEPRODUCTID").value(DEFAULT_C_HARGABLEPRODUCTID))
            .andExpect(jsonPath("$.nONCHARGABLEPRODUCTID").value(DEFAULT_N_ONCHARGABLEPRODUCTID))
            .andExpect(jsonPath("$.uSDBILLERCOLLECTIONACCOUNT").value(DEFAULT_U_SDBILLERCOLLECTIONACCOUNT))
            .andExpect(jsonPath("$.eNABLEDUPLICATECHECK").value(DEFAULT_E_NABLEDUPLICATECHECK.intValue()))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.sESSIONID").value(DEFAULT_S_ESSIONID))
            .andExpect(jsonPath("$.rEWORKBY").value(DEFAULT_R_EWORKBY))
            .andExpect(jsonPath("$.sTATUS").value(DEFAULT_S_TATUS.intValue()))
            .andExpect(jsonPath("$.aCTIVE").value(DEFAULT_A_CTIVE.intValue()))
            .andExpect(jsonPath("$.rEWORK").value(DEFAULT_R_EWORK.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingBILLERS() throws Exception {
        // Get the bILLERS
        restBILLERSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBILLERS() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bILLERS
        BILLERS updatedBILLERS = bILLERSRepository.findById(bILLERS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBILLERS are not directly saved in db
        em.detach(updatedBILLERS);
        updatedBILLERS
            .bILLERID(UPDATED_B_ILLERID)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .bILLERCOLLECTIONACCOUNT(UPDATED_B_ILLERCOLLECTIONACCOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .cHARGABLEPRODUCTID(UPDATED_C_HARGABLEPRODUCTID)
            .nONCHARGABLEPRODUCTID(UPDATED_N_ONCHARGABLEPRODUCTID)
            .uSDBILLERCOLLECTIONACCOUNT(UPDATED_U_SDBILLERCOLLECTIONACCOUNT)
            .eNABLEDUPLICATECHECK(UPDATED_E_NABLEDUPLICATECHECK)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sTATUS(UPDATED_S_TATUS)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEWORK(UPDATED_R_EWORK);

        restBILLERSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBILLERS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBILLERS))
            )
            .andExpect(status().isOk());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBILLERSToMatchAllProperties(updatedBILLERS);
    }

    @Test
    @Transactional
    void putNonExistingBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(put(ENTITY_API_URL_ID, bILLERS.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isBadRequest());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bILLERS))
            )
            .andExpect(status().isBadRequest());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBILLERSWithPatch() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bILLERS using partial update
        BILLERS partialUpdatedBILLERS = new BILLERS();
        partialUpdatedBILLERS.setId(bILLERS.getId());

        partialUpdatedBILLERS
            .bILLERID(UPDATED_B_ILLERID)
            .bILLERCOLLECTIONACCOUNT(UPDATED_B_ILLERCOLLECTIONACCOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .nONCHARGABLEPRODUCTID(UPDATED_N_ONCHARGABLEPRODUCTID)
            .uSDBILLERCOLLECTIONACCOUNT(UPDATED_U_SDBILLERCOLLECTIONACCOUNT)
            .eNABLEDUPLICATECHECK(UPDATED_E_NABLEDUPLICATECHECK)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .aCTIVE(UPDATED_A_CTIVE);

        restBILLERSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBILLERS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBILLERS))
            )
            .andExpect(status().isOk());

        // Validate the BILLERS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBILLERSUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedBILLERS, bILLERS), getPersistedBILLERS(bILLERS));
    }

    @Test
    @Transactional
    void fullUpdateBILLERSWithPatch() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bILLERS using partial update
        BILLERS partialUpdatedBILLERS = new BILLERS();
        partialUpdatedBILLERS.setId(bILLERS.getId());

        partialUpdatedBILLERS
            .bILLERID(UPDATED_B_ILLERID)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .bILLERCOLLECTIONACCOUNT(UPDATED_B_ILLERCOLLECTIONACCOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .cHARGABLEPRODUCTID(UPDATED_C_HARGABLEPRODUCTID)
            .nONCHARGABLEPRODUCTID(UPDATED_N_ONCHARGABLEPRODUCTID)
            .uSDBILLERCOLLECTIONACCOUNT(UPDATED_U_SDBILLERCOLLECTIONACCOUNT)
            .eNABLEDUPLICATECHECK(UPDATED_E_NABLEDUPLICATECHECK)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sTATUS(UPDATED_S_TATUS)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEWORK(UPDATED_R_EWORK);

        restBILLERSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBILLERS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBILLERS))
            )
            .andExpect(status().isOk());

        // Validate the BILLERS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBILLERSUpdatableFieldsEquals(partialUpdatedBILLERS, getPersistedBILLERS(partialUpdatedBILLERS));
    }

    @Test
    @Transactional
    void patchNonExistingBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bILLERS.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bILLERS))
            )
            .andExpect(status().isBadRequest());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bILLERS))
            )
            .andExpect(status().isBadRequest());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBILLERS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bILLERS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBILLERSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bILLERS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BILLERS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBILLERS() throws Exception {
        // Initialize the database
        insertedBILLERS = bILLERSRepository.saveAndFlush(bILLERS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the bILLERS
        restBILLERSMockMvc
            .perform(delete(ENTITY_API_URL_ID, bILLERS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return bILLERSRepository.count();
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

    protected BILLERS getPersistedBILLERS(BILLERS bILLERS) {
        return bILLERSRepository.findById(bILLERS.getId()).orElseThrow();
    }

    protected void assertPersistedBILLERSToMatchAllProperties(BILLERS expectedBILLERS) {
        assertBILLERSAllPropertiesEquals(expectedBILLERS, getPersistedBILLERS(expectedBILLERS));
    }

    protected void assertPersistedBILLERSToMatchUpdatableProperties(BILLERS expectedBILLERS) {
        assertBILLERSAllUpdatablePropertiesEquals(expectedBILLERS, getPersistedBILLERS(expectedBILLERS));
    }
}
