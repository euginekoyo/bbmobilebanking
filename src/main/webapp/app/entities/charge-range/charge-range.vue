<template>
  <div>
    <h2 id="page-heading" data-cy="ChargeRangeHeading">
      <span v-text="t$('bbMobileBankingAdminApp.chargeRange.home.title')" id="charge-range-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.chargeRange.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ChargeRangeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-charge-range"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.chargeRange.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && chargeRanges && chargeRanges.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.chargeRange.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="chargeRanges && chargeRanges.length > 0">
      <table class="table table-striped" aria-describedby="chargeRanges">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.billerid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.processingcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.max')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.min')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.amount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.createdat')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.declined')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.declinedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.chargeRange.chargeid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="chargeRange in chargeRanges" :key="chargeRange.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ChargeRangeView', params: { chargeRangeId: chargeRange.id } }">{{ chargeRange.id }}</router-link>
            </td>
            <td>{{ chargeRange.billerid }}</td>
            <td>{{ chargeRange.processingcode }}</td>
            <td>{{ chargeRange.max }}</td>
            <td>{{ chargeRange.min }}</td>
            <td>{{ chargeRange.amount }}</td>
            <td>{{ chargeRange.createdby }}</td>
            <td>{{ chargeRange.approvedby }}</td>
            <td>{{ chargeRange.createdat }}</td>
            <td>{{ chargeRange.approvedon }}</td>
            <td>{{ chargeRange.approved }}</td>
            <td>{{ chargeRange.declined }}</td>
            <td>{{ chargeRange.declinedby }}</td>
            <td>{{ chargeRange.chargeid }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ChargeRangeView', params: { chargeRangeId: chargeRange.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ChargeRangeEdit', params: { chargeRangeId: chargeRange.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(chargeRange)"
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
          id="bbMobileBankingAdminApp.chargeRange.delete.question"
          data-cy="chargeRangeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-chargeRange-heading" v-text="t$('bbMobileBankingAdminApp.chargeRange.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-chargeRange"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeChargeRange()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./charge-range.component.ts"></script>
