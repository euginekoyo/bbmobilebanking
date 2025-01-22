/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ChargeDetails from './charge-details.vue';
import ChargeService from './charge.service';
import AlertService from '@/shared/alert/alert.service';

type ChargeDetailsComponentType = InstanceType<typeof ChargeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const chargeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Charge Management Detail Component', () => {
    let chargeServiceStub: SinonStubbedInstance<ChargeService>;
    let mountOptions: MountingOptions<ChargeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      chargeServiceStub = sinon.createStubInstance<ChargeService>(ChargeService);

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
          chargeService: () => chargeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        chargeServiceStub.find.resolves(chargeSample);
        route = {
          params: {
            chargeId: `${123}`,
          },
        };
        const wrapper = shallowMount(ChargeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.charge).toMatchObject(chargeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        chargeServiceStub.find.resolves(chargeSample);
        const wrapper = shallowMount(ChargeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
