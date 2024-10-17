import { Component, inject, OnInit, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthResponseData, AuthService } from '../service/auth.service';
import { jwtDecode } from "jwt-decode";
import { Router } from '@angular/router';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css'],
})
export class AuthenticationComponent {

  today = inject(NgbCalendar).getToday();

  model: NgbDateStruct | undefined;

  @Output() authMode: boolean = true;
  gender: any;

  constructor(private authService: AuthService, private router: Router) { }


  convertToDate(dateStruct: NgbDateStruct): Date {
    return new Date(dateStruct.year, dateStruct.month - 1, dateStruct.day);
  }



  getFormattedDate(): string | null {
    if (!this.model) return null;
    const date = this.convertToDate(this.model);
    return new Intl.DateTimeFormat('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric' }).format(date);
  }


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
      const name = form.value.name;
      const surname = form.value.surname;
      const birthday =
        form.value.dp.year + "-" +
        form.value.dp.month.toString().padStart(2, '0') + "-" +
        form.value.dp.day.toString().padStart(2, '0');
        
      const gender = form.value.gender;
      authObs = this.authService.signUp(name, surname, birthday, gender, email, password);
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


