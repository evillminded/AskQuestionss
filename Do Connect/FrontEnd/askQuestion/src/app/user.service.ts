import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as CryptoJS from 'crypto-js';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  
  // baseUrl = "http://localhost:3000/user"
  baseUrl = "http://localhost:8181/"

  // to register the user
  public userRegister(user:any)
  {
     return this.http.post(this.baseUrl+"user/register",user);
  }
    
    // to login user to portal
  public userLogin()
  {
    return this.http.get(this.baseUrl+"owner/getAllUsers");
  }

  public askQuestion(question:any)
  {
    return this.http.post(this.baseUrl+"user/askQuestion",question);
  }

  public getAnswerByQuestionId(id:Number)
  {
    return this.http.get(this.baseUrl+"user/getAnswers/"+id);
  }

  public postAnswer(answer:any)
  {
    return this.http.post(this.baseUrl+"user/giveAnswer",answer);
  }

  queryQuestion(keyword:any)
  {
    return this.http.get(this.baseUrl+"user/getQuestions/"+keyword);
  }

  public sendMessage(message:any)
  {
    return this.http.post("http://localhost:8282/"+"chat/sendMessage",message);
  }

  public getMessage()
  {
    return this.http.get(this.baseUrl+"user/getMessage");
  }

  uploadFiles(file:any)
  {
    return
  }

  encrypt(password:string)
  {
    return CryptoJS.AES.encrypt(password.trim(), "pass").toString();
  }

  decrypt(ecryptPass:any)
  {
    return CryptoJS.AES.decrypt(ecryptPass,"pass").toString(CryptoJS.enc.Utf8);
  }

}
