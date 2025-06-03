<template>
  <div>
    <b-dropdown-item to="/customer">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.customer')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/billers">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.billers')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/branches">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.branches')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/channels">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.channels')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/charge">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.charge')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/charge-range">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.chargeRange')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/limits">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.limits')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/messages-sms">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.messagesSms')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/message-template">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.messageTemplate')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/pin-reset-history">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.pinResetHistory')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/range">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.range')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/service-management">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.serviceManagement')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/notification">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.notification')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
