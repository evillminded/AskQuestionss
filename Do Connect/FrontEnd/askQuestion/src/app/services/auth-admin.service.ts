import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of, throwError } from 'rxjs';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService {

  baseUrl="http://localhost:8181/owner"

  constructor(private router: Router,private http:HttpClient) { }

   registerAdmin(admin:any)
   {
      return this.http.post(this.baseUrl+"/register",admin);
   }

   getAllAdmin()
   {
    return this.http.get(this.baseUrl+"/getAllOwner");
   }

   getAllQuestions()
   {
    return this.http.get(this.baseUrl+"/getAllQuestions");
   }

   approveQuestion(id:number)
   {
    return this.http.get(this.baseUrl+"/approveQuestion/"+id);
   }

   deleteQuestion(id:number)
   {
    return this.http.delete(this.baseUrl+"/deleteQuestion/"+id);
   }

}
