<template>
  <div>
    <h2 id="page-heading" data-cy="MessageTemplateHeading">
      <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.home.title')" id="message-template-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'MessageTemplateCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-message-template"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && messageTemplates && messageTemplates.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.messageTemplate.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="messageTemplates && messageTemplates.length > 0">
      <table class="table table-striped" aria-describedby="messageTemplates">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messageTemplate.description')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messageenglish')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagesomali')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messageTemplate.createdon')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="messageTemplate in messageTemplates" :key="messageTemplate.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MessageTemplateView', params: { messageTemplateId: messageTemplate.id } }">{{
                messageTemplate.id
              }}</router-link>
            </td>
            <td>{{ messageTemplate.messagetype }}</td>
            <td>{{ messageTemplate.description }}</td>
            <td>{{ messageTemplate.messageenglish }}</td>
            <td>{{ messageTemplate.messagesomali }}</td>
            <td>{{ formatDateShort(messageTemplate.createdon) || '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'MessageTemplateView', params: { messageTemplateId: messageTemplate.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'MessageTemplateEdit', params: { messageTemplateId: messageTemplate.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(messageTemplate)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="bbMobileBankingAdminApp.messageTemplate.delete.question"
          data-cy="messageTemplateDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-messageTemplate-heading"
          v-text="t$('bbMobileBankingAdminApp.messageTemplate.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-messageTemplate"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeMessageTemplate()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./message-template.component.ts"></script>
