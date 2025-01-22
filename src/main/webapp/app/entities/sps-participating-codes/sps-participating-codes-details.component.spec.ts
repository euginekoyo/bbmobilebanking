/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SPSParticipatingCodesDetails from './sps-participating-codes-details.vue';
import SPSParticipatingCodesService from './sps-participating-codes.service';
import AlertService from '@/shared/alert/alert.service';

type SPSParticipatingCodesDetailsComponentType = InstanceType<typeof SPSParticipatingCodesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sPSParticipatingCodesSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SPSParticipatingCodes Management Detail Component', () => {
    let sPSParticipatingCodesServiceStub: SinonStubbedInstance<SPSParticipatingCodesService>;
    let mountOptions: MountingOptions<SPSParticipatingCodesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sPSParticipatingCodesServiceStub = sinon.createStubInstance<SPSParticipatingCodesService>(SPSParticipatingCodesService);

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
          sPSParticipatingCodesService: () => sPSParticipatingCodesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSParticipatingCodesServiceStub.find.resolves(sPSParticipatingCodesSample);
        route = {
          params: {
            sPSParticipatingCodesId: `${123}`,
          },
        };
        const wrapper = shallowMount(SPSParticipatingCodesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sPSParticipatingCodes).toMatchObject(sPSParticipatingCodesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sPSParticipatingCodesServiceStub.find.resolves(sPSParticipatingCodesSample);
        const wrapper = shallowMount(SPSParticipatingCodesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
