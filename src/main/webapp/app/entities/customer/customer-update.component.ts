import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CustomerService from './customer.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { Customer, type ICustomer } from '@/shared/model/customer.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerUpdate',
  setup() {
    const customerService = inject('customerService', () => new CustomerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customer: Ref<ICustomer> = ref(new Customer());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCustomer = async customerId => {
      try {
        const res = await customerService().find(customerId);
        res.regdate = new Date(res.regdate);
        res.partialdate = new Date(res.partialdate);
        res.registerdate = new Date(res.registerdate);
        res.approveddate = new Date(res.approveddate);
        res.declineddate = new Date(res.declineddate);
        res.dob = new Date(res.dob);
        res.deactivatedon = new Date(res.deactivatedon);
        res.phonenochangedon = new Date(res.phonenochangedon);
        res.reseton = new Date(res.reseton);
        res.pinblockon = new Date(res.pinblockon);
        res.approvedon = new Date(res.approvedon);
        res.dateofbirth = new Date(res.dateofbirth);
        res.passreseton = new Date(res.passreseton);
        res.passblockon = new Date(res.passblockon);
        res.approveddisableon = new Date(res.approveddisableon);
        res.disabledon = new Date(res.disabledon);
        res.resetapproveon = new Date(res.resetapproveon);
        res.activatedon = new Date(res.activatedon);
        customer.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customerId) {
      retrieveCustomer(route.params.customerId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      customername: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      phonenumber: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 12 }).toString(), 12),
      },
      cardnumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1000 }).toString(), 1000),
      },
      accountnumber: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      lang: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      pin: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      firstlogin: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1 }).toString(), 1),
      },
      active: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1 }).toString(), 1),
      },
      registered: {},
      cstdelete: {},
      regdate: {},
      alertenabled: {},
      remark: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      imsi: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      partiallyregistered: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1 }).toString(), 1),
      },
      partialdate: {},
      registerdate: {},
      approved: {},
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approveddate: {},
      declined: {},
      declinedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      declineddate: {},
      checkerremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      postaladdress: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      residence: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      dob: {},
      createdby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      emailaddress: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      identificationid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      addaccount: {},
      aclinkinginstitution: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      deactivated: {},
      deactivatedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      deactivatedon: {},
      phonenochanged: {},
      phonenochangedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      phonenochangedon: {},
      originalphoneno: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      newphoneno: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      reset: {},
      resetinginstitution: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      pinresetremark: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      resetby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      reseton: {},
      unblockinginstitution: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      pinblock: {},
      pinblockby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      pinblockremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      blockinginstitution: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      pinblockon: {},
      approvedon: {},
      pinunblockby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      loggedin: {},
      trials: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      idtype: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      idnumber: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      gender: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1 }).toString(), 1),
      },
      cif: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      dateofbirth: {},
      remarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      resetimsi: {},
      imsiresetby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      firstname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      secondname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      lastname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      pinblocktime: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      customerstatus: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      username: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 2000 }).toString(), 2000),
      },
      password: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 3900 }).toString(), 3900),
      },
      deviceid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passreset: {},
      passresetby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passreseton: {},
      passblock: {},
      passblockby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passblockon: {},
      pinmarkblock: {},
      passmarkblock: {},
      passresetremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passblockremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passunblockby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      passtrials: {},
      appactive: {},
      lastlogin: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 32 }).toString(), 32),
      },
      appmarkeddisable: {},
      disableby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approvedisableby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      appmarkedenable: {},
      enableby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approvedenableby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      markeddeactivate: {},
      appfirstlogin: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 5 }).toString(), 5),
      },
      atmtrials: {},
      shorcuts: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 1000 }).toString(), 1000),
      },
      markedactivate: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      town: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      approveddisableon: {},
      disabledon: {},
      resetapproveon: {},
      deletedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      questionsasked: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      questionstrials: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      questionsanswered: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      validotp: {},
      activatedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      activatedon: {},
      branchcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
    };
    const v$ = useVuelidate(validationRules, customer as any);
    v$.value.$validate();

    return {
      customerService,
      alertService,
      customer,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: customer }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.customer.id) {
        this.customerService()
          .update(this.customer)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.customer.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.customerService()
          .create(this.customer)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.customer.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
