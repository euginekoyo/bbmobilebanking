import dayjs from 'dayjs';

export interface IBRANCHES {
  id?: number;
  bRANCHNAME?: string | null;
  bRANCHCODE?: string;
  aPPROVED?: number | null;
  eMAIL?: string | null;
  aDDRESS?: string | null;
  pHONE?: string | null;
  lOCATION?: string;
  cONTACTPERSON?: string | null;
  rEMARKS?: string | null;
  cREATEDBY?: string | null;
  cREATEDON?: dayjs.Dayjs | null;
  aPPROVEDBY?: string | null;
  aPPROVEDON?: string | null;
  cHECKERREMARKS?: string | null;
  dELETEDBY?: string | null;
  dELETEDON?: dayjs.Dayjs | null;
  dELETEREMARKS?: string | null;
  dELETED?: number | null;
  dECLINED?: number | null;
  dECLINEDDON?: string | null;
  dECLINEDBY?: string | null;
  sESSIONID?: string | null;
  rEWORKED?: number | null;
  rEWORKEDBY?: string | null;
  rEWORKEDON?: dayjs.Dayjs | null;
  dISTRICT?: string | null;
  rEGION?: string | null;
  rEGIONNAME?: string | null;
  rEPORTING?: number | null;
}

export const defaultValue: Readonly<IBRANCHES> = {};
