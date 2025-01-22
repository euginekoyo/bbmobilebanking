import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISPSIncomingTransactions, SPSIncomingTransactions } from '@/shared/model/sps-incoming-transactions.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSIncomingTransactionsUpdate',
  setup() {
    const sPSIncomingTransactionsService = inject('sPSIncomingTransactionsService', () => new SPSIncomingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSIncomingTransactions: Ref<ISPSIncomingTransactions> = ref(new SPSIncomingTransactions());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSPSIncomingTransactions = async sPSIncomingTransactionsId => {
      try {
        const res = await sPSIncomingTransactionsService().find(sPSIncomingTransactionsId);
        res.requestInstanttime = new Date(res.requestInstanttime);
        sPSIncomingTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSIncomingTransactionsId) {
      retrieveSPSIncomingTransactions(route.params.sPSIncomingTransactionsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      messageid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      channelcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 12 }).toString(), 12),
      },
      callbackurl: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      messagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      transcurrency: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 6 }).toString(), 6),
      },
      debtorsname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      debtorsaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      debtorsbankcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      debtorsphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      beneficiaryname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      beneficiaryaccountid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      beneficiarybankcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      beneficiaryphone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
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
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      transactionstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      spsstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      spsstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      cbsstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      cbsstatusdesc: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      requestInstanttime: {},
      isomessagetype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      requestjson: {},
      spsrequestxml: {},
      spsresponsexml: {},
      amount: {},
    };
    const v$ = useVuelidate(validationRules, sPSIncomingTransactions as any);
    v$.value.$validate();

    return {
      sPSIncomingTransactionsService,
      alertService,
      sPSIncomingTransactions,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: sPSIncomingTransactions }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.sPSIncomingTransactions.id) {
        this.sPSIncomingTransactionsService()
          .update(this.sPSIncomingTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.sPSIncomingTransactions.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sPSIncomingTransactionsService()
          .create(this.sPSIncomingTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('bbMobileBankingAdminApp.sPSIncomingTransactions.created', { param: param.id }).toString(),
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
