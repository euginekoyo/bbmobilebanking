<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="mobileBankingAdminPortalApp.mobileAppTransactions.home.createOrEditLabel"
          data-cy="MobileAppTransactionsCreateUpdateHeading"
          v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="mobileAppTransactions.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="mobileAppTransactions.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channel')"
              for="mobile-app-transactions-channel"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="mobile-app-transactions-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelIp')"
              for="mobile-app-transactions-channelIp"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channelIp"
              id="mobile-app-transactions-channelIp"
              data-cy="channelIp"
              :class="{ valid: !v$.channelIp.$invalid, invalid: v$.channelIp.$invalid }"
              v-model="v$.channelIp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelReference')"
              for="mobile-app-transactions-channelReference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channelReference"
              id="mobile-app-transactions-channelReference"
              data-cy="channelReference"
              :class="{ valid: !v$.channelReference.$invalid, invalid: v$.channelReference.$invalid }"
              v-model="v$.channelReference.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.channelTimestamp')"
              for="mobile-app-transactions-channelTimestamp"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channelTimestamp"
              id="mobile-app-transactions-channelTimestamp"
              data-cy="channelTimestamp"
              :class="{ valid: !v$.channelTimestamp.$invalid, invalid: v$.channelTimestamp.$invalid }"
              v-model="v$.channelTimestamp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.clientId')"
              for="mobile-app-transactions-clientId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientId"
              id="mobile-app-transactions-clientId"
              data-cy="clientId"
              :class="{ valid: !v$.clientId.$invalid, invalid: v$.clientId.$invalid }"
              v-model="v$.clientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.createdAt')"
              for="mobile-app-transactions-createdAt"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdAt"
              id="mobile-app-transactions-createdAt"
              data-cy="createdAt"
              :class="{ valid: !v$.createdAt.$invalid, invalid: v$.createdAt.$invalid }"
              v-model="v$.createdAt.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.debitAccount')"
              for="mobile-app-transactions-debitAccount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="debitAccount"
              id="mobile-app-transactions-debitAccount"
              data-cy="debitAccount"
              :class="{ valid: !v$.debitAccount.$invalid, invalid: v$.debitAccount.$invalid }"
              v-model="v$.debitAccount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.direction')"
              for="mobile-app-transactions-direction"
            ></label>
            <input
              type="text"
              class="form-control"
              name="direction"
              id="mobile-app-transactions-direction"
              data-cy="direction"
              :class="{ valid: !v$.direction.$invalid, invalid: v$.direction.$invalid }"
              v-model="v$.direction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.errorDescription')"
              for="mobile-app-transactions-errorDescription"
            ></label>
            <input
              type="text"
              class="form-control"
              name="errorDescription"
              id="mobile-app-transactions-errorDescription"
              data-cy="errorDescription"
              :class="{ valid: !v$.errorDescription.$invalid, invalid: v$.errorDescription.$invalid }"
              v-model="v$.errorDescription.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.geolocation')"
              for="mobile-app-transactions-geolocation"
            ></label>
            <input
              type="text"
              class="form-control"
              name="geolocation"
              id="mobile-app-transactions-geolocation"
              data-cy="geolocation"
              :class="{ valid: !v$.geolocation.$invalid, invalid: v$.geolocation.$invalid }"
              v-model="v$.geolocation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.hostCode')"
              for="mobile-app-transactions-hostCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="hostCode"
              id="mobile-app-transactions-hostCode"
              data-cy="hostCode"
              :class="{ valid: !v$.hostCode.$invalid, invalid: v$.hostCode.$invalid }"
              v-model="v$.hostCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.phoneNumber')"
              for="mobile-app-transactions-phoneNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="mobile-app-transactions-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !v$.phoneNumber.$invalid, invalid: v$.phoneNumber.$invalid }"
              v-model="v$.phoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseCode')"
              for="mobile-app-transactions-responseCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responseCode"
              id="mobile-app-transactions-responseCode"
              data-cy="responseCode"
              :class="{ valid: !v$.responseCode.$invalid, invalid: v$.responseCode.$invalid }"
              v-model="v$.responseCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.responseMessage')"
              for="mobile-app-transactions-responseMessage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responseMessage"
              id="mobile-app-transactions-responseMessage"
              data-cy="responseMessage"
              :class="{ valid: !v$.responseMessage.$invalid, invalid: v$.responseMessage.$invalid }"
              v-model="v$.responseMessage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionCode')"
              for="mobile-app-transactions-transactionCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionCode"
              id="mobile-app-transactions-transactionCode"
              data-cy="transactionCode"
              :class="{ valid: !v$.transactionCode.$invalid, invalid: v$.transactionCode.$invalid }"
              v-model="v$.transactionCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.transactionType')"
              for="mobile-app-transactions-transactionType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactionType"
              id="mobile-app-transactions-transactionType"
              data-cy="transactionType"
              :class="{ valid: !v$.transactionType.$invalid, invalid: v$.transactionType.$invalid }"
              v-model="v$.transactionType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgent')"
              for="mobile-app-transactions-userAgent"
            ></label>
            <input
              type="text"
              class="form-control"
              name="userAgent"
              id="mobile-app-transactions-userAgent"
              data-cy="userAgent"
              :class="{ valid: !v$.userAgent.$invalid, invalid: v$.userAgent.$invalid }"
              v-model="v$.userAgent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.userAgentVersion')"
              for="mobile-app-transactions-userAgentVersion"
            ></label>
            <input
              type="text"
              class="form-control"
              name="userAgentVersion"
              id="mobile-app-transactions-userAgentVersion"
              data-cy="userAgentVersion"
              :class="{ valid: !v$.userAgentVersion.$invalid, invalid: v$.userAgentVersion.$invalid }"
              v-model="v$.userAgentVersion.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.amount')"
              for="mobile-app-transactions-amount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="amount"
              id="mobile-app-transactions-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.chargeamount')"
              for="mobile-app-transactions-chargeamount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="chargeamount"
              id="mobile-app-transactions-chargeamount"
              data-cy="chargeamount"
              :class="{ valid: !v$.chargeamount.$invalid, invalid: v$.chargeamount.$invalid }"
              v-model="v$.chargeamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.creditAccount')"
              for="mobile-app-transactions-creditAccount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creditAccount"
              id="mobile-app-transactions-creditAccount"
              data-cy="creditAccount"
              :class="{ valid: !v$.creditAccount.$invalid, invalid: v$.creditAccount.$invalid }"
              v-model="v$.creditAccount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('mobileBankingAdminPortalApp.mobileAppTransactions.cbsReference')"
              for="mobile-app-transactions-cbsReference"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsReference"
              id="mobile-app-transactions-cbsReference"
              data-cy="cbsReference"
              :class="{ valid: !v$.cbsReference.$invalid, invalid: v$.cbsReference.$invalid }"
              v-model="v$.cbsReference.$model"
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
<script lang="ts" src="./mobile-app-transactions-update.component.ts"></script>
