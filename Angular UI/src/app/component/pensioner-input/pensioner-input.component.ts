import { Component, OnInit } from '@angular/core';
import { PensionResponse } from 'src/app/PensionDetail';
import { PensionerServiceService } from 'src/app/services/pensioner-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pensioner-input',
  templateUrl: './pensioner-input.component.html',
  styleUrls: ['./pensioner-input.component.css']
})
export class PensionerInputComponent implements OnInit {

  credentials={
    aadhaarNumber:""
  }
  result:any;

  pension:PensionResponse[]=[];

  constructor(private pensionService:PensionerServiceService) { }

  ngOnInit(): void {
  }

  processPension(){
    console.log("Processing Pension")
    this.pensionService.processPension(this.pension[0]).subscribe(
      complete=>{
        Swal.fire('Congratualtions!', 'Pension successfully disbursed!', 'success');
      },
      error=>{
        Swal.fire('Error!', "Pension can't be disbursed!", 'error');
      }
    );

  }

  onSubmit()
  {
    this.pensionService.getPensionDetail(this.credentials).subscribe(
      (pensiondetail:any)=>
      {
        this.pension=[]
        this.pension.push(pensiondetail);
      },
      error=>
      {
        Swal.fire('Invalid!','Invalid Details','error');  
        console.log(this.credentials);
        console.log(error);
      }
    );
  }

}
