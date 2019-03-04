import {Topic} from './topic.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';
import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class TopicsService {
  topics: Topic[] = [];
  topicsLoaded = new Subject<Topic []>();
  constructor(private http: HttpClient) {
  }

  list() {
    return this.http.get<Topic[]>(environment.url);
  }

  topicAt(index) {
    return this.topics[index];
  }

  submit(topic: any) {
    return this.http.post(environment.url, topic);
  }
}
