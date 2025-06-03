<template>
  <div>
    <h2 id="page-heading" data-cy="MessagesSmsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.messagesSms.home.title')" id="messages-sms-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.messagesSms.home.refreshListLabel')"></span>
        </button>
        <!-- <router-link :to="{ name: 'MessagesSmsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-messages-sms"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.messagesSms.home.createLabel')"></span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && messagesSms && messagesSms.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.messagesSms.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="messagesSms && messagesSms.length > 0">
      <table class="table table-striped" aria-describedby="messagesSms">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.trndatetime')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.phonenumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.transactionno')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.accountnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.message')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.trials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.priority')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.responsecode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.responsemsg')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.sent')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.delivered')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.messagesSms.resendby')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="messagesSms in messagesSms" :key="messagesSms.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MessagesSmsView', params: { messagesSmsId: messagesSms.id } }">{{ messagesSms.id }}</router-link>
            </td>
            <td>{{ formatDateShort(messagesSms.trndatetime) || '' }}</td>
            <td>{{ messagesSms.phonenumber }}</td>
            <td>{{ messagesSms.transactionno }}</td>
            <td>{{ messagesSms.accountnumber }}</td>
            <td>{{ messagesSms.message }}</td>
            <td>{{ messagesSms.channel }}</td>
            <td>{{ messagesSms.trials }}</td>
            <td>{{ messagesSms.priority }}</td>
            <td>{{ messagesSms.responsecode }}</td>
            <td>{{ messagesSms.responsemsg }}</td>
            <td>{{ messagesSms.sent }}</td>
            <td>{{ messagesSms.delivered }}</td>
            <td>{{ messagesSms.resendby }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MessagesSmsView', params: { messagesSmsId: messagesSms.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <!-- <router-link :to="{ name: 'MessagesSmsEdit', params: { messagesSmsId: messagesSms.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link> -->
                <!-- <b-button
                  @click="prepareRemove(messagesSms)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button> -->
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="bbMobileBankingAdminApp.messagesSms.delete.question"
          data-cy="messagesSmsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-messagesSms-heading" v-text="t$('bbMobileBankingAdminApp.messagesSms.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-messagesSms"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeMessagesSms()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./messages-sms.component.ts"></script>
