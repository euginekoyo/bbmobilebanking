import dayjs from 'dayjs';

export interface ICUSTOMERACCOUNT {
  id?: number;
  cUSTOMERID?: number;
  aCCOUNTNUMBER?: string;
  aCCOUNTCLASS?: string | null;
  cUSTOMERNUMBER?: string | null;
  cIF?: string;
  tIMELINKED?: dayjs.Dayjs | null;
  bLOCKED?: number | null;
  sTOPPED?: number | null;
  dORMANT?: number | null;
}

export const defaultValue: Readonly<ICUSTOMERACCOUNT> = {};
