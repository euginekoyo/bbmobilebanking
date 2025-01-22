/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ChargeRange from './charge-range.vue';
import ChargeRangeService from './charge-range.service';
import AlertService from '@/shared/alert/alert.service';

type ChargeRangeComponentType = InstanceType<typeof ChargeRange>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ChargeRange Management Component', () => {
    let chargeRangeServiceStub: SinonStubbedInstance<ChargeRangeService>;
    let mountOptions: MountingOptions<ChargeRangeComponentType>['global'];

    beforeEach(() => {
      chargeRangeServiceStub = sinon.createStubInstance<ChargeRangeService>(ChargeRangeService);
      chargeRangeServiceStub.retrieve.resolves({ headers: {} });

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
          chargeRangeService: () => chargeRangeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        chargeRangeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ChargeRange, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(chargeRangeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.chargeRanges[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ChargeRangeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ChargeRange, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        chargeRangeServiceStub.retrieve.reset();
        chargeRangeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        chargeRangeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeChargeRange();
        await comp.$nextTick(); // clear components

        // THEN
        expect(chargeRangeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(chargeRangeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
