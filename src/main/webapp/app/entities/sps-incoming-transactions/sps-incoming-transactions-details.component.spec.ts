/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SPSIncomingTransactionsDetails from './sps-incoming-transactions-details.vue';
import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type SPSIncomingTransactionsDetailsComponentType = InstanceType<typeof SPSIncomingTransactionsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sPSIncomingTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SPSIncomingTransactions Management Detail Component', () => {
    let sPSIncomingTransactionsServiceStub: SinonStubbedInstance<SPSIncomingTransactionsService>;
    let mountOptions: MountingOptions<SPSIncomingTransactionsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sPSIncomingTransactionsServiceStub = sinon.createStubInstance<SPSIncomingTransactionsService>(SPSIncomingTransactionsService);

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
          sPSIncomingTransactionsService: () => sPSIncomingTransactionsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSIncomingTransactionsServiceStub.find.resolves(sPSIncomingTransactionsSample);
        route = {
          params: {
            sPSIncomingTransactionsId: `${123}`,
          },
        };
        const wrapper = shallowMount(SPSIncomingTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sPSIncomingTransactions).toMatchObject(sPSIncomingTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sPSIncomingTransactionsServiceStub.find.resolves(sPSIncomingTransactionsSample);
        const wrapper = shallowMount(SPSIncomingTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
