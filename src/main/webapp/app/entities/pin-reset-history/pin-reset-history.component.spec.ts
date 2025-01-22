/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import PinResetHistory from './pin-reset-history.vue';
import PinResetHistoryService from './pin-reset-history.service';
import AlertService from '@/shared/alert/alert.service';

type PinResetHistoryComponentType = InstanceType<typeof PinResetHistory>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PinResetHistory Management Component', () => {
    let pinResetHistoryServiceStub: SinonStubbedInstance<PinResetHistoryService>;
    let mountOptions: MountingOptions<PinResetHistoryComponentType>['global'];

    beforeEach(() => {
      pinResetHistoryServiceStub = sinon.createStubInstance<PinResetHistoryService>(PinResetHistoryService);
      pinResetHistoryServiceStub.retrieve.resolves({ headers: {} });

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
          pinResetHistoryService: () => pinResetHistoryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pinResetHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(PinResetHistory, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(pinResetHistoryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.pinResetHistories[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PinResetHistoryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PinResetHistory, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        pinResetHistoryServiceStub.retrieve.reset();
        pinResetHistoryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        pinResetHistoryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePinResetHistory();
        await comp.$nextTick(); // clear components

        // THEN
        expect(pinResetHistoryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(pinResetHistoryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
