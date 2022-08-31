import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { Message } from '../message';

@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.css']
})
export class ChatboxComponent implements OnInit {

  constructor(private service:UserService) { }

  messages:any
  searchValue:any
  user:any
  tempUser:any
  userName:any
  val:boolean =false
  mess:any
  ngOnInit(): void {

    this.service.userLogin().subscribe(user=>{
      this.user = user;
      for(let a = 0; a<this.user.length; a++)
      {
        if(this.user[a].id == sessionStorage.getItem('uhash'))
        {
          this.tempUser = this.user[a];
          this.userName=this.user[a].name;
        }
      }
    })
    this.service.getMessage().subscribe(data=>{
      this.messages = data;
      console.log(this.messages);
    })
  }

  onSubmit($event: { keyCode: number; })
  {

    if($event.keyCode === 13)
    {
      let message:Message={
        fromUser: this.tempUser.name,
        message: this.searchValue
      }
        this.val = true
        this.mess = this.searchValue
        // console.log(this.searchValue);
        // this.searchValue = null;
        // console.log(message);
        // this.searchValue = null;
        this.service.sendMessage(message).subscribe(data=>{
          console.log("message sent");
        })

        this.searchValue = null;

    }
  }
}
