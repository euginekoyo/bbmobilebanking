/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ChannelsUpdate from './channels-update.vue';
import ChannelsService from './channels.service';
import AlertService from '@/shared/alert/alert.service';

type ChannelsUpdateComponentType = InstanceType<typeof ChannelsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const channelsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ChannelsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Channels Management Update Component', () => {
    let comp: ChannelsUpdateComponentType;
    let channelsServiceStub: SinonStubbedInstance<ChannelsService>;

    beforeEach(() => {
      route = {};
      channelsServiceStub = sinon.createStubInstance<ChannelsService>(ChannelsService);
      channelsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          channelsService: () => channelsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ChannelsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.channels = channelsSample;
        channelsServiceStub.update.resolves(channelsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(channelsServiceStub.update.calledWith(channelsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        channelsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ChannelsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.channels = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(channelsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        channelsServiceStub.find.resolves(channelsSample);
        channelsServiceStub.retrieve.resolves([channelsSample]);

        // WHEN
        route = {
          params: {
            channelsId: `${channelsSample.id}`,
          },
        };
        const wrapper = shallowMount(ChannelsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.channels).toMatchObject(channelsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        channelsServiceStub.find.resolves(channelsSample);
        const wrapper = shallowMount(ChannelsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
