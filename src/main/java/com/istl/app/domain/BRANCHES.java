package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BRANCHES.
 */
@Entity
@Table(name = "branches")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BRANCHES implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 4000)
    @Column(name = "b_ranchname", length = 4000)
    private String bRANCHNAME;

    @NotNull
    @Size(max = 3)
    @Column(name = "b_ranchcode", length = 3, nullable = false)
    private String bRANCHCODE;

    @Column(name = "a_pproved")
    private Long aPPROVED;

    @Size(max = 4000)
    @Column(name = "e_mail", length = 4000)
    private String eMAIL;

    @Size(max = 4000)
    @Column(name = "a_ddress", length = 4000)
    private String aDDRESS;

    @Size(max = 12)
    @Column(name = "p_hone", length = 12)
    private String pHONE;

    @NotNull
    @Size(max = 4000)
    @Column(name = "l_ocation", length = 4000, nullable = false)
    private String lOCATION;

    @Size(max = 4000)
    @Column(name = "c_ontactperson", length = 4000)
    private String cONTACTPERSON;

    @Size(max = 2000)
    @Column(name = "r_emarks", length = 2000)
    private String rEMARKS;

    @Size(max = 20)
    @Column(name = "c_reatedby", length = 20)
    private String cREATEDBY;

    @Column(name = "c_reatedon")
    private Instant cREATEDON;

    @Size(max = 20)
    @Column(name = "a_pprovedby", length = 20)
    private String aPPROVEDBY;

    @Size(max = 7)
    @Column(name = "a_pprovedon", length = 7)
    private String aPPROVEDON;

    @Size(max = 200)
    @Column(name = "c_heckerremarks", length = 200)
    private String cHECKERREMARKS;

    @Size(max = 20)
    @Column(name = "d_eletedby", length = 20)
    private String dELETEDBY;

    @Column(name = "d_eletedon")
    private Instant dELETEDON;

    @Size(max = 200)
    @Column(name = "d_eleteremarks", length = 200)
    private String dELETEREMARKS;

    @Column(name = "d_eleted")
    private Long dELETED;

    @Column(name = "d_eclined")
    private Long dECLINED;

    @Size(max = 7)
    @Column(name = "d_eclineddon", length = 7)
    private String dECLINEDDON;

    @Size(max = 20)
    @Column(name = "d_eclinedby", length = 20)
    private String dECLINEDBY;

    @Size(max = 20)
    @Column(name = "s_essionid", length = 20)
    private String sESSIONID;

    @Column(name = "r_eworked")
    private Long rEWORKED;

    @Size(max = 20)
    @Column(name = "r_eworkedby", length = 20)
    private String rEWORKEDBY;

    @Column(name = "r_eworkedon")
    private Instant rEWORKEDON;

    @Size(max = 50)
    @Column(name = "d_istrict", length = 50)
    private String dISTRICT;

    @Size(max = 50)
    @Column(name = "r_egion", length = 50)
    private String rEGION;

    @Size(max = 50)
    @Column(name = "r_egionname", length = 50)
    private String rEGIONNAME;

    @Column(name = "r_eporting")
    private Long rEPORTING;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BRANCHES id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getbRANCHNAME() {
        return this.bRANCHNAME;
    }

    public BRANCHES bRANCHNAME(String bRANCHNAME) {
        this.setbRANCHNAME(bRANCHNAME);
        return this;
    }

    public void setbRANCHNAME(String bRANCHNAME) {
        this.bRANCHNAME = bRANCHNAME;
    }

    public String getbRANCHCODE() {
        return this.bRANCHCODE;
    }

    public BRANCHES bRANCHCODE(String bRANCHCODE) {
        this.setbRANCHCODE(bRANCHCODE);
        return this;
    }

    public void setbRANCHCODE(String bRANCHCODE) {
        this.bRANCHCODE = bRANCHCODE;
    }

    public Long getaPPROVED() {
        return this.aPPROVED;
    }

    public BRANCHES aPPROVED(Long aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Long aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String geteMAIL() {
        return this.eMAIL;
    }

    public BRANCHES eMAIL(String eMAIL) {
        this.seteMAIL(eMAIL);
        return this;
    }

    public void seteMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getaDDRESS() {
        return this.aDDRESS;
    }

    public BRANCHES aDDRESS(String aDDRESS) {
        this.setaDDRESS(aDDRESS);
        return this;
    }

    public void setaDDRESS(String aDDRESS) {
        this.aDDRESS = aDDRESS;
    }

    public String getpHONE() {
        return this.pHONE;
    }

    public BRANCHES pHONE(String pHONE) {
        this.setpHONE(pHONE);
        return this;
    }

    public void setpHONE(String pHONE) {
        this.pHONE = pHONE;
    }

    public String getlOCATION() {
        return this.lOCATION;
    }

    public BRANCHES lOCATION(String lOCATION) {
        this.setlOCATION(lOCATION);
        return this;
    }

    public void setlOCATION(String lOCATION) {
        this.lOCATION = lOCATION;
    }

    public String getcONTACTPERSON() {
        return this.cONTACTPERSON;
    }

    public BRANCHES cONTACTPERSON(String cONTACTPERSON) {
        this.setcONTACTPERSON(cONTACTPERSON);
        return this;
    }

    public void setcONTACTPERSON(String cONTACTPERSON) {
        this.cONTACTPERSON = cONTACTPERSON;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public BRANCHES rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getcREATEDBY() {
        return this.cREATEDBY;
    }

    public BRANCHES cREATEDBY(String cREATEDBY) {
        this.setcREATEDBY(cREATEDBY);
        return this;
    }

    public void setcREATEDBY(String cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public Instant getcREATEDON() {
        return this.cREATEDON;
    }

    public BRANCHES cREATEDON(Instant cREATEDON) {
        this.setcREATEDON(cREATEDON);
        return this;
    }

    public void setcREATEDON(Instant cREATEDON) {
        this.cREATEDON = cREATEDON;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public BRANCHES aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public String getaPPROVEDON() {
        return this.aPPROVEDON;
    }

    public BRANCHES aPPROVEDON(String aPPROVEDON) {
        this.setaPPROVEDON(aPPROVEDON);
        return this;
    }

    public void setaPPROVEDON(String aPPROVEDON) {
        this.aPPROVEDON = aPPROVEDON;
    }

    public String getcHECKERREMARKS() {
        return this.cHECKERREMARKS;
    }

    public BRANCHES cHECKERREMARKS(String cHECKERREMARKS) {
        this.setcHECKERREMARKS(cHECKERREMARKS);
        return this;
    }

    public void setcHECKERREMARKS(String cHECKERREMARKS) {
        this.cHECKERREMARKS = cHECKERREMARKS;
    }

    public String getdELETEDBY() {
        return this.dELETEDBY;
    }

    public BRANCHES dELETEDBY(String dELETEDBY) {
        this.setdELETEDBY(dELETEDBY);
        return this;
    }

    public void setdELETEDBY(String dELETEDBY) {
        this.dELETEDBY = dELETEDBY;
    }

    public Instant getdELETEDON() {
        return this.dELETEDON;
    }

    public BRANCHES dELETEDON(Instant dELETEDON) {
        this.setdELETEDON(dELETEDON);
        return this;
    }

    public void setdELETEDON(Instant dELETEDON) {
        this.dELETEDON = dELETEDON;
    }

    public String getdELETEREMARKS() {
        return this.dELETEREMARKS;
    }

    public BRANCHES dELETEREMARKS(String dELETEREMARKS) {
        this.setdELETEREMARKS(dELETEREMARKS);
        return this;
    }

    public void setdELETEREMARKS(String dELETEREMARKS) {
        this.dELETEREMARKS = dELETEREMARKS;
    }

    public Long getdELETED() {
        return this.dELETED;
    }

    public BRANCHES dELETED(Long dELETED) {
        this.setdELETED(dELETED);
        return this;
    }

    public void setdELETED(Long dELETED) {
        this.dELETED = dELETED;
    }

    public Long getdECLINED() {
        return this.dECLINED;
    }

    public BRANCHES dECLINED(Long dECLINED) {
        this.setdECLINED(dECLINED);
        return this;
    }

    public void setdECLINED(Long dECLINED) {
        this.dECLINED = dECLINED;
    }

    public String getdECLINEDDON() {
        return this.dECLINEDDON;
    }

    public BRANCHES dECLINEDDON(String dECLINEDDON) {
        this.setdECLINEDDON(dECLINEDDON);
        return this;
    }

    public void setdECLINEDDON(String dECLINEDDON) {
        this.dECLINEDDON = dECLINEDDON;
    }

    public String getdECLINEDBY() {
        return this.dECLINEDBY;
    }

    public BRANCHES dECLINEDBY(String dECLINEDBY) {
        this.setdECLINEDBY(dECLINEDBY);
        return this;
    }

    public void setdECLINEDBY(String dECLINEDBY) {
        this.dECLINEDBY = dECLINEDBY;
    }

    public String getsESSIONID() {
        return this.sESSIONID;
    }

    public BRANCHES sESSIONID(String sESSIONID) {
        this.setsESSIONID(sESSIONID);
        return this;
    }

    public void setsESSIONID(String sESSIONID) {
        this.sESSIONID = sESSIONID;
    }

    public Long getrEWORKED() {
        return this.rEWORKED;
    }

    public BRANCHES rEWORKED(Long rEWORKED) {
        this.setrEWORKED(rEWORKED);
        return this;
    }

    public void setrEWORKED(Long rEWORKED) {
        this.rEWORKED = rEWORKED;
    }

    public String getrEWORKEDBY() {
        return this.rEWORKEDBY;
    }

    public BRANCHES rEWORKEDBY(String rEWORKEDBY) {
        this.setrEWORKEDBY(rEWORKEDBY);
        return this;
    }

    public void setrEWORKEDBY(String rEWORKEDBY) {
        this.rEWORKEDBY = rEWORKEDBY;
    }

    public Instant getrEWORKEDON() {
        return this.rEWORKEDON;
    }

    public BRANCHES rEWORKEDON(Instant rEWORKEDON) {
        this.setrEWORKEDON(rEWORKEDON);
        return this;
    }

    public void setrEWORKEDON(Instant rEWORKEDON) {
        this.rEWORKEDON = rEWORKEDON;
    }

    public String getdISTRICT() {
        return this.dISTRICT;
    }

    public BRANCHES dISTRICT(String dISTRICT) {
        this.setdISTRICT(dISTRICT);
        return this;
    }

    public void setdISTRICT(String dISTRICT) {
        this.dISTRICT = dISTRICT;
    }

    public String getrEGION() {
        return this.rEGION;
    }

    public BRANCHES rEGION(String rEGION) {
        this.setrEGION(rEGION);
        return this;
    }

    public void setrEGION(String rEGION) {
        this.rEGION = rEGION;
    }

    public String getrEGIONNAME() {
        return this.rEGIONNAME;
    }

    public BRANCHES rEGIONNAME(String rEGIONNAME) {
        this.setrEGIONNAME(rEGIONNAME);
        return this;
    }

    public void setrEGIONNAME(String rEGIONNAME) {
        this.rEGIONNAME = rEGIONNAME;
    }

    public Long getrEPORTING() {
        return this.rEPORTING;
    }

    public BRANCHES rEPORTING(Long rEPORTING) {
        this.setrEPORTING(rEPORTING);
        return this;
    }

    public void setrEPORTING(Long rEPORTING) {
        this.rEPORTING = rEPORTING;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BRANCHES)) {
            return false;
        }
        return getId() != null && getId().equals(((BRANCHES) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BRANCHES{" +
            "id=" + getId() +
            ", bRANCHNAME='" + getbRANCHNAME() + "'" +
            ", bRANCHCODE='" + getbRANCHCODE() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", eMAIL='" + geteMAIL() + "'" +
            ", aDDRESS='" + getaDDRESS() + "'" +
            ", pHONE='" + getpHONE() + "'" +
            ", lOCATION='" + getlOCATION() + "'" +
            ", cONTACTPERSON='" + getcONTACTPERSON() + "'" +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", cREATEDBY='" + getcREATEDBY() + "'" +
            ", cREATEDON='" + getcREATEDON() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDON='" + getaPPROVEDON() + "'" +
            ", cHECKERREMARKS='" + getcHECKERREMARKS() + "'" +
            ", dELETEDBY='" + getdELETEDBY() + "'" +
            ", dELETEDON='" + getdELETEDON() + "'" +
            ", dELETEREMARKS='" + getdELETEREMARKS() + "'" +
            ", dELETED=" + getdELETED() +
            ", dECLINED=" + getdECLINED() +
            ", dECLINEDDON='" + getdECLINEDDON() + "'" +
            ", dECLINEDBY='" + getdECLINEDBY() + "'" +
            ", sESSIONID='" + getsESSIONID() + "'" +
            ", rEWORKED=" + getrEWORKED() +
            ", rEWORKEDBY='" + getrEWORKEDBY() + "'" +
            ", rEWORKEDON='" + getrEWORKEDON() + "'" +
            ", dISTRICT='" + getdISTRICT() + "'" +
            ", rEGION='" + getrEGION() + "'" +
            ", rEGIONNAME='" + getrEGIONNAME() + "'" +
            ", rEPORTING=" + getrEPORTING() +
            "}";
    }
}
