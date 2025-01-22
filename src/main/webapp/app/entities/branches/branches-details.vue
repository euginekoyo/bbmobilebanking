<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="branches">
        <h2 class="jh-entity-heading" data-cy="branchesDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.branches.detail.title')"></span> {{ branches.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.branchname')"></span>
          </dt>
          <dd>
            <span>{{ branches.branchname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.branchcode')"></span>
          </dt>
          <dd>
            <span>{{ branches.branchcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.approved')"></span>
          </dt>
          <dd>
            <span>{{ branches.approved }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.email')"></span>
          </dt>
          <dd>
            <span>{{ branches.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.address')"></span>
          </dt>
          <dd>
            <span>{{ branches.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.phone')"></span>
          </dt>
          <dd>
            <span>{{ branches.phone }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.location')"></span>
          </dt>
          <dd>
            <span>{{ branches.location }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.contactperson')"></span>
          </dt>
          <dd>
            <span>{{ branches.contactperson }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.remarks')"></span>
          </dt>
          <dd>
            <span>{{ branches.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.createdby')"></span>
          </dt>
          <dd>
            <span>{{ branches.createdby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.createdon')"></span>
          </dt>
          <dd>
            <span v-if="branches.createdon">{{ formatDateLong(branches.createdon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.approvedby')"></span>
          </dt>
          <dd>
            <span>{{ branches.approvedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.approvedon')"></span>
          </dt>
          <dd>
            <span>{{ branches.approvedon }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.checkerremarks')"></span>
          </dt>
          <dd>
            <span>{{ branches.checkerremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.deletedby')"></span>
          </dt>
          <dd>
            <span>{{ branches.deletedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.deletedon')"></span>
          </dt>
          <dd>
            <span v-if="branches.deletedon">{{ formatDateLong(branches.deletedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.deleteremarks')"></span>
          </dt>
          <dd>
            <span>{{ branches.deleteremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.deleted')"></span>
          </dt>
          <dd>
            <span>{{ branches.deleted }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.declined')"></span>
          </dt>
          <dd>
            <span>{{ branches.declined }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.declineddon')"></span>
          </dt>
          <dd>
            <span>{{ branches.declineddon }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.declinedby')"></span>
          </dt>
          <dd>
            <span>{{ branches.declinedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.sessionid')"></span>
          </dt>
          <dd>
            <span>{{ branches.sessionid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.reworked')"></span>
          </dt>
          <dd>
            <span>{{ branches.reworked }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.reworkedby')"></span>
          </dt>
          <dd>
            <span>{{ branches.reworkedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.reworkedon')"></span>
          </dt>
          <dd>
            <span v-if="branches.reworkedon">{{ formatDateLong(branches.reworkedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.district')"></span>
          </dt>
          <dd>
            <span>{{ branches.district }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.region')"></span>
          </dt>
          <dd>
            <span>{{ branches.region }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.regionname')"></span>
          </dt>
          <dd>
            <span>{{ branches.regionname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.branches.reporting')"></span>
          </dt>
          <dd>
            <span>{{ branches.reporting }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="branches.id" :to="{ name: 'BranchesEdit', params: { branchesId: branches.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./branches-details.component.ts"></script>
