package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CUSTOMER.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CUSTOMER implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "c_ustomername", length = 200)
    private String cUSTOMERNAME;

    @NotNull
    @Size(max = 12)
    @Column(name = "p_honenumber", length = 12, nullable = false)
    private String pHONENUMBER;

    @Size(max = 1000)
    @Column(name = "c_ardnumber", length = 1000)
    private String cARDNUMBER;

    @NotNull
    @Size(max = 20)
    @Column(name = "a_ccountnumber", length = 20, nullable = false)
    private String aCCOUNTNUMBER;

    @Size(max = 10)
    @Column(name = "l_ang", length = 10)
    private String lANG;

    @Size(max = 200)
    @Column(name = "p_in", length = 200)
    private String pIN;

    @Size(max = 1)
    @Column(name = "f_irstlogin", length = 1)
    private String fIRSTLOGIN;

    @Size(max = 1)
    @Column(name = "a_ctive", length = 1)
    private String aCTIVE;

    @Column(name = "r_egistered")
    private Long rEGISTERED;

    @Column(name = "c_stdelete")
    private Long cSTDELETE;

    @Column(name = "r_egdate")
    private Instant rEGDATE;

    @Column(name = "a_lertenabled")
    private Long aLERTENABLED;

    @Size(max = 200)
    @Column(name = "r_emark", length = 200)
    private String rEMARK;

    @Size(max = 200)
    @Column(name = "i_msi", length = 200)
    private String iMSI;

    @Size(max = 1)
    @Column(name = "p_artiallyregistered", length = 1)
    private String pARTIALLYREGISTERED;

    @Column(name = "p_artialdate")
    private Instant pARTIALDATE;

    @Column(name = "r_egisterdate")
    private Instant rEGISTERDATE;

    @Column(name = "a_pproved")
    private Double aPPROVED;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Column(name = "a_pproveddate")
    private Instant aPPROVEDDATE;

    @Column(name = "d_eclined")
    private Double dECLINED;

    @Size(max = 50)
    @Column(name = "d_eclinedby", length = 50)
    private String dECLINEDBY;

    @Column(name = "d_eclineddate")
    private Instant dECLINEDDATE;

    @Size(max = 200)
    @Column(name = "c_heckerremarks", length = 200)
    private String cHECKERREMARKS;

    @Size(max = 50)
    @Column(name = "p_ostaladdress", length = 50)
    private String pOSTALADDRESS;

    @Size(max = 50)
    @Column(name = "r_esidence", length = 50)
    private String rESIDENCE;

    @Column(name = "d_ob")
    private Instant dOB;

    @Size(max = 50)
    @Column(name = "c_reatedby", length = 50)
    private String cREATEDBY;

    @Size(max = 50)
    @Column(name = "e_mailaddress", length = 50)
    private String eMAILADDRESS;

    @Size(max = 50)
    @Column(name = "i_dentificationid", length = 50)
    private String iDENTIFICATIONID;

    @Column(name = "a_ddaccount")
    private Double aDDACCOUNT;

    @Size(max = 50)
    @Column(name = "a_clinkinginstitution", length = 50)
    private String aCLINKINGINSTITUTION;

    @Column(name = "d_eactivated")
    private Double dEACTIVATED;

    @Size(max = 50)
    @Column(name = "d_eactivatedby", length = 50)
    private String dEACTIVATEDBY;

    @Column(name = "d_eactivatedon")
    private Instant dEACTIVATEDON;

    @Column(name = "p_honenochanged")
    private Double pHONENOCHANGED;

    @Size(max = 50)
    @Column(name = "p_honenochangedby", length = 50)
    private String pHONENOCHANGEDBY;

    @Column(name = "p_honenochangedon")
    private Instant pHONENOCHANGEDON;

    @Size(max = 20)
    @Column(name = "o_riginalphoneno", length = 20)
    private String oRIGINALPHONENO;

    @Size(max = 20)
    @Column(name = "n_ewphoneno", length = 20)
    private String nEWPHONENO;

    @Column(name = "r_eset")
    private Double rESET;

    @Size(max = 50)
    @Column(name = "r_esetinginstitution", length = 50)
    private String rESETINGINSTITUTION;

    @Size(max = 50)
    @Column(name = "p_inresetremark", length = 50)
    private String pINRESETREMARK;

    @Size(max = 50)
    @Column(name = "r_esetby", length = 50)
    private String rESETBY;

    @Column(name = "r_eseton")
    private Instant rESETON;

    @Size(max = 50)
    @Column(name = "u_nblockinginstitution", length = 50)
    private String uNBLOCKINGINSTITUTION;

    @Column(name = "p_inblock")
    private Double pINBLOCK;

    @Size(max = 50)
    @Column(name = "p_inblockby", length = 50)
    private String pINBLOCKBY;

    @Size(max = 200)
    @Column(name = "p_inblockremarks", length = 200)
    private String pINBLOCKREMARKS;

    @Size(max = 50)
    @Column(name = "b_lockinginstitution", length = 50)
    private String bLOCKINGINSTITUTION;

    @Column(name = "p_inblockon")
    private Instant pINBLOCKON;

    @Column(name = "a_pprovedon")
    private Instant aPPROVEDON;

    @Size(max = 50)
    @Column(name = "p_inunblockby", length = 50)
    private String pINUNBLOCKBY;

    @Column(name = "l_oggedin")
    private Long lOGGEDIN;

    @Size(max = 50)
    @Column(name = "t_rials", length = 50)
    private String tRIALS;

    @Size(max = 20)
    @Column(name = "i_dtype", length = 20)
    private String iDTYPE;

    @Size(max = 20)
    @Column(name = "i_dnumber", length = 20)
    private String iDNUMBER;

    @Size(max = 1)
    @Column(name = "g_ender", length = 1)
    private String gENDER;

    @Size(max = 20)
    @Column(name = "c_if", length = 20)
    private String cIF;

    @Column(name = "d_ateofbirth")
    private Instant dATEOFBIRTH;

    @Size(max = 200)
    @Column(name = "r_emarks", length = 200)
    private String rEMARKS;

    @Column(name = "r_esetimsi")
    private Double rESETIMSI;

    @Size(max = 50)
    @Column(name = "i_msiresetby", length = 50)
    private String iMSIRESETBY;

    @Size(max = 200)
    @Column(name = "f_irstname", length = 200)
    private String fIRSTNAME;

    @Size(max = 200)
    @Column(name = "s_econdname", length = 200)
    private String sECONDNAME;

    @Size(max = 200)
    @Column(name = "l_astname", length = 200)
    private String lASTNAME;

    @Size(max = 7)
    @Column(name = "p_inblocktime", length = 7)
    private String pINBLOCKTIME;

    @Size(max = 50)
    @Column(name = "c_ustomerstatus", length = 50)
    private String cUSTOMERSTATUS;

    @Size(max = 2000)
    @Column(name = "u_sername", length = 2000)
    private String uSERNAME;

    @Size(max = 3900)
    @Column(name = "p_assword", length = 3900)
    private String pASSWORD;

    @Size(max = 50)
    @Column(name = "d_eviceid", length = 50)
    private String dEVICEID;

    @Size(max = 50)
    @Column(name = "c_hannel", length = 50)
    private String cHANNEL;

    @Column(name = "p_assreset")
    private Double pASSRESET;

    @Size(max = 50)
    @Column(name = "p_assresetby", length = 50)
    private String pASSRESETBY;

    @Column(name = "p_assreseton")
    private Instant pASSRESETON;

    @Column(name = "p_assblock")
    private Double pASSBLOCK;

    @Size(max = 50)
    @Column(name = "p_assblockby", length = 50)
    private String pASSBLOCKBY;

    @Column(name = "p_assblockon")
    private Instant pASSBLOCKON;

    @Column(name = "p_inmarkblock")
    private Double pINMARKBLOCK;

    @Column(name = "p_assmarkblock")
    private Double pASSMARKBLOCK;

    @Size(max = 50)
    @Column(name = "p_assresetremarks", length = 50)
    private String pASSRESETREMARKS;

    @Size(max = 50)
    @Column(name = "p_assblockremarks", length = 50)
    private String pASSBLOCKREMARKS;

    @Size(max = 50)
    @Column(name = "p_assunblockby", length = 50)
    private String pASSUNBLOCKBY;

    @Column(name = "p_asstrials")
    private Double pASSTRIALS;

    @Column(name = "a_ppactive")
    private Long aPPACTIVE;

    @Size(max = 32)
    @Column(name = "l_astlogin", length = 32)
    private String lASTLOGIN;

    @Column(name = "a_ppmarkeddisable")
    private Double aPPMARKEDDISABLE;

    @Size(max = 50)
    @Column(name = "d_isableby", length = 50)
    private String dISABLEBY;

    @Size(max = 50)
    @Column(name = "a_pprovedisableby", length = 50)
    private String aPPROVEDISABLEBY;

    @Column(name = "a_ppmarkedenable")
    private Double aPPMARKEDENABLE;

    @Size(max = 50)
    @Column(name = "e_nableby", length = 50)
    private String eNABLEBY;

    @Size(max = 50)
    @Column(name = "a_pprovedenableby", length = 50)
    private String aPPROVEDENABLEBY;

    @Column(name = "m_arkeddeactivate")
    private Double mARKEDDEACTIVATE;

    @Size(max = 5)
    @Column(name = "a_ppfirstlogin", length = 5)
    private String aPPFIRSTLOGIN;

    @Column(name = "a_tmtrials")
    private Double aTMTRIALS;

    @Size(max = 1000)
    @Column(name = "s_horcuts", length = 1000)
    private String sHORCUTS;

    @Size(max = 50)
    @Column(name = "m_arkedactivate", length = 50)
    private String mARKEDACTIVATE;

    @Size(max = 50)
    @Column(name = "t_own", length = 50)
    private String tOWN;

    @Column(name = "a_pproveddisableon")
    private Instant aPPROVEDDISABLEON;

    @Column(name = "d_isabledon")
    private Instant dISABLEDON;

    @Column(name = "r_esetapproveon")
    private Instant rESETAPPROVEON;

    @Size(max = 50)
    @Column(name = "d_eletedby", length = 50)
    private String dELETEDBY;

    @Size(max = 50)
    @Column(name = "q_uestionsasked", length = 50)
    private String qUESTIONSASKED;

    @Size(max = 50)
    @Column(name = "q_uestionstrials", length = 50)
    private String qUESTIONSTRIALS;

    @Size(max = 50)
    @Column(name = "q_uestionsanswered", length = 50)
    private String qUESTIONSANSWERED;

    @Column(name = "v_alidotp")
    private Double vALIDOTP;

    @Size(max = 50)
    @Column(name = "a_ctivatedby", length = 50)
    private String aCTIVATEDBY;

    @Column(name = "a_ctivatedon")
    private Instant aCTIVATEDON;

    @Size(max = 50)
    @Column(name = "b_ranchcode", length = 50)
    private String bRANCHCODE;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CUSTOMER id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcUSTOMERNAME() {
        return this.cUSTOMERNAME;
    }

    public CUSTOMER cUSTOMERNAME(String cUSTOMERNAME) {
        this.setcUSTOMERNAME(cUSTOMERNAME);
        return this;
    }

    public void setcUSTOMERNAME(String cUSTOMERNAME) {
        this.cUSTOMERNAME = cUSTOMERNAME;
    }

    public String getpHONENUMBER() {
        return this.pHONENUMBER;
    }

    public CUSTOMER pHONENUMBER(String pHONENUMBER) {
        this.setpHONENUMBER(pHONENUMBER);
        return this;
    }

    public void setpHONENUMBER(String pHONENUMBER) {
        this.pHONENUMBER = pHONENUMBER;
    }

    public String getcARDNUMBER() {
        return this.cARDNUMBER;
    }

    public CUSTOMER cARDNUMBER(String cARDNUMBER) {
        this.setcARDNUMBER(cARDNUMBER);
        return this;
    }

    public void setcARDNUMBER(String cARDNUMBER) {
        this.cARDNUMBER = cARDNUMBER;
    }

    public String getaCCOUNTNUMBER() {
        return this.aCCOUNTNUMBER;
    }

    public CUSTOMER aCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.setaCCOUNTNUMBER(aCCOUNTNUMBER);
        return this;
    }

    public void setaCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.aCCOUNTNUMBER = aCCOUNTNUMBER;
    }

    public String getlANG() {
        return this.lANG;
    }

    public CUSTOMER lANG(String lANG) {
        this.setlANG(lANG);
        return this;
    }

    public void setlANG(String lANG) {
        this.lANG = lANG;
    }

    public String getpIN() {
        return this.pIN;
    }

    public CUSTOMER pIN(String pIN) {
        this.setpIN(pIN);
        return this;
    }

    public void setpIN(String pIN) {
        this.pIN = pIN;
    }

    public String getfIRSTLOGIN() {
        return this.fIRSTLOGIN;
    }

    public CUSTOMER fIRSTLOGIN(String fIRSTLOGIN) {
        this.setfIRSTLOGIN(fIRSTLOGIN);
        return this;
    }

    public void setfIRSTLOGIN(String fIRSTLOGIN) {
        this.fIRSTLOGIN = fIRSTLOGIN;
    }

    public String getaCTIVE() {
        return this.aCTIVE;
    }

    public CUSTOMER aCTIVE(String aCTIVE) {
        this.setaCTIVE(aCTIVE);
        return this;
    }

    public void setaCTIVE(String aCTIVE) {
        this.aCTIVE = aCTIVE;
    }

    public Long getrEGISTERED() {
        return this.rEGISTERED;
    }

    public CUSTOMER rEGISTERED(Long rEGISTERED) {
        this.setrEGISTERED(rEGISTERED);
        return this;
    }

    public void setrEGISTERED(Long rEGISTERED) {
        this.rEGISTERED = rEGISTERED;
    }

    public Long getcSTDELETE() {
        return this.cSTDELETE;
    }

    public CUSTOMER cSTDELETE(Long cSTDELETE) {
        this.setcSTDELETE(cSTDELETE);
        return this;
    }

    public void setcSTDELETE(Long cSTDELETE) {
        this.cSTDELETE = cSTDELETE;
    }

    public Instant getrEGDATE() {
        return this.rEGDATE;
    }

    public CUSTOMER rEGDATE(Instant rEGDATE) {
        this.setrEGDATE(rEGDATE);
        return this;
    }

    public void setrEGDATE(Instant rEGDATE) {
        this.rEGDATE = rEGDATE;
    }

    public Long getaLERTENABLED() {
        return this.aLERTENABLED;
    }

    public CUSTOMER aLERTENABLED(Long aLERTENABLED) {
        this.setaLERTENABLED(aLERTENABLED);
        return this;
    }

    public void setaLERTENABLED(Long aLERTENABLED) {
        this.aLERTENABLED = aLERTENABLED;
    }

    public String getrEMARK() {
        return this.rEMARK;
    }

    public CUSTOMER rEMARK(String rEMARK) {
        this.setrEMARK(rEMARK);
        return this;
    }

    public void setrEMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

    public String getiMSI() {
        return this.iMSI;
    }

    public CUSTOMER iMSI(String iMSI) {
        this.setiMSI(iMSI);
        return this;
    }

    public void setiMSI(String iMSI) {
        this.iMSI = iMSI;
    }

    public String getpARTIALLYREGISTERED() {
        return this.pARTIALLYREGISTERED;
    }

    public CUSTOMER pARTIALLYREGISTERED(String pARTIALLYREGISTERED) {
        this.setpARTIALLYREGISTERED(pARTIALLYREGISTERED);
        return this;
    }

    public void setpARTIALLYREGISTERED(String pARTIALLYREGISTERED) {
        this.pARTIALLYREGISTERED = pARTIALLYREGISTERED;
    }

    public Instant getpARTIALDATE() {
        return this.pARTIALDATE;
    }

    public CUSTOMER pARTIALDATE(Instant pARTIALDATE) {
        this.setpARTIALDATE(pARTIALDATE);
        return this;
    }

    public void setpARTIALDATE(Instant pARTIALDATE) {
        this.pARTIALDATE = pARTIALDATE;
    }

    public Instant getrEGISTERDATE() {
        return this.rEGISTERDATE;
    }

    public CUSTOMER rEGISTERDATE(Instant rEGISTERDATE) {
        this.setrEGISTERDATE(rEGISTERDATE);
        return this;
    }

    public void setrEGISTERDATE(Instant rEGISTERDATE) {
        this.rEGISTERDATE = rEGISTERDATE;
    }

    public Double getaPPROVED() {
        return this.aPPROVED;
    }

    public CUSTOMER aPPROVED(Double aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Double aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public CUSTOMER aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public Instant getaPPROVEDDATE() {
        return this.aPPROVEDDATE;
    }

    public CUSTOMER aPPROVEDDATE(Instant aPPROVEDDATE) {
        this.setaPPROVEDDATE(aPPROVEDDATE);
        return this;
    }

    public void setaPPROVEDDATE(Instant aPPROVEDDATE) {
        this.aPPROVEDDATE = aPPROVEDDATE;
    }

    public Double getdECLINED() {
        return this.dECLINED;
    }

    public CUSTOMER dECLINED(Double dECLINED) {
        this.setdECLINED(dECLINED);
        return this;
    }

    public void setdECLINED(Double dECLINED) {
        this.dECLINED = dECLINED;
    }

    public String getdECLINEDBY() {
        return this.dECLINEDBY;
    }

    public CUSTOMER dECLINEDBY(String dECLINEDBY) {
        this.setdECLINEDBY(dECLINEDBY);
        return this;
    }

    public void setdECLINEDBY(String dECLINEDBY) {
        this.dECLINEDBY = dECLINEDBY;
    }

    public Instant getdECLINEDDATE() {
        return this.dECLINEDDATE;
    }

    public CUSTOMER dECLINEDDATE(Instant dECLINEDDATE) {
        this.setdECLINEDDATE(dECLINEDDATE);
        return this;
    }

    public void setdECLINEDDATE(Instant dECLINEDDATE) {
        this.dECLINEDDATE = dECLINEDDATE;
    }

    public String getcHECKERREMARKS() {
        return this.cHECKERREMARKS;
    }

    public CUSTOMER cHECKERREMARKS(String cHECKERREMARKS) {
        this.setcHECKERREMARKS(cHECKERREMARKS);
        return this;
    }

    public void setcHECKERREMARKS(String cHECKERREMARKS) {
        this.cHECKERREMARKS = cHECKERREMARKS;
    }

    public String getpOSTALADDRESS() {
        return this.pOSTALADDRESS;
    }

    public CUSTOMER pOSTALADDRESS(String pOSTALADDRESS) {
        this.setpOSTALADDRESS(pOSTALADDRESS);
        return this;
    }

    public void setpOSTALADDRESS(String pOSTALADDRESS) {
        this.pOSTALADDRESS = pOSTALADDRESS;
    }

    public String getrESIDENCE() {
        return this.rESIDENCE;
    }

    public CUSTOMER rESIDENCE(String rESIDENCE) {
        this.setrESIDENCE(rESIDENCE);
        return this;
    }

    public void setrESIDENCE(String rESIDENCE) {
        this.rESIDENCE = rESIDENCE;
    }

    public Instant getdOB() {
        return this.dOB;
    }

    public CUSTOMER dOB(Instant dOB) {
        this.setdOB(dOB);
        return this;
    }

    public void setdOB(Instant dOB) {
        this.dOB = dOB;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public CUSTOMER cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String geteMAILADDRESS() {
        return this.eMAILADDRESS;
    }

    public CUSTOMER eMAILADDRESS(String eMAILADDRESS) {
        this.seteMAILADDRESS(eMAILADDRESS);
        return this;
    }

    public void seteMAILADDRESS(String eMAILADDRESS) {
        this.eMAILADDRESS = eMAILADDRESS;
    }

    public String getiDENTIFICATIONID() {
        return this.iDENTIFICATIONID;
    }

    public CUSTOMER iDENTIFICATIONID(String iDENTIFICATIONID) {
        this.setiDENTIFICATIONID(iDENTIFICATIONID);
        return this;
    }

    public void setiDENTIFICATIONID(String iDENTIFICATIONID) {
        this.iDENTIFICATIONID = iDENTIFICATIONID;
    }

    public Double getaDDACCOUNT() {
        return this.aDDACCOUNT;
    }

    public CUSTOMER aDDACCOUNT(Double aDDACCOUNT) {
        this.setaDDACCOUNT(aDDACCOUNT);
        return this;
    }

    public void setaDDACCOUNT(Double aDDACCOUNT) {
        this.aDDACCOUNT = aDDACCOUNT;
    }

    public String getaCLINKINGINSTITUTION() {
        return this.aCLINKINGINSTITUTION;
    }

    public CUSTOMER aCLINKINGINSTITUTION(String aCLINKINGINSTITUTION) {
        this.setaCLINKINGINSTITUTION(aCLINKINGINSTITUTION);
        return this;
    }

    public void setaCLINKINGINSTITUTION(String aCLINKINGINSTITUTION) {
        this.aCLINKINGINSTITUTION = aCLINKINGINSTITUTION;
    }

    public Double getdEACTIVATED() {
        return this.dEACTIVATED;
    }

    public CUSTOMER dEACTIVATED(Double dEACTIVATED) {
        this.setdEACTIVATED(dEACTIVATED);
        return this;
    }

    public void setdEACTIVATED(Double dEACTIVATED) {
        this.dEACTIVATED = dEACTIVATED;
    }

    public String getdEACTIVATEDBY() {
        return this.dEACTIVATEDBY;
    }

    public CUSTOMER dEACTIVATEDBY(String dEACTIVATEDBY) {
        this.setdEACTIVATEDBY(dEACTIVATEDBY);
        return this;
    }

    public void setdEACTIVATEDBY(String dEACTIVATEDBY) {
        this.dEACTIVATEDBY = dEACTIVATEDBY;
    }

    public Instant getdEACTIVATEDON() {
        return this.dEACTIVATEDON;
    }

    public CUSTOMER dEACTIVATEDON(Instant dEACTIVATEDON) {
        this.setdEACTIVATEDON(dEACTIVATEDON);
        return this;
    }

    public void setdEACTIVATEDON(Instant dEACTIVATEDON) {
        this.dEACTIVATEDON = dEACTIVATEDON;
    }

    public Double getpHONENOCHANGED() {
        return this.pHONENOCHANGED;
    }

    public CUSTOMER pHONENOCHANGED(Double pHONENOCHANGED) {
        this.setpHONENOCHANGED(pHONENOCHANGED);
        return this;
    }

    public void setpHONENOCHANGED(Double pHONENOCHANGED) {
        this.pHONENOCHANGED = pHONENOCHANGED;
    }

    public String getpHONENOCHANGEDBY() {
        return this.pHONENOCHANGEDBY;
    }

    public CUSTOMER pHONENOCHANGEDBY(String pHONENOCHANGEDBY) {
        this.setpHONENOCHANGEDBY(pHONENOCHANGEDBY);
        return this;
    }

    public void setpHONENOCHANGEDBY(String pHONENOCHANGEDBY) {
        this.pHONENOCHANGEDBY = pHONENOCHANGEDBY;
    }

    public Instant getpHONENOCHANGEDON() {
        return this.pHONENOCHANGEDON;
    }

    public CUSTOMER pHONENOCHANGEDON(Instant pHONENOCHANGEDON) {
        this.setpHONENOCHANGEDON(pHONENOCHANGEDON);
        return this;
    }

    public void setpHONENOCHANGEDON(Instant pHONENOCHANGEDON) {
        this.pHONENOCHANGEDON = pHONENOCHANGEDON;
    }

    public String getoRIGINALPHONENO() {
        return this.oRIGINALPHONENO;
    }

    public CUSTOMER oRIGINALPHONENO(String oRIGINALPHONENO) {
        this.setoRIGINALPHONENO(oRIGINALPHONENO);
        return this;
    }

    public void setoRIGINALPHONENO(String oRIGINALPHONENO) {
        this.oRIGINALPHONENO = oRIGINALPHONENO;
    }

    public String getnEWPHONENO() {
        return this.nEWPHONENO;
    }

    public CUSTOMER nEWPHONENO(String nEWPHONENO) {
        this.setnEWPHONENO(nEWPHONENO);
        return this;
    }

    public void setnEWPHONENO(String nEWPHONENO) {
        this.nEWPHONENO = nEWPHONENO;
    }

    public Double getrESET() {
        return this.rESET;
    }

    public CUSTOMER rESET(Double rESET) {
        this.setrESET(rESET);
        return this;
    }

    public void setrESET(Double rESET) {
        this.rESET = rESET;
    }

    public String getrESETINGINSTITUTION() {
        return this.rESETINGINSTITUTION;
    }

    public CUSTOMER rESETINGINSTITUTION(String rESETINGINSTITUTION) {
        this.setrESETINGINSTITUTION(rESETINGINSTITUTION);
        return this;
    }

    public void setrESETINGINSTITUTION(String rESETINGINSTITUTION) {
        this.rESETINGINSTITUTION = rESETINGINSTITUTION;
    }

    public String getpINRESETREMARK() {
        return this.pINRESETREMARK;
    }

    public CUSTOMER pINRESETREMARK(String pINRESETREMARK) {
        this.setpINRESETREMARK(pINRESETREMARK);
        return this;
    }

    public void setpINRESETREMARK(String pINRESETREMARK) {
        this.pINRESETREMARK = pINRESETREMARK;
    }

    public String getrESETBY() {
        return this.rESETBY;
    }

    public CUSTOMER rESETBY(String rESETBY) {
        this.setrESETBY(rESETBY);
        return this;
    }

    public void setrESETBY(String rESETBY) {
        this.rESETBY = rESETBY;
    }

    public Instant getrESETON() {
        return this.rESETON;
    }

    public CUSTOMER rESETON(Instant rESETON) {
        this.setrESETON(rESETON);
        return this;
    }

    public void setrESETON(Instant rESETON) {
        this.rESETON = rESETON;
    }

    public String getuNBLOCKINGINSTITUTION() {
        return this.uNBLOCKINGINSTITUTION;
    }

    public CUSTOMER uNBLOCKINGINSTITUTION(String uNBLOCKINGINSTITUTION) {
        this.setuNBLOCKINGINSTITUTION(uNBLOCKINGINSTITUTION);
        return this;
    }

    public void setuNBLOCKINGINSTITUTION(String uNBLOCKINGINSTITUTION) {
        this.uNBLOCKINGINSTITUTION = uNBLOCKINGINSTITUTION;
    }

    public Double getpINBLOCK() {
        return this.pINBLOCK;
    }

    public CUSTOMER pINBLOCK(Double pINBLOCK) {
        this.setpINBLOCK(pINBLOCK);
        return this;
    }

    public void setpINBLOCK(Double pINBLOCK) {
        this.pINBLOCK = pINBLOCK;
    }

    public String getpINBLOCKBY() {
        return this.pINBLOCKBY;
    }

    public CUSTOMER pINBLOCKBY(String pINBLOCKBY) {
        this.setpINBLOCKBY(pINBLOCKBY);
        return this;
    }

    public void setpINBLOCKBY(String pINBLOCKBY) {
        this.pINBLOCKBY = pINBLOCKBY;
    }

    public String getpINBLOCKREMARKS() {
        return this.pINBLOCKREMARKS;
    }

    public CUSTOMER pINBLOCKREMARKS(String pINBLOCKREMARKS) {
        this.setpINBLOCKREMARKS(pINBLOCKREMARKS);
        return this;
    }

    public void setpINBLOCKREMARKS(String pINBLOCKREMARKS) {
        this.pINBLOCKREMARKS = pINBLOCKREMARKS;
    }

    public String getbLOCKINGINSTITUTION() {
        return this.bLOCKINGINSTITUTION;
    }

    public CUSTOMER bLOCKINGINSTITUTION(String bLOCKINGINSTITUTION) {
        this.setbLOCKINGINSTITUTION(bLOCKINGINSTITUTION);
        return this;
    }

    public void setbLOCKINGINSTITUTION(String bLOCKINGINSTITUTION) {
        this.bLOCKINGINSTITUTION = bLOCKINGINSTITUTION;
    }

    public Instant getpINBLOCKON() {
        return this.pINBLOCKON;
    }

    public CUSTOMER pINBLOCKON(Instant pINBLOCKON) {
        this.setpINBLOCKON(pINBLOCKON);
        return this;
    }

    public void setpINBLOCKON(Instant pINBLOCKON) {
        this.pINBLOCKON = pINBLOCKON;
    }

    public Instant getaPPROVEDON() {
        return this.aPPROVEDON;
    }

    public CUSTOMER aPPROVEDON(Instant aPPROVEDON) {
        this.setaPPROVEDON(aPPROVEDON);
        return this;
    }

    public void setaPPROVEDON(Instant aPPROVEDON) {
        this.aPPROVEDON = aPPROVEDON;
    }

    public String getpINUNBLOCKBY() {
        return this.pINUNBLOCKBY;
    }

    public CUSTOMER pINUNBLOCKBY(String pINUNBLOCKBY) {
        this.setpINUNBLOCKBY(pINUNBLOCKBY);
        return this;
    }

    public void setpINUNBLOCKBY(String pINUNBLOCKBY) {
        this.pINUNBLOCKBY = pINUNBLOCKBY;
    }

    public Long getlOGGEDIN() {
        return this.lOGGEDIN;
    }

    public CUSTOMER lOGGEDIN(Long lOGGEDIN) {
        this.setlOGGEDIN(lOGGEDIN);
        return this;
    }

    public void setlOGGEDIN(Long lOGGEDIN) {
        this.lOGGEDIN = lOGGEDIN;
    }

    public String gettRIALS() {
        return this.tRIALS;
    }

    public CUSTOMER tRIALS(String tRIALS) {
        this.settRIALS(tRIALS);
        return this;
    }

    public void settRIALS(String tRIALS) {
        this.tRIALS = tRIALS;
    }

    public String getiDTYPE() {
        return this.iDTYPE;
    }

    public CUSTOMER iDTYPE(String iDTYPE) {
        this.setiDTYPE(iDTYPE);
        return this;
    }

    public void setiDTYPE(String iDTYPE) {
        this.iDTYPE = iDTYPE;
    }

    public String getiDNUMBER() {
        return this.iDNUMBER;
    }

    public CUSTOMER iDNUMBER(String iDNUMBER) {
        this.setiDNUMBER(iDNUMBER);
        return this;
    }

    public void setiDNUMBER(String iDNUMBER) {
        this.iDNUMBER = iDNUMBER;
    }

    public String getgENDER() {
        return this.gENDER;
    }

    public CUSTOMER gENDER(String gENDER) {
        this.setgENDER(gENDER);
        return this;
    }

    public void setgENDER(String gENDER) {
        this.gENDER = gENDER;
    }

    public String getcIF() {
        return this.cIF;
    }

    public CUSTOMER cIF(String cIF) {
        this.setcIF(cIF);
        return this;
    }

    public void setcIF(String cIF) {
        this.cIF = cIF;
    }

    public Instant getdATEOFBIRTH() {
        return this.dATEOFBIRTH;
    }

    public CUSTOMER dATEOFBIRTH(Instant dATEOFBIRTH) {
        this.setdATEOFBIRTH(dATEOFBIRTH);
        return this;
    }

    public void setdATEOFBIRTH(Instant dATEOFBIRTH) {
        this.dATEOFBIRTH = dATEOFBIRTH;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public CUSTOMER rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public Double getrESETIMSI() {
        return this.rESETIMSI;
    }

    public CUSTOMER rESETIMSI(Double rESETIMSI) {
        this.setrESETIMSI(rESETIMSI);
        return this;
    }

    public void setrESETIMSI(Double rESETIMSI) {
        this.rESETIMSI = rESETIMSI;
    }

    public String getiMSIRESETBY() {
        return this.iMSIRESETBY;
    }

    public CUSTOMER iMSIRESETBY(String iMSIRESETBY) {
        this.setiMSIRESETBY(iMSIRESETBY);
        return this;
    }

    public void setiMSIRESETBY(String iMSIRESETBY) {
        this.iMSIRESETBY = iMSIRESETBY;
    }

    public String getfIRSTNAME() {
        return this.fIRSTNAME;
    }

    public CUSTOMER fIRSTNAME(String fIRSTNAME) {
        this.setfIRSTNAME(fIRSTNAME);
        return this;
    }

    public void setfIRSTNAME(String fIRSTNAME) {
        this.fIRSTNAME = fIRSTNAME;
    }

    public String getsECONDNAME() {
        return this.sECONDNAME;
    }

    public CUSTOMER sECONDNAME(String sECONDNAME) {
        this.setsECONDNAME(sECONDNAME);
        return this;
    }

    public void setsECONDNAME(String sECONDNAME) {
        this.sECONDNAME = sECONDNAME;
    }

    public String getlASTNAME() {
        return this.lASTNAME;
    }

    public CUSTOMER lASTNAME(String lASTNAME) {
        this.setlASTNAME(lASTNAME);
        return this;
    }

    public void setlASTNAME(String lASTNAME) {
        this.lASTNAME = lASTNAME;
    }

    public String getpINBLOCKTIME() {
        return this.pINBLOCKTIME;
    }

    public CUSTOMER pINBLOCKTIME(String pINBLOCKTIME) {
        this.setpINBLOCKTIME(pINBLOCKTIME);
        return this;
    }

    public void setpINBLOCKTIME(String pINBLOCKTIME) {
        this.pINBLOCKTIME = pINBLOCKTIME;
    }

    public String getcUSTOMERSTATUS() {
        return this.cUSTOMERSTATUS;
    }

    public CUSTOMER cUSTOMERSTATUS(String cUSTOMERSTATUS) {
        this.setcUSTOMERSTATUS(cUSTOMERSTATUS);
        return this;
    }

    public void setcUSTOMERSTATUS(String cUSTOMERSTATUS) {
        this.cUSTOMERSTATUS = cUSTOMERSTATUS;
    }

    public String getuSERNAME() {
        return this.uSERNAME;
    }

    public CUSTOMER uSERNAME(String uSERNAME) {
        this.setuSERNAME(uSERNAME);
        return this;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getpASSWORD() {
        return this.pASSWORD;
    }

    public CUSTOMER pASSWORD(String pASSWORD) {
        this.setpASSWORD(pASSWORD);
        return this;
    }

    public void setpASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    public String getdEVICEID() {
        return this.dEVICEID;
    }

    public CUSTOMER dEVICEID(String dEVICEID) {
        this.setdEVICEID(dEVICEID);
        return this;
    }

    public void setdEVICEID(String dEVICEID) {
        this.dEVICEID = dEVICEID;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public CUSTOMER cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public Double getpASSRESET() {
        return this.pASSRESET;
    }

    public CUSTOMER pASSRESET(Double pASSRESET) {
        this.setpASSRESET(pASSRESET);
        return this;
    }

    public void setpASSRESET(Double pASSRESET) {
        this.pASSRESET = pASSRESET;
    }

    public String getpASSRESETBY() {
        return this.pASSRESETBY;
    }

    public CUSTOMER pASSRESETBY(String pASSRESETBY) {
        this.setpASSRESETBY(pASSRESETBY);
        return this;
    }

    public void setpASSRESETBY(String pASSRESETBY) {
        this.pASSRESETBY = pASSRESETBY;
    }

    public Instant getpASSRESETON() {
        return this.pASSRESETON;
    }

    public CUSTOMER pASSRESETON(Instant pASSRESETON) {
        this.setpASSRESETON(pASSRESETON);
        return this;
    }

    public void setpASSRESETON(Instant pASSRESETON) {
        this.pASSRESETON = pASSRESETON;
    }

    public Double getpASSBLOCK() {
        return this.pASSBLOCK;
    }

    public CUSTOMER pASSBLOCK(Double pASSBLOCK) {
        this.setpASSBLOCK(pASSBLOCK);
        return this;
    }

    public void setpASSBLOCK(Double pASSBLOCK) {
        this.pASSBLOCK = pASSBLOCK;
    }

    public String getpASSBLOCKBY() {
        return this.pASSBLOCKBY;
    }

    public CUSTOMER pASSBLOCKBY(String pASSBLOCKBY) {
        this.setpASSBLOCKBY(pASSBLOCKBY);
        return this;
    }

    public void setpASSBLOCKBY(String pASSBLOCKBY) {
        this.pASSBLOCKBY = pASSBLOCKBY;
    }

    public Instant getpASSBLOCKON() {
        return this.pASSBLOCKON;
    }

    public CUSTOMER pASSBLOCKON(Instant pASSBLOCKON) {
        this.setpASSBLOCKON(pASSBLOCKON);
        return this;
    }

    public void setpASSBLOCKON(Instant pASSBLOCKON) {
        this.pASSBLOCKON = pASSBLOCKON;
    }

    public Double getpINMARKBLOCK() {
        return this.pINMARKBLOCK;
    }

    public CUSTOMER pINMARKBLOCK(Double pINMARKBLOCK) {
        this.setpINMARKBLOCK(pINMARKBLOCK);
        return this;
    }

    public void setpINMARKBLOCK(Double pINMARKBLOCK) {
        this.pINMARKBLOCK = pINMARKBLOCK;
    }

    public Double getpASSMARKBLOCK() {
        return this.pASSMARKBLOCK;
    }

    public CUSTOMER pASSMARKBLOCK(Double pASSMARKBLOCK) {
        this.setpASSMARKBLOCK(pASSMARKBLOCK);
        return this;
    }

    public void setpASSMARKBLOCK(Double pASSMARKBLOCK) {
        this.pASSMARKBLOCK = pASSMARKBLOCK;
    }

    public String getpASSRESETREMARKS() {
        return this.pASSRESETREMARKS;
    }

    public CUSTOMER pASSRESETREMARKS(String pASSRESETREMARKS) {
        this.setpASSRESETREMARKS(pASSRESETREMARKS);
        return this;
    }

    public void setpASSRESETREMARKS(String pASSRESETREMARKS) {
        this.pASSRESETREMARKS = pASSRESETREMARKS;
    }

    public String getpASSBLOCKREMARKS() {
        return this.pASSBLOCKREMARKS;
    }

    public CUSTOMER pASSBLOCKREMARKS(String pASSBLOCKREMARKS) {
        this.setpASSBLOCKREMARKS(pASSBLOCKREMARKS);
        return this;
    }

    public void setpASSBLOCKREMARKS(String pASSBLOCKREMARKS) {
        this.pASSBLOCKREMARKS = pASSBLOCKREMARKS;
    }

    public String getpASSUNBLOCKBY() {
        return this.pASSUNBLOCKBY;
    }

    public CUSTOMER pASSUNBLOCKBY(String pASSUNBLOCKBY) {
        this.setpASSUNBLOCKBY(pASSUNBLOCKBY);
        return this;
    }

    public void setpASSUNBLOCKBY(String pASSUNBLOCKBY) {
        this.pASSUNBLOCKBY = pASSUNBLOCKBY;
    }

    public Double getpASSTRIALS() {
        return this.pASSTRIALS;
    }

    public CUSTOMER pASSTRIALS(Double pASSTRIALS) {
        this.setpASSTRIALS(pASSTRIALS);
        return this;
    }

    public void setpASSTRIALS(Double pASSTRIALS) {
        this.pASSTRIALS = pASSTRIALS;
    }

    public Long getaPPACTIVE() {
        return this.aPPACTIVE;
    }

    public CUSTOMER aPPACTIVE(Long aPPACTIVE) {
        this.setaPPACTIVE(aPPACTIVE);
        return this;
    }

    public void setaPPACTIVE(Long aPPACTIVE) {
        this.aPPACTIVE = aPPACTIVE;
    }

    public String getlASTLOGIN() {
        return this.lASTLOGIN;
    }

    public CUSTOMER lASTLOGIN(String lASTLOGIN) {
        this.setlASTLOGIN(lASTLOGIN);
        return this;
    }

    public void setlASTLOGIN(String lASTLOGIN) {
        this.lASTLOGIN = lASTLOGIN;
    }

    public Double getaPPMARKEDDISABLE() {
        return this.aPPMARKEDDISABLE;
    }

    public CUSTOMER aPPMARKEDDISABLE(Double aPPMARKEDDISABLE) {
        this.setaPPMARKEDDISABLE(aPPMARKEDDISABLE);
        return this;
    }

    public void setaPPMARKEDDISABLE(Double aPPMARKEDDISABLE) {
        this.aPPMARKEDDISABLE = aPPMARKEDDISABLE;
    }

    public String getdISABLEBY() {
        return this.dISABLEBY;
    }

    public CUSTOMER dISABLEBY(String dISABLEBY) {
        this.setdISABLEBY(dISABLEBY);
        return this;
    }

    public void setdISABLEBY(String dISABLEBY) {
        this.dISABLEBY = dISABLEBY;
    }

    public String getaPPROVEDISABLEBY() {
        return this.aPPROVEDISABLEBY;
    }

    public CUSTOMER aPPROVEDISABLEBY(String aPPROVEDISABLEBY) {
        this.setaPPROVEDISABLEBY(aPPROVEDISABLEBY);
        return this;
    }

    public void setaPPROVEDISABLEBY(String aPPROVEDISABLEBY) {
        this.aPPROVEDISABLEBY = aPPROVEDISABLEBY;
    }

    public Double getaPPMARKEDENABLE() {
        return this.aPPMARKEDENABLE;
    }

    public CUSTOMER aPPMARKEDENABLE(Double aPPMARKEDENABLE) {
        this.setaPPMARKEDENABLE(aPPMARKEDENABLE);
        return this;
    }

    public void setaPPMARKEDENABLE(Double aPPMARKEDENABLE) {
        this.aPPMARKEDENABLE = aPPMARKEDENABLE;
    }

    public String geteNABLEBY() {
        return this.eNABLEBY;
    }

    public CUSTOMER eNABLEBY(String eNABLEBY) {
        this.seteNABLEBY(eNABLEBY);
        return this;
    }

    public void seteNABLEBY(String eNABLEBY) {
        this.eNABLEBY = eNABLEBY;
    }

    public String getaPPROVEDENABLEBY() {
        return this.aPPROVEDENABLEBY;
    }

    public CUSTOMER aPPROVEDENABLEBY(String aPPROVEDENABLEBY) {
        this.setaPPROVEDENABLEBY(aPPROVEDENABLEBY);
        return this;
    }

    public void setaPPROVEDENABLEBY(String aPPROVEDENABLEBY) {
        this.aPPROVEDENABLEBY = aPPROVEDENABLEBY;
    }

    public Double getmARKEDDEACTIVATE() {
        return this.mARKEDDEACTIVATE;
    }

    public CUSTOMER mARKEDDEACTIVATE(Double mARKEDDEACTIVATE) {
        this.setmARKEDDEACTIVATE(mARKEDDEACTIVATE);
        return this;
    }

    public void setmARKEDDEACTIVATE(Double mARKEDDEACTIVATE) {
        this.mARKEDDEACTIVATE = mARKEDDEACTIVATE;
    }

    public String getaPPFIRSTLOGIN() {
        return this.aPPFIRSTLOGIN;
    }

    public CUSTOMER aPPFIRSTLOGIN(String aPPFIRSTLOGIN) {
        this.setaPPFIRSTLOGIN(aPPFIRSTLOGIN);
        return this;
    }

    public void setaPPFIRSTLOGIN(String aPPFIRSTLOGIN) {
        this.aPPFIRSTLOGIN = aPPFIRSTLOGIN;
    }

    public Double getaTMTRIALS() {
        return this.aTMTRIALS;
    }

    public CUSTOMER aTMTRIALS(Double aTMTRIALS) {
        this.setaTMTRIALS(aTMTRIALS);
        return this;
    }

    public void setaTMTRIALS(Double aTMTRIALS) {
        this.aTMTRIALS = aTMTRIALS;
    }

    public String getsHORCUTS() {
        return this.sHORCUTS;
    }

    public CUSTOMER sHORCUTS(String sHORCUTS) {
        this.setsHORCUTS(sHORCUTS);
        return this;
    }

    public void setsHORCUTS(String sHORCUTS) {
        this.sHORCUTS = sHORCUTS;
    }

    public String getmARKEDACTIVATE() {
        return this.mARKEDACTIVATE;
    }

    public CUSTOMER mARKEDACTIVATE(String mARKEDACTIVATE) {
        this.setmARKEDACTIVATE(mARKEDACTIVATE);
        return this;
    }

    public void setmARKEDACTIVATE(String mARKEDACTIVATE) {
        this.mARKEDACTIVATE = mARKEDACTIVATE;
    }

    public String gettOWN() {
        return this.tOWN;
    }

    public CUSTOMER tOWN(String tOWN) {
        this.settOWN(tOWN);
        return this;
    }

    public void settOWN(String tOWN) {
        this.tOWN = tOWN;
    }

    public Instant getaPPROVEDDISABLEON() {
        return this.aPPROVEDDISABLEON;
    }

    public CUSTOMER aPPROVEDDISABLEON(Instant aPPROVEDDISABLEON) {
        this.setaPPROVEDDISABLEON(aPPROVEDDISABLEON);
        return this;
    }

    public void setaPPROVEDDISABLEON(Instant aPPROVEDDISABLEON) {
        this.aPPROVEDDISABLEON = aPPROVEDDISABLEON;
    }

    public Instant getdISABLEDON() {
        return this.dISABLEDON;
    }

    public CUSTOMER dISABLEDON(Instant dISABLEDON) {
        this.setdISABLEDON(dISABLEDON);
        return this;
    }

    public void setdISABLEDON(Instant dISABLEDON) {
        this.dISABLEDON = dISABLEDON;
    }

    public Instant getrESETAPPROVEON() {
        return this.rESETAPPROVEON;
    }

    public CUSTOMER rESETAPPROVEON(Instant rESETAPPROVEON) {
        this.setrESETAPPROVEON(rESETAPPROVEON);
        return this;
    }

    public void setrESETAPPROVEON(Instant rESETAPPROVEON) {
        this.rESETAPPROVEON = rESETAPPROVEON;
    }

    public String getdELETEDBY() {
        return this.dELETEDBY;
    }

    public CUSTOMER dELETEDBY(String dELETEDBY) {
        this.setdELETEDBY(dELETEDBY);
        return this;
    }

    public void setdELETEDBY(String dELETEDBY) {
        this.dELETEDBY = dELETEDBY;
    }

    public String getqUESTIONSASKED() {
        return this.qUESTIONSASKED;
    }

    public CUSTOMER qUESTIONSASKED(String qUESTIONSASKED) {
        this.setqUESTIONSASKED(qUESTIONSASKED);
        return this;
    }

    public void setqUESTIONSASKED(String qUESTIONSASKED) {
        this.qUESTIONSASKED = qUESTIONSASKED;
    }

    public String getqUESTIONSTRIALS() {
        return this.qUESTIONSTRIALS;
    }

    public CUSTOMER qUESTIONSTRIALS(String qUESTIONSTRIALS) {
        this.setqUESTIONSTRIALS(qUESTIONSTRIALS);
        return this;
    }

    public void setqUESTIONSTRIALS(String qUESTIONSTRIALS) {
        this.qUESTIONSTRIALS = qUESTIONSTRIALS;
    }

    public String getqUESTIONSANSWERED() {
        return this.qUESTIONSANSWERED;
    }

    public CUSTOMER qUESTIONSANSWERED(String qUESTIONSANSWERED) {
        this.setqUESTIONSANSWERED(qUESTIONSANSWERED);
        return this;
    }

    public void setqUESTIONSANSWERED(String qUESTIONSANSWERED) {
        this.qUESTIONSANSWERED = qUESTIONSANSWERED;
    }

    public Double getvALIDOTP() {
        return this.vALIDOTP;
    }

    public CUSTOMER vALIDOTP(Double vALIDOTP) {
        this.setvALIDOTP(vALIDOTP);
        return this;
    }

    public void setvALIDOTP(Double vALIDOTP) {
        this.vALIDOTP = vALIDOTP;
    }

    public String getaCTIVATEDBY() {
        return this.aCTIVATEDBY;
    }

    public CUSTOMER aCTIVATEDBY(String aCTIVATEDBY) {
        this.setaCTIVATEDBY(aCTIVATEDBY);
        return this;
    }

    public void setaCTIVATEDBY(String aCTIVATEDBY) {
        this.aCTIVATEDBY = aCTIVATEDBY;
    }

    public Instant getaCTIVATEDON() {
        return this.aCTIVATEDON;
    }

    public CUSTOMER aCTIVATEDON(Instant aCTIVATEDON) {
        this.setaCTIVATEDON(aCTIVATEDON);
        return this;
    }

    public void setaCTIVATEDON(Instant aCTIVATEDON) {
        this.aCTIVATEDON = aCTIVATEDON;
    }

    public String getbRANCHCODE() {
        return this.bRANCHCODE;
    }

    public CUSTOMER bRANCHCODE(String bRANCHCODE) {
        this.setbRANCHCODE(bRANCHCODE);
        return this;
    }

    public void setbRANCHCODE(String bRANCHCODE) {
        this.bRANCHCODE = bRANCHCODE;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CUSTOMER)) {
            return false;
        }
        return getId() != null && getId().equals(((CUSTOMER) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CUSTOMER{" +
            "id=" + getId() +
            ", cUSTOMERNAME='" + getcUSTOMERNAME() + "'" +
            ", pHONENUMBER='" + getpHONENUMBER() + "'" +
            ", cARDNUMBER='" + getcARDNUMBER() + "'" +
            ", aCCOUNTNUMBER='" + getaCCOUNTNUMBER() + "'" +
            ", lANG='" + getlANG() + "'" +
            ", pIN='" + getpIN() + "'" +
            ", fIRSTLOGIN='" + getfIRSTLOGIN() + "'" +
            ", aCTIVE='" + getaCTIVE() + "'" +
            ", rEGISTERED=" + getrEGISTERED() +
            ", cSTDELETE=" + getcSTDELETE() +
            ", rEGDATE='" + getrEGDATE() + "'" +
            ", aLERTENABLED=" + getaLERTENABLED() +
            ", rEMARK='" + getrEMARK() + "'" +
            ", iMSI='" + getiMSI() + "'" +
            ", pARTIALLYREGISTERED='" + getpARTIALLYREGISTERED() + "'" +
            ", pARTIALDATE='" + getpARTIALDATE() + "'" +
            ", rEGISTERDATE='" + getrEGISTERDATE() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDDATE='" + getaPPROVEDDATE() + "'" +
            ", dECLINED=" + getdECLINED() +
            ", dECLINEDBY='" + getdECLINEDBY() + "'" +
            ", dECLINEDDATE='" + getdECLINEDDATE() + "'" +
            ", cHECKERREMARKS='" + getcHECKERREMARKS() + "'" +
            ", pOSTALADDRESS='" + getpOSTALADDRESS() + "'" +
            ", rESIDENCE='" + getrESIDENCE() + "'" +
            ", dOB='" + getdOB() + "'" +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", eMAILADDRESS='" + geteMAILADDRESS() + "'" +
            ", iDENTIFICATIONID='" + getiDENTIFICATIONID() + "'" +
            ", aDDACCOUNT=" + getaDDACCOUNT() +
            ", aCLINKINGINSTITUTION='" + getaCLINKINGINSTITUTION() + "'" +
            ", dEACTIVATED=" + getdEACTIVATED() +
            ", dEACTIVATEDBY='" + getdEACTIVATEDBY() + "'" +
            ", dEACTIVATEDON='" + getdEACTIVATEDON() + "'" +
            ", pHONENOCHANGED=" + getpHONENOCHANGED() +
            ", pHONENOCHANGEDBY='" + getpHONENOCHANGEDBY() + "'" +
            ", pHONENOCHANGEDON='" + getpHONENOCHANGEDON() + "'" +
            ", oRIGINALPHONENO='" + getoRIGINALPHONENO() + "'" +
            ", nEWPHONENO='" + getnEWPHONENO() + "'" +
            ", rESET=" + getrESET() +
            ", rESETINGINSTITUTION='" + getrESETINGINSTITUTION() + "'" +
            ", pINRESETREMARK='" + getpINRESETREMARK() + "'" +
            ", rESETBY='" + getrESETBY() + "'" +
            ", rESETON='" + getrESETON() + "'" +
            ", uNBLOCKINGINSTITUTION='" + getuNBLOCKINGINSTITUTION() + "'" +
            ", pINBLOCK=" + getpINBLOCK() +
            ", pINBLOCKBY='" + getpINBLOCKBY() + "'" +
            ", pINBLOCKREMARKS='" + getpINBLOCKREMARKS() + "'" +
            ", bLOCKINGINSTITUTION='" + getbLOCKINGINSTITUTION() + "'" +
            ", pINBLOCKON='" + getpINBLOCKON() + "'" +
            ", aPPROVEDON='" + getaPPROVEDON() + "'" +
            ", pINUNBLOCKBY='" + getpINUNBLOCKBY() + "'" +
            ", lOGGEDIN=" + getlOGGEDIN() +
            ", tRIALS='" + gettRIALS() + "'" +
            ", iDTYPE='" + getiDTYPE() + "'" +
            ", iDNUMBER='" + getiDNUMBER() + "'" +
            ", gENDER='" + getgENDER() + "'" +
            ", cIF='" + getcIF() + "'" +
            ", dATEOFBIRTH='" + getdATEOFBIRTH() + "'" +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", rESETIMSI=" + getrESETIMSI() +
            ", iMSIRESETBY='" + getiMSIRESETBY() + "'" +
            ", fIRSTNAME='" + getfIRSTNAME() + "'" +
            ", sECONDNAME='" + getsECONDNAME() + "'" +
            ", lASTNAME='" + getlASTNAME() + "'" +
            ", pINBLOCKTIME='" + getpINBLOCKTIME() + "'" +
            ", cUSTOMERSTATUS='" + getcUSTOMERSTATUS() + "'" +
            ", uSERNAME='" + getuSERNAME() + "'" +
            ", pASSWORD='" + getpASSWORD() + "'" +
            ", dEVICEID='" + getdEVICEID() + "'" +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", pASSRESET=" + getpASSRESET() +
            ", pASSRESETBY='" + getpASSRESETBY() + "'" +
            ", pASSRESETON='" + getpASSRESETON() + "'" +
            ", pASSBLOCK=" + getpASSBLOCK() +
            ", pASSBLOCKBY='" + getpASSBLOCKBY() + "'" +
            ", pASSBLOCKON='" + getpASSBLOCKON() + "'" +
            ", pINMARKBLOCK=" + getpINMARKBLOCK() +
            ", pASSMARKBLOCK=" + getpASSMARKBLOCK() +
            ", pASSRESETREMARKS='" + getpASSRESETREMARKS() + "'" +
            ", pASSBLOCKREMARKS='" + getpASSBLOCKREMARKS() + "'" +
            ", pASSUNBLOCKBY='" + getpASSUNBLOCKBY() + "'" +
            ", pASSTRIALS=" + getpASSTRIALS() +
            ", aPPACTIVE=" + getaPPACTIVE() +
            ", lASTLOGIN='" + getlASTLOGIN() + "'" +
            ", aPPMARKEDDISABLE=" + getaPPMARKEDDISABLE() +
            ", dISABLEBY='" + getdISABLEBY() + "'" +
            ", aPPROVEDISABLEBY='" + getaPPROVEDISABLEBY() + "'" +
            ", aPPMARKEDENABLE=" + getaPPMARKEDENABLE() +
            ", eNABLEBY='" + geteNABLEBY() + "'" +
            ", aPPROVEDENABLEBY='" + getaPPROVEDENABLEBY() + "'" +
            ", mARKEDDEACTIVATE=" + getmARKEDDEACTIVATE() +
            ", aPPFIRSTLOGIN='" + getaPPFIRSTLOGIN() + "'" +
            ", aTMTRIALS=" + getaTMTRIALS() +
            ", sHORCUTS='" + getsHORCUTS() + "'" +
            ", mARKEDACTIVATE='" + getmARKEDACTIVATE() + "'" +
            ", tOWN='" + gettOWN() + "'" +
            ", aPPROVEDDISABLEON='" + getaPPROVEDDISABLEON() + "'" +
            ", dISABLEDON='" + getdISABLEDON() + "'" +
            ", rESETAPPROVEON='" + getrESETAPPROVEON() + "'" +
            ", dELETEDBY='" + getdELETEDBY() + "'" +
            ", qUESTIONSASKED='" + getqUESTIONSASKED() + "'" +
            ", qUESTIONSTRIALS='" + getqUESTIONSTRIALS() + "'" +
            ", qUESTIONSANSWERED='" + getqUESTIONSANSWERED() + "'" +
            ", vALIDOTP=" + getvALIDOTP() +
            ", aCTIVATEDBY='" + getaCTIVATEDBY() + "'" +
            ", aCTIVATEDON='" + getaCTIVATEDON() + "'" +
            ", bRANCHCODE='" + getbRANCHCODE() + "'" +
            "}";
    }
}
