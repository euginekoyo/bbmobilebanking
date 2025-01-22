/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CBSTransactionsService from './cbs-transactions.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { CBSTransactions } from '@/shared/model/cbs-transactions.model';

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
  describe('CBSTransactions Service', () => {
    let service: CBSTransactionsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CBSTransactionsService();
      currentDate = new Date();
      elemDefault = new CBSTransactions(
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
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = { requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
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

      it('should create a CBSTransactions', async () => {
        const returnedFromService = { id: 123, requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CBSTransactions', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CBSTransactions', async () => {
        const returnedFromService = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          messagetype: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsname: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          debtorsphone: 'BBBBBB',
          creditorsname: 'BBBBBB',
          creditorsaccountid: 'BBBBBB',
          creditorsphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsreference: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          cbsstatusdesc: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          requestjson: 'BBBBBB',
          cbsrequestxml: 'BBBBBB',
          cbsresponsexml: 'BBBBBB',
          amount: 1,
          ...elemDefault,
        };

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CBSTransactions', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CBSTransactions', async () => {
        const patchObject = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          creditorsaccountid: 'BBBBBB',
          creditorsphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          cbsrequestxml: 'BBBBBB',
          cbsresponsexml: 'BBBBBB',
          ...new CBSTransactions(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CBSTransactions', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CBSTransactions', async () => {
        const returnedFromService = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          messagetype: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsname: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          debtorsphone: 'BBBBBB',
          creditorsname: 'BBBBBB',
          creditorsaccountid: 'BBBBBB',
          creditorsphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsreference: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          cbsstatusdesc: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          requestjson: 'BBBBBB',
          cbsrequestxml: 'BBBBBB',
          cbsresponsexml: 'BBBBBB',
          amount: 1,
          ...elemDefault,
        };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CBSTransactions', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CBSTransactions', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CBSTransactions', async () => {
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
