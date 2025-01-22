/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ChargeService from './charge.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Charge } from '@/shared/model/charge.model';

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
  describe('Charge Service', () => {
    let service: ChargeService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ChargeService();
      currentDate = new Date();
      elemDefault = new Charge(123, 'AAAAAAA', 0, 0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = {
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a Charge', async () => {
        const returnedFromService = {
          id: 123,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Charge', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Charge', async () => {
        const returnedFromService = {
          txntype: 'BBBBBB',
          feemode: 1,
          amount: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 'BBBBBB',
          approvedby: 'BBBBBB',
          channel: 'BBBBBB',
          txncode: 1,
          description: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Charge', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Charge', async () => {
        const patchObject = {
          txntype: 'BBBBBB',
          amount: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 'BBBBBB',
          approvedby: 'BBBBBB',
          channel: 'BBBBBB',
          description: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...new Charge(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Charge', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Charge', async () => {
        const returnedFromService = {
          txntype: 'BBBBBB',
          feemode: 1,
          amount: 1,
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 'BBBBBB',
          approvedby: 'BBBBBB',
          channel: 'BBBBBB',
          txncode: 1,
          description: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Charge', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Charge', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Charge', async () => {
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
