import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import BranchesService from './branches.service';
import { type IBranches } from '@/shared/model/branches.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Branches',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const branchesService = inject('branchesService', () => new BranchesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const branches: Ref<IBranches[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveBranchess = async () => {
      isFetching.value = true;
      try {
        const res = await branchesService().retrieve();
        branches.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveBranchess();
    };

    onMounted(async () => {
      await retrieveBranchess();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IBranches) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeBranches = async () => {
      try {
        await branchesService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.branches.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveBranchess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      branches,
      handleSyncList,
      isFetching,
      retrieveBranchess,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeBranches,
      t$,
    };
  },
});
