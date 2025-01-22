import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import MessagesSmsService from './messages-sms.service';
import { type IMessagesSms } from '@/shared/model/messages-sms.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessagesSms',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const messagesSmsService = inject('messagesSmsService', () => new MessagesSmsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const messagesSms: Ref<IMessagesSms[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMessagesSmss = async () => {
      isFetching.value = true;
      try {
        const res = await messagesSmsService().retrieve();
        messagesSms.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMessagesSmss();
    };

    onMounted(async () => {
      await retrieveMessagesSmss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMessagesSms) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMessagesSms = async () => {
      try {
        await messagesSmsService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.messagesSms.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMessagesSmss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      messagesSms,
      handleSyncList,
      isFetching,
      retrieveMessagesSmss,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMessagesSms,
      t$,
    };
  },
});
