import { type IChargeRange } from '@/shared/model/charge-range.model';
import { type IRange } from '@/shared/model/range.model';

export interface ICharge {
  id?: number;
  txntype?: string;
  feemode?: number | null;
  amount?: number | null;
  datecreated?: Date | null;
  createdby?: string | null;
  approved?: string | null;
  approvedby?: string | null;
  channel?: string | null;
  txncode?: number | null;
  description?: string | null;
  approveddate?: Date | null;
  chargeRange?: IChargeRange | null;
  range?: IRange | null;
}

export class Charge implements ICharge {
  constructor(
    public id?: number,
    public txntype?: string,
    public feemode?: number | null,
    public amount?: number | null,
    public datecreated?: Date | null,
    public createdby?: string | null,
    public approved?: string | null,
    public approvedby?: string | null,
    public channel?: string | null,
    public txncode?: number | null,
    public description?: string | null,
    public approveddate?: Date | null,
    public chargeRange?: IChargeRange | null,
    public range?: IRange | null,
  ) {}
}
