import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PinResetHistoryService from './pin-reset-history.service';
import { type IPinResetHistory } from '@/shared/model/pin-reset-history.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PinResetHistoryDetails',
  setup() {
    const pinResetHistoryService = inject('pinResetHistoryService', () => new PinResetHistoryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const pinResetHistory: Ref<IPinResetHistory> = ref({});

    const retrievePinResetHistory = async pinResetHistoryId => {
      try {
        const res = await pinResetHistoryService().find(pinResetHistoryId);
        pinResetHistory.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pinResetHistoryId) {
      retrievePinResetHistory(route.params.pinResetHistoryId);
    }

    return {
      alertService,
      pinResetHistory,

      previousState,
      t$: useI18n().t,
    };
  },
});
