import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SPSParticipatingCodesService from './sps-participating-codes.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISPSParticipatingCodes, SPSParticipatingCodes } from '@/shared/model/sps-participating-codes.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSParticipatingCodesUpdate',
  setup() {
    const sPSParticipatingCodesService = inject('sPSParticipatingCodesService', () => new SPSParticipatingCodesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSParticipatingCodes: Ref<ISPSParticipatingCodes> = ref(new SPSParticipatingCodes());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSPSParticipatingCodes = async sPSParticipatingCodesId => {
      try {
        const res = await sPSParticipatingCodesService().find(sPSParticipatingCodesId);
        sPSParticipatingCodes.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSParticipatingCodesId) {
      retrieveSPSParticipatingCodes(route.params.sPSParticipatingCodesId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      biccode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 8 }).toString(), 8),
      },
      bicname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 12 }).toString(), 12),
      },
      bicstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
    };
    const v$ = useVuelidate(validationRules, sPSParticipatingCodes as any);
    v$.value.$validate();

    return {
      sPSParticipatingCodesService,
      alertService,
      sPSParticipatingCodes,
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
      if (this.sPSParticipatingCodes.id) {
        this.sPSParticipatingCodesService()
          .update(this.sPSParticipatingCodes)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.sPSParticipatingCodes.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sPSParticipatingCodesService()
          .create(this.sPSParticipatingCodes)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.sPSParticipatingCodes.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
