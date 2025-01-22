package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Branches.
 */
@Entity
@Table(name = "branches")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Branches implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 4000)
    @Column(name = "branchname", length = 4000)
    private String branchname;

    @NotNull
    @Size(max = 3)
    @Column(name = "branchcode", length = 3, nullable = false)
    private String branchcode;

    @Column(name = "approved")
    private Long approved;

    @Size(max = 4000)
    @Column(name = "email", length = 4000)
    private String email;

    @Size(max = 4000)
    @Column(name = "address", length = 4000)
    private String address;

    @Size(max = 12)
    @Column(name = "phone", length = 12)
    private String phone;

    @NotNull
    @Size(max = 4000)
    @Column(name = "location", length = 4000, nullable = false)
    private String location;

    @Size(max = 4000)
    @Column(name = "contactperson", length = 4000)
    private String contactperson;

    @Size(max = 2000)
    @Column(name = "remarks", length = 2000)
    private String remarks;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createdon")
    private Instant createdon;

    @Size(max = 20)
    @Column(name = "approvedby", length = 20)
    private String approvedby;

    @Size(max = 7)
    @Column(name = "approvedon", length = 7)
    private String approvedon;

    @Size(max = 200)
    @Column(name = "checkerremarks", length = 200)
    private String checkerremarks;

    @Size(max = 20)
    @Column(name = "deletedby", length = 20)
    private String deletedby;

    @Column(name = "deletedon")
    private Instant deletedon;

    @Size(max = 200)
    @Column(name = "deleteremarks", length = 200)
    private String deleteremarks;

    @Column(name = "deleted")
    private Long deleted;

    @Column(name = "declined")
    private Long declined;

    @Size(max = 7)
    @Column(name = "declineddon", length = 7)
    private String declineddon;

    @Size(max = 20)
    @Column(name = "declinedby", length = 20)
    private String declinedby;

    @Size(max = 20)
    @Column(name = "sessionid", length = 20)
    private String sessionid;

    @Column(name = "reworked")
    private Long reworked;

    @Size(max = 20)
    @Column(name = "reworkedby", length = 20)
    private String reworkedby;

    @Column(name = "reworkedon")
    private Instant reworkedon;

    @Size(max = 50)
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50)
    @Column(name = "region", length = 50)
    private String region;

    @Size(max = 50)
    @Column(name = "regionname", length = 50)
    private String regionname;

    @Column(name = "reporting")
    private Long reporting;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Branches id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchname() {
        return this.branchname;
    }

    public Branches branchname(String branchname) {
        this.setBranchname(branchname);
        return this;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public Branches branchcode(String branchcode) {
        this.setBranchcode(branchcode);
        return this;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public Long getApproved() {
        return this.approved;
    }

    public Branches approved(Long approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public String getEmail() {
        return this.email;
    }

    public Branches email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public Branches address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public Branches phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return this.location;
    }

    public Branches location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactperson() {
        return this.contactperson;
    }

    public Branches contactperson(String contactperson) {
        this.setContactperson(contactperson);
        return this;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Branches remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public Branches createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreatedon() {
        return this.createdon;
    }

    public Branches createdon(Instant createdon) {
        this.setCreatedon(createdon);
        return this;
    }

    public void setCreatedon(Instant createdon) {
        this.createdon = createdon;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public Branches approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getApprovedon() {
        return this.approvedon;
    }

    public Branches approvedon(String approvedon) {
        this.setApprovedon(approvedon);
        return this;
    }

    public void setApprovedon(String approvedon) {
        this.approvedon = approvedon;
    }

    public String getCheckerremarks() {
        return this.checkerremarks;
    }

    public Branches checkerremarks(String checkerremarks) {
        this.setCheckerremarks(checkerremarks);
        return this;
    }

    public void setCheckerremarks(String checkerremarks) {
        this.checkerremarks = checkerremarks;
    }

    public String getDeletedby() {
        return this.deletedby;
    }

    public Branches deletedby(String deletedby) {
        this.setDeletedby(deletedby);
        return this;
    }

    public void setDeletedby(String deletedby) {
        this.deletedby = deletedby;
    }

    public Instant getDeletedon() {
        return this.deletedon;
    }

    public Branches deletedon(Instant deletedon) {
        this.setDeletedon(deletedon);
        return this;
    }

    public void setDeletedon(Instant deletedon) {
        this.deletedon = deletedon;
    }

    public String getDeleteremarks() {
        return this.deleteremarks;
    }

    public Branches deleteremarks(String deleteremarks) {
        this.setDeleteremarks(deleteremarks);
        return this;
    }

    public void setDeleteremarks(String deleteremarks) {
        this.deleteremarks = deleteremarks;
    }

    public Long getDeleted() {
        return this.deleted;
    }

    public Branches deleted(Long deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }

    public Long getDeclined() {
        return this.declined;
    }

    public Branches declined(Long declined) {
        this.setDeclined(declined);
        return this;
    }

    public void setDeclined(Long declined) {
        this.declined = declined;
    }

    public String getDeclineddon() {
        return this.declineddon;
    }

    public Branches declineddon(String declineddon) {
        this.setDeclineddon(declineddon);
        return this;
    }

    public void setDeclineddon(String declineddon) {
        this.declineddon = declineddon;
    }

    public String getDeclinedby() {
        return this.declinedby;
    }

    public Branches declinedby(String declinedby) {
        this.setDeclinedby(declinedby);
        return this;
    }

    public void setDeclinedby(String declinedby) {
        this.declinedby = declinedby;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public Branches sessionid(String sessionid) {
        this.setSessionid(sessionid);
        return this;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Long getReworked() {
        return this.reworked;
    }

    public Branches reworked(Long reworked) {
        this.setReworked(reworked);
        return this;
    }

    public void setReworked(Long reworked) {
        this.reworked = reworked;
    }

    public String getReworkedby() {
        return this.reworkedby;
    }

    public Branches reworkedby(String reworkedby) {
        this.setReworkedby(reworkedby);
        return this;
    }

    public void setReworkedby(String reworkedby) {
        this.reworkedby = reworkedby;
    }

    public Instant getReworkedon() {
        return this.reworkedon;
    }

    public Branches reworkedon(Instant reworkedon) {
        this.setReworkedon(reworkedon);
        return this;
    }

    public void setReworkedon(Instant reworkedon) {
        this.reworkedon = reworkedon;
    }

    public String getDistrict() {
        return this.district;
    }

    public Branches district(String district) {
        this.setDistrict(district);
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return this.region;
    }

    public Branches region(String region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionname() {
        return this.regionname;
    }

    public Branches regionname(String regionname) {
        this.setRegionname(regionname);
        return this;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public Long getReporting() {
        return this.reporting;
    }

    public Branches reporting(Long reporting) {
        this.setReporting(reporting);
        return this;
    }

    public void setReporting(Long reporting) {
        this.reporting = reporting;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branches)) {
            return false;
        }
        return getId() != null && getId().equals(((Branches) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Branches{" +
            "id=" + getId() +
            ", branchname='" + getBranchname() + "'" +
            ", branchcode='" + getBranchcode() + "'" +
            ", approved=" + getApproved() +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", location='" + getLocation() + "'" +
            ", contactperson='" + getContactperson() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createdon='" + getCreatedon() + "'" +
            ", approvedby='" + getApprovedby() + "'" +
            ", approvedon='" + getApprovedon() + "'" +
            ", checkerremarks='" + getCheckerremarks() + "'" +
            ", deletedby='" + getDeletedby() + "'" +
            ", deletedon='" + getDeletedon() + "'" +
            ", deleteremarks='" + getDeleteremarks() + "'" +
            ", deleted=" + getDeleted() +
            ", declined=" + getDeclined() +
            ", declineddon='" + getDeclineddon() + "'" +
            ", declinedby='" + getDeclinedby() + "'" +
            ", sessionid='" + getSessionid() + "'" +
            ", reworked=" + getReworked() +
            ", reworkedby='" + getReworkedby() + "'" +
            ", reworkedon='" + getReworkedon() + "'" +
            ", district='" + getDistrict() + "'" +
            ", region='" + getRegion() + "'" +
            ", regionname='" + getRegionname() + "'" +
            ", reporting=" + getReporting() +
            "}";
    }
}
