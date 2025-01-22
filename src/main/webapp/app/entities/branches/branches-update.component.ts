import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import BranchesService from './branches.service';
import { useDateFormat, useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { Branches, type IBranches } from '@/shared/model/branches.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BranchesUpdate',
  setup() {
    const branchesService = inject('branchesService', () => new BranchesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const branches: Ref<IBranches> = ref(new Branches());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveBranches = async branchesId => {
      try {
        const res = await branchesService().find(branchesId);
        res.createdon = new Date(res.createdon);
        res.deletedon = new Date(res.deletedon);
        res.reworkedon = new Date(res.reworkedon);
        branches.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.branchesId) {
      retrieveBranches(route.params.branchesId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      branchname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      branchcode: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 3 }).toString(), 3),
      },
      approved: {},
      email: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      address: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      phone: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 12 }).toString(), 12),
      },
      location: {
        required: validations.required(t$('entity.validation.required').toString()),
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      contactperson: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 4000 }).toString(), 4000),
      },
      remarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 2000 }).toString(), 2000),
      },
      createdby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      createdon: {},
      approvedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      approvedon: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      checkerremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      deletedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      deletedon: {},
      deleteremarks: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 200 }).toString(), 200),
      },
      deleted: {},
      declined: {},
      declineddon: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 7 }).toString(), 7),
      },
      declinedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      sessionid: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      reworked: {},
      reworkedby: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 20 }).toString(), 20),
      },
      reworkedon: {},
      district: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      region: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      regionname: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      reporting: {},
    };
    const v$ = useVuelidate(validationRules, branches as any);
    v$.value.$validate();

    return {
      branchesService,
      alertService,
      branches,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: branches }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.branches.id) {
        this.branchesService()
          .update(this.branches)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.branches.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.branchesService()
          .create(this.branches)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.branches.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
