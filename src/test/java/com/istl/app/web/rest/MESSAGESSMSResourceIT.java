package com.istl.app.web.rest;

import static com.istl.app.domain.MESSAGESSMSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.MESSAGESSMS;
import com.istl.app.repository.MESSAGESSMSRepository;
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
 * Integration tests for the {@link MESSAGESSMSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MESSAGESSMSResourceIT {

    private static final Instant DEFAULT_T_RNDATETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_T_RNDATETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_P_HONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_P_HONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_T_RANSACTIONNO = "AAAAAAAAAA";
    private static final String UPDATED_T_RANSACTIONNO = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_T_RIALS = 1L;
    private static final Long UPDATED_T_RIALS = 2L;

    private static final Long DEFAULT_P_RIORITY = 1L;
    private static final Long UPDATED_P_RIORITY = 2L;

    private static final String DEFAULT_R_ESPONSECODE = "AAAA";
    private static final String UPDATED_R_ESPONSECODE = "BBBB";

    private static final String DEFAULT_R_ESPONSEMSG = "AAAAAAAAAA";
    private static final String UPDATED_R_ESPONSEMSG = "BBBBBBBBBB";

    private static final Long DEFAULT_S_ENT = 1L;
    private static final Long UPDATED_S_ENT = 2L;

    private static final Long DEFAULT_D_ELIVERED = 1L;
    private static final Long UPDATED_D_ELIVERED = 2L;

    private static final String DEFAULT_T_XNTYPE = "AAAAAAAAAA";
    private static final String UPDATED_T_XNTYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_E_RROREXCEPTION = 1L;
    private static final Long UPDATED_E_RROREXCEPTION = 2L;

    private static final Instant DEFAULT_D_ATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_D_ATESENT = "AAAAAAA";
    private static final String UPDATED_D_ATESENT = "BBBBBBB";

    private static final String DEFAULT_R_TPSREQTIME = "AAAAAAAAAA";
    private static final String UPDATED_R_TPSREQTIME = "BBBBBBBBBB";

    private static final String DEFAULT_F_XGENERATED = "AAAAAAAAAA";
    private static final String UPDATED_F_XGENERATED = "BBBBBBBBBB";

    private static final Long DEFAULT_T_AXPROCESSED = 1L;
    private static final Long UPDATED_T_AXPROCESSED = 2L;

    private static final String DEFAULT_B_ATCHNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_B_ATCHNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_B_ATCHNUMBERTAX = "AAAAAAAAAA";
    private static final String UPDATED_B_ATCHNUMBERTAX = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESPONSETIME = "AAAAAAAAAA";
    private static final String UPDATED_R_ESPONSETIME = "BBBBBBBBBB";

    private static final String DEFAULT_P_DUSEQID = "AAAAAAAAAA";
    private static final String UPDATED_P_DUSEQID = "BBBBBBBBBB";

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESENDBY = "AAAAAAAAAA";
    private static final String UPDATED_R_ESENDBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/messagessms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MESSAGESSMSRepository mESSAGESSMSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMESSAGESSMSMockMvc;

    private MESSAGESSMS mESSAGESSMS;

    private MESSAGESSMS insertedMESSAGESSMS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MESSAGESSMS createEntity() {
        return new MESSAGESSMS()
            .tRNDATETIME(DEFAULT_T_RNDATETIME)
            .pHONENUMBER(DEFAULT_P_HONENUMBER)
            .tRANSACTIONNO(DEFAULT_T_RANSACTIONNO)
            .aCCOUNTNUMBER(DEFAULT_A_CCOUNTNUMBER)
            .mESSAGE(DEFAULT_M_ESSAGE)
            .cHANNEL(DEFAULT_C_HANNEL)
            .tRIALS(DEFAULT_T_RIALS)
            .pRIORITY(DEFAULT_P_RIORITY)
            .rESPONSECODE(DEFAULT_R_ESPONSECODE)
            .rESPONSEMSG(DEFAULT_R_ESPONSEMSG)
            .sENT(DEFAULT_S_ENT)
            .dELIVERED(DEFAULT_D_ELIVERED)
            .tXNTYPE(DEFAULT_T_XNTYPE)
            .eRROREXCEPTION(DEFAULT_E_RROREXCEPTION)
            .dATECREATED(DEFAULT_D_ATECREATED)
            .dATESENT(DEFAULT_D_ATESENT)
            .rTPSREQTIME(DEFAULT_R_TPSREQTIME)
            .fXGENERATED(DEFAULT_F_XGENERATED)
            .tAXPROCESSED(DEFAULT_T_AXPROCESSED)
            .bATCHNUMBER(DEFAULT_B_ATCHNUMBER)
            .bATCHNUMBERTAX(DEFAULT_B_ATCHNUMBERTAX)
            .rESPONSETIME(DEFAULT_R_ESPONSETIME)
            .pDUSEQID(DEFAULT_P_DUSEQID)
            .rEMARKS(DEFAULT_R_EMARKS)
            .rESENDBY(DEFAULT_R_ESENDBY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MESSAGESSMS createUpdatedEntity() {
        return new MESSAGESSMS()
            .tRNDATETIME(UPDATED_T_RNDATETIME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .tRANSACTIONNO(UPDATED_T_RANSACTIONNO)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .mESSAGE(UPDATED_M_ESSAGE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRIALS(UPDATED_T_RIALS)
            .pRIORITY(UPDATED_P_RIORITY)
            .rESPONSECODE(UPDATED_R_ESPONSECODE)
            .rESPONSEMSG(UPDATED_R_ESPONSEMSG)
            .sENT(UPDATED_S_ENT)
            .dELIVERED(UPDATED_D_ELIVERED)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .eRROREXCEPTION(UPDATED_E_RROREXCEPTION)
            .dATECREATED(UPDATED_D_ATECREATED)
            .dATESENT(UPDATED_D_ATESENT)
            .rTPSREQTIME(UPDATED_R_TPSREQTIME)
            .fXGENERATED(UPDATED_F_XGENERATED)
            .tAXPROCESSED(UPDATED_T_AXPROCESSED)
            .bATCHNUMBER(UPDATED_B_ATCHNUMBER)
            .bATCHNUMBERTAX(UPDATED_B_ATCHNUMBERTAX)
            .rESPONSETIME(UPDATED_R_ESPONSETIME)
            .pDUSEQID(UPDATED_P_DUSEQID)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESENDBY(UPDATED_R_ESENDBY);
    }

    @BeforeEach
    public void initTest() {
        mESSAGESSMS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMESSAGESSMS != null) {
            mESSAGESSMSRepository.delete(insertedMESSAGESSMS);
            insertedMESSAGESSMS = null;
        }
    }

    @Test
    @Transactional
    void createMESSAGESSMS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MESSAGESSMS
        var returnedMESSAGESSMS = om.readValue(
            restMESSAGESSMSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGESSMS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MESSAGESSMS.class
        );

        // Validate the MESSAGESSMS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMESSAGESSMSUpdatableFieldsEquals(returnedMESSAGESSMS, getPersistedMESSAGESSMS(returnedMESSAGESSMS));

        insertedMESSAGESSMS = returnedMESSAGESSMS;
    }

    @Test
    @Transactional
    void createMESSAGESSMSWithExistingId() throws Exception {
        // Create the MESSAGESSMS with an existing ID
        mESSAGESSMS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMESSAGESSMSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGESSMS)))
            .andExpect(status().isBadRequest());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMESSAGESSMS() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        // Get all the mESSAGESSMSList
        restMESSAGESSMSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mESSAGESSMS.getId().intValue())))
            .andExpect(jsonPath("$.[*].tRNDATETIME").value(hasItem(DEFAULT_T_RNDATETIME.toString())))
            .andExpect(jsonPath("$.[*].pHONENUMBER").value(hasItem(DEFAULT_P_HONENUMBER)))
            .andExpect(jsonPath("$.[*].tRANSACTIONNO").value(hasItem(DEFAULT_T_RANSACTIONNO)))
            .andExpect(jsonPath("$.[*].aCCOUNTNUMBER").value(hasItem(DEFAULT_A_CCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].mESSAGE").value(hasItem(DEFAULT_M_ESSAGE)))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].tRIALS").value(hasItem(DEFAULT_T_RIALS.intValue())))
            .andExpect(jsonPath("$.[*].pRIORITY").value(hasItem(DEFAULT_P_RIORITY.intValue())))
            .andExpect(jsonPath("$.[*].rESPONSECODE").value(hasItem(DEFAULT_R_ESPONSECODE)))
            .andExpect(jsonPath("$.[*].rESPONSEMSG").value(hasItem(DEFAULT_R_ESPONSEMSG)))
            .andExpect(jsonPath("$.[*].sENT").value(hasItem(DEFAULT_S_ENT.intValue())))
            .andExpect(jsonPath("$.[*].dELIVERED").value(hasItem(DEFAULT_D_ELIVERED.intValue())))
            .andExpect(jsonPath("$.[*].tXNTYPE").value(hasItem(DEFAULT_T_XNTYPE)))
            .andExpect(jsonPath("$.[*].eRROREXCEPTION").value(hasItem(DEFAULT_E_RROREXCEPTION.intValue())))
            .andExpect(jsonPath("$.[*].dATECREATED").value(hasItem(DEFAULT_D_ATECREATED.toString())))
            .andExpect(jsonPath("$.[*].dATESENT").value(hasItem(DEFAULT_D_ATESENT)))
            .andExpect(jsonPath("$.[*].rTPSREQTIME").value(hasItem(DEFAULT_R_TPSREQTIME)))
            .andExpect(jsonPath("$.[*].fXGENERATED").value(hasItem(DEFAULT_F_XGENERATED)))
            .andExpect(jsonPath("$.[*].tAXPROCESSED").value(hasItem(DEFAULT_T_AXPROCESSED.intValue())))
            .andExpect(jsonPath("$.[*].bATCHNUMBER").value(hasItem(DEFAULT_B_ATCHNUMBER)))
            .andExpect(jsonPath("$.[*].bATCHNUMBERTAX").value(hasItem(DEFAULT_B_ATCHNUMBERTAX)))
            .andExpect(jsonPath("$.[*].rESPONSETIME").value(hasItem(DEFAULT_R_ESPONSETIME)))
            .andExpect(jsonPath("$.[*].pDUSEQID").value(hasItem(DEFAULT_P_DUSEQID)))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].rESENDBY").value(hasItem(DEFAULT_R_ESENDBY)));
    }

    @Test
    @Transactional
    void getMESSAGESSMS() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        // Get the mESSAGESSMS
        restMESSAGESSMSMockMvc
            .perform(get(ENTITY_API_URL_ID, mESSAGESSMS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mESSAGESSMS.getId().intValue()))
            .andExpect(jsonPath("$.tRNDATETIME").value(DEFAULT_T_RNDATETIME.toString()))
            .andExpect(jsonPath("$.pHONENUMBER").value(DEFAULT_P_HONENUMBER))
            .andExpect(jsonPath("$.tRANSACTIONNO").value(DEFAULT_T_RANSACTIONNO))
            .andExpect(jsonPath("$.aCCOUNTNUMBER").value(DEFAULT_A_CCOUNTNUMBER))
            .andExpect(jsonPath("$.mESSAGE").value(DEFAULT_M_ESSAGE))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.tRIALS").value(DEFAULT_T_RIALS.intValue()))
            .andExpect(jsonPath("$.pRIORITY").value(DEFAULT_P_RIORITY.intValue()))
            .andExpect(jsonPath("$.rESPONSECODE").value(DEFAULT_R_ESPONSECODE))
            .andExpect(jsonPath("$.rESPONSEMSG").value(DEFAULT_R_ESPONSEMSG))
            .andExpect(jsonPath("$.sENT").value(DEFAULT_S_ENT.intValue()))
            .andExpect(jsonPath("$.dELIVERED").value(DEFAULT_D_ELIVERED.intValue()))
            .andExpect(jsonPath("$.tXNTYPE").value(DEFAULT_T_XNTYPE))
            .andExpect(jsonPath("$.eRROREXCEPTION").value(DEFAULT_E_RROREXCEPTION.intValue()))
            .andExpect(jsonPath("$.dATECREATED").value(DEFAULT_D_ATECREATED.toString()))
            .andExpect(jsonPath("$.dATESENT").value(DEFAULT_D_ATESENT))
            .andExpect(jsonPath("$.rTPSREQTIME").value(DEFAULT_R_TPSREQTIME))
            .andExpect(jsonPath("$.fXGENERATED").value(DEFAULT_F_XGENERATED))
            .andExpect(jsonPath("$.tAXPROCESSED").value(DEFAULT_T_AXPROCESSED.intValue()))
            .andExpect(jsonPath("$.bATCHNUMBER").value(DEFAULT_B_ATCHNUMBER))
            .andExpect(jsonPath("$.bATCHNUMBERTAX").value(DEFAULT_B_ATCHNUMBERTAX))
            .andExpect(jsonPath("$.rESPONSETIME").value(DEFAULT_R_ESPONSETIME))
            .andExpect(jsonPath("$.pDUSEQID").value(DEFAULT_P_DUSEQID))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.rESENDBY").value(DEFAULT_R_ESENDBY));
    }

    @Test
    @Transactional
    void getNonExistingMESSAGESSMS() throws Exception {
        // Get the mESSAGESSMS
        restMESSAGESSMSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMESSAGESSMS() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGESSMS
        MESSAGESSMS updatedMESSAGESSMS = mESSAGESSMSRepository.findById(mESSAGESSMS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMESSAGESSMS are not directly saved in db
        em.detach(updatedMESSAGESSMS);
        updatedMESSAGESSMS
            .tRNDATETIME(UPDATED_T_RNDATETIME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .tRANSACTIONNO(UPDATED_T_RANSACTIONNO)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .mESSAGE(UPDATED_M_ESSAGE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRIALS(UPDATED_T_RIALS)
            .pRIORITY(UPDATED_P_RIORITY)
            .rESPONSECODE(UPDATED_R_ESPONSECODE)
            .rESPONSEMSG(UPDATED_R_ESPONSEMSG)
            .sENT(UPDATED_S_ENT)
            .dELIVERED(UPDATED_D_ELIVERED)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .eRROREXCEPTION(UPDATED_E_RROREXCEPTION)
            .dATECREATED(UPDATED_D_ATECREATED)
            .dATESENT(UPDATED_D_ATESENT)
            .rTPSREQTIME(UPDATED_R_TPSREQTIME)
            .fXGENERATED(UPDATED_F_XGENERATED)
            .tAXPROCESSED(UPDATED_T_AXPROCESSED)
            .bATCHNUMBER(UPDATED_B_ATCHNUMBER)
            .bATCHNUMBERTAX(UPDATED_B_ATCHNUMBERTAX)
            .rESPONSETIME(UPDATED_R_ESPONSETIME)
            .pDUSEQID(UPDATED_P_DUSEQID)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESENDBY(UPDATED_R_ESENDBY);

        restMESSAGESSMSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMESSAGESSMS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMESSAGESSMS))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMESSAGESSMSToMatchAllProperties(updatedMESSAGESSMS);
    }

    @Test
    @Transactional
    void putNonExistingMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mESSAGESSMS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mESSAGESSMS))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mESSAGESSMS))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mESSAGESSMS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMESSAGESSMSWithPatch() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGESSMS using partial update
        MESSAGESSMS partialUpdatedMESSAGESSMS = new MESSAGESSMS();
        partialUpdatedMESSAGESSMS.setId(mESSAGESSMS.getId());

        partialUpdatedMESSAGESSMS
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .mESSAGE(UPDATED_M_ESSAGE)
            .cHANNEL(UPDATED_C_HANNEL)
            .pRIORITY(UPDATED_P_RIORITY)
            .rESPONSECODE(UPDATED_R_ESPONSECODE)
            .dELIVERED(UPDATED_D_ELIVERED)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .dATESENT(UPDATED_D_ATESENT)
            .fXGENERATED(UPDATED_F_XGENERATED)
            .tAXPROCESSED(UPDATED_T_AXPROCESSED)
            .bATCHNUMBER(UPDATED_B_ATCHNUMBER)
            .rESPONSETIME(UPDATED_R_ESPONSETIME)
            .rEMARKS(UPDATED_R_EMARKS);

        restMESSAGESSMSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMESSAGESSMS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMESSAGESSMS))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGESSMS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMESSAGESSMSUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMESSAGESSMS, mESSAGESSMS),
            getPersistedMESSAGESSMS(mESSAGESSMS)
        );
    }

    @Test
    @Transactional
    void fullUpdateMESSAGESSMSWithPatch() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mESSAGESSMS using partial update
        MESSAGESSMS partialUpdatedMESSAGESSMS = new MESSAGESSMS();
        partialUpdatedMESSAGESSMS.setId(mESSAGESSMS.getId());

        partialUpdatedMESSAGESSMS
            .tRNDATETIME(UPDATED_T_RNDATETIME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .tRANSACTIONNO(UPDATED_T_RANSACTIONNO)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .mESSAGE(UPDATED_M_ESSAGE)
            .cHANNEL(UPDATED_C_HANNEL)
            .tRIALS(UPDATED_T_RIALS)
            .pRIORITY(UPDATED_P_RIORITY)
            .rESPONSECODE(UPDATED_R_ESPONSECODE)
            .rESPONSEMSG(UPDATED_R_ESPONSEMSG)
            .sENT(UPDATED_S_ENT)
            .dELIVERED(UPDATED_D_ELIVERED)
            .tXNTYPE(UPDATED_T_XNTYPE)
            .eRROREXCEPTION(UPDATED_E_RROREXCEPTION)
            .dATECREATED(UPDATED_D_ATECREATED)
            .dATESENT(UPDATED_D_ATESENT)
            .rTPSREQTIME(UPDATED_R_TPSREQTIME)
            .fXGENERATED(UPDATED_F_XGENERATED)
            .tAXPROCESSED(UPDATED_T_AXPROCESSED)
            .bATCHNUMBER(UPDATED_B_ATCHNUMBER)
            .bATCHNUMBERTAX(UPDATED_B_ATCHNUMBERTAX)
            .rESPONSETIME(UPDATED_R_ESPONSETIME)
            .pDUSEQID(UPDATED_P_DUSEQID)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESENDBY(UPDATED_R_ESENDBY);

        restMESSAGESSMSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMESSAGESSMS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMESSAGESSMS))
            )
            .andExpect(status().isOk());

        // Validate the MESSAGESSMS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMESSAGESSMSUpdatableFieldsEquals(partialUpdatedMESSAGESSMS, getPersistedMESSAGESSMS(partialUpdatedMESSAGESSMS));
    }

    @Test
    @Transactional
    void patchNonExistingMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mESSAGESSMS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mESSAGESSMS))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mESSAGESSMS))
            )
            .andExpect(status().isBadRequest());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMESSAGESSMS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mESSAGESSMS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMESSAGESSMSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(mESSAGESSMS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MESSAGESSMS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMESSAGESSMS() throws Exception {
        // Initialize the database
        insertedMESSAGESSMS = mESSAGESSMSRepository.saveAndFlush(mESSAGESSMS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the mESSAGESSMS
        restMESSAGESSMSMockMvc
            .perform(delete(ENTITY_API_URL_ID, mESSAGESSMS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return mESSAGESSMSRepository.count();
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

    protected MESSAGESSMS getPersistedMESSAGESSMS(MESSAGESSMS mESSAGESSMS) {
        return mESSAGESSMSRepository.findById(mESSAGESSMS.getId()).orElseThrow();
    }

    protected void assertPersistedMESSAGESSMSToMatchAllProperties(MESSAGESSMS expectedMESSAGESSMS) {
        assertMESSAGESSMSAllPropertiesEquals(expectedMESSAGESSMS, getPersistedMESSAGESSMS(expectedMESSAGESSMS));
    }

    protected void assertPersistedMESSAGESSMSToMatchUpdatableProperties(MESSAGESSMS expectedMESSAGESSMS) {
        assertMESSAGESSMSAllUpdatablePropertiesEquals(expectedMESSAGESSMS, getPersistedMESSAGESSMS(expectedMESSAGESSMS));
    }
}
