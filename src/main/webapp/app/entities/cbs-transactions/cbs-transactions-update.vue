<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.cBSTransactions.home.createOrEditLabel"
          data-cy="CBSTransactionsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.cBSTransactions.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="cBSTransactions.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="cBSTransactions.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messageid')"
              for="cbs-transactions-messageid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messageid"
              id="cbs-transactions-messageid"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.channelcode')"
              for="cbs-transactions-channelcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channelcode"
              id="cbs-transactions-channelcode"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.messagetype')"
              for="cbs-transactions-messagetype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagetype"
              id="cbs-transactions-messagetype"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.transcurrency')"
              for="cbs-transactions-transcurrency"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transcurrency"
              id="cbs-transactions-transcurrency"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsname')"
              for="cbs-transactions-debtorsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsname"
              id="cbs-transactions-debtorsname"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsaccountid')"
              for="cbs-transactions-debtorsaccountid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsaccountid"
              id="cbs-transactions-debtorsaccountid"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.debtorsphone')"
              for="cbs-transactions-debtorsphone"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debtorsphone"
              id="cbs-transactions-debtorsphone"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsname')"
              for="cbs-transactions-creditorsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creditorsname"
              id="cbs-transactions-creditorsname"
              data-cy="creditorsname"
              :class="{ valid: !v$.creditorsname.$invalid, invalid: v$.creditorsname.$invalid }"
              v-model="v$.creditorsname.$model"
            />
            <div v-if="v$.creditorsname.$anyDirty && v$.creditorsname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.creditorsname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsaccountid')"
              for="cbs-transactions-creditorsaccountid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creditorsaccountid"
              id="cbs-transactions-creditorsaccountid"
              data-cy="creditorsaccountid"
              :class="{ valid: !v$.creditorsaccountid.$invalid, invalid: v$.creditorsaccountid.$invalid }"
              v-model="v$.creditorsaccountid.$model"
            />
            <div v-if="v$.creditorsaccountid.$anyDirty && v$.creditorsaccountid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.creditorsaccountid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.creditorsphone')"
              for="cbs-transactions-creditorsphone"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creditorsphone"
              id="cbs-transactions-creditorsphone"
              data-cy="creditorsphone"
              :class="{ valid: !v$.creditorsphone.$invalid, invalid: v$.creditorsphone.$invalid }"
              v-model="v$.creditorsphone.$model"
            />
            <div v-if="v$.creditorsphone.$anyDirty && v$.creditorsphone.$invalid">
              <small class="form-text text-danger" v-for="error of v$.creditorsphone.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.narration')"
              for="cbs-transactions-narration"
            ></label>
            <input
              type="text"
              class="form-control"
              name="narration"
              id="cbs-transactions-narration"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.externalreference')"
              for="cbs-transactions-externalreference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="externalreference"
              id="cbs-transactions-externalreference"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsreference')"
              for="cbs-transactions-cbsreference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsreference"
              id="cbs-transactions-cbsreference"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatus')"
              for="cbs-transactions-cbsstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsstatus"
              id="cbs-transactions-cbsstatus"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsstatusdesc')"
              for="cbs-transactions-cbsstatusdesc"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsstatusdesc"
              id="cbs-transactions-cbsstatusdesc"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestInstanttime')"
              for="cbs-transactions-requestInstanttime"
            ></label>
            <div class="d-flex">
              <input
                id="cbs-transactions-requestInstanttime"
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
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.requestjson')"
              for="cbs-transactions-requestjson"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestjson"
              id="cbs-transactions-requestjson"
              data-cy="requestjson"
              :class="{ valid: !v$.requestjson.$invalid, invalid: v$.requestjson.$invalid }"
              v-model="v$.requestjson.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsrequestxml')"
              for="cbs-transactions-cbsrequestxml"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsrequestxml"
              id="cbs-transactions-cbsrequestxml"
              data-cy="cbsrequestxml"
              :class="{ valid: !v$.cbsrequestxml.$invalid, invalid: v$.cbsrequestxml.$invalid }"
              v-model="v$.cbsrequestxml.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.cbsresponsexml')"
              for="cbs-transactions-cbsresponsexml"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsresponsexml"
              id="cbs-transactions-cbsresponsexml"
              data-cy="cbsresponsexml"
              :class="{ valid: !v$.cbsresponsexml.$invalid, invalid: v$.cbsresponsexml.$invalid }"
              v-model="v$.cbsresponsexml.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.cBSTransactions.amount')"
              for="cbs-transactions-amount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="cbs-transactions-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
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
<script lang="ts" src="./cbs-transactions-update.component.ts"></script>
