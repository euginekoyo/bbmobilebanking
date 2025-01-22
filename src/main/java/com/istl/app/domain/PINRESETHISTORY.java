package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PinResetHistory.
 */
@Entity
@Table(name = "pin_reset_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PinResetHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 20)
    @Column(name = "phonenumber", length = 20)
    private String phonenumber;

    @Size(max = 150)
    @Column(name = "customername", length = 150)
    private String customername;

    @Size(max = 50)
    @Column(name = "pinblockedon", length = 50)
    private String pinblockedon;

    @Size(max = 200)
    @Column(name = "pinblockremarks", length = 200)
    private String pinblockremarks;

    @Size(max = 50)
    @Column(name = "pinresetby", length = 50)
    private String pinresetby;

    @Size(max = 50)
    @Column(name = "pinreseton", length = 50)
    private String pinreseton;

    @Size(max = 50)
    @Column(name = "pinresetapprovedby", length = 50)
    private String pinresetapprovedby;

    @Size(max = 50)
    @Column(name = "pinresetapprovedon", length = 50)
    private String pinresetapprovedon;

    @Size(max = 200)
    @Column(name = "pinresetremarks", length = 200)
    private String pinresetremarks;

    @Size(max = 20)
    @Column(name = "branchcode", length = 20)
    private String branchcode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PinResetHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public PinResetHistory phonenumber(String phonenumber) {
        this.setPhonenumber(phonenumber);
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCustomername() {
        return this.customername;
    }

    public PinResetHistory customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getPinblockedon() {
        return this.pinblockedon;
    }

    public PinResetHistory pinblockedon(String pinblockedon) {
        this.setPinblockedon(pinblockedon);
        return this;
    }

    public void setPinblockedon(String pinblockedon) {
        this.pinblockedon = pinblockedon;
    }

    public String getPinblockremarks() {
        return this.pinblockremarks;
    }

    public PinResetHistory pinblockremarks(String pinblockremarks) {
        this.setPinblockremarks(pinblockremarks);
        return this;
    }

    public void setPinblockremarks(String pinblockremarks) {
        this.pinblockremarks = pinblockremarks;
    }

    public String getPinresetby() {
        return this.pinresetby;
    }

    public PinResetHistory pinresetby(String pinresetby) {
        this.setPinresetby(pinresetby);
        return this;
    }

    public void setPinresetby(String pinresetby) {
        this.pinresetby = pinresetby;
    }

    public String getPinreseton() {
        return this.pinreseton;
    }

    public PinResetHistory pinreseton(String pinreseton) {
        this.setPinreseton(pinreseton);
        return this;
    }

    public void setPinreseton(String pinreseton) {
        this.pinreseton = pinreseton;
    }

    public String getPinresetapprovedby() {
        return this.pinresetapprovedby;
    }

    public PinResetHistory pinresetapprovedby(String pinresetapprovedby) {
        this.setPinresetapprovedby(pinresetapprovedby);
        return this;
    }

    public void setPinresetapprovedby(String pinresetapprovedby) {
        this.pinresetapprovedby = pinresetapprovedby;
    }

    public String getPinresetapprovedon() {
        return this.pinresetapprovedon;
    }

    public PinResetHistory pinresetapprovedon(String pinresetapprovedon) {
        this.setPinresetapprovedon(pinresetapprovedon);
        return this;
    }

    public void setPinresetapprovedon(String pinresetapprovedon) {
        this.pinresetapprovedon = pinresetapprovedon;
    }

    public String getPinresetremarks() {
        return this.pinresetremarks;
    }

    public PinResetHistory pinresetremarks(String pinresetremarks) {
        this.setPinresetremarks(pinresetremarks);
        return this;
    }

    public void setPinresetremarks(String pinresetremarks) {
        this.pinresetremarks = pinresetremarks;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public PinResetHistory branchcode(String branchcode) {
        this.setBranchcode(branchcode);
        return this;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PinResetHistory)) {
            return false;
        }
        return getId() != null && getId().equals(((PinResetHistory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PinResetHistory{" +
            "id=" + getId() +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", customername='" + getCustomername() + "'" +
            ", pinblockedon='" + getPinblockedon() + "'" +
            ", pinblockremarks='" + getPinblockremarks() + "'" +
            ", pinresetby='" + getPinresetby() + "'" +
            ", pinreseton='" + getPinreseton() + "'" +
            ", pinresetapprovedby='" + getPinresetapprovedby() + "'" +
            ", pinresetapprovedon='" + getPinresetapprovedon() + "'" +
            ", pinresetremarks='" + getPinresetremarks() + "'" +
            ", branchcode='" + getBranchcode() + "'" +
            "}";
    }
}
