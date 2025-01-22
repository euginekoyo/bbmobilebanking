import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import BillersService from './billers.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { Billers, type IBillers } from '@/shared/model/billers.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BillersUpdate',
  setup() {
    const billersService = inject('billersService', () => new BillersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const billers: Ref<IBillers> = ref(new Billers());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveBillers = async billersId => {
      try {
        const res = await billersService().find(billersId);
        res.datecreated = new Date(res.datecreated);
        res.approveddate = new Date(res.approveddate);
        billers.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.billersId) {
      retrieveBillers(route.params.billersId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      billerid: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      description: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      billercollectionaccount: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      datecreated: {},
      createdby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approved: {},
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approveddate: {},
      chargableproductid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 25 }).toString(), 25),
      },
      nonchargableproductid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 25 }).toString(), 25),
      },
      usdbillercollectionaccount: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      enableduplicatecheck: {},
      remarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 250 }).toString(), 250),
      },
      sessionid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      reworkby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      status: {},
      active: {},
      rework: {},
    };
    const v$ = useVuelidate(validationRules, billers as any);
    v$.value.$validate();

    return {
      billersService,
      alertService,
      billers,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: billers }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.billers.id) {
        this.billersService()
          .update(this.billers)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.billers.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.billersService()
          .create(this.billers)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.billers.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
