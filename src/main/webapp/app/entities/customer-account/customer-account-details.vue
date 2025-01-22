<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="customerAccount">
        <h2 class="jh-entity-heading" data-cy="customerAccountDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.customerAccount.detail.title')"></span> {{ customerAccount.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.customerid')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.customerid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.accountnumber')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.accountnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.accountclass')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.accountclass }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.customernumber')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.customernumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.cif')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.cif }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.timelinked')"></span>
          </dt>
          <dd>
            <span v-if="customerAccount.timelinked">{{ formatDateLong(customerAccount.timelinked) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.blocked')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.blocked }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.stopped')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.stopped }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.dormant')"></span>
          </dt>
          <dd>
            <span>{{ customerAccount.dormant }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="customerAccount.id"
          :to="{ name: 'CustomerAccountEdit', params: { customerAccountId: customerAccount.id } }"
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

<script lang="ts" src="./customer-account-details.component.ts"></script>
