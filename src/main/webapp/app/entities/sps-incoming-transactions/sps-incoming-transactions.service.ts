import axios from 'axios';

import { type ISPSIncomingTransactions } from '@/shared/model/sps-incoming-transactions.model';

const baseApiUrl = 'api/sps-incoming-transactions';

export default class SPSIncomingTransactionsService {
  public find(id: number): Promise<ISPSIncomingTransactions> {
    return new Promise<ISPSIncomingTransactions>((resolve, reject) => {
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

  public create(entity: ISPSIncomingTransactions): Promise<ISPSIncomingTransactions> {
    return new Promise<ISPSIncomingTransactions>((resolve, reject) => {
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

  public update(entity: ISPSIncomingTransactions): Promise<ISPSIncomingTransactions> {
    return new Promise<ISPSIncomingTransactions>((resolve, reject) => {
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

  public partialUpdate(entity: ISPSIncomingTransactions): Promise<ISPSIncomingTransactions> {
    return new Promise<ISPSIncomingTransactions>((resolve, reject) => {
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
}
