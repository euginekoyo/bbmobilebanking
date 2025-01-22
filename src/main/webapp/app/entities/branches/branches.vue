<template>
  <div>
    <h2 id="page-heading" data-cy="BranchesHeading">
      <span v-text="t$('bbMobileBankingAdminApp.branches.home.title')" id="branches-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.branches.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'BranchesCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-branches"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.branches.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && branches && branches.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.branches.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="branches && branches.length > 0">
      <table class="table table-striped" aria-describedby="branches">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.branchname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.branchcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.email')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.address')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.phone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.location')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.contactperson')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.remarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.createdon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.approvedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.checkerremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.deletedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.deletedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.deleteremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.deleted')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.declined')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.declineddon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.declinedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.sessionid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.reworked')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.reworkedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.reworkedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.district')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.region')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.regionname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.branches.reporting')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="branches in branches" :key="branches.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BranchesView', params: { branchesId: branches.id } }">{{ branches.id }}</router-link>
            </td>
            <td>{{ branches.branchname }}</td>
            <td>{{ branches.branchcode }}</td>
            <td>{{ branches.approved }}</td>
            <td>{{ branches.email }}</td>
            <td>{{ branches.address }}</td>
            <td>{{ branches.phone }}</td>
            <td>{{ branches.location }}</td>
            <td>{{ branches.contactperson }}</td>
            <td>{{ branches.remarks }}</td>
            <td>{{ branches.createdby }}</td>
            <td>{{ formatDateShort(branches.createdon) || '' }}</td>
            <td>{{ branches.approvedby }}</td>
            <td>{{ branches.approvedon }}</td>
            <td>{{ branches.checkerremarks }}</td>
            <td>{{ branches.deletedby }}</td>
            <td>{{ formatDateShort(branches.deletedon) || '' }}</td>
            <td>{{ branches.deleteremarks }}</td>
            <td>{{ branches.deleted }}</td>
            <td>{{ branches.declined }}</td>
            <td>{{ branches.declineddon }}</td>
            <td>{{ branches.declinedby }}</td>
            <td>{{ branches.sessionid }}</td>
            <td>{{ branches.reworked }}</td>
            <td>{{ branches.reworkedby }}</td>
            <td>{{ formatDateShort(branches.reworkedon) || '' }}</td>
            <td>{{ branches.district }}</td>
            <td>{{ branches.region }}</td>
            <td>{{ branches.regionname }}</td>
            <td>{{ branches.reporting }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BranchesView', params: { branchesId: branches.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BranchesEdit', params: { branchesId: branches.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(branches)"
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
          id="bbMobileBankingAdminApp.branches.delete.question"
          data-cy="branchesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-branches-heading" v-text="t$('bbMobileBankingAdminApp.branches.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-branches"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeBranches()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./branches.component.ts"></script>
