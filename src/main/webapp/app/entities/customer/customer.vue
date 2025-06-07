<template>
  <div class="customer-dashboard">
    <!-- Header with title and actions -->
    <div class="dashboard-header">
      <div class="header-content">
        <h1 class="dashboard-title">
          <font-awesome-icon icon="users" class="mr-2" />
          <span v-text="t$('bbMobileBankingAdminApp.customer.home.title')"></span>
        </h1>
        <div class="header-actions">
          <button class="btn btn-refresh" @click="handleSyncList" :disabled="isFetching">
            <font-awesome-icon icon="sync" :spin="isFetching" class="mr-1" />
            <span v-text="t$('bbMobileBankingAdminApp.customer.home.refreshListLabel')"></span>
          </button>
        </div>
      </div>
    </div>

    <!-- Search and Filter Section -->
    <div class="search-section mb-4">
      <div class="input-group">
        <span class="input-group-text">
          <font-awesome-icon icon="search" />
        </span>
        <input type="text" class="form-control search-input" :placeholder="t$('global.search.placeholder')" v-model="searchQuery" />
        <button class="btn btn-filter">
          <font-awesome-icon icon="filter" class="mr-1" />
          <span v-text="t$('global.menu.filters')"></span>
        </button>
      </div>
    </div>

    <!-- Status Summary Cards -->
    <div class="status-summary mb-4">
      <div class="row">
        <div class="col-md-3 col-6 mb-3">
          <div class="summary-card active">
            <div class="summary-icon">
              <font-awesome-icon icon="user-check" />
            </div>
            <div class="summary-content">
              <div class="summary-count">142</div>
              <div class="summary-label">Active Customers</div>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6 mb-3">
          <div class="summary-card registered">
            <div class="summary-icon">
              <font-awesome-icon icon="clipboard-check" />
            </div>
            <div class="summary-content">
              <div class="summary-count">85</div>
              <div class="summary-label">Registered Today</div>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6 mb-3">
          <div class="summary-card inactive">
            <div class="summary-icon">
              <font-awesome-icon icon="user-slash" />
            </div>
            <div class="summary-content">
              <div class="summary-count">23</div>
              <div class="summary-label">Inactive</div>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6 mb-3">
          <div class="summary-card pending">
            <div class="summary-icon">
              <font-awesome-icon icon="hourglass-half" />
            </div>
            <div class="summary-content">
              <div class="summary-count">17</div>
              <div class="summary-label">Pending Approval</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Customer Cards Grid -->
    <div v-if="!isFetching && customers && customers.length === 0" class="empty-state">
      <div class="empty-state-icon">
        <font-awesome-icon icon="user-friends" />
      </div>
      <h3 class="empty-state-title" v-text="t$('bbMobileBankingAdminApp.customer.home.notFound')"></h3>
      <p class="empty-state-text">No customers match your search criteria</p>
    </div>

    <div class="customer-grid" v-if="customers && customers.length > 0">
      <div class="row">
        <div class="col-12 col-md-6 col-lg-4 mb-4" v-for="customer in filteredCustomers" :key="customer.id">
          <div class="customer-card" :class="{ inactive: !customer.active }">
            <div class="card-header">
              <div class="customer-avatar">
                <font-awesome-icon icon="user-circle" />
              </div>
              <div class="customer-info">
                <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }" class="customer-name">
                  {{ customer.customername }}
                </router-link>
                <div class="customer-id">ID: {{ customer.id }}</div>
              </div>
              <div class="customer-status" :class="getStatusClass(customer)">
                {{ getStatusText(customer) }}
              </div>
            </div>

            <div class="card-body">
              <div class="customer-detail">
                <div class="detail-icon">
                  <font-awesome-icon icon="phone" />
                </div>
                <div class="detail-value">{{ customer.phonenumber }}</div>
              </div>

              <div class="customer-detail">
                <div class="detail-icon">
                  <font-awesome-icon icon="envelope" />
                </div>
                <div class="detail-value">{{ customer.emailaddress || 'N/A' }}</div>
              </div>

              <div class="customer-detail">
                <div class="detail-icon">
                  <font-awesome-icon icon="credit-card" />
                </div>
                <div class="detail-value">{{ customer.accountnumber }}</div>
              </div>

              <div class="row">
                <div class="col-6">
                  <div class="customer-detail">
                    <div class="detail-icon">
                      <font-awesome-icon icon="building" />
                    </div>
                    <div class="detail-value">{{ customer.branchcode }}</div>
                  </div>
                </div>
                <div class="col-6">
                  <div class="customer-detail">
                    <div class="detail-icon">
                      <font-awesome-icon icon="calendar-alt" />
                    </div>
                    <div class="detail-value">{{ formatDateShort(customer.regdate) }}</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="card-footer">
              <div class="btn-group">
                <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-view">
                    <font-awesome-icon icon="eye" />
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>

                <b-button @click="prepareRemove(customer)" variant="danger" class="btn btn-delete" v-b-modal.removeEntity>
                  <font-awesome-icon icon="trash-alt" />
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>

                <button class="btn btn-more">
                  <font-awesome-icon icon="ellipsis-h" />
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Pagination -->
    <div class="pagination-section mt-4" v-if="customers && customers.length > 12">
      <nav aria-label="Customer pagination">
        <ul class="pagination justify-content-center">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
          </li>
          <li class="page-item active"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
    </div>

    <!-- Delete Modal -->
    <b-modal ref="removeEntity" id="removeEntity" centered>
      <template #modal-title>
        <div class="modal-title">
          <font-awesome-icon icon="exclamation-triangle" class="text-warning mr-2" />
          <span v-text="t$('entity.delete.title')"></span>
        </div>
      </template>
      <div class="modal-body">
        <p class="delete-message">
          Are you sure you want to delete customer
          <strong>{{ removeId }}</strong
          >? This action cannot be undone.
        </p>
      </div>
      <template #modal-footer>
        <div class="modal-footer-btns">
          <button type="button" class="btn btn-cancel" v-text="t$('entity.action.cancel')" @click="closeDialog()"></button>
          <button type="button" class="btn btn-confirm-delete" @click="removeCustomer()">
            <font-awesome-icon icon="trash-alt" class="mr-1" />
            <span v-text="t$('entity.action.delete')"></span>
          </button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./customer.component.ts"></script>

<style scoped>
.customer-dashboard {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: 100vh;
}

.dashboard-header {
  background: linear-gradient(120deg, #1a3a6c, #2c5282);
  color: white;
  padding: 20px 0;
  border-radius: 10px;
  margin-bottom: 25px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 95%;
  margin: 0 auto;
}

.dashboard-title {
  font-weight: 600;
  font-size: 1.8rem;
  margin: 0;
}

.btn-refresh {
  background-color: rgba(255, 255, 255, 0.15);
  border: none;
  color: white;
  transition: all 0.3s;
  padding: 8px 16px;
  border-radius: 6px;
}

.btn-refresh:hover {
  background-color: rgba(255, 255, 255, 0.25);
  transform: translateY(-2px);
}

.btn-refresh:disabled {
  opacity: 0.7;
}

.search-section {
  max-width: 95%;
  margin: 0 auto;
}

.search-input {
  border-radius: 6px 0 0 6px;
  border: 1px solid #e2e8f0;
  padding: 12px 15px;
  transition: border-color 0.3s;
}

.search-input:focus {
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.2);
  outline: none;
}

.btn-filter {
  background-color: #2b6cb0;
  color: white;
  border-radius: 0 6px 6px 0;
  padding: 12px 20px;
  transition: background-color 0.3s;
}

.btn-filter:hover {
  background-color: #2c5282;
}

.status-summary {
  max-width: 95%;
  margin: 0 auto;
}

.summary-card {
  display: flex;
  align-items: center;
  padding: 15px;
  border-radius: 8px;
  color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  height: 100%;
}

.summary-card.active {
  background: linear-gradient(135deg, #0ea5e9, #0284c7);
}
.summary-card.registered {
  background: linear-gradient(135deg, #10b981, #059669);
}
.summary-card.inactive {
  background: linear-gradient(135deg, #64748b, #475569);
}
.summary-card.pending {
  background: linear-gradient(135deg, #f59e0b, #d97706);
}

.summary-icon {
  font-size: 2rem;
  margin-right: 15px;
  opacity: 0.9;
}

.summary-count {
  font-size: 1.8rem;
  font-weight: 700;
  line-height: 1;
}

.summary-label {
  font-size: 0.9rem;
  opacity: 0.9;
}

.empty-state {
  text-align: center;
  padding: 50px 20px;
  max-width: 600px;
  margin: 0 auto;
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.empty-state-icon {
  font-size: 4rem;
  color: #cbd5e0;
  margin-bottom: 20px;
}

.empty-state-title {
  color: #4a5568;
  margin-bottom: 10px;
}

.empty-state-text {
  color: #718096;
  margin-bottom: 20px;
}

.customer-grid {
  max-width: 95%;
  margin: 0 auto;
}

.customer-card {
  background: white;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.customer-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
}

.customer-card.inactive {
  opacity: 0.85;
  border-left: 4px solid #e53e3e;
}

.card-header {
  display: flex;
  padding: 15px;
  background-color: #f8fafc;
  border-bottom: 1px solid #edf2f7;
}

.customer-avatar {
  width: 50px;
  height: 50px;
  background: linear-gradient(135deg, #ebf4ff, #c3dafe);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  color: #3b82f6;
  flex-shrink: 0;
  margin-right: 15px;
}

.customer-info {
  flex-grow: 1;
  overflow: hidden;
}

.customer-name {
  font-weight: 600;
  font-size: 1.1rem;
  color: #2d3748;
  display: block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.customer-name:hover {
  color: #2b6cb0;
  text-decoration: none;
}

.customer-id {
  font-size: 0.85rem;
  color: #718096;
}

.customer-status {
  align-self: flex-start;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.8rem;
  font-weight: 600;
  margin-left: 10px;
  flex-shrink: 0;
}

.status-active {
  background-color: #c6f6d5;
  color: #065f46;
}
.status-inactive {
  background-color: #fed7d7;
  color: #975a16;
}
.status-pending {
  background-color: #feebc8;
  color: #c05621;
}

.card-body {
  padding: 15px;
  flex-grow: 1;
}

.customer-detail {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
}

.detail-icon {
  width: 30px;
  color: #718096;
  font-size: 0.9rem;
  flex-shrink: 0;
}

.detail-value {
  flex-grow: 1;
  font-size: 0.95rem;
  color: #4a5568;
  word-break: break-word;
}

.card-footer {
  padding: 15px;
  background-color: #f8fafc;
  border-top: 1px solid #edf2f7;
}

.btn-group {
  display: flex;
  width: 100%;
}

.btn-view,
.btn-delete,
.btn-more {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 8px 5px;
  border-radius: 6px;
  font-size: 0.9rem;
  transition: all 0.2s;
  border: none;
}

.btn-view {
  background-color: #ebf8ff;
  color: #3182ce;
}

.btn-view:hover {
  background-color: #bee3f8;
}

.btn-delete {
  background-color: #fff5f5;
  color: #e53e3e;
  margin: 0 5px;
}

.btn-delete:hover {
  background-color: #fed7d7;
}

.btn-more {
  background-color: #f7fafc;
  color: #718096;
}

.btn-more:hover {
  background-color: #edf2f7;
}

/* Modal Styles */
.modal-title {
  display: flex;
  align-items: center;
  font-weight: 600;
}

.delete-message {
  font-size: 1.05rem;
  color: #4a5568;
  margin-bottom: 0;
}

.modal-footer-btns {
  display: flex;
  justify-content: flex-end;
  width: 100%;
}

.btn-cancel {
  background-color: #edf2f7;
  color: #4a5568;
  padding: 8px 16px;
  border-radius: 6px;
  border: none;
  margin-right: 10px;
  transition: background-color 0.2s;
}

.btn-cancel:hover {
  background-color: #e2e8f0;
}

.btn-confirm-delete {
  background: linear-gradient(135deg, #e53e3e, #c53030);
  color: white;
  padding: 8px 16px;
  border-radius: 6px;
  border: none;
  transition: all 0.2s;
}

.btn-confirm-delete:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Pagination */
.pagination-section {
  max-width: 95%;
  margin: 0 auto;
}

.page-link {
  color: #2b6cb0;
  border: 1px solid #e2e8f0;
  margin: 0 4px;
  border-radius: 6px !important;
  transition: all 0.2s;
}

.page-link:hover {
  background-color: #ebf8ff;
  border-color: #bee3f8;
}

.page-item.active .page-link {
  background: linear-gradient(135deg, #0ea5e9, #0284c7);
  border-color: #0284c7;
}
</style>
