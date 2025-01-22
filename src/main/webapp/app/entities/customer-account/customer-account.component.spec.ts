/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CustomerAccount from './customer-account.vue';
import CustomerAccountService from './customer-account.service';
import AlertService from '@/shared/alert/alert.service';

type CustomerAccountComponentType = InstanceType<typeof CustomerAccount>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CustomerAccount Management Component', () => {
    let customerAccountServiceStub: SinonStubbedInstance<CustomerAccountService>;
    let mountOptions: MountingOptions<CustomerAccountComponentType>['global'];

    beforeEach(() => {
      customerAccountServiceStub = sinon.createStubInstance<CustomerAccountService>(CustomerAccountService);
      customerAccountServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          customerAccountService: () => customerAccountServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customerAccountServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CustomerAccount, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(customerAccountServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.customerAccounts[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CustomerAccountComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CustomerAccount, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        customerAccountServiceStub.retrieve.reset();
        customerAccountServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        customerAccountServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCustomerAccount();
        await comp.$nextTick(); // clear components

        // THEN
        expect(customerAccountServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(customerAccountServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
