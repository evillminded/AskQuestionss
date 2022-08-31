import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm= new FormGroup({
    email:new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private router: Router,private service:UserService) { }

  isUser:boolean=false;
  user:any
  encryptPass:any 
  decryptPass:any

  ngOnInit(): void {
  }

  userLogin()
  {
    // console.log(this.loginForm.controls.email.value);
    // console.log(this.loginForm.controls.password.value);
    this.service.userLogin().subscribe(user=>{
      this.user = user;
        for(let a = 0; a<this.user.length; a++)
        {
          if(this.loginForm.controls.email.value == this.user[a].email  && this.loginForm.controls.password.value == this.user[a].password)
          {
            alert("login successfull!");
            this.router.navigate(['user/allQuestion']);
            sessionStorage.setItem("uhash",this.user[a].id);
            this.isUser = true;
          }
        }
      if(!this.isUser)
      {
        alert("Invalid Credentials!");
        window.location.reload();
      }
      // console.log(this.loginForm.value);
    },
    (err) => {console.log(err.message)})
  }

  //method is used to encrypt and decrypt the text  
//   convertText(conversion:string) {  
//     if (conversion=="encrypt") {  
//       this.conversionEncryptOutput = CryptoJS.AES.encrypt(this.plainText.trim(), this.encPassword.trim()).toString();  
//     }  
//     else {  
//       this.conversionDecryptOutput = CryptoJS.AES.decrypt(this.encryptText.trim(), this.decPassword.trim()).toString(CryptoJS.enc.Utf8);  
     
//   }  
// }

}
