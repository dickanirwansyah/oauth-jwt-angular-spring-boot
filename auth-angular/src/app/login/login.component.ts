import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginData = {
    username : "",
    password : ""
  }

  constructor(
    private auth: AuthService
  ) { }

  ngOnInit() {
  }

  login(){
    this.auth.getOAuth2(this.loginData);
  }

}
