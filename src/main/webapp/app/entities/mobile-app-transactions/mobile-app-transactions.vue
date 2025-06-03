<template>
  <div>
    <h2 id="page-heading" data-cy="MobileAppTransactionsHeading">
      <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.home.title')" id="mobile-app-transactions-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'MobileAppTransactionsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-mobile-app-transactions"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && mobileAppTransactions && mobileAppTransactions.length === 0">
      <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="mobileAppTransactions && mobileAppTransactions.length > 0">
      <table class="table table-striped" aria-describedby="mobileAppTransactions">
        <thead>
          <tr>
            <th scope="row" @click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('channel')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channel')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channel'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('channelIp')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelIp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channelIp'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('channelReference')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelReference')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channelReference'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('channelTimestamp')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelTimestamp')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channelTimestamp'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('clientId')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.clientId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientId'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('createdAt')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.createdAt')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('debitAccount')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.debitAccount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'debitAccount'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('direction')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.direction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'direction'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('errorDescription')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.errorDescription')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'errorDescription'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('geolocation')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.geolocation')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'geolocation'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('hostCode')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.hostCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hostCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('phoneNumber')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.phoneNumber')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('responseCode')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'responseCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('responseMessage')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseMessage')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'responseMessage'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('transactionCode')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionCode')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('transactionType')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionType')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionType'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('userAgent')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgent')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userAgent'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('userAgentVersion')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgentVersion')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userAgentVersion'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('amount')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.amount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('chargeamount')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.chargeamount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'chargeamount'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('creditAccount')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.creditAccount')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'creditAccount'"></jhi-sort-indicator>
            </th>
            <th scope="row" @click="changeOrder('cbsReference')">
              <span v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.cbsReference')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cbsReference'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="mobileAppTransactions in mobileAppTransactions" :key="mobileAppTransactions.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MobileAppTransactionsView', params: { mobileAppTransactionsId: mobileAppTransactions.id } }">{{
                mobileAppTransactions.id
              }}</router-link>
            </td>
            <td>{{ mobileAppTransactions.channel }}</td>
            <td>{{ mobileAppTransactions.channelIp }}</td>
            <td>{{ mobileAppTransactions.channelReference }}</td>
            <td>{{ mobileAppTransactions.channelTimestamp }}</td>
            <td>{{ mobileAppTransactions.clientId }}</td>
            <td>{{ mobileAppTransactions.createdAt }}</td>
            <td>{{ mobileAppTransactions.debitAccount }}</td>
            <td>{{ mobileAppTransactions.direction }}</td>
            <td>{{ mobileAppTransactions.errorDescription }}</td>
            <td>{{ mobileAppTransactions.geolocation }}</td>
            <td>{{ mobileAppTransactions.hostCode }}</td>
            <td>{{ mobileAppTransactions.phoneNumber }}</td>
            <td>{{ mobileAppTransactions.responseCode }}</td>
            <td>{{ mobileAppTransactions.responseMessage }}</td>
            <td>{{ mobileAppTransactions.transactionCode }}</td>
            <td>{{ mobileAppTransactions.transactionType }}</td>
            <td>{{ mobileAppTransactions.userAgent }}</td>
            <td>{{ mobileAppTransactions.userAgentVersion }}</td>
            <td>{{ mobileAppTransactions.amount }}</td>
            <td>{{ mobileAppTransactions.chargeamount }}</td>
            <td>{{ mobileAppTransactions.creditAccount }}</td>
            <td>{{ mobileAppTransactions.cbsReference }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'MobileAppTransactionsView', params: { mobileAppTransactionsId: mobileAppTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'MobileAppTransactionsEdit', params: { mobileAppTransactionsId: mobileAppTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(mobileAppTransactions)"
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
          id="mobileBankingAdminPortalApp.mobileAppTransactions.delete.question"
          data-cy="mobileAppTransactionsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-mobileAppTransactions-heading"
          v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-mobileAppTransactions"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeMobileAppTransactions()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="mobileAppTransactions && mobileAppTransactions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./mobile-app-transactions.component.ts"></script>
