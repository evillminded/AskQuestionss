import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-all-answer',
  templateUrl: './all-answer.component.html',
  styleUrls: ['./all-answer.component.css']
})
export class AllAnswerComponent implements OnInit {

  constructor(private route:ActivatedRoute,private service:UserService) { }
  size:any
  quesId:any
  answers:any
  ngOnInit(): void {
    this.quesId = this.route.snapshot.paramMap.get('id');
    this.service.getAnswerByQuestionId(this.quesId).subscribe(answer=>{
      this.answers = answer;
      this.size = this.answers.length
      console.log(this.answers);
    })
  }

}
