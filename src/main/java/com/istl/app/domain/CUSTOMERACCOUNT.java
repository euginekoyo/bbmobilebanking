package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CUSTOMERACCOUNT.
 */
@Entity
@Table(name = "customeraccount")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CUSTOMERACCOUNT implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "c_ustomerid", nullable = false)
    private Long cUSTOMERID;

    @NotNull
    @Size(max = 11)
    @Column(name = "a_ccountnumber", length = 11, nullable = false)
    private String aCCOUNTNUMBER;

    @Size(max = 10)
    @Column(name = "a_ccountclass", length = 10)
    private String aCCOUNTCLASS;

    @Size(max = 20)
    @Column(name = "c_ustomernumber", length = 20)
    private String cUSTOMERNUMBER;

    @NotNull
    @Size(max = 20)
    @Column(name = "c_if", length = 20, nullable = false)
    private String cIF;

    @Column(name = "t_imelinked")
    private Instant tIMELINKED;

    @Column(name = "b_locked")
    private Long bLOCKED;

    @Column(name = "s_topped")
    private Long sTOPPED;

    @Column(name = "d_ormant")
    private Long dORMANT;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CUSTOMERACCOUNT id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcUSTOMERID() {
        return this.cUSTOMERID;
    }

    public CUSTOMERACCOUNT cUSTOMERID(Long cUSTOMERID) {
        this.setcUSTOMERID(cUSTOMERID);
        return this;
    }

    public void setcUSTOMERID(Long cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
    }

    public String getaCCOUNTNUMBER() {
        return this.aCCOUNTNUMBER;
    }

    public CUSTOMERACCOUNT aCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.setaCCOUNTNUMBER(aCCOUNTNUMBER);
        return this;
    }

    public void setaCCOUNTNUMBER(String aCCOUNTNUMBER) {
        this.aCCOUNTNUMBER = aCCOUNTNUMBER;
    }

    public String getaCCOUNTCLASS() {
        return this.aCCOUNTCLASS;
    }

    public CUSTOMERACCOUNT aCCOUNTCLASS(String aCCOUNTCLASS) {
        this.setaCCOUNTCLASS(aCCOUNTCLASS);
        return this;
    }

    public void setaCCOUNTCLASS(String aCCOUNTCLASS) {
        this.aCCOUNTCLASS = aCCOUNTCLASS;
    }

    public String getcUSTOMERNUMBER() {
        return this.cUSTOMERNUMBER;
    }

    public CUSTOMERACCOUNT cUSTOMERNUMBER(String cUSTOMERNUMBER) {
        this.setcUSTOMERNUMBER(cUSTOMERNUMBER);
        return this;
    }

    public void setcUSTOMERNUMBER(String cUSTOMERNUMBER) {
        this.cUSTOMERNUMBER = cUSTOMERNUMBER;
    }

    public String getcIF() {
        return this.cIF;
    }

    public CUSTOMERACCOUNT cIF(String cIF) {
        this.setcIF(cIF);
        return this;
    }

    public void setcIF(String cIF) {
        this.cIF = cIF;
    }

    public Instant gettIMELINKED() {
        return this.tIMELINKED;
    }

    public CUSTOMERACCOUNT tIMELINKED(Instant tIMELINKED) {
        this.settIMELINKED(tIMELINKED);
        return this;
    }

    public void settIMELINKED(Instant tIMELINKED) {
        this.tIMELINKED = tIMELINKED;
    }

    public Long getbLOCKED() {
        return this.bLOCKED;
    }

    public CUSTOMERACCOUNT bLOCKED(Long bLOCKED) {
        this.setbLOCKED(bLOCKED);
        return this;
    }

    public void setbLOCKED(Long bLOCKED) {
        this.bLOCKED = bLOCKED;
    }

    public Long getsTOPPED() {
        return this.sTOPPED;
    }

    public CUSTOMERACCOUNT sTOPPED(Long sTOPPED) {
        this.setsTOPPED(sTOPPED);
        return this;
    }

    public void setsTOPPED(Long sTOPPED) {
        this.sTOPPED = sTOPPED;
    }

    public Long getdORMANT() {
        return this.dORMANT;
    }

    public CUSTOMERACCOUNT dORMANT(Long dORMANT) {
        this.setdORMANT(dORMANT);
        return this;
    }

    public void setdORMANT(Long dORMANT) {
        this.dORMANT = dORMANT;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CUSTOMERACCOUNT)) {
            return false;
        }
        return getId() != null && getId().equals(((CUSTOMERACCOUNT) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CUSTOMERACCOUNT{" +
            "id=" + getId() +
            ", cUSTOMERID=" + getcUSTOMERID() +
            ", aCCOUNTNUMBER='" + getaCCOUNTNUMBER() + "'" +
            ", aCCOUNTCLASS='" + getaCCOUNTCLASS() + "'" +
            ", cUSTOMERNUMBER='" + getcUSTOMERNUMBER() + "'" +
            ", cIF='" + getcIF() + "'" +
            ", tIMELINKED='" + gettIMELINKED() + "'" +
            ", bLOCKED=" + getbLOCKED() +
            ", sTOPPED=" + getsTOPPED() +
            ", dORMANT=" + getdORMANT() +
            "}";
    }
}
