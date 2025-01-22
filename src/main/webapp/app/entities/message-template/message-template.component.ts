import { type Ref, defineComponent, inject, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';

import MessageTemplateService from './message-template.service';
import { type IMessageTemplate } from '@/shared/model/message-template.model';
import { useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessageTemplate',
  setup() {
    const { t: t$ } = useI18n();
    const dateFormat = useDateFormat();
    const messageTemplateService = inject('messageTemplateService', () => new MessageTemplateService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const messageTemplates: Ref<IMessageTemplate[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMessageTemplates = async () => {
      isFetching.value = true;
      try {
        const res = await messageTemplateService().retrieve();
        messageTemplates.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMessageTemplates();
    };

    onMounted(async () => {
      await retrieveMessageTemplates();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMessageTemplate) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMessageTemplate = async () => {
      try {
        await messageTemplateService().delete(removeId.value);
        const message = t$('bbMobileBankingAdminApp.messageTemplate.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMessageTemplates();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      messageTemplates,
      handleSyncList,
      isFetching,
      retrieveMessageTemplates,
      clear,
      ...dateFormat,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMessageTemplate,
      t$,
    };
  },
});
