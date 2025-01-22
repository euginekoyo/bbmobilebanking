export interface IMessagesSms {
  id?: number;
  trndatetime?: Date | null;
  phonenumber?: string | null;
  transactionno?: string | null;
  accountnumber?: string | null;
  message?: string | null;
  channel?: string | null;
  trials?: number | null;
  priority?: number | null;
  responsecode?: string | null;
  responsemsg?: string | null;
  sent?: number | null;
  delivered?: number | null;
  txntype?: string | null;
  errorexception?: number | null;
  datecreated?: Date | null;
  datesent?: string | null;
  rtpsreqtime?: string | null;
  fxgenerated?: string | null;
  taxprocessed?: number | null;
  batchnumber?: string | null;
  batchnumbertax?: string | null;
  responsetime?: string | null;
  pduseqid?: string | null;
  remarks?: string | null;
  resendby?: string | null;
}

export class MessagesSms implements IMessagesSms {
  constructor(
    public id?: number,
    public trndatetime?: Date | null,
    public phonenumber?: string | null,
    public transactionno?: string | null,
    public accountnumber?: string | null,
    public message?: string | null,
    public channel?: string | null,
    public trials?: number | null,
    public priority?: number | null,
    public responsecode?: string | null,
    public responsemsg?: string | null,
    public sent?: number | null,
    public delivered?: number | null,
    public txntype?: string | null,
    public errorexception?: number | null,
    public datecreated?: Date | null,
    public datesent?: string | null,
    public rtpsreqtime?: string | null,
    public fxgenerated?: string | null,
    public taxprocessed?: number | null,
    public batchnumber?: string | null,
    public batchnumbertax?: string | null,
    public responsetime?: string | null,
    public pduseqid?: string | null,
    public remarks?: string | null,
    public resendby?: string | null,
  ) {}
}
