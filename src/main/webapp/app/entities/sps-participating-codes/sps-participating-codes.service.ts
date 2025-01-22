import axios from 'axios';

import { type ISPSParticipatingCodes } from '@/shared/model/sps-participating-codes.model';

const baseApiUrl = 'api/sps-participating-codes';

export default class SPSParticipatingCodesService {
  public find(id: number): Promise<ISPSParticipatingCodes> {
    return new Promise<ISPSParticipatingCodes>((resolve, reject) => {
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

  public create(entity: ISPSParticipatingCodes): Promise<ISPSParticipatingCodes> {
    return new Promise<ISPSParticipatingCodes>((resolve, reject) => {
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

  public update(entity: ISPSParticipatingCodes): Promise<ISPSParticipatingCodes> {
    return new Promise<ISPSParticipatingCodes>((resolve, reject) => {
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

  public partialUpdate(entity: ISPSParticipatingCodes): Promise<ISPSParticipatingCodes> {
    return new Promise<ISPSParticipatingCodes>((resolve, reject) => {
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
