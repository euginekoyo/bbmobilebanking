package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Limits.
 */
@Entity
@Table(name = "limits")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Limits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "transactiontype", length = 50, nullable = false)
    private String transactiontype;

    @Size(max = 6)
    @Column(name = "procode", length = 6)
    private String procode;

    @Size(max = 30)
    @Column(name = "channel", length = 30)
    private String channel;

    @NotNull
    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "transactionlimit", nullable = false)
    private Long transactionlimit;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "dailylimit")
    private Long dailylimit;

    @Size(max = 50)
    @Column(name = "registeredby", length = 50)
    private String registeredby;

    @Size(max = 7)
    @Column(name = "registereddate", length = 7)
    private String registereddate;

    @Size(max = 2)
    @Column(name = "approved", length = 2)
    private String approved;

    @Size(max = 50)
    @Column(name = "approvedby", length = 50)
    private String approvedby;

    @Size(max = 7)
    @Column(name = "approveddate", length = 7)
    private String approveddate;

    @Size(max = 50)
    @Column(name = "updatedby", length = 50)
    private String updatedby;

    @Size(max = 7)
    @Column(name = "updateddate", length = 7)
    private String updateddate;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "rework")
    private Long rework;

    @Size(max = 50)
    @Column(name = "reworkby", length = 50)
    private String reworkby;

    @Size(max = 50)
    @Column(name = "sessionid", length = 50)
    private String sessionid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Limits id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactiontype() {
        return this.transactiontype;
    }

    public Limits transactiontype(String transactiontype) {
        this.setTransactiontype(transactiontype);
        return this;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getProcode() {
        return this.procode;
    }

    public Limits procode(String procode) {
        this.setProcode(procode);
        return this;
    }

    public void setProcode(String procode) {
        this.procode = procode;
    }

    public String getChannel() {
        return this.channel;
    }

    public Limits channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getTransactionlimit() {
        return this.transactionlimit;
    }

    public Limits transactionlimit(Long transactionlimit) {
        this.setTransactionlimit(transactionlimit);
        return this;
    }

    public void setTransactionlimit(Long transactionlimit) {
        this.transactionlimit = transactionlimit;
    }

    public Long getDailylimit() {
        return this.dailylimit;
    }

    public Limits dailylimit(Long dailylimit) {
        this.setDailylimit(dailylimit);
        return this;
    }

    public void setDailylimit(Long dailylimit) {
        this.dailylimit = dailylimit;
    }

    public String getRegisteredby() {
        return this.registeredby;
    }

    public Limits registeredby(String registeredby) {
        this.setRegisteredby(registeredby);
        return this;
    }

    public void setRegisteredby(String registeredby) {
        this.registeredby = registeredby;
    }

    public String getRegistereddate() {
        return this.registereddate;
    }

    public Limits registereddate(String registereddate) {
        this.setRegistereddate(registereddate);
        return this;
    }

    public void setRegistereddate(String registereddate) {
        this.registereddate = registereddate;
    }

    public String getApproved() {
        return this.approved;
    }

    public Limits approved(String approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public Limits approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getApproveddate() {
        return this.approveddate;
    }

    public Limits approveddate(String approveddate) {
        this.setApproveddate(approveddate);
        return this;
    }

    public void setApproveddate(String approveddate) {
        this.approveddate = approveddate;
    }

    public String getUpdatedby() {
        return this.updatedby;
    }

    public Limits updatedby(String updatedby) {
        this.setUpdatedby(updatedby);
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getUpdateddate() {
        return this.updateddate;
    }

    public Limits updateddate(String updateddate) {
        this.setUpdateddate(updateddate);
        return this;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public Long getRework() {
        return this.rework;
    }

    public Limits rework(Long rework) {
        this.setRework(rework);
        return this;
    }

    public void setRework(Long rework) {
        this.rework = rework;
    }

    public String getReworkby() {
        return this.reworkby;
    }

    public Limits reworkby(String reworkby) {
        this.setReworkby(reworkby);
        return this;
    }

    public void setReworkby(String reworkby) {
        this.reworkby = reworkby;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public Limits sessionid(String sessionid) {
        this.setSessionid(sessionid);
        return this;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Limits)) {
            return false;
        }
        return getId() != null && getId().equals(((Limits) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Limits{" +
            "id=" + getId() +
            ", transactiontype='" + getTransactiontype() + "'" +
            ", procode='" + getProcode() + "'" +
            ", channel='" + getChannel() + "'" +
            ", transactionlimit=" + getTransactionlimit() +
            ", dailylimit=" + getDailylimit() +
            ", registeredby='" + getRegisteredby() + "'" +
            ", registereddate='" + getRegistereddate() + "'" +
            ", approved='" + getApproved() + "'" +
            ", approvedby='" + getApprovedby() + "'" +
            ", approveddate='" + getApproveddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            ", rework=" + getRework() +
            ", reworkby='" + getReworkby() + "'" +
            ", sessionid='" + getSessionid() + "'" +
            "}";
    }
}
