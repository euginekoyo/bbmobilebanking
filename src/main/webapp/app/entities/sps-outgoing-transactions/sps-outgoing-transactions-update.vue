<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.sPSOutgoingTransactions.home.createOrEditLabel"
          data-cy="SPSOutgoingTransactionsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="sPSOutgoingTransactions.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="sPSOutgoingTransactions.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageid')"
              for="sps-outgoing-transactions-messageid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messageid"
              id="sps-outgoing-transactions-messageid"
              data-cy="messageid"
              :class="{ valid: !v$.messageid.$invalid, invalid: v$.messageid.$invalid }"
              v-model="v$.messageid.$model"
            />
            <div v-if="v$.messageid.$anyDirty && v$.messageid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messageid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.channelcode')"
              for="sps-outgoing-transactions-channelcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channelcode"
              id="sps-outgoing-transactions-channelcode"
              data-cy="channelcode"
              :class="{ valid: !v$.channelcode.$invalid, invalid: v$.channelcode.$invalid }"
              v-model="v$.channelcode.$model"
            />
            <div v-if="v$.channelcode.$anyDirty && v$.channelcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channelcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackurl')"
              for="sps-outgoing-transactions-callbackurl"
            ></label>
            <input
              type="text"
              class="form-control"
              name="callbackurl"
              id="sps-outgoing-transactions-callbackurl"
              data-cy="callbackurl"
              :class="{ valid: !v$.callbackurl.$invalid, invalid: v$.callbackurl.$invalid }"
              v-model="v$.callbackurl.$model"
            />
            <div v-if="v$.callbackurl.$anyDirty && v$.callbackurl.$invalid">
              <small class="form-text text-danger" v-for="error of v$.callbackurl.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messagetype')"
              for="sps-outgoing-transactions-messagetype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagetype"
              id="sps-outgoing-transactions-messagetype"
              data-cy="messagetype"
              :class="{ valid: !v$.messagetype.$invalid, invalid: v$.messagetype.$invalid }"
              v-model="v$.messagetype.$model"
            />
            <div v-if="v$.messagetype.$anyDirty && v$.messagetype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messagetype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transcurrency')"
              for="sps-outgoing-transactions-transcurrency"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transcurrency"
              id="sps-outgoing-transactions-transcurrency"
              data-cy="transcurrency"
              :class="{ valid: !v$.transcurrency.$invalid, invalid: v$.transcurrency.$invalid }"
              v-model="v$.transcurrency.$model"
            />
            <div v-if="v$.transcurrency.$anyDirty && v$.transcurrency.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transcurrency.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsname')"
              for="sps-outgoing-transactions-debtorsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsname"
              id="sps-outgoing-transactions-debtorsname"
              data-cy="debtorsname"
              :class="{ valid: !v$.debtorsname.$invalid, invalid: v$.debtorsname.$invalid }"
              v-model="v$.debtorsname.$model"
            />
            <div v-if="v$.debtorsname.$anyDirty && v$.debtorsname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.debtorsname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsaccountid')"
              for="sps-outgoing-transactions-debtorsaccountid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsaccountid"
              id="sps-outgoing-transactions-debtorsaccountid"
              data-cy="debtorsaccountid"
              :class="{ valid: !v$.debtorsaccountid.$invalid, invalid: v$.debtorsaccountid.$invalid }"
              v-model="v$.debtorsaccountid.$model"
            />
            <div v-if="v$.debtorsaccountid.$anyDirty && v$.debtorsaccountid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.debtorsaccountid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsbankcode')"
              for="sps-outgoing-transactions-debtorsbankcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsbankcode"
              id="sps-outgoing-transactions-debtorsbankcode"
              data-cy="debtorsbankcode"
              :class="{ valid: !v$.debtorsbankcode.$invalid, invalid: v$.debtorsbankcode.$invalid }"
              v-model="v$.debtorsbankcode.$model"
            />
            <div v-if="v$.debtorsbankcode.$anyDirty && v$.debtorsbankcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.debtorsbankcode.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.debtorsphone')"
              for="sps-outgoing-transactions-debtorsphone"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsphone"
              id="sps-outgoing-transactions-debtorsphone"
              data-cy="debtorsphone"
              :class="{ valid: !v$.debtorsphone.$invalid, invalid: v$.debtorsphone.$invalid }"
              v-model="v$.debtorsphone.$model"
            />
            <div v-if="v$.debtorsphone.$anyDirty && v$.debtorsphone.$invalid">
              <small class="form-text text-danger" v-for="error of v$.debtorsphone.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryname')"
              for="sps-outgoing-transactions-beneficiaryname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="beneficiaryname"
              id="sps-outgoing-transactions-beneficiaryname"
              data-cy="beneficiaryname"
              :class="{ valid: !v$.beneficiaryname.$invalid, invalid: v$.beneficiaryname.$invalid }"
              v-model="v$.beneficiaryname.$model"
            />
            <div v-if="v$.beneficiaryname.$anyDirty && v$.beneficiaryname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.beneficiaryname.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryaccountid')"
              for="sps-outgoing-transactions-beneficiaryaccountid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="beneficiaryaccountid"
              id="sps-outgoing-transactions-beneficiaryaccountid"
              data-cy="beneficiaryaccountid"
              :class="{ valid: !v$.beneficiaryaccountid.$invalid, invalid: v$.beneficiaryaccountid.$invalid }"
              v-model="v$.beneficiaryaccountid.$model"
            />
            <div v-if="v$.beneficiaryaccountid.$anyDirty && v$.beneficiaryaccountid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.beneficiaryaccountid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiarybankcode')"
              for="sps-outgoing-transactions-beneficiarybankcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="beneficiarybankcode"
              id="sps-outgoing-transactions-beneficiarybankcode"
              data-cy="beneficiarybankcode"
              :class="{ valid: !v$.beneficiarybankcode.$invalid, invalid: v$.beneficiarybankcode.$invalid }"
              v-model="v$.beneficiarybankcode.$model"
            />
            <div v-if="v$.beneficiarybankcode.$anyDirty && v$.beneficiarybankcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.beneficiarybankcode.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.beneficiaryphone')"
              for="sps-outgoing-transactions-beneficiaryphone"
            ></label>
            <input
              type="text"
              class="form-control"
              name="beneficiaryphone"
              id="sps-outgoing-transactions-beneficiaryphone"
              data-cy="beneficiaryphone"
              :class="{ valid: !v$.beneficiaryphone.$invalid, invalid: v$.beneficiaryphone.$invalid }"
              v-model="v$.beneficiaryphone.$model"
            />
            <div v-if="v$.beneficiaryphone.$anyDirty && v$.beneficiaryphone.$invalid">
              <small class="form-text text-danger" v-for="error of v$.beneficiaryphone.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.narration')"
              for="sps-outgoing-transactions-narration"
            ></label>
            <input
              type="text"
              class="form-control"
              name="narration"
              id="sps-outgoing-transactions-narration"
              data-cy="narration"
              :class="{ valid: !v$.narration.$invalid, invalid: v$.narration.$invalid }"
              v-model="v$.narration.$model"
            />
            <div v-if="v$.narration.$anyDirty && v$.narration.$invalid">
              <small class="form-text text-danger" v-for="error of v$.narration.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.externalreference')"
              for="sps-outgoing-transactions-externalreference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalreference"
              id="sps-outgoing-transactions-externalreference"
              data-cy="externalreference"
              :class="{ valid: !v$.externalreference.$invalid, invalid: v$.externalreference.$invalid }"
              v-model="v$.externalreference.$model"
            />
            <div v-if="v$.externalreference.$anyDirty && v$.externalreference.$invalid">
              <small class="form-text text-danger" v-for="error of v$.externalreference.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsreference')"
              for="sps-outgoing-transactions-cbsreference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsreference"
              id="sps-outgoing-transactions-cbsreference"
              data-cy="cbsreference"
              :class="{ valid: !v$.cbsreference.$invalid, invalid: v$.cbsreference.$invalid }"
              v-model="v$.cbsreference.$model"
            />
            <div v-if="v$.cbsreference.$anyDirty && v$.cbsreference.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cbsreference.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.messageendtoendid')"
              for="sps-outgoing-transactions-messageendtoendid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messageendtoendid"
              id="sps-outgoing-transactions-messageendtoendid"
              data-cy="messageendtoendid"
              :class="{ valid: !v$.messageendtoendid.$invalid, invalid: v$.messageendtoendid.$invalid }"
              v-model="v$.messageendtoendid.$model"
            />
            <div v-if="v$.messageendtoendid.$anyDirty && v$.messageendtoendid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messageendtoendid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatus')"
              for="sps-outgoing-transactions-transactionstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionstatus"
              id="sps-outgoing-transactions-transactionstatus"
              data-cy="transactionstatus"
              :class="{ valid: !v$.transactionstatus.$invalid, invalid: v$.transactionstatus.$invalid }"
              v-model="v$.transactionstatus.$model"
            />
            <div v-if="v$.transactionstatus.$anyDirty && v$.transactionstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transactionstatus.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.transactionstatusdesc')"
              for="sps-outgoing-transactions-transactionstatusdesc"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionstatusdesc"
              id="sps-outgoing-transactions-transactionstatusdesc"
              data-cy="transactionstatusdesc"
              :class="{ valid: !v$.transactionstatusdesc.$invalid, invalid: v$.transactionstatusdesc.$invalid }"
              v-model="v$.transactionstatusdesc.$model"
            />
            <div v-if="v$.transactionstatusdesc.$anyDirty && v$.transactionstatusdesc.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transactionstatusdesc.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatus')"
              for="sps-outgoing-transactions-spsstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="spsstatus"
              id="sps-outgoing-transactions-spsstatus"
              data-cy="spsstatus"
              :class="{ valid: !v$.spsstatus.$invalid, invalid: v$.spsstatus.$invalid }"
              v-model="v$.spsstatus.$model"
            />
            <div v-if="v$.spsstatus.$anyDirty && v$.spsstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.spsstatus.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsstatusdesc')"
              for="sps-outgoing-transactions-spsstatusdesc"
            ></label>
            <input
              type="text"
              class="form-control"
              name="spsstatusdesc"
              id="sps-outgoing-transactions-spsstatusdesc"
              data-cy="spsstatusdesc"
              :class="{ valid: !v$.spsstatusdesc.$invalid, invalid: v$.spsstatusdesc.$invalid }"
              v-model="v$.spsstatusdesc.$model"
            />
            <div v-if="v$.spsstatusdesc.$anyDirty && v$.spsstatusdesc.$invalid">
              <small class="form-text text-danger" v-for="error of v$.spsstatusdesc.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatus')"
              for="sps-outgoing-transactions-cbsstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsstatus"
              id="sps-outgoing-transactions-cbsstatus"
              data-cy="cbsstatus"
              :class="{ valid: !v$.cbsstatus.$invalid, invalid: v$.cbsstatus.$invalid }"
              v-model="v$.cbsstatus.$model"
            />
            <div v-if="v$.cbsstatus.$anyDirty && v$.cbsstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cbsstatus.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.cbsstatusdesc')"
              for="sps-outgoing-transactions-cbsstatusdesc"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsstatusdesc"
              id="sps-outgoing-transactions-cbsstatusdesc"
              data-cy="cbsstatusdesc"
              :class="{ valid: !v$.cbsstatusdesc.$invalid, invalid: v$.cbsstatusdesc.$invalid }"
              v-model="v$.cbsstatusdesc.$model"
            />
            <div v-if="v$.cbsstatusdesc.$anyDirty && v$.cbsstatusdesc.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cbsstatusdesc.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.requestInstanttime')"
              for="sps-outgoing-transactions-requestInstanttime"
            ></label>
            <div class="d-flex">
              <input
                id="sps-outgoing-transactions-requestInstanttime"
                data-cy="requestInstanttime"
                type="datetime-local"
                class="form-control"
                name="requestInstanttime"
                :class="{ valid: !v$.requestInstanttime.$invalid, invalid: v$.requestInstanttime.$invalid }"
                :value="convertDateTimeFromServer(v$.requestInstanttime.$model)"
                @change="updateInstantField('requestInstanttime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.isomessagetype')"
              for="sps-outgoing-transactions-isomessagetype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="isomessagetype"
              id="sps-outgoing-transactions-isomessagetype"
              data-cy="isomessagetype"
              :class="{ valid: !v$.isomessagetype.$invalid, invalid: v$.isomessagetype.$invalid }"
              v-model="v$.isomessagetype.$model"
            />
            <div v-if="v$.isomessagetype.$anyDirty && v$.isomessagetype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.isomessagetype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.requestjson')"
              for="sps-outgoing-transactions-requestjson"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestjson"
              id="sps-outgoing-transactions-requestjson"
              data-cy="requestjson"
              :class="{ valid: !v$.requestjson.$invalid, invalid: v$.requestjson.$invalid }"
              v-model="v$.requestjson.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsrequestxml')"
              for="sps-outgoing-transactions-spsrequestxml"
            ></label>
            <input
              type="text"
              class="form-control"
              name="spsrequestxml"
              id="sps-outgoing-transactions-spsrequestxml"
              data-cy="spsrequestxml"
              :class="{ valid: !v$.spsrequestxml.$invalid, invalid: v$.spsrequestxml.$invalid }"
              v-model="v$.spsrequestxml.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.spsresponsexml')"
              for="sps-outgoing-transactions-spsresponsexml"
            ></label>
            <input
              type="text"
              class="form-control"
              name="spsresponsexml"
              id="sps-outgoing-transactions-spsresponsexml"
              data-cy="spsresponsexml"
              :class="{ valid: !v$.spsresponsexml.$invalid, invalid: v$.spsresponsexml.$invalid }"
              v-model="v$.spsresponsexml.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.amount')"
              for="sps-outgoing-transactions-amount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="sps-outgoing-transactions-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatus')"
              for="sps-outgoing-transactions-callbackstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="callbackstatus"
              id="sps-outgoing-transactions-callbackstatus"
              data-cy="callbackstatus"
              :class="{ valid: !v$.callbackstatus.$invalid, invalid: v$.callbackstatus.$invalid }"
              v-model="v$.callbackstatus.$model"
            />
            <div v-if="v$.callbackstatus.$anyDirty && v$.callbackstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.callbackstatus.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.callbackstatusdesc')"
              for="sps-outgoing-transactions-callbackstatusdesc"
            ></label>
            <input
              type="text"
              class="form-control"
              name="callbackstatusdesc"
              id="sps-outgoing-transactions-callbackstatusdesc"
              data-cy="callbackstatusdesc"
              :class="{ valid: !v$.callbackstatusdesc.$invalid, invalid: v$.callbackstatusdesc.$invalid }"
              v-model="v$.callbackstatusdesc.$model"
            />
            <div v-if="v$.callbackstatusdesc.$anyDirty && v$.callbackstatusdesc.$invalid">
              <small class="form-text text-danger" v-for="error of v$.callbackstatusdesc.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" @click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sps-outgoing-transactions-update.component.ts"></script>
