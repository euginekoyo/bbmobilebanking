<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.range.home.createOrEditLabel"
          data-cy="RangeCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.range.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="range.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="range.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.rangefrom')" for="range-rangefrom"></label>
            <input
              type="number"
              class="form-control"
              name="rangefrom"
              id="range-rangefrom"
              data-cy="rangefrom"
              :class="{ valid: !v$.rangefrom.$invalid, invalid: v$.rangefrom.$invalid }"
              v-model.number="v$.rangefrom.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.rangeto')" for="range-rangeto"></label>
            <input
              type="number"
              class="form-control"
              name="rangeto"
              id="range-rangeto"
              data-cy="rangeto"
              :class="{ valid: !v$.rangeto.$invalid, invalid: v$.rangeto.$invalid }"
              v-model.number="v$.rangeto.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.amount')" for="range-amount"></label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="range-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.txntype')" for="range-txntype"></label>
            <input
              type="text"
              class="form-control"
              name="txntype"
              id="range-txntype"
              data-cy="txntype"
              :class="{ valid: !v$.txntype.$invalid, invalid: v$.txntype.$invalid }"
              v-model="v$.txntype.$model"
            />
            <div v-if="v$.txntype.$anyDirty && v$.txntype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.txntype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.txncode')" for="range-txncode"></label>
            <input
              type="text"
              class="form-control"
              name="txncode"
              id="range-txncode"
              data-cy="txncode"
              :class="{ valid: !v$.txncode.$invalid, invalid: v$.txncode.$invalid }"
              v-model="v$.txncode.$model"
            />
            <div v-if="v$.txncode.$anyDirty && v$.txncode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.txncode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.chargeid')" for="range-chargeid"></label>
            <input
              type="number"
              class="form-control"
              name="chargeid"
              id="range-chargeid"
              data-cy="chargeid"
              :class="{ valid: !v$.chargeid.$invalid, invalid: v$.chargeid.$invalid }"
              v-model.number="v$.chargeid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.range.channel')" for="range-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="range-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./range-update.component.ts"></script>
