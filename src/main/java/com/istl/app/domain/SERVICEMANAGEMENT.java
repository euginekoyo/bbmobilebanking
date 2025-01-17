package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SERVICEMANAGEMENT.
 */
@Entity
@Table(name = "servicemanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SERVICEMANAGEMENT implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 20)
    @Column(name = "p_rocessingcode", length = 20)
    private String pROCESSINGCODE;

    @Size(max = 20)
    @Column(name = "a_ctive", length = 20)
    private String aCTIVE;

    @Size(max = 100)
    @Column(name = "c_reatedby", length = 100)
    private String cREATEDBY;

    @Column(name = "d_atecreated")
    private Instant dATECREATED;

    @Column(name = "a_pproved")
    private Long aPPROVED;

    @Size(max = 100)
    @Column(name = "a_pprovedby", length = 100)
    private String aPPROVEDBY;

    @Column(name = "a_pproveddate")
    private Instant aPPROVEDDATE;

    @Size(max = 20)
    @Column(name = "a_daptortype", length = 20)
    private String aDAPTORTYPE;

    @Size(max = 20)
    @Column(name = "d_estination", length = 20)
    private String dESTINATION;

    @Column(name = "t_hirdpartyresponse")
    private Double tHIRDPARTYRESPONSE;

    @Size(max = 20)
    @Column(name = "t_elco", length = 20)
    private String tELCO;

    @NotNull
    @Column(name = "d_escription", nullable = false)
    private String dESCRIPTION;

    @Column(name = "r_emarks")
    private String rEMARKS;

    @Size(max = 100)
    @Column(name = "s_essionid", length = 100)
    private String sESSIONID;

    @Size(max = 100)
    @Column(name = "r_eworkby", length = 100)
    private String rEWORKBY;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SERVICEMANAGEMENT id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getpROCESSINGCODE() {
        return this.pROCESSINGCODE;
    }

    public SERVICEMANAGEMENT pROCESSINGCODE(String pROCESSINGCODE) {
        this.setpROCESSINGCODE(pROCESSINGCODE);
        return this;
    }

    public void setpROCESSINGCODE(String pROCESSINGCODE) {
        this.pROCESSINGCODE = pROCESSINGCODE;
    }

    public String getaCTIVE() {
        return this.aCTIVE;
    }

    public SERVICEMANAGEMENT aCTIVE(String aCTIVE) {
        this.setaCTIVE(aCTIVE);
        return this;
    }

    public void setaCTIVE(String aCTIVE) {
        this.aCTIVE = aCTIVE;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public SERVICEMANAGEMENT cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public Instant getdATECREATED() {
        return this.dATECREATED;
    }

    public SERVICEMANAGEMENT dATECREATED(Instant dATECREATED) {
        this.setdATECREATED(dATECREATED);
        return this;
    }

    public void setdATECREATED(Instant dATECREATED) {
        this.dATECREATED = dATECREATED;
    }

    public Long getaPPROVED() {
        return this.aPPROVED;
    }

    public SERVICEMANAGEMENT aPPROVED(Long aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Long aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public SERVICEMANAGEMENT aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public Instant getaPPROVEDDATE() {
        return this.aPPROVEDDATE;
    }

    public SERVICEMANAGEMENT aPPROVEDDATE(Instant aPPROVEDDATE) {
        this.setaPPROVEDDATE(aPPROVEDDATE);
        return this;
    }

    public void setaPPROVEDDATE(Instant aPPROVEDDATE) {
        this.aPPROVEDDATE = aPPROVEDDATE;
    }

    public String getaDAPTORTYPE() {
        return this.aDAPTORTYPE;
    }

    public SERVICEMANAGEMENT aDAPTORTYPE(String aDAPTORTYPE) {
        this.setaDAPTORTYPE(aDAPTORTYPE);
        return this;
    }

    public void setaDAPTORTYPE(String aDAPTORTYPE) {
        this.aDAPTORTYPE = aDAPTORTYPE;
    }

    public String getdESTINATION() {
        return this.dESTINATION;
    }

    public SERVICEMANAGEMENT dESTINATION(String dESTINATION) {
        this.setdESTINATION(dESTINATION);
        return this;
    }

    public void setdESTINATION(String dESTINATION) {
        this.dESTINATION = dESTINATION;
    }

    public Double gettHIRDPARTYRESPONSE() {
        return this.tHIRDPARTYRESPONSE;
    }

    public SERVICEMANAGEMENT tHIRDPARTYRESPONSE(Double tHIRDPARTYRESPONSE) {
        this.settHIRDPARTYRESPONSE(tHIRDPARTYRESPONSE);
        return this;
    }

    public void settHIRDPARTYRESPONSE(Double tHIRDPARTYRESPONSE) {
        this.tHIRDPARTYRESPONSE = tHIRDPARTYRESPONSE;
    }

    public String gettELCO() {
        return this.tELCO;
    }

    public SERVICEMANAGEMENT tELCO(String tELCO) {
        this.settELCO(tELCO);
        return this;
    }

    public void settELCO(String tELCO) {
        this.tELCO = tELCO;
    }

    public String getdESCRIPTION() {
        return this.dESCRIPTION;
    }

    public SERVICEMANAGEMENT dESCRIPTION(String dESCRIPTION) {
        this.setdESCRIPTION(dESCRIPTION);
        return this;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public SERVICEMANAGEMENT rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getsESSIONID() {
        return this.sESSIONID;
    }

    public SERVICEMANAGEMENT sESSIONID(String sESSIONID) {
        this.setsESSIONID(sESSIONID);
        return this;
    }

    public void setsESSIONID(String sESSIONID) {
        this.sESSIONID = sESSIONID;
    }

    public String getrEWORKBY() {
        return this.rEWORKBY;
    }

    public SERVICEMANAGEMENT rEWORKBY(String rEWORKBY) {
        this.setrEWORKBY(rEWORKBY);
        return this;
    }

    public void setrEWORKBY(String rEWORKBY) {
        this.rEWORKBY = rEWORKBY;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SERVICEMANAGEMENT)) {
            return false;
        }
        return getId() != null && getId().equals(((SERVICEMANAGEMENT) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SERVICEMANAGEMENT{" +
            "id=" + getId() +
            ", pROCESSINGCODE='" + getpROCESSINGCODE() + "'" +
            ", aCTIVE='" + getaCTIVE() + "'" +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", dATECREATED='" + getdATECREATED() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDDATE='" + getaPPROVEDDATE() + "'" +
            ", aDAPTORTYPE='" + getaDAPTORTYPE() + "'" +
            ", dESTINATION='" + getdESTINATION() + "'" +
            ", tHIRDPARTYRESPONSE=" + gettHIRDPARTYRESPONSE() +
            ", tELCO='" + gettELCO() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", sESSIONID='" + getsESSIONID() + "'" +
            ", rEWORKBY='" + getrEWORKBY() + "'" +
            "}";
    }
}
