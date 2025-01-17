export interface ICHARGERANGES {
  id?: number;
  bILLERID?: string;
  pROCESSINGCODE?: string;
  mAX?: number;
  mIN?: number;
  aMOUNT?: number;
  cREATEDBY?: string | null;
  aPPROVEDBY?: string | null;
  cREATEDAT?: string | null;
  aPPROVEDON?: string | null;
  aPPROVED?: number | null;
  dECLINED?: number | null;
  dECLINEDBY?: string | null;
  cHARGEID?: number;
}

export const defaultValue: Readonly<ICHARGERANGES> = {};
