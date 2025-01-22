import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import { useDateFormat } from '@/shared/composables';
import { type ISPSIncomingTransactions } from '@/shared/model/sps-incoming-transactions.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSIncomingTransactionsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const sPSIncomingTransactionsService = inject('sPSIncomingTransactionsService', () => new SPSIncomingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sPSIncomingTransactions: Ref<ISPSIncomingTransactions> = ref({});

    const retrieveSPSIncomingTransactions = async sPSIncomingTransactionsId => {
      try {
        const res = await sPSIncomingTransactionsService().find(sPSIncomingTransactionsId);
        sPSIncomingTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSIncomingTransactionsId) {
      retrieveSPSIncomingTransactions(route.params.sPSIncomingTransactionsId);
    }

    return {
      ...dateFormat,
      alertService,
      sPSIncomingTransactions,

      previousState,
      t$: useI18n().t,
    };
  },
});
