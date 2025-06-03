<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.branches.home.createOrEditLabel"
          data-cy="BranchesCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.branches.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="branches.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="branches.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.branchname')" for="branches-branchname"></label>
            <input
              type="text"
              class="form-control"
              name="branchname"
              id="branches-branchname"
              data-cy="branchname"
              :class="{ valid: !v$.branchname.$invalid, invalid: v$.branchname.$invalid }"
              v-model="v$.branchname.$model"
            />
            <div v-if="v$.branchname.$anyDirty && v$.branchname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branchname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.branchcode')" for="branches-branchcode"></label>
            <input
              type="text"
              class="form-control"
              name="branchcode"
              id="branches-branchcode"
              data-cy="branchcode"
              :class="{ valid: !v$.branchcode.$invalid, invalid: v$.branchcode.$invalid }"
              v-model="v$.branchcode.$model"
              required
            />
            <div v-if="v$.branchcode.$anyDirty && v$.branchcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branchcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.email')" for="branches-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="branches-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
            <div v-if="v$.email.$anyDirty && v$.email.$invalid">
              <small class="form-text text-danger" v-for="error of v$.email.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.address')" for="branches-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="branches-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
            <div v-if="v$.address.$anyDirty && v$.address.$invalid">
              <small class="form-text text-danger" v-for="error of v$.address.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.phone')" for="branches-phone"></label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="branches-phone"
              data-cy="phone"
              :class="{ valid: !v$.phone.$invalid, invalid: v$.phone.$invalid }"
              v-model="v$.phone.$model"
            />
            <div v-if="v$.phone.$anyDirty && v$.phone.$invalid">
              <small class="form-text text-danger" v-for="error of v$.phone.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.location')" for="branches-location"></label>
            <input
              type="text"
              class="form-control"
              name="location"
              id="branches-location"
              data-cy="location"
              :class="{ valid: !v$.location.$invalid, invalid: v$.location.$invalid }"
              v-model="v$.location.$model"
              required
            />
            <div v-if="v$.location.$anyDirty && v$.location.$invalid">
              <small class="form-text text-danger" v-for="error of v$.location.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.branches.contactperson')"
              for="branches-contactperson"
            ></label>
            <input
              type="text"
              class="form-control"
              name="contactperson"
              id="branches-contactperson"
              data-cy="contactperson"
              :class="{ valid: !v$.contactperson.$invalid, invalid: v$.contactperson.$invalid }"
              v-model="v$.contactperson.$model"
            />
            <div v-if="v$.contactperson.$anyDirty && v$.contactperson.$invalid">
              <small class="form-text text-danger" v-for="error of v$.contactperson.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.remarks')" for="branches-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="branches-remarks"
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
              v-text="t$('bbMobileBankingAdminApp.branches.checkerremarks')"
              for="branches-checkerremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="checkerremarks"
              id="branches-checkerremarks"
              data-cy="checkerremarks"
              :class="{ valid: !v$.checkerremarks.$invalid, invalid: v$.checkerremarks.$invalid }"
              v-model="v$.checkerremarks.$model"
            />
            <div v-if="v$.checkerremarks.$anyDirty && v$.checkerremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.checkerremarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.district')" for="branches-district"></label>
            <input
              type="text"
              class="form-control"
              name="district"
              id="branches-district"
              data-cy="district"
              :class="{ valid: !v$.district.$invalid, invalid: v$.district.$invalid }"
              v-model="v$.district.$model"
            />
            <div v-if="v$.district.$anyDirty && v$.district.$invalid">
              <small class="form-text text-danger" v-for="error of v$.district.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.region')" for="branches-region"></label>
            <input
              type="text"
              class="form-control"
              name="region"
              id="branches-region"
              data-cy="region"
              :class="{ valid: !v$.region.$invalid, invalid: v$.region.$invalid }"
              v-model="v$.region.$model"
            />
            <div v-if="v$.region.$anyDirty && v$.region.$invalid">
              <small class="form-text text-danger" v-for="error of v$.region.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.regionname')" for="branches-regionname"></label>
            <input
              type="text"
              class="form-control"
              name="regionname"
              id="branches-regionname"
              data-cy="regionname"
              :class="{ valid: !v$.regionname.$invalid, invalid: v$.regionname.$invalid }"
              v-model="v$.regionname.$model"
            />
            <div v-if="v$.regionname.$anyDirty && v$.regionname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.regionname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.reporting')" for="branches-reporting"></label>
            <input
              type="number"
              class="form-control"
              name="reporting"
              id="branches-reporting"
              data-cy="reporting"
              :class="{ valid: !v$.reporting.$invalid, invalid: v$.reporting.$invalid }"
              v-model.number="v$.reporting.$model"
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
<script lang="ts" src="./branches-update.component.ts"></script>
