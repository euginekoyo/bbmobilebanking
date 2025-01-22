import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ChannelsService from './channels.service';
import { type IChannels } from '@/shared/model/channels.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ChannelsDetails',
  setup() {
    const channelsService = inject('channelsService', () => new ChannelsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const channels: Ref<IChannels> = ref({});

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

    return {
      alertService,
      channels,

      previousState,
      t$: useI18n().t,
    };
  },
});
