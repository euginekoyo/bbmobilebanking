/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import LimitsService from './limits.service';
import { Limits } from '@/shared/model/limits.model';

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
  describe('Limits Service', () => {
    let service: LimitsService;
    let elemDefault;

    beforeEach(() => {
      service = new LimitsService();
      elemDefault = new Limits(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = { ...elemDefault };
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

      it('should create a Limits', async () => {
        const returnedFromService = { id: 123, ...elemDefault };
        const expected = { ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Limits', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Limits', async () => {
        const returnedFromService = {
          transactiontype: 'BBBBBB',
          procode: 'BBBBBB',
          channel: 'BBBBBB',
          transactionlimit: 1,
          dailylimit: 1,
          registeredby: 'BBBBBB',
          registereddate: 'BBBBBB',
          approved: 'BBBBBB',
          approvedby: 'BBBBBB',
          approveddate: 'BBBBBB',
          updatedby: 'BBBBBB',
          updateddate: 'BBBBBB',
          rework: 1,
          reworkby: 'BBBBBB',
          sessionid: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Limits', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Limits', async () => {
        const patchObject = {
          transactiontype: 'BBBBBB',
          channel: 'BBBBBB',
          approvedby: 'BBBBBB',
          updateddate: 'BBBBBB',
          reworkby: 'BBBBBB',
          ...new Limits(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Limits', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Limits', async () => {
        const returnedFromService = {
          transactiontype: 'BBBBBB',
          procode: 'BBBBBB',
          channel: 'BBBBBB',
          transactionlimit: 1,
          dailylimit: 1,
          registeredby: 'BBBBBB',
          registereddate: 'BBBBBB',
          approved: 'BBBBBB',
          approvedby: 'BBBBBB',
          approveddate: 'BBBBBB',
          updatedby: 'BBBBBB',
          updateddate: 'BBBBBB',
          rework: 1,
          reworkby: 'BBBBBB',
          sessionid: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Limits', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Limits', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Limits', async () => {
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
