package com.istl.app.web.rest;

import static com.istl.app.domain.LIMITSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.LIMITS;
import com.istl.app.repository.LIMITSRepository;
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
 * Integration tests for the {@link LIMITSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LIMITSResourceIT {

    private static final String DEFAULT_T_RANSACTIONTYPE = "AAAAAAAAAA";
    private static final String UPDATED_T_RANSACTIONTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_P_ROCODE = "AAAAAA";
    private static final String UPDATED_P_ROCODE = "BBBBBB";

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_T_RANSACTIONLIMIT = 1L;
    private static final Long UPDATED_T_RANSACTIONLIMIT = 2L;

    private static final Long DEFAULT_D_AILYLIMIT = 1L;
    private static final Long UPDATED_D_AILYLIMIT = 2L;

    private static final String DEFAULT_R_EGISTEREDBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EGISTEREDBY = "BBBBBBBBBB";

    private static final String DEFAULT_R_EGISTEREDDATE = "AAAAAAA";
    private static final String UPDATED_R_EGISTEREDDATE = "BBBBBBB";

    private static final String DEFAULT_A_PPROVED = "AA";
    private static final String UPDATED_A_PPROVED = "BB";

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDDATE = "AAAAAAA";
    private static final String UPDATED_A_PPROVEDDATE = "BBBBBBB";

    private static final String DEFAULT_U_PDATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_U_PDATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_U_PDATEDDATE = "AAAAAAA";
    private static final String UPDATED_U_PDATEDDATE = "BBBBBBB";

    private static final Long DEFAULT_R_EWORK = 1L;
    private static final Long UPDATED_R_EWORK = 2L;

    private static final String DEFAULT_R_EWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EWORKBY = "BBBBBBBBBB";

    private static final String DEFAULT_S_ESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_S_ESSIONID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/limits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LIMITSRepository lIMITSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLIMITSMockMvc;

    private LIMITS lIMITS;

    private LIMITS insertedLIMITS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LIMITS createEntity() {
        return new LIMITS()
            .tRANSACTIONTYPE(DEFAULT_T_RANSACTIONTYPE)
            .pROCODE(DEFAULT_P_ROCODE)
            .cHANNEL(DEFAULT_C_HANNEL)
            .tRANSACTIONLIMIT(DEFAULT_T_RANSACTIONLIMIT)
            .dAILYLIMIT(DEFAULT_D_AILYLIMIT)
            .rEGISTEREDBY(DEFAULT_R_EGISTEREDBY)
            .rEGISTEREDDATE(DEFAULT_R_EGISTEREDDATE)
            .aPPROVED(DEFAULT_A_PPROVED)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDDATE(DEFAULT_A_PPROVEDDATE)
            .uPDATEDBY(DEFAULT_U_PDATEDBY)
            .uPDATEDDATE(DEFAULT_U_PDATEDDATE)
            .rEWORK(DEFAULT_R_EWORK)
            .rEWORKBY(DEFAULT_R_EWORKBY)
            .sESSIONID(DEFAULT_S_ESSIONID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LIMITS createUpdatedEntity() {
        return new LIMITS()
            .tRANSACTIONTYPE(UPDATED_T_RANSACTIONTYPE)
            .pROCODE(UPDATED_P_ROCODE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRANSACTIONLIMIT(UPDATED_T_RANSACTIONLIMIT)
            .dAILYLIMIT(UPDATED_D_AILYLIMIT)
            .rEGISTEREDBY(UPDATED_R_EGISTEREDBY)
            .rEGISTEREDDATE(UPDATED_R_EGISTEREDDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .uPDATEDBY(UPDATED_U_PDATEDBY)
            .uPDATEDDATE(UPDATED_U_PDATEDDATE)
            .rEWORK(UPDATED_R_EWORK)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sESSIONID(UPDATED_S_ESSIONID);
    }

    @BeforeEach
    public void initTest() {
        lIMITS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLIMITS != null) {
            lIMITSRepository.delete(insertedLIMITS);
            insertedLIMITS = null;
        }
    }

    @Test
    @Transactional
    void createLIMITS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LIMITS
        var returnedLIMITS = om.readValue(
            restLIMITSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LIMITS.class
        );

        // Validate the LIMITS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLIMITSUpdatableFieldsEquals(returnedLIMITS, getPersistedLIMITS(returnedLIMITS));

        insertedLIMITS = returnedLIMITS;
    }

    @Test
    @Transactional
    void createLIMITSWithExistingId() throws Exception {
        // Create the LIMITS with an existing ID
        lIMITS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLIMITSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isBadRequest());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checktRANSACTIONTYPEIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lIMITS.settRANSACTIONTYPE(null);

        // Create the LIMITS, which fails.

        restLIMITSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checktRANSACTIONLIMITIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lIMITS.settRANSACTIONLIMIT(null);

        // Create the LIMITS, which fails.

        restLIMITSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLIMITS() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        // Get all the lIMITSList
        restLIMITSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lIMITS.getId().intValue())))
            .andExpect(jsonPath("$.[*].tRANSACTIONTYPE").value(hasItem(DEFAULT_T_RANSACTIONTYPE)))
            .andExpect(jsonPath("$.[*].pROCODE").value(hasItem(DEFAULT_P_ROCODE)))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].tRANSACTIONLIMIT").value(hasItem(DEFAULT_T_RANSACTIONLIMIT.intValue())))
            .andExpect(jsonPath("$.[*].dAILYLIMIT").value(hasItem(DEFAULT_D_AILYLIMIT.intValue())))
            .andExpect(jsonPath("$.[*].rEGISTEREDBY").value(hasItem(DEFAULT_R_EGISTEREDBY)))
            .andExpect(jsonPath("$.[*].rEGISTEREDDATE").value(hasItem(DEFAULT_R_EGISTEREDDATE)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDDATE").value(hasItem(DEFAULT_A_PPROVEDDATE)))
            .andExpect(jsonPath("$.[*].uPDATEDBY").value(hasItem(DEFAULT_U_PDATEDBY)))
            .andExpect(jsonPath("$.[*].uPDATEDDATE").value(hasItem(DEFAULT_U_PDATEDDATE)))
            .andExpect(jsonPath("$.[*].rEWORK").value(hasItem(DEFAULT_R_EWORK.intValue())))
            .andExpect(jsonPath("$.[*].rEWORKBY").value(hasItem(DEFAULT_R_EWORKBY)))
            .andExpect(jsonPath("$.[*].sESSIONID").value(hasItem(DEFAULT_S_ESSIONID)));
    }

    @Test
    @Transactional
    void getLIMITS() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        // Get the lIMITS
        restLIMITSMockMvc
            .perform(get(ENTITY_API_URL_ID, lIMITS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lIMITS.getId().intValue()))
            .andExpect(jsonPath("$.tRANSACTIONTYPE").value(DEFAULT_T_RANSACTIONTYPE))
            .andExpect(jsonPath("$.pROCODE").value(DEFAULT_P_ROCODE))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.tRANSACTIONLIMIT").value(DEFAULT_T_RANSACTIONLIMIT.intValue()))
            .andExpect(jsonPath("$.dAILYLIMIT").value(DEFAULT_D_AILYLIMIT.intValue()))
            .andExpect(jsonPath("$.rEGISTEREDBY").value(DEFAULT_R_EGISTEREDBY))
            .andExpect(jsonPath("$.rEGISTEREDDATE").value(DEFAULT_R_EGISTEREDDATE))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDDATE").value(DEFAULT_A_PPROVEDDATE))
            .andExpect(jsonPath("$.uPDATEDBY").value(DEFAULT_U_PDATEDBY))
            .andExpect(jsonPath("$.uPDATEDDATE").value(DEFAULT_U_PDATEDDATE))
            .andExpect(jsonPath("$.rEWORK").value(DEFAULT_R_EWORK.intValue()))
            .andExpect(jsonPath("$.rEWORKBY").value(DEFAULT_R_EWORKBY))
            .andExpect(jsonPath("$.sESSIONID").value(DEFAULT_S_ESSIONID));
    }

    @Test
    @Transactional
    void getNonExistingLIMITS() throws Exception {
        // Get the lIMITS
        restLIMITSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLIMITS() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lIMITS
        LIMITS updatedLIMITS = lIMITSRepository.findById(lIMITS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLIMITS are not directly saved in db
        em.detach(updatedLIMITS);
        updatedLIMITS
            .tRANSACTIONTYPE(UPDATED_T_RANSACTIONTYPE)
            .pROCODE(UPDATED_P_ROCODE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRANSACTIONLIMIT(UPDATED_T_RANSACTIONLIMIT)
            .dAILYLIMIT(UPDATED_D_AILYLIMIT)
            .rEGISTEREDBY(UPDATED_R_EGISTEREDBY)
            .rEGISTEREDDATE(UPDATED_R_EGISTEREDDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .uPDATEDBY(UPDATED_U_PDATEDBY)
            .uPDATEDDATE(UPDATED_U_PDATEDDATE)
            .rEWORK(UPDATED_R_EWORK)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sESSIONID(UPDATED_S_ESSIONID);

        restLIMITSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLIMITS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLIMITS))
            )
            .andExpect(status().isOk());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLIMITSToMatchAllProperties(updatedLIMITS);
    }

    @Test
    @Transactional
    void putNonExistingLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(put(ENTITY_API_URL_ID, lIMITS.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isBadRequest());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(lIMITS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLIMITSWithPatch() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lIMITS using partial update
        LIMITS partialUpdatedLIMITS = new LIMITS();
        partialUpdatedLIMITS.setId(lIMITS.getId());

        partialUpdatedLIMITS
            .tRANSACTIONTYPE(UPDATED_T_RANSACTIONTYPE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRANSACTIONLIMIT(UPDATED_T_RANSACTIONLIMIT)
            .dAILYLIMIT(UPDATED_D_AILYLIMIT)
            .rEGISTEREDBY(UPDATED_R_EGISTEREDBY)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .uPDATEDBY(UPDATED_U_PDATEDBY)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sESSIONID(UPDATED_S_ESSIONID);

        restLIMITSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLIMITS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLIMITS))
            )
            .andExpect(status().isOk());

        // Validate the LIMITS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLIMITSUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedLIMITS, lIMITS), getPersistedLIMITS(lIMITS));
    }

    @Test
    @Transactional
    void fullUpdateLIMITSWithPatch() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lIMITS using partial update
        LIMITS partialUpdatedLIMITS = new LIMITS();
        partialUpdatedLIMITS.setId(lIMITS.getId());

        partialUpdatedLIMITS
            .tRANSACTIONTYPE(UPDATED_T_RANSACTIONTYPE)
            .pROCODE(UPDATED_P_ROCODE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRANSACTIONLIMIT(UPDATED_T_RANSACTIONLIMIT)
            .dAILYLIMIT(UPDATED_D_AILYLIMIT)
            .rEGISTEREDBY(UPDATED_R_EGISTEREDBY)
            .rEGISTEREDDATE(UPDATED_R_EGISTEREDDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .uPDATEDBY(UPDATED_U_PDATEDBY)
            .uPDATEDDATE(UPDATED_U_PDATEDDATE)
            .rEWORK(UPDATED_R_EWORK)
            .rEWORKBY(UPDATED_R_EWORKBY)
            .sESSIONID(UPDATED_S_ESSIONID);

        restLIMITSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLIMITS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLIMITS))
            )
            .andExpect(status().isOk());

        // Validate the LIMITS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLIMITSUpdatableFieldsEquals(partialUpdatedLIMITS, getPersistedLIMITS(partialUpdatedLIMITS));
    }

    @Test
    @Transactional
    void patchNonExistingLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lIMITS.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(lIMITS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(lIMITS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLIMITS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lIMITS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLIMITSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(lIMITS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LIMITS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLIMITS() throws Exception {
        // Initialize the database
        insertedLIMITS = lIMITSRepository.saveAndFlush(lIMITS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the lIMITS
        restLIMITSMockMvc
            .perform(delete(ENTITY_API_URL_ID, lIMITS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return lIMITSRepository.count();
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

    protected LIMITS getPersistedLIMITS(LIMITS lIMITS) {
        return lIMITSRepository.findById(lIMITS.getId()).orElseThrow();
    }

    protected void assertPersistedLIMITSToMatchAllProperties(LIMITS expectedLIMITS) {
        assertLIMITSAllPropertiesEquals(expectedLIMITS, getPersistedLIMITS(expectedLIMITS));
    }

    protected void assertPersistedLIMITSToMatchUpdatableProperties(LIMITS expectedLIMITS) {
        assertLIMITSAllUpdatablePropertiesEquals(expectedLIMITS, getPersistedLIMITS(expectedLIMITS));
    }
}
