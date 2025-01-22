<template>
  <div>
    <h2 id="page-heading" data-cy="CBSTransactionsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.home.title')" id="cbs-transactions-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CBSTransactionsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-cbs-transactions"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && cBSTransactions && cBSTransactions.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="cBSTransactions && cBSTransactions.length > 0">
      <table class="table table-striped" aria-describedby="cBSTransactions">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messageid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.channelcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messagetype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.transcurrency')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsaccountid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsphone')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.narration')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.externalreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsreference')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatusdesc')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestInstanttime')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestjson')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsrequestxml')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsresponsexml')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.cBSTransactions.amount')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cBSTransactions in cBSTransactions" :key="cBSTransactions.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CBSTransactionsView', params: { cBSTransactionsId: cBSTransactions.id } }">{{
                cBSTransactions.id
              }}</router-link>
            </td>
            <td>{{ cBSTransactions.messageid }}</td>
            <td>{{ cBSTransactions.channelcode }}</td>
            <td>{{ cBSTransactions.messagetype }}</td>
            <td>{{ cBSTransactions.transcurrency }}</td>
            <td>{{ cBSTransactions.debtorsname }}</td>
            <td>{{ cBSTransactions.debtorsaccountid }}</td>
            <td>{{ cBSTransactions.debtorsphone }}</td>
            <td>{{ cBSTransactions.creditorsname }}</td>
            <td>{{ cBSTransactions.creditorsaccountid }}</td>
            <td>{{ cBSTransactions.creditorsphone }}</td>
            <td>{{ cBSTransactions.narration }}</td>
            <td>{{ cBSTransactions.externalreference }}</td>
            <td>{{ cBSTransactions.cbsreference }}</td>
            <td>{{ cBSTransactions.cbsstatus }}</td>
            <td>{{ cBSTransactions.cbsstatusdesc }}</td>
            <td>{{ formatDateShort(cBSTransactions.requestInstanttime) || '' }}</td>
            <td>{{ cBSTransactions.requestjson }}</td>
            <td>{{ cBSTransactions.cbsrequestxml }}</td>
            <td>{{ cBSTransactions.cbsresponsexml }}</td>
            <td>{{ cBSTransactions.amount }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CBSTransactionsView', params: { cBSTransactionsId: cBSTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CBSTransactionsEdit', params: { cBSTransactionsId: cBSTransactions.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(cBSTransactions)"
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
          id="bbMobileBankingAdminApp.cBSTransactions.delete.question"
          data-cy="cBSTransactionsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-cBSTransactions-heading"
          v-text="t$('bbMobileBankingAdminApp.cBSTransactions.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-cBSTransactions"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeCBSTransactions()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./cbs-transactions.component.ts"></script>
