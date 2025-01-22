<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="transactions">
        <h2 class="jh-entity-heading" data-cy="transactionsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.transactions.detail.title')"></span> {{ transactions.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.processed')"></span>
          </dt>
          <dd>
            <span>{{ transactions.processed }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmap')"></span>
          </dt>
          <dd>
            <span>{{ transactions.incomingbitmap }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmap')"></span>
          </dt>
          <dd>
            <span>{{ transactions.outgoingbitmap }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.inmessage')"></span>
          </dt>
          <dd>
            <span>{{ transactions.inmessage }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.messagetocbs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.messagetocbs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.messagefromcbs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.messagefromcbs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.cbsprocess')"></span>
          </dt>
          <dd>
            <span>{{ transactions.cbsprocess }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.cbsonline')"></span>
          </dt>
          <dd>
            <span>{{ transactions.cbsonline }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.cbsresponse')"></span>
          </dt>
          <dd>
            <span>{{ transactions.cbsresponse }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.responsemessage')"></span>
          </dt>
          <dd>
            <span>{{ transactions.responsemessage }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.responsesent')"></span>
          </dt>
          <dd>
            <span>{{ transactions.responsesent }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.channel')"></span>
          </dt>
          <dd>
            <span>{{ transactions.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata')"></span>
          </dt>
          <dd>
            <span>{{ transactions.originaldata }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.field39resp')"></span>
          </dt>
          <dd>
            <span>{{ transactions.field39resp }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.narration')"></span>
          </dt>
          <dd>
            <span>{{ transactions.narration }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.authorised')"></span>
          </dt>
          <dd>
            <span>{{ transactions.authorised }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.branchcode')"></span>
          </dt>
          <dd>
            <span>{{ transactions.branchcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.field39original')"></span>
          </dt>
          <dd>
            <span>{{ transactions.field39original }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.messageclass')"></span>
          </dt>
          <dd>
            <span>{{ transactions.messageclass }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.txncode')"></span>
          </dt>
          <dd>
            <span>{{ transactions.txncode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.currcode')"></span>
          </dt>
          <dd>
            <span>{{ transactions.currcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.device')"></span>
          </dt>
          <dd>
            <span>{{ transactions.device }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.branch2')"></span>
          </dt>
          <dd>
            <span>{{ transactions.branch2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.longerbranch')"></span>
          </dt>
          <dd>
            <span>{{ transactions.longerbranch }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.datex')"></span>
          </dt>
          <dd>
            <span v-if="transactions.datex">{{ formatDateLong(transactions.datex) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.timex')"></span>
          </dt>
          <dd>
            <span>{{ transactions.timex }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.posted')"></span>
          </dt>
          <dd>
            <span>{{ transactions.posted }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.attempts')"></span>
          </dt>
          <dd>
            <span>{{ transactions.attempts }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata2')"></span>
          </dt>
          <dd>
            <span>{{ transactions.originaldata2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.commission')"></span>
          </dt>
          <dd>
            <span>{{ transactions.commission }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.responsecreated')"></span>
          </dt>
          <dd>
            <span>{{ transactions.responsecreated }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.online')"></span>
          </dt>
          <dd>
            <span>{{ transactions.online }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata3')"></span>
          </dt>
          <dd>
            <span>{{ transactions.originaldata3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.toswitch')"></span>
          </dt>
          <dd>
            <span>{{ transactions.toswitch }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.fromswitch')"></span>
          </dt>
          <dd>
            <span>{{ transactions.fromswitch }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.tocbs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.tocbs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.fromcbs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.fromcbs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.postinglegs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.postinglegs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.commissiontxncode')"></span>
          </dt>
          <dd>
            <span>{{ transactions.commissiontxncode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.hostref')"></span>
          </dt>
          <dd>
            <span>{{ transactions.hostref }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.requestcreated')"></span>
          </dt>
          <dd>
            <span>{{ transactions.requestcreated }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.requestmessage')"></span>
          </dt>
          <dd>
            <span>{{ transactions.requestmessage }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmapflex')"></span>
          </dt>
          <dd>
            <span>{{ transactions.outgoingbitmapflex }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmapflex')"></span>
          </dt>
          <dd>
            <span>{{ transactions.incomingbitmapflex }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.requestsent')"></span>
          </dt>
          <dd>
            <span>{{ transactions.requestsent }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.minicbs')"></span>
          </dt>
          <dd>
            <span>{{ transactions.minicbs }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.reversed')"></span>
          </dt>
          <dd>
            <span>{{ transactions.reversed }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.offlinesenttohost')"></span>
          </dt>
          <dd>
            <span>{{ transactions.offlinesenttohost }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.offlineresponse')"></span>
          </dt>
          <dd>
            <span>{{ transactions.offlineresponse }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.sourceLongerface')"></span>
          </dt>
          <dd>
            <span>{{ transactions.sourceLongerface }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.mtirrn')"></span>
          </dt>
          <dd>
            <span>{{ transactions.mtirrn }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.hostresponsecode')"></span>
          </dt>
          <dd>
            <span>{{ transactions.hostresponsecode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.field48')"></span>
          </dt>
          <dd>
            <span>{{ transactions.field48 }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.source')"></span>
          </dt>
          <dd>
            <span>{{ transactions.source }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="transactions.id"
          :to="{ name: 'TransactionsEdit', params: { transactionsId: transactions.id } }"
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

<script lang="ts" src="./transactions-details.component.ts"></script>
