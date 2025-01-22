export interface ISPSParticipatingCodes {
  id?: number;
  biccode?: string | null;
  bicname?: string | null;
  bicstatus?: string | null;
}

export class SPSParticipatingCodes implements ISPSParticipatingCodes {
  constructor(
    public id?: number,
    public biccode?: string | null,
    public bicname?: string | null,
    public bicstatus?: string | null,
  ) {}
}
