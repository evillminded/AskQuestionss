import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthAdminService } from '../services/auth-admin.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  admin:any
  isAdmin:boolean=false

  loginForm= new FormGroup({
    email:new FormControl(''),
    password: new FormControl(''),
  });
  constructor(private adminService: AuthAdminService, private router: Router,private userService: UserService) { }

  ngOnInit(): void {
  }

  adminLog()
  {
    // console.log(this.loginForm.value);
    if(sessionStorage.getItem("ahash")==null)
      this.adminService.getAllAdmin().subscribe(admin=>{
        this.admin = admin;
        for(let a = 0; a<this.admin.length; a++)
        {
          if(this.admin[a].email== this.loginForm.controls.email.value && this.admin[a].password == this.loginForm.controls.password.value)
          {
            alert("Admin Login Sucessfully");
            this.isAdmin=true;
            sessionStorage.setItem("ahash",this.userService.encrypt(this.admin[a].password));
            this.router.navigate(['admin']);
          }
        }
        if(!this.isAdmin)
        {
          alert("Invalid Credentials!");
          window.location.reload();
        }
      },
      (err) => {console.log(err.message)})
    else
      alert("already logged in");
    }

}
