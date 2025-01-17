import dayjs from 'dayjs';

export interface ILINKEDACCOUNTS {
  id?: number;
  aCOUNTNAME?: string | null;
  aCCOUNTCLASS?: string | null;
  aCCOUNTCURRENCY?: string | null;
  aCCOUNTNUMBER?: string | null;
  cIF?: string | null;
  tIMELINKED?: dayjs.Dayjs | null;
  pHONENUMBER?: string | null;
  aPPROVEDON?: dayjs.Dayjs | null;
  aPPROVED?: string | null;
  dECLINED?: string | null;
  dECLINEDON?: dayjs.Dayjs | null;
  rEMARKS?: string;
  lINKEDBY?: string | null;
  aPPROVEDBY?: string | null;
  lINKED?: string | null;
  aCTIVE?: string | null;
  dELINKEDBY?: string | null;
  dELINKEDON?: dayjs.Dayjs | null;
  dELINKED?: string | null;
  aCCOUNTALIAS?: string | null;
  sHORTCUTS?: string | null;
}

export const defaultValue: Readonly<ILINKEDACCOUNTS> = {};
