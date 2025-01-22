/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SPSIncomingTransactions from './sps-incoming-transactions.vue';
import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type SPSIncomingTransactionsComponentType = InstanceType<typeof SPSIncomingTransactions>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SPSIncomingTransactions Management Component', () => {
    let sPSIncomingTransactionsServiceStub: SinonStubbedInstance<SPSIncomingTransactionsService>;
    let mountOptions: MountingOptions<SPSIncomingTransactionsComponentType>['global'];

    beforeEach(() => {
      sPSIncomingTransactionsServiceStub = sinon.createStubInstance<SPSIncomingTransactionsService>(SPSIncomingTransactionsService);
      sPSIncomingTransactionsServiceStub.retrieve.resolves({ headers: {} });

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
          sPSIncomingTransactionsService: () => sPSIncomingTransactionsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSIncomingTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SPSIncomingTransactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(sPSIncomingTransactionsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.sPSIncomingTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SPSIncomingTransactionsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SPSIncomingTransactions, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        sPSIncomingTransactionsServiceStub.retrieve.reset();
        sPSIncomingTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        sPSIncomingTransactionsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSPSIncomingTransactions();
        await comp.$nextTick(); // clear components

        // THEN
        expect(sPSIncomingTransactionsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(sPSIncomingTransactionsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
