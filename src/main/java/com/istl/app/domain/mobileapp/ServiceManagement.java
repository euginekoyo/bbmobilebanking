package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A ServiceManagement.
 */
@Entity
@Table(name = "service_management")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceManagement implements Serializable {

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

    @Size(max = 20)
    @Column(name = "processingcode", length = 20)
    private String processingcode;

    @Size(max = 20)
    @Column(name = "active", length = 20)
    private String active;

    @Size(max = 100)
    @Column(name = "createdby", length = 100)
    private String createdby;

    @Column(name = "datecreated")
    private Instant datecreated;

    @Column(name = "approved")
    private Long approved;

    @Size(max = 100)
    @Column(name = "approvedby", length = 100)
    private String approvedby;

    @Column(name = "approveddate")
    private Instant approveddate;

    @Size(max = 20)
    @Column(name = "adaptortype", length = 20)
    private String adaptortype;

    @Size(max = 20)
    @Column(name = "destination", length = 20)
    private String destination;

    @Column(name = "thirdpartyresponse")
    private Double thirdpartyresponse;

    @Size(max = 20)
    @Column(name = "telco", length = 20)
    private String telco;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "remarks")
    private String remarks;

    @Size(max = 100)
    @Column(name = "sessionid", length = 100)
    private String sessionid;

    @Size(max = 100)
    @Column(name = "reworkby", length = 100)
    private String reworkby;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ServiceManagement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessingcode() {
        return this.processingcode;
    }

    public ServiceManagement processingcode(String processingcode) {
        this.setProcessingcode(processingcode);
        return this;
    }

    public void setProcessingcode(String processingcode) {
        this.processingcode = processingcode;
    }

    public String getActive() {
        return this.active;
    }

    public ServiceManagement active(String active) {
        this.setActive(active);
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public ServiceManagement createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getDatecreated() {
        return this.datecreated;
    }

    public ServiceManagement datecreated(Instant datecreated) {
        this.setDatecreated(datecreated);
        return this;
    }

    public void setDatecreated(Instant datecreated) {
        this.datecreated = datecreated;
    }

    public Long getApproved() {
        return this.approved;
    }

    public ServiceManagement approved(Long approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public ServiceManagement approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Instant getApproveddate() {
        return this.approveddate;
    }

    public ServiceManagement approveddate(Instant approveddate) {
        this.setApproveddate(approveddate);
        return this;
    }

    public void setApproveddate(Instant approveddate) {
        this.approveddate = approveddate;
    }

    public String getAdaptortype() {
        return this.adaptortype;
    }

    public ServiceManagement adaptortype(String adaptortype) {
        this.setAdaptortype(adaptortype);
        return this;
    }

    public void setAdaptortype(String adaptortype) {
        this.adaptortype = adaptortype;
    }

    public String getDestination() {
        return this.destination;
    }

    public ServiceManagement destination(String destination) {
        this.setDestination(destination);
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getThirdpartyresponse() {
        return this.thirdpartyresponse;
    }

    public ServiceManagement thirdpartyresponse(Double thirdpartyresponse) {
        this.setThirdpartyresponse(thirdpartyresponse);
        return this;
    }

    public void setThirdpartyresponse(Double thirdpartyresponse) {
        this.thirdpartyresponse = thirdpartyresponse;
    }

    public String getTelco() {
        return this.telco;
    }

    public ServiceManagement telco(String telco) {
        this.setTelco(telco);
        return this;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getDescription() {
        return this.description;
    }

    public ServiceManagement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public ServiceManagement remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public ServiceManagement sessionid(String sessionid) {
        this.setSessionid(sessionid);
        return this;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getReworkby() {
        return this.reworkby;
    }

    public ServiceManagement reworkby(String reworkby) {
        this.setReworkby(reworkby);
        return this;
    }

    public void setReworkby(String reworkby) {
        this.reworkby = reworkby;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceManagement)) {
            return false;
        }
        return getId() != null && getId().equals(((ServiceManagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceManagement{" +
            "id=" + getId() +
            ", processingcode='" + getProcessingcode() + "'" +
            ", active='" + getActive() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", datecreated='" + getDatecreated() + "'" +
            ", approved=" + getApproved() +
            ", approvedby='" + getApprovedby() + "'" +
            ", approveddate='" + getApproveddate() + "'" +
            ", adaptortype='" + getAdaptortype() + "'" +
            ", destination='" + getDestination() + "'" +
            ", thirdpartyresponse=" + getThirdpartyresponse() +
            ", telco='" + getTelco() + "'" +
            ", description='" + getDescription() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", sessionid='" + getSessionid() + "'" +
            ", reworkby='" + getReworkby() + "'" +
            "}";
    }
}
