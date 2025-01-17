export interface IRANGE {
  id?: number;
  rANGEFROM?: number | null;
  rANGETO?: number | null;
  aMOUNT?: number | null;
  tXNTYPE?: string | null;
  tXNCODE?: string | null;
  cHARGEID?: number | null;
  cHANNEL?: string | null;
}

export const defaultValue: Readonly<IRANGE> = {};
