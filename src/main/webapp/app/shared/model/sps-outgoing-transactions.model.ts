export interface ISPSOutgoingTransactions {
  id?: number;
  messageid?: string | null;
  channelcode?: string | null;
  callbackurl?: string | null;
  messagetype?: string | null;
  transcurrency?: string | null;
  debtorsname?: string | null;
  debtorsaccountid?: string | null;
  debtorsbankcode?: string | null;
  debtorsphone?: string | null;
  beneficiaryname?: string | null;
  beneficiaryaccountid?: string | null;
  beneficiarybankcode?: string | null;
  beneficiaryphone?: string | null;
  narration?: string | null;
  externalreference?: string | null;
  cbsreference?: string | null;
  messageendtoendid?: string | null;
  transactionstatus?: string | null;
  transactionstatusdesc?: string | null;
  spsstatus?: string | null;
  spsstatusdesc?: string | null;
  cbsstatus?: string | null;
  cbsstatusdesc?: string | null;
  requestInstanttime?: Date | null;
  isomessagetype?: string | null;
  requestjson?: string | null;
  spsrequestxml?: string | null;
  spsresponsexml?: string | null;
  amount?: number | null;
  callbackstatus?: string | null;
  callbackstatusdesc?: string | null;
}

export class SPSOutgoingTransactions implements ISPSOutgoingTransactions {
  constructor(
    public id?: number,
    public messageid?: string | null,
    public channelcode?: string | null,
    public callbackurl?: string | null,
    public messagetype?: string | null,
    public transcurrency?: string | null,
    public debtorsname?: string | null,
    public debtorsaccountid?: string | null,
    public debtorsbankcode?: string | null,
    public debtorsphone?: string | null,
    public beneficiaryname?: string | null,
    public beneficiaryaccountid?: string | null,
    public beneficiarybankcode?: string | null,
    public beneficiaryphone?: string | null,
    public narration?: string | null,
    public externalreference?: string | null,
    public cbsreference?: string | null,
    public messageendtoendid?: string | null,
    public transactionstatus?: string | null,
    public transactionstatusdesc?: string | null,
    public spsstatus?: string | null,
    public spsstatusdesc?: string | null,
    public cbsstatus?: string | null,
    public cbsstatusdesc?: string | null,
    public requestInstanttime?: Date | null,
    public isomessagetype?: string | null,
    public requestjson?: string | null,
    public spsrequestxml?: string | null,
    public spsresponsexml?: string | null,
    public amount?: number | null,
    public callbackstatus?: string | null,
    public callbackstatusdesc?: string | null,
  ) {}
}
