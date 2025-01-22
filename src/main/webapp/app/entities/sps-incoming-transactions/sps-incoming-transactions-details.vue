<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sPSIncomingTransactions">
        <h2 class="jh-entity-heading" data-cy="sPSIncomingTransactionsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.detail.title')"></span> {{ sPSIncomingTransactions.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messageid')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.messageid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.channelcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.channelcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.callbackurl')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.callbackurl }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messagetype')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.messagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transcurrency')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.transcurrency }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsname')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.debtorsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsaccountid')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.debtorsaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsbankcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.debtorsbankcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsphone')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.debtorsphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryname')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.beneficiaryname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryaccountid')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.beneficiaryaccountid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiarybankcode')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.beneficiarybankcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryphone')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.beneficiaryphone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.narration')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.narration }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.externalreference')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.externalreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsreference')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.cbsreference }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messageendtoendid')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.messageendtoendid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transactionstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.transactionstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transactionstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.transactionstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.spsstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.spsstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.cbsstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsstatusdesc')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.cbsstatusdesc }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.requestInstanttime')"></span>
          </dt>
          <dd>
            <span v-if="sPSIncomingTransactions.requestInstanttime">{{ formatDateLong(sPSIncomingTransactions.requestInstanttime) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.isomessagetype')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.isomessagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.requestjson')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.requestjson }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsrequestxml')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.spsrequestxml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsresponsexml')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.spsresponsexml }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.amount')"></span>
          </dt>
          <dd>
            <span>{{ sPSIncomingTransactions.amount }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="sPSIncomingTransactions.id"
          :to="{ name: 'SPSIncomingTransactionsEdit', params: { sPSIncomingTransactionsId: sPSIncomingTransactions.id } }"
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

<script lang="ts" src="./sps-incoming-transactions-details.component.ts"></script>
