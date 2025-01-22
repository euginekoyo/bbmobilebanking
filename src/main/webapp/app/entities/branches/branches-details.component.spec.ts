/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import BranchesDetails from './branches-details.vue';
import BranchesService from './branches.service';
import AlertService from '@/shared/alert/alert.service';

type BranchesDetailsComponentType = InstanceType<typeof BranchesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const branchesSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Branches Management Detail Component', () => {
    let branchesServiceStub: SinonStubbedInstance<BranchesService>;
    let mountOptions: MountingOptions<BranchesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      branchesServiceStub = sinon.createStubInstance<BranchesService>(BranchesService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          branchesService: () => branchesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        branchesServiceStub.find.resolves(branchesSample);
        route = {
          params: {
            branchesId: `${123}`,
          },
        };
        const wrapper = shallowMount(BranchesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.branches).toMatchObject(branchesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        branchesServiceStub.find.resolves(branchesSample);
        const wrapper = shallowMount(BranchesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
