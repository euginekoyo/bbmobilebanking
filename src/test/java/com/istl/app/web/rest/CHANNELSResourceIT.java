package com.istl.app.web.rest;

import static com.istl.app.domain.CHANNELSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CHANNELS;
import com.istl.app.repository.CHANNELSRepository;
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
 * Integration tests for the {@link CHANNELSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CHANNELSResourceIT {

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_B_IN = "AAAAAA";
    private static final String UPDATED_B_IN = "BBBBBB";

    private static final String ENTITY_API_URL = "/api/channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CHANNELSRepository cHANNELSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCHANNELSMockMvc;

    private CHANNELS cHANNELS;

    private CHANNELS insertedCHANNELS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHANNELS createEntity() {
        return new CHANNELS().cHANNEL(DEFAULT_C_HANNEL).dESCRIPTION(DEFAULT_D_ESCRIPTION).bIN(DEFAULT_B_IN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHANNELS createUpdatedEntity() {
        return new CHANNELS().cHANNEL(UPDATED_C_HANNEL).dESCRIPTION(UPDATED_D_ESCRIPTION).bIN(UPDATED_B_IN);
    }

    @BeforeEach
    public void initTest() {
        cHANNELS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCHANNELS != null) {
            cHANNELSRepository.delete(insertedCHANNELS);
            insertedCHANNELS = null;
        }
    }

    @Test
    @Transactional
    void createCHANNELS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CHANNELS
        var returnedCHANNELS = om.readValue(
            restCHANNELSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHANNELS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CHANNELS.class
        );

        // Validate the CHANNELS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCHANNELSUpdatableFieldsEquals(returnedCHANNELS, getPersistedCHANNELS(returnedCHANNELS));

        insertedCHANNELS = returnedCHANNELS;
    }

    @Test
    @Transactional
    void createCHANNELSWithExistingId() throws Exception {
        // Create the CHANNELS with an existing ID
        cHANNELS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCHANNELSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHANNELS)))
            .andExpect(status().isBadRequest());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCHANNELS() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        // Get all the cHANNELSList
        restCHANNELSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cHANNELS.getId().intValue())))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)))
            .andExpect(jsonPath("$.[*].bIN").value(hasItem(DEFAULT_B_IN)));
    }

    @Test
    @Transactional
    void getCHANNELS() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        // Get the cHANNELS
        restCHANNELSMockMvc
            .perform(get(ENTITY_API_URL_ID, cHANNELS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cHANNELS.getId().intValue()))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION))
            .andExpect(jsonPath("$.bIN").value(DEFAULT_B_IN));
    }

    @Test
    @Transactional
    void getNonExistingCHANNELS() throws Exception {
        // Get the cHANNELS
        restCHANNELSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCHANNELS() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHANNELS
        CHANNELS updatedCHANNELS = cHANNELSRepository.findById(cHANNELS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCHANNELS are not directly saved in db
        em.detach(updatedCHANNELS);
        updatedCHANNELS.cHANNEL(UPDATED_C_HANNEL).dESCRIPTION(UPDATED_D_ESCRIPTION).bIN(UPDATED_B_IN);

        restCHANNELSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCHANNELS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCHANNELS))
            )
            .andExpect(status().isOk());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCHANNELSToMatchAllProperties(updatedCHANNELS);
    }

    @Test
    @Transactional
    void putNonExistingCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cHANNELS.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHANNELS))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cHANNELS))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHANNELS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCHANNELSWithPatch() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHANNELS using partial update
        CHANNELS partialUpdatedCHANNELS = new CHANNELS();
        partialUpdatedCHANNELS.setId(cHANNELS.getId());

        partialUpdatedCHANNELS.bIN(UPDATED_B_IN);

        restCHANNELSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHANNELS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHANNELS))
            )
            .andExpect(status().isOk());

        // Validate the CHANNELS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHANNELSUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCHANNELS, cHANNELS), getPersistedCHANNELS(cHANNELS));
    }

    @Test
    @Transactional
    void fullUpdateCHANNELSWithPatch() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHANNELS using partial update
        CHANNELS partialUpdatedCHANNELS = new CHANNELS();
        partialUpdatedCHANNELS.setId(cHANNELS.getId());

        partialUpdatedCHANNELS.cHANNEL(UPDATED_C_HANNEL).dESCRIPTION(UPDATED_D_ESCRIPTION).bIN(UPDATED_B_IN);

        restCHANNELSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHANNELS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHANNELS))
            )
            .andExpect(status().isOk());

        // Validate the CHANNELS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHANNELSUpdatableFieldsEquals(partialUpdatedCHANNELS, getPersistedCHANNELS(partialUpdatedCHANNELS));
    }

    @Test
    @Transactional
    void patchNonExistingCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cHANNELS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cHANNELS))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cHANNELS))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCHANNELS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHANNELS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHANNELSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cHANNELS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHANNELS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCHANNELS() throws Exception {
        // Initialize the database
        insertedCHANNELS = cHANNELSRepository.saveAndFlush(cHANNELS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cHANNELS
        restCHANNELSMockMvc
            .perform(delete(ENTITY_API_URL_ID, cHANNELS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cHANNELSRepository.count();
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

    protected CHANNELS getPersistedCHANNELS(CHANNELS cHANNELS) {
        return cHANNELSRepository.findById(cHANNELS.getId()).orElseThrow();
    }

    protected void assertPersistedCHANNELSToMatchAllProperties(CHANNELS expectedCHANNELS) {
        assertCHANNELSAllPropertiesEquals(expectedCHANNELS, getPersistedCHANNELS(expectedCHANNELS));
    }

    protected void assertPersistedCHANNELSToMatchUpdatableProperties(CHANNELS expectedCHANNELS) {
        assertCHANNELSAllUpdatablePropertiesEquals(expectedCHANNELS, getPersistedCHANNELS(expectedCHANNELS));
    }
}
