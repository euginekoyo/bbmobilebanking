package com.istl.app.web.rest;

import static com.istl.app.domain.RANGEAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.RANGE;
import com.istl.app.repository.RANGERepository;
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
 * Integration tests for the {@link RANGEResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RANGEResourceIT {

    private static final Long DEFAULT_R_ANGEFROM = 1L;
    private static final Long UPDATED_R_ANGEFROM = 2L;

    private static final Long DEFAULT_R_ANGETO = 1L;
    private static final Long UPDATED_R_ANGETO = 2L;

    private static final Double DEFAULT_A_MOUNT = 1D;
    private static final Double UPDATED_A_MOUNT = 2D;

    private static final String DEFAULT_T_XNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_T_XNTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_T_XNCODE = "AAAAAAAAAA";
    private static final String UPDATED_T_XNCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_C_HARGEID = 1L;
    private static final Long UPDATED_C_HARGEID = 2L;

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ranges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RANGERepository rANGERepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRANGEMockMvc;

    private RANGE rANGE;

    private RANGE insertedRANGE;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RANGE createEntity() {
        return new RANGE()
            .rANGEFROM(DEFAULT_R_ANGEFROM)
            .rANGETO(DEFAULT_R_ANGETO)
            .aMOUNT(DEFAULT_A_MOUNT)
            .tXNTYPE(DEFAULT_T_XNTYPE)
            .tXNCODE(DEFAULT_T_XNCODE)
            .cHARGEID(DEFAULT_C_HARGEID)
            .cHANNEL(DEFAULT_C_HANNEL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RANGE createUpdatedEntity() {
        return new RANGE()
            .rANGEFROM(UPDATED_R_ANGEFROM)
            .rANGETO(UPDATED_R_ANGETO)
            .aMOUNT(UPDATED_A_MOUNT)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .tXNCODE(UPDATED_T_XNCODE)
            .cHARGEID(UPDATED_C_HARGEID)
            .cHANNEL(UPDATED_C_HANNEL);
    }

    @BeforeEach
    public void initTest() {
        rANGE = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedRANGE != null) {
            rANGERepository.delete(insertedRANGE);
            insertedRANGE = null;
        }
    }

    @Test
    @Transactional
    void createRANGE() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RANGE
        var returnedRANGE = om.readValue(
            restRANGEMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rANGE)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RANGE.class
        );

        // Validate the RANGE in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRANGEUpdatableFieldsEquals(returnedRANGE, getPersistedRANGE(returnedRANGE));

        insertedRANGE = returnedRANGE;
    }

    @Test
    @Transactional
    void createRANGEWithExistingId() throws Exception {
        // Create the RANGE with an existing ID
        rANGE.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRANGEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rANGE)))
            .andExpect(status().isBadRequest());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRANGES() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        // Get all the rANGEList
        restRANGEMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rANGE.getId().intValue())))
            .andExpect(jsonPath("$.[*].rANGEFROM").value(hasItem(DEFAULT_R_ANGEFROM.intValue())))
            .andExpect(jsonPath("$.[*].rANGETO").value(hasItem(DEFAULT_R_ANGETO.intValue())))
            .andExpect(jsonPath("$.[*].aMOUNT").value(hasItem(DEFAULT_A_MOUNT)))
            .andExpect(jsonPath("$.[*].tXNTYPE").value(hasItem(DEFAULT_T_XNTYPE)))
            .andExpect(jsonPath("$.[*].tXNCODE").value(hasItem(DEFAULT_T_XNCODE)))
            .andExpect(jsonPath("$.[*].cHARGEID").value(hasItem(DEFAULT_C_HARGEID.intValue())))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)));
    }

    @Test
    @Transactional
    void getRANGE() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        // Get the rANGE
        restRANGEMockMvc
            .perform(get(ENTITY_API_URL_ID, rANGE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rANGE.getId().intValue()))
            .andExpect(jsonPath("$.rANGEFROM").value(DEFAULT_R_ANGEFROM.intValue()))
            .andExpect(jsonPath("$.rANGETO").value(DEFAULT_R_ANGETO.intValue()))
            .andExpect(jsonPath("$.aMOUNT").value(DEFAULT_A_MOUNT))
            .andExpect(jsonPath("$.tXNTYPE").value(DEFAULT_T_XNTYPE))
            .andExpect(jsonPath("$.tXNCODE").value(DEFAULT_T_XNCODE))
            .andExpect(jsonPath("$.cHARGEID").value(DEFAULT_C_HARGEID.intValue()))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL));
    }

    @Test
    @Transactional
    void getNonExistingRANGE() throws Exception {
        // Get the rANGE
        restRANGEMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRANGE() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rANGE
        RANGE updatedRANGE = rANGERepository.findById(rANGE.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRANGE are not directly saved in db
        em.detach(updatedRANGE);
        updatedRANGE
            .rANGEFROM(UPDATED_R_ANGEFROM)
            .rANGETO(UPDATED_R_ANGETO)
            .aMOUNT(UPDATED_A_MOUNT)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .tXNCODE(UPDATED_T_XNCODE)
            .cHARGEID(UPDATED_C_HARGEID)
            .cHANNEL(UPDATED_C_HANNEL);

        restRANGEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRANGE.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRANGE))
            )
            .andExpect(status().isOk());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRANGEToMatchAllProperties(updatedRANGE);
    }

    @Test
    @Transactional
    void putNonExistingRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(put(ENTITY_API_URL_ID, rANGE.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rANGE)))
            .andExpect(status().isBadRequest());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rANGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rANGE)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRANGEWithPatch() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rANGE using partial update
        RANGE partialUpdatedRANGE = new RANGE();
        partialUpdatedRANGE.setId(rANGE.getId());

        partialUpdatedRANGE.rANGEFROM(UPDATED_R_ANGEFROM).rANGETO(UPDATED_R_ANGETO).aMOUNT(UPDATED_A_MOUNT).tXNTYPE(UPDATED_T_XNTYPE);

        restRANGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRANGE.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRANGE))
            )
            .andExpect(status().isOk());

        // Validate the RANGE in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRANGEUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRANGE, rANGE), getPersistedRANGE(rANGE));
    }

    @Test
    @Transactional
    void fullUpdateRANGEWithPatch() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rANGE using partial update
        RANGE partialUpdatedRANGE = new RANGE();
        partialUpdatedRANGE.setId(rANGE.getId());

        partialUpdatedRANGE
            .rANGEFROM(UPDATED_R_ANGEFROM)
            .rANGETO(UPDATED_R_ANGETO)
            .aMOUNT(UPDATED_A_MOUNT)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .tXNCODE(UPDATED_T_XNCODE)
            .cHARGEID(UPDATED_C_HARGEID)
            .cHANNEL(UPDATED_C_HANNEL);

        restRANGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRANGE.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRANGE))
            )
            .andExpect(status().isOk());

        // Validate the RANGE in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRANGEUpdatableFieldsEquals(partialUpdatedRANGE, getPersistedRANGE(partialUpdatedRANGE));
    }

    @Test
    @Transactional
    void patchNonExistingRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rANGE.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rANGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rANGE))
            )
            .andExpect(status().isBadRequest());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRANGE() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rANGE.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRANGEMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rANGE)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RANGE in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRANGE() throws Exception {
        // Initialize the database
        insertedRANGE = rANGERepository.saveAndFlush(rANGE);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rANGE
        restRANGEMockMvc
            .perform(delete(ENTITY_API_URL_ID, rANGE.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rANGERepository.count();
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

    protected RANGE getPersistedRANGE(RANGE rANGE) {
        return rANGERepository.findById(rANGE.getId()).orElseThrow();
    }

    protected void assertPersistedRANGEToMatchAllProperties(RANGE expectedRANGE) {
        assertRANGEAllPropertiesEquals(expectedRANGE, getPersistedRANGE(expectedRANGE));
    }

    protected void assertPersistedRANGEToMatchUpdatableProperties(RANGE expectedRANGE) {
        assertRANGEAllUpdatablePropertiesEquals(expectedRANGE, getPersistedRANGE(expectedRANGE));
    }
}
