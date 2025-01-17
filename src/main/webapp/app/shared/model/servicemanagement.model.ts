import dayjs from 'dayjs';

export interface ISERVICEMANAGEMENT {
  id?: number;
  pROCESSINGCODE?: string | null;
  aCTIVE?: string | null;
  cREATEDBY?: string | null;
  dATECREATED?: dayjs.Dayjs | null;
  aPPROVED?: number | null;
  aPPROVEDBY?: string | null;
  aPPROVEDDATE?: dayjs.Dayjs | null;
  aDAPTORTYPE?: string | null;
  dESTINATION?: string | null;
  tHIRDPARTYRESPONSE?: number | null;
  tELCO?: string | null;
  dESCRIPTION?: string;
  rEMARKS?: string | null;
  sESSIONID?: string | null;
  rEWORKBY?: string | null;
}

export const defaultValue: Readonly<ISERVICEMANAGEMENT> = {};
