/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SPSOutgoingTransactionsDetails from './sps-outgoing-transactions-details.vue';
import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type SPSOutgoingTransactionsDetailsComponentType = InstanceType<typeof SPSOutgoingTransactionsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sPSOutgoingTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SPSOutgoingTransactions Management Detail Component', () => {
    let sPSOutgoingTransactionsServiceStub: SinonStubbedInstance<SPSOutgoingTransactionsService>;
    let mountOptions: MountingOptions<SPSOutgoingTransactionsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sPSOutgoingTransactionsServiceStub = sinon.createStubInstance<SPSOutgoingTransactionsService>(SPSOutgoingTransactionsService);

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
          sPSOutgoingTransactionsService: () => sPSOutgoingTransactionsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSOutgoingTransactionsServiceStub.find.resolves(sPSOutgoingTransactionsSample);
        route = {
          params: {
            sPSOutgoingTransactionsId: `${123}`,
          },
        };
        const wrapper = shallowMount(SPSOutgoingTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sPSOutgoingTransactions).toMatchObject(sPSOutgoingTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sPSOutgoingTransactionsServiceStub.find.resolves(sPSOutgoingTransactionsSample);
        const wrapper = shallowMount(SPSOutgoingTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
