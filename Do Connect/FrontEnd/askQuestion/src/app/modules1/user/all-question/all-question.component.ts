import { Component, OnInit } from '@angular/core';
import { AuthAdminService } from 'src/app/services/auth-admin.service';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-all-question',
  templateUrl: './all-question.component.html',
  styleUrls: ['./all-question.component.css']
})
export class AllQuestionComponent implements OnInit {

  questions:any
  size:any
  constructor(private adminService:AuthAdminService,private userService:UserService) { }

  ngOnInit(): void {

    this.adminService.getAllQuestions().subscribe(questions=>{
      this.questions = questions;
      this.size = this.questions.length
      console.log(this.questions);
      console.log(this.size);
    })
  }
}
