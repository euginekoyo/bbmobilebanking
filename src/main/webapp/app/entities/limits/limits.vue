<template>
  <div>
    <h2 id="page-heading" data-cy="LimitsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.limits.home.title')" id="limits-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.limits.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'LimitsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-limits"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.limits.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && limits && limits.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.limits.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="limits && limits.length > 0">
      <table class="table table-striped" aria-describedby="limits">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.transactiontype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.procode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.transactionlimit')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.dailylimit')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.registeredby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.registereddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.approveddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.updatedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.updateddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.rework')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.reworkby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.limits.sessionid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="limits in limits" :key="limits.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LimitsView', params: { limitsId: limits.id } }">{{ limits.id }}</router-link>
            </td>
            <td>{{ limits.transactiontype }}</td>
            <td>{{ limits.procode }}</td>
            <td>{{ limits.channel }}</td>
            <td>{{ limits.transactionlimit }}</td>
            <td>{{ limits.dailylimit }}</td>
            <td>{{ limits.registeredby }}</td>
            <td>{{ limits.registereddate }}</td>
            <td>{{ limits.approved }}</td>
            <td>{{ limits.approvedby }}</td>
            <td>{{ limits.approveddate }}</td>
            <td>{{ limits.updatedby }}</td>
            <td>{{ limits.updateddate }}</td>
            <td>{{ limits.rework }}</td>
            <td>{{ limits.reworkby }}</td>
            <td>{{ limits.sessionid }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LimitsView', params: { limitsId: limits.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LimitsEdit', params: { limitsId: limits.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(limits)"
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
          id="bbMobileBankingAdminApp.limits.delete.question"
          data-cy="limitsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-limits-heading" v-text="t$('bbMobileBankingAdminApp.limits.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-limits"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeLimits()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./limits.component.ts"></script>
