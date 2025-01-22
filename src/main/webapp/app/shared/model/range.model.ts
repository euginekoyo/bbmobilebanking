export interface IRange {
  id?: number;
  rangefrom?: number | null;
  rangeto?: number | null;
  amount?: number | null;
  txntype?: string | null;
  txncode?: string | null;
  chargeid?: number | null;
  channel?: string | null;
}

export class Range implements IRange {
  constructor(
    public id?: number,
    public rangefrom?: number | null,
    public rangeto?: number | null,
    public amount?: number | null,
    public txntype?: string | null,
    public txncode?: string | null,
    public chargeid?: number | null,
    public channel?: string | null,
  ) {}
}
