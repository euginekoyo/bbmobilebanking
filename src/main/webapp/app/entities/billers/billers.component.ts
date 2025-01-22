import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import BillersService from './billers.service';
import { type IBillers } from '@/shared/model/billers.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Billers',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const billersService = inject('billersService', () => new BillersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const billers: Ref<IBillers[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveBillerss = async () => {
      isFetching.value = true;
      try {
        const res = await billersService().retrieve();
        billers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveBillerss();
    };

    onMounted(async () => {
      await retrieveBillerss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IBillers) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeBillers = async () => {
      try {
        await billersService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.billers.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveBillerss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      billers,
      handleSyncList,
      isFetching,
      retrieveBillerss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeBillers,
      t$,
    };
  },
});
