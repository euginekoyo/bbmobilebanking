<template>
  <div class="row justify-content-center">
    <div class="col-6">
      <div v-if="limits">
        <h2 class="jh-entity-heading" data-cy="limitsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.limits.detail.title')"></span> {{ limits.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.transactiontype')"></span>
          </dt>
          <dd>
            <span>{{ limits.transactiontype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.procode')"></span>
          </dt>
          <dd>
            <span>{{ limits.procode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.channel')"></span>
          </dt>
          <dd>
            <span>{{ limits.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.transactionlimit')"></span>
          </dt>
          <dd>
            <span>{{ limits.transactionlimit }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.dailylimit')"></span>
          </dt>
          <dd>
            <span>{{ limits.dailylimit }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.registeredby')"></span>
          </dt>
          <dd>
            <span>{{ limits.registeredby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.limits.registereddate')"></span>
          </dt>
          <dd>
            <span>{{ limits.registereddate }}</span>
          </dd>
        </dl>
      </div>
    </div>
    <div class="col-6">
      <div v-if="limits">
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.approved')"></span>
        </dt>
        <dd>
          <span>{{ limits.approved }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.approvedby')"></span>
        </dt>
        <dd>
          <span>{{ limits.approvedby }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.approveddate')"></span>
        </dt>
        <dd>
          <span>{{ limits.approveddate }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.updatedby')"></span>
        </dt>
        <dd>
          <span>{{ limits.updatedby }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.updateddate')"></span>
        </dt>
        <dd>
          <span>{{ limits.updateddate }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.rework')"></span>
        </dt>
        <dd>
          <span>{{ limits.rework }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.reworkby')"></span>
        </dt>
        <dd>
          <span>{{ limits.reworkby }}</span>
        </dd>
        <dt>
          <span v-text="t$('bbMobileBankingAdminApp.limits.sessionid')"></span>
        </dt>
        <dd>
          <span>{{ limits.sessionid }}</span>
        </dd>
      </div>
    </div>
    <div class="col-3">
      <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
        <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
      </button>
    </div>
    <div class="col-3">
      <router-link v-if="limits.id" :to="{ name: 'LimitsEdit', params: { limitsId: limits.id } }" custom v-slot="{ navigate }">
        <button @click="navigate" class="btn btn-primary">
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
        </button>
      </router-link>
    </div>
    <div class="col-3" v-if="limits.approved == 0">
      <button type="submit" id="save-entity" data-cy="entityCreateSaveButton" class="btn btn-success" v-on:click="approve()">
        <font-awesome-icon icon="check"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.approve')"></span>
      </button>
    </div>
    <div class="col-3" v-if="limits.approved == 0">
      <button type="submit" id="save-entity" data-cy="entityCreateSaveButton" class="btn btn-danger" v-on:click="reject()">
        <font-awesome-icon icon="times"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.reject')"></span>
      </button>
    </div>
  </div>
</template>

<script lang="ts" src="./limits-details.component.ts"></script>
