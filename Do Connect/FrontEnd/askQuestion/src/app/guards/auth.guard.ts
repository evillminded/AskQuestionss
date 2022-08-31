import { AuthAdminService } from './../services/auth-admin.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private auth1: AuthAdminService){

  }
  canActivate( //use Api here for authenticate
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      return true;
  }
  
}
