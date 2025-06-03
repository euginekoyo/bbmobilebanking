/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import MobileAppTransactionsService from './mobile-app-transactions.service';
import { MobileAppTransactions } from '@/shared/model/mobile-app-transactions.model';

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
  describe('MobileAppTransactions Service', () => {
    let service: MobileAppTransactionsService;
    let elemDefault;

    beforeEach(() => {
      service = new MobileAppTransactionsService();
      elemDefault = new MobileAppTransactions(
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

      it('should create a MobileAppTransactions', async () => {
        const returnedFromService = { id: 123, ...elemDefault };
        const expected = { ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MobileAppTransactions', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MobileAppTransactions', async () => {
        const returnedFromService = {
          channel: 'BBBBBB',
          channelIp: 'BBBBBB',
          channelReference: 'BBBBBB',
          channelTimestamp: 'BBBBBB',
          clientId: 'BBBBBB',
          createdAt: 'BBBBBB',
          debitAccount: 'BBBBBB',
          direction: 'BBBBBB',
          errorDescription: 'BBBBBB',
          geolocation: 'BBBBBB',
          hostCode: 'BBBBBB',
          phoneNumber: 'BBBBBB',
          responseCode: 'BBBBBB',
          responseMessage: 'BBBBBB',
          transactionCode: 'BBBBBB',
          transactionType: 'BBBBBB',
          userAgent: 'BBBBBB',
          userAgentVersion: 'BBBBBB',
          amount: 'BBBBBB',
          chargeamount: 'BBBBBB',
          creditAccount: 'BBBBBB',
          cbsReference: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MobileAppTransactions', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MobileAppTransactions', async () => {
        const patchObject = {
          channel: 'BBBBBB',
          channelIp: 'BBBBBB',
          channelTimestamp: 'BBBBBB',
          clientId: 'BBBBBB',
          createdAt: 'BBBBBB',
          debitAccount: 'BBBBBB',
          errorDescription: 'BBBBBB',
          hostCode: 'BBBBBB',
          responseMessage: 'BBBBBB',
          transactionType: 'BBBBBB',
          userAgentVersion: 'BBBBBB',
          ...new MobileAppTransactions(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MobileAppTransactions', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MobileAppTransactions', async () => {
        const returnedFromService = {
          channel: 'BBBBBB',
          channelIp: 'BBBBBB',
          channelReference: 'BBBBBB',
          channelTimestamp: 'BBBBBB',
          clientId: 'BBBBBB',
          createdAt: 'BBBBBB',
          debitAccount: 'BBBBBB',
          direction: 'BBBBBB',
          errorDescription: 'BBBBBB',
          geolocation: 'BBBBBB',
          hostCode: 'BBBBBB',
          phoneNumber: 'BBBBBB',
          responseCode: 'BBBBBB',
          responseMessage: 'BBBBBB',
          transactionCode: 'BBBBBB',
          transactionType: 'BBBBBB',
          userAgent: 'BBBBBB',
          userAgentVersion: 'BBBBBB',
          amount: 'BBBBBB',
          chargeamount: 'BBBBBB',
          creditAccount: 'BBBBBB',
          cbsReference: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MobileAppTransactions', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MobileAppTransactions', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MobileAppTransactions', async () => {
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
