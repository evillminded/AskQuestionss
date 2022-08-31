import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private service:UserService,private router:Router) { }

  ngOnInit(): void {
  }

  userRegister(user:any)
  {
    this.service.userRegister(user).subscribe(data=>{
      // console.log(user);
      alert("Thank you For Registering with Us!")
      this.router.navigate(['login']);
    })
  }
}
