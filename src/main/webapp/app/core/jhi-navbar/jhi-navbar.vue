<template>
  <b-navbar data-cy="navbar" toggleable="md" type="dark" class="jh-navbar sticky-top w-100">
    <b-navbar-brand class="logo rounded-pill" b-link to="/">
      <span class="logo-img"></span>
      <span v-text="t$('global.title')" class="navbar-title"></span>
    </b-navbar-brand>
    <b-navbar-toggle
      right
      class="jh-navbar-toggler d-lg-none"
      href="javascript:void(0);"
      data-toggle="collapse"
      target="header-tabs"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <font-awesome-icon icon="bars" />
    </b-navbar-toggle>

    <b-collapse is-nav id="header-tabs" class="flex justify-content-start">
      <b-navbar-nav class="">
        <b-nav-item to="/" exact>
          <span>
            <font-awesome-icon icon="home" />
            <span v-text="t$('global.menu.home')"></span>
          </span>
        </b-nav-item>
        <b-nav-item to="/dashboard" v-if="authenticated" exact>
          <span>
            <font-awesome-icon icon="pie-chart" />
            <span v-text="t$('global.menu.dashboard')"></span>
          </span>
        </b-nav-item>
        <!--        <b-nav-item-dropdown right id="entity-menu" v-if="authenticated" active-class="active" class="pointer" data-cy="entity">-->
        <!--          <template #button-content>-->
        <!--            <span class="navbar-dropdown-menu">-->
        <!--              <font-awesome-icon icon="university" />-->
        <!--              <span class="no-bold" v-text="t$('global.menu.entities.main')"></span>-->
        <!--            </span>-->
        <!--          </template>-->
        <!--          <entities-menu></entities-menu>-->
        <!--          &lt;!&ndash; jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here &ndash;&gt;-->
        <!--        </b-nav-item-dropdown>-->

        <!--        <b-nav-item-dropdown right id="txn-menu" v-if="authenticated" active-class="active" class="pointer" data-cy="entity">-->
        <!--          <template #button-content>-->
        <!--            <span class="navbar-dropdown-menu">-->
        <!--              <font-awesome-icon icon="binoculars" />-->
        <!--              <span class="no-bold" v-text="t$('global.menu.entities.txn')"></span>-->
        <!--            </span>-->
        <!--          </template>-->
        <!--          <txn-menu></txn-menu>-->
        <!--        </b-nav-item-dropdown>-->

        <!--        <b-nav-item-dropdown right id="recon-menu" v-if="authenticated" active-class="active" class="pointer" data-cy="entity">-->
        <!--          <template #button-content>-->
        <!--            <span class="navbar-dropdown-menu">-->
        <!--              <font-awesome-icon icon="list-alt" />-->
        <!--              <span class="no-bold" v-text="t$('global.menu.entities.recon')"></span>-->
        <!--            </span>-->
        <!--          </template>-->
        <!--          <recon-menu></recon-menu>-->
        <!--        </b-nav-item-dropdown>-->

        <!--        <b-nav-item-dropdown-->
        <!--          right-->
        <!--          id="admin-menu"-->
        <!--          v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"-->
        <!--          :class="{ 'router-link-active': subIsActive('/admin') }"-->
        <!--          active-class="active"-->
        <!--          class="pointer"-->
        <!--          data-cy="adminMenu"-->
        <!--        >-->
        <!--          <template #button-content>-->
        <!--            <span class="navbar-dropdown-menu">-->
        <!--              <font-awesome-icon icon="users-cog" />-->
        <!--              <span class="no-bold" v-text="t$('global.menu.admin.main')"></span>-->
        <!--            </span>-->
        <!--          </template>-->
        <!--          <b-dropdown-item to="/admin/user-management" active-class="active">-->
        <!--            <font-awesome-icon icon="users" />-->
        <!--            <span v-text="t$('global.menu.admin.userManagement')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item to="/admin/metrics" active-class="active">-->
        <!--            <font-awesome-icon icon="tachometer-alt" />-->
        <!--            <span v-text="t$('global.menu.admin.metrics')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item to="/admin/health" active-class="active">-->
        <!--            <font-awesome-icon icon="heart" />-->
        <!--            <span v-text="t$('global.menu.admin.health')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item to="/admin/configuration" active-class="active">-->
        <!--            <font-awesome-icon icon="cogs" />-->
        <!--            <span v-text="t$('global.menu.admin.configuration')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item to="/admin/logs" active-class="active">-->
        <!--            <font-awesome-icon icon="tasks" />-->
        <!--            <span v-text="t$('global.menu.admin.logs')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item v-if="openAPIEnabled" to="/admin/docs" active-class="active">-->
        <!--            <font-awesome-icon icon="book" />-->
        <!--            <span v-text="t$('global.menu.admin.apidocs')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--          <b-dropdown-item v-if="!inProduction" href="./h2-console/" target="_tab">-->
        <!--            <font-awesome-icon icon="database" />-->
        <!--            <span v-text="t$('global.menu.admin.database')"></span>-->
        <!--          </b-dropdown-item>-->
        <!--        </b-nav-item-dropdown>-->
      </b-navbar-nav>
    </b-collapse>
    <b-nav-item-dropdown id="languagesnavBarDropdown" right v-if="languages && Object.keys(languages).length > 1">
      <template #button-content>
        <font-awesome-icon icon="flag" />
        <span class="no-bold" v-text="t$('global.menu.language')"></span>
      </template>
      <b-dropdown-item
        v-for="(value, key) in languages"
        :key="`lang-${key}`"
        @click="changeLanguage(key)"
        :class="{ active: isActiveLanguage(key) }"
      >
        {{ value.name }}
      </b-dropdown-item>
    </b-nav-item-dropdown>
    <b-nav-item-dropdown
      right
      href="javascript:void(0);"
      id="account-menu"
      :class="{ 'router-link-active': subIsActive('/account') }"
      active-class="active"
      class=""
      data-cy="accountMenu"
    >
      <template #button-content>
        <span class="navbar-dropdown-menu">
          <font-awesome-icon icon="user" />
          <span class="no-bold" v-text="t$('global.menu.account.main')"></span>
        </span>
      </template>
      <b-dropdown-item data-cy="settings" to="/account/settings" v-if="authenticated" active-class="active">
        <font-awesome-icon icon="wrench" />
        <span v-text="t$('global.menu.account.settings')"></span>
      </b-dropdown-item>
      <b-dropdown-item data-cy="passwordItem" to="/account/password" v-if="authenticated" active-class="active">
        <font-awesome-icon icon="lock" />
        <span v-text="t$('global.menu.account.password')"></span>
      </b-dropdown-item>
      <b-dropdown-item data-cy="logout" v-if="authenticated" @click="logout()" id="logout" active-class="active">
        <font-awesome-icon icon="sign-out-alt" />
        <span v-text="t$('global.menu.account.logout')"></span>
      </b-dropdown-item>
      <b-dropdown-item data-cy="login" v-if="!authenticated" @click="openLogin()" id="login" active-class="active">
        <font-awesome-icon icon="sign-in-alt" />
        <span v-text="t$('global.menu.account.login')"></span>
      </b-dropdown-item>
    </b-nav-item-dropdown>
  </b-navbar>
</template>

<script lang="ts" src="./jhi-navbar.component.ts"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* ==========================================================================
    Navbar
    ========================================================================== */
.navbar-version {
  font-size: 0.65em;
  color: #fffefe;
}

.jh-navbar {
  background: linear-gradient(135deg, #7674b9 0%, #1f1d63 50%, #1f1d46 100%);

  /* background-color: #353d47; */
  padding: 0.2em 1em;
}

.jh-navbar .profile-image {
  margin: -10px 0px;
  height: 40px;
  width: 40px;
  border-radius: 50%;
}

.jh-navbar .dropdown-item.active,
.jh-navbar .dropdown-item.active:focus,
.jh-navbar .dropdown-item.active:hover {
  background-color: #18164d;
}

.jh-navbar .dropdown-toggle::after {
  margin-left: 0.15em;
}

.jh-navbar ul.navbar-nav {
  padding: 0.5em;
}

.jh-navbar .navbar-nav .nav-item {
  margin-left: 1.5rem;
}

.jh-navbar a.nav-link,
.jh-navbar .no-bold {
  font-weight: 400;
}

.jh-navbar .jh-navbar-toggler {
  color: #ffffff;
  font-size: 1.5em;
  padding: 10px;
}

.jh-navbar .jh-navbar-toggler:hover {
  color: #fff;
}

@media screen and (min-width: 768px) {
  .jh-navbar-toggler {
    display: none;
  }
}

@media screen and (min-width: 768px) and (max-width: 1150px) {
  span span {
    display: none;
  }
}

.navbar-title {
  display: inline-block;
  color: rgb(255, 254, 254);
}

/* ==========================================================================
    Logo styles
    ========================================================================== */
.navbar-brand.logo {
  padding: 0 7px;
}

.logo .logo-img {
  height: 45px;
  display: inline-block;
  vertical-align: middle;
  width: 45px;
}

.logo-img {
  height: 100%;
  background: url('/content/images/bushralogo.png') no-repeat center center;
  background-size: contain;
  width: 100%;
  filter: drop-shadow(0 0 0.05rem rgb(255, 255, 255));
  margin: 0 5px;
}
</style>
