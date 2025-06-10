<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white p-3">
          <h2
            class="mb-0"
            id="bbMobileBankingAdminApp.customer.home.createOrEditLabel"
            data-cy="CustomerCreateUpdateHeading"
            v-text="t$('bbMobileBankingAdminApp.customer.home.createOrEditLabel')"
          ></h2>
        </div>

        <div class="card-body">
          <form name="editForm" novalidate @submit.prevent="save()" class="customer-form">
            <!-- Form Sections with Collapsible Panels -->
            <div class="accordion mb-4" id="customerFormSections">
              <!-- Basic Information Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingBasic">
                  <button
                    class="accordion-button"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseBasic"
                    aria-expanded="true"
                    aria-controls="collapseBasic"
                  >
                    <i class="fas fa-user me-2"></i> Basic Information
                  </button>
                </h2>
                <div
                  id="collapseBasic"
                  class="accordion-collapse collapse show"
                  aria-labelledby="headingBasic"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <div class="form-group col-md-6" v-if="customer.id">
                        <label for="id" v-text="t$('global.field.id')"></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                          <input type="text" class="form-control" id="id" name="id" v-model="customer.id" readonly />
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.customername')"
                          for="customer-customername"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-signature"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="customername"
                            id="customer-customername"
                            data-cy="customername"
                            :class="{ 'is-valid': !v$.customername.$invalid, 'is-invalid': v$.customername.$invalid }"
                            v-model="v$.customername.$model"
                          />
                        </div>
                        <div v-if="v$.customername.$anyDirty && v$.customername.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.customername.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Add other basic fields here following the same pattern -->
                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.phonenumber')"
                          for="customer-phonenumber"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-phone"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="phonenumber"
                            id="customer-phonenumber"
                            data-cy="phonenumber"
                            :class="{ 'is-valid': !v$.phonenumber.$invalid, 'is-invalid': v$.phonenumber.$invalid }"
                            v-model="v$.phonenumber.$model"
                            required
                          />
                        </div>
                        <div v-if="v$.phonenumber.$anyDirty && v$.phonenumber.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.phonenumber.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"
                          for="customer-emailaddress"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="emailaddress"
                            id="customer-emailaddress"
                            data-cy="emailaddress"
                            :class="{ 'is-valid': !v$.emailaddress.$invalid, 'is-invalid': v$.emailaddress.$invalid }"
                            v-model="v$.emailaddress.$model"
                          />
                        </div>
                        <div v-if="v$.emailaddress.$anyDirty && v$.emailaddress.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.emailaddress.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.gender')"
                          for="customer-gender"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-venus-mars"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="gender"
                            id="customer-gender"
                            data-cy="gender"
                            :class="{ 'is-valid': !v$.gender.$invalid, 'is-invalid': v$.gender.$invalid }"
                            v-model="v$.gender.$model"
                          />
                        </div>
                        <div v-if="v$.gender.$anyDirty && v$.gender.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.gender.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Account Details Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingAccount">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseAccount"
                    aria-expanded="false"
                    aria-controls="collapseAccount"
                  >
                    <i class="fas fa-wallet me-2"></i> Account Details
                  </button>
                </h2>
                <div
                  id="collapseAccount"
                  class="accordion-collapse collapse"
                  aria-labelledby="headingAccount"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"
                          for="customer-accountnumber"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-hashtag"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="accountnumber"
                            id="customer-accountnumber"
                            data-cy="accountnumber"
                            :class="{ 'is-valid': !v$.accountnumber.$invalid, 'is-invalid': v$.accountnumber.$invalid }"
                            v-model="v$.accountnumber.$model"
                            required
                          />
                        </div>
                        <div v-if="v$.accountnumber.$anyDirty && v$.accountnumber.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.accountnumber.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.cardnumber')"
                          for="customer-cardnumber"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-credit-card"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="cardnumber"
                            id="customer-cardnumber"
                            data-cy="cardnumber"
                            :class="{ 'is-valid': !v$.cardnumber.$invalid, 'is-invalid': v$.cardnumber.$invalid }"
                            v-model="v$.cardnumber.$model"
                          />
                        </div>
                        <div v-if="v$.cardnumber.$anyDirty && v$.cardnumber.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.cardnumber.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Add other account fields here -->
                    </div>
                  </div>
                </div>
              </div>

              <!-- Security Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingSecurity">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseSecurity"
                    aria-expanded="false"
                    aria-controls="collapseSecurity"
                  >
                    <i class="fas fa-shield-alt me-2"></i> Security Settings
                  </button>
                </h2>
                <div
                  id="collapseSecurity"
                  class="accordion-collapse collapse"
                  aria-labelledby="headingSecurity"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <div class="form-group col-md-6">
                        <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.pin')" for="customer-pin"></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-lock"></i></span>
                          <input
                            type="password"
                            class="form-control"
                            name="pin"
                            id="customer-pin"
                            data-cy="pin"
                            :class="{ 'is-valid': !v$.pin.$invalid, 'is-invalid': v$.pin.$invalid }"
                            v-model="v$.pin.$model"
                          />
                        </div>
                        <div v-if="v$.pin.$anyDirty && v$.pin.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.pin.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.password')"
                          for="customer-password"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-key"></i></span>
                          <input
                            type="password"
                            class="form-control"
                            name="password"
                            id="customer-password"
                            data-cy="password"
                            :class="{ 'is-valid': !v$.password.$invalid, 'is-invalid': v$.password.$invalid }"
                            v-model="v$.password.$model"
                          />
                        </div>
                        <div v-if="v$.password.$anyDirty && v$.password.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.password.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Add other security fields here -->
                    </div>
                  </div>
                </div>
              </div>

              <!-- Status Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingStatus">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseStatus"
                    aria-expanded="false"
                    aria-controls="collapseStatus"
                  >
                    <i class="fas fa-info-circle me-2"></i> Status & Activity
                  </button>
                </h2>
                <div
                  id="collapseStatus"
                  class="accordion-collapse collapse"
                  aria-labelledby="headingStatus"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.active')"
                          for="customer-active"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-toggle-on"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="active"
                            id="customer-active"
                            data-cy="active"
                            :class="{ 'is-valid': !v$.active.$invalid, 'is-invalid': v$.active.$invalid }"
                            v-model="v$.active.$model"
                          />
                        </div>
                        <div v-if="v$.active.$anyDirty && v$.active.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.active.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')"
                          for="customer-lastlogin"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-clock"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="lastlogin"
                            id="customer-lastlogin"
                            data-cy="lastlogin"
                            :class="{ 'is-valid': !v$.lastlogin.$invalid, 'is-invalid': v$.lastlogin.$invalid }"
                            v-model="v$.lastlogin.$model"
                          />
                        </div>
                        <div v-if="v$.lastlogin.$anyDirty && v$.lastlogin.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.lastlogin.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Add other status fields here -->
                    </div>
                  </div>
                </div>
              </div>

              <!-- Address Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingAddress">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseAddress"
                    aria-expanded="false"
                    aria-controls="collapseAddress"
                  >
                    <i class="fas fa-map-marker-alt me-2"></i> Address & Contact
                  </button>
                </h2>
                <div
                  id="collapseAddress"
                  class="accordion-collapse collapse"
                  aria-labelledby="headingAddress"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.postaladdress')"
                          for="customer-postaladdress"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-map-pin"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="postaladdress"
                            id="customer-postaladdress"
                            data-cy="postaladdress"
                            :class="{ 'is-valid': !v$.postaladdress.$invalid, 'is-invalid': v$.postaladdress.$invalid }"
                            v-model="v$.postaladdress.$model"
                          />
                        </div>
                        <div v-if="v$.postaladdress.$anyDirty && v$.postaladdress.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.postaladdress.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.residence')"
                          for="customer-residence"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-home"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="residence"
                            id="customer-residence"
                            data-cy="residence"
                            :class="{ 'is-valid': !v$.residence.$invalid, 'is-invalid': v$.residence.$invalid }"
                            v-model="v$.residence.$model"
                          />
                        </div>
                        <div v-if="v$.residence.$anyDirty && v$.residence.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.residence.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Add other address fields here -->
                    </div>
                  </div>
                </div>
              </div>

              <!-- Additional Fields Section -->
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingAdditional">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#collapseAdditional"
                    aria-expanded="false"
                    aria-controls="collapseAdditional"
                  >
                    <i class="fas fa-plus-circle me-2"></i> Additional Information
                  </button>
                </h2>
                <div
                  id="collapseAdditional"
                  class="accordion-collapse collapse"
                  aria-labelledby="headingAdditional"
                  data-bs-parent="#customerFormSections"
                >
                  <div class="accordion-body">
                    <div class="row">
                      <!-- Add remaining fields here in two-column layout -->
                      <!-- Example field: -->
                      <div class="form-group col-md-6">
                        <label
                          class="form-control-label"
                          v-text="t$('bbMobileBankingAdminApp.customer.remarks')"
                          for="customer-remarks"
                        ></label>
                        <div class="input-group">
                          <span class="input-group-text"><i class="fas fa-comment"></i></span>
                          <input
                            type="text"
                            class="form-control"
                            name="remarks"
                            id="customer-remarks"
                            data-cy="remarks"
                            :class="{ 'is-valid': !v$.remarks.$invalid, 'is-invalid': v$.remarks.$invalid }"
                            v-model="v$.remarks.$model"
                          />
                        </div>
                        <div v-if="v$.remarks.$anyDirty && v$.remarks.$invalid" class="mt-1">
                          <small class="form-text text-danger" v-for="error of v$.remarks.$errors" :key="error.$uid">{{
                            error.$message
                          }}</small>
                        </div>
                      </div>

                      <!-- Continue adding other fields in this pattern -->
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Action Buttons -->
            <div class="d-flex justify-content-between mt-4">
              <button
                type="button"
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                class="btn btn-outline-secondary"
                @click="previousState()"
              >
                <i class="fas fa-ban me-1"></i>
                <span v-text="t$('entity.action.cancel')"></span>
              </button>
              <button
                type="submit"
                id="save-entity"
                data-cy="entityCreateSaveButton"
                :disabled="v$.$invalid || isSaving"
                class="btn btn-primary"
              >
                <i class="fas fa-save me-1"></i>
                <span v-text="t$('entity.action.save')"></span>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./customer-update.component.ts"></script>

<style scoped>
.customer-form {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
}

.card {
  border: none;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.card-header {
  border-radius: 8px 8px 0 0 !important;
}

.accordion-button {
  font-weight: 600;
  padding: 1rem 1.25rem;
}

.accordion-button:not(.collapsed) {
  background-color: #e9f7fe;
  color: #0d6efd;
}

.accordion-body {
  padding: 1.5rem;
}

.form-group {
  margin-bottom: 1.25rem;
}

.form-control-label {
  font-weight: 500;
  margin-bottom: 0.5rem;
  color: #495057;
}

.input-group-text {
  background-color: #e9ecef;
  border-right: none;
}

.form-control {
  border-left: none;
  padding-left: 0;
}

.form-control:focus {
  box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
}

.is-valid {
  border-color: #198754;
}

.is-invalid {
  border-color: #dc3545;
}

.text-danger {
  font-size: 0.875rem;
}

.btn {
  padding: 0.5rem 1.5rem;
  font-weight: 500;
}

.btn-outline-secondary:hover {
  background-color: #6c757d;
  color: white;
}

.btn-primary {
  background-color: #0d6efd;
  border-color: #0d6efd;
}

.btn-primary:hover {
  background-color: #0b5ed7;
  border-color: #0a58ca;
}

.btn:disabled {
  opacity: 0.65;
}
</style>
