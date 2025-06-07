import { defineComponent, inject, ref, onMounted, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { Modal } from 'bootstrap';
import CustomerService from './customer.service';
import { useAlertService } from '@/shared/alert/alert.service';
import { type ICustomer } from '@/shared/model/customer.model';
import { useDateFormat } from '@/shared/composables';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerList',
  setup() {
    const customerService = inject('customerService', () => new CustomerService());
    const alertService = inject('alertService', () => useAlertService(), true);
    const { t: t$ } = useI18n();
    const router = useRouter();

    const customers = ref<ICustomer[]>([]);
    const selectedCustomer = ref<ICustomer | null>(null);
    const remark = ref<string>('');
    const remarkInputRef = ref<HTMLInputElement>();
    const isLoading = ref(false);

    // Computed property to ensure reactivity
    const isRemarkValid = computed(() => {
      return remark.value && remark.value.trim().length > 0;
    });

    // dates
    const dateFormat = useDateFormat();

    // Handle remark input to ensure reactivity
    const handleRemarkInput = (event: Event) => {
      const target = event.target as HTMLTextAreaElement;
      remark.value = target.value;
      console.log('Remark updated:', remark.value); // Debug log
    };

    // Test function to manually set remark
    const testRemark = () => {
      remark.value = 'Test remark ' + Date.now();
      console.log('Test remark set:', remark.value);
      if (remarkInputRef.value) {
        remarkInputRef.value.value = remark.value;
      }
    };

    // Fetch all customers
    const fetchCustomers = async () => {
      try {
        isLoading.value = true;
        const response = await customerService().retrieve();
        customers.value = response.data;
      } catch (error) {
        alertService.showHttpError(error.response);
      } finally {
        isLoading.value = false;
      }
    };

    // Initialize component
    onMounted(() => {
      fetchCustomers();
    });

    // Modal handling
    const openBlockModal = (customer: ICustomer) => {
      selectedCustomer.value = customer;
      remark.value = ''; // Reset remark when opening modal
      const modal = new Modal(document.getElementById('blockCustomerModal'));
      modal.show();

      // Ensure input is properly initialized after modal opens
      setTimeout(() => {
        if (remarkInputRef.value) {
          remarkInputRef.value.value = '';
          remarkInputRef.value.focus();
        }
      }, 300);
    };

    const openViewModal = (customer: ICustomer) => {
      selectedCustomer.value = customer;
      const modal = new Modal(document.getElementById('viewCustomerModal'));
      modal.show();
    };

    // Reset PIN for customer
    const confirmResetPin = async () => {
      if (!selectedCustomer.value) return;

      try {
        // Verify customer exists
        const customer = await customerService().find(selectedCustomer.value.id);
        if (!customer) {
          alertService.showError('Customer not found. Please refresh the list and try again.');
          const modal = Modal.getInstance(document.getElementById('blockCustomerModal'));
          modal?.hide();
          return;
        }

        // Add resetby for audit trail
        const resetBy = 'admin'; // Replace with actual logged-in user ID or name
        await customerService().resetPin(selectedCustomer.value.id, remark.value, resetBy);
        alertService.showSuccess(t$('global.messages.validate.reset').toString());

        // Close modal
        const modal = Modal.getInstance(document.getElementById('blockCustomerModal'));
        modal?.hide();

        // Refresh customer list
        fetchCustomers();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    const approveResetPin = async () => {
      if (!selectedCustomer.value) return;

      // Trim whitespace and check if remark is empty
      if (!remark.value || remark.value.trim().length === 0) {
        alertService.showError('Please enter a remark before approving.');
        return;
      }

      try {
        const customer = await customerService().find(selectedCustomer.value.id);
        if (!customer) {
          alertService.showError('Customer not found. Please refresh the list and try again.');
          const modal = Modal.getInstance(document.getElementById('blockCustomerModal'));
          modal?.hide();
          return;
        }

        const approvedBy = 'admin';
        await customerService().approveResetPin(selectedCustomer.value.id, remark.value.trim(), approvedBy);
        alertService.showSuccess(t$('global.messages.validate.approve').toString());

        const modal = Modal.getInstance(document.getElementById('blockCustomerModal'));
        modal?.hide();

        fetchCustomers();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    // Format date for display
    const formatDateShort = (date: string | Date) => {
      if (!date) return '';
      const d = new Date(date);
      return d.toLocaleDateString('en-US', { timeZone: 'Africa/Nairobi' }); // Adjust for EAT
    };

    return {
      t$,
      customers,
      selectedCustomer,
      remark,
      remarkInputRef,
      ...dateFormat,
      isLoading,
      isRemarkValid,
      openBlockModal,
      openViewModal,
      confirmResetPin,
      approveResetPin,
      handleRemarkInput,
      testRemark,
      formatDateShort,
    };
  },
});
