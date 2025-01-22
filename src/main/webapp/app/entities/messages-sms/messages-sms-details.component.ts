import { type Ref, defineComponent, inject, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MessagesSmsService from './messages-sms.service';
import { useDateFormat } from '@/shared/composables';
import { type IMessagesSms } from '@/shared/model/messages-sms.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MessagesSmsDetails',
  setup() {
    const dateFormat = useDateFormat();
    const messagesSmsService = inject('messagesSmsService', () => new MessagesSmsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const messagesSms: Ref<IMessagesSms> = ref({});

    const retrieveMessagesSms = async messagesSmsId => {
      try {
        const res = await messagesSmsService().find(messagesSmsId);
        messagesSms.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.messagesSmsId) {
      retrieveMessagesSms(route.params.messagesSmsId);
    }

    return {
      ...dateFormat,
      alertService,
      messagesSms,

      previousState,
      t$: useI18n().t,
    };
  },
});
