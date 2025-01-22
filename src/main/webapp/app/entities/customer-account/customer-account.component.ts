import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CustomerAccountService from './customer-account.service';
import { type ICustomerAccount } from '@/shared/model/customer-account.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerAccount',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const customerAccountService = inject('customerAccountService', () => new CustomerAccountService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customerAccounts: Ref<ICustomerAccount[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCustomerAccounts = async () => {
      isFetching.value = true;
      try {
        const res = await customerAccountService().retrieve();
        customerAccounts.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCustomerAccounts();
    };

    onMounted(async () => {
      await retrieveCustomerAccounts();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICustomerAccount) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCustomerAccount = async () => {
      try {
        await customerAccountService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.customerAccount.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCustomerAccounts();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      customerAccounts,
      handleSyncList,
      isFetching,
      retrieveCustomerAccounts,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCustomerAccount,
      t$,
    };
  },
});
