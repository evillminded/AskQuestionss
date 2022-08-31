import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AllQuestionComponent } from './all-question/all-question.component';
import { AskQuestionComponent } from './ask-question/ask-question.component';

import {MatToolbarModule} from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';
import { PostAnswerComponent } from './post-answer/post-answer.component';
import { AllAnswerComponent } from './all-answer/all-answer.component';
import { ChatboxComponent } from './chatbox/chatbox.component';



@NgModule({
  declarations: [
    UserDashboardComponent,
    HeaderComponent,
    FooterComponent,
    AllQuestionComponent,
    AskQuestionComponent,
    PostAnswerComponent,
    AllAnswerComponent,
    ChatboxComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatToolbarModule,
    FormsModule
  ],
})
export class UserModule { }
