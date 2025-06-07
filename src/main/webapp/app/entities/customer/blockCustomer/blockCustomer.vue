<template>
  <div class="table-responsive">
    <h2>Customer List</h2>
    <table border="1" cellpadding="8" cellspacing="0">
      <thead>
        <tr>
          <th scope="row"><span v-text="t$('global.field.id')"></span></th>
          <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"></span></th>
          <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"></span></th>
          <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.customername')"></span></th>
          <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.channel')"></span></th>
          <th scope="row"><span v-text="t$('bbMobileBankingAdminApp.customer.customerstatus')"></span></th>
          <th scope="row">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(customer, index) in customers" :key="customer.id" data-cy="entityTable">
          <td>
            <router-link :to="{ name: 'CustomerView', params: { customerId: customer.id } }">{{ customer.id }}</router-link>
          </td>
          <td>{{ customer.emailaddress }}</td>
          <td>{{ customer.accountnumber }}</td>
          <td>{{ customer.customername }}</td>
          <td>{{ customer.channel }}</td>
          <td>{{ customer.customerstatus === '1' ? 'Blocked' : 'Active' }}</td>
          <td>
            <button @click="openBlockModal(customer)" class="btn btn-warning" :disabled="customer.blocked === 1">Block Profile</button>
            <button @click="openViewModal(customer)" class="btn btn-info">View</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Block Customer Modal -->
    <div class="modal fade" id="blockCustomerModal" tabindex="-1" aria-labelledby="blockCustomerModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="blockCustomerModalLabel">Confirm Block Customer</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>
              Are you sure you want to block the customer <strong>{{ selectedCustomer?.customername }}</strong> (ID:
              {{ selectedCustomer?.id }})?
            </p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-warning" @click="confirmBlockCustomer">Confirm Block</button>
          </div>
        </div>
      </div>
    </div>

    <!-- View Customer Modal -->
    <div class="modal fade" id="viewCustomerModal" tabindex="-1" aria-labelledby="viewCustomerModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="viewCustomerModalLabel">Customer Profile</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div v-if="selectedCustomer">
              <p><strong>ID:</strong> {{ selectedCustomer.id }}</p>
              <p><strong>Name:</strong> {{ selectedCustomer.customername }}</p>
              <p><strong>Email:</strong> {{ selectedCustomer.emailaddress }}</p>
              <p><strong>Account Number:</strong> {{ selectedCustomer.accountnumber }}</p>
              <p><strong>Channel:</strong> {{ selectedCustomer.channel }}</p>
              <p><strong>Status:</strong> {{ selectedCustomer.customerstatus === 1 ? 'Blocked' : 'Active' }}</p>
            </div>
            <div v-else>
              <p>Loading customer details...</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.table-responsive {
  width: 100%;
  overflow-x: auto;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th {
  background-color: #f4f4f4;
  text-align: left;
}
td,
th {
  padding: 8px;
  border: 1px solid #ddd;
}
.btn {
  padding: 5px 10px;
  margin-right: 5px;
  border: none;
  cursor: pointer;
}
.btn-warning {
  background-color: #ffc107;
}
.btn-info {
  background-color: #17a2b8;
  color: white;
}
</style>
<script lang="ts" src="../customer.component.ts"></script>
