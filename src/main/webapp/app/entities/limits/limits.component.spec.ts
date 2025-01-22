/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Limits from './limits.vue';
import LimitsService from './limits.service';
import AlertService from '@/shared/alert/alert.service';

type LimitsComponentType = InstanceType<typeof Limits>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Limits Management Component', () => {
    let limitsServiceStub: SinonStubbedInstance<LimitsService>;
    let mountOptions: MountingOptions<LimitsComponentType>['global'];

    beforeEach(() => {
      limitsServiceStub = sinon.createStubInstance<LimitsService>(LimitsService);
      limitsServiceStub.retrieve.resolves({ headers: {} });

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
          limitsService: () => limitsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        limitsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Limits, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(limitsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.limits[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: LimitsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Limits, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        limitsServiceStub.retrieve.reset();
        limitsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        limitsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeLimits();
        await comp.$nextTick(); // clear components

        // THEN
        expect(limitsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(limitsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
