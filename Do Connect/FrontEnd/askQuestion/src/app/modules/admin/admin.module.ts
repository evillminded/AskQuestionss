import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AddAnswerComponent } from './add-answer/add-answer.component';

import { FormsModule } from '@angular/forms';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AllQuestionComponent } from './all-question/all-question.component';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    HeaderComponent,
    FooterComponent,
    AddAnswerComponent,
    AllQuestionComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    MatToolbarModule
  ]
})
export class AdminModule { }
