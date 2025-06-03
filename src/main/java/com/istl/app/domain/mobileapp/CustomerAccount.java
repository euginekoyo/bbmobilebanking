package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A CustomerAccount.
 */
@Entity
@Table(name = "customer_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;

    @NotNull
    @Column(name = "customerid", nullable = false)
    private Long customerid;

    @NotNull
    @Size(max = 11)
    @Column(name = "accountnumber", length = 11, nullable = false)
    private String accountnumber;

    @Size(max = 10)
    @Column(name = "accountclass", length = 10)
    private String accountclass;

    @Size(max = 20)
    @Column(name = "customernumber", length = 20)
    private String customernumber;

    @NotNull
    @Size(max = 20)
    @Column(name = "cif", length = 20, nullable = false)
    private String cif;

    @Column(name = "timelinked")
    private Instant timelinked;

    @Column(name = "blocked")
    private Long blocked;

    @Column(name = "stopped")
    private Long stopped;

    @Column(name = "dormant")
    private Long dormant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomerAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerid() {
        return this.customerid;
    }

    public CustomerAccount customerid(Long customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getAccountnumber() {
        return this.accountnumber;
    }

    public CustomerAccount accountnumber(String accountnumber) {
        this.setAccountnumber(accountnumber);
        return this;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccountclass() {
        return this.accountclass;
    }

    public CustomerAccount accountclass(String accountclass) {
        this.setAccountclass(accountclass);
        return this;
    }

    public void setAccountclass(String accountclass) {
        this.accountclass = accountclass;
    }

    public String getCustomernumber() {
        return this.customernumber;
    }

    public CustomerAccount customernumber(String customernumber) {
        this.setCustomernumber(customernumber);
        return this;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    public String getCif() {
        return this.cif;
    }

    public CustomerAccount cif(String cif) {
        this.setCif(cif);
        return this;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Instant getTimelinked() {
        return this.timelinked;
    }

    public CustomerAccount timelinked(Instant timelinked) {
        this.setTimelinked(timelinked);
        return this;
    }

    public void setTimelinked(Instant timelinked) {
        this.timelinked = timelinked;
    }

    public Long getBlocked() {
        return this.blocked;
    }

    public CustomerAccount blocked(Long blocked) {
        this.setBlocked(blocked);
        return this;
    }

    public void setBlocked(Long blocked) {
        this.blocked = blocked;
    }

    public Long getStopped() {
        return this.stopped;
    }

    public CustomerAccount stopped(Long stopped) {
        this.setStopped(stopped);
        return this;
    }

    public void setStopped(Long stopped) {
        this.stopped = stopped;
    }

    public Long getDormant() {
        return this.dormant;
    }

    public CustomerAccount dormant(Long dormant) {
        this.setDormant(dormant);
        return this;
    }

    public void setDormant(Long dormant) {
        this.dormant = dormant;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerAccount)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomerAccount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerAccount{" +
            "id=" + getId() +
            ", customerid=" + getCustomerid() +
            ", accountnumber='" + getAccountnumber() + "'" +
            ", accountclass='" + getAccountclass() + "'" +
            ", customernumber='" + getCustomernumber() + "'" +
            ", cif='" + getCif() + "'" +
            ", timelinked='" + getTimelinked() + "'" +
            ", blocked=" + getBlocked() +
            ", stopped=" + getStopped() +
            ", dormant=" + getDormant() +
            "}";
    }
}
