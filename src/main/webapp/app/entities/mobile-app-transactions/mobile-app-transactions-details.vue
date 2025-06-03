<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="mobileAppTransactions">
        <h2 class="jh-entity-heading" data-cy="mobileAppTransactionsDetailsHeading">
          <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.detail.title')"></span> {{ mobileAppTransactions.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channel')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelIp')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.channelIp }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelReference')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.channelReference }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelTimestamp')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.channelTimestamp }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.clientId')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.clientId }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.createdAt')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.debitAccount')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.debitAccount }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.direction')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.direction }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.errorDescription')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.errorDescription }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.geolocation')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.geolocation }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.hostCode')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.hostCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.phoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.phoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseCode')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.responseCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseMessage')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.responseMessage }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionCode')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.transactionCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionType')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.transactionType }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgent')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.userAgent }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgentVersion')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.userAgentVersion }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.amount')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.chargeamount')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.chargeamount }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.creditAccount')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.creditAccount }}</span>
          </dd>
          <dt>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.cbsReference')"></span>
          </dt>
          <dd>
            <span>{{ mobileAppTransactions.cbsReference }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="mobileAppTransactions.id"
          :to="{ name: 'MobileAppTransactionsEdit', params: { mobileAppTransactionsId: mobileAppTransactions.id } }"
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

<script lang="ts" src="./mobile-app-transactions-details.component.ts"></script>
