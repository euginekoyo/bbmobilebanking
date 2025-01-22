/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TransactionsDetails from './transactions-details.vue';
import TransactionsService from './transactions.service';
import AlertService from '@/shared/alert/alert.service';

type TransactionsDetailsComponentType = InstanceType<typeof TransactionsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const transactionsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Transactions Management Detail Component', () => {
    let transactionsServiceStub: SinonStubbedInstance<TransactionsService>;
    let mountOptions: MountingOptions<TransactionsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      transactionsServiceStub = sinon.createStubInstance<TransactionsService>(TransactionsService);

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
          transactionsService: () => transactionsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        transactionsServiceStub.find.resolves(transactionsSample);
        route = {
          params: {
            transactionsId: `${123}`,
          },
        };
        const wrapper = shallowMount(TransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.transactions).toMatchObject(transactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        transactionsServiceStub.find.resolves(transactionsSample);
        const wrapper = shallowMount(TransactionsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
