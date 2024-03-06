import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { CheckGuard } from './router-guard/check-guard';


import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExplorecandidatesComponent } from './employer/explorecandidates/explorecandidates.component';
import { EmployerdashboardComponent } from './employer/employerdashboard/employerdashboard.component';
import { JobpostingComponent } from './employer/jobposting/jobposting.component';
import { ChildComponent } from './child/child.component';
import { EmployerdetailsComponent } from './employer/employerdetails/employerdetails.component';
import { JobviewComponent } from './employer/jobview/jobview.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'signup',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'app',
    component: ChildComponent,canActivate:[CheckGuard],
    children: [
      {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full',
      },
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'explorecandidates',
        component: ExplorecandidatesComponent,
      },
      {
        path: 'jobposting',
        component: JobpostingComponent,
      },
      {
        path: 'jobview',
        component: JobviewComponent,
      },
      {
        path: 'employerdashboard',
       component: EmployerdashboardComponent,
      },
      {
        path: 'employerdetails',
        component: EmployerdetailsComponent,
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      
    ]
  },
  {
    path: '**',
    component: PagenotfoundComponent,
  }
 ];

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    PagenotfoundComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    ExplorecandidatesComponent,
    EmployerdashboardComponent,
    JobpostingComponent,
    ChildComponent,
    EmployerdetailsComponent,
    JobviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [CheckGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
