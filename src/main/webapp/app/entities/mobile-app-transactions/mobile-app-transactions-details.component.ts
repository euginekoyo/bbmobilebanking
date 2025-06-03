import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MobileAppTransactionsService from './mobile-app-transactions.service';
import { type IMobileAppTransactions } from '@/shared/model/mobile-app-transactions.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MobileAppTransactionsDetails',
  setup() {
    const mobileAppTransactionsService = inject('mobileAppTransactionsService', () => new MobileAppTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const mobileAppTransactions: Ref<IMobileAppTransactions> = ref({});

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

    return {
      alertService,
      mobileAppTransactions,

      previousState,
      t$: useI18n().t,
    };
  },
});
