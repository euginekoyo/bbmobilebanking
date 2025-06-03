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
 * A ChargeRange.
 */
@Entity
@Table(name = "charge_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChargeRange implements Serializable {

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

    @NotNull
    @Size(max = 20)
    @Column(name = "billerid", length = 20, nullable = false)
    private String billerid;

    @NotNull
    @Size(max = 20)
    @Column(name = "processingcode", length = 20, nullable = false)
    private String processingcode;

    @NotNull
    @Column(name = "max", nullable = false)
    private Long max;

    @NotNull
    @Column(name = "min", nullable = false)
    private Long min;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Size(max = 50)
    @Column(name = "approvedby", length = 50)
    private String approvedby;

    @Size(max = 30)
    @Column(name = "createdat", length = 30)
    private String createdat;

    @Size(max = 20)
    @Column(name = "approvedon", length = 20)
    private String approvedon;

    @Column(name = "approved")
    private Long approved;

    @Column(name = "declined")
    private Long declined;

    @Size(max = 50)
    @Column(name = "declinedby", length = 50)
    private String declinedby;

    @NotNull
    @Column(name = "chargeid", nullable = false)
    private Long chargeid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chargeRange")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "chargeRange", "range" }, allowSetters = true)
    private Set<Charge> chargeids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChargeRange id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillerid() {
        return this.billerid;
    }

    public ChargeRange billerid(String billerid) {
        this.setBillerid(billerid);
        return this;
    }

    public void setBillerid(String billerid) {
        this.billerid = billerid;
    }

    public String getProcessingcode() {
        return this.processingcode;
    }

    public ChargeRange processingcode(String processingcode) {
        this.setProcessingcode(processingcode);
        return this;
    }

    public void setProcessingcode(String processingcode) {
        this.processingcode = processingcode;
    }

    public Long getMax() {
        return this.max;
    }

    public ChargeRange max(Long max) {
        this.setMax(max);
        return this;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return this.min;
    }

    public ChargeRange min(Long min) {
        this.setMin(min);
        return this;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getAmount() {
        return this.amount;
    }

    public ChargeRange amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public ChargeRange createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public ChargeRange approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getCreatedat() {
        return this.createdat;
    }

    public ChargeRange createdat(String createdat) {
        this.setCreatedat(createdat);
        return this;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getApprovedon() {
        return this.approvedon;
    }

    public ChargeRange approvedon(String approvedon) {
        this.setApprovedon(approvedon);
        return this;
    }

    public void setApprovedon(String approvedon) {
        this.approvedon = approvedon;
    }

    public Long getApproved() {
        return this.approved;
    }

    public ChargeRange approved(Long approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public Long getDeclined() {
        return this.declined;
    }

    public ChargeRange declined(Long declined) {
        this.setDeclined(declined);
        return this;
    }

    public void setDeclined(Long declined) {
        this.declined = declined;
    }

    public String getDeclinedby() {
        return this.declinedby;
    }

    public ChargeRange declinedby(String declinedby) {
        this.setDeclinedby(declinedby);
        return this;
    }

    public void setDeclinedby(String declinedby) {
        this.declinedby = declinedby;
    }

    public Long getChargeid() {
        return this.chargeid;
    }

    public ChargeRange chargeid(Long chargeid) {
        this.setChargeid(chargeid);
        return this;
    }

    public void setChargeid(Long chargeid) {
        this.chargeid = chargeid;
    }

    public Set<Charge> getChargeids() {
        return this.chargeids;
    }

    public void setChargeids(Set<Charge> charges) {
        if (this.chargeids != null) {
            this.chargeids.forEach(i -> i.setChargeRange(null));
        }
        if (charges != null) {
            charges.forEach(i -> i.setChargeRange(this));
        }
        this.chargeids = charges;
    }

    public ChargeRange chargeids(Set<Charge> charges) {
        this.setChargeids(charges);
        return this;
    }

    public ChargeRange addChargeid(Charge charge) {
        this.chargeids.add(charge);
        charge.setChargeRange(this);
        return this;
    }

    public ChargeRange removeChargeid(Charge charge) {
        this.chargeids.remove(charge);
        charge.setChargeRange(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChargeRange)) {
            return false;
        }
        return getId() != null && getId().equals(((ChargeRange) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChargeRange{" +
            "id=" + getId() +
            ", billerid='" + getBillerid() + "'" +
            ", processingcode='" + getProcessingcode() + "'" +
            ", max=" + getMax() +
            ", min=" + getMin() +
            ", amount=" + getAmount() +
            ", createdby='" + getCreatedby() + "'" +
            ", approvedby='" + getApprovedby() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", approvedon='" + getApprovedon() + "'" +
            ", approved=" + getApproved() +
            ", declined=" + getDeclined() +
            ", declinedby='" + getDeclinedby() + "'" +
            ", chargeid=" + getChargeid() +
            "}";
    }
}
