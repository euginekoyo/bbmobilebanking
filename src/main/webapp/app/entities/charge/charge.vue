<template>
  <div>
    <h2 id="page-heading" data-cy="ChargeHeading">
      <span v-text="t$('bbMobileBankingAdminApp.charge.home.title')" id="charge-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.charge.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ChargeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-charge"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.charge.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && charges && charges.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.charge.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="charges && charges.length > 0">
      <table class="table table-striped" aria-describedby="charges">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.txntype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.feemode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.amount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.datecreated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.txncode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.description')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.approveddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.chargeRange')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.charge.range')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="charge in charges" :key="charge.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ChargeView', params: { chargeId: charge.id } }">{{ charge.id }}</router-link>
            </td>
            <td>{{ charge.txntype }}</td>
            <td>{{ charge.feemode }}</td>
            <td>{{ charge.amount }}</td>
            <td>{{ formatDateShort(charge.datecreated) || '' }}</td>
            <td>{{ charge.createdby }}</td>
            <td>{{ charge.approved }}</td>
            <td>{{ charge.approvedby }}</td>
            <td>{{ charge.channel }}</td>
            <td>{{ charge.txncode }}</td>
            <td>{{ charge.description }}</td>
            <td>{{ formatDateShort(charge.approveddate) || '' }}</td>
            <td>
              <div v-if="charge.chargeRange">
                <router-link :to="{ name: 'ChargeRangeView', params: { chargeRangeId: charge.chargeRange.id } }">{{
                  charge.chargeRange.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="charge.range">
                <router-link :to="{ name: 'RangeView', params: { rangeId: charge.range.id } }">{{ charge.range.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ChargeView', params: { chargeId: charge.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ChargeEdit', params: { chargeId: charge.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(charge)"
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
          id="bbMobileBankingAdminApp.charge.delete.question"
          data-cy="chargeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-charge-heading" v-text="t$('bbMobileBankingAdminApp.charge.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-charge"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeCharge()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./charge.component.ts"></script>
