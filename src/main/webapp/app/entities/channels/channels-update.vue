<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.channels.home.createOrEditLabel"
          data-cy="ChannelsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.channels.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="channels.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="channels.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.channels.channel')" for="channels-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="channels-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.channels.description')"
              for="channels-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="channels-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
            <div v-if="v$.description.$anyDirty && v$.description.$invalid">
              <small class="form-text text-danger" v-for="error of v$.description.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.channels.bin')" for="channels-bin"></label>
            <input
              type="text"
              class="form-control"
              name="bin"
              id="channels-bin"
              data-cy="bin"
              :class="{ valid: !v$.bin.$invalid, invalid: v$.bin.$invalid }"
              v-model="v$.bin.$model"
            />
            <div v-if="v$.bin.$anyDirty && v$.bin.$invalid">
              <small class="form-text text-danger" v-for="error of v$.bin.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" @click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./channels-update.component.ts"></script>
