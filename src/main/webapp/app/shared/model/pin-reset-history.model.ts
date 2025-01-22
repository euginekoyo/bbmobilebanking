export interface IPinResetHistory {
  id?: number;
  phonenumber?: string | null;
  customername?: string | null;
  pinblockedon?: string | null;
  pinblockremarks?: string | null;
  pinresetby?: string | null;
  pinreseton?: string | null;
  pinresetapprovedby?: string | null;
  pinresetapprovedon?: string | null;
  pinresetremarks?: string | null;
  branchcode?: string | null;
}

export class PinResetHistory implements IPinResetHistory {
  constructor(
    public id?: number,
    public phonenumber?: string | null,
    public customername?: string | null,
    public pinblockedon?: string | null,
    public pinblockremarks?: string | null,
    public pinresetby?: string | null,
    public pinreseton?: string | null,
    public pinresetapprovedby?: string | null,
    public pinresetapprovedon?: string | null,
    public pinresetremarks?: string | null,
    public branchcode?: string | null,
  ) {}
}
