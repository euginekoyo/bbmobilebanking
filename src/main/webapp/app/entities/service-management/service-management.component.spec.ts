/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ServiceManagement from './service-management.vue';
import ServiceManagementService from './service-management.service';
import AlertService from '@/shared/alert/alert.service';

type ServiceManagementComponentType = InstanceType<typeof ServiceManagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ServiceManagement Management Component', () => {
    let serviceManagementServiceStub: SinonStubbedInstance<ServiceManagementService>;
    let mountOptions: MountingOptions<ServiceManagementComponentType>['global'];

    beforeEach(() => {
      serviceManagementServiceStub = sinon.createStubInstance<ServiceManagementService>(ServiceManagementService);
      serviceManagementServiceStub.retrieve.resolves({ headers: {} });

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
          serviceManagementService: () => serviceManagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        serviceManagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ServiceManagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(serviceManagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.serviceManagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ServiceManagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ServiceManagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        serviceManagementServiceStub.retrieve.reset();
        serviceManagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        serviceManagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeServiceManagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(serviceManagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(serviceManagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
