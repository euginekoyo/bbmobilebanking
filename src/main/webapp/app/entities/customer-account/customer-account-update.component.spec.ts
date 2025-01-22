/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import CustomerAccountUpdate from './customer-account-update.vue';
import CustomerAccountService from './customer-account.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type CustomerAccountUpdateComponentType = InstanceType<typeof CustomerAccountUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customerAccountSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CustomerAccountUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CustomerAccount Management Update Component', () => {
    let comp: CustomerAccountUpdateComponentType;
    let customerAccountServiceStub: SinonStubbedInstance<CustomerAccountService>;

    beforeEach(() => {
      route = {};
      customerAccountServiceStub = sinon.createStubInstance<CustomerAccountService>(CustomerAccountService);
      customerAccountServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          customerAccountService: () => customerAccountServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(CustomerAccountUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(CustomerAccountUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customerAccount = customerAccountSample;
        customerAccountServiceStub.update.resolves(customerAccountSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customerAccountServiceStub.update.calledWith(customerAccountSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        customerAccountServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CustomerAccountUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customerAccount = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customerAccountServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        customerAccountServiceStub.find.resolves(customerAccountSample);
        customerAccountServiceStub.retrieve.resolves([customerAccountSample]);

        // WHEN
        route = {
          params: {
            customerAccountId: `${customerAccountSample.id}`,
          },
        };
        const wrapper = shallowMount(CustomerAccountUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.customerAccount).toMatchObject(customerAccountSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customerAccountServiceStub.find.resolves(customerAccountSample);
        const wrapper = shallowMount(CustomerAccountUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
