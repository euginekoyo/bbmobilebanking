<template>
  <div>
    <h2 id="page-heading" data-cy="CustomerHeading">
      <span v-text="t$('bbMobileBankingAdminApp.customer.home.title')" id="customer-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" @click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('bbMobileBankingAdminApp.customer.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CustomerCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-customer"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('bbMobileBankingAdminApp.customer.home.createLabel')"></span>
          </button>
        </router-link>
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
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.cardnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.lang')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.firstlogin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.active')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.registered')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.cstdelete')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.regdate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.alertenabled')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.remark')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.imsi')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.partiallyregistered')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.partialdate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.registerdate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approved')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approvedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approveddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.declined')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.declinedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.declineddate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.checkerremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.postaladdress')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.residence')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.dob')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.createdby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.identificationid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.addaccount')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.aclinkinginstitution')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deactivated')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deactivatedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deactivatedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.phonenochanged')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.originalphoneno')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.newphoneno')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.reset')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.resetinginstitution')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinresetremark')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.resetby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.reseton')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.unblockinginstitution')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinblock')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinblockby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinblockremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.blockinginstitution')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinblockon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approvedon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinunblockby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.loggedin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.trials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.idtype')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.idnumber')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.gender')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.cif')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.dateofbirth')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.remarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.resetimsi')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.imsiresetby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.firstname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.secondname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.lastname')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinblocktime')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.customerstatus')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.username')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.password')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deviceid')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.channel')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passreset')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passresetby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passreseton')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passblock')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passblockby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passblockon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.pinmarkblock')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passmarkblock')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passresetremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passblockremarks')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passunblockby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.passtrials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.appactive')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.appmarkeddisable')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.disableby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approvedisableby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.appmarkedenable')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.enableby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approvedenableby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.markeddeactivate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.appfirstlogin')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.atmtrials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.shorcuts')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.markedactivate')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.town')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.approveddisableon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.disabledon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.resetapproveon')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.deletedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.questionsasked')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.questionstrials')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.questionsanswered')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.validotp')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.activatedby')"></span></th>
            <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.activatedon')"></span></th>
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
            <td>{{ customer.cardnumber }}</td>
            <td>{{ customer.accountnumber }}</td>
            <td>{{ customer.lang }}</td>
            <td>{{ customer.pin }}</td>
            <td>{{ customer.firstlogin }}</td>
            <td>{{ customer.active }}</td>
            <td>{{ customer.registered }}</td>
            <td>{{ customer.cstdelete }}</td>
            <td>{{ formatDateShort(customer.regdate) || '' }}</td>
            <td>{{ customer.alertenabled }}</td>
            <td>{{ customer.remark }}</td>
            <td>{{ customer.imsi }}</td>
            <td>{{ customer.partiallyregistered }}</td>
            <td>{{ formatDateShort(customer.partialdate) || '' }}</td>
            <td>{{ formatDateShort(customer.registerdate) || '' }}</td>
            <td>{{ customer.approved }}</td>
            <td>{{ customer.approvedby }}</td>
            <td>{{ formatDateShort(customer.approveddate) || '' }}</td>
            <td>{{ customer.declined }}</td>
            <td>{{ customer.declinedby }}</td>
            <td>{{ formatDateShort(customer.declineddate) || '' }}</td>
            <td>{{ customer.checkerremarks }}</td>
            <td>{{ customer.postaladdress }}</td>
            <td>{{ customer.residence }}</td>
            <td>{{ formatDateShort(customer.dob) || '' }}</td>
            <td>{{ customer.createdby }}</td>
            <td>{{ customer.emailaddress }}</td>
            <td>{{ customer.identificationid }}</td>
            <td>{{ customer.addaccount }}</td>
            <td>{{ customer.aclinkinginstitution }}</td>
            <td>{{ customer.deactivated }}</td>
            <td>{{ customer.deactivatedby }}</td>
            <td>{{ formatDateShort(customer.deactivatedon) || '' }}</td>
            <td>{{ customer.phonenochanged }}</td>
            <td>{{ customer.phonenochangedby }}</td>
            <td>{{ formatDateShort(customer.phonenochangedon) || '' }}</td>
            <td>{{ customer.originalphoneno }}</td>
            <td>{{ customer.newphoneno }}</td>
            <td>{{ customer.reset }}</td>
            <td>{{ customer.resetinginstitution }}</td>
            <td>{{ customer.pinresetremark }}</td>
            <td>{{ customer.resetby }}</td>
            <td>{{ formatDateShort(customer.reseton) || '' }}</td>
            <td>{{ customer.unblockinginstitution }}</td>
            <td>{{ customer.pinblock }}</td>
            <td>{{ customer.pinblockby }}</td>
            <td>{{ customer.pinblockremarks }}</td>
            <td>{{ customer.blockinginstitution }}</td>
            <td>{{ formatDateShort(customer.pinblockon) || '' }}</td>
            <td>{{ formatDateShort(customer.approvedon) || '' }}</td>
            <td>{{ customer.pinunblockby }}</td>
            <td>{{ customer.loggedin }}</td>
            <td>{{ customer.trials }}</td>
            <td>{{ customer.idtype }}</td>
            <td>{{ customer.idnumber }}</td>
            <td>{{ customer.gender }}</td>
            <td>{{ customer.cif }}</td>
            <td>{{ formatDateShort(customer.dateofbirth) || '' }}</td>
            <td>{{ customer.remarks }}</td>
            <td>{{ customer.resetimsi }}</td>
            <td>{{ customer.imsiresetby }}</td>
            <td>{{ customer.firstname }}</td>
            <td>{{ customer.secondname }}</td>
            <td>{{ customer.lastname }}</td>
            <td>{{ customer.pinblocktime }}</td>
            <td>{{ customer.customerstatus }}</td>
            <td>{{ customer.username }}</td>
            <td>{{ customer.password }}</td>
            <td>{{ customer.deviceid }}</td>
            <td>{{ customer.channel }}</td>
            <td>{{ customer.passreset }}</td>
            <td>{{ customer.passresetby }}</td>
            <td>{{ formatDateShort(customer.passreseton) || '' }}</td>
            <td>{{ customer.passblock }}</td>
            <td>{{ customer.passblockby }}</td>
            <td>{{ formatDateShort(customer.passblockon) || '' }}</td>
            <td>{{ customer.pinmarkblock }}</td>
            <td>{{ customer.passmarkblock }}</td>
            <td>{{ customer.passresetremarks }}</td>
            <td>{{ customer.passblockremarks }}</td>
            <td>{{ customer.passunblockby }}</td>
            <td>{{ customer.passtrials }}</td>
            <td>{{ customer.appactive }}</td>
            <td>{{ customer.lastlogin }}</td>
            <td>{{ customer.appmarkeddisable }}</td>
            <td>{{ customer.disableby }}</td>
            <td>{{ customer.approvedisableby }}</td>
            <td>{{ customer.appmarkedenable }}</td>
            <td>{{ customer.enableby }}</td>
            <td>{{ customer.approvedenableby }}</td>
            <td>{{ customer.markeddeactivate }}</td>
            <td>{{ customer.appfirstlogin }}</td>
            <td>{{ customer.atmtrials }}</td>
            <td>{{ customer.shorcuts }}</td>
            <td>{{ customer.markedactivate }}</td>
            <td>{{ customer.town }}</td>
            <td>{{ formatDateShort(customer.approveddisableon) || '' }}</td>
            <td>{{ formatDateShort(customer.disabledon) || '' }}</td>
            <td>{{ formatDateShort(customer.resetapproveon) || '' }}</td>
            <td>{{ customer.deletedby }}</td>
            <td>{{ customer.questionsasked }}</td>
            <td>{{ customer.questionstrials }}</td>
            <td>{{ customer.questionsanswered }}</td>
            <td>{{ customer.validotp }}</td>
            <td>{{ customer.activatedby }}</td>
            <td>{{ formatDateShort(customer.activatedon) || '' }}</td>
            <td>{{ customer.branchcode }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CustomerEdit', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
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
