package com.istl.app.web.rest;

import static com.istl.app.domain.LINKEDACCOUNTSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.LINKEDACCOUNTS;
import com.istl.app.repository.LINKEDACCOUNTSRepository;
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
 * Integration tests for the {@link LINKEDACCOUNTSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LINKEDACCOUNTSResourceIT {

    private static final String DEFAULT_A_COUNTNAME = "AAAAAAAAAA";
    private static final String UPDATED_A_COUNTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTCLASS = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTCLASS = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTCURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTCURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_C_IF = "AAAAAAAAAA";
    private static final String UPDATED_C_IF = "BBBBBBBBBB";

    private static final Instant DEFAULT_T_IMELINKED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_T_IMELINKED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_P_HONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_P_HONENUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_A_PPROVED = "A";
    private static final String UPDATED_A_PPROVED = "B";

    private static final String DEFAULT_D_ECLINED = "A";
    private static final String UPDATED_D_ECLINED = "B";

    private static final Instant DEFAULT_D_ECLINEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ECLINEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_L_INKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_L_INKEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_L_INKED = "A";
    private static final String UPDATED_L_INKED = "B";

    private static final String DEFAULT_A_CTIVE = "A";
    private static final String UPDATED_A_CTIVE = "B";

    private static final String DEFAULT_D_ELINKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ELINKEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ELINKEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ELINKEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_D_ELINKED = "A";
    private static final String UPDATED_D_ELINKED = "B";

    private static final String DEFAULT_A_CCOUNTALIAS = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTALIAS = "BBBBBBBBBB";

    private static final String DEFAULT_S_HORTCUTS = "AAAAAAAAAA";
    private static final String UPDATED_S_HORTCUTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/linkedaccounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LINKEDACCOUNTSRepository lINKEDACCOUNTSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLINKEDACCOUNTSMockMvc;

    private LINKEDACCOUNTS lINKEDACCOUNTS;

    private LINKEDACCOUNTS insertedLINKEDACCOUNTS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LINKEDACCOUNTS createEntity() {
        return new LINKEDACCOUNTS()
            .aCOUNTNAME(DEFAULT_A_COUNTNAME)
            .aCCOUNTCLASS(DEFAULT_A_CCOUNTCLASS)
            .aCCOUNTCURRENCY(DEFAULT_A_CCOUNTCURRENCY)
            .aCCOUNTNUMBER(DEFAULT_A_CCOUNTNUMBER)
            .cIF(DEFAULT_C_IF)
            .tIMELINKED(DEFAULT_T_IMELINKED)
            .pHONENUMBER(DEFAULT_P_HONENUMBER)
            .aPPROVEDON(DEFAULT_A_PPROVEDON)
            .aPPROVED(DEFAULT_A_PPROVED)
            .dECLINED(DEFAULT_D_ECLINED)
            .dECLINEDON(DEFAULT_D_ECLINEDON)
            .rEMARKS(DEFAULT_R_EMARKS)
            .lINKEDBY(DEFAULT_L_INKEDBY)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .lINKED(DEFAULT_L_INKED)
            .aCTIVE(DEFAULT_A_CTIVE)
            .dELINKEDBY(DEFAULT_D_ELINKEDBY)
            .dELINKEDON(DEFAULT_D_ELINKEDON)
            .dELINKED(DEFAULT_D_ELINKED)
            .aCCOUNTALIAS(DEFAULT_A_CCOUNTALIAS)
            .sHORTCUTS(DEFAULT_S_HORTCUTS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LINKEDACCOUNTS createUpdatedEntity() {
        return new LINKEDACCOUNTS()
            .aCOUNTNAME(UPDATED_A_COUNTNAME)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .aCCOUNTCURRENCY(UPDATED_A_CCOUNTCURRENCY)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDON(UPDATED_D_ECLINEDON)
            .rEMARKS(UPDATED_R_EMARKS)
            .lINKEDBY(UPDATED_L_INKEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .lINKED(UPDATED_L_INKED)
            .aCTIVE(UPDATED_A_CTIVE)
            .dELINKEDBY(UPDATED_D_ELINKEDBY)
            .dELINKEDON(UPDATED_D_ELINKEDON)
            .dELINKED(UPDATED_D_ELINKED)
            .aCCOUNTALIAS(UPDATED_A_CCOUNTALIAS)
            .sHORTCUTS(UPDATED_S_HORTCUTS);
    }

    @BeforeEach
    public void initTest() {
        lINKEDACCOUNTS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLINKEDACCOUNTS != null) {
            lINKEDACCOUNTSRepository.delete(insertedLINKEDACCOUNTS);
            insertedLINKEDACCOUNTS = null;
        }
    }

    @Test
    @Transactional
    void createLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LINKEDACCOUNTS
        var returnedLINKEDACCOUNTS = om.readValue(
            restLINKEDACCOUNTSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lINKEDACCOUNTS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LINKEDACCOUNTS.class
        );

        // Validate the LINKEDACCOUNTS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLINKEDACCOUNTSUpdatableFieldsEquals(returnedLINKEDACCOUNTS, getPersistedLINKEDACCOUNTS(returnedLINKEDACCOUNTS));

        insertedLINKEDACCOUNTS = returnedLINKEDACCOUNTS;
    }

    @Test
    @Transactional
    void createLINKEDACCOUNTSWithExistingId() throws Exception {
        // Create the LINKEDACCOUNTS with an existing ID
        lINKEDACCOUNTS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLINKEDACCOUNTSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lINKEDACCOUNTS)))
            .andExpect(status().isBadRequest());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkrEMARKSIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lINKEDACCOUNTS.setrEMARKS(null);

        // Create the LINKEDACCOUNTS, which fails.

        restLINKEDACCOUNTSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lINKEDACCOUNTS)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLINKEDACCOUNTS() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        // Get all the lINKEDACCOUNTSList
        restLINKEDACCOUNTSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lINKEDACCOUNTS.getId().intValue())))
            .andExpect(jsonPath("$.[*].aCOUNTNAME").value(hasItem(DEFAULT_A_COUNTNAME)))
            .andExpect(jsonPath("$.[*].aCCOUNTCLASS").value(hasItem(DEFAULT_A_CCOUNTCLASS)))
            .andExpect(jsonPath("$.[*].aCCOUNTCURRENCY").value(hasItem(DEFAULT_A_CCOUNTCURRENCY)))
            .andExpect(jsonPath("$.[*].aCCOUNTNUMBER").value(hasItem(DEFAULT_A_CCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].cIF").value(hasItem(DEFAULT_C_IF)))
            .andExpect(jsonPath("$.[*].tIMELINKED").value(hasItem(DEFAULT_T_IMELINKED.toString())))
            .andExpect(jsonPath("$.[*].pHONENUMBER").value(hasItem(DEFAULT_P_HONENUMBER)))
            .andExpect(jsonPath("$.[*].aPPROVEDON").value(hasItem(DEFAULT_A_PPROVEDON.toString())))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED)))
            .andExpect(jsonPath("$.[*].dECLINED").value(hasItem(DEFAULT_D_ECLINED)))
            .andExpect(jsonPath("$.[*].dECLINEDON").value(hasItem(DEFAULT_D_ECLINEDON.toString())))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].lINKEDBY").value(hasItem(DEFAULT_L_INKEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].lINKED").value(hasItem(DEFAULT_L_INKED)))
            .andExpect(jsonPath("$.[*].aCTIVE").value(hasItem(DEFAULT_A_CTIVE)))
            .andExpect(jsonPath("$.[*].dELINKEDBY").value(hasItem(DEFAULT_D_ELINKEDBY)))
            .andExpect(jsonPath("$.[*].dELINKEDON").value(hasItem(DEFAULT_D_ELINKEDON.toString())))
            .andExpect(jsonPath("$.[*].dELINKED").value(hasItem(DEFAULT_D_ELINKED)))
            .andExpect(jsonPath("$.[*].aCCOUNTALIAS").value(hasItem(DEFAULT_A_CCOUNTALIAS)))
            .andExpect(jsonPath("$.[*].sHORTCUTS").value(hasItem(DEFAULT_S_HORTCUTS)));
    }

    @Test
    @Transactional
    void getLINKEDACCOUNTS() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        // Get the lINKEDACCOUNTS
        restLINKEDACCOUNTSMockMvc
            .perform(get(ENTITY_API_URL_ID, lINKEDACCOUNTS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lINKEDACCOUNTS.getId().intValue()))
            .andExpect(jsonPath("$.aCOUNTNAME").value(DEFAULT_A_COUNTNAME))
            .andExpect(jsonPath("$.aCCOUNTCLASS").value(DEFAULT_A_CCOUNTCLASS))
            .andExpect(jsonPath("$.aCCOUNTCURRENCY").value(DEFAULT_A_CCOUNTCURRENCY))
            .andExpect(jsonPath("$.aCCOUNTNUMBER").value(DEFAULT_A_CCOUNTNUMBER))
            .andExpect(jsonPath("$.cIF").value(DEFAULT_C_IF))
            .andExpect(jsonPath("$.tIMELINKED").value(DEFAULT_T_IMELINKED.toString()))
            .andExpect(jsonPath("$.pHONENUMBER").value(DEFAULT_P_HONENUMBER))
            .andExpect(jsonPath("$.aPPROVEDON").value(DEFAULT_A_PPROVEDON.toString()))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED))
            .andExpect(jsonPath("$.dECLINED").value(DEFAULT_D_ECLINED))
            .andExpect(jsonPath("$.dECLINEDON").value(DEFAULT_D_ECLINEDON.toString()))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.lINKEDBY").value(DEFAULT_L_INKEDBY))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.lINKED").value(DEFAULT_L_INKED))
            .andExpect(jsonPath("$.aCTIVE").value(DEFAULT_A_CTIVE))
            .andExpect(jsonPath("$.dELINKEDBY").value(DEFAULT_D_ELINKEDBY))
            .andExpect(jsonPath("$.dELINKEDON").value(DEFAULT_D_ELINKEDON.toString()))
            .andExpect(jsonPath("$.dELINKED").value(DEFAULT_D_ELINKED))
            .andExpect(jsonPath("$.aCCOUNTALIAS").value(DEFAULT_A_CCOUNTALIAS))
            .andExpect(jsonPath("$.sHORTCUTS").value(DEFAULT_S_HORTCUTS));
    }

    @Test
    @Transactional
    void getNonExistingLINKEDACCOUNTS() throws Exception {
        // Get the lINKEDACCOUNTS
        restLINKEDACCOUNTSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLINKEDACCOUNTS() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lINKEDACCOUNTS
        LINKEDACCOUNTS updatedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.findById(lINKEDACCOUNTS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLINKEDACCOUNTS are not directly saved in db
        em.detach(updatedLINKEDACCOUNTS);
        updatedLINKEDACCOUNTS
            .aCOUNTNAME(UPDATED_A_COUNTNAME)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .aCCOUNTCURRENCY(UPDATED_A_CCOUNTCURRENCY)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDON(UPDATED_D_ECLINEDON)
            .rEMARKS(UPDATED_R_EMARKS)
            .lINKEDBY(UPDATED_L_INKEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .lINKED(UPDATED_L_INKED)
            .aCTIVE(UPDATED_A_CTIVE)
            .dELINKEDBY(UPDATED_D_ELINKEDBY)
            .dELINKEDON(UPDATED_D_ELINKEDON)
            .dELINKED(UPDATED_D_ELINKED)
            .aCCOUNTALIAS(UPDATED_A_CCOUNTALIAS)
            .sHORTCUTS(UPDATED_S_HORTCUTS);

        restLINKEDACCOUNTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLINKEDACCOUNTS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLINKEDACCOUNTS))
            )
            .andExpect(status().isOk());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLINKEDACCOUNTSToMatchAllProperties(updatedLINKEDACCOUNTS);
    }

    @Test
    @Transactional
    void putNonExistingLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lINKEDACCOUNTS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(lINKEDACCOUNTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(lINKEDACCOUNTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lINKEDACCOUNTS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLINKEDACCOUNTSWithPatch() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lINKEDACCOUNTS using partial update
        LINKEDACCOUNTS partialUpdatedLINKEDACCOUNTS = new LINKEDACCOUNTS();
        partialUpdatedLINKEDACCOUNTS.setId(lINKEDACCOUNTS.getId());

        partialUpdatedLINKEDACCOUNTS
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .aPPROVED(UPDATED_A_PPROVED)
            .lINKEDBY(UPDATED_L_INKEDBY)
            .dELINKEDON(UPDATED_D_ELINKEDON)
            .dELINKED(UPDATED_D_ELINKED)
            .aCCOUNTALIAS(UPDATED_A_CCOUNTALIAS);

        restLINKEDACCOUNTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLINKEDACCOUNTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLINKEDACCOUNTS))
            )
            .andExpect(status().isOk());

        // Validate the LINKEDACCOUNTS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLINKEDACCOUNTSUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLINKEDACCOUNTS, lINKEDACCOUNTS),
            getPersistedLINKEDACCOUNTS(lINKEDACCOUNTS)
        );
    }

    @Test
    @Transactional
    void fullUpdateLINKEDACCOUNTSWithPatch() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lINKEDACCOUNTS using partial update
        LINKEDACCOUNTS partialUpdatedLINKEDACCOUNTS = new LINKEDACCOUNTS();
        partialUpdatedLINKEDACCOUNTS.setId(lINKEDACCOUNTS.getId());

        partialUpdatedLINKEDACCOUNTS
            .aCOUNTNAME(UPDATED_A_COUNTNAME)
            .aCCOUNTCLASS(UPDATED_A_CCOUNTCLASS)
            .aCCOUNTCURRENCY(UPDATED_A_CCOUNTCURRENCY)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .cIF(UPDATED_C_IF)
            .tIMELINKED(UPDATED_T_IMELINKED)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .aPPROVED(UPDATED_A_PPROVED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDON(UPDATED_D_ECLINEDON)
            .rEMARKS(UPDATED_R_EMARKS)
            .lINKEDBY(UPDATED_L_INKEDBY)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .lINKED(UPDATED_L_INKED)
            .aCTIVE(UPDATED_A_CTIVE)
            .dELINKEDBY(UPDATED_D_ELINKEDBY)
            .dELINKEDON(UPDATED_D_ELINKEDON)
            .dELINKED(UPDATED_D_ELINKED)
            .aCCOUNTALIAS(UPDATED_A_CCOUNTALIAS)
            .sHORTCUTS(UPDATED_S_HORTCUTS);

        restLINKEDACCOUNTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLINKEDACCOUNTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLINKEDACCOUNTS))
            )
            .andExpect(status().isOk());

        // Validate the LINKEDACCOUNTS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLINKEDACCOUNTSUpdatableFieldsEquals(partialUpdatedLINKEDACCOUNTS, getPersistedLINKEDACCOUNTS(partialUpdatedLINKEDACCOUNTS));
    }

    @Test
    @Transactional
    void patchNonExistingLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lINKEDACCOUNTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(lINKEDACCOUNTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(lINKEDACCOUNTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLINKEDACCOUNTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lINKEDACCOUNTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLINKEDACCOUNTSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(lINKEDACCOUNTS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LINKEDACCOUNTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLINKEDACCOUNTS() throws Exception {
        // Initialize the database
        insertedLINKEDACCOUNTS = lINKEDACCOUNTSRepository.saveAndFlush(lINKEDACCOUNTS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the lINKEDACCOUNTS
        restLINKEDACCOUNTSMockMvc
            .perform(delete(ENTITY_API_URL_ID, lINKEDACCOUNTS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return lINKEDACCOUNTSRepository.count();
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

    protected LINKEDACCOUNTS getPersistedLINKEDACCOUNTS(LINKEDACCOUNTS lINKEDACCOUNTS) {
        return lINKEDACCOUNTSRepository.findById(lINKEDACCOUNTS.getId()).orElseThrow();
    }

    protected void assertPersistedLINKEDACCOUNTSToMatchAllProperties(LINKEDACCOUNTS expectedLINKEDACCOUNTS) {
        assertLINKEDACCOUNTSAllPropertiesEquals(expectedLINKEDACCOUNTS, getPersistedLINKEDACCOUNTS(expectedLINKEDACCOUNTS));
    }

    protected void assertPersistedLINKEDACCOUNTSToMatchUpdatableProperties(LINKEDACCOUNTS expectedLINKEDACCOUNTS) {
        assertLINKEDACCOUNTSAllUpdatablePropertiesEquals(expectedLINKEDACCOUNTS, getPersistedLINKEDACCOUNTS(expectedLINKEDACCOUNTS));
    }
}
