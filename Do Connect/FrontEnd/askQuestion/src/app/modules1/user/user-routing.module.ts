import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QueryResultComponent } from 'src/app/query-result/query-result.component';
import { AllAnswerComponent } from './all-answer/all-answer.component';
import { AllQuestionComponent } from './all-question/all-question.component';
import { AskQuestionComponent } from './ask-question/ask-question.component';
import { ChatboxComponent } from './chatbox/chatbox.component';
import { PostAnswerComponent } from './post-answer/post-answer.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';

const routes: Routes = [

  { path:'', component:UserDashboardComponent,
  children:[
    {path: 'allQuestion', component:AllQuestionComponent},
    {path: 'askQuestion', component:AskQuestionComponent},
    {path:'query/:id',component:QueryResultComponent},
    {path: 'postAnswer/:id',   component:PostAnswerComponent},
    {path: 'allAnswer/:id',   component:AllAnswerComponent},
    {path:'chatbox', component:ChatboxComponent},
    {path:'', redirectTo:'/user', pathMatch:'full'},
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
