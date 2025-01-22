<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.billers.home.createOrEditLabel"
          data-cy="BillersCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.billers.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="billers.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="billers.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.billerid')" for="billers-billerid"></label>
            <input
              type="text"
              class="form-control"
              name="billerid"
              id="billers-billerid"
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
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.description')" for="billers-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="billers-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
              required
            />
            <div v-if="v$.description.$anyDirty && v$.description.$invalid">
              <small class="form-text text-danger" v-for="error of v$.description.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.billers.billercollectionaccount')"
              for="billers-billercollectionaccount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="billercollectionaccount"
              id="billers-billercollectionaccount"
              data-cy="billercollectionaccount"
              :class="{ valid: !v$.billercollectionaccount.$invalid, invalid: v$.billercollectionaccount.$invalid }"
              v-model="v$.billercollectionaccount.$model"
            />
            <div v-if="v$.billercollectionaccount.$anyDirty && v$.billercollectionaccount.$invalid">
              <small class="form-text text-danger" v-for="error of v$.billercollectionaccount.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.datecreated')" for="billers-datecreated"></label>
            <div class="d-flex">
              <input
                id="billers-datecreated"
                data-cy="datecreated"
                type="datetime-local"
                class="form-control"
                name="datecreated"
                :class="{ valid: !v$.datecreated.$invalid, invalid: v$.datecreated.$invalid }"
                :value="convertDateTimeFromServer(v$.datecreated.$model)"
                @change="updateInstantField('datecreated', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.createdby')" for="billers-createdby"></label>
            <input
              type="text"
              class="form-control"
              name="createdby"
              id="billers-createdby"
              data-cy="createdby"
              :class="{ valid: !v$.createdby.$invalid, invalid: v$.createdby.$invalid }"
              v-model="v$.createdby.$model"
            />
            <div v-if="v$.createdby.$anyDirty && v$.createdby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.approved')" for="billers-approved"></label>
            <input
              type="number"
              class="form-control"
              name="approved"
              id="billers-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model.number="v$.approved.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.approvedby')" for="billers-approvedby"></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="billers-approvedby"
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
              v-text="t$('bbMobileBankingAdminApp.billers.approveddate')"
              for="billers-approveddate"
            ></label>
            <div class="d-flex">
              <input
                id="billers-approveddate"
                data-cy="approveddate"
                type="datetime-local"
                class="form-control"
                name="approveddate"
                :class="{ valid: !v$.approveddate.$invalid, invalid: v$.approveddate.$invalid }"
                :value="convertDateTimeFromServer(v$.approveddate.$model)"
                @change="updateInstantField('approveddate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.billers.chargableproductid')"
              for="billers-chargableproductid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="chargableproductid"
              id="billers-chargableproductid"
              data-cy="chargableproductid"
              :class="{ valid: !v$.chargableproductid.$invalid, invalid: v$.chargableproductid.$invalid }"
              v-model="v$.chargableproductid.$model"
            />
            <div v-if="v$.chargableproductid.$anyDirty && v$.chargableproductid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.chargableproductid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.billers.nonchargableproductid')"
              for="billers-nonchargableproductid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="nonchargableproductid"
              id="billers-nonchargableproductid"
              data-cy="nonchargableproductid"
              :class="{ valid: !v$.nonchargableproductid.$invalid, invalid: v$.nonchargableproductid.$invalid }"
              v-model="v$.nonchargableproductid.$model"
            />
            <div v-if="v$.nonchargableproductid.$anyDirty && v$.nonchargableproductid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.nonchargableproductid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.billers.usdbillercollectionaccount')"
              for="billers-usdbillercollectionaccount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="usdbillercollectionaccount"
              id="billers-usdbillercollectionaccount"
              data-cy="usdbillercollectionaccount"
              :class="{ valid: !v$.usdbillercollectionaccount.$invalid, invalid: v$.usdbillercollectionaccount.$invalid }"
              v-model="v$.usdbillercollectionaccount.$model"
            />
            <div v-if="v$.usdbillercollectionaccount.$anyDirty && v$.usdbillercollectionaccount.$invalid">
              <small class="form-text text-danger" v-for="error of v$.usdbillercollectionaccount.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.billers.enableduplicatecheck')"
              for="billers-enableduplicatecheck"
            ></label>
            <input
              type="number"
              class="form-control"
              name="enableduplicatecheck"
              id="billers-enableduplicatecheck"
              data-cy="enableduplicatecheck"
              :class="{ valid: !v$.enableduplicatecheck.$invalid, invalid: v$.enableduplicatecheck.$invalid }"
              v-model.number="v$.enableduplicatecheck.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.remarks')" for="billers-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="billers-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
            <div v-if="v$.remarks.$anyDirty && v$.remarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.remarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.sessionid')" for="billers-sessionid"></label>
            <input
              type="text"
              class="form-control"
              name="sessionid"
              id="billers-sessionid"
              data-cy="sessionid"
              :class="{ valid: !v$.sessionid.$invalid, invalid: v$.sessionid.$invalid }"
              v-model="v$.sessionid.$model"
            />
            <div v-if="v$.sessionid.$anyDirty && v$.sessionid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.sessionid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.reworkby')" for="billers-reworkby"></label>
            <input
              type="text"
              class="form-control"
              name="reworkby"
              id="billers-reworkby"
              data-cy="reworkby"
              :class="{ valid: !v$.reworkby.$invalid, invalid: v$.reworkby.$invalid }"
              v-model="v$.reworkby.$model"
            />
            <div v-if="v$.reworkby.$anyDirty && v$.reworkby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.reworkby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.status')" for="billers-status"></label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="billers-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.active')" for="billers-active"></label>
            <input
              type="number"
              class="form-control"
              name="active"
              id="billers-active"
              data-cy="active"
              :class="{ valid: !v$.active.$invalid, invalid: v$.active.$invalid }"
              v-model.number="v$.active.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.billers.rework')" for="billers-rework"></label>
            <input
              type="number"
              class="form-control"
              name="rework"
              id="billers-rework"
              data-cy="rework"
              :class="{ valid: !v$.rework.$invalid, invalid: v$.rework.$invalid }"
              v-model.number="v$.rework.$model"
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
<script lang="ts" src="./billers-update.component.ts"></script>
