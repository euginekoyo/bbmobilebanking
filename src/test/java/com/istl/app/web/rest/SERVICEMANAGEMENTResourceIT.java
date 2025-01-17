package com.istl.app.web.rest;

import static com.istl.app.domain.SERVICEMANAGEMENTAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.SERVICEMANAGEMENT;
import com.istl.app.repository.SERVICEMANAGEMENTRepository;
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
 * Integration tests for the {@link SERVICEMANAGEMENTResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SERVICEMANAGEMENTResourceIT {

    private static final String DEFAULT_P_ROCESSINGCODE = "AAAAAAAAAA";
    private static final String UPDATED_P_ROCESSINGCODE = "BBBBBBBBBB";

    private static final String DEFAULT_A_CTIVE = "AAAAAAAAAA";
    private static final String UPDATED_A_CTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_A_PPROVED = 1L;
    private static final Long UPDATED_A_PPROVED = 2L;

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_A_DAPTORTYPE = "AAAAAAAAAA";
    private static final String UPDATED_A_DAPTORTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_D_ESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESTINATION = "BBBBBBBBBB";

    private static final Double DEFAULT_T_HIRDPARTYRESPONSE = 1D;
    private static final Double UPDATED_T_HIRDPARTYRESPONSE = 2D;

    private static final String DEFAULT_T_ELCO = "AAAAAAAAAA";
    private static final String UPDATED_T_ELCO = "BBBBBBBBBB";

    private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_S_ESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_S_ESSIONID = "BBBBBBBBBB";

    private static final String DEFAULT_R_EWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EWORKBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/servicemanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SERVICEMANAGEMENTRepository sERVICEMANAGEMENTRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSERVICEMANAGEMENTMockMvc;

    private SERVICEMANAGEMENT sERVICEMANAGEMENT;

    private SERVICEMANAGEMENT insertedSERVICEMANAGEMENT;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SERVICEMANAGEMENT createEntity() {
        return new SERVICEMANAGEMENT()
            .pROCESSINGCODE(DEFAULT_P_ROCESSINGCODE)
            .aCTIVE(DEFAULT_A_CTIVE)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .dATECREATED(DEFAULT_D_ATECREATED)
            .aPPROVED(DEFAULT_A_PPROVED)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDDATE(DEFAULT_A_PPROVEDDATE)
            .aDAPTORTYPE(DEFAULT_A_DAPTORTYPE)
            .dESTINATION(DEFAULT_D_ESTINATION)
            .tHIRDPARTYRESPONSE(DEFAULT_T_HIRDPARTYRESPONSE)
            .tELCO(DEFAULT_T_ELCO)
            .dESCRIPTION(DEFAULT_D_ESCRIPTION)
            .rEMARKS(DEFAULT_R_EMARKS)
            .sESSIONID(DEFAULT_S_ESSIONID)
            .rEWORKBY(DEFAULT_R_EWORKBY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SERVICEMANAGEMENT createUpdatedEntity() {
        return new SERVICEMANAGEMENT()
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .aCTIVE(UPDATED_A_CTIVE)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .aDAPTORTYPE(UPDATED_A_DAPTORTYPE)
            .dESTINATION(UPDATED_D_ESTINATION)
            .tHIRDPARTYRESPONSE(UPDATED_T_HIRDPARTYRESPONSE)
            .tELCO(UPDATED_T_ELCO)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY);
    }

    @BeforeEach
    public void initTest() {
        sERVICEMANAGEMENT = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSERVICEMANAGEMENT != null) {
            sERVICEMANAGEMENTRepository.delete(insertedSERVICEMANAGEMENT);
            insertedSERVICEMANAGEMENT = null;
        }
    }

    @Test
    @Transactional
    void createSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SERVICEMANAGEMENT
        var returnedSERVICEMANAGEMENT = om.readValue(
            restSERVICEMANAGEMENTMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sERVICEMANAGEMENT)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SERVICEMANAGEMENT.class
        );

        // Validate the SERVICEMANAGEMENT in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSERVICEMANAGEMENTUpdatableFieldsEquals(returnedSERVICEMANAGEMENT, getPersistedSERVICEMANAGEMENT(returnedSERVICEMANAGEMENT));

        insertedSERVICEMANAGEMENT = returnedSERVICEMANAGEMENT;
    }

    @Test
    @Transactional
    void createSERVICEMANAGEMENTWithExistingId() throws Exception {
        // Create the SERVICEMANAGEMENT with an existing ID
        sERVICEMANAGEMENT.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSERVICEMANAGEMENTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sERVICEMANAGEMENT)))
            .andExpect(status().isBadRequest());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkdESCRIPTIONIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        sERVICEMANAGEMENT.setdESCRIPTION(null);

        // Create the SERVICEMANAGEMENT, which fails.

        restSERVICEMANAGEMENTMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sERVICEMANAGEMENT)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSERVICEMANAGEMENTS() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        // Get all the sERVICEMANAGEMENTList
        restSERVICEMANAGEMENTMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sERVICEMANAGEMENT.getId().intValue())))
            .andExpect(jsonPath("$.[*].pROCESSINGCODE").value(hasItem(DEFAULT_P_ROCESSINGCODE)))
            .andExpect(jsonPath("$.[*].aCTIVE").value(hasItem(DEFAULT_A_CTIVE)))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].dATECREATED").value(hasItem(DEFAULT_D_ATECREATED.toString())))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED.intValue())))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDDATE").value(hasItem(DEFAULT_A_PPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].aDAPTORTYPE").value(hasItem(DEFAULT_A_DAPTORTYPE)))
            .andExpect(jsonPath("$.[*].dESTINATION").value(hasItem(DEFAULT_D_ESTINATION)))
            .andExpect(jsonPath("$.[*].tHIRDPARTYRESPONSE").value(hasItem(DEFAULT_T_HIRDPARTYRESPONSE)))
            .andExpect(jsonPath("$.[*].tELCO").value(hasItem(DEFAULT_T_ELCO)))
            .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].sESSIONID").value(hasItem(DEFAULT_S_ESSIONID)))
            .andExpect(jsonPath("$.[*].rEWORKBY").value(hasItem(DEFAULT_R_EWORKBY)));
    }

    @Test
    @Transactional
    void getSERVICEMANAGEMENT() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        // Get the sERVICEMANAGEMENT
        restSERVICEMANAGEMENTMockMvc
            .perform(get(ENTITY_API_URL_ID, sERVICEMANAGEMENT.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sERVICEMANAGEMENT.getId().intValue()))
            .andExpect(jsonPath("$.pROCESSINGCODE").value(DEFAULT_P_ROCESSINGCODE))
            .andExpect(jsonPath("$.aCTIVE").value(DEFAULT_A_CTIVE))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.dATECREATED").value(DEFAULT_D_ATECREATED.toString()))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED.intValue()))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDDATE").value(DEFAULT_A_PPROVEDDATE.toString()))
            .andExpect(jsonPath("$.aDAPTORTYPE").value(DEFAULT_A_DAPTORTYPE))
            .andExpect(jsonPath("$.dESTINATION").value(DEFAULT_D_ESTINATION))
            .andExpect(jsonPath("$.tHIRDPARTYRESPONSE").value(DEFAULT_T_HIRDPARTYRESPONSE))
            .andExpect(jsonPath("$.tELCO").value(DEFAULT_T_ELCO))
            .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.sESSIONID").value(DEFAULT_S_ESSIONID))
            .andExpect(jsonPath("$.rEWORKBY").value(DEFAULT_R_EWORKBY));
    }

    @Test
    @Transactional
    void getNonExistingSERVICEMANAGEMENT() throws Exception {
        // Get the sERVICEMANAGEMENT
        restSERVICEMANAGEMENTMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSERVICEMANAGEMENT() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sERVICEMANAGEMENT
        SERVICEMANAGEMENT updatedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.findById(sERVICEMANAGEMENT.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSERVICEMANAGEMENT are not directly saved in db
        em.detach(updatedSERVICEMANAGEMENT);
        updatedSERVICEMANAGEMENT
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .aCTIVE(UPDATED_A_CTIVE)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .aDAPTORTYPE(UPDATED_A_DAPTORTYPE)
            .dESTINATION(UPDATED_D_ESTINATION)
            .tHIRDPARTYRESPONSE(UPDATED_T_HIRDPARTYRESPONSE)
            .tELCO(UPDATED_T_ELCO)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY);

        restSERVICEMANAGEMENTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSERVICEMANAGEMENT.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSERVICEMANAGEMENT))
            )
            .andExpect(status().isOk());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSERVICEMANAGEMENTToMatchAllProperties(updatedSERVICEMANAGEMENT);
    }

    @Test
    @Transactional
    void putNonExistingSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sERVICEMANAGEMENT.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sERVICEMANAGEMENT))
            )
            .andExpect(status().isBadRequest());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sERVICEMANAGEMENT))
            )
            .andExpect(status().isBadRequest());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sERVICEMANAGEMENT)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSERVICEMANAGEMENTWithPatch() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sERVICEMANAGEMENT using partial update
        SERVICEMANAGEMENT partialUpdatedSERVICEMANAGEMENT = new SERVICEMANAGEMENT();
        partialUpdatedSERVICEMANAGEMENT.setId(sERVICEMANAGEMENT.getId());

        partialUpdatedSERVICEMANAGEMENT
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .dESTINATION(UPDATED_D_ESTINATION)
            .tELCO(UPDATED_T_ELCO)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .rEWORKBY(UPDATED_R_EWORKBY);

        restSERVICEMANAGEMENTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSERVICEMANAGEMENT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSERVICEMANAGEMENT))
            )
            .andExpect(status().isOk());

        // Validate the SERVICEMANAGEMENT in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSERVICEMANAGEMENTUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSERVICEMANAGEMENT, sERVICEMANAGEMENT),
            getPersistedSERVICEMANAGEMENT(sERVICEMANAGEMENT)
        );
    }

    @Test
    @Transactional
    void fullUpdateSERVICEMANAGEMENTWithPatch() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sERVICEMANAGEMENT using partial update
        SERVICEMANAGEMENT partialUpdatedSERVICEMANAGEMENT = new SERVICEMANAGEMENT();
        partialUpdatedSERVICEMANAGEMENT.setId(sERVICEMANAGEMENT.getId());

        partialUpdatedSERVICEMANAGEMENT
            .pROCESSINGCODE(UPDATED_P_ROCESSINGCODE)
            .aCTIVE(UPDATED_A_CTIVE)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .dATECREATED(UPDATED_D_ATECREATED)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .aDAPTORTYPE(UPDATED_A_DAPTORTYPE)
            .dESTINATION(UPDATED_D_ESTINATION)
            .tHIRDPARTYRESPONSE(UPDATED_T_HIRDPARTYRESPONSE)
            .tELCO(UPDATED_T_ELCO)
            .dESCRIPTION(UPDATED_D_ESCRIPTION)
            .rEMARKS(UPDATED_R_EMARKS)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKBY(UPDATED_R_EWORKBY);

        restSERVICEMANAGEMENTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSERVICEMANAGEMENT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSERVICEMANAGEMENT))
            )
            .andExpect(status().isOk());

        // Validate the SERVICEMANAGEMENT in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSERVICEMANAGEMENTUpdatableFieldsEquals(
            partialUpdatedSERVICEMANAGEMENT,
            getPersistedSERVICEMANAGEMENT(partialUpdatedSERVICEMANAGEMENT)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sERVICEMANAGEMENT.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sERVICEMANAGEMENT))
            )
            .andExpect(status().isBadRequest());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sERVICEMANAGEMENT))
            )
            .andExpect(status().isBadRequest());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSERVICEMANAGEMENT() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sERVICEMANAGEMENT.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSERVICEMANAGEMENTMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(sERVICEMANAGEMENT)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SERVICEMANAGEMENT in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSERVICEMANAGEMENT() throws Exception {
        // Initialize the database
        insertedSERVICEMANAGEMENT = sERVICEMANAGEMENTRepository.saveAndFlush(sERVICEMANAGEMENT);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the sERVICEMANAGEMENT
        restSERVICEMANAGEMENTMockMvc
            .perform(delete(ENTITY_API_URL_ID, sERVICEMANAGEMENT.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return sERVICEMANAGEMENTRepository.count();
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

    protected SERVICEMANAGEMENT getPersistedSERVICEMANAGEMENT(SERVICEMANAGEMENT sERVICEMANAGEMENT) {
        return sERVICEMANAGEMENTRepository.findById(sERVICEMANAGEMENT.getId()).orElseThrow();
    }

    protected void assertPersistedSERVICEMANAGEMENTToMatchAllProperties(SERVICEMANAGEMENT expectedSERVICEMANAGEMENT) {
        assertSERVICEMANAGEMENTAllPropertiesEquals(expectedSERVICEMANAGEMENT, getPersistedSERVICEMANAGEMENT(expectedSERVICEMANAGEMENT));
    }

    protected void assertPersistedSERVICEMANAGEMENTToMatchUpdatableProperties(SERVICEMANAGEMENT expectedSERVICEMANAGEMENT) {
        assertSERVICEMANAGEMENTAllUpdatablePropertiesEquals(
            expectedSERVICEMANAGEMENT,
            getPersistedSERVICEMANAGEMENT(expectedSERVICEMANAGEMENT)
        );
    }
}
