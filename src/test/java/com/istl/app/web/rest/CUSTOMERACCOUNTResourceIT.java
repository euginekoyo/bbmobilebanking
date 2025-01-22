package com.istl.app.web.rest;

import static com.istl.app.domain.CustomerAccountAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.CustomerAccount;
import com.istl.app.repository.CustomerAccountRepository;
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
 * Integration tests for the {@link CustomerAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomerAccountResourceIT {

    private static final Long DEFAULT_CUSTOMERID = 1L;
    private static final Long UPDATED_CUSTOMERID = 2L;

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTCLASS = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCLASS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CIF = "AAAAAAAAAA";
    private static final String UPDATED_CIF = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIMELINKED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIMELINKED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_BLOCKED = 1L;
    private static final Long UPDATED_BLOCKED = 2L;

    private static final Long DEFAULT_STOPPED = 1L;
    private static final Long UPDATED_STOPPED = 2L;

    private static final Long DEFAULT_DORMANT = 1L;
    private static final Long UPDATED_DORMANT = 2L;

    private static final String ENTITY_API_URL = "/api/customer-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerAccountMockMvc;

    private CustomerAccount customerAccount;

    private CustomerAccount insertedCustomerAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerAccount createEntity() {
        return new CustomerAccount()
            .customerid(DEFAULT_CUSTOMERID)
            .accountnumber(DEFAULT_ACCOUNTNUMBER)
            .accountclass(DEFAULT_ACCOUNTCLASS)
            .customernumber(DEFAULT_CUSTOMERNUMBER)
            .cif(DEFAULT_CIF)
            .timelinked(DEFAULT_TIMELINKED)
            .blocked(DEFAULT_BLOCKED)
            .stopped(DEFAULT_STOPPED)
            .dormant(DEFAULT_DORMANT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerAccount createUpdatedEntity() {
        return new CustomerAccount()
            .customerid(UPDATED_CUSTOMERID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountclass(UPDATED_ACCOUNTCLASS)
            .customernumber(UPDATED_CUSTOMERNUMBER)
            .cif(UPDATED_CIF)
            .timelinked(UPDATED_TIMELINKED)
            .blocked(UPDATED_BLOCKED)
            .stopped(UPDATED_STOPPED)
            .dormant(UPDATED_DORMANT);
    }

    @BeforeEach
    public void initTest() {
        customerAccount = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomerAccount != null) {
            customerAccountRepository.delete(insertedCustomerAccount);
            insertedCustomerAccount = null;
        }
    }

    @Test
    @Transactional
    void createCustomerAccount() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CustomerAccount
        var returnedCustomerAccount = om.readValue(
            restCustomerAccountMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomerAccount.class
        );

        // Validate the CustomerAccount in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomerAccountUpdatableFieldsEquals(returnedCustomerAccount, getPersistedCustomerAccount(returnedCustomerAccount));

        insertedCustomerAccount = returnedCustomerAccount;
    }

    @Test
    @Transactional
    void createCustomerAccountWithExistingId() throws Exception {
        // Create the CustomerAccount with an existing ID
        customerAccount.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCustomeridIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        customerAccount.setCustomerid(null);

        // Create the CustomerAccount, which fails.

        restCustomerAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAccountnumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        customerAccount.setAccountnumber(null);

        // Create the CustomerAccount, which fails.

        restCustomerAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCifIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        customerAccount.setCif(null);

        // Create the CustomerAccount, which fails.

        restCustomerAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCustomerAccounts() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        // Get all the customerAccountList
        restCustomerAccountMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID.intValue())))
            .andExpect(jsonPath("$.[*].accountnumber").value(hasItem(DEFAULT_ACCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].accountclass").value(hasItem(DEFAULT_ACCOUNTCLASS)))
            .andExpect(jsonPath("$.[*].customernumber").value(hasItem(DEFAULT_CUSTOMERNUMBER)))
            .andExpect(jsonPath("$.[*].cif").value(hasItem(DEFAULT_CIF)))
            .andExpect(jsonPath("$.[*].timelinked").value(hasItem(DEFAULT_TIMELINKED.toString())))
            .andExpect(jsonPath("$.[*].blocked").value(hasItem(DEFAULT_BLOCKED.intValue())))
            .andExpect(jsonPath("$.[*].stopped").value(hasItem(DEFAULT_STOPPED.intValue())))
            .andExpect(jsonPath("$.[*].dormant").value(hasItem(DEFAULT_DORMANT.intValue())));
    }

    @Test
    @Transactional
    void getCustomerAccount() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        // Get the customerAccount
        restCustomerAccountMockMvc
            .perform(get(ENTITY_API_URL_ID, customerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerAccount.getId().intValue()))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID.intValue()))
            .andExpect(jsonPath("$.accountnumber").value(DEFAULT_ACCOUNTNUMBER))
            .andExpect(jsonPath("$.accountclass").value(DEFAULT_ACCOUNTCLASS))
            .andExpect(jsonPath("$.customernumber").value(DEFAULT_CUSTOMERNUMBER))
            .andExpect(jsonPath("$.cif").value(DEFAULT_CIF))
            .andExpect(jsonPath("$.timelinked").value(DEFAULT_TIMELINKED.toString()))
            .andExpect(jsonPath("$.blocked").value(DEFAULT_BLOCKED.intValue()))
            .andExpect(jsonPath("$.stopped").value(DEFAULT_STOPPED.intValue()))
            .andExpect(jsonPath("$.dormant").value(DEFAULT_DORMANT.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCustomerAccount() throws Exception {
        // Get the customerAccount
        restCustomerAccountMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomerAccount() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerAccount
        CustomerAccount updatedCustomerAccount = customerAccountRepository.findById(customerAccount.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomerAccount are not directly saved in db
        em.detach(updatedCustomerAccount);
        updatedCustomerAccount
            .customerid(UPDATED_CUSTOMERID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountclass(UPDATED_ACCOUNTCLASS)
            .customernumber(UPDATED_CUSTOMERNUMBER)
            .cif(UPDATED_CIF)
            .timelinked(UPDATED_TIMELINKED)
            .blocked(UPDATED_BLOCKED)
            .stopped(UPDATED_STOPPED)
            .dormant(UPDATED_DORMANT);

        restCustomerAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomerAccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomerAccount))
            )
            .andExpect(status().isOk());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomerAccountToMatchAllProperties(updatedCustomerAccount);
    }

    @Test
    @Transactional
    void putNonExistingCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customerAccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customerAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customerAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerAccountWithPatch() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerAccount using partial update
        CustomerAccount partialUpdatedCustomerAccount = new CustomerAccount();
        partialUpdatedCustomerAccount.setId(customerAccount.getId());

        partialUpdatedCustomerAccount.customernumber(UPDATED_CUSTOMERNUMBER).timelinked(UPDATED_TIMELINKED).blocked(UPDATED_BLOCKED);

        restCustomerAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomerAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomerAccount))
            )
            .andExpect(status().isOk());

        // Validate the CustomerAccount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerAccountUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomerAccount, customerAccount),
            getPersistedCustomerAccount(customerAccount)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomerAccountWithPatch() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerAccount using partial update
        CustomerAccount partialUpdatedCustomerAccount = new CustomerAccount();
        partialUpdatedCustomerAccount.setId(customerAccount.getId());

        partialUpdatedCustomerAccount
            .customerid(UPDATED_CUSTOMERID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountclass(UPDATED_ACCOUNTCLASS)
            .customernumber(UPDATED_CUSTOMERNUMBER)
            .cif(UPDATED_CIF)
            .timelinked(UPDATED_TIMELINKED)
            .blocked(UPDATED_BLOCKED)
            .stopped(UPDATED_STOPPED)
            .dormant(UPDATED_DORMANT);

        restCustomerAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomerAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomerAccount))
            )
            .andExpect(status().isOk());

        // Validate the CustomerAccount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerAccountUpdatableFieldsEquals(
            partialUpdatedCustomerAccount,
            getPersistedCustomerAccount(partialUpdatedCustomerAccount)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customerAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customerAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customerAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomerAccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerAccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerAccountMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customerAccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomerAccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomerAccount() throws Exception {
        // Initialize the database
        insertedCustomerAccount = customerAccountRepository.saveAndFlush(customerAccount);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customerAccount
        restCustomerAccountMockMvc
            .perform(delete(ENTITY_API_URL_ID, customerAccount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customerAccountRepository.count();
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

    protected CustomerAccount getPersistedCustomerAccount(CustomerAccount customerAccount) {
        return customerAccountRepository.findById(customerAccount.getId()).orElseThrow();
    }

    protected void assertPersistedCustomerAccountToMatchAllProperties(CustomerAccount expectedCustomerAccount) {
        assertCustomerAccountAllPropertiesEquals(expectedCustomerAccount, getPersistedCustomerAccount(expectedCustomerAccount));
    }

    protected void assertPersistedCustomerAccountToMatchUpdatableProperties(CustomerAccount expectedCustomerAccount) {
        assertCustomerAccountAllUpdatablePropertiesEquals(expectedCustomerAccount, getPersistedCustomerAccount(expectedCustomerAccount));
    }
}
