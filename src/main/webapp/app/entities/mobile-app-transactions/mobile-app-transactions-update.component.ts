import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MobileAppTransactionsService from './mobile-app-transactions.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IMobileAppTransactions, MobileAppTransactions } from '@/shared/model/mobile-app-transactions.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MobileAppTransactionsUpdate',
  setup() {
    const mobileAppTransactionsService = inject('mobileAppTransactionsService', () => new MobileAppTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const mobileAppTransactions: Ref<IMobileAppTransactions> = ref(new MobileAppTransactions());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMobileAppTransactions = async mobileAppTransactionsId => {
      try {
        const res = await mobileAppTransactionsService().find(mobileAppTransactionsId);
        mobileAppTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.mobileAppTransactionsId) {
      retrieveMobileAppTransactions(route.params.mobileAppTransactionsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      channel: {},
      channelIp: {},
      channelReference: {},
      channelTimestamp: {},
      clientId: {},
      createdAt: {},
      debitAccount: {},
      direction: {},
      errorDescription: {},
      geolocation: {},
      hostCode: {},
      phoneNumber: {},
      responseCode: {},
      responseMessage: {},
      transactionCode: {},
      transactionType: {},
      userAgent: {},
      userAgentVersion: {},
      amount: {},
      chargeamount: {},
      creditAccount: {},
      cbsReference: {},
    };
    const v$ = useVuelidate(validationRules, mobileAppTransactions as any);
    v$.value.$validate();

    return {
      mobileAppTransactionsService,
      alertService,
      mobileAppTransactions,
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
      if (this.mobileAppTransactions.id) {
        this.mobileAppTransactionsService()
          .update(this.mobileAppTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('mobileBankingAdminPortalApp.mobileAppTransactions.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.mobileAppTransactionsService()
          .create(this.mobileAppTransactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('mobileBankingAdminPortalApp.mobileAppTransactions.created', { param: param.id }).toString(),
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
