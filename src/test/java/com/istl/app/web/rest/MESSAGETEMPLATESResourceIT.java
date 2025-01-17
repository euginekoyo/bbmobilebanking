package com.istl.app.web.rest;

import static com.istl.app.domain.MESSAGETEMPLATESAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.MESSAGETEMPLATES;
import com.istl.app.repository.MESSAGETEMPLATESRepository;
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
 * Integration tests for the {@link MESSAGETEMPLATESResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MESSAGETEMPLATESResourceIT {

    private static final String DEFAULT_M_ESSAGETYPE = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGEENGLISH = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGEENGLISH = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGESOMALI = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGESOMALI = "BBBBBBBBBB";

    private static final Instant DEFAULT_C_REATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_REATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/messagetemplates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MESSAGETEMPLATESRepository mESSAGETEMPLATESRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMESSAGETEMPLATESMockMvc;

    private MESSAGETEMPLATES mESSAGETEMPLATES;

    private MESSAGETEMPLATES insertedMESSAGETEMPLATES;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MESSAGETEMPLATES createEntity() {
        return new MESSAGETEMPLATES()
            .mESSAGETYPE(DEFAULT_M_ESSAGETYPE)
            .dESCRIPTION(DEFAULT_D_ESCRIPTION)
            .mESSAGEENGLISH(DEFAULT_M_ESSAGEENGLISH)
            .mESSAGESOMALI(DEFAULT_M_ESSAGESOMALI)
            .cREATEDON(DEFAULT_C_REATEDON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MESSAGETEMPLATES createUpdatedEntity() {
        return new MESSAGETEMPLATES()
            .mESSAGETYPE(UPDATED_M_ESSAGETYPE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .mESSAGEENGLISH(UPDATED_M_ESSAGEENGLISH)
            .mESSAGESOMALI(UPDATED_M_ESSAGESOMALI)
            .cREATEDON(UPDATED_C_REATEDON);
    }

    @BeforeEach
    public void initTest() {
        mESSAGETEMPLATES = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMESSAGETEMPLATES != null) {
            mESSAGETEMPLATESRepository.delete(insertedMESSAGETEMPLATES);
            insertedMESSAGETEMPLATES = null;
        }
    }

    @Test
    @Transactional
    void createMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MESSAGETEMPLATES
        var returnedMESSAGETEMPLATES = om.readValue(
            restMESSAGETEMPLATESMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGETEMPLATES)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MESSAGETEMPLATES.class
        );

        // Validate the MESSAGETEMPLATES in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMESSAGETEMPLATESUpdatableFieldsEquals(returnedMESSAGETEMPLATES, getPersistedMESSAGETEMPLATES(returnedMESSAGETEMPLATES));

        insertedMESSAGETEMPLATES = returnedMESSAGETEMPLATES;
    }

    @Test
    @Transactional
    void createMESSAGETEMPLATESWithExistingId() throws Exception {
        // Create the MESSAGETEMPLATES with an existing ID
        mESSAGETEMPLATES.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMESSAGETEMPLATESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGETEMPLATES)))
            .andExpect(status().isBadRequest());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMESSAGETEMPLATES() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        // Get all the mESSAGETEMPLATESList
        restMESSAGETEMPLATESMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mESSAGETEMPLATES.getId().intValue())))
            .andExpect(jsonPath("$.[*].mESSAGETYPE").value(hasItem(DEFAULT_M_ESSAGETYPE)))
            .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)))
            .andExpect(jsonPath("$.[*].mESSAGEENGLISH").value(hasItem(DEFAULT_M_ESSAGEENGLISH)))
            .andExpect(jsonPath("$.[*].mESSAGESOMALI").value(hasItem(DEFAULT_M_ESSAGESOMALI)))
            .andExpect(jsonPath("$.[*].cREATEDON").value(hasItem(DEFAULT_C_REATEDON.toString())));
    }

    @Test
    @Transactional
    void getMESSAGETEMPLATES() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        // Get the mESSAGETEMPLATES
        restMESSAGETEMPLATESMockMvc
            .perform(get(ENTITY_API_URL_ID, mESSAGETEMPLATES.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mESSAGETEMPLATES.getId().intValue()))
            .andExpect(jsonPath("$.mESSAGETYPE").value(DEFAULT_M_ESSAGETYPE))
            .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION))
            .andExpect(jsonPath("$.mESSAGEENGLISH").value(DEFAULT_M_ESSAGEENGLISH))
            .andExpect(jsonPath("$.mESSAGESOMALI").value(DEFAULT_M_ESSAGESOMALI))
            .andExpect(jsonPath("$.cREATEDON").value(DEFAULT_C_REATEDON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMESSAGETEMPLATES() throws Exception {
        // Get the mESSAGETEMPLATES
        restMESSAGETEMPLATESMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMESSAGETEMPLATES() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGETEMPLATES
        MESSAGETEMPLATES updatedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.findById(mESSAGETEMPLATES.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMESSAGETEMPLATES are not directly saved in db
        em.detach(updatedMESSAGETEMPLATES);
        updatedMESSAGETEMPLATES
            .mESSAGETYPE(UPDATED_M_ESSAGETYPE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .mESSAGEENGLISH(UPDATED_M_ESSAGEENGLISH)
            .mESSAGESOMALI(UPDATED_M_ESSAGESOMALI)
            .cREATEDON(UPDATED_C_REATEDON);

        restMESSAGETEMPLATESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMESSAGETEMPLATES.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMESSAGETEMPLATES))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMESSAGETEMPLATESToMatchAllProperties(updatedMESSAGETEMPLATES);
    }

    @Test
    @Transactional
    void putNonExistingMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mESSAGETEMPLATES.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mESSAGETEMPLATES))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mESSAGETEMPLATES))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGETEMPLATES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMESSAGETEMPLATESWithPatch() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGETEMPLATES using partial update
        MESSAGETEMPLATES partialUpdatedMESSAGETEMPLATES = new MESSAGETEMPLATES();
        partialUpdatedMESSAGETEMPLATES.setId(mESSAGETEMPLATES.getId());

        partialUpdatedMESSAGETEMPLATES.mESSAGESOMALI(UPDATED_M_ESSAGESOMALI).cREATEDON(UPDATED_C_REATEDON);

        restMESSAGETEMPLATESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMESSAGETEMPLATES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMESSAGETEMPLATES))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGETEMPLATES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMESSAGETEMPLATESUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMESSAGETEMPLATES, mESSAGETEMPLATES),
            getPersistedMESSAGETEMPLATES(mESSAGETEMPLATES)
        );
    }

    @Test
    @Transactional
    void fullUpdateMESSAGETEMPLATESWithPatch() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGETEMPLATES using partial update
        MESSAGETEMPLATES partialUpdatedMESSAGETEMPLATES = new MESSAGETEMPLATES();
        partialUpdatedMESSAGETEMPLATES.setId(mESSAGETEMPLATES.getId());

        partialUpdatedMESSAGETEMPLATES
            .mESSAGETYPE(UPDATED_M_ESSAGETYPE)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .mESSAGEENGLISH(UPDATED_M_ESSAGEENGLISH)
            .mESSAGESOMALI(UPDATED_M_ESSAGESOMALI)
            .cREATEDON(UPDATED_C_REATEDON);

        restMESSAGETEMPLATESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMESSAGETEMPLATES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMESSAGETEMPLATES))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGETEMPLATES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMESSAGETEMPLATESUpdatableFieldsEquals(
            partialUpdatedMESSAGETEMPLATES,
            getPersistedMESSAGETEMPLATES(partialUpdatedMESSAGETEMPLATES)
        );
    }

    @Test
    @Transactional
    void patchNonExistingMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mESSAGETEMPLATES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mESSAGETEMPLATES))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mESSAGETEMPLATES))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMESSAGETEMPLATES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGETEMPLATES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGETEMPLATESMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(mESSAGETEMPLATES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MESSAGETEMPLATES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMESSAGETEMPLATES() throws Exception {
        // Initialize the database
        insertedMESSAGETEMPLATES = mESSAGETEMPLATESRepository.saveAndFlush(mESSAGETEMPLATES);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the mESSAGETEMPLATES
        restMESSAGETEMPLATESMockMvc
            .perform(delete(ENTITY_API_URL_ID, mESSAGETEMPLATES.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return mESSAGETEMPLATESRepository.count();
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

    protected MESSAGETEMPLATES getPersistedMESSAGETEMPLATES(MESSAGETEMPLATES mESSAGETEMPLATES) {
        return mESSAGETEMPLATESRepository.findById(mESSAGETEMPLATES.getId()).orElseThrow();
    }

    protected void assertPersistedMESSAGETEMPLATESToMatchAllProperties(MESSAGETEMPLATES expectedMESSAGETEMPLATES) {
        assertMESSAGETEMPLATESAllPropertiesEquals(expectedMESSAGETEMPLATES, getPersistedMESSAGETEMPLATES(expectedMESSAGETEMPLATES));
    }

    protected void assertPersistedMESSAGETEMPLATESToMatchUpdatableProperties(MESSAGETEMPLATES expectedMESSAGETEMPLATES) {
        assertMESSAGETEMPLATESAllUpdatablePropertiesEquals(
            expectedMESSAGETEMPLATES,
            getPersistedMESSAGETEMPLATES(expectedMESSAGETEMPLATES)
        );
    }
}
