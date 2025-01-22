<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="pinResetHistory">
        <h2 class="jh-entity-heading" data-cy="pinResetHistoryDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.detail.title')"></span> {{ pinResetHistory.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.phonenumber')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.phonenumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.customername')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.customername }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockedon')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinblockedon }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockremarks')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinblockremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetby')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinresetby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinreseton')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinreseton }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedby')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinresetapprovedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedon')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinresetapprovedon }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetremarks')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.pinresetremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.branchcode')"></span>
          </dt>
          <dd>
            <span>{{ pinResetHistory.branchcode }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="pinResetHistory.id"
          :to="{ name: 'PinResetHistoryEdit', params: { pinResetHistoryId: pinResetHistory.id } }"
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

<script lang="ts" src="./pin-reset-history-details.component.ts"></script>
