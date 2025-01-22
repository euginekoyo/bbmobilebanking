import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CBSTransactionsService from './cbs-transactions.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { CBSTransactions, type ICBSTransactions } from '@/shared/model/cbs-transactions.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CBSTransactionsUpdate',
  setup() {
    const cBSTransactionsService = inject('cBSTransactionsService', () => new CBSTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const cBSTransactions: Ref<ICBSTransactions> = ref(new CBSTransactions());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCBSTransactions = async cBSTransactionsId => {
      try {
        const res = await cBSTransactionsService().find(cBSTransactionsId);
        res.requestInstanttime = new Date(res.requestInstanttime);
        cBSTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.cBSTransactionsId) {
      retrieveCBSTransactions(route.params.cBSTransactionsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      messageid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 14 }).toString(), 14),
      },
      channelcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4 }).toString(), 4),
      },
      messagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 3 }).toString(), 3),
      },
      transcurrency: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 3 }).toString(), 3),
      },
      debtorsname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      debtorsaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 14 }).toString(), 14),
      },
      debtorsphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 14 }).toString(), 14),
      },
      creditorsname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      creditorsaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 14 }).toString(), 14),
      },
      creditorsphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 14 }).toString(), 14),
      },
      narration: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      externalreference: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      cbsreference: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      cbsstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      cbsstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      requestInstanttime: {},
      requestjson: {},
      cbsrequestxml: {},
      cbsresponsexml: {},
      amount: {},
    };
    const v$ = useVuelidate(validationRules, cBSTransactions as any);
    v$.value.$validate();

    return {
      cBSTransactionsService,
      alertService,
      cBSTransactions,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: cBSTransactions }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.cBSTransactions.id) {
        this.cBSTransactionsService()
          .update(this.cBSTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.cBSTransactions.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.cBSTransactionsService()
          .create(this.cBSTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.cBSTransactions.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
