<template>
  <div class="customer-details-container">
    <!-- Header Section -->
    <div class="row mb-4">
      <div class="col-12">
        <div class="d-flex justify-content-between align-items-center">
          <h2 class="entity-heading"><span v-text="t$('bbMobileBankingAdminApp.customer.detail.title')"></span> {{ customer.id }}</h2>
          <div>
            <button @click="previousState()" class="btn btn-outline-secondary">
              <font-awesome-icon icon="arrow-left" class="mr-1" />
              <span v-text="t$('entity.action.back')"></span>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Main Content - Grouped in Cards -->
    <div class="row">
      <!-- Personal Information Card -->
      <div class="col-lg-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-header bg-light-primary">
            <h5 class="mb-0">
              <font-awesome-icon icon="user" class="mr-2" />
              Personal Information
            </h5>
          </div>
          <div class="card-body">
            <dl class="entity-details-grid">
              <!-- Group 1: Personal Details -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.customername')"></span>
                </dt>
                <dd class="text-truncate">
                  <span>{{ customer.customername }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"></span>
                </dt>
                <dd>
                  <span>{{ customer.emailaddress }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.dob')"></span>
                </dt>
                <dd>
                  <span v-if="customer.dob">{{ formatDateLong(customer.dob) }}</span>
                </dd>
              </div>

              <!-- Group 2: Identification -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.idtype')"></span>
                </dt>
                <dd>
                  <span>{{ customer.idtype }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.idnumber')"></span>
                </dt>
                <dd>
                  <span>{{ customer.idnumber }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.gender')"></span>
                </dt>
                <dd>
                  <span>{{ customer.gender }}</span>
                </dd>
              </div>
            </dl>
          </div>
        </div>
      </div>

      <!-- Account Information Card -->
      <div class="col-lg-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-header bg-light-info">
            <h5 class="mb-0">
              <font-awesome-icon icon="credit-card" class="mr-2" />
              Account Details
            </h5>
          </div>
          <div class="card-body">
            <dl class="entity-details-grid">
              <!-- Group 1: Account Numbers -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.cardnumber')"></span>
                </dt>
                <dd>
                  <span class="monospace">{{ customer.cardnumber }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"></span>
                </dt>
                <dd>
                  <span class="monospace">{{ customer.accountnumber }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.cif')"></span>
                </dt>
                <dd>
                  <span>{{ customer.cif }}</span>
                </dd>
              </div>

              <!-- Group 2: Registration -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.registered')"></span>
                </dt>
                <dd>
                  <status-badge :value="customer.registered" />
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.regdate')"></span>
                </dt>
                <dd>
                  <span v-if="customer.regdate">{{ formatDateLong(customer.regdate) }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.active')"></span>
                </dt>
                <dd>
                  <status-badge :value="customer.active" type="success" />
                </dd>
              </div>
            </dl>
          </div>
        </div>
      </div>

      <!-- Security & Status Card -->
      <div class="col-lg-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-header bg-light-warning">
            <h5 class="mb-0">
              <font-awesome-icon icon="shield-alt" class="mr-2" />
              Security & Status
            </h5>
          </div>
          <div class="card-body">
            <dl class="entity-details-grid">
              <!-- Group 1: Security -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.pin')"></span>
                </dt>
                <dd>
                  <span class="text-muted">••••</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')"></span>
                </dt>
                <dd>
                  <span>{{ customer.lastlogin }}</span>
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.trials')"></span>
                </dt>
                <dd>
                  <span>{{ customer.trials }}</span>
                </dd>
              </div>

              <!-- Group 2: Status Flags -->
              <div class="detail-group">
                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.deactivated')"></span>
                </dt>
                <dd>
                  <status-badge :value="customer.deactivated" type="danger" />
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.declined')"></span>
                </dt>
                <dd>
                  <status-badge :value="customer.declined" type="danger" />
                </dd>

                <dt>
                  <span v-text="t$('bbMobileBankingAdminApp.customer.approved')"></span>
                </dt>
                <dd>
                  <status-badge :value="customer.approved" type="success" />
                </dd>
              </div>
            </dl>
          </div>
        </div>
      </div>
    </div>

    <!-- Action Buttons -->
    <div class="row mt-4">
      <div class="col-12 d-flex justify-content-end">
        <router-link v-if="customer.id" :to="{ name: 'CustomerEdit', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary mr-2">
            <font-awesome-icon icon="pencil-alt" class="mr-1" />
            <span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>

        <button type="submit" class="btn btn-success mr-2">
          <font-awesome-icon icon="lock-open" class="mr-1" />
          <span>Unlock Account</span>
        </button>

        <button type="submit" class="btn btn-danger">
          <font-awesome-icon icon="ban" class="mr-1" />
          <span>Deactivate</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./customer-details.component.ts"></script>

<style scoped>
.customer-details-container {
  padding: 20px;
  background-color: #f8f9fa;
}

.entity-heading {
  font-weight: 600;
  color: #2c3e50;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.card {
  border-radius: 8px;
  border: none;
  transition: transform 0.2s;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  font-weight: 600;
  border-top-left-radius: 8px !important;
  border-top-right-radius: 8px !important;
}

.bg-light-primary {
  background-color: #e3f2fd;
  color: #0d47a1;
}

.bg-light-info {
  background-color: #e0f7fa;
  color: #006064;
}

.bg-light-warning {
  background-color: #fff8e1;
  color: #ff6f00;
}

.entity-details-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 15px;
}

.detail-group {
  background: white;
  padding: 15px;
  border-radius: 6px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}

dt {
  font-weight: 600;
  color: #7f8c8d;
  font-size: 0.85rem;
  margin-bottom: 4px;
}

dd {
  margin-bottom: 12px;
  padding-left: 10px;
  font-size: 0.95rem;
  color: #2c3e50;
  border-left: 3px solid #3498db;
}

.monospace {
  font-family: 'Courier New', monospace;
  letter-spacing: 0.5px;
}

.btn-outline-secondary {
  border-color: #bdc3c7;
}

.btn {
  transition: all 0.2s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
}

@media (max-width: 992px) {
  .entity-details-grid {
    grid-template-columns: 1fr;
  }
}
</style>
