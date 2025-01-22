<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="billers">
        <h2 class="jh-entity-heading" data-cy="billersDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.billers.detail.title')"></span> {{ billers.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.billerid')"></span>
          </dt>
          <dd>
            <span>{{ billers.billerid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.description')"></span>
          </dt>
          <dd>
            <span>{{ billers.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.billercollectionaccount')"></span>
          </dt>
          <dd>
            <span>{{ billers.billercollectionaccount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.datecreated')"></span>
          </dt>
          <dd>
            <span v-if="billers.datecreated">{{ formatDateLong(billers.datecreated) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.createdby')"></span>
          </dt>
          <dd>
            <span>{{ billers.createdby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.approved')"></span>
          </dt>
          <dd>
            <span>{{ billers.approved }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.approvedby')"></span>
          </dt>
          <dd>
            <span>{{ billers.approvedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.approveddate')"></span>
          </dt>
          <dd>
            <span v-if="billers.approveddate">{{ formatDateLong(billers.approveddate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.chargableproductid')"></span>
          </dt>
          <dd>
            <span>{{ billers.chargableproductid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.nonchargableproductid')"></span>
          </dt>
          <dd>
            <span>{{ billers.nonchargableproductid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.usdbillercollectionaccount')"></span>
          </dt>
          <dd>
            <span>{{ billers.usdbillercollectionaccount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.enableduplicatecheck')"></span>
          </dt>
          <dd>
            <span>{{ billers.enableduplicatecheck }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.remarks')"></span>
          </dt>
          <dd>
            <span>{{ billers.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.sessionid')"></span>
          </dt>
          <dd>
            <span>{{ billers.sessionid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.reworkby')"></span>
          </dt>
          <dd>
            <span>{{ billers.reworkby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.status')"></span>
          </dt>
          <dd>
            <span>{{ billers.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.active')"></span>
          </dt>
          <dd>
            <span>{{ billers.active }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.billers.rework')"></span>
          </dt>
          <dd>
            <span>{{ billers.rework }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="billers.id" :to="{ name: 'BillersEdit', params: { billersId: billers.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./billers-details.component.ts"></script>
