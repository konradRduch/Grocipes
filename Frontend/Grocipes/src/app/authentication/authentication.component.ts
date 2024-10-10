import { Component, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthResponseData, AuthService } from '../service/auth.service';
import { jwtDecode } from "jwt-decode";
import { Router } from '@angular/router';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent  {

  @Output() authMode: boolean = true;

  constructor(private authService: AuthService, private router: Router){}

  onSwitchMode() {
    this.authMode = !this.authMode;
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;
   
    let authObs: Observable<AuthResponseData>;

    if (this.authMode) {
      authObs = this.authService.login(email, password);
    } else {
      authObs = this.authService.signUp(email, password);
    }

    authObs.subscribe(
      resData => {
         this.router.navigate(['/home']);
      },
      errorMessage => {
        console.log(errorMessage);
      }
    );
    form.reset();
  }


}


