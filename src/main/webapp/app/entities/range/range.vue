<template>
  <div>
    <h2 id="page-heading" data-cy="RangeHeading">
      <span v-text="t$('bbMobileBankingAdminApp.range.home.title')" id="range-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.range.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RangeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-range"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.range.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && ranges && ranges.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.range.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="ranges && ranges.length > 0">
      <table class="table table-striped" aria-describedby="ranges">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.rangefrom')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.rangeto')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.amount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.txntype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.txncode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.chargeid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.range.channel')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="range in ranges" :key="range.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RangeView', params: { rangeId: range.id } }">{{ range.id }}</router-link>
            </td>
            <td>{{ range.rangefrom }}</td>
            <td>{{ range.rangeto }}</td>
            <td>{{ range.amount }}</td>
            <td>{{ range.txntype }}</td>
            <td>{{ range.txncode }}</td>
            <td>{{ range.chargeid }}</td>
            <td>{{ range.channel }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RangeView', params: { rangeId: range.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RangeEdit', params: { rangeId: range.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(range)"
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
          id="bbMobileBankingAdminApp.range.delete.question"
          data-cy="rangeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-range-heading" v-text="t$('bbMobileBankingAdminApp.range.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-range"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeRange()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./range.component.ts"></script>
