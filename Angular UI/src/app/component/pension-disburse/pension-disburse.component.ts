import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PensionerServiceService } from 'src/app/services/pensioner-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pension-disburse',
  templateUrl: './pension-disburse.component.html',
  styleUrls: ['./pension-disburse.component.css']
})
export class PensionDisburseComponent implements OnInit {

  transactionList=[{
    aadhaarNumber:"",
    transactionAmount:"",
    accountNumber:"",
    transactionTimestamp:"",
  }];

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
    this.getTransaction();
  }

  private getTransaction()
  {
    this.loginService.getTransactionList().subscribe(
      data=>{
        this.transactionList=data;
        console.log(this.transactionList);
      }
    );
  }
}
