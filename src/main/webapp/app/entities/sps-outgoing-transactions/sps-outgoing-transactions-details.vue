<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sPSOutgoingTransactions">
        <h2 class="jh-entity-heading" data-cy="sPSOutgoingTransactionsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.detail.title')"></span> {{ sPSOutgoingTransactions.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageid')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.messageid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.channelcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.channelcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackurl')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.callbackurl }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messagetype')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.messagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transcurrency')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.transcurrency }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsname')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.debtorsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsaccountid')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.debtorsaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsbankcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.debtorsbankcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsphone')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.debtorsphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryname')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.beneficiaryname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryaccountid')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.beneficiaryaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiarybankcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.beneficiarybankcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryphone')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.beneficiaryphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.narration')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.narration }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.externalreference')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.externalreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsreference')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.cbsreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageendtoendid')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.messageendtoendid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.transactionstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.transactionstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.spsstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.spsstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.cbsstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.cbsstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.requestInstanttime')"></span>
          </dt>
          <dd>
            <span v-if="sPSOutgoingTransactions.requestInstanttime">{{ formatDateLong(sPSOutgoingTransactions.requestInstanttime) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.isomessagetype')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.isomessagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.requestjson')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.requestjson }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsrequestxml')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.spsrequestxml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsresponsexml')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.spsresponsexml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.amount')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.callbackstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSOutgoingTransactions.callbackstatusdesc }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="sPSOutgoingTransactions.id"
          :to="{ name: 'SPSOutgoingTransactionsEdit', params: { sPSOutgoingTransactionsId: sPSOutgoingTransactions.id } }"
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

<script lang="ts" src="./sps-outgoing-transactions-details.component.ts"></script>
