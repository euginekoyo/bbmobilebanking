package com.istl.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CHARGERANGES.
 */
@Entity
@Table(name = "chargeranges")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CHARGERANGES implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "b_illerid", length = 20, nullable = false)
    private String bILLERID;

    @NotNull
    @Size(max = 20)
    @Column(name = "p_rocessingcode", length = 20, nullable = false)
    private String pROCESSINGCODE;

    @NotNull
    @Column(name = "m_ax", nullable = false)
    private Long mAX;

    @NotNull
    @Column(name = "m_in", nullable = false)
    private Long mIN;

    @NotNull
    @Column(name = "a_mount", nullable = false)
    private Long aMOUNT;

    @Size(max = 50)
    @Column(name = "c_reatedby", length = 50)
    private String cREATEDBY;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Size(max = 30)
    @Column(name = "c_reatedat", length = 30)
    private String cREATEDAT;

    @Size(max = 20)
    @Column(name = "a_pprovedon", length = 20)
    private String aPPROVEDON;

    @Column(name = "a_pproved")
    private Long aPPROVED;

    @Column(name = "d_eclined")
    private Long dECLINED;

    @Size(max = 50)
    @Column(name = "d_eclinedby", length = 50)
    private String dECLINEDBY;

    @NotNull
    @Column(name = "c_hargeid", nullable = false)
    private Long cHARGEID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cHARGERANGES")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cHARGERANGES", "rANGE" }, allowSetters = true)
    private Set<CHARGE> cHARGEIDS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CHARGERANGES id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getbILLERID() {
        return this.bILLERID;
    }

    public CHARGERANGES bILLERID(String bILLERID) {
        this.setbILLERID(bILLERID);
        return this;
    }

    public void setbILLERID(String bILLERID) {
        this.bILLERID = bILLERID;
    }

    public String getpROCESSINGCODE() {
        return this.pROCESSINGCODE;
    }

    public CHARGERANGES pROCESSINGCODE(String pROCESSINGCODE) {
        this.setpROCESSINGCODE(pROCESSINGCODE);
        return this;
    }

    public void setpROCESSINGCODE(String pROCESSINGCODE) {
        this.pROCESSINGCODE = pROCESSINGCODE;
    }

    public Long getmAX() {
        return this.mAX;
    }

    public CHARGERANGES mAX(Long mAX) {
        this.setmAX(mAX);
        return this;
    }

    public void setmAX(Long mAX) {
        this.mAX = mAX;
    }

    public Long getmIN() {
        return this.mIN;
    }

    public CHARGERANGES mIN(Long mIN) {
        this.setmIN(mIN);
        return this;
    }

    public void setmIN(Long mIN) {
        this.mIN = mIN;
    }

    public Long getaMOUNT() {
        return this.aMOUNT;
    }

    public CHARGERANGES aMOUNT(Long aMOUNT) {
        this.setaMOUNT(aMOUNT);
        return this;
    }

    public void setaMOUNT(Long aMOUNT) {
        this.aMOUNT = aMOUNT;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public CHARGERANGES cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public CHARGERANGES aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public String getcREATEDAT() {
        return this.cREATEDAT;
    }

    public CHARGERANGES cREATEDAT(String cREATEDAT) {
        this.setcREATEDAT(cREATEDAT);
        return this;
    }

    public void setcREATEDAT(String cREATEDAT) {
        this.cREATEDAT = cREATEDAT;
    }

    public String getaPPROVEDON() {
        return this.aPPROVEDON;
    }

    public CHARGERANGES aPPROVEDON(String aPPROVEDON) {
        this.setaPPROVEDON(aPPROVEDON);
        return this;
    }

    public void setaPPROVEDON(String aPPROVEDON) {
        this.aPPROVEDON = aPPROVEDON;
    }

    public Long getaPPROVED() {
        return this.aPPROVED;
    }

    public CHARGERANGES aPPROVED(Long aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Long aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public Long getdECLINED() {
        return this.dECLINED;
    }

    public CHARGERANGES dECLINED(Long dECLINED) {
        this.setdECLINED(dECLINED);
        return this;
    }

    public void setdECLINED(Long dECLINED) {
        this.dECLINED = dECLINED;
    }

    public String getdECLINEDBY() {
        return this.dECLINEDBY;
    }

    public CHARGERANGES dECLINEDBY(String dECLINEDBY) {
        this.setdECLINEDBY(dECLINEDBY);
        return this;
    }

    public void setdECLINEDBY(String dECLINEDBY) {
        this.dECLINEDBY = dECLINEDBY;
    }

    public Long getcHARGEID() {
        return this.cHARGEID;
    }

    public CHARGERANGES cHARGEID(Long cHARGEID) {
        this.setcHARGEID(cHARGEID);
        return this;
    }

    public void setcHARGEID(Long cHARGEID) {
        this.cHARGEID = cHARGEID;
    }

    public Set<CHARGE> getCHARGEIDS() {
        return this.cHARGEIDS;
    }

    public void setCHARGEIDS(Set<CHARGE> cHARGES) {
        if (this.cHARGEIDS != null) {
            this.cHARGEIDS.forEach(i -> i.setCHARGERANGES(null));
        }
        if (cHARGES != null) {
            cHARGES.forEach(i -> i.setCHARGERANGES(this));
        }
        this.cHARGEIDS = cHARGES;
    }

    public CHARGERANGES cHARGEIDS(Set<CHARGE> cHARGES) {
        this.setCHARGEIDS(cHARGES);
        return this;
    }

    public CHARGERANGES addCHARGEID(CHARGE cHARGE) {
        this.cHARGEIDS.add(cHARGE);
        cHARGE.setCHARGERANGES(this);
        return this;
    }

    public CHARGERANGES removeCHARGEID(CHARGE cHARGE) {
        this.cHARGEIDS.remove(cHARGE);
        cHARGE.setCHARGERANGES(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CHARGERANGES)) {
            return false;
        }
        return getId() != null && getId().equals(((CHARGERANGES) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CHARGERANGES{" +
            "id=" + getId() +
            ", bILLERID='" + getbILLERID() + "'" +
            ", pROCESSINGCODE='" + getpROCESSINGCODE() + "'" +
            ", mAX=" + getmAX() +
            ", mIN=" + getmIN() +
            ", aMOUNT=" + getaMOUNT() +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", cREATEDAT='" + getcREATEDAT() + "'" +
            ", aPPROVEDON='" + getaPPROVEDON() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", dECLINED=" + getdECLINED() +
            ", dECLINEDBY='" + getdECLINEDBY() + "'" +
            ", cHARGEID=" + getcHARGEID() +
            "}";
    }
}
