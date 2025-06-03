package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A MessagesSms.
 */
@Entity
@Table(name = "messages_sms")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MessagesSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;

    @Column(name = "trndatetime")
    private Instant trndatetime;

    @Size(max = 50)
    @Column(name = "phonenumber", length = 50)
    private String phonenumber;

    @Size(max = 4000)
    @Column(name = "transactionno", length = 4000)
    private String transactionno;

    @Size(max = 50)
    @Column(name = "accountnumber", length = 50)
    private String accountnumber;

    @Size(max = 2000)
    @Column(name = "message", length = 2000)
    private String message;

    @Size(max = 4000)
    @Column(name = "channel", length = 4000)
    private String channel;

    @Column(name = "trials")
    private Long trials;

    @Column(name = "priority")
    private Long priority;

    @Size(max = 4)
    @Column(name = "responsecode", length = 4)
    private String responsecode;

    @Size(max = 4000)
    @Column(name = "responsemsg", length = 4000)
    private String responsemsg;

    @Column(name = "sent")
    private Long sent;

    @Column(name = "delivered")
    private Long delivered;

    @Size(max = 200)
    @Column(name = "txntype", length = 200)
    private String txntype;

    @Column(name = "errorexception")
    private Long errorexception;

    @Column(name = "datecreated")
    private Instant datecreated;

    @Size(max = 7)
    @Column(name = "datesent", length = 7)
    private String datesent;

    @Size(max = 200)
    @Column(name = "rtpsreqtime", length = 200)
    private String rtpsreqtime;

    @Size(max = 20)
    @Column(name = "fxgenerated", length = 20)
    private String fxgenerated;

    @Column(name = "taxprocessed")
    private Long taxprocessed;

    @Size(max = 200)
    @Column(name = "batchnumber", length = 200)
    private String batchnumber;

    @Size(max = 200)
    @Column(name = "batchnumbertax", length = 200)
    private String batchnumbertax;

    @Size(max = 200)
    @Column(name = "responsetime", length = 200)
    private String responsetime;

    @Size(max = 200)
    @Column(name = "pduseqid", length = 200)
    private String pduseqid;

    @Size(max = 300)
    @Column(name = "remarks", length = 300)
    private String remarks;

    @Size(max = 50)
    @Column(name = "resendby", length = 50)
    private String resendby;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MessagesSms id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTrndatetime() {
        return this.trndatetime;
    }

    public MessagesSms trndatetime(Instant trndatetime) {
        this.setTrndatetime(trndatetime);
        return this;
    }

    public void setTrndatetime(Instant trndatetime) {
        this.trndatetime = trndatetime;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public MessagesSms phonenumber(String phonenumber) {
        this.setPhonenumber(phonenumber);
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTransactionno() {
        return this.transactionno;
    }

    public MessagesSms transactionno(String transactionno) {
        this.setTransactionno(transactionno);
        return this;
    }

    public void setTransactionno(String transactionno) {
        this.transactionno = transactionno;
    }

    public String getAccountnumber() {
        return this.accountnumber;
    }

    public MessagesSms accountnumber(String accountnumber) {
        this.setAccountnumber(accountnumber);
        return this;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getMessage() {
        return this.message;
    }

    public MessagesSms message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return this.channel;
    }

    public MessagesSms channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getTrials() {
        return this.trials;
    }

    public MessagesSms trials(Long trials) {
        this.setTrials(trials);
        return this;
    }

    public void setTrials(Long trials) {
        this.trials = trials;
    }

    public Long getPriority() {
        return this.priority;
    }

    public MessagesSms priority(Long priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getResponsecode() {
        return this.responsecode;
    }

    public MessagesSms responsecode(String responsecode) {
        this.setResponsecode(responsecode);
        return this;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponsemsg() {
        return this.responsemsg;
    }

    public MessagesSms responsemsg(String responsemsg) {
        this.setResponsemsg(responsemsg);
        return this;
    }

    public void setResponsemsg(String responsemsg) {
        this.responsemsg = responsemsg;
    }

    public Long getSent() {
        return this.sent;
    }

    public MessagesSms sent(Long sent) {
        this.setSent(sent);
        return this;
    }

    public void setSent(Long sent) {
        this.sent = sent;
    }

    public Long getDelivered() {
        return this.delivered;
    }

    public MessagesSms delivered(Long delivered) {
        this.setDelivered(delivered);
        return this;
    }

    public void setDelivered(Long delivered) {
        this.delivered = delivered;
    }

    public String getTxntype() {
        return this.txntype;
    }

    public MessagesSms txntype(String txntype) {
        this.setTxntype(txntype);
        return this;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public Long getErrorexception() {
        return this.errorexception;
    }

    public MessagesSms errorexception(Long errorexception) {
        this.setErrorexception(errorexception);
        return this;
    }

    public void setErrorexception(Long errorexception) {
        this.errorexception = errorexception;
    }

    public Instant getDatecreated() {
        return this.datecreated;
    }

    public MessagesSms datecreated(Instant datecreated) {
        this.setDatecreated(datecreated);
        return this;
    }

    public void setDatecreated(Instant datecreated) {
        this.datecreated = datecreated;
    }

    public String getDatesent() {
        return this.datesent;
    }

    public MessagesSms datesent(String datesent) {
        this.setDatesent(datesent);
        return this;
    }

    public void setDatesent(String datesent) {
        this.datesent = datesent;
    }

    public String getRtpsreqtime() {
        return this.rtpsreqtime;
    }

    public MessagesSms rtpsreqtime(String rtpsreqtime) {
        this.setRtpsreqtime(rtpsreqtime);
        return this;
    }

    public void setRtpsreqtime(String rtpsreqtime) {
        this.rtpsreqtime = rtpsreqtime;
    }

    public String getFxgenerated() {
        return this.fxgenerated;
    }

    public MessagesSms fxgenerated(String fxgenerated) {
        this.setFxgenerated(fxgenerated);
        return this;
    }

    public void setFxgenerated(String fxgenerated) {
        this.fxgenerated = fxgenerated;
    }

    public Long getTaxprocessed() {
        return this.taxprocessed;
    }

    public MessagesSms taxprocessed(Long taxprocessed) {
        this.setTaxprocessed(taxprocessed);
        return this;
    }

    public void setTaxprocessed(Long taxprocessed) {
        this.taxprocessed = taxprocessed;
    }

    public String getBatchnumber() {
        return this.batchnumber;
    }

    public MessagesSms batchnumber(String batchnumber) {
        this.setBatchnumber(batchnumber);
        return this;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getBatchnumbertax() {
        return this.batchnumbertax;
    }

    public MessagesSms batchnumbertax(String batchnumbertax) {
        this.setBatchnumbertax(batchnumbertax);
        return this;
    }

    public void setBatchnumbertax(String batchnumbertax) {
        this.batchnumbertax = batchnumbertax;
    }

    public String getResponsetime() {
        return this.responsetime;
    }

    public MessagesSms responsetime(String responsetime) {
        this.setResponsetime(responsetime);
        return this;
    }

    public void setResponsetime(String responsetime) {
        this.responsetime = responsetime;
    }

    public String getPduseqid() {
        return this.pduseqid;
    }

    public MessagesSms pduseqid(String pduseqid) {
        this.setPduseqid(pduseqid);
        return this;
    }

    public void setPduseqid(String pduseqid) {
        this.pduseqid = pduseqid;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public MessagesSms remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResendby() {
        return this.resendby;
    }

    public MessagesSms resendby(String resendby) {
        this.setResendby(resendby);
        return this;
    }

    public void setResendby(String resendby) {
        this.resendby = resendby;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessagesSms)) {
            return false;
        }
        return getId() != null && getId().equals(((MessagesSms) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessagesSms{" +
            "id=" + getId() +
            ", trndatetime='" + getTrndatetime() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", transactionno='" + getTransactionno() + "'" +
            ", accountnumber='" + getAccountnumber() + "'" +
            ", message='" + getMessage() + "'" +
            ", channel='" + getChannel() + "'" +
            ", trials=" + getTrials() +
            ", priority=" + getPriority() +
            ", responsecode='" + getResponsecode() + "'" +
            ", responsemsg='" + getResponsemsg() + "'" +
            ", sent=" + getSent() +
            ", delivered=" + getDelivered() +
            ", txntype='" + getTxntype() + "'" +
            ", errorexception=" + getErrorexception() +
            ", datecreated='" + getDatecreated() + "'" +
            ", datesent='" + getDatesent() + "'" +
            ", rtpsreqtime='" + getRtpsreqtime() + "'" +
            ", fxgenerated='" + getFxgenerated() + "'" +
            ", taxprocessed=" + getTaxprocessed() +
            ", batchnumber='" + getBatchnumber() + "'" +
            ", batchnumbertax='" + getBatchnumbertax() + "'" +
            ", responsetime='" + getResponsetime() + "'" +
            ", pduseqid='" + getPduseqid() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", resendby='" + getResendby() + "'" +
            "}";
    }
}
