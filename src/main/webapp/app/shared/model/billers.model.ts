export interface IBillers {
  id?: number;
  billerid?: string;
  description?: string;
  billercollectionaccount?: string | null;
  datecreated?: Date | null;
  createdby?: string | null;
  approved?: number | null;
  approvedby?: string | null;
  approveddate?: Date | null;
  chargableproductid?: string | null;
  nonchargableproductid?: string | null;
  usdbillercollectionaccount?: string | null;
  enableduplicatecheck?: number | null;
  remarks?: string | null;
  sessionid?: string | null;
  reworkby?: string | null;
  status?: number | null;
  active?: number | null;
  rework?: number | null;
}

export class Billers implements IBillers {
  constructor(
    public id?: number,
    public billerid?: string,
    public description?: string,
    public billercollectionaccount?: string | null,
    public datecreated?: Date | null,
    public createdby?: string | null,
    public approved?: number | null,
    public approvedby?: string | null,
    public approveddate?: Date | null,
    public chargableproductid?: string | null,
    public nonchargableproductid?: string | null,
    public usdbillercollectionaccount?: string | null,
    public enableduplicatecheck?: number | null,
    public remarks?: string | null,
    public sessionid?: string | null,
    public reworkby?: string | null,
    public status?: number | null,
    public active?: number | null,
    public rework?: number | null,
  ) {}
}
