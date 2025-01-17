package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TRANSACTIONS.
 */
@Entity
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TRANSACTIONS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "p_rocessed")
    private Long pROCESSED;

    @Size(max = 150)
    @Column(name = "i_ncomingbitmap", length = 150)
    private String iNCOMINGBITMAP;

    @Size(max = 150)
    @Column(name = "o_utgoingbitmap", length = 150)
    private String oUTGOINGBITMAP;

    @Size(max = 4000)
    @Column(name = "i_nmessage", length = 4000)
    private String iNMESSAGE;

    @Size(max = 4000)
    @Column(name = "m_essagetocbs", length = 4000)
    private String mESSAGETOCBS;

    @Size(max = 4000)
    @Column(name = "m_essagefromcbs", length = 4000)
    private String mESSAGEFROMCBS;

    @Column(name = "c_bsprocess")
    private Long cBSPROCESS;

    @Column(name = "c_bsonline")
    private Long cBSONLINE;

    @Size(max = 500)
    @Column(name = "c_bsresponse", length = 500)
    private String cBSRESPONSE;

    @Size(max = 4000)
    @Column(name = "r_esponsemessage", length = 4000)
    private String rESPONSEMESSAGE;

    @Column(name = "r_esponsesent")
    private Long rESPONSESENT;

    @Size(max = 20)
    @Column(name = "c_hannel", length = 20)
    private String cHANNEL;

    @Size(max = 50)
    @Column(name = "o_riginaldata", length = 50)
    private String oRIGINALDATA;

    @Size(max = 150)
    @Column(name = "f_ield_39_resp", length = 150)
    private String fIELD39RESP;

    @Size(max = 4000)
    @Column(name = "n_arration", length = 4000)
    private String nARRATION;

    @Column(name = "a_uthorised")
    private Long aUTHORISED;

    @Size(max = 30)
    @Column(name = "b_ranchcode", length = 30)
    private String bRANCHCODE;

    @Size(max = 150)
    @Column(name = "f_ield_39_original", length = 150)
    private String fIELD39ORIGINAL;

    @Size(max = 10)
    @Column(name = "m_essageclass", length = 10)
    private String mESSAGECLASS;

    @Size(max = 10)
    @Column(name = "t_xncode", length = 10)
    private String tXNCODE;

    @Size(max = 5)
    @Column(name = "c_urrcode", length = 5)
    private String cURRCODE;

    @Size(max = 20)
    @Column(name = "d_evice", length = 20)
    private String dEVICE;

    @Size(max = 30)
    @Column(name = "b_ranch_2", length = 30)
    private String bRANCH2;

    @Column(name = "long_erbranch")
    private Long longERBRANCH;

    @Column(name = "d_atex")
    private Instant dATEX;

    @Size(max = 50)
    @Column(name = "t_imex", length = 50)
    private String tIMEX;

    @Column(name = "p_osted")
    private Long pOSTED;

    @Column(name = "a_ttempts")
    private Long aTTEMPTS;

    @Size(max = 100)
    @Column(name = "o_riginaldata_2", length = 100)
    private String oRIGINALDATA2;

    @Column(name = "c_ommission")
    private Long cOMMISSION;

    @Column(name = "r_esponsecreated")
    private Long rESPONSECREATED;

    @Column(name = "o_nline")
    private Long oNLINE;

    @Size(max = 100)
    @Column(name = "o_riginaldata_3", length = 100)
    private String oRIGINALDATA3;

    @Size(max = 15)
    @Column(name = "t_oswitch", length = 15)
    private String tOSWITCH;

    @Size(max = 15)
    @Column(name = "f_romswitch", length = 15)
    private String fROMSWITCH;

    @Size(max = 15)
    @Column(name = "t_ocbs", length = 15)
    private String tOCBS;

    @Size(max = 15)
    @Column(name = "f_romcbs", length = 15)
    private String fROMCBS;

    @Column(name = "p_ostinglegs")
    private Long pOSTINGLEGS;

    @Size(max = 10)
    @Column(name = "c_ommissiontxncode", length = 10)
    private String cOMMISSIONTXNCODE;

    @Size(max = 30)
    @Column(name = "h_ostref", length = 30)
    private String hOSTREF;

    @Column(name = "r_equestcreated")
    private Long rEQUESTCREATED;

    @Size(max = 4000)
    @Column(name = "r_equestmessage", length = 4000)
    private String rEQUESTMESSAGE;

    @Size(max = 150)
    @Column(name = "o_utgoingbitmapflex", length = 150)
    private String oUTGOINGBITMAPFLEX;

    @Size(max = 150)
    @Column(name = "i_ncomingbitmapflex", length = 150)
    private String iNCOMINGBITMAPFLEX;

    @Column(name = "r_equestsent")
    private Long rEQUESTSENT;

    @Column(name = "m_inicbs")
    private Long mINICBS;

    @Column(name = "r_eversed")
    private Long rEVERSED;

    @Column(name = "o_fflinesenttohost")
    private Long oFFLINESENTTOHOST;

    @Size(max = 150)
    @Column(name = "o_fflineresponse", length = 150)
    private String oFFLINERESPONSE;

    @Size(max = 40)
    @Column(name = "s_ource_long_erface", length = 40)
    private String sOURCELongERFACE;

    @Size(max = 150)
    @Column(name = "m_tirrn", length = 150)
    private String mTIRRN;

    @Size(max = 200)
    @Column(name = "h_ostresponsecode", length = 200)
    private String hOSTRESPONSECODE;

    @Size(max = 150)
    @Column(name = "f_ield_48", length = 150)
    private String fIELD48;

    @Size(max = 150)
    @Column(name = "s_ource", length = 150)
    private String sOURCE;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TRANSACTIONS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpROCESSED() {
        return this.pROCESSED;
    }

    public TRANSACTIONS pROCESSED(Long pROCESSED) {
        this.setpROCESSED(pROCESSED);
        return this;
    }

    public void setpROCESSED(Long pROCESSED) {
        this.pROCESSED = pROCESSED;
    }

    public String getiNCOMINGBITMAP() {
        return this.iNCOMINGBITMAP;
    }

    public TRANSACTIONS iNCOMINGBITMAP(String iNCOMINGBITMAP) {
        this.setiNCOMINGBITMAP(iNCOMINGBITMAP);
        return this;
    }

    public void setiNCOMINGBITMAP(String iNCOMINGBITMAP) {
        this.iNCOMINGBITMAP = iNCOMINGBITMAP;
    }

    public String getoUTGOINGBITMAP() {
        return this.oUTGOINGBITMAP;
    }

    public TRANSACTIONS oUTGOINGBITMAP(String oUTGOINGBITMAP) {
        this.setoUTGOINGBITMAP(oUTGOINGBITMAP);
        return this;
    }

    public void setoUTGOINGBITMAP(String oUTGOINGBITMAP) {
        this.oUTGOINGBITMAP = oUTGOINGBITMAP;
    }

    public String getiNMESSAGE() {
        return this.iNMESSAGE;
    }

    public TRANSACTIONS iNMESSAGE(String iNMESSAGE) {
        this.setiNMESSAGE(iNMESSAGE);
        return this;
    }

    public void setiNMESSAGE(String iNMESSAGE) {
        this.iNMESSAGE = iNMESSAGE;
    }

    public String getmESSAGETOCBS() {
        return this.mESSAGETOCBS;
    }

    public TRANSACTIONS mESSAGETOCBS(String mESSAGETOCBS) {
        this.setmESSAGETOCBS(mESSAGETOCBS);
        return this;
    }

    public void setmESSAGETOCBS(String mESSAGETOCBS) {
        this.mESSAGETOCBS = mESSAGETOCBS;
    }

    public String getmESSAGEFROMCBS() {
        return this.mESSAGEFROMCBS;
    }

    public TRANSACTIONS mESSAGEFROMCBS(String mESSAGEFROMCBS) {
        this.setmESSAGEFROMCBS(mESSAGEFROMCBS);
        return this;
    }

    public void setmESSAGEFROMCBS(String mESSAGEFROMCBS) {
        this.mESSAGEFROMCBS = mESSAGEFROMCBS;
    }

    public Long getcBSPROCESS() {
        return this.cBSPROCESS;
    }

    public TRANSACTIONS cBSPROCESS(Long cBSPROCESS) {
        this.setcBSPROCESS(cBSPROCESS);
        return this;
    }

    public void setcBSPROCESS(Long cBSPROCESS) {
        this.cBSPROCESS = cBSPROCESS;
    }

    public Long getcBSONLINE() {
        return this.cBSONLINE;
    }

    public TRANSACTIONS cBSONLINE(Long cBSONLINE) {
        this.setcBSONLINE(cBSONLINE);
        return this;
    }

    public void setcBSONLINE(Long cBSONLINE) {
        this.cBSONLINE = cBSONLINE;
    }

    public String getcBSRESPONSE() {
        return this.cBSRESPONSE;
    }

    public TRANSACTIONS cBSRESPONSE(String cBSRESPONSE) {
        this.setcBSRESPONSE(cBSRESPONSE);
        return this;
    }

    public void setcBSRESPONSE(String cBSRESPONSE) {
        this.cBSRESPONSE = cBSRESPONSE;
    }

    public String getrESPONSEMESSAGE() {
        return this.rESPONSEMESSAGE;
    }

    public TRANSACTIONS rESPONSEMESSAGE(String rESPONSEMESSAGE) {
        this.setrESPONSEMESSAGE(rESPONSEMESSAGE);
        return this;
    }

    public void setrESPONSEMESSAGE(String rESPONSEMESSAGE) {
        this.rESPONSEMESSAGE = rESPONSEMESSAGE;
    }

    public Long getrESPONSESENT() {
        return this.rESPONSESENT;
    }

    public TRANSACTIONS rESPONSESENT(Long rESPONSESENT) {
        this.setrESPONSESENT(rESPONSESENT);
        return this;
    }

    public void setrESPONSESENT(Long rESPONSESENT) {
        this.rESPONSESENT = rESPONSESENT;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public TRANSACTIONS cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public String getoRIGINALDATA() {
        return this.oRIGINALDATA;
    }

    public TRANSACTIONS oRIGINALDATA(String oRIGINALDATA) {
        this.setoRIGINALDATA(oRIGINALDATA);
        return this;
    }

    public void setoRIGINALDATA(String oRIGINALDATA) {
        this.oRIGINALDATA = oRIGINALDATA;
    }

    public String getfIELD39RESP() {
        return this.fIELD39RESP;
    }

    public TRANSACTIONS fIELD39RESP(String fIELD39RESP) {
        this.setfIELD39RESP(fIELD39RESP);
        return this;
    }

    public void setfIELD39RESP(String fIELD39RESP) {
        this.fIELD39RESP = fIELD39RESP;
    }

    public String getnARRATION() {
        return this.nARRATION;
    }

    public TRANSACTIONS nARRATION(String nARRATION) {
        this.setnARRATION(nARRATION);
        return this;
    }

    public void setnARRATION(String nARRATION) {
        this.nARRATION = nARRATION;
    }

    public Long getaUTHORISED() {
        return this.aUTHORISED;
    }

    public TRANSACTIONS aUTHORISED(Long aUTHORISED) {
        this.setaUTHORISED(aUTHORISED);
        return this;
    }

    public void setaUTHORISED(Long aUTHORISED) {
        this.aUTHORISED = aUTHORISED;
    }

    public String getbRANCHCODE() {
        return this.bRANCHCODE;
    }

    public TRANSACTIONS bRANCHCODE(String bRANCHCODE) {
        this.setbRANCHCODE(bRANCHCODE);
        return this;
    }

    public void setbRANCHCODE(String bRANCHCODE) {
        this.bRANCHCODE = bRANCHCODE;
    }

    public String getfIELD39ORIGINAL() {
        return this.fIELD39ORIGINAL;
    }

    public TRANSACTIONS fIELD39ORIGINAL(String fIELD39ORIGINAL) {
        this.setfIELD39ORIGINAL(fIELD39ORIGINAL);
        return this;
    }

    public void setfIELD39ORIGINAL(String fIELD39ORIGINAL) {
        this.fIELD39ORIGINAL = fIELD39ORIGINAL;
    }

    public String getmESSAGECLASS() {
        return this.mESSAGECLASS;
    }

    public TRANSACTIONS mESSAGECLASS(String mESSAGECLASS) {
        this.setmESSAGECLASS(mESSAGECLASS);
        return this;
    }

    public void setmESSAGECLASS(String mESSAGECLASS) {
        this.mESSAGECLASS = mESSAGECLASS;
    }

    public String gettXNCODE() {
        return this.tXNCODE;
    }

    public TRANSACTIONS tXNCODE(String tXNCODE) {
        this.settXNCODE(tXNCODE);
        return this;
    }

    public void settXNCODE(String tXNCODE) {
        this.tXNCODE = tXNCODE;
    }

    public String getcURRCODE() {
        return this.cURRCODE;
    }

    public TRANSACTIONS cURRCODE(String cURRCODE) {
        this.setcURRCODE(cURRCODE);
        return this;
    }

    public void setcURRCODE(String cURRCODE) {
        this.cURRCODE = cURRCODE;
    }

    public String getdEVICE() {
        return this.dEVICE;
    }

    public TRANSACTIONS dEVICE(String dEVICE) {
        this.setdEVICE(dEVICE);
        return this;
    }

    public void setdEVICE(String dEVICE) {
        this.dEVICE = dEVICE;
    }

    public String getbRANCH2() {
        return this.bRANCH2;
    }

    public TRANSACTIONS bRANCH2(String bRANCH2) {
        this.setbRANCH2(bRANCH2);
        return this;
    }

    public void setbRANCH2(String bRANCH2) {
        this.bRANCH2 = bRANCH2;
    }

    public Long getLongERBRANCH() {
        return this.longERBRANCH;
    }

    public TRANSACTIONS longERBRANCH(Long longERBRANCH) {
        this.setLongERBRANCH(longERBRANCH);
        return this;
    }

    public void setLongERBRANCH(Long longERBRANCH) {
        this.longERBRANCH = longERBRANCH;
    }

    public Instant getdATEX() {
        return this.dATEX;
    }

    public TRANSACTIONS dATEX(Instant dATEX) {
        this.setdATEX(dATEX);
        return this;
    }

    public void setdATEX(Instant dATEX) {
        this.dATEX = dATEX;
    }

    public String gettIMEX() {
        return this.tIMEX;
    }

    public TRANSACTIONS tIMEX(String tIMEX) {
        this.settIMEX(tIMEX);
        return this;
    }

    public void settIMEX(String tIMEX) {
        this.tIMEX = tIMEX;
    }

    public Long getpOSTED() {
        return this.pOSTED;
    }

    public TRANSACTIONS pOSTED(Long pOSTED) {
        this.setpOSTED(pOSTED);
        return this;
    }

    public void setpOSTED(Long pOSTED) {
        this.pOSTED = pOSTED;
    }

    public Long getaTTEMPTS() {
        return this.aTTEMPTS;
    }

    public TRANSACTIONS aTTEMPTS(Long aTTEMPTS) {
        this.setaTTEMPTS(aTTEMPTS);
        return this;
    }

    public void setaTTEMPTS(Long aTTEMPTS) {
        this.aTTEMPTS = aTTEMPTS;
    }

    public String getoRIGINALDATA2() {
        return this.oRIGINALDATA2;
    }

    public TRANSACTIONS oRIGINALDATA2(String oRIGINALDATA2) {
        this.setoRIGINALDATA2(oRIGINALDATA2);
        return this;
    }

    public void setoRIGINALDATA2(String oRIGINALDATA2) {
        this.oRIGINALDATA2 = oRIGINALDATA2;
    }

    public Long getcOMMISSION() {
        return this.cOMMISSION;
    }

    public TRANSACTIONS cOMMISSION(Long cOMMISSION) {
        this.setcOMMISSION(cOMMISSION);
        return this;
    }

    public void setcOMMISSION(Long cOMMISSION) {
        this.cOMMISSION = cOMMISSION;
    }

    public Long getrESPONSECREATED() {
        return this.rESPONSECREATED;
    }

    public TRANSACTIONS rESPONSECREATED(Long rESPONSECREATED) {
        this.setrESPONSECREATED(rESPONSECREATED);
        return this;
    }

    public void setrESPONSECREATED(Long rESPONSECREATED) {
        this.rESPONSECREATED = rESPONSECREATED;
    }

    public Long getoNLINE() {
        return this.oNLINE;
    }

    public TRANSACTIONS oNLINE(Long oNLINE) {
        this.setoNLINE(oNLINE);
        return this;
    }

    public void setoNLINE(Long oNLINE) {
        this.oNLINE = oNLINE;
    }

    public String getoRIGINALDATA3() {
        return this.oRIGINALDATA3;
    }

    public TRANSACTIONS oRIGINALDATA3(String oRIGINALDATA3) {
        this.setoRIGINALDATA3(oRIGINALDATA3);
        return this;
    }

    public void setoRIGINALDATA3(String oRIGINALDATA3) {
        this.oRIGINALDATA3 = oRIGINALDATA3;
    }

    public String gettOSWITCH() {
        return this.tOSWITCH;
    }

    public TRANSACTIONS tOSWITCH(String tOSWITCH) {
        this.settOSWITCH(tOSWITCH);
        return this;
    }

    public void settOSWITCH(String tOSWITCH) {
        this.tOSWITCH = tOSWITCH;
    }

    public String getfROMSWITCH() {
        return this.fROMSWITCH;
    }

    public TRANSACTIONS fROMSWITCH(String fROMSWITCH) {
        this.setfROMSWITCH(fROMSWITCH);
        return this;
    }

    public void setfROMSWITCH(String fROMSWITCH) {
        this.fROMSWITCH = fROMSWITCH;
    }

    public String gettOCBS() {
        return this.tOCBS;
    }

    public TRANSACTIONS tOCBS(String tOCBS) {
        this.settOCBS(tOCBS);
        return this;
    }

    public void settOCBS(String tOCBS) {
        this.tOCBS = tOCBS;
    }

    public String getfROMCBS() {
        return this.fROMCBS;
    }

    public TRANSACTIONS fROMCBS(String fROMCBS) {
        this.setfROMCBS(fROMCBS);
        return this;
    }

    public void setfROMCBS(String fROMCBS) {
        this.fROMCBS = fROMCBS;
    }

    public Long getpOSTINGLEGS() {
        return this.pOSTINGLEGS;
    }

    public TRANSACTIONS pOSTINGLEGS(Long pOSTINGLEGS) {
        this.setpOSTINGLEGS(pOSTINGLEGS);
        return this;
    }

    public void setpOSTINGLEGS(Long pOSTINGLEGS) {
        this.pOSTINGLEGS = pOSTINGLEGS;
    }

    public String getcOMMISSIONTXNCODE() {
        return this.cOMMISSIONTXNCODE;
    }

    public TRANSACTIONS cOMMISSIONTXNCODE(String cOMMISSIONTXNCODE) {
        this.setcOMMISSIONTXNCODE(cOMMISSIONTXNCODE);
        return this;
    }

    public void setcOMMISSIONTXNCODE(String cOMMISSIONTXNCODE) {
        this.cOMMISSIONTXNCODE = cOMMISSIONTXNCODE;
    }

    public String gethOSTREF() {
        return this.hOSTREF;
    }

    public TRANSACTIONS hOSTREF(String hOSTREF) {
        this.sethOSTREF(hOSTREF);
        return this;
    }

    public void sethOSTREF(String hOSTREF) {
        this.hOSTREF = hOSTREF;
    }

    public Long getrEQUESTCREATED() {
        return this.rEQUESTCREATED;
    }

    public TRANSACTIONS rEQUESTCREATED(Long rEQUESTCREATED) {
        this.setrEQUESTCREATED(rEQUESTCREATED);
        return this;
    }

    public void setrEQUESTCREATED(Long rEQUESTCREATED) {
        this.rEQUESTCREATED = rEQUESTCREATED;
    }

    public String getrEQUESTMESSAGE() {
        return this.rEQUESTMESSAGE;
    }

    public TRANSACTIONS rEQUESTMESSAGE(String rEQUESTMESSAGE) {
        this.setrEQUESTMESSAGE(rEQUESTMESSAGE);
        return this;
    }

    public void setrEQUESTMESSAGE(String rEQUESTMESSAGE) {
        this.rEQUESTMESSAGE = rEQUESTMESSAGE;
    }

    public String getoUTGOINGBITMAPFLEX() {
        return this.oUTGOINGBITMAPFLEX;
    }

    public TRANSACTIONS oUTGOINGBITMAPFLEX(String oUTGOINGBITMAPFLEX) {
        this.setoUTGOINGBITMAPFLEX(oUTGOINGBITMAPFLEX);
        return this;
    }

    public void setoUTGOINGBITMAPFLEX(String oUTGOINGBITMAPFLEX) {
        this.oUTGOINGBITMAPFLEX = oUTGOINGBITMAPFLEX;
    }

    public String getiNCOMINGBITMAPFLEX() {
        return this.iNCOMINGBITMAPFLEX;
    }

    public TRANSACTIONS iNCOMINGBITMAPFLEX(String iNCOMINGBITMAPFLEX) {
        this.setiNCOMINGBITMAPFLEX(iNCOMINGBITMAPFLEX);
        return this;
    }

    public void setiNCOMINGBITMAPFLEX(String iNCOMINGBITMAPFLEX) {
        this.iNCOMINGBITMAPFLEX = iNCOMINGBITMAPFLEX;
    }

    public Long getrEQUESTSENT() {
        return this.rEQUESTSENT;
    }

    public TRANSACTIONS rEQUESTSENT(Long rEQUESTSENT) {
        this.setrEQUESTSENT(rEQUESTSENT);
        return this;
    }

    public void setrEQUESTSENT(Long rEQUESTSENT) {
        this.rEQUESTSENT = rEQUESTSENT;
    }

    public Long getmINICBS() {
        return this.mINICBS;
    }

    public TRANSACTIONS mINICBS(Long mINICBS) {
        this.setmINICBS(mINICBS);
        return this;
    }

    public void setmINICBS(Long mINICBS) {
        this.mINICBS = mINICBS;
    }

    public Long getrEVERSED() {
        return this.rEVERSED;
    }

    public TRANSACTIONS rEVERSED(Long rEVERSED) {
        this.setrEVERSED(rEVERSED);
        return this;
    }

    public void setrEVERSED(Long rEVERSED) {
        this.rEVERSED = rEVERSED;
    }

    public Long getoFFLINESENTTOHOST() {
        return this.oFFLINESENTTOHOST;
    }

    public TRANSACTIONS oFFLINESENTTOHOST(Long oFFLINESENTTOHOST) {
        this.setoFFLINESENTTOHOST(oFFLINESENTTOHOST);
        return this;
    }

    public void setoFFLINESENTTOHOST(Long oFFLINESENTTOHOST) {
        this.oFFLINESENTTOHOST = oFFLINESENTTOHOST;
    }

    public String getoFFLINERESPONSE() {
        return this.oFFLINERESPONSE;
    }

    public TRANSACTIONS oFFLINERESPONSE(String oFFLINERESPONSE) {
        this.setoFFLINERESPONSE(oFFLINERESPONSE);
        return this;
    }

    public void setoFFLINERESPONSE(String oFFLINERESPONSE) {
        this.oFFLINERESPONSE = oFFLINERESPONSE;
    }

    public String getsOURCELongERFACE() {
        return this.sOURCELongERFACE;
    }

    public TRANSACTIONS sOURCELongERFACE(String sOURCELongERFACE) {
        this.setsOURCELongERFACE(sOURCELongERFACE);
        return this;
    }

    public void setsOURCELongERFACE(String sOURCELongERFACE) {
        this.sOURCELongERFACE = sOURCELongERFACE;
    }

    public String getmTIRRN() {
        return this.mTIRRN;
    }

    public TRANSACTIONS mTIRRN(String mTIRRN) {
        this.setmTIRRN(mTIRRN);
        return this;
    }

    public void setmTIRRN(String mTIRRN) {
        this.mTIRRN = mTIRRN;
    }

    public String gethOSTRESPONSECODE() {
        return this.hOSTRESPONSECODE;
    }

    public TRANSACTIONS hOSTRESPONSECODE(String hOSTRESPONSECODE) {
        this.sethOSTRESPONSECODE(hOSTRESPONSECODE);
        return this;
    }

    public void sethOSTRESPONSECODE(String hOSTRESPONSECODE) {
        this.hOSTRESPONSECODE = hOSTRESPONSECODE;
    }

    public String getfIELD48() {
        return this.fIELD48;
    }

    public TRANSACTIONS fIELD48(String fIELD48) {
        this.setfIELD48(fIELD48);
        return this;
    }

    public void setfIELD48(String fIELD48) {
        this.fIELD48 = fIELD48;
    }

    public String getsOURCE() {
        return this.sOURCE;
    }

    public TRANSACTIONS sOURCE(String sOURCE) {
        this.setsOURCE(sOURCE);
        return this;
    }

    public void setsOURCE(String sOURCE) {
        this.sOURCE = sOURCE;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TRANSACTIONS)) {
            return false;
        }
        return getId() != null && getId().equals(((TRANSACTIONS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TRANSACTIONS{" +
            "id=" + getId() +
            ", pROCESSED=" + getpROCESSED() +
            ", iNCOMINGBITMAP='" + getiNCOMINGBITMAP() + "'" +
            ", oUTGOINGBITMAP='" + getoUTGOINGBITMAP() + "'" +
            ", iNMESSAGE='" + getiNMESSAGE() + "'" +
            ", mESSAGETOCBS='" + getmESSAGETOCBS() + "'" +
            ", mESSAGEFROMCBS='" + getmESSAGEFROMCBS() + "'" +
            ", cBSPROCESS=" + getcBSPROCESS() +
            ", cBSONLINE=" + getcBSONLINE() +
            ", cBSRESPONSE='" + getcBSRESPONSE() + "'" +
            ", rESPONSEMESSAGE='" + getrESPONSEMESSAGE() + "'" +
            ", rESPONSESENT=" + getrESPONSESENT() +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", oRIGINALDATA='" + getoRIGINALDATA() + "'" +
            ", fIELD39RESP='" + getfIELD39RESP() + "'" +
            ", nARRATION='" + getnARRATION() + "'" +
            ", aUTHORISED=" + getaUTHORISED() +
            ", bRANCHCODE='" + getbRANCHCODE() + "'" +
            ", fIELD39ORIGINAL='" + getfIELD39ORIGINAL() + "'" +
            ", mESSAGECLASS='" + getmESSAGECLASS() + "'" +
            ", tXNCODE='" + gettXNCODE() + "'" +
            ", cURRCODE='" + getcURRCODE() + "'" +
            ", dEVICE='" + getdEVICE() + "'" +
            ", bRANCH2='" + getbRANCH2() + "'" +
            ", longERBRANCH=" + getLongERBRANCH() +
            ", dATEX='" + getdATEX() + "'" +
            ", tIMEX='" + gettIMEX() + "'" +
            ", pOSTED=" + getpOSTED() +
            ", aTTEMPTS=" + getaTTEMPTS() +
            ", oRIGINALDATA2='" + getoRIGINALDATA2() + "'" +
            ", cOMMISSION=" + getcOMMISSION() +
            ", rESPONSECREATED=" + getrESPONSECREATED() +
            ", oNLINE=" + getoNLINE() +
            ", oRIGINALDATA3='" + getoRIGINALDATA3() + "'" +
            ", tOSWITCH='" + gettOSWITCH() + "'" +
            ", fROMSWITCH='" + getfROMSWITCH() + "'" +
            ", tOCBS='" + gettOCBS() + "'" +
            ", fROMCBS='" + getfROMCBS() + "'" +
            ", pOSTINGLEGS=" + getpOSTINGLEGS() +
            ", cOMMISSIONTXNCODE='" + getcOMMISSIONTXNCODE() + "'" +
            ", hOSTREF='" + gethOSTREF() + "'" +
            ", rEQUESTCREATED=" + getrEQUESTCREATED() +
            ", rEQUESTMESSAGE='" + getrEQUESTMESSAGE() + "'" +
            ", oUTGOINGBITMAPFLEX='" + getoUTGOINGBITMAPFLEX() + "'" +
            ", iNCOMINGBITMAPFLEX='" + getiNCOMINGBITMAPFLEX() + "'" +
            ", rEQUESTSENT=" + getrEQUESTSENT() +
            ", mINICBS=" + getmINICBS() +
            ", rEVERSED=" + getrEVERSED() +
            ", oFFLINESENTTOHOST=" + getoFFLINESENTTOHOST() +
            ", oFFLINERESPONSE='" + getoFFLINERESPONSE() + "'" +
            ", sOURCELongERFACE='" + getsOURCELongERFACE() + "'" +
            ", mTIRRN='" + getmTIRRN() + "'" +
            ", hOSTRESPONSECODE='" + gethOSTRESPONSECODE() + "'" +
            ", fIELD48='" + getfIELD48() + "'" +
            ", sOURCE='" + getsOURCE() + "'" +
            "}";
    }
}
