package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BILLERS.
 */
@Entity
@Table(name = "billers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BILLERS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "b_illerid", length = 50, nullable = false)
    private String bILLERID;

    @NotNull
    @Size(max = 100)
    @Column(name = "d_escription", length = 100, nullable = false)
    private String dESCRIPTION;

    @Size(max = 20)
    @Column(name = "b_illercollectionaccount", length = 20)
    private String bILLERCOLLECTIONACCOUNT;

    @Column(name = "d_atecreated")
    private Instant dATECREATED;

    @Size(max = 50)
    @Column(name = "c_reatedby", length = 50)
    private String cREATEDBY;

    @Column(name = "a_pproved")
    private Long aPPROVED;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Column(name = "a_pproveddate")
    private Instant aPPROVEDDATE;

    @Size(max = 25)
    @Column(name = "c_hargableproductid", length = 25)
    private String cHARGABLEPRODUCTID;

    @Size(max = 25)
    @Column(name = "n_onchargableproductid", length = 25)
    private String nONCHARGABLEPRODUCTID;

    @Size(max = 20)
    @Column(name = "u_sdbillercollectionaccount", length = 20)
    private String uSDBILLERCOLLECTIONACCOUNT;

    @Column(name = "e_nableduplicatecheck")
    private Long eNABLEDUPLICATECHECK;

    @Size(max = 250)
    @Column(name = "r_emarks", length = 250)
    private String rEMARKS;

    @Size(max = 50)
    @Column(name = "s_essionid", length = 50)
    private String sESSIONID;

    @Size(max = 50)
    @Column(name = "r_eworkby", length = 50)
    private String rEWORKBY;

    @Column(name = "s_tatus")
    private Long sTATUS;

    @Column(name = "a_ctive")
    private Long aCTIVE;

    @Column(name = "r_ework")
    private Long rEWORK;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BILLERS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getbILLERID() {
        return this.bILLERID;
    }

    public BILLERS bILLERID(String bILLERID) {
        this.setbILLERID(bILLERID);
        return this;
    }

    public void setbILLERID(String bILLERID) {
        this.bILLERID = bILLERID;
    }

    public String getdESCRIPTION() {
        return this.dESCRIPTION;
    }

    public BILLERS dESCRIPTION(String dESCRIPTION) {
        this.setdESCRIPTION(dESCRIPTION);
        return this;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getbILLERCOLLECTIONACCOUNT() {
        return this.bILLERCOLLECTIONACCOUNT;
    }

    public BILLERS bILLERCOLLECTIONACCOUNT(String bILLERCOLLECTIONACCOUNT) {
        this.setbILLERCOLLECTIONACCOUNT(bILLERCOLLECTIONACCOUNT);
        return this;
    }

    public void setbILLERCOLLECTIONACCOUNT(String bILLERCOLLECTIONACCOUNT) {
        this.bILLERCOLLECTIONACCOUNT = bILLERCOLLECTIONACCOUNT;
    }

    public Instant getdATECREATED() {
        return this.dATECREATED;
    }

    public BILLERS dATECREATED(Instant dATECREATED) {
        this.setdATECREATED(dATECREATED);
        return this;
    }

    public void setdATECREATED(Instant dATECREATED) {
        this.dATECREATED = dATECREATED;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public BILLERS cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public Long getaPPROVED() {
        return this.aPPROVED;
    }

    public BILLERS aPPROVED(Long aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Long aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public BILLERS aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public Instant getaPPROVEDDATE() {
        return this.aPPROVEDDATE;
    }

    public BILLERS aPPROVEDDATE(Instant aPPROVEDDATE) {
        this.setaPPROVEDDATE(aPPROVEDDATE);
        return this;
    }

    public void setaPPROVEDDATE(Instant aPPROVEDDATE) {
        this.aPPROVEDDATE = aPPROVEDDATE;
    }

    public String getcHARGABLEPRODUCTID() {
        return this.cHARGABLEPRODUCTID;
    }

    public BILLERS cHARGABLEPRODUCTID(String cHARGABLEPRODUCTID) {
        this.setcHARGABLEPRODUCTID(cHARGABLEPRODUCTID);
        return this;
    }

    public void setcHARGABLEPRODUCTID(String cHARGABLEPRODUCTID) {
        this.cHARGABLEPRODUCTID = cHARGABLEPRODUCTID;
    }

    public String getnONCHARGABLEPRODUCTID() {
        return this.nONCHARGABLEPRODUCTID;
    }

    public BILLERS nONCHARGABLEPRODUCTID(String nONCHARGABLEPRODUCTID) {
        this.setnONCHARGABLEPRODUCTID(nONCHARGABLEPRODUCTID);
        return this;
    }

    public void setnONCHARGABLEPRODUCTID(String nONCHARGABLEPRODUCTID) {
        this.nONCHARGABLEPRODUCTID = nONCHARGABLEPRODUCTID;
    }

    public String getuSDBILLERCOLLECTIONACCOUNT() {
        return this.uSDBILLERCOLLECTIONACCOUNT;
    }

    public BILLERS uSDBILLERCOLLECTIONACCOUNT(String uSDBILLERCOLLECTIONACCOUNT) {
        this.setuSDBILLERCOLLECTIONACCOUNT(uSDBILLERCOLLECTIONACCOUNT);
        return this;
    }

    public void setuSDBILLERCOLLECTIONACCOUNT(String uSDBILLERCOLLECTIONACCOUNT) {
        this.uSDBILLERCOLLECTIONACCOUNT = uSDBILLERCOLLECTIONACCOUNT;
    }

    public Long geteNABLEDUPLICATECHECK() {
        return this.eNABLEDUPLICATECHECK;
    }

    public BILLERS eNABLEDUPLICATECHECK(Long eNABLEDUPLICATECHECK) {
        this.seteNABLEDUPLICATECHECK(eNABLEDUPLICATECHECK);
        return this;
    }

    public void seteNABLEDUPLICATECHECK(Long eNABLEDUPLICATECHECK) {
        this.eNABLEDUPLICATECHECK = eNABLEDUPLICATECHECK;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public BILLERS rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getsESSIONID() {
        return this.sESSIONID;
    }

    public BILLERS sESSIONID(String sESSIONID) {
        this.setsESSIONID(sESSIONID);
        return this;
    }

    public void setsESSIONID(String sESSIONID) {
        this.sESSIONID = sESSIONID;
    }

    public String getrEWORKBY() {
        return this.rEWORKBY;
    }

    public BILLERS rEWORKBY(String rEWORKBY) {
        this.setrEWORKBY(rEWORKBY);
        return this;
    }

    public void setrEWORKBY(String rEWORKBY) {
        this.rEWORKBY = rEWORKBY;
    }

    public Long getsTATUS() {
        return this.sTATUS;
    }

    public BILLERS sTATUS(Long sTATUS) {
        this.setsTATUS(sTATUS);
        return this;
    }

    public void setsTATUS(Long sTATUS) {
        this.sTATUS = sTATUS;
    }

    public Long getaCTIVE() {
        return this.aCTIVE;
    }

    public BILLERS aCTIVE(Long aCTIVE) {
        this.setaCTIVE(aCTIVE);
        return this;
    }

    public void setaCTIVE(Long aCTIVE) {
        this.aCTIVE = aCTIVE;
    }

    public Long getrEWORK() {
        return this.rEWORK;
    }

    public BILLERS rEWORK(Long rEWORK) {
        this.setrEWORK(rEWORK);
        return this;
    }

    public void setrEWORK(Long rEWORK) {
        this.rEWORK = rEWORK;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BILLERS)) {
            return false;
        }
        return getId() != null && getId().equals(((BILLERS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BILLERS{" +
            "id=" + getId() +
            ", bILLERID='" + getbILLERID() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", bILLERCOLLECTIONACCOUNT='" + getbILLERCOLLECTIONACCOUNT() + "'" +
            ", dATECREATED='" + getdATECREATED() + "'" +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDDATE='" + getaPPROVEDDATE() + "'" +
            ", cHARGABLEPRODUCTID='" + getcHARGABLEPRODUCTID() + "'" +
            ", nONCHARGABLEPRODUCTID='" + getnONCHARGABLEPRODUCTID() + "'" +
            ", uSDBILLERCOLLECTIONACCOUNT='" + getuSDBILLERCOLLECTIONACCOUNT() + "'" +
            ", eNABLEDUPLICATECHECK=" + geteNABLEDUPLICATECHECK() +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", sESSIONID='" + getsESSIONID() + "'" +
            ", rEWORKBY='" + getrEWORKBY() + "'" +
            ", sTATUS=" + getsTATUS() +
            ", aCTIVE=" + getaCTIVE() +
            ", rEWORK=" + getrEWORK() +
            "}";
    }
}
