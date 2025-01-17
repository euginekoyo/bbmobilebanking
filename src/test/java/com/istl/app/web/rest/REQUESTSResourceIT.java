package com.istl.app.web.rest;

import static com.istl.app.domain.REQUESTSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.REQUESTS;
import com.istl.app.repository.REQUESTSRepository;
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
 * Integration tests for the {@link REQUESTSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class REQUESTSResourceIT {

    private static final String DEFAULT_M_OBILENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_M_OBILENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTNO = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTNO = "BBBBBBBBBB";

    private static final String DEFAULT_C_URRENCY = "AAAAAAAAAA";
    private static final String UPDATED_C_URRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_C_IF = "AAAAAAAAAA";
    private static final String UPDATED_C_IF = "BBBBBBBBBB";

    private static final String DEFAULT_R_EQUESTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_R_EQUESTTYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_R_EQUESTCHARGE = 1D;
    private static final Double UPDATED_R_EQUESTCHARGE = 2D;

    private static final String DEFAULT_R_EQUESTSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_R_EQUESTSTATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ATEREQUESTED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATEREQUESTED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_T_RNREFNO = "AAAAAAAAAA";
    private static final String UPDATED_T_RNREFNO = "BBBBBBBBBB";

    private static final Long DEFAULT_N_OOFBOOKS = 1L;
    private static final Long UPDATED_N_OOFBOOKS = 2L;

    private static final String DEFAULT_N_OOFLEAVES = "AAAAAAAAAA";
    private static final String UPDATED_N_OOFLEAVES = "BBBBBBBBBB";

    private static final Long DEFAULT_A_PPROVED = 1L;
    private static final Long UPDATED_A_PPROVED = 2L;

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_HECKERREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_C_HECKERREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESPCODE = "AAAAAAAAAA";
    private static final String UPDATED_R_ESPCODE = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESPDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_R_ESPDESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ATERESPONDED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATERESPONDED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_USTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_C_USTOMERNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_R_EJECTED = 1L;
    private static final Long UPDATED_R_EJECTED = 2L;

    private static final String DEFAULT_R_EJECTEDBY = "AAAAAAAAAA";
    private static final String UPDATED_R_EJECTEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_R_EJECTEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_EJECTEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private REQUESTSRepository rEQUESTSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restREQUESTSMockMvc;

    private REQUESTS rEQUESTS;

    private REQUESTS insertedREQUESTS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static REQUESTS createEntity() {
        return new REQUESTS()
            .mOBILENUMBER(DEFAULT_M_OBILENUMBER)
            .aCCOUNTNO(DEFAULT_A_CCOUNTNO)
            .cURRENCY(DEFAULT_C_URRENCY)
            .cIF(DEFAULT_C_IF)
            .rEQUESTTYPE(DEFAULT_R_EQUESTTYPE)
            .rEQUESTCHARGE(DEFAULT_R_EQUESTCHARGE)
            .rEQUESTSTATUS(DEFAULT_R_EQUESTSTATUS)
            .dATEREQUESTED(DEFAULT_D_ATEREQUESTED)
            .tRNREFNO(DEFAULT_T_RNREFNO)
            .nOOFBOOKS(DEFAULT_N_OOFBOOKS)
            .nOOFLEAVES(DEFAULT_N_OOFLEAVES)
            .aPPROVED(DEFAULT_A_PPROVED)
            .cHANNEL(DEFAULT_C_HANNEL)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDON(DEFAULT_A_PPROVEDON)
            .cHECKERREMARKS(DEFAULT_C_HECKERREMARKS)
            .rESPCODE(DEFAULT_R_ESPCODE)
            .rESPDESCRIPTION(DEFAULT_R_ESPDESCRIPTION)
            .dATERESPONDED(DEFAULT_D_ATERESPONDED)
            .cUSTOMERNAME(DEFAULT_C_USTOMERNAME)
            .rEJECTED(DEFAULT_R_EJECTED)
            .rEJECTEDBY(DEFAULT_R_EJECTEDBY)
            .rEJECTEDON(DEFAULT_R_EJECTEDON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static REQUESTS createUpdatedEntity() {
        return new REQUESTS()
            .mOBILENUMBER(UPDATED_M_OBILENUMBER)
            .aCCOUNTNO(UPDATED_A_CCOUNTNO)
            .cURRENCY(UPDATED_C_URRENCY)
            .cIF(UPDATED_C_IF)
            .rEQUESTTYPE(UPDATED_R_EQUESTTYPE)
            .rEQUESTCHARGE(UPDATED_R_EQUESTCHARGE)
            .rEQUESTSTATUS(UPDATED_R_EQUESTSTATUS)
            .dATEREQUESTED(UPDATED_D_ATEREQUESTED)
            .tRNREFNO(UPDATED_T_RNREFNO)
            .nOOFBOOKS(UPDATED_N_OOFBOOKS)
            .nOOFLEAVES(UPDATED_N_OOFLEAVES)
            .aPPROVED(UPDATED_A_PPROVED)
            .cHANNEL(UPDATED_C_HANNEL)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .rESPCODE(UPDATED_R_ESPCODE)
            .rESPDESCRIPTION(UPDATED_R_ESPDESCRIPTION)
            .dATERESPONDED(UPDATED_D_ATERESPONDED)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .rEJECTED(UPDATED_R_EJECTED)
            .rEJECTEDBY(UPDATED_R_EJECTEDBY)
            .rEJECTEDON(UPDATED_R_EJECTEDON);
    }

    @BeforeEach
    public void initTest() {
        rEQUESTS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedREQUESTS != null) {
            rEQUESTSRepository.delete(insertedREQUESTS);
            insertedREQUESTS = null;
        }
    }

    @Test
    @Transactional
    void createREQUESTS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the REQUESTS
        var returnedREQUESTS = om.readValue(
            restREQUESTSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rEQUESTS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            REQUESTS.class
        );

        // Validate the REQUESTS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertREQUESTSUpdatableFieldsEquals(returnedREQUESTS, getPersistedREQUESTS(returnedREQUESTS));

        insertedREQUESTS = returnedREQUESTS;
    }

    @Test
    @Transactional
    void createREQUESTSWithExistingId() throws Exception {
        // Create the REQUESTS with an existing ID
        rEQUESTS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restREQUESTSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rEQUESTS)))
            .andExpect(status().isBadRequest());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllREQUESTS() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        // Get all the rEQUESTSList
        restREQUESTSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rEQUESTS.getId().intValue())))
            .andExpect(jsonPath("$.[*].mOBILENUMBER").value(hasItem(DEFAULT_M_OBILENUMBER)))
            .andExpect(jsonPath("$.[*].aCCOUNTNO").value(hasItem(DEFAULT_A_CCOUNTNO)))
            .andExpect(jsonPath("$.[*].cURRENCY").value(hasItem(DEFAULT_C_URRENCY)))
            .andExpect(jsonPath("$.[*].cIF").value(hasItem(DEFAULT_C_IF)))
            .andExpect(jsonPath("$.[*].rEQUESTTYPE").value(hasItem(DEFAULT_R_EQUESTTYPE)))
            .andExpect(jsonPath("$.[*].rEQUESTCHARGE").value(hasItem(DEFAULT_R_EQUESTCHARGE)))
            .andExpect(jsonPath("$.[*].rEQUESTSTATUS").value(hasItem(DEFAULT_R_EQUESTSTATUS)))
            .andExpect(jsonPath("$.[*].dATEREQUESTED").value(hasItem(DEFAULT_D_ATEREQUESTED.toString())))
            .andExpect(jsonPath("$.[*].tRNREFNO").value(hasItem(DEFAULT_T_RNREFNO)))
            .andExpect(jsonPath("$.[*].nOOFBOOKS").value(hasItem(DEFAULT_N_OOFBOOKS.intValue())))
            .andExpect(jsonPath("$.[*].nOOFLEAVES").value(hasItem(DEFAULT_N_OOFLEAVES)))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED.intValue())))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDON").value(hasItem(DEFAULT_A_PPROVEDON.toString())))
            .andExpect(jsonPath("$.[*].cHECKERREMARKS").value(hasItem(DEFAULT_C_HECKERREMARKS)))
            .andExpect(jsonPath("$.[*].rESPCODE").value(hasItem(DEFAULT_R_ESPCODE)))
            .andExpect(jsonPath("$.[*].rESPDESCRIPTION").value(hasItem(DEFAULT_R_ESPDESCRIPTION)))
            .andExpect(jsonPath("$.[*].dATERESPONDED").value(hasItem(DEFAULT_D_ATERESPONDED.toString())))
            .andExpect(jsonPath("$.[*].cUSTOMERNAME").value(hasItem(DEFAULT_C_USTOMERNAME)))
            .andExpect(jsonPath("$.[*].rEJECTED").value(hasItem(DEFAULT_R_EJECTED.intValue())))
            .andExpect(jsonPath("$.[*].rEJECTEDBY").value(hasItem(DEFAULT_R_EJECTEDBY)))
            .andExpect(jsonPath("$.[*].rEJECTEDON").value(hasItem(DEFAULT_R_EJECTEDON.toString())));
    }

    @Test
    @Transactional
    void getREQUESTS() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        // Get the rEQUESTS
        restREQUESTSMockMvc
            .perform(get(ENTITY_API_URL_ID, rEQUESTS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rEQUESTS.getId().intValue()))
            .andExpect(jsonPath("$.mOBILENUMBER").value(DEFAULT_M_OBILENUMBER))
            .andExpect(jsonPath("$.aCCOUNTNO").value(DEFAULT_A_CCOUNTNO))
            .andExpect(jsonPath("$.cURRENCY").value(DEFAULT_C_URRENCY))
            .andExpect(jsonPath("$.cIF").value(DEFAULT_C_IF))
            .andExpect(jsonPath("$.rEQUESTTYPE").value(DEFAULT_R_EQUESTTYPE))
            .andExpect(jsonPath("$.rEQUESTCHARGE").value(DEFAULT_R_EQUESTCHARGE))
            .andExpect(jsonPath("$.rEQUESTSTATUS").value(DEFAULT_R_EQUESTSTATUS))
            .andExpect(jsonPath("$.dATEREQUESTED").value(DEFAULT_D_ATEREQUESTED.toString()))
            .andExpect(jsonPath("$.tRNREFNO").value(DEFAULT_T_RNREFNO))
            .andExpect(jsonPath("$.nOOFBOOKS").value(DEFAULT_N_OOFBOOKS.intValue()))
            .andExpect(jsonPath("$.nOOFLEAVES").value(DEFAULT_N_OOFLEAVES))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED.intValue()))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDON").value(DEFAULT_A_PPROVEDON.toString()))
            .andExpect(jsonPath("$.cHECKERREMARKS").value(DEFAULT_C_HECKERREMARKS))
            .andExpect(jsonPath("$.rESPCODE").value(DEFAULT_R_ESPCODE))
            .andExpect(jsonPath("$.rESPDESCRIPTION").value(DEFAULT_R_ESPDESCRIPTION))
            .andExpect(jsonPath("$.dATERESPONDED").value(DEFAULT_D_ATERESPONDED.toString()))
            .andExpect(jsonPath("$.cUSTOMERNAME").value(DEFAULT_C_USTOMERNAME))
            .andExpect(jsonPath("$.rEJECTED").value(DEFAULT_R_EJECTED.intValue()))
            .andExpect(jsonPath("$.rEJECTEDBY").value(DEFAULT_R_EJECTEDBY))
            .andExpect(jsonPath("$.rEJECTEDON").value(DEFAULT_R_EJECTEDON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingREQUESTS() throws Exception {
        // Get the rEQUESTS
        restREQUESTSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingREQUESTS() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rEQUESTS
        REQUESTS updatedREQUESTS = rEQUESTSRepository.findById(rEQUESTS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedREQUESTS are not directly saved in db
        em.detach(updatedREQUESTS);
        updatedREQUESTS
            .mOBILENUMBER(UPDATED_M_OBILENUMBER)
            .aCCOUNTNO(UPDATED_A_CCOUNTNO)
            .cURRENCY(UPDATED_C_URRENCY)
            .cIF(UPDATED_C_IF)
            .rEQUESTTYPE(UPDATED_R_EQUESTTYPE)
            .rEQUESTCHARGE(UPDATED_R_EQUESTCHARGE)
            .rEQUESTSTATUS(UPDATED_R_EQUESTSTATUS)
            .dATEREQUESTED(UPDATED_D_ATEREQUESTED)
            .tRNREFNO(UPDATED_T_RNREFNO)
            .nOOFBOOKS(UPDATED_N_OOFBOOKS)
            .nOOFLEAVES(UPDATED_N_OOFLEAVES)
            .aPPROVED(UPDATED_A_PPROVED)
            .cHANNEL(UPDATED_C_HANNEL)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .rESPCODE(UPDATED_R_ESPCODE)
            .rESPDESCRIPTION(UPDATED_R_ESPDESCRIPTION)
            .dATERESPONDED(UPDATED_D_ATERESPONDED)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .rEJECTED(UPDATED_R_EJECTED)
            .rEJECTEDBY(UPDATED_R_EJECTEDBY)
            .rEJECTEDON(UPDATED_R_EJECTEDON);

        restREQUESTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedREQUESTS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedREQUESTS))
            )
            .andExpect(status().isOk());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedREQUESTSToMatchAllProperties(updatedREQUESTS);
    }

    @Test
    @Transactional
    void putNonExistingREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rEQUESTS.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rEQUESTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rEQUESTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rEQUESTS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateREQUESTSWithPatch() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rEQUESTS using partial update
        REQUESTS partialUpdatedREQUESTS = new REQUESTS();
        partialUpdatedREQUESTS.setId(rEQUESTS.getId());

        partialUpdatedREQUESTS
            .mOBILENUMBER(UPDATED_M_OBILENUMBER)
            .cIF(UPDATED_C_IF)
            .rEQUESTSTATUS(UPDATED_R_EQUESTSTATUS)
            .tRNREFNO(UPDATED_T_RNREFNO)
            .nOOFLEAVES(UPDATED_N_OOFLEAVES)
            .aPPROVED(UPDATED_A_PPROVED)
            .cHANNEL(UPDATED_C_HANNEL)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .rESPCODE(UPDATED_R_ESPCODE)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME);

        restREQUESTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedREQUESTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedREQUESTS))
            )
            .andExpect(status().isOk());

        // Validate the REQUESTS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertREQUESTSUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedREQUESTS, rEQUESTS), getPersistedREQUESTS(rEQUESTS));
    }

    @Test
    @Transactional
    void fullUpdateREQUESTSWithPatch() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rEQUESTS using partial update
        REQUESTS partialUpdatedREQUESTS = new REQUESTS();
        partialUpdatedREQUESTS.setId(rEQUESTS.getId());

        partialUpdatedREQUESTS
            .mOBILENUMBER(UPDATED_M_OBILENUMBER)
            .aCCOUNTNO(UPDATED_A_CCOUNTNO)
            .cURRENCY(UPDATED_C_URRENCY)
            .cIF(UPDATED_C_IF)
            .rEQUESTTYPE(UPDATED_R_EQUESTTYPE)
            .rEQUESTCHARGE(UPDATED_R_EQUESTCHARGE)
            .rEQUESTSTATUS(UPDATED_R_EQUESTSTATUS)
            .dATEREQUESTED(UPDATED_D_ATEREQUESTED)
            .tRNREFNO(UPDATED_T_RNREFNO)
            .nOOFBOOKS(UPDATED_N_OOFBOOKS)
            .nOOFLEAVES(UPDATED_N_OOFLEAVES)
            .aPPROVED(UPDATED_A_PPROVED)
            .cHANNEL(UPDATED_C_HANNEL)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .rESPCODE(UPDATED_R_ESPCODE)
            .rESPDESCRIPTION(UPDATED_R_ESPDESCRIPTION)
            .dATERESPONDED(UPDATED_D_ATERESPONDED)
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .rEJECTED(UPDATED_R_EJECTED)
            .rEJECTEDBY(UPDATED_R_EJECTEDBY)
            .rEJECTEDON(UPDATED_R_EJECTEDON);

        restREQUESTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedREQUESTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedREQUESTS))
            )
            .andExpect(status().isOk());

        // Validate the REQUESTS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertREQUESTSUpdatableFieldsEquals(partialUpdatedREQUESTS, getPersistedREQUESTS(partialUpdatedREQUESTS));
    }

    @Test
    @Transactional
    void patchNonExistingREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rEQUESTS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rEQUESTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rEQUESTS))
            )
            .andExpect(status().isBadRequest());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamREQUESTS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rEQUESTS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restREQUESTSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rEQUESTS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the REQUESTS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteREQUESTS() throws Exception {
        // Initialize the database
        insertedREQUESTS = rEQUESTSRepository.saveAndFlush(rEQUESTS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rEQUESTS
        restREQUESTSMockMvc
            .perform(delete(ENTITY_API_URL_ID, rEQUESTS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rEQUESTSRepository.count();
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

    protected REQUESTS getPersistedREQUESTS(REQUESTS rEQUESTS) {
        return rEQUESTSRepository.findById(rEQUESTS.getId()).orElseThrow();
    }

    protected void assertPersistedREQUESTSToMatchAllProperties(REQUESTS expectedREQUESTS) {
        assertREQUESTSAllPropertiesEquals(expectedREQUESTS, getPersistedREQUESTS(expectedREQUESTS));
    }

    protected void assertPersistedREQUESTSToMatchUpdatableProperties(REQUESTS expectedREQUESTS) {
        assertREQUESTSAllUpdatablePropertiesEquals(expectedREQUESTS, getPersistedREQUESTS(expectedREQUESTS));
    }
}
