import dayjs from 'dayjs';

export interface IBILLERS {
  id?: number;
  bILLERID?: string;
  dESCRIPTION?: string;
  bILLERCOLLECTIONACCOUNT?: string | null;
  dATECREATED?: dayjs.Dayjs | null;
  cREATEDBY?: string | null;
  aPPROVED?: number | null;
  aPPROVEDBY?: string | null;
  aPPROVEDDATE?: dayjs.Dayjs | null;
  cHARGABLEPRODUCTID?: string | null;
  nONCHARGABLEPRODUCTID?: string | null;
  uSDBILLERCOLLECTIONACCOUNT?: string | null;
  eNABLEDUPLICATECHECK?: number | null;
  rEMARKS?: string | null;
  sESSIONID?: string | null;
  rEWORKBY?: string | null;
  sTATUS?: number | null;
  aCTIVE?: number | null;
  rEWORK?: number | null;
}

export const defaultValue: Readonly<IBILLERS> = {};
