<template>
  <div>
    <h2 id="page-heading" data-cy="BillersHeading">
      <span v-text="t$('bbMobileBankingAdminApp.billers.home.title')" id="billers-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.billers.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'BillersCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-billers"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.billers.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && billers && billers.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.billers.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="billers && billers.length > 0">
      <table class="table table-striped" aria-describedby="billers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.billerid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.description')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.billercollectionaccount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.datecreated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.approveddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.chargableproductid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.nonchargableproductid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.usdbillercollectionaccount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.enableduplicatecheck')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.remarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.sessionid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.reworkby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.status')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.active')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.billers.rework')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="billers in billers" :key="billers.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BillersView', params: { billersId: billers.id } }">{{ billers.id }}</router-link>
            </td>
            <td>{{ billers.billerid }}</td>
            <td>{{ billers.description }}</td>
            <td>{{ billers.billercollectionaccount }}</td>
            <td>{{ formatDateShort(billers.datecreated) || '' }}</td>
            <td>{{ billers.createdby }}</td>
            <td>{{ billers.approved }}</td>
            <td>{{ billers.approvedby }}</td>
            <td>{{ formatDateShort(billers.approveddate) || '' }}</td>
            <td>{{ billers.chargableproductid }}</td>
            <td>{{ billers.nonchargableproductid }}</td>
            <td>{{ billers.usdbillercollectionaccount }}</td>
            <td>{{ billers.enableduplicatecheck }}</td>
            <td>{{ billers.remarks }}</td>
            <td>{{ billers.sessionid }}</td>
            <td>{{ billers.reworkby }}</td>
            <td>{{ billers.status }}</td>
            <td>{{ billers.active }}</td>
            <td>{{ billers.rework }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BillersView', params: { billersId: billers.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BillersEdit', params: { billersId: billers.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(billers)"
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
          id="bbMobileBankingAdminApp.billers.delete.question"
          data-cy="billersDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-billers-heading" v-text="t$('bbMobileBankingAdminApp.billers.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-billers"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeBillers()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./billers.component.ts"></script>
