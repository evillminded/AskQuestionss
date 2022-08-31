import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
import { Problem } from '../Problem';

@Component({
  selector: 'app-ask-question',
  templateUrl: './ask-question.component.html',
  styleUrls: ['./ask-question.component.css']
})
export class AskQuestionComponent implements OnInit {
  userName:any
  user:any
  tempUser:any
  userId:any

  uploadedImage!: File;  
  dbImage: any; 
  postResponse: any;
  successResponse: string="";
  image: any;


  constructor(private service:UserService,private router:Router,private http:HttpClient) { }

  ngOnInit(): void {

    this.service.userLogin().subscribe(user=>{
      this.user = user;
      for(let a = 0; a<this.user.length; a++)
      {
        if(this.user[a].id == sessionStorage.getItem('uhash'))
        {
          this.tempUser = this.user[a];
          console.log(this.tempUser);
          this.userId=this.user[a].id;
          this.userName=this.user[a].name;
        }
      }
    })
    // console.log(this.userId);
    // console.log(this.service.encrypt(this.password));
    // sessionStorage.setItem('token',this.service.encrypt(this.password));
  }


  askQuestion(data:any)
  {
    // alert(this.userId);

    let problem:Problem={
      userId: this.userId,
      topic: data.topic,
      question: data.question,
      userName:this.userName
    };
    
    let token = sessionStorage.getItem("uhash");
    if(token!=null && token!=undefined && token!="")
    {
      this.service.askQuestion(problem).subscribe(data=>{
        alert("Question Posted Successfully!");
        this.router.navigate(['user/allQuestion']);
      })
    }
    // console.log(data);
  }


  public onImageUpload(event:any) {    
    this.uploadedImage = event.target.files[0];
  }


  imageUploadAction() {    
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);
  

    this.http.post('http://localhost:8181/user/upload/image/', imageFormData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) { 
          this.postResponse = response;                
          this.successResponse = this.postResponse.body.message;
        } else {
          this.successResponse = 'Image not uploaded due to some error!';
        }
      }
      );
    }

  viewImage() {
    this.http.get('http://localhost:8181/user/get/image/info/' + this.image)
      .subscribe(
        res => {
          this.postResponse = res;          
          this.dbImage = 'data:image/jpeg;base64,' + this.postResponse.image;
        }
      );
  }


}
