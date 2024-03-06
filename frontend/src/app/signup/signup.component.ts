import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from 'src/app/services/common.services';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers:[CommonService],
})
export class SignupComponent {
  signupForm: FormGroup;
  signupFormData:any;

  constructor(private fb: FormBuilder, private http: HttpClient, private commonService: CommonService, private router: Router) {
    this.signupForm = this.fb.group({
      user_type: ['JOB_SEEKER', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.signupForm.valid) {
      const signupFormData = {
        user_type: this.signupForm.value.user_type,        
        email: this.signupForm.value.email,
        password: this.signupForm.value.password
      };

      this.commonService.saveSignUpData(signupFormData).subscribe((res: any) => {
        this.signupFormData = res;
      });
      this.router.navigateByUrl('/login');
    }
  }
}
