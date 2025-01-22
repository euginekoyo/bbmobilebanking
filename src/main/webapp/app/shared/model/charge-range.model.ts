export interface IChargeRange {
  id?: number;
  billerid?: string;
  processingcode?: string;
  max?: number;
  min?: number;
  amount?: number;
  createdby?: string | null;
  approvedby?: string | null;
  createdat?: string | null;
  approvedon?: string | null;
  approved?: number | null;
  declined?: number | null;
  declinedby?: string | null;
  chargeid?: number;
}

export class ChargeRange implements IChargeRange {
  constructor(
    public id?: number,
    public billerid?: string,
    public processingcode?: string,
    public max?: number,
    public min?: number,
    public amount?: number,
    public createdby?: string | null,
    public approvedby?: string | null,
    public createdat?: string | null,
    public approvedon?: string | null,
    public approved?: number | null,
    public declined?: number | null,
    public declinedby?: string | null,
    public chargeid?: number,
  ) {}
}
