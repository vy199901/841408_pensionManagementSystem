import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class PensionerServiceService {

  constructor(private http:HttpClient) { }

  getPensionDetail(credentials:any)
  {
    return this.http.post( `${API_URL}/pensionDetail`,credentials);
  }

  processPension(credentials:any)
  {
    return this.http.post(`${API_URL}/processPension`,credentials);
  }
}
