import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ServiceManagementService from './service-management.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IServiceManagement, ServiceManagement } from '@/shared/model/service-management.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ServiceManagementUpdate',
  setup() {
    const serviceManagementService = inject('serviceManagementService', () => new ServiceManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const serviceManagement: Ref<IServiceManagement> = ref(new ServiceManagement());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveServiceManagement = async serviceManagementId => {
      try {
        const res = await serviceManagementService().find(serviceManagementId);
        res.datecreated = new Date(res.datecreated);
        res.approveddate = new Date(res.approveddate);
        serviceManagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.serviceManagementId) {
      retrieveServiceManagement(route.params.serviceManagementId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      processingcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      active: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      createdby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      datecreated: {},
      approved: {},
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      approveddate: {},
      adaptortype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      destination: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      thirdpartyresponse: {},
      telco: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      description: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      remarks: {},
      sessionid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      reworkby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
    };
    const v$ = useVuelidate(validationRules, serviceManagement as any);
    v$.value.$validate();

    return {
      serviceManagementService,
      alertService,
      serviceManagement,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: serviceManagement }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.serviceManagement.id) {
        this.serviceManagementService()
          .update(this.serviceManagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.serviceManagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.serviceManagementService()
          .create(this.serviceManagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.serviceManagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
