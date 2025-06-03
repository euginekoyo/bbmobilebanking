<template>
  <div>
    <b-dropdown-item to="/mobile-app-transactions">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.transactions')"></span>
    </b-dropdown-item>
  </div>
</template>

<script lang="ts" src="./txn-menu.component.ts"></script>
