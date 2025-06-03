<template>
  <div>
    <h2 id="page-heading" data-cy="SPSOutgoingTransactionsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.home.title')" id="sps-outgoing-transactions-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.home.refreshListLabel')"></span>
        </button>
        <!-- <router-link :to="{ name: 'SPSOutgoingTransactionsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sps-outgoing-transactions"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.home.createLabel')"></span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sPSOutgoingTransactions && sPSOutgoingTransactions.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sPSOutgoingTransactions && sPSOutgoingTransactions.length > 0">
      <table class="table table-striped" aria-describedby="sPSOutgoingTransactions">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.channelcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackurl')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transcurrency')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsbankcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiarybankcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.narration')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.externalreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageendtoendid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.requestInstanttime')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.isomessagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.amount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatusdesc')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sPSOutgoingTransactions in sPSOutgoingTransactions" :key="sPSOutgoingTransactions.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'SPSOutgoingTransactionsView', params: { sPSOutgoingTransactionsId: sPSOutgoingTransactions.id } }"
                >{{ sPSOutgoingTransactions.id }}</router-link
              >
            </td>
            <td>{{ sPSOutgoingTransactions.messageid }}</td>
            <td>{{ sPSOutgoingTransactions.channelcode }}</td>
            <td>{{ sPSOutgoingTransactions.callbackurl }}</td>
            <td>{{ sPSOutgoingTransactions.messagetype }}</td>
            <td>{{ sPSOutgoingTransactions.transcurrency }}</td>
            <td>{{ sPSOutgoingTransactions.debtorsname }}</td>
            <td>{{ sPSOutgoingTransactions.debtorsaccountid }}</td>
            <td>{{ sPSOutgoingTransactions.debtorsbankcode }}</td>
            <td>{{ sPSOutgoingTransactions.debtorsphone }}</td>
            <td>{{ sPSOutgoingTransactions.beneficiaryname }}</td>
            <td>{{ sPSOutgoingTransactions.beneficiaryaccountid }}</td>
            <td>{{ sPSOutgoingTransactions.beneficiarybankcode }}</td>
            <td>{{ sPSOutgoingTransactions.beneficiaryphone }}</td>
            <td>{{ sPSOutgoingTransactions.narration }}</td>
            <td>{{ sPSOutgoingTransactions.externalreference }}</td>
            <td>{{ sPSOutgoingTransactions.cbsreference }}</td>
            <td>{{ sPSOutgoingTransactions.messageendtoendid }}</td>
            <td>{{ sPSOutgoingTransactions.transactionstatus }}</td>
            <td>{{ sPSOutgoingTransactions.transactionstatusdesc }}</td>
            <td>{{ sPSOutgoingTransactions.spsstatus }}</td>
            <td>{{ sPSOutgoingTransactions.spsstatusdesc }}</td>
            <td>{{ sPSOutgoingTransactions.cbsstatus }}</td>
            <td>{{ sPSOutgoingTransactions.cbsstatusdesc }}</td>
            <td>{{ formatDateShort(sPSOutgoingTransactions.requestInstanttime) || '' }}</td>
            <td>{{ sPSOutgoingTransactions.isomessagetype }}</td>
            <td>{{ sPSOutgoingTransactions.amount }}</td>
            <td>{{ sPSOutgoingTransactions.callbackstatus }}</td>
            <td>{{ sPSOutgoingTransactions.callbackstatusdesc }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SPSOutgoingTransactionsView', params: { sPSOutgoingTransactionsId: sPSOutgoingTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <!-- <router-link
                  :to="{ name: 'SPSOutgoingTransactionsEdit', params: { sPSOutgoingTransactionsId: sPSOutgoingTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link> -->
                <!-- <b-button
                  @click="prepareRemove(sPSOutgoingTransactions)"
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
          id="bbMobileBankingAdminApp.sPSOutgoingTransactions.delete.question"
          data-cy="sPSOutgoingTransactionsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-sPSOutgoingTransactions-heading"
          v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sPSOutgoingTransactions"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeSPSOutgoingTransactions()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sps-outgoing-transactions.component.ts"></script>
