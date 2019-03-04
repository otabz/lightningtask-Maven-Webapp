import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TopicListComponent} from './topic-list/topic-list.component';

const routes: Routes = [{path: '', component: TopicListComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
