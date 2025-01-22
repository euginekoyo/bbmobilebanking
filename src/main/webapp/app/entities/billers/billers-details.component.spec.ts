/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BillersDetails from './billers-details.vue';
import BillersService from './billers.service';
import AlertService from '@/shared/alert/alert.service';

type BillersDetailsComponentType = InstanceType<typeof BillersDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const billersSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Billers Management Detail Component', () => {
    let billersServiceStub: SinonStubbedInstance<BillersService>;
    let mountOptions: MountingOptions<BillersDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      billersServiceStub = sinon.createStubInstance<BillersService>(BillersService);

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
          billersService: () => billersServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        billersServiceStub.find.resolves(billersSample);
        route = {
          params: {
            billersId: `${123}`,
          },
        };
        const wrapper = shallowMount(BillersDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.billers).toMatchObject(billersSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        billersServiceStub.find.resolves(billersSample);
        const wrapper = shallowMount(BillersDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
