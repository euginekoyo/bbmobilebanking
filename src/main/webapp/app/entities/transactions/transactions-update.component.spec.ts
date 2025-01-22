/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import TransactionsUpdate from './transactions-update.vue';
import TransactionsService from './transactions.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type TransactionsUpdateComponentType = InstanceType<typeof TransactionsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const transactionsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TransactionsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Transactions Management Update Component', () => {
    let comp: TransactionsUpdateComponentType;
    let transactionsServiceStub: SinonStubbedInstance<TransactionsService>;

    beforeEach(() => {
      route = {};
      transactionsServiceStub = sinon.createStubInstance<TransactionsService>(TransactionsService);
      transactionsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          transactionsService: () => transactionsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(TransactionsUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(TransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.transactions = transactionsSample;
        transactionsServiceStub.update.resolves(transactionsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionsServiceStub.update.calledWith(transactionsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        transactionsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.transactions = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        transactionsServiceStub.find.resolves(transactionsSample);
        transactionsServiceStub.retrieve.resolves([transactionsSample]);

        // WHEN
        route = {
          params: {
            transactionsId: `${transactionsSample.id}`,
          },
        };
        const wrapper = shallowMount(TransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.transactions).toMatchObject(transactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        transactionsServiceStub.find.resolves(transactionsSample);
        const wrapper = shallowMount(TransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
