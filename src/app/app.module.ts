import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TopicListComponent } from './topic-list/topic-list.component';
import { TopicSubmissionComponent } from './topic-submission/topic-submission.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TopicListComponent,
    TopicSubmissionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
