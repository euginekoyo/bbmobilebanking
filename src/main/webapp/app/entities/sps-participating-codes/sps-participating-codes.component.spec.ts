/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SPSParticipatingCodes from './sps-participating-codes.vue';
import SPSParticipatingCodesService from './sps-participating-codes.service';
import AlertService from '@/shared/alert/alert.service';

type SPSParticipatingCodesComponentType = InstanceType<typeof SPSParticipatingCodes>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SPSParticipatingCodes Management Component', () => {
    let sPSParticipatingCodesServiceStub: SinonStubbedInstance<SPSParticipatingCodesService>;
    let mountOptions: MountingOptions<SPSParticipatingCodesComponentType>['global'];

    beforeEach(() => {
      sPSParticipatingCodesServiceStub = sinon.createStubInstance<SPSParticipatingCodesService>(SPSParticipatingCodesService);
      sPSParticipatingCodesServiceStub.retrieve.resolves({ headers: {} });

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
          sPSParticipatingCodesService: () => sPSParticipatingCodesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sPSParticipatingCodesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SPSParticipatingCodes, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(sPSParticipatingCodesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.sPSParticipatingCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SPSParticipatingCodesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SPSParticipatingCodes, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        sPSParticipatingCodesServiceStub.retrieve.reset();
        sPSParticipatingCodesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        sPSParticipatingCodesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSPSParticipatingCodes();
        await comp.$nextTick(); // clear components

        // THEN
        expect(sPSParticipatingCodesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(sPSParticipatingCodesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
