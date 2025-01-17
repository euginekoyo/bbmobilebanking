package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LIMITS.
 */
@Entity
@Table(name = "limits")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LIMITS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "t_ransactiontype", length = 50, nullable = false)
    private String tRANSACTIONTYPE;

    @Size(max = 6)
    @Column(name = "p_rocode", length = 6)
    private String pROCODE;

    @Size(max = 30)
    @Column(name = "c_hannel", length = 30)
    private String cHANNEL;

    @NotNull
    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "t_ransactionlimit", nullable = false)
    private Long tRANSACTIONLIMIT;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "d_ailylimit")
    private Long dAILYLIMIT;

    @Size(max = 50)
    @Column(name = "r_egisteredby", length = 50)
    private String rEGISTEREDBY;

    @Size(max = 7)
    @Column(name = "r_egistereddate", length = 7)
    private String rEGISTEREDDATE;

    @Size(max = 2)
    @Column(name = "a_pproved", length = 2)
    private String aPPROVED;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Size(max = 7)
    @Column(name = "a_pproveddate", length = 7)
    private String aPPROVEDDATE;

    @Size(max = 50)
    @Column(name = "u_pdatedby", length = 50)
    private String uPDATEDBY;

    @Size(max = 7)
    @Column(name = "u_pdateddate", length = 7)
    private String uPDATEDDATE;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "r_ework")
    private Long rEWORK;

    @Size(max = 50)
    @Column(name = "r_eworkby", length = 50)
    private String rEWORKBY;

    @Size(max = 50)
    @Column(name = "s_essionid", length = 50)
    private String sESSIONID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LIMITS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettRANSACTIONTYPE() {
        return this.tRANSACTIONTYPE;
    }

    public LIMITS tRANSACTIONTYPE(String tRANSACTIONTYPE) {
        this.settRANSACTIONTYPE(tRANSACTIONTYPE);
        return this;
    }

    public void settRANSACTIONTYPE(String tRANSACTIONTYPE) {
        this.tRANSACTIONTYPE = tRANSACTIONTYPE;
    }

    public String getpROCODE() {
        return this.pROCODE;
    }

    public LIMITS pROCODE(String pROCODE) {
        this.setpROCODE(pROCODE);
        return this;
    }

    public void setpROCODE(String pROCODE) {
        this.pROCODE = pROCODE;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public LIMITS cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public Long gettRANSACTIONLIMIT() {
        return this.tRANSACTIONLIMIT;
    }

    public LIMITS tRANSACTIONLIMIT(Long tRANSACTIONLIMIT) {
        this.settRANSACTIONLIMIT(tRANSACTIONLIMIT);
        return this;
    }

    public void settRANSACTIONLIMIT(Long tRANSACTIONLIMIT) {
        this.tRANSACTIONLIMIT = tRANSACTIONLIMIT;
    }

    public Long getdAILYLIMIT() {
        return this.dAILYLIMIT;
    }

    public LIMITS dAILYLIMIT(Long dAILYLIMIT) {
        this.setdAILYLIMIT(dAILYLIMIT);
        return this;
    }

    public void setdAILYLIMIT(Long dAILYLIMIT) {
        this.dAILYLIMIT = dAILYLIMIT;
    }

    public String getrEGISTEREDBY() {
        return this.rEGISTEREDBY;
    }

    public LIMITS rEGISTEREDBY(String rEGISTEREDBY) {
        this.setrEGISTEREDBY(rEGISTEREDBY);
        return this;
    }

    public void setrEGISTEREDBY(String rEGISTEREDBY) {
        this.rEGISTEREDBY = rEGISTEREDBY;
    }

    public String getrEGISTEREDDATE() {
        return this.rEGISTEREDDATE;
    }

    public LIMITS rEGISTEREDDATE(String rEGISTEREDDATE) {
        this.setrEGISTEREDDATE(rEGISTEREDDATE);
        return this;
    }

    public void setrEGISTEREDDATE(String rEGISTEREDDATE) {
        this.rEGISTEREDDATE = rEGISTEREDDATE;
    }

    public String getaPPROVED() {
        return this.aPPROVED;
    }

    public LIMITS aPPROVED(String aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(String aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public LIMITS aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public String getaPPROVEDDATE() {
        return this.aPPROVEDDATE;
    }

    public LIMITS aPPROVEDDATE(String aPPROVEDDATE) {
        this.setaPPROVEDDATE(aPPROVEDDATE);
        return this;
    }

    public void setaPPROVEDDATE(String aPPROVEDDATE) {
        this.aPPROVEDDATE = aPPROVEDDATE;
    }

    public String getuPDATEDBY() {
        return this.uPDATEDBY;
    }

    public LIMITS uPDATEDBY(String uPDATEDBY) {
        this.setuPDATEDBY(uPDATEDBY);
        return this;
    }

    public void setuPDATEDBY(String uPDATEDBY) {
        this.uPDATEDBY = uPDATEDBY;
    }

    public String getuPDATEDDATE() {
        return this.uPDATEDDATE;
    }

    public LIMITS uPDATEDDATE(String uPDATEDDATE) {
        this.setuPDATEDDATE(uPDATEDDATE);
        return this;
    }

    public void setuPDATEDDATE(String uPDATEDDATE) {
        this.uPDATEDDATE = uPDATEDDATE;
    }

    public Long getrEWORK() {
        return this.rEWORK;
    }

    public LIMITS rEWORK(Long rEWORK) {
        this.setrEWORK(rEWORK);
        return this;
    }

    public void setrEWORK(Long rEWORK) {
        this.rEWORK = rEWORK;
    }

    public String getrEWORKBY() {
        return this.rEWORKBY;
    }

    public LIMITS rEWORKBY(String rEWORKBY) {
        this.setrEWORKBY(rEWORKBY);
        return this;
    }

    public void setrEWORKBY(String rEWORKBY) {
        this.rEWORKBY = rEWORKBY;
    }

    public String getsESSIONID() {
        return this.sESSIONID;
    }

    public LIMITS sESSIONID(String sESSIONID) {
        this.setsESSIONID(sESSIONID);
        return this;
    }

    public void setsESSIONID(String sESSIONID) {
        this.sESSIONID = sESSIONID;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LIMITS)) {
            return false;
        }
        return getId() != null && getId().equals(((LIMITS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LIMITS{" +
            "id=" + getId() +
            ", tRANSACTIONTYPE='" + gettRANSACTIONTYPE() + "'" +
            ", pROCODE='" + getpROCODE() + "'" +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", tRANSACTIONLIMIT=" + gettRANSACTIONLIMIT() +
            ", dAILYLIMIT=" + getdAILYLIMIT() +
            ", rEGISTEREDBY='" + getrEGISTEREDBY() + "'" +
            ", rEGISTEREDDATE='" + getrEGISTEREDDATE() + "'" +
            ", aPPROVED='" + getaPPROVED() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDDATE='" + getaPPROVEDDATE() + "'" +
            ", uPDATEDBY='" + getuPDATEDBY() + "'" +
            ", uPDATEDDATE='" + getuPDATEDDATE() + "'" +
            ", rEWORK=" + getrEWORK() +
            ", rEWORKBY='" + getrEWORKBY() + "'" +
            ", sESSIONID='" + getsESSIONID() + "'" +
            "}";
    }
}
