<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.pinResetHistory.home.createOrEditLabel"
          data-cy="PinResetHistoryCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.pinResetHistory.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="pinResetHistory.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="pinResetHistory.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.phonenumber')"
              for="pin-reset-history-phonenumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phonenumber"
              id="pin-reset-history-phonenumber"
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
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.customername')"
              for="pin-reset-history-customername"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customername"
              id="pin-reset-history-customername"
              data-cy="customername"
              :class="{ valid: !v$.customername.$invalid, invalid: v$.customername.$invalid }"
              v-model="v$.customername.$model"
            />
            <div v-if="v$.customername.$anyDirty && v$.customername.$invalid">
              <small class="form-text text-danger" v-for="error of v$.customername.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockedon')"
              for="pin-reset-history-pinblockedon"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinblockedon"
              id="pin-reset-history-pinblockedon"
              data-cy="pinblockedon"
              :class="{ valid: !v$.pinblockedon.$invalid, invalid: v$.pinblockedon.$invalid }"
              v-model="v$.pinblockedon.$model"
            />
            <div v-if="v$.pinblockedon.$anyDirty && v$.pinblockedon.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinblockedon.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinblockremarks')"
              for="pin-reset-history-pinblockremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinblockremarks"
              id="pin-reset-history-pinblockremarks"
              data-cy="pinblockremarks"
              :class="{ valid: !v$.pinblockremarks.$invalid, invalid: v$.pinblockremarks.$invalid }"
              v-model="v$.pinblockremarks.$model"
            />
            <div v-if="v$.pinblockremarks.$anyDirty && v$.pinblockremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinblockremarks.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetby')"
              for="pin-reset-history-pinresetby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinresetby"
              id="pin-reset-history-pinresetby"
              data-cy="pinresetby"
              :class="{ valid: !v$.pinresetby.$invalid, invalid: v$.pinresetby.$invalid }"
              v-model="v$.pinresetby.$model"
            />
            <div v-if="v$.pinresetby.$anyDirty && v$.pinresetby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinresetby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinreseton')"
              for="pin-reset-history-pinreseton"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinreseton"
              id="pin-reset-history-pinreseton"
              data-cy="pinreseton"
              :class="{ valid: !v$.pinreseton.$invalid, invalid: v$.pinreseton.$invalid }"
              v-model="v$.pinreseton.$model"
            />
            <div v-if="v$.pinreseton.$anyDirty && v$.pinreseton.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinreseton.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedby')"
              for="pin-reset-history-pinresetapprovedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinresetapprovedby"
              id="pin-reset-history-pinresetapprovedby"
              data-cy="pinresetapprovedby"
              :class="{ valid: !v$.pinresetapprovedby.$invalid, invalid: v$.pinresetapprovedby.$invalid }"
              v-model="v$.pinresetapprovedby.$model"
            />
            <div v-if="v$.pinresetapprovedby.$anyDirty && v$.pinresetapprovedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinresetapprovedby.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetapprovedon')"
              for="pin-reset-history-pinresetapprovedon"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinresetapprovedon"
              id="pin-reset-history-pinresetapprovedon"
              data-cy="pinresetapprovedon"
              :class="{ valid: !v$.pinresetapprovedon.$invalid, invalid: v$.pinresetapprovedon.$invalid }"
              v-model="v$.pinresetapprovedon.$model"
            />
            <div v-if="v$.pinresetapprovedon.$anyDirty && v$.pinresetapprovedon.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinresetapprovedon.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.pinresetremarks')"
              for="pin-reset-history-pinresetremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinresetremarks"
              id="pin-reset-history-pinresetremarks"
              data-cy="pinresetremarks"
              :class="{ valid: !v$.pinresetremarks.$invalid, invalid: v$.pinresetremarks.$invalid }"
              v-model="v$.pinresetremarks.$model"
            />
            <div v-if="v$.pinresetremarks.$anyDirty && v$.pinresetremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinresetremarks.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.pinResetHistory.branchcode')"
              for="pin-reset-history-branchcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="branchcode"
              id="pin-reset-history-branchcode"
              data-cy="branchcode"
              :class="{ valid: !v$.branchcode.$invalid, invalid: v$.branchcode.$invalid }"
              v-model="v$.branchcode.$model"
            />
            <div v-if="v$.branchcode.$anyDirty && v$.branchcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branchcode.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./pin-reset-history-update.component.ts"></script>
