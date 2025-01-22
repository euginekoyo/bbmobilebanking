export interface IServiceManagement {
  id?: number;
  processingcode?: string | null;
  active?: string | null;
  createdby?: string | null;
  datecreated?: Date | null;
  approved?: number | null;
  approvedby?: string | null;
  approveddate?: Date | null;
  adaptortype?: string | null;
  destination?: string | null;
  thirdpartyresponse?: number | null;
  telco?: string | null;
  description?: string;
  remarks?: string | null;
  sessionid?: string | null;
  reworkby?: string | null;
}

export class ServiceManagement implements IServiceManagement {
  constructor(
    public id?: number,
    public processingcode?: string | null,
    public active?: string | null,
    public createdby?: string | null,
    public datecreated?: Date | null,
    public approved?: number | null,
    public approvedby?: string | null,
    public approveddate?: Date | null,
    public adaptortype?: string | null,
    public destination?: string | null,
    public thirdpartyresponse?: number | null,
    public telco?: string | null,
    public description?: string,
    public remarks?: string | null,
    public sessionid?: string | null,
    public reworkby?: string | null,
  ) {}
}
