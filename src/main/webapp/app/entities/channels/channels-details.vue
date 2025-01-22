<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="channels">
        <h2 class="jh-entity-heading" data-cy="channelsDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.channels.detail.title')"></span> {{ channels.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.channels.channel')"></span>
          </dt>
          <dd>
            <span>{{ channels.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.channels.description')"></span>
          </dt>
          <dd>
            <span>{{ channels.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.channels.bin')"></span>
          </dt>
          <dd>
            <span>{{ channels.bin }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="channels.id" :to="{ name: 'ChannelsEdit', params: { channelsId: channels.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./channels-details.component.ts"></script>
