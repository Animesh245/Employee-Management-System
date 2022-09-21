import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authenticationUrl = "http://localhost:8080/auth"

  constructor( private httpClient: HttpClient) { }


  // calling server for generating token
  generateToken(credentials:any)
  {
    return this.httpClient.post(`${this.authenticationUrl}/token`, credentials)
  }

  //to get the token
  getToken()
  {
    return localStorage.getItem('token');
  }

  // to keep an employee loggedin after authentication
  employeeLogin(token: string)
  {
    localStorage.setItem("token", token)
    return true;
  }

  isLoggedIn()
  {
    let token = localStorage.getItem("token");
    return !(token == undefined || token === '' || token == null);
  }

  logout()
  {
    localStorage.removeItem('token');
    return true;
  }
}
