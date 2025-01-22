import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TransactionsService from './transactions.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ITransactions, Transactions } from '@/shared/model/transactions.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionsUpdate',
  setup() {
    const transactionsService = inject('transactionsService', () => new TransactionsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transactions: Ref<ITransactions> = ref(new Transactions());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTransactions = async transactionsId => {
      try {
        const res = await transactionsService().find(transactionsId);
        res.datex = new Date(res.datex);
        transactions.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionsId) {
      retrieveTransactions(route.params.transactionsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      processed: {},
      incomingbitmap: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      outgoingbitmap: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      inmessage: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      messagetocbs: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      messagefromcbs: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      cbsprocess: {},
      cbsonline: {},
      cbsresponse: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 500 }).toString(), 500),
      },
      responsemessage: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      responsesent: {},
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      originaldata: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      field39resp: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      narration: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      authorised: {},
      branchcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      field39original: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      messageclass: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      txncode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      currcode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 5 }).toString(), 5),
      },
      device: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      branch2: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      longerbranch: {},
      datex: {},
      timex: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      posted: {},
      attempts: {},
      originaldata2: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      commission: {},
      responsecreated: {},
      online: {},
      originaldata3: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 100 }).toString(), 100),
      },
      toswitch: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 15 }).toString(), 15),
      },
      fromswitch: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 15 }).toString(), 15),
      },
      tocbs: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 15 }).toString(), 15),
      },
      fromcbs: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 15 }).toString(), 15),
      },
      postinglegs: {},
      commissiontxncode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 10 }).toString(), 10),
      },
      hostref: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 30 }).toString(), 30),
      },
      requestcreated: {},
      requestmessage: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      outgoingbitmapflex: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      incomingbitmapflex: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      requestsent: {},
      minicbs: {},
      reversed: {},
      offlinesenttohost: {},
      offlineresponse: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      sourceLongerface: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 40 }).toString(), 40),
      },
      mtirrn: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      hostresponsecode: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      field48: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      source: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
    };
    const v$ = useVuelidate(validationRules, transactions as any);
    v$.value.$validate();

    return {
      transactionsService,
      alertService,
      transactions,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: transactions }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.transactions.id) {
        this.transactionsService()
          .update(this.transactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.transactions.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.transactionsService()
          .create(this.transactions)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.transactions.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
