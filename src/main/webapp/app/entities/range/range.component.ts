import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RangeService from './range.service';
import { type IRange } from '@/shared/model/range.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Range',
  setup() {
    const { t: t$ } = useI18n();
    const rangeService = inject('rangeService', () => new RangeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const ranges: Ref<IRange[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRanges = async () => {
      isFetching.value = true;
      try {
        const res = await rangeService().retrieve();
        ranges.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRanges();
    };

    onMounted(async () => {
      await retrieveRanges();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRange) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRange = async () => {
      try {
        await rangeService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.range.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRanges();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      ranges,
      handleSyncList,
      isFetching,
      retrieveRanges,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRange,
      t$,
    };
  },
});
