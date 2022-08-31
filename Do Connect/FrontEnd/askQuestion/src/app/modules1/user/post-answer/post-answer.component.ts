import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
import { Answer } from '../Answer';

@Component({
  selector: 'app-post-answer',
  templateUrl: './post-answer.component.html',
  styleUrls: ['./post-answer.component.css']
})
export class PostAnswerComponent implements OnInit {

  constructor(private service:UserService,private route:ActivatedRoute,private router:Router) { }

  userName:any
  user:any
  tempUser:any
  userId:any
  quesId:any

  ngOnInit(): void {
    this.quesId = this.route.snapshot.paramMap.get('id');
    this.service.userLogin().subscribe(user=>{
      this.user = user;
      for(let a = 0; a<this.user.length; a++)
      {
        if(this.user[a].id == sessionStorage.getItem('uhash'))
        {
          this.tempUser = this.user[a];
          // console.log(this.tempUser);
          this.userId=this.user[a].id;
          this.userName=this.user[a].name;
        }
      }
    })

  }

  
  postAnswer(data:any)
  {
    // alert("hello there");
    let answer:Answer={
     userId: this.userId,
     questionId: this.quesId,
     answer: data.question
   }

   this.service.postAnswer(answer).subscribe(data=>{
      // console.log(data);
      alert("Answer posted successfully");
      this.router.navigate(['user/allQuestion']);
   })
  // console.log(data.question);
  }

}
