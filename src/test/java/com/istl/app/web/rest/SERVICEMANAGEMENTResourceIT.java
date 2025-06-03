package com.istl.app.web.rest;

import static com.istl.app.domain.ServiceManagementAsserts.*;
import static com.istl.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istl.app.IntegrationTest;
import com.istl.app.domain.mobileapp.ServiceManagement;
import com.istl.app.repository.mobileapp.ServiceManagementRepository;
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
 * Integration tests for the {@link ServiceManagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceManagementResourceIT {

    private static final String DEFAULT_PROCESSINGCODE = "AAAAAAAAAA";
    private static final String UPDATED_PROCESSINGCODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATECREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATECREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_APPROVED = 1L;
    private static final Long UPDATED_APPROVED = 2L;

    private static final String DEFAULT_APPROVEDBY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVEDBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPROVEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ADAPTORTYPE = "AAAAAAAAAA";
    private static final String UPDATED_ADAPTORTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION = "BBBBBBBBBB";

    private static final Double DEFAULT_THIRDPARTYRESPONSE = 1D;
    private static final Double UPDATED_THIRDPARTYRESPONSE = 2D;

    private static final String DEFAULT_TELCO = "AAAAAAAAAA";
    private static final String UPDATED_TELCO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_SESSIONID = "AAAAAAAAAA";
    private static final String UPDATED_SESSIONID = "BBBBBBBBBB";

    private static final String DEFAULT_REWORKBY = "AAAAAAAAAA";
    private static final String UPDATED_REWORKBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/service-managements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ServiceManagementRepository serviceManagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceManagementMockMvc;

    private ServiceManagement serviceManagement;

    private ServiceManagement insertedServiceManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceManagement createEntity() {
        return new ServiceManagement()
            .processingcode(DEFAULT_PROCESSINGCODE)
            .active(DEFAULT_ACTIVE)
            .createdby(DEFAULT_CREATEDBY)
            .datecreated(DEFAULT_DATECREATED)
            .approved(DEFAULT_APPROVED)
            .approvedby(DEFAULT_APPROVEDBY)
            .approveddate(DEFAULT_APPROVEDDATE)
            .adaptortype(DEFAULT_ADAPTORTYPE)
            .destination(DEFAULT_DESTINATION)
            .thirdpartyresponse(DEFAULT_THIRDPARTYRESPONSE)
            .telco(DEFAULT_TELCO)
            .description(DEFAULT_DESCRIPTION)
            .remarks(DEFAULT_REMARKS)
            .sessionid(DEFAULT_SESSIONID)
            .reworkby(DEFAULT_REWORKBY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceManagement createUpdatedEntity() {
        return new ServiceManagement()
            .processingcode(UPDATED_PROCESSINGCODE)
            .active(UPDATED_ACTIVE)
            .createdby(UPDATED_CREATEDBY)
            .datecreated(UPDATED_DATECREATED)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .adaptortype(UPDATED_ADAPTORTYPE)
            .destination(UPDATED_DESTINATION)
            .thirdpartyresponse(UPDATED_THIRDPARTYRESPONSE)
            .telco(UPDATED_TELCO)
            .description(UPDATED_DESCRIPTION)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY);
    }

    @BeforeEach
    public void initTest() {
        serviceManagement = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedServiceManagement != null) {
            serviceManagementRepository.delete(insertedServiceManagement);
            insertedServiceManagement = null;
        }
    }

    @Test
    @Transactional
    void createServiceManagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ServiceManagement
        var returnedServiceManagement = om.readValue(
            restServiceManagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceManagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ServiceManagement.class
        );

        // Validate the ServiceManagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertServiceManagementUpdatableFieldsEquals(returnedServiceManagement, getPersistedServiceManagement(returnedServiceManagement));

        insertedServiceManagement = returnedServiceManagement;
    }

    @Test
    @Transactional
    void createServiceManagementWithExistingId() throws Exception {
        // Create the ServiceManagement with an existing ID
        serviceManagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceManagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceManagement)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        serviceManagement.setDescription(null);

        // Create the ServiceManagement, which fails.

        restServiceManagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceManagement)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllServiceManagements() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        // Get all the serviceManagementList
        restServiceManagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].processingcode").value(hasItem(DEFAULT_PROCESSINGCODE)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)))
            .andExpect(jsonPath("$.[*].datecreated").value(hasItem(DEFAULT_DATECREATED.toString())))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.intValue())))
            .andExpect(jsonPath("$.[*].approvedby").value(hasItem(DEFAULT_APPROVEDBY)))
            .andExpect(jsonPath("$.[*].approveddate").value(hasItem(DEFAULT_APPROVEDDATE.toString())))
            .andExpect(jsonPath("$.[*].adaptortype").value(hasItem(DEFAULT_ADAPTORTYPE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].thirdpartyresponse").value(hasItem(DEFAULT_THIRDPARTYRESPONSE)))
            .andExpect(jsonPath("$.[*].telco").value(hasItem(DEFAULT_TELCO)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].sessionid").value(hasItem(DEFAULT_SESSIONID)))
            .andExpect(jsonPath("$.[*].reworkby").value(hasItem(DEFAULT_REWORKBY)));
    }

    @Test
    @Transactional
    void getServiceManagement() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        // Get the serviceManagement
        restServiceManagementMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceManagement.getId().intValue()))
            .andExpect(jsonPath("$.processingcode").value(DEFAULT_PROCESSINGCODE))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY))
            .andExpect(jsonPath("$.datecreated").value(DEFAULT_DATECREATED.toString()))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.intValue()))
            .andExpect(jsonPath("$.approvedby").value(DEFAULT_APPROVEDBY))
            .andExpect(jsonPath("$.approveddate").value(DEFAULT_APPROVEDDATE.toString()))
            .andExpect(jsonPath("$.adaptortype").value(DEFAULT_ADAPTORTYPE))
            .andExpect(jsonPath("$.destination").value(DEFAULT_DESTINATION))
            .andExpect(jsonPath("$.thirdpartyresponse").value(DEFAULT_THIRDPARTYRESPONSE))
            .andExpect(jsonPath("$.telco").value(DEFAULT_TELCO))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.sessionid").value(DEFAULT_SESSIONID))
            .andExpect(jsonPath("$.reworkby").value(DEFAULT_REWORKBY));
    }

    @Test
    @Transactional
    void getNonExistingServiceManagement() throws Exception {
        // Get the serviceManagement
        restServiceManagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServiceManagement() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceManagement
        ServiceManagement updatedServiceManagement = serviceManagementRepository.findById(serviceManagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedServiceManagement are not directly saved in db
        em.detach(updatedServiceManagement);
        updatedServiceManagement
            .processingcode(UPDATED_PROCESSINGCODE)
            .active(UPDATED_ACTIVE)
            .createdby(UPDATED_CREATEDBY)
            .datecreated(UPDATED_DATECREATED)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .adaptortype(UPDATED_ADAPTORTYPE)
            .destination(UPDATED_DESTINATION)
            .thirdpartyresponse(UPDATED_THIRDPARTYRESPONSE)
            .telco(UPDATED_TELCO)
            .description(UPDATED_DESCRIPTION)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY);

        restServiceManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedServiceManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedServiceManagement))
            )
            .andExpect(status().isOk());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedServiceManagementToMatchAllProperties(updatedServiceManagement);
    }

    @Test
    @Transactional
    void putNonExistingServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(serviceManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(serviceManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceManagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceManagementWithPatch() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceManagement using partial update
        ServiceManagement partialUpdatedServiceManagement = new ServiceManagement();
        partialUpdatedServiceManagement.setId(serviceManagement.getId());

        partialUpdatedServiceManagement
            .processingcode(UPDATED_PROCESSINGCODE)
            .active(UPDATED_ACTIVE)
            .datecreated(UPDATED_DATECREATED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .adaptortype(UPDATED_ADAPTORTYPE)
            .destination(UPDATED_DESTINATION)
            .thirdpartyresponse(UPDATED_THIRDPARTYRESPONSE)
            .reworkby(UPDATED_REWORKBY);

        restServiceManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServiceManagement))
            )
            .andExpect(status().isOk());

        // Validate the ServiceManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServiceManagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedServiceManagement, serviceManagement),
            getPersistedServiceManagement(serviceManagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateServiceManagementWithPatch() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceManagement using partial update
        ServiceManagement partialUpdatedServiceManagement = new ServiceManagement();
        partialUpdatedServiceManagement.setId(serviceManagement.getId());

        partialUpdatedServiceManagement
            .processingcode(UPDATED_PROCESSINGCODE)
            .active(UPDATED_ACTIVE)
            .createdby(UPDATED_CREATEDBY)
            .datecreated(UPDATED_DATECREATED)
            .approved(UPDATED_APPROVED)
            .approvedby(UPDATED_APPROVEDBY)
            .approveddate(UPDATED_APPROVEDDATE)
            .adaptortype(UPDATED_ADAPTORTYPE)
            .destination(UPDATED_DESTINATION)
            .thirdpartyresponse(UPDATED_THIRDPARTYRESPONSE)
            .telco(UPDATED_TELCO)
            .description(UPDATED_DESCRIPTION)
            .remarks(UPDATED_REMARKS)
            .sessionid(UPDATED_SESSIONID)
            .reworkby(UPDATED_REWORKBY);

        restServiceManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServiceManagement))
            )
            .andExpect(status().isOk());

        // Validate the ServiceManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServiceManagementUpdatableFieldsEquals(
            partialUpdatedServiceManagement,
            getPersistedServiceManagement(partialUpdatedServiceManagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(serviceManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(serviceManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceManagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceManagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(serviceManagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceManagement() throws Exception {
        // Initialize the database
        insertedServiceManagement = serviceManagementRepository.saveAndFlush(serviceManagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the serviceManagement
        restServiceManagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceManagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return serviceManagementRepository.count();
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

    protected ServiceManagement getPersistedServiceManagement(ServiceManagement serviceManagement) {
        return serviceManagementRepository.findById(serviceManagement.getId()).orElseThrow();
    }

    protected void assertPersistedServiceManagementToMatchAllProperties(ServiceManagement expectedServiceManagement) {
        assertServiceManagementAllPropertiesEquals(expectedServiceManagement, getPersistedServiceManagement(expectedServiceManagement));
    }

    protected void assertPersistedServiceManagementToMatchUpdatableProperties(ServiceManagement expectedServiceManagement) {
        assertServiceManagementAllUpdatablePropertiesEquals(
            expectedServiceManagement,
            getPersistedServiceManagement(expectedServiceManagement)
        );
    }
}
