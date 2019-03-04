import { Component, OnInit } from '@angular/core';
import {TopicsService} from '../topics.service';
import {Topic} from '../topic.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {

  constructor(private router: ActivatedRoute,
    private service: TopicsService) {
  }

  ngOnInit() {
    this.service.list();
  }
}
