import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RangeService from './range.service';
import { type IRange } from '@/shared/model/range.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RangeDetails',
  setup() {
    const rangeService = inject('rangeService', () => new RangeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const range: Ref<IRange> = ref({});

    const retrieveRange = async rangeId => {
      try {
        const res = await rangeService().find(rangeId);
        range.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.rangeId) {
      retrieveRange(route.params.rangeId);
    }

    return {
      alertService,
      range,

      previousState,
      t$: useI18n().t,
    };
  },
});
