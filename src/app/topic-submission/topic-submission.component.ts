import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormGroup, NgForm} from '@angular/forms';
import {Router} from '@angular/router';
import {TopicsService} from '../topics.service';
import {Topic} from '../topic.model';
import {HttpErrorResponse} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-topic-submission',
  templateUrl: './topic-submission.component.html',
  styleUrls: ['./topic-submission.component.css']
})
export class TopicSubmissionComponent implements OnInit {
  @ViewChild('f') form: NgForm;
  showClear = false;
  submission;
  talk;
  show = false;
  topic = {
    subject: '',
    description: '',
    email: ''
  };
  cerror = {
    error: ''
  };
  constructor(private router: Router,
              private service: TopicsService) { }

  ngOnInit() {
  }

  onBack() {
    this.router.navigate(['/topics']);
  }

  onSubmit() {
    this.service.submit({
      subject: this.form.value.topic,
      description: this.form.value.description,
      email: this.form.value.email
    })
      .subscribe(
      (response: Topic) => {
        this.show = true;
        this.showClear = true;
        this.form.setValue({
          topic: response.subject,
          description: response.description,
          email: response.userId
        });
        this.submission = response.time;
        this.talk = response.talkDate;
      },
      error => {
        this.form.controls['topic'].setErrors({'incorrect': true});
        this.cerror = error.error;
      }
    );
  }

  onClear() {
    this.form.setValue({
      topic: '',
      description: '',
      email: ''
    });
    this.submission = '';
    this.talk = '';
    this.showClear = false;
  }

  onChange() {
    this.form.controls['topic'].setErrors(null);
    this.cerror.error = '';
  }
}
