import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import BranchesService from './branches.service';
import { useDateFormat } from '@/shared/composables';
import { type IBranches } from '@/shared/model/branches.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'BranchesDetails',
  setup() {
    const dateFormat = useDateFormat();
    const branchesService = inject('branchesService', () => new BranchesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const branches: Ref<IBranches> = ref({});

    const retrieveBranches = async branchesId => {
      try {
        const res = await branchesService().find(branchesId);
        branches.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.branchesId) {
      retrieveBranches(route.params.branchesId);
    }

    return {
      ...dateFormat,
      alertService,
      branches,

      previousState,
      t$: useI18n().t,
    };
  },
});
