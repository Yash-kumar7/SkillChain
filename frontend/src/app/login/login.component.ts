import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonService } from '../services/common.services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup | null = null; // Initialize to null
  public store:any;

  constructor(private formBuilder: FormBuilder,private router: Router, private commonService: CommonService) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      user_type: ['jobseeker', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm) {
      if (this.loginForm.valid) {
        const user_type = this.loginForm.get('user_type')?.value;
        const username = this.loginForm.get('username')?.value;
        const password = this.loginForm.get('password')?.value;

        // Use the CommonService method to send a login request
        this.commonService.login(username, password, user_type).subscribe((response: any) => {
          if (response.success) {
            this.store=localStorage.getItem("isloggedin");
            if (user_type === 'jobseeker') {
          
              this.router.navigateByUrl('app/dashboard');
            } else {
              this.router.navigateByUrl('app/employerdashboard');
            }
          } else {
            console.log('Login failed. Invalid credentials.');
          }
        });
      }
    }
  }
}
