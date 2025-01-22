/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LimitsDetails from './limits-details.vue';
import LimitsService from './limits.service';
import AlertService from '@/shared/alert/alert.service';

type LimitsDetailsComponentType = InstanceType<typeof LimitsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const limitsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Limits Management Detail Component', () => {
    let limitsServiceStub: SinonStubbedInstance<LimitsService>;
    let mountOptions: MountingOptions<LimitsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      limitsServiceStub = sinon.createStubInstance<LimitsService>(LimitsService);

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
          limitsService: () => limitsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        limitsServiceStub.find.resolves(limitsSample);
        route = {
          params: {
            limitsId: `${123}`,
          },
        };
        const wrapper = shallowMount(LimitsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.limits).toMatchObject(limitsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        limitsServiceStub.find.resolves(limitsSample);
        const wrapper = shallowMount(LimitsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
