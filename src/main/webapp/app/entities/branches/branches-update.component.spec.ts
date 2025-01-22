/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import BranchesUpdate from './branches-update.vue';
import BranchesService from './branches.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type BranchesUpdateComponentType = InstanceType<typeof BranchesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const branchesSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<BranchesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Branches Management Update Component', () => {
    let comp: BranchesUpdateComponentType;
    let branchesServiceStub: SinonStubbedInstance<BranchesService>;

    beforeEach(() => {
      route = {};
      branchesServiceStub = sinon.createStubInstance<BranchesService>(BranchesService);
      branchesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          branchesService: () => branchesServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(BranchesUpdate, { global: mountOptions });
        comp = wrapper.vm;
      });
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(BranchesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.branches = branchesSample;
        branchesServiceStub.update.resolves(branchesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(branchesServiceStub.update.calledWith(branchesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        branchesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(BranchesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.branches = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(branchesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        branchesServiceStub.find.resolves(branchesSample);
        branchesServiceStub.retrieve.resolves([branchesSample]);

        // WHEN
        route = {
          params: {
            branchesId: `${branchesSample.id}`,
          },
        };
        const wrapper = shallowMount(BranchesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.branches).toMatchObject(branchesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        branchesServiceStub.find.resolves(branchesSample);
        const wrapper = shallowMount(BranchesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
