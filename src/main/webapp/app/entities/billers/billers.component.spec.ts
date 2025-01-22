/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Billers from './billers.vue';
import BillersService from './billers.service';
import AlertService from '@/shared/alert/alert.service';

type BillersComponentType = InstanceType<typeof Billers>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Billers Management Component', () => {
    let billersServiceStub: SinonStubbedInstance<BillersService>;
    let mountOptions: MountingOptions<BillersComponentType>['global'];

    beforeEach(() => {
      billersServiceStub = sinon.createStubInstance<BillersService>(BillersService);
      billersServiceStub.retrieve.resolves({ headers: {} });

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
          billersService: () => billersServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        billersServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Billers, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(billersServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.billers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: BillersComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Billers, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        billersServiceStub.retrieve.reset();
        billersServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        billersServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeBillers();
        await comp.$nextTick(); // clear components

        // THEN
        expect(billersServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(billersServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
