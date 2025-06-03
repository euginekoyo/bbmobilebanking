package com.istl.app.domain.mobileapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Range.
 */
@Entity
@Table(name = "range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Range implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;

    @Column(name = "rangefrom")
    private Long rangefrom;

    @Column(name = "rangeto")
    private Long rangeto;

    @Column(name = "amount")
    private Double amount;

    @Size(max = 50)
    @Column(name = "txntype", length = 50)
    private String txntype;

    @Size(max = 50)
    @Column(name = "txncode", length = 50)
    private String txncode;

    @Column(name = "chargeid")
    private Long chargeid;

    @Size(max = 50)
    @Column(name = "channel", length = 50)
    private String channel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "range")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "chargeRange", "range" }, allowSetters = true)
    private Set<Charge> chargeids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Range id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRangefrom() {
        return this.rangefrom;
    }

    public Range rangefrom(Long rangefrom) {
        this.setRangefrom(rangefrom);
        return this;
    }

    public void setRangefrom(Long rangefrom) {
        this.rangefrom = rangefrom;
    }

    public Long getRangeto() {
        return this.rangeto;
    }

    public Range rangeto(Long rangeto) {
        this.setRangeto(rangeto);
        return this;
    }

    public void setRangeto(Long rangeto) {
        this.rangeto = rangeto;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Range amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTxntype() {
        return this.txntype;
    }

    public Range txntype(String txntype) {
        this.setTxntype(txntype);
        return this;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public String getTxncode() {
        return this.txncode;
    }

    public Range txncode(String txncode) {
        this.setTxncode(txncode);
        return this;
    }

    public void setTxncode(String txncode) {
        this.txncode = txncode;
    }

    public Long getChargeid() {
        return this.chargeid;
    }

    public Range chargeid(Long chargeid) {
        this.setChargeid(chargeid);
        return this;
    }

    public void setChargeid(Long chargeid) {
        this.chargeid = chargeid;
    }

    public String getChannel() {
        return this.channel;
    }

    public Range channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Set<Charge> getChargeids() {
        return this.chargeids;
    }

    public void setChargeids(Set<Charge> charges) {
        if (this.chargeids != null) {
            this.chargeids.forEach(i -> i.setRange(null));
        }
        if (charges != null) {
            charges.forEach(i -> i.setRange(this));
        }
        this.chargeids = charges;
    }

    public Range chargeids(Set<Charge> charges) {
        this.setChargeids(charges);
        return this;
    }

    public Range addChargeid(Charge charge) {
        this.chargeids.add(charge);
        charge.setRange(this);
        return this;
    }

    public Range removeChargeid(Charge charge) {
        this.chargeids.remove(charge);
        charge.setRange(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Range)) {
            return false;
        }
        return getId() != null && getId().equals(((Range) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Range{" +
            "id=" + getId() +
            ", rangefrom=" + getRangefrom() +
            ", rangeto=" + getRangeto() +
            ", amount=" + getAmount() +
            ", txntype='" + getTxntype() + "'" +
            ", txncode='" + getTxncode() + "'" +
            ", chargeid=" + getChargeid() +
            ", channel='" + getChannel() + "'" +
            "}";
    }
}
