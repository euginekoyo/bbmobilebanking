/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ServiceManagementDetails from './service-management-details.vue';
import ServiceManagementService from './service-management.service';
import AlertService from '@/shared/alert/alert.service';

type ServiceManagementDetailsComponentType = InstanceType<typeof ServiceManagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const serviceManagementSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ServiceManagement Management Detail Component', () => {
    let serviceManagementServiceStub: SinonStubbedInstance<ServiceManagementService>;
    let mountOptions: MountingOptions<ServiceManagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      serviceManagementServiceStub = sinon.createStubInstance<ServiceManagementService>(ServiceManagementService);

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
          serviceManagementService: () => serviceManagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        serviceManagementServiceStub.find.resolves(serviceManagementSample);
        route = {
          params: {
            serviceManagementId: `${123}`,
          },
        };
        const wrapper = shallowMount(ServiceManagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.serviceManagement).toMatchObject(serviceManagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        serviceManagementServiceStub.find.resolves(serviceManagementSample);
        const wrapper = shallowMount(ServiceManagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
