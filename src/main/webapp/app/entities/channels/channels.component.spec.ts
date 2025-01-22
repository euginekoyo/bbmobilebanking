/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Channels from './channels.vue';
import ChannelsService from './channels.service';
import AlertService from '@/shared/alert/alert.service';

type ChannelsComponentType = InstanceType<typeof Channels>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Channels Management Component', () => {
    let channelsServiceStub: SinonStubbedInstance<ChannelsService>;
    let mountOptions: MountingOptions<ChannelsComponentType>['global'];

    beforeEach(() => {
      channelsServiceStub = sinon.createStubInstance<ChannelsService>(ChannelsService);
      channelsServiceStub.retrieve.resolves({ headers: {} });

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
          channelsService: () => channelsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        channelsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Channels, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(channelsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.channels[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ChannelsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Channels, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        channelsServiceStub.retrieve.reset();
        channelsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        channelsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeChannels();
        await comp.$nextTick(); // clear components

        // THEN
        expect(channelsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(channelsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
