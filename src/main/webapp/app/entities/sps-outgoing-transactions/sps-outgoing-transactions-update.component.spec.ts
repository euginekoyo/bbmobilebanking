/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import SPSOutgoingTransactionsUpdate from './sps-outgoing-transactions-update.vue';
import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type SPSOutgoingTransactionsUpdateComponentType = InstanceType<typeof SPSOutgoingTransactionsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sPSOutgoingTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SPSOutgoingTransactionsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SPSOutgoingTransactions Management Update Component', () => {
    let comp: SPSOutgoingTransactionsUpdateComponentType;
    let sPSOutgoingTransactionsServiceStub: SinonStubbedInstance<SPSOutgoingTransactionsService>;

    beforeEach(() => {
      route = {};
      sPSOutgoingTransactionsServiceStub = sinon.createStubInstance<SPSOutgoingTransactionsService>(SPSOutgoingTransactionsService);
      sPSOutgoingTransactionsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          sPSOutgoingTransactionsService: () => sPSOutgoingTransactionsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(SPSOutgoingTransactionsUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(SPSOutgoingTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sPSOutgoingTransactions = sPSOutgoingTransactionsSample;
        sPSOutgoingTransactionsServiceStub.update.resolves(sPSOutgoingTransactionsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sPSOutgoingTransactionsServiceStub.update.calledWith(sPSOutgoingTransactionsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sPSOutgoingTransactionsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SPSOutgoingTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sPSOutgoingTransactions = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sPSOutgoingTransactionsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sPSOutgoingTransactionsServiceStub.find.resolves(sPSOutgoingTransactionsSample);
        sPSOutgoingTransactionsServiceStub.retrieve.resolves([sPSOutgoingTransactionsSample]);

        // WHEN
        route = {
          params: {
            sPSOutgoingTransactionsId: `${sPSOutgoingTransactionsSample.id}`,
          },
        };
        const wrapper = shallowMount(SPSOutgoingTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.sPSOutgoingTransactions).toMatchObject(sPSOutgoingTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sPSOutgoingTransactionsServiceStub.find.resolves(sPSOutgoingTransactionsSample);
        const wrapper = shallowMount(SPSOutgoingTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
