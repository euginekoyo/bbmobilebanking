import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ServiceManagementService from './service-management.service';
import { useDateFormat } from '@/shared/composables';
import { type IServiceManagement } from '@/shared/model/service-management.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ServiceManagementDetails',
  setup() {
    const dateFormat = useDateFormat();
    const serviceManagementService = inject('serviceManagementService', () => new ServiceManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const serviceManagement: Ref<IServiceManagement> = ref({});

    const retrieveServiceManagement = async serviceManagementId => {
      try {
        const res = await serviceManagementService().find(serviceManagementId);
        serviceManagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.serviceManagementId) {
      retrieveServiceManagement(route.params.serviceManagementId);
    }

    return {
      ...dateFormat,
      alertService,
      serviceManagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
