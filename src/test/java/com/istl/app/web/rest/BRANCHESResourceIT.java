package com.istl.app.web.rest;

import static com.istl.app.domain.BRANCHESAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.BRANCHES;
import com.istl.app.repository.BRANCHESRepository;
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
 * Integration tests for the {@link BRANCHESResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BRANCHESResourceIT {

    private static final String DEFAULT_B_RANCHNAME = "AAAAAAAAAA";
    private static final String UPDATED_B_RANCHNAME = "BBBBBBBBBB";

    private static final String DEFAULT_B_RANCHCODE = "AAA";
    private static final String UPDATED_B_RANCHCODE = "BBB";

    private static final Long DEFAULT_A_PPROVED = 1L;
    private static final Long UPDATED_A_PPROVED = 2L;

    private static final String DEFAULT_E_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_E_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_A_DDRESS = "AAAAAAAAAA";
    private static final String UPDATED_A_DDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_P_HONE = "AAAAAAAAAA";
    private static final String UPDATED_P_HONE = "BBBBBBBBBB";

    private static final String DEFAULT_L_OCATION = "AAAAAAAAAA";
    private static final String UPDATED_L_OCATION = "BBBBBBBBBB";

    private static final String DEFAULT_C_ONTACTPERSON = "AAAAAAAAAA";
    private static final String UPDATED_C_ONTACTPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_C_REATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_REATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDON = "AAAAAAA";
    private static final String UPDATED_A_PPROVEDON = "BBBBBBB";

    private static final String DEFAULT_C_HECKERREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_C_HECKERREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_D_ELETEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ELETEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ELETEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ELETEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_D_ELETEREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_D_ELETEREMARKS = "BBBBBBBBBB";

    private static final Long DEFAULT_D_ELETED = 1L;
    private static final Long UPDATED_D_ELETED = 2L;

    private static final Long DEFAULT_D_ECLINED = 1L;
    private static final Long UPDATED_D_ECLINED = 2L;

    private static final String DEFAULT_D_ECLINEDDON = "AAAAAAA";
    private static final String UPDATED_D_ECLINEDDON = "BBBBBBB";

    private static final String DEFAULT_D_ECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ECLINEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_S_ESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_S_ESSIONID = "BBBBBBBBBB";

    private static final Long DEFAULT_R_EWORKED = 1L;
    private static final Long UPDATED_R_EWORKED = 2L;

    private static final String DEFAULT_R_EWORKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EWORKEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_R_EWORKEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_EWORKEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_D_ISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_D_ISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_R_EGION = "AAAAAAAAAA";
    private static final String UPDATED_R_EGION = "BBBBBBBBBB";

    private static final String DEFAULT_R_EGIONNAME = "AAAAAAAAAA";
    private static final String UPDATED_R_EGIONNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_R_EPORTING = 1L;
    private static final Long UPDATED_R_EPORTING = 2L;

    private static final String ENTITY_API_URL = "/api/branches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BRANCHESRepository bRANCHESRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBRANCHESMockMvc;

    private BRANCHES bRANCHES;

    private BRANCHES insertedBRANCHES;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BRANCHES createEntity() {
        return new BRANCHES()
            .bRANCHNAME(DEFAULT_B_RANCHNAME)
            .bRANCHCODE(DEFAULT_B_RANCHCODE)
            .aPPROVED(DEFAULT_A_PPROVED)
            .eMAIL(DEFAULT_E_MAIL)
            .aDDRESS(DEFAULT_A_DDRESS)
            .pHONE(DEFAULT_P_HONE)
            .lOCATION(DEFAULT_L_OCATION)
            .cONTACTPERSON(DEFAULT_C_ONTACTPERSON)
            .rEMARKS(DEFAULT_R_EMARKS)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .cREATEDON(DEFAULT_C_REATEDON)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDON(DEFAULT_A_PPROVEDON)
            .cHECKERREMARKS(DEFAULT_C_HECKERREMARKS)
            .dELETEDBY(DEFAULT_D_ELETEDBY)
            .dELETEDON(DEFAULT_D_ELETEDON)
            .dELETEREMARKS(DEFAULT_D_ELETEREMARKS)
            .dELETED(DEFAULT_D_ELETED)
            .dECLINED(DEFAULT_D_ECLINED)
            .dECLINEDDON(DEFAULT_D_ECLINEDDON)
            .dECLINEDBY(DEFAULT_D_ECLINEDBY)
            .sESSIONID(DEFAULT_S_ESSIONID)
            .rEWORKED(DEFAULT_R_EWORKED)
            .rEWORKEDBY(DEFAULT_R_EWORKEDBY)
            .rEWORKEDON(DEFAULT_R_EWORKEDON)
            .dISTRICT(DEFAULT_D_ISTRICT)
            .rEGION(DEFAULT_R_EGION)
            .rEGIONNAME(DEFAULT_R_EGIONNAME)
            .rEPORTING(DEFAULT_R_EPORTING);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BRANCHES createUpdatedEntity() {
        return new BRANCHES()
            .bRANCHNAME(UPDATED_B_RANCHNAME)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .aPPROVED(UPDATED_A_PPROVED)
            .eMAIL(UPDATED_E_MAIL)
            .aDDRESS(UPDATED_A_DDRESS)
            .pHONE(UPDATED_P_HONE)
            .lOCATION(UPDATED_L_OCATION)
            .cONTACTPERSON(UPDATED_C_ONTACTPERSON)
            .rEMARKS(UPDATED_R_EMARKS)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .cREATEDON(UPDATED_C_REATEDON)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .dELETEDON(UPDATED_D_ELETEDON)
            .dELETEREMARKS(UPDATED_D_ELETEREMARKS)
            .dELETED(UPDATED_D_ELETED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDDON(UPDATED_D_ECLINEDDON)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKED(UPDATED_R_EWORKED)
            .rEWORKEDBY(UPDATED_R_EWORKEDBY)
            .rEWORKEDON(UPDATED_R_EWORKEDON)
            .dISTRICT(UPDATED_D_ISTRICT)
            .rEGION(UPDATED_R_EGION)
            .rEGIONNAME(UPDATED_R_EGIONNAME)
            .rEPORTING(UPDATED_R_EPORTING);
    }

    @BeforeEach
    public void initTest() {
        bRANCHES = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBRANCHES != null) {
            bRANCHESRepository.delete(insertedBRANCHES);
            insertedBRANCHES = null;
        }
    }

    @Test
    @Transactional
    void createBRANCHES() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BRANCHES
        var returnedBRANCHES = om.readValue(
            restBRANCHESMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BRANCHES.class
        );

        // Validate the BRANCHES in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBRANCHESUpdatableFieldsEquals(returnedBRANCHES, getPersistedBRANCHES(returnedBRANCHES));

        insertedBRANCHES = returnedBRANCHES;
    }

    @Test
    @Transactional
    void createBRANCHESWithExistingId() throws Exception {
        // Create the BRANCHES with an existing ID
        bRANCHES.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBRANCHESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES)))
            .andExpect(status().isBadRequest());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkbRANCHCODEIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        bRANCHES.setbRANCHCODE(null);

        // Create the BRANCHES, which fails.

        restBRANCHESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checklOCATIONIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        bRANCHES.setlOCATION(null);

        // Create the BRANCHES, which fails.

        restBRANCHESMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBRANCHES() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        // Get all the bRANCHESList
        restBRANCHESMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bRANCHES.getId().intValue())))
            .andExpect(jsonPath("$.[*].bRANCHNAME").value(hasItem(DEFAULT_B_RANCHNAME)))
            .andExpect(jsonPath("$.[*].bRANCHCODE").value(hasItem(DEFAULT_B_RANCHCODE)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED.intValue())))
            .andExpect(jsonPath("$.[*].eMAIL").value(hasItem(DEFAULT_E_MAIL)))
            .andExpect(jsonPath("$.[*].aDDRESS").value(hasItem(DEFAULT_A_DDRESS)))
            .andExpect(jsonPath("$.[*].pHONE").value(hasItem(DEFAULT_P_HONE)))
            .andExpect(jsonPath("$.[*].lOCATION").value(hasItem(DEFAULT_L_OCATION)))
            .andExpect(jsonPath("$.[*].cONTACTPERSON").value(hasItem(DEFAULT_C_ONTACTPERSON)))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].cREATEDON").value(hasItem(DEFAULT_C_REATEDON.toString())))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDON").value(hasItem(DEFAULT_A_PPROVEDON)))
            .andExpect(jsonPath("$.[*].cHECKERREMARKS").value(hasItem(DEFAULT_C_HECKERREMARKS)))
            .andExpect(jsonPath("$.[*].dELETEDBY").value(hasItem(DEFAULT_D_ELETEDBY)))
            .andExpect(jsonPath("$.[*].dELETEDON").value(hasItem(DEFAULT_D_ELETEDON.toString())))
            .andExpect(jsonPath("$.[*].dELETEREMARKS").value(hasItem(DEFAULT_D_ELETEREMARKS)))
            .andExpect(jsonPath("$.[*].dELETED").value(hasItem(DEFAULT_D_ELETED.intValue())))
            .andExpect(jsonPath("$.[*].dECLINED").value(hasItem(DEFAULT_D_ECLINED.intValue())))
            .andExpect(jsonPath("$.[*].dECLINEDDON").value(hasItem(DEFAULT_D_ECLINEDDON)))
            .andExpect(jsonPath("$.[*].dECLINEDBY").value(hasItem(DEFAULT_D_ECLINEDBY)))
            .andExpect(jsonPath("$.[*].sESSIONID").value(hasItem(DEFAULT_S_ESSIONID)))
            .andExpect(jsonPath("$.[*].rEWORKED").value(hasItem(DEFAULT_R_EWORKED.intValue())))
            .andExpect(jsonPath("$.[*].rEWORKEDBY").value(hasItem(DEFAULT_R_EWORKEDBY)))
            .andExpect(jsonPath("$.[*].rEWORKEDON").value(hasItem(DEFAULT_R_EWORKEDON.toString())))
            .andExpect(jsonPath("$.[*].dISTRICT").value(hasItem(DEFAULT_D_ISTRICT)))
            .andExpect(jsonPath("$.[*].rEGION").value(hasItem(DEFAULT_R_EGION)))
            .andExpect(jsonPath("$.[*].rEGIONNAME").value(hasItem(DEFAULT_R_EGIONNAME)))
            .andExpect(jsonPath("$.[*].rEPORTING").value(hasItem(DEFAULT_R_EPORTING.intValue())));
    }

    @Test
    @Transactional
    void getBRANCHES() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        // Get the bRANCHES
        restBRANCHESMockMvc
            .perform(get(ENTITY_API_URL_ID, bRANCHES.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bRANCHES.getId().intValue()))
            .andExpect(jsonPath("$.bRANCHNAME").value(DEFAULT_B_RANCHNAME))
            .andExpect(jsonPath("$.bRANCHCODE").value(DEFAULT_B_RANCHCODE))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED.intValue()))
            .andExpect(jsonPath("$.eMAIL").value(DEFAULT_E_MAIL))
            .andExpect(jsonPath("$.aDDRESS").value(DEFAULT_A_DDRESS))
            .andExpect(jsonPath("$.pHONE").value(DEFAULT_P_HONE))
            .andExpect(jsonPath("$.lOCATION").value(DEFAULT_L_OCATION))
            .andExpect(jsonPath("$.cONTACTPERSON").value(DEFAULT_C_ONTACTPERSON))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.cREATEDON").value(DEFAULT_C_REATEDON.toString()))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDON").value(DEFAULT_A_PPROVEDON))
            .andExpect(jsonPath("$.cHECKERREMARKS").value(DEFAULT_C_HECKERREMARKS))
            .andExpect(jsonPath("$.dELETEDBY").value(DEFAULT_D_ELETEDBY))
            .andExpect(jsonPath("$.dELETEDON").value(DEFAULT_D_ELETEDON.toString()))
            .andExpect(jsonPath("$.dELETEREMARKS").value(DEFAULT_D_ELETEREMARKS))
            .andExpect(jsonPath("$.dELETED").value(DEFAULT_D_ELETED.intValue()))
            .andExpect(jsonPath("$.dECLINED").value(DEFAULT_D_ECLINED.intValue()))
            .andExpect(jsonPath("$.dECLINEDDON").value(DEFAULT_D_ECLINEDDON))
            .andExpect(jsonPath("$.dECLINEDBY").value(DEFAULT_D_ECLINEDBY))
            .andExpect(jsonPath("$.sESSIONID").value(DEFAULT_S_ESSIONID))
            .andExpect(jsonPath("$.rEWORKED").value(DEFAULT_R_EWORKED.intValue()))
            .andExpect(jsonPath("$.rEWORKEDBY").value(DEFAULT_R_EWORKEDBY))
            .andExpect(jsonPath("$.rEWORKEDON").value(DEFAULT_R_EWORKEDON.toString()))
            .andExpect(jsonPath("$.dISTRICT").value(DEFAULT_D_ISTRICT))
            .andExpect(jsonPath("$.rEGION").value(DEFAULT_R_EGION))
            .andExpect(jsonPath("$.rEGIONNAME").value(DEFAULT_R_EGIONNAME))
            .andExpect(jsonPath("$.rEPORTING").value(DEFAULT_R_EPORTING.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingBRANCHES() throws Exception {
        // Get the bRANCHES
        restBRANCHESMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBRANCHES() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bRANCHES
        BRANCHES updatedBRANCHES = bRANCHESRepository.findById(bRANCHES.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBRANCHES are not directly saved in db
        em.detach(updatedBRANCHES);
        updatedBRANCHES
            .bRANCHNAME(UPDATED_B_RANCHNAME)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .aPPROVED(UPDATED_A_PPROVED)
            .eMAIL(UPDATED_E_MAIL)
            .aDDRESS(UPDATED_A_DDRESS)
            .pHONE(UPDATED_P_HONE)
            .lOCATION(UPDATED_L_OCATION)
            .cONTACTPERSON(UPDATED_C_ONTACTPERSON)
            .rEMARKS(UPDATED_R_EMARKS)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .cREATEDON(UPDATED_C_REATEDON)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .dELETEDON(UPDATED_D_ELETEDON)
            .dELETEREMARKS(UPDATED_D_ELETEREMARKS)
            .dELETED(UPDATED_D_ELETED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDDON(UPDATED_D_ECLINEDDON)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKED(UPDATED_R_EWORKED)
            .rEWORKEDBY(UPDATED_R_EWORKEDBY)
            .rEWORKEDON(UPDATED_R_EWORKEDON)
            .dISTRICT(UPDATED_D_ISTRICT)
            .rEGION(UPDATED_R_EGION)
            .rEGIONNAME(UPDATED_R_EGIONNAME)
            .rEPORTING(UPDATED_R_EPORTING);

        restBRANCHESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBRANCHES.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBRANCHES))
            )
            .andExpect(status().isOk());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBRANCHESToMatchAllProperties(updatedBRANCHES);
    }

    @Test
    @Transactional
    void putNonExistingBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bRANCHES.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES))
            )
            .andExpect(status().isBadRequest());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bRANCHES))
            )
            .andExpect(status().isBadRequest());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bRANCHES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBRANCHESWithPatch() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bRANCHES using partial update
        BRANCHES partialUpdatedBRANCHES = new BRANCHES();
        partialUpdatedBRANCHES.setId(bRANCHES.getId());

        partialUpdatedBRANCHES
            .bRANCHNAME(UPDATED_B_RANCHNAME)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .eMAIL(UPDATED_E_MAIL)
            .aDDRESS(UPDATED_A_DDRESS)
            .rEMARKS(UPDATED_R_EMARKS)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .cREATEDON(UPDATED_C_REATEDON)
            .dELETEREMARKS(UPDATED_D_ELETEREMARKS)
            .dELETED(UPDATED_D_ELETED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDDON(UPDATED_D_ECLINEDDON)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKEDBY(UPDATED_R_EWORKEDBY)
            .rEWORKEDON(UPDATED_R_EWORKEDON)
            .rEPORTING(UPDATED_R_EPORTING);

        restBRANCHESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBRANCHES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBRANCHES))
            )
            .andExpect(status().isOk());

        // Validate the BRANCHES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBRANCHESUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedBRANCHES, bRANCHES), getPersistedBRANCHES(bRANCHES));
    }

    @Test
    @Transactional
    void fullUpdateBRANCHESWithPatch() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bRANCHES using partial update
        BRANCHES partialUpdatedBRANCHES = new BRANCHES();
        partialUpdatedBRANCHES.setId(bRANCHES.getId());

        partialUpdatedBRANCHES
            .bRANCHNAME(UPDATED_B_RANCHNAME)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .aPPROVED(UPDATED_A_PPROVED)
            .eMAIL(UPDATED_E_MAIL)
            .aDDRESS(UPDATED_A_DDRESS)
            .pHONE(UPDATED_P_HONE)
            .lOCATION(UPDATED_L_OCATION)
            .cONTACTPERSON(UPDATED_C_ONTACTPERSON)
            .rEMARKS(UPDATED_R_EMARKS)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .cREATEDON(UPDATED_C_REATEDON)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .dELETEDON(UPDATED_D_ELETEDON)
            .dELETEREMARKS(UPDATED_D_ELETEREMARKS)
            .dELETED(UPDATED_D_ELETED)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDDON(UPDATED_D_ECLINEDDON)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .sESSIONID(UPDATED_S_ESSIONID)
            .rEWORKED(UPDATED_R_EWORKED)
            .rEWORKEDBY(UPDATED_R_EWORKEDBY)
            .rEWORKEDON(UPDATED_R_EWORKEDON)
            .dISTRICT(UPDATED_D_ISTRICT)
            .rEGION(UPDATED_R_EGION)
            .rEGIONNAME(UPDATED_R_EGIONNAME)
            .rEPORTING(UPDATED_R_EPORTING);

        restBRANCHESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBRANCHES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBRANCHES))
            )
            .andExpect(status().isOk());

        // Validate the BRANCHES in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBRANCHESUpdatableFieldsEquals(partialUpdatedBRANCHES, getPersistedBRANCHES(partialUpdatedBRANCHES));
    }

    @Test
    @Transactional
    void patchNonExistingBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bRANCHES.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bRANCHES))
            )
            .andExpect(status().isBadRequest());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bRANCHES))
            )
            .andExpect(status().isBadRequest());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBRANCHES() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bRANCHES.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBRANCHESMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bRANCHES)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BRANCHES in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBRANCHES() throws Exception {
        // Initialize the database
        insertedBRANCHES = bRANCHESRepository.saveAndFlush(bRANCHES);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the bRANCHES
        restBRANCHESMockMvc
            .perform(delete(ENTITY_API_URL_ID, bRANCHES.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return bRANCHESRepository.count();
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

    protected BRANCHES getPersistedBRANCHES(BRANCHES bRANCHES) {
        return bRANCHESRepository.findById(bRANCHES.getId()).orElseThrow();
    }

    protected void assertPersistedBRANCHESToMatchAllProperties(BRANCHES expectedBRANCHES) {
        assertBRANCHESAllPropertiesEquals(expectedBRANCHES, getPersistedBRANCHES(expectedBRANCHES));
    }

    protected void assertPersistedBRANCHESToMatchUpdatableProperties(BRANCHES expectedBRANCHES) {
        assertBRANCHESAllUpdatablePropertiesEquals(expectedBRANCHES, getPersistedBRANCHES(expectedBRANCHES));
    }
}
