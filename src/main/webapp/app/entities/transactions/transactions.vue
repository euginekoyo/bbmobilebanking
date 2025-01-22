<template>
  <div>
    <h2 id="page-heading" data-cy="TransactionsHeading">
      <span v-text="t$('bbMobileBankingAdminApp.transactions.home.title')" id="transactions-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.transactions.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TransactionsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-transactions"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.transactions.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && transactions && transactions.length === 0">
      <span v-text="t$('bbMobileBankingAdminApp.transactions.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="transactions && transactions.length > 0">
      <table class="table table-striped" aria-describedby="transactions">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.processed')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmap')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmap')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.inmessage')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.messagetocbs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.messagefromcbs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.cbsprocess')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.cbsonline')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.cbsresponse')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.responsemessage')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.responsesent')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.field39resp')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.narration')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.authorised')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.branchcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.field39original')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.messageclass')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.txncode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.currcode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.device')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.branch2')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.longerbranch')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.datex')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.timex')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.posted')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.attempts')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata2')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.commission')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.responsecreated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.online')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.originaldata3')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.toswitch')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.fromswitch')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.tocbs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.fromcbs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.postinglegs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.commissiontxncode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.hostref')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.requestcreated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.requestmessage')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmapflex')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmapflex')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.requestsent')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.minicbs')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.reversed')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.offlinesenttohost')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.offlineresponse')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.sourceLongerface')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.mtirrn')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.hostresponsecode')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.field48')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.transactions.source')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="transactions in transactions" :key="transactions.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TransactionsView', params: { transactionsId: transactions.id } }">{{
                transactions.id
              }}</router-link>
            </td>
            <td>{{ transactions.processed }}</td>
            <td>{{ transactions.incomingbitmap }}</td>
            <td>{{ transactions.outgoingbitmap }}</td>
            <td>{{ transactions.inmessage }}</td>
            <td>{{ transactions.messagetocbs }}</td>
            <td>{{ transactions.messagefromcbs }}</td>
            <td>{{ transactions.cbsprocess }}</td>
            <td>{{ transactions.cbsonline }}</td>
            <td>{{ transactions.cbsresponse }}</td>
            <td>{{ transactions.responsemessage }}</td>
            <td>{{ transactions.responsesent }}</td>
            <td>{{ transactions.channel }}</td>
            <td>{{ transactions.originaldata }}</td>
            <td>{{ transactions.field39resp }}</td>
            <td>{{ transactions.narration }}</td>
            <td>{{ transactions.authorised }}</td>
            <td>{{ transactions.branchcode }}</td>
            <td>{{ transactions.field39original }}</td>
            <td>{{ transactions.messageclass }}</td>
            <td>{{ transactions.txncode }}</td>
            <td>{{ transactions.currcode }}</td>
            <td>{{ transactions.device }}</td>
            <td>{{ transactions.branch2 }}</td>
            <td>{{ transactions.longerbranch }}</td>
            <td>{{ formatDateShort(transactions.datex) || '' }}</td>
            <td>{{ transactions.timex }}</td>
            <td>{{ transactions.posted }}</td>
            <td>{{ transactions.attempts }}</td>
            <td>{{ transactions.originaldata2 }}</td>
            <td>{{ transactions.commission }}</td>
            <td>{{ transactions.responsecreated }}</td>
            <td>{{ transactions.online }}</td>
            <td>{{ transactions.originaldata3 }}</td>
            <td>{{ transactions.toswitch }}</td>
            <td>{{ transactions.fromswitch }}</td>
            <td>{{ transactions.tocbs }}</td>
            <td>{{ transactions.fromcbs }}</td>
            <td>{{ transactions.postinglegs }}</td>
            <td>{{ transactions.commissiontxncode }}</td>
            <td>{{ transactions.hostref }}</td>
            <td>{{ transactions.requestcreated }}</td>
            <td>{{ transactions.requestmessage }}</td>
            <td>{{ transactions.outgoingbitmapflex }}</td>
            <td>{{ transactions.incomingbitmapflex }}</td>
            <td>{{ transactions.requestsent }}</td>
            <td>{{ transactions.minicbs }}</td>
            <td>{{ transactions.reversed }}</td>
            <td>{{ transactions.offlinesenttohost }}</td>
            <td>{{ transactions.offlineresponse }}</td>
            <td>{{ transactions.sourceLongerface }}</td>
            <td>{{ transactions.mtirrn }}</td>
            <td>{{ transactions.hostresponsecode }}</td>
            <td>{{ transactions.field48 }}</td>
            <td>{{ transactions.source }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TransactionsView', params: { transactionsId: transactions.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TransactionsEdit', params: { transactionsId: transactions.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  @click="prepareRemove(transactions)"
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
          id="bbMobileBankingAdminApp.transactions.delete.question"
          data-cy="transactionsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-transactions-heading" v-text="t$('bbMobileBankingAdminApp.transactions.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-transactions"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            @click="removeTransactions()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./transactions.component.ts"></script>
