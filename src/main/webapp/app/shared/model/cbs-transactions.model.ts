export interface ICBSTransactions {
  id?: number;
  messageid?: string | null;
  channelcode?: string | null;
  messagetype?: string | null;
  transcurrency?: string | null;
  debtorsname?: string | null;
  debtorsaccountid?: string | null;
  debtorsphone?: string | null;
  creditorsname?: string | null;
  creditorsaccountid?: string | null;
  creditorsphone?: string | null;
  narration?: string | null;
  externalreference?: string | null;
  cbsreference?: string | null;
  cbsstatus?: string | null;
  cbsstatusdesc?: string | null;
  requestInstanttime?: Date | null;
  requestjson?: string | null;
  cbsrequestxml?: string | null;
  cbsresponsexml?: string | null;
  amount?: number | null;
}

export class CBSTransactions implements ICBSTransactions {
  constructor(
    public id?: number,
    public messageid?: string | null,
    public channelcode?: string | null,
    public messagetype?: string | null,
    public transcurrency?: string | null,
    public debtorsname?: string | null,
    public debtorsaccountid?: string | null,
    public debtorsphone?: string | null,
    public creditorsname?: string | null,
    public creditorsaccountid?: string | null,
    public creditorsphone?: string | null,
    public narration?: string | null,
    public externalreference?: string | null,
    public cbsreference?: string | null,
    public cbsstatus?: string | null,
    public cbsstatusdesc?: string | null,
    public requestInstanttime?: Date | null,
    public requestjson?: string | null,
    public cbsrequestxml?: string | null,
    public cbsresponsexml?: string | null,
    public amount?: number | null,
  ) {}
}
