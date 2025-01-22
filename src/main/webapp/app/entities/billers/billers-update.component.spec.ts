/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import BillersUpdate from './billers-update.vue';
import BillersService from './billers.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type BillersUpdateComponentType = InstanceType<typeof BillersUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const billersSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<BillersUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Billers Management Update Component', () => {
    let comp: BillersUpdateComponentType;
    let billersServiceStub: SinonStubbedInstance<BillersService>;

    beforeEach(() => {
      route = {};
      billersServiceStub = sinon.createStubInstance<BillersService>(BillersService);
      billersServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          billersService: () => billersServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(BillersUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(BillersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.billers = billersSample;
        billersServiceStub.update.resolves(billersSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(billersServiceStub.update.calledWith(billersSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        billersServiceStub.create.resolves(entity);
        const wrapper = shallowMount(BillersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.billers = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(billersServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        billersServiceStub.find.resolves(billersSample);
        billersServiceStub.retrieve.resolves([billersSample]);

        // WHEN
        route = {
          params: {
            billersId: `${billersSample.id}`,
          },
        };
        const wrapper = shallowMount(BillersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.billers).toMatchObject(billersSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        billersServiceStub.find.resolves(billersSample);
        const wrapper = shallowMount(BillersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
