package com.istl.app.web.rest;

import static com.istl.app.domain.TransactionsAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.Transactions;
import com.istl.app.repository.mobileapp.TransactionsRepository;
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
 * Integration tests for the {@link TransactionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransactionsResourceIT {

    private static final Long DEFAULT_PROCESSED = 1L;
    private static final Long UPDATED_PROCESSED = 2L;

    private static final String DEFAULT_INCOMINGBITMAP = "AAAAAAAAAA";
    private static final String UPDATED_INCOMINGBITMAP = "BBBBBBBBBB";

    private static final String DEFAULT_OUTGOINGBITMAP = "AAAAAAAAAA";
    private static final String UPDATED_OUTGOINGBITMAP = "BBBBBBBBBB";

    private static final String DEFAULT_INMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_INMESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGETOCBS = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGETOCBS = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGEFROMCBS = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGEFROMCBS = "BBBBBBBBBB";

    private static final Long DEFAULT_CBSPROCESS = 1L;
    private static final Long UPDATED_CBSPROCESS = 2L;

    private static final Long DEFAULT_CBSONLINE = 1L;
    private static final Long UPDATED_CBSONLINE = 2L;

    private static final String DEFAULT_CBSRESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_CBSRESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSEMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSEMESSAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_RESPONSESENT = 1L;
    private static final Long UPDATED_RESPONSESENT = 2L;

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINALDATA = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALDATA = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_39_RESP = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_39_RESP = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    private static final Long DEFAULT_AUTHORISED = 1L;
    private static final Long UPDATED_AUTHORISED = 2L;

    private static final String DEFAULT_BRANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHCODE = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_39_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_39_ORIGINAL = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGECLASS = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGECLASS = "BBBBBBBBBB";

    private static final String DEFAULT_TXNCODE = "AAAAAAAAAA";
    private static final String UPDATED_TXNCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRCODE = "AAAAA";
    private static final String UPDATED_CURRCODE = "BBBBB";

    private static final String DEFAULT_DEVICE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_LONGERBRANCH = 1L;
    private static final Long UPDATED_LONGERBRANCH = 2L;

    private static final Instant DEFAULT_DATEX = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATEX = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TIMEX = "AAAAAAAAAA";
    private static final String UPDATED_TIMEX = "BBBBBBBBBB";

    private static final Long DEFAULT_POSTED = 1L;
    private static final Long UPDATED_POSTED = 2L;

    private static final Long DEFAULT_ATTEMPTS = 1L;
    private static final Long UPDATED_ATTEMPTS = 2L;

    private static final String DEFAULT_ORIGINALDATA_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALDATA_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_COMMISSION = 1L;
    private static final Long UPDATED_COMMISSION = 2L;

    private static final Long DEFAULT_RESPONSECREATED = 1L;
    private static final Long UPDATED_RESPONSECREATED = 2L;

    private static final Long DEFAULT_ONLINE = 1L;
    private static final Long UPDATED_ONLINE = 2L;

    private static final String DEFAULT_ORIGINALDATA_3 = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALDATA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TOSWITCH = "AAAAAAAAAA";
    private static final String UPDATED_TOSWITCH = "BBBBBBBBBB";

    private static final String DEFAULT_FROMSWITCH = "AAAAAAAAAA";
    private static final String UPDATED_FROMSWITCH = "BBBBBBBBBB";

    private static final String DEFAULT_TOCBS = "AAAAAAAAAA";
    private static final String UPDATED_TOCBS = "BBBBBBBBBB";

    private static final String DEFAULT_FROMCBS = "AAAAAAAAAA";
    private static final String UPDATED_FROMCBS = "BBBBBBBBBB";

    private static final Long DEFAULT_POSTINGLEGS = 1L;
    private static final Long UPDATED_POSTINGLEGS = 2L;

    private static final String DEFAULT_COMMISSIONTXNCODE = "AAAAAAAAAA";
    private static final String UPDATED_COMMISSIONTXNCODE = "BBBBBBBBBB";

    private static final String DEFAULT_HOSTREF = "AAAAAAAAAA";
    private static final String UPDATED_HOSTREF = "BBBBBBBBBB";

    private static final Long DEFAULT_REQUESTCREATED = 1L;
    private static final Long UPDATED_REQUESTCREATED = 2L;

    private static final String DEFAULT_REQUESTMESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTMESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_OUTGOINGBITMAPFLEX = "AAAAAAAAAA";
    private static final String UPDATED_OUTGOINGBITMAPFLEX = "BBBBBBBBBB";

    private static final String DEFAULT_INCOMINGBITMAPFLEX = "AAAAAAAAAA";
    private static final String UPDATED_INCOMINGBITMAPFLEX = "BBBBBBBBBB";

    private static final Long DEFAULT_REQUESTSENT = 1L;
    private static final Long UPDATED_REQUESTSENT = 2L;

    private static final Long DEFAULT_MINICBS = 1L;
    private static final Long UPDATED_MINICBS = 2L;

    private static final Long DEFAULT_REVERSED = 1L;
    private static final Long UPDATED_REVERSED = 2L;

    private static final Long DEFAULT_OFFLINESENTTOHOST = 1L;
    private static final Long UPDATED_OFFLINESENTTOHOST = 2L;

    private static final String DEFAULT_OFFLINERESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_OFFLINERESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_LONGERFACE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_LONGERFACE = "BBBBBBBBBB";

    private static final String DEFAULT_MTIRRN = "AAAAAAAAAA";
    private static final String UPDATED_MTIRRN = "BBBBBBBBBB";

    private static final String DEFAULT_HOSTRESPONSECODE = "AAAAAAAAAA";
    private static final String UPDATED_HOSTRESPONSECODE = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_48 = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_48 = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionsMockMvc;

    private Transactions transactions;

    private Transactions insertedTransactions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createEntity() {
        return new Transactions()
            .processed(DEFAULT_PROCESSED)
            .incomingbitmap(DEFAULT_INCOMINGBITMAP)
            .outgoingbitmap(DEFAULT_OUTGOINGBITMAP)
            .inmessage(DEFAULT_INMESSAGE)
            .messagetocbs(DEFAULT_MESSAGETOCBS)
            .messagefromcbs(DEFAULT_MESSAGEFROMCBS)
            .cbsprocess(DEFAULT_CBSPROCESS)
            .cbsonline(DEFAULT_CBSONLINE)
            .cbsresponse(DEFAULT_CBSRESPONSE)
            .responsemessage(DEFAULT_RESPONSEMESSAGE)
            .responsesent(DEFAULT_RESPONSESENT)
            .channel(DEFAULT_CHANNEL)
            .originaldata(DEFAULT_ORIGINALDATA)
            .field39resp(DEFAULT_FIELD_39_RESP)
            .narration(DEFAULT_NARRATION)
            .authorised(DEFAULT_AUTHORISED)
            .branchcode(DEFAULT_BRANCHCODE)
            .field39original(DEFAULT_FIELD_39_ORIGINAL)
            .messageclass(DEFAULT_MESSAGECLASS)
            .txncode(DEFAULT_TXNCODE)
            .currcode(DEFAULT_CURRCODE)
            .device(DEFAULT_DEVICE)
            .branch2(DEFAULT_BRANCH_2)
            .longerbranch(DEFAULT_LONGERBRANCH)
            .datex(DEFAULT_DATEX)
            .timex(DEFAULT_TIMEX)
            .posted(DEFAULT_POSTED)
            .attempts(DEFAULT_ATTEMPTS)
            .originaldata2(DEFAULT_ORIGINALDATA_2)
            .commission(DEFAULT_COMMISSION)
            .responsecreated(DEFAULT_RESPONSECREATED)
            .online(DEFAULT_ONLINE)
            .originaldata3(DEFAULT_ORIGINALDATA_3)
            .toswitch(DEFAULT_TOSWITCH)
            .fromswitch(DEFAULT_FROMSWITCH)
            .tocbs(DEFAULT_TOCBS)
            .fromcbs(DEFAULT_FROMCBS)
            .postinglegs(DEFAULT_POSTINGLEGS)
            .commissiontxncode(DEFAULT_COMMISSIONTXNCODE)
            .hostref(DEFAULT_HOSTREF)
            .requestcreated(DEFAULT_REQUESTCREATED)
            .requestmessage(DEFAULT_REQUESTMESSAGE)
            .outgoingbitmapflex(DEFAULT_OUTGOINGBITMAPFLEX)
            .incomingbitmapflex(DEFAULT_INCOMINGBITMAPFLEX)
            .requestsent(DEFAULT_REQUESTSENT)
            .minicbs(DEFAULT_MINICBS)
            .reversed(DEFAULT_REVERSED)
            .offlinesenttohost(DEFAULT_OFFLINESENTTOHOST)
            .offlineresponse(DEFAULT_OFFLINERESPONSE)
            .sourceLongerface(DEFAULT_SOURCE_LONGERFACE)
            .mtirrn(DEFAULT_MTIRRN)
            .hostresponsecode(DEFAULT_HOSTRESPONSECODE)
            .field48(DEFAULT_FIELD_48)
            .source(DEFAULT_SOURCE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createUpdatedEntity() {
        return new Transactions()
            .processed(UPDATED_PROCESSED)
            .incomingbitmap(UPDATED_INCOMINGBITMAP)
            .outgoingbitmap(UPDATED_OUTGOINGBITMAP)
            .inmessage(UPDATED_INMESSAGE)
            .messagetocbs(UPDATED_MESSAGETOCBS)
            .messagefromcbs(UPDATED_MESSAGEFROMCBS)
            .cbsprocess(UPDATED_CBSPROCESS)
            .cbsonline(UPDATED_CBSONLINE)
            .cbsresponse(UPDATED_CBSRESPONSE)
            .responsemessage(UPDATED_RESPONSEMESSAGE)
            .responsesent(UPDATED_RESPONSESENT)
            .channel(UPDATED_CHANNEL)
            .originaldata(UPDATED_ORIGINALDATA)
            .field39resp(UPDATED_FIELD_39_RESP)
            .narration(UPDATED_NARRATION)
            .authorised(UPDATED_AUTHORISED)
            .branchcode(UPDATED_BRANCHCODE)
            .field39original(UPDATED_FIELD_39_ORIGINAL)
            .messageclass(UPDATED_MESSAGECLASS)
            .txncode(UPDATED_TXNCODE)
            .currcode(UPDATED_CURRCODE)
            .device(UPDATED_DEVICE)
            .branch2(UPDATED_BRANCH_2)
            .longerbranch(UPDATED_LONGERBRANCH)
            .datex(UPDATED_DATEX)
            .timex(UPDATED_TIMEX)
            .posted(UPDATED_POSTED)
            .attempts(UPDATED_ATTEMPTS)
            .originaldata2(UPDATED_ORIGINALDATA_2)
            .commission(UPDATED_COMMISSION)
            .responsecreated(UPDATED_RESPONSECREATED)
            .online(UPDATED_ONLINE)
            .originaldata3(UPDATED_ORIGINALDATA_3)
            .toswitch(UPDATED_TOSWITCH)
            .fromswitch(UPDATED_FROMSWITCH)
            .tocbs(UPDATED_TOCBS)
            .fromcbs(UPDATED_FROMCBS)
            .postinglegs(UPDATED_POSTINGLEGS)
            .commissiontxncode(UPDATED_COMMISSIONTXNCODE)
            .hostref(UPDATED_HOSTREF)
            .requestcreated(UPDATED_REQUESTCREATED)
            .requestmessage(UPDATED_REQUESTMESSAGE)
            .outgoingbitmapflex(UPDATED_OUTGOINGBITMAPFLEX)
            .incomingbitmapflex(UPDATED_INCOMINGBITMAPFLEX)
            .requestsent(UPDATED_REQUESTSENT)
            .minicbs(UPDATED_MINICBS)
            .reversed(UPDATED_REVERSED)
            .offlinesenttohost(UPDATED_OFFLINESENTTOHOST)
            .offlineresponse(UPDATED_OFFLINERESPONSE)
            .sourceLongerface(UPDATED_SOURCE_LONGERFACE)
            .mtirrn(UPDATED_MTIRRN)
            .hostresponsecode(UPDATED_HOSTRESPONSECODE)
            .field48(UPDATED_FIELD_48)
            .source(UPDATED_SOURCE);
    }

    @BeforeEach
    public void initTest() {
        transactions = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTransactions != null) {
            transactionsRepository.delete(insertedTransactions);
            insertedTransactions = null;
        }
    }

    @Test
    @Transactional
    void createTransactions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Transactions
        var returnedTransactions = om.readValue(
            restTransactionsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactions)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Transactions.class
        );

        // Validate the Transactions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTransactionsUpdatableFieldsEquals(returnedTransactions, getPersistedTransactions(returnedTransactions));

        insertedTransactions = returnedTransactions;
    }

    @Test
    @Transactional
    void createTransactionsWithExistingId() throws Exception {
        // Create the Transactions with an existing ID
        transactions.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactions)))
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTransactions() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList
        restTransactionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactions.getId().intValue())))
            .andExpect(jsonPath("$.[*].processed").value(hasItem(DEFAULT_PROCESSED.intValue())))
            .andExpect(jsonPath("$.[*].incomingbitmap").value(hasItem(DEFAULT_INCOMINGBITMAP)))
            .andExpect(jsonPath("$.[*].outgoingbitmap").value(hasItem(DEFAULT_OUTGOINGBITMAP)))
            .andExpect(jsonPath("$.[*].inmessage").value(hasItem(DEFAULT_INMESSAGE)))
            .andExpect(jsonPath("$.[*].messagetocbs").value(hasItem(DEFAULT_MESSAGETOCBS)))
            .andExpect(jsonPath("$.[*].messagefromcbs").value(hasItem(DEFAULT_MESSAGEFROMCBS)))
            .andExpect(jsonPath("$.[*].cbsprocess").value(hasItem(DEFAULT_CBSPROCESS.intValue())))
            .andExpect(jsonPath("$.[*].cbsonline").value(hasItem(DEFAULT_CBSONLINE.intValue())))
            .andExpect(jsonPath("$.[*].cbsresponse").value(hasItem(DEFAULT_CBSRESPONSE)))
            .andExpect(jsonPath("$.[*].responsemessage").value(hasItem(DEFAULT_RESPONSEMESSAGE)))
            .andExpect(jsonPath("$.[*].responsesent").value(hasItem(DEFAULT_RESPONSESENT.intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].originaldata").value(hasItem(DEFAULT_ORIGINALDATA)))
            .andExpect(jsonPath("$.[*].field39resp").value(hasItem(DEFAULT_FIELD_39_RESP)))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)))
            .andExpect(jsonPath("$.[*].authorised").value(hasItem(DEFAULT_AUTHORISED.intValue())))
            .andExpect(jsonPath("$.[*].branchcode").value(hasItem(DEFAULT_BRANCHCODE)))
            .andExpect(jsonPath("$.[*].field39original").value(hasItem(DEFAULT_FIELD_39_ORIGINAL)))
            .andExpect(jsonPath("$.[*].messageclass").value(hasItem(DEFAULT_MESSAGECLASS)))
            .andExpect(jsonPath("$.[*].txncode").value(hasItem(DEFAULT_TXNCODE)))
            .andExpect(jsonPath("$.[*].currcode").value(hasItem(DEFAULT_CURRCODE)))
            .andExpect(jsonPath("$.[*].device").value(hasItem(DEFAULT_DEVICE)))
            .andExpect(jsonPath("$.[*].branch2").value(hasItem(DEFAULT_BRANCH_2)))
            .andExpect(jsonPath("$.[*].longerbranch").value(hasItem(DEFAULT_LONGERBRANCH.intValue())))
            .andExpect(jsonPath("$.[*].datex").value(hasItem(DEFAULT_DATEX.toString())))
            .andExpect(jsonPath("$.[*].timex").value(hasItem(DEFAULT_TIMEX)))
            .andExpect(jsonPath("$.[*].posted").value(hasItem(DEFAULT_POSTED.intValue())))
            .andExpect(jsonPath("$.[*].attempts").value(hasItem(DEFAULT_ATTEMPTS.intValue())))
            .andExpect(jsonPath("$.[*].originaldata2").value(hasItem(DEFAULT_ORIGINALDATA_2)))
            .andExpect(jsonPath("$.[*].commission").value(hasItem(DEFAULT_COMMISSION.intValue())))
            .andExpect(jsonPath("$.[*].responsecreated").value(hasItem(DEFAULT_RESPONSECREATED.intValue())))
            .andExpect(jsonPath("$.[*].online").value(hasItem(DEFAULT_ONLINE.intValue())))
            .andExpect(jsonPath("$.[*].originaldata3").value(hasItem(DEFAULT_ORIGINALDATA_3)))
            .andExpect(jsonPath("$.[*].toswitch").value(hasItem(DEFAULT_TOSWITCH)))
            .andExpect(jsonPath("$.[*].fromswitch").value(hasItem(DEFAULT_FROMSWITCH)))
            .andExpect(jsonPath("$.[*].tocbs").value(hasItem(DEFAULT_TOCBS)))
            .andExpect(jsonPath("$.[*].fromcbs").value(hasItem(DEFAULT_FROMCBS)))
            .andExpect(jsonPath("$.[*].postinglegs").value(hasItem(DEFAULT_POSTINGLEGS.intValue())))
            .andExpect(jsonPath("$.[*].commissiontxncode").value(hasItem(DEFAULT_COMMISSIONTXNCODE)))
            .andExpect(jsonPath("$.[*].hostref").value(hasItem(DEFAULT_HOSTREF)))
            .andExpect(jsonPath("$.[*].requestcreated").value(hasItem(DEFAULT_REQUESTCREATED.intValue())))
            .andExpect(jsonPath("$.[*].requestmessage").value(hasItem(DEFAULT_REQUESTMESSAGE)))
            .andExpect(jsonPath("$.[*].outgoingbitmapflex").value(hasItem(DEFAULT_OUTGOINGBITMAPFLEX)))
            .andExpect(jsonPath("$.[*].incomingbitmapflex").value(hasItem(DEFAULT_INCOMINGBITMAPFLEX)))
            .andExpect(jsonPath("$.[*].requestsent").value(hasItem(DEFAULT_REQUESTSENT.intValue())))
            .andExpect(jsonPath("$.[*].minicbs").value(hasItem(DEFAULT_MINICBS.intValue())))
            .andExpect(jsonPath("$.[*].reversed").value(hasItem(DEFAULT_REVERSED.intValue())))
            .andExpect(jsonPath("$.[*].offlinesenttohost").value(hasItem(DEFAULT_OFFLINESENTTOHOST.intValue())))
            .andExpect(jsonPath("$.[*].offlineresponse").value(hasItem(DEFAULT_OFFLINERESPONSE)))
            .andExpect(jsonPath("$.[*].sourceLongerface").value(hasItem(DEFAULT_SOURCE_LONGERFACE)))
            .andExpect(jsonPath("$.[*].mtirrn").value(hasItem(DEFAULT_MTIRRN)))
            .andExpect(jsonPath("$.[*].hostresponsecode").value(hasItem(DEFAULT_HOSTRESPONSECODE)))
            .andExpect(jsonPath("$.[*].field48").value(hasItem(DEFAULT_FIELD_48)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)));
    }

    @Test
    @Transactional
    void getTransactions() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        // Get the transactions
        restTransactionsMockMvc
            .perform(get(ENTITY_API_URL_ID, transactions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactions.getId().intValue()))
            .andExpect(jsonPath("$.processed").value(DEFAULT_PROCESSED.intValue()))
            .andExpect(jsonPath("$.incomingbitmap").value(DEFAULT_INCOMINGBITMAP))
            .andExpect(jsonPath("$.outgoingbitmap").value(DEFAULT_OUTGOINGBITMAP))
            .andExpect(jsonPath("$.inmessage").value(DEFAULT_INMESSAGE))
            .andExpect(jsonPath("$.messagetocbs").value(DEFAULT_MESSAGETOCBS))
            .andExpect(jsonPath("$.messagefromcbs").value(DEFAULT_MESSAGEFROMCBS))
            .andExpect(jsonPath("$.cbsprocess").value(DEFAULT_CBSPROCESS.intValue()))
            .andExpect(jsonPath("$.cbsonline").value(DEFAULT_CBSONLINE.intValue()))
            .andExpect(jsonPath("$.cbsresponse").value(DEFAULT_CBSRESPONSE))
            .andExpect(jsonPath("$.responsemessage").value(DEFAULT_RESPONSEMESSAGE))
            .andExpect(jsonPath("$.responsesent").value(DEFAULT_RESPONSESENT.intValue()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.originaldata").value(DEFAULT_ORIGINALDATA))
            .andExpect(jsonPath("$.field39resp").value(DEFAULT_FIELD_39_RESP))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION))
            .andExpect(jsonPath("$.authorised").value(DEFAULT_AUTHORISED.intValue()))
            .andExpect(jsonPath("$.branchcode").value(DEFAULT_BRANCHCODE))
            .andExpect(jsonPath("$.field39original").value(DEFAULT_FIELD_39_ORIGINAL))
            .andExpect(jsonPath("$.messageclass").value(DEFAULT_MESSAGECLASS))
            .andExpect(jsonPath("$.txncode").value(DEFAULT_TXNCODE))
            .andExpect(jsonPath("$.currcode").value(DEFAULT_CURRCODE))
            .andExpect(jsonPath("$.device").value(DEFAULT_DEVICE))
            .andExpect(jsonPath("$.branch2").value(DEFAULT_BRANCH_2))
            .andExpect(jsonPath("$.longerbranch").value(DEFAULT_LONGERBRANCH.intValue()))
            .andExpect(jsonPath("$.datex").value(DEFAULT_DATEX.toString()))
            .andExpect(jsonPath("$.timex").value(DEFAULT_TIMEX))
            .andExpect(jsonPath("$.posted").value(DEFAULT_POSTED.intValue()))
            .andExpect(jsonPath("$.attempts").value(DEFAULT_ATTEMPTS.intValue()))
            .andExpect(jsonPath("$.originaldata2").value(DEFAULT_ORIGINALDATA_2))
            .andExpect(jsonPath("$.commission").value(DEFAULT_COMMISSION.intValue()))
            .andExpect(jsonPath("$.responsecreated").value(DEFAULT_RESPONSECREATED.intValue()))
            .andExpect(jsonPath("$.online").value(DEFAULT_ONLINE.intValue()))
            .andExpect(jsonPath("$.originaldata3").value(DEFAULT_ORIGINALDATA_3))
            .andExpect(jsonPath("$.toswitch").value(DEFAULT_TOSWITCH))
            .andExpect(jsonPath("$.fromswitch").value(DEFAULT_FROMSWITCH))
            .andExpect(jsonPath("$.tocbs").value(DEFAULT_TOCBS))
            .andExpect(jsonPath("$.fromcbs").value(DEFAULT_FROMCBS))
            .andExpect(jsonPath("$.postinglegs").value(DEFAULT_POSTINGLEGS.intValue()))
            .andExpect(jsonPath("$.commissiontxncode").value(DEFAULT_COMMISSIONTXNCODE))
            .andExpect(jsonPath("$.hostref").value(DEFAULT_HOSTREF))
            .andExpect(jsonPath("$.requestcreated").value(DEFAULT_REQUESTCREATED.intValue()))
            .andExpect(jsonPath("$.requestmessage").value(DEFAULT_REQUESTMESSAGE))
            .andExpect(jsonPath("$.outgoingbitmapflex").value(DEFAULT_OUTGOINGBITMAPFLEX))
            .andExpect(jsonPath("$.incomingbitmapflex").value(DEFAULT_INCOMINGBITMAPFLEX))
            .andExpect(jsonPath("$.requestsent").value(DEFAULT_REQUESTSENT.intValue()))
            .andExpect(jsonPath("$.minicbs").value(DEFAULT_MINICBS.intValue()))
            .andExpect(jsonPath("$.reversed").value(DEFAULT_REVERSED.intValue()))
            .andExpect(jsonPath("$.offlinesenttohost").value(DEFAULT_OFFLINESENTTOHOST.intValue()))
            .andExpect(jsonPath("$.offlineresponse").value(DEFAULT_OFFLINERESPONSE))
            .andExpect(jsonPath("$.sourceLongerface").value(DEFAULT_SOURCE_LONGERFACE))
            .andExpect(jsonPath("$.mtirrn").value(DEFAULT_MTIRRN))
            .andExpect(jsonPath("$.hostresponsecode").value(DEFAULT_HOSTRESPONSECODE))
            .andExpect(jsonPath("$.field48").value(DEFAULT_FIELD_48))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE));
    }

    @Test
    @Transactional
    void getNonExistingTransactions() throws Exception {
        // Get the transactions
        restTransactionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTransactions() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactions
        Transactions updatedTransactions = transactionsRepository.findById(transactions.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTransactions are not directly saved in db
        em.detach(updatedTransactions);
        updatedTransactions
            .processed(UPDATED_PROCESSED)
            .incomingbitmap(UPDATED_INCOMINGBITMAP)
            .outgoingbitmap(UPDATED_OUTGOINGBITMAP)
            .inmessage(UPDATED_INMESSAGE)
            .messagetocbs(UPDATED_MESSAGETOCBS)
            .messagefromcbs(UPDATED_MESSAGEFROMCBS)
            .cbsprocess(UPDATED_CBSPROCESS)
            .cbsonline(UPDATED_CBSONLINE)
            .cbsresponse(UPDATED_CBSRESPONSE)
            .responsemessage(UPDATED_RESPONSEMESSAGE)
            .responsesent(UPDATED_RESPONSESENT)
            .channel(UPDATED_CHANNEL)
            .originaldata(UPDATED_ORIGINALDATA)
            .field39resp(UPDATED_FIELD_39_RESP)
            .narration(UPDATED_NARRATION)
            .authorised(UPDATED_AUTHORISED)
            .branchcode(UPDATED_BRANCHCODE)
            .field39original(UPDATED_FIELD_39_ORIGINAL)
            .messageclass(UPDATED_MESSAGECLASS)
            .txncode(UPDATED_TXNCODE)
            .currcode(UPDATED_CURRCODE)
            .device(UPDATED_DEVICE)
            .branch2(UPDATED_BRANCH_2)
            .longerbranch(UPDATED_LONGERBRANCH)
            .datex(UPDATED_DATEX)
            .timex(UPDATED_TIMEX)
            .posted(UPDATED_POSTED)
            .attempts(UPDATED_ATTEMPTS)
            .originaldata2(UPDATED_ORIGINALDATA_2)
            .commission(UPDATED_COMMISSION)
            .responsecreated(UPDATED_RESPONSECREATED)
            .online(UPDATED_ONLINE)
            .originaldata3(UPDATED_ORIGINALDATA_3)
            .toswitch(UPDATED_TOSWITCH)
            .fromswitch(UPDATED_FROMSWITCH)
            .tocbs(UPDATED_TOCBS)
            .fromcbs(UPDATED_FROMCBS)
            .postinglegs(UPDATED_POSTINGLEGS)
            .commissiontxncode(UPDATED_COMMISSIONTXNCODE)
            .hostref(UPDATED_HOSTREF)
            .requestcreated(UPDATED_REQUESTCREATED)
            .requestmessage(UPDATED_REQUESTMESSAGE)
            .outgoingbitmapflex(UPDATED_OUTGOINGBITMAPFLEX)
            .incomingbitmapflex(UPDATED_INCOMINGBITMAPFLEX)
            .requestsent(UPDATED_REQUESTSENT)
            .minicbs(UPDATED_MINICBS)
            .reversed(UPDATED_REVERSED)
            .offlinesenttohost(UPDATED_OFFLINESENTTOHOST)
            .offlineresponse(UPDATED_OFFLINERESPONSE)
            .sourceLongerface(UPDATED_SOURCE_LONGERFACE)
            .mtirrn(UPDATED_MTIRRN)
            .hostresponsecode(UPDATED_HOSTRESPONSECODE)
            .field48(UPDATED_FIELD_48)
            .source(UPDATED_SOURCE);

        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTransactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTransactionsToMatchAllProperties(updatedTransactions);
    }

    @Test
    @Transactional
    void putNonExistingTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransactionsWithPatch() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactions using partial update
        Transactions partialUpdatedTransactions = new Transactions();
        partialUpdatedTransactions.setId(transactions.getId());

        partialUpdatedTransactions
            .processed(UPDATED_PROCESSED)
            .inmessage(UPDATED_INMESSAGE)
            .cbsprocess(UPDATED_CBSPROCESS)
            .originaldata(UPDATED_ORIGINALDATA)
            .field39original(UPDATED_FIELD_39_ORIGINAL)
            .messageclass(UPDATED_MESSAGECLASS)
            .currcode(UPDATED_CURRCODE)
            .device(UPDATED_DEVICE)
            .branch2(UPDATED_BRANCH_2)
            .datex(UPDATED_DATEX)
            .timex(UPDATED_TIMEX)
            .posted(UPDATED_POSTED)
            .attempts(UPDATED_ATTEMPTS)
            .originaldata2(UPDATED_ORIGINALDATA_2)
            .commission(UPDATED_COMMISSION)
            .fromswitch(UPDATED_FROMSWITCH)
            .fromcbs(UPDATED_FROMCBS)
            .postinglegs(UPDATED_POSTINGLEGS)
            .requestmessage(UPDATED_REQUESTMESSAGE)
            .outgoingbitmapflex(UPDATED_OUTGOINGBITMAPFLEX)
            .incomingbitmapflex(UPDATED_INCOMINGBITMAPFLEX)
            .minicbs(UPDATED_MINICBS)
            .offlinesenttohost(UPDATED_OFFLINESENTTOHOST)
            .mtirrn(UPDATED_MTIRRN)
            .hostresponsecode(UPDATED_HOSTRESPONSECODE)
            .source(UPDATED_SOURCE);

        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransactionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTransactions, transactions),
            getPersistedTransactions(transactions)
        );
    }

    @Test
    @Transactional
    void fullUpdateTransactionsWithPatch() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactions using partial update
        Transactions partialUpdatedTransactions = new Transactions();
        partialUpdatedTransactions.setId(transactions.getId());

        partialUpdatedTransactions
            .processed(UPDATED_PROCESSED)
            .incomingbitmap(UPDATED_INCOMINGBITMAP)
            .outgoingbitmap(UPDATED_OUTGOINGBITMAP)
            .inmessage(UPDATED_INMESSAGE)
            .messagetocbs(UPDATED_MESSAGETOCBS)
            .messagefromcbs(UPDATED_MESSAGEFROMCBS)
            .cbsprocess(UPDATED_CBSPROCESS)
            .cbsonline(UPDATED_CBSONLINE)
            .cbsresponse(UPDATED_CBSRESPONSE)
            .responsemessage(UPDATED_RESPONSEMESSAGE)
            .responsesent(UPDATED_RESPONSESENT)
            .channel(UPDATED_CHANNEL)
            .originaldata(UPDATED_ORIGINALDATA)
            .field39resp(UPDATED_FIELD_39_RESP)
            .narration(UPDATED_NARRATION)
            .authorised(UPDATED_AUTHORISED)
            .branchcode(UPDATED_BRANCHCODE)
            .field39original(UPDATED_FIELD_39_ORIGINAL)
            .messageclass(UPDATED_MESSAGECLASS)
            .txncode(UPDATED_TXNCODE)
            .currcode(UPDATED_CURRCODE)
            .device(UPDATED_DEVICE)
            .branch2(UPDATED_BRANCH_2)
            .longerbranch(UPDATED_LONGERBRANCH)
            .datex(UPDATED_DATEX)
            .timex(UPDATED_TIMEX)
            .posted(UPDATED_POSTED)
            .attempts(UPDATED_ATTEMPTS)
            .originaldata2(UPDATED_ORIGINALDATA_2)
            .commission(UPDATED_COMMISSION)
            .responsecreated(UPDATED_RESPONSECREATED)
            .online(UPDATED_ONLINE)
            .originaldata3(UPDATED_ORIGINALDATA_3)
            .toswitch(UPDATED_TOSWITCH)
            .fromswitch(UPDATED_FROMSWITCH)
            .tocbs(UPDATED_TOCBS)
            .fromcbs(UPDATED_FROMCBS)
            .postinglegs(UPDATED_POSTINGLEGS)
            .commissiontxncode(UPDATED_COMMISSIONTXNCODE)
            .hostref(UPDATED_HOSTREF)
            .requestcreated(UPDATED_REQUESTCREATED)
            .requestmessage(UPDATED_REQUESTMESSAGE)
            .outgoingbitmapflex(UPDATED_OUTGOINGBITMAPFLEX)
            .incomingbitmapflex(UPDATED_INCOMINGBITMAPFLEX)
            .requestsent(UPDATED_REQUESTSENT)
            .minicbs(UPDATED_MINICBS)
            .reversed(UPDATED_REVERSED)
            .offlinesenttohost(UPDATED_OFFLINESENTTOHOST)
            .offlineresponse(UPDATED_OFFLINERESPONSE)
            .sourceLongerface(UPDATED_SOURCE_LONGERFACE)
            .mtirrn(UPDATED_MTIRRN)
            .hostresponsecode(UPDATED_HOSTRESPONSECODE)
            .field48(UPDATED_FIELD_48)
            .source(UPDATED_SOURCE);

        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransactionsUpdatableFieldsEquals(partialUpdatedTransactions, getPersistedTransactions(partialUpdatedTransactions));
    }

    @Test
    @Transactional
    void patchNonExistingTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(transactions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransactions() throws Exception {
        // Initialize the database
        insertedTransactions = transactionsRepository.saveAndFlush(transactions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the transactions
        restTransactionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, transactions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return transactionsRepository.count();
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

    protected Transactions getPersistedTransactions(Transactions transactions) {
        return transactionsRepository.findById(transactions.getId()).orElseThrow();
    }

    protected void assertPersistedTransactionsToMatchAllProperties(Transactions expectedTransactions) {
        assertTransactionsAllPropertiesEquals(expectedTransactions, getPersistedTransactions(expectedTransactions));
    }

    protected void assertPersistedTransactionsToMatchUpdatableProperties(Transactions expectedTransactions) {
        assertTransactionsAllUpdatablePropertiesEquals(expectedTransactions, getPersistedTransactions(expectedTransactions));
    }
}
