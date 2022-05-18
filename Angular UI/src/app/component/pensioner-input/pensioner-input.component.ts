import { Component, OnInit } from '@angular/core';
import { PensionDetail } from 'src/app/PensionDetail';
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

  pension:PensionDetail[]=[];

  constructor(private pensionService:PensionerServiceService) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
    this.pensionService.getPensionDetail(this.credentials).subscribe(
      (pensiondetail:any)=>
      {
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
