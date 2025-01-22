import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SPSParticipatingCodesService from './sps-participating-codes.service';
import { type ISPSParticipatingCodes } from '@/shared/model/sps-participating-codes.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSParticipatingCodes',
  setup() {
    const { t: t$ } = useI18n();
    const sPSParticipatingCodesService = inject('sPSParticipatingCodesService', () => new SPSParticipatingCodesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sPSParticipatingCodes: Ref<ISPSParticipatingCodes[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSPSParticipatingCodess = async () => {
      isFetching.value = true;
      try {
        const res = await sPSParticipatingCodesService().retrieve();
        sPSParticipatingCodes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSPSParticipatingCodess();
    };

    onMounted(async () => {
      await retrieveSPSParticipatingCodess();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISPSParticipatingCodes) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSPSParticipatingCodes = async () => {
      try {
        await sPSParticipatingCodesService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.sPSParticipatingCodes.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSPSParticipatingCodess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      sPSParticipatingCodes,
      handleSyncList,
      isFetching,
      retrieveSPSParticipatingCodess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSPSParticipatingCodes,
      t$,
    };
  },
});
