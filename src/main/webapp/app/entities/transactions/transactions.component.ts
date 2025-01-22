import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TransactionsService from './transactions.service';
import { type ITransactions } from '@/shared/model/transactions.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Transactions',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const transactionsService = inject('transactionsService', () => new TransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transactions: Ref<ITransactions[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTransactionss = async () => {
      isFetching.value = true;
      try {
        const res = await transactionsService().retrieve();
        transactions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTransactionss();
    };

    onMounted(async () => {
      await retrieveTransactionss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITransactions) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTransactions = async () => {
      try {
        await transactionsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.transactions.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTransactionss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      transactions,
      handleSyncList,
      isFetching,
      retrieveTransactionss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTransactions,
      t$,
    };
  },
});
