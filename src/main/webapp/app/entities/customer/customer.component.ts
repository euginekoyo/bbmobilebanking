import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CustomerService from './customer.service';
import { type ICustomer } from '@/shared/model/customer.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Customer',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const customerService = inject('customerService', () => new CustomerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customers: Ref<ICustomer[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCustomers = async () => {
      isFetching.value = true;
      try {
        const res = await customerService().retrieve();
        customers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCustomers();
    };

    onMounted(async () => {
      await retrieveCustomers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICustomer) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCustomer = async () => {
      try {
        await customerService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.customer.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCustomers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      customers,
      handleSyncList,
      isFetching,
      retrieveCustomers,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCustomer,
      t$,
    };
  },
});
