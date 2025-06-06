<template>
  <div id="app">
    <div id="app-header " class="sticky-top">
      <jhi-navbar></jhi-navbar>
    </div>
    <div class="nav-bar">
      <jhi-sidebar v-if="authenticated" :visible="sidebarVisible" @toggle="handleSidebarToggle"></jhi-sidebar>
    </div>

    <div class="container-fluid rounded-lg">
      <ribbon></ribbon>
      <div class="card jh-card ml-5">
        <main :class="{ 'sidebar-open': sidebarVisible }">
          <router-view></router-view>
        </main>
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
.app-layout {
  display: flex;
}

.main-content {
  flex-grow: 1;
  transition: margin-left 0.3s ease;
  margin-left: 60px; /* collapsed sidebar width */
  padding: 1rem;
}

.sidebar-open {
  margin-left: 250px; /* expanded sidebar width */
}
</style>

<script lang="ts" src="./app.component.ts"></script>
