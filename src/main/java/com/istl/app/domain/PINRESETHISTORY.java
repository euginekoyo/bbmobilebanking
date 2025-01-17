package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PINRESETHISTORY.
 */
@Entity
@Table(name = "pinresethistory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PINRESETHISTORY implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 20)
    @Column(name = "p_honenumber", length = 20)
    private String pHONENUMBER;

    @Size(max = 150)
    @Column(name = "c_ustomername", length = 150)
    private String cUSTOMERNAME;

    @Size(max = 50)
    @Column(name = "p_inblockedon", length = 50)
    private String pINBLOCKEDON;

    @Size(max = 200)
    @Column(name = "p_inblockremarks", length = 200)
    private String pINBLOCKREMARKS;

    @Size(max = 50)
    @Column(name = "p_inresetby", length = 50)
    private String pINRESETBY;

    @Size(max = 50)
    @Column(name = "p_inreseton", length = 50)
    private String pINRESETON;

    @Size(max = 50)
    @Column(name = "p_inresetapprovedby", length = 50)
    private String pINRESETAPPROVEDBY;

    @Size(max = 50)
    @Column(name = "p_inresetapprovedon", length = 50)
    private String pINRESETAPPROVEDON;

    @Size(max = 200)
    @Column(name = "p_inresetremarks", length = 200)
    private String pINRESETREMARKS;

    @Size(max = 20)
    @Column(name = "b_ranchcode", length = 20)
    private String bRANCHCODE;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PINRESETHISTORY id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getpHONENUMBER() {
        return this.pHONENUMBER;
    }

    public PINRESETHISTORY pHONENUMBER(String pHONENUMBER) {
        this.setpHONENUMBER(pHONENUMBER);
        return this;
    }

    public void setpHONENUMBER(String pHONENUMBER) {
        this.pHONENUMBER = pHONENUMBER;
    }

    public String getcUSTOMERNAME() {
        return this.cUSTOMERNAME;
    }

    public PINRESETHISTORY cUSTOMERNAME(String cUSTOMERNAME) {
        this.setcUSTOMERNAME(cUSTOMERNAME);
        return this;
    }

    public void setcUSTOMERNAME(String cUSTOMERNAME) {
        this.cUSTOMERNAME = cUSTOMERNAME;
    }

    public String getpINBLOCKEDON() {
        return this.pINBLOCKEDON;
    }

    public PINRESETHISTORY pINBLOCKEDON(String pINBLOCKEDON) {
        this.setpINBLOCKEDON(pINBLOCKEDON);
        return this;
    }

    public void setpINBLOCKEDON(String pINBLOCKEDON) {
        this.pINBLOCKEDON = pINBLOCKEDON;
    }

    public String getpINBLOCKREMARKS() {
        return this.pINBLOCKREMARKS;
    }

    public PINRESETHISTORY pINBLOCKREMARKS(String pINBLOCKREMARKS) {
        this.setpINBLOCKREMARKS(pINBLOCKREMARKS);
        return this;
    }

    public void setpINBLOCKREMARKS(String pINBLOCKREMARKS) {
        this.pINBLOCKREMARKS = pINBLOCKREMARKS;
    }

    public String getpINRESETBY() {
        return this.pINRESETBY;
    }

    public PINRESETHISTORY pINRESETBY(String pINRESETBY) {
        this.setpINRESETBY(pINRESETBY);
        return this;
    }

    public void setpINRESETBY(String pINRESETBY) {
        this.pINRESETBY = pINRESETBY;
    }

    public String getpINRESETON() {
        return this.pINRESETON;
    }

    public PINRESETHISTORY pINRESETON(String pINRESETON) {
        this.setpINRESETON(pINRESETON);
        return this;
    }

    public void setpINRESETON(String pINRESETON) {
        this.pINRESETON = pINRESETON;
    }

    public String getpINRESETAPPROVEDBY() {
        return this.pINRESETAPPROVEDBY;
    }

    public PINRESETHISTORY pINRESETAPPROVEDBY(String pINRESETAPPROVEDBY) {
        this.setpINRESETAPPROVEDBY(pINRESETAPPROVEDBY);
        return this;
    }

    public void setpINRESETAPPROVEDBY(String pINRESETAPPROVEDBY) {
        this.pINRESETAPPROVEDBY = pINRESETAPPROVEDBY;
    }

    public String getpINRESETAPPROVEDON() {
        return this.pINRESETAPPROVEDON;
    }

    public PINRESETHISTORY pINRESETAPPROVEDON(String pINRESETAPPROVEDON) {
        this.setpINRESETAPPROVEDON(pINRESETAPPROVEDON);
        return this;
    }

    public void setpINRESETAPPROVEDON(String pINRESETAPPROVEDON) {
        this.pINRESETAPPROVEDON = pINRESETAPPROVEDON;
    }

    public String getpINRESETREMARKS() {
        return this.pINRESETREMARKS;
    }

    public PINRESETHISTORY pINRESETREMARKS(String pINRESETREMARKS) {
        this.setpINRESETREMARKS(pINRESETREMARKS);
        return this;
    }

    public void setpINRESETREMARKS(String pINRESETREMARKS) {
        this.pINRESETREMARKS = pINRESETREMARKS;
    }

    public String getbRANCHCODE() {
        return this.bRANCHCODE;
    }

    public PINRESETHISTORY bRANCHCODE(String bRANCHCODE) {
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
        if (!(o instanceof PINRESETHISTORY)) {
            return false;
        }
        return getId() != null && getId().equals(((PINRESETHISTORY) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PINRESETHISTORY{" +
            "id=" + getId() +
            ", pHONENUMBER='" + getpHONENUMBER() + "'" +
            ", cUSTOMERNAME='" + getcUSTOMERNAME() + "'" +
            ", pINBLOCKEDON='" + getpINBLOCKEDON() + "'" +
            ", pINBLOCKREMARKS='" + getpINBLOCKREMARKS() + "'" +
            ", pINRESETBY='" + getpINRESETBY() + "'" +
            ", pINRESETON='" + getpINRESETON() + "'" +
            ", pINRESETAPPROVEDBY='" + getpINRESETAPPROVEDBY() + "'" +
            ", pINRESETAPPROVEDON='" + getpINRESETAPPROVEDON() + "'" +
            ", pINRESETREMARKS='" + getpINRESETREMARKS() + "'" +
            ", bRANCHCODE='" + getbRANCHCODE() + "'" +
            "}";
    }
}
