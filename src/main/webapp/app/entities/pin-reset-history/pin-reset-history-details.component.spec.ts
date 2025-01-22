/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PinResetHistoryDetails from './pin-reset-history-details.vue';
import PinResetHistoryService from './pin-reset-history.service';
import AlertService from '@/shared/alert/alert.service';

type PinResetHistoryDetailsComponentType = InstanceType<typeof PinResetHistoryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pinResetHistorySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PinResetHistory Management Detail Component', () => {
    let pinResetHistoryServiceStub: SinonStubbedInstance<PinResetHistoryService>;
    let mountOptions: MountingOptions<PinResetHistoryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      pinResetHistoryServiceStub = sinon.createStubInstance<PinResetHistoryService>(PinResetHistoryService);

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
          pinResetHistoryService: () => pinResetHistoryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pinResetHistoryServiceStub.find.resolves(pinResetHistorySample);
        route = {
          params: {
            pinResetHistoryId: `${123}`,
          },
        };
        const wrapper = shallowMount(PinResetHistoryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.pinResetHistory).toMatchObject(pinResetHistorySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pinResetHistoryServiceStub.find.resolves(pinResetHistorySample);
        const wrapper = shallowMount(PinResetHistoryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
