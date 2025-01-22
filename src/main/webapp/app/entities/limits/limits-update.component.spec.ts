/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LimitsUpdate from './limits-update.vue';
import LimitsService from './limits.service';
import AlertService from '@/shared/alert/alert.service';

type LimitsUpdateComponentType = InstanceType<typeof LimitsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const limitsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<LimitsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Limits Management Update Component', () => {
    let comp: LimitsUpdateComponentType;
    let limitsServiceStub: SinonStubbedInstance<LimitsService>;

    beforeEach(() => {
      route = {};
      limitsServiceStub = sinon.createStubInstance<LimitsService>(LimitsService);
      limitsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          limitsService: () => limitsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(LimitsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.limits = limitsSample;
        limitsServiceStub.update.resolves(limitsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(limitsServiceStub.update.calledWith(limitsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        limitsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(LimitsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.limits = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(limitsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        limitsServiceStub.find.resolves(limitsSample);
        limitsServiceStub.retrieve.resolves([limitsSample]);

        // WHEN
        route = {
          params: {
            limitsId: `${limitsSample.id}`,
          },
        };
        const wrapper = shallowMount(LimitsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.limits).toMatchObject(limitsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        limitsServiceStub.find.resolves(limitsSample);
        const wrapper = shallowMount(LimitsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
