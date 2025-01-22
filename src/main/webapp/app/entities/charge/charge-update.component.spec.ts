/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import ChargeUpdate from './charge-update.vue';
import ChargeService from './charge.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

import ChargeRangeService from '@/entities/charge-range/charge-range.service';
import RangeService from '@/entities/range/range.service';

type ChargeUpdateComponentType = InstanceType<typeof ChargeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const chargeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ChargeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Charge Management Update Component', () => {
    let comp: ChargeUpdateComponentType;
    let chargeServiceStub: SinonStubbedInstance<ChargeService>;

    beforeEach(() => {
      route = {};
      chargeServiceStub = sinon.createStubInstance<ChargeService>(ChargeService);
      chargeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          chargeService: () => chargeServiceStub,
          chargeRangeService: () =>
            sinon.createStubInstance<ChargeRangeService>(ChargeRangeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          rangeService: () =>
            sinon.createStubInstance<RangeService>(RangeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(ChargeUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(ChargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.charge = chargeSample;
        chargeServiceStub.update.resolves(chargeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(chargeServiceStub.update.calledWith(chargeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        chargeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ChargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.charge = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(chargeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        chargeServiceStub.find.resolves(chargeSample);
        chargeServiceStub.retrieve.resolves([chargeSample]);

        // WHEN
        route = {
          params: {
            chargeId: `${chargeSample.id}`,
          },
        };
        const wrapper = shallowMount(ChargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.charge).toMatchObject(chargeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        chargeServiceStub.find.resolves(chargeSample);
        const wrapper = shallowMount(ChargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
