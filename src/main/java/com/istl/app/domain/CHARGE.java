package com.istl.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Charge.
 */
@Entity
@Table(name = "charge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Charge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "txntype", length = 150, nullable = false)
    private String txntype;

    @Column(name = "feemode")
    private Long feemode;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "datecreated")
    private Instant datecreated;

    @Size(max = 150)
    @Column(name = "createdby", length = 150)
    private String createdby;

    @Size(max = 150)
    @Column(name = "approved", length = 150)
    private String approved;

    @Size(max = 150)
    @Column(name = "approvedby", length = 150)
    private String approvedby;

    @Size(max = 150)
    @Column(name = "channel", length = 150)
    private String channel;

    @Column(name = "txncode")
    private Long txncode;

    @Size(max = 64)
    @Column(name = "description", length = 64)
    private String description;

    @Column(name = "approveddate")
    private Instant approveddate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "chargeids" }, allowSetters = true)
    private ChargeRange chargeRange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "chargeids" }, allowSetters = true)
    private Range range;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Charge id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxntype() {
        return this.txntype;
    }

    public Charge txntype(String txntype) {
        this.setTxntype(txntype);
        return this;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public Long getFeemode() {
        return this.feemode;
    }

    public Charge feemode(Long feemode) {
        this.setFeemode(feemode);
        return this;
    }

    public void setFeemode(Long feemode) {
        this.feemode = feemode;
    }

    public Long getAmount() {
        return this.amount;
    }

    public Charge amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Instant getDatecreated() {
        return this.datecreated;
    }

    public Charge datecreated(Instant datecreated) {
        this.setDatecreated(datecreated);
        return this;
    }

    public void setDatecreated(Instant datecreated) {
        this.datecreated = datecreated;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public Charge createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getApproved() {
        return this.approved;
    }

    public Charge approved(String approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public Charge approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getChannel() {
        return this.channel;
    }

    public Charge channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getTxncode() {
        return this.txncode;
    }

    public Charge txncode(Long txncode) {
        this.setTxncode(txncode);
        return this;
    }

    public void setTxncode(Long txncode) {
        this.txncode = txncode;
    }

    public String getDescription() {
        return this.description;
    }

    public Charge description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getApproveddate() {
        return this.approveddate;
    }

    public Charge approveddate(Instant approveddate) {
        this.setApproveddate(approveddate);
        return this;
    }

    public void setApproveddate(Instant approveddate) {
        this.approveddate = approveddate;
    }

    public ChargeRange getChargeRange() {
        return this.chargeRange;
    }

    public void setChargeRange(ChargeRange chargeRange) {
        this.chargeRange = chargeRange;
    }

    public Charge chargeRange(ChargeRange chargeRange) {
        this.setChargeRange(chargeRange);
        return this;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Charge range(Range range) {
        this.setRange(range);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Charge)) {
            return false;
        }
        return getId() != null && getId().equals(((Charge) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Charge{" +
            "id=" + getId() +
            ", txntype='" + getTxntype() + "'" +
            ", feemode=" + getFeemode() +
            ", amount=" + getAmount() +
            ", datecreated='" + getDatecreated() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", approved='" + getApproved() + "'" +
            ", approvedby='" + getApprovedby() + "'" +
            ", channel='" + getChannel() + "'" +
            ", txncode=" + getTxncode() +
            ", description='" + getDescription() + "'" +
            ", approveddate='" + getApproveddate() + "'" +
            "}";
    }
}
