import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Topic} from '../../topic.model';
import {TopicsService} from '../../topics.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {
  topic: Topic;
  constructor(private route: ActivatedRoute,
              private service: TopicsService) { }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.topic = this.service.topicAt(params['id']);
      }
    );
  }

}
