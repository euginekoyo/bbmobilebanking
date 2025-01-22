/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CustomerAccountDetails from './customer-account-details.vue';
import CustomerAccountService from './customer-account.service';
import AlertService from '@/shared/alert/alert.service';

type CustomerAccountDetailsComponentType = InstanceType<typeof CustomerAccountDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customerAccountSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CustomerAccount Management Detail Component', () => {
    let customerAccountServiceStub: SinonStubbedInstance<CustomerAccountService>;
    let mountOptions: MountingOptions<CustomerAccountDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      customerAccountServiceStub = sinon.createStubInstance<CustomerAccountService>(CustomerAccountService);

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
          customerAccountService: () => customerAccountServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customerAccountServiceStub.find.resolves(customerAccountSample);
        route = {
          params: {
            customerAccountId: `${123}`,
          },
        };
        const wrapper = shallowMount(CustomerAccountDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.customerAccount).toMatchObject(customerAccountSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customerAccountServiceStub.find.resolves(customerAccountSample);
        const wrapper = shallowMount(CustomerAccountDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
