export class Topic {

  constructor(public subject: string,
              public description: string,
              public userId: string,
              public time: string,
              public talkDate: string,
              public hostName: string,
              public ipAddress: string,
              public userAgent: string) {}
}
