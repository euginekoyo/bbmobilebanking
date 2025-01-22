/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ChargeRangeUpdate from './charge-range-update.vue';
import ChargeRangeService from './charge-range.service';
import AlertService from '@/shared/alert/alert.service';

type ChargeRangeUpdateComponentType = InstanceType<typeof ChargeRangeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const chargeRangeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ChargeRangeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ChargeRange Management Update Component', () => {
    let comp: ChargeRangeUpdateComponentType;
    let chargeRangeServiceStub: SinonStubbedInstance<ChargeRangeService>;

    beforeEach(() => {
      route = {};
      chargeRangeServiceStub = sinon.createStubInstance<ChargeRangeService>(ChargeRangeService);
      chargeRangeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          chargeRangeService: () => chargeRangeServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ChargeRangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.chargeRange = chargeRangeSample;
        chargeRangeServiceStub.update.resolves(chargeRangeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(chargeRangeServiceStub.update.calledWith(chargeRangeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        chargeRangeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ChargeRangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.chargeRange = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(chargeRangeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        chargeRangeServiceStub.find.resolves(chargeRangeSample);
        chargeRangeServiceStub.retrieve.resolves([chargeRangeSample]);

        // WHEN
        route = {
          params: {
            chargeRangeId: `${chargeRangeSample.id}`,
          },
        };
        const wrapper = shallowMount(ChargeRangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.chargeRange).toMatchObject(chargeRangeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        chargeRangeServiceStub.find.resolves(chargeRangeSample);
        const wrapper = shallowMount(ChargeRangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
