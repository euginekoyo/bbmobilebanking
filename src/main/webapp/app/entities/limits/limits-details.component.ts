import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import LimitsService from './limits.service';
import { type ILimits } from '@/shared/model/limits.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LimitsDetails',
  setup() {
    const limitsService = inject('limitsService', () => new LimitsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const limits: Ref<ILimits> = ref({});

    const retrieveLimits = async limitsId => {
      try {
        const res = await limitsService().find(limitsId);
        limits.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.limitsId) {
      retrieveLimits(route.params.limitsId);
    }

    return {
      alertService,
      limits,

      previousState,
      t$: useI18n().t,
    };
  },
});
