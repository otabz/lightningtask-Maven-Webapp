export class Topic {

  constructor(public subject: string,
              public description: string,
              public email: string,
              public submittedAt: string,
              public talkAt: string,
              public host: string,
              public ip: string,
              public agent: string) {}
}
