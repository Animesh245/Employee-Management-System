import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials=
  {
    username:'',
    password:''
  }

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }
  
  onSubmit()
  {
    if((this.credentials.username != null && this.credentials.password != null) && (this.credentials.username != '' && this.credentials.password != ''))
    {
      // console.log("Username: " + this.credentials.username +" and password "+  this.credentials.password)
      this.authService.generateToken(this.credentials)
      .subscribe({
          next: 
          (response:any) => this.authService.employeeLogin(response.token) && console.log(response.token),
          error: e => console.error(e),
          complete: () => window.location.href="/"
        }
        )}
        else{
      console.log("Fields are emplty ")
    }
  };

}
