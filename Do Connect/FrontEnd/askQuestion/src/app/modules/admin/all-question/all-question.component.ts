import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(private adminService:AuthAdminService,private router:Router,private userService:UserService) { }

  ngOnInit(): void {

    this.adminService.getAllQuestions().subscribe(data=>{
      this.questions = data;
      this.size = this.questions.length
      console.log(this.questions);
    })
  }

  // to approve the question
  approveQuestion(id:number)
  {
    this.adminService.approveQuestion(id).subscribe(data=>{
      alert("Question Approved Successfully");
      window.location.reload();
    })
  }

  // to delete the question
  deleteQuestion(id:number)
  {
    this.adminService.deleteQuestion(id).subscribe(data=>{ 
      alert("Question Deleted Successfully");
      window.location.reload();
    })
  }

}
