import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ChargeService from './charge.service';
import { type ICharge } from '@/shared/model/charge.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Charge',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const chargeService = inject('chargeService', () => new ChargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const charges: Ref<ICharge[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCharges = async () => {
      isFetching.value = true;
      try {
        const res = await chargeService().retrieve();
        charges.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCharges();
    };

    onMounted(async () => {
      await retrieveCharges();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICharge) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCharge = async () => {
      try {
        await chargeService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.charge.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCharges();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      charges,
      handleSyncList,
      isFetching,
      retrieveCharges,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCharge,
      t$,
    };
  },
});
