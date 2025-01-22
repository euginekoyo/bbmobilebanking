import { type Ref, computed, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ChannelsService from './channels.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { Channels, type IChannels } from '@/shared/model/channels.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChannelsUpdate',
  setup() {
    const channelsService = inject('channelsService', () => new ChannelsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const channels: Ref<IChannels> = ref(new Channels());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveChannels = async channelsId => {
      try {
        const res = await channelsService().find(channelsId);
        channels.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.channelsId) {
      retrieveChannels(route.params.channelsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      channel: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 15 }).toString(), 15),
      },
      description: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 50 }).toString(), 50),
      },
      bin: {
        maxLength: validations.maxLength(t$('entity.validation.maxlength', { max: 6 }).toString(), 6),
      },
    };
    const v$ = useVuelidate(validationRules, channels as any);
    v$.value.$validate();

    return {
      channelsService,
      alertService,
      channels,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.channels.id) {
        this.channelsService()
          .update(this.channels)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('bbMobileBankingAdminApp.channels.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.channelsService()
          .create(this.channels)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('bbMobileBankingAdminApp.channels.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
