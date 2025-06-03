<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.charge.home.createOrEditLabel"
          data-cy="ChargeCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.charge.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="charge.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="charge.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.txntype')" for="charge-txntype"></label>
            <input
              type="text"
              class="form-control"
              name="txntype"
              id="charge-txntype"
              data-cy="txntype"
              :class="{ valid: !v$.txntype.$invalid, invalid: v$.txntype.$invalid }"
              v-model="v$.txntype.$model"
              required
            />
            <div v-if="v$.txntype.$anyDirty && v$.txntype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.txntype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.feemode')" for="charge-feemode"></label>
            <input
              type="number"
              class="form-control"
              name="feemode"
              id="charge-feemode"
              data-cy="feemode"
              :class="{ valid: !v$.feemode.$invalid, invalid: v$.feemode.$invalid }"
              v-model.number="v$.feemode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.amount')" for="charge-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="charge-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>

          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.channel')" for="charge-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="charge-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.txncode')" for="charge-txncode"></label>
            <input
              type="number"
              class="form-control"
              name="txncode"
              id="charge-txncode"
              data-cy="txncode"
              :class="{ valid: !v$.txncode.$invalid, invalid: v$.txncode.$invalid }"
              v-model.number="v$.txncode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.description')" for="charge-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="charge-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
            <div v-if="v$.description.$anyDirty && v$.description.$invalid">
              <small class="form-text text-danger" v-for="error of v$.description.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>

          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.chargeRange')" for="charge-chargeRange"></label>
            <select class="form-control" id="charge-chargeRange" data-cy="chargeRange" name="chargeRange" v-model="charge.chargeRange">
              <option :value="null"></option>
              <option
                :value="charge.chargeRange && chargeRangeOption.id === charge.chargeRange.id ? charge.chargeRange : chargeRangeOption"
                v-for="chargeRangeOption in chargeRanges"
                :key="chargeRangeOption.id"
              >
                {{ chargeRangeOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.charge.range')" for="charge-range"></label>
            <select class="form-control" id="charge-range" data-cy="range" name="range" v-model="charge.range">
              <option :value="null"></option>
              <option
                :value="charge.range && rangeOption.id === charge.range.id ? charge.range : rangeOption"
                v-for="rangeOption in ranges"
                :key="rangeOption.id"
              >
                {{ rangeOption.id }}
              </option>
            </select>
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
<script lang="ts" src="./charge-update.component.ts"></script>
