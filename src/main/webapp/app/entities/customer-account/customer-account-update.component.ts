import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CustomerAccountService from './customer-account.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { CustomerAccount, type ICustomerAccount } from '@/shared/model/customer-account.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerAccountUpdate',
  setup() {
    const customerAccountService = inject('customerAccountService', () => new CustomerAccountService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customerAccount: Ref<ICustomerAccount> = ref(new CustomerAccount());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCustomerAccount = async customerAccountId => {
      try {
        const res = await customerAccountService().find(customerAccountId);
        res.timelinked = new Date(res.timelinked);
        customerAccount.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customerAccountId) {
      retrieveCustomerAccount(route.params.customerAccountId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      customerid: {
        required: validations.required(t$('entity.validation.required').toString()),
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      accountnumber: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 11 }).toString(), 11),
      },
      accountclass: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      customernumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      cif: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      timelinked: {},
      blocked: {},
      stopped: {},
      dormant: {},
    };
    const v$ = useVuelidate(validationRules, customerAccount as any);
    v$.value.$validate();

    return {
      customerAccountService,
      alertService,
      customerAccount,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: customerAccount }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.customerAccount.id) {
        this.customerAccountService()
          .update(this.customerAccount)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.customerAccount.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.customerAccountService()
          .create(this.customerAccount)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.customerAccount.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
