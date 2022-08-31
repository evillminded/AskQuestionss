import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-query-result',
  templateUrl: './query-result.component.html',
  styleUrls: ['./query-result.component.css']
})
export class QueryResultComponent implements OnInit {

  constructor(private userService:UserService,private route:ActivatedRoute) { }

  questions:any
  size:any
  myParam:any
  ngOnInit(): void {

    this.route.params.subscribe((params: Params) => this.myParam = params['id']);
    alert(this.myParam);
    this.userService.queryQuestion(this.myParam).subscribe(data=>{
      this.questions = data;
      this.size = this.questions.length
      console.log(this.questions);
    })
  }

}
