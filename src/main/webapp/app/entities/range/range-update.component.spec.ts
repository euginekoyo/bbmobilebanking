/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RangeUpdate from './range-update.vue';
import RangeService from './range.service';
import AlertService from '@/shared/alert/alert.service';

type RangeUpdateComponentType = InstanceType<typeof RangeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rangeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RangeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Range Management Update Component', () => {
    let comp: RangeUpdateComponentType;
    let rangeServiceStub: SinonStubbedInstance<RangeService>;

    beforeEach(() => {
      route = {};
      rangeServiceStub = sinon.createStubInstance<RangeService>(RangeService);
      rangeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          rangeService: () => rangeServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.range = rangeSample;
        rangeServiceStub.update.resolves(rangeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rangeServiceStub.update.calledWith(rangeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        rangeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.range = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rangeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        rangeServiceStub.find.resolves(rangeSample);
        rangeServiceStub.retrieve.resolves([rangeSample]);

        // WHEN
        route = {
          params: {
            rangeId: `${rangeSample.id}`,
          },
        };
        const wrapper = shallowMount(RangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.range).toMatchObject(rangeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rangeServiceStub.find.resolves(rangeSample);
        const wrapper = shallowMount(RangeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
