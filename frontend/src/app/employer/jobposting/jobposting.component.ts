import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from 'src/app/services/common.services';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-jobposting',
  templateUrl: './jobposting.component.html',
  styleUrls: ['./jobposting.component.css'],
  providers:[CommonService],
})
export class JobpostingComponent {
  jobposting: FormGroup;
  jobpostingObj: any;

  constructor(private fb: FormBuilder, private commonService: CommonService, private http: HttpClient) {
    this.jobposting = this.fb.group({
      jobtitle: ['', Validators.required],
      companyname: ['', Validators.required],
      profile: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.jobposting.valid) {
      const jobPostingData = {
        jobtitle: this.jobposting.value.jobtitle,
        companyname: this.jobposting.value.companyname,
        profile: this.jobposting.value.profile
      };

      this.commonService.saveJobPostingData(jobPostingData).subscribe((res: any) => {
        this.jobpostingObj = res;
      });
      
    }
  }
}
