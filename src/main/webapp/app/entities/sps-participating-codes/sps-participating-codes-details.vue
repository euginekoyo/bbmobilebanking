<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sPSParticipatingCodes">
        <h2 class="jh-entity-heading" data-cy="sPSParticipatingCodesDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.detail.title')"></span> {{ sPSParticipatingCodes.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.biccode')"></span>
          </dt>
          <dd>
            <span>{{ sPSParticipatingCodes.biccode }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicname')"></span>
          </dt>
          <dd>
            <span>{{ sPSParticipatingCodes.bicname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicstatus')"></span>
          </dt>
          <dd>
            <span>{{ sPSParticipatingCodes.bicstatus }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="sPSParticipatingCodes.id"
          :to="{ name: 'SPSParticipatingCodesEdit', params: { sPSParticipatingCodesId: sPSParticipatingCodes.id } }"
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

<script lang="ts" src="./sps-participating-codes-details.component.ts"></script>
