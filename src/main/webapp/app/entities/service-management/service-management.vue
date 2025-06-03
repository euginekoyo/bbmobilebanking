<template>
  <div>
    <h2 id="page-heading" data-cy="ServiceManagementHeading">
      <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.home.title')" id="service-management-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.home.refreshListLabel')"></span>
        </button>
        <!-- <router-link :to="{ name: 'ServiceManagementCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-service-management"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.home.createLabel')"></span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && serviceManagements && serviceManagements.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.serviceManagement.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="serviceManagements && serviceManagements.length > 0">
      <table class="table table-striped" aria-describedby="serviceManagements">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.processingcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.active')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.datecreated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.approveddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.adaptortype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.destination')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.thirdpartyresponse')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.telco')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.description')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.remarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.sessionid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.serviceManagement.reworkby')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="serviceManagement in serviceManagements" :key="serviceManagement.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ServiceManagementView', params: { serviceManagementId: serviceManagement.id } }">{{
                serviceManagement.id
              }}</router-link>
            </td>
            <td>{{ serviceManagement.processingcode }}</td>
            <td>{{ serviceManagement.active }}</td>
            <td>{{ serviceManagement.createdby }}</td>
            <td>{{ formatDateShort(serviceManagement.datecreated) || '' }}</td>
            <td>{{ serviceManagement.approved }}</td>
            <td>{{ serviceManagement.approvedby }}</td>
            <td>{{ formatDateShort(serviceManagement.approveddate) || '' }}</td>
            <td>{{ serviceManagement.adaptortype }}</td>
            <td>{{ serviceManagement.destination }}</td>
            <td>{{ serviceManagement.thirdpartyresponse }}</td>
            <td>{{ serviceManagement.telco }}</td>
            <td>{{ serviceManagement.description }}</td>
            <td>{{ serviceManagement.remarks }}</td>
            <td>{{ serviceManagement.sessionid }}</td>
            <td>{{ serviceManagement.reworkby }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ServiceManagementView', params: { serviceManagementId: serviceManagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <!-- <router-link
                  :to="{ name: 'ServiceManagementEdit', params: { serviceManagementId: serviceManagement.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link> -->
                <!-- <b-button
                  @click="prepareRemove(serviceManagement)"
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
          id="bbMobileBankingAdminApp.serviceManagement.delete.question"
          data-cy="serviceManagementDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-serviceManagement-heading"
          v-text="t$('bbMobileBankingAdminApp.serviceManagement.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-serviceManagement"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeServiceManagement()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./service-management.component.ts"></script>
