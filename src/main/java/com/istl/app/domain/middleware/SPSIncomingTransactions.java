package com.istl.app.domain.middleware;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SPSIncomingTransactions.
 */
@Entity
@Table(name = "`SPSIncomingTransactions`")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SPSIncomingTransactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "`ID`")
    private Long id;

    @Size(max = 40)
    @Column(name = "`MESSAGE_ID`", length = 40)
    private String messageid;

    @Size(max = 12)
    @Column(name = "`CHANNELCODE`", length = 12)
    private String channelcode;

    @Size(max = 100)
    @Column(name = "`CALLBACK_URL`", length = 100)
    private String callbackurl;

    @Size(max = 20)
    @Column(name = "`MESSAGE_TYPE`", length = 20)
    private String messagetype;

    @Size(max = 6)
    @Column(name = "`TRANS_CURRENCY`", length = 6)
    private String transcurrency;

    @Size(max = 100)
    @Column(name = "`DEBTORS_NAME`", length = 100)
    private String debtorsname;

    @Size(max = 30)
    @Column(name = "`DEBTORS_ACCOUNTID`", length = 30)
    private String debtorsaccountid;

    @Size(max = 20)
    @Column(name = "`DEBTORS_BANKCODE`", length = 20)
    private String debtorsbankcode;

    @Size(max = 20)
    @Column(name = "`DEBTORS_PHONE`", length = 20)
    private String debtorsphone;

    @Size(max = 100)
    @Column(name = "`BENEFICIARY_NAME`", length = 100)
    private String beneficiaryname;

    @Size(max = 30)
    @Column(name = "`BENEFICIARY_ACCOUNTID`", length = 30)
    private String beneficiaryaccountid;

    @Size(max = 20)
    @Column(name = "`BENEFICIARY_BANKCODE`", length = 20)
    private String beneficiarybankcode;

    @Size(max = 20)
    @Column(name = "`BENEFICIARY_PHONE`", length = 20)
    private String beneficiaryphone;

    @Size(max = 100)
    @Column(name = "`NARRATION`", length = 100)
    private String narration;

    @Size(max = 40)
    @Column(name = "`EXTERNAL_REFERENCE`", length = 40)
    private String externalreference;

    @Size(max = 40)
    @Column(name = "`CBS_REFERENCE`", length = 40)
    private String cbsreference;

    @Size(max = 40)
    @Column(name = "`MESSAGE_ENDTOENDID`", length = 40)
    private String messageendtoendid;

    @Size(max = 20)
    @Column(name = "`TRANSACTION_STATUS`", length = 20)
    private String transactionstatus;

    @Size(max = 200)
    @Column(name = "`TRANSACTION_STATUS_DESC`", length = 200)
    private String transactionstatusdesc;

    @Size(max = 20)
    @Column(name = "`SPS_STATUS`", length = 20)
    private String spsstatus;

    @Size(max = 200)
    @Column(name = "`SPS_STATUS_DESC`", length = 200)
    private String spsstatusdesc;

    @Size(max = 20)
    @Column(name = "`CBS_STATUS`", length = 20)
    private String cbsstatus;

    @Size(max = 200)
    @Column(name = "`CBS_STATUS_DESC`", length = 200)
    private String cbsstatusdesc;

    @Column(name = "`REQUEST_DATETIME`")
    private Instant requestInstanttime;

    @Size(max = 20)
    @Column(name = "`ISO_MESSAGETYPE`", length = 20)
    private String isomessagetype;

    @Column(name = "`REQUEST_JSON`")
    private String requestjson;

    @Column(name = "`SPS_REQUEST_XML`")
    private String spsrequestxml;

    @Column(name = "`SPS_RESPONSE_XML`")
    private String spsresponsexml;

    @Column(name = "`AMOUNT`")
    private Double amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SPSIncomingTransactions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageid() {
        return this.messageid;
    }

    public SPSIncomingTransactions messageid(String messageid) {
        this.setMessageid(messageid);
        return this;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getChannelcode() {
        return this.channelcode;
    }

    public SPSIncomingTransactions channelcode(String channelcode) {
        this.setChannelcode(channelcode);
        return this;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getCallbackurl() {
        return this.callbackurl;
    }

    public SPSIncomingTransactions callbackurl(String callbackurl) {
        this.setCallbackurl(callbackurl);
        return this;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getMessagetype() {
        return this.messagetype;
    }

    public SPSIncomingTransactions messagetype(String messagetype) {
        this.setMessagetype(messagetype);
        return this;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getTranscurrency() {
        return this.transcurrency;
    }

    public SPSIncomingTransactions transcurrency(String transcurrency) {
        this.setTranscurrency(transcurrency);
        return this;
    }

    public void setTranscurrency(String transcurrency) {
        this.transcurrency = transcurrency;
    }

    public String getDebtorsname() {
        return this.debtorsname;
    }

    public SPSIncomingTransactions debtorsname(String debtorsname) {
        this.setDebtorsname(debtorsname);
        return this;
    }

    public void setDebtorsname(String debtorsname) {
        this.debtorsname = debtorsname;
    }

    public String getDebtorsaccountid() {
        return this.debtorsaccountid;
    }

    public SPSIncomingTransactions debtorsaccountid(String debtorsaccountid) {
        this.setDebtorsaccountid(debtorsaccountid);
        return this;
    }

    public void setDebtorsaccountid(String debtorsaccountid) {
        this.debtorsaccountid = debtorsaccountid;
    }

    public String getDebtorsbankcode() {
        return this.debtorsbankcode;
    }

    public SPSIncomingTransactions debtorsbankcode(String debtorsbankcode) {
        this.setDebtorsbankcode(debtorsbankcode);
        return this;
    }

    public void setDebtorsbankcode(String debtorsbankcode) {
        this.debtorsbankcode = debtorsbankcode;
    }

    public String getDebtorsphone() {
        return this.debtorsphone;
    }

    public SPSIncomingTransactions debtorsphone(String debtorsphone) {
        this.setDebtorsphone(debtorsphone);
        return this;
    }

    public void setDebtorsphone(String debtorsphone) {
        this.debtorsphone = debtorsphone;
    }

    public String getBeneficiaryname() {
        return this.beneficiaryname;
    }

    public SPSIncomingTransactions beneficiaryname(String beneficiaryname) {
        this.setBeneficiaryname(beneficiaryname);
        return this;
    }

    public void setBeneficiaryname(String beneficiaryname) {
        this.beneficiaryname = beneficiaryname;
    }

    public String getBeneficiaryaccountid() {
        return this.beneficiaryaccountid;
    }

    public SPSIncomingTransactions beneficiaryaccountid(String beneficiaryaccountid) {
        this.setBeneficiaryaccountid(beneficiaryaccountid);
        return this;
    }

    public void setBeneficiaryaccountid(String beneficiaryaccountid) {
        this.beneficiaryaccountid = beneficiaryaccountid;
    }

    public String getBeneficiarybankcode() {
        return this.beneficiarybankcode;
    }

    public SPSIncomingTransactions beneficiarybankcode(String beneficiarybankcode) {
        this.setBeneficiarybankcode(beneficiarybankcode);
        return this;
    }

    public void setBeneficiarybankcode(String beneficiarybankcode) {
        this.beneficiarybankcode = beneficiarybankcode;
    }

    public String getBeneficiaryphone() {
        return this.beneficiaryphone;
    }

    public SPSIncomingTransactions beneficiaryphone(String beneficiaryphone) {
        this.setBeneficiaryphone(beneficiaryphone);
        return this;
    }

    public void setBeneficiaryphone(String beneficiaryphone) {
        this.beneficiaryphone = beneficiaryphone;
    }

    public String getNarration() {
        return this.narration;
    }

    public SPSIncomingTransactions narration(String narration) {
        this.setNarration(narration);
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getExternalreference() {
        return this.externalreference;
    }

    public SPSIncomingTransactions externalreference(String externalreference) {
        this.setExternalreference(externalreference);
        return this;
    }

    public void setExternalreference(String externalreference) {
        this.externalreference = externalreference;
    }

    public String getCbsreference() {
        return this.cbsreference;
    }

    public SPSIncomingTransactions cbsreference(String cbsreference) {
        this.setCbsreference(cbsreference);
        return this;
    }

    public void setCbsreference(String cbsreference) {
        this.cbsreference = cbsreference;
    }

    public String getMessageendtoendid() {
        return this.messageendtoendid;
    }

    public SPSIncomingTransactions messageendtoendid(String messageendtoendid) {
        this.setMessageendtoendid(messageendtoendid);
        return this;
    }

    public void setMessageendtoendid(String messageendtoendid) {
        this.messageendtoendid = messageendtoendid;
    }

    public String getTransactionstatus() {
        return this.transactionstatus;
    }

    public SPSIncomingTransactions transactionstatus(String transactionstatus) {
        this.setTransactionstatus(transactionstatus);
        return this;
    }

    public void setTransactionstatus(String transactionstatus) {
        this.transactionstatus = transactionstatus;
    }

    public String getTransactionstatusdesc() {
        return this.transactionstatusdesc;
    }

    public SPSIncomingTransactions transactionstatusdesc(String transactionstatusdesc) {
        this.setTransactionstatusdesc(transactionstatusdesc);
        return this;
    }

    public void setTransactionstatusdesc(String transactionstatusdesc) {
        this.transactionstatusdesc = transactionstatusdesc;
    }

    public String getSpsstatus() {
        return this.spsstatus;
    }

    public SPSIncomingTransactions spsstatus(String spsstatus) {
        this.setSpsstatus(spsstatus);
        return this;
    }

    public void setSpsstatus(String spsstatus) {
        this.spsstatus = spsstatus;
    }

    public String getSpsstatusdesc() {
        return this.spsstatusdesc;
    }

    public SPSIncomingTransactions spsstatusdesc(String spsstatusdesc) {
        this.setSpsstatusdesc(spsstatusdesc);
        return this;
    }

    public void setSpsstatusdesc(String spsstatusdesc) {
        this.spsstatusdesc = spsstatusdesc;
    }

    public String getCbsstatus() {
        return this.cbsstatus;
    }

    public SPSIncomingTransactions cbsstatus(String cbsstatus) {
        this.setCbsstatus(cbsstatus);
        return this;
    }

    public void setCbsstatus(String cbsstatus) {
        this.cbsstatus = cbsstatus;
    }

    public String getCbsstatusdesc() {
        return this.cbsstatusdesc;
    }

    public SPSIncomingTransactions cbsstatusdesc(String cbsstatusdesc) {
        this.setCbsstatusdesc(cbsstatusdesc);
        return this;
    }

    public void setCbsstatusdesc(String cbsstatusdesc) {
        this.cbsstatusdesc = cbsstatusdesc;
    }

    public Instant getRequestInstanttime() {
        return this.requestInstanttime;
    }

    public SPSIncomingTransactions requestInstanttime(Instant requestInstanttime) {
        this.setRequestInstanttime(requestInstanttime);
        return this;
    }

    public void setRequestInstanttime(Instant requestInstanttime) {
        this.requestInstanttime = requestInstanttime;
    }

    public String getIsomessagetype() {
        return this.isomessagetype;
    }

    public SPSIncomingTransactions isomessagetype(String isomessagetype) {
        this.setIsomessagetype(isomessagetype);
        return this;
    }

    public void setIsomessagetype(String isomessagetype) {
        this.isomessagetype = isomessagetype;
    }

    public String getRequestjson() {
        return this.requestjson;
    }

    public SPSIncomingTransactions requestjson(String requestjson) {
        this.setRequestjson(requestjson);
        return this;
    }

    public void setRequestjson(String requestjson) {
        this.requestjson = requestjson;
    }

    public String getSpsrequestxml() {
        return this.spsrequestxml;
    }

    public SPSIncomingTransactions spsrequestxml(String spsrequestxml) {
        this.setSpsrequestxml(spsrequestxml);
        return this;
    }

    public void setSpsrequestxml(String spsrequestxml) {
        this.spsrequestxml = spsrequestxml;
    }

    public String getSpsresponsexml() {
        return this.spsresponsexml;
    }

    public SPSIncomingTransactions spsresponsexml(String spsresponsexml) {
        this.setSpsresponsexml(spsresponsexml);
        return this;
    }

    public void setSpsresponsexml(String spsresponsexml) {
        this.spsresponsexml = spsresponsexml;
    }

    public Double getAmount() {
        return this.amount;
    }

    public SPSIncomingTransactions amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SPSIncomingTransactions)) {
            return false;
        }
        return getId() != null && getId().equals(((SPSIncomingTransactions) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SPSIncomingTransactions{" +
            "id=" + getId() +
            ", messageid='" + getMessageid() + "'" +
            ", channelcode='" + getChannelcode() + "'" +
            ", callbackurl='" + getCallbackurl() + "'" +
            ", messagetype='" + getMessagetype() + "'" +
            ", transcurrency='" + getTranscurrency() + "'" +
            ", debtorsname='" + getDebtorsname() + "'" +
            ", debtorsaccountid='" + getDebtorsaccountid() + "'" +
            ", debtorsbankcode='" + getDebtorsbankcode() + "'" +
            ", debtorsphone='" + getDebtorsphone() + "'" +
            ", beneficiaryname='" + getBeneficiaryname() + "'" +
            ", beneficiaryaccountid='" + getBeneficiaryaccountid() + "'" +
            ", beneficiarybankcode='" + getBeneficiarybankcode() + "'" +
            ", beneficiaryphone='" + getBeneficiaryphone() + "'" +
            ", narration='" + getNarration() + "'" +
            ", externalreference='" + getExternalreference() + "'" +
            ", cbsreference='" + getCbsreference() + "'" +
            ", messageendtoendid='" + getMessageendtoendid() + "'" +
            ", transactionstatus='" + getTransactionstatus() + "'" +
            ", transactionstatusdesc='" + getTransactionstatusdesc() + "'" +
            ", spsstatus='" + getSpsstatus() + "'" +
            ", spsstatusdesc='" + getSpsstatusdesc() + "'" +
            ", cbsstatus='" + getCbsstatus() + "'" +
            ", cbsstatusdesc='" + getCbsstatusdesc() + "'" +
            ", requestInstanttime='" + getRequestInstanttime() + "'" +
            ", isomessagetype='" + getIsomessagetype() + "'" +
            ", requestjson='" + getRequestjson() + "'" +
            ", spsrequestxml='" + getSpsrequestxml() + "'" +
            ", spsresponsexml='" + getSpsresponsexml() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
