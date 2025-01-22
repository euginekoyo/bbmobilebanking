<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.sPSParticipatingCodes.home.createOrEditLabel"
          data-cy="SPSParticipatingCodesCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="sPSParticipatingCodes.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="sPSParticipatingCodes.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.biccode')"
              for="sps-participating-codes-biccode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="biccode"
              id="sps-participating-codes-biccode"
              data-cy="biccode"
              :class="{ valid: !v$.biccode.$invalid, invalid: v$.biccode.$invalid }"
              v-model="v$.biccode.$model"
            />
            <div v-if="v$.biccode.$anyDirty && v$.biccode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.biccode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicname')"
              for="sps-participating-codes-bicname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bicname"
              id="sps-participating-codes-bicname"
              data-cy="bicname"
              :class="{ valid: !v$.bicname.$invalid, invalid: v$.bicname.$invalid }"
              v-model="v$.bicname.$model"
            />
            <div v-if="v$.bicname.$anyDirty && v$.bicname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.bicname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.sPSParticipatingCodes.bicstatus')"
              for="sps-participating-codes-bicstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bicstatus"
              id="sps-participating-codes-bicstatus"
              data-cy="bicstatus"
              :class="{ valid: !v$.bicstatus.$invalid, invalid: v$.bicstatus.$invalid }"
              v-model="v$.bicstatus.$model"
            />
            <div v-if="v$.bicstatus.$anyDirty && v$.bicstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.bicstatus.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./sps-participating-codes-update.component.ts"></script>
