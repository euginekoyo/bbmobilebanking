import axios from 'axios';
import { type ICustomer } from '@/shared/model/customer.model';

const baseApiUrl = 'api/customers';

export default class CustomerService {
  public find(id: number): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: ICustomer): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: ICustomer): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: ICustomer): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
  public approveResetPin(id: number, remark: string, approvedBy: string): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      const payload = {
        id: id,
        remark: remark,
        approveReset: 1.0, // Trigger approval logic in the backend
        approvedby: approvedBy,
      };
      console.log('Sending PATCH request to approve PIN reset with payload:', payload);
      axios
        .patch(`${baseApiUrl}/${id}`, payload)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          console.error('PATCH request failed:', err.response);
          reject(err);
        });
    });
  }
  public resetPin(id: number, remark: string, resetBy: string): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      const payload = {
        id: id,
        pinresetremark: remark,
        resetby: resetBy,
      };
      console.log('Sending PATCH request with payload:', payload);
      axios
        .patch(`${baseApiUrl}/${id}`, payload)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          console.error('PATCH request failed:', err.response);
          reject(err);
        });
    });
  }

  public confirmBlockCustomer(id: number, remark: string, blockBy: string): Promise<ICustomer> {
    return new Promise<ICustomer>((resolve, reject) => {
      const payload = {
        id: id,
        pinblockremarks: remark,
        blockBy: blockBy,
      };
      console.log('Sending PATCH request with payload:', payload);
      axios
        .patch(`${baseApiUrl}/${id}`, payload)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          console.error('PATCH request failed:', err.response);
          reject(err);
        });
    });
  }
}
