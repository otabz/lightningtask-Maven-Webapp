import { Component, OnInit } from '@angular/core';
import {TopicsService} from '../topics.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {

  topics = [];

  constructor(private service: TopicsService) {
    this.topics = service.topics;
  }

  ngOnInit() {
  }

}
