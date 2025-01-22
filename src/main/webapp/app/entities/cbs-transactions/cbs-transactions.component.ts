import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CBSTransactionsService from './cbs-transactions.service';
import { type ICBSTransactions } from '@/shared/model/cbs-transactions.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CBSTransactions',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const cBSTransactionsService = inject('cBSTransactionsService', () => new CBSTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const cBSTransactions: Ref<ICBSTransactions[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCBSTransactionss = async () => {
      isFetching.value = true;
      try {
        const res = await cBSTransactionsService().retrieve();
        cBSTransactions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCBSTransactionss();
    };

    onMounted(async () => {
      await retrieveCBSTransactionss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICBSTransactions) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCBSTransactions = async () => {
      try {
        await cBSTransactionsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.cBSTransactions.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCBSTransactionss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      cBSTransactions,
      handleSyncList,
      isFetching,
      retrieveCBSTransactionss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCBSTransactions,
      t$,
    };
  },
});
