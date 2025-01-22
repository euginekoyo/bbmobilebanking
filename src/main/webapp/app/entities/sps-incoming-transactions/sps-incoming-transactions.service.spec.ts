/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import SPSIncomingTransactionsService from './sps-incoming-transactions.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { SPSIncomingTransactions } from '@/shared/model/sps-incoming-transactions.model';

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
  describe('SPSIncomingTransactions Service', () => {
    let service: SPSIncomingTransactionsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new SPSIncomingTransactionsService();
      currentDate = new Date();
      elemDefault = new SPSIncomingTransactions(
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
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
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

      it('should create a SPSIncomingTransactions', async () => {
        const returnedFromService = { id: 123, requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SPSIncomingTransactions', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SPSIncomingTransactions', async () => {
        const returnedFromService = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          callbackurl: 'BBBBBB',
          messagetype: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsname: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          debtorsbankcode: 'BBBBBB',
          debtorsphone: 'BBBBBB',
          beneficiaryname: 'BBBBBB',
          beneficiaryaccountid: 'BBBBBB',
          beneficiarybankcode: 'BBBBBB',
          beneficiaryphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsreference: 'BBBBBB',
          messageendtoendid: 'BBBBBB',
          transactionstatus: 'BBBBBB',
          transactionstatusdesc: 'BBBBBB',
          spsstatus: 'BBBBBB',
          spsstatusdesc: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          cbsstatusdesc: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          isomessagetype: 'BBBBBB',
          requestjson: 'BBBBBB',
          spsrequestxml: 'BBBBBB',
          spsresponsexml: 'BBBBBB',
          amount: 1,
          ...elemDefault,
        };

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SPSIncomingTransactions', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SPSIncomingTransactions', async () => {
        const patchObject = {
          callbackurl: 'BBBBBB',
          messagetype: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          beneficiaryname: 'BBBBBB',
          beneficiarybankcode: 'BBBBBB',
          transactionstatus: 'BBBBBB',
          transactionstatusdesc: 'BBBBBB',
          spsstatusdesc: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          isomessagetype: 'BBBBBB',
          requestjson: 'BBBBBB',
          spsresponsexml: 'BBBBBB',
          amount: 1,
          ...new SPSIncomingTransactions(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SPSIncomingTransactions', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SPSIncomingTransactions', async () => {
        const returnedFromService = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          callbackurl: 'BBBBBB',
          messagetype: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsname: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          debtorsbankcode: 'BBBBBB',
          debtorsphone: 'BBBBBB',
          beneficiaryname: 'BBBBBB',
          beneficiaryaccountid: 'BBBBBB',
          beneficiarybankcode: 'BBBBBB',
          beneficiaryphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsreference: 'BBBBBB',
          messageendtoendid: 'BBBBBB',
          transactionstatus: 'BBBBBB',
          transactionstatusdesc: 'BBBBBB',
          spsstatus: 'BBBBBB',
          spsstatusdesc: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          cbsstatusdesc: 'BBBBBB',
          requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          isomessagetype: 'BBBBBB',
          requestjson: 'BBBBBB',
          spsrequestxml: 'BBBBBB',
          spsresponsexml: 'BBBBBB',
          amount: 1,
          ...elemDefault,
        };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SPSIncomingTransactions', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SPSIncomingTransactions', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SPSIncomingTransactions', async () => {
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
