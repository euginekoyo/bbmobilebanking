import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ChannelsService from './channels.service';
import { type IChannels } from '@/shared/model/channels.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Channels',
  setup() {
    const { t: t$ } = useI18n();
    const channelsService = inject('channelsService', () => new ChannelsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const channels: Ref<IChannels[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveChannelss = async () => {
      isFetching.value = true;
      try {
        const res = await channelsService().retrieve();
        channels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveChannelss();
    };

    onMounted(async () => {
      await retrieveChannelss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IChannels) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeChannels = async () => {
      try {
        await channelsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.channels.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveChannelss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      channels,
      handleSyncList,
      isFetching,
      retrieveChannelss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeChannels,
      t$,
    };
  },
});
