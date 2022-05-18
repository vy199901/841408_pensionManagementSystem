import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  doLogin(credentials:any)
  {
    return this.http.post(`${API_URL}/token`,credentials);
  }

  getPensionerList()
  {
    return this.http.get<[]>(`${API_URL}/details`);
  }

  loginUser(token:any,credentials:any)
  {
    localStorage.setItem('token',token);
    localStorage.setItem('uname',credentials.userName);
    return true;
  }

  loginUserName()
  {
    return localStorage.getItem('uname');
  }

  isLoggedIn()
  {
      let token=localStorage.getItem('token');
      if(token==undefined||token==''||token==null)
      {
        return false;
      }else{

        return true;
      }
  }
  logout()
  {
    localStorage.removeItem('token');
    return true;
  }

  getToken()
  {
    return localStorage.getItem('token');
  }
  
}
