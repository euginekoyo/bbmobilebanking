<template>
  <div>
    <h2 id="page-heading" data-cy="SPSIncomingTransactionsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.home.title')" id="sps-incoming-transactions-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SPSIncomingTransactionsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sps-incoming-transactions"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sPSIncomingTransactions && sPSIncomingTransactions.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sPSIncomingTransactions && sPSIncomingTransactions.length > 0">
      <table class="table table-striped" aria-describedby="sPSIncomingTransactions">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messageid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.channelcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.callbackurl')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transcurrency')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsbankcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.debtorsphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiarybankcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.beneficiaryphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.narration')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.externalreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.messageendtoendid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transactionstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.transactionstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.cbsstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.requestInstanttime')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.isomessagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.requestjson')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsrequestxml')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.spsresponsexml')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.amount')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sPSIncomingTransactions in sPSIncomingTransactions" :key="sPSIncomingTransactions.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'SPSIncomingTransactionsView', params: { sPSIncomingTransactionsId: sPSIncomingTransactions.id } }"
                >{{ sPSIncomingTransactions.id }}</router-link
              >
            </td>
            <td>{{ sPSIncomingTransactions.messageid }}</td>
            <td>{{ sPSIncomingTransactions.channelcode }}</td>
            <td>{{ sPSIncomingTransactions.callbackurl }}</td>
            <td>{{ sPSIncomingTransactions.messagetype }}</td>
            <td>{{ sPSIncomingTransactions.transcurrency }}</td>
            <td>{{ sPSIncomingTransactions.debtorsname }}</td>
            <td>{{ sPSIncomingTransactions.debtorsaccountid }}</td>
            <td>{{ sPSIncomingTransactions.debtorsbankcode }}</td>
            <td>{{ sPSIncomingTransactions.debtorsphone }}</td>
            <td>{{ sPSIncomingTransactions.beneficiaryname }}</td>
            <td>{{ sPSIncomingTransactions.beneficiaryaccountid }}</td>
            <td>{{ sPSIncomingTransactions.beneficiarybankcode }}</td>
            <td>{{ sPSIncomingTransactions.beneficiaryphone }}</td>
            <td>{{ sPSIncomingTransactions.narration }}</td>
            <td>{{ sPSIncomingTransactions.externalreference }}</td>
            <td>{{ sPSIncomingTransactions.cbsreference }}</td>
            <td>{{ sPSIncomingTransactions.messageendtoendid }}</td>
            <td>{{ sPSIncomingTransactions.transactionstatus }}</td>
            <td>{{ sPSIncomingTransactions.transactionstatusdesc }}</td>
            <td>{{ sPSIncomingTransactions.spsstatus }}</td>
            <td>{{ sPSIncomingTransactions.spsstatusdesc }}</td>
            <td>{{ sPSIncomingTransactions.cbsstatus }}</td>
            <td>{{ sPSIncomingTransactions.cbsstatusdesc }}</td>
            <td>{{ formatDateShort(sPSIncomingTransactions.requestInstanttime) || '' }}</td>
            <td>{{ sPSIncomingTransactions.isomessagetype }}</td>
            <td>{{ sPSIncomingTransactions.requestjson }}</td>
            <td>{{ sPSIncomingTransactions.spsrequestxml }}</td>
            <td>{{ sPSIncomingTransactions.spsresponsexml }}</td>
            <td>{{ sPSIncomingTransactions.amount }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SPSIncomingTransactionsView', params: { sPSIncomingTransactionsId: sPSIncomingTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SPSIncomingTransactionsEdit', params: { sPSIncomingTransactionsId: sPSIncomingTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(sPSIncomingTransactions)"
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
          id="bbMobileBankingAdminApp.sPSIncomingTransactions.delete.question"
          data-cy="sPSIncomingTransactionsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-sPSIncomingTransactions-heading"
          v-text="t$('bbMobileBankingAdminApp.sPSIncomingTransactions.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sPSIncomingTransactions"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeSPSIncomingTransactions()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sps-incoming-transactions.component.ts"></script>
