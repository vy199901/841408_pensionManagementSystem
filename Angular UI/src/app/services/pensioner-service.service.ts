import { HttpClient } from '@angular/common/http';
import { Injectable, ɵsetCurrentInjector } from '@angular/core';
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

  processPension(pension:any)
  {
    const transactionDetail={
      aadhaarNumber:pension.pensionerDetail.aadhaarNumber,
      transactionAmount:pension.pensionAmount-pension.bankCharge,
      accountNumber:pension.pensionerDetail.bankDetail.accountNumber,
      transactionTimestamp:new Date()
  };
    return this.http.post(`${API_URL}/processPension`,transactionDetail);
  }
}
