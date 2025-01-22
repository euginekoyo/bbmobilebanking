import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import { type ISPSOutgoingTransactions } from '@/shared/model/sps-outgoing-transactions.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSOutgoingTransactions',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const sPSOutgoingTransactionsService = inject('sPSOutgoingTransactionsService', () => new SPSOutgoingTransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSOutgoingTransactions: Ref<ISPSOutgoingTransactions[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSPSOutgoingTransactionss = async () => {
      isFetching.value = true;
      try {
        const res = await sPSOutgoingTransactionsService().retrieve();
        sPSOutgoingTransactions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSPSOutgoingTransactionss();
    };

    onMounted(async () => {
      await retrieveSPSOutgoingTransactionss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISPSOutgoingTransactions) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSPSOutgoingTransactions = async () => {
      try {
        await sPSOutgoingTransactionsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.sPSOutgoingTransactions.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSPSOutgoingTransactionss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      sPSOutgoingTransactions,
      handleSyncList,
      isFetching,
      retrieveSPSOutgoingTransactionss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSPSOutgoingTransactions,
      t$,
    };
  },
});
