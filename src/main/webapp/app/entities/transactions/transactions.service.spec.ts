/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import TransactionsService from './transactions.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Transactions } from '@/shared/model/transactions.model';

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
  describe('Transactions Service', () => {
    let service: TransactionsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new TransactionsService();
      currentDate = new Date();
      elemDefault = new Transactions(
        123,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
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
        0,
        currentDate,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
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
        const returnedFromService = { datex: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
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

      it('should create a Transactions', async () => {
        const returnedFromService = { id: 123, datex: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
        const expected = { datex: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Transactions', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Transactions', async () => {
        const returnedFromService = {
          processed: 1,
          incomingbitmap: 'BBBBBB',
          outgoingbitmap: 'BBBBBB',
          inmessage: 'BBBBBB',
          messagetocbs: 'BBBBBB',
          messagefromcbs: 'BBBBBB',
          cbsprocess: 1,
          cbsonline: 1,
          cbsresponse: 'BBBBBB',
          responsemessage: 'BBBBBB',
          responsesent: 1,
          channel: 'BBBBBB',
          originaldata: 'BBBBBB',
          field39resp: 'BBBBBB',
          narration: 'BBBBBB',
          authorised: 1,
          branchcode: 'BBBBBB',
          field39original: 'BBBBBB',
          messageclass: 'BBBBBB',
          txncode: 'BBBBBB',
          currcode: 'BBBBBB',
          device: 'BBBBBB',
          branch2: 'BBBBBB',
          longerbranch: 1,
          datex: dayjs(currentDate).format(DATE_TIME_FORMAT),
          timex: 'BBBBBB',
          posted: 1,
          attempts: 1,
          originaldata2: 'BBBBBB',
          commission: 1,
          responsecreated: 1,
          online: 1,
          originaldata3: 'BBBBBB',
          toswitch: 'BBBBBB',
          fromswitch: 'BBBBBB',
          tocbs: 'BBBBBB',
          fromcbs: 'BBBBBB',
          postinglegs: 1,
          commissiontxncode: 'BBBBBB',
          hostref: 'BBBBBB',
          requestcreated: 1,
          requestmessage: 'BBBBBB',
          outgoingbitmapflex: 'BBBBBB',
          incomingbitmapflex: 'BBBBBB',
          requestsent: 1,
          minicbs: 1,
          reversed: 1,
          offlinesenttohost: 1,
          offlineresponse: 'BBBBBB',
          sourceLongerface: 'BBBBBB',
          mtirrn: 'BBBBBB',
          hostresponsecode: 'BBBBBB',
          field48: 'BBBBBB',
          source: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { datex: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Transactions', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Transactions', async () => {
        const patchObject = {
          processed: 1,
          outgoingbitmap: 'BBBBBB',
          inmessage: 'BBBBBB',
          cbsprocess: 1,
          cbsonline: 1,
          responsesent: 1,
          narration: 'BBBBBB',
          field39original: 'BBBBBB',
          currcode: 'BBBBBB',
          device: 'BBBBBB',
          branch2: 'BBBBBB',
          longerbranch: 1,
          originaldata2: 'BBBBBB',
          responsecreated: 1,
          online: 1,
          originaldata3: 'BBBBBB',
          fromswitch: 'BBBBBB',
          postinglegs: 1,
          commissiontxncode: 'BBBBBB',
          requestmessage: 'BBBBBB',
          minicbs: 1,
          offlinesenttohost: 1,
          offlineresponse: 'BBBBBB',
          sourceLongerface: 'BBBBBB',
          ...new Transactions(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { datex: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Transactions', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Transactions', async () => {
        const returnedFromService = {
          processed: 1,
          incomingbitmap: 'BBBBBB',
          outgoingbitmap: 'BBBBBB',
          inmessage: 'BBBBBB',
          messagetocbs: 'BBBBBB',
          messagefromcbs: 'BBBBBB',
          cbsprocess: 1,
          cbsonline: 1,
          cbsresponse: 'BBBBBB',
          responsemessage: 'BBBBBB',
          responsesent: 1,
          channel: 'BBBBBB',
          originaldata: 'BBBBBB',
          field39resp: 'BBBBBB',
          narration: 'BBBBBB',
          authorised: 1,
          branchcode: 'BBBBBB',
          field39original: 'BBBBBB',
          messageclass: 'BBBBBB',
          txncode: 'BBBBBB',
          currcode: 'BBBBBB',
          device: 'BBBBBB',
          branch2: 'BBBBBB',
          longerbranch: 1,
          datex: dayjs(currentDate).format(DATE_TIME_FORMAT),
          timex: 'BBBBBB',
          posted: 1,
          attempts: 1,
          originaldata2: 'BBBBBB',
          commission: 1,
          responsecreated: 1,
          online: 1,
          originaldata3: 'BBBBBB',
          toswitch: 'BBBBBB',
          fromswitch: 'BBBBBB',
          tocbs: 'BBBBBB',
          fromcbs: 'BBBBBB',
          postinglegs: 1,
          commissiontxncode: 'BBBBBB',
          hostref: 'BBBBBB',
          requestcreated: 1,
          requestmessage: 'BBBBBB',
          outgoingbitmapflex: 'BBBBBB',
          incomingbitmapflex: 'BBBBBB',
          requestsent: 1,
          minicbs: 1,
          reversed: 1,
          offlinesenttohost: 1,
          offlineresponse: 'BBBBBB',
          sourceLongerface: 'BBBBBB',
          mtirrn: 'BBBBBB',
          hostresponsecode: 'BBBBBB',
          field48: 'BBBBBB',
          source: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { datex: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Transactions', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Transactions', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Transactions', async () => {
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
