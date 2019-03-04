import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Topic} from '../../topic.model';
import {TopicsService} from '../../topics.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {
  topic: Topic = new Topic('', '', '', '', '', '',
    '', '');
  id: Number;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private service: TopicsService) { }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = params['id'];
        if (this.service.topics.length === 0) {
            this.router.navigate(['/topics']);
        } else {
          this.topic = this.service.topicAt(this.id);
        }
      }
    );
  }

}
