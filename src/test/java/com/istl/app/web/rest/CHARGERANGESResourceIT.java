package com.istl.app.web.rest;

import static com.istl.app.domain.CHARGERANGESAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CHARGERANGES;
import com.istl.app.repository.CHARGERANGESRepository;
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
 * Integration tests for the {@link CHARGERANGESResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CHARGERANGESResourceIT {

    private static final String DEFAULT_B_ILLERID = "AAAAAAAAAA";
    private static final String UPDATED_B_ILLERID = "BBBBBBBBBB";

    private static final String DEFAULT_P_ROCESSINGCODE = "AAAAAAAAAA";
    private static final String UPDATED_P_ROCESSINGCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_M_AX = 1L;
    private static final Long UPDATED_M_AX = 2L;

    private static final Long DEFAULT_M_IN = 1L;
    private static final Long UPDATED_M_IN = 2L;

    private static final Long DEFAULT_A_MOUNT = 1L;
    private static final Long UPDATED_A_MOUNT = 2L;

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_C_REATEDAT = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDAT = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDON = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDON = "BBBBBBBBBB";

    private static final Long DEFAULT_A_PPROVED = 1L;
    private static final Long UPDATED_A_PPROVED = 2L;

    private static final Long DEFAULT_D_ECLINED = 1L;
    private static final Long UPDATED_D_ECLINED = 2L;

    private static final String DEFAULT_D_ECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ECLINEDBY = "BBBBBBBBBB";

    private static final Long DEFAULT_C_HARGEID = 1L;
    private static final Long UPDATED_C_HARGEID = 2L;

    private static final String ENTITY_API_URL = "/api/chargeranges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CHARGERANGESRepository cHARGERANGESRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCHARGERANGESMockMvc;

    private CHARGERANGES cHARGERANGES;

    private CHARGERANGES insertedCHARGERANGES;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHARGERANGES createEntity() {
        return new CHARGERANGES()
            .bILLERID(DEFAULT_B_ILLERID)
            .pROCESSINGCODE(DEFAULT_P_ROCESSINGCODE)
            .mAX(DEFAULT_M_AX)
            .mIN(DEFAULT_M_IN)
            .aMOUNT(DEFAULT_A_MOUNT)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .cREATEDAT(DEFAULT_C_REATEDAT)
            .aPPROVEDON(DEFAULT_A_PPROVEDON)
            .aPPROVED(DEFAULT_A_PPROVED)
            .dECLINED(DEFAULT_D_ECLINED)
            .dECLINEDBY(DEFAULT_D_ECLINEDBY)
            .cHARGEID(DEFAULT_C_HARGEID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CHARGERANGES createUpdatedEntity() {
        return new CHARGERANGES()
            .bILLERID(UPDATED_B_ILLERID)
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .mAX(UPDATED_M_AX)
            .mIN(UPDATED_M_IN)
            .aMOUNT(UPDATED_A_MOUNT)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cREATEDAT(UPDATED_C_REATEDAT)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .cHARGEID(UPDATED_C_HARGEID);
    }

    @BeforeEach
    public void initTest() {
        cHARGERANGES = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCHARGERANGES != null) {
            cHARGERANGESRepository.delete(insertedCHARGERANGES);
            insertedCHARGERANGES = null;
        }
    }

    @Test
    @Transactional
    void createCHARGERANGES() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CHARGERANGES
        var returnedCHARGERANGES = om.readValue(
            restCHARGERANGESMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CHARGERANGES.class
        );

        // Validate the CHARGERANGES in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCHARGERANGESUpdatableFieldsEquals(returnedCHARGERANGES, getPersistedCHARGERANGES(returnedCHARGERANGES));

        insertedCHARGERANGES = returnedCHARGERANGES;
    }

    @Test
    @Transactional
    void createCHARGERANGESWithExistingId() throws Exception {
        // Create the CHARGERANGES with an existing ID
        cHARGERANGES.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkbILLERIDIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setbILLERID(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpROCESSINGCODEIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setpROCESSINGCODE(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkmAXIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setmAX(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkmINIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setmIN(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkaMOUNTIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setaMOUNT(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkcHARGEIDIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cHARGERANGES.setcHARGEID(null);

        // Create the CHARGERANGES, which fails.

        restCHARGERANGESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCHARGERANGES() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        // Get all the cHARGERANGESList
        restCHARGERANGESMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cHARGERANGES.getId().intValue())))
            .andExpect(jsonPath("$.[*].bILLERID").value(hasItem(DEFAULT_B_ILLERID)))
            .andExpect(jsonPath("$.[*].pROCESSINGCODE").value(hasItem(DEFAULT_P_ROCESSINGCODE)))
            .andExpect(jsonPath("$.[*].mAX").value(hasItem(DEFAULT_M_AX.intValue())))
            .andExpect(jsonPath("$.[*].mIN").value(hasItem(DEFAULT_M_IN.intValue())))
            .andExpect(jsonPath("$.[*].aMOUNT").value(hasItem(DEFAULT_A_MOUNT.intValue())))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].cREATEDAT").value(hasItem(DEFAULT_C_REATEDAT)))
            .andExpect(jsonPath("$.[*].aPPROVEDON").value(hasItem(DEFAULT_A_PPROVEDON)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED.intValue())))
            .andExpect(jsonPath("$.[*].dECLINED").value(hasItem(DEFAULT_D_ECLINED.intValue())))
            .andExpect(jsonPath("$.[*].dECLINEDBY").value(hasItem(DEFAULT_D_ECLINEDBY)))
            .andExpect(jsonPath("$.[*].cHARGEID").value(hasItem(DEFAULT_C_HARGEID.intValue())));
    }

    @Test
    @Transactional
    void getCHARGERANGES() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        // Get the cHARGERANGES
        restCHARGERANGESMockMvc
            .perform(get(ENTITY_API_URL_ID, cHARGERANGES.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cHARGERANGES.getId().intValue()))
            .andExpect(jsonPath("$.bILLERID").value(DEFAULT_B_ILLERID))
            .andExpect(jsonPath("$.pROCESSINGCODE").value(DEFAULT_P_ROCESSINGCODE))
            .andExpect(jsonPath("$.mAX").value(DEFAULT_M_AX.intValue()))
            .andExpect(jsonPath("$.mIN").value(DEFAULT_M_IN.intValue()))
            .andExpect(jsonPath("$.aMOUNT").value(DEFAULT_A_MOUNT.intValue()))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.cREATEDAT").value(DEFAULT_C_REATEDAT))
            .andExpect(jsonPath("$.aPPROVEDON").value(DEFAULT_A_PPROVEDON))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED.intValue()))
            .andExpect(jsonPath("$.dECLINED").value(DEFAULT_D_ECLINED.intValue()))
            .andExpect(jsonPath("$.dECLINEDBY").value(DEFAULT_D_ECLINEDBY))
            .andExpect(jsonPath("$.cHARGEID").value(DEFAULT_C_HARGEID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCHARGERANGES() throws Exception {
        // Get the cHARGERANGES
        restCHARGERANGESMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCHARGERANGES() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGERANGES
        CHARGERANGES updatedCHARGERANGES = cHARGERANGESRepository.findById(cHARGERANGES.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCHARGERANGES are not directly saved in db
        em.detach(updatedCHARGERANGES);
        updatedCHARGERANGES
            .bILLERID(UPDATED_B_ILLERID)
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .mAX(UPDATED_M_AX)
            .mIN(UPDATED_M_IN)
            .aMOUNT(UPDATED_A_MOUNT)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cREATEDAT(UPDATED_C_REATEDAT)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .cHARGEID(UPDATED_C_HARGEID);

        restCHARGERANGESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCHARGERANGES.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCHARGERANGES))
            )
            .andExpect(status().isOk());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCHARGERANGESToMatchAllProperties(updatedCHARGERANGES);
    }

    @Test
    @Transactional
    void putNonExistingCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cHARGERANGES.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cHARGERANGES))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cHARGERANGES))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCHARGERANGESWithPatch() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGERANGES using partial update
        CHARGERANGES partialUpdatedCHARGERANGES = new CHARGERANGES();
        partialUpdatedCHARGERANGES.setId(cHARGERANGES.getId());

        partialUpdatedCHARGERANGES
            .mAX(UPDATED_M_AX)
            .aMOUNT(UPDATED_A_MOUNT)
            .cREATEDAT(UPDATED_C_REATEDAT)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .cHARGEID(UPDATED_C_HARGEID);

        restCHARGERANGESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHARGERANGES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHARGERANGES))
            )
            .andExpect(status().isOk());

        // Validate the CHARGERANGES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHARGERANGESUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCHARGERANGES, cHARGERANGES),
            getPersistedCHARGERANGES(cHARGERANGES)
        );
    }

    @Test
    @Transactional
    void fullUpdateCHARGERANGESWithPatch() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cHARGERANGES using partial update
        CHARGERANGES partialUpdatedCHARGERANGES = new CHARGERANGES();
        partialUpdatedCHARGERANGES.setId(cHARGERANGES.getId());

        partialUpdatedCHARGERANGES
            .bILLERID(UPDATED_B_ILLERID)
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .mAX(UPDATED_M_AX)
            .mIN(UPDATED_M_IN)
            .aMOUNT(UPDATED_A_MOUNT)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .cREATEDAT(UPDATED_C_REATEDAT)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .cHARGEID(UPDATED_C_HARGEID);

        restCHARGERANGESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCHARGERANGES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCHARGERANGES))
            )
            .andExpect(status().isOk());

        // Validate the CHARGERANGES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCHARGERANGESUpdatableFieldsEquals(partialUpdatedCHARGERANGES, getPersistedCHARGERANGES(partialUpdatedCHARGERANGES));
    }

    @Test
    @Transactional
    void patchNonExistingCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cHARGERANGES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cHARGERANGES))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cHARGERANGES))
            )
            .andExpect(status().isBadRequest());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCHARGERANGES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cHARGERANGES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCHARGERANGESMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cHARGERANGES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CHARGERANGES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCHARGERANGES() throws Exception {
        // Initialize the database
        insertedCHARGERANGES = cHARGERANGESRepository.saveAndFlush(cHARGERANGES);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cHARGERANGES
        restCHARGERANGESMockMvc
            .perform(delete(ENTITY_API_URL_ID, cHARGERANGES.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cHARGERANGESRepository.count();
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

    protected CHARGERANGES getPersistedCHARGERANGES(CHARGERANGES cHARGERANGES) {
        return cHARGERANGESRepository.findById(cHARGERANGES.getId()).orElseThrow();
    }

    protected void assertPersistedCHARGERANGESToMatchAllProperties(CHARGERANGES expectedCHARGERANGES) {
        assertCHARGERANGESAllPropertiesEquals(expectedCHARGERANGES, getPersistedCHARGERANGES(expectedCHARGERANGES));
    }

    protected void assertPersistedCHARGERANGESToMatchUpdatableProperties(CHARGERANGES expectedCHARGERANGES) {
        assertCHARGERANGESAllUpdatablePropertiesEquals(expectedCHARGERANGES, getPersistedCHARGERANGES(expectedCHARGERANGES));
    }
}
