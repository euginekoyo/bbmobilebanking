<template>
  <aside :class="`${is_expanded ? 'is-expanded' : ''}`">
    <div class="menu-toggle-wrap">
      <button class="menu-toggle" @click="ToggleMenu" aria-label="Toggle menu">
        <span class="material-icons">keyboard_double_arrow_right</span>
      </button>
    </div>

    <h3>Menu</h3>
    <div class="menu">
      <router-link to="/" class="button">
        <span class="material-icons">account_circle</span>
        <span class="text">{{ t$('global.menu.accountMod') }}</span>
      </router-link>

      <!-- Customer Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button class="button menu-parent" @click="toggleSubmenu('customer')" :class="{ 'is-active': activeSubmenu === 'customer' }">
          <span class="material-icons">person</span>
          <span class="text">{{ t$('global.menu.customer') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'customer' }">
          <router-link to="customer" class="submenu-item">
            <span class="material-icons">visibility</span>
            <span class="text">View Customer Details</span>
          </router-link>
          <a href="#" class="submenu-item">
            <span class="material-icons">verified_user</span>
            <span class="text">Complete KYC</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">refresh</span>
            <span class="text">Customer Resets</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">block</span>
            <span class="text">Block Customer</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">lock_open</span>
            <span class="text">Unblock Customer</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">check_circle</span>
            <span class="text">Approve Unblock Customer</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">close</span>
            <span class="text">Close Customer Profile</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">done_all</span>
            <span class="text">Approve Close Profile</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">error</span>
            <span class="text">Failed Registration</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">devices</span>
            <span class="text">Approve Customer Device</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">info</span>
            <span class="text">Customer Details</span>
          </a>
        </div>
      </div>

      <!-- Account Opening Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('account-opening')"
          :class="{ 'is-active': activeSubmenu === 'account-opening' }"
        >
          <span class="material-icons">account_balance</span>
          <span class="text">{{ t$('global.menu.accountOpening') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'account-opening' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">add_circle</span>
            <span class="text">New Account</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">pending</span>
            <span class="text">Pending Applications</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">check_circle</span>
            <span class="text">Approved Accounts</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">cancel</span>
            <span class="text">Rejected Applications</span>
          </a>
        </div>
      </div>

      <!-- Agency Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button class="button menu-parent" @click="toggleSubmenu('agency')" :class="{ 'is-active': activeSubmenu === 'agency' }">
          <span class="material-icons">business</span>
          <span class="text">{{ t$('global.menu.agency') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'agency' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">store</span>
            <span class="text">View Agencies</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">add_business</span>
            <span class="text">Add New Agency</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">location_on</span>
            <span class="text">Agency Locations</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">assessment</span>
            <span class="text">Performance Reports</span>
          </a>
        </div>
      </div>

      <!-- Agency Management Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('agency-management')"
          :class="{ 'is-active': activeSubmenu === 'agency-management' }"
        >
          <span class="material-icons">manage_accounts</span>
          <span class="text">{{ t$('global.menu.agencyMgnt') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'agency-management' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">supervisor_account</span>
            <span class="text">Agency Staff</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">Agency Settings</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">monetization_on</span>
            <span class="text">Commission Management</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">schedule</span>
            <span class="text">Working Hours</span>
          </a>
        </div>
      </div>

      <!-- Registration Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('registration')"
          :class="{ 'is-active': activeSubmenu === 'registration' }"
        >
          <span class="material-icons">how_to_reg</span>
          <span class="text">{{ t$('global.menu.registration') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'registration' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">person_add</span>
            <span class="text">New Registration</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">list_alt</span>
            <span class="text">Registration Queue</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">verified</span>
            <span class="text">Verified Registrations</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">history</span>
            <span class="text">Registration History</span>
          </a>
        </div>
      </div>

      <!-- Alerts Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button class="button menu-parent" @click="toggleSubmenu('alerts')" :class="{ 'is-active': activeSubmenu === 'alerts' }">
          <span class="material-icons">notifications</span>
          <span class="text">{{ t$('global.menu.alerts') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'alerts' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">notification_important</span>
            <span class="text">Active Alerts</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">add_alert</span>
            <span class="text">Create Alert</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">history</span>
            <span class="text">Alert History</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">tune</span>
            <span class="text">Alert Settings</span>
          </a>
        </div>
      </div>

      <!-- Bulk Operations Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button class="button menu-parent" @click="toggleSubmenu('bulk')" :class="{ 'is-active': activeSubmenu === 'bulk' }">
          <span class="material-icons">upload_file</span>
          <span class="text">{{ t$('global.menu.bulk') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'bulk' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">cloud_upload</span>
            <span class="text">Bulk Upload</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">download</span>
            <span class="text">Download Template</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">playlist_add_check</span>
            <span class="text">Validation Results</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">sync</span>
            <span class="text">Processing Status</span>
          </a>
        </div>
      </div>

      <!-- Institution Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button class="button menu-parent" @click="toggleSubmenu('institution')" :class="{ 'is-active': activeSubmenu === 'institution' }">
          <span class="material-icons">domain</span>
          <span class="text">{{ t$('global.menu.institution') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'institution' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">corporate_fare</span>
            <span class="text">Institution Profile</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">account_balance</span>
            <span class="text">Branch Management</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">policy</span>
            <span class="text">Policies & Rules</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">security</span>
            <span class="text">Security Settings</span>
          </a>
        </div>
      </div>

      <!-- Limit Management Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('limit-management')"
          :class="{ 'is-active': activeSubmenu === 'limit-management' }"
        >
          <span class="material-icons">trending_up</span>
          <span class="text">{{ t$('global.menu.limitMgnt') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'limit-management' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">account_balance_wallet</span>
            <span class="text">Transaction Limits</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">credit_card</span>
            <span class="text">Card Limits</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">schedule</span>
            <span class="text">Daily Limits</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">Limit Configuration</span>
          </a>
        </div>
      </div>

      <!-- User Management Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('user-management')"
          :class="{ 'is-active': activeSubmenu === 'user-management' }"
        >
          <span class="material-icons">group</span>
          <span class="text">{{ t$('global.menu.userMgnt') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'user-management' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">person_add</span>
            <span class="text">Add User</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">people</span>
            <span class="text">User List</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">admin_panel_settings</span>
            <span class="text">Roles & Permissions</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">login</span>
            <span class="text">Active Sessions</span>
          </a>
        </div>
      </div>

      <!-- System Management Menu with Collapsible Submenu -->
      <div class="menu-item-group">
        <button
          class="button menu-parent"
          @click="toggleSubmenu('system-management')"
          :class="{ 'is-active': activeSubmenu === 'system-management' }"
        >
          <span class="material-icons">dns</span>
          <span class="text">{{ t$('global.menu.systemMgnt') }}</span>
          <span class="material-icons expand-icon">expand_more</span>
        </button>

        <div class="submenu" :class="{ 'is-open': activeSubmenu === 'system-management' }">
          <a href="#" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">System Configuration</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">backup</span>
            <span class="text">Backup & Restore</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">bug_report</span>
            <span class="text">System Logs</span>
          </a>
          <a href="#" class="submenu-item">
            <span class="material-icons">monitor_heart</span>
            <span class="text">System Health</span>
          </a>
        </div>
      </div>
    </div>
  </aside>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import './siderbar.css';

const emit = defineEmits(['toggle']);

const { t } = useI18n();
const t$ = t;

const is_expanded = ref(false);
const activeSubmenu = ref(null);

const ToggleMenu = () => {
  is_expanded.value = !is_expanded.value;
  emit('toggle', is_expanded.value);

  // Close all submenus when collapsing sidebar
  if (!is_expanded.value) {
    activeSubmenu.value = null;
  }
};

const toggleSubmenu = submenuName => {
  // Only allow submenu interaction when sidebar is expanded
  if (!is_expanded.value) {
    // If sidebar is collapsed, expand it first
    is_expanded.value = true;
    emit('toggle', is_expanded.value);
    // Set a small delay to allow sidebar expansion animation
    setTimeout(() => {
      activeSubmenu.value = activeSubmenu.value === submenuName ? null : submenuName;
    }, 100);
  } else {
    activeSubmenu.value = activeSubmenu.value === submenuName ? null : submenuName;
  }
};

onMounted(() => {
  is_expanded.value = false;
});
</script>
