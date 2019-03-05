import { Component, OnInit } from '@angular/core';
import {TopicsService} from '../topics.service';
import {ActivatedRoute} from '@angular/router';
import {catchError} from 'rxjs/operators';
import {Topic} from '../topic.model';
import {HttpErrorResponse} from '@angular/common/http';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  crashed = 'kkk';
  constructor(private router: ActivatedRoute,
    public service: TopicsService) {
  }

  ngOnInit() {
    this.service.list().pipe(catchError(this.handleError))
      .subscribe(
      (response: Topic[]) => {
        this.service.topics = response;
      }
    );
  }

  handleError(error) {
    return throwError(error);
  }
}
