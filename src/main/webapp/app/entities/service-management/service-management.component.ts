import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ServiceManagementService from './service-management.service';
import { type IServiceManagement } from '@/shared/model/service-management.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ServiceManagement',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const serviceManagementService = inject('serviceManagementService', () => new ServiceManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const serviceManagements: Ref<IServiceManagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveServiceManagements = async () => {
      isFetching.value = true;
      try {
        const res = await serviceManagementService().retrieve();
        serviceManagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveServiceManagements();
    };

    onMounted(async () => {
      await retrieveServiceManagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IServiceManagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeServiceManagement = async () => {
      try {
        await serviceManagementService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.serviceManagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveServiceManagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      serviceManagements,
      handleSyncList,
      isFetching,
      retrieveServiceManagements,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeServiceManagement,
      t$,
    };
  },
});
