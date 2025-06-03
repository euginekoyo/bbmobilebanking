<template>
  <div>
    <h2 id="page-heading" data-cy="PinResetHistoryHeading">
      <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.home.title')" id="pin-reset-history-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.home.refreshListLabel')"></span>
        </button>
        <!-- <router-link :to="{ name: 'PinResetHistoryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-pin-reset-history"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.home.createLabel')"></span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && pinResetHistories && pinResetHistories.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="pinResetHistories && pinResetHistories.length > 0">
      <table class="table table-striped" aria-describedby="pinResetHistories">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.phonenumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.customername')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinreseton')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.pinResetHistory.branchcode')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pinResetHistory in pinResetHistories" :key="pinResetHistory.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PinResetHistoryView', params: { pinResetHistoryId: pinResetHistory.id } }">{{
                pinResetHistory.id
              }}</router-link>
            </td>
            <td>{{ pinResetHistory.phonenumber }}</td>
            <td>{{ pinResetHistory.customername }}</td>
            <td>{{ pinResetHistory.pinblockedon }}</td>
            <td>{{ pinResetHistory.pinblockremarks }}</td>
            <td>{{ pinResetHistory.pinresetby }}</td>
            <td>{{ pinResetHistory.pinreseton }}</td>
            <td>{{ pinResetHistory.pinresetapprovedby }}</td>
            <td>{{ pinResetHistory.pinresetapprovedon }}</td>
            <td>{{ pinResetHistory.pinresetremarks }}</td>
            <td>{{ pinResetHistory.branchcode }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'PinResetHistoryView', params: { pinResetHistoryId: pinResetHistory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <!-- <router-link
                  :to="{ name: 'PinResetHistoryEdit', params: { pinResetHistoryId: pinResetHistory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link> -->
                <!-- <b-button
                  @click="prepareRemove(pinResetHistory)"
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
          id="bbMobileBankingAdminApp.pinResetHistory.delete.question"
          data-cy="pinResetHistoryDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-pinResetHistory-heading"
          v-text="t$('bbMobileBankingAdminApp.pinResetHistory.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-pinResetHistory"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removePinResetHistory()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./pin-reset-history.component.ts"></script>
