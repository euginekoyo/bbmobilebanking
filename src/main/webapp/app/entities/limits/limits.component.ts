import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import LimitsService from './limits.service';
import { type ILimits } from '@/shared/model/limits.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Limits',
  setup() {
    const { t: t$ } = useI18n();
    const limitsService = inject('limitsService', () => new LimitsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const limits: Ref<ILimits[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveLimitss = async () => {
      isFetching.value = true;
      try {
        const res = await limitsService().retrieve();
        limits.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveLimitss();
    };

    onMounted(async () => {
      await retrieveLimitss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ILimits) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeLimits = async () => {
      try {
        await limitsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.limits.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveLimitss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      limits,
      handleSyncList,
      isFetching,
      retrieveLimitss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeLimits,
      t$,
    };
  },
});
