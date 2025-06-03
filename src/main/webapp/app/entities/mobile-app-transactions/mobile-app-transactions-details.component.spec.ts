/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MobileAppTransactionsDetails from './mobile-app-transactions-details.vue';
import MobileAppTransactionsService from './mobile-app-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type MobileAppTransactionsDetailsComponentType = InstanceType<typeof MobileAppTransactionsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mobileAppTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MobileAppTransactions Management Detail Component', () => {
    let mobileAppTransactionsServiceStub: SinonStubbedInstance<MobileAppTransactionsService>;
    let mountOptions: MountingOptions<MobileAppTransactionsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      mobileAppTransactionsServiceStub = sinon.createStubInstance<MobileAppTransactionsService>(MobileAppTransactionsService);

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
          mobileAppTransactionsService: () => mobileAppTransactionsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mobileAppTransactionsServiceStub.find.resolves(mobileAppTransactionsSample);
        route = {
          params: {
            mobileAppTransactionsId: `${123}`,
          },
        };
        const wrapper = shallowMount(MobileAppTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.mobileAppTransactions).toMatchObject(mobileAppTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mobileAppTransactionsServiceStub.find.resolves(mobileAppTransactionsSample);
        const wrapper = shallowMount(MobileAppTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
