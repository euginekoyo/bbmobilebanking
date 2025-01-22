/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ServiceManagementService from './service-management.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { ServiceManagement } from '@/shared/model/service-management.model';

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
  describe('ServiceManagement Service', () => {
    let service: ServiceManagementService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ServiceManagementService();
      currentDate = new Date();
      elemDefault = new ServiceManagement(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
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

      it('should create a ServiceManagement', async () => {
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

      it('should not create a ServiceManagement', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ServiceManagement', async () => {
        const returnedFromService = {
          processingcode: 'BBBBBB',
          active: 'BBBBBB',
          createdby: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          adaptortype: 'BBBBBB',
          destination: 'BBBBBB',
          thirdpartyresponse: 1,
          telco: 'BBBBBB',
          description: 'BBBBBB',
          remarks: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          ...elemDefault,
        };

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ServiceManagement', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ServiceManagement', async () => {
        const patchObject = {
          processingcode: 'BBBBBB',
          active: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          adaptortype: 'BBBBBB',
          thirdpartyresponse: 1,
          description: 'BBBBBB',
          remarks: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          ...new ServiceManagement(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ServiceManagement', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ServiceManagement', async () => {
        const returnedFromService = {
          processingcode: 'BBBBBB',
          active: 'BBBBBB',
          createdby: 'BBBBBB',
          datecreated: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          adaptortype: 'BBBBBB',
          destination: 'BBBBBB',
          thirdpartyresponse: 1,
          telco: 'BBBBBB',
          description: 'BBBBBB',
          remarks: 'BBBBBB',
          sessionid: 'BBBBBB',
          reworkby: 'BBBBBB',
          ...elemDefault,
        };
        const expected = { datecreated: currentDate, approveddate: currentDate, ...returnedFromService };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ServiceManagement', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ServiceManagement', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ServiceManagement', async () => {
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
