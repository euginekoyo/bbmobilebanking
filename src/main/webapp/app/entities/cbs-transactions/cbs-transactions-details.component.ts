import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CBSTransactionsService from './cbs-transactions.service';
import { useDateFormat } from '@/shared/composables';
import { type ICBSTransactions } from '@/shared/model/cbs-transactions.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CBSTransactionsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const cBSTransactionsService = inject('cBSTransactionsService', () => new CBSTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const cBSTransactions: Ref<ICBSTransactions> = ref({});

    const retrieveCBSTransactions = async cBSTransactionsId => {
      try {
        const res = await cBSTransactionsService().find(cBSTransactionsId);
        cBSTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.cBSTransactionsId) {
      retrieveCBSTransactions(route.params.cBSTransactionsId);
    }

    return {
      ...dateFormat,
      alertService,
      cBSTransactions,

      previousState,
      t$: useI18n().t,
    };
  },
});
