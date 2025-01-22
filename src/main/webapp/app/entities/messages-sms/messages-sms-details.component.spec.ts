/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MessagesSmsDetails from './messages-sms-details.vue';
import MessagesSmsService from './messages-sms.service';
import AlertService from '@/shared/alert/alert.service';

type MessagesSmsDetailsComponentType = InstanceType<typeof MessagesSmsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const messagesSmsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MessagesSms Management Detail Component', () => {
    let messagesSmsServiceStub: SinonStubbedInstance<MessagesSmsService>;
    let mountOptions: MountingOptions<MessagesSmsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      messagesSmsServiceStub = sinon.createStubInstance<MessagesSmsService>(MessagesSmsService);

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
          messagesSmsService: () => messagesSmsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        messagesSmsServiceStub.find.resolves(messagesSmsSample);
        route = {
          params: {
            messagesSmsId: `${123}`,
          },
        };
        const wrapper = shallowMount(MessagesSmsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.messagesSms).toMatchObject(messagesSmsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        messagesSmsServiceStub.find.resolves(messagesSmsSample);
        const wrapper = shallowMount(MessagesSmsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
