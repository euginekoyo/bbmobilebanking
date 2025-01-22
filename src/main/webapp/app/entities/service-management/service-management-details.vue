<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="serviceManagement">
        <h2 class="jh-entity-heading" data-cy="serviceManagementDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.detail.title')"></span> {{ serviceManagement.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.processingcode')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.processingcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.active')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.active }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.createdby')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.createdby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.datecreated')"></span>
          </dt>
          <dd>
            <span v-if="serviceManagement.datecreated">{{ formatDateLong(serviceManagement.datecreated) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approved')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.approved }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approvedby')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.approvedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approveddate')"></span>
          </dt>
          <dd>
            <span v-if="serviceManagement.approveddate">{{ formatDateLong(serviceManagement.approveddate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.adaptortype')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.adaptortype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.destination')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.destination }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.thirdpartyresponse')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.thirdpartyresponse }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.telco')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.telco }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.description')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.remarks')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.sessionid')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.sessionid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.reworkby')"></span>
          </dt>
          <dd>
            <span>{{ serviceManagement.reworkby }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="serviceManagement.id"
          :to="{ name: 'ServiceManagementEdit', params: { serviceManagementId: serviceManagement.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./service-management-details.component.ts"></script>
