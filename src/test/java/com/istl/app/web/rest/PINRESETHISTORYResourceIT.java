package com.istl.app.web.rest;

import static com.istl.app.domain.PINRESETHISTORYAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.PINRESETHISTORY;
import com.istl.app.repository.PINRESETHISTORYRepository;
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
 * Integration tests for the {@link PINRESETHISTORYResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PINRESETHISTORYResourceIT {

    private static final String DEFAULT_P_HONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_P_HONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_C_USTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_C_USTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_P_INBLOCKEDON = "AAAAAAAAAA";
    private static final String UPDATED_P_INBLOCKEDON = "BBBBBBBBBB";

    private static final String DEFAULT_P_INBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_P_INBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETBY = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETON = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETON = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETAPPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETAPPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETAPPROVEDON = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETAPPROVEDON = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_B_RANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_B_RANCHCODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pinresethistories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PINRESETHISTORYRepository pINRESETHISTORYRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPINRESETHISTORYMockMvc;

    private PINRESETHISTORY pINRESETHISTORY;

    private PINRESETHISTORY insertedPINRESETHISTORY;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PINRESETHISTORY createEntity() {
        return new PINRESETHISTORY()
            .pHONENUMBER(DEFAULT_P_HONENUMBER)
            .cUSTOMERNAME(DEFAULT_C_USTOMERNAME)
            .pINBLOCKEDON(DEFAULT_P_INBLOCKEDON)
            .pINBLOCKREMARKS(DEFAULT_P_INBLOCKREMARKS)
            .pINRESETBY(DEFAULT_P_INRESETBY)
            .pINRESETON(DEFAULT_P_INRESETON)
            .pINRESETAPPROVEDBY(DEFAULT_P_INRESETAPPROVEDBY)
            .pINRESETAPPROVEDON(DEFAULT_P_INRESETAPPROVEDON)
            .pINRESETREMARKS(DEFAULT_P_INRESETREMARKS)
            .bRANCHCODE(DEFAULT_B_RANCHCODE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PINRESETHISTORY createUpdatedEntity() {
        return new PINRESETHISTORY()
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pINBLOCKEDON(UPDATED_P_INBLOCKEDON)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .pINRESETBY(UPDATED_P_INRESETBY)
            .pINRESETON(UPDATED_P_INRESETON)
            .pINRESETAPPROVEDBY(UPDATED_P_INRESETAPPROVEDBY)
            .pINRESETAPPROVEDON(UPDATED_P_INRESETAPPROVEDON)
            .pINRESETREMARKS(UPDATED_P_INRESETREMARKS)
            .bRANCHCODE(UPDATED_B_RANCHCODE);
    }

    @BeforeEach
    public void initTest() {
        pINRESETHISTORY = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPINRESETHISTORY != null) {
            pINRESETHISTORYRepository.delete(insertedPINRESETHISTORY);
            insertedPINRESETHISTORY = null;
        }
    }

    @Test
    @Transactional
    void createPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PINRESETHISTORY
        var returnedPINRESETHISTORY = om.readValue(
            restPINRESETHISTORYMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pINRESETHISTORY)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PINRESETHISTORY.class
        );

        // Validate the PINRESETHISTORY in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPINRESETHISTORYUpdatableFieldsEquals(returnedPINRESETHISTORY, getPersistedPINRESETHISTORY(returnedPINRESETHISTORY));

        insertedPINRESETHISTORY = returnedPINRESETHISTORY;
    }

    @Test
    @Transactional
    void createPINRESETHISTORYWithExistingId() throws Exception {
        // Create the PINRESETHISTORY with an existing ID
        pINRESETHISTORY.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPINRESETHISTORYMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pINRESETHISTORY)))
            .andExpect(status().isBadRequest());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPINRESETHISTORIES() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        // Get all the pINRESETHISTORYList
        restPINRESETHISTORYMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pINRESETHISTORY.getId().intValue())))
            .andExpect(jsonPath("$.[*].pHONENUMBER").value(hasItem(DEFAULT_P_HONENUMBER)))
            .andExpect(jsonPath("$.[*].cUSTOMERNAME").value(hasItem(DEFAULT_C_USTOMERNAME)))
            .andExpect(jsonPath("$.[*].pINBLOCKEDON").value(hasItem(DEFAULT_P_INBLOCKEDON)))
            .andExpect(jsonPath("$.[*].pINBLOCKREMARKS").value(hasItem(DEFAULT_P_INBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].pINRESETBY").value(hasItem(DEFAULT_P_INRESETBY)))
            .andExpect(jsonPath("$.[*].pINRESETON").value(hasItem(DEFAULT_P_INRESETON)))
            .andExpect(jsonPath("$.[*].pINRESETAPPROVEDBY").value(hasItem(DEFAULT_P_INRESETAPPROVEDBY)))
            .andExpect(jsonPath("$.[*].pINRESETAPPROVEDON").value(hasItem(DEFAULT_P_INRESETAPPROVEDON)))
            .andExpect(jsonPath("$.[*].pINRESETREMARKS").value(hasItem(DEFAULT_P_INRESETREMARKS)))
            .andExpect(jsonPath("$.[*].bRANCHCODE").value(hasItem(DEFAULT_B_RANCHCODE)));
    }

    @Test
    @Transactional
    void getPINRESETHISTORY() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        // Get the pINRESETHISTORY
        restPINRESETHISTORYMockMvc
            .perform(get(ENTITY_API_URL_ID, pINRESETHISTORY.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pINRESETHISTORY.getId().intValue()))
            .andExpect(jsonPath("$.pHONENUMBER").value(DEFAULT_P_HONENUMBER))
            .andExpect(jsonPath("$.cUSTOMERNAME").value(DEFAULT_C_USTOMERNAME))
            .andExpect(jsonPath("$.pINBLOCKEDON").value(DEFAULT_P_INBLOCKEDON))
            .andExpect(jsonPath("$.pINBLOCKREMARKS").value(DEFAULT_P_INBLOCKREMARKS))
            .andExpect(jsonPath("$.pINRESETBY").value(DEFAULT_P_INRESETBY))
            .andExpect(jsonPath("$.pINRESETON").value(DEFAULT_P_INRESETON))
            .andExpect(jsonPath("$.pINRESETAPPROVEDBY").value(DEFAULT_P_INRESETAPPROVEDBY))
            .andExpect(jsonPath("$.pINRESETAPPROVEDON").value(DEFAULT_P_INRESETAPPROVEDON))
            .andExpect(jsonPath("$.pINRESETREMARKS").value(DEFAULT_P_INRESETREMARKS))
            .andExpect(jsonPath("$.bRANCHCODE").value(DEFAULT_B_RANCHCODE));
    }

    @Test
    @Transactional
    void getNonExistingPINRESETHISTORY() throws Exception {
        // Get the pINRESETHISTORY
        restPINRESETHISTORYMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPINRESETHISTORY() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pINRESETHISTORY
        PINRESETHISTORY updatedPINRESETHISTORY = pINRESETHISTORYRepository.findById(pINRESETHISTORY.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPINRESETHISTORY are not directly saved in db
        em.detach(updatedPINRESETHISTORY);
        updatedPINRESETHISTORY
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pINBLOCKEDON(UPDATED_P_INBLOCKEDON)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .pINRESETBY(UPDATED_P_INRESETBY)
            .pINRESETON(UPDATED_P_INRESETON)
            .pINRESETAPPROVEDBY(UPDATED_P_INRESETAPPROVEDBY)
            .pINRESETAPPROVEDON(UPDATED_P_INRESETAPPROVEDON)
            .pINRESETREMARKS(UPDATED_P_INRESETREMARKS)
            .bRANCHCODE(UPDATED_B_RANCHCODE);

        restPINRESETHISTORYMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPINRESETHISTORY.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPINRESETHISTORY))
            )
            .andExpect(status().isOk());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPINRESETHISTORYToMatchAllProperties(updatedPINRESETHISTORY);
    }

    @Test
    @Transactional
    void putNonExistingPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pINRESETHISTORY.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pINRESETHISTORY))
            )
            .andExpect(status().isBadRequest());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pINRESETHISTORY))
            )
            .andExpect(status().isBadRequest());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pINRESETHISTORY)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePINRESETHISTORYWithPatch() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pINRESETHISTORY using partial update
        PINRESETHISTORY partialUpdatedPINRESETHISTORY = new PINRESETHISTORY();
        partialUpdatedPINRESETHISTORY.setId(pINRESETHISTORY.getId());

        partialUpdatedPINRESETHISTORY
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .pINRESETBY(UPDATED_P_INRESETBY)
            .pINRESETREMARKS(UPDATED_P_INRESETREMARKS);

        restPINRESETHISTORYMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPINRESETHISTORY.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPINRESETHISTORY))
            )
            .andExpect(status().isOk());

        // Validate the PINRESETHISTORY in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPINRESETHISTORYUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPINRESETHISTORY, pINRESETHISTORY),
            getPersistedPINRESETHISTORY(pINRESETHISTORY)
        );
    }

    @Test
    @Transactional
    void fullUpdatePINRESETHISTORYWithPatch() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pINRESETHISTORY using partial update
        PINRESETHISTORY partialUpdatedPINRESETHISTORY = new PINRESETHISTORY();
        partialUpdatedPINRESETHISTORY.setId(pINRESETHISTORY.getId());

        partialUpdatedPINRESETHISTORY
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pINBLOCKEDON(UPDATED_P_INBLOCKEDON)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .pINRESETBY(UPDATED_P_INRESETBY)
            .pINRESETON(UPDATED_P_INRESETON)
            .pINRESETAPPROVEDBY(UPDATED_P_INRESETAPPROVEDBY)
            .pINRESETAPPROVEDON(UPDATED_P_INRESETAPPROVEDON)
            .pINRESETREMARKS(UPDATED_P_INRESETREMARKS)
            .bRANCHCODE(UPDATED_B_RANCHCODE);

        restPINRESETHISTORYMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPINRESETHISTORY.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPINRESETHISTORY))
            )
            .andExpect(status().isOk());

        // Validate the PINRESETHISTORY in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPINRESETHISTORYUpdatableFieldsEquals(
            partialUpdatedPINRESETHISTORY,
            getPersistedPINRESETHISTORY(partialUpdatedPINRESETHISTORY)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pINRESETHISTORY.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pINRESETHISTORY))
            )
            .andExpect(status().isBadRequest());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pINRESETHISTORY))
            )
            .andExpect(status().isBadRequest());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPINRESETHISTORY() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pINRESETHISTORY.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPINRESETHISTORYMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(pINRESETHISTORY)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PINRESETHISTORY in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePINRESETHISTORY() throws Exception {
        // Initialize the database
        insertedPINRESETHISTORY = pINRESETHISTORYRepository.saveAndFlush(pINRESETHISTORY);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the pINRESETHISTORY
        restPINRESETHISTORYMockMvc
            .perform(delete(ENTITY_API_URL_ID, pINRESETHISTORY.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return pINRESETHISTORYRepository.count();
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

    protected PINRESETHISTORY getPersistedPINRESETHISTORY(PINRESETHISTORY pINRESETHISTORY) {
        return pINRESETHISTORYRepository.findById(pINRESETHISTORY.getId()).orElseThrow();
    }

    protected void assertPersistedPINRESETHISTORYToMatchAllProperties(PINRESETHISTORY expectedPINRESETHISTORY) {
        assertPINRESETHISTORYAllPropertiesEquals(expectedPINRESETHISTORY, getPersistedPINRESETHISTORY(expectedPINRESETHISTORY));
    }

    protected void assertPersistedPINRESETHISTORYToMatchUpdatableProperties(PINRESETHISTORY expectedPINRESETHISTORY) {
        assertPINRESETHISTORYAllUpdatablePropertiesEquals(expectedPINRESETHISTORY, getPersistedPINRESETHISTORY(expectedPINRESETHISTORY));
    }
}
