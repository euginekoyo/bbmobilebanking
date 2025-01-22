/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CustomerAccountService from './customer-account.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { CustomerAccount } from '@/shared/model/customer-account.model';

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
  describe('CustomerAccount Service', () => {
    let service: CustomerAccountService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CustomerAccountService();
      currentDate = new Date();
      elemDefault = new CustomerAccount(123, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = { timelinked: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
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

      it('should create a CustomerAccount', async () => {
        const returnedFromService = { id: 123, timelinked: dayjs(currentDate).format(DATE_TIME_FORMAT), ...elemDefault };
        const expected = { timelinked: currentDate, ...returnedFromService };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CustomerAccount', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CustomerAccount', async () => {
        const returnedFromService = {
          customerid: 1,
          accountnumber: 'BBBBBB',
          accountclass: 'BBBBBB',
          customernumber: 'BBBBBB',
          cif: 'BBBBBB',
          timelinked: dayjs(currentDate).format(DATE_TIME_FORMAT),
          blocked: 1,
          stopped: 1,
          dormant: 1,
          ...elemDefault,
        };

        const expected = { timelinked: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CustomerAccount', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CustomerAccount', async () => {
        const patchObject = {
          customerid: 1,
          accountnumber: 'BBBBBB',
          accountclass: 'BBBBBB',
          cif: 'BBBBBB',
          timelinked: dayjs(currentDate).format(DATE_TIME_FORMAT),
          blocked: 1,
          dormant: 1,
          ...new CustomerAccount(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { timelinked: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CustomerAccount', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CustomerAccount', async () => {
        const returnedFromService = {
          customerid: 1,
          accountnumber: 'BBBBBB',
          accountclass: 'BBBBBB',
          customernumber: 'BBBBBB',
          cif: 'BBBBBB',
          timelinked: dayjs(currentDate).format(DATE_TIME_FORMAT),
          blocked: 1,
          stopped: 1,
          dormant: 1,
          ...elemDefault,
        };
        const expected = { timelinked: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CustomerAccount', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CustomerAccount', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CustomerAccount', async () => {
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
