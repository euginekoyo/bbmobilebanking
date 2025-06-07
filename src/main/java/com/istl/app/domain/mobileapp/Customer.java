package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;

    @Size(max = 200)
    @Column(name = "customername", length = 200)
    private String customername;

    @NotNull
    @Size(max = 12)
    @Column(name = "phonenumber", length = 12, nullable = false)
    private String phonenumber;

    @Size(max = 1000)
    @Column(name = "cardnumber", length = 1000)
    private String cardnumber;

    @NotNull
    @Size(max = 20)
    @Column(name = "accountnumber", length = 20, nullable = false)
    private String accountnumber;

    @Size(max = 10)
    @Column(name = "lang", length = 10)
    private String lang;

    @Size(max = 200)
    @Column(name = "pin", length = 200)
    private String pin;

    @Size(max = 1)
    @Column(name = "firstlogin", length = 1)
    private String firstlogin;

    @Size(max = 1)
    @Column(name = "active", length = 1)
    private String active;

    @Column(name = "registered")
    private Long registered;

    @Column(name = "cstdelete")
    private Long cstdelete;

    @Column(name = "regdate")
    private Instant regdate;

    @Column(name = "alertenabled")
    private Long alertenabled;

    @Size(max = 200)
    @Column(name = "remark", length = 200)
    private String remark;

    @Size(max = 200)
    @Column(name = "imsi", length = 200)
    private String imsi;

    @Size(max = 1)
    @Column(name = "partiallyregistered", length = 1)
    private String partiallyregistered;

    @Column(name = "partialdate")
    private Instant partialdate;

    @Column(name = "registerdate")
    private Instant registerdate;

    @Column(name = "approved")
    private Double approved;

    @Size(max = 50)
    @Column(name = "approvedby", length = 50)
    private String approvedby;

    @Column(name = "approveddate")
    private Instant approveddate;

    @Column(name = "declined")
    private Double declined;

    @Size(max = 50)
    @Column(name = "declinedby", length = 50)
    private String declinedby;

    @Column(name = "declineddate")
    private Instant declineddate;

    @Size(max = 200)
    @Column(name = "checkerremarks", length = 200)
    private String checkerremarks;

    @Size(max = 50)
    @Column(name = "postaladdress", length = 50)
    private String postaladdress;

    @Size(max = 50)
    @Column(name = "residence", length = 50)
    private String residence;

    @Column(name = "dob")
    private Instant dob;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Size(max = 50)
    @Column(name = "emailaddress", length = 50)
    private String emailaddress;

    @Size(max = 50)
    @Column(name = "identificationid", length = 50)
    private String identificationid;

    @Column(name = "addaccount")
    private Double addaccount;

    @Size(max = 50)
    @Column(name = "aclinkinginstitution", length = 50)
    private String aclinkinginstitution;

    @Column(name = "deactivated")
    private Double deactivated;

    @Size(max = 50)
    @Column(name = "deactivatedby", length = 50)
    private String deactivatedby;

    @Column(name = "deactivatedon")
    private Instant deactivatedon;

    @Column(name = "phonenochanged")
    private Double phonenochanged;

    @Size(max = 50)
    @Column(name = "phonenochangedby", length = 50)
    private String phonenochangedby;

    @Column(name = "phonenochangedon")
    private Instant phonenochangedon;

    @Size(max = 20)
    @Column(name = "originalphoneno", length = 20)
    private String originalphoneno;

    @Size(max = 20)
    @Column(name = "newphoneno", length = 20)
    private String newphoneno;

    @Column(name = "reset")
    private Double reset;

    @Size(max = 50)
    @Column(name = "resetinginstitution", length = 50)
    private String resetinginstitution;

    @Size(max = 50)
    @Column(name = "pinresetremark", length = 50)
    private String pinresetremark;

    @Size(max = 50)
    @Column(name = "resetby", length = 50)
    private String resetby;

    @Column(name = "reseton")
    private Instant reseton;

    @Size(max = 50)
    @Column(name = "unblockinginstitution", length = 50)
    private String unblockinginstitution;

    @Column(name = "pinblock")
    private Double pinblock;

    @Size(max = 50)
    @Column(name = "pinblockby", length = 50)
    private String pinblockby;

    @Size(max = 200)
    @Column(name = "pinblockremarks", length = 200)
    private String pinblockremarks;

    @Size(max = 50)
    @Column(name = "blockinginstitution", length = 50)
    private String blockinginstitution;

    @Column(name = "pinblockon")
    private Instant pinblockon;

    @Column(name = "approvedon")
    private Instant approvedon;

    @Size(max = 50)
    @Column(name = "pinunblockby", length = 50)
    private String pinunblockby;

    @Column(name = "loggedin")
    private Long loggedin;

    @Size(max = 50)
    @Column(name = "trials", length = 50)
    private String trials;

    @Size(max = 20)
    @Column(name = "idtype", length = 20)
    private String idtype;

    @Size(max = 20)
    @Column(name = "idnumber", length = 20)
    private String idnumber;

    @Size(max = 1)
    @Column(name = "gender", length = 1)
    private String gender;

    @Size(max = 20)
    @Column(name = "cif", length = 20)
    private String cif;

    @Column(name = "dateofbirth")
    private Instant dateofbirth;

    @Size(max = 200)
    @Column(name = "remarks", length = 200)
    private String remarks;

    @Column(name = "resetimsi")
    private Double resetimsi;

    @Size(max = 50)
    @Column(name = "imsiresetby", length = 50)
    private String imsiresetby;

    @Size(max = 200)
    @Column(name = "firstname", length = 200)
    private String firstname;

    @Size(max = 200)
    @Column(name = "secondname", length = 200)
    private String secondname;

    @Size(max = 200)
    @Column(name = "lastname", length = 200)
    private String lastname;

    @Size(max = 7)
    @Column(name = "pinblocktime", length = 7)
    private String pinblocktime;

    @Size(max = 50)
    @Column(name = "customerstatus", length = 50)
    private String customerstatus;

    @Size(max = 2000)
    @Column(name = "username", length = 2000)
    private String username;

    @Size(max = 3900)
    @Column(name = "password", length = 3900)
    private String password;

    @Size(max = 50)
    @Column(name = "deviceid", length = 50)
    private String deviceid;

    @Size(max = 50)
    @Column(name = "channel", length = 50)
    private String channel;

    @Column(name = "passreset")
    private Double passreset;

    @Size(max = 50)
    @Column(name = "passresetby", length = 50)
    private String passresetby;

    @Column(name = "passreseton")
    private Instant passreseton;

    @Column(name = "passblock")
    private Double passblock;

    @Size(max = 50)
    @Column(name = "passblockby", length = 50)
    private String passblockby;

    @Column(name = "passblockon")
    private Instant passblockon;

    @Column(name = "pinmarkblock")
    private Double pinmarkblock;

    @Column(name = "passmarkblock")
    private Double passmarkblock;

    @Size(max = 50)
    @Column(name = "passresetremarks", length = 50)
    private String passresetremarks;

    @Size(max = 50)
    @Column(name = "passblockremarks", length = 50)
    private String passblockremarks;

    @Size(max = 50)
    @Column(name = "passunblockby", length = 50)
    private String passunblockby;

    @Column(name = "passtrials")
    private Double passtrials;

    @Column(name = "appactive")
    private Long appactive;

    @Size(max = 32)
    @Column(name = "lastlogin", length = 32)
    private String lastlogin;

    @Column(name = "appmarkeddisable")
    private Double appmarkeddisable;

    @Size(max = 50)
    @Column(name = "disableby", length = 50)
    private String disableby;

    @Size(max = 50)
    @Column(name = "approvedisableby", length = 50)
    private String approvedisableby;

    @Column(name = "appmarkedenable")
    private Double appmarkedenable;

    @Size(max = 50)
    @Column(name = "enableby", length = 50)
    private String enableby;

    @Size(max = 50)
    @Column(name = "approvedenableby", length = 50)
    private String approvedenableby;

    @Column(name = "markeddeactivate")
    private Double markeddeactivate;

    @Size(max = 5)
    @Column(name = "appfirstlogin", length = 5)
    private String appfirstlogin;

    @Column(name = "atmtrials")
    private Double atmtrials;

    @Size(max = 1000)
    @Column(name = "shorcuts", length = 1000)
    private String shorcuts;

    @Size(max = 50)
    @Column(name = "markedactivate", length = 50)
    private String markedactivate;

    @Size(max = 50)
    @Column(name = "town", length = 50)
    private String town;

    @Column(name = "approveddisableon")
    private Instant approveddisableon;

    @Column(name = "disabledon")
    private Instant disabledon;

    @Column(name = "resetapproveon")
    private Instant resetapproveon;

    @Size(max = 50)
    @Column(name = "deletedby", length = 50)
    private String deletedby;

    @Size(max = 50)
    @Column(name = "questionsasked", length = 50)
    private String questionsasked;

    @Size(max = 50)
    @Column(name = "questionstrials", length = 50)
    private String questionstrials;

    @Size(max = 50)
    @Column(name = "questionsanswered", length = 50)
    private String questionsanswered;

    @Column(name = "validotp")
    private Double validotp;

    @Size(max = 50)
    @Column(name = "activatedby", length = 50)
    private String activatedby;

    @Column(name = "activatedon")
    private Instant activatedon;

    @Size(max = 50)
    @Column(name = "branchcode", length = 50)
    private String branchcode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    @Transient // Not persisted in the database
    private Double approveReset;

    public Double getApproveReset() {
        return approveReset;
    }

    public void setApproveReset(Double approveReset) {
        this.approveReset = approveReset;
    }

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Customer customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public Customer phonenumber(String phonenumber) {
        this.setPhonenumber(phonenumber);
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCardnumber() {
        return this.cardnumber;
    }

    public Customer cardnumber(String cardnumber) {
        this.setCardnumber(cardnumber);
        return this;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getAccountnumber() {
        return this.accountnumber;
    }

    public Customer accountnumber(String accountnumber) {
        this.setAccountnumber(accountnumber);
        return this;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getLang() {
        return this.lang;
    }

    public Customer lang(String lang) {
        this.setLang(lang);
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPin() {
        return this.pin;
    }

    public Customer pin(String pin) {
        this.setPin(pin);
        return this;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFirstlogin() {
        return this.firstlogin;
    }

    public Customer firstlogin(String firstlogin) {
        this.setFirstlogin(firstlogin);
        return this;
    }

    public void setFirstlogin(String firstlogin) {
        this.firstlogin = firstlogin;
    }

    public String getActive() {
        return this.active;
    }

    public Customer active(String active) {
        this.setActive(active);
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Long getRegistered() {
        return this.registered;
    }

    public Customer registered(Long registered) {
        this.setRegistered(registered);
        return this;
    }

    public void setRegistered(Long registered) {
        this.registered = registered;
    }

    public Long getCstdelete() {
        return this.cstdelete;
    }

    public Customer cstdelete(Long cstdelete) {
        this.setCstdelete(cstdelete);
        return this;
    }

    public void setCstdelete(Long cstdelete) {
        this.cstdelete = cstdelete;
    }

    public Instant getRegdate() {
        return this.regdate;
    }

    public Customer regdate(Instant regdate) {
        this.setRegdate(regdate);
        return this;
    }

    public void setRegdate(Instant regdate) {
        this.regdate = regdate;
    }

    public Long getAlertenabled() {
        return this.alertenabled;
    }

    public Customer alertenabled(Long alertenabled) {
        this.setAlertenabled(alertenabled);
        return this;
    }

    public void setAlertenabled(Long alertenabled) {
        this.alertenabled = alertenabled;
    }

    public String getRemark() {
        return this.remark;
    }

    public Customer remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImsi() {
        return this.imsi;
    }

    public Customer imsi(String imsi) {
        this.setImsi(imsi);
        return this;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPartiallyregistered() {
        return this.partiallyregistered;
    }

    public Customer partiallyregistered(String partiallyregistered) {
        this.setPartiallyregistered(partiallyregistered);
        return this;
    }

    public void setPartiallyregistered(String partiallyregistered) {
        this.partiallyregistered = partiallyregistered;
    }

    public Instant getPartialdate() {
        return this.partialdate;
    }

    public Customer partialdate(Instant partialdate) {
        this.setPartialdate(partialdate);
        return this;
    }

    public void setPartialdate(Instant partialdate) {
        this.partialdate = partialdate;
    }

    public Instant getRegisterdate() {
        return this.registerdate;
    }

    public Customer registerdate(Instant registerdate) {
        this.setRegisterdate(registerdate);
        return this;
    }

    public void setRegisterdate(Instant registerdate) {
        this.registerdate = registerdate;
    }

    public Double getApproved() {
        return this.approved;
    }

    public Customer approved(Double approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Double approved) {
        this.approved = approved;
    }

    public String getApprovedby() {
        return this.approvedby;
    }

    public Customer approvedby(String approvedby) {
        this.setApprovedby(approvedby);
        return this;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Instant getApproveddate() {
        return this.approveddate;
    }

    public Customer approveddate(Instant approveddate) {
        this.setApproveddate(approveddate);
        return this;
    }

    public void setApproveddate(Instant approveddate) {
        this.approveddate = approveddate;
    }

    public Double getDeclined() {
        return this.declined;
    }

    public Customer declined(Double declined) {
        this.setDeclined(declined);
        return this;
    }

    public void setDeclined(Double declined) {
        this.declined = declined;
    }

    public String getDeclinedby() {
        return this.declinedby;
    }

    public Customer declinedby(String declinedby) {
        this.setDeclinedby(declinedby);
        return this;
    }

    public void setDeclinedby(String declinedby) {
        this.declinedby = declinedby;
    }

    public Instant getDeclineddate() {
        return this.declineddate;
    }

    public Customer declineddate(Instant declineddate) {
        this.setDeclineddate(declineddate);
        return this;
    }

    public void setDeclineddate(Instant declineddate) {
        this.declineddate = declineddate;
    }

    public String getCheckerremarks() {
        return this.checkerremarks;
    }

    public Customer checkerremarks(String checkerremarks) {
        this.setCheckerremarks(checkerremarks);
        return this;
    }

    public void setCheckerremarks(String checkerremarks) {
        this.checkerremarks = checkerremarks;
    }

    public String getPostaladdress() {
        return this.postaladdress;
    }

    public Customer postaladdress(String postaladdress) {
        this.setPostaladdress(postaladdress);
        return this;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public String getResidence() {
        return this.residence;
    }

    public Customer residence(String residence) {
        this.setResidence(residence);
        return this;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Instant getDob() {
        return this.dob;
    }

    public Customer dob(Instant dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public Customer createdby(String createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getEmailaddress() {
        return this.emailaddress;
    }

    public Customer emailaddress(String emailaddress) {
        this.setEmailaddress(emailaddress);
        return this;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getIdentificationid() {
        return this.identificationid;
    }

    public Customer identificationid(String identificationid) {
        this.setIdentificationid(identificationid);
        return this;
    }

    public void setIdentificationid(String identificationid) {
        this.identificationid = identificationid;
    }

    public Double getAddaccount() {
        return this.addaccount;
    }

    public Customer addaccount(Double addaccount) {
        this.setAddaccount(addaccount);
        return this;
    }

    public void setAddaccount(Double addaccount) {
        this.addaccount = addaccount;
    }

    public String getAclinkinginstitution() {
        return this.aclinkinginstitution;
    }

    public Customer aclinkinginstitution(String aclinkinginstitution) {
        this.setAclinkinginstitution(aclinkinginstitution);
        return this;
    }

    public void setAclinkinginstitution(String aclinkinginstitution) {
        this.aclinkinginstitution = aclinkinginstitution;
    }

    public Double getDeactivated() {
        return this.deactivated;
    }

    public Customer deactivated(Double deactivated) {
        this.setDeactivated(deactivated);
        return this;
    }

    public void setDeactivated(Double deactivated) {
        this.deactivated = deactivated;
    }

    public String getDeactivatedby() {
        return this.deactivatedby;
    }

    public Customer deactivatedby(String deactivatedby) {
        this.setDeactivatedby(deactivatedby);
        return this;
    }

    public void setDeactivatedby(String deactivatedby) {
        this.deactivatedby = deactivatedby;
    }

    public Instant getDeactivatedon() {
        return this.deactivatedon;
    }

    public Customer deactivatedon(Instant deactivatedon) {
        this.setDeactivatedon(deactivatedon);
        return this;
    }

    public void setDeactivatedon(Instant deactivatedon) {
        this.deactivatedon = deactivatedon;
    }

    public Double getPhonenochanged() {
        return this.phonenochanged;
    }

    public Customer phonenochanged(Double phonenochanged) {
        this.setPhonenochanged(phonenochanged);
        return this;
    }

    public void setPhonenochanged(Double phonenochanged) {
        this.phonenochanged = phonenochanged;
    }

    public String getPhonenochangedby() {
        return this.phonenochangedby;
    }

    public Customer phonenochangedby(String phonenochangedby) {
        this.setPhonenochangedby(phonenochangedby);
        return this;
    }

    public void setPhonenochangedby(String phonenochangedby) {
        this.phonenochangedby = phonenochangedby;
    }

    public Instant getPhonenochangedon() {
        return this.phonenochangedon;
    }

    public Customer phonenochangedon(Instant phonenochangedon) {
        this.setPhonenochangedon(phonenochangedon);
        return this;
    }

    public void setPhonenochangedon(Instant phonenochangedon) {
        this.phonenochangedon = phonenochangedon;
    }

    public String getOriginalphoneno() {
        return this.originalphoneno;
    }

    public Customer originalphoneno(String originalphoneno) {
        this.setOriginalphoneno(originalphoneno);
        return this;
    }

    public void setOriginalphoneno(String originalphoneno) {
        this.originalphoneno = originalphoneno;
    }

    public String getNewphoneno() {
        return this.newphoneno;
    }

    public Customer newphoneno(String newphoneno) {
        this.setNewphoneno(newphoneno);
        return this;
    }

    public void setNewphoneno(String newphoneno) {
        this.newphoneno = newphoneno;
    }

    public Double getReset() {
        return this.reset;
    }

    public Customer reset(Double reset) {
        this.setReset(reset);
        return this;
    }

    public void setReset(Double reset) {
        this.reset = reset;
    }

    public String getResetinginstitution() {
        return this.resetinginstitution;
    }

    public Customer resetinginstitution(String resetinginstitution) {
        this.setResetinginstitution(resetinginstitution);
        return this;
    }

    public void setResetinginstitution(String resetinginstitution) {
        this.resetinginstitution = resetinginstitution;
    }

    public String getPinresetremark() {
        return this.pinresetremark;
    }

    public Customer pinresetremark(String pinresetremark) {
        this.setPinresetremark(pinresetremark);
        return this;
    }

    public void setPinresetremark(String pinresetremark) {
        this.pinresetremark = pinresetremark;
    }

    public String getResetby() {
        return this.resetby;
    }

    public Customer resetby(String resetby) {
        this.setResetby(resetby);
        return this;
    }

    public void setResetby(String resetby) {
        this.resetby = resetby;
    }

    public Instant getReseton() {
        return this.reseton;
    }

    public Customer reseton(Instant reseton) {
        this.setReseton(reseton);
        return this;
    }

    public void setReseton(Instant reseton) {
        this.reseton = reseton;
    }

    public String getUnblockinginstitution() {
        return this.unblockinginstitution;
    }

    public Customer unblockinginstitution(String unblockinginstitution) {
        this.setUnblockinginstitution(unblockinginstitution);
        return this;
    }

    public void setUnblockinginstitution(String unblockinginstitution) {
        this.unblockinginstitution = unblockinginstitution;
    }

    public Double getPinblock() {
        return this.pinblock;
    }

    public Customer pinblock(Double pinblock) {
        this.setPinblock(pinblock);
        return this;
    }

    public void setPinblock(Double pinblock) {
        this.pinblock = pinblock;
    }

    public String getPinblockby() {
        return this.pinblockby;
    }

    public Customer pinblockby(String pinblockby) {
        this.setPinblockby(pinblockby);
        return this;
    }

    public void setPinblockby(String pinblockby) {
        this.pinblockby = pinblockby;
    }

    public String getPinblockremarks() {
        return this.pinblockremarks;
    }

    public Customer pinblockremarks(String pinblockremarks) {
        this.setPinblockremarks(pinblockremarks);
        return this;
    }

    public void setPinblockremarks(String pinblockremarks) {
        this.pinblockremarks = pinblockremarks;
    }

    public String getBlockinginstitution() {
        return this.blockinginstitution;
    }

    public Customer blockinginstitution(String blockinginstitution) {
        this.setBlockinginstitution(blockinginstitution);
        return this;
    }

    public void setBlockinginstitution(String blockinginstitution) {
        this.blockinginstitution = blockinginstitution;
    }

    public Instant getPinblockon() {
        return this.pinblockon;
    }

    public Customer pinblockon(Instant pinblockon) {
        this.setPinblockon(pinblockon);
        return this;
    }

    public void setPinblockon(Instant pinblockon) {
        this.pinblockon = pinblockon;
    }

    public Instant getApprovedon() {
        return this.approvedon;
    }

    public Customer approvedon(Instant approvedon) {
        this.setApprovedon(approvedon);
        return this;
    }

    public void setApprovedon(Instant approvedon) {
        this.approvedon = approvedon;
    }

    public String getPinunblockby() {
        return this.pinunblockby;
    }

    public Customer pinunblockby(String pinunblockby) {
        this.setPinunblockby(pinunblockby);
        return this;
    }

    public void setPinunblockby(String pinunblockby) {
        this.pinunblockby = pinunblockby;
    }

    public Long getLoggedin() {
        return this.loggedin;
    }

    public Customer loggedin(Long loggedin) {
        this.setLoggedin(loggedin);
        return this;
    }

    public void setLoggedin(Long loggedin) {
        this.loggedin = loggedin;
    }

    public String getTrials() {
        return this.trials;
    }

    public Customer trials(String trials) {
        this.setTrials(trials);
        return this;
    }

    public void setTrials(String trials) {
        this.trials = trials;
    }

    public String getIdtype() {
        return this.idtype;
    }

    public Customer idtype(String idtype) {
        this.setIdtype(idtype);
        return this;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdnumber() {
        return this.idnumber;
    }

    public Customer idnumber(String idnumber) {
        this.setIdnumber(idnumber);
        return this;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getGender() {
        return this.gender;
    }

    public Customer gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCif() {
        return this.cif;
    }

    public Customer cif(String cif) {
        this.setCif(cif);
        return this;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Instant getDateofbirth() {
        return this.dateofbirth;
    }

    public Customer dateofbirth(Instant dateofbirth) {
        this.setDateofbirth(dateofbirth);
        return this;
    }

    public void setDateofbirth(Instant dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Customer remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getResetimsi() {
        return this.resetimsi;
    }

    public Customer resetimsi(Double resetimsi) {
        this.setResetimsi(resetimsi);
        return this;
    }

    public void setResetimsi(Double resetimsi) {
        this.resetimsi = resetimsi;
    }

    public String getImsiresetby() {
        return this.imsiresetby;
    }

    public Customer imsiresetby(String imsiresetby) {
        this.setImsiresetby(imsiresetby);
        return this;
    }

    public void setImsiresetby(String imsiresetby) {
        this.imsiresetby = imsiresetby;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Customer firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return this.secondname;
    }

    public Customer secondname(String secondname) {
        this.setSecondname(secondname);
        return this;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Customer lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPinblocktime() {
        return this.pinblocktime;
    }

    public Customer pinblocktime(String pinblocktime) {
        this.setPinblocktime(pinblocktime);
        return this;
    }

    public void setPinblocktime(String pinblocktime) {
        this.pinblocktime = pinblocktime;
    }

    public String getCustomerstatus() {
        return this.customerstatus;
    }

    public Customer customerstatus(String customerstatus) {
        this.setCustomerstatus(customerstatus);
        return this;
    }

    public void setCustomerstatus(String customerstatus) {
        this.customerstatus = customerstatus;
    }

    public String getUsername() {
        return this.username;
    }

    public Customer username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public Customer password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public Customer deviceid(String deviceid) {
        this.setDeviceid(deviceid);
        return this;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getChannel() {
        return this.channel;
    }

    public Customer channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Double getPassreset() {
        return this.passreset;
    }

    public Customer passreset(Double passreset) {
        this.setPassreset(passreset);
        return this;
    }

    public void setPassreset(Double passreset) {
        this.passreset = passreset;
    }

    public String getPassresetby() {
        return this.passresetby;
    }

    public Customer passresetby(String passresetby) {
        this.setPassresetby(passresetby);
        return this;
    }

    public void setPassresetby(String passresetby) {
        this.passresetby = passresetby;
    }

    public Instant getPassreseton() {
        return this.passreseton;
    }

    public Customer passreseton(Instant passreseton) {
        this.setPassreseton(passreseton);
        return this;
    }

    public void setPassreseton(Instant passreseton) {
        this.passreseton = passreseton;
    }

    public Double getPassblock() {
        return this.passblock;
    }

    public Customer passblock(Double passblock) {
        this.setPassblock(passblock);
        return this;
    }

    public void setPassblock(Double passblock) {
        this.passblock = passblock;
    }

    public String getPassblockby() {
        return this.passblockby;
    }

    public Customer passblockby(String passblockby) {
        this.setPassblockby(passblockby);
        return this;
    }

    public void setPassblockby(String passblockby) {
        this.passblockby = passblockby;
    }

    public Instant getPassblockon() {
        return this.passblockon;
    }

    public Customer passblockon(Instant passblockon) {
        this.setPassblockon(passblockon);
        return this;
    }

    public void setPassblockon(Instant passblockon) {
        this.passblockon = passblockon;
    }

    public Double getPinmarkblock() {
        return this.pinmarkblock;
    }

    public Customer pinmarkblock(Double pinmarkblock) {
        this.setPinmarkblock(pinmarkblock);
        return this;
    }

    public void setPinmarkblock(Double pinmarkblock) {
        this.pinmarkblock = pinmarkblock;
    }

    public Double getPassmarkblock() {
        return this.passmarkblock;
    }

    public Customer passmarkblock(Double passmarkblock) {
        this.setPassmarkblock(passmarkblock);
        return this;
    }

    public void setPassmarkblock(Double passmarkblock) {
        this.passmarkblock = passmarkblock;
    }

    public String getPassresetremarks() {
        return this.passresetremarks;
    }

    public Customer passresetremarks(String passresetremarks) {
        this.setPassresetremarks(passresetremarks);
        return this;
    }

    public void setPassresetremarks(String passresetremarks) {
        this.passresetremarks = passresetremarks;
    }

    public String getPassblockremarks() {
        return this.passblockremarks;
    }

    public Customer passblockremarks(String passblockremarks) {
        this.setPassblockremarks(passblockremarks);
        return this;
    }

    public void setPassblockremarks(String passblockremarks) {
        this.passblockremarks = passblockremarks;
    }

    public String getPassunblockby() {
        return this.passunblockby;
    }

    public Customer passunblockby(String passunblockby) {
        this.setPassunblockby(passunblockby);
        return this;
    }

    public void setPassunblockby(String passunblockby) {
        this.passunblockby = passunblockby;
    }

    public Double getPasstrials() {
        return this.passtrials;
    }

    public Customer passtrials(Double passtrials) {
        this.setPasstrials(passtrials);
        return this;
    }

    public void setPasstrials(Double passtrials) {
        this.passtrials = passtrials;
    }

    public Long getAppactive() {
        return this.appactive;
    }

    public Customer appactive(Long appactive) {
        this.setAppactive(appactive);
        return this;
    }

    public void setAppactive(Long appactive) {
        this.appactive = appactive;
    }

    public String getLastlogin() {
        return this.lastlogin;
    }

    public Customer lastlogin(String lastlogin) {
        this.setLastlogin(lastlogin);
        return this;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Double getAppmarkeddisable() {
        return this.appmarkeddisable;
    }

    public Customer appmarkeddisable(Double appmarkeddisable) {
        this.setAppmarkeddisable(appmarkeddisable);
        return this;
    }

    public void setAppmarkeddisable(Double appmarkeddisable) {
        this.appmarkeddisable = appmarkeddisable;
    }

    public String getDisableby() {
        return this.disableby;
    }

    public Customer disableby(String disableby) {
        this.setDisableby(disableby);
        return this;
    }

    public void setDisableby(String disableby) {
        this.disableby = disableby;
    }

    public String getApprovedisableby() {
        return this.approvedisableby;
    }

    public Customer approvedisableby(String approvedisableby) {
        this.setApprovedisableby(approvedisableby);
        return this;
    }

    public void setApprovedisableby(String approvedisableby) {
        this.approvedisableby = approvedisableby;
    }

    public Double getAppmarkedenable() {
        return this.appmarkedenable;
    }

    public Customer appmarkedenable(Double appmarkedenable) {
        this.setAppmarkedenable(appmarkedenable);
        return this;
    }

    public void setAppmarkedenable(Double appmarkedenable) {
        this.appmarkedenable = appmarkedenable;
    }

    public String getEnableby() {
        return this.enableby;
    }

    public Customer enableby(String enableby) {
        this.setEnableby(enableby);
        return this;
    }

    public void setEnableby(String enableby) {
        this.enableby = enableby;
    }

    public String getApprovedenableby() {
        return this.approvedenableby;
    }

    public Customer approvedenableby(String approvedenableby) {
        this.setApprovedenableby(approvedenableby);
        return this;
    }

    public void setApprovedenableby(String approvedenableby) {
        this.approvedenableby = approvedenableby;
    }

    public Double getMarkeddeactivate() {
        return this.markeddeactivate;
    }

    public Customer markeddeactivate(Double markeddeactivate) {
        this.setMarkeddeactivate(markeddeactivate);
        return this;
    }

    public void setMarkeddeactivate(Double markeddeactivate) {
        this.markeddeactivate = markeddeactivate;
    }

    public String getAppfirstlogin() {
        return this.appfirstlogin;
    }

    public Customer appfirstlogin(String appfirstlogin) {
        this.setAppfirstlogin(appfirstlogin);
        return this;
    }

    public void setAppfirstlogin(String appfirstlogin) {
        this.appfirstlogin = appfirstlogin;
    }

    public Double getAtmtrials() {
        return this.atmtrials;
    }

    public Customer atmtrials(Double atmtrials) {
        this.setAtmtrials(atmtrials);
        return this;
    }

    public void setAtmtrials(Double atmtrials) {
        this.atmtrials = atmtrials;
    }

    public String getShorcuts() {
        return this.shorcuts;
    }

    public Customer shorcuts(String shorcuts) {
        this.setShorcuts(shorcuts);
        return this;
    }

    public void setShorcuts(String shorcuts) {
        this.shorcuts = shorcuts;
    }

    public String getMarkedactivate() {
        return this.markedactivate;
    }

    public Customer markedactivate(String markedactivate) {
        this.setMarkedactivate(markedactivate);
        return this;
    }

    public void setMarkedactivate(String markedactivate) {
        this.markedactivate = markedactivate;
    }

    public String getTown() {
        return this.town;
    }

    public Customer town(String town) {
        this.setTown(town);
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Instant getApproveddisableon() {
        return this.approveddisableon;
    }

    public Customer approveddisableon(Instant approveddisableon) {
        this.setApproveddisableon(approveddisableon);
        return this;
    }

    public void setApproveddisableon(Instant approveddisableon) {
        this.approveddisableon = approveddisableon;
    }

    public Instant getDisabledon() {
        return this.disabledon;
    }

    public Customer disabledon(Instant disabledon) {
        this.setDisabledon(disabledon);
        return this;
    }

    public void setDisabledon(Instant disabledon) {
        this.disabledon = disabledon;
    }

    public Instant getResetapproveon() {
        return this.resetapproveon;
    }

    public Customer resetapproveon(Instant resetapproveon) {
        this.setResetapproveon(resetapproveon);
        return this;
    }

    public void setResetapproveon(Instant resetapproveon) {
        this.resetapproveon = resetapproveon;
    }

    public String getDeletedby() {
        return this.deletedby;
    }

    public Customer deletedby(String deletedby) {
        this.setDeletedby(deletedby);
        return this;
    }

    public void setDeletedby(String deletedby) {
        this.deletedby = deletedby;
    }

    public String getQuestionsasked() {
        return this.questionsasked;
    }

    public Customer questionsasked(String questionsasked) {
        this.setQuestionsasked(questionsasked);
        return this;
    }

    public void setQuestionsasked(String questionsasked) {
        this.questionsasked = questionsasked;
    }

    public String getQuestionstrials() {
        return this.questionstrials;
    }

    public Customer questionstrials(String questionstrials) {
        this.setQuestionstrials(questionstrials);
        return this;
    }

    public void setQuestionstrials(String questionstrials) {
        this.questionstrials = questionstrials;
    }

    public String getQuestionsanswered() {
        return this.questionsanswered;
    }

    public Customer questionsanswered(String questionsanswered) {
        this.setQuestionsanswered(questionsanswered);
        return this;
    }

    public void setQuestionsanswered(String questionsanswered) {
        this.questionsanswered = questionsanswered;
    }

    public Double getValidotp() {
        return this.validotp;
    }

    public Customer validotp(Double validotp) {
        this.setValidotp(validotp);
        return this;
    }

    public void setValidotp(Double validotp) {
        this.validotp = validotp;
    }

    public String getActivatedby() {
        return this.activatedby;
    }

    public Customer activatedby(String activatedby) {
        this.setActivatedby(activatedby);
        return this;
    }

    public void setActivatedby(String activatedby) {
        this.activatedby = activatedby;
    }

    public Instant getActivatedon() {
        return this.activatedon;
    }

    public Customer activatedon(Instant activatedon) {
        this.setActivatedon(activatedon);
        return this;
    }

    public void setActivatedon(Instant activatedon) {
        this.activatedon = activatedon;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public Customer branchcode(String branchcode) {
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
        if (!(o instanceof Customer)) {
            return false;
        }
        return getId() != null && getId().equals(((Customer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customername='" + getCustomername() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", cardnumber='" + getCardnumber() + "'" +
            ", accountnumber='" + getAccountnumber() + "'" +
            ", lang='" + getLang() + "'" +
            ", pin='" + getPin() + "'" +
            ", firstlogin='" + getFirstlogin() + "'" +
            ", active='" + getActive() + "'" +
            ", registered=" + getRegistered() +
            ", cstdelete=" + getCstdelete() +
            ", regdate='" + getRegdate() + "'" +
            ", alertenabled=" + getAlertenabled() +
            ", remark='" + getRemark() + "'" +
            ", imsi='" + getImsi() + "'" +
            ", partiallyregistered='" + getPartiallyregistered() + "'" +
            ", partialdate='" + getPartialdate() + "'" +
            ", registerdate='" + getRegisterdate() + "'" +
            ", approved=" + getApproved() +
            ", approvedby='" + getApprovedby() + "'" +
            ", approveddate='" + getApproveddate() + "'" +
            ", declined=" + getDeclined() +
            ", declinedby='" + getDeclinedby() + "'" +
            ", declineddate='" + getDeclineddate() + "'" +
            ", checkerremarks='" + getCheckerremarks() + "'" +
            ", postaladdress='" + getPostaladdress() + "'" +
            ", residence='" + getResidence() + "'" +
            ", dob='" + getDob() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", emailaddress='" + getEmailaddress() + "'" +
            ", identificationid='" + getIdentificationid() + "'" +
            ", addaccount=" + getAddaccount() +
            ", aclinkinginstitution='" + getAclinkinginstitution() + "'" +
            ", deactivated=" + getDeactivated() +
            ", deactivatedby='" + getDeactivatedby() + "'" +
            ", deactivatedon='" + getDeactivatedon() + "'" +
            ", phonenochanged=" + getPhonenochanged() +
            ", phonenochangedby='" + getPhonenochangedby() + "'" +
            ", phonenochangedon='" + getPhonenochangedon() + "'" +
            ", originalphoneno='" + getOriginalphoneno() + "'" +
            ", newphoneno='" + getNewphoneno() + "'" +
            ", reset=" + getReset() +
            ", resetinginstitution='" + getResetinginstitution() + "'" +
            ", pinresetremark='" + getPinresetremark() + "'" +
            ", resetby='" + getResetby() + "'" +
            ", reseton='" + getReseton() + "'" +
            ", unblockinginstitution='" + getUnblockinginstitution() + "'" +
            ", pinblock=" + getPinblock() +
            ", pinblockby='" + getPinblockby() + "'" +
            ", pinblockremarks='" + getPinblockremarks() + "'" +
            ", blockinginstitution='" + getBlockinginstitution() + "'" +
            ", pinblockon='" + getPinblockon() + "'" +
            ", approvedon='" + getApprovedon() + "'" +
            ", pinunblockby='" + getPinunblockby() + "'" +
            ", loggedin=" + getLoggedin() +
            ", trials='" + getTrials() + "'" +
            ", idtype='" + getIdtype() + "'" +
            ", idnumber='" + getIdnumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", cif='" + getCif() + "'" +
            ", dateofbirth='" + getDateofbirth() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", resetimsi=" + getResetimsi() +
            ", imsiresetby='" + getImsiresetby() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", secondname='" + getSecondname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", pinblocktime='" + getPinblocktime() + "'" +
            ", customerstatus='" + getCustomerstatus() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", deviceid='" + getDeviceid() + "'" +
            ", channel='" + getChannel() + "'" +
            ", passreset=" + getPassreset() +
            ", passresetby='" + getPassresetby() + "'" +
            ", passreseton='" + getPassreseton() + "'" +
            ", passblock=" + getPassblock() +
            ", passblockby='" + getPassblockby() + "'" +
            ", passblockon='" + getPassblockon() + "'" +
            ", pinmarkblock=" + getPinmarkblock() +
            ", passmarkblock=" + getPassmarkblock() +
            ", passresetremarks='" + getPassresetremarks() + "'" +
            ", passblockremarks='" + getPassblockremarks() + "'" +
            ", passunblockby='" + getPassunblockby() + "'" +
            ", passtrials=" + getPasstrials() +
            ", appactive=" + getAppactive() +
            ", lastlogin='" + getLastlogin() + "'" +
            ", appmarkeddisable=" + getAppmarkeddisable() +
            ", disableby='" + getDisableby() + "'" +
            ", approvedisableby='" + getApprovedisableby() + "'" +
            ", appmarkedenable=" + getAppmarkedenable() +
            ", enableby='" + getEnableby() + "'" +
            ", approvedenableby='" + getApprovedenableby() + "'" +
            ", markeddeactivate=" + getMarkeddeactivate() +
            ", appfirstlogin='" + getAppfirstlogin() + "'" +
            ", atmtrials=" + getAtmtrials() +
            ", shorcuts='" + getShorcuts() + "'" +
            ", markedactivate='" + getMarkedactivate() + "'" +
            ", town='" + getTown() + "'" +
            ", approveddisableon='" + getApproveddisableon() + "'" +
            ", disabledon='" + getDisabledon() + "'" +
            ", resetapproveon='" + getResetapproveon() + "'" +
            ", deletedby='" + getDeletedby() + "'" +
            ", questionsasked='" + getQuestionsasked() + "'" +
            ", questionstrials='" + getQuestionstrials() + "'" +
            ", questionsanswered='" + getQuestionsanswered() + "'" +
            ", validotp=" + getValidotp() +
            ", activatedby='" + getActivatedby() + "'" +
            ", activatedon='" + getActivatedon() + "'" +
            ", branchcode='" + getBranchcode() + "'" +
            "}";
    }
}
