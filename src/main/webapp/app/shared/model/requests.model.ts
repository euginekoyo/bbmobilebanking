import dayjs from 'dayjs';

export interface IREQUESTS {
  id?: number;
  mOBILENUMBER?: string | null;
  aCCOUNTNO?: string | null;
  cURRENCY?: string | null;
  cIF?: string | null;
  rEQUESTTYPE?: string | null;
  rEQUESTCHARGE?: number | null;
  rEQUESTSTATUS?: string | null;
  dATEREQUESTED?: dayjs.Dayjs | null;
  tRNREFNO?: string | null;
  nOOFBOOKS?: number | null;
  nOOFLEAVES?: string | null;
  aPPROVED?: number | null;
  cHANNEL?: string | null;
  aPPROVEDBY?: string | null;
  aPPROVEDON?: dayjs.Dayjs | null;
  cHECKERREMARKS?: string | null;
  rESPCODE?: string | null;
  rESPDESCRIPTION?: string | null;
  dATERESPONDED?: dayjs.Dayjs | null;
  cUSTOMERNAME?: string | null;
  rEJECTED?: number | null;
  rEJECTEDBY?: string | null;
  rEJECTEDON?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IREQUESTS> = {};
