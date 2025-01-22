/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import PinResetHistoryService from './pin-reset-history.service';
import { PinResetHistory } from '@/shared/model/pin-reset-history.model';

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
  describe('PinResetHistory Service', () => {
    let service: PinResetHistoryService;
    let elemDefault;

    beforeEach(() => {
      service = new PinResetHistoryService();
      elemDefault = new PinResetHistory(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a PinResetHistory', async () => {
        const returnedFromService = { id: 123, ...elemDefault };
        const expected = { ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a PinResetHistory', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a PinResetHistory', async () => {
        const returnedFromService = {
          phonenumber: 'BBBBBB',
          customername: 'BBBBBB',
          pinblockedon: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          pinresetby: 'BBBBBB',
          pinreseton: 'BBBBBB',
          pinresetapprovedby: 'BBBBBB',
          pinresetapprovedon: 'BBBBBB',
          pinresetremarks: 'BBBBBB',
          branchcode: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a PinResetHistory', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a PinResetHistory', async () => {
        const patchObject = {
          customername: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          pinresetby: 'BBBBBB',
          branchcode: 'BBBBBB',
          ...new PinResetHistory(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a PinResetHistory', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of PinResetHistory', async () => {
        const returnedFromService = {
          phonenumber: 'BBBBBB',
          customername: 'BBBBBB',
          pinblockedon: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          pinresetby: 'BBBBBB',
          pinreseton: 'BBBBBB',
          pinresetapprovedby: 'BBBBBB',
          pinresetapprovedon: 'BBBBBB',
          pinresetremarks: 'BBBBBB',
          branchcode: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of PinResetHistory', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a PinResetHistory', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a PinResetHistory', async () => {
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
