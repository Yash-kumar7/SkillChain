import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService{
 
  
  constructor(private http: HttpClient) {}

  saveJobPostingData(jobPostingData: any) {
    alert("SaveJobPost");
    const urlSave = 'http://localhost:8080/jobpost';
    console.log(jobPostingData)
    return this.http.post(urlSave, jobPostingData);
  }

  saveEmployerDetailsData(saveEmployerDetailsData: any) {
    alert("saveEmployerDetailsData");
    const urlSave = 'http://localhost:8080/recruiterDetails';
    console.log(saveEmployerDetailsData)
    return this.http.post(urlSave, saveEmployerDetailsData);
  }

  saveJobSeekerDetailsData(saveJobSeekerDetailsData: any) {
    alert("saveEmployerDetailsData"); 
    const urlSave = 'http://localhost:8080/jobSeekerDetails';
    console.log(saveJobSeekerDetailsData)
    return this.http.post(urlSave, saveJobSeekerDetailsData);
  }

  saveSignUpData(saveJobSeekerDetailsData: any) {
    const urlSave = 'http://localhost:8080/api/auth/signup';
    console.log(saveJobSeekerDetailsData)
    return this.http.post(urlSave, saveJobSeekerDetailsData);
  }


  // Method for sending a POST request to the login endpoint
  login(username: string, password: string, user_type: string): Observable<any> {
    const url = `http://localhost:8080/api/auth/login`; // Replace with your login endpoint
    const loginData = { username, password, user_type };
    return this.http.post(url, loginData);
  }

}

