package com.istl.app.web.rest;

import static com.istl.app.domain.CustomerAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.Customer;
import com.istl.app.repository.mobileapp.CustomerRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomerResourceIT {

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CARDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CARDNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    private static final String DEFAULT_PIN = "AAAAAAAAAA";
    private static final String UPDATED_PIN = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTLOGIN = "A";
    private static final String UPDATED_FIRSTLOGIN = "B";

    private static final String DEFAULT_ACTIVE = "A";
    private static final String UPDATED_ACTIVE = "B";

    private static final Long DEFAULT_REGISTERED = 1L;
    private static final Long UPDATED_REGISTERED = 2L;

    private static final Long DEFAULT_CSTDELETE = 1L;
    private static final Long UPDATED_CSTDELETE = 2L;

    private static final Instant DEFAULT_REGDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ALERTENABLED = 1L;
    private static final Long UPDATED_ALERTENABLED = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI = "AAAAAAAAAA";
    private static final String UPDATED_IMSI = "BBBBBBBBBB";

    private static final String DEFAULT_PARTIALLYREGISTERED = "A";
    private static final String UPDATED_PARTIALLYREGISTERED = "B";

    private static final Instant DEFAULT_PARTIALDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PARTIALDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REGISTERDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGISTERDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_APPROVED = 1D;
    private static final Double UPDATED_APPROVED = 2D;

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_DECLINED = 1D;
    private static final Double UPDATED_DECLINED = 2D;

    private static final String DEFAULT_DECLINEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DECLINEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DECLINEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DECLINEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHECKERREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_CHECKERREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTALADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_POSTALADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAILADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAILADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFICATIONID = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATIONID = "BBBBBBBBBB";

    private static final Double DEFAULT_ADDACCOUNT = 1D;
    private static final Double UPDATED_ADDACCOUNT = 2D;

    private static final String DEFAULT_ACLINKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_ACLINKINGINSTITUTION = "BBBBBBBBBB";

    private static final Double DEFAULT_DEACTIVATED = 1D;
    private static final Double UPDATED_DEACTIVATED = 2D;

    private static final String DEFAULT_DEACTIVATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DEACTIVATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DEACTIVATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEACTIVATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_PHONENOCHANGED = 1D;
    private static final Double UPDATED_PHONENOCHANGED = 2D;

    private static final String DEFAULT_PHONENOCHANGEDBY = "AAAAAAAAAA";
    private static final String UPDATED_PHONENOCHANGEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_PHONENOCHANGEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PHONENOCHANGEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORIGINALPHONENO = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALPHONENO = "BBBBBBBBBB";

    private static final String DEFAULT_NEWPHONENO = "AAAAAAAAAA";
    private static final String UPDATED_NEWPHONENO = "BBBBBBBBBB";

    private static final Double DEFAULT_RESET = 1D;
    private static final Double UPDATED_RESET = 2D;

    private static final String DEFAULT_RESETINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_RESETINGINSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_PINRESETREMARK = "AAAAAAAAAA";
    private static final String UPDATED_PINRESETREMARK = "BBBBBBBBBB";

    private static final String DEFAULT_RESETBY = "AAAAAAAAAA";
    private static final String UPDATED_RESETBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_RESETON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESETON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UNBLOCKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_UNBLOCKINGINSTITUTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PINBLOCK = 1D;
    private static final Double UPDATED_PINBLOCK = 2D;

    private static final String DEFAULT_PINBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_PINBLOCKBY = "BBBBBBBBBB";

    private static final String DEFAULT_PINBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_PINBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCKINGINSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_BLOCKINGINSTITUTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_PINBLOCKON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PINBLOCKON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_APPROVEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PINUNBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_PINUNBLOCKBY = "BBBBBBBBBB";

    private static final Long DEFAULT_LOGGEDIN = 1L;
    private static final Long UPDATED_LOGGEDIN = 2L;

    private static final String DEFAULT_TRIALS = "AAAAAAAAAA";
    private static final String UPDATED_TRIALS = "BBBBBBBBBB";

    private static final String DEFAULT_IDTYPE = "AAAAAAAAAA";
    private static final String UPDATED_IDTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_IDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_IDNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "A";
    private static final String UPDATED_GENDER = "B";

    private static final String DEFAULT_CIF = "AAAAAAAAAA";
    private static final String UPDATED_CIF = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATEOFBIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATEOFBIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Double DEFAULT_RESETIMSI = 1D;
    private static final Double UPDATED_RESETIMSI = 2D;

    private static final String DEFAULT_IMSIRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_IMSIRESETBY = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDNAME = "AAAAAAAAAA";
    private static final String UPDATED_SECONDNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PINBLOCKTIME = "AAAAAAA";
    private static final String UPDATED_PINBLOCKTIME = "BBBBBBB";

    private static final String DEFAULT_CUSTOMERSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICEID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICEID = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final Double DEFAULT_PASSRESET = 1D;
    private static final Double UPDATED_PASSRESET = 2D;

    private static final String DEFAULT_PASSRESETBY = "AAAAAAAAAA";
    private static final String UPDATED_PASSRESETBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_PASSRESETON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PASSRESETON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_PASSBLOCK = 1D;
    private static final Double UPDATED_PASSBLOCK = 2D;

    private static final String DEFAULT_PASSBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_PASSBLOCKBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_PASSBLOCKON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PASSBLOCKON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_PINMARKBLOCK = 1D;
    private static final Double UPDATED_PINMARKBLOCK = 2D;

    private static final Double DEFAULT_PASSMARKBLOCK = 1D;
    private static final Double UPDATED_PASSMARKBLOCK = 2D;

    private static final String DEFAULT_PASSRESETREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_PASSRESETREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_PASSBLOCKREMARKS = "AAAAAAAAAA";
    private static final String UPDATED_PASSBLOCKREMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_PASSUNBLOCKBY = "AAAAAAAAAA";
    private static final String UPDATED_PASSUNBLOCKBY = "BBBBBBBBBB";

    private static final Double DEFAULT_PASSTRIALS = 1D;
    private static final Double UPDATED_PASSTRIALS = 2D;

    private static final Long DEFAULT_APPACTIVE = 1L;
    private static final Long UPDATED_APPACTIVE = 2L;

    private static final String DEFAULT_LASTLOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LASTLOGIN = "BBBBBBBBBB";

    private static final Double DEFAULT_APPMARKEDDISABLE = 1D;
    private static final Double UPDATED_APPMARKEDDISABLE = 2D;

    private static final String DEFAULT_DISABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_DISABLEBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDISABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDISABLEBY = "BBBBBBBBBB";

    private static final Double DEFAULT_APPMARKEDENABLE = 1D;
    private static final Double UPDATED_APPMARKEDENABLE = 2D;

    private static final String DEFAULT_ENABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_ENABLEBY = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVEDENABLEBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDENABLEBY = "BBBBBBBBBB";

    private static final Double DEFAULT_MARKEDDEACTIVATE = 1D;
    private static final Double UPDATED_MARKEDDEACTIVATE = 2D;

    private static final String DEFAULT_APPFIRSTLOGIN = "AAAAA";
    private static final String UPDATED_APPFIRSTLOGIN = "BBBBB";

    private static final Double DEFAULT_ATMTRIALS = 1D;
    private static final Double UPDATED_ATMTRIALS = 2D;

    private static final String DEFAULT_SHORCUTS = "AAAAAAAAAA";
    private static final String UPDATED_SHORCUTS = "BBBBBBBBBB";

    private static final String DEFAULT_MARKEDACTIVATE = "AAAAAAAAAA";
    private static final String UPDATED_MARKEDACTIVATE = "BBBBBBBBBB";

    private static final String DEFAULT_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_TOWN = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPROVEDDISABLEON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDDISABLEON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DISABLEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DISABLEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_RESETAPPROVEON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESETAPPROVEON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DELETEDBY = "AAAAAAAAAA";
    private static final String UPDATED_DELETEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTIONSASKED = "AAAAAAAAAA";
    private static final String UPDATED_QUESTIONSASKED = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTIONSTRIALS = "AAAAAAAAAA";
    private static final String UPDATED_QUESTIONSTRIALS = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTIONSANSWERED = "AAAAAAAAAA";
    private static final String UPDATED_QUESTIONSANSWERED = "BBBBBBBBBB";

    private static final Double DEFAULT_VALIDOTP = 1D;
    private static final Double UPDATED_VALIDOTP = 2D;

    private static final String DEFAULT_ACTIVATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_ACTIVATEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVATEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BRANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHCODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMockMvc;

    private Customer customer;

    private Customer insertedCustomer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity() {
        return new Customer()
            .customername(DEFAULT_CUSTOMERNAME)
            .phonenumber(DEFAULT_PHONENUMBER)
            .cardnumber(DEFAULT_CARDNUMBER)
            .accountnumber(DEFAULT_ACCOUNTNUMBER)
            .lang(DEFAULT_LANG)
            .pin(DEFAULT_PIN)
            .firstlogin(DEFAULT_FIRSTLOGIN)
            .active(DEFAULT_ACTIVE)
            .registered(DEFAULT_REGISTERED)
            .cstdelete(DEFAULT_CSTDELETE)
            .regdate(DEFAULT_REGDATE)
            .alertenabled(DEFAULT_ALERTENABLED)
            .remark(DEFAULT_REMARK)
            .imsi(DEFAULT_IMSI)
            .partiallyregistered(DEFAULT_PARTIALLYREGISTERED)
            .partialdate(DEFAULT_PARTIALDATE)
            .registerdate(DEFAULT_REGISTERDATE)
            .approved(DEFAULT_APPROVED)
            .approvedby(DEFAULT_APPROVEDBY)
            .approveddate(DEFAULT_APPROVEDDATE)
            .declined(DEFAULT_DECLINED)
            .declinedby(DEFAULT_DECLINEDBY)
            .declineddate(DEFAULT_DECLINEDDATE)
            .checkerremarks(DEFAULT_CHECKERREMARKS)
            .postaladdress(DEFAULT_POSTALADDRESS)
            .residence(DEFAULT_RESIDENCE)
            .dob(DEFAULT_DOB)
            .createdby(DEFAULT_CREATEDBY)
            .emailaddress(DEFAULT_EMAILADDRESS)
            .identificationid(DEFAULT_IDENTIFICATIONID)
            .addaccount(DEFAULT_ADDACCOUNT)
            .aclinkinginstitution(DEFAULT_ACLINKINGINSTITUTION)
            .deactivated(DEFAULT_DEACTIVATED)
            .deactivatedby(DEFAULT_DEACTIVATEDBY)
            .deactivatedon(DEFAULT_DEACTIVATEDON)
            .phonenochanged(DEFAULT_PHONENOCHANGED)
            .phonenochangedby(DEFAULT_PHONENOCHANGEDBY)
            .phonenochangedon(DEFAULT_PHONENOCHANGEDON)
            .originalphoneno(DEFAULT_ORIGINALPHONENO)
            .newphoneno(DEFAULT_NEWPHONENO)
            .reset(DEFAULT_RESET)
            .resetinginstitution(DEFAULT_RESETINGINSTITUTION)
            .pinresetremark(DEFAULT_PINRESETREMARK)
            .resetby(DEFAULT_RESETBY)
            .reseton(DEFAULT_RESETON)
            .unblockinginstitution(DEFAULT_UNBLOCKINGINSTITUTION)
            .pinblock(DEFAULT_PINBLOCK)
            .pinblockby(DEFAULT_PINBLOCKBY)
            .pinblockremarks(DEFAULT_PINBLOCKREMARKS)
            .blockinginstitution(DEFAULT_BLOCKINGINSTITUTION)
            .pinblockon(DEFAULT_PINBLOCKON)
            .approvedon(DEFAULT_APPROVEDON)
            .pinunblockby(DEFAULT_PINUNBLOCKBY)
            .loggedin(DEFAULT_LOGGEDIN)
            .trials(DEFAULT_TRIALS)
            .idtype(DEFAULT_IDTYPE)
            .idnumber(DEFAULT_IDNUMBER)
            .gender(DEFAULT_GENDER)
            .cif(DEFAULT_CIF)
            .dateofbirth(DEFAULT_DATEOFBIRTH)
            .remarks(DEFAULT_REMARKS)
            .resetimsi(DEFAULT_RESETIMSI)
            .imsiresetby(DEFAULT_IMSIRESETBY)
            .firstname(DEFAULT_FIRSTNAME)
            .secondname(DEFAULT_SECONDNAME)
            .lastname(DEFAULT_LASTNAME)
            .pinblocktime(DEFAULT_PINBLOCKTIME)
            .customerstatus(DEFAULT_CUSTOMERSTATUS)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .deviceid(DEFAULT_DEVICEID)
            .channel(DEFAULT_CHANNEL)
            .passreset(DEFAULT_PASSRESET)
            .passresetby(DEFAULT_PASSRESETBY)
            .passreseton(DEFAULT_PASSRESETON)
            .passblock(DEFAULT_PASSBLOCK)
            .passblockby(DEFAULT_PASSBLOCKBY)
            .passblockon(DEFAULT_PASSBLOCKON)
            .pinmarkblock(DEFAULT_PINMARKBLOCK)
            .passmarkblock(DEFAULT_PASSMARKBLOCK)
            .passresetremarks(DEFAULT_PASSRESETREMARKS)
            .passblockremarks(DEFAULT_PASSBLOCKREMARKS)
            .passunblockby(DEFAULT_PASSUNBLOCKBY)
            .passtrials(DEFAULT_PASSTRIALS)
            .appactive(DEFAULT_APPACTIVE)
            .lastlogin(DEFAULT_LASTLOGIN)
            .appmarkeddisable(DEFAULT_APPMARKEDDISABLE)
            .disableby(DEFAULT_DISABLEBY)
            .approvedisableby(DEFAULT_APPROVEDISABLEBY)
            .appmarkedenable(DEFAULT_APPMARKEDENABLE)
            .enableby(DEFAULT_ENABLEBY)
            .approvedenableby(DEFAULT_APPROVEDENABLEBY)
            .markeddeactivate(DEFAULT_MARKEDDEACTIVATE)
            .appfirstlogin(DEFAULT_APPFIRSTLOGIN)
            .atmtrials(DEFAULT_ATMTRIALS)
            .shorcuts(DEFAULT_SHORCUTS)
            .markedactivate(DEFAULT_MARKEDACTIVATE)
            .town(DEFAULT_TOWN)
            .approveddisableon(DEFAULT_APPROVEDDISABLEON)
            .disabledon(DEFAULT_DISABLEDON)
            .resetapproveon(DEFAULT_RESETAPPROVEON)
            .deletedby(DEFAULT_DELETEDBY)
            .questionsasked(DEFAULT_QUESTIONSASKED)
            .questionstrials(DEFAULT_QUESTIONSTRIALS)
            .questionsanswered(DEFAULT_QUESTIONSANSWERED)
            .validotp(DEFAULT_VALIDOTP)
            .activatedby(DEFAULT_ACTIVATEDBY)
            .activatedon(DEFAULT_ACTIVATEDON)
            .branchcode(DEFAULT_BRANCHCODE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity() {
        return new Customer()
            .customername(UPDATED_CUSTOMERNAME)
            .phonenumber(UPDATED_PHONENUMBER)
            .cardnumber(UPDATED_CARDNUMBER)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .lang(UPDATED_LANG)
            .pin(UPDATED_PIN)
            .firstlogin(UPDATED_FIRSTLOGIN)
            .active(UPDATED_ACTIVE)
            .registered(UPDATED_REGISTERED)
            .cstdelete(UPDATED_CSTDELETE)
            .regdate(UPDATED_REGDATE)
            .alertenabled(UPDATED_ALERTENABLED)
            .remark(UPDATED_REMARK)
            .imsi(UPDATED_IMSI)
            .partiallyregistered(UPDATED_PARTIALLYREGISTERED)
            .partialdate(UPDATED_PARTIALDATE)
            .registerdate(UPDATED_REGISTERDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .declineddate(UPDATED_DECLINEDDATE)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .postaladdress(UPDATED_POSTALADDRESS)
            .residence(UPDATED_RESIDENCE)
            .dob(UPDATED_DOB)
            .createdby(UPDATED_CREATEDBY)
            .emailaddress(UPDATED_EMAILADDRESS)
            .identificationid(UPDATED_IDENTIFICATIONID)
            .addaccount(UPDATED_ADDACCOUNT)
            .aclinkinginstitution(UPDATED_ACLINKINGINSTITUTION)
            .deactivated(UPDATED_DEACTIVATED)
            .deactivatedby(UPDATED_DEACTIVATEDBY)
            .deactivatedon(UPDATED_DEACTIVATEDON)
            .phonenochanged(UPDATED_PHONENOCHANGED)
            .phonenochangedby(UPDATED_PHONENOCHANGEDBY)
            .phonenochangedon(UPDATED_PHONENOCHANGEDON)
            .originalphoneno(UPDATED_ORIGINALPHONENO)
            .newphoneno(UPDATED_NEWPHONENO)
            .reset(UPDATED_RESET)
            .resetinginstitution(UPDATED_RESETINGINSTITUTION)
            .pinresetremark(UPDATED_PINRESETREMARK)
            .resetby(UPDATED_RESETBY)
            .reseton(UPDATED_RESETON)
            .unblockinginstitution(UPDATED_UNBLOCKINGINSTITUTION)
            .pinblock(UPDATED_PINBLOCK)
            .pinblockby(UPDATED_PINBLOCKBY)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .blockinginstitution(UPDATED_BLOCKINGINSTITUTION)
            .pinblockon(UPDATED_PINBLOCKON)
            .approvedon(UPDATED_APPROVEDON)
            .pinunblockby(UPDATED_PINUNBLOCKBY)
            .loggedin(UPDATED_LOGGEDIN)
            .trials(UPDATED_TRIALS)
            .idtype(UPDATED_IDTYPE)
            .idnumber(UPDATED_IDNUMBER)
            .gender(UPDATED_GENDER)
            .cif(UPDATED_CIF)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .remarks(UPDATED_REMARKS)
            .resetimsi(UPDATED_RESETIMSI)
            .imsiresetby(UPDATED_IMSIRESETBY)
            .firstname(UPDATED_FIRSTNAME)
            .secondname(UPDATED_SECONDNAME)
            .lastname(UPDATED_LASTNAME)
            .pinblocktime(UPDATED_PINBLOCKTIME)
            .customerstatus(UPDATED_CUSTOMERSTATUS)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .deviceid(UPDATED_DEVICEID)
            .channel(UPDATED_CHANNEL)
            .passreset(UPDATED_PASSRESET)
            .passresetby(UPDATED_PASSRESETBY)
            .passreseton(UPDATED_PASSRESETON)
            .passblock(UPDATED_PASSBLOCK)
            .passblockby(UPDATED_PASSBLOCKBY)
            .passblockon(UPDATED_PASSBLOCKON)
            .pinmarkblock(UPDATED_PINMARKBLOCK)
            .passmarkblock(UPDATED_PASSMARKBLOCK)
            .passresetremarks(UPDATED_PASSRESETREMARKS)
            .passblockremarks(UPDATED_PASSBLOCKREMARKS)
            .passunblockby(UPDATED_PASSUNBLOCKBY)
            .passtrials(UPDATED_PASSTRIALS)
            .appactive(UPDATED_APPACTIVE)
            .lastlogin(UPDATED_LASTLOGIN)
            .appmarkeddisable(UPDATED_APPMARKEDDISABLE)
            .disableby(UPDATED_DISABLEBY)
            .approvedisableby(UPDATED_APPROVEDISABLEBY)
            .appmarkedenable(UPDATED_APPMARKEDENABLE)
            .enableby(UPDATED_ENABLEBY)
            .approvedenableby(UPDATED_APPROVEDENABLEBY)
            .markeddeactivate(UPDATED_MARKEDDEACTIVATE)
            .appfirstlogin(UPDATED_APPFIRSTLOGIN)
            .atmtrials(UPDATED_ATMTRIALS)
            .shorcuts(UPDATED_SHORCUTS)
            .markedactivate(UPDATED_MARKEDACTIVATE)
            .town(UPDATED_TOWN)
            .approveddisableon(UPDATED_APPROVEDDISABLEON)
            .disabledon(UPDATED_DISABLEDON)
            .resetapproveon(UPDATED_RESETAPPROVEON)
            .deletedby(UPDATED_DELETEDBY)
            .questionsasked(UPDATED_QUESTIONSASKED)
            .questionstrials(UPDATED_QUESTIONSTRIALS)
            .questionsanswered(UPDATED_QUESTIONSANSWERED)
            .validotp(UPDATED_VALIDOTP)
            .activatedby(UPDATED_ACTIVATEDBY)
            .activatedon(UPDATED_ACTIVATEDON)
            .branchcode(UPDATED_BRANCHCODE);
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomer != null) {
            customerRepository.delete(insertedCustomer);
            insertedCustomer = null;
        }
    }

    @Test
    @Transactional
    void createCustomer() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Customer
        var returnedCustomer = om.readValue(
            restCustomerMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Customer.class
        );

        // Validate the Customer in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomerUpdatableFieldsEquals(returnedCustomer, getPersistedCustomer(returnedCustomer));

        insertedCustomer = returnedCustomer;
    }

    @Test
    @Transactional
    void createCustomerWithExistingId() throws Exception {
        // Create the Customer with an existing ID
        customer.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPhonenumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        customer.setPhonenumber(null);

        // Create the Customer, which fails.

        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAccountnumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        customer.setAccountnumber(null);

        // Create the Customer, which fails.

        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCustomers() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].cardnumber").value(hasItem(DEFAULT_CARDNUMBER)))
            .andExpect(jsonPath("$.[*].accountnumber").value(hasItem(DEFAULT_ACCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN)))
            .andExpect(jsonPath("$.[*].firstlogin").value(hasItem(DEFAULT_FIRSTLOGIN)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)))
            .andExpect(jsonPath("$.[*].registered").value(hasItem(DEFAULT_REGISTERED.intValue())))
            .andExpect(jsonPath("$.[*].cstdelete").value(hasItem(DEFAULT_CSTDELETE.intValue())))
            .andExpect(jsonPath("$.[*].regdate").value(hasItem(DEFAULT_REGDATE.toString())))
            .andExpect(jsonPath("$.[*].alertenabled").value(hasItem(DEFAULT_ALERTENABLED.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].imsi").value(hasItem(DEFAULT_IMSI)))
            .andExpect(jsonPath("$.[*].partiallyregistered").value(hasItem(DEFAULT_PARTIALLYREGISTERED)))
            .andExpect(jsonPath("$.[*].partialdate").value(hasItem(DEFAULT_PARTIALDATE.toString())))
            .andExpect(jsonPath("$.[*].registerdate").value(hasItem(DEFAULT_REGISTERDATE.toString())))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED)))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].approveddate").value(hasItem(DEFAULT_APPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].declined").value(hasItem(DEFAULT_DECLINED)))
            .andExpect(jsonPath("$.[*].declinedby").value(hasItem(DEFAULT_DECLINEDBY)))
            .andExpect(jsonPath("$.[*].declineddate").value(hasItem(DEFAULT_DECLINEDDATE.toString())))
            .andExpect(jsonPath("$.[*].checkerremarks").value(hasItem(DEFAULT_CHECKERREMARKS)))
            .andExpect(jsonPath("$.[*].postaladdress").value(hasItem(DEFAULT_POSTALADDRESS)))
            .andExpect(jsonPath("$.[*].residence").value(hasItem(DEFAULT_RESIDENCE)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].emailaddress").value(hasItem(DEFAULT_EMAILADDRESS)))
            .andExpect(jsonPath("$.[*].identificationid").value(hasItem(DEFAULT_IDENTIFICATIONID)))
            .andExpect(jsonPath("$.[*].addaccount").value(hasItem(DEFAULT_ADDACCOUNT)))
            .andExpect(jsonPath("$.[*].aclinkinginstitution").value(hasItem(DEFAULT_ACLINKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].deactivated").value(hasItem(DEFAULT_DEACTIVATED)))
            .andExpect(jsonPath("$.[*].deactivatedby").value(hasItem(DEFAULT_DEACTIVATEDBY)))
            .andExpect(jsonPath("$.[*].deactivatedon").value(hasItem(DEFAULT_DEACTIVATEDON.toString())))
            .andExpect(jsonPath("$.[*].phonenochanged").value(hasItem(DEFAULT_PHONENOCHANGED)))
            .andExpect(jsonPath("$.[*].phonenochangedby").value(hasItem(DEFAULT_PHONENOCHANGEDBY)))
            .andExpect(jsonPath("$.[*].phonenochangedon").value(hasItem(DEFAULT_PHONENOCHANGEDON.toString())))
            .andExpect(jsonPath("$.[*].originalphoneno").value(hasItem(DEFAULT_ORIGINALPHONENO)))
            .andExpect(jsonPath("$.[*].newphoneno").value(hasItem(DEFAULT_NEWPHONENO)))
            .andExpect(jsonPath("$.[*].reset").value(hasItem(DEFAULT_RESET)))
            .andExpect(jsonPath("$.[*].resetinginstitution").value(hasItem(DEFAULT_RESETINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pinresetremark").value(hasItem(DEFAULT_PINRESETREMARK)))
            .andExpect(jsonPath("$.[*].resetby").value(hasItem(DEFAULT_RESETBY)))
            .andExpect(jsonPath("$.[*].reseton").value(hasItem(DEFAULT_RESETON.toString())))
            .andExpect(jsonPath("$.[*].unblockinginstitution").value(hasItem(DEFAULT_UNBLOCKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pinblock").value(hasItem(DEFAULT_PINBLOCK)))
            .andExpect(jsonPath("$.[*].pinblockby").value(hasItem(DEFAULT_PINBLOCKBY)))
            .andExpect(jsonPath("$.[*].pinblockremarks").value(hasItem(DEFAULT_PINBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].blockinginstitution").value(hasItem(DEFAULT_BLOCKINGINSTITUTION)))
            .andExpect(jsonPath("$.[*].pinblockon").value(hasItem(DEFAULT_PINBLOCKON.toString())))
            .andExpect(jsonPath("$.[*].approvedon").value(hasItem(DEFAULT_APPROVEDON.toString())))
            .andExpect(jsonPath("$.[*].pinunblockby").value(hasItem(DEFAULT_PINUNBLOCKBY)))
            .andExpect(jsonPath("$.[*].loggedin").value(hasItem(DEFAULT_LOGGEDIN.intValue())))
            .andExpect(jsonPath("$.[*].trials").value(hasItem(DEFAULT_TRIALS)))
            .andExpect(jsonPath("$.[*].idtype").value(hasItem(DEFAULT_IDTYPE)))
            .andExpect(jsonPath("$.[*].idnumber").value(hasItem(DEFAULT_IDNUMBER)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].cif").value(hasItem(DEFAULT_CIF)))
            .andExpect(jsonPath("$.[*].dateofbirth").value(hasItem(DEFAULT_DATEOFBIRTH.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].resetimsi").value(hasItem(DEFAULT_RESETIMSI)))
            .andExpect(jsonPath("$.[*].imsiresetby").value(hasItem(DEFAULT_IMSIRESETBY)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].secondname").value(hasItem(DEFAULT_SECONDNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].pinblocktime").value(hasItem(DEFAULT_PINBLOCKTIME)))
            .andExpect(jsonPath("$.[*].customerstatus").value(hasItem(DEFAULT_CUSTOMERSTATUS)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].deviceid").value(hasItem(DEFAULT_DEVICEID)))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].passreset").value(hasItem(DEFAULT_PASSRESET)))
            .andExpect(jsonPath("$.[*].passresetby").value(hasItem(DEFAULT_PASSRESETBY)))
            .andExpect(jsonPath("$.[*].passreseton").value(hasItem(DEFAULT_PASSRESETON.toString())))
            .andExpect(jsonPath("$.[*].passblock").value(hasItem(DEFAULT_PASSBLOCK)))
            .andExpect(jsonPath("$.[*].passblockby").value(hasItem(DEFAULT_PASSBLOCKBY)))
            .andExpect(jsonPath("$.[*].passblockon").value(hasItem(DEFAULT_PASSBLOCKON.toString())))
            .andExpect(jsonPath("$.[*].pinmarkblock").value(hasItem(DEFAULT_PINMARKBLOCK)))
            .andExpect(jsonPath("$.[*].passmarkblock").value(hasItem(DEFAULT_PASSMARKBLOCK)))
            .andExpect(jsonPath("$.[*].passresetremarks").value(hasItem(DEFAULT_PASSRESETREMARKS)))
            .andExpect(jsonPath("$.[*].passblockremarks").value(hasItem(DEFAULT_PASSBLOCKREMARKS)))
            .andExpect(jsonPath("$.[*].passunblockby").value(hasItem(DEFAULT_PASSUNBLOCKBY)))
            .andExpect(jsonPath("$.[*].passtrials").value(hasItem(DEFAULT_PASSTRIALS)))
            .andExpect(jsonPath("$.[*].appactive").value(hasItem(DEFAULT_APPACTIVE.intValue())))
            .andExpect(jsonPath("$.[*].lastlogin").value(hasItem(DEFAULT_LASTLOGIN)))
            .andExpect(jsonPath("$.[*].appmarkeddisable").value(hasItem(DEFAULT_APPMARKEDDISABLE)))
            .andExpect(jsonPath("$.[*].disableby").value(hasItem(DEFAULT_DISABLEBY)))
            .andExpect(jsonPath("$.[*].approvedisableby").value(hasItem(DEFAULT_APPROVEDISABLEBY)))
            .andExpect(jsonPath("$.[*].appmarkedenable").value(hasItem(DEFAULT_APPMARKEDENABLE)))
            .andExpect(jsonPath("$.[*].enableby").value(hasItem(DEFAULT_ENABLEBY)))
            .andExpect(jsonPath("$.[*].approvedenableby").value(hasItem(DEFAULT_APPROVEDENABLEBY)))
            .andExpect(jsonPath("$.[*].markeddeactivate").value(hasItem(DEFAULT_MARKEDDEACTIVATE)))
            .andExpect(jsonPath("$.[*].appfirstlogin").value(hasItem(DEFAULT_APPFIRSTLOGIN)))
            .andExpect(jsonPath("$.[*].atmtrials").value(hasItem(DEFAULT_ATMTRIALS)))
            .andExpect(jsonPath("$.[*].shorcuts").value(hasItem(DEFAULT_SHORCUTS)))
            .andExpect(jsonPath("$.[*].markedactivate").value(hasItem(DEFAULT_MARKEDACTIVATE)))
            .andExpect(jsonPath("$.[*].town").value(hasItem(DEFAULT_TOWN)))
            .andExpect(jsonPath("$.[*].approveddisableon").value(hasItem(DEFAULT_APPROVEDDISABLEON.toString())))
            .andExpect(jsonPath("$.[*].disabledon").value(hasItem(DEFAULT_DISABLEDON.toString())))
            .andExpect(jsonPath("$.[*].resetapproveon").value(hasItem(DEFAULT_RESETAPPROVEON.toString())))
            .andExpect(jsonPath("$.[*].deletedby").value(hasItem(DEFAULT_DELETEDBY)))
            .andExpect(jsonPath("$.[*].questionsasked").value(hasItem(DEFAULT_QUESTIONSASKED)))
            .andExpect(jsonPath("$.[*].questionstrials").value(hasItem(DEFAULT_QUESTIONSTRIALS)))
            .andExpect(jsonPath("$.[*].questionsanswered").value(hasItem(DEFAULT_QUESTIONSANSWERED)))
            .andExpect(jsonPath("$.[*].validotp").value(hasItem(DEFAULT_VALIDOTP)))
            .andExpect(jsonPath("$.[*].activatedby").value(hasItem(DEFAULT_ACTIVATEDBY)))
            .andExpect(jsonPath("$.[*].activatedon").value(hasItem(DEFAULT_ACTIVATEDON.toString())))
            .andExpect(jsonPath("$.[*].branchcode").value(hasItem(DEFAULT_BRANCHCODE)));
    }

    @Test
    @Transactional
    void getCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER))
            .andExpect(jsonPath("$.cardnumber").value(DEFAULT_CARDNUMBER))
            .andExpect(jsonPath("$.accountnumber").value(DEFAULT_ACCOUNTNUMBER))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN))
            .andExpect(jsonPath("$.firstlogin").value(DEFAULT_FIRSTLOGIN))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE))
            .andExpect(jsonPath("$.registered").value(DEFAULT_REGISTERED.intValue()))
            .andExpect(jsonPath("$.cstdelete").value(DEFAULT_CSTDELETE.intValue()))
            .andExpect(jsonPath("$.regdate").value(DEFAULT_REGDATE.toString()))
            .andExpect(jsonPath("$.alertenabled").value(DEFAULT_ALERTENABLED.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.imsi").value(DEFAULT_IMSI))
            .andExpect(jsonPath("$.partiallyregistered").value(DEFAULT_PARTIALLYREGISTERED))
            .andExpect(jsonPath("$.partialdate").value(DEFAULT_PARTIALDATE.toString()))
            .andExpect(jsonPath("$.registerdate").value(DEFAULT_REGISTERDATE.toString()))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.approveddate").value(DEFAULT_APPROVEDDATE.toString()))
            .andExpect(jsonPath("$.declined").value(DEFAULT_DECLINED))
            .andExpect(jsonPath("$.declinedby").value(DEFAULT_DECLINEDBY))
            .andExpect(jsonPath("$.declineddate").value(DEFAULT_DECLINEDDATE.toString()))
            .andExpect(jsonPath("$.checkerremarks").value(DEFAULT_CHECKERREMARKS))
            .andExpect(jsonPath("$.postaladdress").value(DEFAULT_POSTALADDRESS))
            .andExpect(jsonPath("$.residence").value(DEFAULT_RESIDENCE))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.emailaddress").value(DEFAULT_EMAILADDRESS))
            .andExpect(jsonPath("$.identificationid").value(DEFAULT_IDENTIFICATIONID))
            .andExpect(jsonPath("$.addaccount").value(DEFAULT_ADDACCOUNT))
            .andExpect(jsonPath("$.aclinkinginstitution").value(DEFAULT_ACLINKINGINSTITUTION))
            .andExpect(jsonPath("$.deactivated").value(DEFAULT_DEACTIVATED))
            .andExpect(jsonPath("$.deactivatedby").value(DEFAULT_DEACTIVATEDBY))
            .andExpect(jsonPath("$.deactivatedon").value(DEFAULT_DEACTIVATEDON.toString()))
            .andExpect(jsonPath("$.phonenochanged").value(DEFAULT_PHONENOCHANGED))
            .andExpect(jsonPath("$.phonenochangedby").value(DEFAULT_PHONENOCHANGEDBY))
            .andExpect(jsonPath("$.phonenochangedon").value(DEFAULT_PHONENOCHANGEDON.toString()))
            .andExpect(jsonPath("$.originalphoneno").value(DEFAULT_ORIGINALPHONENO))
            .andExpect(jsonPath("$.newphoneno").value(DEFAULT_NEWPHONENO))
            .andExpect(jsonPath("$.reset").value(DEFAULT_RESET))
            .andExpect(jsonPath("$.resetinginstitution").value(DEFAULT_RESETINGINSTITUTION))
            .andExpect(jsonPath("$.pinresetremark").value(DEFAULT_PINRESETREMARK))
            .andExpect(jsonPath("$.resetby").value(DEFAULT_RESETBY))
            .andExpect(jsonPath("$.reseton").value(DEFAULT_RESETON.toString()))
            .andExpect(jsonPath("$.unblockinginstitution").value(DEFAULT_UNBLOCKINGINSTITUTION))
            .andExpect(jsonPath("$.pinblock").value(DEFAULT_PINBLOCK))
            .andExpect(jsonPath("$.pinblockby").value(DEFAULT_PINBLOCKBY))
            .andExpect(jsonPath("$.pinblockremarks").value(DEFAULT_PINBLOCKREMARKS))
            .andExpect(jsonPath("$.blockinginstitution").value(DEFAULT_BLOCKINGINSTITUTION))
            .andExpect(jsonPath("$.pinblockon").value(DEFAULT_PINBLOCKON.toString()))
            .andExpect(jsonPath("$.approvedon").value(DEFAULT_APPROVEDON.toString()))
            .andExpect(jsonPath("$.pinunblockby").value(DEFAULT_PINUNBLOCKBY))
            .andExpect(jsonPath("$.loggedin").value(DEFAULT_LOGGEDIN.intValue()))
            .andExpect(jsonPath("$.trials").value(DEFAULT_TRIALS))
            .andExpect(jsonPath("$.idtype").value(DEFAULT_IDTYPE))
            .andExpect(jsonPath("$.idnumber").value(DEFAULT_IDNUMBER))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.cif").value(DEFAULT_CIF))
            .andExpect(jsonPath("$.dateofbirth").value(DEFAULT_DATEOFBIRTH.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.resetimsi").value(DEFAULT_RESETIMSI))
            .andExpect(jsonPath("$.imsiresetby").value(DEFAULT_IMSIRESETBY))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.secondname").value(DEFAULT_SECONDNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.pinblocktime").value(DEFAULT_PINBLOCKTIME))
            .andExpect(jsonPath("$.customerstatus").value(DEFAULT_CUSTOMERSTATUS))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.deviceid").value(DEFAULT_DEVICEID))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.passreset").value(DEFAULT_PASSRESET))
            .andExpect(jsonPath("$.passresetby").value(DEFAULT_PASSRESETBY))
            .andExpect(jsonPath("$.passreseton").value(DEFAULT_PASSRESETON.toString()))
            .andExpect(jsonPath("$.passblock").value(DEFAULT_PASSBLOCK))
            .andExpect(jsonPath("$.passblockby").value(DEFAULT_PASSBLOCKBY))
            .andExpect(jsonPath("$.passblockon").value(DEFAULT_PASSBLOCKON.toString()))
            .andExpect(jsonPath("$.pinmarkblock").value(DEFAULT_PINMARKBLOCK))
            .andExpect(jsonPath("$.passmarkblock").value(DEFAULT_PASSMARKBLOCK))
            .andExpect(jsonPath("$.passresetremarks").value(DEFAULT_PASSRESETREMARKS))
            .andExpect(jsonPath("$.passblockremarks").value(DEFAULT_PASSBLOCKREMARKS))
            .andExpect(jsonPath("$.passunblockby").value(DEFAULT_PASSUNBLOCKBY))
            .andExpect(jsonPath("$.passtrials").value(DEFAULT_PASSTRIALS))
            .andExpect(jsonPath("$.appactive").value(DEFAULT_APPACTIVE.intValue()))
            .andExpect(jsonPath("$.lastlogin").value(DEFAULT_LASTLOGIN))
            .andExpect(jsonPath("$.appmarkeddisable").value(DEFAULT_APPMARKEDDISABLE))
            .andExpect(jsonPath("$.disableby").value(DEFAULT_DISABLEBY))
            .andExpect(jsonPath("$.approvedisableby").value(DEFAULT_APPROVEDISABLEBY))
            .andExpect(jsonPath("$.appmarkedenable").value(DEFAULT_APPMARKEDENABLE))
            .andExpect(jsonPath("$.enableby").value(DEFAULT_ENABLEBY))
            .andExpect(jsonPath("$.approvedenableby").value(DEFAULT_APPROVEDENABLEBY))
            .andExpect(jsonPath("$.markeddeactivate").value(DEFAULT_MARKEDDEACTIVATE))
            .andExpect(jsonPath("$.appfirstlogin").value(DEFAULT_APPFIRSTLOGIN))
            .andExpect(jsonPath("$.atmtrials").value(DEFAULT_ATMTRIALS))
            .andExpect(jsonPath("$.shorcuts").value(DEFAULT_SHORCUTS))
            .andExpect(jsonPath("$.markedactivate").value(DEFAULT_MARKEDACTIVATE))
            .andExpect(jsonPath("$.town").value(DEFAULT_TOWN))
            .andExpect(jsonPath("$.approveddisableon").value(DEFAULT_APPROVEDDISABLEON.toString()))
            .andExpect(jsonPath("$.disabledon").value(DEFAULT_DISABLEDON.toString()))
            .andExpect(jsonPath("$.resetapproveon").value(DEFAULT_RESETAPPROVEON.toString()))
            .andExpect(jsonPath("$.deletedby").value(DEFAULT_DELETEDBY))
            .andExpect(jsonPath("$.questionsasked").value(DEFAULT_QUESTIONSASKED))
            .andExpect(jsonPath("$.questionstrials").value(DEFAULT_QUESTIONSTRIALS))
            .andExpect(jsonPath("$.questionsanswered").value(DEFAULT_QUESTIONSANSWERED))
            .andExpect(jsonPath("$.validotp").value(DEFAULT_VALIDOTP))
            .andExpect(jsonPath("$.activatedby").value(DEFAULT_ACTIVATEDBY))
            .andExpect(jsonPath("$.activatedon").value(DEFAULT_ACTIVATEDON.toString()))
            .andExpect(jsonPath("$.branchcode").value(DEFAULT_BRANCHCODE));
    }

    @Test
    @Transactional
    void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .customername(UPDATED_CUSTOMERNAME)
            .phonenumber(UPDATED_PHONENUMBER)
            .cardnumber(UPDATED_CARDNUMBER)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .lang(UPDATED_LANG)
            .pin(UPDATED_PIN)
            .firstlogin(UPDATED_FIRSTLOGIN)
            .active(UPDATED_ACTIVE)
            .registered(UPDATED_REGISTERED)
            .cstdelete(UPDATED_CSTDELETE)
            .regdate(UPDATED_REGDATE)
            .alertenabled(UPDATED_ALERTENABLED)
            .remark(UPDATED_REMARK)
            .imsi(UPDATED_IMSI)
            .partiallyregistered(UPDATED_PARTIALLYREGISTERED)
            .partialdate(UPDATED_PARTIALDATE)
            .registerdate(UPDATED_REGISTERDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .declineddate(UPDATED_DECLINEDDATE)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .postaladdress(UPDATED_POSTALADDRESS)
            .residence(UPDATED_RESIDENCE)
            .dob(UPDATED_DOB)
            .createdby(UPDATED_CREATEDBY)
            .emailaddress(UPDATED_EMAILADDRESS)
            .identificationid(UPDATED_IDENTIFICATIONID)
            .addaccount(UPDATED_ADDACCOUNT)
            .aclinkinginstitution(UPDATED_ACLINKINGINSTITUTION)
            .deactivated(UPDATED_DEACTIVATED)
            .deactivatedby(UPDATED_DEACTIVATEDBY)
            .deactivatedon(UPDATED_DEACTIVATEDON)
            .phonenochanged(UPDATED_PHONENOCHANGED)
            .phonenochangedby(UPDATED_PHONENOCHANGEDBY)
            .phonenochangedon(UPDATED_PHONENOCHANGEDON)
            .originalphoneno(UPDATED_ORIGINALPHONENO)
            .newphoneno(UPDATED_NEWPHONENO)
            .reset(UPDATED_RESET)
            .resetinginstitution(UPDATED_RESETINGINSTITUTION)
            .pinresetremark(UPDATED_PINRESETREMARK)
            .resetby(UPDATED_RESETBY)
            .reseton(UPDATED_RESETON)
            .unblockinginstitution(UPDATED_UNBLOCKINGINSTITUTION)
            .pinblock(UPDATED_PINBLOCK)
            .pinblockby(UPDATED_PINBLOCKBY)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .blockinginstitution(UPDATED_BLOCKINGINSTITUTION)
            .pinblockon(UPDATED_PINBLOCKON)
            .approvedon(UPDATED_APPROVEDON)
            .pinunblockby(UPDATED_PINUNBLOCKBY)
            .loggedin(UPDATED_LOGGEDIN)
            .trials(UPDATED_TRIALS)
            .idtype(UPDATED_IDTYPE)
            .idnumber(UPDATED_IDNUMBER)
            .gender(UPDATED_GENDER)
            .cif(UPDATED_CIF)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .remarks(UPDATED_REMARKS)
            .resetimsi(UPDATED_RESETIMSI)
            .imsiresetby(UPDATED_IMSIRESETBY)
            .firstname(UPDATED_FIRSTNAME)
            .secondname(UPDATED_SECONDNAME)
            .lastname(UPDATED_LASTNAME)
            .pinblocktime(UPDATED_PINBLOCKTIME)
            .customerstatus(UPDATED_CUSTOMERSTATUS)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .deviceid(UPDATED_DEVICEID)
            .channel(UPDATED_CHANNEL)
            .passreset(UPDATED_PASSRESET)
            .passresetby(UPDATED_PASSRESETBY)
            .passreseton(UPDATED_PASSRESETON)
            .passblock(UPDATED_PASSBLOCK)
            .passblockby(UPDATED_PASSBLOCKBY)
            .passblockon(UPDATED_PASSBLOCKON)
            .pinmarkblock(UPDATED_PINMARKBLOCK)
            .passmarkblock(UPDATED_PASSMARKBLOCK)
            .passresetremarks(UPDATED_PASSRESETREMARKS)
            .passblockremarks(UPDATED_PASSBLOCKREMARKS)
            .passunblockby(UPDATED_PASSUNBLOCKBY)
            .passtrials(UPDATED_PASSTRIALS)
            .appactive(UPDATED_APPACTIVE)
            .lastlogin(UPDATED_LASTLOGIN)
            .appmarkeddisable(UPDATED_APPMARKEDDISABLE)
            .disableby(UPDATED_DISABLEBY)
            .approvedisableby(UPDATED_APPROVEDISABLEBY)
            .appmarkedenable(UPDATED_APPMARKEDENABLE)
            .enableby(UPDATED_ENABLEBY)
            .approvedenableby(UPDATED_APPROVEDENABLEBY)
            .markeddeactivate(UPDATED_MARKEDDEACTIVATE)
            .appfirstlogin(UPDATED_APPFIRSTLOGIN)
            .atmtrials(UPDATED_ATMTRIALS)
            .shorcuts(UPDATED_SHORCUTS)
            .markedactivate(UPDATED_MARKEDACTIVATE)
            .town(UPDATED_TOWN)
            .approveddisableon(UPDATED_APPROVEDDISABLEON)
            .disabledon(UPDATED_DISABLEDON)
            .resetapproveon(UPDATED_RESETAPPROVEON)
            .deletedby(UPDATED_DELETEDBY)
            .questionsasked(UPDATED_QUESTIONSASKED)
            .questionstrials(UPDATED_QUESTIONSTRIALS)
            .questionsanswered(UPDATED_QUESTIONSANSWERED)
            .validotp(UPDATED_VALIDOTP)
            .activatedby(UPDATED_ACTIVATEDBY)
            .activatedon(UPDATED_ACTIVATEDON)
            .branchcode(UPDATED_BRANCHCODE);

        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomerToMatchAllProperties(updatedCustomer);
    }

    @Test
    @Transactional
    void putNonExistingCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customer.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .customername(UPDATED_CUSTOMERNAME)
            .phonenumber(UPDATED_PHONENUMBER)
            .cardnumber(UPDATED_CARDNUMBER)
            .active(UPDATED_ACTIVE)
            .registered(UPDATED_REGISTERED)
            .cstdelete(UPDATED_CSTDELETE)
            .remark(UPDATED_REMARK)
            .imsi(UPDATED_IMSI)
            .partiallyregistered(UPDATED_PARTIALLYREGISTERED)
            .partialdate(UPDATED_PARTIALDATE)
            .approvedby(UPDATED_APPROVEDBY)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .declineddate(UPDATED_DECLINEDDATE)
            .postaladdress(UPDATED_POSTALADDRESS)
            .dob(UPDATED_DOB)
            .createdby(UPDATED_CREATEDBY)
            .emailaddress(UPDATED_EMAILADDRESS)
            .addaccount(UPDATED_ADDACCOUNT)
            .aclinkinginstitution(UPDATED_ACLINKINGINSTITUTION)
            .deactivated(UPDATED_DEACTIVATED)
            .deactivatedon(UPDATED_DEACTIVATEDON)
            .phonenochanged(UPDATED_PHONENOCHANGED)
            .phonenochangedby(UPDATED_PHONENOCHANGEDBY)
            .resetinginstitution(UPDATED_RESETINGINSTITUTION)
            .pinresetremark(UPDATED_PINRESETREMARK)
            .resetby(UPDATED_RESETBY)
            .reseton(UPDATED_RESETON)
            .pinblock(UPDATED_PINBLOCK)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .pinunblockby(UPDATED_PINUNBLOCKBY)
            .loggedin(UPDATED_LOGGEDIN)
            .idtype(UPDATED_IDTYPE)
            .idnumber(UPDATED_IDNUMBER)
            .gender(UPDATED_GENDER)
            .cif(UPDATED_CIF)
            .remarks(UPDATED_REMARKS)
            .resetimsi(UPDATED_RESETIMSI)
            .imsiresetby(UPDATED_IMSIRESETBY)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .pinblocktime(UPDATED_PINBLOCKTIME)
            .channel(UPDATED_CHANNEL)
            .passresetby(UPDATED_PASSRESETBY)
            .passblock(UPDATED_PASSBLOCK)
            .passblockon(UPDATED_PASSBLOCKON)
            .passresetremarks(UPDATED_PASSRESETREMARKS)
            .passunblockby(UPDATED_PASSUNBLOCKBY)
            .appactive(UPDATED_APPACTIVE)
            .lastlogin(UPDATED_LASTLOGIN)
            .appmarkeddisable(UPDATED_APPMARKEDDISABLE)
            .disableby(UPDATED_DISABLEBY)
            .shorcuts(UPDATED_SHORCUTS)
            .approveddisableon(UPDATED_APPROVEDDISABLEON)
            .resetapproveon(UPDATED_RESETAPPROVEON)
            .deletedby(UPDATED_DELETEDBY)
            .questionsasked(UPDATED_QUESTIONSASKED)
            .questionstrials(UPDATED_QUESTIONSTRIALS)
            .validotp(UPDATED_VALIDOTP)
            .activatedon(UPDATED_ACTIVATEDON);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCustomer, customer), getPersistedCustomer(customer));
    }

    @Test
    @Transactional
    void fullUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .customername(UPDATED_CUSTOMERNAME)
            .phonenumber(UPDATED_PHONENUMBER)
            .cardnumber(UPDATED_CARDNUMBER)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .lang(UPDATED_LANG)
            .pin(UPDATED_PIN)
            .firstlogin(UPDATED_FIRSTLOGIN)
            .active(UPDATED_ACTIVE)
            .registered(UPDATED_REGISTERED)
            .cstdelete(UPDATED_CSTDELETE)
            .regdate(UPDATED_REGDATE)
            .alertenabled(UPDATED_ALERTENABLED)
            .remark(UPDATED_REMARK)
            .imsi(UPDATED_IMSI)
            .partiallyregistered(UPDATED_PARTIALLYREGISTERED)
            .partialdate(UPDATED_PARTIALDATE)
            .registerdate(UPDATED_REGISTERDATE)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .declined(UPDATED_DECLINED)
            .declinedby(UPDATED_DECLINEDBY)
            .declineddate(UPDATED_DECLINEDDATE)
            .checkerremarks(UPDATED_CHECKERREMARKS)
            .postaladdress(UPDATED_POSTALADDRESS)
            .residence(UPDATED_RESIDENCE)
            .dob(UPDATED_DOB)
            .createdby(UPDATED_CREATEDBY)
            .emailaddress(UPDATED_EMAILADDRESS)
            .identificationid(UPDATED_IDENTIFICATIONID)
            .addaccount(UPDATED_ADDACCOUNT)
            .aclinkinginstitution(UPDATED_ACLINKINGINSTITUTION)
            .deactivated(UPDATED_DEACTIVATED)
            .deactivatedby(UPDATED_DEACTIVATEDBY)
            .deactivatedon(UPDATED_DEACTIVATEDON)
            .phonenochanged(UPDATED_PHONENOCHANGED)
            .phonenochangedby(UPDATED_PHONENOCHANGEDBY)
            .phonenochangedon(UPDATED_PHONENOCHANGEDON)
            .originalphoneno(UPDATED_ORIGINALPHONENO)
            .newphoneno(UPDATED_NEWPHONENO)
            .reset(UPDATED_RESET)
            .resetinginstitution(UPDATED_RESETINGINSTITUTION)
            .pinresetremark(UPDATED_PINRESETREMARK)
            .resetby(UPDATED_RESETBY)
            .reseton(UPDATED_RESETON)
            .unblockinginstitution(UPDATED_UNBLOCKINGINSTITUTION)
            .pinblock(UPDATED_PINBLOCK)
            .pinblockby(UPDATED_PINBLOCKBY)
            .pinblockremarks(UPDATED_PINBLOCKREMARKS)
            .blockinginstitution(UPDATED_BLOCKINGINSTITUTION)
            .pinblockon(UPDATED_PINBLOCKON)
            .approvedon(UPDATED_APPROVEDON)
            .pinunblockby(UPDATED_PINUNBLOCKBY)
            .loggedin(UPDATED_LOGGEDIN)
            .trials(UPDATED_TRIALS)
            .idtype(UPDATED_IDTYPE)
            .idnumber(UPDATED_IDNUMBER)
            .gender(UPDATED_GENDER)
            .cif(UPDATED_CIF)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .remarks(UPDATED_REMARKS)
            .resetimsi(UPDATED_RESETIMSI)
            .imsiresetby(UPDATED_IMSIRESETBY)
            .firstname(UPDATED_FIRSTNAME)
            .secondname(UPDATED_SECONDNAME)
            .lastname(UPDATED_LASTNAME)
            .pinblocktime(UPDATED_PINBLOCKTIME)
            .customerstatus(UPDATED_CUSTOMERSTATUS)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .deviceid(UPDATED_DEVICEID)
            .channel(UPDATED_CHANNEL)
            .passreset(UPDATED_PASSRESET)
            .passresetby(UPDATED_PASSRESETBY)
            .passreseton(UPDATED_PASSRESETON)
            .passblock(UPDATED_PASSBLOCK)
            .passblockby(UPDATED_PASSBLOCKBY)
            .passblockon(UPDATED_PASSBLOCKON)
            .pinmarkblock(UPDATED_PINMARKBLOCK)
            .passmarkblock(UPDATED_PASSMARKBLOCK)
            .passresetremarks(UPDATED_PASSRESETREMARKS)
            .passblockremarks(UPDATED_PASSBLOCKREMARKS)
            .passunblockby(UPDATED_PASSUNBLOCKBY)
            .passtrials(UPDATED_PASSTRIALS)
            .appactive(UPDATED_APPACTIVE)
            .lastlogin(UPDATED_LASTLOGIN)
            .appmarkeddisable(UPDATED_APPMARKEDDISABLE)
            .disableby(UPDATED_DISABLEBY)
            .approvedisableby(UPDATED_APPROVEDISABLEBY)
            .appmarkedenable(UPDATED_APPMARKEDENABLE)
            .enableby(UPDATED_ENABLEBY)
            .approvedenableby(UPDATED_APPROVEDENABLEBY)
            .markeddeactivate(UPDATED_MARKEDDEACTIVATE)
            .appfirstlogin(UPDATED_APPFIRSTLOGIN)
            .atmtrials(UPDATED_ATMTRIALS)
            .shorcuts(UPDATED_SHORCUTS)
            .markedactivate(UPDATED_MARKEDACTIVATE)
            .town(UPDATED_TOWN)
            .approveddisableon(UPDATED_APPROVEDDISABLEON)
            .disabledon(UPDATED_DISABLEDON)
            .resetapproveon(UPDATED_RESETAPPROVEON)
            .deletedby(UPDATED_DELETEDBY)
            .questionsasked(UPDATED_QUESTIONSASKED)
            .questionstrials(UPDATED_QUESTIONSTRIALS)
            .questionsanswered(UPDATED_QUESTIONSANSWERED)
            .validotp(UPDATED_VALIDOTP)
            .activatedby(UPDATED_ACTIVATEDBY)
            .activatedon(UPDATED_ACTIVATEDON)
            .branchcode(UPDATED_BRANCHCODE);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerUpdatableFieldsEquals(partialUpdatedCustomer, getPersistedCustomer(partialUpdatedCustomer));
    }

    @Test
    @Transactional
    void patchNonExistingCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customer
        restCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, customer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customerRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Customer getPersistedCustomer(Customer customer) {
        return customerRepository.findById(customer.getId()).orElseThrow();
    }

    protected void assertPersistedCustomerToMatchAllProperties(Customer expectedCustomer) {
        assertCustomerAllPropertiesEquals(expectedCustomer, getPersistedCustomer(expectedCustomer));
    }

    protected void assertPersistedCustomerToMatchUpdatableProperties(Customer expectedCustomer) {
        assertCustomerAllUpdatablePropertiesEquals(expectedCustomer, getPersistedCustomer(expectedCustomer));
    }
}
