/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import CBSTransactionsUpdate from './cbs-transactions-update.vue';
import CBSTransactionsService from './cbs-transactions.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type CBSTransactionsUpdateComponentType = InstanceType<typeof CBSTransactionsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const cBSTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CBSTransactionsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CBSTransactions Management Update Component', () => {
    let comp: CBSTransactionsUpdateComponentType;
    let cBSTransactionsServiceStub: SinonStubbedInstance<CBSTransactionsService>;

    beforeEach(() => {
      route = {};
      cBSTransactionsServiceStub = sinon.createStubInstance<CBSTransactionsService>(CBSTransactionsService);
      cBSTransactionsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          cBSTransactionsService: () => cBSTransactionsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(CBSTransactionsUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(CBSTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.cBSTransactions = cBSTransactionsSample;
        cBSTransactionsServiceStub.update.resolves(cBSTransactionsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cBSTransactionsServiceStub.update.calledWith(cBSTransactionsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        cBSTransactionsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CBSTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.cBSTransactions = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cBSTransactionsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        cBSTransactionsServiceStub.find.resolves(cBSTransactionsSample);
        cBSTransactionsServiceStub.retrieve.resolves([cBSTransactionsSample]);

        // WHEN
        route = {
          params: {
            cBSTransactionsId: `${cBSTransactionsSample.id}`,
          },
        };
        const wrapper = shallowMount(CBSTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.cBSTransactions).toMatchObject(cBSTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        cBSTransactionsServiceStub.find.resolves(cBSTransactionsSample);
        const wrapper = shallowMount(CBSTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
