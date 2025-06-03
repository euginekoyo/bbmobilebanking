import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ChargeRangeService from './charge-range.service';
import { type IChargeRange } from '@/shared/model/charge-range.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChargeRangeDetails',
  setup() {
    const chargeRangeService = inject('chargeRangeService', () => new ChargeRangeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const chargeRange: Ref<IChargeRange> = ref({});

    const retrieveChargeRange = async chargeRangeId => {
      try {
        const res = await chargeRangeService().find(chargeRangeId);
        chargeRange.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.chargeRangeId) {
      retrieveChargeRange(route.params.chargeRangeId);
    }

    return {
      alertService,
      chargeRange,
      chargeRangeService,
      previousState,
      t$: useI18n().t,
    };
  },
  methods: {
    approve(): void {
      this.chargeRangeService()
        .approve(this.chargeRange)
        .then(param => {
          this.previousState();
          this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.chargeRange.updated', { param: param.id }));
        })
        .catch(error => {
          this.alertService.showHttpError(error.response);
        });
    },

    reject(): void {
      this.chargeRangeService()
        .reject(this.chargeRange)
        .then(param => {
          this.previousState();
          this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.chargeRange.updated', { param: param.id }));
        })
        .catch(error => {
          this.alertService.showHttpError(error.response);
        });
    },
  },
});
