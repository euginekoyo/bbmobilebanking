package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Billers.
 */
@Entity
@Table(name = "billers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Billers implements Serializable {

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
    @Size(max = 50)
    @Column(name = "billerid", length = 50, nullable = false)
    private String billerid;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Size(max = 20)
    @Column(name = "billercollectionaccount", length = 20)
    private String billercollectionaccount;

    @Column(name = "datecreated")
    private Instant datecreated;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Column(name = "approved")
    private Long approved;

    @Size(max = 50)
    @Column(name = "approvedby", length = 50)
    private String approvedby;

    @Column(name = "approveddate")
    private Instant approveddate;

    @Size(max = 25)
    @Column(name = "chargableproductid", length = 25)
    private String chargableproductid;

    @Size(max = 25)
    @Column(name = "nonchargableproductid", length = 25)
    private String nonchargableproductid;

    @Size(max = 20)
    @Column(name = "usdbillercollectionaccount", length = 20)
    private String usdbillercollectionaccount;

    @Column(name = "enableduplicatecheck")
    private Long enableduplicatecheck;

    @Size(max = 250)
    @Column(name = "remarks", length = 250)
    private String remarks;

    @Size(max = 50)
    @Column(name = "sessionid", length = 50)
    private String sessionid;

    @Size(max = 50)
    @Column(name = "reworkby", length = 50)
    private String reworkby;

    @Column(name = "status")
    private Long status;

    @Column(name = "active")
    private Long active;

    @Column(name = "rework")
    private Long rework;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Billers id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillerid() {
        return this.billerid;
    }

    public Billers billerid(String billerid) {
        this.setBillerid(billerid);
        return this;
    }

    public void setBillerid(String billerid) {
        this.billerid = billerid;
    }

    public String getDescription() {
        return this.description;
    }

    public Billers description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillercollectionaccount() {
        return this.billercollectionaccount;
    }

    public Billers billercollectionaccount(String billercollectionaccount) {
        this.setBillercollectionaccount(billercollectionaccount);
        return this;
    }

    public void setBillercollectionaccount(String billercollectionaccount) {
        this.billercollectionaccount = billercollectionaccount;
    }

    public Instant getDatecreated() {
        return this.datecreated;
    }

    public Billers datecreated(Instant datecreated) {
        this.setDatecreated(datecreated);
        return this;
    }

    public void setDatecreated(Instant datecreated) {
        this.datecreated = datecreated;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public Billers createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Long getApproved() {
        return this.approved;
    }

    public Billers approved(Long approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public Billers approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Instant getApproveddate() {
        return this.approveddate;
    }

    public Billers approveddate(Instant approveddate) {
        this.setApproveddate(approveddate);
        return this;
    }

    public void setApproveddate(Instant approveddate) {
        this.approveddate = approveddate;
    }

    public String getChargableproductid() {
        return this.chargableproductid;
    }

    public Billers chargableproductid(String chargableproductid) {
        this.setChargableproductid(chargableproductid);
        return this;
    }

    public void setChargableproductid(String chargableproductid) {
        this.chargableproductid = chargableproductid;
    }

    public String getNonchargableproductid() {
        return this.nonchargableproductid;
    }

    public Billers nonchargableproductid(String nonchargableproductid) {
        this.setNonchargableproductid(nonchargableproductid);
        return this;
    }

    public void setNonchargableproductid(String nonchargableproductid) {
        this.nonchargableproductid = nonchargableproductid;
    }

    public String getUsdbillercollectionaccount() {
        return this.usdbillercollectionaccount;
    }

    public Billers usdbillercollectionaccount(String usdbillercollectionaccount) {
        this.setUsdbillercollectionaccount(usdbillercollectionaccount);
        return this;
    }

    public void setUsdbillercollectionaccount(String usdbillercollectionaccount) {
        this.usdbillercollectionaccount = usdbillercollectionaccount;
    }

    public Long getEnableduplicatecheck() {
        return this.enableduplicatecheck;
    }

    public Billers enableduplicatecheck(Long enableduplicatecheck) {
        this.setEnableduplicatecheck(enableduplicatecheck);
        return this;
    }

    public void setEnableduplicatecheck(Long enableduplicatecheck) {
        this.enableduplicatecheck = enableduplicatecheck;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Billers remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public Billers sessionid(String sessionid) {
        this.setSessionid(sessionid);
        return this;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getReworkby() {
        return this.reworkby;
    }

    public Billers reworkby(String reworkby) {
        this.setReworkby(reworkby);
        return this;
    }

    public void setReworkby(String reworkby) {
        this.reworkby = reworkby;
    }

    public Long getStatus() {
        return this.status;
    }

    public Billers status(Long status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getActive() {
        return this.active;
    }

    public Billers active(Long active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getRework() {
        return this.rework;
    }

    public Billers rework(Long rework) {
        this.setRework(rework);
        return this;
    }

    public void setRework(Long rework) {
        this.rework = rework;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Billers)) {
            return false;
        }
        return getId() != null && getId().equals(((Billers) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Billers{" +
            "id=" + getId() +
            ", billerid='" + getBillerid() + "'" +
            ", description='" + getDescription() + "'" +
            ", billercollectionaccount='" + getBillercollectionaccount() + "'" +
            ", datecreated='" + getDatecreated() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", approved=" + getApproved() +
            ", approvedby='" + getApprovedby() + "'" +
            ", approveddate='" + getApproveddate() + "'" +
            ", chargableproductid='" + getChargableproductid() + "'" +
            ", nonchargableproductid='" + getNonchargableproductid() + "'" +
            ", usdbillercollectionaccount='" + getUsdbillercollectionaccount() + "'" +
            ", enableduplicatecheck=" + getEnableduplicatecheck() +
            ", remarks='" + getRemarks() + "'" +
            ", sessionid='" + getSessionid() + "'" +
            ", reworkby='" + getReworkby() + "'" +
            ", status=" + getStatus() +
            ", active=" + getActive() +
            ", rework=" + getRework() +
            "}";
    }
}
