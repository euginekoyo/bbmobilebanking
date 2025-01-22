import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MessageTemplateService from './message-template.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IMessageTemplate, MessageTemplate } from '@/shared/model/message-template.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessageTemplateUpdate',
  setup() {
    const messageTemplateService = inject('messageTemplateService', () => new MessageTemplateService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const messageTemplate: Ref<IMessageTemplate> = ref(new MessageTemplate());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMessageTemplate = async messageTemplateId => {
      try {
        const res = await messageTemplateService().find(messageTemplateId);
        res.createdon = new Date(res.createdon);
        messageTemplate.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.messageTemplateId) {
      retrieveMessageTemplate(route.params.messageTemplateId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      messagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      description: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      messageenglish: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      messagesomali: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      createdon: {},
    };
    const v$ = useVuelidate(validationRules, messageTemplate as any);
    v$.value.$validate();

    return {
      messageTemplateService,
      alertService,
      messageTemplate,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: messageTemplate }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.messageTemplate.id) {
        this.messageTemplateService()
          .update(this.messageTemplate)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.messageTemplate.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.messageTemplateService()
          .create(this.messageTemplate)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.messageTemplate.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
