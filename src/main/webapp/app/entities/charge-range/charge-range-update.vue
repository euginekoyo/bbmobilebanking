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
