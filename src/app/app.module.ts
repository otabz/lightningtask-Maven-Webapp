import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TopicListComponent } from './topic-list/topic-list.component';
import { TopicSubmissionComponent } from './topic-submission/topic-submission.component';
import { TopicComponent } from './topic-list/topic/topic.component';
import { NoTopicComponent } from './topic-list/no-topic/no-topic.component';
import {TopicsService} from './topics.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TopicListComponent,
    TopicSubmissionComponent,
    TopicComponent,
    NoTopicComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [TopicsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
