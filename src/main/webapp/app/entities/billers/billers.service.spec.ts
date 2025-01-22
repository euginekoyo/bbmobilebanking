/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import BillersService from './billers.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Billers } from '@/shared/model/billers.model';

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
  describe('Billers Service', () => {
    let service: BillersService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BillersService();
      currentDate = new Date();
      elemDefault = new Billers(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
      );
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

      it('should create a Billers', async () => {
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

      it('should not create a Billers', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Billers', async () => {
        const returnedFromService = {
          billerid: 'BBBBBB',
          description: 'BBBBBB',
          billercollectionaccount: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          chargableproductid: 'BBBBBB',
          nonchargableproductid: 'BBBBBB',
          usdbillercollectionaccount: 'BBBBBB',
          enableduplicatecheck: 1,
          remarks: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          status: 1,
          active: 1,
          rework: 1,
          ...elemDefault,
        };

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Billers', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Billers', async () => {
        const patchObject = {
          description: 'BBBBBB',
          billercollectionaccount: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 1,
          nonchargableproductid: 'BBBBBB',
          usdbillercollectionaccount: 'BBBBBB',
          enableduplicatecheck: 1,
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          status: 1,
          active: 1,
          rework: 1,
          ...new Billers(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Billers', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Billers', async () => {
        const returnedFromService = {
          billerid: 'BBBBBB',
          description: 'BBBBBB',
          billercollectionaccount: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          chargableproductid: 'BBBBBB',
          nonchargableproductid: 'BBBBBB',
          usdbillercollectionaccount: 'BBBBBB',
          enableduplicatecheck: 1,
          remarks: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          status: 1,
          active: 1,
          rework: 1,
          ...elemDefault,
        };
        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Billers', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Billers', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Billers', async () => {
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
