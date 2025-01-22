/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CustomerService from './customer.service';
import { DATE_TIME_FORMAT } from '@/shared/composables/date-format';
import { Customer } from '@/shared/model/customer.model';

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
  describe('Customer Service', () => {
    let service: CustomerService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CustomerService();
      currentDate = new Date();
      elemDefault = new Customer(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        currentDate,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
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
        currentDate,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
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
        0,
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        currentDate,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = {
          regdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          partialdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          registerdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          declineddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          dob: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deactivatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenochangedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          reseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          dateofbirth: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passreseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddisableon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          disabledon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          resetapproveon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          activatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a Customer', async () => {
        const returnedFromService = {
          id: 123,
          regdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          partialdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          registerdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          declineddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          dob: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deactivatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenochangedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          reseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          dateofbirth: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passreseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approveddisableon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          disabledon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          resetapproveon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          activatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          ...elemDefault,
        };
        const expected = {
          regdate: currentDate,
          partialdate: currentDate,
          registerdate: currentDate,
          approveddate: currentDate,
          declineddate: currentDate,
          dob: currentDate,
          deactivatedon: currentDate,
          phonenochangedon: currentDate,
          reseton: currentDate,
          pinblockon: currentDate,
          approvedon: currentDate,
          dateofbirth: currentDate,
          passreseton: currentDate,
          passblockon: currentDate,
          approveddisableon: currentDate,
          disabledon: currentDate,
          resetapproveon: currentDate,
          activatedon: currentDate,
          ...returnedFromService,
        };

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Customer', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Customer', async () => {
        const returnedFromService = {
          customername: 'BBBBBB',
          phonenumber: 'BBBBBB',
          cardnumber: 'BBBBBB',
          accountnumber: 'BBBBBB',
          lang: 'BBBBBB',
          pin: 'BBBBBB',
          firstlogin: 'BBBBBB',
          active: 'BBBBBB',
          registered: 1,
          cstdelete: 1,
          regdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          alertenabled: 1,
          remark: 'BBBBBB',
          imsi: 'BBBBBB',
          partiallyregistered: 'BBBBBB',
          partialdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          registerdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          declined: 1,
          declinedby: 'BBBBBB',
          declineddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          checkerremarks: 'BBBBBB',
          postaladdress: 'BBBBBB',
          residence: 'BBBBBB',
          dob: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          emailaddress: 'BBBBBB',
          identificationid: 'BBBBBB',
          addaccount: 1,
          aclinkinginstitution: 'BBBBBB',
          deactivated: 1,
          deactivatedby: 'BBBBBB',
          deactivatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenochanged: 1,
          phonenochangedby: 'BBBBBB',
          phonenochangedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          originalphoneno: 'BBBBBB',
          newphoneno: 'BBBBBB',
          reset: 1,
          resetinginstitution: 'BBBBBB',
          pinresetremark: 'BBBBBB',
          resetby: 'BBBBBB',
          reseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          unblockinginstitution: 'BBBBBB',
          pinblock: 1,
          pinblockby: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          blockinginstitution: 'BBBBBB',
          pinblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinunblockby: 'BBBBBB',
          loggedin: 1,
          trials: 'BBBBBB',
          idtype: 'BBBBBB',
          idnumber: 'BBBBBB',
          gender: 'BBBBBB',
          cif: 'BBBBBB',
          dateofbirth: dayjs(currentDate).format(DATE_TIME_FORMAT),
          remarks: 'BBBBBB',
          resetimsi: 1,
          imsiresetby: 'BBBBBB',
          firstname: 'BBBBBB',
          secondname: 'BBBBBB',
          lastname: 'BBBBBB',
          pinblocktime: 'BBBBBB',
          customerstatus: 'BBBBBB',
          username: 'BBBBBB',
          password: 'BBBBBB',
          deviceid: 'BBBBBB',
          channel: 'BBBBBB',
          passreset: 1,
          passresetby: 'BBBBBB',
          passreseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passblock: 1,
          passblockby: 'BBBBBB',
          passblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinmarkblock: 1,
          passmarkblock: 1,
          passresetremarks: 'BBBBBB',
          passblockremarks: 'BBBBBB',
          passunblockby: 'BBBBBB',
          passtrials: 1,
          appactive: 1,
          lastlogin: 'BBBBBB',
          appmarkeddisable: 1,
          disableby: 'BBBBBB',
          approvedisableby: 'BBBBBB',
          appmarkedenable: 1,
          enableby: 'BBBBBB',
          approvedenableby: 'BBBBBB',
          markeddeactivate: 1,
          appfirstlogin: 'BBBBBB',
          atmtrials: 1,
          shorcuts: 'BBBBBB',
          markedactivate: 'BBBBBB',
          town: 'BBBBBB',
          approveddisableon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          disabledon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          resetapproveon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deletedby: 'BBBBBB',
          questionsasked: 'BBBBBB',
          questionstrials: 'BBBBBB',
          questionsanswered: 'BBBBBB',
          validotp: 1,
          activatedby: 'BBBBBB',
          activatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          branchcode: 'BBBBBB',
          ...elemDefault,
        };

        const expected = {
          regdate: currentDate,
          partialdate: currentDate,
          registerdate: currentDate,
          approveddate: currentDate,
          declineddate: currentDate,
          dob: currentDate,
          deactivatedon: currentDate,
          phonenochangedon: currentDate,
          reseton: currentDate,
          pinblockon: currentDate,
          approvedon: currentDate,
          dateofbirth: currentDate,
          passreseton: currentDate,
          passblockon: currentDate,
          approveddisableon: currentDate,
          disabledon: currentDate,
          resetapproveon: currentDate,
          activatedon: currentDate,
          ...returnedFromService,
        };
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Customer', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Customer', async () => {
        const patchObject = {
          phonenumber: 'BBBBBB',
          cardnumber: 'BBBBBB',
          accountnumber: 'BBBBBB',
          lang: 'BBBBBB',
          firstlogin: 'BBBBBB',
          active: 'BBBBBB',
          cstdelete: 1,
          alertenabled: 1,
          imsi: 'BBBBBB',
          partialdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          declinedby: 'BBBBBB',
          dob: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          aclinkinginstitution: 'BBBBBB',
          deactivated: 1,
          deactivatedby: 'BBBBBB',
          deactivatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenochanged: 1,
          originalphoneno: 'BBBBBB',
          pinresetremark: 'BBBBBB',
          pinblockby: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          pinunblockby: 'BBBBBB',
          idtype: 'BBBBBB',
          idnumber: 'BBBBBB',
          gender: 'BBBBBB',
          cif: 'BBBBBB',
          remarks: 'BBBBBB',
          resetimsi: 1,
          imsiresetby: 'BBBBBB',
          secondname: 'BBBBBB',
          pinblocktime: 'BBBBBB',
          customerstatus: 'BBBBBB',
          username: 'BBBBBB',
          password: 'BBBBBB',
          deviceid: 'BBBBBB',
          passreset: 1,
          passresetby: 'BBBBBB',
          passblockby: 'BBBBBB',
          passblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinmarkblock: 1,
          passmarkblock: 1,
          passblockremarks: 'BBBBBB',
          appactive: 1,
          enableby: 'BBBBBB',
          approvedenableby: 'BBBBBB',
          shorcuts: 'BBBBBB',
          town: 'BBBBBB',
          approveddisableon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          resetapproveon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          questionstrials: 'BBBBBB',
          validotp: 1,
          activatedby: 'BBBBBB',
          ...new Customer(),
        };
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = {
          regdate: currentDate,
          partialdate: currentDate,
          registerdate: currentDate,
          approveddate: currentDate,
          declineddate: currentDate,
          dob: currentDate,
          deactivatedon: currentDate,
          phonenochangedon: currentDate,
          reseton: currentDate,
          pinblockon: currentDate,
          approvedon: currentDate,
          dateofbirth: currentDate,
          passreseton: currentDate,
          passblockon: currentDate,
          approveddisableon: currentDate,
          disabledon: currentDate,
          resetapproveon: currentDate,
          activatedon: currentDate,
          ...returnedFromService,
        };
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Customer', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Customer', async () => {
        const returnedFromService = {
          customername: 'BBBBBB',
          phonenumber: 'BBBBBB',
          cardnumber: 'BBBBBB',
          accountnumber: 'BBBBBB',
          lang: 'BBBBBB',
          pin: 'BBBBBB',
          firstlogin: 'BBBBBB',
          active: 'BBBBBB',
          registered: 1,
          cstdelete: 1,
          regdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          alertenabled: 1,
          remark: 'BBBBBB',
          imsi: 'BBBBBB',
          partiallyregistered: 'BBBBBB',
          partialdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          registerdate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approved: 1,
          approvedby: 'BBBBBB',
          approveddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          declined: 1,
          declinedby: 'BBBBBB',
          declineddate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          checkerremarks: 'BBBBBB',
          postaladdress: 'BBBBBB',
          residence: 'BBBBBB',
          dob: dayjs(currentDate).format(DATE_TIME_FORMAT),
          createdby: 'BBBBBB',
          emailaddress: 'BBBBBB',
          identificationid: 'BBBBBB',
          addaccount: 1,
          aclinkinginstitution: 'BBBBBB',
          deactivated: 1,
          deactivatedby: 'BBBBBB',
          deactivatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          phonenochanged: 1,
          phonenochangedby: 'BBBBBB',
          phonenochangedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          originalphoneno: 'BBBBBB',
          newphoneno: 'BBBBBB',
          reset: 1,
          resetinginstitution: 'BBBBBB',
          pinresetremark: 'BBBBBB',
          resetby: 'BBBBBB',
          reseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          unblockinginstitution: 'BBBBBB',
          pinblock: 1,
          pinblockby: 'BBBBBB',
          pinblockremarks: 'BBBBBB',
          blockinginstitution: 'BBBBBB',
          pinblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          approvedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinunblockby: 'BBBBBB',
          loggedin: 1,
          trials: 'BBBBBB',
          idtype: 'BBBBBB',
          idnumber: 'BBBBBB',
          gender: 'BBBBBB',
          cif: 'BBBBBB',
          dateofbirth: dayjs(currentDate).format(DATE_TIME_FORMAT),
          remarks: 'BBBBBB',
          resetimsi: 1,
          imsiresetby: 'BBBBBB',
          firstname: 'BBBBBB',
          secondname: 'BBBBBB',
          lastname: 'BBBBBB',
          pinblocktime: 'BBBBBB',
          customerstatus: 'BBBBBB',
          username: 'BBBBBB',
          password: 'BBBBBB',
          deviceid: 'BBBBBB',
          channel: 'BBBBBB',
          passreset: 1,
          passresetby: 'BBBBBB',
          passreseton: dayjs(currentDate).format(DATE_TIME_FORMAT),
          passblock: 1,
          passblockby: 'BBBBBB',
          passblockon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          pinmarkblock: 1,
          passmarkblock: 1,
          passresetremarks: 'BBBBBB',
          passblockremarks: 'BBBBBB',
          passunblockby: 'BBBBBB',
          passtrials: 1,
          appactive: 1,
          lastlogin: 'BBBBBB',
          appmarkeddisable: 1,
          disableby: 'BBBBBB',
          approvedisableby: 'BBBBBB',
          appmarkedenable: 1,
          enableby: 'BBBBBB',
          approvedenableby: 'BBBBBB',
          markeddeactivate: 1,
          appfirstlogin: 'BBBBBB',
          atmtrials: 1,
          shorcuts: 'BBBBBB',
          markedactivate: 'BBBBBB',
          town: 'BBBBBB',
          approveddisableon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          disabledon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          resetapproveon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          deletedby: 'BBBBBB',
          questionsasked: 'BBBBBB',
          questionstrials: 'BBBBBB',
          questionsanswered: 'BBBBBB',
          validotp: 1,
          activatedby: 'BBBBBB',
          activatedon: dayjs(currentDate).format(DATE_TIME_FORMAT),
          branchcode: 'BBBBBB',
          ...elemDefault,
        };
        const expected = {
          regdate: currentDate,
          partialdate: currentDate,
          registerdate: currentDate,
          approveddate: currentDate,
          declineddate: currentDate,
          dob: currentDate,
          deactivatedon: currentDate,
          phonenochangedon: currentDate,
          reseton: currentDate,
          pinblockon: currentDate,
          approvedon: currentDate,
          dateofbirth: currentDate,
          passreseton: currentDate,
          passblockon: currentDate,
          approveddisableon: currentDate,
          disabledon: currentDate,
          resetapproveon: currentDate,
          activatedon: currentDate,
          ...returnedFromService,
        };
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Customer', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Customer', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Customer', async () => {
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
