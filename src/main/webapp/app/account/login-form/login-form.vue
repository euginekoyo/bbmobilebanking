<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <b-alert
          show
          data-cy="loginError"
          variant="danger"
          v-if="authenticationError"
          v-html="t$('login.messages.error.authentication')"
        ></b-alert>
      </div>
      <div>
        <b-form @submit.prevent="doLogin()">
          <div class="form-group floating-label mb-4">
            <b-form-input
              id="username"
              type="text"
              name="username"
              class="form-control form-control-lg"
              placeholder=" "
              v-model="login"
              required
              data-cy="username"
            ></b-form-input>
            <label for="username">{{ t$('global.form["username.label"]') }}</label>
          </div>

          <div class="form-group floating-label mb-4">
            <b-form-input
              id="password"
              type="password"
              name="password"
              class="form-control form-control-lg"
              placeholder=" "
              v-model="password"
              required
              data-cy="password"
            >
            </b-form-input>
            <label for="password">{{ t$('login.form.password') }} </label>
          </div>

          <b-form-checkbox id="rememberMe" name="rememberMe" v-model="rememberMe" checked>
            <span v-text="t$('login.form.rememberme')"></span>
          </b-form-checkbox>
          <div>
            <b-button
              class="custom-login-button w-100"
              data-cy="submit"
              type="submit"
              variant="primary"
              v-text="t$('login.form.button')"
            ></b-button>
          </div>
          <b-alert show variant="light" class="custom-alert text-center">
            <b-link
              :to="'/account/reset/request'"
              class="text-decoration-none"
              v-text="t$('login.password.forgot')"
              data-cy="forgetYourPasswordSelector"
            ></b-link>
            <div class="my-2">
              <span class="me-1 mx-1" v-text="t$('global.messages.info.register.noaccount')"></span>
              <b-link
                :to="'/register'"
                class="text-decoration-none fw-bold w-100"
                v-text="t$('global.messages.info.register.link')"
              ></b-link>
            </div>
          </b-alert>
        </b-form>
      </div>
    </div>
  </div>
</template>
<style scoped>
.custom-login-button {
  border-radius: 0.5rem; /* ← Customize this value */
  padding: 5px 24px;
  font-weight: bold;
  background-color: #007bff; /* or your custom color */
  color: white;
  border: none;
  margin-top: 10px;
  width: 130px;
  transition: background-color 0.3s ease;
}
.custom-login-button:hover {
  background-color: #0056b3;
}
.floating-label {
  position: relative;
  font-size: 10px;
}

.floating-label input {
  padding-top: 1.25rem;
  padding-bottom: 0.25rem;
}

.floating-label label {
  position: absolute;
  top: 0.5rem;
  left: 0.75rem;
  color: #6c757d;
  font-size: 1.2rem;
  pointer-events: none;
  transition: all 0.2s ease-in-out;
  background: white;
  padding: 0 0.25rem;
}

.floating-label input:focus + label,
.floating-label input:not(:placeholder-shown) + label {
  top: -0.65rem;
  left: 0.65rem;
  font-size: 0.75rem;
  color: #495057;
}
</style>
<script lang="ts" src="./login-form.component.ts"></script>
