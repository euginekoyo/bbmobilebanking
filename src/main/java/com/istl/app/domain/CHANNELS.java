package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CHANNELS.
 */
@Entity
@Table(name = "channels")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CHANNELS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 15)
    @Column(name = "c_hannel", length = 15)
    private String cHANNEL;

    @Size(max = 50)
    @Column(name = "d_escription", length = 50)
    private String dESCRIPTION;

    @Size(max = 6)
    @Column(name = "b_in", length = 6)
    private String bIN;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CHANNELS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public CHANNELS cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public String getdESCRIPTION() {
        return this.dESCRIPTION;
    }

    public CHANNELS dESCRIPTION(String dESCRIPTION) {
        this.setdESCRIPTION(dESCRIPTION);
        return this;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getbIN() {
        return this.bIN;
    }

    public CHANNELS bIN(String bIN) {
        this.setbIN(bIN);
        return this;
    }

    public void setbIN(String bIN) {
        this.bIN = bIN;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CHANNELS)) {
            return false;
        }
        return getId() != null && getId().equals(((CHANNELS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CHANNELS{" +
            "id=" + getId() +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", bIN='" + getbIN() + "'" +
            "}";
    }
}
