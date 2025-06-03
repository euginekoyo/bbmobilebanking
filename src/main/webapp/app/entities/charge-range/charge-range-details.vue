<template>
  <div class="row justify-content-center">
    <div class="col-6">
      <div v-if="chargeRange">
        <h2 class="jh-entity-heading" data-cy="chargeRangeDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.detail.title')"></span> {{ chargeRange.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.billerid')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.billerid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.processingcode')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.processingcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.max')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.max }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.min')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.min }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.amount')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.createdby')"></span>
          </dt>
          <dd>
            <span>{{ chargeRange.createdby }}</span>
          </dd>
        </dl>
      </div>
    </div>
    <div class="col-6">
      <div v-if="chargeRange">
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedby')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.approvedby }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.createdat')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.createdat }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedon')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.approvedon }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.approved')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.approved }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.declined')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.declined }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.declinedby')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.declinedby }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.chargeid')"></span>
        </dt>
        <dd>
          <span>{{ chargeRange.chargeid }}</span>
        </dd>
      </div>
    </div>
    <div class="col-3">
      <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
        <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
      </button>
    </div>
    <div class="col-3">
      <router-link
        v-if="chargeRange.id"
        :to="{ name: 'ChargeRangeEdit', params: { chargeRangeId: chargeRange.id } }"
        custom
        v-slot="{ navigate }"
      >
        <button @click="navigate" class="btn btn-primary">
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
        </button>
      </router-link>
    </div>
    <div class="col-3" v-if="chargeRange.approved == 0">
      <button type="submit" id="save-entity" data-cy="entityCreateSaveButton" class="btn btn-success" v-on:click="approve()">
        <font-awesome-icon icon="check"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.approve')"></span>
      </button>
    </div>
    <div class="col-3" v-if="chargeRange.approved == 0">
      <button type="submit" id="save-entity" data-cy="entityCreateSaveButton" class="btn btn-danger" v-on:click="reject()">
        <font-awesome-icon icon="times"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.reject')"></span>
      </button>
    </div>
  </div>
</template>

<script lang="ts" src="./charge-range-details.component.ts"></script>
