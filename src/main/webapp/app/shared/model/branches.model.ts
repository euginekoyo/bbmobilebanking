export interface IBranches {
  id?: number;
  branchname?: string | null;
  branchcode?: string;
  approved?: number | null;
  email?: string | null;
  address?: string | null;
  phone?: string | null;
  location?: string;
  contactperson?: string | null;
  remarks?: string | null;
  createdby?: string | null;
  createdon?: Date | null;
  approvedby?: string | null;
  approvedon?: string | null;
  checkerremarks?: string | null;
  deletedby?: string | null;
  deletedon?: Date | null;
  deleteremarks?: string | null;
  deleted?: number | null;
  declined?: number | null;
  declineddon?: string | null;
  declinedby?: string | null;
  sessionid?: string | null;
  reworked?: number | null;
  reworkedby?: string | null;
  reworkedon?: Date | null;
  district?: string | null;
  region?: string | null;
  regionname?: string | null;
  reporting?: number | null;
}

export class Branches implements IBranches {
  constructor(
    public id?: number,
    public branchname?: string | null,
    public branchcode?: string,
    public approved?: number | null,
    public email?: string | null,
    public address?: string | null,
    public phone?: string | null,
    public location?: string,
    public contactperson?: string | null,
    public remarks?: string | null,
    public createdby?: string | null,
    public createdon?: Date | null,
    public approvedby?: string | null,
    public approvedon?: string | null,
    public checkerremarks?: string | null,
    public deletedby?: string | null,
    public deletedon?: Date | null,
    public deleteremarks?: string | null,
    public deleted?: number | null,
    public declined?: number | null,
    public declineddon?: string | null,
    public declinedby?: string | null,
    public sessionid?: string | null,
    public reworked?: number | null,
    public reworkedby?: string | null,
    public reworkedon?: Date | null,
    public district?: string | null,
    public region?: string | null,
    public regionname?: string | null,
    public reporting?: number | null,
  ) {}
}
