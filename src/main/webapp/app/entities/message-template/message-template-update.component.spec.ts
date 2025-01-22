/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import dayjs from 'dayjs';
import MessageTemplateUpdate from './message-template-update.vue';
import MessageTemplateService from './message-template.service';
import { DATE_TIME_LONG_FORMAT } from '@/shared/composables/date-format';
import AlertService from '@/shared/alert/alert.service';

type MessageTemplateUpdateComponentType = InstanceType<typeof MessageTemplateUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const messageTemplateSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MessageTemplateUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MessageTemplate Management Update Component', () => {
    let comp: MessageTemplateUpdateComponentType;
    let messageTemplateServiceStub: SinonStubbedInstance<MessageTemplateService>;

    beforeEach(() => {
      route = {};
      messageTemplateServiceStub = sinon.createStubInstance<MessageTemplateService>(MessageTemplateService);
      messageTemplateServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          messageTemplateService: () => messageTemplateServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('load', () => {
      beforeEach(() => {
        const wrapper = shallowMount(MessageTemplateUpdate, { global: mountOptions });
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
        const wrapper = shallowMount(MessageTemplateUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.messageTemplate = messageTemplateSample;
        messageTemplateServiceStub.update.resolves(messageTemplateSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messageTemplateServiceStub.update.calledWith(messageTemplateSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        messageTemplateServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MessageTemplateUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.messageTemplate = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messageTemplateServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        messageTemplateServiceStub.find.resolves(messageTemplateSample);
        messageTemplateServiceStub.retrieve.resolves([messageTemplateSample]);

        // WHEN
        route = {
          params: {
            messageTemplateId: `${messageTemplateSample.id}`,
          },
        };
        const wrapper = shallowMount(MessageTemplateUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.messageTemplate).toMatchObject(messageTemplateSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        messageTemplateServiceStub.find.resolves(messageTemplateSample);
        const wrapper = shallowMount(MessageTemplateUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
