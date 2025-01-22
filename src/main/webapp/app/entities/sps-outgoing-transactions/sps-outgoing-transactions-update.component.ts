import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISPSOutgoingTransactions, SPSOutgoingTransactions } from '@/shared/model/sps-outgoing-transactions.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSOutgoingTransactionsUpdate',
  setup() {
    const sPSOutgoingTransactionsService = inject('sPSOutgoingTransactionsService', () => new SPSOutgoingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSOutgoingTransactions: Ref<ISPSOutgoingTransactions> = ref(new SPSOutgoingTransactions());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSPSOutgoingTransactions = async sPSOutgoingTransactionsId => {
      try {
        const res = await sPSOutgoingTransactionsService().find(sPSOutgoingTransactionsId);
        res.requestInstanttime = new Date(res.requestInstanttime);
        sPSOutgoingTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSOutgoingTransactionsId) {
      retrieveSPSOutgoingTransactions(route.params.sPSOutgoingTransactionsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      messageid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      channelcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 16 }).toString(), 16),
      },
      callbackurl: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      messagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 28 }).toString(), 28),
      },
      transcurrency: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      debtorsname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      debtorsaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      debtorsbankcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 18 }).toString(), 18),
      },
      debtorsphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
      },
      beneficiaryname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      beneficiaryaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      beneficiarybankcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
      },
      beneficiaryphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
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
      messageendtoendid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      transactionstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
      },
      transactionstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      spsstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
      },
      spsstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      cbsstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 19 }).toString(), 19),
      },
      cbsstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      requestInstanttime: {},
      isomessagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 29 }).toString(), 29),
      },
      requestjson: {},
      spsrequestxml: {},
      spsresponsexml: {},
      amount: {},
      callbackstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      callbackstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
    };
    const v$ = useVuelidate(validationRules, sPSOutgoingTransactions as any);
    v$.value.$validate();

    return {
      sPSOutgoingTransactionsService,
      alertService,
      sPSOutgoingTransactions,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: sPSOutgoingTransactions }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.sPSOutgoingTransactions.id) {
        this.sPSOutgoingTransactionsService()
          .update(this.sPSOutgoingTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sPSOutgoingTransactionsService()
          .create(this.sPSOutgoingTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
