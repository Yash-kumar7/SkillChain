import { Injectable, OnInit } from "@angular/core";
import { CanActivate, Router } from "@angular/router";

@Injectable()
export class CheckGuard implements CanActivate{

constructor(private router: Router){}

    canActivate():boolean{
        if(localStorage)
        {
          //  alert("Match Found!"); 
            return true;
           
        }
        else{
            this.router.navigateByUrl('/login');
         alert("You don't have permision to visit this page, please Log in or register!");
            return false;
        }
       
    }

}