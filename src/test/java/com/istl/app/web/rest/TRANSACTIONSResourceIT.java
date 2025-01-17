package com.istl.app.web.rest;

import static com.istl.app.domain.TRANSACTIONSAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.TRANSACTIONS;
import com.istl.app.repository.TRANSACTIONSRepository;
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
 * Integration tests for the {@link TRANSACTIONSResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TRANSACTIONSResourceIT {

    private static final Long DEFAULT_P_ROCESSED = 1L;
    private static final Long UPDATED_P_ROCESSED = 2L;

    private static final String DEFAULT_I_NCOMINGBITMAP = "AAAAAAAAAA";
    private static final String UPDATED_I_NCOMINGBITMAP = "BBBBBBBBBB";

    private static final String DEFAULT_O_UTGOINGBITMAP = "AAAAAAAAAA";
    private static final String UPDATED_O_UTGOINGBITMAP = "BBBBBBBBBB";

    private static final String DEFAULT_I_NMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_I_NMESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGETOCBS = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGETOCBS = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGEFROMCBS = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGEFROMCBS = "BBBBBBBBBB";

    private static final Long DEFAULT_C_BSPROCESS = 1L;
    private static final Long UPDATED_C_BSPROCESS = 2L;

    private static final Long DEFAULT_C_BSONLINE = 1L;
    private static final Long UPDATED_C_BSONLINE = 2L;

    private static final String DEFAULT_C_BSRESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_C_BSRESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESPONSEMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_R_ESPONSEMESSAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_R_ESPONSESENT = 1L;
    private static final Long UPDATED_R_ESPONSESENT = 2L;

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_O_RIGINALDATA = "AAAAAAAAAA";
    private static final String UPDATED_O_RIGINALDATA = "BBBBBBBBBB";

    private static final String DEFAULT_F_IELD_39_RESP = "AAAAAAAAAA";
    private static final String UPDATED_F_IELD_39_RESP = "BBBBBBBBBB";

    private static final String DEFAULT_N_ARRATION = "AAAAAAAAAA";
    private static final String UPDATED_N_ARRATION = "BBBBBBBBBB";

    private static final Long DEFAULT_A_UTHORISED = 1L;
    private static final Long UPDATED_A_UTHORISED = 2L;

    private static final String DEFAULT_B_RANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_B_RANCHCODE = "BBBBBBBBBB";

    private static final String DEFAULT_F_IELD_39_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_F_IELD_39_ORIGINAL = "BBBBBBBBBB";

    private static final String DEFAULT_M_ESSAGECLASS = "AAAAAAAAAA";
    private static final String UPDATED_M_ESSAGECLASS = "BBBBBBBBBB";

    private static final String DEFAULT_T_XNCODE = "AAAAAAAAAA";
    private static final String UPDATED_T_XNCODE = "BBBBBBBBBB";

    private static final String DEFAULT_C_URRCODE = "AAAAA";
    private static final String UPDATED_C_URRCODE = "BBBBB";

    private static final String DEFAULT_D_EVICE = "AAAAAAAAAA";
    private static final String UPDATED_D_EVICE = "BBBBBBBBBB";

    private static final String DEFAULT_B_RANCH_2 = "AAAAAAAAAA";
    private static final String UPDATED_B_RANCH_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_LONG_ERBRANCH = 1L;
    private static final Long UPDATED_LONG_ERBRANCH = 2L;

    private static final Instant DEFAULT_D_ATEX = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATEX = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_T_IMEX = "AAAAAAAAAA";
    private static final String UPDATED_T_IMEX = "BBBBBBBBBB";

    private static final Long DEFAULT_P_OSTED = 1L;
    private static final Long UPDATED_P_OSTED = 2L;

    private static final Long DEFAULT_A_TTEMPTS = 1L;
    private static final Long UPDATED_A_TTEMPTS = 2L;

    private static final String DEFAULT_O_RIGINALDATA_2 = "AAAAAAAAAA";
    private static final String UPDATED_O_RIGINALDATA_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_C_OMMISSION = 1L;
    private static final Long UPDATED_C_OMMISSION = 2L;

    private static final Long DEFAULT_R_ESPONSECREATED = 1L;
    private static final Long UPDATED_R_ESPONSECREATED = 2L;

    private static final Long DEFAULT_O_NLINE = 1L;
    private static final Long UPDATED_O_NLINE = 2L;

    private static final String DEFAULT_O_RIGINALDATA_3 = "AAAAAAAAAA";
    private static final String UPDATED_O_RIGINALDATA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_T_OSWITCH = "AAAAAAAAAA";
    private static final String UPDATED_T_OSWITCH = "BBBBBBBBBB";

    private static final String DEFAULT_F_ROMSWITCH = "AAAAAAAAAA";
    private static final String UPDATED_F_ROMSWITCH = "BBBBBBBBBB";

    private static final String DEFAULT_T_OCBS = "AAAAAAAAAA";
    private static final String UPDATED_T_OCBS = "BBBBBBBBBB";

    private static final String DEFAULT_F_ROMCBS = "AAAAAAAAAA";
    private static final String UPDATED_F_ROMCBS = "BBBBBBBBBB";

    private static final Long DEFAULT_P_OSTINGLEGS = 1L;
    private static final Long UPDATED_P_OSTINGLEGS = 2L;

    private static final String DEFAULT_C_OMMISSIONTXNCODE = "AAAAAAAAAA";
    private static final String UPDATED_C_OMMISSIONTXNCODE = "BBBBBBBBBB";

    private static final String DEFAULT_H_OSTREF = "AAAAAAAAAA";
    private static final String UPDATED_H_OSTREF = "BBBBBBBBBB";

    private static final Long DEFAULT_R_EQUESTCREATED = 1L;
    private static final Long UPDATED_R_EQUESTCREATED = 2L;

    private static final String DEFAULT_R_EQUESTMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_R_EQUESTMESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_O_UTGOINGBITMAPFLEX = "AAAAAAAAAA";
    private static final String UPDATED_O_UTGOINGBITMAPFLEX = "BBBBBBBBBB";

    private static final String DEFAULT_I_NCOMINGBITMAPFLEX = "AAAAAAAAAA";
    private static final String UPDATED_I_NCOMINGBITMAPFLEX = "BBBBBBBBBB";

    private static final Long DEFAULT_R_EQUESTSENT = 1L;
    private static final Long UPDATED_R_EQUESTSENT = 2L;

    private static final Long DEFAULT_M_INICBS = 1L;
    private static final Long UPDATED_M_INICBS = 2L;

    private static final Long DEFAULT_R_EVERSED = 1L;
    private static final Long UPDATED_R_EVERSED = 2L;

    private static final Long DEFAULT_O_FFLINESENTTOHOST = 1L;
    private static final Long UPDATED_O_FFLINESENTTOHOST = 2L;

    private static final String DEFAULT_O_FFLINERESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_O_FFLINERESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_S_OURCE_LONG_ERFACE = "AAAAAAAAAA";
    private static final String UPDATED_S_OURCE_LONG_ERFACE = "BBBBBBBBBB";

    private static final String DEFAULT_M_TIRRN = "AAAAAAAAAA";
    private static final String UPDATED_M_TIRRN = "BBBBBBBBBB";

    private static final String DEFAULT_H_OSTRESPONSECODE = "AAAAAAAAAA";
    private static final String UPDATED_H_OSTRESPONSECODE = "BBBBBBBBBB";

    private static final String DEFAULT_F_IELD_48 = "AAAAAAAAAA";
    private static final String UPDATED_F_IELD_48 = "BBBBBBBBBB";

    private static final String DEFAULT_S_OURCE = "AAAAAAAAAA";
    private static final String UPDATED_S_OURCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TRANSACTIONSRepository tRANSACTIONSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTRANSACTIONSMockMvc;

    private TRANSACTIONS tRANSACTIONS;

    private TRANSACTIONS insertedTRANSACTIONS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TRANSACTIONS createEntity() {
        return new TRANSACTIONS()
            .pROCESSED(DEFAULT_P_ROCESSED)
            .iNCOMINGBITMAP(DEFAULT_I_NCOMINGBITMAP)
            .oUTGOINGBITMAP(DEFAULT_O_UTGOINGBITMAP)
            .iNMESSAGE(DEFAULT_I_NMESSAGE)
            .mESSAGETOCBS(DEFAULT_M_ESSAGETOCBS)
            .mESSAGEFROMCBS(DEFAULT_M_ESSAGEFROMCBS)
            .cBSPROCESS(DEFAULT_C_BSPROCESS)
            .cBSONLINE(DEFAULT_C_BSONLINE)
            .cBSRESPONSE(DEFAULT_C_BSRESPONSE)
            .rESPONSEMESSAGE(DEFAULT_R_ESPONSEMESSAGE)
            .rESPONSESENT(DEFAULT_R_ESPONSESENT)
            .cHANNEL(DEFAULT_C_HANNEL)
            .oRIGINALDATA(DEFAULT_O_RIGINALDATA)
            .fIELD39RESP(DEFAULT_F_IELD_39_RESP)
            .nARRATION(DEFAULT_N_ARRATION)
            .aUTHORISED(DEFAULT_A_UTHORISED)
            .bRANCHCODE(DEFAULT_B_RANCHCODE)
            .fIELD39ORIGINAL(DEFAULT_F_IELD_39_ORIGINAL)
            .mESSAGECLASS(DEFAULT_M_ESSAGECLASS)
            .tXNCODE(DEFAULT_T_XNCODE)
            .cURRCODE(DEFAULT_C_URRCODE)
            .dEVICE(DEFAULT_D_EVICE)
            .bRANCH2(DEFAULT_B_RANCH_2)
            .longERBRANCH(DEFAULT_LONG_ERBRANCH)
            .dATEX(DEFAULT_D_ATEX)
            .tIMEX(DEFAULT_T_IMEX)
            .pOSTED(DEFAULT_P_OSTED)
            .aTTEMPTS(DEFAULT_A_TTEMPTS)
            .oRIGINALDATA2(DEFAULT_O_RIGINALDATA_2)
            .cOMMISSION(DEFAULT_C_OMMISSION)
            .rESPONSECREATED(DEFAULT_R_ESPONSECREATED)
            .oNLINE(DEFAULT_O_NLINE)
            .oRIGINALDATA3(DEFAULT_O_RIGINALDATA_3)
            .tOSWITCH(DEFAULT_T_OSWITCH)
            .fROMSWITCH(DEFAULT_F_ROMSWITCH)
            .tOCBS(DEFAULT_T_OCBS)
            .fROMCBS(DEFAULT_F_ROMCBS)
            .pOSTINGLEGS(DEFAULT_P_OSTINGLEGS)
            .cOMMISSIONTXNCODE(DEFAULT_C_OMMISSIONTXNCODE)
            .hOSTREF(DEFAULT_H_OSTREF)
            .rEQUESTCREATED(DEFAULT_R_EQUESTCREATED)
            .rEQUESTMESSAGE(DEFAULT_R_EQUESTMESSAGE)
            .oUTGOINGBITMAPFLEX(DEFAULT_O_UTGOINGBITMAPFLEX)
            .iNCOMINGBITMAPFLEX(DEFAULT_I_NCOMINGBITMAPFLEX)
            .rEQUESTSENT(DEFAULT_R_EQUESTSENT)
            .mINICBS(DEFAULT_M_INICBS)
            .rEVERSED(DEFAULT_R_EVERSED)
            .oFFLINESENTTOHOST(DEFAULT_O_FFLINESENTTOHOST)
            .oFFLINERESPONSE(DEFAULT_O_FFLINERESPONSE)
            .sOURCELongERFACE(DEFAULT_S_OURCE_LONG_ERFACE)
            .mTIRRN(DEFAULT_M_TIRRN)
            .hOSTRESPONSECODE(DEFAULT_H_OSTRESPONSECODE)
            .fIELD48(DEFAULT_F_IELD_48)
            .sOURCE(DEFAULT_S_OURCE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TRANSACTIONS createUpdatedEntity() {
        return new TRANSACTIONS()
            .pROCESSED(UPDATED_P_ROCESSED)
            .iNCOMINGBITMAP(UPDATED_I_NCOMINGBITMAP)
            .oUTGOINGBITMAP(UPDATED_O_UTGOINGBITMAP)
            .iNMESSAGE(UPDATED_I_NMESSAGE)
            .mESSAGETOCBS(UPDATED_M_ESSAGETOCBS)
            .mESSAGEFROMCBS(UPDATED_M_ESSAGEFROMCBS)
            .cBSPROCESS(UPDATED_C_BSPROCESS)
            .cBSONLINE(UPDATED_C_BSONLINE)
            .cBSRESPONSE(UPDATED_C_BSRESPONSE)
            .rESPONSEMESSAGE(UPDATED_R_ESPONSEMESSAGE)
            .rESPONSESENT(UPDATED_R_ESPONSESENT)
            .cHANNEL(UPDATED_C_HANNEL)
            .oRIGINALDATA(UPDATED_O_RIGINALDATA)
            .fIELD39RESP(UPDATED_F_IELD_39_RESP)
            .nARRATION(UPDATED_N_ARRATION)
            .aUTHORISED(UPDATED_A_UTHORISED)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .fIELD39ORIGINAL(UPDATED_F_IELD_39_ORIGINAL)
            .mESSAGECLASS(UPDATED_M_ESSAGECLASS)
            .tXNCODE(UPDATED_T_XNCODE)
            .cURRCODE(UPDATED_C_URRCODE)
            .dEVICE(UPDATED_D_EVICE)
            .bRANCH2(UPDATED_B_RANCH_2)
            .longERBRANCH(UPDATED_LONG_ERBRANCH)
            .dATEX(UPDATED_D_ATEX)
            .tIMEX(UPDATED_T_IMEX)
            .pOSTED(UPDATED_P_OSTED)
            .aTTEMPTS(UPDATED_A_TTEMPTS)
            .oRIGINALDATA2(UPDATED_O_RIGINALDATA_2)
            .cOMMISSION(UPDATED_C_OMMISSION)
            .rESPONSECREATED(UPDATED_R_ESPONSECREATED)
            .oNLINE(UPDATED_O_NLINE)
            .oRIGINALDATA3(UPDATED_O_RIGINALDATA_3)
            .tOSWITCH(UPDATED_T_OSWITCH)
            .fROMSWITCH(UPDATED_F_ROMSWITCH)
            .tOCBS(UPDATED_T_OCBS)
            .fROMCBS(UPDATED_F_ROMCBS)
            .pOSTINGLEGS(UPDATED_P_OSTINGLEGS)
            .cOMMISSIONTXNCODE(UPDATED_C_OMMISSIONTXNCODE)
            .hOSTREF(UPDATED_H_OSTREF)
            .rEQUESTCREATED(UPDATED_R_EQUESTCREATED)
            .rEQUESTMESSAGE(UPDATED_R_EQUESTMESSAGE)
            .oUTGOINGBITMAPFLEX(UPDATED_O_UTGOINGBITMAPFLEX)
            .iNCOMINGBITMAPFLEX(UPDATED_I_NCOMINGBITMAPFLEX)
            .rEQUESTSENT(UPDATED_R_EQUESTSENT)
            .mINICBS(UPDATED_M_INICBS)
            .rEVERSED(UPDATED_R_EVERSED)
            .oFFLINESENTTOHOST(UPDATED_O_FFLINESENTTOHOST)
            .oFFLINERESPONSE(UPDATED_O_FFLINERESPONSE)
            .sOURCELongERFACE(UPDATED_S_OURCE_LONG_ERFACE)
            .mTIRRN(UPDATED_M_TIRRN)
            .hOSTRESPONSECODE(UPDATED_H_OSTRESPONSECODE)
            .fIELD48(UPDATED_F_IELD_48)
            .sOURCE(UPDATED_S_OURCE);
    }

    @BeforeEach
    public void initTest() {
        tRANSACTIONS = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTRANSACTIONS != null) {
            tRANSACTIONSRepository.delete(insertedTRANSACTIONS);
            insertedTRANSACTIONS = null;
        }
    }

    @Test
    @Transactional
    void createTRANSACTIONS() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TRANSACTIONS
        var returnedTRANSACTIONS = om.readValue(
            restTRANSACTIONSMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tRANSACTIONS)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TRANSACTIONS.class
        );

        // Validate the TRANSACTIONS in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTRANSACTIONSUpdatableFieldsEquals(returnedTRANSACTIONS, getPersistedTRANSACTIONS(returnedTRANSACTIONS));

        insertedTRANSACTIONS = returnedTRANSACTIONS;
    }

    @Test
    @Transactional
    void createTRANSACTIONSWithExistingId() throws Exception {
        // Create the TRANSACTIONS with an existing ID
        tRANSACTIONS.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTRANSACTIONSMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tRANSACTIONS)))
            .andExpect(status().isBadRequest());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTRANSACTIONS() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        // Get all the tRANSACTIONSList
        restTRANSACTIONSMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tRANSACTIONS.getId().intValue())))
            .andExpect(jsonPath("$.[*].pROCESSED").value(hasItem(DEFAULT_P_ROCESSED.intValue())))
            .andExpect(jsonPath("$.[*].iNCOMINGBITMAP").value(hasItem(DEFAULT_I_NCOMINGBITMAP)))
            .andExpect(jsonPath("$.[*].oUTGOINGBITMAP").value(hasItem(DEFAULT_O_UTGOINGBITMAP)))
            .andExpect(jsonPath("$.[*].iNMESSAGE").value(hasItem(DEFAULT_I_NMESSAGE)))
            .andExpect(jsonPath("$.[*].mESSAGETOCBS").value(hasItem(DEFAULT_M_ESSAGETOCBS)))
            .andExpect(jsonPath("$.[*].mESSAGEFROMCBS").value(hasItem(DEFAULT_M_ESSAGEFROMCBS)))
            .andExpect(jsonPath("$.[*].cBSPROCESS").value(hasItem(DEFAULT_C_BSPROCESS.intValue())))
            .andExpect(jsonPath("$.[*].cBSONLINE").value(hasItem(DEFAULT_C_BSONLINE.intValue())))
            .andExpect(jsonPath("$.[*].cBSRESPONSE").value(hasItem(DEFAULT_C_BSRESPONSE)))
            .andExpect(jsonPath("$.[*].rESPONSEMESSAGE").value(hasItem(DEFAULT_R_ESPONSEMESSAGE)))
            .andExpect(jsonPath("$.[*].rESPONSESENT").value(hasItem(DEFAULT_R_ESPONSESENT.intValue())))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].oRIGINALDATA").value(hasItem(DEFAULT_O_RIGINALDATA)))
            .andExpect(jsonPath("$.[*].fIELD39RESP").value(hasItem(DEFAULT_F_IELD_39_RESP)))
            .andExpect(jsonPath("$.[*].nARRATION").value(hasItem(DEFAULT_N_ARRATION)))
            .andExpect(jsonPath("$.[*].aUTHORISED").value(hasItem(DEFAULT_A_UTHORISED.intValue())))
            .andExpect(jsonPath("$.[*].bRANCHCODE").value(hasItem(DEFAULT_B_RANCHCODE)))
            .andExpect(jsonPath("$.[*].fIELD39ORIGINAL").value(hasItem(DEFAULT_F_IELD_39_ORIGINAL)))
            .andExpect(jsonPath("$.[*].mESSAGECLASS").value(hasItem(DEFAULT_M_ESSAGECLASS)))
            .andExpect(jsonPath("$.[*].tXNCODE").value(hasItem(DEFAULT_T_XNCODE)))
            .andExpect(jsonPath("$.[*].cURRCODE").value(hasItem(DEFAULT_C_URRCODE)))
            .andExpect(jsonPath("$.[*].dEVICE").value(hasItem(DEFAULT_D_EVICE)))
            .andExpect(jsonPath("$.[*].bRANCH2").value(hasItem(DEFAULT_B_RANCH_2)))
            .andExpect(jsonPath("$.[*].longERBRANCH").value(hasItem(DEFAULT_LONG_ERBRANCH.intValue())))
            .andExpect(jsonPath("$.[*].dATEX").value(hasItem(DEFAULT_D_ATEX.toString())))
            .andExpect(jsonPath("$.[*].tIMEX").value(hasItem(DEFAULT_T_IMEX)))
            .andExpect(jsonPath("$.[*].pOSTED").value(hasItem(DEFAULT_P_OSTED.intValue())))
            .andExpect(jsonPath("$.[*].aTTEMPTS").value(hasItem(DEFAULT_A_TTEMPTS.intValue())))
            .andExpect(jsonPath("$.[*].oRIGINALDATA2").value(hasItem(DEFAULT_O_RIGINALDATA_2)))
            .andExpect(jsonPath("$.[*].cOMMISSION").value(hasItem(DEFAULT_C_OMMISSION.intValue())))
            .andExpect(jsonPath("$.[*].rESPONSECREATED").value(hasItem(DEFAULT_R_ESPONSECREATED.intValue())))
            .andExpect(jsonPath("$.[*].oNLINE").value(hasItem(DEFAULT_O_NLINE.intValue())))
            .andExpect(jsonPath("$.[*].oRIGINALDATA3").value(hasItem(DEFAULT_O_RIGINALDATA_3)))
            .andExpect(jsonPath("$.[*].tOSWITCH").value(hasItem(DEFAULT_T_OSWITCH)))
            .andExpect(jsonPath("$.[*].fROMSWITCH").value(hasItem(DEFAULT_F_ROMSWITCH)))
            .andExpect(jsonPath("$.[*].tOCBS").value(hasItem(DEFAULT_T_OCBS)))
            .andExpect(jsonPath("$.[*].fROMCBS").value(hasItem(DEFAULT_F_ROMCBS)))
            .andExpect(jsonPath("$.[*].pOSTINGLEGS").value(hasItem(DEFAULT_P_OSTINGLEGS.intValue())))
            .andExpect(jsonPath("$.[*].cOMMISSIONTXNCODE").value(hasItem(DEFAULT_C_OMMISSIONTXNCODE)))
            .andExpect(jsonPath("$.[*].hOSTREF").value(hasItem(DEFAULT_H_OSTREF)))
            .andExpect(jsonPath("$.[*].rEQUESTCREATED").value(hasItem(DEFAULT_R_EQUESTCREATED.intValue())))
            .andExpect(jsonPath("$.[*].rEQUESTMESSAGE").value(hasItem(DEFAULT_R_EQUESTMESSAGE)))
            .andExpect(jsonPath("$.[*].oUTGOINGBITMAPFLEX").value(hasItem(DEFAULT_O_UTGOINGBITMAPFLEX)))
            .andExpect(jsonPath("$.[*].iNCOMINGBITMAPFLEX").value(hasItem(DEFAULT_I_NCOMINGBITMAPFLEX)))
            .andExpect(jsonPath("$.[*].rEQUESTSENT").value(hasItem(DEFAULT_R_EQUESTSENT.intValue())))
            .andExpect(jsonPath("$.[*].mINICBS").value(hasItem(DEFAULT_M_INICBS.intValue())))
            .andExpect(jsonPath("$.[*].rEVERSED").value(hasItem(DEFAULT_R_EVERSED.intValue())))
            .andExpect(jsonPath("$.[*].oFFLINESENTTOHOST").value(hasItem(DEFAULT_O_FFLINESENTTOHOST.intValue())))
            .andExpect(jsonPath("$.[*].oFFLINERESPONSE").value(hasItem(DEFAULT_O_FFLINERESPONSE)))
            .andExpect(jsonPath("$.[*].sOURCELongERFACE").value(hasItem(DEFAULT_S_OURCE_LONG_ERFACE)))
            .andExpect(jsonPath("$.[*].mTIRRN").value(hasItem(DEFAULT_M_TIRRN)))
            .andExpect(jsonPath("$.[*].hOSTRESPONSECODE").value(hasItem(DEFAULT_H_OSTRESPONSECODE)))
            .andExpect(jsonPath("$.[*].fIELD48").value(hasItem(DEFAULT_F_IELD_48)))
            .andExpect(jsonPath("$.[*].sOURCE").value(hasItem(DEFAULT_S_OURCE)));
    }

    @Test
    @Transactional
    void getTRANSACTIONS() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        // Get the tRANSACTIONS
        restTRANSACTIONSMockMvc
            .perform(get(ENTITY_API_URL_ID, tRANSACTIONS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tRANSACTIONS.getId().intValue()))
            .andExpect(jsonPath("$.pROCESSED").value(DEFAULT_P_ROCESSED.intValue()))
            .andExpect(jsonPath("$.iNCOMINGBITMAP").value(DEFAULT_I_NCOMINGBITMAP))
            .andExpect(jsonPath("$.oUTGOINGBITMAP").value(DEFAULT_O_UTGOINGBITMAP))
            .andExpect(jsonPath("$.iNMESSAGE").value(DEFAULT_I_NMESSAGE))
            .andExpect(jsonPath("$.mESSAGETOCBS").value(DEFAULT_M_ESSAGETOCBS))
            .andExpect(jsonPath("$.mESSAGEFROMCBS").value(DEFAULT_M_ESSAGEFROMCBS))
            .andExpect(jsonPath("$.cBSPROCESS").value(DEFAULT_C_BSPROCESS.intValue()))
            .andExpect(jsonPath("$.cBSONLINE").value(DEFAULT_C_BSONLINE.intValue()))
            .andExpect(jsonPath("$.cBSRESPONSE").value(DEFAULT_C_BSRESPONSE))
            .andExpect(jsonPath("$.rESPONSEMESSAGE").value(DEFAULT_R_ESPONSEMESSAGE))
            .andExpect(jsonPath("$.rESPONSESENT").value(DEFAULT_R_ESPONSESENT.intValue()))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.oRIGINALDATA").value(DEFAULT_O_RIGINALDATA))
            .andExpect(jsonPath("$.fIELD39RESP").value(DEFAULT_F_IELD_39_RESP))
            .andExpect(jsonPath("$.nARRATION").value(DEFAULT_N_ARRATION))
            .andExpect(jsonPath("$.aUTHORISED").value(DEFAULT_A_UTHORISED.intValue()))
            .andExpect(jsonPath("$.bRANCHCODE").value(DEFAULT_B_RANCHCODE))
            .andExpect(jsonPath("$.fIELD39ORIGINAL").value(DEFAULT_F_IELD_39_ORIGINAL))
            .andExpect(jsonPath("$.mESSAGECLASS").value(DEFAULT_M_ESSAGECLASS))
            .andExpect(jsonPath("$.tXNCODE").value(DEFAULT_T_XNCODE))
            .andExpect(jsonPath("$.cURRCODE").value(DEFAULT_C_URRCODE))
            .andExpect(jsonPath("$.dEVICE").value(DEFAULT_D_EVICE))
            .andExpect(jsonPath("$.bRANCH2").value(DEFAULT_B_RANCH_2))
            .andExpect(jsonPath("$.longERBRANCH").value(DEFAULT_LONG_ERBRANCH.intValue()))
            .andExpect(jsonPath("$.dATEX").value(DEFAULT_D_ATEX.toString()))
            .andExpect(jsonPath("$.tIMEX").value(DEFAULT_T_IMEX))
            .andExpect(jsonPath("$.pOSTED").value(DEFAULT_P_OSTED.intValue()))
            .andExpect(jsonPath("$.aTTEMPTS").value(DEFAULT_A_TTEMPTS.intValue()))
            .andExpect(jsonPath("$.oRIGINALDATA2").value(DEFAULT_O_RIGINALDATA_2))
            .andExpect(jsonPath("$.cOMMISSION").value(DEFAULT_C_OMMISSION.intValue()))
            .andExpect(jsonPath("$.rESPONSECREATED").value(DEFAULT_R_ESPONSECREATED.intValue()))
            .andExpect(jsonPath("$.oNLINE").value(DEFAULT_O_NLINE.intValue()))
            .andExpect(jsonPath("$.oRIGINALDATA3").value(DEFAULT_O_RIGINALDATA_3))
            .andExpect(jsonPath("$.tOSWITCH").value(DEFAULT_T_OSWITCH))
            .andExpect(jsonPath("$.fROMSWITCH").value(DEFAULT_F_ROMSWITCH))
            .andExpect(jsonPath("$.tOCBS").value(DEFAULT_T_OCBS))
            .andExpect(jsonPath("$.fROMCBS").value(DEFAULT_F_ROMCBS))
            .andExpect(jsonPath("$.pOSTINGLEGS").value(DEFAULT_P_OSTINGLEGS.intValue()))
            .andExpect(jsonPath("$.cOMMISSIONTXNCODE").value(DEFAULT_C_OMMISSIONTXNCODE))
            .andExpect(jsonPath("$.hOSTREF").value(DEFAULT_H_OSTREF))
            .andExpect(jsonPath("$.rEQUESTCREATED").value(DEFAULT_R_EQUESTCREATED.intValue()))
            .andExpect(jsonPath("$.rEQUESTMESSAGE").value(DEFAULT_R_EQUESTMESSAGE))
            .andExpect(jsonPath("$.oUTGOINGBITMAPFLEX").value(DEFAULT_O_UTGOINGBITMAPFLEX))
            .andExpect(jsonPath("$.iNCOMINGBITMAPFLEX").value(DEFAULT_I_NCOMINGBITMAPFLEX))
            .andExpect(jsonPath("$.rEQUESTSENT").value(DEFAULT_R_EQUESTSENT.intValue()))
            .andExpect(jsonPath("$.mINICBS").value(DEFAULT_M_INICBS.intValue()))
            .andExpect(jsonPath("$.rEVERSED").value(DEFAULT_R_EVERSED.intValue()))
            .andExpect(jsonPath("$.oFFLINESENTTOHOST").value(DEFAULT_O_FFLINESENTTOHOST.intValue()))
            .andExpect(jsonPath("$.oFFLINERESPONSE").value(DEFAULT_O_FFLINERESPONSE))
            .andExpect(jsonPath("$.sOURCELongERFACE").value(DEFAULT_S_OURCE_LONG_ERFACE))
            .andExpect(jsonPath("$.mTIRRN").value(DEFAULT_M_TIRRN))
            .andExpect(jsonPath("$.hOSTRESPONSECODE").value(DEFAULT_H_OSTRESPONSECODE))
            .andExpect(jsonPath("$.fIELD48").value(DEFAULT_F_IELD_48))
            .andExpect(jsonPath("$.sOURCE").value(DEFAULT_S_OURCE));
    }

    @Test
    @Transactional
    void getNonExistingTRANSACTIONS() throws Exception {
        // Get the tRANSACTIONS
        restTRANSACTIONSMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTRANSACTIONS() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tRANSACTIONS
        TRANSACTIONS updatedTRANSACTIONS = tRANSACTIONSRepository.findById(tRANSACTIONS.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTRANSACTIONS are not directly saved in db
        em.detach(updatedTRANSACTIONS);
        updatedTRANSACTIONS
            .pROCESSED(UPDATED_P_ROCESSED)
            .iNCOMINGBITMAP(UPDATED_I_NCOMINGBITMAP)
            .oUTGOINGBITMAP(UPDATED_O_UTGOINGBITMAP)
            .iNMESSAGE(UPDATED_I_NMESSAGE)
            .mESSAGETOCBS(UPDATED_M_ESSAGETOCBS)
            .mESSAGEFROMCBS(UPDATED_M_ESSAGEFROMCBS)
            .cBSPROCESS(UPDATED_C_BSPROCESS)
            .cBSONLINE(UPDATED_C_BSONLINE)
            .cBSRESPONSE(UPDATED_C_BSRESPONSE)
            .rESPONSEMESSAGE(UPDATED_R_ESPONSEMESSAGE)
            .rESPONSESENT(UPDATED_R_ESPONSESENT)
            .cHANNEL(UPDATED_C_HANNEL)
            .oRIGINALDATA(UPDATED_O_RIGINALDATA)
            .fIELD39RESP(UPDATED_F_IELD_39_RESP)
            .nARRATION(UPDATED_N_ARRATION)
            .aUTHORISED(UPDATED_A_UTHORISED)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .fIELD39ORIGINAL(UPDATED_F_IELD_39_ORIGINAL)
            .mESSAGECLASS(UPDATED_M_ESSAGECLASS)
            .tXNCODE(UPDATED_T_XNCODE)
            .cURRCODE(UPDATED_C_URRCODE)
            .dEVICE(UPDATED_D_EVICE)
            .bRANCH2(UPDATED_B_RANCH_2)
            .longERBRANCH(UPDATED_LONG_ERBRANCH)
            .dATEX(UPDATED_D_ATEX)
            .tIMEX(UPDATED_T_IMEX)
            .pOSTED(UPDATED_P_OSTED)
            .aTTEMPTS(UPDATED_A_TTEMPTS)
            .oRIGINALDATA2(UPDATED_O_RIGINALDATA_2)
            .cOMMISSION(UPDATED_C_OMMISSION)
            .rESPONSECREATED(UPDATED_R_ESPONSECREATED)
            .oNLINE(UPDATED_O_NLINE)
            .oRIGINALDATA3(UPDATED_O_RIGINALDATA_3)
            .tOSWITCH(UPDATED_T_OSWITCH)
            .fROMSWITCH(UPDATED_F_ROMSWITCH)
            .tOCBS(UPDATED_T_OCBS)
            .fROMCBS(UPDATED_F_ROMCBS)
            .pOSTINGLEGS(UPDATED_P_OSTINGLEGS)
            .cOMMISSIONTXNCODE(UPDATED_C_OMMISSIONTXNCODE)
            .hOSTREF(UPDATED_H_OSTREF)
            .rEQUESTCREATED(UPDATED_R_EQUESTCREATED)
            .rEQUESTMESSAGE(UPDATED_R_EQUESTMESSAGE)
            .oUTGOINGBITMAPFLEX(UPDATED_O_UTGOINGBITMAPFLEX)
            .iNCOMINGBITMAPFLEX(UPDATED_I_NCOMINGBITMAPFLEX)
            .rEQUESTSENT(UPDATED_R_EQUESTSENT)
            .mINICBS(UPDATED_M_INICBS)
            .rEVERSED(UPDATED_R_EVERSED)
            .oFFLINESENTTOHOST(UPDATED_O_FFLINESENTTOHOST)
            .oFFLINERESPONSE(UPDATED_O_FFLINERESPONSE)
            .sOURCELongERFACE(UPDATED_S_OURCE_LONG_ERFACE)
            .mTIRRN(UPDATED_M_TIRRN)
            .hOSTRESPONSECODE(UPDATED_H_OSTRESPONSECODE)
            .fIELD48(UPDATED_F_IELD_48)
            .sOURCE(UPDATED_S_OURCE);

        restTRANSACTIONSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTRANSACTIONS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTRANSACTIONS))
            )
            .andExpect(status().isOk());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTRANSACTIONSToMatchAllProperties(updatedTRANSACTIONS);
    }

    @Test
    @Transactional
    void putNonExistingTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tRANSACTIONS.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tRANSACTIONS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tRANSACTIONS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tRANSACTIONS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTRANSACTIONSWithPatch() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tRANSACTIONS using partial update
        TRANSACTIONS partialUpdatedTRANSACTIONS = new TRANSACTIONS();
        partialUpdatedTRANSACTIONS.setId(tRANSACTIONS.getId());

        partialUpdatedTRANSACTIONS
            .pROCESSED(UPDATED_P_ROCESSED)
            .oUTGOINGBITMAP(UPDATED_O_UTGOINGBITMAP)
            .mESSAGEFROMCBS(UPDATED_M_ESSAGEFROMCBS)
            .cBSONLINE(UPDATED_C_BSONLINE)
            .rESPONSEMESSAGE(UPDATED_R_ESPONSEMESSAGE)
            .rESPONSESENT(UPDATED_R_ESPONSESENT)
            .cHANNEL(UPDATED_C_HANNEL)
            .fIELD39RESP(UPDATED_F_IELD_39_RESP)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .fIELD39ORIGINAL(UPDATED_F_IELD_39_ORIGINAL)
            .tXNCODE(UPDATED_T_XNCODE)
            .cURRCODE(UPDATED_C_URRCODE)
            .bRANCH2(UPDATED_B_RANCH_2)
            .pOSTED(UPDATED_P_OSTED)
            .oRIGINALDATA2(UPDATED_O_RIGINALDATA_2)
            .cOMMISSION(UPDATED_C_OMMISSION)
            .oRIGINALDATA3(UPDATED_O_RIGINALDATA_3)
            .tOSWITCH(UPDATED_T_OSWITCH)
            .tOCBS(UPDATED_T_OCBS)
            .rEQUESTCREATED(UPDATED_R_EQUESTCREATED)
            .rEQUESTMESSAGE(UPDATED_R_EQUESTMESSAGE)
            .iNCOMINGBITMAPFLEX(UPDATED_I_NCOMINGBITMAPFLEX)
            .rEQUESTSENT(UPDATED_R_EQUESTSENT)
            .mINICBS(UPDATED_M_INICBS)
            .rEVERSED(UPDATED_R_EVERSED)
            .oFFLINERESPONSE(UPDATED_O_FFLINERESPONSE)
            .sOURCELongERFACE(UPDATED_S_OURCE_LONG_ERFACE)
            .mTIRRN(UPDATED_M_TIRRN)
            .hOSTRESPONSECODE(UPDATED_H_OSTRESPONSECODE)
            .sOURCE(UPDATED_S_OURCE);

        restTRANSACTIONSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTRANSACTIONS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTRANSACTIONS))
            )
            .andExpect(status().isOk());

        // Validate the TRANSACTIONS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTRANSACTIONSUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTRANSACTIONS, tRANSACTIONS),
            getPersistedTRANSACTIONS(tRANSACTIONS)
        );
    }

    @Test
    @Transactional
    void fullUpdateTRANSACTIONSWithPatch() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tRANSACTIONS using partial update
        TRANSACTIONS partialUpdatedTRANSACTIONS = new TRANSACTIONS();
        partialUpdatedTRANSACTIONS.setId(tRANSACTIONS.getId());

        partialUpdatedTRANSACTIONS
            .pROCESSED(UPDATED_P_ROCESSED)
            .iNCOMINGBITMAP(UPDATED_I_NCOMINGBITMAP)
            .oUTGOINGBITMAP(UPDATED_O_UTGOINGBITMAP)
            .iNMESSAGE(UPDATED_I_NMESSAGE)
            .mESSAGETOCBS(UPDATED_M_ESSAGETOCBS)
            .mESSAGEFROMCBS(UPDATED_M_ESSAGEFROMCBS)
            .cBSPROCESS(UPDATED_C_BSPROCESS)
            .cBSONLINE(UPDATED_C_BSONLINE)
            .cBSRESPONSE(UPDATED_C_BSRESPONSE)
            .rESPONSEMESSAGE(UPDATED_R_ESPONSEMESSAGE)
            .rESPONSESENT(UPDATED_R_ESPONSESENT)
            .cHANNEL(UPDATED_C_HANNEL)
            .oRIGINALDATA(UPDATED_O_RIGINALDATA)
            .fIELD39RESP(UPDATED_F_IELD_39_RESP)
            .nARRATION(UPDATED_N_ARRATION)
            .aUTHORISED(UPDATED_A_UTHORISED)
            .bRANCHCODE(UPDATED_B_RANCHCODE)
            .fIELD39ORIGINAL(UPDATED_F_IELD_39_ORIGINAL)
            .mESSAGECLASS(UPDATED_M_ESSAGECLASS)
            .tXNCODE(UPDATED_T_XNCODE)
            .cURRCODE(UPDATED_C_URRCODE)
            .dEVICE(UPDATED_D_EVICE)
            .bRANCH2(UPDATED_B_RANCH_2)
            .longERBRANCH(UPDATED_LONG_ERBRANCH)
            .dATEX(UPDATED_D_ATEX)
            .tIMEX(UPDATED_T_IMEX)
            .pOSTED(UPDATED_P_OSTED)
            .aTTEMPTS(UPDATED_A_TTEMPTS)
            .oRIGINALDATA2(UPDATED_O_RIGINALDATA_2)
            .cOMMISSION(UPDATED_C_OMMISSION)
            .rESPONSECREATED(UPDATED_R_ESPONSECREATED)
            .oNLINE(UPDATED_O_NLINE)
            .oRIGINALDATA3(UPDATED_O_RIGINALDATA_3)
            .tOSWITCH(UPDATED_T_OSWITCH)
            .fROMSWITCH(UPDATED_F_ROMSWITCH)
            .tOCBS(UPDATED_T_OCBS)
            .fROMCBS(UPDATED_F_ROMCBS)
            .pOSTINGLEGS(UPDATED_P_OSTINGLEGS)
            .cOMMISSIONTXNCODE(UPDATED_C_OMMISSIONTXNCODE)
            .hOSTREF(UPDATED_H_OSTREF)
            .rEQUESTCREATED(UPDATED_R_EQUESTCREATED)
            .rEQUESTMESSAGE(UPDATED_R_EQUESTMESSAGE)
            .oUTGOINGBITMAPFLEX(UPDATED_O_UTGOINGBITMAPFLEX)
            .iNCOMINGBITMAPFLEX(UPDATED_I_NCOMINGBITMAPFLEX)
            .rEQUESTSENT(UPDATED_R_EQUESTSENT)
            .mINICBS(UPDATED_M_INICBS)
            .rEVERSED(UPDATED_R_EVERSED)
            .oFFLINESENTTOHOST(UPDATED_O_FFLINESENTTOHOST)
            .oFFLINERESPONSE(UPDATED_O_FFLINERESPONSE)
            .sOURCELongERFACE(UPDATED_S_OURCE_LONG_ERFACE)
            .mTIRRN(UPDATED_M_TIRRN)
            .hOSTRESPONSECODE(UPDATED_H_OSTRESPONSECODE)
            .fIELD48(UPDATED_F_IELD_48)
            .sOURCE(UPDATED_S_OURCE);

        restTRANSACTIONSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTRANSACTIONS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTRANSACTIONS))
            )
            .andExpect(status().isOk());

        // Validate the TRANSACTIONS in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTRANSACTIONSUpdatableFieldsEquals(partialUpdatedTRANSACTIONS, getPersistedTRANSACTIONS(partialUpdatedTRANSACTIONS));
    }

    @Test
    @Transactional
    void patchNonExistingTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tRANSACTIONS.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tRANSACTIONS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tRANSACTIONS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTRANSACTIONS() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tRANSACTIONS.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTRANSACTIONSMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tRANSACTIONS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TRANSACTIONS in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTRANSACTIONS() throws Exception {
        // Initialize the database
        insertedTRANSACTIONS = tRANSACTIONSRepository.saveAndFlush(tRANSACTIONS);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the tRANSACTIONS
        restTRANSACTIONSMockMvc
            .perform(delete(ENTITY_API_URL_ID, tRANSACTIONS.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tRANSACTIONSRepository.count();
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

    protected TRANSACTIONS getPersistedTRANSACTIONS(TRANSACTIONS tRANSACTIONS) {
        return tRANSACTIONSRepository.findById(tRANSACTIONS.getId()).orElseThrow();
    }

    protected void assertPersistedTRANSACTIONSToMatchAllProperties(TRANSACTIONS expectedTRANSACTIONS) {
        assertTRANSACTIONSAllPropertiesEquals(expectedTRANSACTIONS, getPersistedTRANSACTIONS(expectedTRANSACTIONS));
    }

    protected void assertPersistedTRANSACTIONSToMatchUpdatableProperties(TRANSACTIONS expectedTRANSACTIONS) {
        assertTRANSACTIONSAllUpdatablePropertiesEquals(expectedTRANSACTIONS, getPersistedTRANSACTIONS(expectedTRANSACTIONS));
    }
}
