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
 * A RANGE.
 */
@Entity
@Table(name = "range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RANGE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "r_angefrom")
    private Long rANGEFROM;

    @Column(name = "r_angeto")
    private Long rANGETO;

    @Column(name = "a_mount")
    private Double aMOUNT;

    @Size(max = 50)
    @Column(name = "t_xntype", length = 50)
    private String tXNTYPE;

    @Size(max = 50)
    @Column(name = "t_xncode", length = 50)
    private String tXNCODE;

    @Column(name = "c_hargeid")
    private Long cHARGEID;

    @Size(max = 50)
    @Column(name = "c_hannel", length = 50)
    private String cHANNEL;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rANGE")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cHARGERANGES", "rANGE" }, allowSetters = true)
    private Set<CHARGE> cHARGEIDS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RANGE id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrANGEFROM() {
        return this.rANGEFROM;
    }

    public RANGE rANGEFROM(Long rANGEFROM) {
        this.setrANGEFROM(rANGEFROM);
        return this;
    }

    public void setrANGEFROM(Long rANGEFROM) {
        this.rANGEFROM = rANGEFROM;
    }

    public Long getrANGETO() {
        return this.rANGETO;
    }

    public RANGE rANGETO(Long rANGETO) {
        this.setrANGETO(rANGETO);
        return this;
    }

    public void setrANGETO(Long rANGETO) {
        this.rANGETO = rANGETO;
    }

    public Double getaMOUNT() {
        return this.aMOUNT;
    }

    public RANGE aMOUNT(Double aMOUNT) {
        this.setaMOUNT(aMOUNT);
        return this;
    }

    public void setaMOUNT(Double aMOUNT) {
        this.aMOUNT = aMOUNT;
    }

    public String gettXNTYPE() {
        return this.tXNTYPE;
    }

    public RANGE tXNTYPE(String tXNTYPE) {
        this.settXNTYPE(tXNTYPE);
        return this;
    }

    public void settXNTYPE(String tXNTYPE) {
        this.tXNTYPE = tXNTYPE;
    }

    public String gettXNCODE() {
        return this.tXNCODE;
    }

    public RANGE tXNCODE(String tXNCODE) {
        this.settXNCODE(tXNCODE);
        return this;
    }

    public void settXNCODE(String tXNCODE) {
        this.tXNCODE = tXNCODE;
    }

    public Long getcHARGEID() {
        return this.cHARGEID;
    }

    public RANGE cHARGEID(Long cHARGEID) {
        this.setcHARGEID(cHARGEID);
        return this;
    }

    public void setcHARGEID(Long cHARGEID) {
        this.cHARGEID = cHARGEID;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public RANGE cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public Set<CHARGE> getCHARGEIDS() {
        return this.cHARGEIDS;
    }

    public void setCHARGEIDS(Set<CHARGE> cHARGES) {
        if (this.cHARGEIDS != null) {
            this.cHARGEIDS.forEach(i -> i.setRANGE(null));
        }
        if (cHARGES != null) {
            cHARGES.forEach(i -> i.setRANGE(this));
        }
        this.cHARGEIDS = cHARGES;
    }

    public RANGE cHARGEIDS(Set<CHARGE> cHARGES) {
        this.setCHARGEIDS(cHARGES);
        return this;
    }

    public RANGE addCHARGEID(CHARGE cHARGE) {
        this.cHARGEIDS.add(cHARGE);
        cHARGE.setRANGE(this);
        return this;
    }

    public RANGE removeCHARGEID(CHARGE cHARGE) {
        this.cHARGEIDS.remove(cHARGE);
        cHARGE.setRANGE(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RANGE)) {
            return false;
        }
        return getId() != null && getId().equals(((RANGE) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RANGE{" +
            "id=" + getId() +
            ", rANGEFROM=" + getrANGEFROM() +
            ", rANGETO=" + getrANGETO() +
            ", aMOUNT=" + getaMOUNT() +
            ", tXNTYPE='" + gettXNTYPE() + "'" +
            ", tXNCODE='" + gettXNCODE() + "'" +
            ", cHARGEID=" + getcHARGEID() +
            ", cHANNEL='" + getcHANNEL() + "'" +
            "}";
    }
}
