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
    this.http.get<Topic[]>(environment.list).subscribe(
      (response) => this.topics = response.slice()
    );
  }

  topicAt(index) {
    return this.topics[index];
  }
}
