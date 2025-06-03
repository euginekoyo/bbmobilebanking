<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.limits.home.createOrEditLabel"
          data-cy="LimitsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.limits.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="limits.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="limits.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.limits.transactiontype')"
              for="limits-transactiontype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transactiontype"
              id="limits-transactiontype"
              data-cy="transactiontype"
              :class="{ valid: !v$.transactiontype.$invalid, invalid: v$.transactiontype.$invalid }"
              v-model="v$.transactiontype.$model"
              required
            />
            <div v-if="v$.transactiontype.$anyDirty && v$.transactiontype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transactiontype.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.procode')" for="limits-procode"></label>
            <input
              type="text"
              class="form-control"
              name="procode"
              id="limits-procode"
              data-cy="procode"
              :class="{ valid: !v$.procode.$invalid, invalid: v$.procode.$invalid }"
              v-model="v$.procode.$model"
            />
            <div v-if="v$.procode.$anyDirty && v$.procode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.procode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.channel')" for="limits-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="limits-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.limits.transactionlimit')"
              for="limits-transactionlimit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="transactionlimit"
              id="limits-transactionlimit"
              data-cy="transactionlimit"
              :class="{ valid: !v$.transactionlimit.$invalid, invalid: v$.transactionlimit.$invalid }"
              v-model.number="v$.transactionlimit.$model"
              required
            />
            <div v-if="v$.transactionlimit.$anyDirty && v$.transactionlimit.$invalid">
              <small class="form-text text-danger" v-for="error of v$.transactionlimit.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.dailylimit')" for="limits-dailylimit"></label>
            <input
              type="number"
              class="form-control"
              name="dailylimit"
              id="limits-dailylimit"
              data-cy="dailylimit"
              :class="{ valid: !v$.dailylimit.$invalid, invalid: v$.dailylimit.$invalid }"
              v-model.number="v$.dailylimit.$model"
            />
            <div v-if="v$.dailylimit.$anyDirty && v$.dailylimit.$invalid">
              <small class="form-text text-danger" v-for="error of v$.dailylimit.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./limits-update.component.ts"></script>
