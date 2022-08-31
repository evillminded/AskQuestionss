import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthAdminService } from 'src/app/services/auth-admin.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private adminService:AuthAdminService,private router:Router) { }

  ngOnInit(): void {
  }

  logout()
  {
    sessionStorage.removeItem("ahash");
    alert("Admin Successfully logged Out!");
    this.router.navigate(['adminLogin']);
  }
}
