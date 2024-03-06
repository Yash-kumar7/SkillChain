import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from 'src/app/services/common.services';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employerdetails',
  templateUrl: './employerdetails.component.html',
  styleUrls: ['./employerdetails.component.css'],
  providers:[CommonService],
})
export class EmployerdetailsComponent {
  EmployerDetails: FormGroup;
  EmployerDetailsObj: any;

  constructor(private fb: FormBuilder, private commonService: CommonService, private http: HttpClient) {
    this.EmployerDetails = this.fb.group({
      name: ['', Validators.required],
      companyname: ['', Validators.required],
      aboutcompany: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.EmployerDetails.valid) {
      const EmployerDetails = {
        name: this.EmployerDetails.value.name,
        companyname: this.EmployerDetails.value.companyname,
        aboutcompany: this.EmployerDetails.value.aboutcompany
      };

      this.commonService.saveEmployerDetailsData(EmployerDetails).subscribe((res: any) => {
        this.EmployerDetailsObj = res;
      });
      
    }
  }
}
