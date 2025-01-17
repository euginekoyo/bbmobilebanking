export interface ILIMITS {
  id?: number;
  tRANSACTIONTYPE?: string;
  pROCODE?: string | null;
  cHANNEL?: string | null;
  tRANSACTIONLIMIT?: number;
  dAILYLIMIT?: number | null;
  rEGISTEREDBY?: string | null;
  rEGISTEREDDATE?: string | null;
  aPPROVED?: string | null;
  aPPROVEDBY?: string | null;
  aPPROVEDDATE?: string | null;
  uPDATEDBY?: string | null;
  uPDATEDDATE?: string | null;
  rEWORK?: number | null;
  rEWORKBY?: string | null;
  sESSIONID?: string | null;
}

export const defaultValue: Readonly<ILIMITS> = {};
