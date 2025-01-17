package com.istl.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CHARGE.
 */
@Entity
@Table(name = "charge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CHARGE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "t_xntype", length = 150, nullable = false)
    private String tXNTYPE;

    @Column(name = "f_eemode")
    private Long fEEMODE;

    @Column(name = "a_mount")
    private Long aMOUNT;

    @Column(name = "d_atecreated")
    private Instant dATECREATED;

    @Size(max = 150)
    @Column(name = "c_reatedby", length = 150)
    private String cREATEDBY;

    @Size(max = 150)
    @Column(name = "a_pproved", length = 150)
    private String aPPROVED;

    @Size(max = 150)
    @Column(name = "a_pprovedby", length = 150)
    private String aPPROVEDBY;

    @Size(max = 150)
    @Column(name = "c_hannel", length = 150)
    private String cHANNEL;

    @Column(name = "t_xncode")
    private Long tXNCODE;

    @Size(max = 64)
    @Column(name = "d_escription", length = 64)
    private String dESCRIPTION;

    @Column(name = "a_pproveddate")
    private Instant aPPROVEDDATE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "cHARGEIDS" }, allowSetters = true)
    private CHARGERANGES cHARGERANGES;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "cHARGEIDS" }, allowSetters = true)
    private RANGE rANGE;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CHARGE id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettXNTYPE() {
        return this.tXNTYPE;
    }

    public CHARGE tXNTYPE(String tXNTYPE) {
        this.settXNTYPE(tXNTYPE);
        return this;
    }

    public void settXNTYPE(String tXNTYPE) {
        this.tXNTYPE = tXNTYPE;
    }

    public Long getfEEMODE() {
        return this.fEEMODE;
    }

    public CHARGE fEEMODE(Long fEEMODE) {
        this.setfEEMODE(fEEMODE);
        return this;
    }

    public void setfEEMODE(Long fEEMODE) {
        this.fEEMODE = fEEMODE;
    }

    public Long getaMOUNT() {
        return this.aMOUNT;
    }

    public CHARGE aMOUNT(Long aMOUNT) {
        this.setaMOUNT(aMOUNT);
        return this;
    }

    public void setaMOUNT(Long aMOUNT) {
        this.aMOUNT = aMOUNT;
    }

    public Instant getdATECREATED() {
        return this.dATECREATED;
    }

    public CHARGE dATECREATED(Instant dATECREATED) {
        this.setdATECREATED(dATECREATED);
        return this;
    }

    public void setdATECREATED(Instant dATECREATED) {
        this.dATECREATED = dATECREATED;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public CHARGE cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String getaPPROVED() {
        return this.aPPROVED;
    }

    public CHARGE aPPROVED(String aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(String aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public CHARGE aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public CHARGE cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public Long gettXNCODE() {
        return this.tXNCODE;
    }

    public CHARGE tXNCODE(Long tXNCODE) {
        this.settXNCODE(tXNCODE);
        return this;
    }

    public void settXNCODE(Long tXNCODE) {
        this.tXNCODE = tXNCODE;
    }

    public String getdESCRIPTION() {
        return this.dESCRIPTION;
    }

    public CHARGE dESCRIPTION(String dESCRIPTION) {
        this.setdESCRIPTION(dESCRIPTION);
        return this;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public Instant getaPPROVEDDATE() {
        return this.aPPROVEDDATE;
    }

    public CHARGE aPPROVEDDATE(Instant aPPROVEDDATE) {
        this.setaPPROVEDDATE(aPPROVEDDATE);
        return this;
    }

    public void setaPPROVEDDATE(Instant aPPROVEDDATE) {
        this.aPPROVEDDATE = aPPROVEDDATE;
    }

    public CHARGERANGES getCHARGERANGES() {
        return this.cHARGERANGES;
    }

    public void setCHARGERANGES(CHARGERANGES cHARGERANGES) {
        this.cHARGERANGES = cHARGERANGES;
    }

    public CHARGE cHARGERANGES(CHARGERANGES cHARGERANGES) {
        this.setCHARGERANGES(cHARGERANGES);
        return this;
    }

    public RANGE getRANGE() {
        return this.rANGE;
    }

    public void setRANGE(RANGE rANGE) {
        this.rANGE = rANGE;
    }

    public CHARGE rANGE(RANGE rANGE) {
        this.setRANGE(rANGE);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CHARGE)) {
            return false;
        }
        return getId() != null && getId().equals(((CHARGE) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CHARGE{" +
            "id=" + getId() +
            ", tXNTYPE='" + gettXNTYPE() + "'" +
            ", fEEMODE=" + getfEEMODE() +
            ", aMOUNT=" + getaMOUNT() +
            ", dATECREATED='" + getdATECREATED() + "'" +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", aPPROVED='" + getaPPROVED() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", tXNCODE=" + gettXNCODE() +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", aPPROVEDDATE='" + getaPPROVEDDATE() + "'" +
            "}";
    }
}
