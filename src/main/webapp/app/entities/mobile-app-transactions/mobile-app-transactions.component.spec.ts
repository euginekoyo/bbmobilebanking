/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MobileAppTransactions from './mobile-app-transactions.vue';
import MobileAppTransactionsService from './mobile-app-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type MobileAppTransactionsComponentType = InstanceType<typeof MobileAppTransactions>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MobileAppTransactions Management Component', () => {
    let mobileAppTransactionsServiceStub: SinonStubbedInstance<MobileAppTransactionsService>;
    let mountOptions: MountingOptions<MobileAppTransactionsComponentType>['global'];

    beforeEach(() => {
      mobileAppTransactionsServiceStub = sinon.createStubInstance<MobileAppTransactionsService>(MobileAppTransactionsService);
      mobileAppTransactionsServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          mobileAppTransactionsService: () => mobileAppTransactionsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mobileAppTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MobileAppTransactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.mobileAppTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(MobileAppTransactions, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: MobileAppTransactionsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MobileAppTransactions, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        mobileAppTransactionsServiceStub.retrieve.reset();
        mobileAppTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        mobileAppTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.retrieve.called).toBeTruthy();
        expect(comp.mobileAppTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(mobileAppTransactionsServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        mobileAppTransactionsServiceStub.retrieve.reset();
        mobileAppTransactionsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(mobileAppTransactionsServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.mobileAppTransactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        mobileAppTransactionsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMobileAppTransactions();
        await comp.$nextTick(); // clear components

        // THEN
        expect(mobileAppTransactionsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(mobileAppTransactionsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
