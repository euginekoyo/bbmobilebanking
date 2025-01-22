<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="charge">
        <h2 class="jh-entity-heading" data-cy="chargeDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.charge.detail.title')"></span> {{ charge.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.txntype')"></span>
          </dt>
          <dd>
            <span>{{ charge.txntype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.feemode')"></span>
          </dt>
          <dd>
            <span>{{ charge.feemode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.amount')"></span>
          </dt>
          <dd>
            <span>{{ charge.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.datecreated')"></span>
          </dt>
          <dd>
            <span v-if="charge.datecreated">{{ formatDateLong(charge.datecreated) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.createdby')"></span>
          </dt>
          <dd>
            <span>{{ charge.createdby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.approved')"></span>
          </dt>
          <dd>
            <span>{{ charge.approved }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.approvedby')"></span>
          </dt>
          <dd>
            <span>{{ charge.approvedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.channel')"></span>
          </dt>
          <dd>
            <span>{{ charge.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.txncode')"></span>
          </dt>
          <dd>
            <span>{{ charge.txncode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.description')"></span>
          </dt>
          <dd>
            <span>{{ charge.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.approveddate')"></span>
          </dt>
          <dd>
            <span v-if="charge.approveddate">{{ formatDateLong(charge.approveddate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.chargeRange')"></span>
          </dt>
          <dd>
            <div v-if="charge.chargeRange">
              <router-link :to="{ name: 'ChargeRangeView', params: { chargeRangeId: charge.chargeRange.id } }">{{
                charge.chargeRange.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.charge.range')"></span>
          </dt>
          <dd>
            <div v-if="charge.range">
              <router-link :to="{ name: 'RangeView', params: { rangeId: charge.range.id } }">{{ charge.range.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="charge.id" :to="{ name: 'ChargeEdit', params: { chargeId: charge.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./charge-details.component.ts"></script>
