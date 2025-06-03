/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MobileAppTransactionsUpdate from './mobile-app-transactions-update.vue';
import MobileAppTransactionsService from './mobile-app-transactions.service';
import AlertService from '@/shared/alert/alert.service';

type MobileAppTransactionsUpdateComponentType = InstanceType<typeof MobileAppTransactionsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mobileAppTransactionsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MobileAppTransactionsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MobileAppTransactions Management Update Component', () => {
    let comp: MobileAppTransactionsUpdateComponentType;
    let mobileAppTransactionsServiceStub: SinonStubbedInstance<MobileAppTransactionsService>;

    beforeEach(() => {
      route = {};
      mobileAppTransactionsServiceStub = sinon.createStubInstance<MobileAppTransactionsService>(MobileAppTransactionsService);
      mobileAppTransactionsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          mobileAppTransactionsService: () => mobileAppTransactionsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(MobileAppTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mobileAppTransactions = mobileAppTransactionsSample;
        mobileAppTransactionsServiceStub.update.resolves(mobileAppTransactionsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.update.calledWith(mobileAppTransactionsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        mobileAppTransactionsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MobileAppTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mobileAppTransactions = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mobileAppTransactionsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        mobileAppTransactionsServiceStub.find.resolves(mobileAppTransactionsSample);
        mobileAppTransactionsServiceStub.retrieve.resolves([mobileAppTransactionsSample]);

        // WHEN
        route = {
          params: {
            mobileAppTransactionsId: `${mobileAppTransactionsSample.id}`,
          },
        };
        const wrapper = shallowMount(MobileAppTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.mobileAppTransactions).toMatchObject(mobileAppTransactionsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mobileAppTransactionsServiceStub.find.resolves(mobileAppTransactionsSample);
        const wrapper = shallowMount(MobileAppTransactionsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
