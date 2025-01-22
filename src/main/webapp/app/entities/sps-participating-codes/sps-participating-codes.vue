<template>
  <div>
    <h2 id="page-heading" data-cy="SPSParticipatingCodesHeading">
      <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.home.title')" id="sps-participating-codes-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SPSParticipatingCodesCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sps-participating-codes"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sPSParticipatingCodes && sPSParticipatingCodes.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sPSParticipatingCodes && sPSParticipatingCodes.length > 0">
      <table class="table table-striped" aria-describedby="sPSParticipatingCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.biccode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicstatus')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sPSParticipatingCodes in sPSParticipatingCodes" :key="sPSParticipatingCodes.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SPSParticipatingCodesView', params: { sPSParticipatingCodesId: sPSParticipatingCodes.id } }">{{
                sPSParticipatingCodes.id
              }}</router-link>
            </td>
            <td>{{ sPSParticipatingCodes.biccode }}</td>
            <td>{{ sPSParticipatingCodes.bicname }}</td>
            <td>{{ sPSParticipatingCodes.bicstatus }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SPSParticipatingCodesView', params: { sPSParticipatingCodesId: sPSParticipatingCodes.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SPSParticipatingCodesEdit', params: { sPSParticipatingCodesId: sPSParticipatingCodes.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(sPSParticipatingCodes)"
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
          id="bbMobileBankingAdminApp.sPSParticipatingCodes.delete.question"
          data-cy="sPSParticipatingCodesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-sPSParticipatingCodes-heading"
          v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sPSParticipatingCodes"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeSPSParticipatingCodes()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sps-participating-codes.component.ts"></script>
