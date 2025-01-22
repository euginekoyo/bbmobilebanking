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
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.approved')" for="branches-approved"></label>
            <input
              type="number"
              class="form-control"
              name="approved"
              id="branches-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model.number="v$.approved.$model"
            />
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
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.createdby')" for="branches-createdby"></label>
            <input
              type="text"
              class="form-control"
              name="createdby"
              id="branches-createdby"
              data-cy="createdby"
              :class="{ valid: !v$.createdby.$invalid, invalid: v$.createdby.$invalid }"
              v-model="v$.createdby.$model"
            />
            <div v-if="v$.createdby.$anyDirty && v$.createdby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.createdon')" for="branches-createdon"></label>
            <div class="d-flex">
              <input
                id="branches-createdon"
                data-cy="createdon"
                type="datetime-local"
                class="form-control"
                name="createdon"
                :class="{ valid: !v$.createdon.$invalid, invalid: v$.createdon.$invalid }"
                :value="convertDateTimeFromServer(v$.createdon.$model)"
                @change="updateInstantField('createdon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.approvedby')" for="branches-approvedby"></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="branches-approvedby"
              data-cy="approvedby"
              :class="{ valid: !v$.approvedby.$invalid, invalid: v$.approvedby.$invalid }"
              v-model="v$.approvedby.$model"
            />
            <div v-if="v$.approvedby.$anyDirty && v$.approvedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.approvedon')" for="branches-approvedon"></label>
            <input
              type="text"
              class="form-control"
              name="approvedon"
              id="branches-approvedon"
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
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.deletedby')" for="branches-deletedby"></label>
            <input
              type="text"
              class="form-control"
              name="deletedby"
              id="branches-deletedby"
              data-cy="deletedby"
              :class="{ valid: !v$.deletedby.$invalid, invalid: v$.deletedby.$invalid }"
              v-model="v$.deletedby.$model"
            />
            <div v-if="v$.deletedby.$anyDirty && v$.deletedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.deletedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.deletedon')" for="branches-deletedon"></label>
            <div class="d-flex">
              <input
                id="branches-deletedon"
                data-cy="deletedon"
                type="datetime-local"
                class="form-control"
                name="deletedon"
                :class="{ valid: !v$.deletedon.$invalid, invalid: v$.deletedon.$invalid }"
                :value="convertDateTimeFromServer(v$.deletedon.$model)"
                @change="updateInstantField('deletedon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.branches.deleteremarks')"
              for="branches-deleteremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="deleteremarks"
              id="branches-deleteremarks"
              data-cy="deleteremarks"
              :class="{ valid: !v$.deleteremarks.$invalid, invalid: v$.deleteremarks.$invalid }"
              v-model="v$.deleteremarks.$model"
            />
            <div v-if="v$.deleteremarks.$anyDirty && v$.deleteremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.deleteremarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.deleted')" for="branches-deleted"></label>
            <input
              type="number"
              class="form-control"
              name="deleted"
              id="branches-deleted"
              data-cy="deleted"
              :class="{ valid: !v$.deleted.$invalid, invalid: v$.deleted.$invalid }"
              v-model.number="v$.deleted.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.declined')" for="branches-declined"></label>
            <input
              type="number"
              class="form-control"
              name="declined"
              id="branches-declined"
              data-cy="declined"
              :class="{ valid: !v$.declined.$invalid, invalid: v$.declined.$invalid }"
              v-model.number="v$.declined.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.branches.declineddon')"
              for="branches-declineddon"
            ></label>
            <input
              type="text"
              class="form-control"
              name="declineddon"
              id="branches-declineddon"
              data-cy="declineddon"
              :class="{ valid: !v$.declineddon.$invalid, invalid: v$.declineddon.$invalid }"
              v-model="v$.declineddon.$model"
            />
            <div v-if="v$.declineddon.$anyDirty && v$.declineddon.$invalid">
              <small class="form-text text-danger" v-for="error of v$.declineddon.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.declinedby')" for="branches-declinedby"></label>
            <input
              type="text"
              class="form-control"
              name="declinedby"
              id="branches-declinedby"
              data-cy="declinedby"
              :class="{ valid: !v$.declinedby.$invalid, invalid: v$.declinedby.$invalid }"
              v-model="v$.declinedby.$model"
            />
            <div v-if="v$.declinedby.$anyDirty && v$.declinedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.declinedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.sessionid')" for="branches-sessionid"></label>
            <input
              type="text"
              class="form-control"
              name="sessionid"
              id="branches-sessionid"
              data-cy="sessionid"
              :class="{ valid: !v$.sessionid.$invalid, invalid: v$.sessionid.$invalid }"
              v-model="v$.sessionid.$model"
            />
            <div v-if="v$.sessionid.$anyDirty && v$.sessionid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.sessionid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.reworked')" for="branches-reworked"></label>
            <input
              type="number"
              class="form-control"
              name="reworked"
              id="branches-reworked"
              data-cy="reworked"
              :class="{ valid: !v$.reworked.$invalid, invalid: v$.reworked.$invalid }"
              v-model.number="v$.reworked.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.reworkedby')" for="branches-reworkedby"></label>
            <input
              type="text"
              class="form-control"
              name="reworkedby"
              id="branches-reworkedby"
              data-cy="reworkedby"
              :class="{ valid: !v$.reworkedby.$invalid, invalid: v$.reworkedby.$invalid }"
              v-model="v$.reworkedby.$model"
            />
            <div v-if="v$.reworkedby.$anyDirty && v$.reworkedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.reworkedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.branches.reworkedon')" for="branches-reworkedon"></label>
            <div class="d-flex">
              <input
                id="branches-reworkedon"
                data-cy="reworkedon"
                type="datetime-local"
                class="form-control"
                name="reworkedon"
                :class="{ valid: !v$.reworkedon.$invalid, invalid: v$.reworkedon.$invalid }"
                :value="convertDateTimeFromServer(v$.reworkedon.$model)"
                @change="updateInstantField('reworkedon', $event)"
              />
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
