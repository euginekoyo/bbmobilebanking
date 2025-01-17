import dayjs from 'dayjs';

export interface IMESSAGESSMS {
  id?: number;
  tRNDATETIME?: dayjs.Dayjs | null;
  pHONENUMBER?: string | null;
  tRANSACTIONNO?: string | null;
  aCCOUNTNUMBER?: string | null;
  mESSAGE?: string | null;
  cHANNEL?: string | null;
  tRIALS?: number | null;
  pRIORITY?: number | null;
  rESPONSECODE?: string | null;
  rESPONSEMSG?: string | null;
  sENT?: number | null;
  dELIVERED?: number | null;
  tXNTYPE?: string | null;
  eRROREXCEPTION?: number | null;
  dATECREATED?: dayjs.Dayjs | null;
  dATESENT?: string | null;
  rTPSREQTIME?: string | null;
  fXGENERATED?: string | null;
  tAXPROCESSED?: number | null;
  bATCHNUMBER?: string | null;
  bATCHNUMBERTAX?: string | null;
  rESPONSETIME?: string | null;
  pDUSEQID?: string | null;
  rEMARKS?: string | null;
  rESENDBY?: string | null;
}

export const defaultValue: Readonly<IMESSAGESSMS> = {};
