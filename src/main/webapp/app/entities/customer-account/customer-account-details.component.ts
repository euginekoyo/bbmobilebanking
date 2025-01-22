import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CustomerAccountService from './customer-account.service';
import { useDateFormat } from '@/shared/composables';
import { type ICustomerAccount } from '@/shared/model/customer-account.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerAccountDetails',
  setup() {
    const dateFormat = useDateFormat();
    const customerAccountService = inject('customerAccountService', () => new CustomerAccountService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const customerAccount: Ref<ICustomerAccount> = ref({});

    const retrieveCustomerAccount = async customerAccountId => {
      try {
        const res = await customerAccountService().find(customerAccountId);
        customerAccount.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customerAccountId) {
      retrieveCustomerAccount(route.params.customerAccountId);
    }

    return {
      ...dateFormat,
      alertService,
      customerAccount,

      previousState,
      t$: useI18n().t,
    };
  },
});
