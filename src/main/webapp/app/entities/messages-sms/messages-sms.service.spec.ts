/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import MessagesSmsService from './messages-sms.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { MessagesSms } from '@/shared/model/messages-sms.model';

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
  describe('MessagesSms Service', () => {
    let service: MessagesSmsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new MessagesSmsService();
      currentDate = new Date();
      elemDefault = new MessagesSms(
        123,
        currentDate,
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
        0,
        'AAAAAAA',
        0,
        currentDate,
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
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = {
          trndatetime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a MessagesSms', async () => {
        const returnedFromService = {
          id: 123,
          trndatetime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        const expected = { trndatetime: currentDate, datecreated: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MessagesSms', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MessagesSms', async () => {
        const returnedFromService = {
          trndatetime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenumber: 'BBBBBB',
          transactionno: 'BBBBBB',
          accountnumber: 'BBBBBB',
          message: 'BBBBBB',
          channel: 'BBBBBB',
          trials: 1,
          priority: 1,
          responsecode: 'BBBBBB',
          responsemsg: 'BBBBBB',
          sent: 1,
          delivered: 1,
          txntype: 'BBBBBB',
          errorexception: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          datesent: 'BBBBBB',
          rtpsreqtime: 'BBBBBB',
          fxgenerated: 'BBBBBB',
          taxprocessed: 1,
          batchnumber: 'BBBBBB',
          batchnumbertax: 'BBBBBB',
          responsetime: 'BBBBBB',
          pduseqid: 'BBBBBB',
          remarks: 'BBBBBB',
          resendby: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { trndatetime: currentDate, datecreated: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MessagesSms', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MessagesSms', async () => {
        const patchObject = {
          phonenumber: 'BBBBBB',
          transactionno: 'BBBBBB',
          accountnumber: 'BBBBBB',
          responsecode: 'BBBBBB',
          responsemsg: 'BBBBBB',
          sent: 1,
          errorexception: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          datesent: 'BBBBBB',
          fxgenerated: 'BBBBBB',
          taxprocessed: 1,
          pduseqid: 'BBBBBB',
          resendby: 'BBBBBB',
          ...new MessagesSms(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { trndatetime: currentDate, datecreated: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MessagesSms', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MessagesSms', async () => {
        const returnedFromService = {
          trndatetime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenumber: 'BBBBBB',
          transactionno: 'BBBBBB',
          accountnumber: 'BBBBBB',
          message: 'BBBBBB',
          channel: 'BBBBBB',
          trials: 1,
          priority: 1,
          responsecode: 'BBBBBB',
          responsemsg: 'BBBBBB',
          sent: 1,
          delivered: 1,
          txntype: 'BBBBBB',
          errorexception: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          datesent: 'BBBBBB',
          rtpsreqtime: 'BBBBBB',
          fxgenerated: 'BBBBBB',
          taxprocessed: 1,
          batchnumber: 'BBBBBB',
          batchnumbertax: 'BBBBBB',
          responsetime: 'BBBBBB',
          pduseqid: 'BBBBBB',
          remarks: 'BBBBBB',
          resendby: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { trndatetime: currentDate, datecreated: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MessagesSms', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MessagesSms', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MessagesSms', async () => {
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
