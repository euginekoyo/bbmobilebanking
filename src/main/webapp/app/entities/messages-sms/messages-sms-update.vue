<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.messagesSms.home.createOrEditLabel"
          data-cy="MessagesSmsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.messagesSms.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="messagesSms.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="messagesSms.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.trndatetime')"
              for="messages-sms-trndatetime"
            ></label>
            <div class="d-flex">
              <input
                id="messages-sms-trndatetime"
                data-cy="trndatetime"
                type="datetime-local"
                class="form-control"
                name="trndatetime"
                :class="{ valid: !v$.trndatetime.$invalid, invalid: v$.trndatetime.$invalid }"
                :value="convertDateTimeFromServer(v$.trndatetime.$model)"
                @change="updateInstantField('trndatetime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.phonenumber')"
              for="messages-sms-phonenumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phonenumber"
              id="messages-sms-phonenumber"
              data-cy="phonenumber"
              :class="{ valid: !v$.phonenumber.$invalid, invalid: v$.phonenumber.$invalid }"
              v-model="v$.phonenumber.$model"
            />
            <div v-if="v$.phonenumber.$anyDirty && v$.phonenumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.phonenumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.transactionno')"
              for="messages-sms-transactionno"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionno"
              id="messages-sms-transactionno"
              data-cy="transactionno"
              :class="{ valid: !v$.transactionno.$invalid, invalid: v$.transactionno.$invalid }"
              v-model="v$.transactionno.$model"
            />
            <div v-if="v$.transactionno.$anyDirty && v$.transactionno.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transactionno.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.accountnumber')"
              for="messages-sms-accountnumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="accountnumber"
              id="messages-sms-accountnumber"
              data-cy="accountnumber"
              :class="{ valid: !v$.accountnumber.$invalid, invalid: v$.accountnumber.$invalid }"
              v-model="v$.accountnumber.$model"
            />
            <div v-if="v$.accountnumber.$anyDirty && v$.accountnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.accountnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.message')" for="messages-sms-message"></label>
            <input
              type="text"
              class="form-control"
              name="message"
              id="messages-sms-message"
              data-cy="message"
              :class="{ valid: !v$.message.$invalid, invalid: v$.message.$invalid }"
              v-model="v$.message.$model"
            />
            <div v-if="v$.message.$anyDirty && v$.message.$invalid">
              <small class="form-text text-danger" v-for="error of v$.message.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.channel')" for="messages-sms-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="messages-sms-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.trials')" for="messages-sms-trials"></label>
            <input
              type="number"
              class="form-control"
              name="trials"
              id="messages-sms-trials"
              data-cy="trials"
              :class="{ valid: !v$.trials.$invalid, invalid: v$.trials.$invalid }"
              v-model.number="v$.trials.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.priority')"
              for="messages-sms-priority"
            ></label>
            <input
              type="number"
              class="form-control"
              name="priority"
              id="messages-sms-priority"
              data-cy="priority"
              :class="{ valid: !v$.priority.$invalid, invalid: v$.priority.$invalid }"
              v-model.number="v$.priority.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.responsecode')"
              for="messages-sms-responsecode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsecode"
              id="messages-sms-responsecode"
              data-cy="responsecode"
              :class="{ valid: !v$.responsecode.$invalid, invalid: v$.responsecode.$invalid }"
              v-model="v$.responsecode.$model"
            />
            <div v-if="v$.responsecode.$anyDirty && v$.responsecode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.responsecode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.responsemsg')"
              for="messages-sms-responsemsg"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsemsg"
              id="messages-sms-responsemsg"
              data-cy="responsemsg"
              :class="{ valid: !v$.responsemsg.$invalid, invalid: v$.responsemsg.$invalid }"
              v-model="v$.responsemsg.$model"
            />
            <div v-if="v$.responsemsg.$anyDirty && v$.responsemsg.$invalid">
              <small class="form-text text-danger" v-for="error of v$.responsemsg.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.sent')" for="messages-sms-sent"></label>
            <input
              type="number"
              class="form-control"
              name="sent"
              id="messages-sms-sent"
              data-cy="sent"
              :class="{ valid: !v$.sent.$invalid, invalid: v$.sent.$invalid }"
              v-model.number="v$.sent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.delivered')"
              for="messages-sms-delivered"
            ></label>
            <input
              type="number"
              class="form-control"
              name="delivered"
              id="messages-sms-delivered"
              data-cy="delivered"
              :class="{ valid: !v$.delivered.$invalid, invalid: v$.delivered.$invalid }"
              v-model.number="v$.delivered.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.txntype')" for="messages-sms-txntype"></label>
            <input
              type="text"
              class="form-control"
              name="txntype"
              id="messages-sms-txntype"
              data-cy="txntype"
              :class="{ valid: !v$.txntype.$invalid, invalid: v$.txntype.$invalid }"
              v-model="v$.txntype.$model"
            />
            <div v-if="v$.txntype.$anyDirty && v$.txntype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.txntype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.errorexception')"
              for="messages-sms-errorexception"
            ></label>
            <input
              type="number"
              class="form-control"
              name="errorexception"
              id="messages-sms-errorexception"
              data-cy="errorexception"
              :class="{ valid: !v$.errorexception.$invalid, invalid: v$.errorexception.$invalid }"
              v-model.number="v$.errorexception.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.datecreated')"
              for="messages-sms-datecreated"
            ></label>
            <div class="d-flex">
              <input
                id="messages-sms-datecreated"
                data-cy="datecreated"
                type="datetime-local"
                class="form-control"
                name="datecreated"
                :class="{ valid: !v$.datecreated.$invalid, invalid: v$.datecreated.$invalid }"
                :value="convertDateTimeFromServer(v$.datecreated.$model)"
                @change="updateInstantField('datecreated', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.datesent')"
              for="messages-sms-datesent"
            ></label>
            <input
              type="text"
              class="form-control"
              name="datesent"
              id="messages-sms-datesent"
              data-cy="datesent"
              :class="{ valid: !v$.datesent.$invalid, invalid: v$.datesent.$invalid }"
              v-model="v$.datesent.$model"
            />
            <div v-if="v$.datesent.$anyDirty && v$.datesent.$invalid">
              <small class="form-text text-danger" v-for="error of v$.datesent.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.rtpsreqtime')"
              for="messages-sms-rtpsreqtime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="rtpsreqtime"
              id="messages-sms-rtpsreqtime"
              data-cy="rtpsreqtime"
              :class="{ valid: !v$.rtpsreqtime.$invalid, invalid: v$.rtpsreqtime.$invalid }"
              v-model="v$.rtpsreqtime.$model"
            />
            <div v-if="v$.rtpsreqtime.$anyDirty && v$.rtpsreqtime.$invalid">
              <small class="form-text text-danger" v-for="error of v$.rtpsreqtime.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.fxgenerated')"
              for="messages-sms-fxgenerated"
            ></label>
            <input
              type="text"
              class="form-control"
              name="fxgenerated"
              id="messages-sms-fxgenerated"
              data-cy="fxgenerated"
              :class="{ valid: !v$.fxgenerated.$invalid, invalid: v$.fxgenerated.$invalid }"
              v-model="v$.fxgenerated.$model"
            />
            <div v-if="v$.fxgenerated.$anyDirty && v$.fxgenerated.$invalid">
              <small class="form-text text-danger" v-for="error of v$.fxgenerated.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.taxprocessed')"
              for="messages-sms-taxprocessed"
            ></label>
            <input
              type="number"
              class="form-control"
              name="taxprocessed"
              id="messages-sms-taxprocessed"
              data-cy="taxprocessed"
              :class="{ valid: !v$.taxprocessed.$invalid, invalid: v$.taxprocessed.$invalid }"
              v-model.number="v$.taxprocessed.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.batchnumber')"
              for="messages-sms-batchnumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="batchnumber"
              id="messages-sms-batchnumber"
              data-cy="batchnumber"
              :class="{ valid: !v$.batchnumber.$invalid, invalid: v$.batchnumber.$invalid }"
              v-model="v$.batchnumber.$model"
            />
            <div v-if="v$.batchnumber.$anyDirty && v$.batchnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.batchnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.batchnumbertax')"
              for="messages-sms-batchnumbertax"
            ></label>
            <input
              type="text"
              class="form-control"
              name="batchnumbertax"
              id="messages-sms-batchnumbertax"
              data-cy="batchnumbertax"
              :class="{ valid: !v$.batchnumbertax.$invalid, invalid: v$.batchnumbertax.$invalid }"
              v-model="v$.batchnumbertax.$model"
            />
            <div v-if="v$.batchnumbertax.$anyDirty && v$.batchnumbertax.$invalid">
              <small class="form-text text-danger" v-for="error of v$.batchnumbertax.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.responsetime')"
              for="messages-sms-responsetime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsetime"
              id="messages-sms-responsetime"
              data-cy="responsetime"
              :class="{ valid: !v$.responsetime.$invalid, invalid: v$.responsetime.$invalid }"
              v-model="v$.responsetime.$model"
            />
            <div v-if="v$.responsetime.$anyDirty && v$.responsetime.$invalid">
              <small class="form-text text-danger" v-for="error of v$.responsetime.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.pduseqid')"
              for="messages-sms-pduseqid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pduseqid"
              id="messages-sms-pduseqid"
              data-cy="pduseqid"
              :class="{ valid: !v$.pduseqid.$invalid, invalid: v$.pduseqid.$invalid }"
              v-model="v$.pduseqid.$model"
            />
            <div v-if="v$.pduseqid.$anyDirty && v$.pduseqid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pduseqid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.messagesSms.remarks')" for="messages-sms-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="messages-sms-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
            <div v-if="v$.remarks.$anyDirty && v$.remarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.remarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messagesSms.resendby')"
              for="messages-sms-resendby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="resendby"
              id="messages-sms-resendby"
              data-cy="resendby"
              :class="{ valid: !v$.resendby.$invalid, invalid: v$.resendby.$invalid }"
              v-model="v$.resendby.$model"
            />
            <div v-if="v$.resendby.$anyDirty && v$.resendby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.resendby.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./messages-sms-update.component.ts"></script>
