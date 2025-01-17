import dayjs from 'dayjs';

export interface IMESSAGETEMPLATES {
  id?: number;
  mESSAGETYPE?: string | null;
  dESCRIPTION?: string | null;
  mESSAGEENGLISH?: string | null;
  mESSAGESOMALI?: string | null;
  cREATEDON?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IMESSAGETEMPLATES> = {};
