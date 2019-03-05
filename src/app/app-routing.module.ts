import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TopicListComponent} from './topic-list/topic-list.component';
import {TopicComponent} from './topic-list/topic/topic.component';
import {NoTopicComponent} from './topic-list/no-topic/no-topic.component';
import {PreFetchGuard} from './pre-fetch.guard';
import {TopicSubmissionComponent} from './topic-submission/topic-submission.component';

const routes: Routes = [{path: '', redirectTo: 'topics', pathMatch: 'full'},
  {path: 'topics', component: TopicListComponent,
  children: [{path: '', component: NoTopicComponent},
    {path: ':id', component: TopicComponent}]
}, {path: 'submit', component: TopicSubmissionComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
