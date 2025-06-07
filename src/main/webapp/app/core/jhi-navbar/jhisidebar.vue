<template>
  <aside :class="{ 'is-expanded': is_expanded }">
    <div class="menu-toggle-wrap">
      <button class="menu-toggle" @click="ToggleMenu" aria-label="Toggle menu">
        <span class="material-icons">{{ is_expanded ? 'keyboard_double_arrow_left' : 'keyboard_double_arrow_right' }}</span>
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
          <router-link to="/customer/details" class="submenu-item">
            <span class="material-icons">visibility</span>
            <span class="text">View Customer Details</span>
          </router-link>
          <router-link to="/customer/kyc" class="submenu-item">
            <span class="material-icons">verified_user</span>
            <span class="text">Complete KYC</span>
          </router-link>
          <!-- Customer Resets Sub-Submenu -->
          <div class="submenu-item-group">
            <a
              class="submenu-item submenu-parent"
              @click="toggleSubSubmenu('customer-resets')"
              :class="{ 'is-active': activeSubSubmenu === 'customer-resets' }"
              :aria-expanded="activeSubSubmenu === 'customer-resets' ? 'true' : 'false'"
            >
              <span class="material-icons">refresh</span>
              <span class="text">Customer Resets</span>
              <span class="material-icons expand-icon">expand_more</span>
            </a>
            <div class="sub-submenu w-75" :class="{ 'is-open': activeSubSubmenu === 'customer-resets' }">
              <router-link to="/reset-pin" class="sub-submenu-item">
                <span class="material-icons">lock</span>
                <span class="text">Reset PIN</span>
              </router-link>
              <router-link to="/approve-pin-reset" class="sub-submenu-item">
                <span class="material-icons">vpn_key</span>
                <span class="text">Approve PIN Reset</span>
              </router-link>
            </div>
          </div>
          <router-link to="/block-customer" class="submenu-item">
            <span class="material-icons">block</span>
            <span class="text">Block Customer</span>
          </router-link>
          <router-link to="/unblock-customer" class="submenu-item">
            <span class="material-icons">lock_open</span>
            <span class="text">Unblock Customer</span>
          </router-link>
          <router-link to="/customer/approve-unblock" class="submenu-item">
            <span class="material-icons">check_circle</span>
            <span class="text">Approve Unblock Customer</span>
          </router-link>
          <router-link to="/customer/close-profile" class="submenu-item">
            <span class="material-icons">close</span>
            <span class="text">Close Customer Profile</span>
          </router-link>
          <router-link to="/customer/approve-close" class="submenu-item">
            <span class="material-icons">done_all</span>
            <span class="text">Approve Close Profile</span>
          </router-link>
          <router-link to="/customer/failed-registration" class="submenu-item">
            <span class="material-icons">error</span>
            <span class="text">Failed Registration</span>
          </router-link>
          <router-link to="/customer/approve-device" class="submenu-item">
            <span class="material-icons">devices</span>
            <span class="text">Approve Customer Device</span>
          </router-link>
          <div class="submenu-item-group">
            <a
              class="submenu-item submenu-parent"
              @click="toggleSubSubmenu('customer-resets')"
              :class="{ 'is-active': activeSubSubmenu === 'customer-resets' }"
              :aria-expanded="activeSubSubmenu === 'customer-resets' ? 'true' : 'false'"
            >
              <span class="material-icons">refresh</span>
              <span class="text">Customers Details</span>
              <span class="material-icons expand-icon">expand_more</span>
            </a>
            <div class="sub-submenu w-75" :class="{ 'is-open': activeSubSubmenu === 'customer-resets' }">
              <router-link to="#" class="sub-submenu-item">
                <span class="material-icons">lock</span>
                <span class="text">Reset PIN</span>
              </router-link>
              <router-link to="#" class="sub-submenu-item">
                <span class="material-icons">vpn_key</span>
                <span class="text">Approve PIN Reset</span>
              </router-link>
            </div>
          </div>
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
          <router-link to="/account-opening/new" class="submenu-item">
            <span class="material-icons">add_circle</span>
            <span class="text">New Account</span>
          </router-link>
          <router-link to="/account-opening/pending" class="submenu-item">
            <span class="material-icons">pending</span>
            <span class="text">Pending Applications</span>
          </router-link>
          <router-link to="/account-opening/approved" class="submenu-item">
            <span class="material-icons">check_circle</span>
            <span class="text">Approved Accounts</span>
          </router-link>
          <router-link to="/account-opening/rejected" class="submenu-item">
            <span class="material-icons">cancel</span>
            <span class="text">Rejected Applications</span>
          </router-link>
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
          <router-link to="/agency/view" class="submenu-item">
            <span class="material-icons">store</span>
            <span class="text">View Agencies</span>
          </router-link>
          <router-link to="/agency/add" class="submenu-item">
            <span class="material-icons">add_business</span>
            <span class="text">Add New Agency</span>
          </router-link>
          <router-link to="/agency/locations" class="submenu-item">
            <span class="material-icons">location_on</span>
            <span class="text">Agency Locations</span>
          </router-link>
          <router-link to="/agency/reports" class="submenu-item">
            <span class="material-icons">assessment</span>
            <span class="text">Performance Reports</span>
          </router-link>
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
          <router-link to="/agency-management/staff" class="submenu-item">
            <span class="material-icons">supervisor_account</span>
            <span class="text">Agency Staff</span>
          </router-link>
          <router-link to="/agency-management/settings" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">Agency Settings</span>
          </router-link>
          <router-link to="/agency-management/commissions" class="submenu-item">
            <span class="material-icons">monetization_on</span>
            <span class="text">Commission Management</span>
          </router-link>
          <router-link to="/agency-management/hours" class="submenu-item">
            <span class="material-icons">schedule</span>
            <span class="text">Working Hours</span>
          </router-link>
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
          <router-link to="/registration/new" class="submenu-item">
            <span class="material-icons">person_add</span>
            <span class="text">New Registration</span>
          </router-link>
          <router-link to="/registration/queue" class="submenu-item">
            <span class="material-icons">list_alt</span>
            <span class="text">Registration Queue</span>
          </router-link>
          <router-link to="/registration/verified" class="submenu-item">
            <span class="material-icons">verified</span>
            <span class="text">Verified Registrations</span>
          </router-link>
          <router-link to="/registration/history" class="submenu-item">
            <span class="material-icons">history</span>
            <span class="text">Registration History</span>
          </router-link>
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
          <router-link to="/alerts/active" class="submenu-item">
            <span class="material-icons">notification_important</span>
            <span class="text">Active Alerts</span>
          </router-link>
          <router-link to="/alerts/create" class="submenu-item">
            <span class="material-icons">add_alert</span>
            <span class="text">Create Alert</span>
          </router-link>
          <router-link to="/alerts/history" class="submenu-item">
            <span class="material-icons">history</span>
            <span class="text">Alert History</span>
          </router-link>
          <router-link to="/alerts/settings" class="submenu-item">
            <span class="material-icons">tune</span>
            <span class="text">Alert Settings</span>
          </router-link>
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
          <router-link to="/bulk/upload" class="submenu-item">
            <span class="material-icons">cloud_upload</span>
            <span class="text">Bulk Upload</span>
          </router-link>
          <router-link to="/bulk/template" class="submenu-item">
            <span class="material-icons">download</span>
            <span class="text">Download Template</span>
          </router-link>
          <router-link to="/bulk/validation" class="submenu-item">
            <span class="material-icons">playlist_add_check</span>
            <span class="text">Validation Results</span>
          </router-link>
          <router-link to="/bulk/status" class="submenu-item">
            <span class="material-icons">sync</span>
            <span class="text">Processing Status</span>
          </router-link>
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
          <router-link to="/institution/profile" class="submenu-item">
            <span class="material-icons">corporate_fare</span>
            <span class="text">Institution Profile</span>
          </router-link>
          <router-link to="/institution/branches" class="submenu-item">
            <span class="material-icons">account_balance</span>
            <span class="text">Branch Management</span>
          </router-link>
          <router-link to="/institution/policies" class="submenu-item">
            <span class="material-icons">policy</span>
            <span class="text">Policies & Rules</span>
          </router-link>
          <router-link to="/institution/security" class="submenu-item">
            <span class="material-icons">security</span>
            <span class="text">Security Settings</span>
          </router-link>
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
          <router-link to="/limit-management/transactions" class="submenu-item">
            <span class="material-icons">account_balance_wallet</span>
            <span class="text">Transaction Limits</span>
          </router-link>
          <router-link to="/limit-management/cards" class="submenu-item">
            <span class="material-icons">credit_card</span>
            <span class="text">Card Limits</span>
          </router-link>
          <router-link to="/limit-management/daily" class="submenu-item">
            <span class="material-icons">schedule</span>
            <span class="text">Daily Limits</span>
          </router-link>
          <router-link to="/limit-management/config" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">Limit Configuration</span>
          </router-link>
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
          <router-link to="/user-management/add" class="submenu-item">
            <span class="material-icons">person_add</span>
            <span class="text">Add User</span>
          </router-link>
          <router-link to="/user-management/list" class="submenu-item">
            <span class="material-icons">people</span>
            <span class="text">User List</span>
          </router-link>
          <router-link to="/user-management/roles" class="submenu-item">
            <span class="material-icons">admin_panel_settings</span>
            <span class="text">Roles & Permissions</span>
          </router-link>
          <router-link to="/user-management/sessions" class="submenu-item">
            <span class="material-icons">login</span>
            <span class="text">Active Sessions</span>
          </router-link>
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
          <router-link to="/system-management/config" class="submenu-item">
            <span class="material-icons">settings</span>
            <span class="text">System Configuration</span>
          </router-link>
          <router-link to="/system-management/backup" class="submenu-item">
            <span class="material-icons">backup</span>
            <span class="text">Backup & Restore</span>
          </router-link>
          <router-link to="/system-management/logs" class="submenu-item">
            <span class="material-icons">bug_report</span>
            <span class="text">System Logs</span>
          </router-link>
          <router-link to="/system-management/health" class="submenu-item">
            <span class="material-icons">monitor_heart</span>
            <span class="text">System Health</span>
          </router-link>
        </div>
      </div>
    </div>
  </aside>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import './siderbar.css';

const emit = defineEmits<{
  (e: 'toggle', isExpanded: boolean): void;
}>();

const { t } = useI18n();
const t$ = t;

const is_expanded = ref(false);
const activeSubmenu = ref<string | null>(null);
const activeSubSubmenu = ref<string | null>(null);

const ToggleMenu = () => {
  is_expanded.value = !is_expanded.value;
  emit('toggle', is_expanded.value);
  if (!is_expanded.value) {
    activeSubmenu.value = null;
    activeSubSubmenu.value = null;
  }
};

const toggleSubmenu = (submenuName: string) => {
  if (!is_expanded.value) {
    is_expanded.value = true;
    emit('toggle', is_expanded.value);
    setTimeout(() => {
      activeSubmenu.value = activeSubmenu.value === submenuName ? null : submenuName;
      if (activeSubmenu.value !== 'customer') {
        activeSubSubmenu.value = null;
      }
    }, 100);
  } else {
    activeSubmenu.value = activeSubmenu.value === submenuName ? null : submenuName;
    if (activeSubmenu.value !== 'customer') {
      activeSubSubmenu.value = null;
    }
  }
};

const toggleSubSubmenu = (subSubmenuName: string) => {
  if (!is_expanded.value || activeSubmenu.value !== 'customer') {
    is_expanded.value = true;
    activeSubmenu.value = 'customer';
    emit('toggle', is_expanded.value);
    setTimeout(() => {
      activeSubSubmenu.value = activeSubSubmenu.value === subSubmenuName ? null : subSubmenuName;
    }, 100);
  } else {
    activeSubSubmenu.value = activeSubSubmenu.value === subSubmenuName ? null : subSubmenuName;
  }
};

onMounted(() => {
  is_expanded.value = false;
});
</script>
