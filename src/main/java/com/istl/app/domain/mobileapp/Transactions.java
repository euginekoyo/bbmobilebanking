package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transactions.
 */
@Entity
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "processed")
    private Long processed;

    @Size(max = 150)
    @Column(name = "incomingbitmap", length = 150)
    private String incomingbitmap;

    @Size(max = 150)
    @Column(name = "outgoingbitmap", length = 150)
    private String outgoingbitmap;

    @Size(max = 4000)
    @Column(name = "inmessage", length = 4000)
    private String inmessage;

    @Size(max = 4000)
    @Column(name = "messagetocbs", length = 4000)
    private String messagetocbs;

    @Size(max = 4000)
    @Column(name = "messagefromcbs", length = 4000)
    private String messagefromcbs;

    @Column(name = "cbsprocess")
    private Long cbsprocess;

    @Column(name = "cbsonline")
    private Long cbsonline;

    @Size(max = 500)
    @Column(name = "cbsresponse", length = 500)
    private String cbsresponse;

    @Size(max = 4000)
    @Column(name = "responsemessage", length = 4000)
    private String responsemessage;

    @Column(name = "responsesent")
    private Long responsesent;

    @Size(max = 20)
    @Column(name = "channel", length = 20)
    private String channel;

    @Size(max = 50)
    @Column(name = "originaldata", length = 50)
    private String originaldata;

    @Size(max = 150)
    @Column(name = "field_39_resp", length = 150)
    private String field39resp;

    @Size(max = 4000)
    @Column(name = "narration", length = 4000)
    private String narration;

    @Column(name = "authorised")
    private Long authorised;

    @Size(max = 30)
    @Column(name = "branchcode", length = 30)
    private String branchcode;

    @Size(max = 150)
    @Column(name = "field_39_original", length = 150)
    private String field39original;

    @Size(max = 10)
    @Column(name = "messageclass", length = 10)
    private String messageclass;

    @Size(max = 10)
    @Column(name = "txncode", length = 10)
    private String txncode;

    @Size(max = 5)
    @Column(name = "currcode", length = 5)
    private String currcode;

    @Size(max = 20)
    @Column(name = "device", length = 20)
    private String device;

    @Size(max = 30)
    @Column(name = "branch_2", length = 30)
    private String branch2;

    @Column(name = "longerbranch")
    private Long longerbranch;

    @Column(name = "datex")
    private Instant datex;

    @Size(max = 50)
    @Column(name = "timex", length = 50)
    private String timex;

    @Column(name = "posted")
    private Long posted;

    @Column(name = "attempts")
    private Long attempts;

    @Size(max = 100)
    @Column(name = "originaldata_2", length = 100)
    private String originaldata2;

    @Column(name = "commission")
    private Long commission;

    @Column(name = "responsecreated")
    private Long responsecreated;

    @Column(name = "online")
    private Long online;

    @Size(max = 100)
    @Column(name = "originaldata_3", length = 100)
    private String originaldata3;

    @Size(max = 15)
    @Column(name = "toswitch", length = 15)
    private String toswitch;

    @Size(max = 15)
    @Column(name = "fromswitch", length = 15)
    private String fromswitch;

    @Size(max = 15)
    @Column(name = "tocbs", length = 15)
    private String tocbs;

    @Size(max = 15)
    @Column(name = "fromcbs", length = 15)
    private String fromcbs;

    @Column(name = "postinglegs")
    private Long postinglegs;

    @Size(max = 10)
    @Column(name = "commissiontxncode", length = 10)
    private String commissiontxncode;

    @Size(max = 30)
    @Column(name = "hostref", length = 30)
    private String hostref;

    @Column(name = "requestcreated")
    private Long requestcreated;

    @Size(max = 4000)
    @Column(name = "requestmessage", length = 4000)
    private String requestmessage;

    @Size(max = 150)
    @Column(name = "outgoingbitmapflex", length = 150)
    private String outgoingbitmapflex;

    @Size(max = 150)
    @Column(name = "incomingbitmapflex", length = 150)
    private String incomingbitmapflex;

    @Column(name = "requestsent")
    private Long requestsent;

    @Column(name = "minicbs")
    private Long minicbs;

    @Column(name = "reversed")
    private Long reversed;

    @Column(name = "offlinesenttohost")
    private Long offlinesenttohost;

    @Size(max = 150)
    @Column(name = "offlineresponse", length = 150)
    private String offlineresponse;

    @Size(max = 40)
    @Column(name = "source_longerface", length = 40)
    private String sourceLongerface;

    @Size(max = 150)
    @Column(name = "mtirrn", length = 150)
    private String mtirrn;

    @Size(max = 200)
    @Column(name = "hostresponsecode", length = 200)
    private String hostresponsecode;

    @Size(max = 150)
    @Column(name = "field_48", length = 150)
    private String field48;

    @Size(max = 150)
    @Column(name = "source", length = 150)
    private String source;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transactions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessed() {
        return this.processed;
    }

    public Transactions processed(Long processed) {
        this.setProcessed(processed);
        return this;
    }

    public void setProcessed(Long processed) {
        this.processed = processed;
    }

    public String getIncomingbitmap() {
        return this.incomingbitmap;
    }

    public Transactions incomingbitmap(String incomingbitmap) {
        this.setIncomingbitmap(incomingbitmap);
        return this;
    }

    public void setIncomingbitmap(String incomingbitmap) {
        this.incomingbitmap = incomingbitmap;
    }

    public String getOutgoingbitmap() {
        return this.outgoingbitmap;
    }

    public Transactions outgoingbitmap(String outgoingbitmap) {
        this.setOutgoingbitmap(outgoingbitmap);
        return this;
    }

    public void setOutgoingbitmap(String outgoingbitmap) {
        this.outgoingbitmap = outgoingbitmap;
    }

    public String getInmessage() {
        return this.inmessage;
    }

    public Transactions inmessage(String inmessage) {
        this.setInmessage(inmessage);
        return this;
    }

    public void setInmessage(String inmessage) {
        this.inmessage = inmessage;
    }

    public String getMessagetocbs() {
        return this.messagetocbs;
    }

    public Transactions messagetocbs(String messagetocbs) {
        this.setMessagetocbs(messagetocbs);
        return this;
    }

    public void setMessagetocbs(String messagetocbs) {
        this.messagetocbs = messagetocbs;
    }

    public String getMessagefromcbs() {
        return this.messagefromcbs;
    }

    public Transactions messagefromcbs(String messagefromcbs) {
        this.setMessagefromcbs(messagefromcbs);
        return this;
    }

    public void setMessagefromcbs(String messagefromcbs) {
        this.messagefromcbs = messagefromcbs;
    }

    public Long getCbsprocess() {
        return this.cbsprocess;
    }

    public Transactions cbsprocess(Long cbsprocess) {
        this.setCbsprocess(cbsprocess);
        return this;
    }

    public void setCbsprocess(Long cbsprocess) {
        this.cbsprocess = cbsprocess;
    }

    public Long getCbsonline() {
        return this.cbsonline;
    }

    public Transactions cbsonline(Long cbsonline) {
        this.setCbsonline(cbsonline);
        return this;
    }

    public void setCbsonline(Long cbsonline) {
        this.cbsonline = cbsonline;
    }

    public String getCbsresponse() {
        return this.cbsresponse;
    }

    public Transactions cbsresponse(String cbsresponse) {
        this.setCbsresponse(cbsresponse);
        return this;
    }

    public void setCbsresponse(String cbsresponse) {
        this.cbsresponse = cbsresponse;
    }

    public String getResponsemessage() {
        return this.responsemessage;
    }

    public Transactions responsemessage(String responsemessage) {
        this.setResponsemessage(responsemessage);
        return this;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public Long getResponsesent() {
        return this.responsesent;
    }

    public Transactions responsesent(Long responsesent) {
        this.setResponsesent(responsesent);
        return this;
    }

    public void setResponsesent(Long responsesent) {
        this.responsesent = responsesent;
    }

    public String getChannel() {
        return this.channel;
    }

    public Transactions channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOriginaldata() {
        return this.originaldata;
    }

    public Transactions originaldata(String originaldata) {
        this.setOriginaldata(originaldata);
        return this;
    }

    public void setOriginaldata(String originaldata) {
        this.originaldata = originaldata;
    }

    public String getField39resp() {
        return this.field39resp;
    }

    public Transactions field39resp(String field39resp) {
        this.setField39resp(field39resp);
        return this;
    }

    public void setField39resp(String field39resp) {
        this.field39resp = field39resp;
    }

    public String getNarration() {
        return this.narration;
    }

    public Transactions narration(String narration) {
        this.setNarration(narration);
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Long getAuthorised() {
        return this.authorised;
    }

    public Transactions authorised(Long authorised) {
        this.setAuthorised(authorised);
        return this;
    }

    public void setAuthorised(Long authorised) {
        this.authorised = authorised;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public Transactions branchcode(String branchcode) {
        this.setBranchcode(branchcode);
        return this;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getField39original() {
        return this.field39original;
    }

    public Transactions field39original(String field39original) {
        this.setField39original(field39original);
        return this;
    }

    public void setField39original(String field39original) {
        this.field39original = field39original;
    }

    public String getMessageclass() {
        return this.messageclass;
    }

    public Transactions messageclass(String messageclass) {
        this.setMessageclass(messageclass);
        return this;
    }

    public void setMessageclass(String messageclass) {
        this.messageclass = messageclass;
    }

    public String getTxncode() {
        return this.txncode;
    }

    public Transactions txncode(String txncode) {
        this.setTxncode(txncode);
        return this;
    }

    public void setTxncode(String txncode) {
        this.txncode = txncode;
    }

    public String getCurrcode() {
        return this.currcode;
    }

    public Transactions currcode(String currcode) {
        this.setCurrcode(currcode);
        return this;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode;
    }

    public String getDevice() {
        return this.device;
    }

    public Transactions device(String device) {
        this.setDevice(device);
        return this;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBranch2() {
        return this.branch2;
    }

    public Transactions branch2(String branch2) {
        this.setBranch2(branch2);
        return this;
    }

    public void setBranch2(String branch2) {
        this.branch2 = branch2;
    }

    public Long getLongerbranch() {
        return this.longerbranch;
    }

    public Transactions longerbranch(Long longerbranch) {
        this.setLongerbranch(longerbranch);
        return this;
    }

    public void setLongerbranch(Long longerbranch) {
        this.longerbranch = longerbranch;
    }

    public Instant getDatex() {
        return this.datex;
    }

    public Transactions datex(Instant datex) {
        this.setDatex(datex);
        return this;
    }

    public void setDatex(Instant datex) {
        this.datex = datex;
    }

    public String getTimex() {
        return this.timex;
    }

    public Transactions timex(String timex) {
        this.setTimex(timex);
        return this;
    }

    public void setTimex(String timex) {
        this.timex = timex;
    }

    public Long getPosted() {
        return this.posted;
    }

    public Transactions posted(Long posted) {
        this.setPosted(posted);
        return this;
    }

    public void setPosted(Long posted) {
        this.posted = posted;
    }

    public Long getAttempts() {
        return this.attempts;
    }

    public Transactions attempts(Long attempts) {
        this.setAttempts(attempts);
        return this;
    }

    public void setAttempts(Long attempts) {
        this.attempts = attempts;
    }

    public String getOriginaldata2() {
        return this.originaldata2;
    }

    public Transactions originaldata2(String originaldata2) {
        this.setOriginaldata2(originaldata2);
        return this;
    }

    public void setOriginaldata2(String originaldata2) {
        this.originaldata2 = originaldata2;
    }

    public Long getCommission() {
        return this.commission;
    }

    public Transactions commission(Long commission) {
        this.setCommission(commission);
        return this;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public Long getResponsecreated() {
        return this.responsecreated;
    }

    public Transactions responsecreated(Long responsecreated) {
        this.setResponsecreated(responsecreated);
        return this;
    }

    public void setResponsecreated(Long responsecreated) {
        this.responsecreated = responsecreated;
    }

    public Long getOnline() {
        return this.online;
    }

    public Transactions online(Long online) {
        this.setOnline(online);
        return this;
    }

    public void setOnline(Long online) {
        this.online = online;
    }

    public String getOriginaldata3() {
        return this.originaldata3;
    }

    public Transactions originaldata3(String originaldata3) {
        this.setOriginaldata3(originaldata3);
        return this;
    }

    public void setOriginaldata3(String originaldata3) {
        this.originaldata3 = originaldata3;
    }

    public String getToswitch() {
        return this.toswitch;
    }

    public Transactions toswitch(String toswitch) {
        this.setToswitch(toswitch);
        return this;
    }

    public void setToswitch(String toswitch) {
        this.toswitch = toswitch;
    }

    public String getFromswitch() {
        return this.fromswitch;
    }

    public Transactions fromswitch(String fromswitch) {
        this.setFromswitch(fromswitch);
        return this;
    }

    public void setFromswitch(String fromswitch) {
        this.fromswitch = fromswitch;
    }

    public String getTocbs() {
        return this.tocbs;
    }

    public Transactions tocbs(String tocbs) {
        this.setTocbs(tocbs);
        return this;
    }

    public void setTocbs(String tocbs) {
        this.tocbs = tocbs;
    }

    public String getFromcbs() {
        return this.fromcbs;
    }

    public Transactions fromcbs(String fromcbs) {
        this.setFromcbs(fromcbs);
        return this;
    }

    public void setFromcbs(String fromcbs) {
        this.fromcbs = fromcbs;
    }

    public Long getPostinglegs() {
        return this.postinglegs;
    }

    public Transactions postinglegs(Long postinglegs) {
        this.setPostinglegs(postinglegs);
        return this;
    }

    public void setPostinglegs(Long postinglegs) {
        this.postinglegs = postinglegs;
    }

    public String getCommissiontxncode() {
        return this.commissiontxncode;
    }

    public Transactions commissiontxncode(String commissiontxncode) {
        this.setCommissiontxncode(commissiontxncode);
        return this;
    }

    public void setCommissiontxncode(String commissiontxncode) {
        this.commissiontxncode = commissiontxncode;
    }

    public String getHostref() {
        return this.hostref;
    }

    public Transactions hostref(String hostref) {
        this.setHostref(hostref);
        return this;
    }

    public void setHostref(String hostref) {
        this.hostref = hostref;
    }

    public Long getRequestcreated() {
        return this.requestcreated;
    }

    public Transactions requestcreated(Long requestcreated) {
        this.setRequestcreated(requestcreated);
        return this;
    }

    public void setRequestcreated(Long requestcreated) {
        this.requestcreated = requestcreated;
    }

    public String getRequestmessage() {
        return this.requestmessage;
    }

    public Transactions requestmessage(String requestmessage) {
        this.setRequestmessage(requestmessage);
        return this;
    }

    public void setRequestmessage(String requestmessage) {
        this.requestmessage = requestmessage;
    }

    public String getOutgoingbitmapflex() {
        return this.outgoingbitmapflex;
    }

    public Transactions outgoingbitmapflex(String outgoingbitmapflex) {
        this.setOutgoingbitmapflex(outgoingbitmapflex);
        return this;
    }

    public void setOutgoingbitmapflex(String outgoingbitmapflex) {
        this.outgoingbitmapflex = outgoingbitmapflex;
    }

    public String getIncomingbitmapflex() {
        return this.incomingbitmapflex;
    }

    public Transactions incomingbitmapflex(String incomingbitmapflex) {
        this.setIncomingbitmapflex(incomingbitmapflex);
        return this;
    }

    public void setIncomingbitmapflex(String incomingbitmapflex) {
        this.incomingbitmapflex = incomingbitmapflex;
    }

    public Long getRequestsent() {
        return this.requestsent;
    }

    public Transactions requestsent(Long requestsent) {
        this.setRequestsent(requestsent);
        return this;
    }

    public void setRequestsent(Long requestsent) {
        this.requestsent = requestsent;
    }

    public Long getMinicbs() {
        return this.minicbs;
    }

    public Transactions minicbs(Long minicbs) {
        this.setMinicbs(minicbs);
        return this;
    }

    public void setMinicbs(Long minicbs) {
        this.minicbs = minicbs;
    }

    public Long getReversed() {
        return this.reversed;
    }

    public Transactions reversed(Long reversed) {
        this.setReversed(reversed);
        return this;
    }

    public void setReversed(Long reversed) {
        this.reversed = reversed;
    }

    public Long getOfflinesenttohost() {
        return this.offlinesenttohost;
    }

    public Transactions offlinesenttohost(Long offlinesenttohost) {
        this.setOfflinesenttohost(offlinesenttohost);
        return this;
    }

    public void setOfflinesenttohost(Long offlinesenttohost) {
        this.offlinesenttohost = offlinesenttohost;
    }

    public String getOfflineresponse() {
        return this.offlineresponse;
    }

    public Transactions offlineresponse(String offlineresponse) {
        this.setOfflineresponse(offlineresponse);
        return this;
    }

    public void setOfflineresponse(String offlineresponse) {
        this.offlineresponse = offlineresponse;
    }

    public String getSourceLongerface() {
        return this.sourceLongerface;
    }

    public Transactions sourceLongerface(String sourceLongerface) {
        this.setSourceLongerface(sourceLongerface);
        return this;
    }

    public void setSourceLongerface(String sourceLongerface) {
        this.sourceLongerface = sourceLongerface;
    }

    public String getMtirrn() {
        return this.mtirrn;
    }

    public Transactions mtirrn(String mtirrn) {
        this.setMtirrn(mtirrn);
        return this;
    }

    public void setMtirrn(String mtirrn) {
        this.mtirrn = mtirrn;
    }

    public String getHostresponsecode() {
        return this.hostresponsecode;
    }

    public Transactions hostresponsecode(String hostresponsecode) {
        this.setHostresponsecode(hostresponsecode);
        return this;
    }

    public void setHostresponsecode(String hostresponsecode) {
        this.hostresponsecode = hostresponsecode;
    }

    public String getField48() {
        return this.field48;
    }

    public Transactions field48(String field48) {
        this.setField48(field48);
        return this;
    }

    public void setField48(String field48) {
        this.field48 = field48;
    }

    public String getSource() {
        return this.source;
    }

    public Transactions source(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transactions)) {
            return false;
        }
        return getId() != null && getId().equals(((Transactions) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + getId() +
            ", processed=" + getProcessed() +
            ", incomingbitmap='" + getIncomingbitmap() + "'" +
            ", outgoingbitmap='" + getOutgoingbitmap() + "'" +
            ", inmessage='" + getInmessage() + "'" +
            ", messagetocbs='" + getMessagetocbs() + "'" +
            ", messagefromcbs='" + getMessagefromcbs() + "'" +
            ", cbsprocess=" + getCbsprocess() +
            ", cbsonline=" + getCbsonline() +
            ", cbsresponse='" + getCbsresponse() + "'" +
            ", responsemessage='" + getResponsemessage() + "'" +
            ", responsesent=" + getResponsesent() +
            ", channel='" + getChannel() + "'" +
            ", originaldata='" + getOriginaldata() + "'" +
            ", field39resp='" + getField39resp() + "'" +
            ", narration='" + getNarration() + "'" +
            ", authorised=" + getAuthorised() +
            ", branchcode='" + getBranchcode() + "'" +
            ", field39original='" + getField39original() + "'" +
            ", messageclass='" + getMessageclass() + "'" +
            ", txncode='" + getTxncode() + "'" +
            ", currcode='" + getCurrcode() + "'" +
            ", device='" + getDevice() + "'" +
            ", branch2='" + getBranch2() + "'" +
            ", longerbranch=" + getLongerbranch() +
            ", datex='" + getDatex() + "'" +
            ", timex='" + getTimex() + "'" +
            ", posted=" + getPosted() +
            ", attempts=" + getAttempts() +
            ", originaldata2='" + getOriginaldata2() + "'" +
            ", commission=" + getCommission() +
            ", responsecreated=" + getResponsecreated() +
            ", online=" + getOnline() +
            ", originaldata3='" + getOriginaldata3() + "'" +
            ", toswitch='" + getToswitch() + "'" +
            ", fromswitch='" + getFromswitch() + "'" +
            ", tocbs='" + getTocbs() + "'" +
            ", fromcbs='" + getFromcbs() + "'" +
            ", postinglegs=" + getPostinglegs() +
            ", commissiontxncode='" + getCommissiontxncode() + "'" +
            ", hostref='" + getHostref() + "'" +
            ", requestcreated=" + getRequestcreated() +
            ", requestmessage='" + getRequestmessage() + "'" +
            ", outgoingbitmapflex='" + getOutgoingbitmapflex() + "'" +
            ", incomingbitmapflex='" + getIncomingbitmapflex() + "'" +
            ", requestsent=" + getRequestsent() +
            ", minicbs=" + getMinicbs() +
            ", reversed=" + getReversed() +
            ", offlinesenttohost=" + getOfflinesenttohost() +
            ", offlineresponse='" + getOfflineresponse() + "'" +
            ", sourceLongerface='" + getSourceLongerface() + "'" +
            ", mtirrn='" + getMtirrn() + "'" +
            ", hostresponsecode='" + getHostresponsecode() + "'" +
            ", field48='" + getField48() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }
}
