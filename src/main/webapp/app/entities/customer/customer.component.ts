import { defineComponent, inject, ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import * as bootstrap from 'bootstrap';
import { useDateFormat } from '@/shared/composables';
import CustomerService from './customer.service';
import { type ICustomer } from '@/shared/model/customer.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  name: 'Customer',
  setup() {
    const { t: t$ } = useI18n();
    const customerService = inject('customerService', () => new CustomerService());
    const alertService = inject('alertService', () => useAlertService(), true);
    const router = useRouter();
    const dateFormat = useDateFormat();
    const customers = ref<ICustomer[]>([]);
    const selectedCustomer = ref<ICustomer | null>(null);
    const isFetching = ref(false);
    const clear = () => {};
    // Fetch customers
    const loadCustomers = async () => {
      try {
        const result = await customerService().retrieve();
        customers.value = result.data;
      } catch (error) {
        alertService.showHttpError(error);
      }
    };
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

    // Open block customer modal
    const openBlockModal = (customer: ICustomer) => {
      selectedCustomer.value = customer;
      const modal = new bootstrap.Modal(document.getElementById('blockCustomerModal'), {});
      modal.show();
    };

    // Confirm block customer

    // Open view customer modal
    const openViewModal = async (customer: ICustomer) => {
      try {
        const result = await customerService().find(customer.id);
        selectedCustomer.value = result;
        const modal = new bootstrap.Modal(document.getElementById('viewCustomerModal'), {});
        modal.show();
      } catch (error) {
        alertService.showHttpError(error);
      }
    };
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

    // Load customers on component mount
    loadCustomers();

    return {
      t$,
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
      customers,
      selectedCustomer,
      openBlockModal,
      openViewModal,
    };
  },
});
