<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="messagesSms">
        <h2 class="jh-entity-heading" data-cy="messagesSmsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.messagesSms.detail.title')"></span> {{ messagesSms.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.trndatetime')"></span>
          </dt>
          <dd>
            <span v-if="messagesSms.trndatetime">{{ formatDateLong(messagesSms.trndatetime) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.phonenumber')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.phonenumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.transactionno')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.transactionno }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.accountnumber')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.accountnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.message')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.message }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.channel')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.trials')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.trials }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.priority')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.priority }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.responsecode')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.responsecode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.responsemsg')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.responsemsg }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.sent')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.sent }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.delivered')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.delivered }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.txntype')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.txntype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.errorexception')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.errorexception }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.datecreated')"></span>
          </dt>
          <dd>
            <span v-if="messagesSms.datecreated">{{ formatDateLong(messagesSms.datecreated) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.datesent')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.datesent }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.rtpsreqtime')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.rtpsreqtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.fxgenerated')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.fxgenerated }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.taxprocessed')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.taxprocessed }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.batchnumber')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.batchnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.batchnumbertax')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.batchnumbertax }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.responsetime')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.responsetime }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.pduseqid')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.pduseqid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.remarks')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.resendby')"></span>
          </dt>
          <dd>
            <span>{{ messagesSms.resendby }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="messagesSms.id"
          :to="{ name: 'MessagesSmsEdit', params: { messagesSmsId: messagesSms.id } }"
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

<script lang="ts" src="./messages-sms-details.component.ts"></script>
