export interface IMobileAppTransactions {
  id?: number;
  channel?: string | null;
  channelIp?: string | null;
  channelReference?: string | null;
  channelTimestamp?: string | null;
  clientId?: string | null;
  createdAt?: string | null;
  debitAccount?: string | null;
  direction?: string | null;
  errorDescription?: string | null;
  geolocation?: string | null;
  hostCode?: string | null;
  phoneNumber?: string | null;
  responseCode?: string | null;
  responseMessage?: string | null;
  transactionCode?: string | null;
  transactionType?: string | null;
  userAgent?: string | null;
  userAgentVersion?: string | null;
  amount?: string | null;
  chargeamount?: string | null;
  creditAccount?: string | null;
  cbsReference?: string | null;
}

export class MobileAppTransactions implements IMobileAppTransactions {
  constructor(
    public id?: number,
    public channel?: string | null,
    public channelIp?: string | null,
    public channelReference?: string | null,
    public channelTimestamp?: string | null,
    public clientId?: string | null,
    public createdAt?: string | null,
    public debitAccount?: string | null,
    public direction?: string | null,
    public errorDescription?: string | null,
    public geolocation?: string | null,
    public hostCode?: string | null,
    public phoneNumber?: string | null,
    public responseCode?: string | null,
    public responseMessage?: string | null,
    public transactionCode?: string | null,
    public transactionType?: string | null,
    public userAgent?: string | null,
    public userAgentVersion?: string | null,
    public amount?: string | null,
    public chargeamount?: string | null,
    public creditAccount?: string | null,
    public cbsReference?: string | null,
  ) {}
}
