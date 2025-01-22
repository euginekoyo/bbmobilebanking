export interface ITransactions {
  id?: number;
  processed?: number | null;
  incomingbitmap?: string | null;
  outgoingbitmap?: string | null;
  inmessage?: string | null;
  messagetocbs?: string | null;
  messagefromcbs?: string | null;
  cbsprocess?: number | null;
  cbsonline?: number | null;
  cbsresponse?: string | null;
  responsemessage?: string | null;
  responsesent?: number | null;
  channel?: string | null;
  originaldata?: string | null;
  field39resp?: string | null;
  narration?: string | null;
  authorised?: number | null;
  branchcode?: string | null;
  field39original?: string | null;
  messageclass?: string | null;
  txncode?: string | null;
  currcode?: string | null;
  device?: string | null;
  branch2?: string | null;
  longerbranch?: number | null;
  datex?: Date | null;
  timex?: string | null;
  posted?: number | null;
  attempts?: number | null;
  originaldata2?: string | null;
  commission?: number | null;
  responsecreated?: number | null;
  online?: number | null;
  originaldata3?: string | null;
  toswitch?: string | null;
  fromswitch?: string | null;
  tocbs?: string | null;
  fromcbs?: string | null;
  postinglegs?: number | null;
  commissiontxncode?: string | null;
  hostref?: string | null;
  requestcreated?: number | null;
  requestmessage?: string | null;
  outgoingbitmapflex?: string | null;
  incomingbitmapflex?: string | null;
  requestsent?: number | null;
  minicbs?: number | null;
  reversed?: number | null;
  offlinesenttohost?: number | null;
  offlineresponse?: string | null;
  sourceLongerface?: string | null;
  mtirrn?: string | null;
  hostresponsecode?: string | null;
  field48?: string | null;
  source?: string | null;
}

export class Transactions implements ITransactions {
  constructor(
    public id?: number,
    public processed?: number | null,
    public incomingbitmap?: string | null,
    public outgoingbitmap?: string | null,
    public inmessage?: string | null,
    public messagetocbs?: string | null,
    public messagefromcbs?: string | null,
    public cbsprocess?: number | null,
    public cbsonline?: number | null,
    public cbsresponse?: string | null,
    public responsemessage?: string | null,
    public responsesent?: number | null,
    public channel?: string | null,
    public originaldata?: string | null,
    public field39resp?: string | null,
    public narration?: string | null,
    public authorised?: number | null,
    public branchcode?: string | null,
    public field39original?: string | null,
    public messageclass?: string | null,
    public txncode?: string | null,
    public currcode?: string | null,
    public device?: string | null,
    public branch2?: string | null,
    public longerbranch?: number | null,
    public datex?: Date | null,
    public timex?: string | null,
    public posted?: number | null,
    public attempts?: number | null,
    public originaldata2?: string | null,
    public commission?: number | null,
    public responsecreated?: number | null,
    public online?: number | null,
    public originaldata3?: string | null,
    public toswitch?: string | null,
    public fromswitch?: string | null,
    public tocbs?: string | null,
    public fromcbs?: string | null,
    public postinglegs?: number | null,
    public commissiontxncode?: string | null,
    public hostref?: string | null,
    public requestcreated?: number | null,
    public requestmessage?: string | null,
    public outgoingbitmapflex?: string | null,
    public incomingbitmapflex?: string | null,
    public requestsent?: number | null,
    public minicbs?: number | null,
    public reversed?: number | null,
    public offlinesenttohost?: number | null,
    public offlineresponse?: string | null,
    public sourceLongerface?: string | null,
    public mtirrn?: string | null,
    public hostresponsecode?: string | null,
    public field48?: string | null,
    public source?: string | null,
  ) {}
}
