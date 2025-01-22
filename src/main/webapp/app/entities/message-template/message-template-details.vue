<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="messageTemplate">
        <h2 class="jh-entity-heading" data-cy="messageTemplateDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.detail.title')"></span> {{ messageTemplate.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagetype')"></span>
          </dt>
          <dd>
            <span>{{ messageTemplate.messagetype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.description')"></span>
          </dt>
          <dd>
            <span>{{ messageTemplate.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messageenglish')"></span>
          </dt>
          <dd>
            <span>{{ messageTemplate.messageenglish }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagesomali')"></span>
          </dt>
          <dd>
            <span>{{ messageTemplate.messagesomali }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.createdon')"></span>
          </dt>
          <dd>
            <span v-if="messageTemplate.createdon">{{ formatDateLong(messageTemplate.createdon) }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="messageTemplate.id"
          :to="{ name: 'MessageTemplateEdit', params: { messageTemplateId: messageTemplate.id } }"
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

<script lang="ts" src="./message-template-details.component.ts"></script>
