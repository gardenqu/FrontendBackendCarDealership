import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  constructor(){

  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  username:String= ""
  password:String= ""


  public BasicAuthenication(){

    console.log(this.username)
    console.log(this.password)


  }



}
