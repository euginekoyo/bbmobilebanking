import dayjs from 'dayjs';
import { ICHARGERANGES } from 'app/shared/model/chargeranges.model';
import { IRANGE } from 'app/shared/model/range.model';

export interface ICHARGE {
  id?: number;
  tXNTYPE?: string;
  fEEMODE?: number | null;
  aMOUNT?: number | null;
  dATECREATED?: dayjs.Dayjs | null;
  cREATEDBY?: string | null;
  aPPROVED?: string | null;
  aPPROVEDBY?: string | null;
  cHANNEL?: string | null;
  tXNCODE?: number | null;
  dESCRIPTION?: string | null;
  aPPROVEDDATE?: dayjs.Dayjs | null;
  cHARGERANGES?: ICHARGERANGES | null;
  rANGE?: IRANGE | null;
}

export const defaultValue: Readonly<ICHARGE> = {};
