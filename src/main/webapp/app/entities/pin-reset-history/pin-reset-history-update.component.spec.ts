/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PinResetHistoryUpdate from './pin-reset-history-update.vue';
import PinResetHistoryService from './pin-reset-history.service';
import AlertService from '@/shared/alert/alert.service';

type PinResetHistoryUpdateComponentType = InstanceType<typeof PinResetHistoryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pinResetHistorySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PinResetHistoryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PinResetHistory Management Update Component', () => {
    let comp: PinResetHistoryUpdateComponentType;
    let pinResetHistoryServiceStub: SinonStubbedInstance<PinResetHistoryService>;

    beforeEach(() => {
      route = {};
      pinResetHistoryServiceStub = sinon.createStubInstance<PinResetHistoryService>(PinResetHistoryService);
      pinResetHistoryServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          pinResetHistoryService: () => pinResetHistoryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PinResetHistoryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pinResetHistory = pinResetHistorySample;
        pinResetHistoryServiceStub.update.resolves(pinResetHistorySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pinResetHistoryServiceStub.update.calledWith(pinResetHistorySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        pinResetHistoryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PinResetHistoryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pinResetHistory = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pinResetHistoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        pinResetHistoryServiceStub.find.resolves(pinResetHistorySample);
        pinResetHistoryServiceStub.retrieve.resolves([pinResetHistorySample]);

        // WHEN
        route = {
          params: {
            pinResetHistoryId: `${pinResetHistorySample.id}`,
          },
        };
        const wrapper = shallowMount(PinResetHistoryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.pinResetHistory).toMatchObject(pinResetHistorySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pinResetHistoryServiceStub.find.resolves(pinResetHistorySample);
        const wrapper = shallowMount(PinResetHistoryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
