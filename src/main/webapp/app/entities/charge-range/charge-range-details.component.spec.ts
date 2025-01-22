/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ChargeRangeDetails from './charge-range-details.vue';
import ChargeRangeService from './charge-range.service';
import AlertService from '@/shared/alert/alert.service';

type ChargeRangeDetailsComponentType = InstanceType<typeof ChargeRangeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const chargeRangeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ChargeRange Management Detail Component', () => {
    let chargeRangeServiceStub: SinonStubbedInstance<ChargeRangeService>;
    let mountOptions: MountingOptions<ChargeRangeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      chargeRangeServiceStub = sinon.createStubInstance<ChargeRangeService>(ChargeRangeService);

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
          chargeRangeService: () => chargeRangeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        chargeRangeServiceStub.find.resolves(chargeRangeSample);
        route = {
          params: {
            chargeRangeId: `${123}`,
          },
        };
        const wrapper = shallowMount(ChargeRangeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.chargeRange).toMatchObject(chargeRangeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        chargeRangeServiceStub.find.resolves(chargeRangeSample);
        const wrapper = shallowMount(ChargeRangeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
