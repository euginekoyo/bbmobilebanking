package com.istl.app.web.rest;

import static com.istl.app.domain.CUSTOMERAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CUSTOMER;
import com.istl.app.repository.CUSTOMERRepository;
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
 * Integration tests for the {@link CUSTOMERResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CUSTOMERResourceIT {

    private static final String DEFAULT_C_USTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_C_USTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_P_HONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_P_HONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_C_ARDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_C_ARDNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_A_CCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_A_CCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_L_ANG = "AAAAAAAAAA";
    private static final String UPDATED_L_ANG = "BBBBBBBBBB";

    private static final String DEFAULT_P_IN = "AAAAAAAAAA";
    private static final String UPDATED_P_IN = "BBBBBBBBBB";

    private static final String DEFAULT_F_IRSTLOGIN = "A";
    private static final String UPDATED_F_IRSTLOGIN = "B";

    private static final String DEFAULT_A_CTIVE = "A";
    private static final String UPDATED_A_CTIVE = "B";

    private static final Long DEFAULT_R_EGISTERED = 1L;
    private static final Long UPDATED_R_EGISTERED = 2L;

    private static final Long DEFAULT_C_STDELETE = 1L;
    private static final Long UPDATED_C_STDELETE = 2L;

    private static final Instant DEFAULT_R_EGDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_EGDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_A_LERTENABLED = 1L;
    private static final Long UPDATED_A_LERTENABLED = 2L;

    private static final String DEFAULT_R_EMARK = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARK = "BBBBBBBBBB";

    private static final String DEFAULT_I_MSI = "AAAAAAAAAA";
    private static final String UPDATED_I_MSI = "BBBBBBBBBB";

    private static final String DEFAULT_P_ARTIALLYREGISTERED = "A";
    private static final String UPDATED_P_ARTIALLYREGISTERED = "B";

    private static final Instant DEFAULT_P_ARTIALDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_P_ARTIALDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_R_EGISTERDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_EGISTERDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_A_PPROVED = 1D;
    private static final Double UPDATED_A_PPROVED = 2D;

    private static final String DEFAULT_A_PPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_D_ECLINED = 1D;
    private static final Double UPDATED_D_ECLINED = 2D;

    private static final String DEFAULT_D_ECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ECLINEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ECLINEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ECLINEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_HECKERREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_C_HECKERREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_P_OSTALADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_P_OSTALADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_R_ESIDENCE = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_OB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_OB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_REATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_E_MAILADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_E_MAILADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_I_DENTIFICATIONID = "AAAAAAAAAA";
    private static final String UPDATED_I_DENTIFICATIONID = "BBBBBBBBBB";

    private static final Double DEFAULT_A_DDACCOUNT = 1D;
    private static final Double UPDATED_A_DDACCOUNT = 2D;

    private static final String DEFAULT_A_CLINKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_A_CLINKINGINSTITUTION = "BBBBBBBBBB";

    private static final Double DEFAULT_D_EACTIVATED = 1D;
    private static final Double UPDATED_D_EACTIVATED = 2D;

    private static final String DEFAULT_D_EACTIVATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_EACTIVATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_EACTIVATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_EACTIVATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_P_HONENOCHANGED = 1D;
    private static final Double UPDATED_P_HONENOCHANGED = 2D;

    private static final String DEFAULT_P_HONENOCHANGEDBY = "AAAAAAAAAA";
    private static final String UPDATED_P_HONENOCHANGEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_P_HONENOCHANGEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_P_HONENOCHANGEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_O_RIGINALPHONENO = "AAAAAAAAAA";
    private static final String UPDATED_O_RIGINALPHONENO = "BBBBBBBBBB";

    private static final String DEFAULT_N_EWPHONENO = "AAAAAAAAAA";
    private static final String UPDATED_N_EWPHONENO = "BBBBBBBBBB";

    private static final Double DEFAULT_R_ESET = 1D;
    private static final Double UPDATED_R_ESET = 2D;

    private static final String DEFAULT_R_ESETINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_R_ESETINGINSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_P_INRESETREMARK = "AAAAAAAAAA";
    private static final String UPDATED_P_INRESETREMARK = "BBBBBBBBBB";

    private static final String DEFAULT_R_ESETBY = "AAAAAAAAAA";
    private static final String UPDATED_R_ESETBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_R_ESETON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_ESETON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_U_NBLOCKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_U_NBLOCKINGINSTITUTION = "BBBBBBBBBB";

    private static final Double DEFAULT_P_INBLOCK = 1D;
    private static final Double UPDATED_P_INBLOCK = 2D;

    private static final String DEFAULT_P_INBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_P_INBLOCKBY = "BBBBBBBBBB";

    private static final String DEFAULT_P_INBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_P_INBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_B_LOCKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_B_LOCKINGINSTITUTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_P_INBLOCKON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_P_INBLOCKON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_A_PPROVEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_P_INUNBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_P_INUNBLOCKBY = "BBBBBBBBBB";

    private static final Long DEFAULT_L_OGGEDIN = 1L;
    private static final Long UPDATED_L_OGGEDIN = 2L;

    private static final String DEFAULT_T_RIALS = "AAAAAAAAAA";
    private static final String UPDATED_T_RIALS = "BBBBBBBBBB";

    private static final String DEFAULT_I_DTYPE = "AAAAAAAAAA";
    private static final String UPDATED_I_DTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_I_DNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_I_DNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_G_ENDER = "A";
    private static final String UPDATED_G_ENDER = "B";

    private static final String DEFAULT_C_IF = "AAAAAAAAAA";
    private static final String UPDATED_C_IF = "BBBBBBBBBB";

    private static final Instant DEFAULT_D_ATEOFBIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ATEOFBIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_R_EMARKS = "AAAAAAAAAA";
    private static final String UPDATED_R_EMARKS = "BBBBBBBBBB";

    private static final Double DEFAULT_R_ESETIMSI = 1D;
    private static final Double UPDATED_R_ESETIMSI = 2D;

    private static final String DEFAULT_I_MSIRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_I_MSIRESETBY = "BBBBBBBBBB";

    private static final String DEFAULT_F_IRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_F_IRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_S_ECONDNAME = "AAAAAAAAAA";
    private static final String UPDATED_S_ECONDNAME = "BBBBBBBBBB";

    private static final String DEFAULT_L_ASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_L_ASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_P_INBLOCKTIME = "AAAAAAA";
    private static final String UPDATED_P_INBLOCKTIME = "BBBBBBB";

    private static final String DEFAULT_C_USTOMERSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_C_USTOMERSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_U_SERNAME = "AAAAAAAAAA";
    private static final String UPDATED_U_SERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_P_ASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_D_EVICEID = "AAAAAAAAAA";
    private static final String UPDATED_D_EVICEID = "BBBBBBBBBB";

    private static final String DEFAULT_C_HANNEL = "AAAAAAAAAA";
    private static final String UPDATED_C_HANNEL = "BBBBBBBBBB";

    private static final Double DEFAULT_P_ASSRESET = 1D;
    private static final Double UPDATED_P_ASSRESET = 2D;

    private static final String DEFAULT_P_ASSRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSRESETBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_P_ASSRESETON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_P_ASSRESETON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_P_ASSBLOCK = 1D;
    private static final Double UPDATED_P_ASSBLOCK = 2D;

    private static final String DEFAULT_P_ASSBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSBLOCKBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_P_ASSBLOCKON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_P_ASSBLOCKON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_P_INMARKBLOCK = 1D;
    private static final Double UPDATED_P_INMARKBLOCK = 2D;

    private static final Double DEFAULT_P_ASSMARKBLOCK = 1D;
    private static final Double UPDATED_P_ASSMARKBLOCK = 2D;

    private static final String DEFAULT_P_ASSRESETREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSRESETREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_P_ASSBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_P_ASSUNBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_P_ASSUNBLOCKBY = "BBBBBBBBBB";

    private static final Double DEFAULT_P_ASSTRIALS = 1D;
    private static final Double UPDATED_P_ASSTRIALS = 2D;

    private static final Long DEFAULT_A_PPACTIVE = 1L;
    private static final Long UPDATED_A_PPACTIVE = 2L;

    private static final String DEFAULT_L_ASTLOGIN = "AAAAAAAAAA";
    private static final String UPDATED_L_ASTLOGIN = "BBBBBBBBBB";

    private static final Double DEFAULT_A_PPMARKEDDISABLE = 1D;
    private static final Double UPDATED_A_PPMARKEDDISABLE = 2D;

    private static final String DEFAULT_D_ISABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ISABLEBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDISABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDISABLEBY = "BBBBBBBBBB";

    private static final Double DEFAULT_A_PPMARKEDENABLE = 1D;
    private static final Double UPDATED_A_PPMARKEDENABLE = 2D;

    private static final String DEFAULT_E_NABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_E_NABLEBY = "BBBBBBBBBB";

    private static final String DEFAULT_A_PPROVEDENABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_A_PPROVEDENABLEBY = "BBBBBBBBBB";

    private static final Double DEFAULT_M_ARKEDDEACTIVATE = 1D;
    private static final Double UPDATED_M_ARKEDDEACTIVATE = 2D;

    private static final String DEFAULT_A_PPFIRSTLOGIN = "AAAAA";
    private static final String UPDATED_A_PPFIRSTLOGIN = "BBBBB";

    private static final Double DEFAULT_A_TMTRIALS = 1D;
    private static final Double UPDATED_A_TMTRIALS = 2D;

    private static final String DEFAULT_S_HORCUTS = "AAAAAAAAAA";
    private static final String UPDATED_S_HORCUTS = "BBBBBBBBBB";

    private static final String DEFAULT_M_ARKEDACTIVATE = "AAAAAAAAAA";
    private static final String UPDATED_M_ARKEDACTIVATE = "BBBBBBBBBB";

    private static final String DEFAULT_T_OWN = "AAAAAAAAAA";
    private static final String UPDATED_T_OWN = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_PPROVEDDISABLEON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_PPROVEDDISABLEON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_D_ISABLEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_D_ISABLEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_R_ESETAPPROVEON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_R_ESETAPPROVEON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_D_ELETEDBY = "AAAAAAAAAA";
    private static final String UPDATED_D_ELETEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_Q_UESTIONSASKED = "AAAAAAAAAA";
    private static final String UPDATED_Q_UESTIONSASKED = "BBBBBBBBBB";

    private static final String DEFAULT_Q_UESTIONSTRIALS = "AAAAAAAAAA";
    private static final String UPDATED_Q_UESTIONSTRIALS = "BBBBBBBBBB";

    private static final String DEFAULT_Q_UESTIONSANSWERED = "AAAAAAAAAA";
    private static final String UPDATED_Q_UESTIONSANSWERED = "BBBBBBBBBB";

    private static final Double DEFAULT_V_ALIDOTP = 1D;
    private static final Double UPDATED_V_ALIDOTP = 2D;

    private static final String DEFAULT_A_CTIVATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_A_CTIVATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_A_CTIVATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_A_CTIVATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_B_RANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_B_RANCHCODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CUSTOMERRepository cUSTOMERRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCUSTOMERMockMvc;

    private CUSTOMER cUSTOMER;

    private CUSTOMER insertedCUSTOMER;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CUSTOMER createEntity() {
        return new CUSTOMER()
            .cUSTOMERNAME(DEFAULT_C_USTOMERNAME)
            .pHONENUMBER(DEFAULT_P_HONENUMBER)
            .cARDNUMBER(DEFAULT_C_ARDNUMBER)
            .aCCOUNTNUMBER(DEFAULT_A_CCOUNTNUMBER)
            .lANG(DEFAULT_L_ANG)
            .pIN(DEFAULT_P_IN)
            .fIRSTLOGIN(DEFAULT_F_IRSTLOGIN)
            .aCTIVE(DEFAULT_A_CTIVE)
            .rEGISTERED(DEFAULT_R_EGISTERED)
            .cSTDELETE(DEFAULT_C_STDELETE)
            .rEGDATE(DEFAULT_R_EGDATE)
            .aLERTENABLED(DEFAULT_A_LERTENABLED)
            .rEMARK(DEFAULT_R_EMARK)
            .iMSI(DEFAULT_I_MSI)
            .pARTIALLYREGISTERED(DEFAULT_P_ARTIALLYREGISTERED)
            .pARTIALDATE(DEFAULT_P_ARTIALDATE)
            .rEGISTERDATE(DEFAULT_R_EGISTERDATE)
            .aPPROVED(DEFAULT_A_PPROVED)
            .aPPROVEDBY(DEFAULT_A_PPROVEDBY)
            .aPPROVEDDATE(DEFAULT_A_PPROVEDDATE)
            .dECLINED(DEFAULT_D_ECLINED)
            .dECLINEDBY(DEFAULT_D_ECLINEDBY)
            .dECLINEDDATE(DEFAULT_D_ECLINEDDATE)
            .cHECKERREMARKS(DEFAULT_C_HECKERREMARKS)
            .pOSTALADDRESS(DEFAULT_P_OSTALADDRESS)
            .rESIDENCE(DEFAULT_R_ESIDENCE)
            .dOB(DEFAULT_D_OB)
            .cREATEDBY(DEFAULT_C_REATEDBY)
            .eMAILADDRESS(DEFAULT_E_MAILADDRESS)
            .iDENTIFICATIONID(DEFAULT_I_DENTIFICATIONID)
            .aDDACCOUNT(DEFAULT_A_DDACCOUNT)
            .aCLINKINGINSTITUTION(DEFAULT_A_CLINKINGINSTITUTION)
            .dEACTIVATED(DEFAULT_D_EACTIVATED)
            .dEACTIVATEDBY(DEFAULT_D_EACTIVATEDBY)
            .dEACTIVATEDON(DEFAULT_D_EACTIVATEDON)
            .pHONENOCHANGED(DEFAULT_P_HONENOCHANGED)
            .pHONENOCHANGEDBY(DEFAULT_P_HONENOCHANGEDBY)
            .pHONENOCHANGEDON(DEFAULT_P_HONENOCHANGEDON)
            .oRIGINALPHONENO(DEFAULT_O_RIGINALPHONENO)
            .nEWPHONENO(DEFAULT_N_EWPHONENO)
            .rESET(DEFAULT_R_ESET)
            .rESETINGINSTITUTION(DEFAULT_R_ESETINGINSTITUTION)
            .pINRESETREMARK(DEFAULT_P_INRESETREMARK)
            .rESETBY(DEFAULT_R_ESETBY)
            .rESETON(DEFAULT_R_ESETON)
            .uNBLOCKINGINSTITUTION(DEFAULT_U_NBLOCKINGINSTITUTION)
            .pINBLOCK(DEFAULT_P_INBLOCK)
            .pINBLOCKBY(DEFAULT_P_INBLOCKBY)
            .pINBLOCKREMARKS(DEFAULT_P_INBLOCKREMARKS)
            .bLOCKINGINSTITUTION(DEFAULT_B_LOCKINGINSTITUTION)
            .pINBLOCKON(DEFAULT_P_INBLOCKON)
            .aPPROVEDON(DEFAULT_A_PPROVEDON)
            .pINUNBLOCKBY(DEFAULT_P_INUNBLOCKBY)
            .lOGGEDIN(DEFAULT_L_OGGEDIN)
            .tRIALS(DEFAULT_T_RIALS)
            .iDTYPE(DEFAULT_I_DTYPE)
            .iDNUMBER(DEFAULT_I_DNUMBER)
            .gENDER(DEFAULT_G_ENDER)
            .cIF(DEFAULT_C_IF)
            .dATEOFBIRTH(DEFAULT_D_ATEOFBIRTH)
            .rEMARKS(DEFAULT_R_EMARKS)
            .rESETIMSI(DEFAULT_R_ESETIMSI)
            .iMSIRESETBY(DEFAULT_I_MSIRESETBY)
            .fIRSTNAME(DEFAULT_F_IRSTNAME)
            .sECONDNAME(DEFAULT_S_ECONDNAME)
            .lASTNAME(DEFAULT_L_ASTNAME)
            .pINBLOCKTIME(DEFAULT_P_INBLOCKTIME)
            .cUSTOMERSTATUS(DEFAULT_C_USTOMERSTATUS)
            .uSERNAME(DEFAULT_U_SERNAME)
            .pASSWORD(DEFAULT_P_ASSWORD)
            .dEVICEID(DEFAULT_D_EVICEID)
            .cHANNEL(DEFAULT_C_HANNEL)
            .pASSRESET(DEFAULT_P_ASSRESET)
            .pASSRESETBY(DEFAULT_P_ASSRESETBY)
            .pASSRESETON(DEFAULT_P_ASSRESETON)
            .pASSBLOCK(DEFAULT_P_ASSBLOCK)
            .pASSBLOCKBY(DEFAULT_P_ASSBLOCKBY)
            .pASSBLOCKON(DEFAULT_P_ASSBLOCKON)
            .pINMARKBLOCK(DEFAULT_P_INMARKBLOCK)
            .pASSMARKBLOCK(DEFAULT_P_ASSMARKBLOCK)
            .pASSRESETREMARKS(DEFAULT_P_ASSRESETREMARKS)
            .pASSBLOCKREMARKS(DEFAULT_P_ASSBLOCKREMARKS)
            .pASSUNBLOCKBY(DEFAULT_P_ASSUNBLOCKBY)
            .pASSTRIALS(DEFAULT_P_ASSTRIALS)
            .aPPACTIVE(DEFAULT_A_PPACTIVE)
            .lASTLOGIN(DEFAULT_L_ASTLOGIN)
            .aPPMARKEDDISABLE(DEFAULT_A_PPMARKEDDISABLE)
            .dISABLEBY(DEFAULT_D_ISABLEBY)
            .aPPROVEDISABLEBY(DEFAULT_A_PPROVEDISABLEBY)
            .aPPMARKEDENABLE(DEFAULT_A_PPMARKEDENABLE)
            .eNABLEBY(DEFAULT_E_NABLEBY)
            .aPPROVEDENABLEBY(DEFAULT_A_PPROVEDENABLEBY)
            .mARKEDDEACTIVATE(DEFAULT_M_ARKEDDEACTIVATE)
            .aPPFIRSTLOGIN(DEFAULT_A_PPFIRSTLOGIN)
            .aTMTRIALS(DEFAULT_A_TMTRIALS)
            .sHORCUTS(DEFAULT_S_HORCUTS)
            .mARKEDACTIVATE(DEFAULT_M_ARKEDACTIVATE)
            .tOWN(DEFAULT_T_OWN)
            .aPPROVEDDISABLEON(DEFAULT_A_PPROVEDDISABLEON)
            .dISABLEDON(DEFAULT_D_ISABLEDON)
            .rESETAPPROVEON(DEFAULT_R_ESETAPPROVEON)
            .dELETEDBY(DEFAULT_D_ELETEDBY)
            .qUESTIONSASKED(DEFAULT_Q_UESTIONSASKED)
            .qUESTIONSTRIALS(DEFAULT_Q_UESTIONSTRIALS)
            .qUESTIONSANSWERED(DEFAULT_Q_UESTIONSANSWERED)
            .vALIDOTP(DEFAULT_V_ALIDOTP)
            .aCTIVATEDBY(DEFAULT_A_CTIVATEDBY)
            .aCTIVATEDON(DEFAULT_A_CTIVATEDON)
            .bRANCHCODE(DEFAULT_B_RANCHCODE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CUSTOMER createUpdatedEntity() {
        return new CUSTOMER()
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cARDNUMBER(UPDATED_C_ARDNUMBER)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .lANG(UPDATED_L_ANG)
            .pIN(UPDATED_P_IN)
            .fIRSTLOGIN(UPDATED_F_IRSTLOGIN)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEGISTERED(UPDATED_R_EGISTERED)
            .cSTDELETE(UPDATED_C_STDELETE)
            .rEGDATE(UPDATED_R_EGDATE)
            .aLERTENABLED(UPDATED_A_LERTENABLED)
            .rEMARK(UPDATED_R_EMARK)
            .iMSI(UPDATED_I_MSI)
            .pARTIALLYREGISTERED(UPDATED_P_ARTIALLYREGISTERED)
            .pARTIALDATE(UPDATED_P_ARTIALDATE)
            .rEGISTERDATE(UPDATED_R_EGISTERDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .dECLINEDDATE(UPDATED_D_ECLINEDDATE)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .pOSTALADDRESS(UPDATED_P_OSTALADDRESS)
            .rESIDENCE(UPDATED_R_ESIDENCE)
            .dOB(UPDATED_D_OB)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .eMAILADDRESS(UPDATED_E_MAILADDRESS)
            .iDENTIFICATIONID(UPDATED_I_DENTIFICATIONID)
            .aDDACCOUNT(UPDATED_A_DDACCOUNT)
            .aCLINKINGINSTITUTION(UPDATED_A_CLINKINGINSTITUTION)
            .dEACTIVATED(UPDATED_D_EACTIVATED)
            .dEACTIVATEDBY(UPDATED_D_EACTIVATEDBY)
            .dEACTIVATEDON(UPDATED_D_EACTIVATEDON)
            .pHONENOCHANGED(UPDATED_P_HONENOCHANGED)
            .pHONENOCHANGEDBY(UPDATED_P_HONENOCHANGEDBY)
            .pHONENOCHANGEDON(UPDATED_P_HONENOCHANGEDON)
            .oRIGINALPHONENO(UPDATED_O_RIGINALPHONENO)
            .nEWPHONENO(UPDATED_N_EWPHONENO)
            .rESET(UPDATED_R_ESET)
            .rESETINGINSTITUTION(UPDATED_R_ESETINGINSTITUTION)
            .pINRESETREMARK(UPDATED_P_INRESETREMARK)
            .rESETBY(UPDATED_R_ESETBY)
            .rESETON(UPDATED_R_ESETON)
            .uNBLOCKINGINSTITUTION(UPDATED_U_NBLOCKINGINSTITUTION)
            .pINBLOCK(UPDATED_P_INBLOCK)
            .pINBLOCKBY(UPDATED_P_INBLOCKBY)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .bLOCKINGINSTITUTION(UPDATED_B_LOCKINGINSTITUTION)
            .pINBLOCKON(UPDATED_P_INBLOCKON)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .pINUNBLOCKBY(UPDATED_P_INUNBLOCKBY)
            .lOGGEDIN(UPDATED_L_OGGEDIN)
            .tRIALS(UPDATED_T_RIALS)
            .iDTYPE(UPDATED_I_DTYPE)
            .iDNUMBER(UPDATED_I_DNUMBER)
            .gENDER(UPDATED_G_ENDER)
            .cIF(UPDATED_C_IF)
            .dATEOFBIRTH(UPDATED_D_ATEOFBIRTH)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESETIMSI(UPDATED_R_ESETIMSI)
            .iMSIRESETBY(UPDATED_I_MSIRESETBY)
            .fIRSTNAME(UPDATED_F_IRSTNAME)
            .sECONDNAME(UPDATED_S_ECONDNAME)
            .lASTNAME(UPDATED_L_ASTNAME)
            .pINBLOCKTIME(UPDATED_P_INBLOCKTIME)
            .cUSTOMERSTATUS(UPDATED_C_USTOMERSTATUS)
            .uSERNAME(UPDATED_U_SERNAME)
            .pASSWORD(UPDATED_P_ASSWORD)
            .dEVICEID(UPDATED_D_EVICEID)
            .cHANNEL(UPDATED_C_HANNEL)
            .pASSRESET(UPDATED_P_ASSRESET)
            .pASSRESETBY(UPDATED_P_ASSRESETBY)
            .pASSRESETON(UPDATED_P_ASSRESETON)
            .pASSBLOCK(UPDATED_P_ASSBLOCK)
            .pASSBLOCKBY(UPDATED_P_ASSBLOCKBY)
            .pASSBLOCKON(UPDATED_P_ASSBLOCKON)
            .pINMARKBLOCK(UPDATED_P_INMARKBLOCK)
            .pASSMARKBLOCK(UPDATED_P_ASSMARKBLOCK)
            .pASSRESETREMARKS(UPDATED_P_ASSRESETREMARKS)
            .pASSBLOCKREMARKS(UPDATED_P_ASSBLOCKREMARKS)
            .pASSUNBLOCKBY(UPDATED_P_ASSUNBLOCKBY)
            .pASSTRIALS(UPDATED_P_ASSTRIALS)
            .aPPACTIVE(UPDATED_A_PPACTIVE)
            .lASTLOGIN(UPDATED_L_ASTLOGIN)
            .aPPMARKEDDISABLE(UPDATED_A_PPMARKEDDISABLE)
            .dISABLEBY(UPDATED_D_ISABLEBY)
            .aPPROVEDISABLEBY(UPDATED_A_PPROVEDISABLEBY)
            .aPPMARKEDENABLE(UPDATED_A_PPMARKEDENABLE)
            .eNABLEBY(UPDATED_E_NABLEBY)
            .aPPROVEDENABLEBY(UPDATED_A_PPROVEDENABLEBY)
            .mARKEDDEACTIVATE(UPDATED_M_ARKEDDEACTIVATE)
            .aPPFIRSTLOGIN(UPDATED_A_PPFIRSTLOGIN)
            .aTMTRIALS(UPDATED_A_TMTRIALS)
            .sHORCUTS(UPDATED_S_HORCUTS)
            .mARKEDACTIVATE(UPDATED_M_ARKEDACTIVATE)
            .tOWN(UPDATED_T_OWN)
            .aPPROVEDDISABLEON(UPDATED_A_PPROVEDDISABLEON)
            .dISABLEDON(UPDATED_D_ISABLEDON)
            .rESETAPPROVEON(UPDATED_R_ESETAPPROVEON)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .qUESTIONSASKED(UPDATED_Q_UESTIONSASKED)
            .qUESTIONSTRIALS(UPDATED_Q_UESTIONSTRIALS)
            .qUESTIONSANSWERED(UPDATED_Q_UESTIONSANSWERED)
            .vALIDOTP(UPDATED_V_ALIDOTP)
            .aCTIVATEDBY(UPDATED_A_CTIVATEDBY)
            .aCTIVATEDON(UPDATED_A_CTIVATEDON)
            .bRANCHCODE(UPDATED_B_RANCHCODE);
    }

    @BeforeEach
    public void initTest() {
        cUSTOMER = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCUSTOMER != null) {
            cUSTOMERRepository.delete(insertedCUSTOMER);
            insertedCUSTOMER = null;
        }
    }

    @Test
    @Transactional
    void createCUSTOMER() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CUSTOMER
        var returnedCUSTOMER = om.readValue(
            restCUSTOMERMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CUSTOMER.class
        );

        // Validate the CUSTOMER in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCUSTOMERUpdatableFieldsEquals(returnedCUSTOMER, getPersistedCUSTOMER(returnedCUSTOMER));

        insertedCUSTOMER = returnedCUSTOMER;
    }

    @Test
    @Transactional
    void createCUSTOMERWithExistingId() throws Exception {
        // Create the CUSTOMER with an existing ID
        cUSTOMER.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCUSTOMERMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER)))
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkpHONENUMBERIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cUSTOMER.setpHONENUMBER(null);

        // Create the CUSTOMER, which fails.

        restCUSTOMERMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkaCCOUNTNUMBERIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        cUSTOMER.setaCCOUNTNUMBER(null);

        // Create the CUSTOMER, which fails.

        restCUSTOMERMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCUSTOMERS() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        // Get all the cUSTOMERList
        restCUSTOMERMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cUSTOMER.getId().intValue())))
            .andExpect(jsonPath("$.[*].cUSTOMERNAME").value(hasItem(DEFAULT_C_USTOMERNAME)))
            .andExpect(jsonPath("$.[*].pHONENUMBER").value(hasItem(DEFAULT_P_HONENUMBER)))
            .andExpect(jsonPath("$.[*].cARDNUMBER").value(hasItem(DEFAULT_C_ARDNUMBER)))
            .andExpect(jsonPath("$.[*].aCCOUNTNUMBER").value(hasItem(DEFAULT_A_CCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].lANG").value(hasItem(DEFAULT_L_ANG)))
            .andExpect(jsonPath("$.[*].pIN").value(hasItem(DEFAULT_P_IN)))
            .andExpect(jsonPath("$.[*].fIRSTLOGIN").value(hasItem(DEFAULT_F_IRSTLOGIN)))
            .andExpect(jsonPath("$.[*].aCTIVE").value(hasItem(DEFAULT_A_CTIVE)))
            .andExpect(jsonPath("$.[*].rEGISTERED").value(hasItem(DEFAULT_R_EGISTERED.intValue())))
            .andExpect(jsonPath("$.[*].cSTDELETE").value(hasItem(DEFAULT_C_STDELETE.intValue())))
            .andExpect(jsonPath("$.[*].rEGDATE").value(hasItem(DEFAULT_R_EGDATE.toString())))
            .andExpect(jsonPath("$.[*].aLERTENABLED").value(hasItem(DEFAULT_A_LERTENABLED.intValue())))
            .andExpect(jsonPath("$.[*].rEMARK").value(hasItem(DEFAULT_R_EMARK)))
            .andExpect(jsonPath("$.[*].iMSI").value(hasItem(DEFAULT_I_MSI)))
            .andExpect(jsonPath("$.[*].pARTIALLYREGISTERED").value(hasItem(DEFAULT_P_ARTIALLYREGISTERED)))
            .andExpect(jsonPath("$.[*].pARTIALDATE").value(hasItem(DEFAULT_P_ARTIALDATE.toString())))
            .andExpect(jsonPath("$.[*].rEGISTERDATE").value(hasItem(DEFAULT_R_EGISTERDATE.toString())))
            .andExpect(jsonPath("$.[*].aPPROVED").value(hasItem(DEFAULT_A_PPROVED)))
            .andExpect(jsonPath("$.[*].aPPROVEDBY").value(hasItem(DEFAULT_A_PPROVEDBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDDATE").value(hasItem(DEFAULT_A_PPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].dECLINED").value(hasItem(DEFAULT_D_ECLINED)))
            .andExpect(jsonPath("$.[*].dECLINEDBY").value(hasItem(DEFAULT_D_ECLINEDBY)))
            .andExpect(jsonPath("$.[*].dECLINEDDATE").value(hasItem(DEFAULT_D_ECLINEDDATE.toString())))
            .andExpect(jsonPath("$.[*].cHECKERREMARKS").value(hasItem(DEFAULT_C_HECKERREMARKS)))
            .andExpect(jsonPath("$.[*].pOSTALADDRESS").value(hasItem(DEFAULT_P_OSTALADDRESS)))
            .andExpect(jsonPath("$.[*].rESIDENCE").value(hasItem(DEFAULT_R_ESIDENCE)))
            .andExpect(jsonPath("$.[*].dOB").value(hasItem(DEFAULT_D_OB.toString())))
            .andExpect(jsonPath("$.[*].cREATEDBY").value(hasItem(DEFAULT_C_REATEDBY)))
            .andExpect(jsonPath("$.[*].eMAILADDRESS").value(hasItem(DEFAULT_E_MAILADDRESS)))
            .andExpect(jsonPath("$.[*].iDENTIFICATIONID").value(hasItem(DEFAULT_I_DENTIFICATIONID)))
            .andExpect(jsonPath("$.[*].aDDACCOUNT").value(hasItem(DEFAULT_A_DDACCOUNT)))
            .andExpect(jsonPath("$.[*].aCLINKINGINSTITUTION").value(hasItem(DEFAULT_A_CLINKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].dEACTIVATED").value(hasItem(DEFAULT_D_EACTIVATED)))
            .andExpect(jsonPath("$.[*].dEACTIVATEDBY").value(hasItem(DEFAULT_D_EACTIVATEDBY)))
            .andExpect(jsonPath("$.[*].dEACTIVATEDON").value(hasItem(DEFAULT_D_EACTIVATEDON.toString())))
            .andExpect(jsonPath("$.[*].pHONENOCHANGED").value(hasItem(DEFAULT_P_HONENOCHANGED)))
            .andExpect(jsonPath("$.[*].pHONENOCHANGEDBY").value(hasItem(DEFAULT_P_HONENOCHANGEDBY)))
            .andExpect(jsonPath("$.[*].pHONENOCHANGEDON").value(hasItem(DEFAULT_P_HONENOCHANGEDON.toString())))
            .andExpect(jsonPath("$.[*].oRIGINALPHONENO").value(hasItem(DEFAULT_O_RIGINALPHONENO)))
            .andExpect(jsonPath("$.[*].nEWPHONENO").value(hasItem(DEFAULT_N_EWPHONENO)))
            .andExpect(jsonPath("$.[*].rESET").value(hasItem(DEFAULT_R_ESET)))
            .andExpect(jsonPath("$.[*].rESETINGINSTITUTION").value(hasItem(DEFAULT_R_ESETINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pINRESETREMARK").value(hasItem(DEFAULT_P_INRESETREMARK)))
            .andExpect(jsonPath("$.[*].rESETBY").value(hasItem(DEFAULT_R_ESETBY)))
            .andExpect(jsonPath("$.[*].rESETON").value(hasItem(DEFAULT_R_ESETON.toString())))
            .andExpect(jsonPath("$.[*].uNBLOCKINGINSTITUTION").value(hasItem(DEFAULT_U_NBLOCKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pINBLOCK").value(hasItem(DEFAULT_P_INBLOCK)))
            .andExpect(jsonPath("$.[*].pINBLOCKBY").value(hasItem(DEFAULT_P_INBLOCKBY)))
            .andExpect(jsonPath("$.[*].pINBLOCKREMARKS").value(hasItem(DEFAULT_P_INBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].bLOCKINGINSTITUTION").value(hasItem(DEFAULT_B_LOCKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pINBLOCKON").value(hasItem(DEFAULT_P_INBLOCKON.toString())))
            .andExpect(jsonPath("$.[*].aPPROVEDON").value(hasItem(DEFAULT_A_PPROVEDON.toString())))
            .andExpect(jsonPath("$.[*].pINUNBLOCKBY").value(hasItem(DEFAULT_P_INUNBLOCKBY)))
            .andExpect(jsonPath("$.[*].lOGGEDIN").value(hasItem(DEFAULT_L_OGGEDIN.intValue())))
            .andExpect(jsonPath("$.[*].tRIALS").value(hasItem(DEFAULT_T_RIALS)))
            .andExpect(jsonPath("$.[*].iDTYPE").value(hasItem(DEFAULT_I_DTYPE)))
            .andExpect(jsonPath("$.[*].iDNUMBER").value(hasItem(DEFAULT_I_DNUMBER)))
            .andExpect(jsonPath("$.[*].gENDER").value(hasItem(DEFAULT_G_ENDER)))
            .andExpect(jsonPath("$.[*].cIF").value(hasItem(DEFAULT_C_IF)))
            .andExpect(jsonPath("$.[*].dATEOFBIRTH").value(hasItem(DEFAULT_D_ATEOFBIRTH.toString())))
            .andExpect(jsonPath("$.[*].rEMARKS").value(hasItem(DEFAULT_R_EMARKS)))
            .andExpect(jsonPath("$.[*].rESETIMSI").value(hasItem(DEFAULT_R_ESETIMSI)))
            .andExpect(jsonPath("$.[*].iMSIRESETBY").value(hasItem(DEFAULT_I_MSIRESETBY)))
            .andExpect(jsonPath("$.[*].fIRSTNAME").value(hasItem(DEFAULT_F_IRSTNAME)))
            .andExpect(jsonPath("$.[*].sECONDNAME").value(hasItem(DEFAULT_S_ECONDNAME)))
            .andExpect(jsonPath("$.[*].lASTNAME").value(hasItem(DEFAULT_L_ASTNAME)))
            .andExpect(jsonPath("$.[*].pINBLOCKTIME").value(hasItem(DEFAULT_P_INBLOCKTIME)))
            .andExpect(jsonPath("$.[*].cUSTOMERSTATUS").value(hasItem(DEFAULT_C_USTOMERSTATUS)))
            .andExpect(jsonPath("$.[*].uSERNAME").value(hasItem(DEFAULT_U_SERNAME)))
            .andExpect(jsonPath("$.[*].pASSWORD").value(hasItem(DEFAULT_P_ASSWORD)))
            .andExpect(jsonPath("$.[*].dEVICEID").value(hasItem(DEFAULT_D_EVICEID)))
            .andExpect(jsonPath("$.[*].cHANNEL").value(hasItem(DEFAULT_C_HANNEL)))
            .andExpect(jsonPath("$.[*].pASSRESET").value(hasItem(DEFAULT_P_ASSRESET)))
            .andExpect(jsonPath("$.[*].pASSRESETBY").value(hasItem(DEFAULT_P_ASSRESETBY)))
            .andExpect(jsonPath("$.[*].pASSRESETON").value(hasItem(DEFAULT_P_ASSRESETON.toString())))
            .andExpect(jsonPath("$.[*].pASSBLOCK").value(hasItem(DEFAULT_P_ASSBLOCK)))
            .andExpect(jsonPath("$.[*].pASSBLOCKBY").value(hasItem(DEFAULT_P_ASSBLOCKBY)))
            .andExpect(jsonPath("$.[*].pASSBLOCKON").value(hasItem(DEFAULT_P_ASSBLOCKON.toString())))
            .andExpect(jsonPath("$.[*].pINMARKBLOCK").value(hasItem(DEFAULT_P_INMARKBLOCK)))
            .andExpect(jsonPath("$.[*].pASSMARKBLOCK").value(hasItem(DEFAULT_P_ASSMARKBLOCK)))
            .andExpect(jsonPath("$.[*].pASSRESETREMARKS").value(hasItem(DEFAULT_P_ASSRESETREMARKS)))
            .andExpect(jsonPath("$.[*].pASSBLOCKREMARKS").value(hasItem(DEFAULT_P_ASSBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].pASSUNBLOCKBY").value(hasItem(DEFAULT_P_ASSUNBLOCKBY)))
            .andExpect(jsonPath("$.[*].pASSTRIALS").value(hasItem(DEFAULT_P_ASSTRIALS)))
            .andExpect(jsonPath("$.[*].aPPACTIVE").value(hasItem(DEFAULT_A_PPACTIVE.intValue())))
            .andExpect(jsonPath("$.[*].lASTLOGIN").value(hasItem(DEFAULT_L_ASTLOGIN)))
            .andExpect(jsonPath("$.[*].aPPMARKEDDISABLE").value(hasItem(DEFAULT_A_PPMARKEDDISABLE)))
            .andExpect(jsonPath("$.[*].dISABLEBY").value(hasItem(DEFAULT_D_ISABLEBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDISABLEBY").value(hasItem(DEFAULT_A_PPROVEDISABLEBY)))
            .andExpect(jsonPath("$.[*].aPPMARKEDENABLE").value(hasItem(DEFAULT_A_PPMARKEDENABLE)))
            .andExpect(jsonPath("$.[*].eNABLEBY").value(hasItem(DEFAULT_E_NABLEBY)))
            .andExpect(jsonPath("$.[*].aPPROVEDENABLEBY").value(hasItem(DEFAULT_A_PPROVEDENABLEBY)))
            .andExpect(jsonPath("$.[*].mARKEDDEACTIVATE").value(hasItem(DEFAULT_M_ARKEDDEACTIVATE)))
            .andExpect(jsonPath("$.[*].aPPFIRSTLOGIN").value(hasItem(DEFAULT_A_PPFIRSTLOGIN)))
            .andExpect(jsonPath("$.[*].aTMTRIALS").value(hasItem(DEFAULT_A_TMTRIALS)))
            .andExpect(jsonPath("$.[*].sHORCUTS").value(hasItem(DEFAULT_S_HORCUTS)))
            .andExpect(jsonPath("$.[*].mARKEDACTIVATE").value(hasItem(DEFAULT_M_ARKEDACTIVATE)))
            .andExpect(jsonPath("$.[*].tOWN").value(hasItem(DEFAULT_T_OWN)))
            .andExpect(jsonPath("$.[*].aPPROVEDDISABLEON").value(hasItem(DEFAULT_A_PPROVEDDISABLEON.toString())))
            .andExpect(jsonPath("$.[*].dISABLEDON").value(hasItem(DEFAULT_D_ISABLEDON.toString())))
            .andExpect(jsonPath("$.[*].rESETAPPROVEON").value(hasItem(DEFAULT_R_ESETAPPROVEON.toString())))
            .andExpect(jsonPath("$.[*].dELETEDBY").value(hasItem(DEFAULT_D_ELETEDBY)))
            .andExpect(jsonPath("$.[*].qUESTIONSASKED").value(hasItem(DEFAULT_Q_UESTIONSASKED)))
            .andExpect(jsonPath("$.[*].qUESTIONSTRIALS").value(hasItem(DEFAULT_Q_UESTIONSTRIALS)))
            .andExpect(jsonPath("$.[*].qUESTIONSANSWERED").value(hasItem(DEFAULT_Q_UESTIONSANSWERED)))
            .andExpect(jsonPath("$.[*].vALIDOTP").value(hasItem(DEFAULT_V_ALIDOTP)))
            .andExpect(jsonPath("$.[*].aCTIVATEDBY").value(hasItem(DEFAULT_A_CTIVATEDBY)))
            .andExpect(jsonPath("$.[*].aCTIVATEDON").value(hasItem(DEFAULT_A_CTIVATEDON.toString())))
            .andExpect(jsonPath("$.[*].bRANCHCODE").value(hasItem(DEFAULT_B_RANCHCODE)));
    }

    @Test
    @Transactional
    void getCUSTOMER() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        // Get the cUSTOMER
        restCUSTOMERMockMvc
            .perform(get(ENTITY_API_URL_ID, cUSTOMER.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cUSTOMER.getId().intValue()))
            .andExpect(jsonPath("$.cUSTOMERNAME").value(DEFAULT_C_USTOMERNAME))
            .andExpect(jsonPath("$.pHONENUMBER").value(DEFAULT_P_HONENUMBER))
            .andExpect(jsonPath("$.cARDNUMBER").value(DEFAULT_C_ARDNUMBER))
            .andExpect(jsonPath("$.aCCOUNTNUMBER").value(DEFAULT_A_CCOUNTNUMBER))
            .andExpect(jsonPath("$.lANG").value(DEFAULT_L_ANG))
            .andExpect(jsonPath("$.pIN").value(DEFAULT_P_IN))
            .andExpect(jsonPath("$.fIRSTLOGIN").value(DEFAULT_F_IRSTLOGIN))
            .andExpect(jsonPath("$.aCTIVE").value(DEFAULT_A_CTIVE))
            .andExpect(jsonPath("$.rEGISTERED").value(DEFAULT_R_EGISTERED.intValue()))
            .andExpect(jsonPath("$.cSTDELETE").value(DEFAULT_C_STDELETE.intValue()))
            .andExpect(jsonPath("$.rEGDATE").value(DEFAULT_R_EGDATE.toString()))
            .andExpect(jsonPath("$.aLERTENABLED").value(DEFAULT_A_LERTENABLED.intValue()))
            .andExpect(jsonPath("$.rEMARK").value(DEFAULT_R_EMARK))
            .andExpect(jsonPath("$.iMSI").value(DEFAULT_I_MSI))
            .andExpect(jsonPath("$.pARTIALLYREGISTERED").value(DEFAULT_P_ARTIALLYREGISTERED))
            .andExpect(jsonPath("$.pARTIALDATE").value(DEFAULT_P_ARTIALDATE.toString()))
            .andExpect(jsonPath("$.rEGISTERDATE").value(DEFAULT_R_EGISTERDATE.toString()))
            .andExpect(jsonPath("$.aPPROVED").value(DEFAULT_A_PPROVED))
            .andExpect(jsonPath("$.aPPROVEDBY").value(DEFAULT_A_PPROVEDBY))
            .andExpect(jsonPath("$.aPPROVEDDATE").value(DEFAULT_A_PPROVEDDATE.toString()))
            .andExpect(jsonPath("$.dECLINED").value(DEFAULT_D_ECLINED))
            .andExpect(jsonPath("$.dECLINEDBY").value(DEFAULT_D_ECLINEDBY))
            .andExpect(jsonPath("$.dECLINEDDATE").value(DEFAULT_D_ECLINEDDATE.toString()))
            .andExpect(jsonPath("$.cHECKERREMARKS").value(DEFAULT_C_HECKERREMARKS))
            .andExpect(jsonPath("$.pOSTALADDRESS").value(DEFAULT_P_OSTALADDRESS))
            .andExpect(jsonPath("$.rESIDENCE").value(DEFAULT_R_ESIDENCE))
            .andExpect(jsonPath("$.dOB").value(DEFAULT_D_OB.toString()))
            .andExpect(jsonPath("$.cREATEDBY").value(DEFAULT_C_REATEDBY))
            .andExpect(jsonPath("$.eMAILADDRESS").value(DEFAULT_E_MAILADDRESS))
            .andExpect(jsonPath("$.iDENTIFICATIONID").value(DEFAULT_I_DENTIFICATIONID))
            .andExpect(jsonPath("$.aDDACCOUNT").value(DEFAULT_A_DDACCOUNT))
            .andExpect(jsonPath("$.aCLINKINGINSTITUTION").value(DEFAULT_A_CLINKINGINSTITUTION))
            .andExpect(jsonPath("$.dEACTIVATED").value(DEFAULT_D_EACTIVATED))
            .andExpect(jsonPath("$.dEACTIVATEDBY").value(DEFAULT_D_EACTIVATEDBY))
            .andExpect(jsonPath("$.dEACTIVATEDON").value(DEFAULT_D_EACTIVATEDON.toString()))
            .andExpect(jsonPath("$.pHONENOCHANGED").value(DEFAULT_P_HONENOCHANGED))
            .andExpect(jsonPath("$.pHONENOCHANGEDBY").value(DEFAULT_P_HONENOCHANGEDBY))
            .andExpect(jsonPath("$.pHONENOCHANGEDON").value(DEFAULT_P_HONENOCHANGEDON.toString()))
            .andExpect(jsonPath("$.oRIGINALPHONENO").value(DEFAULT_O_RIGINALPHONENO))
            .andExpect(jsonPath("$.nEWPHONENO").value(DEFAULT_N_EWPHONENO))
            .andExpect(jsonPath("$.rESET").value(DEFAULT_R_ESET))
            .andExpect(jsonPath("$.rESETINGINSTITUTION").value(DEFAULT_R_ESETINGINSTITUTION))
            .andExpect(jsonPath("$.pINRESETREMARK").value(DEFAULT_P_INRESETREMARK))
            .andExpect(jsonPath("$.rESETBY").value(DEFAULT_R_ESETBY))
            .andExpect(jsonPath("$.rESETON").value(DEFAULT_R_ESETON.toString()))
            .andExpect(jsonPath("$.uNBLOCKINGINSTITUTION").value(DEFAULT_U_NBLOCKINGINSTITUTION))
            .andExpect(jsonPath("$.pINBLOCK").value(DEFAULT_P_INBLOCK))
            .andExpect(jsonPath("$.pINBLOCKBY").value(DEFAULT_P_INBLOCKBY))
            .andExpect(jsonPath("$.pINBLOCKREMARKS").value(DEFAULT_P_INBLOCKREMARKS))
            .andExpect(jsonPath("$.bLOCKINGINSTITUTION").value(DEFAULT_B_LOCKINGINSTITUTION))
            .andExpect(jsonPath("$.pINBLOCKON").value(DEFAULT_P_INBLOCKON.toString()))
            .andExpect(jsonPath("$.aPPROVEDON").value(DEFAULT_A_PPROVEDON.toString()))
            .andExpect(jsonPath("$.pINUNBLOCKBY").value(DEFAULT_P_INUNBLOCKBY))
            .andExpect(jsonPath("$.lOGGEDIN").value(DEFAULT_L_OGGEDIN.intValue()))
            .andExpect(jsonPath("$.tRIALS").value(DEFAULT_T_RIALS))
            .andExpect(jsonPath("$.iDTYPE").value(DEFAULT_I_DTYPE))
            .andExpect(jsonPath("$.iDNUMBER").value(DEFAULT_I_DNUMBER))
            .andExpect(jsonPath("$.gENDER").value(DEFAULT_G_ENDER))
            .andExpect(jsonPath("$.cIF").value(DEFAULT_C_IF))
            .andExpect(jsonPath("$.dATEOFBIRTH").value(DEFAULT_D_ATEOFBIRTH.toString()))
            .andExpect(jsonPath("$.rEMARKS").value(DEFAULT_R_EMARKS))
            .andExpect(jsonPath("$.rESETIMSI").value(DEFAULT_R_ESETIMSI))
            .andExpect(jsonPath("$.iMSIRESETBY").value(DEFAULT_I_MSIRESETBY))
            .andExpect(jsonPath("$.fIRSTNAME").value(DEFAULT_F_IRSTNAME))
            .andExpect(jsonPath("$.sECONDNAME").value(DEFAULT_S_ECONDNAME))
            .andExpect(jsonPath("$.lASTNAME").value(DEFAULT_L_ASTNAME))
            .andExpect(jsonPath("$.pINBLOCKTIME").value(DEFAULT_P_INBLOCKTIME))
            .andExpect(jsonPath("$.cUSTOMERSTATUS").value(DEFAULT_C_USTOMERSTATUS))
            .andExpect(jsonPath("$.uSERNAME").value(DEFAULT_U_SERNAME))
            .andExpect(jsonPath("$.pASSWORD").value(DEFAULT_P_ASSWORD))
            .andExpect(jsonPath("$.dEVICEID").value(DEFAULT_D_EVICEID))
            .andExpect(jsonPath("$.cHANNEL").value(DEFAULT_C_HANNEL))
            .andExpect(jsonPath("$.pASSRESET").value(DEFAULT_P_ASSRESET))
            .andExpect(jsonPath("$.pASSRESETBY").value(DEFAULT_P_ASSRESETBY))
            .andExpect(jsonPath("$.pASSRESETON").value(DEFAULT_P_ASSRESETON.toString()))
            .andExpect(jsonPath("$.pASSBLOCK").value(DEFAULT_P_ASSBLOCK))
            .andExpect(jsonPath("$.pASSBLOCKBY").value(DEFAULT_P_ASSBLOCKBY))
            .andExpect(jsonPath("$.pASSBLOCKON").value(DEFAULT_P_ASSBLOCKON.toString()))
            .andExpect(jsonPath("$.pINMARKBLOCK").value(DEFAULT_P_INMARKBLOCK))
            .andExpect(jsonPath("$.pASSMARKBLOCK").value(DEFAULT_P_ASSMARKBLOCK))
            .andExpect(jsonPath("$.pASSRESETREMARKS").value(DEFAULT_P_ASSRESETREMARKS))
            .andExpect(jsonPath("$.pASSBLOCKREMARKS").value(DEFAULT_P_ASSBLOCKREMARKS))
            .andExpect(jsonPath("$.pASSUNBLOCKBY").value(DEFAULT_P_ASSUNBLOCKBY))
            .andExpect(jsonPath("$.pASSTRIALS").value(DEFAULT_P_ASSTRIALS))
            .andExpect(jsonPath("$.aPPACTIVE").value(DEFAULT_A_PPACTIVE.intValue()))
            .andExpect(jsonPath("$.lASTLOGIN").value(DEFAULT_L_ASTLOGIN))
            .andExpect(jsonPath("$.aPPMARKEDDISABLE").value(DEFAULT_A_PPMARKEDDISABLE))
            .andExpect(jsonPath("$.dISABLEBY").value(DEFAULT_D_ISABLEBY))
            .andExpect(jsonPath("$.aPPROVEDISABLEBY").value(DEFAULT_A_PPROVEDISABLEBY))
            .andExpect(jsonPath("$.aPPMARKEDENABLE").value(DEFAULT_A_PPMARKEDENABLE))
            .andExpect(jsonPath("$.eNABLEBY").value(DEFAULT_E_NABLEBY))
            .andExpect(jsonPath("$.aPPROVEDENABLEBY").value(DEFAULT_A_PPROVEDENABLEBY))
            .andExpect(jsonPath("$.mARKEDDEACTIVATE").value(DEFAULT_M_ARKEDDEACTIVATE))
            .andExpect(jsonPath("$.aPPFIRSTLOGIN").value(DEFAULT_A_PPFIRSTLOGIN))
            .andExpect(jsonPath("$.aTMTRIALS").value(DEFAULT_A_TMTRIALS))
            .andExpect(jsonPath("$.sHORCUTS").value(DEFAULT_S_HORCUTS))
            .andExpect(jsonPath("$.mARKEDACTIVATE").value(DEFAULT_M_ARKEDACTIVATE))
            .andExpect(jsonPath("$.tOWN").value(DEFAULT_T_OWN))
            .andExpect(jsonPath("$.aPPROVEDDISABLEON").value(DEFAULT_A_PPROVEDDISABLEON.toString()))
            .andExpect(jsonPath("$.dISABLEDON").value(DEFAULT_D_ISABLEDON.toString()))
            .andExpect(jsonPath("$.rESETAPPROVEON").value(DEFAULT_R_ESETAPPROVEON.toString()))
            .andExpect(jsonPath("$.dELETEDBY").value(DEFAULT_D_ELETEDBY))
            .andExpect(jsonPath("$.qUESTIONSASKED").value(DEFAULT_Q_UESTIONSASKED))
            .andExpect(jsonPath("$.qUESTIONSTRIALS").value(DEFAULT_Q_UESTIONSTRIALS))
            .andExpect(jsonPath("$.qUESTIONSANSWERED").value(DEFAULT_Q_UESTIONSANSWERED))
            .andExpect(jsonPath("$.vALIDOTP").value(DEFAULT_V_ALIDOTP))
            .andExpect(jsonPath("$.aCTIVATEDBY").value(DEFAULT_A_CTIVATEDBY))
            .andExpect(jsonPath("$.aCTIVATEDON").value(DEFAULT_A_CTIVATEDON.toString()))
            .andExpect(jsonPath("$.bRANCHCODE").value(DEFAULT_B_RANCHCODE));
    }

    @Test
    @Transactional
    void getNonExistingCUSTOMER() throws Exception {
        // Get the cUSTOMER
        restCUSTOMERMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCUSTOMER() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMER
        CUSTOMER updatedCUSTOMER = cUSTOMERRepository.findById(cUSTOMER.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCUSTOMER are not directly saved in db
        em.detach(updatedCUSTOMER);
        updatedCUSTOMER
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cARDNUMBER(UPDATED_C_ARDNUMBER)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .lANG(UPDATED_L_ANG)
            .pIN(UPDATED_P_IN)
            .fIRSTLOGIN(UPDATED_F_IRSTLOGIN)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEGISTERED(UPDATED_R_EGISTERED)
            .cSTDELETE(UPDATED_C_STDELETE)
            .rEGDATE(UPDATED_R_EGDATE)
            .aLERTENABLED(UPDATED_A_LERTENABLED)
            .rEMARK(UPDATED_R_EMARK)
            .iMSI(UPDATED_I_MSI)
            .pARTIALLYREGISTERED(UPDATED_P_ARTIALLYREGISTERED)
            .pARTIALDATE(UPDATED_P_ARTIALDATE)
            .rEGISTERDATE(UPDATED_R_EGISTERDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .dECLINEDDATE(UPDATED_D_ECLINEDDATE)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .pOSTALADDRESS(UPDATED_P_OSTALADDRESS)
            .rESIDENCE(UPDATED_R_ESIDENCE)
            .dOB(UPDATED_D_OB)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .eMAILADDRESS(UPDATED_E_MAILADDRESS)
            .iDENTIFICATIONID(UPDATED_I_DENTIFICATIONID)
            .aDDACCOUNT(UPDATED_A_DDACCOUNT)
            .aCLINKINGINSTITUTION(UPDATED_A_CLINKINGINSTITUTION)
            .dEACTIVATED(UPDATED_D_EACTIVATED)
            .dEACTIVATEDBY(UPDATED_D_EACTIVATEDBY)
            .dEACTIVATEDON(UPDATED_D_EACTIVATEDON)
            .pHONENOCHANGED(UPDATED_P_HONENOCHANGED)
            .pHONENOCHANGEDBY(UPDATED_P_HONENOCHANGEDBY)
            .pHONENOCHANGEDON(UPDATED_P_HONENOCHANGEDON)
            .oRIGINALPHONENO(UPDATED_O_RIGINALPHONENO)
            .nEWPHONENO(UPDATED_N_EWPHONENO)
            .rESET(UPDATED_R_ESET)
            .rESETINGINSTITUTION(UPDATED_R_ESETINGINSTITUTION)
            .pINRESETREMARK(UPDATED_P_INRESETREMARK)
            .rESETBY(UPDATED_R_ESETBY)
            .rESETON(UPDATED_R_ESETON)
            .uNBLOCKINGINSTITUTION(UPDATED_U_NBLOCKINGINSTITUTION)
            .pINBLOCK(UPDATED_P_INBLOCK)
            .pINBLOCKBY(UPDATED_P_INBLOCKBY)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .bLOCKINGINSTITUTION(UPDATED_B_LOCKINGINSTITUTION)
            .pINBLOCKON(UPDATED_P_INBLOCKON)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .pINUNBLOCKBY(UPDATED_P_INUNBLOCKBY)
            .lOGGEDIN(UPDATED_L_OGGEDIN)
            .tRIALS(UPDATED_T_RIALS)
            .iDTYPE(UPDATED_I_DTYPE)
            .iDNUMBER(UPDATED_I_DNUMBER)
            .gENDER(UPDATED_G_ENDER)
            .cIF(UPDATED_C_IF)
            .dATEOFBIRTH(UPDATED_D_ATEOFBIRTH)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESETIMSI(UPDATED_R_ESETIMSI)
            .iMSIRESETBY(UPDATED_I_MSIRESETBY)
            .fIRSTNAME(UPDATED_F_IRSTNAME)
            .sECONDNAME(UPDATED_S_ECONDNAME)
            .lASTNAME(UPDATED_L_ASTNAME)
            .pINBLOCKTIME(UPDATED_P_INBLOCKTIME)
            .cUSTOMERSTATUS(UPDATED_C_USTOMERSTATUS)
            .uSERNAME(UPDATED_U_SERNAME)
            .pASSWORD(UPDATED_P_ASSWORD)
            .dEVICEID(UPDATED_D_EVICEID)
            .cHANNEL(UPDATED_C_HANNEL)
            .pASSRESET(UPDATED_P_ASSRESET)
            .pASSRESETBY(UPDATED_P_ASSRESETBY)
            .pASSRESETON(UPDATED_P_ASSRESETON)
            .pASSBLOCK(UPDATED_P_ASSBLOCK)
            .pASSBLOCKBY(UPDATED_P_ASSBLOCKBY)
            .pASSBLOCKON(UPDATED_P_ASSBLOCKON)
            .pINMARKBLOCK(UPDATED_P_INMARKBLOCK)
            .pASSMARKBLOCK(UPDATED_P_ASSMARKBLOCK)
            .pASSRESETREMARKS(UPDATED_P_ASSRESETREMARKS)
            .pASSBLOCKREMARKS(UPDATED_P_ASSBLOCKREMARKS)
            .pASSUNBLOCKBY(UPDATED_P_ASSUNBLOCKBY)
            .pASSTRIALS(UPDATED_P_ASSTRIALS)
            .aPPACTIVE(UPDATED_A_PPACTIVE)
            .lASTLOGIN(UPDATED_L_ASTLOGIN)
            .aPPMARKEDDISABLE(UPDATED_A_PPMARKEDDISABLE)
            .dISABLEBY(UPDATED_D_ISABLEBY)
            .aPPROVEDISABLEBY(UPDATED_A_PPROVEDISABLEBY)
            .aPPMARKEDENABLE(UPDATED_A_PPMARKEDENABLE)
            .eNABLEBY(UPDATED_E_NABLEBY)
            .aPPROVEDENABLEBY(UPDATED_A_PPROVEDENABLEBY)
            .mARKEDDEACTIVATE(UPDATED_M_ARKEDDEACTIVATE)
            .aPPFIRSTLOGIN(UPDATED_A_PPFIRSTLOGIN)
            .aTMTRIALS(UPDATED_A_TMTRIALS)
            .sHORCUTS(UPDATED_S_HORCUTS)
            .mARKEDACTIVATE(UPDATED_M_ARKEDACTIVATE)
            .tOWN(UPDATED_T_OWN)
            .aPPROVEDDISABLEON(UPDATED_A_PPROVEDDISABLEON)
            .dISABLEDON(UPDATED_D_ISABLEDON)
            .rESETAPPROVEON(UPDATED_R_ESETAPPROVEON)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .qUESTIONSASKED(UPDATED_Q_UESTIONSASKED)
            .qUESTIONSTRIALS(UPDATED_Q_UESTIONSTRIALS)
            .qUESTIONSANSWERED(UPDATED_Q_UESTIONSANSWERED)
            .vALIDOTP(UPDATED_V_ALIDOTP)
            .aCTIVATEDBY(UPDATED_A_CTIVATEDBY)
            .aCTIVATEDON(UPDATED_A_CTIVATEDON)
            .bRANCHCODE(UPDATED_B_RANCHCODE);

        restCUSTOMERMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCUSTOMER.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCUSTOMER))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCUSTOMERToMatchAllProperties(updatedCUSTOMER);
    }

    @Test
    @Transactional
    void putNonExistingCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cUSTOMER.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cUSTOMER))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cUSTOMER)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCUSTOMERWithPatch() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMER using partial update
        CUSTOMER partialUpdatedCUSTOMER = new CUSTOMER();
        partialUpdatedCUSTOMER.setId(cUSTOMER.getId());

        partialUpdatedCUSTOMER
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .pARTIALLYREGISTERED(UPDATED_P_ARTIALLYREGISTERED)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .pOSTALADDRESS(UPDATED_P_OSTALADDRESS)
            .rESIDENCE(UPDATED_R_ESIDENCE)
            .eMAILADDRESS(UPDATED_E_MAILADDRESS)
            .aDDACCOUNT(UPDATED_A_DDACCOUNT)
            .dEACTIVATEDBY(UPDATED_D_EACTIVATEDBY)
            .dEACTIVATEDON(UPDATED_D_EACTIVATEDON)
            .pHONENOCHANGED(UPDATED_P_HONENOCHANGED)
            .pHONENOCHANGEDBY(UPDATED_P_HONENOCHANGEDBY)
            .nEWPHONENO(UPDATED_N_EWPHONENO)
            .rESETBY(UPDATED_R_ESETBY)
            .rESETON(UPDATED_R_ESETON)
            .uNBLOCKINGINSTITUTION(UPDATED_U_NBLOCKINGINSTITUTION)
            .pINBLOCK(UPDATED_P_INBLOCK)
            .pINBLOCKBY(UPDATED_P_INBLOCKBY)
            .bLOCKINGINSTITUTION(UPDATED_B_LOCKINGINSTITUTION)
            .tRIALS(UPDATED_T_RIALS)
            .cIF(UPDATED_C_IF)
            .rESETIMSI(UPDATED_R_ESETIMSI)
            .fIRSTNAME(UPDATED_F_IRSTNAME)
            .lASTNAME(UPDATED_L_ASTNAME)
            .pINBLOCKTIME(UPDATED_P_INBLOCKTIME)
            .cUSTOMERSTATUS(UPDATED_C_USTOMERSTATUS)
            .uSERNAME(UPDATED_U_SERNAME)
            .pASSRESET(UPDATED_P_ASSRESET)
            .pASSRESETBY(UPDATED_P_ASSRESETBY)
            .pASSRESETON(UPDATED_P_ASSRESETON)
            .pASSBLOCKON(UPDATED_P_ASSBLOCKON)
            .pASSUNBLOCKBY(UPDATED_P_ASSUNBLOCKBY)
            .pASSTRIALS(UPDATED_P_ASSTRIALS)
            .aPPMARKEDDISABLE(UPDATED_A_PPMARKEDDISABLE)
            .aPPROVEDISABLEBY(UPDATED_A_PPROVEDISABLEBY)
            .aPPMARKEDENABLE(UPDATED_A_PPMARKEDENABLE)
            .eNABLEBY(UPDATED_E_NABLEBY)
            .aPPROVEDENABLEBY(UPDATED_A_PPROVEDENABLEBY)
            .aPPFIRSTLOGIN(UPDATED_A_PPFIRSTLOGIN)
            .aTMTRIALS(UPDATED_A_TMTRIALS)
            .rESETAPPROVEON(UPDATED_R_ESETAPPROVEON)
            .qUESTIONSASKED(UPDATED_Q_UESTIONSASKED)
            .vALIDOTP(UPDATED_V_ALIDOTP)
            .aCTIVATEDON(UPDATED_A_CTIVATEDON)
            .bRANCHCODE(UPDATED_B_RANCHCODE);

        restCUSTOMERMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCUSTOMER.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCUSTOMER))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMER in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCUSTOMERUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCUSTOMER, cUSTOMER), getPersistedCUSTOMER(cUSTOMER));
    }

    @Test
    @Transactional
    void fullUpdateCUSTOMERWithPatch() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cUSTOMER using partial update
        CUSTOMER partialUpdatedCUSTOMER = new CUSTOMER();
        partialUpdatedCUSTOMER.setId(cUSTOMER.getId());

        partialUpdatedCUSTOMER
            .cUSTOMERNAME(UPDATED_C_USTOMERNAME)
            .pHONENUMBER(UPDATED_P_HONENUMBER)
            .cARDNUMBER(UPDATED_C_ARDNUMBER)
            .aCCOUNTNUMBER(UPDATED_A_CCOUNTNUMBER)
            .lANG(UPDATED_L_ANG)
            .pIN(UPDATED_P_IN)
            .fIRSTLOGIN(UPDATED_F_IRSTLOGIN)
            .aCTIVE(UPDATED_A_CTIVE)
            .rEGISTERED(UPDATED_R_EGISTERED)
            .cSTDELETE(UPDATED_C_STDELETE)
            .rEGDATE(UPDATED_R_EGDATE)
            .aLERTENABLED(UPDATED_A_LERTENABLED)
            .rEMARK(UPDATED_R_EMARK)
            .iMSI(UPDATED_I_MSI)
            .pARTIALLYREGISTERED(UPDATED_P_ARTIALLYREGISTERED)
            .pARTIALDATE(UPDATED_P_ARTIALDATE)
            .rEGISTERDATE(UPDATED_R_EGISTERDATE)
            .aPPROVED(UPDATED_A_PPROVED)
            .aPPROVEDBY(UPDATED_A_PPROVEDBY)
            .aPPROVEDDATE(UPDATED_A_PPROVEDDATE)
            .dECLINED(UPDATED_D_ECLINED)
            .dECLINEDBY(UPDATED_D_ECLINEDBY)
            .dECLINEDDATE(UPDATED_D_ECLINEDDATE)
            .cHECKERREMARKS(UPDATED_C_HECKERREMARKS)
            .pOSTALADDRESS(UPDATED_P_OSTALADDRESS)
            .rESIDENCE(UPDATED_R_ESIDENCE)
            .dOB(UPDATED_D_OB)
            .cREATEDBY(UPDATED_C_REATEDBY)
            .eMAILADDRESS(UPDATED_E_MAILADDRESS)
            .iDENTIFICATIONID(UPDATED_I_DENTIFICATIONID)
            .aDDACCOUNT(UPDATED_A_DDACCOUNT)
            .aCLINKINGINSTITUTION(UPDATED_A_CLINKINGINSTITUTION)
            .dEACTIVATED(UPDATED_D_EACTIVATED)
            .dEACTIVATEDBY(UPDATED_D_EACTIVATEDBY)
            .dEACTIVATEDON(UPDATED_D_EACTIVATEDON)
            .pHONENOCHANGED(UPDATED_P_HONENOCHANGED)
            .pHONENOCHANGEDBY(UPDATED_P_HONENOCHANGEDBY)
            .pHONENOCHANGEDON(UPDATED_P_HONENOCHANGEDON)
            .oRIGINALPHONENO(UPDATED_O_RIGINALPHONENO)
            .nEWPHONENO(UPDATED_N_EWPHONENO)
            .rESET(UPDATED_R_ESET)
            .rESETINGINSTITUTION(UPDATED_R_ESETINGINSTITUTION)
            .pINRESETREMARK(UPDATED_P_INRESETREMARK)
            .rESETBY(UPDATED_R_ESETBY)
            .rESETON(UPDATED_R_ESETON)
            .uNBLOCKINGINSTITUTION(UPDATED_U_NBLOCKINGINSTITUTION)
            .pINBLOCK(UPDATED_P_INBLOCK)
            .pINBLOCKBY(UPDATED_P_INBLOCKBY)
            .pINBLOCKREMARKS(UPDATED_P_INBLOCKREMARKS)
            .bLOCKINGINSTITUTION(UPDATED_B_LOCKINGINSTITUTION)
            .pINBLOCKON(UPDATED_P_INBLOCKON)
            .aPPROVEDON(UPDATED_A_PPROVEDON)
            .pINUNBLOCKBY(UPDATED_P_INUNBLOCKBY)
            .lOGGEDIN(UPDATED_L_OGGEDIN)
            .tRIALS(UPDATED_T_RIALS)
            .iDTYPE(UPDATED_I_DTYPE)
            .iDNUMBER(UPDATED_I_DNUMBER)
            .gENDER(UPDATED_G_ENDER)
            .cIF(UPDATED_C_IF)
            .dATEOFBIRTH(UPDATED_D_ATEOFBIRTH)
            .rEMARKS(UPDATED_R_EMARKS)
            .rESETIMSI(UPDATED_R_ESETIMSI)
            .iMSIRESETBY(UPDATED_I_MSIRESETBY)
            .fIRSTNAME(UPDATED_F_IRSTNAME)
            .sECONDNAME(UPDATED_S_ECONDNAME)
            .lASTNAME(UPDATED_L_ASTNAME)
            .pINBLOCKTIME(UPDATED_P_INBLOCKTIME)
            .cUSTOMERSTATUS(UPDATED_C_USTOMERSTATUS)
            .uSERNAME(UPDATED_U_SERNAME)
            .pASSWORD(UPDATED_P_ASSWORD)
            .dEVICEID(UPDATED_D_EVICEID)
            .cHANNEL(UPDATED_C_HANNEL)
            .pASSRESET(UPDATED_P_ASSRESET)
            .pASSRESETBY(UPDATED_P_ASSRESETBY)
            .pASSRESETON(UPDATED_P_ASSRESETON)
            .pASSBLOCK(UPDATED_P_ASSBLOCK)
            .pASSBLOCKBY(UPDATED_P_ASSBLOCKBY)
            .pASSBLOCKON(UPDATED_P_ASSBLOCKON)
            .pINMARKBLOCK(UPDATED_P_INMARKBLOCK)
            .pASSMARKBLOCK(UPDATED_P_ASSMARKBLOCK)
            .pASSRESETREMARKS(UPDATED_P_ASSRESETREMARKS)
            .pASSBLOCKREMARKS(UPDATED_P_ASSBLOCKREMARKS)
            .pASSUNBLOCKBY(UPDATED_P_ASSUNBLOCKBY)
            .pASSTRIALS(UPDATED_P_ASSTRIALS)
            .aPPACTIVE(UPDATED_A_PPACTIVE)
            .lASTLOGIN(UPDATED_L_ASTLOGIN)
            .aPPMARKEDDISABLE(UPDATED_A_PPMARKEDDISABLE)
            .dISABLEBY(UPDATED_D_ISABLEBY)
            .aPPROVEDISABLEBY(UPDATED_A_PPROVEDISABLEBY)
            .aPPMARKEDENABLE(UPDATED_A_PPMARKEDENABLE)
            .eNABLEBY(UPDATED_E_NABLEBY)
            .aPPROVEDENABLEBY(UPDATED_A_PPROVEDENABLEBY)
            .mARKEDDEACTIVATE(UPDATED_M_ARKEDDEACTIVATE)
            .aPPFIRSTLOGIN(UPDATED_A_PPFIRSTLOGIN)
            .aTMTRIALS(UPDATED_A_TMTRIALS)
            .sHORCUTS(UPDATED_S_HORCUTS)
            .mARKEDACTIVATE(UPDATED_M_ARKEDACTIVATE)
            .tOWN(UPDATED_T_OWN)
            .aPPROVEDDISABLEON(UPDATED_A_PPROVEDDISABLEON)
            .dISABLEDON(UPDATED_D_ISABLEDON)
            .rESETAPPROVEON(UPDATED_R_ESETAPPROVEON)
            .dELETEDBY(UPDATED_D_ELETEDBY)
            .qUESTIONSASKED(UPDATED_Q_UESTIONSASKED)
            .qUESTIONSTRIALS(UPDATED_Q_UESTIONSTRIALS)
            .qUESTIONSANSWERED(UPDATED_Q_UESTIONSANSWERED)
            .vALIDOTP(UPDATED_V_ALIDOTP)
            .aCTIVATEDBY(UPDATED_A_CTIVATEDBY)
            .aCTIVATEDON(UPDATED_A_CTIVATEDON)
            .bRANCHCODE(UPDATED_B_RANCHCODE);

        restCUSTOMERMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCUSTOMER.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCUSTOMER))
            )
            .andExpect(status().isOk());

        // Validate the CUSTOMER in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCUSTOMERUpdatableFieldsEquals(partialUpdatedCUSTOMER, getPersistedCUSTOMER(partialUpdatedCUSTOMER));
    }

    @Test
    @Transactional
    void patchNonExistingCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cUSTOMER.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cUSTOMER))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cUSTOMER))
            )
            .andExpect(status().isBadRequest());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCUSTOMER() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cUSTOMER.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCUSTOMERMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cUSTOMER)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CUSTOMER in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCUSTOMER() throws Exception {
        // Initialize the database
        insertedCUSTOMER = cUSTOMERRepository.saveAndFlush(cUSTOMER);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cUSTOMER
        restCUSTOMERMockMvc
            .perform(delete(ENTITY_API_URL_ID, cUSTOMER.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cUSTOMERRepository.count();
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

    protected CUSTOMER getPersistedCUSTOMER(CUSTOMER cUSTOMER) {
        return cUSTOMERRepository.findById(cUSTOMER.getId()).orElseThrow();
    }

    protected void assertPersistedCUSTOMERToMatchAllProperties(CUSTOMER expectedCUSTOMER) {
        assertCUSTOMERAllPropertiesEquals(expectedCUSTOMER, getPersistedCUSTOMER(expectedCUSTOMER));
    }

    protected void assertPersistedCUSTOMERToMatchUpdatableProperties(CUSTOMER expectedCUSTOMER) {
        assertCUSTOMERAllUpdatablePropertiesEquals(expectedCUSTOMER, getPersistedCUSTOMER(expectedCUSTOMER));
    }
}
