<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.messageTemplate.home.createOrEditLabel"
          data-cy="MessageTemplateCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.messageTemplate.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="messageTemplate.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="messageTemplate.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagetype')"
              for="message-template-messagetype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagetype"
              id="message-template-messagetype"
              data-cy="messagetype"
              :class="{ valid: !v$.messagetype.$invalid, invalid: v$.messagetype.$invalid }"
              v-model="v$.messagetype.$model"
            />
            <div v-if="v$.messagetype.$anyDirty && v$.messagetype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messagetype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messageTemplate.description')"
              for="message-template-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="message-template-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
            <div v-if="v$.description.$anyDirty && v$.description.$invalid">
              <small class="form-text text-danger" v-for="error of v$.description.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messageTemplate.messageenglish')"
              for="message-template-messageenglish"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messageenglish"
              id="message-template-messageenglish"
              data-cy="messageenglish"
              :class="{ valid: !v$.messageenglish.$invalid, invalid: v$.messageenglish.$invalid }"
              v-model="v$.messageenglish.$model"
            />
            <div v-if="v$.messageenglish.$anyDirty && v$.messageenglish.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messageenglish.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messageTemplate.messagesomali')"
              for="message-template-messagesomali"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagesomali"
              id="message-template-messagesomali"
              data-cy="messagesomali"
              :class="{ valid: !v$.messagesomali.$invalid, invalid: v$.messagesomali.$invalid }"
              v-model="v$.messagesomali.$model"
            />
            <div v-if="v$.messagesomali.$anyDirty && v$.messagesomali.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messagesomali.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.messageTemplate.createdon')"
              for="message-template-createdon"
            ></label>
            <div class="d-flex">
              <input
                id="message-template-createdon"
                data-cy="createdon"
                type="datetime-local"
                class="form-control"
                name="createdon"
                :class="{ valid: !v$.createdon.$invalid, invalid: v$.createdon.$invalid }"
                :value="convertDateTimeFromServer(v$.createdon.$model)"
                @change="updateInstantField('createdon', $event)"
              />
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
<script lang="ts" src="./message-template-update.component.ts"></script>
