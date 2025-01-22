/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ServiceManagementUpdate from './service-management-update.vue';
import ServiceManagementService from './service-management.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type ServiceManagementUpdateComponentType = InstanceType<typeof ServiceManagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const serviceManagementSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ServiceManagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ServiceManagement Management Update Component', () => {
    let comp: ServiceManagementUpdateComponentType;
    let serviceManagementServiceStub: SinonStubbedInstance<ServiceManagementService>;

    beforeEach(() => {
      route = {};
      serviceManagementServiceStub = sinon.createStubInstance<ServiceManagementService>(ServiceManagementService);
      serviceManagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          serviceManagementService: () => serviceManagementServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ServiceManagementUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ServiceManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.serviceManagement = serviceManagementSample;
        serviceManagementServiceStub.update.resolves(serviceManagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(serviceManagementServiceStub.update.calledWith(serviceManagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        serviceManagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ServiceManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.serviceManagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(serviceManagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        serviceManagementServiceStub.find.resolves(serviceManagementSample);
        serviceManagementServiceStub.retrieve.resolves([serviceManagementSample]);

        // WHEN
        route = {
          params: {
            serviceManagementId: `${serviceManagementSample.id}`,
          },
        };
        const wrapper = shallowMount(ServiceManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.serviceManagement).toMatchObject(serviceManagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        serviceManagementServiceStub.find.resolves(serviceManagementSample);
        const wrapper = shallowMount(ServiceManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
