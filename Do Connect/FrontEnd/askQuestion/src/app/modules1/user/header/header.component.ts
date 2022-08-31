import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private service:UserService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
  }

  logout()
  {
    sessionStorage.removeItem("hash");
    alert("logged Out successfully!");
    this.router.navigate(['login']);
  }

  addQuestion()
  {
    alert("Question added");
  }

  query(data:any)
  {
    let word = data.search;
    this.router.navigate(['/user/query/',word]); 
    // console.log(word);
  }
}
