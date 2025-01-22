export interface IChannels {
  id?: number;
  channel?: string | null;
  description?: string | null;
  bin?: string | null;
}

export class Channels implements IChannels {
  constructor(
    public id?: number,
    public channel?: string | null,
    public description?: string | null,
    public bin?: string | null,
  ) {}
}
