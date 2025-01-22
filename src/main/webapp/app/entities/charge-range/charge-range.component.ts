import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ChargeRangeService from './charge-range.service';
import { type IChargeRange } from '@/shared/model/charge-range.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChargeRange',
  setup() {
    const { t: t$ } = useI18n();
    const chargeRangeService = inject('chargeRangeService', () => new ChargeRangeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const chargeRanges: Ref<IChargeRange[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveChargeRanges = async () => {
      isFetching.value = true;
      try {
        const res = await chargeRangeService().retrieve();
        chargeRanges.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveChargeRanges();
    };

    onMounted(async () => {
      await retrieveChargeRanges();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IChargeRange) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeChargeRange = async () => {
      try {
        await chargeRangeService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.chargeRange.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveChargeRanges();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      chargeRanges,
      handleSyncList,
      isFetching,
      retrieveChargeRanges,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeChargeRange,
      t$,
    };
  },
});
