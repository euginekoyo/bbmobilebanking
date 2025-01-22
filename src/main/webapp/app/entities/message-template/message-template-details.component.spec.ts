/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MessageTemplateDetails from './message-template-details.vue';
import MessageTemplateService from './message-template.service';
import AlertService from '@/shared/alert/alert.service';

type MessageTemplateDetailsComponentType = InstanceType<typeof MessageTemplateDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const messageTemplateSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MessageTemplate Management Detail Component', () => {
    let messageTemplateServiceStub: SinonStubbedInstance<MessageTemplateService>;
    let mountOptions: MountingOptions<MessageTemplateDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      messageTemplateServiceStub = sinon.createStubInstance<MessageTemplateService>(MessageTemplateService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          messageTemplateService: () => messageTemplateServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        messageTemplateServiceStub.find.resolves(messageTemplateSample);
        route = {
          params: {
            messageTemplateId: `${123}`,
          },
        };
        const wrapper = shallowMount(MessageTemplateDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.messageTemplate).toMatchObject(messageTemplateSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        messageTemplateServiceStub.find.resolves(messageTemplateSample);
        const wrapper = shallowMount(MessageTemplateDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
