import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import { type ISPSIncomingTransactions } from '@/shared/model/sps-incoming-transactions.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSIncomingTransactions',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const sPSIncomingTransactionsService = inject('sPSIncomingTransactionsService', () => new SPSIncomingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSIncomingTransactions: Ref<ISPSIncomingTransactions[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSPSIncomingTransactionss = async () => {
      isFetching.value = true;
      try {
        const res = await sPSIncomingTransactionsService().retrieve();
        sPSIncomingTransactions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSPSIncomingTransactionss();
    };

    onMounted(async () => {
      await retrieveSPSIncomingTransactionss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISPSIncomingTransactions) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSPSIncomingTransactions = async () => {
      try {
        await sPSIncomingTransactionsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.sPSIncomingTransactions.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSPSIncomingTransactionss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      sPSIncomingTransactions,
      handleSyncList,
      isFetching,
      retrieveSPSIncomingTransactionss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSPSIncomingTransactions,
      t$,
    };
  },
});
