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
        <span class="material-icons">home</span>
        <span class="text">Home</span>
      </router-link>
      <router-link to="/about" class="button">
        <span class="material-icons">description</span>
        <span class="text">About</span>
      </router-link>
      <router-link to="/team" class="button">
        <span class="material-icons">group</span>
        <span class="text">Team</span>
      </router-link>
      <router-link to="/contact" class="button">
        <span class="material-icons">email</span>
        <span class="text">Contact</span>
      </router-link>
    </div>

    <div class="flex"></div>

    <div class="menu">
      <router-link to="/settings" class="button">
        <span class="material-icons">settings</span>
        <span class="text">Settings</span>
      </router-link>
    </div>
  </aside>
</template>

<script setup>
import { ref } from 'vue';
// import logoURL from '../assets/logo.png'

const is_expanded = ref(localStorage.getItem('is_expanded') === 'true');

const ToggleMenu = () => {
  is_expanded.value = !is_expanded.value;
  localStorage.setItem('is_expanded', is_expanded.value);
};
</script>

<style lang="scss" scoped>
:root {
  --dark: #1e2a38;
  --dark-alt: #2c3e50;
  --light: #b0bec5;
  --primary: #2196f3;
  --grey: #78909c;
  --sidebar-width: 220px;
}

aside {
  display: flex;
  flex-direction: column;
  background: black;
  margin-top: 100px;
  color: var(--light);
  width: 60px;
  min-height: 100vh;
  padding: 1rem;
  transition: width 0.3s ease-in-out;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;

  .flex {
    flex: 1 1 0%;
  }

  .logo {
    margin-bottom: 1rem;
    display: flex;
    justify-content: center;

    img {
      width: 2.5rem;
      height: 2.5rem;
    }
  }

  .menu-toggle-wrap {
    display: flex;
    justify-content: flex-end;
    margin-bottom: 1rem;

    .menu-toggle {
      background: transparent;
      border: none;
      cursor: pointer;
      padding: 0.5rem;
      border-radius: 4px;
      transition: all 0.2s ease-in-out;

      .material-icons {
        font-size: 1.5rem;
        color: var(--light);
        transition: 0.2s ease-out;
      }

      &:hover {
        background: rgba(255, 255, 255, 0.05);

        .material-icons {
          color: var(--primary);
        }
      }

      &:focus {
        outline: none;
        box-shadow: 0 0 0 2px rgba(33, 150, 243, 0.5);
      }
    }
  }

  h3,
  .button .text {
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
  }

  h3 {
    color: var(--grey);
    font-size: 0.9rem;
    margin: 0.5rem 0;
    text-transform: uppercase;
    letter-spacing: 1px;
  }

  .menu {
    margin: 0 -1rem;

    .button {
      display: flex;
      align-items: center;
      text-decoration: none;
      padding: 0.75rem 1.25rem;
      transition: all 0.2s ease-in-out;

      .material-icons {
        font-size: 1.2rem;
        color: var(--light);
        width: 20px;
        text-align: center;
        transition: 0.2s ease-in-out;
      }

      .text {
        color: var(--light);
        font-size: 0.95rem;
        transition: 0.2s ease-in-out;
      }

      &:hover {
        background-color: rgba(255, 255, 255, 0.05);

        .material-icons,
        .text {
          color: var(--primary);
        }
      }

      &.router-link-exact-active {
        background-color: var(--primary);
        .material-icons,
        .text {
          color: #ffffff;
        }
      }
    }
  }

  &.is-expanded {
    width: var(--sidebar-width);

    .menu-toggle-wrap {
      .menu-toggle {
        transform: rotate(-180deg);
      }
    }

    h3,
    .button .text {
      opacity: 1;
    }

    .button {
      .material-icons {
        margin-right: 0.75rem;
      }
    }
  }

  @media (max-width: 1024px) {
    width: 60px;
    &.is-expanded {
      width: 200px;
    }
  }
}
</style>
