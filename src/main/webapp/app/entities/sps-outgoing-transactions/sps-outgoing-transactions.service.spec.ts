/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import SPSOutgoingTransactionsService from './sps-outgoing-transactions.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { SPSOutgoingTransactions } from '@/shared/model/sps-outgoing-transactions.model';

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
  describe('SPSOutgoingTransactions Service', () => {
    let service: SPSOutgoingTransactionsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new SPSOutgoingTransactionsService();
      currentDate = new Date();
      elemDefault = new SPSOutgoingTransactions(
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
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a SPSOutgoingTransactions', async () => {
        const returnedFromService = { id: 123, requestInstanttime: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SPSOutgoingTransactions', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SPSOutgoingTransactions', async () => {
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
          callbackstatus: 'BBBBBB',
          callbackstatusdesc: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SPSOutgoingTransactions', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SPSOutgoingTransactions', async () => {
        const patchObject = {
          messageid: 'BBBBBB',
          channelcode: 'BBBBBB',
          messagetype: 'BBBBBB',
          transcurrency: 'BBBBBB',
          debtorsname: 'BBBBBB',
          debtorsaccountid: 'BBBBBB',
          debtorsbankcode: 'BBBBBB',
          debtorsphone: 'BBBBBB',
          beneficiaryaccountid: 'BBBBBB',
          beneficiaryphone: 'BBBBBB',
          narration: 'BBBBBB',
          externalreference: 'BBBBBB',
          cbsreference: 'BBBBBB',
          transactionstatusdesc: 'BBBBBB',
          spsstatus: 'BBBBBB',
          spsstatusdesc: 'BBBBBB',
          cbsstatus: 'BBBBBB',
          cbsstatusdesc: 'BBBBBB',
          isomessagetype: 'BBBBBB',
          spsrequestxml: 'BBBBBB',
          spsresponsexml: 'BBBBBB',
          callbackstatusdesc: 'BBBBBB',
          ...new SPSOutgoingTransactions(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SPSOutgoingTransactions', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SPSOutgoingTransactions', async () => {
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
          callbackstatus: 'BBBBBB',
          callbackstatusdesc: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { requestInstanttime: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SPSOutgoingTransactions', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SPSOutgoingTransactions', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SPSOutgoingTransactions', async () => {
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
