/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CBSTransactionsDetails from './cbs-transactions-details.vue';
import CBSTransactionsService from './cbs-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type CBSTransactionsDetailsComponentType = InstanceType<typeof CBSTransactionsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const cBSTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CBSTransactions Management Detail Component', () => {
    let cBSTransactionsServiceStub: SinonStubbedInstance<CBSTransactionsService>;
    let mountOptions: MountingOptions<CBSTransactionsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      cBSTransactionsServiceStub = sinon.createStubInstance<CBSTransactionsService>(CBSTransactionsService);

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
          cBSTransactionsService: () => cBSTransactionsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        cBSTransactionsServiceStub.find.resolves(cBSTransactionsSample);
        route = {
          params: {
            cBSTransactionsId: `${123}`,
          },
        };
        const wrapper = shallowMount(CBSTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.cBSTransactions).toMatchObject(cBSTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        cBSTransactionsServiceStub.find.resolves(cBSTransactionsSample);
        const wrapper = shallowMount(CBSTransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
