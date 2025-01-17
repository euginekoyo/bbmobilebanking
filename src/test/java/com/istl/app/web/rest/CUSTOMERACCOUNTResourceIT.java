package com.istl.app.web.rest;

import static com.istl.app.domain.CUSTOMERACCOUNTAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CUSTOMERACCOUNT;
import com.istl.app.repository.CUSTOMERACCOUNTRepository;
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
 * Integration tests for the {@link CUSTOMERACCOUNTResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CUSTOMERACCOUNTResourceIT {

    private static final Long DEFAULT_C_USTOMERID = 1L;
    private static final Long UPDATED_C_USTOMERID = 2L;

    private static final String DEFAULT_A_CCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTCLASS = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTCLASS = "BBBBBBBBBB";

    private static final String DEFAULT_C_USTOMERNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_C_USTOMERNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_C_IF = "AAAAAAAAAA";
    private static final String UPDATED_C_IF = "BBBBBBBBBB";

    private static final Instant DEFAULT_T_IMELINKED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_T_IMELINKED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_B_LOCKED = 1L;
    private static final Long UPDATED_B_LOCKED = 2L;

    private static final Long DEFAULT_S_TOPPED = 1L;
    private static final Long UPDATED_S_TOPPED = 2L;

    private static final Long DEFAULT_D_ORMANT = 1L;
    private static final Long UPDATED_D_ORMANT = 2L;

    private static final String ENTITY_API_URL = "/api/customeraccounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CUSTOMERACCOUNTRepository cUSTOMERACCOUNTRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCUSTOMERACCOUNTMockMvc;

    private CUSTOMERACCOUNT cUSTOMERACCOUNT;

    private CUSTOMERACCOUNT insertedCUSTOMERACCOUNT;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CUSTOMERACCOUNT createEntity() {
        return new CUSTOMERACCOUNT()
            .cUSTOMERID(DEFAULT_C_USTOMERID)
            .aCCOUNTNUMBER(DEFAULT_A_CCOUNTNUMBER)
            .aCCOUNTCLASS(DEFAULT_A_CCOUNTCLASS)
            .cUSTOMERNUMBER(DEFAULT_C_USTOMERNUMBER)
            .cIF(DEFAULT_C_IF)
            .tIMELINKED(DEFAULT_T_IMELINKED)
            .bLOCKED(DEFAULT_B_LOCKED)
            .sTOPPED(DEFAULT_S_TOPPED)
            .dORMANT(DEFAULT_D_ORMANT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CUSTOMERACCOUNT createUpdatedEntity() {
        return new CUSTOMERACCOUNT()
            .cUSTOMERID(UPDATED_C_USTOMERID)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .cUSTOMERNUMBER(UPDATED_C_USTOMERNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .bLOCKED(UPDATED_B_LOCKED)
            .sTOPPED(UPDATED_S_TOPPED)
            .dORMANT(UPDATED_D_ORMANT);
    }

    @BeforeEach
    public void initTest() {
        cUSTOMERACCOUNT = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCUSTOMERACCOUNT != null) {
            cUSTOMERACCOUNTRepository.delete(insertedCUSTOMERACCOUNT);
            insertedCUSTOMERACCOUNT = null;
        }
    }

    @Test
    @Transactional
    void createCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CUSTOMERACCOUNT
        var returnedCUSTOMERACCOUNT = om.readValue(
            restCUSTOMERACCOUNTMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CUSTOMERACCOUNT.class
        );

        // Validate the CUSTOMERACCOUNT in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCUSTOMERACCOUNTUpdatableFieldsEquals(returnedCUSTOMERACCOUNT, getPersistedCUSTOMERACCOUNT(returnedCUSTOMERACCOUNT));

        insertedCUSTOMERACCOUNT = returnedCUSTOMERACCOUNT;
    }

    @Test
    @Transactional
    void createCUSTOMERACCOUNTWithExistingId() throws Exception {
        // Create the CUSTOMERACCOUNT with an existing ID
        cUSTOMERACCOUNT.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCUSTOMERACCOUNTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkcUSTOMERIDIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cUSTOMERACCOUNT.setcUSTOMERID(null);

        // Create the CUSTOMERACCOUNT, which fails.

        restCUSTOMERACCOUNTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkaCCOUNTNUMBERIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cUSTOMERACCOUNT.setaCCOUNTNUMBER(null);

        // Create the CUSTOMERACCOUNT, which fails.

        restCUSTOMERACCOUNTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkcIFIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cUSTOMERACCOUNT.setcIF(null);

        // Create the CUSTOMERACCOUNT, which fails.

        restCUSTOMERACCOUNTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCUSTOMERACCOUNTS() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        // Get all the cUSTOMERACCOUNTList
        restCUSTOMERACCOUNTMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cUSTOMERACCOUNT.getId().intValue())))
            .andExpect(jsonPath("$.[*].cUSTOMERID").value(hasItem(DEFAULT_C_USTOMERID.intValue())))
            .andExpect(jsonPath("$.[*].aCCOUNTNUMBER").value(hasItem(DEFAULT_A_CCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].aCCOUNTCLASS").value(hasItem(DEFAULT_A_CCOUNTCLASS)))
            .andExpect(jsonPath("$.[*].cUSTOMERNUMBER").value(hasItem(DEFAULT_C_USTOMERNUMBER)))
            .andExpect(jsonPath("$.[*].cIF").value(hasItem(DEFAULT_C_IF)))
            .andExpect(jsonPath("$.[*].tIMELINKED").value(hasItem(DEFAULT_T_IMELINKED.toString())))
            .andExpect(jsonPath("$.[*].bLOCKED").value(hasItem(DEFAULT_B_LOCKED.intValue())))
            .andExpect(jsonPath("$.[*].sTOPPED").value(hasItem(DEFAULT_S_TOPPED.intValue())))
            .andExpect(jsonPath("$.[*].dORMANT").value(hasItem(DEFAULT_D_ORMANT.intValue())));
    }

    @Test
    @Transactional
    void getCUSTOMERACCOUNT() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        // Get the cUSTOMERACCOUNT
        restCUSTOMERACCOUNTMockMvc
            .perform(get(ENTITY_API_URL_ID, cUSTOMERACCOUNT.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cUSTOMERACCOUNT.getId().intValue()))
            .andExpect(jsonPath("$.cUSTOMERID").value(DEFAULT_C_USTOMERID.intValue()))
            .andExpect(jsonPath("$.aCCOUNTNUMBER").value(DEFAULT_A_CCOUNTNUMBER))
            .andExpect(jsonPath("$.aCCOUNTCLASS").value(DEFAULT_A_CCOUNTCLASS))
            .andExpect(jsonPath("$.cUSTOMERNUMBER").value(DEFAULT_C_USTOMERNUMBER))
            .andExpect(jsonPath("$.cIF").value(DEFAULT_C_IF))
            .andExpect(jsonPath("$.tIMELINKED").value(DEFAULT_T_IMELINKED.toString()))
            .andExpect(jsonPath("$.bLOCKED").value(DEFAULT_B_LOCKED.intValue()))
            .andExpect(jsonPath("$.sTOPPED").value(DEFAULT_S_TOPPED.intValue()))
            .andExpect(jsonPath("$.dORMANT").value(DEFAULT_D_ORMANT.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCUSTOMERACCOUNT() throws Exception {
        // Get the cUSTOMERACCOUNT
        restCUSTOMERACCOUNTMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCUSTOMERACCOUNT() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMERACCOUNT
        CUSTOMERACCOUNT updatedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.findById(cUSTOMERACCOUNT.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCUSTOMERACCOUNT are not directly saved in db
        em.detach(updatedCUSTOMERACCOUNT);
        updatedCUSTOMERACCOUNT
            .cUSTOMERID(UPDATED_C_USTOMERID)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .cUSTOMERNUMBER(UPDATED_C_USTOMERNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .bLOCKED(UPDATED_B_LOCKED)
            .sTOPPED(UPDATED_S_TOPPED)
            .dORMANT(UPDATED_D_ORMANT);

        restCUSTOMERACCOUNTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCUSTOMERACCOUNT.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCUSTOMERACCOUNT))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCUSTOMERACCOUNTToMatchAllProperties(updatedCUSTOMERACCOUNT);
    }

    @Test
    @Transactional
    void putNonExistingCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cUSTOMERACCOUNT.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cUSTOMERACCOUNT))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cUSTOMERACCOUNT))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCUSTOMERACCOUNTWithPatch() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMERACCOUNT using partial update
        CUSTOMERACCOUNT partialUpdatedCUSTOMERACCOUNT = new CUSTOMERACCOUNT();
        partialUpdatedCUSTOMERACCOUNT.setId(cUSTOMERACCOUNT.getId());

        partialUpdatedCUSTOMERACCOUNT
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .cUSTOMERNUMBER(UPDATED_C_USTOMERNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .bLOCKED(UPDATED_B_LOCKED)
            .sTOPPED(UPDATED_S_TOPPED)
            .dORMANT(UPDATED_D_ORMANT);

        restCUSTOMERACCOUNTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCUSTOMERACCOUNT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCUSTOMERACCOUNT))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMERACCOUNT in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCUSTOMERACCOUNTUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCUSTOMERACCOUNT, cUSTOMERACCOUNT),
            getPersistedCUSTOMERACCOUNT(cUSTOMERACCOUNT)
        );
    }

    @Test
    @Transactional
    void fullUpdateCUSTOMERACCOUNTWithPatch() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMERACCOUNT using partial update
        CUSTOMERACCOUNT partialUpdatedCUSTOMERACCOUNT = new CUSTOMERACCOUNT();
        partialUpdatedCUSTOMERACCOUNT.setId(cUSTOMERACCOUNT.getId());

        partialUpdatedCUSTOMERACCOUNT
            .cUSTOMERID(UPDATED_C_USTOMERID)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .cUSTOMERNUMBER(UPDATED_C_USTOMERNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .bLOCKED(UPDATED_B_LOCKED)
            .sTOPPED(UPDATED_S_TOPPED)
            .dORMANT(UPDATED_D_ORMANT);

        restCUSTOMERACCOUNTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCUSTOMERACCOUNT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCUSTOMERACCOUNT))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMERACCOUNT in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCUSTOMERACCOUNTUpdatableFieldsEquals(
            partialUpdatedCUSTOMERACCOUNT,
            getPersistedCUSTOMERACCOUNT(partialUpdatedCUSTOMERACCOUNT)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cUSTOMERACCOUNT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cUSTOMERACCOUNT))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cUSTOMERACCOUNT))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCUSTOMERACCOUNT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMERACCOUNT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERACCOUNTMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cUSTOMERACCOUNT)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CUSTOMERACCOUNT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCUSTOMERACCOUNT() throws Exception {
        // Initialize the database
        insertedCUSTOMERACCOUNT = cUSTOMERACCOUNTRepository.saveAndFlush(cUSTOMERACCOUNT);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cUSTOMERACCOUNT
        restCUSTOMERACCOUNTMockMvc
            .perform(delete(ENTITY_API_URL_ID, cUSTOMERACCOUNT.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cUSTOMERACCOUNTRepository.count();
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

    protected CUSTOMERACCOUNT getPersistedCUSTOMERACCOUNT(CUSTOMERACCOUNT cUSTOMERACCOUNT) {
        return cUSTOMERACCOUNTRepository.findById(cUSTOMERACCOUNT.getId()).orElseThrow();
    }

    protected void assertPersistedCUSTOMERACCOUNTToMatchAllProperties(CUSTOMERACCOUNT expectedCUSTOMERACCOUNT) {
        assertCUSTOMERACCOUNTAllPropertiesEquals(expectedCUSTOMERACCOUNT, getPersistedCUSTOMERACCOUNT(expectedCUSTOMERACCOUNT));
    }

    protected void assertPersistedCUSTOMERACCOUNTToMatchUpdatableProperties(CUSTOMERACCOUNT expectedCUSTOMERACCOUNT) {
        assertCUSTOMERACCOUNTAllUpdatablePropertiesEquals(expectedCUSTOMERACCOUNT, getPersistedCUSTOMERACCOUNT(expectedCUSTOMERACCOUNT));
    }
}
