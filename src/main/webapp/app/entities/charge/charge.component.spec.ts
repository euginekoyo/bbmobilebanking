/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Charge from './charge.vue';
import ChargeService from './charge.service';
import AlertService from '@/shared/alert/alert.service';

type ChargeComponentType = InstanceType<typeof Charge>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Charge Management Component', () => {
    let chargeServiceStub: SinonStubbedInstance<ChargeService>;
    let mountOptions: MountingOptions<ChargeComponentType>['global'];

    beforeEach(() => {
      chargeServiceStub = sinon.createStubInstance<ChargeService>(ChargeService);
      chargeServiceStub.retrieve.resolves({ headers: {} });

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
          chargeService: () => chargeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        chargeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Charge, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(chargeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.charges[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ChargeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Charge, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        chargeServiceStub.retrieve.reset();
        chargeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        chargeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCharge();
        await comp.$nextTick(); // clear components

        // THEN
        expect(chargeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(chargeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
