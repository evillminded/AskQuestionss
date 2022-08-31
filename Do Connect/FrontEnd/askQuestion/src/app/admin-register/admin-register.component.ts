import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthAdminService } from '../services/auth-admin.service';

@Component({
  selector: 'app-admin-register',
  templateUrl: './admin-register.component.html',
  styleUrls: ['./admin-register.component.css']
})
export class AdminRegisterComponent implements OnInit {

  constructor(private service:AuthAdminService,private router:Router) { }

  ngOnInit(): void {
  }

  adminRegister(data:any)
  {
    this.service.registerAdmin(data).subscribe(data=>{
      alert("Admin successfully Registered!");
      this.router.navigate(['adminLogin']);
    })
    // console.log(data);
  }

}
