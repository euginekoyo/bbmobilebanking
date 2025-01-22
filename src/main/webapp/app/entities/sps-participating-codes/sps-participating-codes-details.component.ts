import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SPSParticipatingCodesService from './sps-participating-codes.service';
import { type ISPSParticipatingCodes } from '@/shared/model/sps-participating-codes.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SPSParticipatingCodesDetails',
  setup() {
    const sPSParticipatingCodesService = inject('sPSParticipatingCodesService', () => new SPSParticipatingCodesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sPSParticipatingCodes: Ref<ISPSParticipatingCodes> = ref({});

    const retrieveSPSParticipatingCodes = async sPSParticipatingCodesId => {
      try {
        const res = await sPSParticipatingCodesService().find(sPSParticipatingCodesId);
        sPSParticipatingCodes.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sPSParticipatingCodesId) {
      retrieveSPSParticipatingCodes(route.params.sPSParticipatingCodesId);
    }

    return {
      alertService,
      sPSParticipatingCodes,

      previousState,
      t$: useI18n().t,
    };
  },
});
