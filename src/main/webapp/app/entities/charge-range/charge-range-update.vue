<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.chargeRange.home.createOrEditLabel"
          data-cy="ChargeRangeCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.chargeRange.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="chargeRange.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="chargeRange.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.billerid')"
              for="charge-range-billerid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="billerid"
              id="charge-range-billerid"
              data-cy="billerid"
              :class="{ valid: !v$.billerid.$invalid, invalid: v$.billerid.$invalid }"
              v-model="v$.billerid.$model"
              required
            />
            <div v-if="v$.billerid.$anyDirty && v$.billerid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.billerid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.processingcode')"
              for="charge-range-processingcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="processingcode"
              id="charge-range-processingcode"
              data-cy="processingcode"
              :class="{ valid: !v$.processingcode.$invalid, invalid: v$.processingcode.$invalid }"
              v-model="v$.processingcode.$model"
              required
            />
            <div v-if="v$.processingcode.$anyDirty && v$.processingcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.processingcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.chargeRange.max')" for="charge-range-max"></label>
            <input
              type="number"
              class="form-control"
              name="max"
              id="charge-range-max"
              data-cy="max"
              :class="{ valid: !v$.max.$invalid, invalid: v$.max.$invalid }"
              v-model.number="v$.max.$model"
              required
            />
            <div v-if="v$.max.$anyDirty && v$.max.$invalid">
              <small class="form-text text-danger" v-for="error of v$.max.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.chargeRange.min')" for="charge-range-min"></label>
            <input
              type="number"
              class="form-control"
              name="min"
              id="charge-range-min"
              data-cy="min"
              :class="{ valid: !v$.min.$invalid, invalid: v$.min.$invalid }"
              v-model.number="v$.min.$model"
              required
            />
            <div v-if="v$.min.$anyDirty && v$.min.$invalid">
              <small class="form-text text-danger" v-for="error of v$.min.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.chargeRange.amount')" for="charge-range-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="charge-range-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
              required
            />
            <div v-if="v$.amount.$anyDirty && v$.amount.$invalid">
              <small class="form-text text-danger" v-for="error of v$.amount.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.createdby')"
              for="charge-range-createdby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdby"
              id="charge-range-createdby"
              data-cy="createdby"
              :class="{ valid: !v$.createdby.$invalid, invalid: v$.createdby.$invalid }"
              v-model="v$.createdby.$model"
            />
            <div v-if="v$.createdby.$anyDirty && v$.createdby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedby')"
              for="charge-range-approvedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="charge-range-approvedby"
              data-cy="approvedby"
              :class="{ valid: !v$.approvedby.$invalid, invalid: v$.approvedby.$invalid }"
              v-model="v$.approvedby.$model"
            />
            <div v-if="v$.approvedby.$anyDirty && v$.approvedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.createdat')"
              for="charge-range-createdat"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdat"
              id="charge-range-createdat"
              data-cy="createdat"
              :class="{ valid: !v$.createdat.$invalid, invalid: v$.createdat.$invalid }"
              v-model="v$.createdat.$model"
            />
            <div v-if="v$.createdat.$anyDirty && v$.createdat.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdat.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.approvedon')"
              for="charge-range-approvedon"
            ></label>
            <input
              type="text"
              class="form-control"
              name="approvedon"
              id="charge-range-approvedon"
              data-cy="approvedon"
              :class="{ valid: !v$.approvedon.$invalid, invalid: v$.approvedon.$invalid }"
              v-model="v$.approvedon.$model"
            />
            <div v-if="v$.approvedon.$anyDirty && v$.approvedon.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedon.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.approved')"
              for="charge-range-approved"
            ></label>
            <input
              type="number"
              class="form-control"
              name="approved"
              id="charge-range-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model.number="v$.approved.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.declined')"
              for="charge-range-declined"
            ></label>
            <input
              type="number"
              class="form-control"
              name="declined"
              id="charge-range-declined"
              data-cy="declined"
              :class="{ valid: !v$.declined.$invalid, invalid: v$.declined.$invalid }"
              v-model.number="v$.declined.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.declinedby')"
              for="charge-range-declinedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="declinedby"
              id="charge-range-declinedby"
              data-cy="declinedby"
              :class="{ valid: !v$.declinedby.$invalid, invalid: v$.declinedby.$invalid }"
              v-model="v$.declinedby.$model"
            />
            <div v-if="v$.declinedby.$anyDirty && v$.declinedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.declinedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.chargeRange.chargeid')"
              for="charge-range-chargeid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="chargeid"
              id="charge-range-chargeid"
              data-cy="chargeid"
              :class="{ valid: !v$.chargeid.$invalid, invalid: v$.chargeid.$invalid }"
              v-model.number="v$.chargeid.$model"
              required
            />
            <div v-if="v$.chargeid.$anyDirty && v$.chargeid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.chargeid.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./charge-range-update.component.ts"></script>
