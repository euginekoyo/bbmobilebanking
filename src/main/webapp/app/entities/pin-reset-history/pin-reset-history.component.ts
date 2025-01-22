import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PinResetHistoryService from './pin-reset-history.service';
import { type IPinResetHistory } from '@/shared/model/pin-reset-history.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PinResetHistory',
  setup() {
    const { t: t$ } = useI18n();
    const pinResetHistoryService = inject('pinResetHistoryService', () => new PinResetHistoryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pinResetHistories: Ref<IPinResetHistory[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePinResetHistorys = async () => {
      isFetching.value = true;
      try {
        const res = await pinResetHistoryService().retrieve();
        pinResetHistories.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePinResetHistorys();
    };

    onMounted(async () => {
      await retrievePinResetHistorys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPinResetHistory) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePinResetHistory = async () => {
      try {
        await pinResetHistoryService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.pinResetHistory.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePinResetHistorys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      pinResetHistories,
      handleSyncList,
      isFetching,
      retrievePinResetHistorys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePinResetHistory,
      t$,
    };
  },
});
