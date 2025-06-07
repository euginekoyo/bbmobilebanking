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
    const modalInstance = ref<Modal | null>(null); // Store modal instance

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
      if (!modalInstance.value) {
        const modalElement = document.getElementById('blockCustomerModal');
        if (modalElement) {
          modalInstance.value = new Modal(modalElement);
        } else {
          console.error('Modal element not found:', 'blockCustomerModal');
        }
      }
      modalInstance.value?.show();

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
      const modalElement = document.getElementById('viewCustomerModal');
      if (modalElement) {
        new Modal(modalElement).show();
      } else {
        console.error('Modal element not found:', 'viewCustomerModal');
      }
    };

    // Block customer
    const confirmBlockCustomer = async () => {
      if (!selectedCustomer.value || !modalInstance.value) return;

      // Validate remark
      if (!remark.value || remark.value.trim().length === 0) {
        alertService.showError('Please enter a remark before blocking.');
        return;
      }

      isLoading.value = true; // Start loading
      try {
        const updatedCustomer = await customerService().confirmBlockCustomer(
          selectedCustomer.value.id,
          remark.value.trim(),
          'admin', // Replace with logged-in user ID or name
        );
        alertService.showSuccess(t$('bbMobileBankingAdminApp.customer.blockSuccess').toString());
        // Update the customer in the list to reflect the new blocked status
        const index = customers.value.findIndex(c => c.id === selectedCustomer.value!.id);
        if (index !== -1) {
          customers.value[index] = updatedCustomer;
        }
      } catch (error) {
        alertService.showHttpError(error.response);
      } finally {
        isLoading.value = false; // Stop loading
        modalInstance.value?.hide(); // Close modal
        remark.value = ''; // Reset remark
        fetchCustomers(); // Refresh customer list
      }
    };

    // Reset PIN for customer
    const confirmResetPin = async () => {
      if (!selectedCustomer.value || !modalInstance.value) return;

      console.log('Starting confirmResetPin, isLoading:', isLoading.value); // Debug
      isLoading.value = true; // Start loading
      console.log('isLoading set to true:', isLoading.value); // Debug
      try {
        // Verify customer exists
        const customer = await customerService().find(selectedCustomer.value.id);
        if (!customer) {
          alertService.showError('Customer not found. Please refresh the list and try again.');
          return; // Let finally handle modal close
        }

        // Add resetby for audit trail
        const resetBy = 'admin'; // Replace with actual logged-in user ID or name
        await customerService().resetPin(selectedCustomer.value.id, remark.value, resetBy);
        alertService.showSuccess(t$('global.messages.validate.reset').toString());
      } catch (error) {
        alertService.showHttpError(error.response);
      } finally {
        console.log('Finally block, isLoading before reset:', isLoading.value); // Debug
        isLoading.value = false; // Stop loading
        console.log('isLoading set to false:', isLoading.value); // Debug
        modalInstance.value?.hide(); // Close modal
        remark.value = ''; // Reset remark
        fetchCustomers(); // Refresh customer list
      }
    };

    const approveResetPin = async () => {
      if (!selectedCustomer.value || !modalInstance.value) return;

      // Trim whitespace and check if remark is empty
      if (!remark.value || remark.value.trim().length === 0) {
        alertService.showError('Please enter a remark before approving.');
        return;
      }
      console.log('Starting approveResetPin, isLoading:', isLoading.value); // Debug
      isLoading.value = true;
      console.log('isLoading set to true:', isLoading.value); // Debug
      try {
        const customer = await customerService().find(selectedCustomer.value.id);
        if (!customer) {
          alertService.showError('Customer not found. Please refresh the list and try again.');
          return;
        }

        const approvedBy = 'admin';
        await customerService().approveResetPin(selectedCustomer.value.id, remark.value.trim(), approvedBy);
        alertService.showSuccess(t$('global.messages.validate.approve').toString());
      } catch (error) {
        alertService.showHttpError(error.response);
      } finally {
        console.log('Finally block, isLoading before reset:', isLoading.value); // Debug
        isLoading.value = false;
        console.log('isLoading set to false:', isLoading.value); // Debug
        modalInstance.value?.hide();
        remark.value = '';
        fetchCustomers();
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
      confirmBlockCustomer,
      handleRemarkInput,
      testRemark,
      formatDateShort,
    };
  },
});
