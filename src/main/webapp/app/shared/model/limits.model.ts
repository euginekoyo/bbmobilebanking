export interface ILimits {
  id?: number;
  transactiontype?: string;
  procode?: string | null;
  channel?: string | null;
  transactionlimit?: number;
  dailylimit?: number | null;
  registeredby?: string | null;
  registereddate?: string | null;
  approved?: string | null;
  approvedby?: string | null;
  approveddate?: string | null;
  updatedby?: string | null;
  updateddate?: string | null;
  rework?: number | null;
  reworkby?: string | null;
  sessionid?: string | null;
}

export class Limits implements ILimits {
  constructor(
    public id?: number,
    public transactiontype?: string,
    public procode?: string | null,
    public channel?: string | null,
    public transactionlimit?: number,
    public dailylimit?: number | null,
    public registeredby?: string | null,
    public registereddate?: string | null,
    public approved?: string | null,
    public approvedby?: string | null,
    public approveddate?: string | null,
    public updatedby?: string | null,
    public updateddate?: string | null,
    public rework?: number | null,
    public reworkby?: string | null,
    public sessionid?: string | null,
  ) {}
}
