package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LINKEDACCOUNTS.
 */
@Entity
@Table(name = "linkedaccounts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LINKEDACCOUNTS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 200)
    @Column(name = "a_countname", length = 200)
    private String aCOUNTNAME;

    @Size(max = 20)
    @Column(name = "a_ccountclass", length = 20)
    private String aCCOUNTCLASS;

    @Size(max = 20)
    @Column(name = "a_ccountcurrency", length = 20)
    private String aCCOUNTCURRENCY;

    @Size(max = 20)
    @Column(name = "a_ccountnumber", length = 20)
    private String aCCOUNTNUMBER;

    @Size(max = 20)
    @Column(name = "c_if", length = 20)
    private String cIF;

    @Column(name = "t_imelinked")
    private Instant tIMELINKED;

    @Size(max = 200)
    @Column(name = "p_honenumber", length = 200)
    private String pHONENUMBER;

    @Column(name = "a_pprovedon")
    private Instant aPPROVEDON;

    @Size(max = 1)
    @Column(name = "a_pproved", length = 1)
    private String aPPROVED;

    @Size(max = 1)
    @Column(name = "d_eclined", length = 1)
    private String dECLINED;

    @Column(name = "d_eclinedon")
    private Instant dECLINEDON;

    @NotNull
    @Size(max = 250)
    @Column(name = "r_emarks", length = 250, nullable = false)
    private String rEMARKS;

    @Size(max = 50)
    @Column(name = "l_inkedby", length = 50)
    private String lINKEDBY;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Size(max = 1)
    @Column(name = "l_inked", length = 1)
    private String lINKED;

    @Size(max = 1)
    @Column(name = "a_ctive", length = 1)
    private String aCTIVE;

    @Size(max = 50)
    @Column(name = "d_elinkedby", length = 50)
    private String dELINKEDBY;

    @Column(name = "d_elinkedon")
    private Instant dELINKEDON;

    @Size(max = 1)
    @Column(name = "d_elinked", length = 1)
    private String dELINKED;

    @Size(max = 20)
    @Column(name = "a_ccountalias", length = 20)
    private String aCCOUNTALIAS;

    @Size(max = 1000)
    @Column(name = "s_hortcuts", length = 1000)
    private String sHORTCUTS;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LINKEDACCOUNTS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getaCOUNTNAME() {
        return this.aCOUNTNAME;
    }

    public LINKEDACCOUNTS aCOUNTNAME(String aCOUNTNAME) {
        this.setaCOUNTNAME(aCOUNTNAME);
        return this;
    }

    public void setaCOUNTNAME(String aCOUNTNAME) {
        this.aCOUNTNAME = aCOUNTNAME;
    }

    public String getaCCOUNTCLASS() {
        return this.aCCOUNTCLASS;
    }

    public LINKEDACCOUNTS aCCOUNTCLASS(String aCCOUNTCLASS) {
        this.setaCCOUNTCLASS(aCCOUNTCLASS);
        return this;
    }

    public void setaCCOUNTCLASS(String aCCOUNTCLASS) {
        this.aCCOUNTCLASS = aCCOUNTCLASS;
    }

    public String getaCCOUNTCURRENCY() {
        return this.aCCOUNTCURRENCY;
    }

    public LINKEDACCOUNTS aCCOUNTCURRENCY(String aCCOUNTCURRENCY) {
        this.setaCCOUNTCURRENCY(aCCOUNTCURRENCY);
        return this;
    }

    public void setaCCOUNTCURRENCY(String aCCOUNTCURRENCY) {
        this.aCCOUNTCURRENCY = aCCOUNTCURRENCY;
    }

    public String getaCCOUNTNUMBER() {
        return this.aCCOUNTNUMBER;
    }

    public LINKEDACCOUNTS aCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.setaCCOUNTNUMBER(aCCOUNTNUMBER);
        return this;
    }

    public void setaCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.aCCOUNTNUMBER = aCCOUNTNUMBER;
    }

    public String getcIF() {
        return this.cIF;
    }

    public LINKEDACCOUNTS cIF(String cIF) {
        this.setcIF(cIF);
        return this;
    }

    public void setcIF(String cIF) {
        this.cIF = cIF;
    }

    public Instant gettIMELINKED() {
        return this.tIMELINKED;
    }

    public LINKEDACCOUNTS tIMELINKED(Instant tIMELINKED) {
        this.settIMELINKED(tIMELINKED);
        return this;
    }

    public void settIMELINKED(Instant tIMELINKED) {
        this.tIMELINKED = tIMELINKED;
    }

    public String getpHONENUMBER() {
        return this.pHONENUMBER;
    }

    public LINKEDACCOUNTS pHONENUMBER(String pHONENUMBER) {
        this.setpHONENUMBER(pHONENUMBER);
        return this;
    }

    public void setpHONENUMBER(String pHONENUMBER) {
        this.pHONENUMBER = pHONENUMBER;
    }

    public Instant getaPPROVEDON() {
        return this.aPPROVEDON;
    }

    public LINKEDACCOUNTS aPPROVEDON(Instant aPPROVEDON) {
        this.setaPPROVEDON(aPPROVEDON);
        return this;
    }

    public void setaPPROVEDON(Instant aPPROVEDON) {
        this.aPPROVEDON = aPPROVEDON;
    }

    public String getaPPROVED() {
        return this.aPPROVED;
    }

    public LINKEDACCOUNTS aPPROVED(String aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(String aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getdECLINED() {
        return this.dECLINED;
    }

    public LINKEDACCOUNTS dECLINED(String dECLINED) {
        this.setdECLINED(dECLINED);
        return this;
    }

    public void setdECLINED(String dECLINED) {
        this.dECLINED = dECLINED;
    }

    public Instant getdECLINEDON() {
        return this.dECLINEDON;
    }

    public LINKEDACCOUNTS dECLINEDON(Instant dECLINEDON) {
        this.setdECLINEDON(dECLINEDON);
        return this;
    }

    public void setdECLINEDON(Instant dECLINEDON) {
        this.dECLINEDON = dECLINEDON;
    }

    public String getrEMARKS() {
        return this.rEMARKS;
    }

    public LINKEDACCOUNTS rEMARKS(String rEMARKS) {
        this.setrEMARKS(rEMARKS);
        return this;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getlINKEDBY() {
        return this.lINKEDBY;
    }

    public LINKEDACCOUNTS lINKEDBY(String lINKEDBY) {
        this.setlINKEDBY(lINKEDBY);
        return this;
    }

    public void setlINKEDBY(String lINKEDBY) {
        this.lINKEDBY = lINKEDBY;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public LINKEDACCOUNTS aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public String getlINKED() {
        return this.lINKED;
    }

    public LINKEDACCOUNTS lINKED(String lINKED) {
        this.setlINKED(lINKED);
        return this;
    }

    public void setlINKED(String lINKED) {
        this.lINKED = lINKED;
    }

    public String getaCTIVE() {
        return this.aCTIVE;
    }

    public LINKEDACCOUNTS aCTIVE(String aCTIVE) {
        this.setaCTIVE(aCTIVE);
        return this;
    }

    public void setaCTIVE(String aCTIVE) {
        this.aCTIVE = aCTIVE;
    }

    public String getdELINKEDBY() {
        return this.dELINKEDBY;
    }

    public LINKEDACCOUNTS dELINKEDBY(String dELINKEDBY) {
        this.setdELINKEDBY(dELINKEDBY);
        return this;
    }

    public void setdELINKEDBY(String dELINKEDBY) {
        this.dELINKEDBY = dELINKEDBY;
    }

    public Instant getdELINKEDON() {
        return this.dELINKEDON;
    }

    public LINKEDACCOUNTS dELINKEDON(Instant dELINKEDON) {
        this.setdELINKEDON(dELINKEDON);
        return this;
    }

    public void setdELINKEDON(Instant dELINKEDON) {
        this.dELINKEDON = dELINKEDON;
    }

    public String getdELINKED() {
        return this.dELINKED;
    }

    public LINKEDACCOUNTS dELINKED(String dELINKED) {
        this.setdELINKED(dELINKED);
        return this;
    }

    public void setdELINKED(String dELINKED) {
        this.dELINKED = dELINKED;
    }

    public String getaCCOUNTALIAS() {
        return this.aCCOUNTALIAS;
    }

    public LINKEDACCOUNTS aCCOUNTALIAS(String aCCOUNTALIAS) {
        this.setaCCOUNTALIAS(aCCOUNTALIAS);
        return this;
    }

    public void setaCCOUNTALIAS(String aCCOUNTALIAS) {
        this.aCCOUNTALIAS = aCCOUNTALIAS;
    }

    public String getsHORTCUTS() {
        return this.sHORTCUTS;
    }

    public LINKEDACCOUNTS sHORTCUTS(String sHORTCUTS) {
        this.setsHORTCUTS(sHORTCUTS);
        return this;
    }

    public void setsHORTCUTS(String sHORTCUTS) {
        this.sHORTCUTS = sHORTCUTS;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LINKEDACCOUNTS)) {
            return false;
        }
        return getId() != null && getId().equals(((LINKEDACCOUNTS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LINKEDACCOUNTS{" +
            "id=" + getId() +
            ", aCOUNTNAME='" + getaCOUNTNAME() + "'" +
            ", aCCOUNTCLASS='" + getaCCOUNTCLASS() + "'" +
            ", aCCOUNTCURRENCY='" + getaCCOUNTCURRENCY() + "'" +
            ", aCCOUNTNUMBER='" + getaCCOUNTNUMBER() + "'" +
            ", cIF='" + getcIF() + "'" +
            ", tIMELINKED='" + gettIMELINKED() + "'" +
            ", pHONENUMBER='" + getpHONENUMBER() + "'" +
            ", aPPROVEDON='" + getaPPROVEDON() + "'" +
            ", aPPROVED='" + getaPPROVED() + "'" +
            ", dECLINED='" + getdECLINED() + "'" +
            ", dECLINEDON='" + getdECLINEDON() + "'" +
            ", rEMARKS='" + getrEMARKS() + "'" +
            ", lINKEDBY='" + getlINKEDBY() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", lINKED='" + getlINKED() + "'" +
            ", aCTIVE='" + getaCTIVE() + "'" +
            ", dELINKEDBY='" + getdELINKEDBY() + "'" +
            ", dELINKEDON='" + getdELINKEDON() + "'" +
            ", dELINKED='" + getdELINKED() + "'" +
            ", aCCOUNTALIAS='" + getaCCOUNTALIAS() + "'" +
            ", sHORTCUTS='" + getsHORTCUTS() + "'" +
            "}";
    }
}
