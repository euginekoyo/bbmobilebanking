<template>
  <div>
    <b-dropdown-item to="/cbs-transactions">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.cbsTransactions')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/sps-incoming-transactions">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.spsIncomingTransactions')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/sps-outgoing-transactions">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.spsOutgoingTransactions')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/sps-participating-codes">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.spsParticipatingCodes')"></span>
    </b-dropdown-item>
  </div>
</template>

<script lang="ts" src="./recon-menu.component.ts"></script>
