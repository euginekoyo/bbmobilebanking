package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MESSAGETEMPLATES.
 */
@Entity
@Table(name = "messagetemplates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MESSAGETEMPLATES implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "m_essagetype", length = 50)
    private String mESSAGETYPE;

    @Size(max = 200)
    @Column(name = "d_escription", length = 200)
    private String dESCRIPTION;

    @Size(max = 4000)
    @Column(name = "m_essageenglish", length = 4000)
    private String mESSAGEENGLISH;

    @Size(max = 4000)
    @Column(name = "m_essagesomali", length = 4000)
    private String mESSAGESOMALI;

    @Column(name = "c_reatedon")
    private Instant cREATEDON;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MESSAGETEMPLATES id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmESSAGETYPE() {
        return this.mESSAGETYPE;
    }

    public MESSAGETEMPLATES mESSAGETYPE(String mESSAGETYPE) {
        this.setmESSAGETYPE(mESSAGETYPE);
        return this;
    }

    public void setmESSAGETYPE(String mESSAGETYPE) {
        this.mESSAGETYPE = mESSAGETYPE;
    }

    public String getdESCRIPTION() {
        return this.dESCRIPTION;
    }

    public MESSAGETEMPLATES dESCRIPTION(String dESCRIPTION) {
        this.setdESCRIPTION(dESCRIPTION);
        return this;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getmESSAGEENGLISH() {
        return this.mESSAGEENGLISH;
    }

    public MESSAGETEMPLATES mESSAGEENGLISH(String mESSAGEENGLISH) {
        this.setmESSAGEENGLISH(mESSAGEENGLISH);
        return this;
    }

    public void setmESSAGEENGLISH(String mESSAGEENGLISH) {
        this.mESSAGEENGLISH = mESSAGEENGLISH;
    }

    public String getmESSAGESOMALI() {
        return this.mESSAGESOMALI;
    }

    public MESSAGETEMPLATES mESSAGESOMALI(String mESSAGESOMALI) {
        this.setmESSAGESOMALI(mESSAGESOMALI);
        return this;
    }

    public void setmESSAGESOMALI(String mESSAGESOMALI) {
        this.mESSAGESOMALI = mESSAGESOMALI;
    }

    public Instant getcREATEDON() {
        return this.cREATEDON;
    }

    public MESSAGETEMPLATES cREATEDON(Instant cREATEDON) {
        this.setcREATEDON(cREATEDON);
        return this;
    }

    public void setcREATEDON(Instant cREATEDON) {
        this.cREATEDON = cREATEDON;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MESSAGETEMPLATES)) {
            return false;
        }
        return getId() != null && getId().equals(((MESSAGETEMPLATES) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MESSAGETEMPLATES{" +
            "id=" + getId() +
            ", mESSAGETYPE='" + getmESSAGETYPE() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", mESSAGEENGLISH='" + getmESSAGEENGLISH() + "'" +
            ", mESSAGESOMALI='" + getmESSAGESOMALI() + "'" +
            ", cREATEDON='" + getcREATEDON() + "'" +
            "}";
    }
}
