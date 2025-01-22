<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="cBSTransactions">
        <h2 class="jh-entity-heading" data-cy="cBSTransactionsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.detail.title')"></span> {{ cBSTransactions.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messageid')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.messageid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.channelcode')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.channelcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messagetype')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.messagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.transcurrency')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.transcurrency }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsname')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.debtorsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsaccountid')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.debtorsaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsphone')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.debtorsphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsname')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.creditorsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsaccountid')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.creditorsaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsphone')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.creditorsphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.narration')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.narration }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.externalreference')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.externalreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsreference')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.cbsreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatus')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.cbsstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.cbsstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestInstanttime')"></span>
          </dt>
          <dd>
            <span v-if="cBSTransactions.requestInstanttime">{{ formatDateLong(cBSTransactions.requestInstanttime) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestjson')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.requestjson }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsrequestxml')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.cbsrequestxml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsresponsexml')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.cbsresponsexml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.amount')"></span>
          </dt>
          <dd>
            <span>{{ cBSTransactions.amount }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="cBSTransactions.id"
          :to="{ name: 'CBSTransactionsEdit', params: { cBSTransactionsId: cBSTransactions.id } }"
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

<script lang="ts" src="./cbs-transactions-details.component.ts"></script>
