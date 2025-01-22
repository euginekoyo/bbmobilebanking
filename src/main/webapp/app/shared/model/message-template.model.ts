export interface IMessageTemplate {
  id?: number;
  messagetype?: string | null;
  description?: string | null;
  messageenglish?: string | null;
  messagesomali?: string | null;
  createdon?: Date | null;
}

export class MessageTemplate implements IMessageTemplate {
  constructor(
    public id?: number,
    public messagetype?: string | null,
    public description?: string | null,
    public messageenglish?: string | null,
    public messagesomali?: string | null,
    public createdon?: Date | null,
  ) {}
}
