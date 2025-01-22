import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MessagesSmsService from './messages-sms.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IMessagesSms, MessagesSms } from '@/shared/model/messages-sms.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessagesSmsUpdate',
  setup() {
    const messagesSmsService = inject('messagesSmsService', () => new MessagesSmsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const messagesSms: Ref<IMessagesSms> = ref(new MessagesSms());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMessagesSms = async messagesSmsId => {
      try {
        const res = await messagesSmsService().find(messagesSmsId);
        res.trndatetime = new Date(res.trndatetime);
        res.datecreated = new Date(res.datecreated);
        messagesSms.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.messagesSmsId) {
      retrieveMessagesSms(route.params.messagesSmsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      trndatetime: {},
      phonenumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      transactionno: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      accountnumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      message: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 2000 }).toString(), 2000),
      },
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      trials: {},
      priority: {},
      responsecode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4 }).toString(), 4),
      },
      responsemsg: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      sent: {},
      delivered: {},
      txntype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      errorexception: {},
      datecreated: {},
      datesent: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      rtpsreqtime: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      fxgenerated: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      taxprocessed: {},
      batchnumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      batchnumbertax: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      responsetime: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      pduseqid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      remarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 300 }).toString(), 300),
      },
      resendby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
    };
    const v$ = useVuelidate(validationRules, messagesSms as any);
    v$.value.$validate();

    return {
      messagesSmsService,
      alertService,
      messagesSms,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: messagesSms }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.messagesSms.id) {
        this.messagesSmsService()
          .update(this.messagesSms)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.messagesSms.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.messagesSmsService()
          .create(this.messagesSms)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.messagesSms.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
