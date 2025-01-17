package com.istl.app.web.rest;

import static com.istl.app.domain.CHARGEAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CHARGE;
import com.istl.app.repository.CHARGERepository;
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
 * Integration tests for the {@link CHARGEResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CHARGEResourceIT {

    private static final String DEFAULT_T_XNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_T_XNTYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_F_EEMODE = 1L;
    private static final Long UPDATED_F_EEMODE = 2L;

    private static final Long DEFAULT_A_MOUNT = 1L;
    private static final Long UPDATED_A_MOUNT = 2L;

    private static final Instant DEFAULT_D_ATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVED = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVED = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_T_XNCODE = 1L;
    private static final Long UPDATED_T_XNCODE = 2L;

    private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/charges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CHARGERepository cHARGERepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCHARGEMockMvc;

    private CHARGE cHARGE;

    private CHARGE insertedCHARGE;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHARGE createEntity() {
        return new CHARGE()
            .tXNTYPE(DEFAULT_T_XNTYPE)
            .fEEMODE(DEFAULT_F_EEMODE)
            .aMOUNT(DEFAULT_A_MOUNT)
            .dATECREATED(DEFAULT_D_ATECREATED)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .aPPROVED(DEFAULT_A_PPROVED)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .cHANNEL(DEFAULT_C_HANNEL)
            .tXNCODE(DEFAULT_T_XNCODE)
            .dESCRIPTION(DEFAULT_D_ESCRIPTION)
            .aPPROVEDDATE(DEFAULT_A_PPROVEDDATE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHARGE createUpdatedEntity() {
        return new CHARGE()
            .tXNTYPE(UPDATED_T_XNTYPE)
            .fEEMODE(UPDATED_F_EEMODE)
            .aMOUNT(UPDATED_A_MOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cHANNEL(UPDATED_C_HANNEL)
            .tXNCODE(UPDATED_T_XNCODE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE);
    }

    @BeforeEach
    public void initTest() {
        cHARGE = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCHARGE != null) {
            cHARGERepository.delete(insertedCHARGE);
            insertedCHARGE = null;
        }
    }

    @Test
    @Transactional
    void createCHARGE() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CHARGE
        var returnedCHARGE = om.readValue(
            restCHARGEMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGE)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CHARGE.class
        );

        // Validate the CHARGE in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCHARGEUpdatableFieldsEquals(returnedCHARGE, getPersistedCHARGE(returnedCHARGE));

        insertedCHARGE = returnedCHARGE;
    }

    @Test
    @Transactional
    void createCHARGEWithExistingId() throws Exception {
        // Create the CHARGE with an existing ID
        cHARGE.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCHARGEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGE)))
            .andExpect(status().isBadRequest());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checktXNTYPEIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGE.settXNTYPE(null);

        // Create the CHARGE, which fails.

        restCHARGEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGE)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCHARGES() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        // Get all the cHARGEList
        restCHARGEMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cHARGE.getId().intValue())))
            .andExpect(jsonPath("$.[*].tXNTYPE").value(hasItem(DEFAULT_T_XNTYPE)))
            .andExpect(jsonPath("$.[*].fEEMODE").value(hasItem(DEFAULT_F_EEMODE.intValue())))
            .andExpect(jsonPath("$.[*].aMOUNT").value(hasItem(DEFAULT_A_MOUNT.intValue())))
            .andExpect(jsonPath("$.[*].dATECREATED").value(hasItem(DEFAULT_D_ATECREATED.toString())))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].tXNCODE").value(hasItem(DEFAULT_T_XNCODE.intValue())))
            .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)))
            .andExpect(jsonPath("$.[*].aPPROVEDDATE").value(hasItem(DEFAULT_A_PPROVEDDATE.toString())));
    }

    @Test
    @Transactional
    void getCHARGE() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        // Get the cHARGE
        restCHARGEMockMvc
            .perform(get(ENTITY_API_URL_ID, cHARGE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cHARGE.getId().intValue()))
            .andExpect(jsonPath("$.tXNTYPE").value(DEFAULT_T_XNTYPE))
            .andExpect(jsonPath("$.fEEMODE").value(DEFAULT_F_EEMODE.intValue()))
            .andExpect(jsonPath("$.aMOUNT").value(DEFAULT_A_MOUNT.intValue()))
            .andExpect(jsonPath("$.dATECREATED").value(DEFAULT_D_ATECREATED.toString()))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.tXNCODE").value(DEFAULT_T_XNCODE.intValue()))
            .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION))
            .andExpect(jsonPath("$.aPPROVEDDATE").value(DEFAULT_A_PPROVEDDATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCHARGE() throws Exception {
        // Get the cHARGE
        restCHARGEMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCHARGE() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGE
        CHARGE updatedCHARGE = cHARGERepository.findById(cHARGE.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCHARGE are not directly saved in db
        em.detach(updatedCHARGE);
        updatedCHARGE
            .tXNTYPE(UPDATED_T_XNTYPE)
            .fEEMODE(UPDATED_F_EEMODE)
            .aMOUNT(UPDATED_A_MOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cHANNEL(UPDATED_C_HANNEL)
            .tXNCODE(UPDATED_T_XNCODE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE);

        restCHARGEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCHARGE.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCHARGE))
            )
            .andExpect(status().isOk());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCHARGEToMatchAllProperties(updatedCHARGE);
    }

    @Test
    @Transactional
    void putNonExistingCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(put(ENTITY_API_URL_ID, cHARGE.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGE)))
            .andExpect(status().isBadRequest());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cHARGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGE)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCHARGEWithPatch() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGE using partial update
        CHARGE partialUpdatedCHARGE = new CHARGE();
        partialUpdatedCHARGE.setId(cHARGE.getId());

        partialUpdatedCHARGE
            .tXNTYPE(UPDATED_T_XNTYPE)
            .aMOUNT(UPDATED_A_MOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVED(UPDATED_A_PPROVED)
            .cHANNEL(UPDATED_C_HANNEL)
            .dESCRIPTION(UPDATED_D_ESCRIPTION);

        restCHARGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHARGE.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHARGE))
            )
            .andExpect(status().isOk());

        // Validate the CHARGE in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHARGEUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCHARGE, cHARGE), getPersistedCHARGE(cHARGE));
    }

    @Test
    @Transactional
    void fullUpdateCHARGEWithPatch() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGE using partial update
        CHARGE partialUpdatedCHARGE = new CHARGE();
        partialUpdatedCHARGE.setId(cHARGE.getId());

        partialUpdatedCHARGE
            .tXNTYPE(UPDATED_T_XNTYPE)
            .fEEMODE(UPDATED_F_EEMODE)
            .aMOUNT(UPDATED_A_MOUNT)
            .dATECREATED(UPDATED_D_ATECREATED)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cHANNEL(UPDATED_C_HANNEL)
            .tXNCODE(UPDATED_T_XNCODE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE);

        restCHARGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHARGE.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHARGE))
            )
            .andExpect(status().isOk());

        // Validate the CHARGE in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHARGEUpdatableFieldsEquals(partialUpdatedCHARGE, getPersistedCHARGE(partialUpdatedCHARGE));
    }

    @Test
    @Transactional
    void patchNonExistingCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cHARGE.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cHARGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cHARGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCHARGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGEMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cHARGE)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHARGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCHARGE() throws Exception {
        // Initialize the database
        insertedCHARGE = cHARGERepository.saveAndFlush(cHARGE);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cHARGE
        restCHARGEMockMvc
            .perform(delete(ENTITY_API_URL_ID, cHARGE.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cHARGERepository.count();
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

    protected CHARGE getPersistedCHARGE(CHARGE cHARGE) {
        return cHARGERepository.findById(cHARGE.getId()).orElseThrow();
    }

    protected void assertPersistedCHARGEToMatchAllProperties(CHARGE expectedCHARGE) {
        assertCHARGEAllPropertiesEquals(expectedCHARGE, getPersistedCHARGE(expectedCHARGE));
    }

    protected void assertPersistedCHARGEToMatchUpdatableProperties(CHARGE expectedCHARGE) {
        assertCHARGEAllUpdatablePropertiesEquals(expectedCHARGE, getPersistedCHARGE(expectedCHARGE));
    }
}
