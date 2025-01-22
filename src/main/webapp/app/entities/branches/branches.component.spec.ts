/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Branches from './branches.vue';
import BranchesService from './branches.service';
import AlertService from '@/shared/alert/alert.service';

type BranchesComponentType = InstanceType<typeof Branches>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Branches Management Component', () => {
    let branchesServiceStub: SinonStubbedInstance<BranchesService>;
    let mountOptions: MountingOptions<BranchesComponentType>['global'];

    beforeEach(() => {
      branchesServiceStub = sinon.createStubInstance<BranchesService>(BranchesService);
      branchesServiceStub.retrieve.resolves({ headers: {} });

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
          branchesService: () => branchesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        branchesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Branches, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(branchesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.branches[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: BranchesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Branches, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        branchesServiceStub.retrieve.reset();
        branchesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        branchesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeBranches();
        await comp.$nextTick(); // clear components

        // THEN
        expect(branchesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(branchesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
