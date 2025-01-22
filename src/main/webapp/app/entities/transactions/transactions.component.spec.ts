/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Transactions from './transactions.vue';
import TransactionsService from './transactions.service';
import AlertService from '@/shared/alert/alert.service';

type TransactionsComponentType = InstanceType<typeof Transactions>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Transactions Management Component', () => {
    let transactionsServiceStub: SinonStubbedInstance<TransactionsService>;
    let mountOptions: MountingOptions<TransactionsComponentType>['global'];

    beforeEach(() => {
      transactionsServiceStub = sinon.createStubInstance<TransactionsService>(TransactionsService);
      transactionsServiceStub.retrieve.resolves({ headers: {} });

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
          transactionsService: () => transactionsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        transactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Transactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(transactionsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.transactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TransactionsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Transactions, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        transactionsServiceStub.retrieve.reset();
        transactionsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        transactionsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTransactions();
        await comp.$nextTick(); // clear components

        // THEN
        expect(transactionsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(transactionsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
