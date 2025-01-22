/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import BranchesService from './branches.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Branches } from '@/shared/model/branches.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Branches Service', () => {
    let service: BranchesService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BranchesService();
      currentDate = new Date();
      elemDefault = new Branches(
        123,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = {
          createdon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deletedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          reworkedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Branches', async () => {
        const returnedFromService = {
          id: 123,
          createdon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deletedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          reworkedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        const expected = { createdon: currentDate, deletedon: currentDate, reworkedon: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Branches', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Branches', async () => {
        const returnedFromService = {
          branchname: 'BBBBBB',
          branchcode: 'BBBBBB',
          approved: 1,
          email: 'BBBBBB',
          address: 'BBBBBB',
          phone: 'BBBBBB',
          location: 'BBBBBB',
          contactperson: 'BBBBBB',
          remarks: 'BBBBBB',
          createdby: 'BBBBBB',
          createdon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedby: 'BBBBBB',
          approvedon: 'BBBBBB',
          checkerremarks: 'BBBBBB',
          deletedby: 'BBBBBB',
          deletedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deleteremarks: 'BBBBBB',
          deleted: 1,
          declined: 1,
          declineddon: 'BBBBBB',
          declinedby: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworked: 1,
          reworkedby: 'BBBBBB',
          reworkedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          district: 'BBBBBB',
          region: 'BBBBBB',
          regionname: 'BBBBBB',
          reporting: 1,
          ...elemDefault,
        };

        const expected = { createdon: currentDate, deletedon: currentDate, reworkedon: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Branches', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Branches', async () => {
        const patchObject = {
          branchcode: 'BBBBBB',
          email: 'BBBBBB',
          address: 'BBBBBB',
          contactperson: 'BBBBBB',
          remarks: 'BBBBBB',
          createdby: 'BBBBBB',
          createdon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedby: 'BBBBBB',
          approvedon: 'BBBBBB',
          deletedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deleteremarks: 'BBBBBB',
          declineddon: 'BBBBBB',
          declinedby: 'BBBBBB',
          reworked: 1,
          region: 'BBBBBB',
          ...new Branches(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { createdon: currentDate, deletedon: currentDate, reworkedon: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Branches', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Branches', async () => {
        const returnedFromService = {
          branchname: 'BBBBBB',
          branchcode: 'BBBBBB',
          approved: 1,
          email: 'BBBBBB',
          address: 'BBBBBB',
          phone: 'BBBBBB',
          location: 'BBBBBB',
          contactperson: 'BBBBBB',
          remarks: 'BBBBBB',
          createdby: 'BBBBBB',
          createdon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedby: 'BBBBBB',
          approvedon: 'BBBBBB',
          checkerremarks: 'BBBBBB',
          deletedby: 'BBBBBB',
          deletedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deleteremarks: 'BBBBBB',
          deleted: 1,
          declined: 1,
          declineddon: 'BBBBBB',
          declinedby: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworked: 1,
          reworkedby: 'BBBBBB',
          reworkedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          district: 'BBBBBB',
          region: 'BBBBBB',
          regionname: 'BBBBBB',
          reporting: 1,
          ...elemDefault,
        };
        const expected = { createdon: currentDate, deletedon: currentDate, reworkedon: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Branches', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Branches', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Branches', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
