/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RangeDetails from './range-details.vue';
import RangeService from './range.service';
import AlertService from '@/shared/alert/alert.service';

type RangeDetailsComponentType = InstanceType<typeof RangeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rangeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Range Management Detail Component', () => {
    let rangeServiceStub: SinonStubbedInstance<RangeService>;
    let mountOptions: MountingOptions<RangeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      rangeServiceStub = sinon.createStubInstance<RangeService>(RangeService);

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
          rangeService: () => rangeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rangeServiceStub.find.resolves(rangeSample);
        route = {
          params: {
            rangeId: `${123}`,
          },
        };
        const wrapper = shallowMount(RangeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.range).toMatchObject(rangeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rangeServiceStub.find.resolves(rangeSample);
        const wrapper = shallowMount(RangeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
