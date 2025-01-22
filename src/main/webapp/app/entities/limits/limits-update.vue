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
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.registeredby')" for="limits-registeredby"></label>
            <input
              type="text"
              class="form-control"
              name="registeredby"
              id="limits-registeredby"
              data-cy="registeredby"
              :class="{ valid: !v$.registeredby.$invalid, invalid: v$.registeredby.$invalid }"
              v-model="v$.registeredby.$model"
            />
            <div v-if="v$.registeredby.$anyDirty && v$.registeredby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.registeredby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.limits.registereddate')"
              for="limits-registereddate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="registereddate"
              id="limits-registereddate"
              data-cy="registereddate"
              :class="{ valid: !v$.registereddate.$invalid, invalid: v$.registereddate.$invalid }"
              v-model="v$.registereddate.$model"
            />
            <div v-if="v$.registereddate.$anyDirty && v$.registereddate.$invalid">
              <small class="form-text text-danger" v-for="error of v$.registereddate.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.approved')" for="limits-approved"></label>
            <input
              type="text"
              class="form-control"
              name="approved"
              id="limits-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model="v$.approved.$model"
            />
            <div v-if="v$.approved.$anyDirty && v$.approved.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approved.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.approvedby')" for="limits-approvedby"></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="limits-approvedby"
              data-cy="approvedby"
              :class="{ valid: !v$.approvedby.$invalid, invalid: v$.approvedby.$invalid }"
              v-model="v$.approvedby.$model"
            />
            <div v-if="v$.approvedby.$anyDirty && v$.approvedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.approveddate')" for="limits-approveddate"></label>
            <input
              type="text"
              class="form-control"
              name="approveddate"
              id="limits-approveddate"
              data-cy="approveddate"
              :class="{ valid: !v$.approveddate.$invalid, invalid: v$.approveddate.$invalid }"
              v-model="v$.approveddate.$model"
            />
            <div v-if="v$.approveddate.$anyDirty && v$.approveddate.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approveddate.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.updatedby')" for="limits-updatedby"></label>
            <input
              type="text"
              class="form-control"
              name="updatedby"
              id="limits-updatedby"
              data-cy="updatedby"
              :class="{ valid: !v$.updatedby.$invalid, invalid: v$.updatedby.$invalid }"
              v-model="v$.updatedby.$model"
            />
            <div v-if="v$.updatedby.$anyDirty && v$.updatedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.updatedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.updateddate')" for="limits-updateddate"></label>
            <input
              type="text"
              class="form-control"
              name="updateddate"
              id="limits-updateddate"
              data-cy="updateddate"
              :class="{ valid: !v$.updateddate.$invalid, invalid: v$.updateddate.$invalid }"
              v-model="v$.updateddate.$model"
            />
            <div v-if="v$.updateddate.$anyDirty && v$.updateddate.$invalid">
              <small class="form-text text-danger" v-for="error of v$.updateddate.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.rework')" for="limits-rework"></label>
            <input
              type="number"
              class="form-control"
              name="rework"
              id="limits-rework"
              data-cy="rework"
              :class="{ valid: !v$.rework.$invalid, invalid: v$.rework.$invalid }"
              v-model.number="v$.rework.$model"
            />
            <div v-if="v$.rework.$anyDirty && v$.rework.$invalid">
              <small class="form-text text-danger" v-for="error of v$.rework.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.reworkby')" for="limits-reworkby"></label>
            <input
              type="text"
              class="form-control"
              name="reworkby"
              id="limits-reworkby"
              data-cy="reworkby"
              :class="{ valid: !v$.reworkby.$invalid, invalid: v$.reworkby.$invalid }"
              v-model="v$.reworkby.$model"
            />
            <div v-if="v$.reworkby.$anyDirty && v$.reworkby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.reworkby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.limits.sessionid')" for="limits-sessionid"></label>
            <input
              type="text"
              class="form-control"
              name="sessionid"
              id="limits-sessionid"
              data-cy="sessionid"
              :class="{ valid: !v$.sessionid.$invalid, invalid: v$.sessionid.$invalid }"
              v-model="v$.sessionid.$model"
            />
            <div v-if="v$.sessionid.$anyDirty && v$.sessionid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.sessionid.$errors" :key="error.$uid">{{ error.$message }}</small>
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
