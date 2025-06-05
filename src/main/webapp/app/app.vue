<template>
  <div id="app">
    <div id="app-header " class="sticky-top">
      <jhi-navbar @toggle-sidebar="toggleSidebar"></jhi-navbar>
    </div>
    <ribbon></ribbon>

    <div class="container-fluid rounded-lg">
      <div class="nav-bar" v-if="authenticated">
        <jhi-sidebar :visible="sidebarVisible"></jhi-sidebar>
      </div>
      <div class="card jh-card ml-5">
        <router-view></router-view>
      </div>

      <!-- Modern Login Modal -->
      <b-modal id="login-page" hide-footer lazy centered size="md" content-class="rounded-4 shadow-lg border-0 p-4 custom-login-modal">
        <template #modal-title>
          <h5 class="fw-bold text-center w-100" data-cy="loginTitle" id="login-title">
            {{ t$('login.title') }}
          </h5>
        </template>

        <login-form />
      </b-modal>

      <jhi-footer></jhi-footer>
    </div>
  </div>
</template>

<style scoped>
.custom-login-modal {
  border-radius: 1.5rem !important;
  box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.15);
}
</style>

<script lang="ts" src="./app.component.ts"></script>
