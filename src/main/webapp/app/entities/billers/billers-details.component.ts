import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import BillersService from './billers.service';
import { useDateFormat } from '@/shared/composables';
import { type IBillers } from '@/shared/model/billers.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BillersDetails',
  setup() {
    const dateFormat = useDateFormat();
    const billersService = inject('billersService', () => new BillersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const billers: Ref<IBillers> = ref({});

    const retrieveBillers = async billersId => {
      try {
        const res = await billersService().find(billersId);
        billers.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.billersId) {
      retrieveBillers(route.params.billersId);
    }

    return {
      ...dateFormat,
      alertService,
      billers,
      billersService,
      previousState,
      t$: useI18n().t,
    };
  },
  methods: {
    approve(): void {
      this.billersService()
        .approve(this.billers)
        .then(param => {
          this.previousState();
          this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.billers.updated', { param: param.id }));
        })
        .catch(error => {
          this.alertService.showHttpError(error.response);
        });
    },
    reject(): void {
      this.billersService()
        .reject(this.billers)
        .then(param => {
          this.previousState();
          this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.billers.updated', { param: param.id }));
        })
        .catch(error => {
          this.alertService.showHttpError(error.response);
        });
    },
  },
});
