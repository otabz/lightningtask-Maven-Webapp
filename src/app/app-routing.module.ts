import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TopicListComponent} from './topic-list/topic-list.component';
import {TopicComponent} from './topic-list/topic/topic.component';
import {NoTopicComponent} from './topic-list/no-topic/no-topic.component';
import {PreFetchGuard} from './pre-fetch.guard';

const routes: Routes = [{path: '', redirectTo: 'topics', pathMatch: 'full'},
  {path: 'topics', component: TopicListComponent,
  children: [{path: '', component: NoTopicComponent},
    {path: ':id', component: TopicComponent}]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
