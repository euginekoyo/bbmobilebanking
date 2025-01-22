/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SPSOutgoingTransactions from './sps-outgoing-transactions.vue';
import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type SPSOutgoingTransactionsComponentType = InstanceType<typeof SPSOutgoingTransactions>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SPSOutgoingTransactions Management Component', () => {
    let sPSOutgoingTransactionsServiceStub: SinonStubbedInstance<SPSOutgoingTransactionsService>;
    let mountOptions: MountingOptions<SPSOutgoingTransactionsComponentType>['global'];

    beforeEach(() => {
      sPSOutgoingTransactionsServiceStub = sinon.createStubInstance<SPSOutgoingTransactionsService>(SPSOutgoingTransactionsService);
      sPSOutgoingTransactionsServiceStub.retrieve.resolves({ headers: {} });

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
          sPSOutgoingTransactionsService: () => sPSOutgoingTransactionsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSOutgoingTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SPSOutgoingTransactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(sPSOutgoingTransactionsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.sPSOutgoingTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SPSOutgoingTransactionsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SPSOutgoingTransactions, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        sPSOutgoingTransactionsServiceStub.retrieve.reset();
        sPSOutgoingTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        sPSOutgoingTransactionsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSPSOutgoingTransactions();
        await comp.$nextTick(); // clear components

        // THEN
        expect(sPSOutgoingTransactionsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(sPSOutgoingTransactionsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
