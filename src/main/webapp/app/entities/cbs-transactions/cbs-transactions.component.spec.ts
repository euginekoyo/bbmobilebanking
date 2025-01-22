/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CBSTransactions from './cbs-transactions.vue';
import CBSTransactionsService from './cbs-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type CBSTransactionsComponentType = InstanceType<typeof CBSTransactions>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CBSTransactions Management Component', () => {
    let cBSTransactionsServiceStub: SinonStubbedInstance<CBSTransactionsService>;
    let mountOptions: MountingOptions<CBSTransactionsComponentType>['global'];

    beforeEach(() => {
      cBSTransactionsServiceStub = sinon.createStubInstance<CBSTransactionsService>(CBSTransactionsService);
      cBSTransactionsServiceStub.retrieve.resolves({ headers: {} });

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
          cBSTransactionsService: () => cBSTransactionsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        cBSTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CBSTransactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(cBSTransactionsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.cBSTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CBSTransactionsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CBSTransactions, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        cBSTransactionsServiceStub.retrieve.reset();
        cBSTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        cBSTransactionsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCBSTransactions();
        await comp.$nextTick(); // clear components

        // THEN
        expect(cBSTransactionsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(cBSTransactionsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
