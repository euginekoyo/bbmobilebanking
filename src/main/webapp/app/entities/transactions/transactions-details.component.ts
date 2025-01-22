import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TransactionsService from './transactions.service';
import { useDateFormat } from '@/shared/composables';
import { type ITransactions } from '@/shared/model/transactions.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const transactionsService = inject('transactionsService', () => new TransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const transactions: Ref<ITransactions> = ref({});

    const retrieveTransactions = async transactionsId => {
      try {
        const res = await transactionsService().find(transactionsId);
        transactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionsId) {
      retrieveTransactions(route.params.transactionsId);
    }

    return {
      ...dateFormat,
      alertService,
      transactions,

      previousState,
      t$: useI18n().t,
    };
  },
});
