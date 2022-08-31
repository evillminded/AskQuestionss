import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnswerComponent } from './add-answer/add-answer.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AllQuestionComponent } from './all-question/all-question.component';

const routes: Routes = [

  { path:'', component:AdminDashboardComponent,
    children:[
      {path: 'allQuestion', component:AllQuestionComponent},
      {path: 'addAnswer', component:AddAnswerComponent},
      {path:'', redirectTo:'/admin/allQuestion', pathMatch:'full'}
    ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
