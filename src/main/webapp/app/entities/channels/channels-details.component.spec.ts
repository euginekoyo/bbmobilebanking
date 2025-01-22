/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ChannelsDetails from './channels-details.vue';
import ChannelsService from './channels.service';
import AlertService from '@/shared/alert/alert.service';

type ChannelsDetailsComponentType = InstanceType<typeof ChannelsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const channelsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Channels Management Detail Component', () => {
    let channelsServiceStub: SinonStubbedInstance<ChannelsService>;
    let mountOptions: MountingOptions<ChannelsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      channelsServiceStub = sinon.createStubInstance<ChannelsService>(ChannelsService);

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
          channelsService: () => channelsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        channelsServiceStub.find.resolves(channelsSample);
        route = {
          params: {
            channelsId: `${123}`,
          },
        };
        const wrapper = shallowMount(ChannelsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.channels).toMatchObject(channelsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        channelsServiceStub.find.resolves(channelsSample);
        const wrapper = shallowMount(ChannelsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
