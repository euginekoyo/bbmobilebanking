import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import { useDateFormat } from '@/shared/composables';
import { type ISPSOutgoingTransactions } from '@/shared/model/sps-outgoing-transactions.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSOutgoingTransactionsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const sPSOutgoingTransactionsService = inject('sPSOutgoingTransactionsService', () => new SPSOutgoingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sPSOutgoingTransactions: Ref<ISPSOutgoingTransactions> = ref({});

    const retrieveSPSOutgoingTransactions = async sPSOutgoingTransactionsId => {
      try {
        const res = await sPSOutgoingTransactionsService().find(sPSOutgoingTransactionsId);
        sPSOutgoingTransactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSOutgoingTransactionsId) {
      retrieveSPSOutgoingTransactions(route.params.sPSOutgoingTransactionsId);
    }

    return {
      ...dateFormat,
      alertService,
      sPSOutgoingTransactions,

      previousState,
      t$: useI18n().t,
    };
  },
});
