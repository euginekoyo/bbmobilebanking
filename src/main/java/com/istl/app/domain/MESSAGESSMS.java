package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MESSAGESSMS.
 */
@Entity
@Table(name = "messagessms")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MESSAGESSMS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "t_rndatetime")
    private Instant tRNDATETIME;

    @Size(max = 50)
    @Column(name = "p_honenumber", length = 50)
    private String pHONENUMBER;

    @Size(max = 4000)
    @Column(name = "t_ransactionno", length = 4000)
    private String tRANSACTIONNO;

    @Size(max = 50)
    @Column(name = "a_ccountnumber", length = 50)
    private String aCCOUNTNUMBER;

    @Size(max = 2000)
    @Column(name = "m_essage", length = 2000)
    private String mESSAGE;

    @Size(max = 4000)
    @Column(name = "c_hannel", length = 4000)
    private String cHANNEL;

    @Column(name = "t_rials")
    private Long tRIALS;

    @Column(name = "p_riority")
    private Long pRIORITY;

    @Size(max = 4)
    @Column(name = "r_esponsecode", length = 4)
    private String rESPONSECODE;

    @Size(max = 4000)
    @Column(name = "r_esponsemsg", length = 4000)
    private String rESPONSEMSG;

    @Column(name = "s_ent")
    private Long sENT;

    @Column(name = "d_elivered")
    private Long dELIVERED;

    @Size(max = 200)
    @Column(name = "t_xntype", length = 200)
    private String tXNTYPE;

    @Column(name = "e_rrorexception")
    private Long eRROREXCEPTION;

    @Column(name = "d_atecreated")
    private Instant dATECREATED;

    @Size(max = 7)
    @Column(name = "d_atesent", length = 7)
    private String dATESENT;

    @Size(max = 200)
    @Column(name = "r_tpsreqtime", length = 200)
    private String rTPSREQTIME;

    @Size(max = 20)
    @Column(name = "f_xgenerated", length = 20)
    private String fXGENERATED;

    @Column(name = "t_axprocessed")
    private Long tAXPROCESSED;

    @Size(max = 200)
    @Column(name = "b_atchnumber", length = 200)
    private String bATCHNUMBER;

    @Size(max = 200)
    @Column(name = "b_atchnumbertax", length = 200)
    private String bATCHNUMBERTAX;

    @Size(max = 200)
    @Column(name = "r_esponsetime", length = 200)
    private String rESPONSETIME;

    @Size(max = 200)
    @Column(name = "p_duseqid", length = 200)
    private String pDUSEQID;

    @Size(max = 300)
    @Column(name = "r_emarks", length = 300)
    private String rEMARKS;

    @Size(max = 50)
    @Column(name = "r_esendby", length = 50)
    private String rESENDBY;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MESSAGESSMS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant gettRNDATETIME() {
        return this.tRNDATETIME;
    }

    public MESSAGESSMS tRNDATETIME(Instant tRNDATETIME) {
        this.settRNDATETIME(tRNDATETIME);
        return this;
    }

    public void settRNDATETIME(Instant tRNDATETIME) {
        this.tRNDATETIME = tRNDATETIME;
    }

    public String getpHONENUMBER() {
        return this.pHONENUMBER;
    }

    public MESSAGESSMS pHONENUMBER(String pHONENUMBER) {
        this.setpHONENUMBER(pHONENUMBER);
        return this;
    }

    public void setpHONENUMBER(String pHONENUMBER) {
        this.pHONENUMBER = pHONENUMBER;
    }

    public String gettRANSACTIONNO() {
        return this.tRANSACTIONNO;
    }

    public MESSAGESSMS tRANSACTIONNO(String tRANSACTIONNO) {
        this.settRANSACTIONNO(tRANSACTIONNO);
        return this;
    }

    public void settRANSACTIONNO(String tRANSACTIONNO) {
        this.tRANSACTIONNO = tRANSACTIONNO;
    }

    public String getaCCOUNTNUMBER() {
        return this.aCCOUNTNUMBER;
    }

    public MESSAGESSMS aCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.setaCCOUNTNUMBER(aCCOUNTNUMBER);
        return this;
    }

    public void setaCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.aCCOUNTNUMBER = aCCOUNTNUMBER;
    }

    public String getmESSAGE() {
        return this.mESSAGE;
    }

    public MESSAGESSMS mESSAGE(String mESSAGE) {
        this.setmESSAGE(mESSAGE);
        return this;
    }

    public void setmESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public MESSAGESSMS cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public Long gettRIALS() {
        return this.tRIALS;
    }

    public MESSAGESSMS tRIALS(Long tRIALS) {
        this.settRIALS(tRIALS);
        return this;
    }

    public void settRIALS(Long tRIALS) {
        this.tRIALS = tRIALS;
    }

    public Long getpRIORITY() {
        return this.pRIORITY;
    }

    public MESSAGESSMS pRIORITY(Long pRIORITY) {
        this.setpRIORITY(pRIORITY);
        return this;
    }

    public void setpRIORITY(Long pRIORITY) {
        this.pRIORITY = pRIORITY;
    }

    public String getrESPONSECODE() {
        return this.rESPONSECODE;
    }

    public MESSAGESSMS rESPONSECODE(String rESPONSECODE) {
        this.setrESPONSECODE(rESPONSECODE);
        return this;
    }

    public void setrESPONSECODE(String rESPONSECODE) {
        this.rESPONSECODE = rESPONSECODE;
    }

    public String getrESPONSEMSG() {
        return this.rESPONSEMSG;
    }

    public MESSAGESSMS rESPONSEMSG(String rESPONSEMSG) {
        this.setrESPONSEMSG(rESPONSEMSG);
        return this;
    }

    public void setrESPONSEMSG(String rESPONSEMSG) {
        this.rESPONSEMSG = rESPONSEMSG;
    }

    public Long getsENT() {
        return this.sENT;
    }

    public MESSAGESSMS sENT(Long sENT) {
        this.setsENT(sENT);
        return this;
    }

    public void setsENT(Long sENT) {
        this.sENT = sENT;
    }

    public Long getdELIVERED() {
        return this.dELIVERED;
    }

    public MESSAGESSMS dELIVERED(Long dELIVERED) {
        this.setdELIVERED(dELIVERED);
        return this;
    }

    public void setdELIVERED(Long dELIVERED) {
        this.dELIVERED = dELIVERED;
    }

    public String gettXNTYPE() {
        return this.tXNTYPE;
    }

    public MESSAGESSMS tXNTYPE(String tXNTYPE) {
        this.settXNTYPE(tXNTYPE);
        return this;
    }

    public void settXNTYPE(String tXNTYPE) {
        this.tXNTYPE = tXNTYPE;
    }

    public Long geteRROREXCEPTION() {
        return this.eRROREXCEPTION;
    }

    public MESSAGESSMS eRROREXCEPTION(Long eRROREXCEPTION) {
        this.seteRROREXCEPTION(eRROREXCEPTION);
        return this;
    }

    public void seteRROREXCEPTION(Long eRROREXCEPTION) {
        this.eRROREXCEPTION = eRROREXCEPTION;
    }

    public Instant getdATECREATED() {
        return this.dATECREATED;
    }

    public MESSAGESSMS dATECREATED(Instant dATECREATED) {
        this.setdATECREATED(dATECREATED);
        return this;
    }

    public void setdATECREATED(Instant dATECREATED) {
        this.dATECREATED = dATECREATED;
    }

    public String getdATESENT() {
        return this.dATESENT;
    }

    public MESSAGESSMS dATESENT(String dATESENT) {
        this.setdATESENT(dATESENT);
        return this;
    }

    public void setdATESENT(String dATESENT) {
        this.dATESENT = dATESENT;
    }

    public String getrTPSREQTIME() {
        return this.rTPSREQTIME;
    }

    public MESSAGESSMS rTPSREQTIME(String rTPSREQTIME) {
        this.setrTPSREQTIME(rTPSREQTIME);
        return this;
    }

    public void setrTPSREQTIME(String rTPSREQTIME) {
        this.rTPSREQTIME = rTPSREQTIME;
    }

    public String getfXGENERATED() {
        return this.fXGENERATED;
    }

    public MESSAGESSMS fXGENERATED(String fXGENERATED) {
        this.setfXGENERATED(fXGENERATED);
        return this;
    }

    public void setfXGENERATED(String fXGENERATED) {
        this.fXGENERATED = fXGENERATED;
    }

    public Long gettAXPROCESSED() {
        return this.tAXPROCESSED;
    }

    public MESSAGESSMS tAXPROCESSED(Long tAXPROCESSED) {
        this.settAXPROCESSED(tAXPROCESSED);
        return this;
    }

    public void settAXPROCESSED(Long tAXPROCESSED) {
        this.tAXPROCESSED = tAXPROCESSED;
    }

    public String getbATCHNUMBER() {
        return this.bATCHNUMBER;
    }

    public MESSAGESSMS bATCHNUMBER(String bATCHNUMBER) {
        this.setbATCHNUMBER(bATCHNUMBER);
        return this;
    }

    public void setbATCHNUMBER(String bATCHNUMBER) {
        this.bATCHNUMBER = bATCHNUMBER;
    }

    public String getbATCHNUMBERTAX() {
        return this.bATCHNUMBERTAX;
    }

    public MESSAGESSMS bATCHNUMBERTAX(String bATCHNUMBERTAX) {
        this.setbATCHNUMBERTAX(bATCHNUMBERTAX);
        return this;
    }

    public void setbATCHNUMBERTAX(String bATCHNUMBERTAX) {
        this.bATCHNUMBERTAX = bATCHNUMBERTAX;
    }

    public String getrESPONSETIME() {
        return this.rESPONSETIME;
    }

    public MESSAGESSMS rESPONSETIME(String rESPONSETIME) {
        this.setrESPONSETIME(rESPONSETIME);
        return this;
    }

    public void setrESPONSETIME(String rESPONSETIME) {
        this.rESPONSETIME = rESPONSETIME;
    }

    public String getpDUSEQID() {
        return this.pDUSEQID;
    }

    public MESSAGESSMS pDUSEQID(String pDUSEQID) {
        this.setpDUSEQID(pDUSEQID);
        return this;
    }

    public void setpDUSEQID(String pDUSEQID) {
        this.pDUSEQID = pDUSEQID;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public MESSAGESSMS rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getrESENDBY() {
        return this.rESENDBY;
    }

    public MESSAGESSMS rESENDBY(String rESENDBY) {
        this.setrESENDBY(rESENDBY);
        return this;
    }

    public void setrESENDBY(String rESENDBY) {
        this.rESENDBY = rESENDBY;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MESSAGESSMS)) {
            return false;
        }
        return getId() != null && getId().equals(((MESSAGESSMS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MESSAGESSMS{" +
            "id=" + getId() +
            ", tRNDATETIME='" + gettRNDATETIME() + "'" +
            ", pHONENUMBER='" + getpHONENUMBER() + "'" +
            ", tRANSACTIONNO='" + gettRANSACTIONNO() + "'" +
            ", aCCOUNTNUMBER='" + getaCCOUNTNUMBER() + "'" +
            ", mESSAGE='" + getmESSAGE() + "'" +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", tRIALS=" + gettRIALS() +
            ", pRIORITY=" + getpRIORITY() +
            ", rESPONSECODE='" + getrESPONSECODE() + "'" +
            ", rESPONSEMSG='" + getrESPONSEMSG() + "'" +
            ", sENT=" + getsENT() +
            ", dELIVERED=" + getdELIVERED() +
            ", tXNTYPE='" + gettXNTYPE() + "'" +
            ", eRROREXCEPTION=" + geteRROREXCEPTION() +
            ", dATECREATED='" + getdATECREATED() + "'" +
            ", dATESENT='" + getdATESENT() + "'" +
            ", rTPSREQTIME='" + getrTPSREQTIME() + "'" +
            ", fXGENERATED='" + getfXGENERATED() + "'" +
            ", tAXPROCESSED=" + gettAXPROCESSED() +
            ", bATCHNUMBER='" + getbATCHNUMBER() + "'" +
            ", bATCHNUMBERTAX='" + getbATCHNUMBERTAX() + "'" +
            ", rESPONSETIME='" + getrESPONSETIME() + "'" +
            ", pDUSEQID='" + getpDUSEQID() + "'" +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", rESENDBY='" + getrESENDBY() + "'" +
            "}";
    }
}
