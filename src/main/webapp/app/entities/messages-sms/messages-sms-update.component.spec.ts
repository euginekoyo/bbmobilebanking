/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import MessagesSmsUpdate from './messages-sms-update.vue';
import MessagesSmsService from './messages-sms.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type MessagesSmsUpdateComponentType = InstanceType<typeof MessagesSmsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const messagesSmsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MessagesSmsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MessagesSms Management Update Component', () => {
    let comp: MessagesSmsUpdateComponentType;
    let messagesSmsServiceStub: SinonStubbedInstance<MessagesSmsService>;

    beforeEach(() => {
      route = {};
      messagesSmsServiceStub = sinon.createStubInstance<MessagesSmsService>(MessagesSmsService);
      messagesSmsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          messagesSmsService: () => messagesSmsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(MessagesSmsUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(MessagesSmsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.messagesSms = messagesSmsSample;
        messagesSmsServiceStub.update.resolves(messagesSmsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messagesSmsServiceStub.update.calledWith(messagesSmsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        messagesSmsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MessagesSmsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.messagesSms = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messagesSmsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        messagesSmsServiceStub.find.resolves(messagesSmsSample);
        messagesSmsServiceStub.retrieve.resolves([messagesSmsSample]);

        // WHEN
        route = {
          params: {
            messagesSmsId: `${messagesSmsSample.id}`,
          },
        };
        const wrapper = shallowMount(MessagesSmsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.messagesSms).toMatchObject(messagesSmsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        messagesSmsServiceStub.find.resolves(messagesSmsSample);
        const wrapper = shallowMount(MessagesSmsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
