import {Topic} from './topic.model';

export class TopicsService {
  topics: Topic[] = [new Topic('First Topic', 'Defect Analysis and fix (Java Optimizing Techniques) ' +
    'Code Quality/Standards/Practices (TDD, Code refactoring, Clean Code, Clean Architecture)', 'me@email.com',
    '12 Januray 2019 12:00', '04 February 2019', 'localhost', '0.0.0.0', 'Mozilla'),
    new Topic('Second Topic', 'Different Defect Analysis and fix (Java Optimizing Techniques)\\n\' +\n' +
      '        \'Code Quality/Standards/Practices (TDD, Code refactoring, Clean Code, Clean Architecture)\\n\' +\n' +
      '        \'Microservices Environment', 'you@email.com',
      '01 January 2019 11:35:40', '4 February 2019', 'localhost', '127.0.0.1', 'Postman')];
  topicAt(index) {
    return this.topics[index];
  }
}
