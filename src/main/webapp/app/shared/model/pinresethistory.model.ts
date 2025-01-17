export interface IPINRESETHISTORY {
  id?: number;
  pHONENUMBER?: string | null;
  cUSTOMERNAME?: string | null;
  pINBLOCKEDON?: string | null;
  pINBLOCKREMARKS?: string | null;
  pINRESETBY?: string | null;
  pINRESETON?: string | null;
  pINRESETAPPROVEDBY?: string | null;
  pINRESETAPPROVEDON?: string | null;
  pINRESETREMARKS?: string | null;
  bRANCHCODE?: string | null;
}

export const defaultValue: Readonly<IPINRESETHISTORY> = {};
