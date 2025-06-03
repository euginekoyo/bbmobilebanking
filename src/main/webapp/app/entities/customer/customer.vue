<template>
  <div>
    <h2 id="page-heading" data-cy="CustomerHeading">
      <span v-text="t$('bbMobileBankingAdminApp.customer.home.title')" id="customer-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.customer.home.refreshListLabel')"></span>
        </button>
        <!-- <router-link :to="{ name: 'CustomerCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-customer"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.customer.home.createLabel')"></span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && customers && customers.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.customer.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="customers && customers.length > 0">
      <table class="table table-striped" aria-describedby="customers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.customername')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.phonenumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.firstlogin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.active')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.registered')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deactivated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approvedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.loggedin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.trials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.idtype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.idnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.gender')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.cif')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.firstname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.secondname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.lastname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.customerstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.username')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deviceid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.branchcode')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="customer in customers" :key="customer.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }">{{ customer.id }}</router-link>
            </td>
            <td>{{ customer.customername }}</td>
            <td>{{ customer.phonenumber }}</td>
            <td>{{ customer.accountnumber }}</td>
            <td>{{ customer.firstlogin }}</td>
            <td>{{ customer.active }}</td>
            <td>{{ customer.registered }}</td>
            <td>{{ formatDateShort(customer.regdate) || '' }}</td>
            <td>{{ customer.createdby }}</td>
            <td>{{ customer.emailaddress }}</td>
            <td>{{ customer.deactivated }}</td>
            <td>{{ customer.loggedin }}</td>
            <td>{{ customer.trials }}</td>
            <td>{{ customer.idtype }}</td>
            <td>{{ customer.idnumber }}</td>
            <td>{{ customer.gender }}</td>
            <td>{{ customer.cif }}</td>
            <td>{{ customer.firstname }}</td>
            <td>{{ customer.secondname }}</td>
            <td>{{ customer.lastname }}</td>
            <td>{{ customer.customerstatus }}</td>
            <td>{{ customer.username }}</td>
            <td>{{ customer.deviceid }}</td>
            <td>{{ customer.channel }}</td>
            <td>{{ customer.lastlogin }}</td>
            <td>{{ customer.branchcode }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <!-- <router-link :to="{ name: 'CustomerEdit', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link> -->
                <b-button
                  @click="prepareRemove(customer)"
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
          id="bbMobileBankingAdminApp.customer.delete.question"
          data-cy="customerDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-customer-heading" v-text="t$('bbMobileBankingAdminApp.customer.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-customer"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeCustomer()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./customer.component.ts"></script>
