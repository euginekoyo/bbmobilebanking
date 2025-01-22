import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import LimitsService from './limits.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ILimits, Limits } from '@/shared/model/limits.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LimitsUpdate',
  setup() {
    const limitsService = inject('limitsService', () => new LimitsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const limits: Ref<ILimits> = ref(new Limits());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveLimits = async limitsId => {
      try {
        const res = await limitsService().find(limitsId);
        limits.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.limitsId) {
      retrieveLimits(route.params.limitsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      transactiontype: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      procode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 6 }).toString(), 6),
      },
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      transactionlimit: {
        required: validations.required(t$('entity.validation.required').toString()),
        integer: validations.integer(t$('entity.validation.number').toString()),
        min: validations.minValue(t$('entity.validation.min', { min: 1 }).toString(), 1),
        max: validations.maxValue(t$('entity.validation.max', { max: 10 }).toString(), 10),
      },
      dailylimit: {
        integer: validations.integer(t$('entity.validation.number').toString()),
        min: validations.minValue(t$('entity.validation.min', { min: 1 }).toString(), 1),
        max: validations.maxValue(t$('entity.validation.max', { max: 10 }).toString(), 10),
      },
      registeredby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      registereddate: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      approved: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 2 }).toString(), 2),
      },
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approveddate: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      updatedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      updateddate: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      rework: {
        integer: validations.integer(t$('entity.validation.number').toString()),
        min: validations.minValue(t$('entity.validation.min', { min: 1 }).toString(), 1),
        max: validations.maxValue(t$('entity.validation.max', { max: 10 }).toString(), 10),
      },
      reworkby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      sessionid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
    };
    const v$ = useVuelidate(validationRules, limits as any);
    v$.value.$validate();

    return {
      limitsService,
      alertService,
      limits,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.limits.id) {
        this.limitsService()
          .update(this.limits)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.limits.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.limitsService()
          .create(this.limits)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.limits.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
