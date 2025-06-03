package com.istl.app.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.com.istl.app.domain.mobileapp.MobileAppTransactions} entity. This class is used
 * in {@link com.istl.app.web.rest.MobileAppTransactionsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /mobile-app-transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MobileAppTransactionsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter channel;

    private StringFilter channelIp;

    private StringFilter channelReference;

    private InstantFilter channelTimestamp;

    private StringFilter clientId;

    private InstantFilter createdAt;

    private StringFilter debitAccount;

    private StringFilter direction;

    private StringFilter errorDescription;

    private StringFilter geolocation;

    private StringFilter hostCode;

    private StringFilter phoneNumber;

    private StringFilter responseCode;

    private StringFilter responseMessage;

    private StringFilter transactionCode;

    private StringFilter transactionType;

    private StringFilter userAgent;

    private StringFilter userAgentVersion;

    private StringFilter amount;

    private StringFilter chargeamount;

    private StringFilter creditRccount;

    private StringFilter cbsReference;

    private Boolean distinct;

    public MobileAppTransactionsCriteria() {}

    public MobileAppTransactionsCriteria(MobileAppTransactionsCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.channel = other.optionalChannel().map(StringFilter::copy).orElse(null);
        this.channelIp = other.optionalChannelIp().map(StringFilter::copy).orElse(null);
        this.channelReference = other.optionalChannelReference().map(StringFilter::copy).orElse(null);
        this.channelTimestamp = other.optionalChannelTimestamp().map(InstantFilter::copy).orElse(null);
        this.clientId = other.optionalClientId().map(StringFilter::copy).orElse(null);
        this.createdAt = other.optionalCreatedAt().map(InstantFilter::copy).orElse(null);
        this.debitAccount = other.optionalDebitAccount().map(StringFilter::copy).orElse(null);
        this.direction = other.optionalDirection().map(StringFilter::copy).orElse(null);
        this.errorDescription = other.optionalErrorDescription().map(StringFilter::copy).orElse(null);
        this.geolocation = other.optionalGeolocation().map(StringFilter::copy).orElse(null);
        this.hostCode = other.optionalHostCode().map(StringFilter::copy).orElse(null);
        this.phoneNumber = other.optionalPhoneNumber().map(StringFilter::copy).orElse(null);
        this.responseCode = other.optionalResponseCode().map(StringFilter::copy).orElse(null);
        this.responseMessage = other.optionalResponseMessage().map(StringFilter::copy).orElse(null);
        this.transactionCode = other.optionalTransactionCode().map(StringFilter::copy).orElse(null);
        this.transactionType = other.optionalTransactionType().map(StringFilter::copy).orElse(null);
        this.userAgent = other.optionalUserAgent().map(StringFilter::copy).orElse(null);
        this.userAgentVersion = other.optionalUserAgentVersion().map(StringFilter::copy).orElse(null);
        this.amount = other.optionalAmount().map(StringFilter::copy).orElse(null);
        this.chargeamount = other.optionalChargeamount().map(StringFilter::copy).orElse(null);
        this.creditRccount = other.optionalCreditRccount().map(StringFilter::copy).orElse(null);
        this.cbsReference = other.optionalCbsReference().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public MobileAppTransactionsCriteria copy() {
        return new MobileAppTransactionsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getChannel() {
        return channel;
    }

    public Optional<StringFilter> optionalChannel() {
        return Optional.ofNullable(channel);
    }

    public StringFilter channel() {
        if (channel == null) {
            setChannel(new StringFilter());
        }
        return channel;
    }

    public void setChannel(StringFilter channel) {
        this.channel = channel;
    }

    public StringFilter getChannelIp() {
        return channelIp;
    }

    public Optional<StringFilter> optionalChannelIp() {
        return Optional.ofNullable(channelIp);
    }

    public StringFilter channelIp() {
        if (channelIp == null) {
            setChannelIp(new StringFilter());
        }
        return channelIp;
    }

    public void setChannelIp(StringFilter channelIp) {
        this.channelIp = channelIp;
    }

    public StringFilter getChannelReference() {
        return channelReference;
    }

    public Optional<StringFilter> optionalChannelReference() {
        return Optional.ofNullable(channelReference);
    }

    public StringFilter channelReference() {
        if (channelReference == null) {
            setChannelReference(new StringFilter());
        }
        return channelReference;
    }

    public void setChannelReference(StringFilter channelReference) {
        this.channelReference = channelReference;
    }

    public InstantFilter getChannelTimestamp() {
        return channelTimestamp;
    }

    public Optional<InstantFilter> optionalChannelTimestamp() {
        return Optional.ofNullable(channelTimestamp);
    }

    public InstantFilter channelTimestamp() {
        if (channelTimestamp == null) {
            setChannelTimestamp(new InstantFilter());
        }
        return channelTimestamp;
    }

    public void setChannelTimestamp(InstantFilter channelTimestamp) {
        this.channelTimestamp = channelTimestamp;
    }

    public StringFilter getClientId() {
        return clientId;
    }

    public Optional<StringFilter> optionalClientId() {
        return Optional.ofNullable(clientId);
    }

    public StringFilter clientId() {
        if (clientId == null) {
            setClientId(new StringFilter());
        }
        return clientId;
    }

    public void setClientId(StringFilter clientId) {
        this.clientId = clientId;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public Optional<InstantFilter> optionalCreatedAt() {
        return Optional.ofNullable(createdAt);
    }

    public InstantFilter createdAt() {
        if (createdAt == null) {
            setCreatedAt(new InstantFilter());
        }
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public StringFilter getDebitAccount() {
        return debitAccount;
    }

    public Optional<StringFilter> optionalDebitAccount() {
        return Optional.ofNullable(debitAccount);
    }

    public StringFilter debitAccount() {
        if (debitAccount == null) {
            setDebitAccount(new StringFilter());
        }
        return debitAccount;
    }

    public void setDebitAccount(StringFilter debitAccount) {
        this.debitAccount = debitAccount;
    }

    public StringFilter getDirection() {
        return direction;
    }

    public Optional<StringFilter> optionalDirection() {
        return Optional.ofNullable(direction);
    }

    public StringFilter direction() {
        if (direction == null) {
            setDirection(new StringFilter());
        }
        return direction;
    }

    public void setDirection(StringFilter direction) {
        this.direction = direction;
    }

    public StringFilter getErrorDescription() {
        return errorDescription;
    }

    public Optional<StringFilter> optionalErrorDescription() {
        return Optional.ofNullable(errorDescription);
    }

    public StringFilter errorDescription() {
        if (errorDescription == null) {
            setErrorDescription(new StringFilter());
        }
        return errorDescription;
    }

    public void setErrorDescription(StringFilter errorDescription) {
        this.errorDescription = errorDescription;
    }

    public StringFilter getGeolocation() {
        return geolocation;
    }

    public Optional<StringFilter> optionalGeolocation() {
        return Optional.ofNullable(geolocation);
    }

    public StringFilter geolocation() {
        if (geolocation == null) {
            setGeolocation(new StringFilter());
        }
        return geolocation;
    }

    public void setGeolocation(StringFilter geolocation) {
        this.geolocation = geolocation;
    }

    public StringFilter getHostCode() {
        return hostCode;
    }

    public Optional<StringFilter> optionalHostCode() {
        return Optional.ofNullable(hostCode);
    }

    public StringFilter hostCode() {
        if (hostCode == null) {
            setHostCode(new StringFilter());
        }
        return hostCode;
    }

    public void setHostCode(StringFilter hostCode) {
        this.hostCode = hostCode;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public Optional<StringFilter> optionalPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public StringFilter phoneNumber() {
        if (phoneNumber == null) {
            setPhoneNumber(new StringFilter());
        }
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter getResponseCode() {
        return responseCode;
    }

    public Optional<StringFilter> optionalResponseCode() {
        return Optional.ofNullable(responseCode);
    }

    public StringFilter responseCode() {
        if (responseCode == null) {
            setResponseCode(new StringFilter());
        }
        return responseCode;
    }

    public void setResponseCode(StringFilter responseCode) {
        this.responseCode = responseCode;
    }

    public StringFilter getResponseMessage() {
        return responseMessage;
    }

    public Optional<StringFilter> optionalResponseMessage() {
        return Optional.ofNullable(responseMessage);
    }

    public StringFilter responseMessage() {
        if (responseMessage == null) {
            setResponseMessage(new StringFilter());
        }
        return responseMessage;
    }

    public void setResponseMessage(StringFilter responseMessage) {
        this.responseMessage = responseMessage;
    }

    public StringFilter getTransactionCode() {
        return transactionCode;
    }

    public Optional<StringFilter> optionalTransactionCode() {
        return Optional.ofNullable(transactionCode);
    }

    public StringFilter transactionCode() {
        if (transactionCode == null) {
            setTransactionCode(new StringFilter());
        }
        return transactionCode;
    }

    public void setTransactionCode(StringFilter transactionCode) {
        this.transactionCode = transactionCode;
    }

    public StringFilter getTransactionType() {
        return transactionType;
    }

    public Optional<StringFilter> optionalTransactionType() {
        return Optional.ofNullable(transactionType);
    }

    public StringFilter transactionType() {
        if (transactionType == null) {
            setTransactionType(new StringFilter());
        }
        return transactionType;
    }

    public void setTransactionType(StringFilter transactionType) {
        this.transactionType = transactionType;
    }

    public StringFilter getUserAgent() {
        return userAgent;
    }

    public Optional<StringFilter> optionalUserAgent() {
        return Optional.ofNullable(userAgent);
    }

    public StringFilter userAgent() {
        if (userAgent == null) {
            setUserAgent(new StringFilter());
        }
        return userAgent;
    }

    public void setUserAgent(StringFilter userAgent) {
        this.userAgent = userAgent;
    }

    public StringFilter getUserAgentVersion() {
        return userAgentVersion;
    }

    public Optional<StringFilter> optionalUserAgentVersion() {
        return Optional.ofNullable(userAgentVersion);
    }

    public StringFilter userAgentVersion() {
        if (userAgentVersion == null) {
            setUserAgentVersion(new StringFilter());
        }
        return userAgentVersion;
    }

    public void setUserAgentVersion(StringFilter userAgentVersion) {
        this.userAgentVersion = userAgentVersion;
    }

    public StringFilter getAmount() {
        return amount;
    }

    public Optional<StringFilter> optionalAmount() {
        return Optional.ofNullable(amount);
    }

    public StringFilter amount() {
        if (amount == null) {
            setAmount(new StringFilter());
        }
        return amount;
    }

    public void setAmount(StringFilter amount) {
        this.amount = amount;
    }

    public StringFilter getChargeamount() {
        return chargeamount;
    }

    public Optional<StringFilter> optionalChargeamount() {
        return Optional.ofNullable(chargeamount);
    }

    public StringFilter chargeamount() {
        if (chargeamount == null) {
            setChargeamount(new StringFilter());
        }
        return chargeamount;
    }

    public void setChargeamount(StringFilter chargeamount) {
        this.chargeamount = chargeamount;
    }

    public StringFilter getCreditRccount() {
        return creditRccount;
    }

    public Optional<StringFilter> optionalCreditRccount() {
        return Optional.ofNullable(creditRccount);
    }

    public StringFilter creditRccount() {
        if (creditRccount == null) {
            setCreditRccount(new StringFilter());
        }
        return creditRccount;
    }

    public void setCreditRccount(StringFilter creditRccount) {
        this.creditRccount = creditRccount;
    }

    public StringFilter getCbsReference() {
        return cbsReference;
    }

    public Optional<StringFilter> optionalCbsReference() {
        return Optional.ofNullable(cbsReference);
    }

    public StringFilter cbsReference() {
        if (cbsReference == null) {
            setCbsReference(new StringFilter());
        }
        return cbsReference;
    }

    public void setCbsReference(StringFilter cbsReference) {
        this.cbsReference = cbsReference;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MobileAppTransactionsCriteria that = (MobileAppTransactionsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(channel, that.channel) &&
            Objects.equals(channelIp, that.channelIp) &&
            Objects.equals(channelReference, that.channelReference) &&
            Objects.equals(channelTimestamp, that.channelTimestamp) &&
            Objects.equals(clientId, that.clientId) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(debitAccount, that.debitAccount) &&
            Objects.equals(direction, that.direction) &&
            Objects.equals(errorDescription, that.errorDescription) &&
            Objects.equals(geolocation, that.geolocation) &&
            Objects.equals(hostCode, that.hostCode) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(responseCode, that.responseCode) &&
            Objects.equals(responseMessage, that.responseMessage) &&
            Objects.equals(transactionCode, that.transactionCode) &&
            Objects.equals(transactionType, that.transactionType) &&
            Objects.equals(userAgent, that.userAgent) &&
            Objects.equals(userAgentVersion, that.userAgentVersion) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(chargeamount, that.chargeamount) &&
            Objects.equals(creditRccount, that.creditRccount) &&
            Objects.equals(cbsReference, that.cbsReference) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            channel,
            channelIp,
            channelReference,
            channelTimestamp,
            clientId,
            createdAt,
            debitAccount,
            direction,
            errorDescription,
            geolocation,
            hostCode,
            phoneNumber,
            responseCode,
            responseMessage,
            transactionCode,
            transactionType,
            userAgent,
            userAgentVersion,
            amount,
            chargeamount,
            creditRccount,
            cbsReference,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MobileAppTransactionsCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalChannel().map(f -> "channel=" + f + ", ").orElse("") +
            optionalChannelIp().map(f -> "channelIp=" + f + ", ").orElse("") +
            optionalChannelReference().map(f -> "channelReference=" + f + ", ").orElse("") +
            optionalChannelTimestamp().map(f -> "channelTimestamp=" + f + ", ").orElse("") +
            optionalClientId().map(f -> "clientId=" + f + ", ").orElse("") +
            optionalCreatedAt().map(f -> "createdAt=" + f + ", ").orElse("") +
            optionalDebitAccount().map(f -> "debitAccount=" + f + ", ").orElse("") +
            optionalDirection().map(f -> "direction=" + f + ", ").orElse("") +
            optionalErrorDescription().map(f -> "errorDescription=" + f + ", ").orElse("") +
            optionalGeolocation().map(f -> "geolocation=" + f + ", ").orElse("") +
            optionalHostCode().map(f -> "hostCode=" + f + ", ").orElse("") +
            optionalPhoneNumber().map(f -> "phoneNumber=" + f + ", ").orElse("") +
            optionalResponseCode().map(f -> "responseCode=" + f + ", ").orElse("") +
            optionalResponseMessage().map(f -> "responseMessage=" + f + ", ").orElse("") +
            optionalTransactionCode().map(f -> "transactionCode=" + f + ", ").orElse("") +
            optionalTransactionType().map(f -> "transactionType=" + f + ", ").orElse("") +
            optionalUserAgent().map(f -> "userAgent=" + f + ", ").orElse("") +
            optionalUserAgentVersion().map(f -> "userAgentVersion=" + f + ", ").orElse("") +
            optionalAmount().map(f -> "amount=" + f + ", ").orElse("") +
            optionalChargeamount().map(f -> "chargeamount=" + f + ", ").orElse("") +
            optionalCreditRccount().map(f -> "creditRccount=" + f + ", ").orElse("") +
            optionalCbsReference().map(f -> "cbsReference=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
