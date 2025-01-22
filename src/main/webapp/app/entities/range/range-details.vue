<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="range">
        <h2 class="jh-entity-heading" data-cy="rangeDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.range.detail.title')"></span> {{ range.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.rangefrom')"></span>
          </dt>
          <dd>
            <span>{{ range.rangefrom }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.rangeto')"></span>
          </dt>
          <dd>
            <span>{{ range.rangeto }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.amount')"></span>
          </dt>
          <dd>
            <span>{{ range.amount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.txntype')"></span>
          </dt>
          <dd>
            <span>{{ range.txntype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.txncode')"></span>
          </dt>
          <dd>
            <span>{{ range.txncode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.chargeid')"></span>
          </dt>
          <dd>
            <span>{{ range.chargeid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.range.channel')"></span>
          </dt>
          <dd>
            <span>{{ range.channel }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="range.id" :to="{ name: 'RangeEdit', params: { rangeId: range.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./range-details.component.ts"></script>
