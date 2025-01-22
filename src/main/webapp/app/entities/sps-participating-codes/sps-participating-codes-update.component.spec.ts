/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SPSParticipatingCodesUpdate from './sps-participating-codes-update.vue';
import SPSParticipatingCodesService from './sps-participating-codes.service';
import AlertService from '@/shared/alert/alert.service';

type SPSParticipatingCodesUpdateComponentType = InstanceType<typeof SPSParticipatingCodesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sPSParticipatingCodesSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SPSParticipatingCodesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SPSParticipatingCodes Management Update Component', () => {
    let comp: SPSParticipatingCodesUpdateComponentType;
    let sPSParticipatingCodesServiceStub: SinonStubbedInstance<SPSParticipatingCodesService>;

    beforeEach(() => {
      route = {};
      sPSParticipatingCodesServiceStub = sinon.createStubInstance<SPSParticipatingCodesService>(SPSParticipatingCodesService);
      sPSParticipatingCodesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          sPSParticipatingCodesService: () => sPSParticipatingCodesServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SPSParticipatingCodesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sPSParticipatingCodes = sPSParticipatingCodesSample;
        sPSParticipatingCodesServiceStub.update.resolves(sPSParticipatingCodesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sPSParticipatingCodesServiceStub.update.calledWith(sPSParticipatingCodesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sPSParticipatingCodesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SPSParticipatingCodesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sPSParticipatingCodes = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sPSParticipatingCodesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sPSParticipatingCodesServiceStub.find.resolves(sPSParticipatingCodesSample);
        sPSParticipatingCodesServiceStub.retrieve.resolves([sPSParticipatingCodesSample]);

        // WHEN
        route = {
          params: {
            sPSParticipatingCodesId: `${sPSParticipatingCodesSample.id}`,
          },
        };
        const wrapper = shallowMount(SPSParticipatingCodesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.sPSParticipatingCodes).toMatchObject(sPSParticipatingCodesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sPSParticipatingCodesServiceStub.find.resolves(sPSParticipatingCodesSample);
        const wrapper = shallowMount(SPSParticipatingCodesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
