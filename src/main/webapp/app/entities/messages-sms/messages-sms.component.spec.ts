/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MessagesSms from './messages-sms.vue';
import MessagesSmsService from './messages-sms.service';
import AlertService from '@/shared/alert/alert.service';

type MessagesSmsComponentType = InstanceType<typeof MessagesSms>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MessagesSms Management Component', () => {
    let messagesSmsServiceStub: SinonStubbedInstance<MessagesSmsService>;
    let mountOptions: MountingOptions<MessagesSmsComponentType>['global'];

    beforeEach(() => {
      messagesSmsServiceStub = sinon.createStubInstance<MessagesSmsService>(MessagesSmsService);
      messagesSmsServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          messagesSmsService: () => messagesSmsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        messagesSmsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MessagesSms, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(messagesSmsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.messagesSms[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: MessagesSmsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MessagesSms, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        messagesSmsServiceStub.retrieve.reset();
        messagesSmsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        messagesSmsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMessagesSms();
        await comp.$nextTick(); // clear components

        // THEN
        expect(messagesSmsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(messagesSmsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
