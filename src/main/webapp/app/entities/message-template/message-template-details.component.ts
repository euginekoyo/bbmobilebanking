import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MessageTemplateService from './message-template.service';
import { useDateFormat } from '@/shared/composables';
import { type IMessageTemplate } from '@/shared/model/message-template.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessageTemplateDetails',
  setup() {
    const dateFormat = useDateFormat();
    const messageTemplateService = inject('messageTemplateService', () => new MessageTemplateService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const messageTemplate: Ref<IMessageTemplate> = ref({});

    const retrieveMessageTemplate = async messageTemplateId => {
      try {
        const res = await messageTemplateService().find(messageTemplateId);
        messageTemplate.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.messageTemplateId) {
      retrieveMessageTemplate(route.params.messageTemplateId);
    }

    return {
      ...dateFormat,
      alertService,
      messageTemplate,

      previousState,
      t$: useI18n().t,
    };
  },
});
