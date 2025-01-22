<template>
  <div>
    <h2 id="page-heading" data-cy="CustomerAccountHeading">
      <span v-text="t$('bbMobileBankingAdminApp.customerAccount.home.title')" id="customer-account-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.customerAccount.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CustomerAccountCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-customer-account"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.customerAccount.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && customerAccounts && customerAccounts.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.customerAccount.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="customerAccounts && customerAccounts.length > 0">
      <table class="table table-striped" aria-describedby="customerAccounts">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.customerid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.accountnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.accountclass')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.customernumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.cif')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.timelinked')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.blocked')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.stopped')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customerAccount.dormant')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="customerAccount in customerAccounts" :key="customerAccount.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CustomerAccountView', params: { customerAccountId: customerAccount.id } }">{{
                customerAccount.id
              }}</router-link>
            </td>
            <td>{{ customerAccount.customerid }}</td>
            <td>{{ customerAccount.accountnumber }}</td>
            <td>{{ customerAccount.accountclass }}</td>
            <td>{{ customerAccount.customernumber }}</td>
            <td>{{ customerAccount.cif }}</td>
            <td>{{ formatDateShort(customerAccount.timelinked) || '' }}</td>
            <td>{{ customerAccount.blocked }}</td>
            <td>{{ customerAccount.stopped }}</td>
            <td>{{ customerAccount.dormant }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CustomerAccountView', params: { customerAccountId: customerAccount.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CustomerAccountEdit', params: { customerAccountId: customerAccount.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(customerAccount)"
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
          id="bbMobileBankingAdminApp.customerAccount.delete.question"
          data-cy="customerAccountDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-customerAccount-heading"
          v-text="t$('bbMobileBankingAdminApp.customerAccount.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-customerAccount"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeCustomerAccount()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./customer-account.component.ts"></script>
