export interface ICustomerAccount {
  id?: number;
  customerid?: number;
  accountnumber?: string;
  accountclass?: string | null;
  customernumber?: string | null;
  cif?: string;
  timelinked?: Date | null;
  blocked?: number | null;
  stopped?: number | null;
  dormant?: number | null;
}

export class CustomerAccount implements ICustomerAccount {
  constructor(
    public id?: number,
    public customerid?: number,
    public accountnumber?: string,
    public accountclass?: string | null,
    public customernumber?: string | null,
    public cif?: string,
    public timelinked?: Date | null,
    public blocked?: number | null,
    public stopped?: number | null,
    public dormant?: number | null,
  ) {}
}
