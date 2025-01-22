import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ChargeService from './charge.service';
import { useDateFormat } from '@/shared/composables';
import { type ICharge } from '@/shared/model/charge.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChargeDetails',
  setup() {
    const dateFormat = useDateFormat();
    const chargeService = inject('chargeService', () => new ChargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const charge: Ref<ICharge> = ref({});

    const retrieveCharge = async chargeId => {
      try {
        const res = await chargeService().find(chargeId);
        charge.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.chargeId) {
      retrieveCharge(route.params.chargeId);
    }

    return {
      ...dateFormat,
      alertService,
      charge,

      previousState,
      t$: useI18n().t,
    };
  },
});
