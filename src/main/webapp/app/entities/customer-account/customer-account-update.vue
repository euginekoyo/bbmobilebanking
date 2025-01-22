<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.customerAccount.home.createOrEditLabel"
          data-cy="CustomerAccountCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.customerAccount.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="customerAccount.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="customerAccount.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.customerid')"
              for="customer-account-customerid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="customerid"
              id="customer-account-customerid"
              data-cy="customerid"
              :class="{ valid: !v$.customerid.$invalid, invalid: v$.customerid.$invalid }"
              v-model.number="v$.customerid.$model"
              required
            />
            <div v-if="v$.customerid.$anyDirty && v$.customerid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.customerid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.accountnumber')"
              for="customer-account-accountnumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="accountnumber"
              id="customer-account-accountnumber"
              data-cy="accountnumber"
              :class="{ valid: !v$.accountnumber.$invalid, invalid: v$.accountnumber.$invalid }"
              v-model="v$.accountnumber.$model"
              required
            />
            <div v-if="v$.accountnumber.$anyDirty && v$.accountnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.accountnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.accountclass')"
              for="customer-account-accountclass"
            ></label>
            <input
              type="text"
              class="form-control"
              name="accountclass"
              id="customer-account-accountclass"
              data-cy="accountclass"
              :class="{ valid: !v$.accountclass.$invalid, invalid: v$.accountclass.$invalid }"
              v-model="v$.accountclass.$model"
            />
            <div v-if="v$.accountclass.$anyDirty && v$.accountclass.$invalid">
              <small class="form-text text-danger" v-for="error of v$.accountclass.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.customernumber')"
              for="customer-account-customernumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customernumber"
              id="customer-account-customernumber"
              data-cy="customernumber"
              :class="{ valid: !v$.customernumber.$invalid, invalid: v$.customernumber.$invalid }"
              v-model="v$.customernumber.$model"
            />
            <div v-if="v$.customernumber.$anyDirty && v$.customernumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.customernumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customerAccount.cif')" for="customer-account-cif"></label>
            <input
              type="text"
              class="form-control"
              name="cif"
              id="customer-account-cif"
              data-cy="cif"
              :class="{ valid: !v$.cif.$invalid, invalid: v$.cif.$invalid }"
              v-model="v$.cif.$model"
              required
            />
            <div v-if="v$.cif.$anyDirty && v$.cif.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cif.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.timelinked')"
              for="customer-account-timelinked"
            ></label>
            <div class="d-flex">
              <input
                id="customer-account-timelinked"
                data-cy="timelinked"
                type="datetime-local"
                class="form-control"
                name="timelinked"
                :class="{ valid: !v$.timelinked.$invalid, invalid: v$.timelinked.$invalid }"
                :value="convertDateTimeFromServer(v$.timelinked.$model)"
                @change="updateInstantField('timelinked', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.blocked')"
              for="customer-account-blocked"
            ></label>
            <input
              type="number"
              class="form-control"
              name="blocked"
              id="customer-account-blocked"
              data-cy="blocked"
              :class="{ valid: !v$.blocked.$invalid, invalid: v$.blocked.$invalid }"
              v-model.number="v$.blocked.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.stopped')"
              for="customer-account-stopped"
            ></label>
            <input
              type="number"
              class="form-control"
              name="stopped"
              id="customer-account-stopped"
              data-cy="stopped"
              :class="{ valid: !v$.stopped.$invalid, invalid: v$.stopped.$invalid }"
              v-model.number="v$.stopped.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customerAccount.dormant')"
              for="customer-account-dormant"
            ></label>
            <input
              type="number"
              class="form-control"
              name="dormant"
              id="customer-account-dormant"
              data-cy="dormant"
              :class="{ valid: !v$.dormant.$invalid, invalid: v$.dormant.$invalid }"
              v-model.number="v$.dormant.$model"
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
<script lang="ts" src="./customer-account-update.component.ts"></script>
