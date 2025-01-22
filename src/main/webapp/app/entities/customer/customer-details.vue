<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="customer">
        <h2 class="jh-entity-heading" data-cy="customerDetailsHeading">
          <span v-text="t$('bbMobileBankingAdminApp.customer.detail.title')"></span> {{ customer.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.customername')"></span>
          </dt>
          <dd>
            <span>{{ customer.customername }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.phonenumber')"></span>
          </dt>
          <dd>
            <span>{{ customer.phonenumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.cardnumber')"></span>
          </dt>
          <dd>
            <span>{{ customer.cardnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"></span>
          </dt>
          <dd>
            <span>{{ customer.accountnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.lang')"></span>
          </dt>
          <dd>
            <span>{{ customer.lang }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pin')"></span>
          </dt>
          <dd>
            <span>{{ customer.pin }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.firstlogin')"></span>
          </dt>
          <dd>
            <span>{{ customer.firstlogin }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.active')"></span>
          </dt>
          <dd>
            <span>{{ customer.active }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.registered')"></span>
          </dt>
          <dd>
            <span>{{ customer.registered }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.cstdelete')"></span>
          </dt>
          <dd>
            <span>{{ customer.cstdelete }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.regdate')"></span>
          </dt>
          <dd>
            <span v-if="customer.regdate">{{ formatDateLong(customer.regdate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.alertenabled')"></span>
          </dt>
          <dd>
            <span>{{ customer.alertenabled }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.remark')"></span>
          </dt>
          <dd>
            <span>{{ customer.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.imsi')"></span>
          </dt>
          <dd>
            <span>{{ customer.imsi }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.partiallyregistered')"></span>
          </dt>
          <dd>
            <span>{{ customer.partiallyregistered }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.partialdate')"></span>
          </dt>
          <dd>
            <span v-if="customer.partialdate">{{ formatDateLong(customer.partialdate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.registerdate')"></span>
          </dt>
          <dd>
            <span v-if="customer.registerdate">{{ formatDateLong(customer.registerdate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approved')"></span>
          </dt>
          <dd>
            <span>{{ customer.approved }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approvedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.approvedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approveddate')"></span>
          </dt>
          <dd>
            <span v-if="customer.approveddate">{{ formatDateLong(customer.approveddate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.declined')"></span>
          </dt>
          <dd>
            <span>{{ customer.declined }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.declinedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.declinedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.declineddate')"></span>
          </dt>
          <dd>
            <span v-if="customer.declineddate">{{ formatDateLong(customer.declineddate) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.checkerremarks')"></span>
          </dt>
          <dd>
            <span>{{ customer.checkerremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.postaladdress')"></span>
          </dt>
          <dd>
            <span>{{ customer.postaladdress }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.residence')"></span>
          </dt>
          <dd>
            <span>{{ customer.residence }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.dob')"></span>
          </dt>
          <dd>
            <span v-if="customer.dob">{{ formatDateLong(customer.dob) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.createdby')"></span>
          </dt>
          <dd>
            <span>{{ customer.createdby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"></span>
          </dt>
          <dd>
            <span>{{ customer.emailaddress }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.identificationid')"></span>
          </dt>
          <dd>
            <span>{{ customer.identificationid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.addaccount')"></span>
          </dt>
          <dd>
            <span>{{ customer.addaccount }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.aclinkinginstitution')"></span>
          </dt>
          <dd>
            <span>{{ customer.aclinkinginstitution }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.deactivated')"></span>
          </dt>
          <dd>
            <span>{{ customer.deactivated }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.deactivatedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.deactivatedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.deactivatedon')"></span>
          </dt>
          <dd>
            <span v-if="customer.deactivatedon">{{ formatDateLong(customer.deactivatedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.phonenochanged')"></span>
          </dt>
          <dd>
            <span>{{ customer.phonenochanged }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.phonenochangedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedon')"></span>
          </dt>
          <dd>
            <span v-if="customer.phonenochangedon">{{ formatDateLong(customer.phonenochangedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.originalphoneno')"></span>
          </dt>
          <dd>
            <span>{{ customer.originalphoneno }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.newphoneno')"></span>
          </dt>
          <dd>
            <span>{{ customer.newphoneno }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.reset')"></span>
          </dt>
          <dd>
            <span>{{ customer.reset }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.resetinginstitution')"></span>
          </dt>
          <dd>
            <span>{{ customer.resetinginstitution }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinresetremark')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinresetremark }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.resetby')"></span>
          </dt>
          <dd>
            <span>{{ customer.resetby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.reseton')"></span>
          </dt>
          <dd>
            <span v-if="customer.reseton">{{ formatDateLong(customer.reseton) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.unblockinginstitution')"></span>
          </dt>
          <dd>
            <span>{{ customer.unblockinginstitution }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinblock')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinblock }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinblockby')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinblockby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinblockremarks')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinblockremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.blockinginstitution')"></span>
          </dt>
          <dd>
            <span>{{ customer.blockinginstitution }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinblockon')"></span>
          </dt>
          <dd>
            <span v-if="customer.pinblockon">{{ formatDateLong(customer.pinblockon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approvedon')"></span>
          </dt>
          <dd>
            <span v-if="customer.approvedon">{{ formatDateLong(customer.approvedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinunblockby')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinunblockby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.loggedin')"></span>
          </dt>
          <dd>
            <span>{{ customer.loggedin }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.trials')"></span>
          </dt>
          <dd>
            <span>{{ customer.trials }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.idtype')"></span>
          </dt>
          <dd>
            <span>{{ customer.idtype }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.idnumber')"></span>
          </dt>
          <dd>
            <span>{{ customer.idnumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.gender')"></span>
          </dt>
          <dd>
            <span>{{ customer.gender }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.cif')"></span>
          </dt>
          <dd>
            <span>{{ customer.cif }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.dateofbirth')"></span>
          </dt>
          <dd>
            <span v-if="customer.dateofbirth">{{ formatDateLong(customer.dateofbirth) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.remarks')"></span>
          </dt>
          <dd>
            <span>{{ customer.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.resetimsi')"></span>
          </dt>
          <dd>
            <span>{{ customer.resetimsi }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.imsiresetby')"></span>
          </dt>
          <dd>
            <span>{{ customer.imsiresetby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.firstname')"></span>
          </dt>
          <dd>
            <span>{{ customer.firstname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.secondname')"></span>
          </dt>
          <dd>
            <span>{{ customer.secondname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.lastname')"></span>
          </dt>
          <dd>
            <span>{{ customer.lastname }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinblocktime')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinblocktime }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.customerstatus')"></span>
          </dt>
          <dd>
            <span>{{ customer.customerstatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.username')"></span>
          </dt>
          <dd>
            <span>{{ customer.username }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.password')"></span>
          </dt>
          <dd>
            <span>{{ customer.password }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.deviceid')"></span>
          </dt>
          <dd>
            <span>{{ customer.deviceid }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.channel')"></span>
          </dt>
          <dd>
            <span>{{ customer.channel }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passreset')"></span>
          </dt>
          <dd>
            <span>{{ customer.passreset }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passresetby')"></span>
          </dt>
          <dd>
            <span>{{ customer.passresetby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passreseton')"></span>
          </dt>
          <dd>
            <span v-if="customer.passreseton">{{ formatDateLong(customer.passreseton) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passblock')"></span>
          </dt>
          <dd>
            <span>{{ customer.passblock }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passblockby')"></span>
          </dt>
          <dd>
            <span>{{ customer.passblockby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passblockon')"></span>
          </dt>
          <dd>
            <span v-if="customer.passblockon">{{ formatDateLong(customer.passblockon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.pinmarkblock')"></span>
          </dt>
          <dd>
            <span>{{ customer.pinmarkblock }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passmarkblock')"></span>
          </dt>
          <dd>
            <span>{{ customer.passmarkblock }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passresetremarks')"></span>
          </dt>
          <dd>
            <span>{{ customer.passresetremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passblockremarks')"></span>
          </dt>
          <dd>
            <span>{{ customer.passblockremarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passunblockby')"></span>
          </dt>
          <dd>
            <span>{{ customer.passunblockby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.passtrials')"></span>
          </dt>
          <dd>
            <span>{{ customer.passtrials }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.appactive')"></span>
          </dt>
          <dd>
            <span>{{ customer.appactive }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')"></span>
          </dt>
          <dd>
            <span>{{ customer.lastlogin }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.appmarkeddisable')"></span>
          </dt>
          <dd>
            <span>{{ customer.appmarkeddisable }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.disableby')"></span>
          </dt>
          <dd>
            <span>{{ customer.disableby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approvedisableby')"></span>
          </dt>
          <dd>
            <span>{{ customer.approvedisableby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.appmarkedenable')"></span>
          </dt>
          <dd>
            <span>{{ customer.appmarkedenable }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.enableby')"></span>
          </dt>
          <dd>
            <span>{{ customer.enableby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approvedenableby')"></span>
          </dt>
          <dd>
            <span>{{ customer.approvedenableby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.markeddeactivate')"></span>
          </dt>
          <dd>
            <span>{{ customer.markeddeactivate }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.appfirstlogin')"></span>
          </dt>
          <dd>
            <span>{{ customer.appfirstlogin }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.atmtrials')"></span>
          </dt>
          <dd>
            <span>{{ customer.atmtrials }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.shorcuts')"></span>
          </dt>
          <dd>
            <span>{{ customer.shorcuts }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.markedactivate')"></span>
          </dt>
          <dd>
            <span>{{ customer.markedactivate }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.town')"></span>
          </dt>
          <dd>
            <span>{{ customer.town }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.approveddisableon')"></span>
          </dt>
          <dd>
            <span v-if="customer.approveddisableon">{{ formatDateLong(customer.approveddisableon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.disabledon')"></span>
          </dt>
          <dd>
            <span v-if="customer.disabledon">{{ formatDateLong(customer.disabledon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.resetapproveon')"></span>
          </dt>
          <dd>
            <span v-if="customer.resetapproveon">{{ formatDateLong(customer.resetapproveon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.deletedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.deletedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.questionsasked')"></span>
          </dt>
          <dd>
            <span>{{ customer.questionsasked }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.questionstrials')"></span>
          </dt>
          <dd>
            <span>{{ customer.questionstrials }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.questionsanswered')"></span>
          </dt>
          <dd>
            <span>{{ customer.questionsanswered }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.validotp')"></span>
          </dt>
          <dd>
            <span>{{ customer.validotp }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.activatedby')"></span>
          </dt>
          <dd>
            <span>{{ customer.activatedby }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.activatedon')"></span>
          </dt>
          <dd>
            <span v-if="customer.activatedon">{{ formatDateLong(customer.activatedon) }}</span>
          </dd>
          <dt>
            <span v-text="t$('bbMobileBankingAdminApp.customer.branchcode')"></span>
          </dt>
          <dd>
            <span>{{ customer.branchcode }}</span>
          </dd>
        </dl>
        <button type="submit" @click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="customer.id" :to="{ name: 'CustomerEdit', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./customer-details.component.ts"></script>
