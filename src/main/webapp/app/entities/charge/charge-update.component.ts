import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ChargeService from './charge.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ChargeRangeService from '@/entities/charge-range/charge-range.service';
import { type IChargeRange } from '@/shared/model/charge-range.model';
import RangeService from '@/entities/range/range.service';
import { type IRange } from '@/shared/model/range.model';
import { Charge, type ICharge } from '@/shared/model/charge.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChargeUpdate',
  setup() {
    const chargeService = inject('chargeService', () => new ChargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const charge: Ref<ICharge> = ref(new Charge());

    const chargeRangeService = inject('chargeRangeService', () => new ChargeRangeService());

    const chargeRanges: Ref<IChargeRange[]> = ref([]);

    const rangeService = inject('rangeService', () => new RangeService());

    const ranges: Ref<IRange[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCharge = async chargeId => {
      try {
        const res = await chargeService().find(chargeId);
        res.datecreated = new Date(res.datecreated);
        res.approveddate = new Date(res.approveddate);
        charge.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.chargeId) {
      retrieveCharge(route.params.chargeId);
    }

    const initRelationships = () => {
      chargeRangeService()
        .retrieve()
        .then(res => {
          chargeRanges.value = res.data;
        });
      rangeService()
        .retrieve()
        .then(res => {
          ranges.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      txntype: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      feemode: {},
      amount: {},
      datecreated: {},
      createdby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      approved: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 150 }).toString(), 150),
      },
      txncode: {},
      description: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 64 }).toString(), 64),
      },
      approveddate: {},
      chargeRange: {},
      range: {},
    };
    const v$ = useVuelidate(validationRules, charge as any);
    v$.value.$validate();

    return {
      chargeService,
      alertService,
      charge,
      previousState,
      isSaving,
      currentLanguage,
      chargeRanges,
      ranges,
      v$,
      ...useDateFormat({ entityRef: charge }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.charge.id) {
        this.chargeService()
          .update(this.charge)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.charge.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.chargeService()
          .create(this.charge)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.charge.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
