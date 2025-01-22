<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.serviceManagement.home.createOrEditLabel"
          data-cy="ServiceManagementCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.serviceManagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="serviceManagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="serviceManagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.processingcode')"
              for="service-management-processingcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="processingcode"
              id="service-management-processingcode"
              data-cy="processingcode"
              :class="{ valid: !v$.processingcode.$invalid, invalid: v$.processingcode.$invalid }"
              v-model="v$.processingcode.$model"
            />
            <div v-if="v$.processingcode.$anyDirty && v$.processingcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.processingcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.active')"
              for="service-management-active"
            ></label>
            <input
              type="text"
              class="form-control"
              name="active"
              id="service-management-active"
              data-cy="active"
              :class="{ valid: !v$.active.$invalid, invalid: v$.active.$invalid }"
              v-model="v$.active.$model"
            />
            <div v-if="v$.active.$anyDirty && v$.active.$invalid">
              <small class="form-text text-danger" v-for="error of v$.active.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.createdby')"
              for="service-management-createdby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="createdby"
              id="service-management-createdby"
              data-cy="createdby"
              :class="{ valid: !v$.createdby.$invalid, invalid: v$.createdby.$invalid }"
              v-model="v$.createdby.$model"
            />
            <div v-if="v$.createdby.$anyDirty && v$.createdby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.datecreated')"
              for="service-management-datecreated"
            ></label>
            <div class="d-flex">
              <input
                id="service-management-datecreated"
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
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.approved')"
              for="service-management-approved"
            ></label>
            <input
              type="number"
              class="form-control"
              name="approved"
              id="service-management-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model.number="v$.approved.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.approvedby')"
              for="service-management-approvedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="service-management-approvedby"
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
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.approveddate')"
              for="service-management-approveddate"
            ></label>
            <div class="d-flex">
              <input
                id="service-management-approveddate"
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
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.adaptortype')"
              for="service-management-adaptortype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="adaptortype"
              id="service-management-adaptortype"
              data-cy="adaptortype"
              :class="{ valid: !v$.adaptortype.$invalid, invalid: v$.adaptortype.$invalid }"
              v-model="v$.adaptortype.$model"
            />
            <div v-if="v$.adaptortype.$anyDirty && v$.adaptortype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.adaptortype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.destination')"
              for="service-management-destination"
            ></label>
            <input
              type="text"
              class="form-control"
              name="destination"
              id="service-management-destination"
              data-cy="destination"
              :class="{ valid: !v$.destination.$invalid, invalid: v$.destination.$invalid }"
              v-model="v$.destination.$model"
            />
            <div v-if="v$.destination.$anyDirty && v$.destination.$invalid">
              <small class="form-text text-danger" v-for="error of v$.destination.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.thirdpartyresponse')"
              for="service-management-thirdpartyresponse"
            ></label>
            <input
              type="number"
              class="form-control"
              name="thirdpartyresponse"
              id="service-management-thirdpartyresponse"
              data-cy="thirdpartyresponse"
              :class="{ valid: !v$.thirdpartyresponse.$invalid, invalid: v$.thirdpartyresponse.$invalid }"
              v-model.number="v$.thirdpartyresponse.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.telco')"
              for="service-management-telco"
            ></label>
            <input
              type="text"
              class="form-control"
              name="telco"
              id="service-management-telco"
              data-cy="telco"
              :class="{ valid: !v$.telco.$invalid, invalid: v$.telco.$invalid }"
              v-model="v$.telco.$model"
            />
            <div v-if="v$.telco.$anyDirty && v$.telco.$invalid">
              <small class="form-text text-danger" v-for="error of v$.telco.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.description')"
              for="service-management-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="service-management-description"
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
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.remarks')"
              for="service-management-remarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="service-management-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.sessionid')"
              for="service-management-sessionid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="sessionid"
              id="service-management-sessionid"
              data-cy="sessionid"
              :class="{ valid: !v$.sessionid.$invalid, invalid: v$.sessionid.$invalid }"
              v-model="v$.sessionid.$model"
            />
            <div v-if="v$.sessionid.$anyDirty && v$.sessionid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.sessionid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.serviceManagement.reworkby')"
              for="service-management-reworkby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="reworkby"
              id="service-management-reworkby"
              data-cy="reworkby"
              :class="{ valid: !v$.reworkby.$invalid, invalid: v$.reworkby.$invalid }"
              v-model="v$.reworkby.$model"
            />
            <div v-if="v$.reworkby.$anyDirty && v$.reworkby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.reworkby.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./service-management-update.component.ts"></script>
